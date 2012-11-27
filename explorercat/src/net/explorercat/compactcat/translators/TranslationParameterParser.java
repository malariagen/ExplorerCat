package net.explorercat.compactcat.translators;

import net.explorercat.util.exceptions.TranslationException;

/**
 * Represents a parser for translation parameters, it will generate a
 * {@link TranslationParameter} object from a string representation of the
 * parameter.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 5 Apr 2011
 */

public interface TranslationParameterParser
{
    /**
     * Parses a string representing a translation parameter.
     * 
     * @param parameterAsString String representation of the parameter.
     * @return The object that provides an interpretation of the parameter.
     */

    TranslationParameter parseParameter(String parameterAsString) throws TranslationException;
}
