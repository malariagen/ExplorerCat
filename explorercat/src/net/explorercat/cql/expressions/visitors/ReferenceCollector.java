package net.explorercat.cql.expressions.visitors;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.expressions.values.ReferenceExpression;

/**
 * Visits all the nodes of an expression tree collecting any referenced
 * property.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public class ReferenceCollector extends ExpressionVisitorBase implements ExpressionVisitor
{
    protected List<ReferenceExpression> referenceExpressions;

    public ReferenceCollector()
    {
	this.referenceExpressions = new ArrayList<ReferenceExpression>();
    }

    @Override
    public void visit(ReferenceExpression expression)
    {
	this.referenceExpressions.add(expression);
    }

    /**
     * Gets the list of all the reference expressions collected after visiting
     * each node of the expression tree.
     * 
     * @return A list containing all the expressions that references a property
     *         collected from the expression tree.
     */

    public List<ReferenceExpression> getCollectedReferenceExpressions()
    {
	return this.referenceExpressions;
    }
}
