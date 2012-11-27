package net.explorercat.compactcat.parser.json.jackson;

import java.io.IOException;

import net.explorercat.compactcat.definitions.DefinitionException;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.parser.json.CatalogParser;
import net.explorercat.cql.types.DateValue;
import net.explorercat.util.exceptions.JsonException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;

public class JacksonCatalogStreamingParser extends JacksonCatalogParser
		implements CatalogParser {
	private static Log log = LogFactory
			.getLog(JacksonCatalogStreamingParser.class);

	@Override
	public void parseDataFile() throws JsonException, JsonParseException,
			JsonMappingException, DefinitionException, IOException {

		// Using Streaming Json parsing
		// Mandatory
		JsonNode name = getFieldNode("name");
		JsonNode description = getFieldNode("description");
		JsonNode version = getFieldNode("version");
		JsonNode catalogDate = getFieldNode("date");

		if (name.isMissingNode() || description.isMissingNode()
				|| version.isMissingNode() || catalogDate.isMissingNode()) {
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

		// Streaming
		jsonParser = mappingJsonFactory.createJsonParser(dataFile);
		JsonToken currentToken = jsonParser.nextToken();
		while (currentToken != null) {
			if (currentToken == JsonToken.START_OBJECT
					&& jsonParser.getCurrentName() != null
					&& jsonParser.getCurrentName().equalsIgnoreCase("entities")) {
				while (currentToken != JsonToken.END_OBJECT) {

					if (currentToken == JsonToken.START_ARRAY
							&& jsonParser.getCurrentName() != null) {
						System.out.println("\t\t currentToken :" + currentToken
								+ "\t jp GetText: " + jsonParser.getText()
								+ " jp GetCurrentName: "
								+ jsonParser.getCurrentName()
								+ " jp isExpectedStartArrayToken: "
								+ jsonParser.isExpectedStartArrayToken());
						String entityName = jsonParser.getCurrentName();
						// Translate Entity
						EntityDefinition entityDefinition = catalogDefinition
								.getEntityDefinition(entityName);
						entityTranslator.translateEntity(entityDefinition,
								translationParameters);
						while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
							JsonNode entityInstanceValue = jsonParser
									.readValueAsTree();
							//System.out.println("\t\t\t\t entityInstanceValue: "
								//	+ entityInstanceValue);
							dataRows.add(parseEntityData(entityName,
									entityInstanceValue));
							if (dataRows.size() >= consecutiveRowsToRead) {
								if (log.isDebugEnabled())
									log.debug("\n\tAdding data rows:  "
											+ dataRows.size() + " to :"
											+ entityName);
								entityTranslator
										.addDataRowsToEntityTranslation(
												dataRows, entityDefinition);
								dataRows.clear();
							}
						}
						if (log.isDebugEnabled())
							log.debug("\n\tAdding data rows:  "
									+ dataRows.size() + " to :" + entityName);
						entityTranslator.addDataRowsToEntityTranslation(
								dataRows, entityDefinition);
						dataRows.clear();
						entityTranslator.finishTranslation(entityDefinition,
								translationParameters);

					}
					currentToken = jsonParser.nextToken();
				}
			}
			currentToken = jsonParser.nextToken();
		}
	}

	private JsonNode getFieldNode(String fieldToFind) throws JsonException,
			JsonParseException, JsonMappingException, DefinitionException,
			IOException {
		JsonNode filedNode = null;
		jsonParser = mappingJsonFactory.createJsonParser(dataFile);
		JsonToken currentToken = jsonParser.nextToken();

		while (currentToken != null) {
			if (currentToken == JsonToken.VALUE_STRING
					&& jsonParser.getCurrentName() != null
					&& jsonParser.getCurrentName()
							.equalsIgnoreCase(fieldToFind)) {
				filedNode = jsonParser.readValueAsTree();
				System.out.println("getFieldNode: fieldToFind: " + fieldToFind
						+ "\t filedNode: " + filedNode);
				break;
			}

			currentToken = jsonParser.nextToken();
		}
		return filedNode;
	}
}
