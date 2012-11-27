package net.explorercat.actions.api.pub.cql;

import net.explorercat.actions.PropertyLookup;
import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.ResultIdentifier;
import net.explorercat.cql.result.ResultPaginationCache;
import net.explorercat.cql.result.builders.ResultGenerationException;
import net.explorercat.cql.result.builders.ResultGenerator;
import net.explorercat.cql.result.generatedresources.GeneratedResource;
import net.explorercat.cql.result.generatedresources.GeneratedResourceRepository;
import net.explorercat.cql.result.resourcegenerators.ResourceGenerationException;
import net.explorercat.cql.result.resourcegenerators.ResourceGenerator;
import net.explorercat.cql.result.resourcegenerators.ResourceGeneratorFactory;
import net.explorercat.cql.result.resourcegenerators.ResourceTooBigException;
import net.explorercat.cql.result.resourcegenerators.SystemBusyException;
import net.explorercat.cql.result.translators.ResultTextualTranslator.OutputFormat;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API entry point for generating text files based on the data of a CQL result.
 * This API has been designed to be used with AJAX and will provide JSON
 * answers.
 * 
 * - Input : { ticketNumber, hashCode, fileFormat}
 * 
 * - Output : { returnCode, resourceId, retrievalURL}
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * The user identifies the result using the ticket number and hash code returned
 * by the setup step. The response object contains the identifier of the
 * resource, the user will use it to retrieve the file (using a different method
 * of the API).
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error generating the file.
 * 
 * - 2 The resource is too big (result too big to be written as a text file).
 * 
 * - 3 The system is too busy to write the text file now.
 * 
 * - 4 Duplicated request, the resource is being generated.
 * 
 * JSONP: There is an optional input parameter (jsoncallback) that specifies the
 * callback function to process the JSON response. This allows scripts from
 * other domains to access this API.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Oct 2010
 */

public class GenerateTextFileFromResultMethod extends JSONBasedAPIMethod
{
    private static final Log log = LogFactory.getLog(GenerateTextFileFromResultMethod.class);

    // Time limit for a resource to be generated.
    // We are not using it to abort the generation, just to keep the resource 
    // registered as "being generated" in the resource repository.
    private static final int GENERATION_TIME_LIMIT_IN_MS = 3600 * 1000;

    // The central repository of resources.
    private static final GeneratedResourceRepository RESOURCE_REPOSITORY;

    // URL that has to be accessed in order to retrieve the text file.
    // TODO Fix the generation of the proper URL.
    private static final String DOWNLOAD_RESOURCE_ACTION = "/pub/downloadResource";

    static
    {
	RESOURCE_REPOSITORY = ApplicationController.getInstance().getResourceRepository();
    }

    // Input Parameter: ticket number assigned to the user.
    private int ticketNumber;

    // Input Parameter: hash code assigned to the result.
    private int hashCode;

    // Input Parameter: format of the text file to be generated.
    private String fileFormat;

    // Output Parameter: Normal JSON response
    private NormalJSONResponse normalResponse;

    // Private property.
    private OutputFormat outputFormat;

    // Input parameters setters.

    public void setTicketNumber(int ticketNumber)
    {
	this.ticketNumber = ticketNumber;
    }

    public void setHashCode(int hashCode)
    {
	this.hashCode = hashCode;
    }

    public void setFileFormat(String fileFormat)
    {
	this.fileFormat = fileFormat;
    }

    // Output parameters getters.

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    /**
     * Generates a CSV file for the result specified by the user.
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Generating text file (" + fileFormat + ") for query result " + hashCode);

	// Check the file format is right.
	outputFormat = OutputFormat.valueOf(fileFormat);
	if(outputFormat == null)
	{
	    setErrorResponse(1, "Error generating file, unknown format: " + fileFormat);
	    log.error(getErrorResponse().getErrorMessage());
	    return ERROR;
	}

	ResultIdentifier resultId = new ResultIdentifier(hashCode, ticketNumber);
	String resourceId = outputFormat + "_" + ticketNumber + "_" + hashCode + outputFormat.getExtension();
	String csvFilename = PropertyLookup.getDownloadFolderPath() + "/" + resourceId;
	String retrievalURL = DOWNLOAD_RESOURCE_ACTION + "?resourceId=" + resourceId;

	// Check we can generate the file (it is not a duplicated request).
	GeneratedResource foundResource = RESOURCE_REPOSITORY.findResource(resultId, resourceId);

	if(!RESOURCE_REPOSITORY.isResourceBeingGenerated(resourceId) && foundResource == null)
	{
	    try
	    {
		generateFileAndRegisterAsResource(csvFilename, resourceId);
		this.normalResponse = new NormalJSONResponse(resourceId, retrievalURL);
		return SUCCESS;
	    }
	    catch(ResourceTooBigException e)
	    {
		RESOURCE_REPOSITORY.removeResource(resultId, resourceId);

		setErrorResponse(2, e.getMessage());
		log.error(getErrorResponse().getErrorMessage());
		return ERROR;
	    }
	    catch(SystemBusyException e)
	    {
		RESOURCE_REPOSITORY.removeResource(resultId, resourceId);

		setErrorResponse(3, e.getMessage());
		log.error(getErrorResponse().getErrorMessage());
		return ERROR;
	    }
	    catch(ExplorerCatCheckedException e)
	    {
		RESOURCE_REPOSITORY.removeResource(resultId, resourceId);

		setErrorResponse(1, "Error generating the text file");
		log.error(getErrorResponse().getErrorMessage());
		return ERROR;
	    }
	    finally
	    {
		// Remove the "being generated" mark from the repository.
		RESOURCE_REPOSITORY.removeResourceBeingGenerated(resourceId);
	    }
	}

	// The resource is in the repository
	else if(foundResource != null)
	{
	    this.normalResponse = new NormalJSONResponse(resourceId, retrievalURL);
	    return SUCCESS;
	}

	// Duplicated request (generation in progress).
	else
	{
	    setErrorResponse(4, "Duplicated request, the resource is being generated");
	    log.error(getErrorResponse().getErrorMessage());
	    return ERROR;
	}
    }

    private void generateFileAndRegisterAsResource(String filename, String resourceId)
	throws ResultGenerationException, ResourceGenerationException
    {
	startResourceGeneration(filename, resourceId);

	// Get the result from the pagination cache, including the headers.
	ResultPaginationCache paginationCache = ApplicationController.getInstance().getPaginationCache();
	ResultIdentifier resultIdentifier = new ResultIdentifier(hashCode, ticketNumber);
	ResultGenerator resultGenerator = paginationCache.findResultGenerator(resultIdentifier);

	if(resultGenerator == null)
	    throw new ResultGenerationException("Result generator not found for pair: " + resultIdentifier);

	// Get the complete result.
	Result result = resultGenerator.generateResult(-1, 0);
	GeneratedResource textFile = generateTextFileResource(resourceId, resultGenerator, result, filename);

	// Register the ZIP file as a generated resource in the central repository.
	RESOURCE_REPOSITORY.registerResource(resultIdentifier, textFile);
    }

    /**
     * Starts the resource generation process checking how busy is the system
     * and marking the resource as being generated.
     */

    private void startResourceGeneration(String filename, String resourceId) throws SystemBusyException
    {
	// Check if the system is too busy to generate another resource.
	// TODO Replace this naive checking with a pool of threads that queue when the system is busy.
	if(RESOURCE_REPOSITORY.getNumberOfRegisteredResources() > PropertyLookup.getMaxNumberOfConcurrentResources())
	    throw new SystemBusyException("System too busy to generate file: " + filename);

	// Register the resource as being generated.
	RESOURCE_REPOSITORY.registerResourceAsBeingGenerated(resourceId, GENERATION_TIME_LIMIT_IN_MS);
    }

    /**
     * Generate the text file from the given result as a resource.
     * 
     * @return The generated resource (the text file).
     */

    private GeneratedResource generateTextFileResource(String resourceId, ResultGenerator resultGenerator,
						       Result result, String filename)
	throws ResourceGenerationException, ResultGenerationException
    {
	ResultHeader header = resultGenerator.getResultHeader();
	ResourceGeneratorFactory factory = ApplicationController.getInstance().getResourceGeneratorFactory();
	ResourceGenerator fileGenerator = factory.getZippedFileGenerator(resourceId, header, result, filename,
									 outputFormat);
	return fileGenerator.generateResource();
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
	private String resourceId;
	private String retrievalURL;

	public NormalJSONResponse(String resourceId, String retrievalURL)
	{
	    super(0);
	    this.resourceId = resourceId;
	    this.retrievalURL = retrievalURL;
	}

	public String getResourceId()
	{
	    return resourceId;
	}

	public String getRetrievalURL()
	{
	    return retrievalURL;
	}
    }
}
