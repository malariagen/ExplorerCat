package net.explorercat.staticresources;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Marks an exception that occurred when loading static resources
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Dec 2010
 */

public class StaticResourceLoadingException extends ExplorerCatCheckedException
{
    public StaticResourceLoadingException(String msg)
    {
	super(msg);
    }

    public StaticResourceLoadingException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public StaticResourceLoadingException(Exception e)
    {
	super(e);
    }
}
