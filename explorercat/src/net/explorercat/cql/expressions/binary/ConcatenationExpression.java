package net.explorercat.cql.expressions.binary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.StringValue;
import net.explorercat.cql.types.DataType;

/**
 * Represents a concatenation of expressions. Notice that the expressions will
 * be converted to strings in order to perform the operation. A recovery
 * expression can be provided to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class ConcatenationExpression extends BinaryExpression implements Expression
{
    /**
     * Creates a concatenation expression for strings (ab). A recovery
     * expression can be provided to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public ConcatenationExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	// A result from a concatenation will be a string.
	return new StringValue(valueA.getValueAsString() + valueB.getValueAsString());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.STRING;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.CONCATENATION;
    }
}
