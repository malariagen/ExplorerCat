package net.explorercat.data;

import net.explorercat.cql.types.DataValue;

/**
 * Represents a data entity whose properties can be queried (values and type).
 * Entities are contained in catalogs, each catalog containing different sets of
 * entities that are grouped by type (for instance SNP, gene, etc.).
 * 
 * This interface extends Comparable to provide a default mechanism for sorting
 * entities. We assume entities to be sorted have the same type, belongs to the
 * same catalog and have unique identifiers so a simple implementation can just
 * sort the entities by id.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public interface QueryableDataEntity extends Comparable<QueryableDataEntity>
{
    /**
     * Gets a unique number that identifies this entity among all the entities
     * of the same kind (in the same catalog). Notice that the identifier is NOT
     * considered a property of the entity.
     * 
     * @return An integer that acts as a unique identifier for the entity among
     *         all the entities with the same type.
     */

    public int getId();

    /**
     * Gets a string representing the type of the entity (for instance SNP, CNV,
     * gene, etc.)
     */

    public String getType();

    /**
     * Gets the value for a property of the entity.
     * 
     * @param propertyName The name of the entity property (case sensitive).
     * @return The value as a data value object or null if the property is not
     *         present.
     */

    public DataValue getPropertyValue(String propertyName);
}
