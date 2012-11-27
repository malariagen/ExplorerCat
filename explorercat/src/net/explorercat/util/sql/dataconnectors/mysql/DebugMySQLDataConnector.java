package net.explorercat.util.sql.dataconnectors.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.explorercat.util.sql.dataconnectors.SQLDataConnector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A MySQL data connector to log performance and execution. Logs execution time,
 * queries and the MySQL explain output (if configured). To be used on
 * development. Notice this implementation is not compatible with row streaming.
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public class DebugMySQLDataConnector extends ConfigurableMySQLDataConnector implements SQLDataConnector
{
    // Logging
    private static Log log = LogFactory.getLog(DebugMySQLDataConnector.class);

    // If true we check the MySQL output for explain
    private static boolean explainModeActivated = true;

    @Override
    public ResultSet executeQuery(String sqlQuery) throws SQLException
    {
	if(log.isWarnEnabled())
	    log.warn("Executing query without parameter binding, SQL injection risk");

	try
	{
	    initConnection();

	    // Measure the execution time of the query.
	    long startTime = System.currentTimeMillis();
	    ResultSet queryResult = statement.executeQuery(sqlQuery);
	    long endTime = System.currentTimeMillis();

	    // Log the time, query and explanation.
	    String logOutput = "\n\tQuery: " + sqlQuery + "\n\tTime: " + (endTime - startTime) + " milliseconds.";

	    if(explainModeActivated && log.isDebugEnabled())
		logOutput += getExplainOutput(sqlQuery);

	    if(log.isDebugEnabled())
		log.debug(logOutput);

	    return queryResult;
	}
	catch(SQLException e)
	{
	    if(log.isErrorEnabled())
		log.error("Error accessing the DB: " + e.getMessage());

	    closeConnection();
	    throw e;
	}
    }

    @Override
    public int executeUpdate(String sqlUpdate) throws SQLException
    {
	if(log.isWarnEnabled())
	    log.warn("Executing update without parameter binding, SQL injection risk");

	try
	{
	    initConnection();

	    // Measure the execution time of the query.
	    long startTime = System.currentTimeMillis();
	    int affectedRows = statement.executeUpdate(sqlUpdate);
	    long endTime = System.currentTimeMillis();

	    // Log the time and query.
	    if(log.isDebugEnabled())
		log.debug("\n\tUpdate: " + sqlUpdate + "\n\tTime: " + (endTime - startTime)
			  + " milliseconds.\n\tAffected Rows: " + affectedRows);

	    return affectedRows;
	}
	catch(SQLException e)
	{
	    if(log.isErrorEnabled())
		log.error("Error accessing the DB: " + e.getMessage());

	    closeConnection();
	    throw e;
	}
    }

    @Override
    public void createPreparedStatement(String sqlStatement) throws SQLException
    {
	initConnection();
	preparedStatementQuery = sqlStatement;

	long startTime = System.currentTimeMillis();
	preparedStatement = connection.prepareStatement(sqlStatement);
	long endTime = System.currentTimeMillis();

	// Log the time and query.				
	if(log.isDebugEnabled())
	    log.debug("\n\tPrepared Statement Created: " + sqlStatement + "\n\tTime: " + (endTime - startTime)
		      + " milliseconds.");
    }

    @Override
    public ResultSet executePreparedStatementAsQuery(List<Object> parameters) throws SQLException
    {
	setPreparedStatementParameters(parameters);

	// Measure the execution time of the query.
	long startTime = System.currentTimeMillis();
	ResultSet result = preparedStatement.executeQuery();
	long endTime = System.currentTimeMillis();

	String logOutput = "\n\tPrepared Statement executed: " + preparedStatementQuery + "\n\tParameters: "
			   + (parameters != null ? parameters.toString() : "null") + "\n\tTime: "
			   + (endTime - startTime) + " milliseconds.";

	// To get the explain output for the prepared statement we have to translate
	// the parametrised query into a real one. No security implications
	// due to SQL injection issues since we already have executed the prepared
	// statement without failure.

	if(explainModeActivated && log.isDebugEnabled())
	    logOutput += getExplainOutput(translatePreparedStatementIntoSQLString(parameters));

	// Log the time and query.		
	if(log.isDebugEnabled())
	    log.debug(logOutput);

	return result;
    }

    @Override
    public int executePreparedStatementAsUpdate(List<Object> parameters) throws SQLException
    {
	setPreparedStatementParameters(parameters);

	// Measure the execution time of the query.
	long startTime = System.currentTimeMillis();
	int affectedRows = preparedStatement.executeUpdate();
	long endTime = System.currentTimeMillis();

	// Log the time and query.		
	if(log.isDebugEnabled())
	    log.debug("\n\tPrepared Statement executed: " + preparedStatementQuery + "\n\tParameters: "
		      + (parameters != null ? parameters.toString() : "null") + "\n\tTime: " + (endTime - startTime)
		      + " milliseconds.");

	return affectedRows;
    }

    @Override
    public void setRowStreamingMode(boolean active)
    {
	if(active)
	    log.error("Error, the streaming mode is not compatible with the DebugMySQLDataProvider class");
    }

    /**
     * Auxiliary method that collects the query execution explanation by MySQL.
     * 
     * @param sqlQuery The query that will be analysed.
     * @return A string containing the MySQL output for the explain clausule.
     * @throws SQLException
     */

    private String getExplainOutput(String sqlQuery) throws SQLException
    {
	Connection explainConnection = createTemporalConnection();
	Statement explainStatement = explainConnection.createStatement();
	ResultSet explainResult = explainStatement.executeQuery("explain " + sqlQuery);

	StringBuilder explainText = new StringBuilder();

	while(explainResult.next())
	{
	    explainText.append("\n\tExplain for table " + explainResult.getString("table"));
	    explainText.append(" = type: " + explainResult.getString("type"));
	    explainText.append(", possible_keys: " + explainResult.getString("possible_keys"));
	    explainText.append(", key: " + explainResult.getString("key"));
	    explainText.append(", key_len: " + explainResult.getString("key_len"));
	    explainText.append(", ref: " + explainResult.getString("ref"));
	    explainText.append(", rows: " + explainResult.getString("rows"));
	}

	explainConnection.close();
	return explainText.toString();
    }

    /**
     * Auxiliary method that converts a list of parameters into a string
     * representation.
     * 
     * @param parameters The list of parameters to be converted.
     * @return A string representation of the parameters.
     */

    private String convertParametersToString(List<Object> parameters)
    {
	StringBuilder parametersString = new StringBuilder();
	for(Object p : parameters)
	    parametersString.append("[" + p.toString() + "] ");

	return parametersString.toString();
    }

    /**
     * Activates/deactivates the explanation mode (we ask MySQL about the
     * explanation of each query).
     * 
     * @param isActivated True/false to activate/deactivate.
     * @return
     */

    public static void setExplainMode(boolean explainModeActivated)
    {
	DebugMySQLDataConnector.explainModeActivated = explainModeActivated;
    }
}
