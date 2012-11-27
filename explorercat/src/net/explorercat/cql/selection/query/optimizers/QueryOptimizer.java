package net.explorercat.cql.selection.query.optimizers;

import java.util.List;

import net.explorercat.cql.selection.SelectionException;
import net.explorercat.data.QueryableDataEntity;

/**
 * Interface for classes in charge of optimizing the execution of queries over
 * selections. These classes will generate a preliminary selection of entities.
 * To check if this selection is final the user has to use the
 * isPreSelectionFinal method. If this method returns false, the original query
 * needs to be executed over the preliminary selection.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public interface QueryOptimizer
{
    /**
     * Checks if the current optimizer can optimizes the query. It is MANDATORY
     * to check this method returns true before callind any of the other methods
     * of the interface.
     * 
     * @return True if the query can be optimized, false otherwise.
     */

    public boolean canOptimizeQuery();

    /**
     * Generates a preliminary selection by executing an optimized version of
     * the query over the associated input selection. To check if this
     * pre-selection is final (no further processing is required) it is
     * necessary to call the method isPreSelectionFinal.
     * 
     * @return A selection that contains all the entities for which the query
     *         condition is true. Note we are not returning a copy of the
     *         entities so client code must not modify them.
     */

    public List<QueryableDataEntity> generatePreliminarySelection() throws SelectionException;

    /**
     * Checks if it is necessary to reevaluate the query over the preliminary
     * selection produced by the optimizer.
     * 
     * @return True if the optimiser returns a list of entities that don't need
     *         to be re-evaluated by the query.
     */

    public boolean isPreliminarySelectionFinal();
}
