package net.explorercat.cql.expressions.binary;

import java.util.Collections;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * If the first expression is a string it behaves like MatchesString. However,
 * if the first expression is an array, it will check if it contains the
 * elements defined by the second expression
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 10 Mar 2011
 */

public class ContainsExpression extends BinaryExpression implements Expression
{   
    /**
     * Creates a contains expression (a contains b?). If the first expression is
     * a string it behaves like MatchesString. However, if the first expression
     * is an array, it will check if it contains the elements defined by the
     * second expression
     * 
     * @param errorRecoveryExpression Expression to be used as recovery for
     *        errors (null if not needed).
     */

    public ContainsExpression(Expression expressionA, Expression expressionB, Expression errorRecoveryExpression)
    {
	super(expressionA, expressionB, errorRecoveryExpression);
    }

    /**
     * We have to override this method since we treat arrays in a different way
     * (this is an array operator).
     */

    @Override
    public DataValue calculateExpressionValue(QueryableDataEntity dataEntity) throws ExpressionEvaluationException
    {	
	try
	{
	    DataValue valueA = expressionA.calculateExpressionValue(dataEntity);
	    DataValue valueB = expressionB.calculateExpressionValue(dataEntity);	    

	    // String case : behaves like Matches.
	    if(valueA.getType() == DataType.STRING)
		return new BooleanValue(valueA.getValueAsString().contains(valueB.getValueAsString()));

	    // Array string case : behaves like Matches for any string in the array.
	    else if(valueA.getType() == DataType.ARRAY && valueB.getType() == DataType.STRING)
	    {
		List<DataValue> containerValues = valueA.getValueAsArray();
		List<DataValue> searchValues = valueB.getValueAsArray();

		for(DataValue currentSearchValue : searchValues)
		    for(DataValue currentContainerValue : containerValues)
		    {
			if(currentContainerValue.getValueAsString().contains(currentSearchValue.getValueAsString()))
			    return new BooleanValue(true);
		    }

		return new BooleanValue(false);
	    }

	    // If not we assume that we are working with non-string arrays (notice it will tolerate scalars).
	    else
	    {
		List<DataValue> containerValues = valueA.getValueAsArray();
		List<DataValue> searchValues = valueB.getValueAsArray();

		for(DataValue currentValue : searchValues)
		    if(Collections.binarySearch(containerValues, currentValue) < 0)
			return new BooleanValue(false);

		return new BooleanValue(true);
	    }
	}
	catch(ExplorerCatCheckedException e)
	{
	    if(errorRecoveryExpression != null)
		return errorRecoveryExpression.calculateExpressionValue(dataEntity);
	    else
		throw new ExpressionEvaluationException(e);
	}
    }

    @Override
    protected DataValue performOperation(DataValue valueA, DataValue valueB) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	throw new ExpressionEvaluationException("ContainsExpression.performOperation: "
						+ " this method should never be called");
    }

    @Override
    public DataType inferResultType()
    {
	return DataType.BOOLEAN;
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.CONTAINS;
    }
}
