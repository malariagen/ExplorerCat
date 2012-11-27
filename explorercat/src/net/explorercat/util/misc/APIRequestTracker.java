package net.explorercat.util.misc;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple class that blacklists addresses if signs of abuse are found (too many
 * requests in a given period of time).
 * 
 * @author Jacob Almagro-Garcia
 * @date 21 June 2011
 */

public class APIRequestTracker
{
    private static final Log log = LogFactory.getLog(APIRequestTracker.class);

    // Maximum number of requests allowed for a specific address during a counting interval.
    private int maxNumberOfRequestsPerInterval;

    // Timer in charge of cleaning requests count periodically.    
    private Timer requestCountCleaner;

    private Map<String, Integer> blacklist;
    private Map<String, Integer> requestCounts;

    /**
     * Creates a new tracker for request addresses.
     * 
     * @param requestCountInterval The interval (in milliseconds) in which
     *        requests are counted.
     * @param maxRequestPerInterval Maximum number of requests per address
     *        during a counting interval.
     */

    public APIRequestTracker(long requestCountInterval, int maxRequestPerInterval)
    {
	blacklist = new ConcurrentHashMap<String, Integer>();
	requestCounts = new ConcurrentHashMap<String, Integer>();
	maxNumberOfRequestsPerInterval = maxRequestPerInterval;

	requestCountCleaner = new Timer();
	requestCountCleaner.schedule(new RequestCountCleaner(), requestCountInterval, requestCountInterval);
    }

    /**
     * Checks if the given address is blacklisted (i.e. the request will be
     * denied).
     * 
     * @param address The address of the host calling the API method.
     * @return True if the request must be denied, false otherwise.
     */

    public boolean isAddressBlacklisted(String address)
    {
	updateRequestsCount(address);
	return (blacklist.get(address) != null);
    }

    /**
     * Updates the count of requests by address, if an address passes the
     * established limit it is blacklisted.
     */

    private void updateRequestsCount(String requestAddress)
    {
	Integer currentCount = requestCounts.get(requestAddress);

	if(currentCount == null)
	{
	    currentCount = 1;
	    requestCounts.put(requestAddress, currentCount);
	}
	else
	    requestCounts.put(requestAddress, ++currentCount);

	if(currentCount > maxNumberOfRequestsPerInterval)
	{
	    if(log.isDebugEnabled())
		log.debug("[Blacklisted] Address " + requestAddress + " has been blacklisted");

	    blacklist.put(requestAddress, currentCount);
	}
    }

    /**
     * Gets a set containing all the blacklisted addresses.
     */

    public Set<String> getBlacklistedAddresses()
    {
	return requestCounts.keySet();
    }

    /**
     * Clears all the request counts.
     */

    private void removeRequestCounts()
    {
	if(log.isDebugEnabled())
	    log.debug("Clearing request counts");

	requestCounts.clear();
    }

    /**
     * The cleaner is a thread running in the background that cleans request
     * counts regularly (notice this class is not static, it is bound to the
     * outer object).
     */

    public class RequestCountCleaner extends TimerTask
    {
	@Override
	public void run()
	{
	    removeRequestCounts();
	}
    }
}
