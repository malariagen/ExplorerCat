package net.explorercat.dataaccess.cached;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.SelectionResult;
import net.explorercat.cql.selection.SelectionResultBackedByCachedDAO;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.selection.sorters.EntitySorter;
import net.explorercat.cql.selection.stats.StatsCalculationException;
import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.PropertyStatsLookup;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.QueryableDataEntityDAO;
import net.explorercat.util.misc.ArchitectureModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the entity DAO that relies on a memory cache. All entities
 * will be loaded into memory at startup time.
 * 
 * A DAO exception is raised if there is any problem loading the data into the
 * cache.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public abstract class QueryableDataEntityDAOCached implements QueryableDataEntityDAO, Selection
{
    // Logging
    private static Log log = LogFactory.getLog(QueryableDataEntityDAOCached.class);

    // Cached selection that contains all the entities.
    protected Selection cachedSelection;

    // Pre-calculated stats for properties (load from a DB table).
    protected PropertyStatsLookup preCalculatedStats;

    /**
     * Builds an empty DAO for entities. The initialize method should be called
     * before using it.
     */

    public QueryableDataEntityDAOCached() throws DAOException
    {
	cachedSelection = null;
	preCalculatedStats = null;
    }

    @Override
    public void initialize(String entityType, Catalog catalog) throws DAOException
    {
	if(log.isDebugEnabled())
	    log.debug("Creating cached entity DAO for " + entityType + ", catalog id: " + catalog.getId());

	// Calls the hook method in charge of initialise the cache.
	// This functionality has to be provided by a subclass.
	initializeCache(entityType, catalog);
    }

    @Override
    public List<QueryableDataEntity> findDataEntities(List<Integer> entityIds)
    {
	ArrayList<QueryableDataEntity> entities = new ArrayList<QueryableDataEntity>(entityIds.size());

	for(Integer id : entityIds)
	    entities.add(cachedSelection.getEntityById(id));

	return entities;
    }

    @Override
    public QueryableDataEntity findDataEntity(int entityId)
    {
	return cachedSelection.getEntityById(entityId);
    }

    @Override
    public String getEntityType()
    {
	return cachedSelection.getEntityType();
    }

    @Override
    public List<String> getEntityPropertyNames()
    {
	return cachedSelection.getEntityPropertyNames();
    }

    @Override
    public List<DataType> getEntityPropertyTypes()
    {
	return cachedSelection.getEntityPropertyTypes();
    }

    @Override
    public Iterator<QueryableDataEntity> getIterator() throws DAOException
    {
	try
	{
	    return cachedSelection.iterator();
	}
	catch(SelectionException e)
	{
	    throw new DAOException(e);
	}
    }

    /**
     * Hook method in charge of initialising the cache. This method has to be
     * implemented by subclasses bounded to a specific implementation (i.e.
     * MySQL).
     * 
     * @throws DAOException If there is a problem initializing the cache.
     */

    abstract protected void initializeCache(String entityType, Catalog catalog) throws DAOException;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Selection interface
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<QueryableDataEntity> getEntities(int numEntities, int offset) throws SelectionException
    {
	return cachedSelection.getEntities(numEntities, offset);
    }

    @Override
    public QueryableDataEntity getEntityById(int entityId)
    {
	return cachedSelection.getEntityById(entityId);
    }

    @Override
    public int getSelectionSize()
    {
	return cachedSelection.getSelectionSize();
    }

    @Override
    public String getSortingProperty()
    {
	return cachedSelection.getSortingProperty();
    }

    @Override
    public boolean isSortedByPropertyValue()
    {
	return cachedSelection.isSortedByPropertyValue();
    }

    @Override
    public Iterator<QueryableDataEntity> iterator() throws SelectionException
    {
	return cachedSelection.iterator();
    }

    @Override
    public Selection selectEntities(Query query) throws SelectionException
    {
	return cachedSelection.selectEntities(query);
    }

    @Override
    public void sortByPropertyValue(EntitySorter sorter)
    {
	cachedSelection.sortByPropertyValue(sorter);
    }

    @Override
    public float getStatsForProperty(String propertyName, StatsType statsType)
    {
	return preCalculatedStats.getPropertyStats(propertyName, statsType);
    }

    @Override
    public float getStatsForVariable(String variableName, Expression variableExpression, StatsType statsType)
	throws StatsCalculationException
    {
	return cachedSelection.getStatsForVariable(variableName, variableExpression, statsType);
    }

    @Override
    public Catalog getEntityCatalog()
    {
	return cachedSelection.getEntityCatalog();
    }

    @Override
    public boolean hasEntityProperty(String propertyName)
    {
	return cachedSelection.hasEntityProperty(propertyName);
    }

    @Override
    public Set<Integer> getEntityIds() throws SelectionException
    {
	return cachedSelection.getEntityIds();
    }

    @Override
    public DataType getEntityPropertyType(String propertyName)
    {
	return cachedSelection.getEntityPropertyType(propertyName);
    }

    @Override
    public PropertyDictionary getPropertyDictionary()
    {
	return cachedSelection.getPropertyDictionary();
    }

    @Override
    public boolean isSortedInAscendingOrder()
    {
	return cachedSelection.isSortedInAscendingOrder();
    }

    @Override
    public String getEntityPropertyDescription(String propertyName)
    {
	return cachedSelection.getEntityPropertyDescription(propertyName);
    }

    @Override
    public List<String> getEntityPropertyDescriptions()
    {
	return cachedSelection.getEntityPropertyDescriptions();
    }

    @Override
    public String getSelectionLabel()
    {
	return cachedSelection.getSelectionLabel();
    }

    @Override
    public long getSizeInBytes()
    {
	return cachedSelection.getSizeInBytes();
    }

    @Override
    public List<QueryableDataEntity> collectEntitiesById(Set<Integer> ids)
    {
	return cachedSelection.collectEntitiesById(ids);
    }

    @Override
    public boolean isCachedInMemory()
    {
	return true;
    }

    @Override
    public String getStringKey()
    {
	return "[DAO]";
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities()
    {
	return cachedSelection.getSelectionCopyWithSharedEntities();
    }

    @Override
    public Selection getSelectionCopyWithSharedEntities(String label)
    {
	return cachedSelection.getSelectionCopyWithSharedEntities(label);
    }

    /**
     * A specialisation of the SelectionResult class that uses a map to keep
     * track of the entity identifiers. This is implementation is memory
     * expensive so it must be ONLY used internally by cached DAOs.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 28 Sep 2010
     */

    protected static class SelectionResultBackedByMap extends SelectionResult
    {
	// Map for tracking entities by identifier.
	private Map<Integer, QueryableDataEntity> entityByIdMap;

	// Cached DAO that will be used to create SelectionResultBackedDAOCached
	// instances when performing selections.
	private QueryableDataEntityDAOCached cachedDAO;

	/**
	 * Builds a new selection based on a given list of entities.
	 * 
	 * @param entityType A string with the type of entity the selection
	 *        contains.
	 * @param catalog The catalog that contains this selection of entities.
	 * @param entities The list of entities contained by the selection.
	 * @param propertyDictionary A dictionary that relates each entity
	 *        property with its type.
	 * @param sorter Sorter used to sort the entities of the selection, null
	 *        if no sorter was used.
	 */

	public SelectionResultBackedByMap(String entityType, Catalog catalog, List<QueryableDataEntity> entities,
					  PropertyDictionary propertyDictionary, EntitySorter sorter,
					  QueryableDataEntityDAOCached cachedDAO)
	{
	    super("QueryableDataEntityDAOCached", entityType, catalog, entities, propertyDictionary, sorter, true);

	    // Set the cached DAO (it should be the DAO that contains this result).
	    this.cachedDAO = cachedDAO;

	    // Initializes the map.
	    int initialCapacity = (int) (entities.size() / 0.75 + entities.size());
	    entityByIdMap = new HashMap<Integer, QueryableDataEntity>(initialCapacity);

	    for(QueryableDataEntity entity : entities)
		entityByIdMap.put(entity.getId(), entity);
	}

	@Override
	public QueryableDataEntity getEntityById(int entityId)
	{
	    return entityByIdMap.get(entityId);
	}

	@Override
	public List<QueryableDataEntity> collectEntitiesById(Set<Integer> ids)
	{
	    List<QueryableDataEntity> collectedEntities = new ArrayList<QueryableDataEntity>(ids.size());

	    for(Integer id : ids)
	    {
		QueryableDataEntity entity = entityByIdMap.get(id);
		if(entity != null)
		    collectedEntities.add(entity);
	    }
	    return collectedEntities;
	}

	@Override
	public long getSizeInBytes()
	{
	    // Adding the references of the map (notice we are missing some memory overhead).
	    return super.getSizeInBytes() + (entities.size() * 3 * ArchitectureModel.getReferenceSizeInBytes());
	}

	@Override
	public Selection selectEntities(Query query) throws SelectionException
	{
	    // Decorates the selectEntities method since we have to return an 
	    // instance of the SelectionResultBackedByCachedDAO implementation.
	    Selection result = super.selectEntities(query);
	    List<QueryableDataEntity> selectedEntities = result.getEntities(result.getSelectionSize(), 0);
	    PropertyDictionary augmentedDictionary = result.getPropertyDictionary();

	    // This selection will replace the original one.
	    return new SelectionResultBackedByCachedDAO(query.getLabel(), entityType, catalog, selectedEntities,
							augmentedDictionary, query.getResultingSelectionSorter(),
							cachedDAO);
	}
    }
}
