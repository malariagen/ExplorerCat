package net.explorercat.compactcat.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.parser.CQLParser.startsFunction_return;
import net.explorercat.cql.selection.stats.StatsType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class that calculates a set of stats for a given vector of values.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 8 Feb 2011
 */

public class AttributeStatsCalculator
{
    // Logging
    private static Log log = LogFactory.getLog(AttributeStatsCalculator.class);

    /**
     * Calculates a set of statistics for the given values. Results are returned
     * in a map where the keys specify the type of stats calculated.
     * 
     * @param values Values that will be used to calculate the stats.
     * @return A map that contains one entry per estimator calculated <name,
     *         value>
     */

    public Map<StatsType, Float> calculateStats(List<Float> values)
    {
	Map<StatsType, Float> statsValues = new HashMap<StatsType, Float>();
	Collections.sort(values);

	// Count
	float count = (float) values.size();
	statsValues.put(StatsType.COUNT, count);

	// Minimum and Maximum.
	statsValues.put(StatsType.MIN, values.get(0));
	statsValues.put(StatsType.MAX, values.get(values.size() - 1));

	// Quartiles (median is the 2nd)
	statsValues.put(StatsType.FIRST_QUARTILE, calcualtePercentile(25, values));
	statsValues.put(StatsType.MEDIAN, calcualtePercentile(50, values));
	statsValues.put(StatsType.THIRD_QUARTILE, calcualtePercentile(75, values));

	// Sum
	float sum = 0;
	for(float currentValue : values)
	    sum += currentValue;
	statsValues.put(StatsType.SUM, sum);

	// Average
	float average = sum / count;

	// Variance and standard deviation.
	float variance = 0;
	for(float currentValue : values)
	    variance += (currentValue - average) * (currentValue - average);

	float standardDeviation = (float) Math.sqrt(variance / (count - 1));
	variance /= count;

	statsValues.put(StatsType.VAR, sum);
	statsValues.put(StatsType.AVG, average);
	statsValues.put(StatsType.STDDEV, Float.isNaN(standardDeviation) ? null : standardDeviation);

	return statsValues;
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

    private float calcualtePercentile(int percentile, List<Float> propertyValues)
    {
	float position = propertyValues.size() * (percentile / 100.F);

	// Case 1
	if(position - (int) position == 0 && position < propertyValues.size() - 1)
	    return (propertyValues.get((int) position) + propertyValues.get((int) position + 1)) / 2F;

	// Case 2.
	else
	{
	    int index = Math.round(position);

	    // Deal with border case when the list is small(1,2 or 3 elements).
	    if(index >= propertyValues.size())
		index = propertyValues.size() - 1;

	    return propertyValues.get(index);
	}
    }
}
