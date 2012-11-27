package net.explorercat.application.configurators;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.cql.selection.query.cache.QueryCacheProvider;

/**
 * Configurator-builder in charge of creating instances of query cache
 * providers.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Nov 2010
 */

public class QueryCacheProviderConfigurator
{
    private static final int MEMORY_LIMIT_IN_MB;
    private static final boolean IS_CACHE_ENABLED;
    private static final String EXCLUSION_LIST;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	MEMORY_LIMIT_IN_MB = Integer.parseInt(lookup.getGlobalProperty("config.smartCache.memorylimitInMB"));
	IS_CACHE_ENABLED = Boolean.parseBoolean(lookup.getGlobalProperty("config.smartCache.enabled"));
	EXCLUSION_LIST = lookup.getGlobalProperty("config.smartCache.catalogExclusionList");
    }

    /**
     * Builds a configured instance.
     * 
     * @return A configured instance of the proper class.
     */

    public static QueryCacheProvider buildInstance()
    {
	StringTokenizer listTonekizer = new StringTokenizer(EXCLUSION_LIST, ",");
	Set<Integer> excludedCatalogIds = new HashSet<Integer>();

	while(listTonekizer.hasMoreTokens())
	    excludedCatalogIds.add(Integer.parseInt(listTonekizer.nextToken()));

	return new QueryCacheProvider(MEMORY_LIMIT_IN_MB, IS_CACHE_ENABLED, excludedCatalogIds);
    }
}
