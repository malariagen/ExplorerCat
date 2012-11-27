package net.explorercat.cql.expressions.visitors;

import net.explorercat.cql.expressions.values.ReferenceExpression;
import net.explorercat.data.PropertyDefinition;
import net.explorercat.data.PropertyDictionary;

/**
 * Visits all the nodes of an expression tree collecting any referenced array
 * property.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Mar 2011
 */

public class ArrayReferenceCollector extends ReferenceCollector
{
    protected PropertyDictionary dictionary;

    /**
     * Creates a new collector of array references.
     * 
     * @param propertyDictionary Dictionary that will be used to check the types
     *        of the referenced properties.
     */

    public ArrayReferenceCollector(PropertyDictionary propertyDictionary)
    {
	super();
	this.dictionary = propertyDictionary;
    }

    @Override
    public void visit(ReferenceExpression expression)
    {
	PropertyDefinition definition = dictionary.getPropertyDefinition(expression.getReferencedPropertyName());	

	if(definition.isArray())		    
	    this.referenceExpressions.add(expression);
    }
}
