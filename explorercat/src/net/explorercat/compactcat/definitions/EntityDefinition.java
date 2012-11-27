package net.explorercat.compactcat.definitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents the definition of an entity read from on a .dic file (following
 * the CompactCat file format). A definition requires the following properties:
 * name, description and the list of attribute definitions.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 1 Feb 2011
 */

public class EntityDefinition
{
    private static Log log = LogFactory.getLog(EntityDefinition.class);

    private String name;
    private String description;

    // Note the order in the list in important 
    // when we read from the data from the file.
    private List<AttributeDefinition> attributeDefinitions;
    private Map<String, AttributeDefinition> attributeLookup;

    /**
     * Builds an empty entity definition. Setter methods must be used to
     * configure the object.
     */

    public EntityDefinition()
    {
	this.attributeDefinitions = new ArrayList<AttributeDefinition>();
	this.attributeLookup = new HashMap<String, AttributeDefinition>();
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    public List<AttributeDefinition> getAttributes()
    {
	return attributeDefinitions;
    }

    public void setAttributes(List<AttributeDefinition> attributes)
    {
	this.attributeDefinitions = attributes;
    }

    /**
     * Adds a new attribute definition. An exception is raised if an attribute
     * with the same name have been already registered.
     */

    public void addAttributeDefintion(AttributeDefinition attribute) throws DefinitionException
    {
	if(!this.attributeLookup.keySet().contains(attribute.getName()))
	{
	    this.attributeDefinitions.add(attribute);
	    this.attributeLookup.put(attribute.getName(), attribute);
	}
	else
	{
	    log.error("Attribute already registered: " + attribute.getName());
	    throw new DefinitionException("Attribute " + attribute.getName() + " already registered.");
	}
    }

    public DataType getAttributeType(String attribute)
    {
	return this.attributeLookup.get(attribute).getType();
    }

    public int getNumberOfAttributes()
    {
	return this.attributeDefinitions.size();
    }

    public AttributeDefinition getAttributeDefinition(int attributeIndex)
    {
	return this.attributeDefinitions.get(attributeIndex);
    }

    public AttributeDefinition getAttributeDefinition(String attributeName)
    {
	return this.attributeLookup.get(attributeName);
    }

    /**
     * Checks if a value is valid for the attribute indicated.
     * 
     * @param value The value that will be checked.
     * @param attributeIndex The index of the attribute we are targeting.
     * @return True if the value is valid for the attribute, false otherwise.
     */

    public boolean isValueValidForAttribute(DataValue value, int attributeIndex)
    {
	return this.attributeDefinitions.get(attributeIndex).isValidValue(value);
    }

    @Override
    public String toString()
    {
	StringBuilder buffer = new StringBuilder();
	String outTabs = "\t\t";
	String inTabs = "\t\t\t";

	buffer.append(outTabs).append(this.name).append(":\n").append(outTabs).append("{\n");
	buffer.append(inTabs).append("description: \"").append(this.description).append("\",\n");
	buffer.append(inTabs).append("attributes:").append("\n").append(inTabs).append("[");

	for(AttributeDefinition attribute : this.attributeDefinitions)
	    buffer.append("\n").append(attribute.toString()).append(",");

	buffer.deleteCharAt(buffer.length() - 1);
	buffer.append("\n").append(inTabs).append("]");
	buffer.append("\n").append(outTabs).append("}");
	return buffer.toString();
    }

}
