package net.explorercat.cql.selection;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.util.cache.SizeMeasureable;

/**
 * An interface to be implemented by any class that represents selections of
 * data entities. Different implementations will be backed by different storage
 * mechanism: memory cache, DB access, etc.
 * 
 * This is a long interface but is the philosopher's stone of the system, it is
 * central to the CQL language whose functionality is always oriented to work
 * with selections.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date July 2010
 */

public interface Selection extends SizeMeasureable
{

    /**
     * Gets the label associated with the selection or null if no label is
     * associated with it.
     * 
     * @return The label associated with the selection or null if no label was
     *         specified.
     */

    public String getSelectionLabel();

    /**
     * Returns the type of the entities contained in this selection.
     * 
     * @return A string with the type of the entities of the selection.
     */

    public String getEntityType();

    /**
     * Gets a list with all the property names for the type of entity of the
     * selection.
     * 
     * @return A list containing all the property names for the selection entity
     *         type. This method is not returning always a copy so client code
     *         must not modify the object.
     */

    public List<String> getEntityPropertyNames();

    /**
     * Gets a list with all the property types for the kind of entity of the
     * selection. The order of the descriptions is the same as the order
     * provided by the getPropertyNames method.
     * 
     * @return A list containing all the property types using the same order as
     *         the getEntityPropertyNames method.
     */

    public List<DataType> getEntityPropertyTypes();

    /**
     * Makes a copy of the selection that shares the same entities but can be
     * sorted independently. This means any modification made to the entities
     * will affect all the other copies of the selection.
     * 
     * @return A copy of the selection that shares the same entities.
     */

    public Selection getSelectionCopyWithSharedEntities();

    /**
     * Makes a copy of the selection with a different label that shares the same
     * entities but can be sorted independently. This means any modification
     * made to the entities will affect all the other copies of the selection.
     * 
     * @param label The label that will be assigned to the copy.
     * @return A copy of the selection that shares the same data but has a
     *         different label.
     */

    public Selection getSelectionCopyWithSharedEntities(String label);

    /**
     * Gets a list with the descriptions of all the properties for the kind of
     * entity of the selection. The order of the descriptions is the same as the
     * order provided by the getPropertyNames method.
     * 
     * @return A list that contains all the property descriptions, if a property
     *         does not have a description then null is provided. This method is
     *         not always returning a copy so client code must not modify the
     *         object.
     */

    public List<String> getEntityPropertyDescriptions();

    /**
     * Checks if the entity type of the selection has a property with the given
     * name.
     * 
     * @param propertyName The name of the property to be checked.
     * @return True if the property is present, false otherwise.
     */

    public boolean hasEntityProperty(String propertyName);

    /**
     * Gets the type of a single property for the type of entity provided by the
     * selection.
     * 
     * @param propertyName The name of the property for which we want to get the
     *        type.
     * @return The type of the given property or null if the property wasn't
     *         registered.
     */

    public DataType getEntityPropertyType(String propertyName);

    /**
     * Gets the description of a single property for the type of entity provided
     * by the selection.
     * 
     * @param propertyName The name of the property for which we want to get the
     *        description. This method is not returning a copy so client code
     *        must not modify the object.
     * @return The description of the given property or null if the property
     *         wasn't registered or no description was provided.
     */

    public String getEntityPropertyDescription(String propertyName);

    /**
     * Generates a new selection of entities based on the query passed as
     * parameter.
     * 
     * @param query The query that will be evaluated and executed to select the
     *        entities.
     * @return A new selection that contains all the entities of the current one
     *         that has been selected by the given query. Note that the entities
     *         in the returned selection are NOT copied so client code must not
     *         modify them.
     * @throws SelectionException If there is any error executing the query.
     */

    public Selection selectEntities(Query query) throws SelectionException;

    /**
     * Returns an iterator that allows to iterate trough all the elements of the
     * selection.
     * 
     * @return A standard iterator that can be used to iterate the selection.
     * @throws SelectionException If there is any problem accessing the data
     *         required to build the iterator.
     */

    public Iterator<QueryableDataEntity> iterator() throws SelectionException;

    /**
     * Gets a list with "numEntities" entities starting at the position of the
     * given offset.
     * 
     * @param numEntities The maximum number of entities to be selected. Less
     *        entities could be returned if there are not enough in the
     *        selection.
     * @param offset The first position that will be selected.
     * @return A list of numEntities (if the selection is big enough) starting
     *         at the offset position from this selection. This method is not
     *         returning a copy of the entities so client code must not modify
     *         them.
     * @throws SelectionException If there is any problem accessing the
     *         underlying data.
     */

    public List<QueryableDataEntity> getEntities(int numEntities, int offset) throws SelectionException;

    /**
     * Sorts the entities of the selection by a property value using a selection
     * sorter.
     * 
     * @param sorter The object that will be used to sort the entities of the
     *        selection.
     */

    public void sortByPropertyValue(EntitySorter sorter);

    /**
     * Check if the selection has been sorted by a property value.
     * 
     * @return True if the entities are sorted according to the value of a
     *         property.
     */

    public boolean isSortedByPropertyValue();

    /**
     * Gets the name of the property that has been used to sort the entities.
     * 
     * @return A string with the name of the property or null if the entities
     *         haven't been sorted (notice that the identifier is NOT considered
     *         a property of an entity). This method is not returning a copy so
     *         client code must not modify the object.
     */

    public String getSortingProperty();

    /**
     * Checks if the selection has been sorted by a property value in ascending
     * order.
     * 
     * @return True if the selection has been sorted using ascending order,
     *         false otherwise. Notice that this method has no meaning if the
     *         isSortedByPropertyValue method returns false.
     */

    public boolean isSortedInAscendingOrder();

    /**
     * Gets the size of the selection.
     * 
     * @return The number of elements in the selection.
     */

    public int getSelectionSize();

    /**
     * Gets a string that identifies the selection query uniquely. Any
     * input/referenced selection and their queries are included as a part of
     * the string. This identifier can be used as a key when caching the
     * queries/selections. Notice this method does NOT return the same result as
     * the toString method and does NOT use the selection label in the key
     * generated.
     * 
     * @return A string that identifies uniquely the selection query.
     */

    public String getStringKey();

    /**
     * Checks if the selection is cached in memory or if it is accessing an
     * external data source (like a DB).
     * 
     * @return True if the selection entities are cached in memory, false
     *         otherwise.
     */

    public boolean isCachedInMemory();

    /**
     * Gets the entity associated with the given id or null if the entity is not
     * present in the selection.
     * 
     * @param entityId The unique identifier of the entity.
     * @return The entity associated with the id or null if the entity was not
     *         present. This method is not returning a copy so client code must
     *         not modify the object.
     */

    public QueryableDataEntity getEntityById(int entityId);

    /**
     * Collects the entities with the given identifiers.
     * 
     * @param ids A set containing the identifiers of the entities to be
     *        collected.
     * @return A list containing the entities of the selection whose identifier
     *         matches one of the given set.
     */

    public List<QueryableDataEntity> collectEntitiesById(Set<Integer> ids);

    /**
     * Gets the catalog that contains all the entities of the selection.
     * 
     * @return The catalog from where the selection is coming from. This method
     *         is not returning a copy so client code must not modify the
     *         object.
     */

    public Catalog getEntityCatalog();

    /**
     * Gets the unique identifiers of all the entities present in the selection.
     * 
     * @return A set containing all the entity identifiers.
     * @throws SelectionException If there is any problem accessing the
     *         underlying data.
     */

    public Set<Integer> getEntityIds() throws SelectionException;

    /**
     * Gets the property dictionary for the entity type of the selection.
     * 
     * @return A dictionary that relates any entity property with its type. This
     *         method is not returning a copy so client code must not modify the
     *         object.
     */

    public PropertyDictionary getPropertyDictionary();

    /**
     * Gets the value of a stats measurement for a given property.
     * 
     * @param propertyName The name of the property for which we want to query
     *        the stats.
     * @param statsType The type of stats we want (AVG, MAX, etc.)
     * @return The value of the stats measurement for the given property using
     *         all the entities of the selection.
     * @throws PropertyAccessException If there is any problem trying to
     *         access/calculate the stats value.
     * @throws StatsCalculationException If there was an error trying to
     *         calculate the stats.
     */

    public float getStatsForProperty(String propertyName, StatsType statsType) throws StatsCalculationException;

    /**
     * Gets the value of a stats measurement for a given variable.
     * 
     * @param variableName The name of the variable for which we want to query
     *        the stats.
     * @param variableExpression The expression that defines the variable.
     * @param statsType The type of stats we want (AVG, MAX, etc.)
     * @return The value of the stats measurement for the given variable using
     *         all the entities of the selection.
     * @throws StatsCalculationException If there was an error trying to
     *         calculate the stats.
     */

    public float getStatsForVariable(String variableName, Expression variableExpression, StatsType statsType)
	throws StatsCalculationException;
}
