package net.explorercat.actions.api.admin;

import net.explorercat.actions.PropertyLookup;
import net.explorercat.plugins.PluginLoader;
import net.explorercat.plugins.PluginLoadingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that updates the plugin repository, deploying any new plugin found
 * in the plugins folder (hot deployment).
 * 
 * - updatePlugins: Input{user, password}, Output{returnCode}.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 9 May 2011
 */

public class UpdatePluginsMethod extends AdminAPIMethod
{
    private static final Log log = LogFactory.getLog(UpdatePluginsMethod.class);

    // Output Parameter: Normal JSON response (success).
    private NormalJSONResponse normalResponse;

    // Output parameters getters

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    /**
     * Updates the plugin repository. Any new plugin found will be deployed and
     * registered. Notice this can take a while since any pre-calculated query
     * will be resolved at deployment time.
     */

    @Override
    public String execute()
    {
	try
	{
	    if(hasUserAdminRights())
	    {
		PluginLoader loader = new PluginLoader(PropertyLookup.getPluginManifestFilename());
		loader.loadPluginsIntoRegistry(PropertyLookup.getPluginsFolderPath());
		this.normalResponse = new NormalJSONResponse();
		return SUCCESS;
	    }
	    else
	    {
		setErrorResponse(4, "User has not rights to perform the operation");
		return ERROR;
	    }
	}
	catch(PluginLoadingException e)
	{
	    String errorMessage = "Error updating the plugin repository, " + e.getMessage();
	    log.error(errorMessage);
	    setErrorResponse(2, errorMessage);
	    return ERROR;
	}
    }

    /**
     * Encapsulates the JSON response that is sent back to the user.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date May 2011
     */

    public static class NormalJSONResponse extends JSONResponse
    {
	public NormalJSONResponse()
	{
	    super(0);
	}
    }
}
