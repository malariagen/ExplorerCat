package net.explorercat.cql.selection.query.translators.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionResult;
import net.explorercat.cql.selection.limiters.Limiter;
import net.explorercat.cql.selection.limiters.LimiterType;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.query.translators.QueryExecutionException;
import net.explorercat.cql.selection.query.translators.StorageLayerQuery;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.data.Catalog;
import net.explorercat.data.EntityPropertyAugmenter;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.mysql.QueryableDataEntityBuilderMySQL;
import net.explorercat.dataaccess.mysql.QueryableDataEntityDAOMySQL;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Storage layer query that can be executed against a MySQL DB. This query
 * executes the query as a prepared statement and it is supported by the entity
 * MySQL DAO.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Mar 2011
 * 
 */
public class StorageLayerQueryMySQL implements StorageLayerQuery
{
    // Logging
    private static Log log = LogFactory.getLog(StorageLayerQueryMySQL.class);

    private String sqlPreparedStatement;
    private List<Object> preparedStatementParameters;
    private Query originalQuery;
    private QueryableDataEntityDAOMySQL entityDAO;

    /**
     * Creates a new query that can be executed against a MySQL database.
     * 
     * @param sqlPreparedStatement Code of the prepared statement that will be
     *        executed.
     * @param preparedStatementParameters Parameters for the prepared statement
     *        (values).
     * @param originalQuery Query that has been translated into MySQL code.
     * @param entityDAO MySQL DAO in charge of providing data for the entity we
     *        are querying.
     */

    public StorageLayerQueryMySQL(String sqlPreparedStatement, List<Object> preparedStatementParameters,
				  Query originalQuery, QueryableDataEntityDAOMySQL entityDAO)
    {
	this.sqlPreparedStatement = sqlPreparedStatement;
	this.preparedStatementParameters = preparedStatementParameters;
	this.originalQuery = originalQuery;
	this.entityDAO = entityDAO;
    }

    @Override
    public Selection executeQuery() throws QueryExecutionException
    {
	SQLDataConnector sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();

	try
	{
	    if(log.isDebugEnabled())
		log.debug("Executing query against storage layer (MySQL): " + sqlPreparedStatement);

	    // Turn on the streaming mode, we will stream the data from the MySQL server.
	    sqlConnector.setRowStreamingMode(true);
	    sqlConnector.createPreparedStatement(sqlPreparedStatement);
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(preparedStatementParameters);

	    // Check if we need to extend the original dictionary with variable definitions.
	    PropertyDictionary augmentedDictionary = null;

	    if(originalQuery.hasVariables())
	    {
		EntityPropertyAugmenter augmenter = new EntityPropertyAugmenter(entityDAO, originalQuery);
		augmentedDictionary = augmenter.getDictionaryAugmentedWithVariables();
	    }
	    else
		augmentedDictionary = entityDAO.getPropertyDictionary();

	    // An entity builder will be used to create the augmented data entities from the result set.
	    // Note this builder uses the augmented property dictionary (a dictionary that could have been 
	    // extended with the variables defined by the user).

	    Catalog entityCatalog = entityDAO.getEntityCatalog();
	    String entityType = entityDAO.getEntityType();
	    QueryableDataEntityBuilderMySQL entityBuilder = null;
	    entityBuilder = new QueryableDataEntityBuilderMySQL(entityType, entityCatalog, augmentedDictionary);

	    List<QueryableDataEntity> entities = entityBuilder.buildEntitiesFromResultSet(resultSet);

	    // Create the returning selection.
	    String selectionLabel = originalQuery.getLabel();
	    EntitySorter selectionSorter = originalQuery.getResultingSelectionSorter();

	    SelectionResult selection = new SelectionResult(selectionLabel, entityType, entityCatalog, entities,
							    augmentedDictionary, selectionSorter, false);

	    // Apply the limiter if we couldn't translate it to SQL.	    
	    Limiter limiter = originalQuery.getResultingSelectionLimiter();
	    if(limiter != null && limiter.getType() == LimiterType.BOTTOM_LIMITER)
		selection.applyLimiter(limiter);

	    return selection;
	}
	catch(SQLException e)
	{
	    throw new QueryExecutionException("Error selecting entities", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }
}
