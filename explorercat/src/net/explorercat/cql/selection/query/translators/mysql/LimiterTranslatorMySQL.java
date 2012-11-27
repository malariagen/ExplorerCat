package net.explorercat.cql.selection.query.translators.mysql;

import java.util.Calendar;

import net.explorercat.cql.selection.limiters.RandomLimiter;
import net.explorercat.cql.selection.limiters.Limiter;
import net.explorercat.cql.selection.limiters.LimiterType;
import net.explorercat.util.exceptions.TranslationException;

/**
 * MySQL translator for limiters. Translate limiters into MySQL code that can be
 * appended to MySQL queries and executed against a MySQL DB.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Mar 2011
 */

public class LimiterTranslatorMySQL
{
    private RandomLimiterTranslator randomTranslator;
    private TopLimiterTranslator topTranslator;
    private BottomLimiterTranslator bottomTranslator;

    /**
     * Builds a MySQL translator for limiters. Internal translators are
     * initialised at building time so it is a good idea to reuse this object
     * instead of creating a new one for each translation.
     */

    public LimiterTranslatorMySQL()
    {
	this.randomTranslator = new RandomLimiterTranslator();
	this.topTranslator = new TopLimiterTranslator();
	this.bottomTranslator = new BottomLimiterTranslator();
    }

    /**
     * Translates the given limiter into MySQL code that can be appended to
     * MySQL queries and executed against a MySQL DB.
     * 
     * @param limiter The limiter to be translated.
     * @return A string represented the MySQL code for the prepared statement.
     * @throws TranslationException If there is an error performing the
     *         translation.
     */

    public String translateIntoMySQLCode(Limiter limiter) throws TranslationException
    {
	LimiterCodeTranslatorMySQL translator = getCodeTranslatorFor(limiter.getType());
	return translator.translateLimiter(limiter);
    }

    /**
     * Gets the appropriate code translator for the given limiter type.
     */

    private LimiterCodeTranslatorMySQL getCodeTranslatorFor(LimiterType type) throws TranslationException
    {
	switch(type)
	{
	    case RANDOM_LIMITER:
		return this.randomTranslator;

	    case TOP_LIMITER:
		return this.topTranslator;

	    case BOTTOM_LIMITER:
		return this.bottomTranslator;

	    default:
		throw new TranslationException("Unknown limiter type : " + type);
	}
    }

    /**
     * Internal interface that represents a MySQL translator for selection
     * limiters into MySQL code. Different implementations will be provided
     * based on the type of limiter to be translated.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 4 Mar 2011
     */

    private static interface LimiterCodeTranslatorMySQL
    {
	/**
	 * Translates the given limiter into equivalent MySQL code.
	 */

	public String translateLimiter(Limiter limiter) throws TranslationException;
    }

    /**
     * Implementation for random limiters
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 4 Mar 2011
     */

    private class RandomLimiterTranslator implements LimiterCodeTranslatorMySQL
    {
	@Override
	public String translateLimiter(Limiter limiter) throws TranslationException
	{
	    // Very ugly... but necessary.
	    if(limiter instanceof RandomLimiter)
	    {
		RandomLimiter randomLimiter = ((RandomLimiter) limiter);

		long seed = randomLimiter.getPredefinedSeed();
		seed = !randomLimiter.hasPredefinedSeed() ? Calendar.getInstance().getTimeInMillis() : seed;

		return "ORDER BY RAND(" + seed + ") LIMIT " + randomLimiter.getMaxNumberOfEntities();
	    }
	    else
		throw new TranslationException("Error translating " + limiter + ", is not a random limiter");
	}
    }

    /**
     * Implementation for top limiters
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 4 Mar 2011
     */

    private class TopLimiterTranslator implements LimiterCodeTranslatorMySQL
    {
	@Override
	public String translateLimiter(Limiter limiter) throws TranslationException
	{
	    return "LIMIT " + limiter.getMaxNumberOfEntities() + " OFFSET " + limiter.getOffset();
	}
    }

    /**
     * Implementation for bottom limiters
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 4 Mar 2011
     */

    private class BottomLimiterTranslator implements LimiterCodeTranslatorMySQL
    {
	@Override
	public String translateLimiter(Limiter limiter) throws TranslationException
	{
	    throw new TranslationException("It is not possible to translate a bottom limiter into MySQL");
	}
    }

}
