package net.explorercat.cql.result.generatedresources;

/**
 * Represents generated resources like files or analysis results. Different
 * implementations know how to deal with each type of resource.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 25 Oct 2010
 */

public interface GeneratedResource
{
    /**
     * Gets a string that identifies the resource.
     * 
     * @return The identifier of the resource.
     */

    public String getResourceId();

    /**
     * Gets the location (URI) of the resource.
     * 
     * @return The URI of the resource.
     */

    public String getResourceURI();

    /**
     * Removes the resource from the system.
     * 
     * @return True if the resource was removed, false otherwise.
     */

    public boolean deleteResource();

    /**
     * Checks if the resource has been deleted.
     * 
     * @return True if the resource has been deleted, false otherwise.
     */

    public boolean hasBeenDeleted();

    /**
     * Gets the expiration time for the resource (in milliseconds)..
     * 
     * @return The time when the resource will expire, in milliseconds.
     */

    public long getExpirationTime();

    /**
     * Returns a string with the configuration value of the contentDisposition
     * parameter for the download request.
     * 
     * @return The value of the contentDisposition download parameter.
     */

    public String getContentDisposition();

    /**
     * Returns a string with the configuration value of the contentType
     * parameter for the download request.
     * 
     * @return The value of the contentType download parameter.
     */

    public String getContentType();
}
