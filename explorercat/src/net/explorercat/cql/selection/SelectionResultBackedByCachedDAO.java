package net.explorercat.cql.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.QueryableDataEntityDAO;
import net.explorercat.dataaccess.cached.QueryableDataEntityDAOCached;

/**
 * A specialisation of the SelectionResult class that improves the performance
 * and reduces its memory footprint thanks to a DAO that is caching all the
 * entities of the selection.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 28 Sep 2010
 */

public class SelectionResultBackedByCachedDAO extends SelectionResult
{
    // Cached entity DAO that is backing this selection.
    private QueryableDataEntityDAO cachedDAO;

    /**
     * Builds a new selection based on a given list of entities. This
     * implementation improves the performance of some operations relying on the
     * cached data of an entity DAO.
     * 
     * @param label Label that will be assgined to the selection.
     * @param entityType A string with the type of entity the selection
     *        contains.
     * @param catalog The catalog that contains this selection of entities.
     * @param entities The list of entities contained by the selection.
     * @param propertyDictionary A dictionary that relates each entity property
     *        with its type.
     * @param sorter Sorter used to sort the entities of the selection, null if
     *        no sorter was used.
     * @param cachedDAO The cached DAO that is backing this selection.
     */

    public SelectionResultBackedByCachedDAO(String label, String entityType, Catalog catalog,
					    List<QueryableDataEntity> entities,
					    PropertyDictionary propertyDictionary, EntitySorter sorter,
					    QueryableDataEntityDAO cachedDAO)
    {
	super(label, entityType, catalog, entities, propertyDictionary, sorter, true);
	this.cachedDAO = cachedDAO;

	// Sanity check (we do not restrict the type of the parameter to give flexibility to
	// third party classes that builds selection results).
	if(!(this.cachedDAO instanceof QueryableDataEntityDAOCached))
	    throw new IllegalArgumentException("Trying to create an instance of SelectionResultBackedByCachedDAO "
					       + " without providing a real cached DAO");
    }

    @Override
    public QueryableDataEntity getEntityById(int entityId)
    {
	return cachedDAO.getEntityById(entityId);
    }

    @Override
    public List<QueryableDataEntity> collectEntitiesById(Set<Integer> ids)
    {
	// Check if we can't do better than the parent class.	
	if(ids.size() >= entities.size() / 1.5)
	    return super.collectEntitiesById(ids);
	else
	{
	    List<QueryableDataEntity> collectedEntities = new ArrayList<QueryableDataEntity>(ids.size());

	    for(Integer id : ids)
	    {
		QueryableDataEntity entity = cachedDAO.getEntityById(id);
		if(entity != null)
		    collectedEntities.add(entity);
	    }

	    return collectedEntities;
	}
    }

    @Override
    public Selection selectEntities(Query query) throws SelectionException
    {
	// "Decorates" the selectEntities method since we have to return an instance of this implementation.
	// We replace the selection returned by the original selectEntities method with an instance of the
	// backedByCachedDAO implementation.
	Selection selectionResult = super.selectEntities(query);
	List<QueryableDataEntity> selectedEntities = selectionResult.getEntities(selectionResult.getSelectionSize(), 0);
	PropertyDictionary augmentedDictionary = selectionResult.getPropertyDictionary();

	// This selection will replace the original one.
	SelectionResult replacementSelection = new SelectionResultBackedByCachedDAO(query.getLabel(), entityType,
										    catalog, selectedEntities,
										    augmentedDictionary,
										    query.getResultingSelectionSorter(),
										    cachedDAO);
	return replacementSelection;
    }
}
