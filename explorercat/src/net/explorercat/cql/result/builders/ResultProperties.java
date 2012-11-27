package net.explorercat.cql.result.builders;

import java.util.ArrayList;
import java.util.List;

/**
 * Auxiliary class that represents a collection of property names and their
 * associated aliases (labels).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public class ResultProperties
{
    private List<String> propertyNames;
    private List<String> propertyLabels;

    /**
     * Builds a new collection of properties
     */

    public ResultProperties()
    {
	propertyNames = new ArrayList<String>();
	propertyLabels = new ArrayList<String>();
    }

    /**
     * Adds a new property to the collection.
     * 
     * @param propertyName The original name of the property.
     * @param propertyLabel The label of the property (alias). Null if there is
     *        not an associated label.
     */

    public void addProperty(String propertyName, String propertyLabel)
    {
	this.propertyNames.add(propertyName);

	if(propertyLabel == null)
	    this.propertyLabels.add(propertyName);
	else
	    this.propertyLabels.add(propertyLabel);
    }

    /**
     * Gets the original name of the property.
     * 
     * @return Original name of the property.
     */

    public String getPropertyName(int propertyIndex)
    {
	return propertyNames.get(propertyIndex);
    }

    /**
     * Gets the alias of the property (label). If no alias has been provided
     * this method returns the original name.
     * 
     * @return The property alias (label) or the original name if no label has
     *         been defined.
     */

    public String getPropertyLabel(int propertyIndex)
    {
	return propertyLabels.get(propertyIndex);
    }

    /**
     * Gets the property labels.
     * 
     * @return A list containing all the property labels.
     */

    public List<String> getPropertyLabels()
    {
	return propertyLabels;
    }

    /**
     * Gets the property labels.
     * 
     * @return A list containing all the property labels.
     */

    public List<String> getPropertyNames()
    {
	return propertyNames;
    }

    /**
     * Gets the number of properties.
     */

    public int size()
    {
	return propertyNames.size();
    }

    @Override
    public String toString()
    {
	return "[" + propertyNames.toString() + "]{" + propertyLabels.toString() + "}";
    }
}
