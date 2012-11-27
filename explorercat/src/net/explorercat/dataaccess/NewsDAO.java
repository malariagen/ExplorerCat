package net.explorercat.dataaccess;

import java.util.List;

import net.explorercat.data.PieceOfNews;
import net.explorercat.data.User;

/**
 * DAO in charge of providing piece of news.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Sep 2010
 */

public interface NewsDAO
{
    /**
     * Finds all the news from the data source.
     * 
     * @return A list containing all the news.
     */

    public List<PieceOfNews> findAllNews();

    /**
     * Gets the n latest piece of news, order by date.
     * 
     * @param numOfNews The number of news to be retrieved.
     * @return A list contained the latest "numOfNews" news.
     */

    public List<PieceOfNews> findLatestNews(int numOfNews);

    /**
     * Inserts a piece of news in the DAO, updating the data source as well.
     * 
     * @param day Date
     * @param month Date
     * @param year Date
     * @param content The content of the piece of news (including HTML tags).
     * @param author The user that wrote the piece of news.
     */

    public void insertPieceOfNews(int day, int month, int year, String content, User author) throws DAOException;

    /**
     * Deletes the piece of news with the given id from the DAO and the data
     * source.
     * 
     * @param id The identifier of the piece of news to be deleted.
     */

    public void deletePieceOfNews(int id) throws DAOException;
}
