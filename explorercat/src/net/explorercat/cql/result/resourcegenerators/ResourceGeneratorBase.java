package net.explorercat.cql.result.resourcegenerators;

/**
 * Base class for resource generators.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Nov 2010
 */

public abstract class ResourceGeneratorBase implements ResourceGenerator
{
    protected String resourceId;
    protected int maxSizeInMB;
    protected int expirationTimeInSeconds;

    /**
     * Constructor for the base class.
     * 
     * @param resourceId A string that acts as unique identifier for the
     *        resource.
     * @param maxSizeInMB Maximum size allowed for the resource.
     * @param expirationTimeInSeconds expiration time for the resource in
     *        seconds.
     */

    public ResourceGeneratorBase(String resourceId, int maxSizeInMB, int expirationTimeInSeconds)
    {
	this.resourceId = resourceId;
	this.maxSizeInMB = maxSizeInMB;
	this.expirationTimeInSeconds = expirationTimeInSeconds;
    }
}
