package net.explorercat.cql.result.resourcegenerators;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.translators.ResultTextualTranslator.OutputFormat;

/**
 * Factory in charge of creating instances of resource generators. TODO This
 * class has to be refactored into a real factory, factory method or builder.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Nov 2010
 */

public class ResourceGeneratorFactory
{
    /**
     * Gets a zipped file generator for the given result.
     * 
     * @param resourceId A string that acts as unique identifier for the
     *        resource.
     * @param fieldHeader The headers of the result field.
     * @param result The result to be written into the file.
     * @param filePath The path of the file to be written.
     * @param zipEntryName The name of the file as a ZIP entry file.
     * @param maxSizeInMB Maximum size allowed for the resource.
     * @param expirationTimeInSeconds expiration time for the resource in
     *        seconds.
     */

    public ResourceGenerator getZippedFileGenerator(String resourceId, ResultHeader fieldHeader, Result result,
						    String filePath, OutputFormat fileFormat)
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	int maxFileSizeInMB = Integer.parseInt(lookup.getGlobalProperty("config.download.maxFileSizeInMB"));
	int expirationTimeInSeconds = Integer.parseInt(lookup.getGlobalProperty("config.download.fileExpirationTimeInSeconds"));

	return new ZippedFileGenerator(resourceId, fieldHeader, result, filePath, fileFormat, maxFileSizeInMB,
				       expirationTimeInSeconds);
    }
}
