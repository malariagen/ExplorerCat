package net.explorercat.cql.types;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * To mark the exceptions generated when an incompatible type is requested to a
 * data value.
 * 
 * @author Jacob Almagro Garcia - April 2010.
 */

public class IncompatibleTypeException extends ExplorerCatCheckedException
{
    public IncompatibleTypeException(String msg)
    {
	super(msg);
    }

    public IncompatibleTypeException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public IncompatibleTypeException(Exception e)
    {
	super(e);
    }
}
