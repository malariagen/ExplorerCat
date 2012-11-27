package net.explorercat.compactcat.definitions;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.IntegerValue;

/**
 * Represents the entities unique identifier, automatically added to any entity.
 * This is an implementation of the SpecialObject pattern.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 Apr 2011
 */

public class UniqueIdAttributeDefinition extends RegularAttributeDefinition implements AttributeDefinition
{
    // Name of the unique id that is automatically generated for each entity.
    private static final String UNIQUE_ID_ATTRIBUTE;

    // Singleton instance (not mutable).
    private static final UniqueIdAttributeDefinition instance;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	UNIQUE_ID_ATTRIBUTE = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.uniqueId");
	instance = new UniqueIdAttributeDefinition();
    }

    private UniqueIdAttributeDefinition()
    {
	this.setName(UNIQUE_ID_ATTRIBUTE);
	this.setDescription("Entity unique identifier (automatically generated)");
	this.setType(DataType.INTEGER);
	this.setMinimumValue(new IntegerValue(1));
    }

    /**
     * Gets an instance of the unique id definition.
     */

    public static AttributeDefinition getInstance()
    {
	return instance;
    }

    @Override
    public boolean isUniqueIdDefinition()
    {
	return true;
    }
}
