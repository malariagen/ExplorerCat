package net.explorercat.cql.result.resourcegenerators;

import net.explorercat.cql.result.generatedresources.GeneratedResource;

/**
 * Interface to be implemented by any class in charge of generating resources.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 26 Oct 2010
 */

public interface ResourceGenerator
{
    /**
     * Creates a new resource, returning its object representation.
     * 
     * @return An object that represents the resource generated (includes its
     *         URI).
     * @throws ResourceGenerationException If there is a problem generating the
     *         resource.
     */

    public GeneratedResource generateResource() throws ResourceGenerationException;
}
