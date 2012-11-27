package net.explorercat.cql.selection.query;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.QueryableDataEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents the condition of a query. It will be evaluated when selecting data
 * entities.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class QueryCondition
{
    // Logging
    private static Log log = LogFactory.getLog(QueryCondition.class);

    // Tree of expressions that codifies the condition.
    private Expression conditionExpression;

    // True if the condition has been simplified.
    private boolean isSimplified;

    // Unique identifier (key) for the condition.
    private String stringKey = null;

    /**
     * Builds a new condition from the given expression.
     * 
     * @param conditionExpression The expression that will be evaluated to
     *        select data entities.
     */

    public QueryCondition(Expression conditionExpression)
    {
	this.conditionExpression = conditionExpression;
	this.isSimplified = false;
    }

    /**
     * Checks if the condition is a constant value or references entity
     * properties.
     * 
     * @return True if the condition is not referencing any entity property.
     */

    public boolean isConstantValue()
    {
	return !conditionExpression.containsReference();
    }

    /**
     * Evaluates the condition for a given data entity.
     * 
     * @param dataEntity The entity that will be used to evaluate the condition.
     * @return True if the condition is true for the given entity, false
     *         otherwise.
     */

    public boolean evaluateCondition(QueryableDataEntity dataEntity) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	return conditionExpression.calculateExpressionValue(dataEntity).getValueAsBoolean();
    }

    /**
     * Tries to simplify the contained expression (if it has not been simplified
     * yet).
     * 
     * @throws IncompatibleTypeException If there is a problem trying to access
     *         a value from the condition.
     * @throws PropertyAccessException If there is a problem trying to access a
     *         property (it does not exist).
     * @throws ExpressionEvaluationException If there is a problem trying to
     *         evaluate the condition.
     */

    public void simplify() throws IncompatibleTypeException, PropertyAccessException, ExpressionEvaluationException
    {
	if(isSimplified == false)
	{
	    isSimplified = true;

	    if(log.isDebugEnabled())
		log.debug("Simplifying condition expression: " + conditionExpression.toString());

	    conditionExpression = conditionExpression.simplify();

	    if(log.isDebugEnabled())
		log.debug("Condition expression simplified: " + conditionExpression.toString());
	}
    }

    /**
     * Gets the expression that defines the condition.
     */

    public Expression getExpression()
    {
	return conditionExpression;
    }

    @Override
    public String toString()
    {
	return conditionExpression.toString();
    }

    /**
     * Gets a string that identifies the condition uniquely. Any referenced
     * selection and their queries are included. Notice this method does NOT
     * return the same result as the toString method.
     * 
     * @return A string that identifies uniquely the condition.
     */

    public String getStringKey()
    {
	if(stringKey == null)
	    stringKey = conditionExpression.getStringKey();

	return stringKey;
    }
}
