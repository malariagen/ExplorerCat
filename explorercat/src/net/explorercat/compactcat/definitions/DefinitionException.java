package net.explorercat.compactcat.definitions;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Marks errors when building catalog, entity and attribute definitions.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 1 Feb 2011
 */

public class DefinitionException extends ExplorerCatCheckedException
{
    public DefinitionException(String msg)
    {
	super(msg);
    }

    public DefinitionException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public DefinitionException(Exception e)
    {
	super(e);
    }
}
