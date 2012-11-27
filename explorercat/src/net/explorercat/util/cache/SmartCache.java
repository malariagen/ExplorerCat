package net.explorercat.util.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.explorercat.application.ApplicationController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class to store frequently accessed instances (of any type). We use a
 * string (representing the instance) as a key. A memory limit footprint can be
 * established, when the limit is reached low accessed instances are removed
 * (any instance that is below or equal the usage average). Instances to be
 * stored in the cache should implement the SizeMeasureable interface.
 * 
 * This class also supports permanent instances that won't be removed from the
 * cache even if they are never accessed.
 * 
 * We didn't run stress tests over this class neither we check its performance
 * under these scenarios.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class SmartCache<T extends SizeMeasureable> {
	// Logging
	private static Log log = LogFactory.getLog(SmartCache.class);

	// Cache attributes.
	private Map<String, CachedInstance<T>> cacheMap;
	private int cacheMemoryLimitInMB;
	private long currentCacheSizeInBytes;

	/**
	 * Builds a cache with a memory limit expressed in MB. When the limit is
	 * reached low accessed instances are removed (any instance that is below
	 * the usage average).
	 * 
	 * @param memoryLimitInMB
	 *            The memory limit for the cache.
	 */

	public SmartCache(int memoryLimitInMB) {
		this.cacheMemoryLimitInMB = memoryLimitInMB;
		this.currentCacheSizeInBytes = 0;

		// We need a concurrent map because different threads can try to
		// access/modify the cache at the same time.
		this.cacheMap = new ConcurrentHashMap<String, CachedInstance<T>>();
	}

	/**
	 * Returns the instance associated with the key. If the cached does not
	 * contain an entry for the key, null is returned
	 * 
	 * @param key
	 *            The key that will be used to look for the instance in the
	 *            cache.
	 * @return The instance associated with the key or null if the instance is
	 *         not present. Note that this method does not return a copy of the
	 *         instance so client code should not modify it.
	 */

	public T findInstance(String key) {
		CachedInstance<T> instance = cacheMap.get(key);

		// We have a miss, return null.
		if (instance == null) {
			if (log.isDebugEnabled())
				log.debug("SmartCache MISS " + key);

			return null;
		}
		// We have a hit, increment the number of times accessed for the
		// instance.
		else {
			if (log.isDebugEnabled())
				log.debug("SmartCache HIT [#"
						+ instance.getNumberOfTimesAccessed() + "] - " + key
						+ "]");

			instance.incrementNumberOfTimesAccessed();
		}

		return instance.getResult();
	}

	/**
	 * Registers an instance into the cache.
	 * 
	 * @param key
	 *            The key that identifies the instance to be added.
	 * @param instance
	 *            Instance associated with the key.
	 * @param isPermanent
	 *            True if the instance won't be removed from the cache.
	 */

	public void addInstanceToCache(String key, T instance, boolean isPermanent) {
		long resultSizeInBytes = instance.getSizeInBytes();

		// 1 MB = 1024 * 1024 = 1048576 Bytes
		if ((currentCacheSizeInBytes + resultSizeInBytes) / 1048576F > cacheMemoryLimitInMB)
			removeLowFrequencyAccessedInstances();

		// Check if we still don't have memory to accommodate the new query.
		// Instead of throwing and exception we log the error (system will work
		// fine but slower).
		if ((currentCacheSizeInBytes + resultSizeInBytes) / 1048576 > cacheMemoryLimitInMB)
			log.error("Impossible to add instance for key: " + key
					+ " to SmartCache, no enough memory");
		else {
			// Add the query to the cache.
			cacheMap.put(key, new CachedInstance<T>(key, instance, isPermanent));

			// Update the current size of the cache.
			currentCacheSizeInBytes += resultSizeInBytes;
			
			ApplicationController.getInstance().getMemoryProfiler().logMemoryUsage();
			
			if (log.isDebugEnabled()) {
				float memoryUsed = currentCacheSizeInBytes / 1048576F;
				log.debug("SmartCache REGISTERING instance: " + key);
				log.debug("SmartCache Memory footprint: "
						+ Math.round(memoryUsed * 100.0) / 100.0 + "/"
						+ cacheMemoryLimitInMB);
			}
		}
	}

	/**
	 * Auxiliary method that makes room in the cache by removing any instance
	 * whose usage is below the occurrence average of all the queries and is NOT
	 * permanent.
	 */

	private void removeLowFrequencyAccessedInstances() {
		if (log.isDebugEnabled())
			log.debug("Removing low frequency searches from the smart cache");

		Iterator<Entry<String, CachedInstance<T>>> mapIterator = cacheMap
				.entrySet().iterator();
		float accessAverage = 0;
		int numInstancesProcessed = 0;

		// Calculate the average NOT taking into account the permanent queries.
		while (mapIterator.hasNext()) {
			CachedInstance<T> currentQuery = mapIterator.next().getValue();

			if (!currentQuery.isPermanent()) {
				accessAverage += currentQuery.getNumberOfTimesAccessed();
				++numInstancesProcessed;
			}
		}

		accessAverage /= numInstancesProcessed;

		// Remove instances below (or are equal to) the average (not permanent)
		// and updates the size of the cache.
		mapIterator = cacheMap.entrySet().iterator();

		while (mapIterator.hasNext()) {
			CachedInstance<T> currentInstance = mapIterator.next().getValue();
			int timesAccessed = currentInstance.getNumberOfTimesAccessed();
			if (timesAccessed <= accessAverage
					&& !currentInstance.isPermanent()) {
				mapIterator.remove();
				currentCacheSizeInBytes -= currentInstance.getResult()
						.getSizeInBytes();
			}
		}
	}

	/**
	 * Private class to represent cached instances. Each instance is associated
	 * with a result (represented by a generic parameter), the number of times
	 * it has been accessed and a flag that indicates if the instance is
	 * permanent or not (a permanent instance won't be removed from the cache in
	 * order to free memory).
	 * 
	 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
	 */

	private static class CachedInstance<T> {
		private String instanceKey;
		private int timesAccessed;
		private T instanceResult;
		private boolean isPermanent;

		/**
		 * Creates a new cached instance associated with a result.
		 * 
		 * @param key
		 *            The string used as key in the cache.
		 * @param result
		 *            Result associated with the key.
		 * @param isPermanent
		 *            True if the instance should never be removed from the
		 *            cache.
		 */

		public CachedInstance(String key, T result, boolean isPermanent) {
			this.instanceKey = key;
			this.timesAccessed = 0;
			this.instanceResult = result;
			this.isPermanent = isPermanent;
		}

		/**
		 * Gets the string that identifies the instance.
		 * 
		 * @return A string used as a key to identify the instance in the cache.
		 */

		public String getInstanceKey() {
			return instanceKey;
		};

		/**
		 * Gets the number of times this instance has been accessed in the
		 * cache.
		 * 
		 * @return The number of times the instance has been accessed.
		 */

		public int getNumberOfTimesAccessed() {
			return timesAccessed;
		}

		/**
		 * Increments the number of times the instance has been accessed.
		 */

		public void incrementNumberOfTimesAccessed() {
			++this.timesAccessed;
		}

		/**
		 * Gets the result associated with the instance.
		 * 
		 * @return The result stored for that instance/key.
		 */

		public T getResult() {
			return instanceResult;
		}

		/**
		 * Check if the instance if permanent in the cache.
		 * 
		 * @return True if the instance is permanent (won't be removed from the
		 *         cache in order to free up space), false otherwise.
		 */

		public boolean isPermanent() {
			return isPermanent;
		}
	}
}
