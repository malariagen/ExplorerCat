package net.explorercat.plugins;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception to mark errors related with the process of loading plug-ins.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 5 Oct 2010
 */

public class PluginLoadingException extends ExplorerCatCheckedException
{
    private String pluginName;
    private String reason;

    public PluginLoadingException(String msg, String pluginName, String reason)
    {
	super(msg);
	this.pluginName = pluginName;
	this.reason = reason;
    }

    public PluginLoadingException(String msg, String pluginName, String reason, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
	this.pluginName = pluginName;
	this.reason = reason;
    }

    public PluginLoadingException(String pluginName, String reason, Exception e)
    {
	super(e);
	this.pluginName = pluginName;
	this.reason = reason;
    }

    public String getPluginName()
    {
	return this.pluginName;
    }

    public String getReason()
    {
	return this.reason;
    }
}
