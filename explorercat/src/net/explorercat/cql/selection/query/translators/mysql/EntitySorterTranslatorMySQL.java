package net.explorercat.cql.selection.query.translators.mysql;

import java.sql.SQLException;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.sql.dataconnectors.mysql.ProductionMySQLDataConnector;

/**
 * MySQL translator for entity sorters. Translate sorters into MySQL code that
 * can be appended to MySQL queries and executed against a MySQL DB.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Mar 2011
 */

public class EntitySorterTranslatorMySQL
{
    private ExpressionTranslatorMySQL expressionTranslator;
    private SinglePropertySorterTranslator singleTranslator;

    /**
     * Builds a MySQL translator for entity sorters. Internal translators are
     * initialised at building time so it is a good idea to reuse this object
     * instead of creating a new one for each translation.
     */

    public EntitySorterTranslatorMySQL()
    {
	this.expressionTranslator = new ExpressionTranslatorMySQL();
	this.singleTranslator = new SinglePropertySorterTranslator();
    }

    /**
     * Translates the given sorter into MySQL code that can be executed against
     * a MySQL DB.
     * 
     * @param sorter The sorter to be translated.
     * @return A string represented the MySQL code for the prepared statement.
     * @throws TranslationException If there is an error performing the
     *         translation.
     */

    public String translateIntoMySQLCode(EntitySorter sorter) throws TranslationException
    {
	switch(sorter.getSorterType())
	{
	    case SINGLE_PROPERTY_SORTER:
		return this.singleTranslator.translateSorter(sorter);
	    default:
		throw new TranslationException("Unknown type of sorter: " + sorter.getSorterType());
	}
    }

    /**
     * Internal interface that represents a MySQL translator for entity sorters
     * into MySQL code. Different implementations will be provided based on the
     * type of sorter to be translated.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 4 Mar 2011
     */

    private static interface SorterCodeTranslatorMySQL
    {
	/**
	 * Translates the given sorter into equivalent MySQL code.
	 */

	public String translateSorter(EntitySorter sorter) throws TranslationException;
    }

    /**
     * Implementation for random limiters
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 4 Mar 2011
     */

    private class SinglePropertySorterTranslator implements SorterCodeTranslatorMySQL
    {
	@Override
	public String translateSorter(EntitySorter sorter) throws TranslationException
	{
	    try
	    {
		String propertyName = sorter.getSortingProperties().get(0);
		Expression varDefinition = sorter.getSortingVariableDefinitions().get(0);

		// We have to manually replace the prepared statement question marks 
		// (there is no PS support for ORDER BY in MySQL)
		// This is very ugly but we save hundreds of lines of code.
		// (Notice we are using the expression translator of the outer class).
		String expressionPreparedStatement = expressionTranslator.translateIntoPreparedStatement(varDefinition);
		List<Object> expressionParameters = expressionTranslator.translateParametersForPreparedStatement(varDefinition);

		// We use the translation capabilities of a connector object.
		ProductionMySQLDataConnector connector = new ProductionMySQLDataConnector();
		connector.createPreparedStatement(expressionPreparedStatement);
		String expressionSQL = connector.translatePreparedStatementIntoSQLString(expressionParameters);

		return ("ORDER BY " + (varDefinition == null ? propertyName : expressionSQL) + (!sorter.isUsingAscendingOrder()
			? " DESC" : ""));
	    }
	    catch(SQLException e)
	    {
		throw new TranslationException("Error translating entity sorter into MySQL code", e);
	    }
	}
    }

}
