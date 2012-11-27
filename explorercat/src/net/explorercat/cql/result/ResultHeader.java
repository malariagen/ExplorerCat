package net.explorercat.cql.result;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.types.DataType;

/**
 * Class that provides the header information for a result. This includes, the
 * name given by the user, the number of rows selected for the result and the
 * list of contained columns.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 6 Oct 2010
 */

public class ResultHeader
{
    private String resultLabel;
    private int numberOfRows;
    private List<ResultColumn> resultColumns;

    /**
     * Constructor.
     * 
     * @param resultLabel The name given by the user to the result.
     * @param numberOfRows The maximum number of rows to be added.
     */

    public ResultHeader(String resultLabel, int numberOfRows)
    {
	this.resultLabel = resultLabel;
	this.numberOfRows = numberOfRows;
	this.resultColumns = new ArrayList<ResultColumn>();
    }

    /**
     * Adds a new column to the header.
     * 
     * @param originalName Original name of the column.
     * @param alias Name given by the user.
     * @param description Description stored in the catalog.
     * @param type Type of the data stored in the column.
     */

    public void addColumn(String originalName, String alias, String description, DataType type)
    {
	this.resultColumns.add(new ResultColumn(originalName, alias, description, type));
    }

    /**
     * Gets the name of the result given by the user.
     * 
     * @return The name given by the user to the result.
     */

    public String getResultName()
    {
	return resultLabel;
    }

    /**
     * Gets the number of rows for the selection of the result.
     * 
     * @return The size of the selection that is represented by the result.
     */

    public int getNumRows()
    {
	return numberOfRows;
    }

    /**
     * Gets the list of columns contained in this result.
     * 
     * @return List of columns of the result.
     */

    public List<ResultColumn> getColumns()
    {
	return resultColumns;
    }

    /**
     * Inner class that represents a column in a result. This class provides
     * information about the original name of the column, the alias given by the
     * user, the description coming from the catalog and the type of data it
     * contains.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 6 Oct 2010
     */

    public static class ResultColumn
    {
	private String name;
	private String alias;
	private String description;
	private DataType type;

	/**
	 * Constructor.
	 */

	public ResultColumn(String name, String alias, String description, DataType type)
	{
	    super();
	    this.name = name;
	    this.alias = alias;
	    this.description = description;
	    this.type = type;
	}

	/**
	 * Gets the original name of the column.
	 */

	public String getName()
	{
	    return name;
	}

	/**
	 * Gets the alias given by the user when requesting the data.
	 */

	public String getAlias()
	{
	    return alias;
	}

	/**
	 * Gets a description of the property that is represented by the column.
	 * This description is coming from the catalog. User defined variables
	 * won't have a description.
	 */

	public String getDesc()
	{
	    return description;
	}

	/**
	 * Gets the type of the data stored by the column.
	 */

	public DataType getType()
	{
	    return type;
	}
    }
}
