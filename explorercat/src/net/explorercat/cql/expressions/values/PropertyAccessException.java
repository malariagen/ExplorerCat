package net.explorercat.cql.expressions.values;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * To mark the exceptions generated when a property is requested but an error
 * occurs (normally the property is not found).
 * 
 * @author Jacob Almagro Garcia - April 2010.
 */

public class PropertyAccessException extends ExplorerCatCheckedException
{
    public PropertyAccessException(String msg)
    {
	super(msg);
    }

    public PropertyAccessException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public PropertyAccessException(Exception e)
    {
	super(e);
    }
}
