package net.explorercat.cql.expressions.binary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;

/**
 * Checks if the first operand (regular expression) matches the second one. A
 * recovery expression can be provided to recover from evaluation errors.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class MatchesStringExpression extends BinaryExpression implements Expression
{
    /**
     * Creates a matches string expression (a matches b?). The first operand
     * will be treated as a regular expression and the second one as a string.
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public MatchesStringExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {	
	Pattern regularExpression = Pattern.compile(valueA.getValueAsString());
	Matcher matcher = regularExpression.matcher(valueB.getValueAsString());
	return new BooleanValue(matcher.matches());
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.BOOLEAN;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.MATCHES_STRING;
    }
}
