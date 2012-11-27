package net.explorercat.cql.expressions.unary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.RealValue;

/**
 * Represents the absolute value operation (abs). Note this operation renders a
 * real data value. A recovery expression can be provided to recover from
 * evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class AbsoluteValueExpression extends UnaryExpression implements Expression
{
    /**
     * Creates an absolute value expression abs(expression). A recovery
     * expression can be provided to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public AbsoluteValueExpression(Expression expression, Expression errorRecoveryExpression)
    {
	super(expression, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue value) throws IncompatibleTypeException, PropertyAccessException,
	ExpressionEvaluationException
    {
	return new RealValue((float) Math.abs(value.getValueAsReal()));
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.REAL;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.ABS;
    }
}
