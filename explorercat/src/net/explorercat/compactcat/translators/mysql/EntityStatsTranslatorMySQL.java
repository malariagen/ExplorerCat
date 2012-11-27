package net.explorercat.compactcat.translators.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.compactcat.util.AttributeStatsCalculator;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.DataType;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Translates a set of statistics calculated for the attributes of an entity
 * into a MySQL representation (basically a table).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 8 Feb 2011
 */

public class EntityStatsTranslatorMySQL
{
    // Logging
    private static Log log = LogFactory.getLog(EntityStatsTranslatorMySQL.class);

    // Initial capacity for the list that will contain the values
    // of each attribute when calculating the statistics.
    private static final int ATTRIBUTE_VALUES_LIST_CAPACITY = 25000;

    private EntityTableCreatorMySQL tableCreator;
    private SQLDataConnector sqlConnector;

    /**
     * Creates a MySQL stats translator.
     * 
     * @param tableCreator The table creator used to generate the data tables of
     *        the entities being translated.
     */

    public EntityStatsTranslatorMySQL(EntityTableCreatorMySQL tableCreator)
    {
	this.tableCreator = tableCreator;
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    /**
     * Creates the table that will contain the statistics for the attributes of
     * the entity. The table registers the following:
     * 
     * <code>
     * - count (number of non-null instances).
     * - minimum
     * - maximum
     * - standard deviation
     * - variance
     * - sum 
     * - first quartile 
     * - median 
     * - third quartile 
     * - average 	
     * </code>
     * 
     * @param entityDefinition Definition of the entity being translated.
     * @param parameters MySQL parameters provided to guide the translation.
     */

    public void createEntityStatsTable(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	if(log.isDebugEnabled())
	    log.debug("Creating entity stats table for " + entityDefinition.getName());

	String tableName = getEntityStatsTableName(entityDefinition);

	String sql = "CREATE TABLE " + tableName + "(" + "attribute_name varchar(255) NOT NULL," + StatsType.COUNT
		     + " int," + StatsType.MIN + " float," + StatsType.MAX + " float," + StatsType.STDDEV + " float,"
		     + StatsType.VAR + " float," + StatsType.SUM + " float," + StatsType.FIRST_QUARTILE + " float,"
		     + StatsType.MEDIAN + " float," + StatsType.THIRD_QUARTILE + " float," + StatsType.AVG + " float"
		     + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Attribute Stats'";
	try
	{
	    sqlConnector.closeConnection();
	    sqlConnector.executeUpdate(sql);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error creating the stats table: " + sql, e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Populates the statistics table for the given entity definition,
     * calculating the statistics on the fly (it could take a while).
     * 
     * @param entityDefinition The definition of the entity being translated.
     */

    public void calculateStatsAndPopulateStatsTable(EntityDefinition entityDefinition) throws TranslationException
    {
	List<AttributeDefinition> attributes = entityDefinition.getAttributes();
	List<Float> attributeValues = new ArrayList<Float>(ATTRIBUTE_VALUES_LIST_CAPACITY);

	// Calculate the stats for each attribute.
	for(AttributeDefinition attribute : attributes)
	{
	    // We reuse the array to speed up the process.
	    attributeValues.clear();
	    DataType type = attribute.getType();

	    if((type.isNumericArray() || type.isNumericScalar()))
	    {
		if(!type.isArray())
		    retrieveRegularAttributeValues(entityDefinition, attribute.getName(), attributeValues);
		else
		    retrieveArrayAttributeValues(entityDefinition, attribute.getName(), attributeValues);

		if(log.isDebugEnabled())
		    log.debug("Calculating Stats for attribute: " + attribute.getName());

		AttributeStatsCalculator calculator = new AttributeStatsCalculator();
		Map<StatsType, Float> statsMap = calculator.calculateStats(attributeValues);
		registerStatsForAttribute(entityDefinition, attribute.getName(), statsMap);
	    }

	    // If the attribute is not numeric (regular or an array), 
	    // we just set all the stats values to null (including the count).
	    else
	    {
		Map<StatsType, Float> statsMap = new HashMap<StatsType, Float>();

		for(StatsType currentStats : StatsType.values())
		    statsMap.put(currentStats, null);

		registerStatsForAttribute(entityDefinition, attribute.getName(), statsMap);
	    }
	}
    }

    /**
     * Populates the statistics table for the given entity definition with null
     * values (no statistics are calculated).
     * 
     * @param entityDefinition The definition of the entity being translated.
     */

    public void populateStatsTableWithNullValues(EntityDefinition entityDefinition) throws TranslationException
    {
	List<AttributeDefinition> attributes = entityDefinition.getAttributes();

	for(AttributeDefinition attribute : attributes)
	{
	    Map<StatsType, Float> statsMap = new HashMap<StatsType, Float>();

	    for(StatsType currentStats : StatsType.values())
		statsMap.put(currentStats, null);

	    registerStatsForAttribute(entityDefinition, attribute.getName(), statsMap);
	}
    }

    /**
     * Auxiliary method that registers the stats for a given attribute in the
     * database (inserts a row into the stats table).
     */

    private void registerStatsForAttribute(EntityDefinition entityDefinition, String attributeName,
					   Map<StatsType, Float> statsMap) throws TranslationException
    {
	String tableName = getEntityStatsTableName(entityDefinition);
	StringBuilder sql = new StringBuilder();
	StringBuilder values = new StringBuilder();

	try
	{
	    Set<StatsType> stats = statsMap.keySet();

	    sql.append("INSERT INTO ").append(tableName).append("(attribute_name,");
	    values.append("'").append(attributeName).append("',");

	    for(StatsType currentStats : stats)
	    {
		sql.append(currentStats).append(",");
		values.append(statsMap.get(currentStats)).append(",");
	    }

	    // Remove the last comma.
	    sql.deleteCharAt(sql.length() - 1);
	    values.deleteCharAt(values.length() - 1);

	    // Compose the final query.
	    sql.append(") VALUES(").append(values).append(")");

	    sqlConnector.closeConnection();
	    sqlConnector.executeUpdate(sql.toString());
	}
	catch(SQLException e)
	{
	    e.printStackTrace();
	    throw new TranslationException("Error registering stats for attribute " + attributeName + ": " + sql);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Retrieves all the values associated with the given array attribute (for
     * all the entity instances).Notice that the attribute has to be numeric and
     * that we are placing the values in the given list of floats numbers
     * (integers are converted).
     */

    private void retrieveArrayAttributeValues(EntityDefinition entityDefinition, String attributeName,
					      List<Float> attributeValues) throws TranslationException
    {
	String tableName = tableCreator.getEntityTableBaseName(entityDefinition) + "_" + attributeName;
	retrieveNumericAttributeValuesFromDB(tableName, attributeName, attributeValues);
    }

    /**
     * Retrieves all the values associated with the given attribute (for all the
     * entity instances). Notice that the attribute has to be numeric and that
     * we are placing the values in the given list of floats numbers (integers
     * are converted).
     */

    private void retrieveRegularAttributeValues(EntityDefinition entityDefinition, String attributeName,
						List<Float> attributeValues) throws TranslationException
    {
	String tableName = tableCreator.getEntityTableBaseName(entityDefinition);
	retrieveNumericAttributeValuesFromDB(tableName, attributeName, attributeValues);
    }

    /**
     * Auxiliary method that retrieves numeric attribute values from the DB.
     */

    private void retrieveNumericAttributeValuesFromDB(String tableName, String attributeName,
						      List<Float> attributeValues) throws TranslationException
    {
	try
	{
	    String sql = "SELECT " + attributeName + " FROM " + tableName;
	    sqlConnector.closeConnection();
	    ResultSet result = sqlConnector.executeQuery(sql);

	    while(result.next())
		attributeValues.add(result.getFloat(1));
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error retrieving values for attribute " + attributeName);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Gets the name of the stats table for the given entity.
     */

    private String getEntityStatsTableName(EntityDefinition entityDefinition)
    {
	String suffix = PropertyLookup.getStatsTableNameSuffix();
	String separator = PropertyLookup.getTableNameSeparator();

	return tableCreator.getEntityTableBaseName(entityDefinition) + separator + suffix;
    }
}
