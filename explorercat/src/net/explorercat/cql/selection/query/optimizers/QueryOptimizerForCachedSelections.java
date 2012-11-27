package net.explorercat.cql.selection.query.optimizers;

import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.values.ReferenceExpression;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for query optimizers that run over cached selections.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Oct 2010
 */

public abstract class QueryOptimizerForCachedSelections implements QueryOptimizer
{
    // Logging
    private static final Log log = LogFactory.getLog(QueryOptimizerForCachedSelections.class);

    // List of cached entities from the input selection. 
    protected List<QueryableDataEntity> cachedSelection;

    // Sorter used to sort the entities.
    protected EntitySorter selectionSorter;

    // Expression that will be used to optimize the query.
    protected Expression optimizableExpression;

    // Query to be optimized over the cached selection.
    protected Query query;

    /**
     * Builds an optimiser for an input selection that can be reused with
     * different queries.
     * 
     * @param cachedEntities The list of entities that are cached in the input
     *        selection.
     * @param sorter The selection sorter used to sort the entities.
     * @param optimizableExpression Expression that will be used to optimize the
     *        query.
     * @param query Original selection query to be executed over the cached
     *        selection.
     */

    public QueryOptimizerForCachedSelections(List<QueryableDataEntity> cachedEntities, EntitySorter sorter,
					     Expression optimizableExpression, Query query)
    {
	this.selectionSorter = sorter;
	this.cachedSelection = cachedEntities;
	this.optimizableExpression = optimizableExpression;
	this.query = query;
    }

    @Override
    public boolean canOptimizeQuery()
    {
	// Impossible to optimize if the selection has NOT been sorted.
	if(selectionSorter == null)
	{
	    log.debug("Selection Sorter is NULL");
	    return false;
	}

	// The first contained expression must be a reference.
	Expression firstExpression = optimizableExpression.getChildExpressions().get(0);
	ReferenceExpression reference = ((ReferenceExpression) firstExpression);

	// Check if the selection has been sorted using this property (required to optimize).
	if(reference.getReferencedPropertyName().equals(selectionSorter.getFirstSortingProperty()))
	    return true;
	else
	    return false;
    }

    // Assumes canOptimizeQuery is true.
    @Override
    public List<QueryableDataEntity> generatePreliminarySelection() throws SelectionException
    {
	if(log.isDebugEnabled())
	    log.debug("Trying to optimize " + optimizableExpression + " " + selectionSorter);

	try
	{
	    return selectEntities();
	}
	catch(ExplorerCatCheckedException e)
	{
	    log.error("Optimization aborted, error trying to optimize a query: " + e);
	    return cachedSelection;
	}
    }

    // Assumes canOptimizeQuery is true.
    @Override
    public boolean isPreliminarySelectionFinal()
    {
	// We can avoid re-evaluating the query (iterating over the selection)
	// if the condition expression is exactly the same as the expression used
	// to optimize the query.
	String queryConditionKey = query.getCondition().getExpression().getStringKey();
	String optimizableExpressionKey = optimizableExpression.getStringKey();

	return queryConditionKey.equals(optimizableExpressionKey);
    }

    /**
     * Finds the index of the first entity where the given property has the
     * wanted value or the index of the closest entity if no one is found.
     * 
     * @param entities The list of entities to be used in the search.
     * @param propertyName The name of the property we are querying.
     * @param wantedValue The value of the property we are looking for.
     * @return The index of the first entity found what the wanted value or the
     *         index of the closest one.
     */

    protected int binarySearchByPropertyValue(List<QueryableDataEntity> entities, String propertyName,
					      DataValue wantedValue) throws SelectionException
    {
	try
	{
	    int start = 0;
	    int end = entities.size() - 1;
	    int middle = (start + end) / 2;

	    // + 1 for ascending order, -1 otherwise.
	    int stepShift = selectionSorter.isUsingAscendingOrder() ? +1 : -1;

	    while(start <= end)
	    {
		// Be careful with the nulls, we assume they have a value of -infinity.
		DataValue currentValue = entities.get(middle).getPropertyValue(propertyName);

		// We assume a null property has a value of -INFINITE
		if(currentValue == null)
		    start = middle + stepShift;
		else
		{
		    int comparison = comparePropertyValue(currentValue, wantedValue);

		    if(comparison < 0) // currentValue < wantedValue		   
			start = middle + stepShift;

		    else if(comparison > 0) // currentValue > wantedValue			
			end = middle + stepShift;

		    else
			return middle;
		}

		middle = (start + end) / 2;
	    }

	    return middle;
	}
	catch(IncompatibleTypeException e)
	{
	    log.error("Error performing a binary search for " + propertyName);
	    throw new SelectionException("Error performing a binary search for: " + propertyName, e);
	}
    }

    /**
     * Gets the index of the first entity that has a different value (from the
     * given one), starting at the given index.
     * 
     * @param index The initial position to start the search.
     * @param entities List of entities to be searched.
     * @param propertyName Name of the property we are checking.
     * @param propertyValue Value of the property we are comparing to.
     * @return The index of the first entity that has a different value for the
     *         given property (0 if there is no entity with a different property
     *         value when searching from the given index).
     */

    protected int findFirstEntityWithDifferentValue(int index, List<QueryableDataEntity> entities, String propertyName,
						    DataValue propertyValue) throws IncompatibleTypeException
    {
	// Extend the indices to get the closest value and cover repeated elements.
	while(index >= 0 && entities.get(index).getPropertyValue(propertyName) != null
	      && comparePropertyValue(entities.get(index).getPropertyValue(propertyName), propertyValue) == 0)
	    --index;

	return index + 1;
    }

    /**
     * Gets the index of the last entity that has a different value (from the
     * given one), starting at the given index.
     * 
     * @param index The initial position to start the search.
     * @param entities List of entities to be searched.
     * @param propertyName Name of the property we are checking.
     * @param propertyValue Value of the property we are comparing to.
     * @return The index of the last entity that has a different value for the
     *         given property (entities.size()-1 if there is no property with a
     *         different property value when searching from the given index).
     */

    protected int findLastEntityWithDifferentValue(int index, List<QueryableDataEntity> entities, String propertyName,
						   DataValue propertyValue) throws IncompatibleTypeException
    {
	// Extend the indices to get the closest value and cover repeated elements.
	while(index < entities.size() && entities.get(index).getPropertyValue(propertyName) != null
	      && comparePropertyValue(entities.get(index).getPropertyValue(propertyName), propertyValue) == 0)
	    ++index;

	return index - 1;
    }

    /**
     * Comparator method to be implemented by each subclass.
     * 
     * @param currentValue The first data value we are comparing.
     * @param wantedValue The second data value we are comparing.
     * @return An integer > 0 if currentValue > wantedValue, < 0 if currentValue
     *         < wanted value and 0 if they are equal.
     */

    protected abstract int comparePropertyValue(DataValue currentValue, DataValue wantedValue)
	throws IncompatibleTypeException;

    /**
     * Hook method that generates a selection of entities using the optimizable
     * expression of the optimizer as condition.
     * 
     * @return The list of entities selected after executing the optimizer.
     */

    protected abstract List<QueryableDataEntity> selectEntities() throws SelectionException,
	ExpressionEvaluationException;

}
