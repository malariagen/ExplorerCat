package net.explorercat.dataaccess;

import net.explorercat.data.PublicAPIKey;

/**
 * DAO in charge of accessing public API access keys associated with a specific
 * host domain/IP. External developers needs to provide a valid key in order to
 * hit the public CQL API.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2011
 */

public interface PublicAPIKeyDAO
{
    /**
     * Retrieves the API key instance associated with the given hash.
     * 
     * @param keyHash Hash that uniquely identifies the key.
     * @return The key instance or null if not found.
     */

    public PublicAPIKey findKey(String keyHash) throws DAOException;

    /**
     * Creates a new API key for the given host and email address. The key will
     * be inserted into the DB and the instance will be returned by the method.
     * 
     * @param host The domain/IP from where the key will be used.
     * @param email The email of the person requesting the key.
     * @return The instance that represents the key generated.
     */

    public PublicAPIKey generateAndInsertKey(String host, String email) throws DAOException;

    /**
     * Disables the key associated with the given hash.
     */

    public void disableKey(String keyHash) throws DAOException;
}
