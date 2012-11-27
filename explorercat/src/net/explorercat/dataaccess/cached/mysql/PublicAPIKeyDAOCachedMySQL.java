package net.explorercat.dataaccess.cached.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.explorercat.data.PublicAPIKey;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PublicAPIKeyDAO;
import net.explorercat.dataaccess.cached.PublicAPIKeyDAOCached;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A MySQL implementation for the API key DAO. This class will interact with the
 * DB engine in order to load all the keys into memory and provides methods to
 * keep the DB updated.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Jun 2011
 */

public class PublicAPIKeyDAOCachedMySQL extends PublicAPIKeyDAOCached implements PublicAPIKeyDAO
{
    private static Log log = LogFactory.getLog(PublicAPIKeyDAOCachedMySQL.class);

    // Name of the DB table that contains the API keys.
    private final static String KEYS_TABLE = "public_api_keys";

    private SQLDataConnector sqlConnector;

    public PublicAPIKeyDAOCachedMySQL() throws DAOException
    {
	super();
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    @Override
    protected void disableKeyInStorage(String keyHash) throws DAOException
    {
	try
	{
	    String statement = "UPDATE " + KEYS_TABLE + " SET is_enabled = false WHERE key = ?";
	    sqlConnector.createPreparedStatement(statement);
	    sqlConnector.executePreparedStatementAsUpdate(keyHash);

	    if(log.isDebugEnabled())
		log.debug("Key disabled: " + keyHash);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error updating an API key in the DB", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    protected PublicAPIKey findLastKeyAdded() throws DAOException
    {
	try
	{
	    sqlConnector.createPreparedStatement("SELECT * FROM " + KEYS_TABLE + " ORDER BY id DESC LIMIT 1");
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);

	    if(resultSet.next())
		return buildAPIKeyFromResultSet(resultSet);
	    else
		return null;
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error retrieving the last key added", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    protected void insertKeyIntoStorage(PublicAPIKey key) throws DAOException
    {
	try
	{
	    List<Object> parameters = new ArrayList<Object>(3);
	    parameters.add(key.getKey());
	    parameters.add(key.getHost());
	    parameters.add(key.getEmail());
	    parameters.add(true);

	    sqlConnector.createPreparedStatement("INSERT INTO " + KEYS_TABLE + " VALUES(?,?,?,?)");
	    sqlConnector.executePreparedStatementAsUpdate(parameters);

	    if(log.isDebugEnabled())
		log.debug("Key stored: " + key);
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error inserting an API key into the DB", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    @Override
    protected List<PublicAPIKey> loadKeys() throws DAOException
    {
	try
	{
	    List<PublicAPIKey> keys = new ArrayList<PublicAPIKey>();

	    sqlConnector.createPreparedStatement("SELECT * FROM " + KEYS_TABLE);
	    ResultSet resultSet = sqlConnector.executePreparedStatementAsQuery(null);

	    while(resultSet.next())
		keys.add(buildAPIKeyFromResultSet(resultSet));

	    return keys;
	}
	catch(SQLException e)
	{
	    throw new DAOException("Error loading public API keys", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Builds an API key instance from the data contained in the current
     * position of the given result set.
     */

    private PublicAPIKey buildAPIKeyFromResultSet(ResultSet resultSet) throws SQLException
    {
	String key = resultSet.getString("key");
	String host = resultSet.getString("host");
	String email = resultSet.getString("email");
	boolean isEnabled = resultSet.getBoolean("is_enabled");

	return new PublicAPIKey(key, host, email, isEnabled);
    }
}
