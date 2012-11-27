package net.explorercat.compactcat.translators.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import net.explorercat.compactcat.definitions.CatalogDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.translators.CatalogTranslator;
import net.explorercat.compactcat.translators.EntityTranslator;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Catalog translator implementation for MySQL. This class will translate a
 * complete catalog into a set of MySQL tables.
 * 
 * The catalog itself will be added as an entry to the catalogs table. A table
 * called <catalog_ID_entities> will list all the entities contained in the
 * catalog. For each entity in the catalog the following tables are created:
 * 
 * <code>
 * - catalog_ID_ENTITY-NAME: Data for the entity.
 * - catalog_ID_ENTITY-NAME_DICTIONARY: Attribute definitions for the entity. 
 * - catalog_ID_ENTITY-NAME_STATS: Calculated stats for the entity attributes.
 * - catalog_ID_ENTITY-NAME_ATTRIBUTE-NAME: Extra table for array values.
 * </code>
 * 
 * After all the tables are populated, FK constraints and indices are added when
 * the {@link translateConstraints} method is called. FK are controlled by the
 * use of references. Indexing and partitioning requirements are passed as
 * translation parameters (check {@link EntityTranslatorMySQL}).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Feb 2011
 */

public class CatalogTranslatorMySQL implements CatalogTranslator
{
    private static Log log = LogFactory.getLog(CatalogTranslatorMySQL.class);

    private int catalogId;
    private SQLDataConnector sqlConnector;

    public CatalogTranslatorMySQL()
    {
	this.catalogId = -1;
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    @Override
    public void translateCatalog(CatalogDefinition catalogDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	if(log.isDebugEnabled())
	    log.debug("Translating catalog : " + catalogDefinition.getName() + ":" + catalogDefinition.getVersion()
		      + " into MySQL DB");
	
	log.debug(catalogDefinition.toString());

	createCatalogsTableIfNotExist();

	if(isCatalogPresent(catalogDefinition))
	    throw new TranslationException("Error, DB contains a catalog with the same name, version and release date");

	addCatalogEntry(catalogDefinition);
	updateCatalogId();
	createCatalogEntitiesTable(catalogDefinition);
	populateCatalogEntitiesTable(catalogDefinition);
    }

    @Override
    public EntityTranslator getEntityTranslator()
    {
	return new EntityTranslatorMySQL(catalogId);
    }

    /**
     * Auxiliary method that creates the catalogs table if it doesn't exist.
     */

    private void createCatalogsTableIfNotExist() throws TranslationException
    {
	if(log.isDebugEnabled())
	    log.debug("Checking/Creating catalogs table");

	String sql = "CREATE TABLE  IF NOT EXISTS " + PropertyLookup.getCatalogsTableName() + " ("
		     + "id int(10) unsigned NOT NULL AUTO_INCREMENT," + "name varchar(255) NOT NULL,"
		     + "release_date date NOT NULL," + "version varchar(45) NOT NULL," + "description text NOT NULL,"
		     + "PRIMARY KEY (id)," + "KEY IDX_catalogs_name (name) USING HASH,"
		     + "CONSTRAINT IDX_catalogs_name_release_date UNIQUE KEY (name,version,release_date)) "
		     + "ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Catalogs of entities.'";
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
    }

    /**
     * Adds an entry for the given catalog in the catalogs table.
     */

    private void addCatalogEntry(CatalogDefinition catalogDefinition) throws TranslationException
    {
	if(log.isDebugEnabled())
	    log.debug("Adding catalog entry to catalogs table");

	String sql = null;

	try
	{
	    String name = "'" + catalogDefinition.getName() + "'";
	    String version = "'" + catalogDefinition.getVersion() + "'";
	    String description = "'" + catalogDefinition.getDescription() + "'";
	    String releaseDate = "'" + catalogDefinition.getReleaseDate().getValueAsString() + "'";

	    sql = "INSERT INTO " + PropertyLookup.getCatalogsTableName()
		  + " (name, version, release_date, description) VALUES (" + name + "," + version + "," + releaseDate
		  + "," + description + ")";

	    sqlConnector.closeConnection();
	    int insertedRows = sqlConnector.executeUpdate(sql);

	    if(insertedRows != 1)
		throw new TranslationException("Error trying inserting the catalog, returning code: " + insertedRows);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error inserting the catalog row: " + sql, e);
	}
	catch(IncompatibleTypeException e)
	{
	    throw new TranslationException("Error accesing the catalog release date", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Auxiliary method that updates the catalogId property selecting the id of
     * the last catalog added to the catalogs table.
     */

    private void updateCatalogId() throws TranslationException
    {
	String sql = "SELECT MAX(id) FROM " + PropertyLookup.getCatalogsTableName();
	try
	{
	    sqlConnector.closeConnection();
	    ResultSet result = sqlConnector.executeQuery(sql);

	    // We assume the last id added is the id of our catalog.
	    // If the table is empty something went wrong.
	    if(result.first())
		catalogId = result.getInt(1);
	    else
		throw new TranslationException("Error retrieving the catalog id");
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error retrieving the catalog id", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Auxiliary method that checks if a catalog is already present in the DB.
     * We consider a catalog is present in the database if we can find a catalog
     * with the same name, version and release date than the current one.
     */

    private boolean isCatalogPresent(CatalogDefinition catalogDefinition) throws TranslationException
    {
	try
	{
	    String name = "'" + catalogDefinition.getName() + "'";
	    String version = "'" + catalogDefinition.getVersion() + "'";
	    String releaseDate = "'" + catalogDefinition.getReleaseDate().getValueAsString() + "'";

	    String sql = "SELECT * FROM " + PropertyLookup.getCatalogsTableName() + " WHERE name = " + name
			 + " AND version = " + version + " AND release_date = " + releaseDate;

	    sqlConnector.closeConnection();
	    ResultSet result = sqlConnector.executeQuery(sql);

	    // If we do NOT retrieve any row is because the catalog is not in the DB yet.
	    return result.first();
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error accesing the catalogs table", e);
	}
	catch(IncompatibleTypeException e)
	{
	    throw new TranslationException("Error accesing the catalog release date", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Creates a table that lists all the entities contained in the catalog. For
     * each entity we register the following properties:
     * 
     * <code>
     * - description: Brief description of the entity.
     * </code>
     * 
     */

    private void createCatalogEntitiesTable(CatalogDefinition catalogDefinition) throws TranslationException
    {
	String tableName = getEntitiesTableName();

	if(log.isDebugEnabled())
	    log.debug("Creating " + tableName + " table");

	try
	{
	    String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "("
			 + "id int(10) unsigned NOT NULL AUTO_INCREMENT," + "name varchar(255) NOT NULL,"
			 + "description text NOT NULL," + "PRIMARY KEY (id)," + "KEY IDX_" + tableName
			 + "_name (name) USING HASH"
			 + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Entities for the catalog.'";

	    sqlConnector.closeConnection();
	    sqlConnector.executeUpdate(sql);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error accessing the catalogs table", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Gets the name for the table that lists all the entities of the catalog.
     */

    private String getEntitiesTableName()
    {
	String prefix = PropertyLookup.getTableNamePrefix();
	String separator = PropertyLookup.getTableNameSeparator();
	String suffix = PropertyLookup.getEntitiesTableNameSuffix();

	return prefix + separator + catalogId + separator + suffix;
    }

    /**
     * Populates the table of entities for the catalog (all the entities
     * contained in the catalog).
     */

    private void populateCatalogEntitiesTable(CatalogDefinition catalogDefinition) throws TranslationException
    {
	Collection<String> entityNames = catalogDefinition.getEntityNames();
	String tableName = getEntitiesTableName();

	try
	{
	    for(String entityName : entityNames)
	    {
		EntityDefinition entityDefinition = catalogDefinition.getEntityDefinition(entityName);
		String sql = "INSERT INTO " + tableName + " (name, description) VALUES ('" + entityDefinition.getName()
			     + "','" + entityDefinition.getDescription() + "')";

		sqlConnector.closeConnection();
		sqlConnector.executeUpdate(sql);
	    }
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error populating " + tableName, e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public void translateConstraints(CatalogDefinition catalogDefinition) throws TranslationException
    {
	EntityTableCreatorMySQL tableCreator = new EntityTableCreatorMySQL(catalogId);
	ConstraintTranslatorMySQL constraintTranslator = new ConstraintTranslatorMySQL(tableCreator);
	constraintTranslator.translateConstraints(catalogDefinition.getEntityDefinitions());
    }
}
