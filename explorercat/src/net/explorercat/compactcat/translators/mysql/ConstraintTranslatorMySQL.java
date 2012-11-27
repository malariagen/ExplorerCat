package net.explorercat.compactcat.translators.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Adds reference constraints to the MySQL representation of the catalog. This
 * constraints will be implemented using Foreign Keys. There are two main cases
 * when a foreign key is added : A) When an attribute references another one. B)
 * When an attribute is an array (it implicitly references its entity table).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 28 Feb 2011
 */

public class ConstraintTranslatorMySQL
{
    private static Log log = LogFactory.getLog(ConstraintTranslatorMySQL.class);

    private SQLDataConnector sqlConnector;
    private EntityTableCreatorMySQL tableCreator;

    /**
     * Creates an translator that will be in charge of adding all the reference
     * constraints to the DB.
     * 
     * @param tableCreator The table creator used to create the tables of the
     *        database.
     */

    public ConstraintTranslatorMySQL(EntityTableCreatorMySQL tableCreator)
    {
	this.tableCreator = tableCreator;
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    /**
     * Adds all the constraints that represents the relationship between the
     * attributes of all the entities.
     * 
     * @param entities List of entities for which attributes constraints will be
     *        extracted and added.
     * @throws TranslationException If there is an error adding the constraints
     *         (foreign keys) or if the data violates them.
     */

    public void translateConstraints(List<EntityDefinition> entities) throws TranslationException
    {
	try
	{
	    List<ReferenceConstraint> constraints = findReferenceConstraints(entities);
	    addConstraintsToDB(constraints);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error adding constraint, please check the data don't violate it", e);
	}
    }

    /**
     * Adds the constraint to the translated representation of the catalog. For
     * this implementation we add a FK constraint between the two attribute
     * columns.
     * 
     * @throws SQLException If there is an error adding the constraint or the
     *         data violate it.
     */

    private void addConstraintsToDB(List<ReferenceConstraint> constraints) throws SQLException, TranslationException
    {
	for(ReferenceConstraint currentConstraint : constraints)
	{
	    if(log.isDebugEnabled())
		log.debug("Checking constraint: " + currentConstraint);

	    List<String> constraintStatements = currentConstraint.translateToMySQLCode(tableCreator);
	    for(String currentStatement : constraintStatements)
	    {
		try
		{
		    if(log.isDebugEnabled())
			log.debug("Adding constraint to the DB: " + currentStatement);

		    sqlConnector.closeConnection();
		    sqlConnector.executeUpdate(currentStatement);
		}
		catch(SQLException e)
		{
		    int errorCode = e.getErrorCode();
		    int duplicatedIndexCode = 1061;
		    int foreignKeyWithPartitionsCode = 1005;

		    // Ignore errors about duplicate indices and FK in partitioned tables.
		    if(errorCode != duplicatedIndexCode && errorCode != foreignKeyWithPartitionsCode)
			throw e;

		    else if(log.isDebugEnabled())
		    {
			if(errorCode == duplicatedIndexCode)
			    log.debug("Ignoring duplicated index");

			else if(errorCode == foreignKeyWithPartitionsCode)
			    log.debug("Ignoring FK referencing a partitioned table");
		    }
		}
		finally
		{
		    sqlConnector.closeConnection();
		}
	    }
	}
    }

    /**
     * Finds all the reference constraints (including implicitly array
     * references to entity tables) for the given list of entities.
     */

    private List<ReferenceConstraint> findReferenceConstraints(List<EntityDefinition> entities)
    {
	Map<String, EntityDefinition> entityLookup = generateEntityMap(entities);
	List<ReferenceConstraint> constraints = new ArrayList<ReferenceConstraint>();

	for(EntityDefinition entity : entities)
	{
	    List<AttributeDefinition> attributes = entity.getAttributes();

	    for(AttributeDefinition attribute : attributes)
	    {
		// First case : The attribute references another one.
		if(attribute.isReference())
		{
		    EntityDefinition destinyEntity = entityLookup.get(attribute.getReferencedEntity());
		    AttributeDefinition destinyAttribute = destinyEntity.getAttributeDefinition(attribute.getReferencedAttribute());

		    constraints.add(new ReferenceConstraint(entity, attribute, destinyEntity, destinyAttribute));
		}

		// Second case : The attribute is an array (implicitly references to its entity table).
		if(attribute.isArray())
		{
		    constraints.add(new ReferenceConstraint(entity, attribute, entity,
							    attribute.getUniqueIdDefinition()));
		}
	    }
	}

	return constraints;
    }

    /**
     * Builds a lookup map for the given list of entities (can be queried by
     * name).
     */

    private Map<String, EntityDefinition> generateEntityMap(List<EntityDefinition> entities)
    {
	Map<String, EntityDefinition> entityMap = new HashMap<String, EntityDefinition>(entities.size());

	for(EntityDefinition entity : entities)
	    entityMap.put(entity.getName(), entity);

	return entityMap;
    }
}
