package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents and array of data values (types can be mixed). Arrays are
 * intended, in this stage, as a help to deal with references (foreign keys to
 * the attributes of other entities). The only method it provides for accessing
 * the values is getValueAsString, calling any other method will raise an
 * exception.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class ArrayValue extends DataValueBase implements DataValue
{
    // Logging
    private static final Log log = LogFactory.getLog(ArrayValue.class);

    private List<DataValue> values;

    /**
     * Creates a array of data values.
     */

    public ArrayValue(List<DataValue> values)
    {
	super(DataType.ARRAY);
	this.values = values;

	// We sort the array values to optimize the searches.
	Collections.sort(this.values);
    }

    @Override
    public String getValueAsString() throws IncompatibleTypeException
    {
	StringBuilder buffer = new StringBuilder();
	buffer.append("[");

	for(int i = 0; i < values.size(); ++i)
	{
	    DataValue currentValue = values.get(i);
	    String valueString = currentValue.getValueAsString();

	    // We protect the string values with quotes.
	    if(currentValue.getType() == DataType.STRING)
		buffer.append("\"").append(valueString).append("\",");
	    else
		buffer.append(valueString).append(",");
	}

	buffer.deleteCharAt(buffer.length() - 1);
	buffer.append("]");

	return buffer.toString();
    }

    @Override
    public int compareTo(DataValue otherValue)
    {
	// Basic values go before arrays.
	if(!(otherValue instanceof ArrayValue))
	    return 1;

	ArrayValue otherArray = (ArrayValue) (otherValue);

	if(values.size() != otherArray.values.size())
	    return values.size() - otherArray.values.size();

	for(int i = 0; i < values.size(); ++i)
	{
	    DataValue valueA = values.get(i);
	    DataValue valueB = otherArray.values.get(i);
	    int comparisonValue = valueA.compareTo(valueB);

	    if(comparisonValue != 0)
		return comparisonValue;
	}

	return 0;
    }

    @Override
    public Object getValueAsObject()
    {
	List<DataValue> valuesAsObject = new ArrayList<DataValue>(values.size());
	valuesAsObject.addAll(values);

	return valuesAsObject;
    }

    @Override
    public List<DataValue> getValueAsArray() throws IncompatibleTypeException
    {
	return this.values;
    }
}
