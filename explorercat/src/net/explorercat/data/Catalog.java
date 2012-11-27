package net.explorercat.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DateValue;
import net.explorercat.cql.types.IntegerValue;
import net.explorercat.cql.types.StringValue;
import net.explorercat.dataaccess.QueryableDataEntityDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents a catalog, a collection of entities with associated metadata
 * (properties). Notice a catalog can behave itself as an entity (implements its
 * interface).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class Catalog implements QueryableDataEntity
{
    // Logging
    private static Log log = LogFactory.getLog(Catalog.class);

    private int id;
    private String name;

    private GregorianCalendar releaseDate;
    private String version;
    private String description;

    // Map of DAOs that will provide the entities contained in the catalog.
    private Map<String, QueryableDataEntityDAO> entityDAOs;
    private List<String> entityTypes;
    private List<String> entityDescriptions;

    /**
     * Builds a new catalog with the given id and metadata attributes.
     */

    public Catalog(int catalogId, String catalogName, GregorianCalendar releaseDate, String version, String description)
    {
	this.id = catalogId;
	this.name = catalogName;
	this.releaseDate = releaseDate;
	this.version = version;
	this.description = description;

	this.entityDAOs = new HashMap<String, QueryableDataEntityDAO>();
	this.entityTypes = new ArrayList<String>();
	this.entityDescriptions = new ArrayList<String>();
    }

    /**
     * Gets the name associated with the catalog.
     * 
     * @return A descriptive name given by the user.
     */

    public String getName()
    {
	return name;
    }

    /**
     * Gets the release date of the catalog.
     */

    public GregorianCalendar getReleaseDate()
    {
	return releaseDate;
    }

    public String getReleaseDateAsString()
    {
	int year = releaseDate.get(GregorianCalendar.YEAR);
	int month = releaseDate.get(GregorianCalendar.DAY_OF_MONTH);
	int day = releaseDate.get(GregorianCalendar.MONTH) + 1; // Jan = 0
	String date = year + "-" + month + "-" + day;

	return date;
    }

    /**
     * Gets the version of the catalog.
     */

    public String getVersion()
    {
	return version;
    }

    /**
     * Gets a brief description of the catalog.
     */

    public String getDescription()
    {
	return this.description;
    }

    /**
     * Registers a type of entity in the catalog.
     * 
     * @param entityType The name (type) of the entity that will be added to the
     *        catalog.
     * @param entityDescription Brief description of the entity.
     */

    public void registerEntityType(String entityType, String entityDescription)
    {
	this.entityTypes.add(entityType);
	this.entityDescriptions.add(entityDescription);
    }

    /**
     * Adds an entity DAO in charge of providing a type of entity contained in
     * the catalog.
     * 
     * @param entityType Type of entity for which a DAO is provided.
     * @param dao The DAO for that kind of entity.
     */

    public void registerEntityDAO(String entityType, QueryableDataEntityDAO dao)
    {
	if(log.isDebugEnabled())
	    log.debug("Registering entity DAO for " + entityType + " in catalog: " + id);

	entityDAOs.put(entityType, dao);
    }

    /**
     * Gets a DAO for a given type of entity contained in the catalog.
     * 
     * @param entityType The type of the entities the DAO will supply.
     * @return The DAO for the provided type of entity or null if there is not
     *         DAO associated with it.
     */

    public QueryableDataEntityDAO getEntityDAO(String entityType)
    {
	return entityDAOs.get(entityType);
    }

    /**
     * Gets a list of strings representing all the entity types contained in
     * this catalog.
     * 
     * @return List of entity names (representing the types for this catalog).
     */

    public List<String> getEntityTypes()
    {
	return entityTypes;
    }

    /**
     * Gets a list containing the description of all the entities of the catalog
     * (same order as {@link getEntityTypes}).
     */

    public List<String> getEntityDescriptions()
    {
	return entityDescriptions;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // QueryableDataEntity interface.
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getId()
    {
	return id;
    }

    @Override
    public String getType()
    {
	return "catalog";
    }

    @Override
    public int compareTo(QueryableDataEntity otherEntity)
    {
	return id - otherEntity.getId();
    }

    @Override
    public DataValue getPropertyValue(String propertyName)
    {
	// Property map emulation to make it compatible with queryable entities :)

	if(propertyName.equals("id"))
	    return new IntegerValue(id);

	else if(propertyName.equals("date") || propertyName.equals("releaseDate"))
	{
	    int year = releaseDate.get(GregorianCalendar.YEAR);
	    int month = releaseDate.get(GregorianCalendar.MONTH);
	    int day = releaseDate.get(GregorianCalendar.DAY_OF_MONTH);
	    return new DateValue(year, month, day);
	}

	else if(propertyName.equals("version"))
	    return new StringValue(version);

	else if(propertyName.equals(description))
	    return new StringValue(description);

	else
	    return null;
    }
}
