package net.explorercat.cql.result.resourcegenerators;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception occurred when generating a resource.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 26 Oct 2010
 */

public class ResourceGenerationException extends ExplorerCatCheckedException
{
    public ResourceGenerationException(String msg)
    {
	super(msg);
    }

    public ResourceGenerationException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ResourceGenerationException(Exception e)
    {
	super(e);
    }
}
