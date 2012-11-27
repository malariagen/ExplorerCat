package net.explorercat.actions.api.pub;

import java.util.Collections;
import java.util.List;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.staticresources.StaticResource;
import net.explorercat.staticresources.StaticResourceRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that gets the list of static resources associated with a given
 * catalog.
 * 
 * - Input : { catalogId }
 * 
 * - Output : { returnCode, [resource {name, resourceURI, type}] }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error retrieving the list of resources.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date June 2011
 */

public class GetStaticResourcesMethod extends JSONBasedAPIMethod
{
    private static final Log log = LogFactory.getLog(GetStaticResourcesMethod.class);

    // Repository of pre-generated resources that are associated with catalogues.
    private static StaticResourceRepository staticResourceRepository;

    static
    {
	ApplicationController globalController = ApplicationController.getInstance();
	staticResourceRepository = globalController.getStaticResourceRespository();
    }

    // Input Parameter: the identifier of the catalog.
    private int catalogId;

    // Output Parameter: Normal JSON response.
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public void setCatalogId(int catalogId)
    {
	this.catalogId = catalogId;
    }

    // Output parameters

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    /**
     * Executes the action, returning the location of the resource (the user can
     * use that path to retrieve it).
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Getting static resources for catatalog, id: " + catalogId);
	try
	{
	    List<StaticResource> resources = staticResourceRepository.getResourcesForCatalog(catalogId);

	    if(resources == null)
		resources = Collections.emptyList();

	    this.normalResponse = new NormalJSONResponse(resources);
	    return SUCCESS;
	}
	catch(Exception e)
	{
	    String errorMessage = "Error trying to retrieve static resources for catalog (" + catalogId + "), "
				  + e.getMessage();
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
	private List<StaticResource> resources;

	public NormalJSONResponse(List<StaticResource> resources)
	{
	    super(0);
	    this.resources = resources;
	}

	/**
	 * Gets the list of static resources associated with the catalog.
	 */

	public List<StaticResource> getResources()
	{
	    return resources;
	}
    }
}
