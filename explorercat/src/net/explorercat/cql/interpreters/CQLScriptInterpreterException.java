package net.explorercat.cql.interpreters;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Error occurred when interpreting CQl code. This kind of exception encompasses
 * any exception related to the process of parsing and interpreting CQL code.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 25 Jan 2011
 */

public class CQLScriptInterpreterException extends ExplorerCatCheckedException
{
    // List of error messages coming from the parsing stage.
    private List<String> parsingErrorMessages = new ArrayList<String>();

    public CQLScriptInterpreterException(String msg, List<String> parsingErrorMessages)
    {
	super(msg);
	this.parsingErrorMessages = parsingErrorMessages;
    }

    public CQLScriptInterpreterException(String msg)
    {
	super(msg);
    }

    public CQLScriptInterpreterException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public CQLScriptInterpreterException(Exception e)
    {
	super(e);
    }

    /**
     * Gets a list with the error messages detected in the parsing stage.
     */

    public List<String> getParsingErrorMessages()
    {
	return this.parsingErrorMessages;
    }
}
