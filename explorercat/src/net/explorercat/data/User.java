package net.explorercat.data;

/**
 * A simple class to represent registered users.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Sep 2010
 */

public class User
{
    private int id;
    private String name;
    private String password;
    private String email;
    private boolean isAdmin;

    /**
     * Constructor.
     */

    public User(int id, String name, String password, String email, boolean isAdmin)
    {
	this.id = id;
	this.name = name;
	this.password = password;
	this.email = email;
	this.isAdmin = isAdmin;
    }

    public int getId()
    {
	return id;
    }

    public String getName()
    {
	return name;
    }

    public String getPassword()
    {
	return password;
    }

    public String getEmail()
    {
	return email;
    }

    /**
     * True if the user has administrator privileges.
     * 
     * @return True if the user is an administrator.
     */

    public boolean isAdmin()
    {
	return isAdmin;
    }
}
