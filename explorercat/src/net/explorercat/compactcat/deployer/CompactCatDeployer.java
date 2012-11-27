package net.explorercat.compactcat.deployer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.explorercat.compactcat.definitions.DefinitionException;
import net.explorercat.compactcat.parser.json.CatalogParser;
import net.explorercat.compactcat.parser.json.CatalogParserProviderFactory;
import net.explorercat.compactcat.translators.CatalogTranslator;
import net.explorercat.compactcat.translators.CatalogTranslatorFactory;
import net.explorercat.compactcat.translators.TranslationParameterParser;
import net.explorercat.compactcat.translators.TranslationParameterParserFactory;
import net.explorercat.util.exceptions.JsonException;
import net.explorercat.util.exceptions.TranslationException;

import org.antlr.runtime.RecognitionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Deploys a catalog translating it from a CompactCat file (.cat) into the
 * chosen representation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 28 Feb 2011
 */

public class CompactCatDeployer {
	// Logging
	private static final Log log = LogFactory.getLog(CompactCatDeployer.class);

	// Number of data rows (entity instances)
	// that will be read and translated together.
	private int consecutiveRowsToRead = 50000;

	private String dictionaryFilename;
	private String dataFilename;
	private List<String> translationParametersAsStrings;

	/**
	 * Builds a deployer specifying the path to the dictionary and data files
	 * and a list containing the translation parameters.
	 * 
	 * @param dicFilename
	 *            Path to the catalog dictionary file (.dic).
	 * @param datFilename
	 *            Path to the catalog data file (.dat).
	 * @param translationParameters
	 *            List of strings that represents the parameters given to guide
	 *            the translation.
	 */

	public CompactCatDeployer(File dicFilename, File datFilename,
			List<String> translationParameters) {
		this.dictionaryFilename = dicFilename.getAbsolutePath();
		this.dataFilename = datFilename.getAbsolutePath();
		this.translationParametersAsStrings = translationParameters;
	}

	/**
	 * Sets the number of consecutive rows to read from the data file (entity
	 * instances that will be read together in a chunk). When the instances are
	 * lightweight this number can be very high (will increase performance. On
	 * the other hand, when the instances are really big (for instance, contains
	 * an embedded genome) this number has to be low to avoid the risk of
	 * running out of memory.
	 * 
	 * @param numberOfRows
	 *            How many rows will read from the data file into memory at the
	 *            same time.
	 */

	public void setConsecutiveRowsToRead(int numberOfRows) {
		this.consecutiveRowsToRead = numberOfRows;
	}

	/**
	 * Deploy the catalog translating it into the chosen representation. The
	 * files specified when building the deployer are parsed, the catalog
	 * translated and validated.
	 * 
	 * @throws DefinitionException
	 * @throws JsonException
	 */

	public void deployCatalog() throws IOException, RecognitionException,
			TranslationException, JsonException, DefinitionException {

		// Deployment using JSON parsing
		CatalogTranslator translator = CatalogTranslatorFactory.getInstance()
				.createCatalogTranslator();
		TranslationParameterParser parameterParser = TranslationParameterParserFactory
				.getInstance().createTranslationParameterParser();
		CatalogParser catalogParser = CatalogParserProviderFactory
				.getInstance().createCatalogParserProvider();
		catalogParser.setDictionaryFile(new File(dictionaryFilename));
		catalogParser.setDataFile(new File(dataFilename));
		catalogParser.setCatalogTranslator(translator);
		catalogParser.setTranslationParameterParser(parameterParser);
		catalogParser
				.setTranslationParametersAsStrings(translationParametersAsStrings);
		catalogParser.setConsecutiveRowsToRead(consecutiveRowsToRead);
		catalogParser.parseCatalog();
		translator.translateConstraints(catalogParser.getCatalogDefinition());

		if (log.isDebugEnabled())
			log.debug("Catalog deployment finished");
	}
}
