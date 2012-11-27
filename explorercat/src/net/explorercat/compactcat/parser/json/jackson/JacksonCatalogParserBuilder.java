package net.explorercat.compactcat.parser.json.jackson;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.compactcat.parser.json.CatalogParser;
import net.explorercat.compactcat.parser.json.CatalogParserProviderBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A builder in charge of creating different implementations (Streaming or
 * Memory) of CatalogParser using Jackson Json library.
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date Jan 2012
 */

public class JacksonCatalogParserBuilder implements
		CatalogParserProviderBuilder {

	private static Log log = LogFactory
			.getLog(JacksonCatalogParserBuilder.class);

	private static final String IMPLEMENTATION_CLASS;

	static {

		ApplicationPropertyLookup lookup = ApplicationPropertyLookup
				.getInstance();
		IMPLEMENTATION_CLASS = lookup
				.getGlobalProperty("config.catalogParser.provider.type.class.builder");
	}

	private CatalogParserProviderBuilder catalogParserProviderBuilder;

	/**
	 * Builds the concrete Jackson based CatalogParser provider implementation
	 */

	public JacksonCatalogParserBuilder() throws ClassNotFoundException {
		int lastDotIndex = IMPLEMENTATION_CLASS.lastIndexOf(".");
		String unqualifiedClassName = IMPLEMENTATION_CLASS
				.substring(lastDotIndex == -1 ? 0 : lastDotIndex + 1);

		if (log.isDebugEnabled())
			log.debug("Creating JacksonCatalogParserBuilder for "
					+ unqualifiedClassName);

		if (JacksonCatalogParserMemoryBuilder.class.getName().contains(
				unqualifiedClassName))
			this.catalogParserProviderBuilder = new JacksonCatalogParserMemoryBuilder();

		else if (JacksonCatalogParserStreamingBuilder.class.getName().contains(
				unqualifiedClassName))
			this.catalogParserProviderBuilder = new JacksonCatalogParserStreamingBuilder();

		else {
			if (log.isErrorEnabled())
				log.error("Builder implementation for " + IMPLEMENTATION_CLASS
						+ " not found.");

			throw new ClassNotFoundException("JacksonCatalogParserBuilder: "
					+ IMPLEMENTATION_CLASS + " not found.");
		}
	}

	/**
	 * Gets the CatalogParser that is implemnetd using Jackson Json library
	 * 
	 * @return A catalogParser instance that will be used by the application to
	 *         deploy Catalog
	 */

	public CatalogParser buildCatalogParserProvider() {
		return catalogParserProviderBuilder.buildCatalogParserProvider();
	}

	/**
	 * Gets the class name of the CatalogParser provider that is built by this
	 * builder.
	 */

	public String getCatalogParserProviderClassName() {
		return catalogParserProviderBuilder.getCatalogParserProviderClassName();
	}
}