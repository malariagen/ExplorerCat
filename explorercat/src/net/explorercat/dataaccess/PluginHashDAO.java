package net.explorercat.dataaccess;

import java.util.List;

import net.explorercat.plugins.PluginHashRepository.PluginHash;

/**
 * DAO in charge of accessing URL hashes that are associated with the concrete
 * configuration of a plugin.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date May 2011
 */

public interface PluginHashDAO
{
    /**
     * Retrieves the plugin hash configuration for the given URL hash
     * 
     * @param hashKey URL hash that identifies the plugin configuration.
     * @return The hash/configuration object or null if not found.
     */

    public PluginHash findPluginHash(String hashKey) throws DAOException;

    /**
     * Registers a new hash configuration object.
     * 
     * @param hashKey URL hash that identifies the plugin configuration.
     * @param configuration A string representing a JSON configuration object.
     * @param isPermanent True if the hash must be always conserved.
     */

    public void insertPluginHash(String hashKey, String configuration, boolean isPermanent) throws DAOException;

    /**
     * Updates the values of the given configuration hash.
     * 
     * @param pluginHash The updated version of the configuration hash.
     */

    public void updatePluginHash(PluginHash pluginHash) throws DAOException;

    /**
     * Updates the plugin configuration for the given hash, making it permanent.
     * 
     * @param hashKey The hash that identifies the configuration.
     */

    public void makePluginHashPermanent(String hashKey) throws DAOException;

    /**
     * Retrieves all the permanent configuration hashes from the DB.
     * 
     * @return A list containing all the permanent plugin configuration hashes.
     */

    public List<PluginHash> findPermamentPluginHashes() throws DAOException;

    /**
     * Retrieves the last configuration hash inserted.
     * 
     * @return The last configuration hash inserted or null if there are no
     *         hashes.
     */

    public PluginHash findLastPluginHashAdded() throws DAOException;

    /**
     * Deletes any hash configuration not marked as permanent from the database.
     */

    public void deleteNonPermanentPluginHashes() throws DAOException;
}
