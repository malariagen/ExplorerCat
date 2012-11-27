package net.explorercat.dataaccess.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PluginHashDAO;
import net.explorercat.plugins.PluginHashRepository.PluginHash;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the hash configuration DAO that relies on accessing a MySQL
 * DB. Objects won't be cached in memory by this implementation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 May 2011
 * 
 */
public class PluginHashDAOMySQL implements PluginHashDAO
{
    private final static String HASHES_TABLE = "plugin_hashes";

    private static Log log = LogFactory.getLog(QueryableDataEntityDAOMySQL.class);

    private SQLDataConnector sqlConnector;

    public PluginHashDAOMySQL()
    {
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    @Override
    public PluginHash findPluginHash(String hashKey) throws DAOException
    {
	try
	{
	    sqlConnector.createPreparedStatement("SELECT * FROM " + HASHES_TABLE + " WHERE hash = ?");
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(hashKey);

	    return buildPluginHashFromResultSet(resultSet);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error looking for a plugin hash configuration", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public void deleteNonPermanentPluginHashes() throws DAOException
    {
	try
	{
	    if(log.isDebugEnabled())
		log.debug("Deleting all non-permament plugin hashes");

	    sqlConnector.createPreparedStatement("DELETE FROM " + HASHES_TABLE + " WHERE permanent = false");
	    sqlConnector.executePreparedStatementAsQuery(null);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error deleting non permanent hashes from the DB", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public void insertPluginHash(String hashKey, String configuration, boolean isPermanent) throws DAOException
    {
	try
	{
	    List<Object> parameters = new ArrayList<Object>(3);
	    parameters.add(hashKey);
	    parameters.add(configuration);
	    parameters.add(isPermanent);

	    sqlConnector.createPreparedStatement("INSERT INTO " + HASHES_TABLE + "(hash,configuration,permanent)"
						 + " VALUES(?,?,?)");
	    sqlConnector.executePreparedStatementAsUpdate(parameters);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error inserting a configuration hash into the DB", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public List<PluginHash> findPermamentPluginHashes() throws DAOException
    {
	try
	{
	    sqlConnector.createPreparedStatement("SELECT * FROM " + HASHES_TABLE + " WHERE permanent = true");
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);

	    ArrayList<PluginHash> permanentHashes = new ArrayList<PluginHash>();
	    PluginHash currentPluginHash = null;

	    while((currentPluginHash = buildPluginHashFromResultSet(resultSet)) != null)
		permanentHashes.add(currentPluginHash);

	    return permanentHashes;
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error retrieving permanent configuration hashes", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public PluginHash findLastPluginHashAdded() throws DAOException
    {
	try
	{
	    sqlConnector.createPreparedStatement("SELECT * FROM " + HASHES_TABLE + " ORDER BY id DESC LIMIT 1");
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);

	    return buildPluginHashFromResultSet(resultSet);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error retrieving the last configuration hash added", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public void updatePluginHash(PluginHash configurationHash) throws DAOException
    {
	try
	{
	    List<Object> parameters = new ArrayList<Object>(3);
	    parameters.add(configurationHash.getPluginConfiguration());
	    parameters.add(configurationHash.isPermanent());
	    parameters.add(configurationHash.getHashKey());

	    String statement = "UPDATE " + HASHES_TABLE + " SET configuration = ?, permanent = ? " + "WHERE hash = ?";
	    sqlConnector.createPreparedStatement(statement);
	    sqlConnector.executePreparedStatementAsUpdate(parameters);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error updating a configuration hash", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    public void makePluginHashPermanent(String hashKey) throws DAOException
    {
	try
	{
	    sqlConnector.createPreparedStatement("UPDATE " + HASHES_TABLE + " SET permanent = true WHERE hash = ?");
	    sqlConnector.executePreparedStatementAsUpdate(hashKey);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error making permanent a configuration hash", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Builds a hash configuration object from the data contained in the given
     * result set.
     * 
     * @return The hash configuration representing the data of the given result
     *         set or null if the result set is empty.
     */

    private PluginHash buildPluginHashFromResultSet(ResultSet resultSet) throws SQLException
    {
	PluginHash pluginHash = null;

	if(resultSet.next())
	{
	    pluginHash = new PluginHash(resultSet.getString("hash"), resultSet.getString("configuration"),
					resultSet.getBoolean("permanent"));
	}

	return pluginHash;
    }
}
