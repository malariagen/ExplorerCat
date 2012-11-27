package net.explorercat.dataaccess;

import java.util.Iterator;
import java.util.List;

import net.explorercat.application.ApplicationController;
import net.explorercat.data.Catalog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Factory that supplies DAOs that can be used to access catalogs and data
 * entities.Provides a method to pre-create all the necessary DAO objects. If
 * not used the objects will be created by lazy initialization.
 * 
 * Notice this is NOT a straightforward implementation of the SimpleFactory
 * idiom.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class DAOFactory {
	// Logging
	private static Log log = LogFactory.getLog(DAOFactory.class);

	private CatalogDAO catalogDAO;
	private UserDAO userDAO;
	private NewsDAO newsDAO;
	private PluginHashDAO configurationHashDAO;

	//

	/**
	 * Constructor. It will initialize all the DAOs (including the entity DAOs
	 * encapsulated in the catalog objects returned by catalog DAOs).
	 * 
	 * @throws DAOException
	 *             If it's not possible to initializes the DAOs.
	 */

	public DAOFactory() throws DAOException {
		catalogDAO = null;
		userDAO = null;
		newsDAO = null;
		configurationHashDAO = null;

		initializeAllDAOs();
	}

	/**
	 * Private method in charge of creating the DAO instances dynamically.
	 * 
	 * @param className
	 *            The name of the class that will be instantiated for the DAO.
	 * @return A generic object that has to be casted to the proper DAO type.
	 * @throws DAOException
	 *             If there is any problem creating the DAO instance.
	 */

	private synchronized Object createDAOInstance(String className)
			throws DAOException {
		Object instanceDAO = null;

		try {
			if (log.isDebugEnabled())
				log.debug("Creating instance for DAO: " + className);

			Class<?> daoClass = Class.forName(className);
			instanceDAO = daoClass.newInstance();
		} catch (IllegalAccessException e) {
			throw new DAOException(
					"DAOFactory: Error, no enough privileges to instantiate: "
							+ className, e);
		} catch (ClassNotFoundException e) {
			throw new DAOException("DAOFactory: Error, class not found: "
					+ className, e);
		} catch (InstantiationException e) {
			throw new DAOException(
					"DAOFactory: Error, not possible to instantiate: "
							+ className, e);
		}

		return instanceDAO;
	}

	/**
	 * Gets the DAO that gives access to all the catalogs of entities.
	 * 
	 * @return The DAO that allows to access all the catalog of entities.
	 * @throws DAOException
	 *             If is not possible to access the DAO.
	 */

	public CatalogDAO getCatalogDAO() throws DAOException {
		if (catalogDAO == null)
			catalogDAO = (CatalogDAO) createDAOInstance(PropertyLookup
					.getCatalogDAOClassName());

		return catalogDAO;
	}

	/**
	 * Gets the DAO that gives access to all the registered users.
	 * 
	 * @return The DAO that can access all the users.
	 * @throws DAOException
	 *             If is not possible to access the DAO.
	 */

	public UserDAO getUserDAO() throws DAOException {
		if (userDAO == null)
			userDAO = (UserDAO) createDAOInstance(PropertyLookup
					.getUserDAOClassName());

		return userDAO;
	}

	/**
	 * Gets the DAO that gives access to all the news.
	 * 
	 * @return The DAO that can access all the news.
	 * @throws DAOException
	 *             If is not possible to access the DAO.
	 */

	public NewsDAO getNewsDAO() throws DAOException {
		if (newsDAO == null)
			newsDAO = (NewsDAO) createDAOInstance(PropertyLookup
					.getNewsDAOClassName());

		return newsDAO;
	}

	/**
	 * Gets the DAO that gives access to the plugin configuration hashes (used
	 * to create unique URLs).
	 * 
	 * @return The DAO that can access all the configuration hashes.
	 * @throws DAOException
	 *             If is not possible to access the DAO.
	 */

	public PluginHashDAO getConfigurationHashDAO() throws DAOException {
		if (configurationHashDAO == null)
			configurationHashDAO = (PluginHashDAO) createDAOInstance(PropertyLookup
					.getPluginHashDAOClassName());

		return configurationHashDAO;
	}

	/**
	 * Gets an DAO for the entities of the given type that are contained in the
	 * catalog with the given id. Note there is a better way of accessing these
	 * DAOs by means of the Catalog objects provided by the CatalogDAO although
	 * this method could be used as a shortcut.
	 * 
	 * @param catalogId
	 *            The identifier of the catalog that contains the entities.
	 * @param entityType
	 *            The type of entity for which we are requesting the DAO.
	 * @return The DAO for the given type of entity in the catalog with the
	 *         given id or null if there is not catalog or not entity
	 * @throws DAOException
	 *             If there is a problem accessing the DAO.
	 */

	public QueryableDataEntityDAO getQueryableDataEntityDAO(int catalogId,
			String entityType) throws DAOException {
		// This will initializes the catalog DAO if required.
		CatalogDAO dao = getCatalogDAO();
		Catalog catalog = dao.findCatalog(catalogId);

		if (catalog != null)
			return catalog.getEntityDAO(entityType);
		else
			return null;
	}

	/**
	 * Method that initializes the catalog DAOs, including the entity DAOs that
	 * are encapsulated into Catalog objects.
	 * 
	 * @throws DAOException
	 *             If there is any problem accessing/creating the DAOs.
	 */

	private void initializeAllDAOs() throws DAOException {
		if (log.isDebugEnabled())
			log.debug("Initializing catalog DAOs");

		// Initializes the catalog DAO if required.
		CatalogDAO dao = getCatalogDAO();
		Iterator<Catalog> catalogIterator = dao.getCatalogIterator();

		// Iterate through all the catalogs initializing the DAO for each kind
		// of entity.
		while (catalogIterator.hasNext()) {
			Catalog catalog = catalogIterator.next();
			List<String> entityTypes = catalog.getEntityTypes();

			if (log.isDebugEnabled())
				log.debug("Initializing entity DAOs for catalog: "
						+ catalog.getId());

			for (String type : entityTypes) {
				if (log.isDebugEnabled())
					log.debug("Initializing entity DAO for: " + type);
				ApplicationController.getInstance().getMemoryProfiler()
						.logMemoryUsage();

				// Create the entity DAO.
				QueryableDataEntityDAO entityDAO = (QueryableDataEntityDAO) createDAOInstance(PropertyLookup
						.getEntityDAOClassName());

				// Initialize the DAO (it will perform the caching if required).
				entityDAO.initialize(type, catalog);

				// Register the entity DAO in the container catalog.
				catalog.registerEntityDAO(type, entityDAO);

				ApplicationController.getInstance().getMemoryProfiler()
						.logMemoryUsage();
			}
		}
	}
}
