package net.explorercat.dataaccess.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import net.explorercat.data.Catalog;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the iterator interface for data entities that are backed a
 * MySQL DB.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class QueryableDataEntityIteratorMySQL implements Iterator<QueryableDataEntity>
{
    // Logging
    private static Log log = LogFactory.getLog(QueryableDataEntityIteratorMySQL.class);

    // Data provider that will access the DB.
    private SQLDataConnector sqlProvider;

    // Result set that will be used to iterate.
    private ResultSet resultSet;

    // Entity builder that will be used to create data entities.
    private QueryableDataEntityBuilderMySQL entityBuilder;

    /**
     * Builds an iterator for entities based on a MySQL implementation.
     * 
     * @param entityType The type of the entity the iterator will provide.
     * @param catalogId The id of the catalogue that contains the entities.
     * @param dataEntityBuilder The builder in charge of creating the data
     *        entities from the result set returned by the DB.
     */

    public QueryableDataEntityIteratorMySQL(String entityType, Catalog catalog,
					    QueryableDataEntityBuilderMySQL dataEntityBuilder) throws SQLException
    {
	if(log.isDebugEnabled())
	    log.debug("Creating entity iterator based on MySQL DB");

	// Activate the streaming mode for the provider.
	sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();
	sqlProvider.setRowStreamingMode(true);

	// Select all the entities from the proper table.
	sqlProvider.createPreparedStatement("SELECT * FROM " + "catalog_" + catalog.getId() + "_" + entityType);
	resultSet = sqlProvider.executePreparedStatementAsQuery(null);
	entityBuilder = dataEntityBuilder;
    }

    @Override
    public boolean hasNext()
    {
	try
	{
	    return !resultSet.isAfterLast();
	}
	catch(SQLException e)
	{
	    return false;
	}
    }

    @Override
    public QueryableDataEntity next()
    {
	try
	{
	    if(resultSet.next())
		return entityBuilder.buildEntityFromResultSet(resultSet);
	    else
		return null;
	}
	catch(SQLException e)
	{
	    log.error("Error iterating through entities", e);
	    return null;
	}
    }

    @Override
    public void remove()
    {
	throw new UnsupportedOperationException("Remove operation not supported");
    }

    @Override
    protected void finalize() throws Throwable
    {
	super.finalize();
	sqlProvider.closeConnection();
    }
}
