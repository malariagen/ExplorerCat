package net.explorercat.cql.types;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Base class that defines the default behaviours for data values. To be
 * extended by concrete data value types.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public abstract class DataValueBase implements DataValue {
	protected DataType valueType;

	/**
	 * Skeleton constructor that sets the type of the data value. To be called
	 * by subclasses.
	 * 
	 * @param type
	 *            The type of the data value.
	 */

	protected DataValueBase(DataType type) {
		valueType = type;
	}

	@Override
	public int getValueAsInteger() throws IncompatibleTypeException {
		throw new IncompatibleTypeException(
				"It is not possible to interpret a value of type " + valueType
						+ " as " + DataType.INTEGER);
	}

	@Override
	public float getValueAsReal() throws IncompatibleTypeException {
		throw new IncompatibleTypeException(
				"It is not possible to interpret a value of type " + valueType
						+ " as " + DataType.REAL);
	}

	@Override
	public String getValueAsString() throws IncompatibleTypeException {
		throw new IncompatibleTypeException(
				"It is not possible to interpret a value of type " + valueType
						+ " as " + DataType.STRING);
	}

	@Override
	public boolean getValueAsBoolean() throws IncompatibleTypeException {
		throw new IncompatibleTypeException(
				"It is not possible to interpret a value of type " + valueType
						+ " as " + DataType.BOOLEAN);
	}

	@Override
	public GregorianCalendar getValueAsDate() throws IncompatibleTypeException {
		throw new IncompatibleTypeException(
				"It is not possible to interpret a value of type " + valueType
						+ " as " + DataType.DATE);
	}

	@Override
	public List<DataValue> getValueAsArray() throws IncompatibleTypeException {
		throw new IncompatibleTypeException(
				"It is not possible to interpret a value of type " + valueType
						+ " as " + DataType.ARRAY);
	}

	@Override
	public DataType getType() {
		return valueType;
	}

	@Override
	public boolean isNumeric() {
		return valueType == DataType.REAL || valueType == DataType.INTEGER;
	}

	@Override
	public String toString() {
		try {
			return getValueAsString();
		} catch (IncompatibleTypeException e) {
			return "Unknown (error evaluating the data value)";
		}
	}
}
