package net.explorercat.cql.result.resourcegenerators;

import java.util.GregorianCalendar;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.generatedresources.GeneratedFile;
import net.explorercat.cql.result.generatedresources.GeneratedResource;
import net.explorercat.cql.result.translators.ResultTextualTranslator;
import net.explorercat.cql.result.translators.ResultTooBigException;
import net.explorercat.cql.result.translators.ResultTranslationException;
import net.explorercat.cql.result.translators.ResultTextualTranslator.OutputFormat;
import net.explorercat.util.misc.FileDeleter;
import net.explorercat.util.misc.ZipCompressor;

/**
 * Class in charge of generating compressed CSV files from CQL results.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 26 Oct 2010
 */

public class ZippedFileGenerator extends ResourceGeneratorBase implements ResourceGenerator
{
    private ResultHeader fieldHeader;
    private Result result;
    private String filePath;
    private OutputFormat fileFormat;

    /**
     * Creates a zipped file generator for the given result.
     * 
     * @param resourceId A string that acts as unique identifier for the
     *        resource.
     * @param fieldHeader The headers of the result field.
     * @param result The result to be written into the file.
     * @param filePath The path of the file to be written.
     * @param fileFormat The format of the file to be zipped.
     * @param maxSizeInMB Maximum size allowed for the resource.
     * @param expirationTimeInSeconds expiration time for the resource in
     *        seconds.
     */

    public ZippedFileGenerator(String resourceId, ResultHeader fieldHeader, Result result, String filePath,
			       OutputFormat fileFormat, int maxSizeInMB, int expirationTimeInSeconds)
    {
	super(resourceId, maxSizeInMB, expirationTimeInSeconds);
	this.fieldHeader = fieldHeader;
	this.result = result;
	this.filePath = filePath;
	this.fileFormat = fileFormat;
    }

    /**
     * Translates the result into a compressed text file. The generation could
     * fail if the file to generate is too big or the system is too busy to
     * write it. An exception of the proper type is thrown for these errors.
     * 
     * @return An object that represents the resource generated (zip file).
     * @throws ResultTranslationException
     * @throws ResourceTooBigException If the file is too big.
     */

    @Override
    public GeneratedResource generateResource() throws ResourceGenerationException
    {
	ResultTextualTranslator translator = null;
	ApplicationController globalController = ApplicationController.getInstance();
	translator = globalController.getResultTextualTranslatorFactory().getTranslator(fileFormat);

	try
	{
	    // Translate into a CSV file. Doing it in memory would be faster but we are not in
	    // a position to support its memory footprint. 
	    translator.translateResultIntoFile(fieldHeader, result.getRows(), filePath, maxSizeInMB);
	    ZipCompressor.compressAsZipFile(filePath, "result" + fileFormat.getExtension());

	    // After compressing we delete the original file.	    
	    FileDeleter.deleteFile(filePath);
	}
	catch(ResultTooBigException e)
	{
	    throw new ResourceTooBigException(e.getMessage());
	}
	catch(ResultTranslationException e)
	{
	    throw new ResourceGenerationException("Error generating the resource, " + filePath);
	}

	long currentTime = GregorianCalendar.getInstance().getTimeInMillis();

	// Download configuration parameters.
	String contentDisposition = "attachment;filename=\"data.zip\"";
	String contentType = "application/zip";

	return new GeneratedFile(resourceId, filePath + ".zip", currentTime + 1000 * expirationTimeInSeconds,
				 contentDisposition, contentType);
    }
}
