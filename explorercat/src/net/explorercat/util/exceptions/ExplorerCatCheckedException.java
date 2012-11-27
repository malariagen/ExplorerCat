package net.explorercat.util.exceptions;

/**
 * Base class to be extend by checked exceptions of the ExplorerCat system.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class ExplorerCatCheckedException extends Exception
{
    public ExplorerCatCheckedException(String msg)
    {
	super(msg);
    }

    public ExplorerCatCheckedException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ExplorerCatCheckedException(Exception e)
    {
	super(e);
    }
}
