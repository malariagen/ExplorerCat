package net.explorercat.compactcat.translators;

import java.util.List;

import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.cql.types.DataValue;
import net.explorercat.util.exceptions.TranslationException;

/**
 * Interface to be implemented by any class in charge of translating a catalog
 * entity (based on the CompactCat format) into a different representation.
 * Steps to translate an entity: 1) Call the translateEntity method. 2) Add all
 * the entity data rows. 3) Call the finishTranslation method.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 14 Feb 2011
 */

public interface EntityTranslator
{
    /**
     * Translates an entity definition into a different representation. Note
     * that data has to be added to the translated representation using another
     * method.
     * 
     * @param entityDefinition Definition of the entity to be translated.
     * @param parameters List of parameters to be used during the translation.
     * @throws TranslationException If there is an error performing the
     *         translation
     */

    public void translateEntity(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException;

    /**
     * Adds a list of data rows (based on the CompactCat format) to the
     * translated representation of the entity.
     * 
     * @param dataRows List of data rows to be added.
     * @param entityDefinition Definition of the entity we are translating.
     * @throws TranslationException If there is an error translating the data
     *         (for instance, a violation of the data type range).
     */

    public void addDataRowsToEntityTranslation(List<List<DataValue>> dataRows, EntityDefinition entityDefinition)
	throws TranslationException;

    /**
     * Finishes the translation process (adds constraints, generates stats,
     * etc.). No more data could be added to the entity after calling this
     * method.
     * 
     * @param entityDefinition Definition of the entity that is being
     *        translated.
     * @param parameters List of parameters to be used during the translation.
     * @throws TranslationException If there is an error finishing the
     *         translation process.
     */

    public void finishTranslation(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException;
}
