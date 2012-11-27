package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents an integer data value.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class IntegerValue extends DataValueBase implements DataValue
{
    // Logging
    private static Log log = LogFactory.getLog(IntegerValue.class);

    private int value;

    /**
     * Creates a new integer data value
     * 
     * @param value Contained value.
     */

    public IntegerValue(int value)
    {
	super(DataType.INTEGER);
	this.value = value;
    }

    @Override
    public int getValueAsInteger() throws IncompatibleTypeException
    {
	return value;
    }

    @Override
    public float getValueAsReal() throws IncompatibleTypeException
    {
	return value;
    }

    @Override
    public String getValueAsString() throws IncompatibleTypeException
    {
	return "" + value;
    }

    @Override
    public boolean getValueAsBoolean() throws IncompatibleTypeException
    {
	return value == 0 ? false : true;
    }

    @Override
    public int compareTo(DataValue otherValue)
    {
	try
	{
	    return this.value - otherValue.getValueAsInteger();
	}
	catch(IncompatibleTypeException e) // Incompatible types
	{
	    log.error("Error comparing integer data values, incompatible type conversion to integer");
	    return 0;
	}
    }

    @Override
    public Object getValueAsObject()
    {
	return new Integer(value);
    }

    @Override
    public List<DataValue> getValueAsArray() throws IncompatibleTypeException
    {
	ArrayList<DataValue> values = new ArrayList<DataValue>(1);
	values.add(this);
	return values;
    }
}
