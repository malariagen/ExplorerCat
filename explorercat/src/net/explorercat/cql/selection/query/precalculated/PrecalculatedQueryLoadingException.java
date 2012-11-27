package net.explorercat.cql.selection.query.precalculated;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Marks an exception that occurred when loading/resolving pre-calculated
 * queries.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 3 Dec 2010
 */

public class PrecalculatedQueryLoadingException extends ExplorerCatCheckedException
{
    public PrecalculatedQueryLoadingException(String msg)
    {
	super(msg);
    }

    public PrecalculatedQueryLoadingException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public PrecalculatedQueryLoadingException(Exception e)
    {
	super(e);
    }
}
