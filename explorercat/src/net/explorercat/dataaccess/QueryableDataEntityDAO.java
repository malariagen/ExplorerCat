package net.explorercat.dataaccess;

import java.util.Iterator;
import java.util.List;

import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.types.DataType;
import net.explorercat.data.Catalog;
import net.explorercat.data.QueryableDataEntity;

/**
 * DAO in charge of providing queryable data entities of a given type for a
 * specific catalog. Notice the DAO has be configured during building time,
 * specifying the type of entity and the containing catalog. This means a
 * different instance of the DAO is necessary for each type of entity in each
 * catalog
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public interface QueryableDataEntityDAO extends Selection
{
    /**
     * Initializes the DAO specifying the type of entity it will be providing
     * and the catalog where these entities will be contained.
     * 
     * @param entityType The type of the entity.
     * @param catalog The catalog that contains all the entities.
     * @throws DAOException If there is any problem initializing the DAO.
     */

    public void initialize(String entityType, Catalog catalog) throws DAOException;

    /**
     * Gets the type of the data entities this DAO is providing.
     * 
     * @return The type of the entities this DAO provides.
     */

    @Override
    public String getEntityType();

    /**
     * Finds an entity for a given identifier.
     * 
     * @param entityId The unique id of the entity we want to retrieve.
     * @return The data entity object or null if it wasn't found.
     */

    public QueryableDataEntity findDataEntity(int entityId) throws DAOException;

    /**
     * Finds a set of entities based on their unique identifiers.
     * 
     * @param entityIds The identifiers of the entities we want to retrieve.
     * @return A list that contains an entity for each identifier or null if the
     *         entity wasn't found.
     * @throws DAOException If there is a problem accessing the data.
     */

    public List<QueryableDataEntity> findDataEntities(List<Integer> entityIds) throws DAOException;

    /**
     * Gets an iterator that allows to iterate through all the entities of the
     * catalog.
     * 
     * @return An iterator that can iterate through all the entities contained
     *         in the catalog.
     * @throws DAOException If there is a problem accessing the data.
     */

    public Iterator<QueryableDataEntity> getIterator() throws DAOException;

    /**
     * Gets all the properties for the entity type this DAO is providing.
     * 
     * @return A list with the names of the properties registered for this
     *         entity type.
     */

    @Override
    public List<String> getEntityPropertyNames();

    /**
     * Gets the types of the entity properties.
     * 
     * @return A list with the types of the properties registered for this
     *         entity type, in the same order as the property names are
     *         retrieved by the getEntityPropertyNames method.
     */

    @Override
    public List<DataType> getEntityPropertyTypes();

    /**
     * Gets the catalog that contains all the entities for which this DAO
     * provides access.
     * 
     * @return The catalog that contains the entities provided by this DAO.
     */

    @Override
    public Catalog getEntityCatalog();
}
