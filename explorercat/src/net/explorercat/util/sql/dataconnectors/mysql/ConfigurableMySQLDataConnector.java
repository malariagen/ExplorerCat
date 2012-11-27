package net.explorercat.util.sql.dataconnectors.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.util.sql.DataSourceLookup;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;

/**
 * A base class for data connector implementations that can be configured with a
 * specific data source URI.
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public abstract class ConfigurableMySQLDataConnector implements SQLDataConnector
{
    // Logging
    private static Log log = LogFactory.getLog(ConfigurableMySQLDataConnector.class);

    // Represents the database we are connecting to.
    private static final String DATA_SOURCE_URI;

    static
    {
	DATA_SOURCE_URI = ApplicationPropertyLookup.getInstance().getDataSourceURI();
    }

    // JDBC objects.
    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected String preparedStatementQuery;

    // Flag for streaming (fetching small blocks of rows to minimise the memory footprint)
    protected boolean streamingModeOn;

    /**
     * Builds a new data connector. The connector does not connect to the DB
     * till an execute method is called (query or update).
     */

    public ConfigurableMySQLDataConnector()
    {
	connection = null;
	statement = null;
	preparedStatement = null;
	preparedStatementQuery = null;
	streamingModeOn = false;
    }

    /**
     * Auxiliary method that initialises the DB connection and checks for
     * unclosed connections trying to be re-used.
     * 
     * @throws SQLException If the connector connection to the DB is still open.
     */

    protected void initConnection() throws SQLException
    {
	// Check for open connections.
	if(connection != null && !connection.isClosed())
	    throw new SQLException("ConfigurableMySQLDataProvider: Error, trying to reuse a DB connection that"
				   + "is still open. You need to call closeConnection() before reusing a connector.");

	connection = DataSourceLookup.getDataSource(DATA_SOURCE_URI).getConnection();
	statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

	// Check if we have to modify the statement in order to obtain a "streaming" result set.
	if(streamingModeOn)
	    statement.setFetchSize(Integer.MIN_VALUE);

	preparedStatement = null;
	preparedStatementQuery = null;
    }

    /**
     * Gets a temporal connection to the DB.
     */

    protected Connection createTemporalConnection() throws SQLException
    {
	return DataSourceLookup.getDataSource(DATA_SOURCE_URI).getConnection();
    }

    /**
     * Auxiliary method that sets the parameters of the prepared statement.
     * 
     * @param parameters The parameters to be set, in order.
     * @throws SQLException If the prepared statement was not configured or if
     *         there is an error setting the parameters.
     */

    protected void setPreparedStatementParameters(List<Object> parameters) throws SQLException
    {
	if(preparedStatement == null)
	    throw new SQLException("Error setting the prepared statement "
				   + "there is not prepared statement configured");

	int parameterIndex = 1;

	if(parameters != null)
	    for(Object p : parameters)
		setPreparedStatementParameter(p, parameterIndex++);
    }

    /**
     * Auxiliary method that sets a parameter in the prepared statement.
     * 
     * @param parameter The parameter that will be set.
     * @param parameterIndex The index (place) of the parameter in the prepared
     *        statement
     * @throws SQLException
     */

    protected void setPreparedStatementParameter(Object parameter, int parameterIndex) throws SQLException
    {
	int sqlType = infereSQLTypeFromParameter(parameter);

	if(parameter == null)
	    preparedStatement.setNull(parameterIndex, sqlType);

	else
	{
	    switch(sqlType)
	    {
		case java.sql.Types.INTEGER:
		    preparedStatement.setInt(parameterIndex, (Integer) parameter);
		    break;

		case java.sql.Types.VARCHAR:
		    preparedStatement.setString(parameterIndex, (String) parameter);
		    break;

		case java.sql.Types.BOOLEAN:
		    preparedStatement.setBoolean(parameterIndex, (Boolean) parameter);
		    break;

		case java.sql.Types.FLOAT:
		    preparedStatement.setFloat(parameterIndex, (Float) parameter);
		    break;

		case java.sql.Types.DOUBLE:
		    preparedStatement.setDouble(parameterIndex, (Double) parameter);
		    break;

		default:
		    throw new SQLException("setPreparedStatementParameter, type not supported for parameter "
					   + parameterIndex);
	    }
	}
    }

    /**
     * Auxiliary method that infers the SQL type of an object.
     * 
     * @param parameter The parameter whose sql type will be inferred.
     * @return An integer representing the java.sql.Types value for the
     *         parameter..
     */

    protected int infereSQLTypeFromParameter(Object parameter)
    {
	if(parameter instanceof Integer)
	    return java.sql.Types.INTEGER;

	else if(parameter instanceof String)
	    return java.sql.Types.VARCHAR;

	else if(parameter instanceof Boolean)
	    return java.sql.Types.BOOLEAN;

	else if(parameter instanceof Float)
	    return java.sql.Types.FLOAT;

	else if(parameter instanceof Double)
	    return java.sql.Types.DOUBLE;

	else if(parameter instanceof Character)
	    return java.sql.Types.CHAR;

	else
	    return java.sql.Types.JAVA_OBJECT;
    }

    /**
     * Auxiliary method that translates the current configured prepared
     * statement to a string SQL query for the given list of parameters.
     * 
     * @param parameters A list of objects representing the parameters of the
     *        query.
     * @return The string representation of the current configured prepare
     *         statement.
     * @throws SQLException If there is an error translating the prepared
     *         statement.
     */

    public String translatePreparedStatementIntoSQLString(List<Object> parameters) throws SQLException
    {
	StringBuilder realSQLQuery = new StringBuilder();
	int currentQueryStringIndex = 0;

	if(parameters == null || parameters.size() == 0)
	    realSQLQuery.append(preparedStatementQuery);
	else
	{
	    for(Object p : parameters)
	    {
		int currentPlaceholderIndex = preparedStatementQuery.indexOf('?', currentQueryStringIndex);
		String parameterAsString = (p != null ? p.toString() : "null");

		if(currentPlaceholderIndex == -1)
		    throw new SQLException("Error translating prepared statement into string query, "
					   + "mismatch in number of parameters");

		realSQLQuery.append(preparedStatementQuery.substring(currentQueryStringIndex, currentPlaceholderIndex));

		if(infereSQLTypeFromParameter(p) == java.sql.Types.VARCHAR)
		    realSQLQuery.append("'" + parameterAsString + "'");
		else
		    realSQLQuery.append(parameterAsString);

		currentQueryStringIndex = currentPlaceholderIndex + 1;
	    }

	    // Append the rest of the query (after all the question marks).
	    realSQLQuery.append(preparedStatementQuery.substring(currentQueryStringIndex));
	}

	return realSQLQuery.toString();
    }

    /**
     * Auxiliary method that checks if a prepared statement can be executed as a
     * translated regular query without safety concerns. If the prepared
     * statement is not safe to be translated then a SQLException is thrown.
     * 
     * @param queryToCheck The prepared statement query to be checked.
     * @param parameters A list of objects representing the parameter values for
     *        the statement.
     * @throws SQLException If the statement can not be safely translated into a
     *         regular SQL query.
     */

    protected void checkPreparedStatementSafety(List<Object> parameters) throws SQLException
    {
	if(log.isDebugEnabled())
	    log.debug("Checking safety of prepared statement: " + preparedStatementQuery);

	// To check if a prepared statement is safe to be executed as a regular translated query
	// we execute a minimal version of the prepared statement (as a sub-query that only gets 1 row 
	// and check if any SQL exception is raised.
	// Note we have to reset the connection after performing the check. 

	String sqlQueryToCheck = "SELECT * FROM (" + preparedStatementQuery + ") AS sqlQueryCheck LIMIT 1";

	// Save the prepared statement query.
	String savedPreparedStatementQuery = preparedStatementQuery;

	closeConnection();
	createPreparedStatement(sqlQueryToCheck);
	setPreparedStatementParameters(parameters);

	try
	{
	    preparedStatement.executeQuery();
	}
	catch(SQLException e)
	{
	    if(log.isDebugEnabled())
		log.debug("Prepared statement is not safe to be translated");

	    throw e; // Re-throw the exception indicating that the query is not safe.
	}
	finally
	{
	    if(log.isDebugEnabled())
		log.debug("Statement is safe to execute");

	    closeConnection();
	    initConnection();

	    // Restore the prepare statement connection.
	    preparedStatementQuery = savedPreparedStatementQuery;
	    preparedStatement = connection.prepareStatement(preparedStatementQuery);
	}
    }

    @Override
    public void closeConnection()
    {
	try
	{
	    if(connection != null && !connection.isClosed())
		connection.close();

	    connection = null;
	}
	catch(SQLException exception)
	{
	    if(log.isErrorEnabled())
		log.error(exception.getMessage());
	}
    }

    @Override
    public void setRowStreamingMode(boolean active)
    {
	streamingModeOn = active;
    }

    @Override
    public int executePreparedStatementAsUpdate(Object parameter) throws SQLException
    {
	List<Object> parameters = new ArrayList<Object>();
	parameters.add(parameter);
	return executePreparedStatementAsUpdate(parameters);
    }

    @Override
    public ResultSet executePreparedStatementAsQuery(Object parameter) throws SQLException
    {
	List<Object> parameters = new ArrayList<Object>();
	parameters.add(parameter);
	return executePreparedStatementAsQuery(parameters);
    }

    @Override
    public abstract ResultSet executeQuery(String sqlQuery) throws SQLException;

    @Override
    public abstract int executeUpdate(String sqlUpdate) throws SQLException;

    @Override
    public abstract void createPreparedStatement(String sqlStatement) throws SQLException;

    @Override
    public abstract ResultSet executePreparedStatementAsQuery(List<Object> parameters) throws SQLException;

    @Override
    public abstract int executePreparedStatementAsUpdate(List<Object> parameters) throws SQLException;
}
