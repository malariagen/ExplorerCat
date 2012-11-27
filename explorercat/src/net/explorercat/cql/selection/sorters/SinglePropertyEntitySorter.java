package net.explorercat.cql.selection.sorters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.types.DataValue;
import net.explorercat.data.QueryableDataEntity;

/**
 * Sorts a given list of entities by the value of one of their properties.
 * Notice that in ascendent order we place null values at the top (at the bottom
 * in descendant order).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class SinglePropertyEntitySorter implements EntitySorter, Comparator<QueryableDataEntity>
{
    // Property to be used in order to sort the entities.
    private String sortingProperty;

    // If the property is a variable we need the definition.
    private Expression variableDefinition;

    // True if sorting in descent order.
    private boolean descentOrder;

    /**
     * Builds a single property sorter.
     * 
     * @param property The property to be used for sorting.
     * @param variableDefinition Expression of the variable to be used for the
     *        sorting instead of the entity property. Null if no variable is
     *        used.
     * @param descendantOrder True if we are sorting in reverse order.
     */

    public SinglePropertyEntitySorter(String property, Expression variableDefinition, boolean descendantOrder)
    {
	this.sortingProperty = property;
	this.descentOrder = descendantOrder;
	this.variableDefinition = variableDefinition;
    }

    @Override
    public void sort(List<QueryableDataEntity> entities)
    {
	// Sorting.
	Collections.sort(entities, this);
	if(descentOrder)
	    Collections.reverse(entities);
    }

    @Override
    public int compare(QueryableDataEntity entityA, QueryableDataEntity entityB)
    {
	DataValue valueA = entityA.getPropertyValue(sortingProperty);
	DataValue valueB = entityB.getPropertyValue(sortingProperty);

	// Check for missing properties.
	if(valueA == null || valueB == null)
	{
	    if(valueA == null && valueB == null)
		return 0;

	    // If A is null then B should be placed before A.
	    else if(valueA == null)
		return -1;

	    // valueB == null, If B is null then A should be placed before B.
	    else
		return 1;
	}
	else
	    return valueA.compareTo(valueB);
    }

    @Override
    public String toString()
    {
	String sortingReference = (variableDefinition == null ? sortingProperty : variableDefinition.toString());
	return "SORT BY " + sortingReference + (descentOrder ? " (descent)" : "");
    }

    @Override
    public String getStringKey()
    {
	StringBuilder key = new StringBuilder(128);
	key.append("SORT[");

	if(variableDefinition == null)
	    key.append(sortingProperty);
	else
	    key.append(variableDefinition.getStringKey());

	key.append(":").append(descentOrder ? " DESC]" : "ASC]");

	return key.toString();
    }

    @Override
    public String getFirstSortingProperty()
    {
	return sortingProperty;
    }

    @Override
    public boolean isUsingAscendingOrder()
    {
	return descentOrder == false;
    }

    @Override
    public List<String> getSortingProperties()
    {
	List<String> sortingProperties = new ArrayList<String>();
	sortingProperties.add(this.sortingProperty);

	return sortingProperties;
    }

    @Override
    public List<Expression> getSortingVariableDefinitions()
    {
	List<Expression> sortingVariables = new ArrayList<Expression>();
	sortingVariables.add(this.variableDefinition);

	return sortingVariables;
    }

    @Override
    public EntitySorterType getSorterType()
    {
	return EntitySorterType.SINGLE_PROPERTY_SORTER;
    }
}
