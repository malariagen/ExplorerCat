package net.explorercat.dataaccess.cached;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.explorercat.application.ApplicationController;
import net.explorercat.data.User;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.UserDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the user DAO that relies on a memory cache. All users will
 * be loaded into memory at startup time.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Sep 2010
 */

public abstract class UserDAOCached implements UserDAO
{
    // Logging
    private static Log log = LogFactory.getLog(UserDAOCached.class);

    // Cached users. These maps are REALLY small, we can afford
    // the duplication.
    protected Map<Integer, User> usersById;
    protected Map<String, User> usersByName;

    /**
     * Creates a new user DAO that whose users are cached in memory.
     * 
     * @throws DAOException If there is any problem initializing the cache.
     */

    public UserDAOCached() throws DAOException
    {
	if(log.isDebugEnabled())
	    log.debug("Creating cached user DAO");

	// DAO accessed by multiple threads (although right now is read-only).
	usersById = new ConcurrentHashMap<Integer, User>();
	usersByName = new ConcurrentHashMap<String, User>();

	// Calls the hook method in charge of initialise the cache.
	// This functionality has to be provided by a subclass.
	initializeCache();
    }

    @Override
    public List<User> findAllUsers()
    {
	return new ArrayList<User>(usersById.values());
    }

    @Override
    public User findUser(int id)
    {
	return usersById.get(id);
    }

    @Override
    public User findUser(String username)
    {
	return usersByName.get(username);
    }

    @Override
    public boolean checkAdminLogin(String username, String password)
    {
	if(username == null || password == null)
	    return false;

	User user = findUser(username);

	if(user != null && user.isAdmin() && user.getPassword().equals(password))
	    return true;
	else
	    return false;
    }

    /**
     * Hook method in charge of initialising the cache. This method has to be
     * implemented by subclasses bounded to a specific implementation (i.e.
     * MySQL).
     * 
     * @throws DAOException If there is a problem initializing the cache.
     */

    abstract protected void initializeCache() throws DAOException;
}
