package net.explorercat.cql.result.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.explorercat.cql.result.Result.ResultRow;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.selection.sorters.SinglePropertyEntitySorter;
import net.explorercat.data.QueryableDataEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class extracts values from entity properties in a given selection. The
 * properties are stored as data values, associated with the entity unique
 * identifier and encapsulated as a result row.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 13 Aug 2010
 */

public class ResultRowExtractor
{
    // Logging
    private static final Log log = LogFactory.getLog(ResultRowExtractor.class);

    private Selection sourceSelection;
    private List<String> propertiesToExtract;

    /**
     * Builds a row extractor configured for the given selection and the given
     * list of properties.
     * 
     * @param sourceSelection The selection from where the entity properties
     *        will be extracted.
     * @param propertyNames List of property names whose values will be
     *        extracted.
     */

    public ResultRowExtractor(Selection sourceSelection, List<String> propertyNames)
    {
	this.sourceSelection = sourceSelection;
	this.propertiesToExtract = propertyNames;
    }

    /**
     * Sorts a copy of the source selection using the values of the given
     * property. The order of the original source selection is NOT modified.
     * (Ugly to be located in this class)
     * 
     * @param sortingPropertyName The name of the property that will be used to
     *        sort the entities.
     * @param sortInDescendantOrder True if the sort must be in descendant
     *        order.
     */

    public void sortSourceSelectionCopyByPropertyValue(String sortingPropertyName, boolean sortInDescendantOrder)
    {
	EntitySorter sorter = new SinglePropertyEntitySorter(sortingPropertyName, null, sortInDescendantOrder);

	this.sourceSelection = this.sourceSelection.getSelectionCopyWithSharedEntities();
	this.sourceSelection.sortByPropertyValue(sorter);
    }

    /**
     * Extracts a row (subset of property values) for each entity of the
     * selection for the given number of entities, beginning at the offset
     * position.
     * 
     * @param numEntities The number of entities to be considered.
     * @param offset The starting position for retrieving entities (an offset of
     *        0 meaning the first entity).
     * @return A list of rows, one per entity considered.
     * @throws SelectionException If there is any problem accessing the source
     *         selection.
     */

    public List<ResultRow> extractRowsFromSelection(int numEntities, int offset) throws SelectionException
    {
	ArrayList<ResultRow> rows = new ArrayList<ResultRow>();
	List<QueryableDataEntity> entities = sourceSelection.getEntities(numEntities, offset);
	
	for(QueryableDataEntity currentEntity : entities)
	{
	    ResultRow row = new ResultRow(currentEntity.getId());

	    for(int i = 0; i < propertiesToExtract.size(); ++i)
		row.addValue(currentEntity.getPropertyValue(propertiesToExtract.get(i)));

	    rows.add(row);
	}

	return rows;
    }

    /**
     * Extracts a row of DISTINCT property values for each entity of the
     * selection for the given number of entities, beginning at the offset
     * position. Note the entity identifiers have no meaning here (we are only
     * retrieving distinct values for the list of given properties).
     * 
     * @param numEntities The number of entities to be considered.
     * @param offset The starting position for retrieving entities (an offset of
     *        0 meaning the first entity).
     * @return A list rows that contains unique property values.
     * @throws SelectionException If there is any problem accessing the source
     *         selection.
     */

    public List<ResultRow> extractDistinctRowsFromSelection(int numEntities, int offset) throws SelectionException
    {
	Set<ResultRow> rows = new TreeSet<ResultRow>();
	List<QueryableDataEntity> entities = sourceSelection.getEntities(numEntities, offset);

	for(QueryableDataEntity currentEntity : entities)
	{
	    ResultRow row = new ResultRow(currentEntity.getId());

	    for(int i = 0; i < propertiesToExtract.size(); ++i)
		row.addValue(currentEntity.getPropertyValue(propertiesToExtract.get(i)));

	    rows.add(row);
	}

	return new ArrayList<ResultRow>(rows);
    }

    /**
     * Gets the source selection from which the extractor is getting all the
     * properties.
     * 
     * @return The selection from where the properties are being extracted.
     */

    public Selection getSourceSelection()
    {
	return this.sourceSelection;
    }

    /**
     * Inner class that represents a selection of properties (subset) for an
     * entity, equivalent to a result row. The entity is identified by its
     * unique identifier
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 13 Aug 2010
     */

}
