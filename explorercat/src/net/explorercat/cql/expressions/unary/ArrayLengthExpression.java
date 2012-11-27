package net.explorercat.cql.expressions.unary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.IntegerValue;

/**
 * Represents the array length operation (.length) that returns the number of
 * elements of an array. Length for scalars is defined as one.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public class ArrayLengthExpression extends UnaryExpression implements Expression
{
    /**
     * Creates an array length expression that will retrieve the number of
     * elements in an array (returning 1 if the expression is an scalar value).
     * 
     * @param expression The expression for which we'll count the number of
     *        elements.
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public ArrayLengthExpression(Expression expression, Expression errorRecoveryExpression)
    {
	super(expression, errorRecoveryExpression);
    }

    // We have to override the default behaviour for arrays.
    @Override
    protected DataValue performOperationForArrays(DataValue expressionValue) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	return performOperation(expressionValue);
    }

    @Override
    protected DataValue performOperation(DataValue value) throws IncompatibleTypeException, PropertyAccessException,
	ExpressionEvaluationException
    {
	return new IntegerValue(value.getValueAsArray().size());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.INTEGER;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.ARRAY_LENGTH;
    }
}
