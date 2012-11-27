package net.explorercat.staticresources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A repository that stores static resources like files or analysis results. It
 * provides functionality to query resources by catalogue id, name or type.
 * 
 * Usually a resources is represented by an URI (Universal Resource Identifier)
 * so this class does NOT provides data streams for the resources. Please note
 * that these resources do NOT expire (they have not been generated on the fly).
 * 
 * Concurrency: This repository is created at startup time and then becomes read
 * only so there is no need to protect against concurrent modifications.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Dec 2010
 */

public class StaticResourceRepository
{
    private static final Log log = LogFactory.getLog(StaticResourceRepository.class);

    // Maps catalogue ids to resources.
    private Map<Integer, List<StaticResource>> resourceRepository;

    public StaticResourceRepository()
    {
	resourceRepository = new HashMap<Integer, List<StaticResource>>();
    }

    public void registerResource(int catalogId, StaticResource resource)
    {
	if(!resourceRepository.containsKey(catalogId))
	    resourceRepository.put(catalogId, new ArrayList<StaticResource>());

	resourceRepository.get(catalogId).add(resource);

	if(log.isDebugEnabled())
	    log.debug("Static resource added: " + catalogId + ":" + resource.getName() + ":"
		      + resource.getResourceURI());
    }

    /**
     * Gets all the static resources associated with the given catalog.
     */

    public List<StaticResource> getResourcesForCatalog(int catalogId)
    {
	return resourceRepository.get(catalogId);
    }

    /**
     * Gets the types of resources available for the given catalog.
     */

    public List<String> getResourceTypesForCatalog(int catalogId)
    {
	List<StaticResource> resources = resourceRepository.get(catalogId);
	Set<String> types = new HashSet<String>();

	if(resources != null)
	    for(StaticResource currentResource : resources)
		types.add(currentResource.getType());

	return new ArrayList<String>(types);
    }

    /**
     * Gets the list of the resources of a given type for a given catalog. Note
     * the type checking is NOT case sensitive.
     */

    public List<StaticResource> getResourcesForCatalog(int catalogId, String type)
    {
	List<StaticResource> resources = resourceRepository.get(catalogId);
	List<StaticResource> filteredResources = new ArrayList<StaticResource>();
	String lowerCaseType = type.toLowerCase();

	for(StaticResource currentResource : resources)
	    if(currentResource.getType().toLowerCase().equals(lowerCaseType))
		filteredResources.add(currentResource);

	return filteredResources;
    }
}
