package net.explorercat.cql.expressions;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Mark exceptions when evaluating expressions due to errors like
 * "divided by zero".
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date July 2010
 */

public class ExpressionEvaluationException extends ExplorerCatCheckedException
{
    public ExpressionEvaluationException(String msg)
    {
	super(msg);
    }

    public ExpressionEvaluationException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ExpressionEvaluationException(Exception e)
    {
	super(e);
    }
}
