package net.explorercat.cql.result.translators;

/**
 * Exception when translating results due to its size.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Oct 2010
 */

public class ResultTooBigException extends ResultTranslationException
{
    public ResultTooBigException(String msg)
    {
	super(msg);
    }

    public ResultTooBigException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ResultTooBigException(Exception e)
    {
	super(e);
    }
}
