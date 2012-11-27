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
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Optimizer for queries that use the Starts With string operator.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 14 Oct 2010
 */

public class StartsWithOptimizer extends QueryOptimizerForCachedSelections implements QueryOptimizer
{
    // Logging
    private static final Log log = LogFactory.getLog(StartsWithOptimizer.class);

    /**
     * Constructor (based on cached selections).
     */
    public StartsWithOptimizer(List<QueryableDataEntity> cachedEntities, EntitySorter sorter,
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
	    List<Expression> prefixExpressions = optimizableExpression.getChildExpressions();

	    // Get the string we are using for the matching.
	    DataValue propertyValue = prefixExpressions.get(1).calculateExpressionValue(null);
	    int valueIndex = binarySearchByPropertyValue(entities, sortingProperty, propertyValue);

	    try
	    {
		int startIndex = findFirstEntityWithDifferentValue(valueIndex, entities, sortingProperty, propertyValue);
		int endIndex = findLastEntityWithDifferentValue(valueIndex, entities, sortingProperty, propertyValue);

		if(log.isDebugEnabled())
		    log.debug("Query optimized by matching: [" + startIndex + "," + endIndex + "](" + propertyValue + ")");

		// Return the segment of selected entities.
		return entities.subList(startIndex, endIndex+1);
	    }
	    catch(ExplorerCatCheckedException e)
	    {
		log.error("Error optimizing a selection for property: " + sortingProperty);
		throw new SelectionException("Error optimizing a selection for property: " + sortingProperty, e);
	    }
	}

	// No optimisation was possible.
	else
	    return entities;
    }

    @Override
    protected int comparePropertyValue(DataValue currentValue, DataValue wantedValue) throws IncompatibleTypeException
    {	
	String current = currentValue.getValueAsString();
	String wanted = wantedValue.getValueAsString();
	
	if(current.startsWith(wanted))
	    return 0;
	else
	    return current.compareTo(wanted);	 	    
    }
}
