package net.explorercat.data;

import java.util.List;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.RegularAttributeDefinition;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.RealValue;

/**
 * Represents the definition of an entity property. A property is just an
 * extension of an {@link AttributeDefinition} that includes an index value
 * indicating the position where the property value will be stored (internally)
 * by any entity object.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 Aug 2010
 */

public class PropertyDefinition extends RegularAttributeDefinition
{
    private int propertyIndexForEntities;

    /**
     * Creates a new property definition.
     * 
     * @param type Type of the property.
     * @param description Brief description of the property.
     * @param minimumValue Minimum value allowed for this property (null means
     *        no limit).
     * @param maximumValue Maximum value allowed for this property (null means
     *        no limit).
     * @param referencedEntity Name of the entity referenced by the property
     *        (null means no reference).
     * @param referencedProperty Name of the property referenced by this
     *        property (null means no reference).
     * @param allowedValues List of allowed values for the property (null means
     *        no restrictions).
     */

    public PropertyDefinition(String name, DataType type, String description, Float minimumValue,
			      Float maximumValue, String referencedEntity, String referencedProperty,
			      List<DataValue> allowedValues)
    {
	this.setName(name);
	this.setType(type);
	this.setDescription(description);

	this.setMinimumValue(minimumValue != null ? new RealValue(minimumValue) : null);
	this.setMaximumValue(maximumValue != null ? new RealValue(maximumValue) : null);
	this.setReference(referencedEntity, referencedProperty);

	for(DataValue currentValue : allowedValues)
	    this.registerAllowedValue(currentValue);

	// Invalid value, has to be updated by the dictionary container.
	this.propertyIndexForEntities = -1;

    }

    /**
     * Gets the index that must be used by any entity instance to access the
     * value of the property (internally). Notice that a value of -1 will mean
     * the definition has not been updated property.
     * 
     * @return The index where the value of the property can be found in entity
     *         instances.
     */

    public int getPropertyIndexForEntities()
    {
	return propertyIndexForEntities;
    }

    /**
     * Sets the index that must be used by any entity instance to access the
     * value of the property (internally).
     * 
     * @param index The index where the value of the property can be found in
     *        entity instances.
     */

    public void setPropertyValueIndexForEntities(int index)
    {
	this.propertyIndexForEntities = index;
    }

    /**
     * Synonymous method to be coherent with the class name (property instead of
     * attribute).
     * 
     * @return The name of the property referenced by this property or null if
     *         no property is referenced.
     */

    public String getReferencedProperty()
    {
	return this.getReferencedAttribute();
    }
}
