package net.explorercat.cql.expressions.visitors;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.binary.BinaryExpression;
import net.explorercat.cql.expressions.special.RangeExpression;
import net.explorercat.cql.expressions.stats.StatisticalFunctionExpression;
import net.explorercat.cql.expressions.unary.UnaryExpression;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.values.ReferenceExpression;
import net.explorercat.cql.expressions.values.VariableExpression;

/**
 * Interface for a visitor that collects information from the nodes of an
 * expression tree. The information retrieved is stored in the visitor object,
 * that can be queried after visiting the tree.
 * 
 * We assume the expression nodes propagate the call to their children before
 * applying the visit method to themselves (post-order execution).
 * 
 * This interface will need a method for each kind of expression.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public interface ExpressionVisitor
{
    public void visit(BinaryExpression expression);

    public void visit(UnaryExpression expression);

    public void visit(StatisticalFunctionExpression expression);

    public void visit(RangeExpression expression);

    public void visit(LiteralExpression expression);

    public void visit(ReferenceExpression expression);

    public void visit(VariableExpression expression);
}
