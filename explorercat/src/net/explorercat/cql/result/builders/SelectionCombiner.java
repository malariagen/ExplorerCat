package net.explorercat.cql.result.builders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.SelectionResult;
import net.explorercat.cql.selection.SelectionResultBackedByCachedDAO;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.QueryableDataEntityDAO;

/**
 * Provides set operations for selections like Union, Difference and
 * Intersection. To be compatible for a set operation both selections have to be
 * coming from the same catalogue and refer to the same type of entity. In
 * addition, they musn't have duplicated properties with a different type in
 * each selection.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class SelectionCombiner
{
    // Logging
    private static Log log = LogFactory.getLog(SelectionCombiner.class);

    /**
     * Calculates the union of the two given selections. Note that the entities
     * contained in the returned selection are not copies so client code must
     * not modify them.
     * 
     * @return A selection that is the union of the given selections.
     * @throws IncompatibleTypeException If the selections are not compatible to
     *         perform a set operation.
     * @throws SelectionException If there is any problem accessing the
     *         selections.
     */

    public static Selection calculateUnion(Selection selectionA, Selection selectionB)
	throws IncompatibleTypeException, SelectionException
    {
	if(log.isDebugEnabled())
	    log.debug("Performing union of selections: A(" + selectionA.getSelectionSize() + ")" + ", B("
		      + selectionB.getSelectionSize() + ")");

	return performSetOperation(selectionA, selectionB, SetOperationType.UNION);
    }

    /**
     * Calculates the intersection of the two given selections. Note that the
     * entities contained in the returned selection are not copies so client
     * code must not modify them.
     * 
     * @return A selection that is the intersection of the given selections.
     * @throws IncompatibleTypeException If the selections are not compatible to
     *         perform a set operation.
     * @throws SelectionException If there is any problem accessing the
     *         selections.
     */

    public static Selection calculateIntersection(Selection selectionA, Selection selectionB)
	throws IncompatibleTypeException, SelectionException
    {
	if(log.isDebugEnabled())
	    log.debug("Performing intersection of selections: A(" + selectionA.getSelectionSize() + ")" + ", B("
		      + selectionB.getSelectionSize() + ")");

	return performSetOperation(selectionA, selectionB, SetOperationType.INTERSECTION);
    }

    /**
     * Calculates the difference of the two given selections. Note that the
     * entities contained in the returned selection are not copies so client
     * code must not modify them.
     * 
     * @return A selection that is the difference of the given selections.
     * @throws IncompatibleTypeException If the selections are not compatible to
     *         perform a set operation.
     * @throws SelectionException If there is any problem accessing the
     *         selections.
     */

    public static Selection calculateDifference(Selection selectionA, Selection selectionB)
	throws IncompatibleTypeException, SelectionException
    {
	if(log.isDebugEnabled())
	    log.debug("Performing difference of selections: A(" + selectionA.getSelectionSize() + ")" + ", B("
		      + selectionB.getSelectionSize() + ")");

	return performSetOperation(selectionA, selectionB, SetOperationType.DIFFERENCE);
    }

    /**
     * Auxiliary method that calculates the set operation specified by operation
     * for the given couple of selections.
     * 
     * @param operation The set operation to be performed (@see
     *        SetOperationType)
     * @return A new selection containing the entities resulting from the
     *         operation.
     * @throws IncompatibleTypeException If the two selections are incompatible
     *         with set operations.
     * @throws SelectionException If there is any problem accessing the
     *         selections.
     */

    private static Selection performSetOperation(Selection selectionA, Selection selectionB, SetOperationType operation)
	throws IncompatibleTypeException, SelectionException
    {
	// Check if we can perform the operation (will raise an exception if not).
	// Better generating the exception in this method but it adds too much complexity.
	checkSetOperationCompatibility(selectionA, selectionB);

	Set<Integer> idsSelectionA = selectionA.getEntityIds();
	Set<Integer> idsSelectionB = selectionB.getEntityIds();

	// Calculate the set operation over the ids.
	Set<Integer> operationIds = new HashSet<Integer>(idsSelectionA);

	switch(operation)
	{
	    case UNION:
		operationIds.addAll(idsSelectionB);
		break;

	    case INTERSECTION:
		log.debug("Calculating intersection");
		operationIds.retainAll(idsSelectionB);
		break;

	    case DIFFERENCE:
		operationIds.removeAll(idsSelectionB);
		break;

	    default:
		throw new IncompatibleTypeException("Unsupported set operation");
	}

	// Build the selection using the set of ids.
	return buildSelectionFromEntityIds(operationIds, selectionA, selectionB, idsSelectionA, idsSelectionB,
					   operation);
    }

    /**
     * Checks we can perform a set operation for the given selections. This
     * operation checks both selections are coming from the same catalogue and
     * refer to the same type of entity. It also checks we don't find duplicated
     * properties with a different type in each selection.
     * 
     * @throws IncompatibleTypeException If the selections are not compatible
     *         for a set operation.
     */

    private static void checkSetOperationCompatibility(Selection selectionA, Selection selectionB)
	throws IncompatibleTypeException
    {
	// Check the entity type and the catalog the entities are coming from are the same.
	if(!selectionA.getEntityType().equals(selectionB.getEntityType())
	   || (selectionA.getEntityCatalog().getId() != selectionB.getEntityCatalog().getId()))
	{
	    throw new IncompatibleTypeException("Error trying to perform a set operation over two selection containing"
						+ " different types of entities");
	}

	// Check we don't have duplicated properties with a different type.
	HashSet<String> duplicatedProperties = new HashSet<String>(selectionA.getEntityPropertyNames());
	duplicatedProperties.retainAll(selectionB.getEntityPropertyNames());

	for(String property : duplicatedProperties)
	    if(selectionA.getEntityPropertyType(property) != selectionB.getEntityPropertyType(property))
		throw new IncompatibleTypeException("Error trying to perform a set operation over two selections. "
						    + " Duplicated property " + property + " with different types: "
						    + selectionA.getEntityPropertyType(property) + " vs "
						    + selectionB.getEntityPropertyType(property));
    }

    /**
     * Builds a new selection from the given set of identifiers. The two
     * selections have to be compatible with set operations.
     * 
     * @return A new selection that contains all the entities whose id is
     *         present in the entityIds set.
     */

    private static Selection buildSelectionFromEntityIds(Set<Integer> entityIds, Selection selectionA,
							 Selection selectionB, Set<Integer> idsSelectionA,
							 Set<Integer> idsSelectionB, SetOperationType operation)
    {
	if(log.isDebugEnabled())
	    log.debug("Building selection from entity ids (" + entityIds.size() + " ids) (A: "
		      + selectionA.getSelectionSize() + ", B: " + selectionB.getSelectionSize() + ")");

	// Build the new selection, starting with the content of A.
	ArrayList<QueryableDataEntity> selectionEntities = new ArrayList<QueryableDataEntity>(entityIds.size());

	// Calculate the identifiers to request for each selection.
	// Notice after the following operations: selectionEntities = idsSelectionA UNION idsSelectionB
	idsSelectionA.retainAll(entityIds);
	idsSelectionB.retainAll(entityIds);
	idsSelectionB.removeAll(idsSelectionA);

	// Add the entities from both selections.
	selectionEntities.addAll(selectionA.collectEntitiesById(idsSelectionA));
	selectionEntities.addAll(selectionB.collectEntitiesById(idsSelectionB));

	// Calculate the dictionary for the new selection.
	// It is possible we have to expand it to accommodate new variable definitions.	
	PropertyDictionary combinedDictionary = new PropertyDictionary("CombinedSelectionEntity");

	// Add all the properties from the selections.
	copyPropertiesFromSelection(selectionA, combinedDictionary);
	copyPropertiesFromSelection(selectionB, combinedDictionary);

	// Return the proper implementation of the selection result.
	return instantiateSelectionImplemention(selectionA, selectionB, selectionEntities, combinedDictionary,
						operation);
    }

    /**
     * Copy all the properties of the given selection into the given dictionary
     * (if a property is already present we skip it).
     */

    private static void copyPropertiesFromSelection(Selection selection, PropertyDictionary dictionary)
    {
	List<String> propertyNames = selection.getEntityPropertyNames();

	for(String propertyName : propertyNames)
	    if(!dictionary.hasProperty(propertyName))
		dictionary.addProperty(selection.getPropertyDictionary().getPropertyDefinition(propertyName));
    }

    /**
     * Auxiliary method that checks the type of selection result to instantiate
     * and builds it.
     * 
     * @return A selection containing the given list of entities that uses an
     *         implementation coherent with the two source selections of the
     *         operation.
     */

    private static Selection instantiateSelectionImplemention(Selection selectionA, Selection selectionB,
							      List<QueryableDataEntity> entities,
							      PropertyDictionary selectionDictionary,
							      SetOperationType operation)
    {
	String entityType = selectionA.getEntityType();
	Catalog catalog = selectionA.getEntityCatalog();
	String combinedSelectionLabel = operation + "(" + selectionA.getSelectionLabel() + ","
					+ selectionB.getSelectionLabel() + ")";

	// Very UGLY but effective at this point.
	if(selectionA instanceof SelectionResultBackedByCachedDAO
	   && selectionB instanceof SelectionResultBackedByCachedDAO)
	{
	    QueryableDataEntityDAO cachedDAO = catalog.getEntityDAO(entityType);
	    return new SelectionResultBackedByCachedDAO(combinedSelectionLabel, entityType, catalog, entities,
							selectionDictionary, null, cachedDAO);
	}
	else
	    return new SelectionResult(combinedSelectionLabel, entityType, catalog, entities, selectionDictionary,
				       null, true);
    }

    /**
     * Enum that identifies the supported set operations.
     */

    public static enum SetOperationType
    {
	UNION,
	INTERSECTION,
	DIFFERENCE;
    }
}
