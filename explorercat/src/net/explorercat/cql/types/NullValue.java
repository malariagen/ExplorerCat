package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent null values for entity attributes. (Makes life easier when working
 * with the values contained in an array).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 2 Feb 2011
 */

public class NullValue extends DataValueBase implements DataValue
{
    public NullValue()
    {
	super(DataType.NULL);
    }

    @Override
    public String getValueAsString()
    {
	return "null";
    }

    @Override
    public Object getValueAsObject()
    {
	return null;
    }

    @Override
    public boolean getValueAsBoolean()
    {
	return false;
    }

    @Override
    public int compareTo(DataValue otherValue)
    {
	if(otherValue == null || otherValue instanceof NullValue)
	    return 0;
	else
	    return -1;
    }

    @Override
    public List<DataValue> getValueAsArray() throws IncompatibleTypeException
    {
	ArrayList<DataValue> values = new ArrayList<DataValue>(1);
	values.add(this);
	return values;
    }
}
