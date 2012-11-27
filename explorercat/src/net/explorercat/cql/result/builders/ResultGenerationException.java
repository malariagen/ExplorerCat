package net.explorercat.cql.result.builders;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception during the building process of a result.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public class ResultGenerationException extends ExplorerCatCheckedException
{
    public ResultGenerationException(String msg)
    {
	super(msg);
    }

    public ResultGenerationException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ResultGenerationException(Exception e)
    {
	super(e);
    }

}
