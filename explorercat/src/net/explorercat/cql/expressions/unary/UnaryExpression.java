package net.explorercat.cql.expressions.unary;

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
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.NullValue;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Base class for unary expressions (-, abs, log, ln, sqrt, not). Implements the
 * core functionality to be extended by the concrete operators by means of the
 * hook method performOperation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public abstract class UnaryExpression implements Expression
{
    // Expression to be evaluated by the unary operator.
    protected Expression expression;

    // To recover from evaluation errors.
    protected Expression errorRecoveryExpression;

    /**
     * Builds a unary expression. A recovery expression can be provided to be
     * used in case of evaluation error.
     * 
     * @param expression The expression to be evaluated by the operator.
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public UnaryExpression(Expression expression, Expression errorRecoveryExpression)
    {
	this.expression = expression;
	this.errorRecoveryExpression = errorRecoveryExpression != null ? errorRecoveryExpression
		: new LiteralExpression(new NullValue(), null);
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedBranch = expression.cloneExpressionTree();
	Expression clonedErrorBranch = errorRecoveryExpression;

	if(this.errorRecoveryExpression != null)
	    clonedErrorBranch = errorRecoveryExpression.cloneExpressionTree();

	// Yes, a bit ugly but avoids tons of repeated code.
	// Note that ARRAY_ACCESS has to override this method.	
	switch(getType())
	{
	    case ABS:
		return new AbsoluteValueExpression(clonedBranch, clonedErrorBranch);

	    case LOG:
		return new LogarithmExpression(clonedBranch, clonedErrorBranch);

	    case MINUS:
		return new MinusExpression(clonedBranch, clonedErrorBranch);

	    case LN:
		return new NeperianLogarithmExpression(clonedBranch, clonedErrorBranch);

	    case NOT:
		return new NotExpression(clonedBranch, clonedErrorBranch);

	    case SQRT:
		return new SquareRootExpression(clonedBranch, clonedErrorBranch);

	    default:
		throw new ExpressionEvaluationException("Unknown unary expression type: " + getType());
	}
    }

    @Override
    public Expression applyTransformation(ExpressionTransformer transformer)
    {
	expression = expression.applyTransformation(transformer);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression = errorRecoveryExpression.applyTransformation(transformer);

	return transformer.transform(this);
    }

    @Override
    public void acceptVisitor(ExpressionVisitor visitor)
    {
	expression.acceptVisitor(visitor);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression.acceptVisitor(visitor);

	visitor.visit(this);
    }

    @Override
    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException
    {
	try
	{
	    DataValue expressionValue = expression.calculateExpressionValue(dataEntity);

	    // Deal with arrays (notice we do not have to reduce for unary operators).
	    if(expressionValue.getType() == DataType.ARRAY)
		return performOperationForArrays(expressionValue);

	    // Deal with scalars.
	    else
		return performOperation(expressionValue);
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
	buffer.append(getType().getShortName());
	buffer.append("(").append(expression.getStringKey()).append(")");
	buffer.append(getErrorRecoveryString());

	return buffer.toString();
    }

    @Override
    public String getStringKey()
    {
	StringBuilder key = new StringBuilder();
	key.append(getType().getShortName());
	key.append("(").append(expression.getStringKey()).append(")");

	if(errorRecoveryExpression != null)
	    key.append("{").append(errorRecoveryExpression.getStringKey()).append("}");

	return key.toString();
    }

    @Override
    public Expression getErrorRecoveryExpression()
    {
	return this.errorRecoveryExpression;
    }

    @Override
    public boolean containsReference()
    {
	return (this.expression.containsReference());
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	try
	{
	    // Try to simplify the contained expression.
	    expression = expression.simplify();

	    // First we check if there are is reference in the contained expression.
	    // If not we can try to simplify, returning the simplified expression and
	    // discarding the recovery expression and the contained expression (this node will
	    // be discarded as well, the parent node will overwrite its reference with the
	    // expression we are returning).

	    if(!expression.containsReference())
	    {
		Expression simplifiedExpression = new LiteralExpression(calculateExpressionValue(null), null);
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
		throw new ExpressionEvaluationException(e);
	}
    }

    @Override
    public boolean canBeUsedToOptimizeQueries()
    {
	return false;
    }

    @Override
    public List<Expression> getDominantExpressionOfType(ExpressionType type)
    {
	ArrayList<Expression> dominantExpressions = new ArrayList<Expression>(1);

	// If we reach this node if because this expression is a dominant one, we return it.
	if(type == this.getType())
	    dominantExpressions.add(this);

	return dominantExpressions;
    }

    @Override
    public List<Expression> getChildExpressions()
    {
	List<Expression> containedExpressions = new ArrayList<Expression>(1);
	containedExpressions.add(expression);

	return containedExpressions;
    }

    /**
     * Performs the operation for an array. This method musn't be called for
     * scalar values.
     * 
     * @param expressionValue The array value to be processes.
     * @return The array that results after applying the unary operation to each
     *         element.
     */

    protected DataValue performOperationForArrays(DataValue expressionValue) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	List<DataValue> arrayValues = expressionValue.getValueAsArray();
	List<DataValue> resultArray = new ArrayList<DataValue>(arrayValues.size());

	for(DataValue currentValue : arrayValues)
	    resultArray.add(performOperation(currentValue));

	return new ArrayValue(resultArray);
    }

    /**
     * Hook method to be implemented by the concrete subclasses (operators).
     * 
     * @return The result data value of the operation.
     */

    protected abstract DataValue performOperation(DataValue value) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException;
}
