package net.explorercat.actions.api.admin;

import net.explorercat.application.ApplicationController;
import net.explorercat.plugins.PluginHashException;
import net.explorercat.plugins.PluginHashRepository;
import net.explorercat.util.misc.HexCodeObfuscator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that marks a plugin configuration hash as permanent (it won't be
 * deleted and will be copied into a backup file). Usually we mark as permanent
 * any hash involved in a published URL (like the ones published in papers).
 * Notice that to use this API method you need admin rights.
 * 
 * - makePluginHashPermament: Input{user, password, hash}, Output{returnCode}.
 * Where hash is the key that identifies the plugin configuration we want to
 * mark as permanent.
 * 
 * Error codes:
 * 
 * 3 - Hash key provided wasn't registered.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 May 2011
 */

public class MakePluginConfigurationPermanentMethod extends AdminAPIMethod
{
    private static final Log log = LogFactory.getLog(MakePluginConfigurationPermanentMethod.class);

    // The global repository of plugin hashes.
    private static final PluginHashRepository PLUGIN_HASH_REPOSITORY = ApplicationController.getInstance().getPluginHashRepository();

    // Input Parameter: Hash that will be marked as permanent.
    private String hash;

    // Output Parameter: Normal JSON response (success).
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public void setHash(String hash)
    {
	// Remove noise from the obfuscated key
	this.hash = HexCodeObfuscator.deobfuscateKey(hash);
    }

    // Output parameters getters

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    /**
     * Marks the plugin hash passed as a parameter as permanent, meaning that it
     * won't be removed from the system or overwritten by other plugin
     * configuration with the same hash key.
     */

    @Override
    public String execute()
    {
	try
	{
	    if(hasUserAdminRights())
	    {
		if(this.hash == null)
		{
		    setErrorResponse(1, "Plugin hash not provided");
		    return ERROR;
		}

		if(PLUGIN_HASH_REPOSITORY.findPluginHash(hash) == null)
		{
		    setErrorResponse(3, "The hash key provided wasn't registered");
		    return ERROR;
		}

		PLUGIN_HASH_REPOSITORY.markHashAsPermanent(hash);
		this.normalResponse = new NormalJSONResponse();

		if(log.isDebugEnabled())
		    log.debug("Plugin hash (" + hash + ") marked as permanent");

		return SUCCESS;
	    }
	    else
	    {
		setErrorResponse(4, "User has not rights to perform the operation");
		return ERROR;
	    }
	}
	catch(PluginHashException e)
	{
	    log.error(e.getMessage());
	    setErrorResponse(2, e.getMessage());
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
