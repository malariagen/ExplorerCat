package net.explorercat.cql.expressions.unary;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.RealValue;
import net.explorercat.cql.types.DataType;

/**
 * Represents the unary minus operation (-). Note that this operation renders a
 * real data value. A recovery expression can be provided to recover from
 * evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class MinusExpression extends UnaryExpression implements Expression
{
    /**
     * Creates a minus expression (-expression). A recovery expression can be
     * provided to recover when an evaluation error occurs.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public MinusExpression(Expression expression, Expression recoveryValue)
    {
	super(expression, recoveryValue);
    }

    @Override
    protected DataValue performOperation(DataValue value) throws IncompatibleTypeException, PropertyAccessException,
	ExpressionEvaluationException
    {
	return new RealValue(value.getValueAsReal() * -1);
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.REAL;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.MINUS;
    }
}
