package net.explorercat.actions.api.pub.cql;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.cql.result.ResultPaginationCache;
import net.explorercat.cql.result.ResultIdentifier;
import net.explorercat.cql.result.ResultPaginationCacheException;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * API entry point for releasing CQL query connections. This API has been
 * designed to be used with AJAX and will provide JSON answers.
 * 
 * A call to this method closes the query connection with the server. No data
 * will be available for the same <ticket, hash> pair after this step is
 * executed.
 * 
 * - Input : { ticketNumber, hashCode }
 * 
 * - Output : { returnCode }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * The user provides the ticket number and hash code associated with the query
 * result (returned by the setup step) and the system will reply with a return
 * code (0 meaning everything was fine).
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 2 There was an error removing the results from the pagination cache.
 * 
 * JSONP: There is an optional input parameter (jsoncallback) that specifies the
 * callback function to process the JSON response. This allows scripts from
 * other domains to access this API.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 23 Sep 2010
 */

public class ReleaseQuerySessionMethod extends JSONBasedAPIMethod
{
    // Logging
    private static final Log log = LogFactory.getLog(ReleaseQuerySessionMethod.class);

    // Input Parameter: ticket number assigned to the user.
    private int ticketNumber;

    // Input Parameter: hash code assigned to the result.
    private int hashCode;

    // Output Parameter:  Normal JSON response (success).
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public void setTicketNumber(int ticketNumber)
    {
	this.ticketNumber = ticketNumber;
    }

    public void setHashCode(int hashCode)
    {
	this.hashCode = hashCode;
    }

    // Output parameters getters.

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    /**
     * Releases the CQL query session.
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Releasing query session for " + hashCode);

	try
	{
	    removeResultFromPaginationCache();
	    normalResponse = new NormalJSONResponse();
	    return SUCCESS;
	}
	catch(ExplorerCatCheckedException e)
	{
	    setErrorResponse(2, "Error removing the result from the pagination cache : " + e);
	    log.error(getErrorResponse().getErrorMessage());
	    return ERROR;
	}
    }

    /**
     * Removes the result of the query session we are releasing from the
     * pagination cache.
     */

    private void removeResultFromPaginationCache() throws ResultPaginationCacheException
    {
	ResultPaginationCache paginationCache = ApplicationController.getInstance().getPaginationCache();
	ResultIdentifier resultId = new ResultIdentifier(hashCode, ticketNumber);
	paginationCache.removeResult(resultId);

	if(log.isDebugEnabled())
	{
	    log.debug("Result removed from the pagination cache: " + resultId);
	    log.debug("Results in the pagination cache: " + paginationCache.getNumberOfResults());
	}
    }

    /**
     * Inner class that encapsulates the normal JSON response that is sent to
     * the user.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 11 Oct 2010
     */

    public static class NormalJSONResponse extends JSONResponse
    {
	public NormalJSONResponse()
	{
	    super(0);
	}
    }
}
