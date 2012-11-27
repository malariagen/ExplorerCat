package net.explorercat.compactcat.translators.mysql;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents different bits of MySQL code that can be accessed/modified
 * individually. They will be assembled in order to generate the final piece of
 * code.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 5 Apr 2011
 */

public class MySQLCodeFragmentPool
{
    private List<StringBuilder> fragments;

    /**
     * Creates an empty buffer for MySQL code.
     */

    public MySQLCodeFragmentPool()
    {
	fragments = new ArrayList<StringBuilder>();
	for(int i = 0; i < CodeFragmentType.values().length; ++i)
	    fragments.add(new StringBuilder());
    }

    /**
     * Appends MySQL code to the specified code fragment.
     */

    public void appendCodeToFragment(String code, CodeFragmentType fragmentType)
    {
	fragments.get(fragmentType.ordinal()).append(code);
    }

    /**
     * Deletes any trailing comma from the code fragments.
     */

    public void deleteTrailingCommas()
    {
	for(StringBuilder fragment : fragments)
	    if(fragment.length() > 0 && fragment.charAt(fragment.length() - 1) == ',')
		fragment.deleteCharAt(fragment.length() - 1);
    }

    /**
     * Gets the MySQL code of the specified fragment.
     */

    public String getCodeFragment(CodeFragmentType fragmentType)
    {
	return fragments.get(fragmentType.ordinal()).toString();
    }

    /**
     * Different types of code fragments that will be used to ensemble the final
     * piece of code.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 14 Feb 2011
     */

    public static enum CodeFragmentType
    {
	// Table columns.
	COLUMNS,
	// Columns that will be part of the PK.
	PRIMARY_KEY_COLUMNS,
	// Columns that will be indexed.
	INDICES,
	// Options for the table creation.
	TABLE_OPTIONS,
	// Options regarding partitions.
	PARTITION_OPTIONS
    }
}
