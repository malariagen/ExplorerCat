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
 * Represents a division of numbers (/). Notice any arithmetic operation will
 * render a real value as a result. A recovery expression can be provided to
 * recover from evaluation errors (like division by zero errors).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class DivisionExpression extends BinaryExpression implements Expression
{
    /**
     * Builds a division expression (a/b)
     * 
     * @param expressionA Numerator
     * @param expressionB Denominator
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public DivisionExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	if(valueB.getValueAsReal() == 0.0F)
	    throw new ExpressionEvaluationException("Error evaluating division: division by zero");
	else
	    return new RealValue(valueA.getValueAsReal() / valueB.getValueAsReal());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.REAL;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.DIVISION;
    }
}
