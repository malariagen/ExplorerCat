package net.explorercat.cql.result.builders;

/**
 * Base class for result builders. This class is abstract and only encapsulates
 * the functionality that is common to all builder implementations.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Aug 2010
 */

public abstract class ResultBuilderBase implements ResultBuilder
{
    protected ResultProperties resultProperties;
    protected String resultLabel;

    
    public ResultBuilderBase()
    {
	this.resultLabel = null;
	this.resultProperties = new ResultProperties();
    }

    @Override
    public String getResultLabel()
    {
	return this.resultLabel;
    }

    /**
     * Sets the label of the result.
     * 
     * @param resultLabel A string representing the results name.
     */

    public void setResultLabel(String resultLabel)
    {
	this.resultLabel = resultLabel;
    }

    /**
     * Adds a property that will be selected in the result.
     * 
     * @param propertyName Original name of the property.
     * @param propertyLabel Alias for the property or null if no alias has been
     *        specified.
     */

    public void addProperty(String propertyName, String propertyLabel)
    {
	this.resultProperties.addProperty(propertyName, propertyLabel);
    }

    @Override
    public String toString()
    {
	String labelString = (resultLabel != null ? resultLabel : "");
	return resultProperties.toString() + "{" + labelString + "}";
    }
}
