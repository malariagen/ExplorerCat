package net.explorercat.cql.expressions.transformers;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.binary.BinaryExpression;
import net.explorercat.cql.expressions.special.RangeExpression;
import net.explorercat.cql.expressions.stats.StatisticalFunctionExpression;
import net.explorercat.cql.expressions.unary.UnaryExpression;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.values.ReferenceExpression;
import net.explorercat.cql.expressions.values.VariableExpression;

/**
 * Base class that implements the default behaviour for transformers (return the
 * same expression they receive as parameter).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public abstract class ExpressionTransformerBase implements ExpressionTransformer
{
    @Override
    public Expression transform(BinaryExpression expression)
    {
	return expression;
    }

    @Override
    public Expression transform(UnaryExpression expression)
    {
	return expression;
    }

    @Override
    public Expression transform(StatisticalFunctionExpression expression)
    {
	return expression;
    }

    @Override
    public Expression transform(RangeExpression expression)
    {
	return expression;
    }

    @Override
    public Expression transform(LiteralExpression expression)
    {
	return expression;
    }

    @Override
    public Expression transform(ReferenceExpression expression)
    {
	return expression;
    }

    @Override
    public Expression transform(VariableExpression expression)
    {
	return expression;
    }
}
