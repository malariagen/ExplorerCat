package net.explorercat.dataaccess.loaders;

import net.explorercat.data.PropertyDictionary;
import net.explorercat.dataaccess.DAOException;

/**
 * Simple interface to be implemented by any helper class able to load a
 * property dictionary from storage (for instance accessing a MySQL DB).
 * Instances of concrete implementations have to be configured (entity type and
 * catalog) during building time.
 * 
 * You can consider a loader as a very simple DAO.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public interface PropertyDictionaryLoader
{
    /**
     * Loads a dictionary of properties for a entity type.
     * 
     * @return A property dictionary for the configured entity type.
     * @throws DAOException If there is any problem accessing the storage layer
     */

    public PropertyDictionary loadPropertyTypesDictionary() throws DAOException;
}
