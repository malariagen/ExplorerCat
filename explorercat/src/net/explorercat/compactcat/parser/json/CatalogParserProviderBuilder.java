package net.explorercat.compactcat.parser.json;

/**
 * An interface to be implemented by concrete builders for CatalogParser
 * providers. There will be a builder per implementation (e.g. Json: Jackson or
 * Gson). The builder will decide which CatalogParser instance will return based
 * on the application configuration. 
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public interface CatalogParserProviderBuilder {

	/**
	 * Builds the concrete CatalogParser provider.
	 */

	public CatalogParser buildCatalogParserProvider();

	/**
	 * Gets the class name of the CatalogParser provider that is built by this
	 * builder.
	 */

	public String getCatalogParserProviderClassName();
}