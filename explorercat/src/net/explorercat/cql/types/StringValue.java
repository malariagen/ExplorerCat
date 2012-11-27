package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a string data value.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class StringValue extends DataValueBase implements DataValue {
	// Logging
	private static Log log = LogFactory.getLog(StringValue.class);

	private String value;

	/**
	 * Creates a new string data value
	 * 
	 * @param value
	 *            Contained value.
	 */

	public StringValue(String value) {
		super(DataType.STRING);
		this.value = value;
	}

	@Override
	public int getValueAsInteger() throws IncompatibleTypeException {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new IncompatibleTypeException(
					"The string value can't be converted to integer");
		}
	}

	@Override
	public float getValueAsReal() throws IncompatibleTypeException {
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException e) {
			throw new IncompatibleTypeException(
					"The string value can't be converted to real");
		}
	}

	@Override
	public String getValueAsString() throws IncompatibleTypeException {
		return value;
	}

	@Override
	public boolean getValueAsBoolean() throws IncompatibleTypeException {
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			throw new IncompatibleTypeException(
					"The string value can't be converted to boolean");
		}
	}

	@Override
	public int compareTo(DataValue otherValue) {
		try {
			return this.value.compareTo(otherValue.getValueAsString());
		} catch (IncompatibleTypeException e) // Incompatible types
		{
			log.error("Error comparing string data values, incompatible type conversion to string");
			return 0;
		}
	}

	@Override
	public Object getValueAsObject() {
		return new String(value);
	}

	@Override
	public List<DataValue> getValueAsArray() throws IncompatibleTypeException {
		ArrayList<DataValue> values = new ArrayList<DataValue>(1);
		values.add(this);
		return values;
	}
}
