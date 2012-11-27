package net.explorercat.dataaccess.cached.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.selection.SelectionResult;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PropertyLookup;
import net.explorercat.dataaccess.QueryableDataEntityDAO;
import net.explorercat.dataaccess.cached.QueryableDataEntityDAOCached;
import net.explorercat.dataaccess.mysql.QueryableDataEntityBuilderMySQL;
import net.explorercat.dataaccess.mysql.loaders.PropertyDictionaryLoaderMySQL;
import net.explorercat.dataaccess.mysql.loaders.PropertyStatsLoaderMySQL;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A MySQL implementation for the data entity DAO. This class will provide the
 * functionality that interacts with the DB engine in order to load all the
 * entities in memory.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class QueryableDataEntityDAOCachedMySQL extends QueryableDataEntityDAOCached implements QueryableDataEntityDAO
{
    // Logging
    private static Log log = LogFactory.getLog(QueryableDataEntityDAOCachedMySQL.class);

    // Data provider that will access the DB.
    private SQLDataConnector sqlProvider;

    // Table in the DB that contains all the entities we have to query.
    private String entityTableName;

    // Type of entity
    private String entityType;

    // Entity builder that will be used to create data entities.
    private QueryableDataEntityBuilderMySQL entityBuilder;

    /**
     * Builds a cached DAO that access a MySQL DB for a type of entity in a
     * given catalog. The parent class is in charge of calling the
     * initializeCache method.
     * 
     */

    public QueryableDataEntityDAOCachedMySQL() throws DAOException
    {
	super();
    }

    @Override
    protected void initializeCache(String entityType, Catalog catalog) throws DAOException
    {
	if(log.isErrorEnabled())
	    log.debug("Initializing cache for entity DAO from MySQL DB");

	this.entityTableName = PropertyLookup.getTableNamePrefix() + PropertyLookup.getTableNameSeparator()
			       + catalog.getId() + PropertyLookup.getTableNameSeparator() + entityType;

	this.sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();
	this.entityType = entityType;

	// Initialise the dictionary.
	PropertyDictionaryLoaderMySQL dictLoader = new PropertyDictionaryLoaderMySQL(entityType, catalog);
	PropertyDictionary propertyDictionary = dictLoader.loadPropertyTypesDictionary();

	// Initialise the stats.
	PropertyStatsLoaderMySQL statsLoader = new PropertyStatsLoaderMySQL(entityType, catalog);
	this.preCalculatedStats = statsLoader.loadPropertyStats(propertyDictionary);

	// Initialise the entity builder.
	this.entityBuilder = new QueryableDataEntityBuilderMySQL(entityType, catalog, propertyDictionary);

	// Load all the entities into memory.
	List<QueryableDataEntity> entities = retrieveEntitiesFromDB();

	// Create the cached selection, notice we are using a specialised implementation 
	// of the SelectionResult class.
	SelectionResult selection = new SelectionResultBackedByMap(entityType, catalog, entities, propertyDictionary,
								   null, this);
	// Set the entity type as label.
	selection.setSelectionLabel(entityType);

	// Store it.
	this.cachedSelection = selection;
    }

    /**
     * Retrieves all the entities from the DB.
     * 
     * @return A list containing all the entities for the configured type and
     *         catalog present in the DB.
     */

    private List<QueryableDataEntity> retrieveEntitiesFromDB() throws DAOException
    {
	List<QueryableDataEntity> entities;

	log.debug("Retrieveng all the entities from " + entityTableName);
	try
	{
	    sqlProvider.closeConnection();
	    sqlProvider.setRowStreamingMode(true);
	    sqlProvider.createPreparedStatement("SELECT * FROM " + entityTableName);
	    ResultSet resultSet = sqlProvider.executePreparedStatementAsQuery(null);
	    
	    entities = entityBuilder.buildEntitiesFromResultSet(resultSet);	    	    	    
	}
	catch(SQLException e)
	{
	    log.error("Error retrieving entities");
	    throw new DAOException("Error retrieving entities", e);
	}

	return entities;
    }
}
