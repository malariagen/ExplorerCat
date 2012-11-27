package net.explorercat.application;

import net.explorercat.application.configurators.APIRequestTrackerConfigurator;
import net.explorercat.application.configurators.GeneratedResourceRepositoryConfigurator;
import net.explorercat.application.configurators.QueryCacheProviderConfigurator;
import net.explorercat.application.configurators.ResultPaginationCacheConfigurator;
import net.explorercat.cql.result.ResultPaginationCache;
import net.explorercat.cql.result.generatedresources.GeneratedResourceRepository;
import net.explorercat.cql.result.resourcegenerators.ResourceGeneratorFactory;
import net.explorercat.cql.result.translators.ResultTextualTranslatorFactory;
import net.explorercat.cql.selection.query.cache.QueryCacheProvider;
import net.explorercat.cql.selection.query.precalculated.PrecalculatedQueryLoader;
import net.explorercat.cql.selection.scopes.SelectionScopeFactory;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.DAOFactory;
import net.explorercat.plugins.PluginHashException;
import net.explorercat.plugins.PluginHashRepository;
import net.explorercat.plugins.PluginRegistry;
import net.explorercat.staticresources.StaticResourceRepository;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;
import net.explorercat.util.exceptions.ExplorerCatRuntimeException;
import net.explorercat.util.misc.APIRequestTracker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Singleton in charge or controlling the application, mainly creating and
 * configuring the system objects (like caches, factories or repositories).
 * 
 * TODO Clarify the use of factories, how many of them should be singletons?
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Nov 2010
 */

public class ApplicationController {
	// Logging
	private static Log log = LogFactory.getLog(ApplicationController.class);

	// This class is a singleton.
	private static ApplicationController instance = new ApplicationController();

	// Global factory for query caches.
	private QueryCacheProvider globalQueryCacheProvider;

	// Global repository for resources like files or analysis results.
	private GeneratedResourceRepository globalGeneratedResourceRepository;

	// Global repository for static resources (pre-generated).
	private StaticResourceRepository globalStaticResourceRepository;

	// Global pagination cache for CQL results.
	private ResultPaginationCache globalPaginationCache;

	// Global request tracker for public API calls.
	private APIRequestTracker globalAPIRequestTracker;

	// Global loader for precalculated queries.
	private PrecalculatedQueryLoader globalPrecalculatedQueryLoader;

	// Global factory for textual translators.
	private ResultTextualTranslatorFactory globalResultTextualTranslatorFactory;

	// Global factory for resource generators.
	private ResourceGeneratorFactory globalResourceGeneratorFactory;

	// Global factory for selection scopes.
	private SelectionScopeFactory globalSelectionScopeFactory;

	// Global registry for plug-ins.
	private PluginRegistry globalPluginRegistry;

	// Global registry for plugin hashes (configurations associated to URLs).
	private PluginHashRepository globalPluginHashRepository;

	// Global factory for DAOs
	private DAOFactory globalDAOFactory;

	// Global factory for DAOs
	private MemoryProfiler memoryProfiler;

	/**
	 * Private constructor, this is a singleton.
	 */

	private ApplicationController() {
		if (log.isDebugEnabled())
			log.debug("Creating application controller.");

		globalQueryCacheProvider = QueryCacheProviderConfigurator
				.buildInstance();
		globalGeneratedResourceRepository = GeneratedResourceRepositoryConfigurator
				.buildInstance();
		globalStaticResourceRepository = new StaticResourceRepository();
		globalPaginationCache = ResultPaginationCacheConfigurator
				.buildInstance();
		globalPrecalculatedQueryLoader = new PrecalculatedQueryLoader();
		globalResultTextualTranslatorFactory = new ResultTextualTranslatorFactory();
		globalResourceGeneratorFactory = new ResourceGeneratorFactory();
		globalPluginRegistry = new PluginRegistry();
		globalAPIRequestTracker = APIRequestTrackerConfigurator.buildInstance();

		// Lazy initialization for these properties.
		globalDAOFactory = null;
		globalSelectionScopeFactory = null;
		globalPluginHashRepository = null;

		memoryProfiler = new MemoryProfiler();
	}

	/**
	 * Initialises all the resources that have not been initialized yet.
	 */

	public void init() {
		try {
			memoryProfiler.logMemoryUsage();
			// They are automatically initialised by the getters.
			getDAOFactory();
			getSelectionScopeFactory();
		} catch (ExplorerCatCheckedException e) {
			log.error("Error initializing global resources", e);
		}
	}

	/**
	 * Singleton getter.
	 * 
	 * @return The singleton instance of the class.
	 */

	public static ApplicationController getInstance() {
		return instance;
	}

	/**
	 * Gets the global repository for generated resources.
	 */

	public GeneratedResourceRepository getResourceRepository() {
		return this.globalGeneratedResourceRepository;
	}

	/**
	 * Gets the global repository for static resources.
	 */

	public StaticResourceRepository getStaticResourceRespository() {
		return this.globalStaticResourceRepository;
	}

	/**
	 * Gets the global cache for pagination of CQL results.
	 */

	public ResultPaginationCache getPaginationCache() {
		return this.globalPaginationCache;
	}

	/**
	 * Gets the global precalculated query loader.
	 */

	public PrecalculatedQueryLoader getPrecalculatedQueryLoader() {
		return this.globalPrecalculatedQueryLoader;
	}

	/**
	 * Gets the global factory for results textual translators.
	 */

	public ResultTextualTranslatorFactory getResultTextualTranslatorFactory() {
		return this.globalResultTextualTranslatorFactory;
	}

	/**
	 * Gets the global factory for resource generators.
	 */

	public ResourceGeneratorFactory getResourceGeneratorFactory() {
		return this.globalResourceGeneratorFactory;
	}

	/**
	 * Gets the global factory for query caches.
	 */

	public QueryCacheProvider getQueryCacheProvider() {
		return this.globalQueryCacheProvider;
	}

	/**
	 * Gets the global tracker for calls to the public API methos.
	 */

	public APIRequestTracker getAPIRequestTracker() {
		return this.globalAPIRequestTracker;
	}

	/**
	 * Gets the global factory for selection scopes.
	 */

	public SelectionScopeFactory getSelectionScopeFactory() {
		try {
			if (this.globalSelectionScopeFactory == null)
				this.globalSelectionScopeFactory = new SelectionScopeFactory(
						globalDAOFactory.getCatalogDAO());

			return this.globalSelectionScopeFactory;
		} catch (ExplorerCatCheckedException e) {
			throw new ExplorerCatRuntimeException(
					"Error trying to access scope factory via application controller",
					e);
		}
	}

	/**
	 * Gets the global registry for plug-ins.
	 */

	public PluginRegistry getPluginRegistry() {
		return this.globalPluginRegistry;
	}

	/**
	 * Gets the global repository of plugin hashes (hashes associated to plugin
	 * configurations that are used to generate unique URLs).
	 */

	public PluginHashRepository getPluginHashRepository() {
		try {
			if (this.globalPluginHashRepository == null)
				this.globalPluginHashRepository = new PluginHashRepository();

			return this.globalPluginHashRepository;
		} catch (ExplorerCatCheckedException e) {
			throw new ExplorerCatRuntimeException(
					"Error trying to access plugin hash repository", e);
		}
	}

	/**
	 * Gets the global factory for DAOs.
	 */

	public DAOFactory getDAOFactory() throws DAOException {
		try {
			if (globalDAOFactory == null)
				globalDAOFactory = new DAOFactory();

			return this.globalDAOFactory;
		}

		catch (ExplorerCatCheckedException e) {
			throw new ExplorerCatRuntimeException(
					"Error trying to access DAOs via application controller", e);
		}
	}

	public MemoryProfiler getMemoryProfiler() {
		return this.memoryProfiler;
	}
}
