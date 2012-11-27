package net.explorercat.cql.expressions.binary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.IncompatibleTypeException;

/**
 * Represents the < relational operation. Note that any relational or logical
 * operation will render a boolean result. A recovery expression can be provided
 * to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class LessThanExpression extends BinaryExpression implements Expression
{
    /**
     * Creates a relational expression (a < b). A recovery expression can be
     * provided to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public LessThanExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	// A result from any relational operation will be render as a boolean.	
	if(valueA.isNumeric() || valueB.isNumeric())
	    return new BooleanValue(Float.compare(valueA.getValueAsReal(), valueB.getValueAsReal()) < 0);
	else
	    throw new IncompatibleTypeException("Operator < is not defined for " + valueA.getType() + " < "
						+ valueB.getType());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.BOOLEAN;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.LESS;
    }
}
