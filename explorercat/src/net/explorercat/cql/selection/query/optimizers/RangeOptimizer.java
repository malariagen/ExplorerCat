package net.explorercat.cql.selection.query.optimizers;

import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.QueryableDataEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Optimizer for range expressions queries.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class RangeOptimizer extends QueryOptimizerForCachedSelections implements QueryOptimizer
{
    // Logging
    private static final Log log = LogFactory.getLog(RangeOptimizer.class);

    /**
     * Builds an optimiser for the given cached selection selection that can be
     * reused with different queries.
     * 
     * @param cachedEntities The list of entities that are cached in the source
     *        selection.
     * @param sorter The selection sorter that has been used to sort the
     *        entities (null if no sorter was used).
     */

    public RangeOptimizer(List<QueryableDataEntity> cachedEntities, EntitySorter sorter,
			  Expression optimizableExpression, Query query)
    {
	super(cachedEntities, sorter, optimizableExpression, query);
    }

    @Override
    protected List<QueryableDataEntity> selectEntities() throws SelectionException, ExpressionEvaluationException
    {
	String sortingProperty = null;
	List<QueryableDataEntity> entities = cachedSelection; // Alias to make the code shorter.

	if(selectionSorter != null)
	{
	    sortingProperty = selectionSorter.getFirstSortingProperty();
	    List<Expression> rangeExpressions = optimizableExpression.getChildExpressions();

	    // Get the limits of the range.
	    DataValue[] propertyValues = new DataValue[2];
	    propertyValues[0] = rangeExpressions.get(1).calculateExpressionValue(null);
	    propertyValues[1] = rangeExpressions.get(2).calculateExpressionValue(null);

	    // Get the indices for each value.
	    int firstValueIndex = binarySearchByPropertyValue(entities, sortingProperty, propertyValues[0]);
	    int secondValueIndex = binarySearchByPropertyValue(entities, sortingProperty, propertyValues[1]);

	    // Sort the indices, checking if they are inverted.
	    int[] indices = sortIndices(firstValueIndex, secondValueIndex, propertyValues);
	    int startIndex = indices[0];
	    DataValue startValue = propertyValues[0];
	    int endIndex = indices[1];
	    DataValue endValue = propertyValues[1];

	    try
	    {
		startIndex = findFirstEntityWithDifferentValue(startIndex, entities, sortingProperty, startValue);
		endIndex = findLastEntityWithDifferentValue(endIndex, entities, sortingProperty, endValue);

		if(log.isDebugEnabled())
		    log.debug("Query optimized by range: [" + startIndex + "," + endIndex + "]");

		// Return the segment of selected entities.
		return entities.subList(startIndex, endIndex + 1);
	    }
	    catch(IncompatibleTypeException e)
	    {
		log.error("Error optimizing a selection for property: " + sortingProperty);
		throw new SelectionException("Error optimizing a selection for property: " + sortingProperty, e);
	    }
	}

	// No optimization is possible.
	else
	    return entities;
    }

    /**
     * Auxiliary method that sorts the given indices and values. Notice the
     * propertyValues array is modified by the method.
     * 
     * @return An array containing the sorted indices ([0] for lower index and
     *         [1] for the upper index). The propertyValues array is modified
     *         accordingly ([0] for the value of the lower index and [1] for the
     *         value of the upper index).
     */

    private int[] sortIndices(int indexA, int indexB, DataValue[] propertyValues)
    {
	int[] sortedIndices = new int[2];

	if(indexA < indexB)
	{
	    sortedIndices[0] = indexA;
	    sortedIndices[1] = indexB;
	}
	else
	{
	    sortedIndices[0] = indexB;
	    sortedIndices[1] = indexA;

	    DataValue temp = propertyValues[0];
	    propertyValues[0] = propertyValues[1];
	    propertyValues[1] = temp;
	}

	return sortedIndices;
    }

    @Override
    protected int comparePropertyValue(DataValue currentValue, DataValue wantedValue) throws IncompatibleTypeException
    {
	return Float.compare(currentValue.getValueAsReal(), wantedValue.getValueAsReal());
    }
}
