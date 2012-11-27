package net.explorercat.cql.selection;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception when dealing with a selection.
 * 
 * @author Jacob Almagro Garcia -
 * @date April 2010.
 */

public class SelectionException extends ExplorerCatCheckedException
{
    public SelectionException(String msg)
    {
	super(msg);
    }

    public SelectionException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public SelectionException(Exception e)
    {
	super(e);
    }
}
