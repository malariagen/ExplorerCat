package net.explorercat.cql.selection.query;

/**
 * Exception when trying to translate any part of a query into SQL
 * 
 * @author Jacob Almagro Garcia - April 2010.
 */

public class QuerySQLTranslationException extends Exception
{
    public QuerySQLTranslationException(String msg)
    {
	super(msg);
    }

    public QuerySQLTranslationException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public QuerySQLTranslationException(Exception e)
    {
	super(e);
    }
}
