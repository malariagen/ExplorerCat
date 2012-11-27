package net.explorercat.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.explorercat.cql.selection.stats.StatsType;

/**
 * Lookup that stores statistics for entity properties. This class stores stats
 * values for a set of properties.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class PropertyStatsLookup implements Cloneable
{
    // Logging
    private static Log log = LogFactory.getLog(PropertyStatsLookup.class);

    // Statistics map (property -> map of stats).
    private Map<String, Map<StatsType, Float>> propertyStats;

    /**
     * Builds an empty lookup for property stats.
     */

    public PropertyStatsLookup()
    {
	propertyStats = new HashMap<String, Map<StatsType, Float>>();
    }

    /**
     * Gets the value of a statistical measurement for a given property.
     * 
     * @param propertyName Name of the property.
     * @param statsType Type of the statistical measurement we want to query
     *        (AVG, MEDIAN, etc).
     */

    public float getPropertyStats(String propertyName, StatsType statsType)
    {
	return propertyStats.get(propertyName).get(statsType);
    }

    /**
     * Adds a stats measurement, associating it with a property.
     * 
     * @param propertyName The name of the property will be associated with the
     *        measurement.
     * @param statsType The type of the measurement (AVG, MIN, MAX, etc.).
     * @param statsValue The value of the stats measurement.
     */

    public void addPropertyStats(String propertyName, StatsType statsType, float statsValue)
    {
	if(propertyStats.get(propertyName) == null)
	    propertyStats.put(propertyName, new HashMap<StatsType, Float>());

	propertyStats.get(propertyName).put(statsType, statsValue);
    }

    /**
     * Checks if a property is registered.
     * 
     * @param propertyName The name of the property to be checked.
     * @return True if the property is registered, false otherwise.
     */

    public boolean isPropertyRegistered(String propertyName)
    {
	return propertyStats.get(propertyName) != null;
    }
}
