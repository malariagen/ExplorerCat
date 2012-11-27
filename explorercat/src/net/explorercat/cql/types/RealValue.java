package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a real data value.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class RealValue extends DataValueBase implements DataValue
{
    // Logging
    private static Log log = LogFactory.getLog(RealValue.class);

    private float value;

    /**
     * Creates a new real data value (float)
     * 
     * @param value Contained value.
     */

    public RealValue(float value)
    {
	super(DataType.REAL);
	this.value = value;
    }

    @Override
    public int getValueAsInteger() throws IncompatibleTypeException
    {
	return (int) value;
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
	return value == 0.0F ? false : true;
    }

    @Override
    public int compareTo(DataValue otherValue)
    {
	try
	{
	    float difference = this.value - otherValue.getValueAsReal();

	    if(difference > 0)
		return 1;
	    else if(difference < 0)
		return -1;
	    else
		return 0;
	}
	catch(IncompatibleTypeException e) // Incompatible types
	{
	    log.error("Error comparing real data values, incompatible type conversion to real");
	    return 0;
	}
    }

    @Override
    public Object getValueAsObject()
    {
	return new Float(value);
    }

    @Override
    public List<DataValue> getValueAsArray() throws IncompatibleTypeException
    {
	ArrayList<DataValue> values = new ArrayList<DataValue>(1);
	values.add(this);
	return values;
    }
}
