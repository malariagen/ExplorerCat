package net.explorercat.data;

import java.util.HashMap;
import java.util.Map;

import net.explorercat.cql.types.DataValue;

/**
 * Decorates a data entity with added properties. This class is used to augment
 * entities with property/values calculated on the fly, like local variables
 * defined in queries. Notice that augmented properties will have preference
 * over properties with the same name (i.e. variables will overwrite property
 * values).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class AugmentedDataEntity implements QueryableDataEntity
{
    // Original data entity that will be augmented.
    private QueryableDataEntity dataEntity;

    // augmented properties.
    private Map<String, DataValue> extendedProperties;

    /**
     * Builds an augmented entity over an original data entity. Extra properties
     * can be added to the entity using the methods of this class.
     * 
     * @param originalDataEntity The entity that will be augmented with new
     *        properties.
     */

    public AugmentedDataEntity(QueryableDataEntity originalDataEntity)
    {
	dataEntity = originalDataEntity;
	extendedProperties = new HashMap<String, DataValue>();
    }

    /**
     * Adds a property with its associated value to the entity. A property with
     * the same name in the original entity will be overwritten by this one.
     * 
     * @param name Name of the property to be added.
     * @param value A DataValue object that encapsulates the value of the
     *        property.
     */

    public void addProperty(String name, DataValue value)
    {
	extendedProperties.put(name, value);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // QueryableDataEntity interface implementation.
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public DataValue getPropertyValue(String propertyName)
    {
	if(extendedProperties.containsKey(propertyName))
	    return extendedProperties.get(propertyName);
	else
	    return dataEntity.getPropertyValue(propertyName);
    }

    @Override
    public String getType()
    {
	return dataEntity.getType();
    }

    @Override
    public int getId()
    {
	return dataEntity.getId();
    }

    @Override
    public int compareTo(QueryableDataEntity otherEntity)
    {
	return dataEntity.getId() - otherEntity.getId();
    }
}
