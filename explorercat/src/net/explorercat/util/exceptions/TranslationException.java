package net.explorercat.util.exceptions;

/**
 * Error when translating anything into a different representation (like a DB).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 2 Feb 2011
 */

public class TranslationException extends ExplorerCatRuntimeException
{
    public TranslationException(String msg)
    {
	super(msg);
    }

    public TranslationException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public TranslationException(Exception e)
    {
	super(e);
    }
}
