package net.explorercat.cql.selection.query.translators;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Error when executing a query.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Mar 2011
 */

public class QueryExecutionException extends ExplorerCatCheckedException
{
    public QueryExecutionException(String msg)
    {
	super(msg);
    }

    public QueryExecutionException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public QueryExecutionException(Exception e)
    {
	super(e);
    }
}
