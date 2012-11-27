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
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Represents a user-defined variable with its associated definition (an
 * expression that will be evaluated to give value to the variable).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date July 2010
 */

public class VariableExpression implements Expression
{
    private String name;
    private Expression definition;

    /**
     * Builds a variable based on the given expression definition.
     * 
     * @param name The name of the variable.
     * @param variableDefinition An expression that will be evaluated to obtain
     *        the variable value.
     */

    public VariableExpression(String name, Expression variableDefinition)
    {
	this.name = name;
	this.definition = variableDefinition;
    }

    @Override
    public boolean containsReference()
    {
	return definition.containsReference();
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder(128);
	buffer.append("var[").append(name).append("]");
	buffer.append("(").append(definition.toString()).append(")");

	return buffer.toString();
    }

    @Override
    public String getStringKey()
    {
	StringBuilder buffer = new StringBuilder(128);
	buffer.append("var[").append(name).append("]");
	buffer.append("(").append(definition.getStringKey()).append(")");

	return buffer.toString();
    }

    @Override
    public Expression getErrorRecoveryExpression()
    {
	return null;
    }

    @Override
    public Expression simplify() throws ExpressionEvaluationException
    {
	// We just try to simplify the variable definition and return the same object.
	try
	{
	    definition = definition.simplify();
	}
	catch(ExplorerCatCheckedException e)
	{
	    throw new ExpressionEvaluationException("Error trying to simplify variable definition for " + name, e);
	}

	return this;
    }

    @Override
    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException
    {
	return definition.calculateExpressionValue(dataEntity);
    }

    @Override
    public DataType inferResultType()
    {
	return definition.inferResultType();
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.VARIABLE;
    }

    @Override
    public List<Expression> getDominantExpressionOfType(ExpressionType type)
    {
	// A variable expression can contain a range.	
	return definition.getDominantExpressionOfType(null);
    }

    @Override
    public List<Expression> getChildExpressions()
    {
	List<Expression> containedExpressions = new ArrayList<Expression>(1);
	containedExpressions.add(definition);

	return containedExpressions;
    }

    @Override
    public boolean canBeUsedToOptimizeQueries()
    {
	return false;
    }

    @Override
    public Expression cloneExpressionTree() throws ExpressionEvaluationException
    {
	Expression clonedDefinition = definition.cloneExpressionTree();
	return new VariableExpression(name, clonedDefinition);
    }

    @Override
    public Expression applyTransformation(ExpressionTransformer transformer)
    {
	definition = definition.applyTransformation(transformer);
	return transformer.transform(this);
    }

    @Override
    public void acceptVisitor(ExpressionVisitor visitor)
    {
	definition.acceptVisitor(visitor);
	visitor.visit(this);
    }

    // Out of the interface.

    /**
     * Gets the name of the variable. This method is not returning a copy so
     * client code must not modify the object.
     */

    public String getName()
    {
	return name;
    }

    /**
     * Gets the expression definition for the variable.
     * 
     * @return The expression used to evaluate the variable value. This method
     *         is not returning a copy so client code must not modify the
     *         object.
     * 
     */

    public Expression getVariableDefinition()
    {
	return definition;
    }
}
