package net.explorercat.cql.result;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;

/**
 * Represents the result of a CQL query (specified by the CQL language).This
 * data is stored as an arbitrary list of rows, each row having an arbitrary
 * list of data values (that correspond to a property/user-defined variable).
 * 
 * A result is always created by a result generator (that delegates in a result
 * builder).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 13 Aug 2010
 */

public class Result
{
    private String resultLabel;
    private List<ResultRow> rows;

    /**
     * Builds an empty result.
     * 
     * @param resultLabel The label associated with the result (can be null if
     *        no name was provided by the user).
     */

    public Result(String resultLabel)
    {
	this.resultLabel = resultLabel != null ? resultLabel : "";
	this.rows = new ArrayList<ResultRow>();
    }

    /**
     * Gets the name of the result.
     * 
     * @return The name given to the result. This method is not returning a copy
     *         so client code must not modify the object. If the user did not
     *         specify a name this method returns the empty string.
     */

    public String getLabel()
    {
	return resultLabel;
    }

    /**
     * Adds a row to the result.
     * 
     * @param entityId The entity id from which the values are coming from.
     * @param values The list of values, each one representing a value for each
     *        property.
     */

    public void addRow(int entityId, List<DataValue> values)
    {
	this.rows.add(new ResultRow(entityId, values));
    }

    /**
     * Adds a data row to the result.
     * 
     * @param row The row that will be added to the result.
     */

    public void addRow(ResultRow row)
    {
	this.rows.add(row);
    }

    /**
     * Adds a list of result rows to the result.
     * 
     * @param rows List of rows that will be added.
     */

    public void addRows(List<ResultRow> rows)
    {
	this.rows.addAll(rows);
    }

    /**
     * Gets a row from the result.
     * 
     * @param rowIndex The index of the row.
     * @return The row associated with that index.
     */

    public ResultRow getRow(int rowIndex)
    {
	return rows.get(rowIndex);
    }

    /**
     * Gets all the rows in the result.
     * 
     * @return All the rows of data within the result.
     */

    public List<ResultRow> getRows()
    {
	return this.rows;
    }

    /**
     * Removes all the rows registered in the result.
     */

    public void removeAllRows()
    {
	this.rows.clear();
    }

    /**
     * Represents a row of values associated with an entity.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 23 Aug 2010
     */

    public static class ResultRow implements Comparable<ResultRow>
    {
	private int rowEntityIdentifier;
	private List<DataValue> rowValues;

	/**
	 * Creates a result row representing a list of selected values
	 * associated with an entity.
	 * 
	 * @param entityId The entity id from which the values are coming from.
	 * @param values The list of values, each one representing a value for
	 *        each property.
	 */

	public ResultRow(int entityId, List<DataValue> values)
	{
	    this.rowEntityIdentifier = entityId;
	    this.rowValues = values;
	}

	/**
	 * Creates an empty row.
	 * 
	 * @param entityId Id of the entity associated with the row.
	 */

	public ResultRow(int entityId)
	{
	    this.rowEntityIdentifier = entityId;
	    this.rowValues = new ArrayList<DataValue>();
	}

	/**
	 * Gets the values of the row as a list of Strings.
	 * 
	 * @return The list of values contained in the row as strings.
	 */

	public List<String> getValues() throws IncompatibleTypeException
	{
	    List<String> values = new ArrayList<String>(rowValues.size());

	    for(DataValue value : rowValues)
	    {
		if(value != null)
		{
		    if(value.getType().isArray())
			values.add(value.getValueAsString().replace("[","").replace("]","").replace("\"", ""));
		    else
			values.add(value.getValueAsString());
		}		
		else
		    values.add("null");
	    }
	    
	    return values;
	}

	/**
	 * Gets the values of the row as a list of data values. This method gets
	 * EXCLUDED from the JSON response of the API methods.
	 * 
	 * @return The list of values as raw data values.
	 */

	public List<DataValue> getRawValues() throws IncompatibleTypeException
	{
	    return rowValues;
	}

	/**
	 * Gets the identifier of the entity associated with the row.
	 * 
	 * @return The identifier associated with the row.
	 */

	public int getId()
	{
	    return rowEntityIdentifier;
	}

	/**
	 * Gets the value in the index position of the row.
	 * 
	 * @param index The index of the value to be retrieved.
	 * @return The data value object that encapsulates the value of a
	 *         property. If the property did not have a value then null is
	 *         provided. Note that the values are not copies from the
	 *         original data entities so client code must not modify them.
	 */

	public DataValue getValue(int index)
	{
	    return rowValues.get(index);
	}

	/**
	 * Gets the number of properties in the set.
	 * 
	 * @return The number of properties contained in the set.
	 */

	public int size()
	{
	    return rowValues.size();
	}

	/**
	 * Adds a new property value to the row.
	 * 
	 * @param value Value to be added to the row.
	 */

	public void addValue(DataValue value)
	{
	    this.rowValues.add(value);
	}

	// Check if two rows are equal, NOT taking into account the entity identifiers.
	// TODO The JSON plug-in seems to be accessing this method.
	// The type checking is protecting us from a crash but we should  avoid this intrusion.
	@Override
	public boolean equals(Object anotherRow)
	{
	    if(!(anotherRow instanceof ResultRow))
		return false;

	    ResultRow rowB = (ResultRow) anotherRow;

	    if(this.rowValues.size() != rowB.rowValues.size())
		return false;
	    else
	    {
		for(int i = 0; i < rowValues.size(); ++i)
		{
		    DataValue valueA = this.rowValues.get(i);
		    DataValue valueB = rowB.rowValues.get(i);

		    if(valueA == null || valueB == null || valueA.compareTo(valueB) != 0)
			return false;
		}

		return true;
	    }
	}

	// Necessary to implement the DISTINCT operator.
	@Override
	public int compareTo(ResultRow anotherRow)
	{
	    int sizeDifference = this.rowValues.size() - anotherRow.rowValues.size();

	    // The row with the smallest number of properties precedes the other one.
	    if(sizeDifference != 0)
		return sizeDifference;

	    // Compare value by value.
	    else
	    {
		for(int i = 0; i < rowValues.size(); ++i)
		{
		    DataValue valueA = this.rowValues.get(i);
		    DataValue valueB = anotherRow.rowValues.get(i);

		    if(valueA == null)
			return -1;

		    else if(valueB == null)
			return 1;

		    else
		    {
			int valueDifference = valueA.compareTo(valueB);

			if(valueDifference != 0)
			    return valueDifference;
		    }
		}

		return 0; // At this point all the values were identical.
	    }
	}
    }

}
