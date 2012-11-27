package net.explorercat.dataaccess.cached.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PropertyLookup;
import net.explorercat.dataaccess.cached.CatalogDAOCached;
import net.explorercat.util.misc.DateUtils;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A MySQL implementation for the catalog DAO. This class will interact with the
 * DB engine in order to load all the catalogs into memory.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class CatalogDAOCachedMySQL extends CatalogDAOCached implements CatalogDAO
{
    // Logging
    private static Log log = LogFactory.getLog(CatalogDAOCachedMySQL.class);

    // Data provider that will access the DB.
    private SQLDataConnector sqlProvider;

    /**
     * Empty constructor, it calls the super constructor that will call the
     * initializeCache method.
     */

    public CatalogDAOCachedMySQL() throws DAOException
    {
	super();
    }

    @Override
    protected void initializeCache() throws DAOException
    {
	if(log.isErrorEnabled())
	    log.debug("Initializing cache for catalog DAO from MySQL DB");

	this.sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();

	try
	{
	    sqlProvider.closeConnection();
	    sqlProvider.createPreparedStatement("SELECT * FROM " + PropertyLookup.getCatalogsTableName());
	    ResultSet resultSet = sqlProvider.executePreparedStatementAsQuery(null);

	    while(resultSet.next())
	    {
		Catalog catalog = buildCatalogFromResultSet(resultSet);
		loadAndRegisterCatalogEntities(catalog);
		catalogs.put(catalog.getId(), catalog);
	    }

	}
	catch(SQLException e)
	{
	    String errorMessage = "Error trying to initialize CatalogDAO cache, accessing MySQL. ";
	    log.error(errorMessage);
	    throw new DAOException(errorMessage, e);
	}
	finally
	{
	    sqlProvider.closeConnection();
	}
    }

    /**
     * Auxiliary method that loads and registers the list of entities associated
     * with this catalog.
     * 
     * @param catalog The catalog in which the entities will be registered.
     */

    private void loadAndRegisterCatalogEntities(Catalog catalog) throws SQLException
    {
	SQLDataConnector tempProvider = SQLDataConnectorFactory.getInstance().getDataConnector();

	try
	{
	    String tableName = PropertyLookup.getTableNamePrefix() + PropertyLookup.getTableNameSeparator()
			       + catalog.getId() + PropertyLookup.getTableNameSeparator()
			       + PropertyLookup.getEntitiesTableNameSuffix();

	    tempProvider.createPreparedStatement("SELECT * FROM " + tableName);
	    ResultSet resultSet = tempProvider.executePreparedStatementAsQuery(null);

	    while(resultSet.next())
	    {
		String entityType = resultSet.getString("name");
		String entityDescription = resultSet.getString("description");
		catalog.registerEntityType(entityType, entityDescription);
	    }

	    tempProvider.closeConnection();
	}
	catch(SQLException e)
	{
	    log.error("Error loading the list of entity types for catalog " + catalog.getId());
	    throw e;
	}
	finally
	{
	    tempProvider.closeConnection();
	}
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The next two methods could be moved to a catalog builder for MySQL (in the package 
    // net.explorercat.dataaccess.mysql). However the implementation is simple and not reused so
    // we keep them as auxiliary methods in this class.
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Auxiliary method that creates a catalog from the current position of the
     * given DB result set.
     * 
     * @param resultSet The result set that contains all the properties of the
     *        catalog.
     * @return The catalog represented in the current row of the result set.
     * @throws DAOException If there is a problem building the catalog.
     */

    private Catalog buildCatalogFromResultSet(ResultSet resultSet) throws SQLException, DAOException
    {
	try
	{
	    int catalogId = resultSet.getInt("id");
	    String catalogName = resultSet.getString("name");
	    String version = resultSet.getString("version");
	    String description = resultSet.getString("description");

	    String stringDate = resultSet.getDate("release_date").toString();
	    GregorianCalendar releaseDate = DateUtils.parseStringDate(stringDate).getValueAsDate();

	    if(log.isDebugEnabled())
		log.debug("Building new catalog with id: " + catalogId + "(" + catalogName + "/" + version + ")");

	    return new Catalog(catalogId, catalogName, releaseDate, version, description);
	}
	catch(SQLException e)
	{
	    String errorMessage = "Error trying to create a catalog from a result set ";
	    log.error(errorMessage);
	    throw new DAOException(errorMessage, e);
	}
	catch(IncompatibleTypeException e)
	{
	    String errorMessage = "Error parsing the catalog date ";
	    log.error(errorMessage + e);
	    throw new DAOException(errorMessage, e);
	}
    }
}
