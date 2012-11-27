package net.explorercat.data;

import java.util.BitSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import net.explorercat.cql.types.ArrayValue;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DateValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.IntegerValue;
import net.explorercat.cql.types.NullValue;
import net.explorercat.cql.types.RealValue;
import net.explorercat.cql.types.StringValue;

/**
 * Represents a generic data entity with a set of properties that can be
 * queried. This is a simple implementation of the {@link QueryableDataEntity}
 * interface.
 * 
 * TODO Refactor this class, has become a monster blob!
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class DataEntity implements QueryableDataEntity
{
    private int entityId;
    private PropertyDictionary propertyDictionary;

    // Note we use an optimized representation of values using one array per integral type.
    // We query the property dictionary in order to calculate the index where 
    // a property value is stored. We use a bit set to track null values when dealing
    // with non java objects (int, float and boolean). By default all values are null
    // until they are registered in the entity.

    private int[] integerPropertyValues;
    private BitSet integerPropertyValuesNonNullFlags;

    private float[] realPropertyValues;
    private BitSet realPropertyValuesNonNullFlags;

    private boolean[] booleanPropertyValue;
    private BitSet booleanPropertyValuesNonNullFlags;

    // Dates and string values
    private GregorianCalendar[] datePropertyValues;
    private String[] stringPropertyValues;

    // Arrays are represented directly as genuine DataValues (no optimization).
    // Notice that any concrete array type is mapped to the ARRAY integral type.
    private ArrayValue[] arrayPropertyValues;

    /**
     * Builds an empty entity.
     * 
     * @param entityId An integer that identifies uniquely the entity among all
     *        the instances of the same entity type coming from the same
     *        catalog.
     * @param propertyDictionary The dictionary that contains all the property
     *        definitions for this kind of entity.
     */

    public DataEntity(int entityId, PropertyDictionary propertyDictionary)
    {
	this.entityId = entityId;
	this.propertyDictionary = propertyDictionary;

	// Initializes the value arrays
	DataType[] types = DataType.getIntegralTypes();

	// This can become obsolete if we add a new integral data type! 
	// (an exception is thrown if detected).
	for(DataType type : types)
	{
	    switch(type)
	    {
		case INTEGER:
		    if(integerPropertyValues == null)
		    {
			integerPropertyValues = new int[propertyDictionary.getNumPropertiesOfType(type)];
			integerPropertyValuesNonNullFlags = new BitSet(integerPropertyValues.length);
		    }
		    break;

		case BOOLEAN:
		    if(booleanPropertyValue == null)
		    {
			booleanPropertyValue = new boolean[propertyDictionary.getNumPropertiesOfType(type)];
			booleanPropertyValuesNonNullFlags = new BitSet(booleanPropertyValue.length);
		    }
		    break;

		case REAL:
		    if(realPropertyValues == null)
		    {
			realPropertyValues = new float[propertyDictionary.getNumPropertiesOfType(type)];
			realPropertyValuesNonNullFlags = new BitSet(realPropertyValues.length);
		    }
		    break;

		case STRING:
		    if(stringPropertyValues == null)
			stringPropertyValues = new String[propertyDictionary.getNumPropertiesOfType(type)];
		    break;

		case DATE:
		    if(datePropertyValues == null)
			datePropertyValues = new GregorianCalendar[propertyDictionary.getNumPropertiesOfType(type)];
		    break;

		// Nothing to do for null, we do not store this type.
		case NULL:
		    break;

		case ARRAY:
		    if(arrayPropertyValues == null)
			arrayPropertyValues = new ArrayValue[propertyDictionary.getNumArrayProperties()];
		    break;

		default:
		    throw new IllegalArgumentException("Error, unknown type: " + type);
	    }
	}
    }

    /**
     * Checks if the value of a property is null for this entity.
     * 
     * @return True if the property value is null or false otherwise.
     */

    public boolean isPropertyValueNull(String propertyName)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.INTEGER);
	DataType type = propertyDictionary.getPropertyType(propertyName);

	switch(type)
	{
	    case INTEGER:
		return !integerPropertyValuesNonNullFlags.get(valueIndex);

	    case BOOLEAN:
		return !booleanPropertyValuesNonNullFlags.get(valueIndex);

	    case REAL:
		return !realPropertyValuesNonNullFlags.get(valueIndex);

	    case STRING:
	    case TEXT:
		return stringPropertyValues[valueIndex] == null;

	    case DATE:
		return datePropertyValues[valueIndex] == null;

		//case NULL:
		//return true;		

	    default:
		throw new IllegalArgumentException("Error, unknown type : " + type);
	}
    }

    /**
     * Adds a property value to the entity.
     * 
     * @param propertyName The name of the property.
     * @param propertyValue A DataValue object that encapsulates the value of
     *        the property. The type of the property and the type of the value
     *        must match or be compatible, if not an IllegalArgumentException is
     *        thrown.
     */

    public void addPropertyValue(String propertyName, DataValue propertyValue)
    {
	int valueIndex = propertyDictionary.getPropertyValueIndexForEntities(propertyName);
	DataType propertyType = propertyDictionary.getPropertyType(propertyName);

	// Check the property was registered in the dictionary.
	if(valueIndex == -1)
	    throw new IllegalArgumentException("Error, property " + propertyName + " was not registered "
					       + "in the dictionary for entity " + propertyDictionary.getEntityType());
	try
	{
	    switch(propertyType.translateToIntegralType())
	    {
		case INTEGER:
		    integerPropertyValues[valueIndex] = propertyValue.getValueAsInteger();
		    integerPropertyValuesNonNullFlags.set(valueIndex);
		    break;

		case BOOLEAN:
		    booleanPropertyValue[valueIndex] = propertyValue.getValueAsBoolean();
		    booleanPropertyValuesNonNullFlags.set(valueIndex);
		    break;

		case REAL:
		    realPropertyValues[valueIndex] = propertyValue.getValueAsReal();
		    realPropertyValuesNonNullFlags.set(valueIndex);
		    break;

		case STRING:
		    stringPropertyValues[valueIndex] = propertyValue.getValueAsString();
		    break;

		case DATE:
		    datePropertyValues[valueIndex] = propertyValue.getValueAsDate();
		    break;

		case ARRAY:
		    arrayPropertyValues[valueIndex] = new ArrayValue(propertyValue.getValueAsArray());
		    break;

		default:
		    throw new IllegalArgumentException("Error, unknown type of property. Did you add "
						       + "a new type of property to the system without "
						       + "updating the DataEntity code?");
	    }
	}
	catch(IncompatibleTypeException e)
	{
	    throw new IllegalArgumentException("Error adding a property value to an entity, the type of the"
					       + " property and the provided value are not compatible: " + propertyType
					       + " vs. " + propertyValue.getType());
	}
    }

    /**
     * Auxiliary method that finds the index to store/retrieve a property value
     * checking if the property is registered in the dictionary and property and
     * value types match. An IllegalArgumentException is thrown is property and
     * value are not coherent.
     */

    private int findPropertyValueIndex(String propertyName, DataType valueType)
    {
	int valueIndex = propertyDictionary.getPropertyValueIndexForEntities(propertyName);
	DataType propertyType = propertyDictionary.getPropertyType(propertyName);

	// Check the property was registered in the dictionary.
	if(valueIndex == -1)
	    throw new IllegalArgumentException("Error, property " + propertyName + " was not registered "
					       + "in the dictionary for entity " + propertyDictionary.getEntityType());

	// Check we the types match (once translated into integral types).
	// Notice we allow arrays to match any array type (it could "potentially" contain a mix of types).
	if(propertyType.translateToIntegralType() != valueType)
	    throw new IllegalArgumentException("Error finding a property(" + propertyName + ") value, the type of the"
					       + " property is " + propertyType + " (must be " + valueType + ")");

	return valueIndex;
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder();
	buffer.append("[" + propertyDictionary.getEntityType() + " : " + entityId + "] { ");

	List<String> properties = propertyDictionary.getPropertyNames();

	for(String propertyName : properties)
	{
	    buffer.append(propertyName + " : ");
	    DataValue value = getPropertyValue(propertyName);

	    if(value != null)
		buffer.append(value.toString() + ", ");
	    else
		buffer.append("null , ");
	}

	buffer.append(" }");
	return buffer.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Concrete type getters and setters (speeds up the access to the data).
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Adds an integer property value to the entity. The type of the property
     * and the type of the value must match or be compatible, if not an
     * IllegalArgumentException is thrown.
     */

    public void addPropertyValue(String propertyName, int value)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.INTEGER);
	integerPropertyValues[valueIndex] = value;
	integerPropertyValuesNonNullFlags.set(valueIndex);
    }

    /**
     * Gets the integer value of a property. If the property has a different
     * type, it is not registered for this kind of entity or has a null value an
     * IllegalArgumentException is thrown.
     */

    public int getIntegerPropertyValue(String propertyName)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.INTEGER);

	if(!integerPropertyValuesNonNullFlags.get(valueIndex))
	    throw new IllegalArgumentException("Error trying to access a null value for property: " + propertyName);

	return integerPropertyValues[valueIndex];
    }

    /**
     * Adds a string property value to the entity. The type of the property and
     * the type of the value must match or be compatible, if not an
     * IllegalArgumentException is thrown.
     */

    public void addPropertyValue(String propertyName, String value)
    {
	stringPropertyValues[findPropertyValueIndex(propertyName, DataType.STRING)] = value;
    }

    /**
     * Gets the string value of a property. If the property has a different
     * type, it is not registered for this kind of entity an
     * IllegalArgumentException is thrown.
     */

    public String getStringPropertyValue(String propertyName)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.STRING);
	return stringPropertyValues[valueIndex];
    }

    /**
     * Adds a real value to the entity. The type of the property and the type of
     * the value must match or be compatible, if not an IllegalArgumentException
     * is thrown.
     */

    public void addPropertyValue(String propertyName, float value)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.REAL);
	realPropertyValues[valueIndex] = value;
	realPropertyValuesNonNullFlags.set(valueIndex);
    }

    /**
     * Gets the real value of a property. If the property has a different type,
     * it is not registered for this kind of entity an IllegalArgumentException
     * is thrown.
     */

    public float getRealPropertyValue(String propertyName)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.REAL);

	if(!realPropertyValuesNonNullFlags.get(valueIndex))
	    throw new IllegalArgumentException("Error trying to access a null value for property: " + propertyName);

	return realPropertyValues[valueIndex];
    }

    /**
     * Gets the date value of a property. If the property has a different type,
     * it is not registered for this kind of entity an IllegalArgumentException
     * is thrown.
     */

    public GregorianCalendar getDatePropertyValue(String propertyName)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.DATE);
	return datePropertyValues[valueIndex];
    }

    /**
     * Adds a date value to the entity. The type of the property and the type of
     * the value must match or be compatible, if not an IllegalArgumentException
     * is thrown.
     */

    public void addPropertyValue(String propertyName, GregorianCalendar value)
    {
	datePropertyValues[findPropertyValueIndex(propertyName, DataType.DATE)] = value;
    }

    /**
     * Adds a boolean property value to the entity. The type of the property and
     * the type of the value must match or be compatible, if not an
     * IllegalArgumentException is thrown.
     */

    public void addPropertyValue(String propertyName, boolean value)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.BOOLEAN);
	booleanPropertyValue[valueIndex] = value;
	booleanPropertyValuesNonNullFlags.set(valueIndex);
    }

    /**
     * Gets the boolean value of a property. If the property has a different
     * type, it is not registered for this kind of entity or has a null value an
     * IllegalArgumentException is thrown.
     */

    public boolean getBooleanPropertyValue(String propertyName)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.BOOLEAN);

	if(!booleanPropertyValuesNonNullFlags.get(valueIndex))
	    throw new IllegalArgumentException("Error trying to access a null value for property: " + propertyName);

	return booleanPropertyValue[valueIndex];
    }

    /**
     * Adds an array property value to the entity. The type of the property and
     * the type of the value must match or be compatible, if not an
     * IllegalArgumentException is thrown.
     */

    public void addPropertyValue(String propertyName, ArrayValue value)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.ARRAY);
	arrayPropertyValues[valueIndex] = value;
    }

    /**
     * Gets the array value of a property. If the property has a different type
     * or it is not registered for this kind of entity, an
     * IllegalArgumentException is thrown.
     */

    public ArrayValue getArrayPropertyValue(String propertyName)
    {
	int valueIndex = findPropertyValueIndex(propertyName, DataType.ARRAY);
	return arrayPropertyValues[valueIndex];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // QueryableDataEntity interface implementation.
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public DataValue getPropertyValue(String propertyName)
    {
	int valueIndex = propertyDictionary.getPropertyValueIndexForEntities(propertyName);
	DataType propertyType = propertyDictionary.getPropertyType(propertyName);

	// Check the property was registered in the dictionary.
	if(valueIndex == -1)
	    return null;

	switch(propertyType.translateToIntegralType())
	{
	    case INTEGER:
		if(integerPropertyValuesNonNullFlags.get(valueIndex))
		    return new IntegerValue(integerPropertyValues[valueIndex]);
		else
		    return new NullValue();

	    case BOOLEAN:
		if(booleanPropertyValuesNonNullFlags.get(valueIndex))
		    return new BooleanValue(booleanPropertyValue[valueIndex]);
		else
		    return new NullValue();

	    case REAL:
		if(realPropertyValuesNonNullFlags.get(valueIndex))
		    return new RealValue(realPropertyValues[valueIndex]);
		else
		    return null;

	    case STRING:
		if(stringPropertyValues[valueIndex] != null)
		    return new StringValue(stringPropertyValues[valueIndex]);
		else
		    return new NullValue();

	    case DATE:
		if(datePropertyValues[valueIndex] != null)
		{
		    GregorianCalendar date = datePropertyValues[valueIndex];
		    new DateValue(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
		}
		else
		    return new NullValue();

	    case ARRAY:
		if(arrayPropertyValues[valueIndex] != null)
		    return arrayPropertyValues[valueIndex];
		else
		    return new NullValue();

	    default:
		throw new IllegalArgumentException("Error, unknown type of property. Did you add "
						   + "a new type of property to the system without "
						   + "updating the DataEntity code?");
	}
    }

    @Override
    public String getType()
    {
	return propertyDictionary.getEntityType();
    }

    @Override
    public int getId()
    {
	return entityId;
    }

    @Override
    public int compareTo(QueryableDataEntity otherEntity)
    {
	return entityId - otherEntity.getId();
    }
}
