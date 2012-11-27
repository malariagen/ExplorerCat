package net.explorercat.plugins;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import net.explorercat.application.ApplicationController;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PluginHashDAO;
import net.explorercat.util.cache.SizeMeasureable;
import net.explorercat.util.cache.SmartCache;

/**
 * This class uses hash values to associate a unique URL to a concrete plugin
 * configuration. The plugin configuration is a string representing a JSON
 * object that will be passed to the plugin when invoked with the proper hash in
 * the URL. Notice this hash code is unique and is generated on demand for each
 * request.
 * 
 * We use the naive approach of generating hashes based on the value of a long
 * integer counter, with the potential problem of running out of hashes after a
 * long while (10^18 hashes). More important for us is the ability to keep vital
 * URL hashes under control (for instance, the URLs published on papers). An API
 * method is provided to make any URL hash permanent (only accessible by the
 * admin user). The system will mark these hashes in the DB so they are never
 * removed (even after running out of hashes). For safety, they will be copied
 * in a backup file.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 May 2011
 */

public class PluginHashRepository
{
    // First value that will be used to generate the hash keys.
    private static final long INITIAL_HASH_COUNT = 123456L;

    // Hash counter (we'll simply translate the counter value into hex).
    private final AtomicLong hashCounter = new AtomicLong();

    private PluginHashDAO pluginHashDAO;
    private SmartCache<PluginHash> pluginHashCache;

    // Stores the hashes that can't be deleted or overwritten.
    private Set<String> permanentHashes;

    public PluginHashRepository() throws PluginHashException
    {
	try
	{
	    int cacheMemoryLimit = PropertyLookup.getConfigurationHashCacheMemoryLimit();
	    this.pluginHashCache = new SmartCache<PluginHash>(cacheMemoryLimit);
	    this.pluginHashDAO = ApplicationController.getInstance().getDAOFactory().getConfigurationHashDAO();
	    this.permanentHashes = new HashSet<String>();

	    syncHashCounterValue();
	    loadPermanentHashes();
	}
	catch(DAOException e)
	{
	    throw new PluginHashException("Error building the plugin hash repository", e);
	}
    }

    /**
     * Syncs the value of the hash counter with the DB.
     */

    private void syncHashCounterValue() throws DAOException
    {
	long counterValue = INITIAL_HASH_COUNT;
	PluginHash lastHashAdded = this.pluginHashDAO.findLastPluginHashAdded();

	if(lastHashAdded != null)
	    counterValue = new BigInteger(lastHashAdded.getHashKey(), 16).longValue();

	this.hashCounter.set(counterValue);
    }

    /**
     * Loads all the configuration hashes marked as permanents (won't be removed
     * from the cache or the DB).
     */

    private void loadPermanentHashes() throws DAOException
    {
	List<PluginHash> hashes = pluginHashDAO.findPermamentPluginHashes();

	for(PluginHash currentHash : hashes)
	{
	    this.permanentHashes.add(currentHash.getHashKey());
	    this.pluginHashCache.addInstanceToCache(currentHash.getHashKey(), currentHash, currentHash.isPermanent());
	}
    }

    /**
     * Registers a new plugin configuration in the repository returning the hash
     * generated for the given configuration. Notice that any existing
     * configuration with the same hash will be overwritten unless it is
     * permanent.
     * 
     * @param configurationObject A string representation of the configuration
     *        object (JSON).
     * @return A string representing the hash of the given configuration.
     */

    public String registerConfigurationHash(String configurationObject) throws PluginHashException
    {
	try
	{
	    // We start again after reaching the max value for the counter.
	    if(this.hashCounter.get() == Long.MAX_VALUE)
	    {
		this.hashCounter.set(INITIAL_HASH_COUNT);
		this.pluginHashDAO.deleteNonPermanentPluginHashes();
	    }

	    String hashKey = Long.toHexString(this.hashCounter.getAndIncrement());

	    if(!this.permanentHashes.contains(hashKey))
	    {
		PluginHash pluginHash = new PluginHash(hashKey, configurationObject, false);
		this.pluginHashCache.addInstanceToCache(hashKey, pluginHash, false);

		if(this.pluginHashDAO.findPluginHash(hashKey) == null)
		    this.pluginHashDAO.insertPluginHash(hashKey, configurationObject, false);
		else
		{
		    PluginHash currentHash = new PluginHash(hashKey, configurationObject, false);
		    this.pluginHashDAO.updatePluginHash(currentHash);
		}
	    }

	    return hashKey;
	}
	catch(DAOException e)
	{
	    throw new PluginHashException("Error registering plugin hash", e);
	}
    }

    /**
     * Gets the plugin configuration associated with the given hash or null if
     * the hash is unknown.
     * 
     * @param hashKey The hash that identifies the plugin configuration.
     * @return The plugin configuration associated with the hash or null if the
     *         hash wasn't present.
     */

    public PluginHash findPluginHash(String hashKey) throws PluginHashException
    {
	try
	{
	    PluginHash pluginHash = this.pluginHashCache.findInstance(hashKey);

	    if(pluginHash == null)
		return this.pluginHashDAO.findPluginHash(hashKey);
	    else
		return pluginHash;
	}
	catch(DAOException e)
	{
	    throw new PluginHashException("Error retrieving a plugin hash", e);
	}
    }

    /**
     * Makes the configuration associated with the given key permanent (won't be
     * deleted).
     * 
     * @param hashKey The key associated with the plugin configuration that will
     *        be made permanent.
     * 
     * @throws PluginHashException If the hash key is not registered.
     */

    public void markHashAsPermanent(String hashKey) throws PluginHashException
    {
	try
	{
	    // Check the hash key exists in our system.
	    PluginHash pluginHash = this.pluginHashCache.findInstance(hashKey);
	    if(pluginHash == null)
		pluginHash = this.pluginHashDAO.findPluginHash(hashKey);

	    if(pluginHash == null)
		throw new PluginHashException("Hash key: " + hashKey + " wasn't registered");

	    pluginHash.markAsPermanent();
	    this.permanentHashes.add(hashKey);
	    this.pluginHashDAO.makePluginHashPermanent(hashKey);

	    if(pluginHashCache.findInstance(hashKey) == null)
		pluginHashCache.addInstanceToCache(hashKey, pluginHash, true);

	    backupPermanentHash(pluginHash);
	}
	catch(DAOException e)
	{
	    throw new PluginHashException("Error accessing DB", e);
	}
    }

    /**
     * Backs up the given configuration hash so it can be retrieved in case of
     * disaster.
     * 
     * @param pluginHash The plugin configuration that will be stored in a
     *        "safe" place.
     * @throws IOException
     * @throws PluginHashException
     */

    public void backupPermanentHash(PluginHash pluginHash) throws PluginHashException
    {
	try
	{
	    String filename = PropertyLookup.getConfigurationHashBackupFilePath();
	    BufferedWriter backupWriter = new BufferedWriter(new FileWriter(new File(filename)));
	    backupWriter.append(pluginHash.getHashKey() + "\t" + pluginHash.getPluginConfiguration() + "\n");
	    backupWriter.close();
	}
	catch(IOException e)
	{
	    throw new PluginHashException("Error backing up a plugin hash", e);
	}
    }

    /**
     * Inner class that associates a plugin configuration object (JSON) with the
     * hash to be used in the URL.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 11 May 2011
     */

    public static class PluginHash implements SizeMeasureable
    {
	private String hashKey;
	private String pluginConfiguration;
	private boolean isPermanent;

	public PluginHash(String hashKey, String configuration, boolean isPermament)
	{
	    this.hashKey = hashKey;
	    this.pluginConfiguration = configuration;
	    this.isPermanent = isPermament;
	}

	public String getHashKey()
	{
	    return this.hashKey;
	}

	public String getPluginConfiguration()
	{
	    return this.pluginConfiguration;
	}

	/**
	 * Marks the configuration as permanent, meaning that won't be removed
	 * from the DB and that will be stored in a back-up file.
	 */

	private void markAsPermanent()
	{
	    this.isPermanent = true;
	}

	public boolean isPermanent()
	{
	    return this.isPermanent;
	}

	@Override
	public long getSizeInBytes()
	{
	    return hashKey.length() + pluginConfiguration.length();
	}
    }
}
