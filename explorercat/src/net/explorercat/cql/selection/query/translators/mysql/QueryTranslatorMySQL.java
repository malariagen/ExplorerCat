package net.explorercat.cql.selection.query.translators.mysql;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.selection.limiters.Limiter;
import net.explorercat.cql.selection.limiters.LimiterType;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.query.translators.QueryTranslator;
import net.explorercat.cql.selection.query.translators.StorageLayerQuery;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.dataaccess.mysql.QueryableDataEntityDAOMySQL;
import net.explorercat.util.exceptions.TranslationException;

/**
 * Implementation of a query translator for the MySQL database. Translates
 * queries into a representation that can be executed against a MySQL database.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 3 Mar 2011
 */

public class QueryTranslatorMySQL implements QueryTranslator
{
    private ExpressionTranslatorMySQL expressionTranslator;
    private LimiterTranslatorMySQL limiterTranslator;
    private EntitySorterTranslatorMySQL sorterTranslator;
    private String entityDBTableName;
    private QueryableDataEntityDAOMySQL entityDAO;

    /**
     * Creates a new query translator for MySQL databases. Internal translators
     * are initialised at building time so it is a good idea to reuse this
     * object with all the queries related with the entity.
     * 
     * @param entityDAO The DAO that is in charge of providing data for the
     *        entity we are querying.
     */

    public QueryTranslatorMySQL(QueryableDataEntityDAOMySQL entityDAO)
    {
	this.expressionTranslator = new ExpressionTranslatorMySQL();
	this.limiterTranslator = new LimiterTranslatorMySQL();
	this.sorterTranslator = new EntitySorterTranslatorMySQL();
	this.entityDAO = entityDAO;
	this.entityDBTableName = entityDAO.getEntityTableName();
    }

    @Override
    public StorageLayerQuery translateQuery(Query query) throws TranslationException
    {
	// Simplify variables and condition before generating the SQL.
	query.simplify();

	Expression conditionExpression = query.getCondition().getExpression();
	Limiter limiter = query.getResultingSelectionLimiter();
	EntitySorter sorter = query.getResultingSelectionSorter();

	// Translate all the variables to SQL expressions. 
	// We'll add them to the SELECT as result columns.
	StringBuilder variableSelectString = new StringBuilder(128);

	if(query.hasVariables())
	{
	    Iterator<Entry<String, Expression>> variableIterator = query.getVariableIterator();

	    while(variableIterator.hasNext())
	    {
		Entry<String, Expression> variable = variableIterator.next();
		String varName = variable.getKey();
		Expression varExpression = variable.getValue();

		variableSelectString.append(", ").append(expressionTranslator.translateIntoPreparedStatement(varExpression));
		variableSelectString.append(" AS '").append(varName).append("'");
	    }
	}

	// Create the SQL prepared statement that will get the entities from the DB.
	String sqlCode = "SELECT * " + variableSelectString.toString() + " FROM " + entityDBTableName + " WHERE "
			 + expressionTranslator.translateIntoPreparedStatement(conditionExpression) + " ";

	// Add sorting restrictions.
	if(sorter != null)
	    sqlCode += sorterTranslator.translateIntoMySQLCode(sorter);

	// Check if we can translate the limiter to SQL.
	if(limiter != null && limiter.getType() != LimiterType.BOTTOM_LIMITER)
	    sqlCode = "SELECT * FROM (" + sqlCode + ") AS automaticNestedQuery "
		      + limiterTranslator.translateIntoMySQLCode(limiter);

	// Get the objects to be used with the translated prepared statement.
	List<Object> preparedStatementParameters = expressionTranslator.translateParametersForPreparedStatement(conditionExpression);

	// Pack everything into a storage layer query.
	return new StorageLayerQueryMySQL(sqlCode, preparedStatementParameters, query, entityDAO);
    }
}
