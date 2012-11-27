package net.explorercat.cql.selection.resolvers;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception that tags an error when trying to resolve a selection.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 27 Sep 2010
 */

public class SelectionResolutionException extends ExplorerCatCheckedException
{
    public SelectionResolutionException(String msg)
    {
	super(msg);
    }

    public SelectionResolutionException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public SelectionResolutionException(Exception e)
    {
	super(e);
    }
}
