package net.explorercat.dataaccess.cached;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import net.explorercat.data.PublicAPIKey;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PublicAPIKeyDAO;

/**
 * Implementation of the API key DAO that relies on a memory cache. Keys will be
 * loaded at creation time and the permanent storage will be automatically
 * updated when necessary.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Jun 2011
 */

public abstract class PublicAPIKeyDAOCached implements PublicAPIKeyDAO
{
    // First value that will be used to generate the hash keys.
    private static final long INITIAL_HASH_COUNT = 10000;

    // Hash counter (we'll simply translate the counter value into hex).
    protected final AtomicLong hashCounter = new AtomicLong();

    protected Map<String, PublicAPIKey> keyMap;

    /**
     * Creates the DAO initializing the cache.
     */

    public PublicAPIKeyDAOCached() throws DAOException
    {
	this.keyMap = new ConcurrentHashMap<String, PublicAPIKey>();
	initCache();
	syncHashCounterValue();
    }

    /**
     * Initializes the cache loading all the keys from the DB.
     */

    private void initCache() throws DAOException
    {
	List<PublicAPIKey> keys = loadKeys();
	for(PublicAPIKey currentKey : keys)
	    this.keyMap.put(currentKey.getKey(), currentKey);
    }

    @Override
    public void disableKey(String keyHash) throws DAOException
    {
	PublicAPIKey key = this.keyMap.get(keyHash);

	if(key != null)
	    key.disable();

	disableKeyInStorage(keyHash);
    }

    @Override
    public PublicAPIKey findKey(String keyHash) throws DAOException
    {
	return this.keyMap.get(keyHash);
    }

    @Override
    public PublicAPIKey generateAndInsertKey(String host, String email) throws DAOException
    {
	String hash = Long.toHexString(this.hashCounter.getAndIncrement());

	if(this.keyMap.containsKey(hash))
	    throw new DAOException("A duplicated API key hash has been generated: " + hash);

	PublicAPIKey apiKey = new PublicAPIKey(hash, host, email, true);
	this.keyMap.put(hash, apiKey);
	insertKeyIntoStorage(apiKey);

	return apiKey;
    }

    /**
     * Synchronises the atomic counter value with the last value present in the
     * storage.
     * 
     * @throws DAOException
     */

    protected void syncHashCounterValue() throws DAOException
    {
	long counterValue = INITIAL_HASH_COUNT;
	PublicAPIKey lastKeyAdded = findLastKeyAdded();

	if(lastKeyAdded != null)
	    counterValue = new BigInteger(lastKeyAdded.getKey(), 16).longValue();

	this.hashCounter.set(counterValue);
    }

    /**
     * Loads all the keys from the storage layer.
     * 
     * @throws DAOException
     */

    abstract protected List<PublicAPIKey> loadKeys() throws DAOException;

    /**
     * Disables the given key (updates the permanent storage).
     */

    abstract protected void disableKeyInStorage(String keyHash) throws DAOException;

    /**
     * Inserts a new key into the permanent storage.
     */

    abstract protected void insertKeyIntoStorage(PublicAPIKey key) throws DAOException;

    /**
     * Finds the last API key added to the storage.
     * 
     * @return The last key instance registered or null if no instances were
     *         found.
     */

    abstract protected PublicAPIKey findLastKeyAdded() throws DAOException;
}
