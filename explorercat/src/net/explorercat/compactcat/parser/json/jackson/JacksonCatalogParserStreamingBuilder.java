package net.explorercat.compactcat.parser.json.jackson;

import net.explorercat.compactcat.parser.json.CatalogParser;
import net.explorercat.compactcat.parser.json.CatalogParserProviderBuilder;

/**
 * A builder in charge of creating concrete implementation of CatalogParser
 * using Jackson Json library. This implementation uses token stream while
 * parsing data file utilising less memory. Recommend to use when deploying
 * large data sets.
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public class JacksonCatalogParserStreamingBuilder implements
		CatalogParserProviderBuilder {

	/**
	 * Gets the JacksonCatalogStreamingParser instance
	 * 
	 * @return A catalogParser instance that uses Jackson library and token
	 *         stream while parsing data file
	 */

	@Override
	public CatalogParser buildCatalogParserProvider() {
		return new JacksonCatalogStreamingParser();
	}

	/**
	 * Gets the class name of the CatalogParser provider that is built by this
	 * builder.
	 */

	@Override
	public String getCatalogParserProviderClassName() {
		return JacksonCatalogStreamingParser.class.getName();
	}

}
