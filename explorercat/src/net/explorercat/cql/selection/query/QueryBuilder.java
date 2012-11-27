package net.explorercat.cql.selection.query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.limiters.Limiter;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.cql.selection.sorters.EntitySorter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A builder for queries. It is configured incrementally, storing all the
 * components required to build the query. During the building process any
 * selection used by the query is resolved using a {@link SelectionResolver}
 * object.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Aug 2010
 */

public class QueryBuilder
{
    // Logging
    private static final Log log = LogFactory.getLog(QueryBuilder.class);

    // Label of the CQL selector block that originates the query.
    private String label;

    // Map of user defined variables to be used in the query.
    private Map<String, Expression> variablesMap;

    // Condition to be evaluated for selecting the entities.
    private QueryCondition queryCondition;

    // Limiter (limits the selection made by the query).
    private Limiter selectionLimiter;

    // Sorter that will sort the entities selected by the query.
    private EntitySorter selectionSorter;

    // Label for the source selection. 
    // The query will be executed against this selection.
    private String sourceSelectionLabel;

    // Labels of the selections that are referenced by 
    // the statistical functions used in the query.
    private Set<String> selectionLabelsReferencedByStats;

    /**
     * Builds an empty selection query. It has to be configured incrementally
     * using the setter methods.
     */

    public QueryBuilder()
    {
	label = null;
	queryCondition = null;
	selectionLimiter = null;
	selectionSorter = null;

	variablesMap = new HashMap<String, Expression>();
	selectionLabelsReferencedByStats = new HashSet<String>();
    }

    /**
     * Sets the label of the source selection.
     * 
     * @param sourceSelectionLabel Label for the selection that will be used as
     *        source by the query.
     */

    public void setSourceSelectionLabel(String sourceSelectionLabel)
    {
	this.sourceSelectionLabel = sourceSelectionLabel;
    }

    /**
     * Gets the label of the source selection associated with the query.
     * 
     * @return The label of the source selection that will be used by the query.
     */

    public String getSourceSelectionLabel()
    {
	return this.sourceSelectionLabel;
    }

    /**
     * Registers the label of a selection that is being referenced by a stats
     * function used by the query. Any referenced selection will be resolved
     * using a SelectionResolver at building time (of the query).
     * 
     * @param referencedSelectionLabel The label of the selection being
     *        referenced.
     */

    public void addLabelOfSelectionReferencedByStats(String referencedSelectionLabel)
    {
	this.selectionLabelsReferencedByStats.add(referencedSelectionLabel);
    }

    /**
     * Sets the label that identifies the query.
     */

    public void setQueryLabel(String labelIdentifier)
    {
	this.label = labelIdentifier;
    }

    /**
     * Gets the query label.
     */

    public String getQueryLabel()
    {
	return label;
    }

    /**
     * Sets the condition of the query.
     * 
     * @param queryCondition The conditional expression that has to be true for
     *        a given entity in order to be selected.
     */

    public void setQueryCondition(QueryCondition queryCondition)
    {
	this.queryCondition = queryCondition;
    }

    /**
     * Registers a new user-defined variable to be used in the query.
     * 
     * @param variableName The identifier of the variable.
     * @param variableDefinition The definition of the variable (expression).
     */

    public void registerVariable(String variableName, Expression variableDefinition)
    {
	variablesMap.put(variableName, variableDefinition);
    }

    /**
     * Sets the limiter that will limit the selection produced by the query.
     * 
     * @param limiter The strategy in charge of limiting the query resulting
     *        selection.
     */

    public void setSelectionLimiter(Limiter limiter)
    {
	this.selectionLimiter = limiter;
    }

    /**
     * Sets the sorter that will sort the entities selected by the query.
     * 
     * @param sorter The sorter that will be in charge of sorting the query
     *        resulting selection.
     */

    public void setSelectionSorter(EntitySorter sorter)
    {
	this.selectionSorter = sorter;
    }

    /**
     * Builds a new query based on the current configuration of the builder.
     * 
     * @param resolver Required to resolve the source selection used by the
     *        query.
     * @return A new query configured with the parameters provided to the
     *         builder. Note that the internal components of the query have NOT
     *         been copied in order to improve performance (this has to be a
     *         fast operation). Any modification of them could be potentially
     *         applied to all the queries created by this builder.
     * @throws NoSuchElementException If it is not possible to resolve the
     *         source selection.
     */

    public Query buildSelectionQuery(SelectionResolver resolver) throws SelectionResolutionException
    {
	if(log.isDebugEnabled())
	    log.debug("Resolving SOURCE selection [" + this.sourceSelectionLabel + "] for [" + this.label + "]");

	// Resolving the source selection (where we are selecting from).
	Selection sourceSelection = resolver.resolveSelection(this.sourceSelectionLabel);

	if(sourceSelection == null)
	    throw new NoSuchElementException("Impossible to resolve source selection [" + this.sourceSelectionLabel
					     + "] for query: " + this.label);
	else if(log.isDebugEnabled())
	    log.debug("Source selection RESOLVED [" + this.sourceSelectionLabel + "] for [" + this.label + "]");

	// Update the scopes with the resolved selection (if necessary).
	resolver.updateScopesWithResolvedSelection(sourceSelection);

	// Resolve any selection referenced by stats functions.
	Iterator<String> referencedLabels = this.selectionLabelsReferencedByStats.iterator();
	while(referencedLabels.hasNext())
	{
	    String refSelectionLabel = referencedLabels.next();

	    if(log.isDebugEnabled())
		log.debug("Resolving REFERENCED selection [" + refSelectionLabel + "] for [" + this.label + "]");

	    if(resolver.resolveSelection(refSelectionLabel) == null)
		throw new NoSuchElementException("Impossible to resolve referenced selection  [" + refSelectionLabel
						 + "] for query: " + this.label);
	    else if(log.isDebugEnabled())
		log.debug("Referenced selection RESOLVED [" + refSelectionLabel + "] for [" + this.label + "]");

	    // Update the scopes with the resolved selection (if necessary).
	    resolver.updateScopesWithResolvedSelection(sourceSelection);
	}

	return new Query(label, queryCondition, sourceSelection, variablesMap, selectionSorter, selectionLimiter);
    }
}
