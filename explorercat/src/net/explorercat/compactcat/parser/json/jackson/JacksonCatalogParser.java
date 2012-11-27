package net.explorercat.compactcat.parser.json.jackson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.CatalogDefinition;
import net.explorercat.compactcat.definitions.DefinitionException;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.definitions.RegularAttributeDefinition;
import net.explorercat.compactcat.parser.json.CatalogParser;
import net.explorercat.compactcat.translators.CatalogTranslator;
import net.explorercat.compactcat.translators.EntityTranslator;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.compactcat.translators.TranslationParameterParser;
import net.explorercat.cql.types.ArrayValue;
import net.explorercat.cql.types.BooleanValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DateValue;
import net.explorercat.cql.types.IntegerValue;
import net.explorercat.cql.types.NullValue;
import net.explorercat.cql.types.RealValue;
import net.explorercat.cql.types.StringValue;
import net.explorercat.util.exceptions.JsonException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * A base class for CatalogParser implementation
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public abstract class JacksonCatalogParser implements CatalogParser {

	// Logging
	private static Log log = LogFactory.getLog(JacksonCatalogParser.class);

	protected File dictionaryFile;
	protected File dataFile;
	protected CatalogTranslator catalogTranslator;
	protected TranslationParameterParser parameterParser;
	protected List<String> translationParametersAsStrings;
	protected List<TranslationParameter> translationParameters;
	protected int consecutiveRowsToRead = 10;

	protected CatalogDefinition catalogDefinition = null;
	protected EntityTranslator entityTranslator;
	protected List<List<DataValue>> dataRows;

	protected ObjectMapper objectMapper;
	protected JsonFactory mappingJsonFactory = null;
	protected JsonParser jsonParser = null;

	/**
	 * Builds a new CatalogDefinition.
	 */

	public JacksonCatalogParser() {
		this.objectMapper = new ObjectMapper();
		this.catalogDefinition = new CatalogDefinition();
		this.dataRows = new ArrayList<List<DataValue>>(consecutiveRowsToRead);
		this.mappingJsonFactory = new MappingJsonFactory();
	}

	/**
	 * Sets Catalog dictionary file
	 * 
	 * @param dictionaryFile
	 *            Catalog dictionary file.
	 */

	@Override
	public void setDictionaryFile(File dictionaryFile) {
		this.dictionaryFile = dictionaryFile;
	}

	/**
	 * Sets Catalog data file
	 * 
	 * @param dataFile
	 *            catalog data file.
	 */

	@Override
	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}

	/**
	 * Sets CatalogTranslator Instance
	 * 
	 * @param translator
	 *            CatalogTranslator Instance.
	 */

	@Override
	public void setCatalogTranslator(CatalogTranslator catalogTranslator) {
		this.catalogTranslator = catalogTranslator;
	}

	/**
	 * Sets TranslationParameterParser Instance
	 * 
	 * @param parameterParser
	 *            TranslationParameterParser Instance.
	 */

	@Override
	public void setTranslationParameterParser(
			TranslationParameterParser parameterParser) {
		this.parameterParser = parameterParser;
	}

	/**
	 * Sets TranslationParameters of type List<String>
	 * 
	 * @param translationParametersAsStrings
	 *            A list that contains the translation parameters (String).
	 */

	@Override
	public void setTranslationParametersAsStrings(
			List<String> translationParametersAsStrings) {
		this.translationParametersAsStrings = translationParametersAsStrings;
	}

	/**
	 * Sets number of rows to read while translating Catalog data file
	 * 
	 * @param consecutiveRowsToRead
	 *            Number of rows to read while translating Catalog data file.
	 */

	@Override
	public void setConsecutiveRowsToRead(int consecutiveRowsToRead) {
		this.consecutiveRowsToRead = consecutiveRowsToRead;
	}

	/**
	 * Method to parse Catalog dictionary & data file, translating them into
	 * Chosen representation ( e.g.MySql)
	 */

	@Override
	public void parseCatalog() throws JsonException, JsonParseException,
			JsonMappingException, DefinitionException, IOException {
		parseDictionaryFile();
		parseDataFile();
	}

	/**
	 * Returns CatalogDefinition instance that is modelling current Catalog
	 * dictionary and data
	 * 
	 * @return CatalogDefinition
	 */

	public CatalogDefinition getCatalogDefinition() {
		return catalogDefinition;
	}

	/**
	 * Method to parse Catalog data file. Different concrete implementation
	 * types differ at implementing data file parsing.
	 * 
	 * JacksonCatalogStreamingParser uses token stream to while parsing data
	 * file utilising less memory. Recommend to use when deploying large data
	 * sets.
	 * 
	 * Memory:JacksonCatalogMemoryParser stores catalog entities in memory while
	 * parsing data file, hence it is possible that large memory is required
	 * with large data files.
	 */

	protected abstract void parseDataFile() throws JsonException,
			JsonParseException, JsonMappingException, DefinitionException,
			IOException;

	/**
	 * Method to parse translation parameters.
	 * 
	 */

	protected void parseTranslationParameters() {
		this.translationParameters = new ArrayList<TranslationParameter>();
		if (this.parameterParser != null
				&& this.translationParametersAsStrings != null) {
			for (String currentParameter : this.translationParametersAsStrings) {
				this.translationParameters.add(this.parameterParser
						.parseParameter(currentParameter));
			}

		}
	}

	/**
	 * Method to translates catalog, gets new entity translator.
	 * 
	 */

	protected void translateCatalog() {
		this.catalogTranslator.translateCatalog(this.catalogDefinition,
				this.translationParameters);
		this.entityTranslator = this.catalogTranslator.getEntityTranslator();
	}

	/**
	 * Method to parse Catalog dictionary file. Irrespect of the concrete
	 * implementation type (Stream:JacksonCatalogStreamingParser or
	 * Memory:JacksonCatalogMemoryParser), dictionary file is always parsed
	 * using Jackson tree model (like XML DOM) since dictionary file is always
	 * expected to be small in size.
	 */

	public void parseDictionaryFile() throws JsonParseException,
			JsonMappingException, IOException, DefinitionException {
		if (log.isDebugEnabled())
			log.debug("\n\tParsing DictionaryFile : "
					+ dictionaryFile.toString());

		JsonNode rootNode = objectMapper.readValue(dictionaryFile,
				JsonNode.class);

		JsonNode entitiesNode = rootNode.path("dictionary").path("entities");
		if (entitiesNode.isMissingNode()) {
			if (log.isDebugEnabled())
				log.debug("\n\tUnable to find mandatory entities field in dictionary file");
			throw new JsonException(
					"Unable to find mandatory entities field in dictionary file");
		}

		Iterator<Entry<String, JsonNode>> entities = entitiesNode.getFields();
		while (entities.hasNext()) {
			Entry<String, JsonNode> entity = entities.next();
			catalogDefinition
					.addEntityDefinition(parseDictionaryEntityDefintion(entity));
		}

		if (catalogDefinition.getEntityDefinitions().size() <= 0) {
			if (log.isDebugEnabled())
				log.error("\n\tUnable to find any entity defintions in Dictionary file: "
						+ dictionaryFile.toString());
			throw new JsonException(
					"Unable to find any entity defintions in Dictionary file: "
							+ dictionaryFile.toString());
		}
		// displayDictionaryDefinition();
	}

	// Auxilary Methods

	/**
	 * Method to parse a dictionary entity definition.
	 * 
	 * @param Entry
	 *            <String, JsonNode> entity A map entry node with the key
	 *            representing a dictionary Entity name and value representing
	 *            JsonNode of an dictionary Entity
	 * @return EntityDefinition dictionary entity definition mapped to
	 *         EntityDefinition
	 */

	private EntityDefinition parseDictionaryEntityDefintion(
			Entry<String, JsonNode> entity) throws DefinitionException {

		String entityName = entity.getKey();
		JsonNode entityValue = entity.getValue();
		EntityDefinition entityDefinition = new EntityDefinition();

		// Not Mandatory
		JsonNode description = entityValue.path("description");
		if (description.isMissingNode()) {
			entityDefinition.setDescription(null);
		} else {
			entityDefinition.setDescription(description.asText());
		}

		// Mandatory
		entityDefinition.setName(entityName);
		JsonNode propertiesNode = entityValue.path("properties");

		if (propertiesNode.isMissingNode()) {
			if (log.isDebugEnabled())
				log.debug("\n\tUnable to find mandatory properties field for entity: "
						+ entityName.toString() + " in dictionary file");
			throw new JsonException(
					"Unable to find mandatory properties field for entity: "
							+ entityName.toString() + " in dictionary file");
		}

		Iterator<Entry<String, JsonNode>> properties = propertiesNode
				.getFields();
		while (properties.hasNext()) {
			Entry<String, JsonNode> attribute = properties.next();
			entityDefinition
					.addAttributeDefintion(parseDictionaryEntityPropertyAttributeDefintion(attribute));
		}

		if (entityDefinition.getAttributes().size() <= 0) {
			if (log.isDebugEnabled())
				log.error("\n\tUnable to find any property defintions for entity"
						+ entityName.toString() + " in Dictionary file ");
			throw new JsonException(
					"Unable to find any property defintions for entity"
							+ entityName.toString() + " in Dictionary file ");
		}
		return entityDefinition;
	}

	/**
	 * Method to parse a dictionary entity property attribute definition.
	 * 
	 * @param Entry
	 *            <String, JsonNode> attribute A map entry node with the key
	 *            representing a dictionary Entity property attribute name and
	 *            value representing JsonNode of an property attribute
	 * @return RegularAttributeDefinition dictionary entity property attribute
	 *         definition mapped to RegularAttributeDefinition
	 */

	private RegularAttributeDefinition parseDictionaryEntityPropertyAttributeDefintion(
			Entry<String, JsonNode> attribute) {
		String attributeName = attribute.getKey();
		JsonNode attributeValue = attribute.getValue();
		RegularAttributeDefinition propertyAttribute = new RegularAttributeDefinition();

		// Mandatory Attributes
		DataType attributeType = parsePropertyAttributeType(attributeName,
				attributeValue);

		// Non Mandatory Attributes
		JsonNode description = attributeValue.path("description");
		DataValue minimumValue = parsePropertyAttributeMinimumValue(
				attributeValue, attributeType);
		DataValue maximumValue = parsePropertyAttributeMaximimValue(
				attributeValue, attributeType);
		List<DataValue> values = parsePropertyAttributeValues(attributeValue,
				attributeType);

		propertyAttribute.setName(attributeName);
		propertyAttribute.setType(attributeType);
		// TODO CHECK WITHOUT DESCRIPTION AT DATABASE
		if (description.isMissingNode()) {
			propertyAttribute.setDescription(null);
		} else {
			propertyAttribute.setDescription(description.asText());
		}
		propertyAttribute.setMinimumValue(minimumValue);
		propertyAttribute.setMaximumValue(maximumValue);
		for (DataValue value : values) {
			propertyAttribute.registerAllowedValue(value);
		}
		parsePropertyAttributeReferences(attributeValue, propertyAttribute);

		return propertyAttribute;
	}

	/**
	 * Method to parse a dictionary entity property attribute type. Type is
	 * Mandatory attribute.
	 * 
	 * @param attributeName
	 *            Attribute name
	 * @param attribute
	 *            attribute JsnoNode
	 * 
	 * @return DataType attribute data type
	 */

	private DataType parsePropertyAttributeType(String attributeName,
			JsonNode attribute) {
		JsonNode typeNode = attribute.path("type");
		DataType attributeType = null;

		if (typeNode.isMissingNode()) {
			if (log.isDebugEnabled())
				log.error("\n\tUnable to find type attribute for "
						+ attributeName + " in Dictionary file ");
			throw new JsonException("Unable to find type attribute for "
					+ attributeName + " in Dictionary file ");
		} else {
			DataType[] allowedDataTypes = DataType
					.getDictionaryDefintionTypes();
			for (DataType allowedDataType : allowedDataTypes) {
				if (typeNode.asText().equalsIgnoreCase(
						allowedDataType.getEquivalentDictionaryDefintionType())) {
					attributeType = allowedDataType;
				}
			}
		}

		if (attributeType == null) {
			if (log.isDebugEnabled())
				log.debug("\n\tUnknown type: " + typeNode.asText()
						+ " of property attribute: " + attributeName
						+ " in dictionary file");
			throw new JsonException("Unknown type: " + typeNode.asText()
					+ " of property attribute: " + attributeName
					+ " in dictionary file");
		}
		return attributeType;
	}

	/**
	 * Method to parse a dictionary entity property attribute Minimum value.
	 * Minimum is optional attribute and can be of Integer or Real data types.
	 * 
	 * @param attribute
	 *            attribute JsnoNode
	 * @param attributeType
	 *            attribute DataType
	 * 
	 * @return DataValue attribute Minimum value
	 */

	private DataValue parsePropertyAttributeMinimumValue(JsonNode attribute,
			DataType attributeType) {
		JsonNode minimum = attribute.path("minimum");
		DataValue minimumValue = null;
		if (!minimum.isMissingNode()) {
			switch (attributeType) {
			case INTEGER:
				minimumValue = new IntegerValue(Integer.parseInt(minimum
						.asText()));
				break;
			case REAL:
				minimumValue = new RealValue(Float.parseFloat(minimum.asText()));
				break;
			}
		}
		return minimumValue;
	}

	/**
	 * Method to parse a dictionary entity property attribute Maximum value.
	 * Maximum is optional attribute and can be of Integer or Real data types.
	 * 
	 * @param attribute
	 *            attribute JsnoNode
	 * @param attributeType
	 *            attribute DataType
	 * 
	 * @return DataValue attribute Minimum value
	 */

	private DataValue parsePropertyAttributeMaximimValue(JsonNode attribute,
			DataType attributeType) {
		JsonNode maximum = attribute.path("maximum");
		DataValue maximumValue = null;
		if (!maximum.isMissingNode()) {
			switch (attributeType) {
			case INTEGER:
				maximumValue = new IntegerValue(Integer.parseInt(maximum
						.asText()));
				break;
			case REAL:
				maximumValue = new RealValue(Float.parseFloat(maximum.asText()));
				break;
			}
		}
		return maximumValue;
	}

	/**
	 * Method to parse a dictionary entity property attribute allowed values.
	 * Values is optional array attribute and all elements of the array must be
	 * of attribute type. Expects a single array of allowed values
	 * 
	 * @param attribute
	 *            attribute JsnoNode
	 * @param attributeType
	 *            attribute DataType
	 * 
	 * @return List<DataValue> List allowed DataValue
	 */

	private List<DataValue> parsePropertyAttributeValues(JsonNode attribute,
			DataType attributeType) {
		List<DataValue> values = new ArrayList<DataValue>();

		JsonNode valuesNode = attribute.path("values");
		if (!valuesNode.isMissingNode() && valuesNode.isArray()) {
			// Expecting a single array of values.
			Iterator<JsonNode> arrayValues = valuesNode.getElements();
			while (arrayValues.hasNext()) {
				JsonNode value = arrayValues.next();
				if (value.isContainerNode() == false
						&& attributeType.isArray() == false) {
					values.add(attributeType.parseValue(value.asText()));
				} else {
					if (log.isDebugEnabled())
						log.debug("\n\tExpecting a single array of allowed values for property: "
								+ attribute.toString()
								+ " each of type: "
								+ attributeType
										.getEquivalentDictionaryDefintionType()
								+ ". Array of Array found: "
								+ values.toString() + " in dictionary file");
					throw new JsonException(
							"Expecting a array value for allowed values property attribute: "
									+ attribute.toString()
									+ " each of type: "
									+ attributeType
											.getEquivalentDictionaryDefintionType()
									+ ". Array of Array found: "
									+ values.toString() + " in dictionary file");

				}
			}
		}
		return values;
	}

	/**
	 * Method to parse a dictionary entity property attribute refereces (i.e.
	 * entity and property. References is optional object. Expects a single
	 * object of reference. Properties of array type can reference other
	 * properties, each element will be treated as an individual reference
	 * 
	 * @param attribute
	 *            attribute JsnoNode
	 * @param propertyAttribute
	 *            RegularAttributeDefinition to which references must add
	 * 
	 */

	private void parsePropertyAttributeReferences(JsonNode attribute,
			RegularAttributeDefinition propertyAttribute) {
		JsonNode references = attribute.path("references");
		if (!references.isMissingNode() && references.isObject()) {
			String referencedEntity = references.path("entity").getTextValue();
			String referencedAttribute = references.path("property")
					.getTextValue();
			propertyAttribute.setReference(referencedEntity,
					referencedAttribute);
		}

	}

	/**
	 * Method to parse a data entity instance.
	 * 
	 * @param entityName
	 *            Name of the entity
	 * @param entityInstanceValue
	 *            Entity instance data value of JsonNode array
	 * @return List<DataValue> An entity object field data value
	 */

	protected List<DataValue> parseEntityData(String entityName,
			JsonNode entityInstanceValue) {
		List<AttributeDefinition> entityAttributes = catalogDefinition
				.getEntityDefinition(entityName).getAttributes();

		DataValue attributeValue = null;

		List<DataValue> rowValues = new ArrayList<DataValue>();
		for (int i = 0; i < entityAttributes.size(); i++) {
			DataType attributeType = entityAttributes.get(i).getType();
			JsonNode attributeData = entityInstanceValue.get(i);
			if (attributeType == null) {
				if (log.isDebugEnabled())
					log.debug("\n\tMissing data: " + attributeData
							+ " for property attribute: "
							+ entityAttributes.get(i).getName() + " of type: "
							+ attributeType + " in data file");
				throw new JsonException("Missing data: " + attributeData
						+ " for property attribute: "
						+ entityAttributes.get(i).getName() + " of type: "
						+ attributeType + " in data file");
			}

			if (attributeData.asText().equalsIgnoreCase("null")) {
				attributeValue = new NullValue();
			} else if (!attributeType.isArray() && !attributeData.isArray()) {
				// Normal values
				if (attributeData.asText().equalsIgnoreCase("null")) {
					attributeValue = new NullValue();
				} else {
					attributeValue = attributeType.parseValue(attributeData
							.asText());
				}
			} else if (attributeType.isArray() && attributeData.isArray()) {
				// Array values
				switch (attributeType) {
				case ARRAY_INTEGER:
					List<DataValue> integerArrayValues = new ArrayList<DataValue>();
					for (JsonNode attributeDataElm : attributeData) {
						if (attributeDataElm.asText().equalsIgnoreCase("null")) {
							integerArrayValues.add(new NullValue());
						} else {
							integerArrayValues.add(new IntegerValue(Integer
									.parseInt(attributeDataElm.asText())));
						}
					}
					attributeValue = new ArrayValue(integerArrayValues);
					break;
				case ARRAY_REAL:
					List<DataValue> realArrayValues = new ArrayList<DataValue>();
					for (JsonNode attributeDataElm : attributeData) {
						if (attributeDataElm.asText().equalsIgnoreCase("null")) {
							realArrayValues.add(new NullValue());
						} else {
							realArrayValues.add(new RealValue(Float
									.parseFloat(attributeDataElm.asText())));
						}
					}
					attributeValue = new ArrayValue(realArrayValues);
					break;
				case ARRAY_DATE:
					List<DataValue> dateArrayValues = new ArrayList<DataValue>();
					for (JsonNode attributeDataElm : attributeData) {
						if (attributeDataElm.asText().equalsIgnoreCase("null")) {
							dateArrayValues.add(new NullValue());
						} else {
							int eyear = Integer.parseInt(attributeDataElm
									.asText()

									.substring(0, 4));
							int emonth = Integer.parseInt(attributeDataElm
									.asText().substring(5, 7));
							int eday = Integer.parseInt(attributeDataElm
									.asText().substring(8, 10));

							dateArrayValues.add(new DateValue(eyear, emonth,
									eday));
						}
					}
					attributeValue = new ArrayValue(dateArrayValues);
					break;
				case ARRAY_BOOLEAN:
					List<DataValue> booleanArrayValues = new ArrayList<DataValue>();
					for (JsonNode attributeDataElm : attributeData) {
						if (attributeDataElm.asText().equalsIgnoreCase("null")) {
							booleanArrayValues.add(new NullValue());
						} else {
							booleanArrayValues.add(new BooleanValue(Boolean
									.parseBoolean(attributeDataElm.asText())));
						}
					}
					attributeValue = new ArrayValue(booleanArrayValues);
					break;
				case ARRAY_STRING:
					List<DataValue> stringArrayValues = new ArrayList<DataValue>();
					for (JsonNode attributeDataElm : attributeData) {
						if (attributeDataElm.asText().equalsIgnoreCase("null")) {
							stringArrayValues.add(new NullValue());
						} else {
							stringArrayValues.add(new StringValue(
									attributeDataElm.asText()));
						}
					}
					attributeValue = new ArrayValue(stringArrayValues);
					break;
				case ARRAY_TEXT:
					List<DataValue> textArrayValues = new ArrayList<DataValue>();
					for (JsonNode attributeDataElm : attributeData) {
						if (attributeDataElm.asText().equalsIgnoreCase("null")) {
							textArrayValues.add(new NullValue());
						} else {
							textArrayValues.add(new StringValue(
									attributeDataElm.asText()));
						}
					}
					attributeValue = new ArrayValue(textArrayValues);
					break;
				default:
					if (log.isDebugEnabled())
						log.debug("\n\tUnknown data type: " + attributeType
								+ " data: " + attributeData + " of entity:"
								+ entityName + " in data file");
					throw new JsonException("Unknown data type: "
							+ attributeType + " data: " + attributeData
							+ " of entity:" + entityName + " in data file");
				}
			} else if (attributeType.isArray() && !attributeData.isArray()) {
				// Array Type but Normal value
				List<DataValue> stringArrayValues = new ArrayList<DataValue>();
				if (attributeData.asText().equalsIgnoreCase("null")) {
					stringArrayValues.add(new NullValue());
				} else {
					stringArrayValues.add(attributeType
							.parseValue(attributeData.asText()));
				}
				attributeValue = new ArrayValue(stringArrayValues);
				break;
			} else {
				if (log.isDebugEnabled())
					log.debug("\n\tUnknown data type: " + attributeType
							+ " data: " + attributeData + " of entity:"
							+ entityName + " in data file");
				throw new JsonException("Unknown data type: " + attributeType
						+ " data: " + attributeData + " of entity:"
						+ entityName + " in data file");
			}
			rowValues.add(attributeValue);
		}
		return rowValues;

	}
}
