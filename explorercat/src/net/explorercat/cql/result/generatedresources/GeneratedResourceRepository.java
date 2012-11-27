package net.explorercat.cql.result.generatedresources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import net.explorercat.cql.result.ResultIdentifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A repository that stores generated resources like files or analysis results.
 * It also provides functionality to register resources that are being
 * generated.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 25 Oct 2010
 */

public class GeneratedResourceRepository
{
    // Logging
    private static final Log log = LogFactory.getLog(GeneratedResourceRepository.class);

    // Interval for removing expired resources.
    private final long CLEANING_INTERVAL_IN_SECONDS;

    // Several resources can be mapped to the same result.
    private Map<ResultIdentifier, Map<String, GeneratedResource>> resourceRepository;

    // Map of results being generated but not in the repository yet.
    // Links the unique identifier of the resource to the expire time.
    private Map<String, Long> resourcesBeingGenerated;

    // Current number of resources in the repository.
    private int numOfRegisteredResources;

    // Timer in charge of removing expired resources periodically.    
    private Timer resourceCleaner;

    /**
     * Creates a new repository that cleans expired resources periodically using
     * the given interval.
     * 
     * @param cleaningIntervalInSeconds Interval for removing expired resources.
     */

    public GeneratedResourceRepository(long cleaningIntervalInSeconds)
    {
	CLEANING_INTERVAL_IN_SECONDS = cleaningIntervalInSeconds;
	resourceRepository = new ConcurrentHashMap<ResultIdentifier, Map<String, GeneratedResource>>();
	resourcesBeingGenerated = new ConcurrentHashMap<String, Long>();
	numOfRegisteredResources = 0;

	long cleaningInterval = 1000 * CLEANING_INTERVAL_IN_SECONDS;
	resourceCleaner = new Timer();
	resourceCleaner.schedule(new ExpiredResourceCleaner(), cleaningInterval, cleaningInterval);
    }

    /**
     * Registers a resource associated with a result in the repository.
     * 
     * @param resultId The identifier of the result (a ticket-hash pair).
     * @param resource The associated resource.
     */

    public void registerResource(ResultIdentifier resultId, GeneratedResource resource)
    {
	if(log.isDebugEnabled())
	    log.debug("Registering generated resource [" + resultId + ", " + resource.getResourceId() + "]");

	if(!resourceRepository.containsKey(resultId))
	    resourceRepository.put(resultId, new ConcurrentHashMap<String, GeneratedResource>());

	resourceRepository.get(resultId).put(resource.getResourceId(), resource);
	++numOfRegisteredResources;

	// Remove the resource form the resources being generated if present.
	resourcesBeingGenerated.remove(resource.getResourceId());
    }

    /**
     * Removes all the resources associated with a result from the repository.
     * 
     * @param resultId The identifier of the result (a ticket-hash pair).
     */

    public void removeResourcesForResult(ResultIdentifier resultId)
    {
	if(log.isDebugEnabled())
	    log.debug("Removing generated resources for " + resultId);

	Map<String, GeneratedResource> resourceMap = resourceRepository.get(resultId);

	if(resourceMap != null)
	{
	    numOfRegisteredResources -= resourceMap.size();
	    resourceRepository.remove(resultId);
	}
    }

    /**
     * Removes a resource from the repository.
     * 
     * @param resultId The identifier of the result that is associated with the
     *        resource.
     * @param resourceId The identifier of the resource.
     */

    public void removeResource(ResultIdentifier resultId, String resourceId)
    {
	if(log.isDebugEnabled())
	    log.debug("Removing generated resource " + resourceId + " for " + resultId);

	Map<String, GeneratedResource> resourceMap = resourceRepository.get(resultId);

	if(resourceMap != null)
	{
	    if(resourceMap.remove(resourceId) != null)
		--numOfRegisteredResources;
	}
    }

    /**
     * Finds a the location of a resource associated with a result.
     * 
     * @param resultId The identifier of the result (a ticket-hash pair).
     * @param resourceId The identifier of the resource.
     * @return The resource associated with the result and the given identifier
     *         or null if no resource is found.
     */

    public GeneratedResource findResource(ResultIdentifier resultId, String resourceId)
    {	
	Map<String, GeneratedResource> resourceMap = resourceRepository.get(resultId);
		
	if(resourceMap != null)
	{
	    return resourceMap.get(resourceId);
	}
	else
	    return null;
    }

    /**
     * Checks if a resource is being generated (it is not in the repository
     * yet).
     * 
     * @param resourceId The identifier of the resource.
     * @return True if the resource is being generated, false otherwise.
     */

    public boolean isResourceBeingGenerated(String resourceId)
    {
	return resourcesBeingGenerated.containsKey(resourceId);
    }

    /**
     * Removes a resource that has being registered as being generated (work in
     * progress).
     * 
     * @param resourceId The identifier of the resource.
     */

    public void removeResourceBeingGenerated(String resourceId)
    {
	resourcesBeingGenerated.remove(resourceId);
    }

    /**
     * Registers the resource as being generated.
     * 
     * @param resourceId The unique identifier of the resource.
     * @param expirationTimeInMs The time when the resource will expire.
     */

    public void registerResourceAsBeingGenerated(String resourceId, long expirationTimeInMs)
    {
	resourcesBeingGenerated.put(resourceId, expirationTimeInMs);
    }

    /**
     * Gets the number of resources registered in the repository.
     * 
     * @return The number of resources in the repository.
     */

    public int getNumberOfRegisteredResources()
    {
	return this.numOfRegisteredResources;
    }

    /**
     * Auxiliary method that removes expired resources from the repository. It
     * also removes expired resources that are being generated.
     */

    private void removeExpiredResources()
    {
	if(log.isDebugEnabled())
	    log.debug("Cleaning expired resources");

	// Registered resources.
	Collection<Map<String, GeneratedResource>> resourceMaps = resourceRepository.values();
	Iterator<Map<String, GeneratedResource>> resourceMapIterator = resourceMaps.iterator();
	long currentTime = GregorianCalendar.getInstance().getTimeInMillis();

	while(resourceMapIterator.hasNext())
	{
	    Map<String, GeneratedResource> currentMap = resourceMapIterator.next();
	    Collection<GeneratedResource> resources = currentMap.values();
	    Iterator<GeneratedResource> resourceIterator = resources.iterator();

	    List<String> resourcesToDelete = new ArrayList<String>();

	    while(resourceIterator.hasNext())
	    {
		GeneratedResource resource = resourceIterator.next();

		if(currentTime >= resource.getExpirationTime())
		{
		    // Physically delete the result
		    if(!resource.deleteResource())
			log.error("Error trying to remove resource : " + resource.getResourceURI());
		    else
			resourcesToDelete.add(resource.getResourceId());
		}
	    }

	    for(String resourceId : resourcesToDelete)
		currentMap.remove(resourceId);
	}

	// Resources registered as being generated.
	Set<String> ids = resourcesBeingGenerated.keySet();

	for(String resourceId : ids)
	{
	    if(currentTime >= resourcesBeingGenerated.get(resourceId))
		resourcesBeingGenerated.remove(resourceId);
	}
    }

    /**
     * Turns off the cleaner before leaving.
     */

    @Override
    public void finalize()
    {
	this.resourceCleaner.cancel();
    }

    /**
     * The cleaner is a thread running in the background that cleans expired
     * resources regularly.
     */

    public class ExpiredResourceCleaner extends TimerTask
    {
	@Override
	public void run()
	{
	    removeExpiredResources();
	}
    }
}
