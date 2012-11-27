package net.explorercat.cql.selection.resolvers;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.scopes.SelectionScope;
import net.explorercat.cql.selection.scopes.SelectionScopeFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Resolves selections by name following a hierarchy of scopes. If the selection
 * is not found in any of the scopes then null is returned (so the client code
 * should check the success of the operation). Resolving a selection will imply
 * executing the associated query so this process can take a while to complete.
 * 
 * Hierarchy of scopes:
 * 
 * Local scope: The first scope should contain the selections from the script
 * that is being interpreted. These selections will be not real selections but
 * proxies. The resolver will resolve the selection encapsulated in a proxy if
 * necessary. The proxy will try to resolve its dependencies using the same
 * resolver, starting the process again. It finishes when all dependencies have
 * been resolved or a cycle is found. This scope has to be provided by the
 * client.
 * 
 * Pre-calculated scope: This scope contains all the selections that have been
 * pre-calculated and cached beforehand. Any selection in this scope is fully
 * resolved and contains no dependencies so the resolver just tries to retrieve
 * the selection from the cache by name. This scope is automatically retrieved
 * by the class.
 * 
 * DAOs scope: The last scope contains the entity DAOs of the system that,
 * because of their dual nature, can act like selections. If the selection is
 * not found in this context then null is returned. This scope is automatically
 * retrieved by the class.
 * 
 * (Note that there are other caches in the system standing out of this
 * hierarchy like the global cache for queries or the result pagination cache).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Aug 2010
 */

public class SelectionResolver
{
    // Logging
    private static final Log log = LogFactory.getLog(SelectionResolver.class);

    // Identifier of the catalog that contains all the selections.
    private int catalogId;

    // First level scope, used for the script being interpreted.
    // Has to be provided by the user.
    private SelectionScope scriptScope;

    // Second level scope, used for pre-calculated selections.
    // Automatically obtained using the scope factory.
    private SelectionScope precalculatedSelectionsScope;

    // Third level scope, used for DAOs.
    // Automatically obtained using the scope factory.
    private SelectionScope DAOSelectionsScope;

    /**
     * Creates a new resolver that will use the given scope as the first level
     * scope.
     * 
     * @param catalogId Identifier of the catalog that contains all the
     *        selections.
     * @param scriptScope The scope that contains all the selections of the
     *        script being interpreted. If there isn't any script being
     *        interpreted
     * @throws SelectionException If there is any error accessing to the cache
     *         scopes.
     */

    public SelectionResolver(int catalogId, SelectionScope scriptScope) throws SelectionException
    {
	this.scriptScope = scriptScope;
	this.catalogId = catalogId;

	ApplicationController globalController = ApplicationController.getInstance();
	SelectionScopeFactory factory = globalController.getSelectionScopeFactory();
	
	// Automatically loaded scopes.
	this.precalculatedSelectionsScope = factory.getPrecalculatedSelectionsScope();
	this.DAOSelectionsScope = factory.getDAOselectionsScope();
    }

    /**
     * Resolves a selection checking the script scope, the pre-calculated
     * selections scope and the DAOs scope (in that order). If the selection has
     * not been resolved it tries to resolve it and all the related
     * dependencies. If the selection is not found in any scope then null is
     * returned.
     * 
     * @param selectionLabel The label of the selection to be resolved.
     * @return The resolved selection or null if not found.
     * @throws SelectionResolutionException If there is an error trying to
     *         resolve the exception or its dependencies.
     */

    public Selection resolveSelection(String selectionLabel) throws SelectionResolutionException
    {
	if(log.isDebugEnabled())
	    log.debug("Resolving selection [" + selectionLabel + "]");

	Selection selection = null;

	if(log.isDebugEnabled())
	    log.debug("Checking SCRIPT scope");

	// First check the script scope.
	if(scriptScope != null)
	    selection = scriptScope.resolveSelection(catalogId, selectionLabel, this);

	// If not found check the pre-calculated selections scope. 
	if(selection == null)
	{
	    if(log.isDebugEnabled())
		log.debug("Checking PRECALCULATED scope");

	    selection = precalculatedSelectionsScope.resolveSelection(catalogId, selectionLabel, this);

	    // If not found check the DAOs scope.
	    if(selection == null)
	    {
		if(log.isDebugEnabled())
		    log.debug("Checking DAO scope");

		selection = DAOSelectionsScope.resolveSelection(catalogId, selectionLabel, this);

		if(log.isDebugEnabled() && selection != null)
		    log.debug("DataAccessObject HIT: " + DAOSelectionsScope.getScopeContent());
	    }
	}

	return selection;
    }

    /**
     * Returns the id of the catalog in which context the selections will be
     * resolved. Note that with context we mean just the container for the
     * entities of the selections (i.e. the catalog contains all the entities of
     * the selections).
     * 
     * @return The identifier of the catalog that contains the selections to be
     *         resolved.
     */

    public int getContextCatalogId()
    {
	return catalogId;
    }

    /**
     * Updates the scopes with the given resolved selection. If the matching
     * selection in a scope has been already resolved his method has no effect.
     * 
     * Note right now this method only updates the script scope since all the
     * other scopes in the chain are read-only.
     * 
     * @param resolvedSelection A resolved selection that will update any
     *        unresolved selection in a scope with the same label
     */

    public void updateScopesWithResolvedSelection(Selection resolvedSelection)
    {
	if(log.isDebugEnabled())
	    log.debug("Updating scopes with RESOLVED selection [" + resolvedSelection.getSelectionLabel() + "]");

	scriptScope.updateScopeWithResolvedSelection(resolvedSelection);
    }
}
