package net.explorercat.cql.selection.query.executors;

import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.query.Query;

/**
 * Interface that will implemented by any helper class that takes care of
 * executing a query over a selection.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public interface QueryExecutor
{
    /**
     * Generates a new selection by executing the query over the source selection
     * associated with it.
     * 
     * @param query The query to be executed.
     * @return A selection that contains all the entities for which the query
     *         condition is true. Note that the entities contained in the
     *         selection are not copies of the original ones so client code must
     *         not modify them.
     * @throws SelectionException If there is any problem dealing with the
     *         selections.
     */

    public Selection performSelection(Query query) throws SelectionException;
}
