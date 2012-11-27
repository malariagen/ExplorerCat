package net.explorercat.cql.result.translators;

import java.util.List;

import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.Result.ResultRow;

/**
 * Interface for classes that translates a CQL result into a textual
 * representation. Different implementations provide supports for different
 * formats like JSON, XML, CSV, TAB, etc.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 16 Aug 2010
 */

public interface ResultTextualTranslator
{
    /**
     * Translates a field of the CQL result into a textual representation.
     * 
     * @param result The result to be translated.
     * @param fieldId The identifier of the field to be translated.
     * @param filename The file where the translation will be written.
     * @param maxFileSizeInMB Maximum size for the file to be generated (in MB).
     *        An exception is thrown if the file size gets bigger than this.
     */

    public void translateResultIntoFile(ResultHeader headers, List<ResultRow> dataRows, String filename,
					int maxFileSizeInMB) throws ResultTranslationException;

    /**
     * Supported output formats.
     */

    public enum OutputFormat
    {
	CSV
	{
	    @Override
	    public String getExtension()
	    {
		return ".csv";
	    }
	},
	
	TAB
	{
	    @Override
	    public String getExtension()
	    {
		return ".tab";
	    }
	};

	/**
	 * Gets the file extension of the output format.
	 * 
	 * @return A string in the form ".extension" with the extension of the
	 *         output format.
	 */

	public abstract String getExtension();
    }
}
