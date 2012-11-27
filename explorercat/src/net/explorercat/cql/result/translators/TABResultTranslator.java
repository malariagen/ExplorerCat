package net.explorercat.cql.result.translators;

/**
 * Translates a result into a TAB file representation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date September 2011
 */

public class TABResultTranslator extends TextualTranslatorBase
{
    public TABResultTranslator()
    {
	super("\t", "\n", ResultTextualTranslator.OutputFormat.TAB.toString());
    }

}
