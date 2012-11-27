package net.explorercat.compactcat.translators.mysql;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.util.exceptions.TranslationException;

/**
 * Represents a reference constraint between two attributes (one of them
 * references the other) that can be translated into MySQL code.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 April 2011
 */

public class ReferenceConstraint
{
    private EntityDefinition sourceEntity;
    private AttributeDefinition sourceAttribute;

    private EntityDefinition destinyEntity;
    private AttributeDefinition destinyAttribute;

    public ReferenceConstraint(EntityDefinition sourceEntity, AttributeDefinition sourceAttribute,
			       EntityDefinition destinyEntity, AttributeDefinition destinyAttribute)
    {
	this.sourceEntity = sourceEntity;
	this.sourceAttribute = sourceAttribute;
	this.destinyEntity = destinyEntity;
	this.destinyAttribute = destinyAttribute;
    }

    /**
     * Translates the reference constraint into MySQL code (a list of
     * statements) that can be executed against the DB (usually an ALTER
     * statement).
     * 
     * @param tableCreator The object used to create the tables of the current
     *        DB.
     * @return The MySQL code statements that will add the reference constraint
     *         to the DB.
     * @throws TranslationException
     */

    public List<String> translateToMySQLCode(EntityTableCreatorMySQL tableCreator) throws TranslationException
    {
	List<String> codeStatements = new ArrayList<String>(2);
	String sourceTable = null;
	String destinyTable = null;

	// Several cases must be taken into account.

	if(arrayReferencesMainTable())
	{
	    sourceTable = tableCreator.getArrayAttributeTableName(sourceEntity, sourceAttribute.getName());
	    destinyTable = tableCreator.getEntityTableBaseName(destinyEntity);
	}

	else if(attributeReferencesAttribute())
	{
	    sourceTable = tableCreator.getEntityTableBaseName(sourceEntity);
	    destinyTable = tableCreator.getEntityTableBaseName(destinyEntity);
	}

	else if(attributeReferencesArray())
	{
	    sourceTable = tableCreator.getEntityTableBaseName(sourceEntity);
	    destinyTable = tableCreator.getArrayAttributeTableName(destinyEntity, destinyAttribute.getName());
	}

	else if(arrayReferencesAttribute())
	{
	    sourceTable = tableCreator.getArrayAttributeTableName(sourceEntity, sourceAttribute.getName());
	    destinyTable = tableCreator.getEntityTableBaseName(destinyEntity);
	}

	else if(arrayReferencesArray())
	{
	    sourceTable = tableCreator.getArrayAttributeTableName(sourceEntity, sourceAttribute.getName());
	    destinyTable = tableCreator.getArrayAttributeTableName(destinyEntity, destinyAttribute.getName());
	}

	else
	    throw new TranslationException("Error interpreting the reference " + this);

	codeStatements.add(generateIndexAlterStatement(destinyTable));
	codeStatements.add(generateForeignKeyAlterStatement(sourceTable, destinyTable));

	return codeStatements;
    }

    /**
     * Checks if the constraint represents an array referencing the entity main
     * table.
     */

    private boolean arrayReferencesMainTable()
    {
	return noEntityNullity() && noAttributeNullity() && this.sourceEntity == this.destinyEntity
	       && isSourceAttributeArray() && this.destinyAttribute.isUniqueIdDefinition();
    }

    /**
     * Checks if the constraint represents a regular attribute referencing
     * another one.
     */

    private boolean attributeReferencesAttribute()
    {
	return noEntityNullity() && noAttributeNullity() && !isSourceAttributeArray() && !isDestinyAttributeArray();
    }

    /**
     * Checks if the constraint represents an array referencing a regular
     * attribute.
     */

    private boolean arrayReferencesAttribute()
    {
	return noEntityNullity() && noAttributeNullity() && isSourceAttributeArray() && !isDestinyAttributeArray();
    }

    /**
     * Checks if the constraint represents a regular attribute referencing an
     * array.
     */

    private boolean attributeReferencesArray()
    {
	return noEntityNullity() && noAttributeNullity() && !isSourceAttributeArray() && isDestinyAttributeArray();
    }

    /**
     * Checks if the constraint represents an array referencing another array.
     */

    private boolean arrayReferencesArray()
    {
	return noEntityNullity() && noAttributeNullity() && isSourceAttributeArray() && isDestinyAttributeArray();
    }

    /**
     * Generates a MySQL statement that adds an index to the destiny table in
     * order to speed up the access to the destiny attribute.
     */

    private String generateIndexAlterStatement(String destinyTable)
    {
	return "ALTER TABLE " + destinyTable + " ADD INDEX IDX_FK_" + destinyTable + "_"
	       + this.destinyAttribute.getName() + "(" + destinyAttribute.getName() + ")";
    }

    /**
     * Generates a MySQL statement that adds a FK to the DB between the two
     * given tables (sourceAttribute and destinyAttribute are used to specify
     * the columns).
     */

    private String generateForeignKeyAlterStatement(String sourceTable, String destinyTable)
    {
	String sourceColumn = this.sourceAttribute.getName();

	// If the destiny attribute refers to the id column (special case) is because
	// we are linking an array attribute with its container entity table. In this case
	// we have to reference the ID column (we get them from the destiny attribute, since the
	// foreign key has the form (id -> id)).	
	if(destinyAttribute.isUniqueIdDefinition())
	    sourceColumn = destinyAttribute.getName();

	return "ALTER TABLE " + sourceTable + " ADD CONSTRAINT FK_" + sourceTable + "_"
	       + this.sourceAttribute.getName() + " FOREIGN KEY (" + sourceColumn + ")" + " REFERENCES " + destinyTable
	       + "(" + destinyAttribute.getName() + ")" + "ON DELETE CASCADE ON UPDATE CASCADE";
    }

    @Override
    public String toString()
    {
	return "(" + sourceEntity.getName() + "," + sourceAttribute.getName() + ") -> (" + destinyEntity.getName()
	       + "," + destinyAttribute.getName() + ")";
    }

    // Utility methods.

    private boolean noEntityNullity()
    {
	return this.sourceEntity != null && this.destinyEntity != null;
    }

    private boolean noAttributeNullity()
    {
	return this.sourceAttribute != null && this.destinyAttribute != null;
    }

    private boolean isSourceAttributeArray()
    {
	return this.sourceAttribute.isArray();
    }

    private boolean isDestinyAttributeArray()
    {
	return this.destinyAttribute.isArray();
    }
}
