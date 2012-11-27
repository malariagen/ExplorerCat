package net.explorercat.compactcat.definitions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents the definition of a regular attribute (we use regular here only to
 * differentiate from special cases, a regular attribute CAN BE an array).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 1 Feb 2011
 */

public class RegularAttributeDefinition implements AttributeDefinition
{
    private static Log log = LogFactory.getLog(RegularAttributeDefinition.class);

    protected String name;
    protected String description;
    protected DataType type;

    protected DataValue minimumValue;
    protected DataValue maximumValue;

    // We keep a hash table (set) with the string representation 
    // of the allowed values. It will save a lot of time when checking.
    protected Set<String> allowedValues;

    // Meaning this attribute has a FK role.
    protected String referencedEntity;
    protected String referencedAttribute;

    /**
     * Builds an empty attribute definition. It has to be configured using the
     * provided setter methods.
     */

    public RegularAttributeDefinition()
    {
	this.allowedValues = new HashSet<String>();
    }

    @Override
    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    @Override
    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    @Override
    public DataType getType()
    {
	return type;
    }

    public void setType(DataType type)
    {
	this.type = type;
    }

    @Override
    public DataValue getMinimumValue()
    {
	return minimumValue;
    }

    public void setMinimumValue(DataValue minimumValue)
    {
	this.minimumValue = minimumValue;
    }

    @Override
    public DataValue getMaximumValue()
    {
	return maximumValue;
    }

    public void setMaximumValue(DataValue maximumValue)
    {
	this.maximumValue = maximumValue;
    }

    @Override
    public String getReferencedEntity()
    {
	return referencedEntity;
    }

    @Override
    public String getReferencedAttribute()
    {
	return referencedAttribute;
    }

    /**
     * Sets the attribute and entity we are referencing. This plays the role of
     * a FK in a relational database.
     * 
     * @param referencedEntity Name of the entity referenced (plays the role of
     *        a table).
     * @param referencedAttribute Name of the attribute referenced (plays the
     *        role of a column).
     */

    public void setReference(String referencedEntity, String referencedAttribute)
    {
	this.referencedEntity = referencedEntity;
	this.referencedAttribute = referencedAttribute;
    }

    @Override
    public boolean isReference()
    {
	return this.referencedEntity != null && this.referencedAttribute != null;
    }

    public void registerAllowedValue(DataValue value)
    {
	try
	{
	    this.allowedValues.add(value.getValueAsString());
	}
	catch(ExplorerCatCheckedException e)
	{
	    log.error("Error registering an entity allowed value: " + e.getMessage());
	}
    }

    @Override
    public List<String> getAllowedValues()
    {
	return new ArrayList<String>(this.allowedValues);
    }

    @Override
    public boolean isValidValue(DataValue valueToCheck)
    {
	try
	{
	    // In case of an array we check all the values have the same type.
	    if(!isValueTypeCoherent(valueToCheck)
	       || (this.type == DataType.ARRAY && !areArrayValuesTypeCoherent(valueToCheck)))
		return false;

	    // Check if we can skip the range and allowed value test.
	    if(this.allowedValues.size() == 0 && this.minimumValue == null && this.maximumValue == null)
		return true;

	    // Check range/allowed values for the value (or values in case of arrays).
	    else
	    {
		List<DataValue> arrayValues = valueToCheck.getValueAsArray();

		for(DataValue currentValue : arrayValues)
		    if(!isValueInRangeAndAllowed(currentValue))
			return false;

		return true;
	    }
	}
	catch(IncompatibleTypeException e)
	{
	    return false;
	}
    }

    /**
     * Checks the type of the value is coherent with the attribute definition.
     */

    private boolean isValueTypeCoherent(DataValue valueToCheck)
    {
	if(this.type != valueToCheck.getType()
	   && (this.type == DataType.REAL && valueToCheck.getType() != DataType.INTEGER)
	   && (this.type == DataType.TEXT && valueToCheck.getType() != DataType.STRING))
	    return false;

	return true;
    }

    /**
     * Checks all the values of the array have the same type (or compatible).
     */

    private boolean areArrayValuesTypeCoherent(DataValue valueToCheck)
    {
	try
	{
	    List<DataValue> arrayValues = valueToCheck.getValueAsArray();
	    DataType type = arrayValues.get(0).getType();

	    for(int i = 1; i < arrayValues.size(); ++i)
		if(arrayValues.get(i).getType() != type)
		    return false;

	    return true;
	}
	catch(IncompatibleTypeException e)
	{
	    return false;
	}
    }

    /**
     * Checks if a value is in range [min,max] or present in the list of allowed
     * values.
     * 
     * @return True if the value is in range and allowed, false otherwise.
     */

    private boolean isValueInRangeAndAllowed(DataValue valueToCheck)
    {
	try
	{
	    boolean isAllowed = true;

	    if(this.minimumValue != null)
		isAllowed = !(this.minimumValue.getValueAsReal() > valueToCheck.getValueAsReal()) && isAllowed;

	    if(this.maximumValue != null)
		isAllowed = !(this.maximumValue.getValueAsReal() < valueToCheck.getValueAsReal()) && isAllowed;

	    if(this.allowedValues.size() != 0)
		isAllowed = this.allowedValues.contains(valueToCheck.getValueAsString()) && isAllowed;

	    return isAllowed;
	}
	catch(ExplorerCatCheckedException e)
	{
	    log.error("Error checking if a value was valid: " + valueToCheck);
	    return false;
	}
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder();
	String tabs = "\t\t\t\t";

	buffer.append(tabs).append("{");

	buffer.append("name: \"").append(this.name).append("\", ");
	buffer.append("description: \"").append(this.description).append("\", ");

	if(this.maximumValue != null)
	    buffer.append("maximumValue: ").append(this.maximumValue).append(", ");

	if(this.minimumValue != null)
	    buffer.append("minimumValue: ").append(this.minimumValue).append(", ");

	if(this.allowedValues.size() > 0)
	{
	    buffer.append("values: [");

	    for(String value : this.allowedValues)
	    {
		if(this.type == DataType.STRING || this.type == DataType.ARRAY_STRING)
		    buffer.append("\"").append(value).append("\",");
		else
		    buffer.append(value).append(",");
	    }

	    buffer.deleteCharAt(buffer.length() - 1);
	    buffer.append("], ");
	}

	if(this.referencedAttribute != null && this.referencedEntity == null)
	{
	    buffer.append("references: ").append(this.referencedEntity);
	    buffer.append("(\"" + this.referencedAttribute + "\"), ");
	}

	buffer.append("type: ").append(type);
	buffer.append("}");

	return buffer.toString();
    }

    @Override
    public AttributeDefinition getUniqueIdDefinition()
    {
	return UniqueIdAttributeDefinition.getInstance();
    }

    @Override
    public boolean isUniqueIdDefinition()
    {
	return false;
    }

    @Override
    public boolean isArray()
    {
	if(type == null)
	    return false;
	else
	    return type.isArray();
    }

    @Override
    public boolean isScalar()
    {
	if(type == null)
	    return false;
	else
	    return !type.isArray();
    }
}
