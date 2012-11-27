package net.explorercat.cql.expressions.binary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Represents the AND logical operation. Note that any relational or logical
 * operation will render a boolean result. A recovery expression can be provided
 * to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class AndExpression extends BinaryExpression implements Expression
{
    /**
     * Creates a logical AND expression. A recovery expression can be provided
     * to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public AndExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	return new BooleanValue(valueA.getValueAsBoolean() && valueB.getValueAsBoolean());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.BOOLEAN;
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	try
	{
	    // Call the general method for simplification.
	    Expression simplifiedExpression = super.simplify();

	    // reference && FALSE => FALSE
	    if(expressionA.containsReference() && !expressionB.containsReference()
	       && expressionB.calculateExpressionValue(null).getValueAsBoolean() == false)
	    {
		return new LiteralExpression(new BooleanValue(false), errorRecoveryExpression);
	    }

	    // FALSE && reference => FALSE
	    else if(expressionB.containsReference() && !expressionA.containsReference()
		    && expressionA.calculateExpressionValue(null).getValueAsBoolean() == false)
	    {
		return new LiteralExpression(new BooleanValue(false), errorRecoveryExpression);
	    }
	    
	    // TRUE && reference => reference
	    else if(expressionB.containsReference() && !expressionA.containsReference()
		    && expressionA.calculateExpressionValue(null).getValueAsBoolean() == true)
	    {
		return expressionB;
	    }
	    
	    // reference && TRUE => reference
	    else if(expressionA.containsReference() && !expressionB.containsReference()
	       && expressionB.calculateExpressionValue(null).getValueAsBoolean() == true)
	    {
		return expressionA;
	    }
	    
	    // No trivial simplification was possible.
	    else
		return simplifiedExpression;
	}

	// If anything went wrong we have to make use of the recovery expression (if present).	
	catch(ExplorerCatCheckedException e)
	{
	    if(errorRecoveryExpression != null)
	    {
		// Try to simplify the recovery expression.
		return errorRecoveryExpression.simplify();
	    }
	    else
		// We don't have an alternative value to recover, re-throw the exception.
		throw new ExpressionEvaluationException(this.toString(), e);
	}
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.AND;
    }
}
