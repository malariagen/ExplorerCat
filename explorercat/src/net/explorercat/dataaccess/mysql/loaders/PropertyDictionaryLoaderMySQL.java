package net.explorercat.dataaccess.mysql.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDefinition;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PropertyLookup;
import net.explorercat.dataaccess.loaders.PropertyDictionaryLoader;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class in charge of getting all the types related to entity properties from
 * the DB (MySQL implementation).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class PropertyDictionaryLoaderMySQL implements PropertyDictionaryLoader
{
    private static Log log = LogFactory.getLog(PropertyDictionaryLoaderMySQL.class);

    private String entityType;
    private Catalog catalog;
    private String entityTableName;
    private SQLDataConnector sqlProvider;

    /**
     * Creates a property dictionary loader for all the entities with the same
     * name contained in the given catalog.
     * 
     * @param entityType The type of the entity.
     * @param catalog The catalog that contains the entities.
     */

    public PropertyDictionaryLoaderMySQL(String entityType, Catalog catalog) throws DAOException
    {
	this.entityType = entityType;
	this.catalog = catalog;
	this.entityTableName = PropertyLookup.getTableNamePrefix() + PropertyLookup.getTableNameSeparator()
			       + catalog.getId() + PropertyLookup.getTableNameSeparator() + entityType;
	this.sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    @Override
    public PropertyDictionary loadPropertyTypesDictionary() throws DAOException
    {
	if(log.isDebugEnabled())
	    log.debug("Loading property dictionary for " + entityType + " in catalog " + catalog.getId());

	PropertyDictionary propertyDictionary = new PropertyDictionary(entityType);

	try
	{
	    String dictionaryTable = entityTableName + PropertyLookup.getTableNameSeparator()
				     + PropertyLookup.getDictionaryTableNameSuffix();

	    sqlProvider.createPreparedStatement("SELECT * FROM " + dictionaryTable);
	    ResultSet resultSet = sqlProvider.executePreparedStatementAsQuery(null);

	    while(resultSet.next())
	    {
		PropertyDefinition property = buildPropertyDefinitionFromResultSet(resultSet);
		propertyDictionary.addProperty(property);
	    }

	    sqlProvider.closeConnection();
	    return propertyDictionary;
	}
	catch(SQLException e)
	{
	    log.error("Error querying the dictionary for catalog " + catalog.getId() + " and entity " + entityType);
	    throw new DAOException("Error loading the property dictionary", e);
	}
	finally
	{
	    sqlProvider.closeConnection();
	}
    }

    /**
     * Builds a property definition from the current row of the result set.
     */

    private PropertyDefinition buildPropertyDefinitionFromResultSet(ResultSet resultSet) throws SQLException
    {
	String name = resultSet.getString("name");
	DataType type = DataType.valueOf(resultSet.getString("type"));
	String desc = resultSet.getString("description");

	Float minValue = resultSet.getFloat("minimum_value");
	minValue = (resultSet.wasNull() ? null : minValue);

	Float maxValue = resultSet.getFloat("maximum_value");
	maxValue = (resultSet.wasNull() ? null : maxValue);

	String refEntity = resultSet.getString("entity_referenced");
	String refProperty = resultSet.getString("attribute_referenced");
	List<DataValue> allowedValues = parseAllowedValues(type, resultSet.getString("allowed_values"));

	return new PropertyDefinition(name, type, desc, minValue, maxValue, refEntity, refProperty, allowedValues);
    }

    /**
     * Parses a string representing a list of allowed values separated by
     * commas. Returns an empty list if the given string is null.
     */

    private List<DataValue> parseAllowedValues(DataType valueType, String valuesAsText)
    {
	if(valuesAsText == null)
	    return new ArrayList<DataValue>();

	StringTokenizer splitter = new StringTokenizer(valuesAsText, ",");
	List<DataValue> parsedValues = new ArrayList<DataValue>();

	while(splitter.hasMoreTokens())
	    parsedValues.add(valueType.parseValue(splitter.nextToken()));

	return parsedValues;
    }
}
