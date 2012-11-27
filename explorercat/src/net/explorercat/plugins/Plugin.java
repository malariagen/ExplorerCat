package net.explorercat.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.selection.query.precalculated.PrecalculatedQueryLoader;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.DAOFactory;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a plug-in of the presentation layer, it connects with
 * the ExplorerCat server via API. Deploying a plug-in is as simple as copying
 * the folder into the plugins folder. The plug-in internal structure and
 * configuration is described in the plug-in manifest file.
 * 
 * The folder containing the plug-in must have a unique name (no spaces). This
 * is typically the name of the plug-in. The internal structure (folder tree) of
 * a plug-in is up to the developer.
 * 
 * The only required files whose location have to be specified in the manifest
 * are the plug-in JSP file (JSP or HTML file that renders the view) and the
 * plug-in icon (a small picture that will identify the plug-in). However if no
 * icon is specified a default icon will be used.
 * 
 * If a plug-in does not specify a JSP file it is considered abstract. An
 * abstract plug-in won't be loaded by the system as an individual tool but can
 * be imported and used by other plug-ins.
 * 
 * Sections of a plug-in manifest file:
 * 
 * - metadata : Name and brief description of the plug-in (REQUIRED).
 * 
 * - required-files : jsp (JSP/HTML file) and small icon (image) (REQUIRED, if
 * not specified the plug-in is considered ABSTRACT).
 * 
 * - resources : Associates resource names with paths. They will be available to
 * the view (if JSP file) by means of the <s:property> tag. They can reference
 * folders or files. (OPTIONAL).
 * 
 * - imports : List of files or plug-ins to be imported (CSS, JS or PLUGIN)
 * (OPTIONAL).
 * 
 * - precalculated-queries : List of CQL queries to be precalculated (OPTIONAL).
 * 
 * - javascript-configuration : List of JSON objects to be passed to the
 * function that will be called to initialise the plug-in. When the plug-in view
 * is rendered, this function is automatically called (if defined), passing the
 * action parameters and the plug-in configuration values.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Oct 2010
 */

public class Plugin implements Comparable<Plugin> {
	// Logging
	private static final Log log = LogFactory.getLog(Plugin.class);

	// General properties of the plug-in.
	private String name;
	private String folderPath;
	private String description;
	private String title;
	
	
	// Path to the required files (view and icon).
	private String jspPath;
	private String iconPath;

	// Path to resources that can be accessed by the JSP view using the ${}
	// operator.
	// Resource name -> path
	private Map<String, String> resources;

	// JavaScript function that will be called when rendering the view to
	// configure the plug-in.
	// Two JSON objects are passed to this function, one containing
	// configuration values for all the
	// catalogs and other one with specific configuration values that are
	// related to the current catalog.
	private String pluginInitFunction;

	// Global CQL precalculated queries (all the catalogs).
	private List<String> globalPrecalculatedQueries;

	// Catalog-specific CQL precalculated queries.
	private Map<String, List<String>> catalogPrecalculatedQueries;

	// CSS files to be imported.
	private List<String> cssFilesToBeImported;

	// Catalogs for which this plugin available to
	private List<Integer> asscoatedCatalogs;

	// JS files to be imported.
	private List<String> jsFilestToBeImported;

	// Global options (JSON object) for the plug-in (all the catalogs).
	private String globalOptionsObject;

	// Catalog-specific options (JSON object) for the plug-in.
	private Map<String, String> catalogOptionsObjects;

	// We import plug-ins in three steeps: Firstly, we register all the plugin
	// dependencies. Secondly, we load the plugin registry. Thirdly, we check
	// there
	// are not cyclic dependencies and we resolve them.
	private Set<String> pluginDependencies;
	private List<Plugin> pluginsToBeImported;

	/**
	 * Default constructor that creates an empty plugin since it will be created
	 * step by step by a builder.
	 */

	public Plugin() {
		this.name = null;
		this.folderPath = null;
		this.description = null;
		this.title = null;

		this.jspPath = null;
		this.iconPath = null;
		this.resources = new HashMap<String, String>();

		this.pluginInitFunction = null;

		this.globalPrecalculatedQueries = new ArrayList<String>();
		this.catalogPrecalculatedQueries = new HashMap<String, List<String>>();

		this.globalOptionsObject = "{}";
		this.catalogOptionsObjects = new HashMap<String, String>();

		this.cssFilesToBeImported = new ArrayList<String>();
		this.jsFilestToBeImported = new ArrayList<String>();
		this.asscoatedCatalogs = new ArrayList<Integer>();

		this.pluginDependencies = new HashSet<String>();
		this.pluginsToBeImported = new ArrayList<Plugin>();
	}

	/**
	 * Checks if the plug-in won't be used as a stand-alone tool (it will be
	 * imported by a different plug-in). A plug-in is considered abstract if no
	 * JSP or HTML file (view) is specified in its manifest.
	 * 
	 * @return True if the plug-in is abstract (its only use is to be imported
	 *         by another plug-in), false otherwise.
	 */

	public boolean isAbstract() {
		return this.jspPath == null;
	}

	// Setters and getters for metadata.

	public void setName(String name) {
		this.name = name;
	}

	public void setFolderPath(String folder) {
		this.folderPath = folder;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	public String getName() {
		return name;
	}

	public String getFolder() {
		return folderPath;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}
	// Resources that can be accessed by the view.

	/**
	 * Registers a resource associated with a path. The path can be recovered
	 * using the resource name.
	 * 
	 * @param resourceName
	 *            Identifier of the resource.
	 * @param path
	 *            The path of the resource (relative to the WebContent folder).
	 */

	public void registerResourcePath(String resourceName, String path) {
		resources.put(resourceName, path);
	}

	/**
	 * Gets the path associated with a resource name. Resources for the current
	 * plug-in are checked first, if the resource is not found then we check the
	 * resources of the imported plug-ins.
	 * 
	 * @return The path associated with that resource name (it can be a folder
	 *         or a file). The path is relative to the WebContent folder.
	 */

	public String getResourcePath(String resourceName) {
		String resourcePath = resources.get(resourceName);

		if (resourcePath == null) {
			for (Plugin plugin : this.pluginsToBeImported) {
				resourcePath = plugin.getResourcePath(resourceName);
				if (resourcePath != null)
					return resourcePath;
			}
		}

		return resourcePath;
	}

	// Getters and setters for required files.

	/**
	 * Gets the path to the view (JSP or HTML file).
	 * 
	 * @return The path of the plug-in view (relative to the WebContent folder).
	 */

	public String getJSPPath() {
		return jspPath;
	}

	/**
	 * Sets the path to the view (JSP or HTML file).
	 * 
	 * @param jspPath
	 *            The path of the view file (relative to the WebContent folder).
	 */

	public void setJSPPath(String jspPath) {
		this.jspPath = jspPath;
	}

	/**
	 * Gets the path to the plug-in picture/icon.
	 * 
	 * @return The path to the icon file (relative to the WebContent folder).
	 */

	public String getIconPath() {
		return iconPath;
	}

	/**
	 * Sets the path to the plug-in picture/icon.
	 * 
	 * @param iconPath
	 *            The path to the icon file (relative to the WebContent folder).
	 */

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	/**
	 * Registers a CSS file to be imported by the plug-in.
	 * 
	 * @param filePath
	 *            Path of the file, relative to the WebContent folder.
	 */

	public void registerCSSFile(String filePath) {
		cssFilesToBeImported.add(filePath);
	}

	/**
	 * Gets the path to the CSS files that will be imported for this plug-in
	 * (CSS files from imported plugins will be added).
	 * 
	 * @return A list containing the paths of all the CSS files that will be
	 *         imported.
	 */

	public List<String> getCSSFilesToBeImported(String contextPath) {
		ArrayList<String> cssFiles = new ArrayList<String>();

		for (Plugin plugin : this.pluginsToBeImported)
			cssFiles.addAll(plugin.getCSSFilesToBeImported(contextPath));

		for (String currentFile : cssFilesToBeImported)
			cssFiles.add(contextPath + currentFile);

		return cssFiles;
	}

	/**
	 * Registers a JS file to be imported by the plug-in.
	 * 
	 * @param filePath
	 *            Path of the file, relative to the WebContent folder.
	 */

	public void registerJSFile(String filePath) {
		jsFilestToBeImported.add(filePath);
	}

	/**
	 * Gets the path to the JS files that will be imported for this plug-in (JS
	 * files from imported plugins will be added).
	 * 
	 * @return A list containing the paths of all the JS files that will be
	 *         imported.
	 */

	public List<String> getJSFilesToBeImported(String contextPath) {
		ArrayList<String> jsFiles = new ArrayList<String>();

		for (Plugin plugin : this.pluginsToBeImported)
			jsFiles.addAll(plugin.getJSFilesToBeImported(contextPath));

		for (String currentFile : jsFilestToBeImported)
			jsFiles.add(contextPath + currentFile);

		return jsFiles;
	}

	/**
	 * Sets the function (name) to be called in order to configure the plug-in.
	 * 
	 * @param initFunction
	 *            The name of the function that will be invoked.
	 */

	public void setInitFunctionName(String initFunction) {
		this.pluginInitFunction = initFunction;
	}

	/**
	 * Returns the name of the function to be invoked right after loading the
	 * plug-in view.
	 * 
	 * @return The name of the function that will be invoked in order to
	 *         initialize the plug-in.
	 */

	public String getInitFunctionName() {
		return pluginInitFunction;
	}

	/**
	 * Register a CQL query that will be precalculated (to be used by the
	 * plug-in).
	 * 
	 * @param query
	 *            A string representing the query to be precalculated.
	 */

	public void registerGlobalPrecalculatedQuery(String query) {
		this.globalPrecalculatedQueries.add(query);
	}

	/**
	 * Registers a CQL query that will be precalculated to be used by the
	 * plug-in but only with the given catalog.
	 * 
	 * @param catalogId
	 *            The id of the catalog for which the query will be calculated.
	 * @param query
	 *            A string representing the query to be pre-calculated.
	 */

	public void registerCatalogPrecalculatedQuery(String catalogName,
			String query) {
		List<String> queries = this.catalogPrecalculatedQueries
				.get(catalogName);

		if (queries == null)
			queries = new ArrayList<String>();

		queries.add(query);
		this.catalogPrecalculatedQueries.put(catalogName, queries);
	}

	/**
	 * Gets the global configuration options of the plug-in (to be used with all
	 * the catalogs for which the plug-in has been enabled).
	 * 
	 * @return A JSON object (as a string) containing the universal plug-in
	 *         configuration options (usually an anonymous JSON object).
	 */

	public String getGlobalConfigurationObject() {
		// If there are no options registered we return the empty object.
		return this.globalOptionsObject != null ? this.globalOptionsObject
				: "{}";
	}

	/**
	 * Sets the configuration options object (JSON) that will be passed to the
	 * init function of the plug-in for all the catalogs.
	 * 
	 * @param jsonOptions
	 *            A string that represents an anonymous JSON object containing
	 *            the global options to configure the plug-in.
	 */

	public void setGlobalConfigurationObject(String jsonOptions) {
		this.globalOptionsObject = jsonOptions;
	}

	/**
	 * Gets the plugin configuration options to be used with the given catalog.
	 * 
	 * @param catalogName
	 *            The name of the catalog.
	 * @return A string containing the plug-in options for the catalog given as
	 *         a parameter.
	 */

	public String getConfigurationObjectForCatalog(String catalogName) {
		String options = this.catalogOptionsObjects.get(catalogName);

		// If there are no options registered we return the empty object.
		return options != null ? options : "{}";
	}

	/**
	 * Registers a JSON object (as a String) that will be passed to the init
	 * function (JavaScript) of the plug-in when working with the given catalog.
	 * 
	 * @param catalogName
	 *            The name of the catalog for which the JSON configuration
	 *            object will be passed (the catalog name has to contain this
	 *            string).
	 * @param jsonOptions
	 *            jsonOptions A string that represents an anonymous JSON object
	 *            containing the options to configure the plug-in.
	 */

	public void registerCatalogConfigurationObject(String catalogName,
			String jsonOptions) {
		this.catalogOptionsObjects.put(catalogName, jsonOptions);
	}

	/**
	 * Loads the precalculated queries specified for this plug-in (for all the
	 * catalogs for which the plug-in will be enabled).
	 */

	public void loadPrecalculatedQueries() throws PluginLoadingException {
		try {
			DAOFactory factory = ApplicationController.getInstance()
					.getDAOFactory();
			List<Catalog> catalogs = factory.getCatalogDAO().findAllCatalogs();

			// The object in charge of loading the precalcualted queries.
			PrecalculatedQueryLoader loader = ApplicationController
					.getInstance().getPrecalculatedQueryLoader();

			// First, We have to load the queries that have been specified for
			// ALL the catalogs in the system.
			if (this.globalPrecalculatedQueries.size() > 0) {
				for (Catalog catalog : catalogs) {
					if (log.isDebugEnabled())
						log.debug("Loading global precalculated queries for plugin: "
								+ name + ", catalog: " + catalog.getId());

					loader.loadPrecalculatedQueriesForCatalog(
							getGlobalPrecalculatedQueries(), catalog);
				}
			}

			// Load the catalog-specific queries
			for (Catalog catalog : catalogs) {
				String catalogName = catalog.getName();

				if (this.catalogPrecalculatedQueries.get(catalogName) != null) {
					if (log.isDebugEnabled())
						log.debug("Loading catalog precalculated queries for plugin: "
								+ name + ", catalog: " + catalog.getId());

					loader.loadPrecalculatedQueriesForCatalog(
							getPrecalculatedQueriesForCatalog(catalogName),
							catalog);
				}
			}
		} catch (ExplorerCatCheckedException e) {
			throw new PluginLoadingException(
					"Error loading queries for plugin (" + name + ")", name,
					"Bad QUERY", e);
		}
	}

	/**
	 * Auxiliary method that gets the global precalculated queries concatenated
	 * as a string.
	 */

	private String getGlobalPrecalculatedQueries() {
		if (this.globalPrecalculatedQueries.size() == 1)
			return this.globalPrecalculatedQueries.get(0);

		else if (this.globalPrecalculatedQueries.size() == 0)
			return "";

		else {
			StringBuilder queryText = new StringBuilder(2048);
			for (String currentQuery : this.globalPrecalculatedQueries)
				queryText.append(currentQuery);

			return queryText.toString();
		}
	}

	/**
	 * Auxiliary function that gets the catalog specific precalculated queries
	 * concatenated as a string
	 */

	private String getPrecalculatedQueriesForCatalog(String catalogName) {
		List<String> catalogQueries = this.catalogPrecalculatedQueries
				.get(catalogName);

		if (catalogQueries == null)
			return "";

		else if (catalogQueries.size() == 1)
			return this.globalPrecalculatedQueries.get(0);

		else if (catalogQueries.size() == 0)
			return "";

		else {
			StringBuilder queryText = new StringBuilder(2048);
			for (String currentQuery : catalogQueries)
				queryText.append(currentQuery);

			return queryText.toString();
		}
	}

	/**
	 * Registers a plugin dependency. Dependencies must be resolved after
	 * loading the plugin registry using the method
	 * {@link resolvePluginDependencies}.
	 * 
	 * @param pluginName
	 *            Name of the plug-in that will be imported.
	 */

	public void registerPluginDependency(String pluginName) {
		this.pluginDependencies.add(pluginName);
	}

	/**
	 * Resolves all the plug-in dependencies, checking there are not dependency
	 * cycles.
	 * 
	 * @param pluginRegistry
	 *            The registry where all the plug-ins have been loaded.
	 * @throws PluginLoadingException
	 *             If there is a cyclic dependency for this plug-in.
	 */

	public void resolvePluginDependencies(PluginRegistry pluginRegistry)
			throws PluginLoadingException {
		if (hasPluginDependencyCycle(pluginRegistry, new ArrayList<String>()))
			throw new PluginLoadingException(
					"Error loading plug-in. Cyclic dependency for " + this.name,
					this.name, "Dependency cycle");

		for (String pluginName : this.pluginDependencies) {
			Plugin importedPlugin = pluginRegistry.getPlugin(pluginName);
			if (importedPlugin != null)
				this.pluginsToBeImported.add(importedPlugin);
		}
	}

	/**
	 * Auxiliary method that checks if the plug-in has a dependency cycle. To be
	 * used by the {@link resolvePluginDependencies} method.
	 * 
	 * @param pluginRegistry
	 *            Registry that has been loaded with all the plug-ins.
	 * @param dependencyChain
	 *            List of plugins that would cause a dependency cycle if found.
	 * @return True if there is a dependency cycle, false otherwise.
	 */

	private boolean hasPluginDependencyCycle(PluginRegistry pluginRegistry,
			List<String> dependencyChain) {
		// We check for "auto-cyclic" dependencies.
		List<String> extendedChain = new ArrayList<String>(dependencyChain);
		extendedChain.add(this.name);

		for (String currentDependency : dependencyChain)
			if (this.pluginDependencies.contains(currentDependency))
				return true;

		for (String pluginName : this.pluginDependencies) {
			Plugin plugin = pluginRegistry.getPlugin(pluginName);

			if (plugin != null
					&& plugin.hasPluginDependencyCycle(pluginRegistry,
							extendedChain))
				return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Plugin[" + name + "](" + folderPath + ")";
	}

	@Override
	public int compareTo(Plugin anotherPlugin) {
		return this.name.compareTo(anotherPlugin.getName());
	}

	/**
	 * Registers a Catalog to be associated with this plug-in by storing its id.
	 * 
	 * @param catalogId
	 *            catalog id
	 */

	public void registerAssociatedCatalog(int catalogId) {
		asscoatedCatalogs.add(catalogId);
	}

	/**
	 * Method checks if the given Catalog is associated with this plugin.
	 * 
	 * @param catalogId
	 *            catalog id to check if this plugin available to.
	 * @return True if the catalog is associated.
	 */

	public boolean isAssociatedToCatalog(int catalogId) {
		return (asscoatedCatalogs.contains(catalogId));
	}
}
