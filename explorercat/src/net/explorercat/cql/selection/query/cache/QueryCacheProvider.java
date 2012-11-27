package net.explorercat.cql.selection.query.cache;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.explorercat.cql.selection.Selection;
import net.explorercat.util.cache.SmartCache;

/**
 * Provides query caches for catalogs. The default behaviour is to create a new
 * cache per catalog, each catalog has a different cache to store queries.
 * 
 * You can think of a provider as a pool of caches for the catalogs. You can
 * create several providers if you plan to use query caches for different
 * purposes.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 24 Aug 2010
 */

public class QueryCacheProvider
{
    // Map of caches (catalogId -> cache).
    private Map<Integer, SmartCache<Selection>> cacheMap;

    // Memory footprint limit (10 MB is the default value per catalog).
    private int memoryLimitInMB = 10;

    // Flag that enables/disables the caches (true by default).
    private boolean isCachedEnabled = true;

    // Catalogs excluded, they won't have a cache.
    private Set<Integer> excludedCatalogIds = null;

    /**
     * Creates a new cache factory configured with the given value.
     * 
     * @param memoryLimitInMB Memory footprint limit for the cache.
     * @param isCachedEnabled True if the cache is enabled, false otherwise.
     * @param excludedCatalogsIds List of catalogs to be excluded from the
     *        cache.
     */

    public QueryCacheProvider(int memoryLimitInMB, boolean isCachedEnabled, Set<Integer> excludedCatalogsIds)
    {
	cacheMap = new ConcurrentHashMap<Integer, SmartCache<Selection>>();

	this.memoryLimitInMB = memoryLimitInMB;
	this.isCachedEnabled = isCachedEnabled;
	this.excludedCatalogIds = excludedCatalogsIds;
    }

    /**
     * Sets the catalogs that are NOT allowed to have a cache. Any cache for a
     * catalog that is in the given set will be removed.
     * 
     * @param catalogIds The set of catalog identifiers that are NOT allowed to
     *        have a query cache. If null is provided then, all catalogs will
     *        have a query cache. The empty set or null means no catalog will be
     *        excluded.
     */

    public void setExcludedCatalogIds(Set<Integer> catalogIds)
    {
	excludedCatalogIds = catalogIds;

	// Check for a null parameter and convert it to the empty set.
	if(excludedCatalogIds == null)
	    excludedCatalogIds = new HashSet<Integer>();

	if(catalogIds != null)
	{
	    Set<Integer> cachesToBeRemoved = new HashSet<Integer>();

	    // Check registered caches that are not allowed and remove them.
	    for(Integer id : cacheMap.keySet())
		if(excludedCatalogIds.contains(id))
		    cachesToBeRemoved.add(id);

	    for(Integer id : cachesToBeRemoved)
		cacheMap.remove(id);
	}
    }

    /**
     * Gets the query cache for the given catalog or null if the catalog is not
     * allowed to have a cache.
     * 
     * @param catalogId The identifier of the catalog whose cache we are
     *        requesting.
     * @return The cache associated with the catalog.
     */

    public SmartCache<Selection> getQueryCacheForCatalog(int catalogId)
    {
	// Check if the cache is enabled and if the catalog is allowed to have a cache.
	if(!isCachedEnabled || excludedCatalogIds.contains(catalogId))
	    return null;

	// Allowed, check if we need to create the cache.
	if(cacheMap.containsKey(catalogId))
	    return cacheMap.get(catalogId);
	else
	{
	    SmartCache<Selection> smartCache = new SmartCache<Selection>(memoryLimitInMB);
	    cacheMap.put(catalogId, smartCache);
	    return smartCache;
	}
    }
}
