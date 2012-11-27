package net.explorercat.actions.api.pub;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.plugins.PluginHashException;
import net.explorercat.plugins.PluginHashRepository;
import net.explorercat.util.misc.HexCodeObfuscator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that registers a plugin configuration object (using the JSON
 * format) associating it with a hash key. The configuration object can be
 * recovered using this hash key (that is returned to the user).
 * 
 * - Input : { configuration }
 * 
 * - Output : { returnCode, hash }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error registering the plug-in configuration (it wasn't
 * registered).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 May 2011
 */

public class RegisterPluginConfigurationMethod extends JSONBasedAPIMethod
{
    // Logging
    private static final Log log = LogFactory.getLog(RegisterPluginConfigurationMethod.class);

    // The global repository of plugin hashes.
    private static final PluginHashRepository PLUGIN_HASH_REPOSITORY = ApplicationController.getInstance().getPluginHashRepository();

    // Input Parameter: configuration of the plugin (JSON format).
    private String configuration;

    // Output Parameter: Normal JSON response (success).
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public void setConfiguration(String configuration)
    {
	this.configuration = configuration;
    }

    // Output parameters getters

    public NormalJSONResponse getNormalResponse()
    {
	return normalResponse;
    }

    /**
     * Executes the action, registering the given plugin configuration in the
     * repository and returning the hash identifier that must be used to
     * retrieve the configuration from the system.
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Registering a plugin configuration: " + configuration);

	try
	{
	    String pluginHash = PLUGIN_HASH_REPOSITORY.registerConfigurationHash(configuration);
	    this.normalResponse = new NormalJSONResponse(pluginHash);
	    return SUCCESS;
	}
	catch(PluginHashException e)
	{
	    e.printStackTrace();
	    String errorMessage = "Error trying to register a plugin configuration, " + e.getMessage();
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
	private String hash;

	public NormalJSONResponse(String hash)
	{
	    super(0);

	    // Add some noise to obfuscate the hash key.
	    this.hash = HexCodeObfuscator.obfuscateKey(hash);
	}

	/**
	 * Gets the hash key that identifies the plugin configuration registered
	 * in the system.
	 * 
	 * @return The hash key that has to be used in order to retrieve the
	 *         plugin configuration from the system.
	 */

	public String getHash()
	{
	    return hash;
	}
    }
}
