package net.explorercat.cql.parser;

import net.explorercat.util.exceptions.ExplorerCatRuntimeException;

/**
 * Marks semantic errors when interpreting CQL code.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 25 Jan 2011
 */

public class CQLSemanticException extends ExplorerCatRuntimeException
{
    public CQLSemanticException(String msg)
    {
	super(msg);
    }

    public CQLSemanticException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public CQLSemanticException(Exception e)
    {
	super(e);
    }
}
