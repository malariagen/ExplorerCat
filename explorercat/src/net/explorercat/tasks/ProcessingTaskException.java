package net.explorercat.tasks;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exceptions occurred when dealing with processing tasks.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 14 Jan 2011
 */

public class ProcessingTaskException extends ExplorerCatCheckedException
{
    public ProcessingTaskException(String msg)
    {
	super(msg);
    }

    public ProcessingTaskException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ProcessingTaskException(Exception e)
    {
	super(e);
    }
}
