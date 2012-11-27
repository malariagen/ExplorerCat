package net.explorercat.cql.selection.scopes;

import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;

/**
 * Interface that represents a set of selections that are contained in the same
 * scope. Different scopes (local, cache, DAOs, etc.) will be searched by a
 * resolver in order to resolve a selection. Different implementations will
 * provide scopes with fully resolved selections or selections that will be
 * calculated on the fly.
 * 
 * Notice that the cache for pre-calculated selections and the CatalogDAO class
 * will implement this interface.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Aug 2010
 */

public interface SelectionScope
{
    /**
     * Gets the name of the scope.
     * 
     * @return An informative string identifying the scope.
     */

    public String getScopeContent();

    /**
     * Resolves a selection with the given name in the current scope.
     * 
     * @param catalogId The identifier of the catalog that contains the entities
     *        of the selection.
     * @param selectionLabel The label associated with the selection that will
     *        be resolved.
     * @param resolver The selection resolver that will be used to resolve any
     *        dependency of the selection.
     * @return The resolved selection or null if the selection was not found in
     *         the scope.
     */

    public Selection resolveSelection(int catalogId, String selectionLabel, SelectionResolver resolver)
	throws SelectionResolutionException;

    /**
     * Updates the scope with the given resolved selection. If the matching
     * selection in the scope has been already resolved this method has no
     * effect.
     * 
     * @param resolvedSelection A resolved selection that will update any
     *        unresolved selection in the scope with the same label.
     */

    public void updateScopeWithResolvedSelection(Selection resolvedSelection);
}
