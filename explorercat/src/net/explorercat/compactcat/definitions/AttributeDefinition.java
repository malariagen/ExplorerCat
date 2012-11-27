package net.explorercat.compactcat.definitions;

import java.util.List;

import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;

/**
 * Represents the definition of an attribute made on a .dic file (following the
 * CompactCat file format). The only properties required by an attribute
 * definition are: name, description and type. A range or a collection of
 * allowed values can be specified. Any attribute can play the role of FK
 * (referencing an attribute present in another entity).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 1 Feb 2011
 */

public interface AttributeDefinition
{
    /**
     * Gets the name of the attribute.
     */

    public String getName();

    /**
     * Gets a brief description of the attribute meaning.
     */

    public String getDescription();

    /**
     * Gets the data type of the attribute.
     */

    public DataType getType();

    /**
     * Checks if the attribute is a scalar (non-array).
     */

    public boolean isScalar();

    /**
     * Checks if the attribute is an array (of any type).
     */
    
    public boolean isArray();

    /**
     * Gets the minimum value allowed for this attribute (only numeric values
     * make sense here).
     */

    public DataValue getMinimumValue();

    /**
     * Gets the maximum value allowed for this attribute (only numeric values
     * make sense here).
     */

    public DataValue getMaximumValue();

    /**
     * Gets the name of the entity referenced by this attribute.
     * 
     * @return The name of the entity referenced by this attribute or null if no
     *         entity is being referenced.
     */

    public String getReferencedEntity();

    /**
     * Gets the name of the attribute referenced by this attribute.
     * 
     * @return The name of the attribute referenced or null if no attribute is
     *         being referenced.
     */

    public String getReferencedAttribute();

    /**
     * Checks if this attribute is a reference to an attribute in another
     * entity.
     * 
     * @return True if this attribute is a reference (referenced entity and
     *         attribute has been specified), false otherwise.
     */

    public boolean isReference();

    /**
     * Gets a list containing the values allowed for this attribute.
     * 
     * @return The list of allowed values for the attribute, an empty list means
     *         anything is allowed.
     */

    public List<String> getAllowedValues();

    /**
     * Checks if a data value is valid for this attribute. It will check the
     * type, the minimum and maximum limits (if the have been specified) and the
     * list of allowed values registered (if there is at least one).
     * 
     * @param valueToCheck Value that will be checked.
     * @return True if the value is valid for the attribute or false otherwise.
     */

    public boolean isValidValue(DataValue valueToCheck);

    /**
     * Gets the attribute definition for the unique id automatically added to
     * any entity. (This is a special case)
     * 
     * @return The attribute definition that represents the unique identifier of
     *         an entity.
     */

    public AttributeDefinition getUniqueIdDefinition();

    /**
     * Checks if the current definition represents the unique id special case.
     * 
     * @return True if this instance represents the special unique id attribute.
     */

    public boolean isUniqueIdDefinition();

}
