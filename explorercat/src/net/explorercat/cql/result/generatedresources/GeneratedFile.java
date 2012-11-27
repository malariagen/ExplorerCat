package net.explorercat.cql.result.generatedresources;

import net.explorercat.util.misc.FileDeleter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents generated files (like CSV or XML files) as resources.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 26 Oct 2010
 */

public class GeneratedFile extends GeneratedResourceBase implements GeneratedResource
{
    // Logging
    private static final Log log = LogFactory.getLog(GeneratedFile.class);

    /**
     * Creates a new generated file.
     * 
     * @param id The identifier of the file.
     * @param resourceURI The location of the file.
     * @param expirationTimeInMs The time (in milliseconds) when the file will
     *        expire.
     * @param contentDisposition The value for the contentDisposition parameter
     *        when the resource is downloaded.
     * @param contentType The value for the contentType parameter when the
     *        resource is downloaded.
     */

    public GeneratedFile(String id, String resourceURI, long expirationTimeInMs, String contentDisposition,
			 String contentType)
    {
	super(id, resourceURI, expirationTimeInMs, contentDisposition, contentType);
    }

    @Override
    public boolean deleteResource()
    {
	hasBeenDeleted = FileDeleter.deleteFile(resourceURI);

	if(hasBeenDeleted && log.isDebugEnabled())
	    log.debug("Resource file deleted: " + resourceURI);

	return hasBeenDeleted;
    }
}
