package net.explorercat.cql.selection.stats;

/**
 * Types of stats supported by the system.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public enum StatsType
{
    COUNT,
    MIN,
    MAX,
    STDDEV
    {
	@Override
	public String translateToSQL()
	{
	    return "STDDEV";
	}
    },

    VAR
    {
	@Override
	public String translateToSQL()
	{
	    return "VAR";
	}
    },

    FIRST_QUARTILE,
    MEDIAN,
    THIRD_QUARTILE,

    AVG
    {
	@Override
	public String translateToSQL()
	{
	    return "AVG";
	}
    },

    SUM;

    /**
     * Translate the function to its SQL name. (Bounded to the MySQL
     * implementation).
     * 
     * @return The name of the stats function in SQL (MySQL).
     */

    public String translateToSQL()
    {
	return toString();
    }
}
