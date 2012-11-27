package net.explorercat.compactcat.translators.mysql;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

/**
 * Translates the dictionary of an entity into a MySQL representation, basically
 * a table containing all the meta-data related to its attributes.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 8 Feb 2011
 */

public class EntityDictionaryTranslatorMySQL
{
    private static Log log = LogFactory.getLog(EntityDictionaryTranslatorMySQL.class);

    private EntityTableCreatorMySQL tableCreator;
    private SQLDataConnector sqlConnector;

    /**
     * Creates a MySQL translator for the dictionary of an entity.
     * 
     * @param tableCreator The table creator used to generate the data tables of
     *        the entities being translated
     */

    public EntityDictionaryTranslatorMySQL(EntityTableCreatorMySQL tableCreator)
    {
	this.tableCreator = tableCreator;
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    /**
     * Creates the dictionary table that describes the attributes of the entity.
     * This method must be called before {@link populateEntityDictionaryTable}
     * 
     * Columns included:
     * 
     * <code>
     * - name 
     * - description 
     * - allowed_values (separated by commas, will be parsed and converted to the proper type, null if none)
     * - minimum_value (as a float, converted to int if necessary, null if none)
     * - maximum_value (same as minimum, null if none)
     * - type (CQL type)
     * - entity_referenced (name of the entity referenced by the attribute, null if none)
     * - attribute_referenced (name of the attribute referenced, null if none)
     * </code>
     * 
     * @param entityDefinition Definition of the entity that is being
     *        translated.
     * @param parameters MySQL parameters provided to guide the translation.
     */

    public void createEntityDictionaryTable(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	String tableName = getEntityDictionaryTableName(entityDefinition);

	if(log.isDebugEnabled())
	    log.debug("Creating entity dictionary table for " + entityDefinition.getName() + ": " + tableName);

	String sql = "CREATE TABLE " + tableName + "(" + "id int(10) unsigned NOT NULL AUTO_INCREMENT,"
		     + "name varchar(255) NOT NULL," + "description text NOT NULL," + "minimum_value float,"
		     + "maximum_value float," + "type varchar(32)," + "entity_referenced varchar(255),"
		     + "attribute_referenced varchar(255)," + "allowed_values text," + "PRIMARY KEY (id)," + "KEY IDX_"
		     + tableName + "_name (name) USING HASH"
		     + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Attributes dictionary'";
	try
	{
	    sqlConnector.closeConnection();
	    sqlConnector.executeUpdate(sql);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error creating the dictionary table", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Populates the dictionary table with the meta-data information coming from
     * the entity attributes. A null value is inserted when no meta-data value
     * has been specified (i.e. if you find a null value for the minimum_value
     * property, it means no minimum has been specified).
     */

    public void populateEntityDictionaryTable(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	String sql = null;

	try
	{
	    List<AttributeDefinition> attributes = entityDefinition.getAttributes();

	    for(AttributeDefinition attribute : attributes)
	    {
		String name = attribute.getName();
		DataType type = attribute.getType();
		String description = attribute.getDescription();

		DataValue maximum = attribute.getMaximumValue();
		DataValue minimum = attribute.getMinimumValue();
		List<String> allowedValues = attribute.getAllowedValues();

		String referencedEntity = attribute.getReferencedEntity();
		String referencedAttribute = attribute.getReferencedAttribute();

		// Allowed values are represented with a string where values are 
		// separated by commas or with a null if no values have been specified. 
		StringBuilder valuesAsCSV = new StringBuilder();
		valuesAsCSV.append("'");

		for(String currentValue : allowedValues)
		    valuesAsCSV.append(currentValue).append(",");

		// Deletes the last comma of the first quote.
		valuesAsCSV.deleteCharAt(valuesAsCSV.length() - 1);

		if(valuesAsCSV.length() > 0)
		    valuesAsCSV.append("'");
		else
		    valuesAsCSV.append("null");

		// Put together all the query values.
		StringBuilder sqlValues = new StringBuilder();
		sqlValues.append("'").append(name).append("','").append(type).append("','");
		sqlValues.append(description).append("',").append(maximum).append(",");
		sqlValues.append(minimum).append(",").append(valuesAsCSV).append(",");

		// Do not make the mistake of storing null values as strings ("null").
		if(referencedEntity == null)
		    sqlValues.append(referencedEntity).append(",");
		else
		    sqlValues.append("'").append(referencedEntity).append("',");

		if(referencedAttribute == null)
		    sqlValues.append(referencedAttribute);
		else
		    sqlValues.append("'").append(referencedAttribute).append("'");

		// Insert a row into the dictionary table for the attribute definition.
		sql = "INSERT INTO " + getEntityDictionaryTableName(entityDefinition)
		      + " (name, type, description, maximum_value, minimum_value, allowed_values,"
		      + " entity_referenced, attribute_referenced)" + " VALUES (" + sqlValues.toString() + ")";

		sqlConnector.closeConnection();
		sqlConnector.executeUpdate(sql);
	    }
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error translating the entity dictionary: " + sql, e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Auxiliary method that gets the name of the table that contains the
     * dictionary.
     */

    private String getEntityDictionaryTableName(EntityDefinition entityDefinition)
    {
	String suffix = PropertyLookup.getDictionaryTableNameSuffix();
	String separator = PropertyLookup.getTableNameSeparator();

	return tableCreator.getEntityTableBaseName(entityDefinition) + separator + suffix;
    }
}
