package net.explorercat.compactcat.parser.json.jackson;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import net.explorercat.compactcat.definitions.DefinitionException;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.parser.json.CatalogParser;
import net.explorercat.cql.types.DateValue;
import net.explorercat.util.exceptions.JsonException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

/**
 * Concrete Implementation of CatalogParser using Jackson library. This
 * implementation stores catalog entities in memory while parsing data file,
 * hence it is possible that large memory is required with large data
 * files.Alternatively use JacksonCatalogParserStreamingBuilder implementation
 * which uses token stream there by less memory requirement.
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public class JacksonCatalogMemoryParser extends JacksonCatalogParser implements
		CatalogParser {

	// Logging
	private static Log log = LogFactory
			.getLog(JacksonCatalogMemoryParser.class);

	/**
	 * Method to parse Catalog data file. This implementataion stores catalog
	 * entities in memory while parsing data file, hence it is possible that
	 * large memory is required with large data files.
	 */

	@Override
	public void parseDataFile() throws JsonException, JsonParseException,
			JsonMappingException, DefinitionException, IOException {

		JsonNode rootNode = objectMapper.readValue(dataFile, JsonNode.class);

		// Mandatory
		JsonNode catalog = rootNode.path("catalog");
		JsonNode name = catalog.path("name");
		JsonNode description = catalog.path("description");
		JsonNode version = catalog.path("version");
		JsonNode catalogDate = catalog.path("date");

		if (catalog.isMissingNode() || name.isMissingNode()
				|| description.isMissingNode() || version.isMissingNode()
				|| catalogDate.isMissingNode()) {
			if (log.isDebugEnabled())
				log.debug("\n\tUnable to find mandatory catalog metadata: name or description or version or date in data file");
			throw new JsonException(
					"Unable to find mandatory catalog metadata: name or description or version or date in data file");
		}

		int year = Integer.parseInt(catalogDate.asText().substring(0, 4));
		int month = Integer.parseInt(catalogDate.asText().substring(5, 7));
		int day = Integer.parseInt(catalogDate.asText().substring(8, 10));
		DateValue releaseDate = new DateValue(year, month, day);

		catalogDefinition.setName(name.asText());
		catalogDefinition.setDescription(description.asText());
		catalogDefinition.setReleaseDate(releaseDate);
		catalogDefinition.setVersion(version.asText());

		if (log.isDebugEnabled())
			log.debug("\n\tcatalogName: " + catalogDefinition.getName()
					+ " catalogDescription: "
					+ catalogDefinition.getDescription() + " catalogDate:"
					+ catalogDefinition.getReleaseDate() + " catalogVersion:"
					+ catalogDefinition.getVersion());

		// Essential Steps
		parseTranslationParameters();
		translateCatalog();

		JsonNode entitiesNode = catalog.path("entities");
		if (entitiesNode.isMissingNode()) {
			if (log.isDebugEnabled())
				log.debug("\n\tUnable to find mandatory entities field in data file");
			throw new JsonException(
					"Unable to find mandatory entities field in data file");
		}

		Iterator<Entry<String, JsonNode>> entities = entitiesNode.getFields();
		while (entities.hasNext()) {
			Entry<String, JsonNode> entity = entities.next();
			EntityDefinition entityDefinition = catalogDefinition
					.getEntityDefinition(entity.getKey());

			// Translate Entity
			entityTranslator.translateEntity(entityDefinition,
					translationParameters);
			
			
			for (JsonNode entityInstanceValue : entity.getValue()) {
				dataRows.add(parseEntityData(entity.getKey(),
						entityInstanceValue));
				if (dataRows.size() >= consecutiveRowsToRead) {
					if (log.isDebugEnabled())
						log.debug("\tAdding data rows:  " + dataRows.size()
								+ " to :" + entity.getKey());
					entityTranslator.addDataRowsToEntityTranslation(dataRows,
							entityDefinition);
					dataRows.clear();
				}
			}

			if (log.isDebugEnabled())
				log.debug("\tAdding data rows:  " + dataRows.size() + " to :"
						+ entity.getKey());
			entityTranslator.addDataRowsToEntityTranslation(dataRows,
					entityDefinition);
			dataRows.clear();
			entityTranslator.finishTranslation(entityDefinition,
					translationParameters);
		}
	}

}
