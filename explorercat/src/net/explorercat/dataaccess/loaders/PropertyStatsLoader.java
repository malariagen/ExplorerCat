package net.explorercat.dataaccess.loaders;

import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.PropertyStatsLookup;
import net.explorercat.dataaccess.DAOException;

/**
 * Simple interface to be implemented by any helper class able to load the stats
 * values for the properties contained in a dictionary (for instance accessing a
 * MySQL DB). Instances of concrete implementations have to be configured
 * (entity type and catalog) during building time.
 * 
 * You can consider a loader as a very simple DAO.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public interface PropertyStatsLoader
{
    /**
     * Loads the stats of all the properties contained in the dictionary.
     * 
     * @param propertyDictionary The dictionary that contains the definition of
     *        all the properties.
     * @return The property stats lookup for the configured catalog/type of
     *         entity.
     * @throws DAOException If there is any problem accessing the storage layer
     */

    public PropertyStatsLookup loadPropertyStats(PropertyDictionary propertyDictionary) throws DAOException;
}
