package net.explorercat.compactcat.parser.json.jackson;

import net.explorercat.compactcat.parser.json.CatalogParser;
import net.explorercat.compactcat.parser.json.CatalogParserProviderBuilder;

/**
 * A builder in charge of creating concrete implementation of CatalogParser
 * using Jackson Json library. This implementation stores catalog entities in
 * memory while parsing data file, hence it is possible large memory is required
 * with large data files. Alternatively use JacksonCatalogParserStreamingBuilder
 * Implementation which uses token stream there by less memory requirement.
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public class JacksonCatalogParserMemoryBuilder implements
		CatalogParserProviderBuilder {

	/**
	 * Gets the JacksonCatalogMemoryParser instance
	 * 
	 * @return A catalogParser instance that uses Jackson library and memory to
	 *         store catalog entities
	 */

	@Override
	public CatalogParser buildCatalogParserProvider() {
		return new JacksonCatalogMemoryParser();
	}

	/**
	 * Gets the class name of the CatalogParser provider that is built by this
	 * builder.
	 */

	@Override
	public String getCatalogParserProviderClassName() {
		return JacksonCatalogMemoryParser.class.getName();
	}
}