package net.explorercat.compactcat.parser.json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.explorercat.compactcat.definitions.CatalogDefinition;
import net.explorercat.compactcat.definitions.DefinitionException;
import net.explorercat.compactcat.translators.CatalogTranslator;
import net.explorercat.compactcat.translators.TranslationParameterParser;
import net.explorercat.util.exceptions.JsonException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

/**
 * Interface representing catalogParser methods. Different implementations of
 * the CatalogParser providers (e.g. Json: Jackson or Gson) offer different
 * functionality like performance measurement (Memory or Streaming).
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public interface CatalogParser extends DataParser, DictrionaryParser{

	/**
	 * Sets Catalog dictionary file
	 * 
	 * @param dictionaryFile
	 *            Catalog dictionary file.
	 */

	public void setDictionaryFile(File dictionaryFile);

	/**
	 * Sets Catalog data file
	 * 
	 * @param dataFile
	 *            catalog data file.
	 */

	public void setDataFile(File dataFile);

	/**
	 * Sets CatalogTranslator Instance
	 * 
	 * @param translator
	 *            CatalogTranslator Instance.
	 */

	public void setCatalogTranslator(CatalogTranslator translator);

	/**
	 * Sets TranslationParameterParser Instance
	 * 
	 * @param parameterParser
	 *            TranslationParameterParser Instance.
	 */

	public void setTranslationParameterParser(
			TranslationParameterParser parameterParser);

	/**
	 * Sets TranslationParameters of type List<String>
	 * 
	 * @param translationParametersAsStrings
	 *            A list that contains the translation parameters (String).
	 */

	public void setTranslationParametersAsStrings(
			List<String> translationParametersAsStrings);

	/**
	 * Sets number of rows to read while translating Catalog data file
	 * 
	 * @param consecutiveRowsToRead
	 *            Number of rows to read while translating Catalog data file.
	 */

	public void setConsecutiveRowsToRead(int consecutiveRowsToRead);

	/**
	 * Method to parse Catalog dictionary & data file, translating them into
	 * Chosen representation ( e.g.MySql)
	 * 
	 */

	public void parseCatalog() throws JsonException, JsonParseException,
			JsonMappingException, DefinitionException, IOException;

	/**
	 * Returns CatalogDefinition instance that is modelling current Catalog
	 * dictionary and data
	 * 
	 * @return CatalogDefinition
	 */

	public CatalogDefinition getCatalogDefinition();

}