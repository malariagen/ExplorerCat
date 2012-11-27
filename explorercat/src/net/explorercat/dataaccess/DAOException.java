package net.explorercat.dataaccess;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception that represents a problem occurred when using a DAO (Data Access
 * Object) to access the elements of the system.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class DAOException extends ExplorerCatCheckedException
{
    public DAOException(String msg)
    {
	super(msg);
    }

    public DAOException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public DAOException(Exception e)
    {
	super(e);
    }
}
