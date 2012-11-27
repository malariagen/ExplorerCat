package net.explorercat.cql.interpreters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.explorercat.cql.result.builders.ResultGenerator;
import net.explorercat.cql.selection.SelectionProxy;

/**
 * Class that aggregates all the objects returned by the CQL interpreter after
 * interpreting a CQL script. It can be used to access the selections or the
 * result generator associated with the script.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 7 Oct 2010
 */

public class CQLScriptContext
{
    // Original query or path to the script file.
    private String cqlQuery;

    // Selection queries referenced or defined in the script.
    private Map<String, SelectionProxy> selections;

    // Generator for the results defined in the script.
    private ResultGenerator resultGenerator;

    /**
     * Constructor.
     * 
     * @param cqlQuery Original query or path to the script file.
     * @param selections Map of selection queries referenced or defined in the
     *        script
     * @param resultGenerator Generator of results defined in the script.
     */

    public CQLScriptContext(String cqlQuery, Map<String, SelectionProxy> selections, ResultGenerator resultGenerator)
    {
	this.cqlQuery = cqlQuery;
	this.selections = selections;
	this.resultGenerator = resultGenerator;
    }

    /**
     * Gets the original query/filename.
     */

    public String getCQLQuery()
    {
	return cqlQuery;
    }

    /**
     * Gets a list with all the defined selections.
     */

    public List<SelectionProxy> getScriptSelections()
    {
	return new ArrayList<SelectionProxy>(selections.values());
    }

    /**
     * Gets the result generator defined in the script.
     * 
     * @return The generator for the result defined in the script or null if no
     *         result was defined.
     */

    public ResultGenerator getResultGenerator()
    {
	return this.resultGenerator;
    }

    /**
     * Gets a list with all the referenced selections (not defined in the
     * script).
     * 
     * @return A list contained all the referenced selections of the script.
     */

    public List<SelectionProxy> getScriptReferencedSelections()
    {
	return getSelectionsByType(false);
    }

    /**
     * Gets a list with all the defined selections of the script.
     * 
     * @return A list with all the selections/queries defined in the script.
     */

    public List<SelectionProxy> getScriptDefinedSelections()
    {
	return getSelectionsByType(true);
    }

    /**
     * Auxiliary method that selects defined or only referenced selections.
     * 
     * @param mustBeDefined True if the selections has to be defined in the
     *        script (false means they will be referenced selections).
     * @return A list with the selections that meet the criteria.
     */

    private List<SelectionProxy> getSelectionsByType(boolean mustBeDefined)
    {
	List<SelectionProxy> collectedSelections = new ArrayList<SelectionProxy>();

	Iterator<SelectionProxy> selectionIterator = selections.values().iterator();
	while(selectionIterator.hasNext())
	{
	    SelectionProxy currentSelection = selectionIterator.next();

	    if(currentSelection.hasQueryDefinition() == mustBeDefined)
		collectedSelections.add(currentSelection);
	}

	return collectedSelections;
    }
}
