package net.explorercat.compactcat.parser.json;

import net.explorercat.application.ApplicationPropertyLookup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A factory that serves catalog parser. The object served is an instance of
 * CatalogParser of configured provider builder type (e.g. Jackson or Gson)
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public class CatalogParserProviderFactory {

	private static Log log = LogFactory
			.getLog(CatalogParserProviderFactory.class);

	private CatalogParserProviderBuilder catalogParserProviderBuilder;

	private static final String BUILDER_CLASS;

	static {

		ApplicationPropertyLookup lookup = ApplicationPropertyLookup
				.getInstance();
		BUILDER_CLASS = lookup
				.getGlobalProperty("config.catalogParser.provider.class.builder");
	}

	// Singleton class
	private static CatalogParserProviderFactory instance = new CatalogParserProviderFactory();

	/**
	 * Gets an instance of the factory
	 */

	public static CatalogParserProviderFactory getInstance() {
		return instance;
	}

	/**
	 * Builds the concrete CatalogParser provider builder implementation
	 */

	protected CatalogParserProviderFactory() {
		if (log.isDebugEnabled())
			log.debug("Creating instance of CatalogParserProviderBuilder");

		try {
			log.debug("BUILDER CLASS:" + BUILDER_CLASS);
			Class<?> builderClass = Class.forName(BUILDER_CLASS);
			catalogParserProviderBuilder = (CatalogParserProviderBuilder) builderClass
					.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the CatalogParser that is in charge of parsing Catalog while
	 * deployment
	 * 
	 * @return A catalogParser instance that will be used by the application to
	 *         deploy Catalog
	 */
	public CatalogParser createCatalogParserProvider() {
		return catalogParserProviderBuilder.buildCatalogParserProvider();
	}

}
