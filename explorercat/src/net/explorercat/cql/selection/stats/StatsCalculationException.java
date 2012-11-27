package net.explorercat.cql.selection.stats;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception to mark errors when calculating statistics.
 * 
 * @author Jacob Almagro Garcia -
 * @date April 2010.
 */

public class StatsCalculationException extends ExplorerCatCheckedException
{
    public StatsCalculationException(String msg)
    {
	super(msg);
    }

    public StatsCalculationException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public StatsCalculationException(Exception e)
    {
	super(e);
    }
}
