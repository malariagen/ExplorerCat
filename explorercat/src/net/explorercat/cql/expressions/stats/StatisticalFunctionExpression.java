package net.explorercat.cql.expressions.stats;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.binary.AdditionExpression;
import net.explorercat.cql.expressions.binary.AndExpression;
import net.explorercat.cql.expressions.binary.ConcatenationExpression;
import net.explorercat.cql.expressions.binary.ContainsExpression;
import net.explorercat.cql.expressions.binary.DifferenceExpression;
import net.explorercat.cql.expressions.binary.DivisionExpression;
import net.explorercat.cql.expressions.binary.EqualThanExpression;
import net.explorercat.cql.expressions.binary.GreaterOrEqualThanExpression;
import net.explorercat.cql.expressions.binary.GreaterThanExpression;
import net.explorercat.cql.expressions.binary.LessOrEqualThanExpression;
import net.explorercat.cql.expressions.binary.LessThanExpression;
import net.explorercat.cql.expressions.binary.MatchesStringExpression;
import net.explorercat.cql.expressions.binary.MultiplicationExpression;
import net.explorercat.cql.expressions.binary.OrExpression;
import net.explorercat.cql.expressions.binary.PowerExpression;
import net.explorercat.cql.expressions.binary.StartsWithExpression;
import net.explorercat.cql.expressions.transformers.ExpressionTransformer;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.visitors.ExpressionVisitor;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.NullValue;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Base class for any statistical function. It relies on the data provided by
 * the source selection. A statistical function can refer to an entity property
 * or to a variable defined in a query previously executed.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date July 2010
 */

public abstract class StatisticalFunctionExpression implements Expression
{
    protected String propertyName;
    protected Expression variableExpression;
    protected Selection sourceSelection;

    // To recover from evaluation errors.
    protected Expression errorRecoveryExpression;

    /**
     * Builds the statistical function for a given property/variable on the
     * context of a given selection.
     * 
     * @param propertyName The name of the property or the variable that will be
     *        used to calculate the value.
     * @param variableExpression The expression that defines the variable or
     *        null if it's a property.
     * @param sourceSelection The selection that will be used to calculate the
     *        statistical function.
     * @param errorRecoveryExpression An expression to be used in case of error
     *        calculating the stats.
     */

    public StatisticalFunctionExpression(String propertyName, Expression variableExpression, Selection sourceSelection,
					 Expression errorRecoveryExpression)
    {
	this.propertyName = propertyName;
	this.variableExpression = variableExpression;
	this.sourceSelection = sourceSelection;
	this.errorRecoveryExpression = errorRecoveryExpression != null ? errorRecoveryExpression
		: new LiteralExpression(new NullValue(), null);
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedVariableBranch = variableExpression;
	Expression clonedErrorBranch = errorRecoveryExpression;

	if(clonedVariableBranch != null)
	    clonedVariableBranch = variableExpression.cloneExpressionTree();

	if(this.errorRecoveryExpression != null)
	    clonedErrorBranch = errorRecoveryExpression.cloneExpressionTree();

	// Yes, a bit ugly but avoids tons of repeated code.
	// (each expression doing the same, works like a factory).
	switch(getType())
	{
	    case AVG:
		return new AverageExpression(propertyName, clonedVariableBranch, sourceSelection, clonedErrorBranch);

	    case COUNT:
		return new CountExpression(propertyName, clonedVariableBranch, sourceSelection, clonedErrorBranch);

	    case MAX:
		return new MaximumExpression(propertyName, clonedVariableBranch, sourceSelection, clonedErrorBranch);

	    case MIN:
		return new MinimumExpression(propertyName, clonedVariableBranch, sourceSelection, clonedErrorBranch);

	    case MEDIAN:
		return new MedianExpression(propertyName, clonedVariableBranch, sourceSelection, clonedErrorBranch);

	    case STDEV:
		return new StandardDeviationExpression(propertyName, clonedVariableBranch, sourceSelection,
						       clonedErrorBranch);
	    case SUM:
		return new SumExpression(propertyName, clonedVariableBranch, sourceSelection, clonedErrorBranch);

	    case VARIANCE:
		return new VarianceExpression(propertyName, clonedVariableBranch, sourceSelection, clonedErrorBranch);

	    default:
		throw new ExpressionEvaluationException("Unknown stats expression type: " + getType());
	}
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.REAL;
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder(256);

	buffer.append(getType().getShortName());

	if(sourceSelection != null && sourceSelection.getSelectionLabel() != null)
	    buffer.append("[").append(sourceSelection.getSelectionLabel()).append("]");

	buffer.append("(").append(propertyName).append(")");

	if(errorRecoveryExpression != null)
	    buffer.append("{").append(errorRecoveryExpression.toString()).append("}");

	return buffer.toString();
    }

    @Override
    public Expression applyTransformation(ExpressionTransformer transformer)
    {
	if(variableExpression != null)
	    variableExpression = variableExpression.applyTransformation(transformer);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression = errorRecoveryExpression.applyTransformation(transformer);

	return transformer.transform(this);
    }

    @Override
    public void acceptVisitor(ExpressionVisitor visitor)
    {
	if(variableExpression != null)
	    variableExpression.acceptVisitor(visitor);

	if(errorRecoveryExpression != null)
	    errorRecoveryExpression.acceptVisitor(visitor);

	visitor.visit(this);
    }

    @Override
    public Expression getErrorRecoveryExpression()
    {
	return this.errorRecoveryExpression;
    }

    @Override
    public String getStringKey()
    {
	StringBuilder key = new StringBuilder(256);

	key.append(getType().getShortName()).append("{");

	if(sourceSelection != null)
	    key.append(sourceSelection.getStringKey());
	else
	    key.append("local}");

	key.append("(").append(propertyName).append(")");

	if(variableExpression != null)
	    key.append("[").append(variableExpression.getStringKey()).append("]");

	if(errorRecoveryExpression != null)
	    key.append(errorRecoveryExpression.getStringKey());

	return key.toString();
    }

    @Override
    public boolean containsReference()
    {
	// The value for any statistical function will be provided
	// by the selection object so we do not treat them as references.
	return false;
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	try
	{
	    return new LiteralExpression(calculateExpressionValue(null), null);
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
    public DataValue calculateExpressionValue(QueryableDataEntity dataValue) throws ExpressionEvaluationException
    {
	try
	{
	    return calculateFunction();
	}
	catch(ExplorerCatCheckedException e)
	{
	    if(errorRecoveryExpression != null)
		return errorRecoveryExpression.calculateExpressionValue(dataValue);
	    else
		throw new ExpressionEvaluationException("Error trying to evaluate a statistical function: "
							+ this.toString(), e);
	}
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
    public boolean canBeUsedToOptimizeQueries()
    {
	return false;
    }

    @Override
    public List<Expression> getChildExpressions()
    {
	List<Expression> containedExpressions = new ArrayList<Expression>(1);

	if(variableExpression == null)
	    containedExpressions.add(this);
	else
	    containedExpressions.add(variableExpression);

	return containedExpressions;
    }

    /**
     * Hook method that just tries to compute the statistical function.
     * 
     * @return A data value containing the computed value.
     */

    protected abstract DataValue calculateFunction() throws StatsCalculationException;
}
