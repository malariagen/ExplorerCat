package net.explorercat.util.sql.dataconnectors.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.explorercat.util.sql.dataconnectors.SQLDataConnector;

/**
 * A MySQL data connector for production systems. It executes the SQL avoiding
 * any logging/measuring overhead but if strict mode is on it will only work
 * with prepared statements (if you try to execute a regular query an exception
 * will be raised). Notice that enabling the streaming mode with this connector
 * has a performance penalty.
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public class ProductionMySQLDataConnector extends ConfigurableMySQLDataConnector implements SQLDataConnector
{
    private static boolean strictModeActivated = true;

    @Override
    public ResultSet executeQuery(String sqlQuery) throws SQLException
    {
	if(strictModeActivated)
	    throw new SQLException("Not using parameter binding for a SQL query in a production system.");

	initConnection();
	return statement.executeQuery(sqlQuery);

    }

    @Override
    public int executeUpdate(String sqlUpdate) throws SQLException
    {
	if(strictModeActivated)
	    throw new SQLException("Not using parameter binding for a SQL query in a production system.");

	initConnection();
	return statement.executeUpdate(sqlUpdate);
    }

    @Override
    public void createPreparedStatement(String sqlStatement) throws SQLException
    {
	initConnection();
	preparedStatement = connection.prepareStatement(sqlStatement);
	preparedStatementQuery = sqlStatement;
    }

    @Override
    public ResultSet executePreparedStatementAsQuery(List<Object> parameters) throws SQLException
    {
	// If the streaming mode is on we have to translate the prepared statement 
	// to a regular query, otherwise the streaming won't work (MySQL quirk :()
	// In order to avoid any risk of SQL injection we have to check if it is safe 
	// to translate the prepared statement into a regular SQL query. 

	if(streamingModeOn)
	{
	    // Check the statement is safe.
	    checkPreparedStatementSafety(parameters);
	    // Translate it and execute it as a regular query.
	    return statement.executeQuery(translatePreparedStatementIntoSQLString(parameters));
	}
	else
	{
	    setPreparedStatementParameters(parameters);
	    return preparedStatement.executeQuery();
	}
    }

    @Override
    public int executePreparedStatementAsUpdate(List<Object> parameters) throws SQLException
    {
	setPreparedStatementParameters(parameters);
	return preparedStatement.executeUpdate();
    }

    /**
     * Enables/disables the strict mode (not allowing dynamic SQL queries).
     * 
     * @param strictModeActivated True to activate the strict mode, false
     *        otherwise.
     */

    static public void setStrictMode(boolean strictModeActivated)
    {
	ProductionMySQLDataConnector.strictModeActivated = strictModeActivated;
    }
}
