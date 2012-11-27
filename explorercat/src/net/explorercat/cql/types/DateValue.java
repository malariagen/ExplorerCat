package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a date value.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public class DateValue extends DataValueBase implements DataValue
{
    // Logging
    private static final Log log = LogFactory.getLog(DateValue.class);

    private GregorianCalendar value;

    /**
     * Creates a new date value
     * 
     * @param value Contained value.
     */

    public DateValue(int year, int month, int dayOfMonth)
    {
	super(DataType.DATE);
	try
	{
	    GregorianCalendar gregoriancalendardate = new GregorianCalendar();
	    gregoriancalendardate.setLenient(false); 
	    gregoriancalendardate.set(GregorianCalendar.YEAR, year);
	    gregoriancalendardate.set(GregorianCalendar.MONTH, month);
	    gregoriancalendardate.set(GregorianCalendar.DATE, dayOfMonth);

	    gregoriancalendardate.getTime(); // exception thrown here if date is wrong
	    this.value = gregoriancalendardate;
	}
	catch(Exception e)
	{
	    this.value = new GregorianCalendar(1900, 1, 1);

	}
    }

    @Override
    public int getValueAsInteger() throws IncompatibleTypeException
    {
	return (int) value.getTimeInMillis();
    }

    @Override
    public float getValueAsReal() throws IncompatibleTypeException
    {
	return value.getTimeInMillis();
    }

    @Override
    public String getValueAsString() throws IncompatibleTypeException
    {
	return value.get(Calendar.YEAR) + "-" + value.get(Calendar.MONTH) + "-" + value.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public boolean getValueAsBoolean() throws IncompatibleTypeException
    {
	// Always true.
	return value == null ? false : true;
    }

    @Override
    public GregorianCalendar getValueAsDate() throws IncompatibleTypeException
    {
	return value;
    }

    @Override
    public int compareTo(DataValue otherValue)
    {
	try
	{
	    // Difference using real values will render the difference in milliseconds.
	    return (int) (this.getValueAsReal() - otherValue.getValueAsReal());
	}
	catch(IncompatibleTypeException e) // Incompatible types
	{
	    log.error("Error comparing date values, incompatible type conversion to real");
	    return 0;
	}
    }

    @Override
    public Object getValueAsObject()
    {
	return value.clone();
    }

    @Override
    public List<DataValue> getValueAsArray() throws IncompatibleTypeException
    {
	ArrayList<DataValue> values = new ArrayList<DataValue>(1);
	values.add(this);
	return values;
    }
}
