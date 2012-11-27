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
 * Represents the OR logical operation. Note that any relational or logical
 * operation will render a boolean result. A recovery expression can be provided
 * to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class OrExpression extends BinaryExpression implements Expression
{
    /**
     * Creates an OR expression (a OR b). A recovery expression can be provided
     * to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public OrExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	return new BooleanValue(valueA.getValueAsBoolean() || valueB.getValueAsBoolean());
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

	    // reference || TRUE => TRUE
	    if(expressionA.containsReference() && !expressionB.containsReference()
	       && expressionB.calculateExpressionValue(null).getValueAsBoolean() == true)
	    {
		return new LiteralExpression(new BooleanValue(true), errorRecoveryExpression);
	    }

	    // TRUE || reference => TRUE
	    else if(expressionB.containsReference() && !expressionA.containsReference()
		    && expressionA.calculateExpressionValue(null).getValueAsBoolean() == true)
	    {
		return new LiteralExpression(new BooleanValue(true), errorRecoveryExpression);
	    }
	    
	    // FALSE || reference => reference
	    else if(expressionB.containsReference() && !expressionA.containsReference()
		    && expressionA.calculateExpressionValue(null).getValueAsBoolean() == false)
	    {
		return expressionB;
	    }
	    
	    // reference || FALSE => reference
	    else if(expressionA.containsReference() && !expressionB.containsReference()
		    && expressionB.calculateExpressionValue(null).getValueAsBoolean() == false)
	    {
		return expressionA;
	    }

	    // No trivial simplification was possible.
	    else
		return simplifiedExpression;
	}

	// If something went wrong we have to make use of the recovery expression (if present).	
	catch(ExplorerCatCheckedException e)
	{
	    if(errorRecoveryExpression != null)
	    {
		// Try to simplify the recovery expression.
		return errorRecoveryExpression.simplify();
	    }
	    else
		// We don't have an alternative value to recover, re-throw the exception.
		throw new ExpressionEvaluationException(e);
	}
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.OR;
    }
}
