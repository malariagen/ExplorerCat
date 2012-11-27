package net.explorercat.cql.result.translators;

/**
 * Factory that provides the proper translator implementation based on the
 * desired output format.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Oct 2010
 */

public class ResultTextualTranslatorFactory
{
    /**
     * Creates a translator for the desired output format.
     * 
     * @param outputFormat The desired output format of the data.
     * @return A translator for the given output format or null if no translator
     *         is available for that format.
     */

    public ResultTextualTranslator getTranslator(ResultTextualTranslator.OutputFormat outputFormat)
    {
	switch(outputFormat)
	{
	    case CSV:
		return new CSVResultTranslator();

	    case TAB:
		return new TABResultTranslator();
		
	    default:
		return null;
	}
    }
}
