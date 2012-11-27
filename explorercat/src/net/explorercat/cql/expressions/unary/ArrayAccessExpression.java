package net.explorercat.cql.expressions.unary;

import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;

/**
 * Represents the array access operation ([index]) that accesses the element
 * located at the index position of an array. Notice that arrays are 0-based.
 * Trying to access a position out of bounds will generate an
 * {@link IncompatibleTypeException} (scalar values are treated as arrays of
 * length one).
 * 
 * It is important to keep in mind that array elements ARE ALWAYS SORTED when an
 * array is created, so you will be accessing a sorted list of values
 * (increasing order).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public class ArrayAccessExpression extends UnaryExpression implements Expression
{
    private int arrayIndex;

    /**
     * Creates an array access expression that will retrieve the array element
     * placed at the position given by the index parameter (arrays are 0-based).
     * 
     * @param expression The expression that will be accessed as an array.
     * @param index The index of the element that will be retrieved from the
     *        array.
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public ArrayAccessExpression(Expression expression, int index, Expression errorRecoveryExpression)
    {
	super(expression, errorRecoveryExpression);
	this.arrayIndex = index;
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
	List<DataValue> arrayValues = value.getValueAsArray();

	if(arrayIndex >= arrayValues.size() || arrayIndex < 0)
	    throw new IncompatibleTypeException("Out of bounds when accessing array");

	return arrayValues.get(arrayIndex);
    }

    @Override
    public DataType inferResultType()
    {
	// No perfect inference (impossible to know without evaluating).
	return expression.inferResultType();
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.ARRAY_ACCESS;
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedBranch = expression.cloneExpressionTree();
	Expression clonedErrorBranch = errorRecoveryExpression;

	if(this.errorRecoveryExpression != null)
	    clonedErrorBranch = errorRecoveryExpression.cloneExpressionTree();

	return new ArrayAccessExpression(clonedBranch, arrayIndex, clonedErrorBranch);
    }

    // Out of the interface.

    public int getAccessIndex()
    {
	return this.arrayIndex;
    }
}
