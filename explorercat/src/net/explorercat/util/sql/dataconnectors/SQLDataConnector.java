package net.explorercat.util.sql.dataconnectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * A simple interface that defines a wrapper over the JDBC layer. Different
 * implementations could offer different functionality like performance
 * measurement.
 * 
 * The client MUST call the closeConnection method after collecting the required
 * data. Providers can be reused (consecutive calls to the execute methods) but
 * they have to be closed between invocations. If you fail to close a provider
 * connection and try to use it again, an exception will be raised.
 * 
 * If you need to maintain more than one connection open at the same time you
 * must request different providers to the factory.
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public interface SQLDataConnector
{
    /**
     * Executes a SQL query using the JDBC library
     * 
     * @param sqlQuery The query to be executed (SELECT).
     * @return A result set object containing the query results.
     * @throws SQLException If there is an error accessing the DB.
     */

    ResultSet executeQuery(String sqlQuery) throws SQLException;

    /**
     * Executes a SQL update statement using the JDBC library.
     * 
     * @param sqlQuery The query to be executed (INSERT, DELETE, UPDATE,
     *        CREATE).
     * @return The number of rows modified by the query.
     * @throws SQLException If there is an error accessing the DB.
     */

    int executeUpdate(String sqlUpdate) throws SQLException;

    /**
     * Creates a prepared statement that can be reused multiple times with
     * different parameter values calling the executePreparedStatement method.
     * 
     * @param sqlStatement The SQL query that will build the prepared statement.
     */

    void createPreparedStatement(String sqlStatement) throws SQLException;;

    /**
     * Executes the prepared statement as a query (SELECT) with the provided
     * list of parameters.
     * 
     * @param parameters A list that contains the parameters for the statement
     *        in order. Parameters will be set up based on the type of each
     *        element (String, Integer, etc).
     * @return A result set object containing the query results.
     */

    ResultSet executePreparedStatementAsQuery(List<Object> parameters) throws SQLException;;

    /**
     * Executes the prepared statement as a query(select) using the single
     * parameter given.
     * 
     * @param parameter The parameter to execute the prepared statement.
     * @return A result set object containing the query results.
     */

    ResultSet executePreparedStatementAsQuery(Object parameter) throws SQLException;;

    /**
     * Executes the prepared statement as an update (INSERT, UPDATE) with the
     * provided list of parameters.
     * 
     * @param parameters A list that contains the parameters for the statement
     *        in order. Parameters will be set up based on the type of each
     *        element (String, Integer, etc).
     * @return The number of rows modified by the query.
     */

    int executePreparedStatementAsUpdate(List<Object> parameters) throws SQLException;

    /**
     * Executes the prepared statement as an update(insert,update) using the
     * single given parameter.
     * 
     * @param parameter The parameter to execute the prepared statement.
     * @return The number of rows modified by the query.
     */

    int executePreparedStatementAsUpdate(Object parameter) throws SQLException;;

    /**
     * Closes the connection with the database. This method MUST be called after
     * collecting the result. Reusing a data provider (calling executeQuery or
     * executeUpdate) will raise an exception if the connection has not been closed.
     */

    void closeConnection();

    /**
     * Sets on/off the streaming mode for rows via result sets. When streaming is on
     * the result set will fetch small blocks of rows when iterating through it
     * instead of fetching a huge amount of them. This, although slower, will
     * avoid the big memory footprint caused by big selections (millions of
     * rows).
     * 
     * @param active True to activate the streaming, false to make it inactive.
     */

    void setRowStreamingMode(boolean active);
}
