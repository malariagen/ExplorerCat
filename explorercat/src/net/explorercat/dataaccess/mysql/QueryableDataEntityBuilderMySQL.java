package net.explorercat.dataaccess.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.types.ArrayValue;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IntegerValue;
import net.explorercat.cql.types.NullValue;
import net.explorercat.cql.types.RealValue;
import net.explorercat.cql.types.StringValue;
import net.explorercat.data.Catalog;
import net.explorercat.data.DataEntity;
import net.explorercat.data.PropertyDefinition;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.PropertyLookup;
import net.explorercat.util.misc.DateUtils;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper class that builds data entities from a MySQL result set. When building
 * an entity, it automatically gets the values for all the properties present in
 * the given property dictionary that are not arrays. Array values are added to
 * the entities afterwards (but automatically) by means of the update array
 * properties methods.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class QueryableDataEntityBuilderMySQL
{
    private static Log log = LogFactory.getLog(QueryableDataEntityDAOMySQL.class);

    private final static int ID_COLUMN_INDEX = 1;
    private final static int ARRAY_VALUE_COLUMN_INDEX = 2;

    private String entityType;
    private Catalog catalog;
    private PropertyDictionary propertyDictionary;
    private SQLDataConnector dbConnector;

    /**
     * Creates a data entity builder that will build entities based on the data
     * provided by a result set and the properties registered in a dictionary.
     * 
     * @param entityType The type of entity to build.
     * @param catalogId The id of the catalog that contains the entities.
     * @param propertyDictionary Dictionary that describes all the entity
     *        properties.
     */

    public QueryableDataEntityBuilderMySQL(String entityType, Catalog catalog, PropertyDictionary propertyDictionary)
    {
	this.entityType = entityType;
	this.catalog = catalog;
	this.propertyDictionary = propertyDictionary;
	this.dbConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    /**
     * Extracts a list of entities from the given DB result set.
     * 
     * @param resultSet The result whose current rows represent data entities.
     * @return A list containing all the entities extracted from the result set.
     * @throws SQLException If there is a problem building the entities.
     */

    public List<QueryableDataEntity> buildEntitiesFromResultSet(ResultSet resultSet) throws SQLException
    {
	List<DataEntity> entities = new ArrayList<DataEntity>();

	while(resultSet.next())
	{
	    if((entities.size() + 1) % 25000 == 0 && log.isDebugEnabled())
		log.debug((entities.size() + 1) + " " + this.entityType + " entities processed");

	    try
	    {
		// Create the empty entity.
		int entityId = resultSet.getInt(PropertyLookup.getUniqueIdAttribute());
		DataEntity entity = new DataEntity(entityId, propertyDictionary);

		// Iterate through all the regular properties adding the data value to the entity.	
		registerScalarPropertiesFromResultSet(entity, resultSet);
		entities.add(entity);
	    }
	    catch(SQLException e)
	    {
		log.error("Error creating a data entity from a result set " + catalog.getId() + "/" + entityType);
		throw e;
	    }

	}

	if(log.isDebugEnabled())
	    log.debug("TOTAL : " + entities.size() + " " + this.entityType + " entities processed");

	// Register all the array property values.
	updateEntitiesWithArrayProperties(entities, entityType);

	// TERRIBLE HACK!! TODO Remove this frightening shortcut.
	return (List<QueryableDataEntity>) (List) entities;
    }

    /**
     * Extracts a single entity from the current row of the given DB result set.
     * 
     * @param resultSet The result whose current row represent the data entity.
     * @return The data entity extracted from the current row of the result set.
     * @throws SQLException If there is a problem building the entity.
     */

    public QueryableDataEntity buildEntityFromResultSet(ResultSet resultSet) throws SQLException
    {
	try
	{
	    // Create the empty entity.
	    int entityId = resultSet.getInt(PropertyLookup.getUniqueIdAttribute());
	    DataEntity entity = new DataEntity(entityId, propertyDictionary);

	    // Iterate through all the regular properties adding the data value to the entity.	
	    registerScalarPropertiesFromResultSet(entity, resultSet);

	    // Register all the array property values (we wrap the entity into a list).
	    List<DataEntity> entities = new ArrayList<DataEntity>(1);
	    entities.add(entity);
	    updateEntitiesWithArrayProperties(entities, entityType);

	    return entity;
	}
	catch(SQLException e)
	{
	    log.error("Error creating a data entity from a result set " + catalog.getId() + "/" + entityType);
	    throw e;
	}
    }

    /**
     * Registers the values of all the scalar properties for the given entity,
     * extracting them from the current position of a SQL result set.
     * 
     * @param entity Entity that will be populated with the property values.
     * @param resultSet Result set positioned in the row that contains the
     *        property values of the entity.
     * @throws SQLException If there is a problem accessing result set values.
     */

    private void registerScalarPropertiesFromResultSet(DataEntity entity, ResultSet resultSet) throws SQLException
    {
	List<String> properties = propertyDictionary.getPropertyNames();

	for(String propertyName : properties)
	{
	    try
	    {
		DataType propertyType = propertyDictionary.getPropertyType(propertyName);

		switch(propertyType.translateToIntegralType())
		{
		    case BOOLEAN:
			boolean boolValue = resultSet.getBoolean(propertyName);
			if(!resultSet.wasNull())
			    entity.addPropertyValue(propertyName, boolValue);
			break;

		    case INTEGER:
			int intValue = resultSet.getInt(propertyName);
			if(!resultSet.wasNull())
			    entity.addPropertyValue(propertyName, intValue);
			break;

		    case REAL:
			float realValue = resultSet.getFloat(propertyName);
			if(!resultSet.wasNull())
			    entity.addPropertyValue(propertyName, realValue);
			break;

		    case STRING:
			String stringValue = resultSet.getString(propertyName);
			if(!resultSet.wasNull())
			    entity.addPropertyValue(propertyName, stringValue);
			break;

		    case DATE:
			String dateValue = resultSet.getString(propertyName);
			if(!resultSet.wasNull())
			{
			    int year = Integer.parseInt(dateValue.substring(0, 4));
			    int month = Integer.parseInt(dateValue.substring(5, 7));
			    int day = Integer.parseInt(dateValue.substring(8, 10));
			    entity.addPropertyValue(propertyName, new GregorianCalendar(year, month, day));
			}
			break;

		    // Special case: We don't set array properties in this method. 
		    // Update methods for arrays have to be used afterwards.
		    case ARRAY:
			break;

		    default:
			throw new IllegalArgumentException("Unknown property type: " + propertyType);
		}
	    }
	    catch(SQLException e)
	    {
		log.error("Error accessing a data value for entity " + entityType + "/" + propertyName);
		throw e;
	    }
	}
    }

    /**
     * Updates all the array properties for the given list of entity instances,
     * getting the array values from the database.
     */

    private void updateEntitiesWithArrayProperties(List<DataEntity> entities, String entityType) throws SQLException
    {
	List<String> propertyNames = propertyDictionary.getPropertyNames();

	for(String currentPropertyName : propertyNames)
	{
	    if(propertyDictionary.getPropertyType(currentPropertyName).isArray())
	    {
		PropertyDefinition property = propertyDictionary.getPropertyDefinition(currentPropertyName);

		if(log.isDebugEnabled())
		    log.debug("Updating " + entities.size() + " entities with array values for " + property.getName());

		// TODO Check the size of the table and the relationship with the entities table
		// in order to decide if getting the data in chunks or by id would be a better option.

		Map<Integer, List<DataValue>> arrayValuesMap = retrieveArrayPropertyValueMap(entityType, property);
		String propertyName = property.getName();

		for(DataEntity currentEntity : entities)
		{
		    List<DataValue> arrayValues = arrayValuesMap.get(currentEntity.getId());
		    if(arrayValues != null)
			currentEntity.addPropertyValue(propertyName, new ArrayValue(arrayValues));
		}
	    }
	}
    }

    /**
     * Updates all the array properties for the given entity instance, getting
     * the array values from the database. Notice that the duplication of code
     * and functionality is needed to boost performance although some
     * refactoring would be highly beneficial.
     */

    private void updateEntityWithArrayProperty(DataEntity entity, String entityType) throws SQLException
    {
	List<String> propertyNames = propertyDictionary.getPropertyNames();

	for(String currentPropertyName : propertyNames)
	{
	    if(propertyDictionary.getPropertyType(currentPropertyName).isArray())
	    {
		PropertyDefinition property = propertyDictionary.getPropertyDefinition(currentPropertyName);
		DataType propertyType = property.getType();
		String arrayTableName = composeArrayTableName(property, entityType);

		try
		{
		    dbConnector.closeConnection();
		    dbConnector.createPreparedStatement("SELECT * FROM " + arrayTableName + " WHERE "
							+ PropertyLookup.getUniqueIdAttribute() + " = ?");

		    ResultSet resultSet = dbConnector.executePreparedStatementAsQuery(entity.getId());
		    List<DataValue> arrayValues = new ArrayList<DataValue>();

		    while(resultSet.next())
			arrayValues.add(extractCurrentArrayValueFromResultSet(resultSet, propertyType));

		    entity.addPropertyValue(currentPropertyName, new ArrayValue(arrayValues));
		}
		catch(SQLException e)
		{
		    log.error("Error retrieving array properties for " + arrayTableName);
		    throw e;
		}
		finally
		{
		    dbConnector.closeConnection();
		}
	    }
	}
    }

    /**
     * Composes the name of the table that contains the data for an array
     * property.
     * 
     * @param property The name of the array property.
     * @param entityType Type of the entity that contains the entity.
     * @return The name of the DB table that contains the array data.
     */

    private String composeArrayTableName(PropertyDefinition property, String entityType)
    {
	return PropertyLookup.getTableNamePrefix() + PropertyLookup.getTableNameSeparator() + catalog.getId()
	       + PropertyLookup.getTableNameSeparator() + entityType + PropertyLookup.getTableNameSeparator()
	       + property.getName();
    }

    /**
     * Gets a value map for an array property (id to values).
     * 
     * @throws SQLException
     */

    private Map<Integer, List<DataValue>> retrieveArrayPropertyValueMap(String entityType, PropertyDefinition property)
	throws SQLException
    {
	String arrayTableName = composeArrayTableName(property, entityType);

	if(log.isDebugEnabled())
	    log.debug("Building array values map from " + arrayTableName + " (" + property.getName() + ")");

	try
	{
	    dbConnector.closeConnection();
	    dbConnector.createPreparedStatement("SELECT * FROM " + arrayTableName);
	    ResultSet resultSet = dbConnector.executePreparedStatementAsQuery(null);

	    return extractArrayValuesMapFromResultSet(resultSet, property.getType().getContainedType());
	}
	catch(SQLException e)
	{
	    log.error("Error retrieving array properties for " + arrayTableName);
	    throw e;
	}
	finally
	{
	    dbConnector.closeConnection();
	}
    }

    /**
     * Builds a map for all the array values contained in the result set.
     * 
     * @param resultSet The SQL result set that contains the data.
     * @param type The array contained data type.
     * @return A map that relates each entity id with an array of data values.
     */

    private Map<Integer, List<DataValue>> extractArrayValuesMapFromResultSet(ResultSet resultSet, DataType type)
	throws SQLException
    {
	Map<Integer, List<DataValue>> valuesMap = new HashMap<Integer, List<DataValue>>();

	while(resultSet.next())
	{
	    int entityId = resultSet.getInt(ID_COLUMN_INDEX);
	    DataValue currentValue = extractCurrentArrayValueFromResultSet(resultSet, type);

	    List<DataValue> arrayValues = valuesMap.get(entityId);

	    if(arrayValues == null)
	    {
		arrayValues = new ArrayList<DataValue>();
		valuesMap.put(entityId, arrayValues);
	    }

	    arrayValues.add(currentValue);
	}

	return valuesMap;
    }

    /**
     * Extracts the array value located at the current row of the given result
     * set.
     */

    private DataValue extractCurrentArrayValueFromResultSet(ResultSet resultSet, DataType type) throws SQLException
    {
	switch(type)
	{
	    case BOOLEAN:
		boolean boolValue = resultSet.getBoolean(ARRAY_VALUE_COLUMN_INDEX);
		if(!resultSet.wasNull())
		    return new BooleanValue(boolValue);
		break;

	    case INTEGER:
		int intValue = resultSet.getInt(ARRAY_VALUE_COLUMN_INDEX);
		if(!resultSet.wasNull())
		    return new IntegerValue(intValue);
		break;

	    case REAL:
		float floatValue = resultSet.getFloat(ARRAY_VALUE_COLUMN_INDEX);
		if(!resultSet.wasNull())
		    return new RealValue(floatValue);
		break;

	    case STRING:
		String stringValue = resultSet.getString(ARRAY_VALUE_COLUMN_INDEX);
		if(!resultSet.wasNull())
		    return new StringValue(stringValue);
		break;

	    case DATE:
		String stringDate = resultSet.getDate(ARRAY_VALUE_COLUMN_INDEX).toString();
		if(!resultSet.wasNull())
		    return DateUtils.parseStringDate(stringDate);
		break;

	    default:
		throw new IllegalArgumentException("No concrete array type found for " + type);
	}

	return new NullValue();
    }

    /**
     * Gets the number of rows in the given table.
     */

    private int getRowCountFromTable(String tableName) throws SQLException
    {
	try
	{
	    dbConnector.closeConnection();
	    dbConnector.createPreparedStatement("SELECT count(*) FROM " + tableName);
	    ResultSet resultSet = dbConnector.executePreparedStatementAsQuery(null);

	    if(resultSet.next())
		return resultSet.getInt(ID_COLUMN_INDEX);
	    else
		return 0;
	}
	catch(SQLException e)
	{
	    log.error("Error counting rows for " + tableName);
	    throw e;
	}
	finally
	{
	    dbConnector.closeConnection();
	}
    }
}
