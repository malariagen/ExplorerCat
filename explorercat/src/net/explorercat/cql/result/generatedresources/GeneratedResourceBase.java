package net.explorercat.cql.result.generatedresources;

/**
 * A base class that represents generated resources like files or analysis
 * results.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 25 Oct 2010
 */

public abstract class GeneratedResourceBase implements GeneratedResource
{
    protected String identifier;
    protected String resourceURI;
    protected long expirationTimeInMs;
    protected boolean hasBeenDeleted;

    // Download configuration parameters
    private String contentDisposition;
    private String contentType;

    /**
     * Creates a new generated resource.
     * 
     * @param id The identifier of the resource.
     * @param resourceURI The location of the resource.
     * @param expirationTimeInMs The time (in milliseconds) when the resource
     *        will expire.
     * @param contentDisposition The value for the contentDisposition parameter
     *        when the resource is downloaded.
     * @param contentType The value for the contentType parameter when the
     *        resource is downloaded.
     */

    public GeneratedResourceBase(String id, String resourceURI, long expirationTimeInMs, String contentDisposition,
				 String contentType)
    {
	this.identifier = id;
	this.resourceURI = resourceURI;
	this.expirationTimeInMs = expirationTimeInMs;
	this.hasBeenDeleted = false;

	this.contentDisposition = contentDisposition;
	this.contentType = contentType;
    }

    @Override
    public String getResourceId()
    {
	return identifier;
    }

    @Override
    public String getResourceURI()
    {
	return resourceURI;
    }

    @Override
    public boolean hasBeenDeleted()
    {
	return hasBeenDeleted;
    }

    @Override
    public long getExpirationTime()
    {
	return expirationTimeInMs;
    }

    @Override
    public String getContentDisposition()
    {
	return contentDisposition;
    }

    @Override
    public String getContentType()
    {
	return contentType;
    }

    @Override
    public abstract boolean deleteResource();
}
