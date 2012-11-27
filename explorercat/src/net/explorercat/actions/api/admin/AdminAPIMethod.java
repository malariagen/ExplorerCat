package net.explorercat.actions.api.admin;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.data.User;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.UserDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for actions that offer API methods only available to
 * administrators.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 May 2011
 */

public abstract class AdminAPIMethod extends JSONBasedAPIMethod
{
    private static final Log log = LogFactory.getLog(AdminAPIMethod.class);

    private String user;
    private String password;

    public void setUser(String username)
    {
	this.user = username;
    }

    public void setPassword(String password)
    {
	this.password = password;
    }

    public String getUser()
    {
	return user;
    }

    public String getPassword()
    {
	return password;
    }

    /**
     * Checks if administrator rights can be granted to the user and password
     * passed to the action.
     * 
     * @return True if administrator rights can be granted to pair user/pass
     *         passed to the action, false otherwise.
     */

    public boolean hasUserAdminRights()
    {
	try
	{
	    UserDAO userDAO = ApplicationController.getInstance().getDAOFactory().getUserDAO();
	    return userDAO.checkAdminLogin(user, password);
	}
	catch(DAOException e)
	{
	    log.error("Error accessing the user data," + e.getMessage());
	    return false;
	}
    }

    /**
     * Gets the object that represents the user in the system.
     * 
     * @return The object representation of the user specified.
     */

    public User getUserObject() throws DAOException
    {
	UserDAO userDAO = ApplicationController.getInstance().getDAOFactory().getUserDAO();
	return userDAO.findUser(user);
    }
}
