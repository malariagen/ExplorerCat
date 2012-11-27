package net.explorercat.cql.expressions.stats;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionType;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.RealValue;

/**
 * Statistical function: Sum.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date July 2010
 */

public class SumExpression extends StatisticalFunctionExpression implements Expression
{
    /**
     * Builds the statistical function for an entity property relying on an
     * selection that will provide the data.
     */

    public SumExpression(String attributeName, Expression variableExpression, Selection sourceSelection,
			 Expression errorRecoveryExpression)
    {
	super(attributeName, variableExpression, sourceSelection, errorRecoveryExpression);
    }

    @Override
    public DataValue calculateFunction() throws StatsCalculationException
    {
	if(variableExpression == null)
	    return new RealValue(sourceSelection.getStatsForProperty(propertyName, StatsType.SUM));
	else
	    return new RealValue(sourceSelection.getStatsForVariable(propertyName, variableExpression,
									 StatsType.SUM));
    }

    @Override
    public ExpressionType getType()
    {
	return ExpressionType.SUM;
    }
}
