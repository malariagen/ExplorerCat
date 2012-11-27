package net.explorercat.cql.selection.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.RealValue;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Calculates statistics for all the values of a property/variable in a given
 * selection. Notice that entities without a value for the property won't be
 * used. Entities that generates an evaluation exception will be discarded as
 * well (i.e. divided by zero due to a zero as a property value). Any
 * abnormality will be recorded in the log as an error.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class SelectionStatsCalculator
{
    // Logging
    private static Log log = LogFactory.getLog(SelectionStatsCalculator.class);

    // Map of values
    Map<StatsType, Float> statsValues;

    /**
     * Builds a statistics calculator for the given property or variable in the
     * given selection. Stats will be calculated during construction time so
     * creating a new calculator can take some time.
     * 
     * @param name The name of the property/variable that will be used to
     *        calculate the stats.
     * @param variableExpression An expression that defines a variable (null if
     *        we are dealing with a property).
     * @param selection Selection of entities that will be used to calculate the
     *        statistics.
     */

    public SelectionStatsCalculator(String name, Expression variableExpression, Selection selection)
	    throws StatsCalculationException
    {
	try
	{
	    statsValues = new HashMap<StatsType, Float>();
	    initializeStatsToDefaultValue(0);

	    // We keep the default values if the selection is empty.
	    if(selection.getSelectionSize() > 0)
	    {
		// Get the property/variable value for all the entities.
		ArrayList<RealValue> values = new ArrayList<RealValue>(selection.getSelectionSize());
		Iterator<QueryableDataEntity> entitiesIterator = selection.iterator();

		while(entitiesIterator.hasNext())
		{
		    try
		    {
			QueryableDataEntity currentEntity = entitiesIterator.next();

			// Get the value for the property or variable.
			DataValue propertyValue = (variableExpression == null ? currentEntity.getPropertyValue(name)
				: variableExpression.calculateExpressionValue(currentEntity));

			// We discard any null value, so the statistics won't take these values into account.
			// We expand array values (adding each array element as an individual value).
			if(propertyValue != null)
			{
			    if(propertyValue.getType() != DataType.ARRAY)
				values.add(new RealValue(propertyValue.getValueAsReal()));
			    else
			    {
				List<DataValue> arrayValues = propertyValue.getValueAsArray();

				for(DataValue currentValue : arrayValues)
				    values.add(new RealValue(currentValue.getValueAsReal()));
			    }
			}
		    }
		    catch(ExpressionEvaluationException e)
		    {
			log.error("Error evaluating expression during stats calculation, value for this entity won't be"
				  + " used to calculate the stats: " + e);
		    }
		}

		calculateStatisticsForProperty(values);
	    }
	}
	catch(ExplorerCatCheckedException e)
	{
	    log.error("Error ocurred during stats calculation");
	    throw new StatsCalculationException(e);
	}
    }

    /**
     * Gets the value for a stats measurement.
     * 
     * @param statsType The type of of stats that will be queried.
     * @return The value for the stats measurement.
     */

    public float getStatsValue(StatsType statsType)
    {
	return statsValues.get(statsType);
    }

    /**
     * Auxiliary method that initialises the stats with a default value.
     * 
     * @param defaultValue The value that will be assigned to the stats.
     */

    private void initializeStatsToDefaultValue(float defaultValue)
    {
	for(StatsType type : StatsType.values())
	    statsValues.put(type, defaultValue);
    }

    /**
     * Calculates the statistics for all the given values.
     * 
     * @param propertyValues The list of property values.
     */

    private void calculateStatisticsForProperty(List<RealValue> propertyValues) throws IncompatibleTypeException
    {
	// Sort the values.
	Collections.sort(propertyValues);

	// Count
	float count = (float) propertyValues.size();
	statsValues.put(StatsType.COUNT, count);

	// Minimum and Maximum.
	statsValues.put(StatsType.MIN, propertyValues.get(0).getValueAsReal());
	statsValues.put(StatsType.MAX, propertyValues.get(propertyValues.size() - 1).getValueAsReal());

	// Quartiles (median is the 2nd)
	statsValues.put(StatsType.FIRST_QUARTILE, calcualtePercentile(25, propertyValues));
	statsValues.put(StatsType.MEDIAN, calcualtePercentile(50, propertyValues));
	statsValues.put(StatsType.THIRD_QUARTILE, calcualtePercentile(75, propertyValues));

	// Sum
	float sum = 0;
	for(RealValue currentValue : propertyValues)
	    sum += currentValue.getValueAsReal();
	statsValues.put(StatsType.SUM, sum);

	// Average
	float average = sum / count;

	// Variance and standard deviation.
	float variance = 0;
	for(RealValue currentValue : propertyValues)
	    variance += (currentValue.getValueAsReal() - average) * (currentValue.getValueAsReal() - average);

	float standardDeviation = (float) Math.sqrt(variance / (count - 1));
	variance /= count;

	statsValues.put(StatsType.VAR, sum);
	statsValues.put(StatsType.AVG, average);
	statsValues.put(StatsType.STDDEV, standardDeviation);
    }

    /**
     * Auxiliary method that implements the formula for locating the position of
     * the observation at a given percentile with n data points sorted in
     * ascending order. L = n * (y/100). Case 1: If L is a whole number, then
     * the value will be found halfway between positions L and L+1. Case 2: If L
     * is a decimal, round up to the nearest whole number. (for example, L = 1.2
     * becomes 2).
     * 
     * @param percentile The percentile to be calculated [0-100]
     * @param propertyValues The list of values.
     * @return The value for the percentile.
     */

    private float calcualtePercentile(int percentile, List<RealValue> propertyValues) throws IncompatibleTypeException
    {
	float position = propertyValues.size() * (percentile / 100.F);

	// Case 1
	if(position - (int) position == 0 && position < propertyValues.size() - 1)
	    return (propertyValues.get((int) position).getValueAsReal() + propertyValues.get((int) position + 1).getValueAsReal()) / 2F;

	// Case 2.
	else
	    return propertyValues.get(Math.round(position)).getValueAsReal();
    }
}
