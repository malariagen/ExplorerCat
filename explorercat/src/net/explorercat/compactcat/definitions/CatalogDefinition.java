package net.explorercat.compactcat.definitions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.types.DataValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents the definition of a catalog read from a .dic file (following the
 * CompactCat file format). A definition requires the following properties:
 * name, version, releaseDate, description and a list of entity definitions.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 1 Feb 2011
 */

public class CatalogDefinition
{
    // Logging
    private static Log log = LogFactory.getLog(CatalogDefinition.class);

    private String name;
    private String version;
    private DataValue releaseDate;
    private String description;

    private Map<String, EntityDefinition> entityDefinitions;

    /**
     * Builds an empty catalog definition. Setter methods must be used to
     * configure the object.
     */

    public CatalogDefinition()
    {
	this.entityDefinitions = new HashMap<String, EntityDefinition>();
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getVersion()
    {
	return version;
    }

    public void setVersion(String version)
    {
	this.version = version;
    }

    public DataValue getReleaseDate()
    {
	return releaseDate;
    }

    public void setReleaseDate(DataValue releaseDate)
    {
	this.releaseDate = releaseDate;
    }

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    public void addEntityDefinition(EntityDefinition entity) throws DefinitionException
    {
	if(!this.entityDefinitions.containsKey(entity.getName()))
	    this.entityDefinitions.put(entity.getName(), entity);
	else
	{
	    log.error("Entity already registered: " + entity.getName());
	    throw new DefinitionException("Entity " + entity.getName() + " already registered");
	}
    }

    /**
     * Gets the entity definition for the entity of the given name or null if
     * the entity is not registered in the catalog.
     */

    public EntityDefinition getEntityDefinition(String entityName)
    {
	return this.entityDefinitions.get(entityName);
    }

    /**
     * Gets the names of the registered entities in a collection.
     */

    public Collection<String> getEntityNames()
    {
	return this.entityDefinitions.keySet();
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder();

	buffer.append("catalog\n{\n");
	buffer.append("\tname: ").append(name).append(",\n");
	buffer.append("\tversion: \"").append(version).append("\",\n");
	buffer.append("\tdate: ").append(releaseDate).append(",\n");
	buffer.append("\tdescription: \"").append(description).append("\",\n");

	buffer.append("\n\tdictionary:\n\t{\n");

	for(EntityDefinition entity : this.entityDefinitions.values())
	{
	    buffer.append(entity.toString()).append(",\n");
	}

	buffer.delete(buffer.length() - 2, buffer.length());
	buffer.append("\n\t}").append("\n}");

	return buffer.toString();
    }

    /**
     * Gets a list containing all the entity definitions within this catalog.
     * 
     * @return A list containing the definition of all the entities defined in
     *         this catalog.
     */

    public List<EntityDefinition> getEntityDefinitions()
    {
	return new ArrayList<EntityDefinition>(this.entityDefinitions.values());
    }
}
