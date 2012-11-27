package net.explorercat.compactcat.translators.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Inserts entity data into the DB that represents its MySQL translation. This
 * class will optimise insertions by means of prepared queries (using prepared
 * statements).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 8 Feb 2011
 */

public class EntityDataInserterMySQL
{
    private static Log log = LogFactory.getLog(EntityDataInserterMySQL.class);

    private EntityTableCreatorMySQL tableCreator;
    private SQLDataConnector sqlConnector;

    // Connector with a prepared statement to insert data for 
    // the regular attributes of the entity.
    private SQLDataConnector preparedConnectorForEntity;
    private List<Integer> regularAttributeIndices;

    // Connectors with a prepared statement to insert data for
    // the array attributes of the entity.
    private List<SQLDataConnector> preparedConnectorsForArrays;
    private List<Integer> arrayAttributeIndices;
    private List<AttributeDefinition> arrayAttributeDefinitions;

    /**
     * Creates a new data inserter.
     * 
     * @param tableCreator The translator that is being used to translate the
     *        entities.
     */

    public EntityDataInserterMySQL(EntityTableCreatorMySQL tableCreator)
    {
	this.tableCreator = tableCreator;
	this.sqlConnector = SQLDataConnectorFactory.getInstance().getDataConnector();

	this.preparedConnectorForEntity = null;
	this.regularAttributeIndices = new ArrayList<Integer>();

	this.preparedConnectorsForArrays = new ArrayList<SQLDataConnector>();
	this.regularAttributeIndices = new ArrayList<Integer>();
	this.arrayAttributeDefinitions = new ArrayList<AttributeDefinition>();
	this.arrayAttributeIndices = new ArrayList<Integer>();
    }

    /**
     * Creates the prepared statements (using individual data connectors) that
     * will be used to insert data into the tables. This will improve the
     * performance of the operation.
     * 
     * @param entityDefinition The definition of the entity being translated.
     */

    public void prepareConnections(EntityDefinition entityDefinition) throws TranslationException
    {
	// Remove any prepared connector set up earlier.
	removePreparedConnectors();

	SQLDataConnectorFactory connectorFactory = SQLDataConnectorFactory.getInstance();
	this.preparedConnectorForEntity = connectorFactory.getDataConnector();

	String tableName = tableCreator.getEntityTableBaseName(entityDefinition);

	// Insert statement for the entity table.
	StringBuilder preparedInsertSQL = new StringBuilder();
	preparedInsertSQL.append("INSERT INTO ").append(tableName).append(" (");

	// Count the attributes that are NOT arrays (their values will be placed on different tables).	
	int regulatAttributesCount = 0;
	List<AttributeDefinition> attributes = entityDefinition.getAttributes();

	try
	{
	    for(int i = 0; i < attributes.size(); ++i)
	    {
		AttributeDefinition attribute = attributes.get(i);

		if(!attribute.isArray())
		{
		    preparedInsertSQL.append(attribute.getName()).append(",");
		    ++regulatAttributesCount;
		    this.regularAttributeIndices.add(i);
		}
		else
		{
		    this.arrayAttributeIndices.add(i);
		    this.arrayAttributeDefinitions.add(attribute);
		    createInsertionPreparedStatementForArray(entityDefinition, attribute);
		}
	    }

	    // Remove the last comma.
	    preparedInsertSQL.deleteCharAt(preparedInsertSQL.length() - 1);
	    preparedInsertSQL.append(") VALUES (");

	    // Add the data place-holders.
	    for(int i = 0; i < regulatAttributesCount; ++i)
		preparedInsertSQL.append("?,");

	    // Remove the last comma.
	    preparedInsertSQL.deleteCharAt(preparedInsertSQL.length() - 1);
	    preparedInsertSQL.append(")");

	    this.preparedConnectorForEntity.createPreparedStatement(preparedInsertSQL.toString());
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error creating insert prepared statement for " + tableName, e);
	}
    }

    /**
     * Creates a prepared statement to be used when inserting the values for an
     * array attribute in the DB.
     */

    private void createInsertionPreparedStatementForArray(EntityDefinition entityDefinition,
							  AttributeDefinition arrayDefinition)
	throws TranslationException
    {
	try
	{
	    SQLDataConnectorFactory connectorFactory = SQLDataConnectorFactory.getInstance();
	    SQLDataConnector arrayAttributeConnector = connectorFactory.getDataConnector();
	    this.preparedConnectorsForArrays.add(arrayAttributeConnector);

	    String tableName = tableCreator.getArrayAttributeTableName(entityDefinition, arrayDefinition.getName());

	    String preparedInsertSQL = "INSERT INTO " + tableName + " VALUES (?,?)";
	    arrayAttributeConnector.createPreparedStatement(preparedInsertSQL);
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error creating prepared statement for array table", e);
	}
    }

    /**
     * Inserts data into the DB using a prepared statement.
     * 
     * @param rows The list of data rows to be inserted (a row of values per
     *        entity instance).
     * 
     * @param entityDefinition Definition of the entity whose data is being
     *        inserted.
     */

    public void insertEntityData(List<List<DataValue>> rows, EntityDefinition entityDefinition)
	throws TranslationException
    {
	if(log.isDebugEnabled())
	{
	    long totalMemory = Runtime.getRuntime().totalMemory();
	    long freeMemory = Runtime.getRuntime().freeMemory();
	    log.debug("Inserting next " + rows.size() + " data rows. [F:" + freeMemory + " / T:" + totalMemory + "]");
	}

	List<Object> entityValues = new ArrayList<Object>(this.regularAttributeIndices.size());

	for(int k = 0; k < this.regularAttributeIndices.size(); ++k)
	    entityValues.add(null);

	try
	{
	    for(int i = 0; i < rows.size(); ++i)
	    {
		List<DataValue> currentRow = rows.get(i);

		// Insert regular values for the entity (entity main table).
		// Invalid values are replaced with nulls.
		for(int j = 0; j < this.regularAttributeIndices.size(); ++j)
		{
		    DataValue value = currentRow.get(regularAttributeIndices.get(j));
		    AttributeDefinition attribute = entityDefinition.getAttributeDefinition(regularAttributeIndices.get(j));

		    if(value.getType() == DataType.NULL)
			entityValues.set(j, null);

		    else if(attribute.isValidValue(value))
			entityValues.set(j, value.getValueAsObject());

		    else
		    {
			log.error("Invalid value, setting null [" + attribute.getName() + " : " + value + "]");
			entityValues.set(j, null);
		    }
		}

		this.preparedConnectorForEntity.executePreparedStatementAsUpdate(entityValues);

		// Insert values for array attributes of the entity.
		for(int k = 0; k < this.arrayAttributeIndices.size(); ++k)
		{
		    int lastEntityId = retrieveLastEntityId(entityDefinition);
		    int arrayIndex = this.arrayAttributeIndices.get(k);
		    DataValue arrayValue = currentRow.get(arrayIndex);

		    InsertArrayDataUsingPreparedStatement(this.preparedConnectorsForArrays.get(k), lastEntityId,
							  arrayValue, this.arrayAttributeDefinitions.get(k));
		}
	    }
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error inserting entity data", e);
	}
    }

    /**
     * Inserts data for an array into the DB using a prepared statement.
     * 
     * @param preparedConnector The connector that contains the prepared
     *        statement to be executed.
     * @param entityId Identifier of the entity that contains the array
     *        attribute.
     * @param arrayValue The array data value that will be inserted.
     * @param attribute Definition of the array attribute.
     */

    private void InsertArrayDataUsingPreparedStatement(SQLDataConnector preparedConnector, int entityId,
						       DataValue arrayValue, AttributeDefinition attribute)
	throws TranslationException
    {
	if(!attribute.isValidValue(arrayValue))
	    log.error("Invalid array value: " + arrayValue);

	else
	{
	    // We insert each array value in a different row.
	    List<Object> rowValues = new ArrayList<Object>(2);
	    for(int i = 0; i < 2; ++i)
		rowValues.add(null);

	    try
	    {
		List<DataValue> arrayValues = arrayValue.getValueAsArray();

		// Insert the array values as pairs (one per row) <entityId, value>.
		// Invalid values are replaced with nulls.
		for(int i = 0; i < arrayValues.size(); ++i)
		{
		    rowValues.set(0, entityId);
		    rowValues.set(1, arrayValues.get(i).getValueAsObject());
		    preparedConnector.executePreparedStatementAsUpdate(rowValues);
		}
	    }
	    catch(Exception e)
	    {
		throw new TranslationException("Error inserting array data", e);
	    }
	}
    }

    /**
     * Auxiliary method that retrieves id of the last entity instance inserted
     * into the DB for the given entity.
     * 
     * @return The identifier of the last entity instance inserted into the the
     *         database.
     */

    private int retrieveLastEntityId(EntityDefinition entityDefinition) throws TranslationException
    {
	String tableName = tableCreator.getEntityTableBaseName(entityDefinition);
	String sql = "SELECT MAX(" + PropertyLookup.getUniqueIdAttribute() + ") FROM " + tableName;

	try
	{
	    sqlConnector.closeConnection();
	    ResultSet result = sqlConnector.executeQuery(sql);

	    // We assume the last id added is the id of the last entity.
	    // If the table is empty something went wrong.
	    if(result.first())
		return result.getInt(1);
	    else
		throw new TranslationException("Error retrieving the last entity id");
	}
	catch(SQLException e)
	{
	    throw new TranslationException("Error retrieving the catalog id", e);
	}
	finally
	{
	    sqlConnector.closeConnection();
	}
    }

    /**
     * Removes any prepared connectors already created.
     */

    private void removePreparedConnectors()
    {
	if(this.preparedConnectorForEntity != null)
	    this.preparedConnectorForEntity.closeConnection();

	this.preparedConnectorForEntity = null;
	this.regularAttributeIndices.clear();

	for(SQLDataConnector connector : this.preparedConnectorsForArrays)
	    connector.closeConnection();

	this.preparedConnectorsForArrays.clear();
	this.arrayAttributeIndices.clear();
    }
}
