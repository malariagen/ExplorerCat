package net.explorercat.cql.expressions.binary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.RealValue;

/**
 * Represents a multiplication of numbers (*). Note this operation renders a
 * real data value.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class MultiplicationExpression extends BinaryExpression implements Expression
{
    /**
     * Creates a multiplication expression (a * b). A recovery expression can be
     * provided to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public MultiplicationExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	// A result from any numeric operation will be render as a real.
	return new RealValue(valueA.getValueAsReal() * valueB.getValueAsReal());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.REAL;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.MULTIPLICATION;
    }
}
