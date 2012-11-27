package net.explorercat.application.configurators;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.cql.result.generatedresources.GeneratedResourceRepository;

/**
 * Configurator-builder in charge of creating instances of repositories for generated resources.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Nov 2010
 */

public class GeneratedResourceRepositoryConfigurator
{
    // Interval for removing expired resources.
    private static final long CLEANING_INTERVAL_IN_SECONDS;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	CLEANING_INTERVAL_IN_SECONDS = Long.parseLong(lookup.getGlobalProperty("config.download.cleaningIntervalInSeconds"));
    }
    
    /**
     * Builds a configured instance.
     * 
     * @return A configured instance of the proper class.
     */

    public static GeneratedResourceRepository buildInstance()
    {
	return new GeneratedResourceRepository(CLEANING_INTERVAL_IN_SECONDS);
    }
}
