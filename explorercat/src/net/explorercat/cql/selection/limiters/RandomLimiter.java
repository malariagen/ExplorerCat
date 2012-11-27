package net.explorercat.cql.selection.limiters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import net.explorercat.data.QueryableDataEntity;

/**
 * Implementation of the random limiter that will select n random elements from
 * the given list without replacement. If n is greater than the quantity of
 * entities available then all of them are selected but in random order.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class RandomLimiter extends LimiterBase implements Limiter
{
    private long seed;

    // Flag to check if we generate a new seed each time we access 
    // the random generator.
    private boolean useTimeBasedSeed;

    /**
     * Creates a random limiter that will select n random elements without
     * replacement.
     * 
     * @param maxNumberOfEntities The number of elements to be selected.
     */

    public RandomLimiter(int maxNumberOfEntities)
    {
	super(maxNumberOfEntities, 0);
	this.seed = -1;
	this.useTimeBasedSeed = true;
    }

    /**
     * Creates a random limiter that will select n random elements without
     * replacement using a seed.
     * 
     * @param maxNumberOfEntities The number of elements to be selected.
     * @param seed The seed for the random number generator.
     */

    public RandomLimiter(int maxNumberOfEntities, long seed)
    {
	super(maxNumberOfEntities, 0);
	this.seed = seed;
	this.useTimeBasedSeed = false;
    }

    @Override
    public List<QueryableDataEntity> limit(List<QueryableDataEntity> baseSelection)
    {
	// Check if we have to use a new seed each time.
	long currentSeed = useTimeBasedSeed ? Calendar.getInstance().getTimeInMillis() : seed;
	Random randomGenerator = new Random(currentSeed);

	// Duplicates the base selection to implement the selection without replacement.
	ArrayList<QueryableDataEntity> baseSelectionCopy = new ArrayList<QueryableDataEntity>(baseSelection);
	ArrayList<QueryableDataEntity> limitedSelection = new ArrayList<QueryableDataEntity>(maxNumberOfEntities);

	// Selection limit that separates elements already selected from the candidates.
	int selectionIndexLimit = baseSelectionCopy.size();

	while(limitedSelection.size() < maxNumberOfEntities && selectionIndexLimit > 0)
	{
	    int randomIndex = randomGenerator.nextInt(selectionIndexLimit);
	    limitedSelection.add(baseSelectionCopy.get(randomIndex));

	    // Overwrite the selected element with the last non selected element
	    baseSelectionCopy.set(randomIndex, baseSelectionCopy.get(selectionIndexLimit - 1));
	    --selectionIndexLimit;
	}

	return limitedSelection;
    }

    @Override
    public String toString()
    {
	return "RANDOM(" + maxNumberOfEntities + "," + seed + ")";
    }

    @Override
    public String getStringKey()
    {
	// We need to take into account that two queries with different seeds are not 
	// the same query (seeds based on current time are never recovered from the cache).
	long seedKey = useTimeBasedSeed ? Calendar.getInstance().getTimeInMillis() : seed;
	return "RANDOM(" + maxNumberOfEntities + "," + seedKey + ")";
    }

    @Override
    public String translateToSQL()
    {
	long currentSeed = useTimeBasedSeed ? Calendar.getInstance().getTimeInMillis() : seed;
	return "ORDER BY RAND(" + currentSeed + ") LIMIT " + maxNumberOfEntities;
    }

    @Override
    public LimiterType getType()
    {
	return LimiterType.RANDOM_LIMITER;
    }

    // Out of the interface.

    /**
     * Checks if the limiter is using a predefined value as seed.
     * 
     * @return True if a value has been specified to be used as seed, false if
     *         the seed is generated using the current time.
     */

    public boolean hasPredefinedSeed()
    {
	return this.useTimeBasedSeed;
    }

    /**
     * Gets the predefined seed that will be used by the limiter. Use the
     * {@link hasPredefinedSeed} method to check if one has been defined. (if no
     * seed has been defined this method returns -1 but it is possible that a
     * user specifies that value as seed).
     * 
     * @return The predefined seed used by the limiter.
     */

    public long getPredefinedSeed()
    {
	return this.seed;
    }
}
