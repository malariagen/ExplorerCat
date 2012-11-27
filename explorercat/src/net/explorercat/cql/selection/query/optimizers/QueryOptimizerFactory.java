package net.explorercat.cql.selection.query.optimizers;

import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.data.QueryableDataEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Factory in charge of instantiate the proper optimizer for a given query.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 14 Oct 2010
 */

public class QueryOptimizerFactory
{
    // Logging
    private static final Log log = LogFactory.getLog(QueryOptimizerFactory.class);

    /**
     * Creates an appropriate optimizer for the given selection query.
     * 
     * @return An optimizer that can be used with the given query or null if no
     *         proper optimizer could be created.
     */

    public static QueryOptimizer createOptimizerForQuery(List<QueryableDataEntity> cachedSelectionEntities,
							 EntitySorter cachedSelectionSorter,
							 Query queryToExecute)
    {	
	// Simplify variables and condition if possible.
	queryToExecute.simplify();

	// Expression that will be used to perform the optimization.
	Expression optimizableExpression = null;

	// Check RANGE expressions.
	optimizableExpression = FindOptimizableExpressionForType(ExpressionType.RANGE, queryToExecute);

	if(optimizableExpression != null)
	{
	    if(log.isDebugEnabled())
		log.debug("Creating RANGE optimizer");

	    return new RangeOptimizer(cachedSelectionEntities, cachedSelectionSorter, optimizableExpression,
				      queryToExecute);
	}

	// Check STARTS WITH expressions.
	optimizableExpression = FindOptimizableExpressionForType(ExpressionType.STARTS_WITH, queryToExecute);

	if(optimizableExpression != null)
	{
	    if(log.isDebugEnabled())
		log.debug("Creating STARTS WITH optimizer");

	    return new StartsWithOptimizer(cachedSelectionEntities, cachedSelectionSorter, optimizableExpression,
					   queryToExecute);
	}

	// No optimizers available.
	return null;
    }

    /**
     * Auxiliary method that finds a dominant optimizable expression of a given
     * type in the given query.
     */

    private static Expression FindOptimizableExpressionForType(ExpressionType type, Query queryToExecute)
    {
	Expression conditionExpression = queryToExecute.getCondition().getExpression();

	// Check range expressions.
	List<Expression> dominantExpressions = conditionExpression.getDominantExpressionOfType(type);

	for(Expression currentExpression : dominantExpressions)
	    if(currentExpression.canBeUsedToOptimizeQueries())
		return currentExpression;

	return null;
    }
}
