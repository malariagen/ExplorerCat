package net.explorercat.plugins;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Marks errors related to the use of plugin configuration hashes (a way of
 * generating unique URLs when using plugins).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 May 2011
 */

public class PluginHashException extends ExplorerCatCheckedException
{
    public PluginHashException(String msg)
    {
	super(msg);
    }

    public PluginHashException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public PluginHashException(Exception e)
    {
	super(e);
    }
}
