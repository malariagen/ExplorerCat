package net.explorercat.plugins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.explorercat.application.ApplicationController;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.util.misc.XMLTagValuesExtractor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Plug-in builder based on plug-in manifest XML files. This class reads the XML
 * file that describes a plug-in and builds the equivalent plug-in object.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 25 Nov 2010
 */

public class PluginBuilder {
	// Logging
	private static final Log log = LogFactory.getLog(PluginBuilder.class);

	/**
	 * Builds a plug-in object based on the XML manifest file definition.
	 * 
	 * @param manifestPath
	 *            Absolute path to the XML manifest file.
	 * @param pluginFolderPath
	 *            Path to the plug-in folder (relative to the WebContent
	 *            folder).
	 */
	public Plugin buildPlugin(String manifestPath, String pluginFolderPath)
			throws PluginLoadingException {

		Document manifestDOM = parseFileAndGetDOM(manifestPath);
		Element pluginRoot = manifestDOM.getDocumentElement();
		Plugin plugin = new Plugin();

		plugin.setFolderPath(pluginFolderPath);

		// Process the different parts of the manifest.
		processPluginMetadata(pluginRoot, plugin);
		processPluginRequiredFiles(pluginRoot, plugin);
		processPluginAssociatedCatalogs(pluginRoot, plugin);
		processPluginResources(pluginRoot, plugin);
		processPluginImports(pluginRoot, plugin);
		processPluginPrecalculatedQueries(pluginRoot, plugin);
		processPluginJavascriptConfiguration(pluginRoot, plugin);

		return plugin;
	}

	/**
	 * Auxiliary method that parses a file and returns its DOM representation.
	 * 
	 * @param filePath
	 *            The path to the XML file to be parsed.
	 * @return The DOM representation of the given XML file.
	 */

	private Document parseFileAndGetDOM(String filePath)
			throws PluginLoadingException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
				.newInstance();

		// Get the DOM representation of the XML file.
		try {
			DocumentBuilder documentBuilder = documentFactory
					.newDocumentBuilder();
			return documentBuilder.parse(filePath);
		} catch (ParserConfigurationException e) {
			throw new PluginLoadingException(
					"Error parsing the manifest file (" + filePath + ")",
					"unknown", "Bad MANIFEST", e);
		} catch (SAXException e) {
			throw new PluginLoadingException(
					"Error parsing the manifest file (" + filePath + ")",
					"unknown", "Bad MANIFEST", e);
		} catch (IOException e) {
			throw new PluginLoadingException(
					"Error reading the manifest file (" + filePath + ")",
					"unknown", "Bad MANIFEST", e);
		}
	}

	/**
	 * Processes the meta-data section of the manifest configuring the plug-in
	 * passed as a parameter with the data found. This section is mandatory.
	 * 
	 * @throws PluginLoadingException
	 */

	private void processPluginMetadata(Element root, Plugin plugin)
			throws PluginLoadingException {
		NodeList nodes = root.getElementsByTagName("meta-data");
		checkTagIsDefinedAndUnique("meta-data", "plugin", nodes);

		// Get the <meta-data> element.
		Element metaDataRoot = (Element) nodes.item(0);

		// Get the text of the <name>
		List<String> pluginName = XMLTagValuesExtractor
				.getTagValuesAsNormalizedStrings(metaDataRoot, "name");
		checkPropertyIsDefinedAndUnique("name", "meta-data", pluginName);

		// Get the text of the <title>
		List<String> pluginTitle = XMLTagValuesExtractor
				.getTagValuesAsNormalizedStrings(metaDataRoot, "title");
		checkPropertyIsDefinedAndUnique("title", "meta-data", pluginName);
		
		
		// Get the text of the <description>
		List<String> pluginDescription = XMLTagValuesExtractor
				.getTagValuesAsNormalizedStrings(metaDataRoot, "description");
		checkPropertyIsDefinedAndUnique("description", "meta-data",
				pluginDescription);

		// Building process
		plugin.setName(pluginName.get(0));
		plugin.setTitle(pluginTitle.get(0));
		plugin.setDescription(pluginDescription.get(0));
	}

	/**
	 * Processes the required-files section of the manifest configuring the
	 * plug-in passed as a parameter with the data found. This section is
	 * mandatory.
	 */

	private void processPluginRequiredFiles(Element root, Plugin plugin)
			throws PluginLoadingException {
		NodeList nodes = root.getElementsByTagName("required-files");
		checkTagIsDefinedAndUnique("required-files", "plugin", nodes);

		// Get the <required-files> element.
		Element requiredFilesRoot = (Element) nodes.item(0);

		// Get the text of the <jsp> tag
		List<String> pluginViewFile = XMLTagValuesExtractor
				.getTagValuesAsNormalizedStrings(requiredFilesRoot, "jsp");

		// Get the text of the <icon> tag
		List<String> pluginIcon = XMLTagValuesExtractor
				.getTagValuesAsNormalizedStrings(requiredFilesRoot, "icon");

		// If no view file has been specified the plugin is considered abstract.
		if (pluginViewFile.size() > 0)
			plugin.setJSPPath(plugin.getFolder() + File.separator
					+ pluginViewFile.get(0));

		// If the icon path is not specified we use the default one.
		if (pluginIcon.size() > 0)
			plugin.setIconPath(plugin.getFolder() + File.separator
					+ pluginIcon.get(0));
		else
			plugin.setIconPath(PropertyLookup.getDefaultIconPath());
	}

	/**
	 * Processes the resources section of the manifest configuring the plug-in
	 * passed as a parameter with the data found. This section is optional.
	 */

	private void processPluginResources(Element root, Plugin plugin)
			throws PluginLoadingException {
		String[] tags = { "name", "path" };
		NodeList rootNodes = root.getElementsByTagName("resources");

		if (rootNodes != null) {
			Element containerRoot = (Element) rootNodes.item(0);
			Map<String, List<String>> tagValues = null;
			tagValues = XMLTagValuesExtractor.getValuesForTags(containerRoot,
					"resource", tags);

			// Building process
			int numValues = tagValues.get(tags[0]).size();

			for (int i = 0; i < numValues; ++i) {
				String name = tagValues.get(tags[0]).get(i);
				String path = tagValues.get(tags[1]).get(i);
				plugin.registerResourcePath(name, plugin.getFolder()
						+ File.separator + path);
			}
		}
	}

	/**
	 * Processes the imports section of the manifest configuring the plug-in
	 * passed as a parameter with the data found. This section is optional.
	 */

	private void processPluginImports(Element root, Plugin plugin)
			throws PluginLoadingException {
		String[] tags = { "type", "path" };
		NodeList rootNodes = root.getElementsByTagName("imports");

		if (rootNodes != null && rootNodes.getLength() > 0) {
			Element containerRoot = (Element) rootNodes.item(0);
			Map<String, List<String>> tagValues = null;
			tagValues = XMLTagValuesExtractor.getValuesForTags(containerRoot,
					"import", tags);

			// Building process
			int numValues = tagValues.get(tags[0]).size();

			for (int i = 0; i < numValues; ++i) {
				String type = tagValues.get(tags[0]).get(i);
				String path = tagValues.get(tags[1]).get(i);

				if (type.toLowerCase().equals("css"))
					plugin.registerCSSFile(plugin.getFolder() + File.separator
							+ path);

				else if (type.toLowerCase().equals("js")
						|| type.toLowerCase().equals("javascript"))
					plugin.registerJSFile(plugin.getFolder() + File.separator
							+ path);

				else
					throw new PluginLoadingException("Unknown type of import "
							+ type, plugin.getName(), "Bad IMPORT");
			}
		}
	}

	/**
	 * Processes the precalculated-queries section of the manifest configuring
	 * the plug-in passed as a parameter with the data found. This section is
	 * optional.
	 */

	private void processPluginPrecalculatedQueries(Element root, Plugin plugin)
			throws PluginLoadingException {
		String[] tags = { "catalogs", "code" };
		NodeList rootNodes = root.getElementsByTagName("precalculated-queries");

		if (rootNodes != null && rootNodes.getLength() > 0) {
			Element containerRoot = (Element) rootNodes.item(0);
			Map<String, List<String>> tagValues = null;
			tagValues = XMLTagValuesExtractor.getValuesForTags(containerRoot,
					"cql-precalculated-query", tags);

			// Building process
			int numValues = tagValues.get(tags[0]).size();

			for (int i = 0; i < numValues; ++i) {
				String catalogs = tagValues.get(tags[0]).get(i);
				String code = tagValues.get(tags[1]).get(i);

				StringTokenizer tokenizer = new StringTokenizer(catalogs, ",");
				int numTokens = tokenizer.countTokens();

				while (tokenizer.hasMoreTokens()) {
					String catalogName = tokenizer.nextToken().trim();

					if (catalogName.toLowerCase().equals("all")) {
						if (numTokens > 1)
							throw new PluginLoadingException(
									"Precalculated Queries, Using ALL with specific "
											+ "catalog identifiers: "
											+ catalogs, plugin.getName(),
									"Bad use of ALL (query)");

						plugin.registerGlobalPrecalculatedQuery(code);
					} else
						plugin.registerCatalogPrecalculatedQuery(catalogName,
								code);

				}
			}
		}
	}

	/**
	 * Processes the javascript-configuration section of the manifest
	 * configuring the plug-in passed as a parameter with the data found. This
	 * section is optional.
	 */

	private void processPluginJavascriptConfiguration(Element root,
			Plugin plugin) throws PluginLoadingException {
		String[] tags = { "catalogs", "code" };
		NodeList rootNodes = root
				.getElementsByTagName("javascript-configuration");

		if (rootNodes != null && rootNodes.getLength() > 0) {
			Element containerRoot = (Element) rootNodes.item(0);
			Map<String, List<String>> tagValues = null;
			tagValues = XMLTagValuesExtractor.getValuesForTags(containerRoot,
					"json-configuration-object", tags);

			// Special case for this section: The init-function tag if defined
			// must be defined only once.
			// Note that if no init-function is specified no function will be
			// called to initialise the plug-in.
			ArrayList<String> initFunction = new ArrayList<String>();
			NodeList nodes = root
					.getElementsByTagName("javascript-configuration");

			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); ++i)
					initFunction.addAll(XMLTagValuesExtractor
							.getTagValuesAsNormalizedStrings(
									(Element) nodes.item(i), "init-function"));

				checkPropertyIsDefinedAndUnique("init-function",
						"javascript-configuration", initFunction);
				plugin.setInitFunctionName(initFunction.get(0));
			}

			// Building process
			int numValues = tagValues.get(tags[0]).size();

			for (int i = 0; i < numValues; ++i) {
				String catalogs = tagValues.get(tags[0]).get(i);
				String code = tagValues.get(tags[1]).get(i);

				StringTokenizer tokenizer = new StringTokenizer(catalogs, ",");
				int numTokens = tokenizer.countTokens();

				while (tokenizer.hasMoreTokens()) {
					String catalogName = tokenizer.nextToken().trim();

					if (catalogName.toLowerCase().equals("all")) {
						if (numTokens > 1)
							throw new PluginLoadingException(
									"JavaScript configuration object, Using ALL with "
											+ "specific catalog identifiers: "
											+ catalogs, plugin.getName(),
									"Bad use of ALL (JS)");

						plugin.setGlobalConfigurationObject(code);
					} else
						plugin.registerCatalogConfigurationObject(catalogName,
								code);
				}
			}
		}
	}

	/**
	 * Auxiliary method that checks that the value for a tag is defined (has
	 * been specified) and unique (don't have been defined multiple times).
	 * 
	 * @throws PluginLoadingException
	 *             If these conditions don't meet.
	 */

	private void checkPropertyIsDefinedAndUnique(String tagName,
			String context, List<String> propertyValues)
			throws PluginLoadingException {
		if (propertyValues.size() == 0)
			throw new PluginLoadingException("No " + tagName
					+ " specified for plug-in in " + context, "unknown",
					"Bad XML");

		if (propertyValues.size() > 1)
			throw new PluginLoadingException("Multiple " + tagName
					+ " values specified for plug-in in " + context, "unknown",
					"Bad XML");
	}

	/**
	 * Auxiliary method that checks that a tag is defined (has been specified)
	 * and unique (don't have been defined multiple times).
	 * 
	 * @throws PluginLoadingException
	 *             If these conditions don't meet.
	 */

	private void checkTagIsDefinedAndUnique(String tagName, String context,
			NodeList tags) throws PluginLoadingException {
		if (tags == null || tags.getLength() == 0)
			throw new PluginLoadingException("No " + tagName
					+ " specified for plug-in in " + context, "unknown",
					"Bad XML");
		if (tags.getLength() > 1)
			throw new PluginLoadingException("Multiple " + tagName
					+ " values specified for plug-in in " + context, "unknown",
					"Bad XML");
	}

	/**
	 * Processes the required "associated-catalogs" tag of the manifest file to
	 * associate a plugin with one or more or all of the catalogs. This section
	 * is mandatory.
	 * 
	 */

	private void processPluginAssociatedCatalogs(Element root, Plugin plugin)
			throws PluginLoadingException {
		// Gets all available catalog ids
		List<Integer> catalogIds = getAllCatalogIds();

		NodeList nodes = root.getElementsByTagName("associated-catalogs");
		checkTagIsDefinedAndUnique("associated-catalogs", "plugin", nodes);

		// Get the <associated-catalogs> element.
		Element requiredFilesRoot = (Element) nodes.item(0);

		// Get the text of the <catalog-id> tag
		List<String> catalogIdTags = XMLTagValuesExtractor
				.getTagValuesAsNormalizedStrings(requiredFilesRoot,
						"catalog-id");

		if (catalogIdTags.contains("all") || catalogIdTags.contains("ALL")) {
			for (Integer catalogId : catalogIds) {
				plugin.registerAssociatedCatalog(catalogId);
			}
		} else {
			for (String catalogIdTag : catalogIdTags) {
				for (Integer catalogId : catalogIds) {
					if (catalogId.toString().equalsIgnoreCase(catalogIdTag)) {
						plugin.registerAssociatedCatalog(catalogId);
					}
				}
			}
		}
	}

	/**
	 * Auxiliary method that gets id's of all catalogs that are available to the
	 * system.
	 * 
	 * @throws DAOException
	 *             If unable to get catalog information.
	 */
	private List<Integer> getAllCatalogIds() {
		List<Integer> catalogIds = new ArrayList<Integer>();
		try {
			List<Catalog> catlogs = ApplicationController.getInstance()
					.getDAOFactory().getCatalogDAO().findAllCatalogs();
			for (Catalog catlog : catlogs) {
				catalogIds.add(catlog.getId());
			}
		} catch (DAOException e) {
			if (log.isDebugEnabled())
				log.debug("Unable to get all available catalogs using CatalogDAO while loading the plugin"
						+ e);
		}
		return catalogIds;
	}
}
