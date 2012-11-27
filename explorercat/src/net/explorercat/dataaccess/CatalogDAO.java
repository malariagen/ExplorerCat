package net.explorercat.dataaccess;

import java.util.Iterator;
import java.util.List;

import net.explorercat.cql.selection.scopes.SelectionScope;
import net.explorercat.data.Catalog;

/**
 * DAO in charge of providing catalog objects. A catalog can we queried to
 * obtain entity DAOs that will provide data entities of a specific type.
 * 
 * Extends the SelectionScope interface so it will look for a selection given a
 * catalog identifier and a label (checking the contained selections (entity
 * DAOs) of the catalog).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public interface CatalogDAO extends SelectionScope
{
    /**
     * Retrieves the catalog with the given id (unique identifier).
     * 
     * @param catalogId Unique identifier for the catalog.
     * @return The catalog or null if no present.
     */

    public Catalog findCatalog(int catalogId);

    /**
     * Retrieves all the catalogs.
     * 
     * @return A list containing all the catalogs registered in the system.
     */

    public List<Catalog> findAllCatalogs();

    /**
     * Gets an iterator to iterate through all the catalogs.
     * 
     * @return An iterator for the catalogs.
     */

    public Iterator<Catalog> getCatalogIterator();
}
