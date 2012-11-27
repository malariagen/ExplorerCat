package net.explorercat.cql.result.builders;

import java.util.List;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.Result.ResultRow;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Builder in charge of building a distinct result (a subset of DISTINCT
 * property values from a given selection).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public class DistinctResultBuilder extends RegularResultBuilder implements ResultBuilder
{
    // Logging
    private static final Log log = LogFactory.getLog(DistinctResultBuilder.class);

    private Result precalculatedResult = null;

    /**
     * Creates a new distinct result builder that will use the given selection.
     * 
     * @param referencedSelectionLabel The name of the selection to be used in
     *        the result.
     */

    public DistinctResultBuilder(String referencedSelectionLabel)
    {
	super(referencedSelectionLabel);
    }

    @Override
    public Result buildResult(int numEntities, int offset) throws ResultGenerationException
    {
	if(precalculatedResult == null)
	    precalculateResult();

	// Copy the rows from the pre-calculated result.
	List<ResultRow> precalcualtedRows = precalculatedResult.getRows();
	int endIndex = offset + numEntities <= precalcualtedRows.size() ? offset + numEntities
		: precalcualtedRows.size();

	Result result = new Result(resultLabel);
	for(int i = offset; i < endIndex; ++i)
	{
	    ResultRow currentRow = precalcualtedRows.get(i);
	    result.addRow(currentRow);
	}

	return result;
    }

    // We need a special treatment for the DISTINCT clause in order to get right the number
    // of distinct clauses, we pre-calculate the result the first time we access this method.
    @Override
    public int getNumberOfSelectedEntities() throws ResultGenerationException
    {
	if(rowExtractor == null)
	    throw new ResultGenerationException("Error, the result builder has not been resolved");

	if(precalculatedResult == null)
	    precalculateResult();

	return precalculatedResult.getRows().size();
    }

    /**
     * Auxiliary method that builds the result (pre-calculates it).
     */

    private void precalculateResult() throws ResultGenerationException
    {
	precalculatedResult = new Result(resultLabel);

	try
	{
	    // Add all the DISTINCT property values extracted from the selection							 
	    List<ResultRow> rows = null;

	    // We need to process all the data rows (we'll retrieve only DISTINCT values)
	    int numRows = rowExtractor.getSourceSelection().getSelectionSize();
	    rows = rowExtractor.extractDistinctRowsFromSelection(numRows, 0);
	    precalculatedResult.addRows(rows);

	}
	catch(ExplorerCatCheckedException e)
	{
	    log.error("Error building distinct result");
	    throw new ResultGenerationException("Error building distinct result", e);
	}
    }
}
