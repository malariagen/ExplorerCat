package net.explorercat.actions.api.admin;

import java.util.GregorianCalendar;

import net.explorercat.application.ApplicationController;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.NewsDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Preparable;

/**
 * API methods to work with news. Communication with the client is done via JSON
 * objects. Notice that for all the methods a return code value different from
 * zero means there was an error performing the operation and an error message
 * can be queried from the response.
 * 
 * API methods:
 * 
 * - addPieceOfNews: Input{user, password, content}, NormalOutput{returnCode},
 * ErrorOutput{returnCode, errorMessage}. Where content is the text of the piece
 * of news (can be HTML).[returnCode = 2] means there was an error accessing the
 * news DAO.
 * 
 * - deletePieceOfNews: Input{user, password, id}, NormalOutput{returnCode},
 * ErrorOutput{returnCode, errorMessage}. Where id is the identifier of the
 * piece of news to be deleted. [returnCode = 1] means the piece of news id was
 * not specified. [returnCode = 2] means there was an error accessing the news
 * DAO.
 * 
 * @author Jacob Almagro Garcia - Summer 2010
 * @modified CM10 - Jun 2011
 */

public class ManageNewsMethod extends AdminAPIMethod implements Preparable
{
    private static final Log log = LogFactory.getLog(ManageNewsMethod.class);

    private NewsDAO newsDAO;

    // Input Parameter: Test of the piece of news to be added.
    // Only to be used by the addPieceOfNews method.
    private String pieceOfNewsContent;

    // Input Parameter: Identifier of the piece of news to be deleted.
    // Only to be used by the deletePieceOfNews method.
    private int pieceOfNewsId;

    // Output Parameter: Normal JSON response (success).
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public int getPieceOfNewsId()
    {
	return pieceOfNewsId;
    }

    public void setPieceOfNewsId(int pieceOfNewsId)
    {
	this.pieceOfNewsId = pieceOfNewsId;
    }

    public String getPieceOfNewsContent()
    {
	return pieceOfNewsContent;
    }

    public void setPieceOfNewsContent(String pieceOfNewsContent)
    {
	this.pieceOfNewsContent = pieceOfNewsContent;
    }

    // test if the content is valid. Extend with same others test.
    public boolean isValidPieceOfNewsContent()
    {
	return this.pieceOfNewsContent.isEmpty();
    }

    // Output parameters getters

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    @Override
    public void prepare() throws DAOException
    {
	try
	{
	    newsDAO = ApplicationController.getInstance().getDAOFactory().getNewsDAO();
	    pieceOfNewsId = -1;
	    pieceOfNewsContent = null;
	}
	catch(DAOException e)
	{
	    log.error("Error accessing the DAOs from AdminNewsAction");
	    throw e;
	}
    }

    /**
     * Deletes a piece of news from the system.
     */

    public String deletePieceOfNews()
    {
	if(hasUserAdminRights())
	{
	    try
	    {
		if(pieceOfNewsId != -1)
		{
		    if(log.isDebugEnabled())
			log.debug("Deleting piece of news with id: " + this.pieceOfNewsId);

		    newsDAO.deletePieceOfNews(pieceOfNewsId);
		    this.normalResponse = new NormalJSONResponse();
		    return SUCCESS;
		}
		else
		{
		    setErrorResponse(1, "No id was specified");
		    return ERROR;
		}
	    }
	    catch(DAOException e)
	    {
		setErrorResponse(2, "Error accessing the News DAO");
		return ERROR;
	    }
	}
	else
	{
	    setErrorResponse(4, "User has not privileges to perform this operation");
	    return ERROR;
	}
    }

    /**
     * Inserts a piece of news into the system (it will use the current date and
     * the requesting user as author).
     */

    public String addPieceOfNews()
    {
	if(log.isDebugEnabled())
	    log.debug("Adding a piece of news");

	if(hasUserAdminRights())
	{
	    if(isValidPieceOfNewsContent())
	    {
		setErrorResponse(3, "Error: you have to fill the News Content.");
		return ERROR;
	    }
	    else
	    {
		try
		{
		    // Date (notice the +1 with the month since January has a value of zero).
		    int day = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
		    int month = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) + 1;
		    int year = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);

		    newsDAO.insertPieceOfNews(day, month, year, pieceOfNewsContent, getUserObject());
		    this.normalResponse = new NormalJSONResponse();
		    return SUCCESS;
		}
		catch(DAOException e)
		{
		    setErrorResponse(2, "Error accessing the DAO layer");
		    return ERROR;
		}
	    }
	}
	else
	{
	    setErrorResponse(4, "User has not privileges to perform this operation");
	    return ERROR;
	}
    }

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Executing ManageNewsAction");

	return SUCCESS;
    }

    /**
     * Encapsulates the JSON response that is sent back to the user.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date May 2011
     */

    public static class NormalJSONResponse extends JSONResponse
    {
	public NormalJSONResponse()
	{
	    super(0);
	}
    }
}
