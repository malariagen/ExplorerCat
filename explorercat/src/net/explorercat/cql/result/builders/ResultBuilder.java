package net.explorercat.cql.result.builders;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;

/**
 * Interface to represent result builders. Different implementations for each
 * type of result.
 * 
 * Notice that the result has to be resolved before the building process starts.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public interface ResultBuilder
{
    /**
     * Gets the label assigned to the result by the user.
     * 
     * @return The label of the result or null if no label was specified.
     */

    public String getResultLabel();

    /**
     * Resolves the selection associated with the result and configures the
     * builder. It has to be called before buildResult or
     * getNumberOfSelectedEntities are invoked.
     * 
     * @param resolver The resolver in charge of resolving referenced
     *        selections.
     * @throws SelectionResolutionException If the associated selections can't be
     *         resolved.
     */

    public void resolveResult(SelectionResolver resolver) throws SelectionResolutionException;

    /**
     * Builds the result. The result must be resolved (calling resolveResult)
     * before using this method.
     * 
     * @param numEntities The number of entities that will be selected from the
     *        selection.
     * @param offset The starting position for selecting the entities, being 0
     *        the first one.
     * @return A result object that contains the selected data.
     * @throws ResultGenerationException If there is any error building the result
     *         or it has not been resolved.
     */

    public Result buildResult(int numEntities, int offset) throws ResultGenerationException;

    /**
     * Gets the header of the result. The header contains the label assigned to
     * the result and the list of columns.
     * 
     * @return A header containing information about the columns (properties and
     *         variables) contained in the result.
     */

    public ResultHeader getHeader() throws ResultGenerationException;

    /**
     * Gets the number of selected entities in the result. The result must be
     * resolved (calling resolveResult) before using this method.
     * 
     * @return The size of the selection contained in the result.
     * @throws ResultGenerationException If there is an error accessing the
     *         selection or the result is not resolved.
     */

    public int getNumberOfSelectedEntities() throws ResultGenerationException;

    /**
     * Sorts a copy of the source selection using the values of the given
     * property. The order of the original source selection is NOT modified.
     * 
     * @param sortingPropertyName The name of the property that will be used to
     *        sort the entities.
     * @param sortInDescendantOrder True if the sort must be in descendant
     *        order.
     */

    public void sortSourceSelectionByPropertyValue(String sortingPropertyName, boolean sortInDescendantOrder);
}
