package net.explorercat.dataaccess.cached.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import net.explorercat.application.ApplicationController;
import net.explorercat.data.PieceOfNews;
import net.explorercat.data.User;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.DAOFactory;
import net.explorercat.dataaccess.cached.NewsDAOCached;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A MySQL implementation for the news DAO. This class will provide the
 * functionality that interacts with the DB engine in order to load all the news
 * into memory.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 21 Sep 2010
 */

public class NewsDAOCachedMySQL extends NewsDAOCached
{
    // Logging
    private static Log log = LogFactory.getLog(NewsDAOCachedMySQL.class);

    // We use a default date in case we retrieve a wrong date field from the DB.
    private static final java.sql.Date DEFAULT_DATE = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

    // Data provider that will access the DB.
    private SQLDataConnector sqlProvider;

    /**
     * Constructor (delegates in the super constructor that will call the
     * initialize method).
     */

    public NewsDAOCachedMySQL() throws DAOException
    {
	super();
    }

    @Override
    protected void initializeCache() throws DAOException
    {
	if(log.isErrorEnabled())
	    log.debug("Initializing cache for UserDAO from MySQL DB");

	try
	{
	    sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();
	    sqlProvider.createPreparedStatement("SELECT * FROM news");
	    ResultSet resultSet = sqlProvider.executePreparedStatementAsQuery(null);

	    // Iterate the result set registering each piece of news.
	    while(resultSet.next())
	    {
		PieceOfNews pieceOfNews = buildPieceOfNewsFromResultSet(resultSet);
		news.add(pieceOfNews);
	    }
	}
	catch(SQLException e)
	{
	    String errorMessage = "Error trying to initialize the cache for NewsDAO, accessing MySQL";
	    log.error(errorMessage);
	    throw new DAOException(errorMessage);
	}
	finally
	{
	    sqlProvider.closeConnection();
	}
    }

    /**
     * Auxiliary method that creates a piece of news from the current position
     * of a DB result set.
     * 
     * @param resultSet The result set that contains all the properties of the
     *        piece of news.
     * @return The piece of news described by the current position of the result
     *         set.
     * @throws DAOException
     */

    private PieceOfNews buildPieceOfNewsFromResultSet(ResultSet resultSet) throws SQLException, DAOException
    {
	java.sql.Date pieceOfNewsDate = null;

	// Check if the date had a wrong format
	// (we use the server startup date instead).
	try
	{
	    pieceOfNewsDate = resultSet.getDate("date");
	}
	catch(SQLException e)
	{
	    pieceOfNewsDate = DEFAULT_DATE;
	}

	// TODO Replace this ugly way of reading dates.
	StringTokenizer dateTokenizer = new StringTokenizer(pieceOfNewsDate.toString(), "-");

	int year = Integer.valueOf(dateTokenizer.nextToken());
	int month = Integer.valueOf(dateTokenizer.nextToken());
	int day = Integer.valueOf(dateTokenizer.nextToken());

	int id = resultSet.getInt("id");
	String content = resultSet.getString("content");
	DAOFactory factory = ApplicationController.getInstance().getDAOFactory();
	User author = factory.getUserDAO().findUser(resultSet.getInt("user_id"));

	// Update the current primary key id if necessary
	if(lastIdValue <= id)
	    lastIdValue = id + 1;

	return new PieceOfNews(id, content, day, month, year, author);
    }

    @Override
    protected void deleteFromDataSource(int id) throws DAOException
    {
	sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();
	try
	{
	    sqlProvider.createPreparedStatement("DELETE FROM news WHERE id = ?");
	    sqlProvider.executePreparedStatementAsUpdate(id);
	}
	catch(SQLException e)
	{
	    String error = "Error deleting a piece of news from the NewsDAO";
	    log.error(error);
	    throw new DAOException(error, e);
	}
	finally
	{
	    sqlProvider.closeConnection();
	}
    }

    @Override
    protected void insertIntoDataSource(PieceOfNews pieceOfNews) throws DAOException
    {
	sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();

	try
	{
	    // Note we do not provide the piece of news identifier (it will be auto-incremented).
	    sqlProvider.createPreparedStatement("INSERT INTO news(id,date,content,user_id) values(?,?,?,?)");
	    ArrayList<Object> parameters = new ArrayList<Object>(4);

	    parameters.add(pieceOfNews.getId());
	    parameters.add(pieceOfNews.getYear() + "-" + pieceOfNews.getMonth() + "-" + pieceOfNews.getDay());
	    parameters.add(pieceOfNews.getContent());
	    parameters.add(pieceOfNews.getAuthorUser().getId());

	    sqlProvider.executePreparedStatementAsUpdate(parameters);
	}
	catch(SQLException e)
	{
	    String error = "Error inserting a piece of news into the NewsDAO";
	    log.error(error);
	    throw new DAOException(error, e);
	}
	finally
	{
	    sqlProvider.closeConnection();
	}
    }
}
