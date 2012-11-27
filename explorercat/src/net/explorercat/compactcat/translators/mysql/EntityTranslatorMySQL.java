package net.explorercat.compactcat.translators.mysql;

import java.util.List;

import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.translators.EntityTranslator;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.compactcat.translators.mysql.TranslationParameterMySQL.MySQLParameterType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.util.exceptions.TranslationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Translates an entity into a MySQL DB representation. Basically it translates
 * the entities and their relationships into a set of tables.
 * 
 * Technical details about the translation can be specified as parameters (e.g.
 * where to use partitions, index creation, etc.)
 * 
 * Supported parameters:
 * 
 * - PARTITIONING <entity> <attribute> <n> : Creates <n> partitions (by hash) in
 * the table created for <entity> based on <attribute>. If the entity is an
 * array, the array table is partitioned (by hash using entity_id).
 * 
 * - INDEXING <entity> <attribute> : Creates an index for <attribute> in the
 * table created for <entity> (if the attribute is an array, the index is
 * applied to its table).
 * 
 * - NOSTATS <entity> : Does not generate stats for the entity attributes.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 9 Feb 2011
 */

public class EntityTranslatorMySQL implements EntityTranslator
{
    private static Log log = LogFactory.getLog(EntityTranslatorMySQL.class);

    // Objects that will assist the translation.
    private EntityTableCreatorMySQL tableCreator;
    private EntityDictionaryTranslatorMySQL dictionaryTranslator;
    private EntityStatsTranslatorMySQL statsTranslator;
    private EntityDataInserterMySQL dataInserter;

    /**
     * Creates an entity translator for the entities of the given catalog.
     * 
     * @param catalogId The unique identifier of the catalog in the DB.
     */

    public EntityTranslatorMySQL(int catalogId)
    {
	this.tableCreator = new EntityTableCreatorMySQL(catalogId);
	this.dictionaryTranslator = new EntityDictionaryTranslatorMySQL(tableCreator);
	this.statsTranslator = new EntityStatsTranslatorMySQL(tableCreator);
	this.dataInserter = new EntityDataInserterMySQL(tableCreator);
    }

    @Override
    public void translateEntity(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	if(log.isDebugEnabled())
	    log.debug("Translating entity " + entityDefinition.getName() + " into MySQL representation");

	tableCreator.createEntityDataTables(entityDefinition, parameters);

	dictionaryTranslator.createEntityDictionaryTable(entityDefinition, parameters);
	dictionaryTranslator.populateEntityDictionaryTable(entityDefinition, parameters);

	statsTranslator.createEntityStatsTable(entityDefinition, parameters);
	dataInserter.prepareConnections(entityDefinition);
    }

    @Override
    public void addDataRowsToEntityTranslation(List<List<DataValue>> dataRows, EntityDefinition entityDefinition)
	throws TranslationException
    {
	dataInserter.insertEntityData(dataRows, entityDefinition);
    }

    @Override
    public void finishTranslation(EntityDefinition entityDefinition, List<TranslationParameter> parameters)
	throws TranslationException
    {
	if(log.isDebugEnabled())
	    log.debug("Finishing entity translation for " + entityDefinition.getName());

	boolean generateEntityStats = true;

	// Ugly but reduces complexity.
	for(TranslationParameter parameter : parameters)
	    if(parameter.getType() == MySQLParameterType.NOSTATS
	       && ((TranslationParameterMySQL) (parameter)).isApplicableToEntity(entityDefinition))
		generateEntityStats = false;

	if(generateEntityStats)
	    statsTranslator.calculateStatsAndPopulateStatsTable(entityDefinition);
	else
	    statsTranslator.populateStatsTableWithNullValues(entityDefinition);
    }
}
