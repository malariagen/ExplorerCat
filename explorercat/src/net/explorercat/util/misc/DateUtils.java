package net.explorercat.util.misc;

import net.explorercat.cql.types.DateValue;

/**
 * Class that contains utility methods to work with Dates (mainly transforming
 * string dates into date values, CQL)
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 2 Mar 2011
 */

public class DateUtils
{
    /**
     * Parses a the given string as a date and returns the equivalent date
     * value.
     */

    public static DateValue parseStringDate(String stringDate)
    {
	int year = Integer.parseInt(stringDate.substring(0, 4));
	int month = Integer.parseInt(stringDate.substring(5, 7));
	int day = Integer.parseInt(stringDate.substring(8, 10));

	return new DateValue(year, month, day);
    }
}
