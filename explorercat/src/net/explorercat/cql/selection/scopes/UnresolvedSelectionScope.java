package net.explorercat.cql.selection.scopes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.explorercat.cql.interpreters.CQLScriptContext;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.SelectionProxy;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;

/**
 * Implements a scope for unresolved query selections. In this scope selections
 * are encapsulated in proxies and could be unresolved. To resolve a selection
 * the proxy will try to resolve all the dependencies first. The process
 * finishes when all dependencies are resolved or a dependency cycle is found.
 * 
 * This scope should be used to store the selections of a CQL script while this
 * is being interpreted.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Aug 2010
 */

public class UnresolvedSelectionScope extends SelectionScopeBase implements SelectionScope
{
    // Logging
    private static Log log = LogFactory.getLog(UnresolvedSelectionScope.class);

    // Map for selections.
    private Map<String, SelectionProxy> selectionMap;

    // Catalog that contains the selection in the scope.
    private int catalogId;

    /**
     * Creates an empty scope.
     * 
     * @param scopeName The name of the scope (doesn't have to be unique).
     * @param catalogId Identifier of the associated catalog.
     */

    public UnresolvedSelectionScope(String scopeName, int catalogId)
    {
	super(scopeName);
	this.selectionMap = new HashMap<String, SelectionProxy>();
	this.catalogId = catalogId;
    }

    /**
     * Creates an scope from a given map of proxy selections.
     * 
     * @param scopeName The name of the scope (doesn't have to be unique).
     * @param catalogId Identifier of the associated catalog.
     * @param interpretedScript Contains all the interpreted objects of the CQL
     *        script.
     */

    public UnresolvedSelectionScope(String scopeName, int catalogId, CQLScriptContext interpretedScript)
    {
	super(scopeName);
	this.catalogId = catalogId;
	this.selectionMap = new HashMap<String, SelectionProxy>();

	// Extract all the proxy selections from the interpreted script.
	List<SelectionProxy> proxySelections = interpretedScript.getScriptSelections();
	for(SelectionProxy proxy : proxySelections)
	    selectionMap.put(proxy.getSelectionLabel(), proxy);
    }

    /**
     * Adds a new selection encapsulated in a proxy. Notice that if a selection
     * with the same label is already in the scope it will be overwritten.
     * 
     * @param selection The selection (proxy) to be added to the scope.
     * @throws SelectionException If there is already a selection in the scope
     *         with the same label and overwriting is not allowed.
     */

    public void addSelectionToScope(SelectionProxy selection) throws SelectionException
    {
	if(selectionMap.containsKey(selection.getSelectionLabel()))
	{
	    if(!selectionOverwritingAllowed)
		throw new SelectionException("Selection overwritten in scope (" + scopeName + "): "
					     + selection.getSelectionLabel());
	    if(log.isDebugEnabled())
		log.debug("Selection overwritten in scope (" + scopeName + "): " + selection.getSelectionLabel());
	}

	selectionMap.put(selection.getSelectionLabel(), selection);
    }

    @Override
    public Selection resolveSelection(int catalogId, String label, SelectionResolver resolver)
	throws SelectionResolutionException
    {
	// Check the catalog id is coherent.
	if(this.catalogId != catalogId)
	    throw new SelectionResolutionException("Mismatch for catalog identifier, this scope is for "
						  + this.catalogId + ", looking for " + catalogId);

	// Find the proxy that encapsulates the selection in the scope.
	SelectionProxy proxy = selectionMap.get(label);

	// We need to check if it has been defined in the scope. 
	// If it has NOT been defined then, it can't be resolved in this scope.
	// (It can happens that the proxy has been directly updated with a resolved selection
	// so we also have to check if it is already resolved.)
	if(proxy != null && (proxy.hasQueryDefinition() || proxy.isSelectionResolved()))
	{
	    if(log.isDebugEnabled())
		log.debug("Scope HIT: " + scopeName + " for label: " + label);

	    // Resolve the selection (including any dependency) using the resolver.	    
	    if(!proxy.isSelectionResolved())
		proxy.resolveSelection(resolver);

	    // Return only the selection (not the proxy container).
	    return proxy.getResolvedSelection();
	}
	else
	{
	    if(log.isDebugEnabled())
		log.debug("Scope MISS: " + scopeName + " for label: " + label);

	    return null;
	}
    }

    @Override
    public void updateScopeWithResolvedSelection(Selection resolvedSelection)
    {
	SelectionProxy selectionToUpdate = selectionMap.get(resolvedSelection.getSelectionLabel());

	if(selectionToUpdate != null && !selectionToUpdate.isSelectionResolved())
	    selectionToUpdate.setResolvedSelection(resolvedSelection);
    }

    @Override
    public String toString()
    {
	return selectionMap.keySet().toString();
    }
}
