package net.explorercat.cql.expressions.values;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.transformers.ExpressionTransformer;
import net.explorercat.cql.expressions.visitors.ExpressionVisitor;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.data.QueryableDataEntity;

/**
 * Expression that contains literal values. An expression recovery expression
 * can be provided to recover from errors but it WILL NOT BE USED in this class
 * since we are dealing only with literals.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class LiteralExpression implements Expression
{
    private DataValue value;
    private Expression errorRecoveryExpression;

    /**
     * Builds an expression from a basic data value. A recovery expression can
     * be provided to recover from property access errors.
     * 
     * @param value The value that will be returned when the expression is
     *        evaluated.
     * @param errorRecoveryExpression Value to be used in case of error (can be
     *        null).
     */

    public LiteralExpression(DataValue value, Expression errorRecoveryExpression)
    {
	this.value = value;
	this.errorRecoveryExpression = errorRecoveryExpression;
    }

    @Override
    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException
    {
	return value;
    }

    @Override
    public Expression getErrorRecoveryExpression()
    {
	return this.errorRecoveryExpression;
    }

    @Override
    public String toString()
    {
	String alternativeValue = "";

	if(errorRecoveryExpression != null)
	    alternativeValue = "{" + errorRecoveryExpression.toString() + "}";

	return value.toString() + alternativeValue;
    }

    @Override
    public String getStringKey()
    {
	String alternativeValue = "";

	if(errorRecoveryExpression != null)
	    alternativeValue = "{" + errorRecoveryExpression.getStringKey() + "}";

	return value.toString() + alternativeValue;
    }

    @Override
    public DataType inferResultType()
    {
	return value.getType();
    }

    @Override
    public boolean containsReference()
    {
	// Data values don't contain references.
	return false;
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	return new LiteralExpression(value, errorRecoveryExpression);
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.LITERAL;
    }

    @Override
    public List<Expression> getDominantExpressionOfType(ExpressionType type)
    {
	ArrayList<Expression> dominantExpressions = new ArrayList<Expression>(0);

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
	return new ArrayList<Expression>();
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedErrorBranch = errorRecoveryExpression;

	if(this.errorRecoveryExpression != null)
	    clonedErrorBranch = errorRecoveryExpression.cloneExpressionTree();

	// We can pass the data value since they are non mutable objects.
	return new LiteralExpression(value, clonedErrorBranch);
    }

    @Override
    public Expression applyTransformation(ExpressionTransformer transformer)
    {
	if(errorRecoveryExpression != null)
	    errorRecoveryExpression = errorRecoveryExpression.applyTransformation(transformer);

	return transformer.transform(this);
    }

    @Override
    public void acceptVisitor(ExpressionVisitor visitor)
    {
	if(errorRecoveryExpression != null)
	    errorRecoveryExpression.acceptVisitor(visitor);

	visitor.visit(this);
    }
}
