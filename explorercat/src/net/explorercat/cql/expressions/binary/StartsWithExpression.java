package net.explorercat.cql.expressions.binary;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;

/**
 * Check if the string represented by the first expression starts with the
 * string represented by the second one.
 * 
 * A recovery expression can be provided to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class StartsWithExpression extends BinaryExpression implements Expression
{
    /**
     * Creates a starts with expression (checks for a prefix). The string
     * representation of the first expression will be used to check if it starts
     * with the string representation of the second one.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public StartsWithExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {		
	// Check if the text starts with the given string.
	return new BooleanValue(valueA.getValueAsString().startsWith(valueB.getValueAsString()));
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.BOOLEAN;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.STARTS_WITH;
    }

    @Override
    public boolean canBeUsedToOptimizeQueries()
    {
	return expressionA.getType() == ExpressionType.REFERENCE && expressionB.getType() == ExpressionType.LITERAL;
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
}
