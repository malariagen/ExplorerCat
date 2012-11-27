package net.explorercat.cql.expressions.binary;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.transformers.ExpressionTransformer;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.expressions.visitors.ExpressionVisitor;
import net.explorercat.cql.types.ArrayValue;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.NullValue;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Base class for binary expressions (*, /, +, -, <, >, =, <=, >=, power,
 * concat, matches, startsWith). Implements the core functionality to be
 * extended by the concrete operators by means of the hook method
 * performOperation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public abstract class BinaryExpression implements Expression
{
    // Left and right expressions.
    protected Expression expressionA;
    protected Expression expressionB;

    // To recover from evaluation errors.
    protected Expression errorRecoveryExpression;

    /**
     * Builds a binary expression from two expression. A recovery expression can
     * be provided to be used when an evaluation error occurs (for instance
     * division by zero).
     * 
     * @param expressionA First expression, to be evaluated by the binary
     *        operator.
     * @param expressionB Second expression, to be evaluated by the binary
     *        operator.
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public BinaryExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	this.expressionA = expressionA;
	this.expressionB = expressionB;
	this.errorRecoveryExpression = errorRecoveryExpression != null ? errorRecoveryExpression
		: new LiteralExpression(new NullValue(), null);
    }

    @Override
    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException
    {
	try
	{
	    DataValue valueA = expressionA.calculateExpressionValue(dataEntity);
	    DataValue valueB = expressionB.calculateExpressionValue(dataEntity);

	    // Deal with arrays.
	    if(valueA.getType() == DataType.ARRAY || valueB.getType() == DataType.ARRAY)
		return performOperationForArrays(valueA, valueB);

	    // Scalar case.
	    else
		return performOperation(valueA, valueB);
	}
	catch(ExplorerCatCheckedException e)
	{
	    if(errorRecoveryExpression != null)
		return errorRecoveryExpression.calculateExpressionValue(dataEntity);
	    else
		throw new ExpressionEvaluationException(e);
	}
    }

    /**
     * It only returns the error recovery expression as a string. To be used by
     * subclasses.
     * 
     * @return A string description of the error recovery expression or the
     *         empty string if no error recovery expression was defined.
     */

    protected String getErrorRecoveryString()
    {
	if(errorRecoveryExpression != null)
	    return "{" + errorRecoveryExpression.toString() + "}";
	else
	    return "";
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder(256);

	buffer.append(expressionA.toString());
	buffer.append(" ").append(getType().getShortName()).append(" ");
	buffer.append(expressionB.toString());
	buffer.append(getErrorRecoveryString());

	return buffer.toString();
    }

    @Override
    public String getStringKey()
    {
	StringBuilder key = new StringBuilder(256);
	key.append("(").append(expressionA.getStringKey());
	key.append(getType().getShortName());
	key.append(expressionB.getStringKey()).append(")");

	if(errorRecoveryExpression != null)
	    key.append("ERROR{").append(errorRecoveryExpression.getStringKey()).append("}");

	return key.toString();
    }

    @Override
    public boolean containsReference()
    {
	return (this.expressionA.containsReference() || this.expressionB.containsReference());
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	try
	{
	    // We try to simplify the contained expressions.
	    expressionA = expressionA.simplify();
	    expressionB = expressionB.simplify();

	    // First we check if there are is reference in the contained expression.
	    // If not we can try to simplify, returning the simplified expression and
	    // discarding the recovery expression and the contained expression (this node will
	    // be discarded as well, the parent node will overwrite its reference with the
	    // expression we are returning).

	    if(!expressionA.containsReference() && !expressionB.containsReference())
	    {
		Expression simplifiedExpression = new LiteralExpression(calculateExpressionValue(null),
									errorRecoveryExpression);
		return simplifiedExpression;
	    }
	    else
		return this; // Not possible to simplify, we return the same expression.
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
		throw new ExpressionEvaluationException(this.toString(), e);
	}
    }

    @Override
    public List<Expression> getDominantExpressionOfType(ExpressionType type)
    {
	// If we reach a node that is an OR operation we stop descending 
	// since a dominant expression has to be linked to the root by AND expressions.
	if(getType() == ExpressionType.OR)
	    return new ArrayList<Expression>(0);
	else
	{
	    ArrayList<Expression> dominantExpressions = new ArrayList<Expression>();
	    dominantExpressions.addAll(expressionA.getDominantExpressionOfType(type));
	    dominantExpressions.addAll(expressionB.getDominantExpressionOfType(type));

	    return dominantExpressions;
	}
    }

    @Override
    public boolean canBeUsedToOptimizeQueries()
    {
	return false; // False by default;
    }

    @Override
    public List<Expression> getChildExpressions()
    {
	List<Expression> containedExpressions = new ArrayList<Expression>(2);
	containedExpressions.add(expressionA);
	containedExpressions.add(expressionB);

	return containedExpressions;
    }

    @Override
    public Expression getErrorRecoveryExpression()
    {
	return this.errorRecoveryExpression;
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedBranchA = expressionA.cloneExpressionTree();
	Expression clonedBranchB = expressionB.cloneExpressionTree();
	Expression clonedErrorBranch = errorRecoveryExpression;

	if(this.errorRecoveryExpression != null)
	    clonedErrorBranch = errorRecoveryExpression.cloneExpressionTree();

	// Yes, a bit ugly but avoids tons of repeated code.
	// (each expression doing the same, works like a factory).
	switch(getType())
	{
	    case ADDITION:
		return new AdditionExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case AND:
		return new AndExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case CONCATENATION:
		return new ConcatenationExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case CONTAINS:
		return new ContainsExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case DIFFERENCE:
		return new DifferenceExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case DIVISION:
		return new DivisionExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case EQUAL:
		return new EqualThanExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case GREATER:
		return new GreaterThanExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case GREATER_OR_EQUAL:
		return new GreaterOrEqualThanExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case LESS:
		return new LessThanExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case LESS_OR_EQUAL:
		return new LessOrEqualThanExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case MATCHES_STRING:
		return new MatchesStringExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case MULTIPLICATION:
		return new MultiplicationExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case OR:
		return new OrExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case POWER:
		return new PowerExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    case STARTS_WITH:
		return new StartsWithExpression(clonedBranchA, clonedBranchB, clonedErrorBranch);

	    default:
		throw new ExpressionEvaluationException("Unknown binary expression type: " + getType());
	}
    }

    /**
     * Performs the binary operation when at least one of the values is an
     * array. Scalar values can be combined with arrays but if the two values
     * are arrays, their size must match or an exception is raised. Do NOT use
     * this method with two scalar values or an exception will be raised.
     */

    protected DataValue performOperationForArrays(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	List<DataValue> arrayA = valueA.getValueAsArray();
	List<DataValue> arrayB = valueB.getValueAsArray();
	List<DataValue> resultValues = new ArrayList<DataValue>(arrayA.size() > arrayB.size() ? arrayA.size()
		: arrayB.size());

	// Both of them are arrays.
	if(valueA.getType() == DataType.ARRAY && valueB.getType() == DataType.ARRAY)
	{
	    if(arrayA.size() != arrayB.size())
		throw new IncompatibleTypeException("Error performing operation, arrays must have the same length");

	    for(int i = 0; i < arrayA.size(); ++i)
		resultValues.add(performOperation(arrayA.get(i), arrayB.get(i)));
	}

	// One of them is a scalar.
	else
	{
	    List<DataValue> arrayValues = null;
	    DataValue scalarValue = null;
	    boolean isOrderInverted = false;

	    if(valueA.getType() == DataType.ARRAY)
	    {
		arrayValues = arrayA;
		scalarValue = valueB;
		isOrderInverted = false; // A op B
	    }
	    else if(valueB.getType() == DataType.ARRAY)
	    {
		arrayValues = arrayB;
		scalarValue = valueA;
		isOrderInverted = true; // B op A.
	    }
	    // Sanity check.
	    else
		throw new IncompatibleTypeException("Trying to perform an array operation with two scalars");

	    // We save one comparison per iteration :).
	    if(!isOrderInverted)
		for(DataValue currentArrayValue : arrayValues)
		    resultValues.add(performOperation(currentArrayValue, scalarValue));
	    else
		for(DataValue currentArrayValue : arrayValues)
		    resultValues.add(performOperation(scalarValue, currentArrayValue));
	}

	// Check if we have to reduce to a boolean value or just return the array.
	// We return true if at least one element of the boolean array is true.
	if(resultValues.size() > 0 && resultValues.get(0).getType() == DataType.BOOLEAN)
	{
	    for(DataValue currentValue : resultValues)
		if(currentValue.getValueAsBoolean())
		    return new BooleanValue(true);

	    return new BooleanValue(false);
	}
	else
	    return new ArrayValue(resultValues);
    }

    @Override
    public Expression applyTransformation(ExpressionTransformer transformer)
    {
	expressionA = expressionA.applyTransformation(transformer);
	expressionB = expressionB.applyTransformation(transformer);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression = errorRecoveryExpression.applyTransformation(transformer);

	return transformer.transform(this);
    }

    @Override
    public void acceptVisitor(ExpressionVisitor visitor)
    {
	expressionA.acceptVisitor(visitor);
	expressionB.acceptVisitor(visitor);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression.acceptVisitor(visitor);

	visitor.visit(this);
    }

    /**
     * Hook method to be implemented by the concrete subclasses (operators).
     * 
     * @return The result data value of the operation.
     */

    protected abstract DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException;
}
