package net.explorercat.staticresources;

/**
 * Represents a static resource (pre-generated) that will be associated with a
 * catalogue. Static resources do not expire and can be accessed by actions and
 * plug-ins by means of the static resource repository
 * {@link StaticResourceRepository}.
 * 
 * TODO Implement discrimination based on different types of resources/URIs
 * (right now everything is a file to be downloaded).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 17 Dec 2010
 */

public class StaticResource
{
    private String name;
    private String resourceURI;
    private String type;
    private String description;

    /**
     * Creates a static resource that is associated with an URI. URI.
     * 
     * @param name The name of the resource, (uniqueness not required, the same
     *        name can match a collection of resources that have a different
     *        URI).
     * @param resourceURI Universal Resource Identifier of the resource, like an
     *        URL or a file path. Right now it refers to the relative path of a
     *        file that lives within the WebContent folder.
     * @param type Type of resource (e.g. XML, CSV, etc.)
     * @param description Brief description of the nature of the resource.
     */

    public StaticResource(String name, String resourceURI, String type, String description)
    {
	this.name = name;
	this.resourceURI = resourceURI;
	this.type = type;
	this.description = description;	
    }

    public String getName()
    {
	return name;
    }

    public String getResourceURI()
    {
	return resourceURI;
    }

    public String getType()
    {
	return type;
    }

    public String getDescription()
    {
	return description;
    }
}
