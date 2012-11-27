package net.explorercat.compactcat.translators;

import java.util.List;

import net.explorercat.compactcat.definitions.CatalogDefinition;
import net.explorercat.util.exceptions.TranslationException;

/**
 * Interface to be implemented by any class in charge of translating a catalog
 * (using a CompactCat format) into a different representation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Feb 2011
 */

public interface CatalogTranslator
{
    /**
     * Translates the given catalog into a different representation, taking into
     * account the parameters provided to guide the translation. Different
     * translators will accept different sets of parameters.
     * 
     * @param catalogDefinition Definition of the catalog that is going to be
     *        translated.
     * @param parameters List of parameters to be applied during the translation
     *        process.
     * @throws TranslationException If there is an error performing the
     *         translation.
     */

    public void translateCatalog(CatalogDefinition catalogDefinition, List<TranslationParameter> parameters)
	throws TranslationException;

    /**
     * Gets the translator in charge of translating the catalog entities into
     * the chosen representation. The object returned by this entity must be
     * used to translate the entities contained in the catalog. Note the
     * translation process consists of three steps executed in order (for
     * details check the @EntityTranslator interface).
     */

    public EntityTranslator getEntityTranslator();

    /**
     * Translates the catalog constraints (relationship between entities and
     * attributes into the the chosen representation.
     * 
     * @param catalogDefinition Definition of the catalog that has been already
     *        translated.
     */

    public void translateConstraints(CatalogDefinition catalogDefinition) throws TranslationException;
}
