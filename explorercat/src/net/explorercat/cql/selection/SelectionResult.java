package net.explorercat.cql.selection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.selection.limiters.Limiter;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.query.executors.QueryExecutorForCachedSelections;
import net.explorercat.cql.selection.query.optimizers.QueryOptimizer;
import net.explorercat.cql.selection.query.optimizers.QueryOptimizerFactory;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.selection.stats.SelectionStatsCalculator;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.PropertyStatsLookup;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.misc.ArchitectureModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents the result of a selection made on the fly by the execution of a
 * query over another selection.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class SelectionResult implements Selection
{
    // Logging
    private static final Log log = LogFactory.getLog(SelectionResult.class);

    protected Catalog catalog;
    protected String selectionLabel;
    protected String entityType;

    protected PropertyDictionary propertyDictionary;
    protected PropertyStatsLookup propertyStats;

    protected EntitySorter selectionSorter;
    protected List<QueryableDataEntity> entities;
    protected boolean isGeneratedFromCachedSelection;

    // Estimated size (in bytes) of the selection. This value can vary a lot
    // if we are loading new entities into memory or if we are using a memory
    // cached source selection (the selection from which we have selected the entities).
    protected long estimatedSizeInBytes;

    /**
     * Builds a new selection based on a given list of entities.
     * 
     * @param label The label of the selection.
     * @param entityType A string with the type of entity the selection
     *        contains.
     * @param catalog The catalog that contains this selection of entities.
     * @param entities The list of entities contained by the selection.
     * @param propertyDictionary A dictionary that relates each entity property
     *        with its type.
     * @param sorter Sorter used to sort the entities of the selection, null if
     *        no sorter was used.
     * @param isGeneratedFromCachedSelection True if the selection from which
     *        the entities have been selected is cached in memory, false
     *        otherwise.
     */

    public SelectionResult(String label, String entityType, Catalog catalog, List<QueryableDataEntity> entities,
			   PropertyDictionary propertyDictionary, EntitySorter sorter,
			   boolean isGeneratedFromCachedSelection)
    {
	this.catalog = catalog;
	this.selectionLabel = label;
	this.entityType = entityType;
	this.entities = entities;

	this.selectionSorter = sorter;
	this.propertyDictionary = propertyDictionary;

	// Stats will be calculated on demand.
	this.propertyStats = new PropertyStatsLookup();

	this.isGeneratedFromCachedSelection = isGeneratedFromCachedSelection;
	this.estimatedSizeInBytes = calculateSizeEstimationInBytes(isGeneratedFromCachedSelection);
    }

    /**
     * Sets the label of the selection with the given string.
     * 
     * @param label The label that will identify the selection.
     */

    public void setSelectionLabel(String label)
    {
	selectionLabel = label;
    }

    /**
     * Applies a limiter to the selection of entities. A limiter will discard
     * some entities from the selection and they will be removed so it will be
     * impossible any recovery after the limiter has been applied.
     * 
     * @param limiter The limiter in charge of discarding the entities from the
     *        selection.
     */

    public void applyLimiter(Limiter limiter)
    {
	if(log.isDebugEnabled())
	    log.debug("Aplying limiter to selection: " + this.toString() + " " + limiter.toString());

	entities = limiter.limit(entities);
    }

    @Override
    public String toString()
    {
	return "SelectionResult[CatalogId: " + catalog.getId() + ", EntityType: " + entityType + ", Size: "
	       + entities.size() + "]";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Selection interface
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getSelectionLabel()
    {
	return selectionLabel;
    }

    @Override
    public QueryableDataEntity getEntityById(int entityId)
    {
	// Sequential search, VERY SLOW. Try to avoid abusing
	// of this method when the system is running without 
	// cached DAOs. Fortunately the SelectionResultBackedByCachedDAO 
	// improves the performance of it. The rational for not using a
	// map to track entity IDs is that we are trying to minimise
	// the memory footprint of each selection as much as possible.

	for(QueryableDataEntity entity : entities)
	    if(entity.getId() == entityId)
		return entity;

	return null;
    }

    @Override
    public List<QueryableDataEntity> collectEntitiesById(Set<Integer> ids)
    {
	// Sequential scan, SLOW, same reasoning as the previous method.
	// Improved in the SelectionResultBackedByCachedDAO implementation.	
	List<QueryableDataEntity> collectedEntities = new ArrayList<QueryableDataEntity>(ids.size());

	for(QueryableDataEntity entity : entities)
	    if(ids.contains(entity.getId()))
		collectedEntities.add(entity);

	return collectedEntities;
    }

    @Override
    public int getSelectionSize()
    {
	return entities.size();
    }

    @Override
    public String getEntityType()
    {
	return entityType;
    }

    @Override
    public String getSortingProperty()
    {
	return selectionSorter.getFirstSortingProperty();
    }

    @Override
    public boolean isSortedByPropertyValue()
    {
	return selectionSorter != null;
    }

    @Override
    public boolean isSortedInAscendingOrder()
    {
	return selectionSorter.isUsingAscendingOrder();
    }

    @Override
    public void sortByPropertyValue(EntitySorter sorter)
    {
	if(log.isDebugEnabled())
	    log.debug("Sorting " + selectionLabel + " by property: " + sorter.getFirstSortingProperty());

	sorter.sort(entities);
	this.selectionSorter = sorter;
    }

    @Override
    public Iterator<QueryableDataEntity> iterator()
    {
	return entities.iterator();
    }

    @Override
    public List<QueryableDataEntity> getEntities(int numEntities, int offset)
    {
	int endIndex = offset + numEntities <= this.entities.size() ? offset + numEntities : this.entities.size();
	return this.entities.subList(offset, endIndex);
    }

    @Override
    public Selection selectEntities(Query query) throws SelectionException
    {
	if(log.isDebugEnabled())
	    log.debug("Performing selection using query: " + query.toString());

	// We'll try to speed up the execution by means of a query optimizer.	
	QueryOptimizer queryOptimizer = QueryOptimizerFactory.createOptimizerForQuery(entities, selectionSorter, query);

	// The executor will be used to execute any query over the selection.
	QueryExecutorForCachedSelections queryExecutor = new QueryExecutorForCachedSelections(this, queryOptimizer);

	// Perform the selection with the executor.
	return queryExecutor.performSelection(query);
    }

    @Override
    public Catalog getEntityCatalog()
    {
	return catalog;
    }

    @Override
    public List<String> getEntityPropertyNames()
    {
	return propertyDictionary.getPropertyNames();
    }

    @Override
    public List<DataType> getEntityPropertyTypes()
    {
	return propertyDictionary.getPropertyTypes();
    }

    @Override
    public boolean hasEntityProperty(String propertyName)
    {
	return propertyDictionary.hasProperty(propertyName);
    }

    @Override
    public DataType getEntityPropertyType(String propertyName)
    {
	return propertyDictionary.getPropertyType(propertyName);
    }

    @Override
    public String getEntityPropertyDescription(String propertyName)
    {
	return propertyDictionary.getPropertyDescription(propertyName);
    }

    @Override
    public List<String> getEntityPropertyDescriptions()
    {
	return propertyDictionary.getPropertyDescriptions();
    }

    @Override
    public Set<Integer> getEntityIds()
    {
	Set<Integer> keySet = new HashSet<Integer>(entities.size());

	for(QueryableDataEntity entity : entities)
	    keySet.add(entity.getId());

	return keySet;
    }

    @Override
    public PropertyDictionary getPropertyDictionary()
    {
	return propertyDictionary;
    }

    // Stats methods (lazy evaluation for stats).

    @Override
    public float getStatsForProperty(String propertyName, StatsType statsType) throws StatsCalculationException
    {
	checkStatsCalculation(propertyName, null);
	return propertyStats.getPropertyStats(propertyName, statsType);
    }

    @Override
    public float getStatsForVariable(String variableName, Expression variableExpression, StatsType statsType)
	throws StatsCalculationException
    {
	checkStatsCalculation(variableName, variableExpression);
	return propertyStats.getPropertyStats(variableName, statsType);
    }

    @Override
    public long getSizeInBytes()
    {
	return estimatedSizeInBytes;
    }

    @Override
    public boolean isCachedInMemory()
    {
	return true;
    }

    @Override
    public String getStringKey()
    {
	return "[cached:" + selectionLabel + "(" + entities.size() + "," + propertyDictionary.getNumProperties()
	       + "," + (isSortedByPropertyValue() ? getSortingProperty() : "false") + ")";
    }

    /**
     * Auxiliary method that calculates an estimation of the selection size in
     * bytes.
     * 
     * @param isGeneratedFromCachedSelection True if the selection from which
     *        the entities have been selected is cached in memory, false
     *        otherwise.
     * @return An estimation in bytes of the selection size.
     */

    private long calculateSizeEstimationInBytes(boolean isGeneratedFromCachedSelection)
    {
	// We'll calculate only an estimation.	
	int referenceSize = ArchitectureModel.getReferenceSizeInBytes();

	// Internal references.
	long numReferences = 10;

	// References the list of entities.
	numReferences += getSelectionSize();

	// Properties in entities, only if not coming from a cached selection.
	if(!isGeneratedFromCachedSelection)
	    numReferences += propertyDictionary.getPropertyTypes().size() * getSelectionSize();

	// Property definitions.
	numReferences += propertyDictionary.getNumProperties();

	// Stats values.
	numReferences += propertyDictionary.getNumProperties() * StatsType.values().length;

	return numReferences * referenceSize;
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities()
    {
	List<QueryableDataEntity> entitiesCopy = new ArrayList<QueryableDataEntity>(this.entities);

	return new SelectionResult(selectionLabel, entityType, catalog, entitiesCopy, propertyDictionary,
				   selectionSorter, isGeneratedFromCachedSelection);
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities(String label)
    {
	SelectionResult selectionCopy = (SelectionResult) getSelectionCopyWithSharedEntities();
	selectionCopy.selectionLabel = label;
	return selectionCopy;
    }

    /**
     * Auxiliary method that checks if we need to recalculate the statistics for
     * a given property/variable. Note we use lazy evaluation for the the stats.
     * 
     * @param name The name of the property/variable for which the stats will be
     *        calculated.
     * @param variableExpression Expression that defines the user-defined
     *        variable (null if we are referring to a property).
     * 
     */

    private void checkStatsCalculation(String name, Expression variableExpression) throws StatsCalculationException
    {
	if(!propertyStats.isPropertyRegistered(name))
	{
	    SelectionStatsCalculator calculator = new SelectionStatsCalculator(name, variableExpression, this);

	    for(StatsType type : StatsType.values())
		propertyStats.addPropertyStats(name, type, calculator.getStatsValue(type));
	}
    }
}
