package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a boolean data value.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class BooleanValue extends DataValueBase implements DataValue
{
    // Logging
    private static final Log log = LogFactory.getLog(BooleanValue.class);

    private boolean value;

    /**
     * Creates a new boolean data value
     * 
     * @param value Contained value.
     */

    public BooleanValue(boolean value)
    {
	super(DataType.BOOLEAN);
	this.value = value;
    }

    @Override
    public int getValueAsInteger() throws IncompatibleTypeException
    {
	return value ? 1 : 0;
    }

    @Override
    public float getValueAsReal() throws IncompatibleTypeException
    {
	return value ? 1 : 0;
    }

    @Override
    public String getValueAsString() throws IncompatibleTypeException
    {
	return value ? "true" : "false";
    }

    @Override
    public boolean getValueAsBoolean() throws IncompatibleTypeException
    {
	return value;
    }

    @Override
    public int compareTo(DataValue otherValue)
    {
	try
	{
	    return getValueAsInteger() - otherValue.getValueAsInteger();
	}
	// For incompatible types we return 0.
	catch(IncompatibleTypeException e)
	{
	    log.error("Error comparing boolean data values, incompatible type conversion to integer");
	    return 0;
	}
    }

    @Override
    public Object getValueAsObject()
    {
	return new Boolean(value);
    }

    @Override
    public List<DataValue> getValueAsArray() throws IncompatibleTypeException
    {
	ArrayList<DataValue> values = new ArrayList<DataValue>(1);
	values.add(this);
	return values;
    }
}
