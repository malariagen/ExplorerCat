package net.explorercat.cql.result.translators;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception when translating results into a specific format.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Oct 2010
 */

public class ResultTranslationException extends ExplorerCatCheckedException
{
    public ResultTranslationException(String msg)
    {
	super(msg);
    }

    public ResultTranslationException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ResultTranslationException(Exception e)
    {
	super(e);
    }
}
