package net.explorercat.cql.selection.query.translators;

import net.explorercat.cql.selection.Selection;

/**
 * Interface that represents a query representation that can be executed against
 * the data storage layer (for instance, a database). Different storage
 * back-ends must provide their own implementations.
 * 
 * When the data is not cached in memory, any query has to be translated into
 * this kind of executable representation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 15 Mar 2011
 */

public interface StorageLayerQuery
{
    /**
     * Executes the query against the current storage layer, returning a
     * selection of entities.
     * 
     * @return The resulting selection of executing the query.
     * @throws QueryExecutionException If there is any error executing the
     *         query.
     */

    public Selection executeQuery() throws QueryExecutionException;
}
