package net.explorercat.dataaccess.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.transformers.ArrayReferenceReplacer;
import net.explorercat.cql.expressions.transformers.ExpressionTransformer;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.visitors.ArrayReferenceCollector;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.query.QueryCondition;
import net.explorercat.cql.selection.query.translators.StorageLayerQuery;
import net.explorercat.cql.selection.query.translators.mysql.ExpressionTranslatorMySQL;
import net.explorercat.cql.selection.query.translators.mysql.QueryTranslatorMySQL;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.PropertyStatsLookup;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PropertyLookup;
import net.explorercat.dataaccess.QueryableDataEntityDAO;
import net.explorercat.dataaccess.mysql.loaders.PropertyDictionaryLoaderMySQL;
import net.explorercat.dataaccess.mysql.loaders.PropertyStatsLoaderMySQL;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.misc.ArchitectureModel;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;
import net.explorercat.util.sql.dataconnectors.mysql.ProductionMySQLDataConnector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the entity DAO that relies on accessing a MySQL DB.
 * Entities won't be cached in memory by this implementation.
 * 
 * A DAO exception is raised if there is any problem accessing the data.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class QueryableDataEntityDAOMySQL implements QueryableDataEntityDAO, Selection
{
    private static Log log = LogFactory.getLog(QueryableDataEntityDAOMySQL.class);

    // Connector that will access the DB.
    protected SQLDataConnector sqlConnector;

    // Type of the entity instances (name).
    private String entityType;

    // Catalog that contains the entity.
    private Catalog catalog;

    // DB table where the entities are stored.
    private String entityTableName;
    private int numEntities;

    // Entity properties and stats.
    private PropertyDictionary propertyDictionary;
    private PropertyStatsLookup propertyStats;

    // Builder that creates data entities from MySQL result sets.
    private QueryableDataEntityBuilderMySQL entityBuilder;

    // Translates a query into a executable storage layer query. 
    private QueryTranslatorMySQL queryTranslator;

    /**
     * Creates a data entity DAO that directly queries a MySQL database. This
     * constructor creates and empty DAO that has to be initialised calling the
     * {@link initialize} method.
     */

    public QueryableDataEntityDAOMySQL() throws DAOException
    {
	this.catalog = null;
	this.entityType = null;
	this.entityTableName = null;
	this.propertyDictionary = null;
	this.numEntities = -1;
	this.entityBuilder = null;
	this.queryTranslator = null;
    }

    @Override
    public void initialize(String entityType, Catalog catalog) throws DAOException
    {
	if(log.isDebugEnabled())
	    log.debug("Creating MySQL entity DAO for " + entityType + ", catalog id: " + catalog.getId());

	this.catalog = catalog;
	this.entityType = entityType;

	this.entityTableName = PropertyLookup.getTableNamePrefix() + PropertyLookup.getTableNameSeparator()
			       + catalog.getId() + PropertyLookup.getTableNameSeparator() + entityType;

	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
	this.queryTranslator = new QueryTranslatorMySQL(this);

	// Load the properties.
	PropertyDictionaryLoaderMySQL dictLoader = new PropertyDictionaryLoaderMySQL(entityType, catalog);
	this.propertyDictionary = dictLoader.loadPropertyTypesDictionary();

	// Lazy initialisation for this field, mark it as not initialised with -1.
	this.numEntities = -1;

	// Initialise the entity builder with the original property dictionary (not extended with variables).
	this.entityBuilder = new QueryableDataEntityBuilderMySQL(entityType, catalog, propertyDictionary);

	// Load the stats.
	PropertyStatsLoaderMySQL statsLoader = new PropertyStatsLoaderMySQL(entityType, catalog);
	this.propertyStats = statsLoader.loadPropertyStats(propertyDictionary);
    }

    @Override
    public List<QueryableDataEntity> findDataEntities(List<Integer> entityIds) throws DAOException
    {
	ArrayList<QueryableDataEntity> entities = new ArrayList<QueryableDataEntity>(entityIds.size());

	try
	{
	    sqlConnector.createPreparedStatement("SELECT * FROM " + entityTableName + " WHERE id = ?");

	    // Execute the prepared statement for each id.
	    for(Integer id : entityIds)
	    {
		ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(id);
		if(resultSet.next())
		    entities.add(entityBuilder.buildEntityFromResultSet(resultSet));
	    }
	}
	catch(SQLException e)
	{
	    log.error("Error trying to retrieve a list of entities " + catalog.getId() + "/" + entityType);
	    throw new DAOException("Error finding the entities", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}

	return entities;
    }

    @Override
    public QueryableDataEntity findDataEntity(int entityId) throws DAOException
    {
	try
	{
	    sqlConnector.createPreparedStatement("SELECT * FROM " + entityTableName + " WHERE id = " + entityId);
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);
	    List<QueryableDataEntity> entities = entityBuilder.buildEntitiesFromResultSet(resultSet);

	    if(entities.size() != 0)
		return entities.get(0);
	    else
		return null;
	}
	catch(SQLException e)
	{
	    log.error("Error trying to retrieve an entity " + catalog.getId() + "/" + entityType + "/" + entityId);
	    throw new DAOException("Error finding the entity", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public String getEntityType()
    {
	return entityType;
    }

    @Override
    public List<String> getEntityPropertyNames()
    {
	return propertyDictionary.getPropertyNames();
    }

    @Override
    public List<DataType> getEntityPropertyTypes()
    {
	return propertyDictionary.getPropertyTypes();
    }

    @Override
    public Iterator<QueryableDataEntity> getIterator() throws DAOException
    {
	try
	{
	    return new QueryableDataEntityIteratorMySQL(entityType, catalog, entityBuilder);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error building a MySQL entity iterator", e);
	}
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Selection interface
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<QueryableDataEntity> getEntities(int numEntities, int offset) throws SelectionException
    {
	ArrayList<QueryableDataEntity> entities = new ArrayList<QueryableDataEntity>();

	try
	{
	    String sql = "SELECT * FROM " + entityTableName + " LIMIT " + offset + "," + numEntities;
	    sqlConnector.closeConnection();
	    sqlConnector.createPreparedStatement(sql);
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);

	    return entityBuilder.buildEntitiesFromResultSet(resultSet);
	}
	catch(SQLException e)
	{
	    log.error("Error selecting a segment of entities");
	    throw new SelectionException("Error selecting a segment of entities", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public QueryableDataEntity getEntityById(int entityId)
    {
	try
	{
	    return findDataEntity(entityId);
	}
	catch(DAOException e)
	{
	    log.error("Error selecting the entity", e);
	    return null;
	}
    }

    @Override
    public List<QueryableDataEntity> collectEntitiesById(Set<Integer> ids)
    {
	try
	{
	    // Convert the set into a list of ids (removing the square brackets).
	    String idsString = ids.toString();
	    idsString = idsString.substring(1, idsString.length() - 1);

	    String sql = "SELECT * FROM " + entityTableName + " WHERE id IN (" + idsString + ")";
	    sqlConnector.createPreparedStatement(sql);
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);

	    return entityBuilder.buildEntitiesFromResultSet(resultSet);
	}
	catch(SQLException e)
	{
	    log.error("Error trying to collect a set of entities by Id, catalog: " + catalog.getId() + "/" + entityType
		      + "/" + e);
	    // In case of error return an empty list.
	    return new ArrayList<QueryableDataEntity>();
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public int getSelectionSize()
    {
	if(numEntities == -1)
	{
	    try
	    {
		sqlConnector.closeConnection();
		sqlConnector.createPreparedStatement("SELECT count(*) FROM " + entityTableName);
		ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);

		if(resultSet.next())
		    numEntities = resultSet.getInt(1);
	    }
	    catch(SQLException e)
	    {
		log.error("Error counting the entities " + entityType + " " + e);
		return 0;
	    }
	    finally
	    {
		sqlConnector.closeConnection();
	    }

	}

	return numEntities;
    }

    @Override
    public String getSortingProperty()
    {
	// Always returns null since is not possible to order the entities on a DAO.
	return null;
    }

    @Override
    public boolean isSortedByPropertyValue()
    {
	// Always returns false since is not possible to order the entities on a DAO.
	return false;
    }

    @Override
    public Iterator<QueryableDataEntity> iterator() throws SelectionException
    {
	try
	{
	    return getIterator();
	}
	catch(DAOException e)
	{
	    throw new SelectionException(e);
	}
    }

    @Override
    public Selection selectEntities(Query query) throws SelectionException
    {
	try
	{
	    // If the query contains references to array properties we have to perform
	    // a 2-stage selection. We check for array references.	    
	    if(log.isDebugEnabled())
		log.debug("Checking for array references in : " + query.getLabel());

	    // Selection source must be this DAO object.
	    Selection querySourceSelection = query.getSourceSelection();
	    PropertyDictionary queryDictionary = querySourceSelection.getPropertyDictionary();

	    // Collect all the array references in the condition expression.
	    ArrayReferenceCollector arrayReferenceCollector = new ArrayReferenceCollector(queryDictionary);
	    query.getCondition().getExpression().acceptVisitor(arrayReferenceCollector);

	    if(arrayReferenceCollector.getCollectedReferenceExpressions().size() > 0)
	    {
		if(log.isDebugEnabled())
		    log.debug("Array references found for " + query.getLabel() + " " + query.toString());

		return performTwoStageSelectionForArrayReferences(query);
	    }
	    else
	    {
		if(log.isDebugEnabled())
		    log.debug("Array references not found for " + query.getLabel() + ", translating into MySQL");

		// Translates the query into a executable representation for MySQL
		StorageLayerQuery storareLayerQuery = queryTranslator.translateQuery(query);
		return storareLayerQuery.executeQuery();
	    }
	}
	catch(ExplorerCatCheckedException e)
	{
	    throw new SelectionException("Error selecting entities for query: " + query, e);
	}
    }

    /**
     * This method executes the selection in two phases to deal with array
     * references. Having the original query Q, we check if the condition of the
     * query references any array element. If that is the case we create a new
     * query Q1. This query will be a copy of Q but we clear the variables map
     * and delete any limiter/sorter. Then we clone the condition expression
     * tree and we apply the a transformer to replace expressions containing
     * references to arrays with boolean literals (true). We execute the query
     * and save the resulting selection as S1. Now we modify the original query
     * (Q) setting the source selection as S1 and we execute it.
     * 
     * @param query The original query to be executed.
     * @return A selection containing the entities selected by the query.
     */

    private Selection performTwoStageSelectionForArrayReferences(Query query) throws ExpressionEvaluationException,
	SelectionException
    {
	if(log.isDebugEnabled())
	    log.debug("Performing two stage selection");

	// Stage 1 : Execute the relaxed query.
	String label = query.getLabel() + "_relaxed";

	// Selection source must be this DAO object.
	Selection querySourceSelection = query.getSourceSelection();
	PropertyDictionary queryDictionary = querySourceSelection.getPropertyDictionary();

	// Apply the transformation (replaces array references expressions with a boolean literal (true)).
	Expression conditionExpression = query.getCondition().getExpression().cloneExpressionTree();
	Expression substituteExpression = new LiteralExpression(new BooleanValue(true), null);
	ExpressionTransformer transformer = new ArrayReferenceReplacer(queryDictionary, substituteExpression);
	conditionExpression = conditionExpression.applyTransformation(transformer);

	// Create the relaxed query and perform the selection (S1).
	QueryCondition relaxedCondition = new QueryCondition(conditionExpression);
	Query relaxedQuery = new Query(label, relaxedCondition, querySourceSelection,
				       new HashMap<String, Expression>(), null, null);
	//if(log.isDebugEnabled())
	//    log.debug("Executing relaxed query: " + label);

	Selection relaxedSelection = relaxedQuery.performSelection();

	// Stage 2 : Execute the original query over the previous selection.		
	if(log.isDebugEnabled())
	    log.debug("Executing original query against relaxed selection");

	query.setSourceSelection(relaxedSelection);
	return query.performSelection();
    }

    @Override
    public void sortByPropertyValue(EntitySorter sorter)
    {
	throw new UnsupportedOperationException("sortByPropertyValue is not supported by DAOs");
    }

    @Override
    public float getStatsForVariable(String variableName, Expression variableExpression, StatsType statsType)
	throws StatsCalculationException
    {
	try
	{
	    // To calculate the median or any quartile is a special case 
	    // since doing it in MySQL is very inefficient.
	    if(statsType == StatsType.MEDIAN || statsType == StatsType.FIRST_QUARTILE
	       || statsType == StatsType.THIRD_QUARTILE)
	    {
		return calculateQuartileForVariable(variableExpression, statsType);
	    }

	    ExpressionTranslatorMySQL expressionTranslator = new ExpressionTranslatorMySQL();
	    String varSQL = expressionTranslator.translateIntoPreparedStatement(variableExpression);
	    List<Object> varParameters = expressionTranslator.translateParametersForPreparedStatement(variableExpression);

	    String sqlQuery = "SELECT " + statsType.translateToSQL() + "(" + varSQL + ")" + " FROM " + entityTableName;

	    sqlConnector.closeConnection();
	    sqlConnector.createPreparedStatement(sqlQuery);

	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(varParameters);
	    resultSet.next();

	    return resultSet.getFloat(1);
	}
	catch(SQLException e)
	{
	    log.error("Error trying go get stats for variable : " + variableName + "/" + statsType + " " + e);
	    throw new StatsCalculationException("Unable to access the stats value for " + variableName + "/"
						+ statsType, e);
	}
	catch(TranslationException e)
	{
	    log.error("Error trying go get stats for variable : " + variableName + "/" + statsType + " " + e);
	    throw new StatsCalculationException("Unable to access the stats value for " + variableName + "/"
						+ statsType, e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Calculates a specific quartile value for the given variable expression.
     * 
     * @param variableExpression The expression that will be used to calculate
     *        the variable value for each entity.
     * @param statsType The type of stats to calculate (FIRST_QUARTILE, MEDIAN,
     *        etc).
     * @return The quartile value given by statswType for the variable.
     */

    private float calculateQuartileForVariable(Expression variableExpression, StatsType statsType) throws SQLException,
	TranslationException
    {
	int totalCount = 0;

	ExpressionTranslatorMySQL expressionTranslator = new ExpressionTranslatorMySQL();
	String varSQL = expressionTranslator.translateIntoPreparedStatement(variableExpression);
	List<Object> varParameters = expressionTranslator.translateParametersForPreparedStatement(variableExpression);

	// Get the count of rows for the given expression.
	String sqlCountQuery = "SELECT count(" + varSQL + ") FROM " + entityTableName;
	sqlConnector.closeConnection();
	sqlConnector.createPreparedStatement(sqlCountQuery);

	ResultSet countResultSet = sqlConnector.executePreparedStatementAsQuery(varParameters);
	countResultSet.next();

	totalCount = countResultSet.getInt(1);

	// Calculate the quartile position in the results.
	int quartile = 50;
	float quartilePosition = 0;

	if(statsType == StatsType.MEDIAN)
	    quartile = 50;
	else if(statsType == StatsType.FIRST_QUARTILE)
	    quartile = 25;
	else
	    quartile = 75;

	quartilePosition = totalCount * quartile / 100.F;

	// Get the values for the expression.	
	String sqlQuery = "SELECT (" + varSQL + ") AS 'stats' FROM " + entityTableName + " ORDER BY stats";
	sqlConnector.closeConnection();
	sqlConnector.createPreparedStatement(sqlQuery);

	ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(varParameters);

	// Iterate till get the quartile position.	
	int cursorPosition = 1;
	int stopPosition = (int) quartilePosition;
	while(resultSet.next() && cursorPosition < quartilePosition)
	    ++cursorPosition;

	// Finally get the value checking if we have to interpolate.
	// (We have to do that if the quartile position is a whole number).
	float quartileValue = resultSet.getFloat(1);
	if(quartilePosition - stopPosition == 0)
	{
	    if(resultSet.next())
	    {
		quartileValue += resultSet.getFloat(1);
		quartileValue /= 2;
	    }
	}

	sqlConnector.closeConnection();
	return quartileValue;
    }

    @Override
    public float getStatsForProperty(String propertyName, StatsType statsType) throws StatsCalculationException
    {
	return propertyStats.getPropertyStats(propertyName, statsType);
    }

    @Override
    public boolean hasEntityProperty(String propertyName)
    {
	return propertyDictionary.hasProperty(propertyName);
    }

    @Override
    public Catalog getEntityCatalog()
    {
	return catalog;
    }

    @Override
    public Set<Integer> getEntityIds() throws SelectionException
    {
	try
	{
	    SQLDataConnector sqlProvider = new ProductionMySQLDataConnector();
	    String sqlQuery = "SELECT id FROM " + entityTableName;

	    // Execute the query as a prepared statement.
	    sqlProvider.createPreparedStatement(sqlQuery);
	    ResultSet resultSet = sqlProvider.executePreparedStatementAsQuery(null);

	    // Get all the ids from the resultset.
	    HashSet<Integer> entityIds = new HashSet<Integer>();

	    while(resultSet.next())
		entityIds.add(resultSet.getInt("id"));

	    return entityIds;
	}
	catch(SQLException e)
	{
	    throw new SelectionException("Error selecting entity ids", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public DataType getEntityPropertyType(String propertyName)
    {
	return propertyDictionary.getPropertyType(propertyName);
    }

    @Override
    public PropertyDictionary getPropertyDictionary()
    {
	return propertyDictionary;
    }

    @Override
    public boolean isSortedInAscendingOrder()
    {
	return true;
    }

    @Override
    public String getEntityPropertyDescription(String propertyName)
    {
	return propertyDictionary.getPropertyDescription(propertyName);
    }

    @Override
    public List<String> getEntityPropertyDescriptions()
    {
	return propertyDictionary.getPropertyDescriptions();
    }

    @Override
    public String getSelectionLabel()
    {
	return entityType;
    }

    @Override
    public long getSizeInBytes()
    {
	// Only an estimation.
	return (8 + propertyDictionary.getPropertyTypes().size() * 3) * ArchitectureModel.getReferenceSizeInBytes();
    }

    @Override
    public boolean isCachedInMemory()
    {
	return false;
    }

    @Override
    public String getStringKey()
    {
	return "[DAO]";
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities()
    {
	String errorMessage = "Error trying to get a copy of a selection that is backed by a DB DAO";
	log.error(errorMessage);
	throw new RuntimeException("Incoherent execution flow: " + errorMessage);
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities(String label)
    {
	String errorMessage = "Error trying to get a copy of a selection that is backed by a DB DAO";
	log.error(errorMessage);
	throw new RuntimeException("Incoherent execution flow: " + errorMessage);
    }

    // Out of the interface.

    /**
     * Gets the name of the table that stores the entity instances accessed by
     * this DAO.
     */

    public String getEntityTableName()
    {
	return PropertyLookup.getTableNamePrefix() + PropertyLookup.getTableNameSeparator() + catalog.getId()
	       + PropertyLookup.getTableNameSeparator() + entityType;
    }
}
