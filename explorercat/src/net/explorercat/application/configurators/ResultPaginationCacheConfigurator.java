package net.explorercat.application.configurators;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.cql.result.ResultPaginationCache;

/**
 * Configurator-builder in charge of creating instances of result pagination caches.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Nov 2010
 */

public class ResultPaginationCacheConfigurator
{
    // Properties coming from the global configuration file.
    private static final String RESULT_EXPIRATION_TIME;
    private static final String CACHE_NUM_ENTRIES_LIMIT;
    private static final String CACHE_CLEANING_INTERVAL;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	RESULT_EXPIRATION_TIME = lookup.getGlobalProperty("config.resultPaginationCache.expirationTimeInMinutes");
	CACHE_CLEANING_INTERVAL = lookup.getGlobalProperty("config.resultPaginationCache.cleaningIntervalInSeconds");
	CACHE_NUM_ENTRIES_LIMIT = lookup.getGlobalProperty("config.resultPaginationCache.numEntriesLimit");
    }

    /**
     * Builds a configured instance.
     * 
     * @return A configured instance of the proper class.
     */

    public static ResultPaginationCache buildInstance()
    {
	int expirationTime = Integer.parseInt(RESULT_EXPIRATION_TIME);
	int maxNumEntries = Integer.parseInt(CACHE_NUM_ENTRIES_LIMIT);
	long cleaningIntervalInMs = 1000 * Long.parseLong(CACHE_CLEANING_INTERVAL);

	return new ResultPaginationCache(expirationTime, maxNumEntries, cleaningIntervalInMs);
    }
}
