package net.explorercat.cql.result.translators;

/**
 * Translates a result into a CSV representation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 16 Aug 2010
 */

public class CSVResultTranslator extends TextualTranslatorBase
{
    public CSVResultTranslator()
    {
	super(",", "\n", ResultTextualTranslator.OutputFormat.CSV.toString());
    }
}
