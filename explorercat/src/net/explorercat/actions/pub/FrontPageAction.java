package net.explorercat.actions.pub;

import java.util.List;

import net.explorercat.actions.PropertyLookup;
import net.explorercat.application.ApplicationController;
import net.explorercat.data.Catalog;
import net.explorercat.data.PieceOfNews;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.DAOFactory;
import net.explorercat.dataaccess.NewsDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Makes available all the data needed to render the front page, including the
 * list of available catalogs and the latest news.
 * 
 * @author Jacob Almagro Garcia - June 2010
 */

public class FrontPageAction extends ActionSupport implements Preparable
{
    private static final Log log = LogFactory.getLog(FrontPageAction.class);

    private NewsDAO newsDAO;
    private CatalogDAO catalogDAO;

    // Output Parameters.
    private List<PieceOfNews> recentNews;
    private List<Catalog> catalogs;

    /**
     * Collects the required data from the DAOs.
     */
    @Override
    public void prepare() throws DAOException
    {
	try
	{
	    DAOFactory factory = ApplicationController.getInstance().getDAOFactory();
	    newsDAO = factory.getNewsDAO();
	    catalogDAO = factory.getCatalogDAO();

	    // Get the data.
	    recentNews = newsDAO.findLatestNews(PropertyLookup.getNumLatestNews());
	    catalogs = catalogDAO.findAllCatalogs();
	}
	catch(DAOException e)
	{
	    log.error("Error accessing the DAOs from FrontPageAction");
	    throw e;
	}
    }

    /**
     * Nothing to do, just logs the execution flow.
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Executing FrontPageAction");

	return SUCCESS;
    }

    // Public getters to allow the JSP access the data objects.

    public List<PieceOfNews> getRecentNews()
    {
	return recentNews;
    }

    public List<Catalog> getCatalogs()
    {
	return catalogs;
    }
}
