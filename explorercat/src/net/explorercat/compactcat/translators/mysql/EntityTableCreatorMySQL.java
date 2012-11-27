package net.explorercat.compactcat.translators.mysql;

import java.sql.SQLException;
import java.util.List;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Creates the tables that will contain the entity data (MySQL representation).
 * Parameters can be provided to customise the translation process (e.g.
 * enabling partitioning for the tables).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 9 Feb 2011
 */

public class EntityTableCreatorMySQL
{
    private static Log log = LogFactory.getLog(EntityTableCreatorMySQL.class);

    // The identifier of the catalog in the database.
    private int catalogId;

    private SQLDataConnector sqlConnector;
    private CodeTranslatorMySQL mySQLCodeTranslator;

    /**
     * Builds a table creator for the entities of the given catalog.
     * 
     * @param catalogId The unique identifier of the catalog in the DB.
     */

    public EntityTableCreatorMySQL(int catalogId)
    {
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
	this.catalogId = catalogId;
	this.mySQLCodeTranslator = new CodeTranslatorMySQL();
    }

    /**
     * Creates the DB tables that will contain the entity data.
     * 
     * @param entityDefinition Definition of the entity we are translating.
     * @param parameters MySQL parameters to guide the table creation.
     * @throws TranslationException If there is an error creating the tables.
     */

    public void createEntityDataTables(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	String tableName = getEntityTableBaseName(entityDefinition);
	String sql = mySQLCodeTranslator.translateEntityIntoMySQLTable(tableName, entityDefinition, parameters);

	if(log.isDebugEnabled())
	    log.debug("Creating entity table for " + entityDefinition.getName() + " : " + sql);

	try
	{
	    sqlConnector.closeConnection();
	    sqlConnector.executeUpdate(sql);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error accessing the catalogs table: " + sql, e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}

	createEntityArrayTables(entityDefinition, parameters);
    }

    /**
     * Creates the tables that will be used to support the array data types.
     * These tables will contain the values for the array attributes of each
     * entity, they will be linked to the entities by id.
     */

    private void createEntityArrayTables(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	List<AttributeDefinition> attributes = entityDefinition.getAttributes();

	for(AttributeDefinition attribute : attributes)
	    if(attribute.isArray())
		createEntityArrayTable(entityDefinition, attribute, parameters);
    }

    /**
     * Creates a table of values for an array attribute. Values will be
     * associated wit the entity using the entity id. Notice we identify the
     * attribute on the table name (attribute definition is available in the
     * entity dictionary).
     */

    private void createEntityArrayTable(EntityDefinition entityDefinition, AttributeDefinition attribute,
					List<TranslationParameter> parameters) throws TranslationException
    {
	String tableName = getArrayAttributeTableName(entityDefinition, attribute.getName());

	String sql = mySQLCodeTranslator.translateArrayAttributeIntoMySQLTable(tableName, entityDefinition, attribute,
									       parameters);

	if(log.isDebugEnabled())
	    log.debug("Creating entity array table " + tableName + " : " + sql);

	try
	{
	    sqlConnector.closeConnection();
	    sqlConnector.executeUpdate(sql);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error creating the values table " + tableName, e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Gets the base name that will be used for the tables. Other objects
     * creating tables for this entity MUST prefix them with it.
     * 
     * @param entityDefinition Definition of the table for which the tables will
     *        be created.
     * @return The base name to be used when creating the tables.
     */

    public String getEntityTableBaseName(EntityDefinition entityDefinition)
    {
	String prefix = PropertyLookup.getTableNamePrefix();
	String separator = PropertyLookup.getTableNameSeparator();

	return prefix + separator + catalogId + separator + entityDefinition.getName();
    }

    /**
     * Gets the name that will be used to create a table for the given array
     * attribute.
     */

    public String getArrayAttributeTableName(EntityDefinition entityDefinition, String attribute)
    {
	String separator = PropertyLookup.getTableNameSeparator();
	return getEntityTableBaseName(entityDefinition) + separator + attribute;
    }
}
