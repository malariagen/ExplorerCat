package net.explorercat.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.explorercat.cql.expressions.Expression;
import net.explorercat.cql.expressions.ExpressionEvaluationException;
import net.explorercat.cql.expressions.values.PropertyAccessException;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.query.Query;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.IncompatibleTypeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class in charge of augmenting entities and dictionaries with user defined
 * variables.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Oct 2010
 * 
 */

public class EntityPropertyAugmenter
{
    // Logging
    private static final Log log = LogFactory.getLog(EntityPropertyAugmenter.class);

    private Selection sourceSelection;
    private Query query;

    /**
     * Creates a property augmenter for the given query, based on the given
     * source selection.
     * 
     * @param sourceSelection The selection that will be used to execute the
     *        query.
     * @param query Query that contains all the variables defined by the user.
     */

    public EntityPropertyAugmenter(Selection sourceSelection, Query query)
    {
	this.sourceSelection = sourceSelection;
	this.query = query;
    }

    /**
     * Gets an augmented dictionary with all the variables defined by the user.
     * This dictionary is based on the dictionary of the source selection.
     * 
     * @return The dictionary of the source selection augmented with all the
     *         variables defined by the user.
     */

    public PropertyDictionary getDictionaryAugmentedWithVariables()
    {
	PropertyDictionary dictionary = cloneSourceSelectionPropertyDictionary(sourceSelection);
	Iterator<Entry<String, Expression>> variableIterator = query.getVariableIterator();

	while(variableIterator.hasNext())
	{
	    Entry<String, Expression> variableEntry = variableIterator.next();

	    // Add the variable to the dictionary of the selection.
	    // Variables have only name and type.	   
	    String varName = variableEntry.getKey();
	    DataType varType = variableEntry.getValue().inferResultType();
	    String desc = "Variable translated into property";

	    PropertyDefinition property = new PropertyDefinition(varName, varType, desc, null, null, null, null,
								 new ArrayList<DataValue>());
	    dictionary.addProperty(property);
	}

	return dictionary;
    }

    /**
     * Augment the properties of the selected entities with the variables
     * defined by the user in the query.
     * 
     * @param entities The list of entities that have been selected by the
     *        query.
     * @param selectionDictionary Dictionary of the selection.
     */

    public void augmentEntitiesWithVariables(List<QueryableDataEntity> entities) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	for(int i = 0; i < entities.size(); ++i)
	{
	    // We extend the entity with all the user defined variables
	    AugmentedDataEntity augmentedEntity = new AugmentedDataEntity(entities.get(i));
	    augmentdEntityWithVariables(augmentedEntity);
	    entities.set(i, augmentedEntity);
	}
    }

    /**
     * Augment an entity calculating and adding all the user defined variables
     * for the given query.
     * 
     * @param entityToAugment Entity to be extended with the user defined
     *        variables.
     */

    private void augmentdEntityWithVariables(AugmentedDataEntity entityToAugment) throws IncompatibleTypeException,
	PropertyAccessException, ExpressionEvaluationException
    {
	Iterator<Entry<String, Expression>> variableIterator = query.getVariableIterator();

	while(variableIterator.hasNext())
	{
	    Entry<String, Expression> currentVariableEntry = variableIterator.next();
	    entityToAugment.addProperty(currentVariableEntry.getKey(),
					currentVariableEntry.getValue().calculateExpressionValue(entityToAugment));
	}
    }

    /**
     * Auxiliary method that clones the dictionary of the given selection.
     * 
     * @param selection The selection whose dictionary will be cloned.
     * @return A copy of the selection dictionary.
     */

    private PropertyDictionary cloneSourceSelectionPropertyDictionary(Selection selection)
    {
	PropertyDictionary dictionaryCopy = new PropertyDictionary(selection.getEntityType());
	List<String> names = selection.getEntityPropertyNames();

	for(String propertyName : names)
	    dictionaryCopy.addProperty(selection.getPropertyDictionary().getPropertyDefinition(propertyName));

	return dictionaryCopy;
    }
}
