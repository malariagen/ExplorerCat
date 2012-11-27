package net.explorercat.cql.result.builders;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.RealValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Builder in charge of building a stats result (containing a stats value
 * for a property).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public class StatsResultBuilder extends ResultBuilderBase implements ResultBuilder
{
    // Logging
    private static Log log = LogFactory.getLog(StatsResultBuilder.class);

    private String referencedSelectionLabel;

    // Stats measurement.
    private StatsType statsType;

    // Stats value that we can reuse after being calculated
    // for the first time (note it is wrapped into a list).
    private List<DataValue> statsValues;

    /**
     * Creates a new stats results builder.
     */

    public StatsResultBuilder()
    {
	super();
	this.referencedSelectionLabel = null;
	this.statsValues = null;
    }

    /**
     * Sets the label of the selection to be used with the stats.
     * 
     * @param referencedSelectionLabel The name of the selection that will be
     *        used to calculate the statistics.
     */

    public void setSelectionName(String referencedSelectionLabel)
    {
	this.referencedSelectionLabel = referencedSelectionLabel;
    }

    /**
     * Sets the type of stats of the result.
     * 
     * @param stats The type of stats the result is representing.
     */

    public void setStatsType(StatsType stats)
    {
	this.statsType = stats;
    }

    @Override
    public Result buildResult(int numEntities, int offset) throws ResultGenerationException
    {
	Result result = new Result(resultLabel);

	if(statsValues == null)
	    throw new ResultGenerationException("Error, the result has not been resolved");

	// Add stats values.
	result.addRow(-1, statsValues);

	return result;
    }

    @Override
    public void resolveResult(SelectionResolver resolver) throws SelectionResolutionException
    {
	Selection selection = null;
	selection = resolver.resolveSelection(referencedSelectionLabel);

	try
	{
	    // Wrap the stats value the stats list.
	    List<String> propertyNames = resultProperties.getPropertyNames();
	    RealValue statsValue = new RealValue(selection.getStatsForProperty(propertyNames.get(0), statsType));
	    this.statsValues = new ArrayList<DataValue>(1);
	    this.statsValues.add(statsValue);

	}
	catch(StatsCalculationException e)
	{
	    log.error("Error resolving stats result");
	    throw new SelectionResolutionException("Error calculating stats for resolved selection in stats result", e);
	}
    }

    @Override
    public String toString()
    {
	return statsType + "(" + referencedSelectionLabel + ")" + super.toString();
    }

    @Override
    public ResultHeader getHeader() throws ResultGenerationException
    {
	ResultHeader header = new ResultHeader(resultLabel, getNumberOfSelectedEntities());

	// Add all the properties/variables as columns.
	for(int i = 0; i < resultProperties.size(); ++i)
	{
	    String originalName = statsType.toString() + "(" + resultProperties.getPropertyName(i) + ")";
	    String alias = statsType.toString() + "(" + resultProperties.getPropertyLabel(i) + ")";
	    String description = statsType.toString();
	    DataType type = DataType.REAL;

	    header.addColumn(originalName, alias, description, type);
	}

	return header;
    }

    @Override
    public int getNumberOfSelectedEntities() throws ResultGenerationException
    {
	return 1;
    }

    @Override
    public void sortSourceSelectionByPropertyValue(String sortingPropertyName, boolean sortInDescendantOrder)
    {
	// Nothing to do.
	;
    }
}
