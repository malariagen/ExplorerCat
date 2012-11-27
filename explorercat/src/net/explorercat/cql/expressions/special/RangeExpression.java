package net.explorercat.cql.expressions.special;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.transformers.ExpressionTransformer;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.visitors.ExpressionVisitor;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.NullValue;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Checks if a expression/property/variable is in a given numeric range. A
 * recovery expression can be provided to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class RangeExpression implements Expression
{
    // Expression to be checked in range.
    public Expression expressionToCheck;
    public Expression rangeStartExpression;
    public Expression rangeEndExpression;

    // Flags to distinguish between "[", closed and "(", open.
    private boolean isRangeLeftClosed;
    private boolean isRangeRightClosed;

    // Recovering from errors.
    private Expression errorRecoveryExpression;

    /**
     * Builds a range expression that will check if the real value of the given
     * expression is contained in the range delimited by rangeStart and
     * rangeEnd.
     * 
     * @param expressionToCheck The expression whose real value will be checked
     *        to be within the range.
     * @param rangeStart The expression that defines the starting real value.
     * @param rangeEnd The expression that defines the ending real value.
     * @param closedRangeStart True if the starting range is included, false if
     *        it is excluded.
     * @param closedRangeEnd True if the ending range is included, false if it
     *        is excluded.
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed)
     */

    public RangeExpression(Expression expressionToCheck, Expression rangeStart, Expression rangeEnd,
			   boolean closedRangeStart, boolean closedRangeEnd, Expression errorRecoveryExpression)
    {
	this.expressionToCheck = expressionToCheck;
	this.rangeStartExpression = rangeStart;
	this.rangeEndExpression = rangeEnd;

	this.isRangeLeftClosed = closedRangeStart;
	this.isRangeRightClosed = closedRangeEnd;

	this.errorRecoveryExpression = errorRecoveryExpression != null ? errorRecoveryExpression
		: new LiteralExpression(new NullValue(), null);
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedBranchA = expressionToCheck.cloneExpressionTree();
	Expression clonedBranchB = rangeStartExpression.cloneExpressionTree();
	Expression clonedBranchC = rangeEndExpression.cloneExpressionTree();
	Expression clonedErrorBranch = errorRecoveryExpression;

	if(this.errorRecoveryExpression != null)
	    clonedErrorBranch = errorRecoveryExpression.cloneExpressionTree();

	return new RangeExpression(clonedBranchA, clonedBranchB, clonedBranchC, isRangeLeftClosed, isRangeRightClosed,
				   clonedErrorBranch);
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder(256);
	buffer.append(getType().getShortName()).append(expressionToCheck.toString()).append(" ");
	buffer.append((isRangeLeftClosed ? "[" : "(")).append(rangeStartExpression.toString()).append(",");
	buffer.append(rangeEndExpression.toString()).append((isRangeRightClosed ? "]" : ")"));

	if(errorRecoveryExpression != null)
	    buffer.append("{").append(errorRecoveryExpression.toString()).append("}");

	return buffer.toString();
    }

    @Override
    public String getStringKey()
    {
	StringBuilder key = new StringBuilder(256);
	key.append(getType().getShortName()).append(expressionToCheck.getStringKey());
	key.append((isRangeLeftClosed ? "[" : "(")).append(rangeStartExpression.getStringKey());
	key.append(",");
	key.append(rangeEndExpression.getStringKey()).append((isRangeRightClosed ? "]" : ")"));

	if(errorRecoveryExpression != null)
	    key.append("{").append(errorRecoveryExpression.getStringKey()).append("}");

	return key.toString();
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.BOOLEAN;
    }

    @Override
    public Expression getErrorRecoveryExpression()
    {
	return this.errorRecoveryExpression;
    }

    @Override
    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException
    {
	try
	{
	    DataValue expressionValue = expressionToCheck.calculateExpressionValue(dataEntity);
	    DataValue startValue = rangeStartExpression.calculateExpressionValue(dataEntity);
	    DataValue endValue = rangeEndExpression.calculateExpressionValue(dataEntity);

	    // Check if all the values in the array are in range.
	    if(expressionValue.getType() == DataType.ARRAY)
	    {
		List<DataValue> arrayValues = expressionValue.getValueAsArray();

		for(DataValue currentArrayValue : arrayValues)
		    if(!performOperation(currentArrayValue, startValue, endValue).getValueAsBoolean())
			return new BooleanValue(false);

		return new BooleanValue(true);
	    }
	    // Scalar case.
	    else
		return performOperation(expressionValue, startValue, endValue);
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
     * Auxiliary method that checks the given expression value is in range.
     * 
     * @param expressionValue Expression to be checked.
     * @param startValue Range lower limit.
     * @param endValue Range upper limit.
     * @return A boolean data value that specifies if the expression is in range
     *         (true) or not (false).
     */

    private DataValue performOperation(DataValue expressionValue, DataValue startValue, DataValue endValue)
	throws IncompatibleTypeException
    {
	boolean passLowerLimit;
	boolean passUpperLimit;

	if(isRangeLeftClosed)
	    passLowerLimit = expressionValue.getValueAsReal() >= startValue.getValueAsReal();
	else
	    passLowerLimit = expressionValue.getValueAsReal() > startValue.getValueAsReal();

	if(isRangeRightClosed)
	    passUpperLimit = expressionValue.getValueAsReal() <= endValue.getValueAsReal();
	else
	    passUpperLimit = expressionValue.getValueAsReal() < endValue.getValueAsReal();

	return new BooleanValue(passLowerLimit && passUpperLimit);
    }

    @Override
    public boolean containsReference()
    {
	return expressionToCheck.containsReference() || rangeStartExpression.containsReference()
	       || rangeEndExpression.containsReference();
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	try
	{
	    // We try to simplify the contained expressions.
	    expressionToCheck = expressionToCheck.simplify();
	    rangeStartExpression = rangeStartExpression.simplify();
	    rangeEndExpression = rangeEndExpression.simplify();

	    // First we check if there are is reference in the contained expressions.
	    // If not we can try to simplify, returning the simplified expression and
	    // discarding the recovery expression and the contained expression (this node will
	    // be discarded as well, the parent node will overwrite its reference with the
	    // expression we are returning).

	    if(!expressionToCheck.containsReference() && !rangeStartExpression.containsReference()
	       && !rangeEndExpression.containsReference())
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
		throw new ExpressionEvaluationException(this.toString(), e);
	}
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.RANGE;
    }

    @Override
    public List<Expression> getDominantExpressionOfType(ExpressionType type)
    {
	ArrayList<Expression> dominantExpressions = new ArrayList<Expression>(1);

	// If we reach this node if because this range is a dominant one, we return it.
	if(type == this.getType())
	    dominantExpressions.add(this);

	return dominantExpressions;
    }

    @Override
    public boolean canBeUsedToOptimizeQueries()
    {
	return expressionToCheck.getType() == ExpressionType.REFERENCE
	       && rangeStartExpression.getType() == ExpressionType.LITERAL
	       && rangeEndExpression.getType() == ExpressionType.LITERAL;
    }

    @Override
    public List<Expression> getChildExpressions()
    {
	List<Expression> containedExpressions = new ArrayList<Expression>(3);
	containedExpressions.add(expressionToCheck);
	containedExpressions.add(rangeStartExpression);
	containedExpressions.add(rangeEndExpression);

	return containedExpressions;
    }

    @Override
    public Expression applyTransformation(ExpressionTransformer transformer)
    {
	expressionToCheck = expressionToCheck.applyTransformation(transformer);
	rangeStartExpression = rangeStartExpression.applyTransformation(transformer);
	rangeEndExpression = rangeEndExpression.applyTransformation(transformer);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression = errorRecoveryExpression.applyTransformation(transformer);

	return transformer.transform(this);
    }

    @Override
    public void acceptVisitor(ExpressionVisitor visitor)
    {
	expressionToCheck.acceptVisitor(visitor);
	rangeStartExpression.acceptVisitor(visitor);
	rangeStartExpression.acceptVisitor(visitor);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression.acceptVisitor(visitor);

	visitor.visit(this);
    }

    // Out of the interface

    /**
     * Checks if the range is left closed (i.e. starts with '[')
     * 
     * @return True if the range is left closed, false if the range starts with
     *         '('.
     */

    public boolean isRangeLeftClosed()
    {
	return this.isRangeLeftClosed;
    }

    /**
     * Checks if the range is right closed (i.e. ends with ']')
     * 
     * @return True if the range is right closed, false if the range ends with
     *         ')'.
     */

    public boolean isRangeRightClosed()
    {
	return this.isRangeRightClosed;
    }

}
