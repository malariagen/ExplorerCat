package net.explorercat.application;

/**
 * An exception to mark any error related with the definition of application
 * properties (via property bundles).
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public class IllegalPropertyDefinitionException extends Exception
{
    public IllegalPropertyDefinitionException(String msg)
    {
	super(msg);
    }

    public IllegalPropertyDefinitionException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public IllegalPropertyDefinitionException(Exception e)
    {
	super(e);
    }
}
