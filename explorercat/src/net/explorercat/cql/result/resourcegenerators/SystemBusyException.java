package net.explorercat.cql.result.resourcegenerators;

/**
 * Exception when generating a resource due to the system being busy.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 26 Oct 2010
 */

public class SystemBusyException extends ResourceGenerationException
{
    public SystemBusyException(String msg)
    {
	super(msg);
    }

    public SystemBusyException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public SystemBusyException(Exception e)
    {
	super(e);
    }
}
