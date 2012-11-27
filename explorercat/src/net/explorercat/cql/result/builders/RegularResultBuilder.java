package net.explorercat.cql.result.builders;

import java.util.List;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.Result.ResultRow;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Builder in charge of building a regular result (usually a subset of entity
 * properties from a given selection).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public class RegularResultBuilder extends ResultBuilderBase implements ResultBuilder
{
    // Logging
    private static final Log log = LogFactory.getLog(RegularResultBuilder.class);

    protected String referencedSelectionLabel;

    // Pre-calculated property extractor that we can reuse 
    // without building it again or recalculating the input selection.
    protected ResultRowExtractor rowExtractor;

    /**
     * Creates a new regular result builder that will use the given selection.
     * 
     * @param referencedSelectionLabel The name of the selection to be used in
     *        the result.
     */

    public RegularResultBuilder(String referencedSelectionLabel)
    {
	super();
	this.referencedSelectionLabel = referencedSelectionLabel;
	this.rowExtractor = null;
    }

    /**
     * Initializes the property extractor and resolves the selection associated
     * with the .
     * 
     * @param resolver The resolver in charge of looking for selections.
     * @throws SelectionResolutionException If there is an error resolving the
     *         selection.
     */

    @Override
    public void resolveResult(SelectionResolver resolver) throws SelectionResolutionException
    {
	// Resolve the selection and build the extractor.
	Selection selection = resolver.resolveSelection(referencedSelectionLabel);

	// If there are not registered properties we get them all.
	if(resultProperties.size() == 0)
	{
	    List<String> propertyNames = selection.getPropertyDictionary().getPropertyNames();

	    for(String propertyName : propertyNames)
		resultProperties.addProperty(propertyName, null);
	}
	
	// Extractor in charge of getting the properties from the entities.
	rowExtractor = new ResultRowExtractor(selection, resultProperties.getPropertyNames());
    }

    @Override
    public String toString()
    {
	return referencedSelectionLabel + super.toString();
    }

    @Override
    public ResultHeader getHeader() throws ResultGenerationException
    {
	ResultHeader header = new ResultHeader(resultLabel, getNumberOfSelectedEntities());
	PropertyDictionary dictionary = rowExtractor.getSourceSelection().getPropertyDictionary();

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
    public Result buildResult(int numEntities, int offset) throws ResultGenerationException
    {
	if(rowExtractor == null)
	    throw new ResultGenerationException("Error, result not resolved");

	try
	{
	    Result result = new Result(resultLabel);

	    // Add all the property values extracted from the selection							 
	    List<ResultRow> rows = rowExtractor.extractRowsFromSelection(numEntities, offset);
	    result.addRows(rows);	    

	    return result;
	}
	catch(ExplorerCatCheckedException e)
	{
	    log.error("Error building regular result");
	    throw new ResultGenerationException("Error building regular result", e);
	}
    }

    @Override
    public int getNumberOfSelectedEntities() throws ResultGenerationException
    {
	if(rowExtractor == null)
	    throw new ResultGenerationException("Error, the result builder has not been resolved");

	return rowExtractor.getSourceSelection().getSelectionSize();
    }

    @Override
    public void sortSourceSelectionByPropertyValue(String sortingPropertyName, boolean sortInDescendantOrder)
    {
	rowExtractor.sortSourceSelectionCopyByPropertyValue(sortingPropertyName, sortInDescendantOrder);
    }
}
