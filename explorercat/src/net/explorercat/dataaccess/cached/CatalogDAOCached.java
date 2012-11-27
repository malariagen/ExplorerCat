package net.explorercat.dataaccess.cached;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the catalog DAO that relies on a memory cache. All catalogs
 * will be loaded into memory at startup time.
 * 
 * A DAO exception is raised if there is any problem loading the data into the
 * cache.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 25 Aug 2010
 */

public abstract class CatalogDAOCached implements CatalogDAO
{
    // Logging
    private static Log log = LogFactory.getLog(CatalogDAOCached.class);

    // Cached list of catalogs.
    protected Map<Integer, Catalog> catalogs;

    /**
     * Creates a new catalog DAO that whose catalogs are cached in memory.
     * 
     * @throws DAOException If there is any problem initializing the cache.
     */

    public CatalogDAOCached() throws DAOException
    {
	if(log.isDebugEnabled())
	    log.debug("Creating cached catalog DAO");

	// DAO accessed by multiple threads (although right now is read-only).
	catalogs = new ConcurrentHashMap<Integer, Catalog>();

	// Calls the hook method in charge of initialise the cache.
	// This functionality has to be provided by a subclass.
	initializeCache();
    }

    @Override
    public List<Catalog> findAllCatalogs()
    {
	return new ArrayList<Catalog>(catalogs.values());
    }

    @Override
    public Catalog findCatalog(int catalogId)
    {
	return catalogs.get(catalogId);
    }

    @Override
    public Iterator<Catalog> getCatalogIterator()
    {
	return catalogs.values().iterator();
    }

    @Override
    public String getScopeContent()
    {
	return "CatalogDAOScope";
    }

    @Override
    public Selection resolveSelection(int catalogId, String selectionLabel, SelectionResolver resolver)
	throws SelectionResolutionException
    {	
	Catalog catalog = catalogs.get(catalogId);
	if(catalog == null)
	    return null;

	// The entity DAO that will resolve the selection has the same name than the label.			
	Selection entityDAO = catalog.getEntityDAO(selectionLabel);

	if(entityDAO == null)
	    return null;
	else
	    return entityDAO;
    }

    @Override
    public void updateScopeWithResolvedSelection(Selection resolvedSelection)
    {
	throw new IllegalAccessError("This scope is read-only, can't be updated with resolved selections");
    }

    /**
     * Hook method in charge of initialising the cache. This method has to be
     * implemented by subclasses bounded to a specific implementation (i.e.
     * MySQL).
     * 
     * @throws DAOException If there is a problem initializing the cache.
     */

    abstract protected void initializeCache() throws DAOException;
}
