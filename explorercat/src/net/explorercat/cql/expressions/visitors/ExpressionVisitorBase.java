package net.explorercat.cql.expressions.visitors;

import net.explorercat.cql.expressions.binary.BinaryExpression;
import net.explorercat.cql.expressions.special.RangeExpression;
import net.explorercat.cql.expressions.stats.StatisticalFunctionExpression;
import net.explorercat.cql.expressions.unary.UnaryExpression;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.values.ReferenceExpression;
import net.explorercat.cql.expressions.values.VariableExpression;

/**
 * Base class for expression visitors that implements the default behaviour
 * (does nothing).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public abstract class ExpressionVisitorBase implements ExpressionVisitor
{
    @Override
    public void visit(BinaryExpression expression)
    {
    }

    @Override
    public void visit(UnaryExpression expression)
    {
    }

    @Override
    public void visit(StatisticalFunctionExpression expression)
    {
    }

    @Override
    public void visit(RangeExpression expression)
    {
    }

    @Override
    public void visit(LiteralExpression expression)
    {
    }

    @Override
    public void visit(ReferenceExpression expression)
    {
    }

    @Override
    public void visit(VariableExpression expression)
    {
    }
}
