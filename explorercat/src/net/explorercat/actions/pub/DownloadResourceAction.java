package net.explorercat.actions.pub;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.result.ResultIdentifier;
import net.explorercat.cql.result.generatedresources.GeneratedResource;
import net.explorercat.cql.result.generatedresources.GeneratedResourceRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action that allows the user to download the specified resource.
 * 
 * The user has to specify the following parameters
 * 
 * - Input : ticketNumber, hashCode, resourceId,
 * 
 * The user identifies the result using the ticket number and hash key. The
 * resource is specified by means of the resourceId.
 * 
 * This action returns an inpuptStream for direct downloading.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Oct 2010
 */

public class DownloadResourceAction extends ActionSupport
{
    // Logging
    private static final Log log = LogFactory.getLog(DownloadResourceAction.class);

    // The central repository of resources.
    private static final GeneratedResourceRepository RESOURCE_REPOSITORY = ApplicationController.getInstance().getResourceRepository();

    // Input Parameter: ticket number assigned to the user session.
    private int ticketNumber;

    // Input Parameter: hash code assigned to the result.
    private int hashCode;

    // Input Parameter: identifier of the resource the user is requesting.
    private String resourceId;

    // Output Parameter: stream for the file.
    private InputStream inputStream;

    // Output Parameters: download configuration.
    private String contentType;
    private String contentDisposition;

    /**
     * Executes the action, returning the resource or an error page if the file
     * is not found.
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Executing DownloadResourceAction");

	GeneratedResource resource = null;

	try
	{
	    // Finds the resource in the central repository.
	    resource = RESOURCE_REPOSITORY.findResource(new ResultIdentifier(hashCode, ticketNumber), resourceId);

	    if(resource != null)
	    {
		// Configure the download request.
		this.contentDisposition = resource.getContentDisposition();
		this.contentType = resource.getContentType();

		// Open the file as an input stream.
		this.inputStream = new BufferedInputStream(new FileInputStream(new File(resource.getResourceURI())));
		return SUCCESS;
	    }
	    else
	    {
		log.error("Error downloading a resource(" + resourceId + "), not found in the respository.");
		return ERROR;
	    }
	}
	catch(FileNotFoundException e)
	{
	    log.error("Error downloading a resource, file not found. " + e.getMessage());
	    return ERROR;
	}
    }

    // Setters for the input parameters.

    public void setTicketNumber(int ticketNumber)
    {
	this.ticketNumber = ticketNumber;
    }

    public void setHashCode(int hashCode)
    {
	this.hashCode = hashCode;
    }

    public void setResourceId(String resourceId)
    {
	this.resourceId = resourceId;
    }

    // Getters for the output (file input stream).

    public InputStream getInputStream()
    {
	return inputStream;
    }

    // Getters for the configuration of the download request.

    public String getContentType()
    {
	return contentType;
    }

    public String getAllowCaching()
    {
	// No caching by default.
	return "false";
    }

    public String getContentDisposition()
    {
	return contentDisposition;
    }
}
