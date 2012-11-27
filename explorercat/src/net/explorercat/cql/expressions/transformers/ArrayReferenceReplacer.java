package net.explorercat.cql.expressions.transformers;

import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.binary.BinaryExpression;
import net.explorercat.cql.expressions.special.RangeExpression;
import net.explorercat.cql.expressions.stats.StatisticalFunctionExpression;
import net.explorercat.cql.expressions.unary.UnaryExpression;
import net.explorercat.cql.expressions.values.LiteralExpression;
import net.explorercat.cql.expressions.values.ReferenceExpression;
import net.explorercat.cql.expressions.values.VariableExpression;
import net.explorercat.cql.expressions.visitors.ArrayReferenceCollector;
import net.explorercat.data.PropertyDictionary;

/**
 * Transformer that replaces logical expressions containing array references or
 * isolated (nodes) array references with the expression given at building time.
 * 
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public class ArrayReferenceReplacer extends ExpressionTransformerBase implements ExpressionTransformer
{
    private PropertyDictionary propertyDictionary;
    private Expression substituteExpression;

    /**
     * Creates an expression transformer that will replace any logical
     * expression that has an array reference with the given expression.
     * 
     * @param propertyDictionary Dictionary that will be used to check the types
     *        of the referenced properties.
     * @param substituteExpression Expression that will be used to replace the
     *        expression containing the array reference.
     */

    public ArrayReferenceReplacer(PropertyDictionary propertyDictionary, Expression substituteExpression)
    {
	this.propertyDictionary = propertyDictionary;
	this.substituteExpression = substituteExpression;
    }

    @Override
    public Expression transform(BinaryExpression expression)
    {
	return performTransformation(expression);
    }

    @Override
    public Expression transform(UnaryExpression expression)
    {
	return performTransformation(expression);
    }

    @Override
    public Expression transform(StatisticalFunctionExpression expression)
    {
	return performTransformation(expression);
    }

    @Override
    public Expression transform(RangeExpression expression)
    {
	return performTransformation(expression);
    }

    @Override
    public Expression transform(LiteralExpression expression)
    {
	return performTransformation(expression);
    }

    @Override
    public Expression transform(ReferenceExpression expression)
    {
	return performTransformation(expression);
    }

    @Override
    public Expression transform(VariableExpression expression)
    {
	return performTransformation(expression);
    }

    /**
     * Auxiliary method that implements the transformation. The rest of methods
     * delegate in this one. Notice the transformation will flow from the
     * bottom-up of the tree (we start transforming from the leaves).
     * 
     * @param expression Expression to be transformed
     * @return The transformed expression (can be the same expression passed as
     *         parameter).
     */

    private Expression performTransformation(Expression expression)
    {
	List<Expression> childExpressions = expression.getChildExpressions();

	for(Expression childExpression : childExpressions)
	{
	    ArrayReferenceCollector referenceCollector = new ArrayReferenceCollector(propertyDictionary);
	    childExpression.acceptVisitor(referenceCollector);
	    
	    // Check if we have found at least one array reference.
	    if(referenceCollector.getCollectedReferenceExpressions().size() > 0)
		return substituteExpression;
	}	

	return expression;
    }
}
