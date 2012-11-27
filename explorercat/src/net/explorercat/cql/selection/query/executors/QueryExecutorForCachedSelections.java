package net.explorercat.cql.selection.query.executors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.SelectionResult;
import net.explorercat.cql.selection.limiters.Limiter;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.query.QueryCondition;
import net.explorercat.cql.selection.query.optimizers.QueryOptimizer;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.Catalog;
import net.explorercat.data.EntityPropertyAugmenter;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper class that optimises the execution of queries over selections that are
 * backed up by a memory cache. Different implementations of the Selection
 * interface will choose between using this class or providing their own
 * implementation for the select method.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class QueryExecutorForCachedSelections implements QueryExecutor
{
    // Logging
    private static Log log = LogFactory.getLog(QueryExecutorForCachedSelections.class);

    // Selection that will be queried to generate the new selection. 
    private Selection sourceSelection;
    private String selectionEntityType;
    private Catalog selectionCatalog;

    // Selector optimizer
    private QueryOptimizer optimizer;

    /**
     * Builds an optimiser for an input selection that can be reused with
     * different queries.
     * 
     * @param inputSelection The selection that will be queried to generate a
     *        new selection.
     * @param cachedEntities The list of entities that are cached in the input
     *        selection.
     */

    public QueryExecutorForCachedSelections(Selection inputSelection, QueryOptimizer optimizer)
    {
	this.sourceSelection = inputSelection;
	this.selectionEntityType = this.sourceSelection.getEntityType();
	this.selectionCatalog = this.sourceSelection.getEntityCatalog();

	this.optimizer = optimizer;
    }

    @Override
    public Selection performSelection(Query query) throws SelectionException
    {
	try
	{
	    // Simplify variables and condition if possible.
	    query.simplify();

	    // Take advantage of the optimizer (if any).
	    if(optimizer != null && optimizer.canOptimizeQuery())
	    {
		// Try to optimise the selection with a pre-selection.
		List<QueryableDataEntity> optimizedPreSelection = optimizer.generatePreliminarySelection();

		// Check if we can avoid iterating through the pre-selection.
		if(optimizer.isPreliminarySelectionFinal())
		{
		    if(log.isDebugEnabled())
			log.debug("The selection query was optimized (executed without iteration)");

		    PropertyDictionary dictionary = sourceSelection.getPropertyDictionary();

		    // Augment the entities and the dictionary with the variables defined by the user.
		    if(query.hasVariables())
		    {
			EntityPropertyAugmenter augmenter = new EntityPropertyAugmenter(sourceSelection, query);
			dictionary = augmenter.getDictionaryAugmentedWithVariables();
			augmenter.augmentEntitiesWithVariables(optimizedPreSelection);
		    }

		    // Note that we can share the same property dictionary since is not going to be modified.
		    SelectionResult finalSelection = new SelectionResult(query.getLabel(), selectionEntityType,
									 selectionCatalog, optimizedPreSelection,
									 dictionary, null,
									 sourceSelection.isCachedInMemory());
		    // Sort and limit the selection if necessary.
		    EntitySorter sorter = query.getResultingSelectionSorter();
		    Limiter limiter = query.getResultingSelectionLimiter();

		    if(sorter != null)
			finalSelection.sortByPropertyValue(sorter);

		    if(limiter != null)
			finalSelection.applyLimiter(limiter);

		    return finalSelection;
		}
		// Bad luck, run the normal selection process over the optimised pre-selection.	
		else
		    return selectEntitiesThroughIteration(optimizedPreSelection.iterator(), query);
	    }
	    else
	    {
		if(log.isDebugEnabled())
		    log.debug("No optimization was possible for the query, using source : "
			      + sourceSelection.getSelectionLabel() + ", size: " + sourceSelection.getSelectionSize());

		return selectEntitiesThroughIteration(sourceSelection.iterator(), query);
	    }
	}
	catch(ExplorerCatCheckedException e)
	{
	    throw new SelectionException("Error trying to perform a selection", e);
	}
    }

    /**
     * Auxiliary method that executes a query over a selection iterating through
     * all the entities.
     * 
     * @param entityIterator An iterator to iterate through all the entities in
     *        the selection.
     * @param query The query to be executed.
     * @return A new selection that contains all the entities for which the
     *         condition of the query was true.
     */

    private Selection selectEntitiesThroughIteration(Iterator<QueryableDataEntity> entityIterator, Query query)
	throws IncompatibleTypeException, PropertyAccessException, ExpressionEvaluationException
    {
	if(log.isDebugEnabled())
	    log.debug("Selecting entities through iteration");

	// Get the necessary objects from the selector.
	QueryCondition condition = query.getCondition();
	EntitySorter sorter = query.getResultingSelectionSorter();
	Limiter limiter = query.getResultingSelectionLimiter();

	// Create the new selection that will contain the selected entities.
	ArrayList<QueryableDataEntity> selectedEntities = new ArrayList<QueryableDataEntity>();

	while(entityIterator.hasNext())
	{
	    QueryableDataEntity currentEntity = entityIterator.next();

	    // Check if the selection condition is true for the current entity.
	    if(condition.evaluateCondition(currentEntity))
		selectedEntities.add(currentEntity);
	}

	PropertyDictionary dictionary = sourceSelection.getPropertyDictionary();

	// Augment the entities and the dictionary with the variables defined by the user.
	if(query.hasVariables())
	{
	    EntityPropertyAugmenter augmenter = new EntityPropertyAugmenter(sourceSelection, query);
	    dictionary = augmenter.getDictionaryAugmentedWithVariables();
	    augmenter.augmentEntitiesWithVariables(selectedEntities);
	}

	// Assembly the result of the selection.
	SelectionResult selectionResult = new SelectionResult(query.getLabel(), selectionEntityType, selectionCatalog,
							      selectedEntities, dictionary, null,
							      query.getSourceSelection().isCachedInMemory());
	// Sort if necessary.
	if(sorter != null)
	    selectionResult.sortByPropertyValue(sorter);

	// Limit the result if necessary.
	if(limiter != null)
	    selectionResult.applyLimiter(limiter);

	return selectionResult;
    }
}
