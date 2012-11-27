package net.explorercat.cql.result.resourcegenerators;

/**
 * Exception when generating a resource due to its size.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 26 Oct 2010
 */

public class ResourceTooBigException extends ResourceGenerationException
{
    public ResourceTooBigException(String msg)
    {
	super(msg);
    }

    public ResourceTooBigException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ResourceTooBigException(Exception e)
    {
	super(e);
    }
}
