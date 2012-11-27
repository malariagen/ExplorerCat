package net.explorercat.cql.types;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Interface that represents a generic wrapper for primitive types of the CQL
 * language. The current supported types are integer, real, boolean and string.
 * 
 * An IncompatibleTypeException is thrown when accessing the data value if the
 * asked representation is incompatible with the contained value.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public interface DataValue extends Comparable<DataValue>
{
    /**
     * Gets the data value as an Integer.
     * 
     * @return The contained value as an integer (primitive type).
     */

    public int getValueAsInteger() throws IncompatibleTypeException;

    /**
     * Gets the data value as a Float.
     * 
     * @return The contained value as a float (primitive type).
     */

    public float getValueAsReal() throws IncompatibleTypeException;

    /**
     * Gets the data value as a Boolean.
     * 
     * @return The contained value as an boolean (primitive type).
     */

    public boolean getValueAsBoolean() throws IncompatibleTypeException;

    /**
     * Gets the data value as a String.
     * 
     * @return The contained value as a java String.
     */

    public String getValueAsString() throws IncompatibleTypeException;

    /**
     * Gets the data value as a date.
     * 
     * @return An instance of GregorialCalendar containing the date.
     */

    public GregorianCalendar getValueAsDate() throws IncompatibleTypeException;

    /**
     * Gets the data value as an array of data values.
     * 
     * @return The contained values as a list of data values.
     */

    public List<DataValue> getValueAsArray() throws IncompatibleTypeException;
    
    /**
     * Gets the data value as a generic object.
     * 
     * @return An Integer, Float, Boolean or String object representing the data
     *         value.
     */

    public Object getValueAsObject();

    /**
     * Gets the type of the value (string, integer, boolean or real).
     * 
     * @return The type of this data value.
     */

    public DataType getType();

    /**
     * Checks if the value is numeric (real or integer).
     * 
     * @return True if the type is integer or real.
     */

    public boolean isNumeric();
}
