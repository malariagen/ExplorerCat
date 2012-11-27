package net.explorercat.dataaccess;

import java.util.List;

import net.explorercat.data.User;

/**
 * DAO in charge of providing registered users.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Sep 2010
 */

public interface UserDAO
{
    /**
     * Finds a user by id.
     * 
     * @param id The id of the user we want to retrieve.
     * @return The user associated with that id or null otherwise.
     */

    public User findUser(int id);

    /**
     * Finds a user by name.
     * 
     * @param username The name of the user.
     * @return The user object associated with that name or null otherwise.
     */

    public User findUser(String username);

    /**
     * Finds the list of all the users.
     * 
     * @return A list containing all the registered users.
     */

    public List<User> findAllUsers();

    /**
     * Checks if the given combination of username/password provides a valid
     * admin login.
     * 
     * @return True if an admin session could be started with the given username
     *         and password.
     */

    public boolean checkAdminLogin(String username, String password);

}
