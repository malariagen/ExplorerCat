package net.explorercat.cql.expressions.unary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.DataType;

/**
 * Represents the NOT logical operation. Note that any relational or logical
 * operation will render a boolean result. A recovery expression can be provided
 * to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class NotExpression extends UnaryExpression implements Expression
{
    /**
     * Creates an logical NOT expression NOT(expression). A recovery expression
     * can be provided to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public NotExpression(Expression expressionA, Expression errorRecoveryExpression)
    {
	super(expressionA, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA) throws IncompatibleTypeException, PropertyAccessException,
	ExpressionEvaluationException
    {
	return new BooleanValue(!valueA.getValueAsBoolean());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.BOOLEAN;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.NOT;
    }
}
