package net.explorercat.compactcat.translators;

import net.explorercat.compactcat.translators.mysql.CatalogTranslatorMySQL;

/**
 * Factory that provides different implementations for catalog translators.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 14 Feb 2011
 */

public class CatalogTranslatorFactory
{
    // Singleton class.
    private static CatalogTranslatorFactory instance = new CatalogTranslatorFactory();

    private CatalogTranslatorFactory()
    {
    }

    /**
     * Gets an instance of the factory (this class is a singleton).
     */

    public static CatalogTranslatorFactory getInstance()
    {
	return instance;
    }

    /**
     * Gets an implementation of the {@link CatalogTranslator} interface based
     * on the current system configuration.
     * 
     * @return A concrete implementation of the {@link CatalogTranslator}
     *         interface.
     */

    public CatalogTranslator createCatalogTranslator()
    {
	// Right now we only have a MySQL implementation.
	return new CatalogTranslatorMySQL();
    }
}
