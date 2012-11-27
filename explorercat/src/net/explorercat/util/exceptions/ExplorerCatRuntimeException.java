package net.explorercat.util.exceptions;

/**
 * Base class to be extend by runtime exceptions of the ExplorerCat system.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class ExplorerCatRuntimeException extends RuntimeException
{
    public ExplorerCatRuntimeException(String msg)
    {
	super(msg);
    }

    public ExplorerCatRuntimeException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ExplorerCatRuntimeException(Exception e)
    {
	super(e);
    }
}
