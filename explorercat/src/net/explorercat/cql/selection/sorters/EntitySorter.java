package net.explorercat.cql.selection.sorters;

import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.data.QueryableDataEntity;

/**
 * Interface to be implemented by any class able to sort queryable data entities
 * by the value of a property or a set of properties.
 * 
 * Instead of receiving selection objects the sorters will deal with lists of
 * entities so under normal conditions the own selections will be in charge of
 * applying the sorters.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public interface EntitySorter
{
    /**
     * Sorts a list of entities using a property, set of properties or
     * variables. This method DOES modify the given list.
     * 
     * @param entities The list of entities to be sorted.
     * @return The sorted list of entities.
     */

    public void sort(List<QueryableDataEntity> entities);

    /**
     * Gets the first property used to sort the entities.
     * 
     * @return The name of the property whose values will be used to sort the
     *         entities.
     */

    public String getFirstSortingProperty();

    /**
     * Gets a list containing the names of all the properties/variables that
     * will be used to sort the entities (in order).
     * 
     * @return A list containing the names of all the properties/variables that
     *         will be used to sort the entities.
     */

    public List<String> getSortingProperties();

    /**
     * Gets a list containing all the variable definitions (as expressions) that
     * will be used to sort the entities (in order). If a real property is used
     * instead of a variable, null will be found at that position.
     * 
     * @return A list containing the definition of the variables that will be
     *         used to sort the entities. This list will have the same length
     *         than the list returned by {@link getSortingProperties}, having
     *         nulls at the locations where the sorter is using a real property
     *         instead of a variable.
     */

    public List<Expression> getSortingVariableDefinitions();

    /**
     * Checks if the sorter will sort the entities in ascending order.
     * 
     * @return True if the sorter will sort in ascending order.
     */

    public boolean isUsingAscendingOrder();

    /**
     * Gets a string that identifies the sorter uniquely. Any referenced
     * variable and their queries are included as a part of the string.
     * 
     * @return A string that identifies uniquely the sorter.
     */

    public String getStringKey();

    /**
     * Gets the type of the sorter.
     */

    public EntitySorterType getSorterType();
}
