package net.explorercat.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.types.DataType;

/**
 * Dictionary of entity properties. A dictionary is used to keep track of the
 * types and constraints of the properties associated with an entity. This class
 * provides property indexing, meaning that entity instances will locate
 * property values (internally) using the indices provided by the dictionary. We
 * use this schema to save memory in each entity instance.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class PropertyDictionary
{
    // Type of entity whose properties this dictionary represents.
    private String entityType;

    // Associates property names with definitions.
    private Map<String, PropertyDefinition> propertyDictionary;

    // Precalculated list of property names.
    private List<String> propertyNames;

    // Index counters to keep track of the value indices by type.
    // Notice that we only use INTEGRAL data types
    // (counter for NULL type won't be used but we keep it for clarity).
    private int[] indexCountersByDataType;

    /**
     * Builds an empty dictionary for the given entity type.
     * 
     * @param entityType The type of entity whose properties will be represented
     *        by the dictionary.
     */

    public PropertyDictionary(String entityType)
    {
	this.entityType = entityType;
	this.propertyDictionary = new HashMap<String, PropertyDefinition>();
	this.propertyNames = new ArrayList<String>();

	// Initialize the index counters by type (only integral types).	
	DataType[] integralTypes = DataType.getIntegralTypes();
	this.indexCountersByDataType = new int[integralTypes.length];

	for(int i = 0; i < integralTypes.length; ++i)
	    this.indexCountersByDataType[i] = 0;
    }

    /**
     * Gets the type of entity this dictionary represents.
     */

    public String getEntityType()
    {
	return entityType;
    }

    /**
     * Adds a property definition to the dictionary.
     */

    public void addProperty(PropertyDefinition property)
    {
	// Update the index counter for the type of property being added.
	// Notice we have the translate the type into its integral equivalent.
	DataType equivalentIntegralType = property.getType().translateToIntegralType();
	int propertyValueIndex = indexCountersByDataType[equivalentIntegralType.ordinal()]++;
	property.setPropertyValueIndexForEntities(propertyValueIndex);

	// Register the property in the dictionary with its type, description and the index that 
	// will indicate in which position individual entity objects are storing the value for this property.
	propertyDictionary.put(property.getName(), property);
	propertyNames.add(property.getName());
    }

    /**
     * Gets a list with all the property names registered.
     */

    public List<String> getPropertyNames()
    {
	return propertyNames;
    }

    /**
     * Gets a list with the types of all the properties registered in the
     * dictionary. The order of the types is the same as the order provided by
     * the {@link getPropertyNames} method.
     */

    public List<DataType> getPropertyTypes()
    {
	List<String> propertyNames = getPropertyNames();
	ArrayList<DataType> propertyTypes = new ArrayList<DataType>(propertyNames.size());

	for(String property : propertyNames)
	    propertyTypes.add(propertyDictionary.get(property).getType());

	return propertyTypes;
    }

    /**
     * Gets a list with the descriptions of all the properties registered in the
     * dictionary. The order of the descriptions is the same as the order
     * provided by the {@link getPropertyNames} method.
     * 
     * @return A list that contains all the property descriptions, if a property
     *         does not have a description then null is provided.
     */

    public List<String> getPropertyDescriptions()
    {
	List<String> propertyNames = getPropertyNames();
	ArrayList<String> propertyDescriptions = new ArrayList<String>(propertyNames.size());

	for(String property : propertyNames)
	    propertyDescriptions.add(propertyDictionary.get(property).getDescription());

	return propertyDescriptions;
    }

    /**
     * Gets the type of a single property.
     * 
     * @return They type of the given property or null if the property wasn't
     *         registered.
     */

    public DataType getPropertyType(String propertyName)
    {
	PropertyDefinition definition = propertyDictionary.get(propertyName);

	if(definition != null)
	    return definition.getType();
	else
	    return null;
    }

    /**
     * Gets the description of a single property.
     * 
     * @return The description of the given property or null if no description
     *         was provided or the property wasn't registered
     */

    public String getPropertyDescription(String propertyName)
    {
	PropertyDefinition definition = propertyDictionary.get(propertyName);

	if(definition != null)
	    return definition.getDescription();
	else
	    return null;
    }

    /**
     * Gets the index that indicates in which position individual entity objects
     * are storing the value for this property. Method to be used only by entity
     * objects (notice the protected modifier).
     * 
     * @param propertyName The name of the property whose value index will be
     *        queried.
     * 
     * @return The index of the property value in an entity object (regarding
     *         the type of the property). It will return -1 if no property was
     *         registered with the given name.
     */

    protected int getPropertyValueIndexForEntities(String propertyName)
    {
	PropertyDefinition definition = propertyDictionary.get(propertyName);

	if(definition != null)
	    return definition.getPropertyIndexForEntities();
	else
	    return -1;
    }

    /**
     * Gets how many properties have been registered in the dictionary for a
     * given type.
     */

    public int getNumPropertiesOfType(DataType type)
    {
	DataType equivalentIntegralType = type.translateToIntegralType();
	return indexCountersByDataType[equivalentIntegralType.ordinal()];
    }

    /**
     * Checks if a property has been registered in the dictionary.
     */

    public boolean hasProperty(String propertyName)
    {
	return propertyDictionary.get(propertyName) != null;
    }

    /**
     * Gets the number of entity properties registered in the dictionary.
     */

    public int getNumProperties()
    {
	return propertyDictionary.size();
    }

    /**
     * Gets the definition for the property of the given name or null if there
     * isn't a property registered with that name.
     */

    public PropertyDefinition getPropertyDefinition(String propertyName)
    {
	return this.propertyDictionary.get(propertyName);
    }

    /**
     * Counts the number of array properties present in the dictionary.
     * 
     * @return The number of array properties (of any concrete type) in the
     *         dictionary.
     */

    public int getNumArrayProperties()
    {
	return getNumPropertiesOfType(DataType.ARRAY);
    }

}
