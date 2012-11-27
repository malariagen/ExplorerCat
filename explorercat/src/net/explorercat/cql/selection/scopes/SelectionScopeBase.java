package net.explorercat.cql.selection.scopes;

/**
 * Base class for a selection scope.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Aug 2010
 */

public abstract class SelectionScopeBase implements SelectionScope
{
    // The name of the scope (doesn't have to be unique).
    protected String scopeName;

    // Flag to allow/disallow selection overwriting
    protected static boolean selectionOverwritingAllowed = false;

    /**
     * Creates an empty scope assigning it a name.
     * 
     * @param scopeName The name of the scope (doesn't have to be unique).
     */

    public SelectionScopeBase(String scopeName)
    {
	this.scopeName = scopeName;
    }

    /**
     * Gets the content (queries) associated with the scope or the filename of
     * the script that contains them.
     */

    @Override
    public String getScopeContent()
    {
	return scopeName;
    }
}
