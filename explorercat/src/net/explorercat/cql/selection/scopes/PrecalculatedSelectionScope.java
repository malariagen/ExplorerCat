package net.explorercat.cql.selection.scopes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.DAOFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Stores precalculated selections that can be accessed by label and catalog id.
 * Note that the name supplied with the selection must not be the same as the
 * type of entities of the catalog (it would shadow it). An exception is raised
 * if the name provided is not valid
 * 
 * Implements the SelectionScope interface so it will act also as an scope where
 * we can look for selections.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 16 Aug 2010
 */

public class PrecalculatedSelectionScope extends SelectionScopeBase implements SelectionScope
{
    // Logging
    private static final Log log = LogFactory.getLog(PrecalculatedSelectionScope.class);

    // Map for selections, catalog ids -> (selection label, selection).
    private Map<Integer, Map<String, Selection>> selectionCache;

    // Forbidden names by catalog (names of their entities).
    private Map<Integer, Set<String>> forbiddenSelectionNames;

    /**
     * Initializes the cache. This method will load automatically all the
     * forbidden names for a registered selection.
     * 
     * @throws SelectionException If there is any error creating the cache.
     */

    public PrecalculatedSelectionScope() throws SelectionException
    {
	// Constructor for selection scope (name of the scope).
	super("GlobalPrecalculatedCacheScope");

	try
	{
	    if(log.isDebugEnabled())
		log.debug("Creating precalculated selection cache");

	    // We only need the concurrent map for the pool of selections
	    // TODO Check the performance with the concurrent map.
	    selectionCache = new ConcurrentHashMap<Integer, Map<String, Selection>>();
	    forbiddenSelectionNames = new HashMap<Integer, Set<String>>();

	    // Register the entity types for each catalog.
	    DAOFactory factory = ApplicationController.getInstance().getDAOFactory();
	    CatalogDAO catalogDAO = factory.getCatalogDAO();
	    List<Catalog> catalogs = catalogDAO.findAllCatalogs();

	    for(Catalog currentCatalog : catalogs)
		forbiddenSelectionNames.put(currentCatalog.getId(),
					    new HashSet<String>(currentCatalog.getEntityTypes()));
	}
	catch(DAOException e)
	{
	    String errorMessage = "Error creating the precalculated cache for selections ";
	    log.error(errorMessage);
	    throw new SelectionException(errorMessage, e);
	}
    }

    /**
     * Registers a selection in the pool with the label returned by
     * getSelectionLabel. If there is a selection already registered with the
     * same label for the same catalog, an exception is raised.
     * 
     * @param selection The selection that will be registered.
     * @throws SelectionException If the associated catalog does not exist or
     *         the selection label conflicts with an entity type.
     */

    public void registerSelection(Selection selection) throws SelectionException
    {
	int catalogId = selection.getEntityCatalog().getId();
	String label = selection.getSelectionLabel();

	if(log.isDebugEnabled())
	    log.debug("PrecalculatedCache REGISTERING: " + label + "[" + catalogId + "]");

	// Check the catalog has been previously registered with a set of entity names.
	if(!forbiddenSelectionNames.containsKey(catalogId))
	    throw new SelectionException("Error registering a precalculated selection, unknown catalog: " + catalogId);

	// Check for label collision.
	else if(selectionCache.get(catalogId) != null && selectionCache.get(catalogId).get(label) != null)
	    throw new SelectionException("Error registering a precalculated selection, label collision : " + label);

	// Check the label is not colliding with entity names.
	if(!forbiddenSelectionNames.get(catalogId).contains(label))
	{
	    if(!selectionCache.containsKey(catalogId))
		selectionCache.put(catalogId, new ConcurrentHashMap<String, Selection>());

	    selectionCache.get(catalogId).put(label, selection);
	}
	else
	{
	    String errorMessage = "Error registering a selection in cache, label collides with entity type: " + label;
	    log.error(errorMessage);
	    throw new SelectionException(errorMessage);
	}
    }

    /**
     * Retrieves a precalculated selection for the given catalog and label.
     * 
     * @param catalogId The catalog associated with the selection.
     * @param selectionLabel The label of the selection.
     * @return The precalculated selection for that catalog and label or null if
     *         the selection is not registered.
     */

    public Selection findSelection(int catalogId, String selectionLabel)
    {
	Map<String, Selection> selectionMap = selectionCache.get(catalogId);

	if(selectionMap != null)
	{
	    Selection selection = selectionMap.get(selectionLabel);

	    if(log.isDebugEnabled())
	    {
		if(selection != null)
		    log.debug("PrecalculatedCache HIT: " + selectionLabel + "[" + catalogId + "]");
		else
		    log.debug("PrecalculatedCache MISS: " + selectionLabel + "[" + catalogId + "]");
	    }

	    return selection;
	}
	else
	{
	    if(log.isDebugEnabled())
		log.debug("PrecalculatedCache MISS: " + selectionLabel + "[" + catalogId + "]");

	    return null;
	}
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SelectionScope interface.
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Selection resolveSelection(int catalogId, String selectionLabel, SelectionResolver resolver)
	throws SelectionResolutionException
    {
	return findSelection(catalogId, selectionLabel);
    }

    @Override
    public void updateScopeWithResolvedSelection(Selection resolvedSelection)
    {
	throw new IllegalAccessError("This scope is read-only, can't be updated with resolved selections");
    }
}
