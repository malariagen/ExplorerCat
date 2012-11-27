package net.explorercat.dataaccess.cached.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.explorercat.data.User;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.cached.UserDAOCached;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A MySQL implementation for the user DAO. This class will provide the
 * functionality that interacts with the DB engine in order to load all the
 * users in memory.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Sep 2010
 */

public class UserDAOCachedMySQL extends UserDAOCached
{
    // Logging
    private static Log log = LogFactory.getLog(UserDAOCachedMySQL.class);

    // Data provider that will access the DB.
    private SQLDataConnector sqlProvider;

    /**
     * Constructor (delegates in the super constructor that will call the
     * initialize method).
     */

    public UserDAOCachedMySQL() throws DAOException
    {
	super();
    }

    @Override
    protected void initializeCache() throws DAOException
    {
	if(log.isErrorEnabled())
	    log.debug("Initializing cache for UserDAO from MySQL DB");

	try
	{
	    sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();
	    sqlProvider.createPreparedStatement("SELECT * FROM users");
	    ResultSet resultSet = sqlProvider.executePreparedStatementAsQuery(null);

	    // Iterate the result set registering each user.
	    while(resultSet.next())
	    {
		User user = buildUserFromResultSet(resultSet);
		usersById.put(user.getId(), user);
		usersByName.put(user.getName(), user);
	    }
	}
	catch(SQLException e)
	{
	    String errorMessage = "Error trying to initialize the cache for UserDao, accessing MySQL";
	    log.error(errorMessage);
	    throw new DAOException(errorMessage);
	}
	finally
	{
	    sqlProvider.closeConnection();
	}
    }

    /**
     * Auxiliary method that creates a user from the current position of a DB
     * result set.
     * 
     * @param resultSet The result set that contains all the properties of the
     *        user.
     * @return The user described by the current position of the result set.
     */

    private User buildUserFromResultSet(ResultSet resultSet) throws SQLException
    {
	int id = resultSet.getInt("id");
	String name = resultSet.getString("name");
	String password = resultSet.getString("password");
	String email = resultSet.getString("email");
	boolean isAdmin = resultSet.getBoolean("is_admin");

	return new User(id, name, password, email, isAdmin);
    }
}
