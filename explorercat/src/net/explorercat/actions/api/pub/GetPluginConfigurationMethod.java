package net.explorercat.actions.api.pub;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.plugins.PluginHashException;
import net.explorercat.plugins.PluginHashRepository;
import net.explorercat.plugins.PluginHashRepository.PluginHash;
import net.explorercat.util.misc.HexCodeObfuscator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that gets the plugin configuration object (using the JSON format)
 * associated with the hash key passed as a parameter.
 * 
 * - Input : { hash }
 * 
 * - Output : { returnCode, configuration }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error retrieving the plug-in configuration.
 * 
 * - 2 There is no configuration associated with the given hash.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 May 2011
 */

public class GetPluginConfigurationMethod extends JSONBasedAPIMethod
{
    // Logging
    private static final Log log = LogFactory.getLog(GetPluginConfigurationMethod.class);

    // The global repository of plugin hashes.
    private static final PluginHashRepository PLUGIN_HASH_REPOSITORY = ApplicationController.getInstance().getPluginHashRepository();

    // Input Parameter: hash key associated with the configuration.
    private String hash;

    // Output Parameter: Normal JSON response.
    private NormalJSONResponse normalResposne;

    // Input parameters setters.

    public void setHash(String hash)
    {
	// Remove the noise used to obfuscate the key.
	this.hash = HexCodeObfuscator.deobfuscateKey(hash);
    }

    // Output parameters

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResposne;
    }

    /**
     * Executes the action, returning the plugin configuration in a JSON
     * response or a description of the error occurred. TODO Document the return
     * codes.
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Getting plugin configuration for " + hash);
	try
	{
	    PluginHash pluginHash = PLUGIN_HASH_REPOSITORY.findPluginHash(hash);

	    if(pluginHash == null)
	    {
		setErrorResponse(2, "No plugin configuration registered for hash: " + hash);
		return ERROR;
	    }

	    this.normalResposne = new NormalJSONResponse(pluginHash.getPluginConfiguration());
	    return SUCCESS;
	}
	catch(PluginHashException e)
	{
	    String errorMessage = "Error trying to retrieve a plugin configuration(" + hash + "), " + e.getMessage();
	    log.error(errorMessage);
	    setErrorResponse(1, errorMessage);
	    return ERROR;
	}
    }

    /**
     * Inner class that encapsulates the JSON response that is sent to the user.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date May 2011
     */

    public static class NormalJSONResponse extends JSONResponse
    {
	private String configuration;

	public NormalJSONResponse(String configuration)
	{
	    super(0);
	    this.configuration = configuration;
	}

	/**
	 * Gets the configuration of the plugin as string that represents a JSON
	 * object.
	 * 
	 * @return A string representing the JSON configuration object to be
	 *         used by the plugin.
	 */

	public String getConfiguration()
	{
	    return configuration;
	}
    }
}
