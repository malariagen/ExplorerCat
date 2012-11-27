package net.explorercat.cql.expressions.unary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.RealValue;
import net.explorercat.cql.types.DataType;

/**
 * Represents the logarithm operation (base 10). Note that this operation
 * renders a real data value. A recovery expression can be provided to recover
 * from negative arguments.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class LogarithmExpression extends UnaryExpression implements Expression
{
    /**
     * Creates a new logarithm (base 10) expression log(expression).
     * 
     * @param expression The expression that will be evaluated by the logarithm.
     * @param recoveryValue An alternative value to be used is the expression is
     *        < 0.
     */

    public LogarithmExpression(Expression expression, Expression recoveryValue)
    {
	super(expression, recoveryValue);
    }

    @Override
    protected DataValue performOperation(DataValue value) throws IncompatibleTypeException, PropertyAccessException,
	ExpressionEvaluationException
    {
	if(value.getValueAsReal() < 0)
	    throw new ExpressionEvaluationException("Negative argument for logarithm expression");
	else
	    return new RealValue((float) Math.log10(value.getValueAsReal()));
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.REAL;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.LOG;
    }
}
