package net.explorercat.application.configurators;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.util.misc.APIRequestTracker;

/**
 * Configurator-builder in charge of creating instances for the API request
 * tracker class.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 21 Jun 2011
 */

public class APIRequestTrackerConfigurator
{
    // Properties coming from the global configuration file.
    private static final String MAX_REQUESTS_PER_INTERVAL;
    private static final String REQUEST_COUNTING_INTERVAL;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	MAX_REQUESTS_PER_INTERVAL = lookup.getGlobalProperty("config.apiRequestTracker.maxRequestsPerInterval");
	REQUEST_COUNTING_INTERVAL = lookup.getGlobalProperty("config.apiRequestTracker.requestCountInterval");
    }

    /**
     * Builds a configured instance.
     * 
     * @return A configured instance of the proper class.
     */

    public static APIRequestTracker buildInstance()
    {
	int maxNumRequests = Integer.parseInt(MAX_REQUESTS_PER_INTERVAL);
	long countingIntervalInMs = 1000 * Long.parseLong(REQUEST_COUNTING_INTERVAL);

	return new APIRequestTracker(countingIntervalInMs, maxNumRequests);
    }
}
