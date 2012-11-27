package net.explorercat.cql.selection.query.translators;

import net.explorercat.cql.selection.query.Query;
import net.explorercat.util.exceptions.TranslationException;

/**
 * Interface to be implemented by any class in charge of translating a query
 * into a {@link StorageLayerQuery} object. These objects can be used to execute
 * the query against the data storage layer (i.e. when the data are not loaded
 * in memory). Different storing systems have to provide specific
 * implementations of this interface (e.g. databases vs. files).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 3 Mar 2011
 */

public interface QueryTranslator
{
    /**
     * Translates the given query into a query that can be executed against the
     * storage layer (e.g. a database).
     * 
     * @param query The query that will be translated.
     * @return An object that can be used to execute the query against the data
     *         storage layer.
     * @throws TranslationException
     */

    public StorageLayerQuery translateQuery(Query query) throws TranslationException;
}
