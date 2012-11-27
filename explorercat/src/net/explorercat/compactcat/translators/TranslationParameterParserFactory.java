package net.explorercat.compactcat.translators;

import net.explorercat.compactcat.translators.mysql.TranslationParameterParserMySQL;

/**
 * Factory that provides different implementations for translation parameter
 * parsers.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 14 Feb 2011
 */

public class TranslationParameterParserFactory
{
    // Singleton class.
    private static TranslationParameterParserFactory instance = new TranslationParameterParserFactory();

    private TranslationParameterParserFactory()
    {
    }

    /**
     * Gets an instance of the factory (this class is a singleton).
     */

    public static TranslationParameterParserFactory getInstance()
    {
	return instance;
    }

    /**
     * Gets an implementation of the {@link TranslationParameterParser}
     * interface based on the current system configuration.
     * 
     * @return A concrete implementation of the
     *         {@link TranslationParameterParser} interface.
     */

    public TranslationParameterParser createTranslationParameterParser()
    {
	// Right now we only have a MySQL implementation.
	return new TranslationParameterParserMySQL();
    }
}
