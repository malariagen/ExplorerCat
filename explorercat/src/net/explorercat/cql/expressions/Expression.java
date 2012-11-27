package net.explorercat.cql.expressions;

import java.util.List;

import net.explorercat.cql.expressions.transformers.ExpressionTransformer;
import net.explorercat.cql.expressions.visitors.ExpressionVisitor;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.QueryableDataEntity;

/**
 * Interface that represents expressions that generate data values after
 * evaluation. Expressions are combined in a expression tree that is evaluated
 * starting from the root.
 * 
 * Error recovery expressions can be provided for any expression subtree, these
 * expressions will be used in case of finding an error at any level of the
 * subtree. (Notice that contained subtrees can receive more specific recovery
 * expressions that will shadow any one coming from a parent expression).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public interface Expression
{
    /**
     * Returns the result of the expression calculation as a data value using
     * the given data entity as input. (Notice some expressions won't need to
     * access a data entity to be evaluated so a null value is sometimes
     * appropriated).
     * 
     * @param dataEntity The data entity that will be used to evaluate the
     *        expression.
     * @return The resulting data value after evaluating the expression. This
     *         method is not returning always a copy of the value so client code
     *         should not modify the object.
     * @throws ExpressionEvaluationException If we find an evaluation problem
     *         like divided by zero.
     */

    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException;

    /**
     * Infers the type of the expression (boolean, string, real or integer).
     * 
     * @return The (inferred) type that the expression will have once evaluated.
     */

    public DataType inferResultType();

    /**
     * Checks if the expression subtree for which this expression is root
     * contains a property reference (the property will be queried from a data
     * entity during evaluation). This will guide the simplification process
     * (expressions containing a reference can't be simplified, only in
     * exceptional cases of logical expressions).
     */

    public boolean containsReference();

    /**
     * Tries to simplify the expression subtree for which this expression is
     * root. We'll try to resolve any expressions that is not referring to an
     * entity property. However this kind of expressions can be simplified too
     * for some trivial logical cases.
     * 
     * @return A simplified version of the expression if any simplification was
     *         possible. Note that the original expression is NOT modified by
     *         this method.
     */

    public Expression simplify() throws ExpressionEvaluationException;

    /**
     * Gets the type of the expression (AND, OR, ADDITION, etc.)
     * 
     * @return The element of the ExpressionType enum that represents the type
     *         of the expression.
     */

    public ExpressionType getType();

    /**
     * Returns the error recovery expression associated with the expression.
     * 
     * @return The expression that will be evaluated in case of error or null if
     *         no error recovery expression has been defined.
     */

    public Expression getErrorRecoveryExpression();

    /**
     * Gets a list of dominant expressions of the given type for the current
     * expression subtree (for which this expression is root). A expression is
     * dominant if it must be true for all the entities selected by the
     * condition, this implies that is connected by AND operators to the root of
     * the expression tree.
     * 
     * @param type The type of dominant expression we are looking for.
     * @return A list of all the dominant expressions found in the condition.
     */

    public List<Expression> getDominantExpressionOfType(ExpressionType type);

    /**
     * Gets a string that identifies the expression uniquely. Any
     * input/referenced selection and their queries are included as a part of
     * the string. Notice this method does NOT have to return the same result as
     * the toString method.
     * 
     * @return A string that identifies uniquely the expression.
     */

    public String getStringKey();

    /**
     * Gets the expressions contained as operands in this expression. Basically
     * it retrieves the children of the expression. Notice we are not including
     * error recovery expressions in the returned list.
     * 
     * @return The list of child expressions that act as operands in the current
     *         expression. The children are returned in the same order they have
     *         been arranged in the parent expression. This list does not
     *         include error recovery expressions.
     */

    public List<Expression> getChildExpressions();

    /**
     * Checks if the expression can be used to perform query optimisations.
     * Usually a selection query can be optimised if it only uses references and
     * constant values. In addition, most of the times, the input selection has
     * to be sorted by the properties that are referenced by the expression.
     * 
     * @return True if this expression can be potentially used to optimise
     *         queries.
     */

    public boolean canBeUsedToOptimizeQueries();

    /**
     * Returns a copy of the expression tree that has this expression as its
     * root.
     * 
     * @return An independent copy of the expression tree.
     */

    public Expression cloneExpressionTree() throws ExpressionEvaluationException;

    /**
     * Applies a transformation to the expression using a transformer object and
     * returns the transformed node. The transformation will be applied to the
     * current node after propagating the call to the children (post-order
     * execution).
     * 
     * @param transformer The object in charge of transforming the expression.
     * @return The transformed expression.
     */

    public Expression applyTransformation(ExpressionTransformer transformer);

    /**
     * Accepts a visitor object that will collect information from the
     * expression tree nodes. The visitor will be accepted after propagating the
     * call to the children (post-order execution).
     * 
     * @param visitor Object in charge of visiting the node (collecting
     *        information).
     */

    public void acceptVisitor(ExpressionVisitor visitor);

}
