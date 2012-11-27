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
 * Interface for visitor-like objects that transform the nodes of an expression
 * tree. The transformation can delete nodes, change selection sources, etc. The
 * transformed expression is returned by the transform method.
 * 
 * We assume the expression nodes propagate the call to their children before
 * returning the transformed version of themselves (post-order execution).
 * 
 * This interface will need a method for each kind of expression.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public interface ExpressionTransformer
{
    public Expression transform(BinaryExpression expression);

    public Expression transform(UnaryExpression expression);

    public Expression transform(StatisticalFunctionExpression expression);

    public Expression transform(RangeExpression expression);

    public Expression transform(LiteralExpression expression);

    public Expression transform(ReferenceExpression expression);

    public Expression transform(VariableExpression expression);
}
