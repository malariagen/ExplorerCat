package net.explorercat.dataaccess.cached;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.explorercat.data.PieceOfNews;
import net.explorercat.data.User;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.NewsDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the news DAO that relies on a memory cache. All the news
 * will be loaded into memory at startup time.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Sep 2010
 */

public abstract class NewsDAOCached implements NewsDAO
{
    // Logging
    private static Log log = LogFactory.getLog(NewsDAOCached.class);

    // Cached list of catalogs.
    protected List<PieceOfNews> news;

    // Current value of the primary key identifier
    protected int lastIdValue;

    /**
     * Creates a new news DAO that whose news are cached in memory.
     * 
     * @throws DAOException If there is any problem initializing the cache.
     */

    public NewsDAOCached() throws DAOException
    {
	if(log.isDebugEnabled())
	    log.debug("Creating cached news DAO");

	// DAO accessed by multiple threads (although right now is read-only).
	news = new ArrayList<PieceOfNews>();

	// Starting at 1 by default (will be modified by the concrete implementation).
	lastIdValue = 1;

	// Calls the hook method in charge of initialise the cache.
	// This functionality has to be provided by a subclass.
	initializeCache();
    }

    @Override
    public List<PieceOfNews> findAllNews()
    {
	return new ArrayList<PieceOfNews>(news);
    }

    @Override
    public List<PieceOfNews> findLatestNews(int numOfNews)
    {
	ArrayList<PieceOfNews> latestNews = new ArrayList<PieceOfNews>(numOfNews);
	for(int i = news.size(); i > 0 && latestNews.size() < numOfNews; --i)
	    latestNews.add(news.get(i - 1));

	return latestNews;
    }

    @Override
    public void deletePieceOfNews(int id) throws DAOException
    {
	// Remove the piece of news from the original data source.
	deleteFromDataSource(id);

	// Remove the piece of news from the cache.
	Iterator<PieceOfNews> newsIterator = news.iterator();

	while(newsIterator.hasNext())
	    if(newsIterator.next().getId() == id)
		newsIterator.remove();
    }

    @Override
    public void insertPieceOfNews(int day, int month, int year, String content, User author) throws DAOException
    {
	PieceOfNews pieceOfNews = new PieceOfNews(lastIdValue, content, day, month, year, author);

	// Insert into the original data source.
	insertIntoDataSource(pieceOfNews);

	// Insert into the cache
	news.add(pieceOfNews);

	// Increment the value of the primary key identifier
	++lastIdValue;
    }

    /**
     * Hook method in charge of initialising the cache. This method has to be
     * implemented by subclasses bounded to a specific implementation (i.e.
     * MySQL).
     * 
     * @throws DAOException If there is a problem initializing the cache.
     */

    abstract protected void initializeCache() throws DAOException;

    /**
     * Hook method in charge of deleting a piece of news from the data source.
     * 
     * @param id The identifier of the piece of news.
     * @throws DAOException If there is a problem accessing the data source.
     */

    abstract protected void deleteFromDataSource(int id) throws DAOException;

    /**
     * Hook method in charge of inserting a piece of news into the data source.
     * 
     * @param pieceOfNews The piece of news to be inserted into the data source.
     * @throws DAOException If there is a problem accessing the data source.
     */

    abstract protected void insertIntoDataSource(PieceOfNews pieceOfNews) throws DAOException;
}
