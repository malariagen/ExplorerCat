package net.explorercat.cql.expressions.values;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.transformers.ExpressionTransformer;
import net.explorercat.cql.expressions.visitors.ExpressionVisitor;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.NullValue;
import net.explorercat.data.QueryableDataEntity;

/**
 * A class that represents a reference to the property of an entity. For example
 * the value associated with the property "Chromosome" of a SNP.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class ReferenceExpression implements Expression
{
    private String propertyName;
    private DataType propertyType;

    // To recover from accessing errors.
    protected Expression errorRecoveryExpression;

    /**
     * Creates a reference for the property of an entity.
     * 
     * @param propertyName The name of the property.
     * @param propertyType The expected data type for the property.
     * @param errorRecoveryExpression An expression to be used in case is not
     *        possible to access the property.
     */

    public ReferenceExpression(String propertyName, DataType propertyType, Expression errorRecoveryExpression)
    {
	this.propertyType = propertyType;
	this.propertyName = propertyName;
	this.errorRecoveryExpression = errorRecoveryExpression != null ? errorRecoveryExpression
		: new LiteralExpression(new NullValue(), null);
    }

    @Override
    public boolean containsReference()
    {
	return true;
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

	return getType().getShortName() + "[" + propertyName + "]" + alternativeValue;
    }

    @Override
    public String getStringKey()
    {
	String alternativeValue = "";

	if(errorRecoveryExpression != null)
	    alternativeValue = "{" + errorRecoveryExpression.getStringKey() + "}";

	return getType().getShortName() + "[" + propertyName + "]" + alternativeValue;
    }

    @Override
    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException
    {
	DataValue propertyValue = dataEntity.getPropertyValue(propertyName);

	if(propertyValue != null)
	    return propertyValue;

	else if(propertyValue == null && errorRecoveryExpression != null)
	    return errorRecoveryExpression.calculateExpressionValue(dataEntity);

	else
	    throw new ExpressionEvaluationException(new PropertyAccessException("Error trying to access property "
										+ propertyName));
    }

    @Override
    public DataType inferResultType()
    {
	return propertyType;
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	return this;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.REFERENCE;
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
    public List<Expression> getChildExpressions()
    {
	return new ArrayList<Expression>();
    }

    @Override
    public boolean canBeUsedToOptimizeQueries()
    {
	return false;
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedErrorBranch = errorRecoveryExpression;

	if(this.errorRecoveryExpression != null)
	    clonedErrorBranch = errorRecoveryExpression.cloneExpressionTree();

	return new ReferenceExpression(propertyName, propertyType, clonedErrorBranch);
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

    // Out of the interface.

    /**
     * Gets the name of the property references.
     * 
     * @return The name of the property referenced by the expression.
     */

    public String getReferencedPropertyName()
    {
	return propertyName;
    }
}
