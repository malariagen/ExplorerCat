package net.explorercat.cql.result;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import net.explorercat.cql.result.builders.ResultGenerationException;
import net.explorercat.cql.result.builders.ResultGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A cache that stores CQL result generators for a certain amount of time,
 * allowing client applications to perform pagination from the server side.
 * 
 * A TimerTask removes the expired results periodically.
 * 
 * After registering a result (generator), a ticket number and a hash code are
 * returned to the user. They must be used to retrieve the result from the
 * cache.
 * 
 * Note that this cache stores result generators, no complete results. New
 * results will be generated on the fly by request. Usually the user request a
 * result that contains only the current "page" he is using.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 17 Aug 2010
 */

public class ResultPaginationCache
{
    // Logging
    private static final Log log = LogFactory.getLog(ResultPaginationCache.class);

    // Timer in charge of removing expired results periodically.    
    private Timer resultCleaner;
    private long cleaningInterval;
    private int resultExpirationTimeInMinutes;

    // Cache with maxNumberOfEntries elements.
    private TimeLimitedResultEntry[] resultGeneratorCache;
    private int maxNumberOfEntries;
    private int currentNumberOfEntries;

    /**
     * Builds a new pagination cache for result generators.
     * 
     * @param resultExpirationTime The maximum amount of time (in minutes) that
     *        a result will be stored in the cache after being accessed.
     * @param maxNumberOfEntries Maximum number of entries that the cache will
     *        store. When this limit is reached and a new entry is registered
     *        the oldest entry is removed to make room.
     * @param cleaningIntervalInMs Time interval for cleaning the cache of
     *        expired results, in milliseconds.
     */

    public ResultPaginationCache(int resultExpirationTime, int maxNumberOfEntries, long cleaningIntervalInMs)
    {
	this.resultExpirationTimeInMinutes = resultExpirationTime;
	this.maxNumberOfEntries = maxNumberOfEntries;
	this.resultGeneratorCache = new TimeLimitedResultEntry[maxNumberOfEntries];
	this.currentNumberOfEntries = 0;

	this.cleaningInterval = cleaningIntervalInMs;

	this.resultCleaner = new Timer();
	this.resultCleaner.schedule(new ResultEntryCleaner(), cleaningInterval, cleaningInterval);
    }

    /**
     * Registers a new result generator in the cache.
     * 
     * @param resultGenerator The generator that will be cached.
     * @return A result identifier that contains the ticket number and hash code
     *         that identifies the entry.
     */

    public ResultIdentifier registerResultGenerator(ResultGenerator resultGenerator)
    {
	if(log.isDebugEnabled())
	    log.debug("Registering result in pagination cache");

	TimeLimitedResultEntry entry = new TimeLimitedResultEntry(resultGenerator, maxNumberOfEntries);
	int hashCode = resultGenerator.hashCode();
	int cachePosition = (int) (entry.getTicketNumber() % maxNumberOfEntries);

	ResultIdentifier id = new ResultIdentifier(hashCode, entry.getTicketNumber());
	resultGeneratorCache[cachePosition] = entry;
	++currentNumberOfEntries;

	// Return the hashCode and ticket number as identifiers for the entry.
	return id;
    }

    /**
     * Finds a result in the cache for the given entry identifier. This method
     * finds the generator in the cache and builds the result.
     * 
     * @param entryIdentifier An result identifier that consists of the hash
     *        code of the generator and the unique ticket number assigned when
     *        the result generator was registered in the cache.
     * @param numEntities Number of entities to be selected from the result.
     * @param offset Starting position that will be used when selecting the
     *        entities (where 0 is the first one).
     * 
     * @return A result for the given parameters or null if the corresponding
     *         result generator was not registered in the cache.
     */

    public Result findResult(ResultIdentifier entryIdentifier, int numEntities, int offset)
	throws ResultGenerationException
    {
	TimeLimitedResultEntry generatorEntry = resultGeneratorCache[entryIdentifier.getTicketNumber()];

	if(generatorEntry != null
	   && generatorEntry.getGenerator().hashCode() == entryIdentifier.getResultGeneratorHashCode())
	    return generatorEntry.getGenerator().generateResult(numEntities, offset);
	else
	    return null;
    }

    /**
     * Finds a result generator for the given result identifier.
     * 
     * @param entryIdentifier An entry identifier that consists of the hash code
     *        of the generator and the unique ticket number assigned when the
     *        result generator was registered in the cache.
     * @return The result generator associated with the given entry
     */

    public ResultGenerator findResultGenerator(ResultIdentifier entryIdentifier) throws ResultGenerationException
    {
	TimeLimitedResultEntry builderEntry = resultGeneratorCache[entryIdentifier.getTicketNumber()];

	if(builderEntry != null
	   && builderEntry.getGenerator().hashCode() == entryIdentifier.getResultGeneratorHashCode())
	    return builderEntry.getGenerator();
	else
	    return null;
    }

    /**
     * Removes all the result generator whose live time has expired from the
     * cache.
     */

    private void removeExpiredResults()
    {
	if(log.isDebugEnabled())
	    log.debug("Cleaning expired results from the pagination cache");

	int numEntriesRemoved = 0;

	for(int i = 0; i < maxNumberOfEntries; ++i)
	{
	    TimeLimitedResultEntry generatorEntry = this.resultGeneratorCache[i];

	    if(generatorEntry != null && generatorEntry.getMinutesSinceLastAccess() >= resultExpirationTimeInMinutes)
	    {
		++numEntriesRemoved;
		this.resultGeneratorCache[i] = null;
	    }
	}

	currentNumberOfEntries -= numEntriesRemoved;

	if(log.isDebugEnabled())
	    log.debug("Expired results removed: " + numEntriesRemoved);
    }

    /**
     * Removes the result generator associated with the given result identifier.
     * 
     * @param entryIdentifier The identifier (hash + ticket) of the entry to be
     *        removed from the cache.
     */

    public void removeResult(ResultIdentifier entryIdentifier) throws ResultPaginationCacheException
    {
	int index = entryIdentifier.getTicketNumber();
	TimeLimitedResultEntry entry = resultGeneratorCache[index];

	if(entry != null && entry.getGenerator().hashCode() == entryIdentifier.getResultGeneratorHashCode())
	{
	    resultGeneratorCache[index] = null;
	    --currentNumberOfEntries;
	}
	else
	{
	    throw new ResultPaginationCacheException("Error removing the result from the pagination cache, "
						     + entryIdentifier);
	}
    }

    /**
     * Gets the total number of results currently registered in the pagination
     * cache.
     * 
     * @return The number of results in the cache.
     */

    public int getNumberOfResults()
    {
	return currentNumberOfEntries;
    }

    /**
     * Turns off the cleaner before leaving.
     */

    @Override
    public void finalize()
    {
	this.resultCleaner.cancel();
    }

    /**
     * Inner class to keep track of the time a result spends without being
     * accessed. It implements a ticketing system to resolve collisions in the
     * cache.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 17 Aug 2010
     */

    private static class TimeLimitedResultEntry
    {
	// Ticket number generator. 
	// Don't worry about running out of tickets you have about 10^18 of them.
	private static final AtomicLong ticketNumberGenerator = new AtomicLong();

	// Ticket number that is used to index the entries in the cache.
	private int ticketNumber;

	private long lastAccessTime;
	private ResultGenerator resultGenerator;

	/**
	 * Builds a new entry registering the creation time.
	 */

	public TimeLimitedResultEntry(ResultGenerator generator, int maxNumberOfEntries)
	{
	    this.resultGenerator = generator;
	    this.lastAccessTime = Calendar.getInstance().getTimeInMillis();
	    this.ticketNumber = (int) (ticketNumberGenerator.getAndIncrement() % maxNumberOfEntries);
	}

	/**
	 * Gets the result generator and updates the expire time for it.
	 */

	public ResultGenerator getGenerator()
	{
	    lastAccessTime = Calendar.getInstance().getTimeInMillis();
	    return resultGenerator;
	}

	/**
	 * Gets how many minutes has passed since the last access.
	 */

	public int getMinutesSinceLastAccess()
	{
	    return (int) ((Calendar.getInstance().getTimeInMillis() - lastAccessTime) / 60000);
	}

	/**
	 * Gets the ticket number associated with the entry.
	 * 
	 * @return A unique ticket number associated with the entry that will be
	 *         used to resolve collisions in the cache.
	 */

	public int getTicketNumber()
	{
	    return ticketNumber;
	}
    }

    /**
     * The cleaner is a thread running in the background that cleans expired
     * results regularly (notice this class is not static, it is bound to the
     * outer object).
     */

    public class ResultEntryCleaner extends TimerTask
    {
	@Override
	public void run()
	{
	    removeExpiredResults();
	}
    }
}
