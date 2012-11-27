package net.explorercat.cql.result.builders;

import java.util.List;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.Result.ResultRow;
import net.explorercat.cql.result.builders.SelectionCombiner.SetOperationType;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Builder in charge of building a combined result (containing a combination of
 * selections, combined by set operators).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public class CombinedResultBuilder extends ResultBuilderBase implements ResultBuilder
{
    // Logging
    private static Log log = LogFactory.getLog(CombinedResultBuilder.class);

    // Tree of combinations (nested set operations over selections).
    private SelectionSetCombination selectionCombinationTree;

    // Pre-calculated property extractor that we can reuse 
    // without building it again or recalculating the input selection.
    private ResultRowExtractor propertyExtractor;

    /**
     * Creates a new combined result builder.
     */

    public CombinedResultBuilder()
    {
	super();
	this.selectionCombinationTree = null;
	this.propertyExtractor = null;
    }

    /**
     * Sets the selection combination of the result. A selection combination is
     * just a tree of set operations that combines selections. This tree has to
     * be evaluated in order to obtain the final selection.
     * 
     * @param selectionCombination The selection combination that will be
     *        evaluated to obtain the final selection.
     */

    public void setSelectionCombination(SelectionSetCombination selectionCombination)
    {
	this.selectionCombinationTree = selectionCombination;
    }

    @Override
    public Result buildResult(int numEntities, int offset) throws ResultGenerationException
    {
	if(propertyExtractor == null)
	    throw new ResultGenerationException("Error, result not resolved");

	try
	{
	    Result result = new Result(this.resultLabel);

	    // Add all the property values extracted from the selection							 
	    List<ResultRow> rows = propertyExtractor.extractRowsFromSelection(numEntities, offset);
	    result.addRows(rows);

	    return result;
	}
	catch(SelectionException e)
	{
	    log.error("Error building combined result");
	    throw new ResultGenerationException("Error building combined result", e);
	}
    }

    @Override
    public void resolveResult(SelectionResolver resolver) throws SelectionResolutionException
    {
	try
	{
	    // Evaluate the combination tree and build the extractor.
	    Selection selection = selectionCombinationTree.evaluateCombination(resolver);

	    // If there are not registered properties we get them all.
	    if(resultProperties.size() == 0)
	    {
		List<String> propertyNames = selection.getPropertyDictionary().getPropertyNames();

		for(String propertyName : propertyNames)
		    resultProperties.addProperty(propertyName, null);
	    }

	    // Extractor in charge of getting the properties from the entities.
	    propertyExtractor = new ResultRowExtractor(selection, resultProperties.getPropertyNames());
	}
	catch(ResultGenerationException e)
	{
	    log.error("Error resolving combined result");
	    throw new SelectionResolutionException("Error resolving a combined selection for a combined result", e);
	}
    }

    @Override
    public String toString()
    {
	return selectionCombinationTree.toString();
    }

    @Override
    public ResultHeader getHeader() throws ResultGenerationException
    {
	ResultHeader header = new ResultHeader(resultLabel, getNumberOfSelectedEntities());
	PropertyDictionary dictionary = propertyExtractor.getSourceSelection().getPropertyDictionary();

	// Add all the properties/variables as columns.
	for(int i = 0; i < resultProperties.size(); ++i)
	{
	    String originalName = resultProperties.getPropertyName(i);
	    String alias = resultProperties.getPropertyLabel(i);
	    String description = dictionary.getPropertyDescription(originalName);
	    DataType type = dictionary.getPropertyType(originalName);

	    header.addColumn(originalName, alias, description, type);
	}

	return header;
    }

    @Override
    public int getNumberOfSelectedEntities() throws ResultGenerationException
    {
	if(propertyExtractor == null)
	    throw new ResultGenerationException("The result builder has not been resolved");

	return propertyExtractor.getSourceSelection().getSelectionSize();
    }

    @Override
    public void sortSourceSelectionByPropertyValue(String sortingPropertyName, boolean sortInDescendantOrder)
    {
	this.propertyExtractor.sortSourceSelectionCopyByPropertyValue(sortingPropertyName, sortInDescendantOrder);
    }

    /**
     * Inner class that will be used to build a tree of selection combinations
     * step by step. A combination is just the application of a set operation to
     * a couple of selections, like UNION or INTERSECTION. Combinations can be
     * nested so evaluating a combination means evaluating a tree of
     * combinations.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 18 Aug 2010
     */

    public static class SelectionSetCombination
    {
	// First operand: a nested combination or a selection name.
	private SelectionSetCombination firstNestedCombination;
	private String firstSelectionName;

	// Second operand: a nested combination or a selection name.
	private SelectionSetCombination secondNestedCombination;
	private String secondSelectionName;

	// Type of operation to perform over the operands (UNION, DIFFERENCE, etc.)
	private SetOperationType operationType;

	/**
	 * Builds an empty combination.
	 */

	public SelectionSetCombination()
	{
	    operationType = null;
	    firstNestedCombination = null;
	    secondNestedCombination = null;
	    firstSelectionName = null;
	    secondNestedCombination = null;
	}

	/**
	 * Sets the type of operation to perform.
	 */

	public void setOperationType(SetOperationType operation)
	{
	    operationType = operation;
	}

	/**
	 * Sets the first operand as a nested combination.
	 * 
	 * @param firstNestedCombination The combination that will act as first
	 *        operand.
	 */

	public void setFirstOperand(SelectionSetCombination firstNestedCombination)
	{
	    this.firstNestedCombination = firstNestedCombination;
	}

	/**
	 * Sets the first operand as a selection.
	 * 
	 * @param firstSelectionName The name of the selection that will act as
	 *        first operand.
	 */

	public void setFirstOperand(String firstSelectionName)
	{
	    this.firstSelectionName = firstSelectionName;
	}

	/**
	 * Sets the second operand as a nested combination.
	 * 
	 * @param secondNestedCombination The combination that will act as
	 *        second operand.
	 */

	public void setSecondOperand(SelectionSetCombination secondNestedCombination)
	{
	    this.secondNestedCombination = secondNestedCombination;
	}

	/**
	 * Sets the second operand as a selection.
	 * 
	 * @param secondSelectionName The name of the selection that will act as
	 *        second operand.
	 */

	public void setSecondOperand(String secondSelectionName)
	{
	    this.secondSelectionName = secondSelectionName;
	}

	/**
	 * Evaluates the combination returning the resulting selection.
	 * 
	 * @param resolver Resolver that will be used to look for selections.
	 * @return A selection that is the result of evaluating all the
	 *         combinations in the tree.
	 * @throws ResultGenerationException If there is an error combining the
	 *         selections.
	 */

	public Selection evaluateCombination(SelectionResolver resolver) throws ResultGenerationException
	{
	    try
	    {
		Selection firstSelection = null;
		Selection secondSelection = null;

		// Get the first operand selection.
		if(firstNestedCombination != null)
		    firstSelection = firstNestedCombination.evaluateCombination(resolver);
		else
		    firstSelection = resolver.resolveSelection(firstSelectionName);

		// Get the second operand selection
		if(secondNestedCombination != null)
		    secondSelection = secondNestedCombination.evaluateCombination(resolver);
		else
		    secondSelection = resolver.resolveSelection(secondSelectionName);

		return performSelectionCombination(firstSelection, secondSelection);
	    }
	    catch(ExplorerCatCheckedException e)
	    {
		log.error("Error building combined result");
		throw new ResultGenerationException("Error building combined result", e);
	    }
	}

	/**
	 * Auxiliary method that performs the selection combination based on the
	 * operator type.
	 * 
	 * @param selectionA The first selection to combine.
	 * @param selectionB The second selection to combine.
	 * @return The selection resulting from the combination.
	 * @throws IncompatibleTypeException If the set operation is unknown.
	 * @throws SelectionException If there is any error manipulating the
	 *         selections.
	 */

	private Selection performSelectionCombination(Selection selectionA, Selection selectionB)
	    throws IncompatibleTypeException, SelectionException
	{
	    switch(operationType)
	    {
		case UNION:
		    return SelectionCombiner.calculateUnion(selectionA, selectionB);

		case INTERSECTION:
		    return SelectionCombiner.calculateIntersection(selectionA, selectionB);

		case DIFFERENCE:
		    return SelectionCombiner.calculateDifference(selectionA, selectionB);

		default:
		    throw new IncompatibleTypeException("Unsupported set operation");
	    }
	}

	@Override
	public String toString()
	{
	    String firstOperand = (firstSelectionName != null ? firstSelectionName : firstNestedCombination.toString());
	    String secondOperand = (secondSelectionName != null ? secondSelectionName
		    : secondNestedCombination.toString());

	    return operationType + "(" + firstOperand + "," + secondOperand + ")" + super.toString();
	}
    }
}
