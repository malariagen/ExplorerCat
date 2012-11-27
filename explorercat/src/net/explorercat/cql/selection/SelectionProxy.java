package net.explorercat.cql.selection;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.query.QueryBuilder;
import net.explorercat.cql.selection.query.cache.QueryCacheProvider;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.cache.SmartCache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A proxy class that will be used to represent unresolved selections
 * (selections whose query has not been executed yet). This will be useful when
 * parsing CQL scripts since there could be references to selections that have
 * not been resolved or even defined.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Aug 2010
 */

public class SelectionProxy implements Selection
{
    // Logging
    private static final Log log = LogFactory.getLog(SelectionProxy.class);

    // Label for the selection to be resolved.
    private String selectionLabel;

    // Query builder in charge of building the query
    // that will resolve the selection.
    private QueryBuilder queryBuilder;

    // Selection to be resolved.
    private Selection resolvedSelection;

    // Flag to mark if we are resolving a selection.
    // Used to identify dependency cycles.
    private boolean selectionResolutionInProgress;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Proxy methods (out of the interface)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Builds a selection proxy for the given selection name.
     * 
     * @param selectionName The name of the selection the proxy is representing.
     */

    public SelectionProxy(String selectionName)
    {
	this.selectionLabel = selectionName;
	this.resolvedSelection = null;
	this.queryBuilder = null;
	this.selectionResolutionInProgress = false;
    }

    /**
     * Sets the query builder that will generate the query to resolve the
     * selection represented by the proxy.
     * 
     * @param queryBuilder Builder that will be used to generate the query and
     *        the resulting selection.
     * @throws SelectionException If the label for the proxy selection is
     *         different from the label of the query builder (i.e. they
     *         represent different selections).
     */

    public void setSelectionQueryBuilder(QueryBuilder queryBuilder) throws SelectionException
    {
	// Check coherence of labels.
	if(!queryBuilder.getQueryLabel().equals(selectionLabel))
	    throw new SelectionException("Incoherence in selection proxy. Representing: " + selectionLabel
					 + " but received: " + queryBuilder.getQueryLabel());

	this.queryBuilder = queryBuilder;
    }

    /**
     * Checks if the proxy has been assigned a query definition or if its
     * selection has been resolved. Selections without a query definition can
     * NOT be resolved in the script scope. They have been referenced by another
     * selection of the scope but they have not been defined there.
     * 
     * @return True if the proxy has been assigned a query builder or the
     *         selection has been resolved, false otherwise.
     */

    public boolean hasQueryDefinition()
    {
	return queryBuilder != null && !isSelectionResolved();
    }

    /**
     * Resolves the proxy selection using the one given as a parameter.
     * 
     * @param resolvedSelection The selection that will be used as a resolved
     *        selection by the proxy. Notice that labels must match.
     */

    public void setResolvedSelection(Selection resolvedSelection)
    {
	if(!resolvedSelection.getSelectionLabel().equals(this.selectionLabel))
	    throw new IllegalArgumentException("Mismatch between the proxy and resolved selection label: "
					       + this.selectionLabel + " vs " + resolvedSelection.getSelectionLabel());

	this.resolvedSelection = resolvedSelection;
    }

    /**
     * Resolves the selection represented by the proxy, resolving any dependency
     * before.
     * 
     * @param resolver A selection resolver used to find selections already
     *        resolved.
     * @throws SelectionResolutionException If there is a problem resolving the
     *         exception like a cycle in the dependency chain.
     */

    public void resolveSelection(SelectionResolver resolver) throws SelectionResolutionException
    {
	if(log.isDebugEnabled())
	    log.debug("Resolving proxy selection [" + this.selectionLabel + "]");

	// Check if we are currently resolving a selection.
	// In that case, there was a cycle in the chain of selection dependencies.
	if(selectionResolutionInProgress)
	    throw new SelectionResolutionException("Error resolving selection: " + selectionLabel
						   + ", cyclic dependency");
	else
	    selectionResolutionInProgress = true;

	// Build the query selection. This will resolve the input selection used by the query.
	Query query = queryBuilder.buildSelectionQuery(resolver);

	try
	{
	    // Check the query cache (SmartCache, do not confuse with the precalculated cache) 
	    // for the context catalog.	    	    
	    ApplicationController globalController = ApplicationController.getInstance();
	    QueryCacheProvider factory = globalController.getQueryCacheProvider();

	    int contextCatalogId = resolver.getContextCatalogId();
	    SmartCache<Selection> queryCache = factory.getQueryCacheForCatalog(contextCatalogId);

	    // There is a cache for the catalog.
	    if(queryCache != null)
	    {
		String queryKey = query.getStringKey();
		Selection cachedSelection = queryCache.findInstance(queryKey);

		// Either we found the selection in the cache or we register it.
		if(cachedSelection != null)
		    resolvedSelection = cachedSelection.getSelectionCopyWithSharedEntities(query.getLabel());
		else
		{
		    resolvedSelection = query.performSelection();
		    queryCache.addInstanceToCache(queryKey, resolvedSelection, false);
		}
	    }
	    else
		resolvedSelection = query.performSelection();
	}
	catch(SelectionException e)
	{
	    throw new SelectionResolutionException("Error trying to resolve a selection", e);
	}

	// Restore the flag
	selectionResolutionInProgress = false;

	if(log.isDebugEnabled())
	    log.debug("[" + selectionLabel + "] resolved: " + resolvedSelection.getSelectionSize() + " entities.");
    }

    /**
     * Checks if the selection protected by the solver has been resolved.
     * 
     * @return True if the protected selection has been resolved and the proxy
     *         can be used as a normal selection, false otherwise.
     */

    public boolean isSelectionResolved()
    {
	return resolvedSelection != null;
    }

    /**
     * Gets the resolved selection encapsulated in the proxy.
     * 
     * @return The resolved selection protected by the proxy or null if the
     *         selection has not been resolved yet.
     */

    public Selection getResolvedSelection()
    {
	return resolvedSelection;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Selection interface (delegates into the resolved selection or throws if a runtime exception if the selection
    // has not been resolved).
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<QueryableDataEntity> getEntities(int numEntities, int offset) throws SelectionException
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntities(numEntities, offset);
    }

    @Override
    public QueryableDataEntity getEntityById(int entityId)
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityById(entityId);
    }

    @Override
    public Catalog getEntityCatalog()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityCatalog();
    }

    @Override
    public Set<Integer> getEntityIds() throws SelectionException
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityIds();
    }

    @Override
    public String getEntityPropertyDescription(String propertyName)
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityPropertyDescription(propertyName);
    }

    @Override
    public List<String> getEntityPropertyDescriptions()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityPropertyDescriptions();
    }

    @Override
    public List<String> getEntityPropertyNames()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityPropertyNames();
    }

    @Override
    public DataType getEntityPropertyType(String propertyName)
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityPropertyType(propertyName);
    }

    @Override
    public List<DataType> getEntityPropertyTypes()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityPropertyTypes();
    }

    @Override
    public String getEntityType()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getEntityType();
    }

    @Override
    public PropertyDictionary getPropertyDictionary()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getPropertyDictionary();
    }

    @Override
    public String getSelectionLabel()
    {
	if(resolvedSelection == null)
	    return this.selectionLabel;
	else
	    return resolvedSelection.getSelectionLabel();
    }

    @Override
    public int getSelectionSize()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getSelectionSize();
    }

    @Override
    public String getSortingProperty()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getSortingProperty();
    }

    @Override
    public float getStatsForProperty(String propertyName, StatsType statsType) throws StatsCalculationException
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getStatsForProperty(propertyName, statsType);
    }

    @Override
    public float getStatsForVariable(String variableName, Expression variableExpression, StatsType statsType)
	throws StatsCalculationException
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getStatsForVariable(variableName, variableExpression, statsType);
    }

    @Override
    public boolean hasEntityProperty(String propertyName)
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.hasEntityProperty(propertyName);
    }

    @Override
    public boolean isSortedByPropertyValue()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.isSortedByPropertyValue();
    }

    @Override
    public boolean isSortedInAscendingOrder()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.isSortedInAscendingOrder();
    }

    @Override
    public Iterator<QueryableDataEntity> iterator() throws SelectionException
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.iterator();
    }

    @Override
    public Selection selectEntities(Query query) throws SelectionException
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.selectEntities(query);
    }

    @Override
    public void sortByPropertyValue(EntitySorter sorter)
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	resolvedSelection.sortByPropertyValue(sorter);
    }

    @Override
    public long getSizeInBytes()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getSizeInBytes();
    }

    @Override
    public List<QueryableDataEntity> collectEntitiesById(Set<Integer> ids)
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.collectEntitiesById(ids);
    }

    @Override
    public boolean isCachedInMemory()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.isCachedInMemory();
    }

    @Override
    public String getStringKey()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getStringKey();
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities()
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getSelectionCopyWithSharedEntities();
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities(String label)
    {
	if(resolvedSelection == null)
	    throw new IllegalAccessError("Proxy selection accessed before a resolved selection was assigned");

	return resolvedSelection.getSelectionCopyWithSharedEntities(label);
    }
}
