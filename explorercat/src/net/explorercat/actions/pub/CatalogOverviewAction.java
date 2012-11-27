package net.explorercat.actions.pub;

import java.util.GregorianCalendar;
import java.util.List;

import net.explorercat.application.ApplicationController;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.DAOFactory;
import net.explorercat.plugins.Plugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Makes available all the data needed to render the catalog overview page. It
 * will show the meta-data associated with the catalog will provide the list of
 * plug-ins (tools) that will be presented to the user.
 * 
 * @author Jacob Almagro Garcia - Sep 2010
 */

public class CatalogOverviewAction extends ActionSupport implements Preparable {
	private static final Log log = LogFactory
			.getLog(CatalogOverviewAction.class);

	private CatalogDAO catalogDAO;

	// Input Parameter: Identifier of the selected catalog.
	private int selectedCatalogId;

	// Output parameters
	// Objects that will be accessed by the view
	private Catalog catalog;
	private List<Plugin> plugins;

	/**
	 * Collects the required data from the DAOs.
	 */

	@Override
	public void prepare() throws DAOException {
		try {
			DAOFactory factory = ApplicationController.getInstance()
					.getDAOFactory();
			catalogDAO = factory.getCatalogDAO();

			// The following is moved to execute method
			// Set the plug-ins, list of tools that will be accessible by the
			// user.
			/*
			 * ApplicationController globalController = ApplicationController
			 * .getInstance(); plugins =
			 * globalController.getPluginRegistry().getPlugins(
			 * selectedCatalogId);
			 */

		} catch (DAOException e) {
			log.error("Error accessing the DAOs from CatalogOverviewAction");
			throw e;
		}
	}

	/**
	 * The main method of the action, configures the view properties.
	 */

	@Override
	public String execute() {
		catalog = catalogDAO.findCatalog(selectedCatalogId);

		ApplicationController globalController = ApplicationController
				.getInstance();
		plugins = globalController.getPluginRegistry().getPlugins(
				selectedCatalogId);

		return SUCCESS;
	}

	// Public getters/setters to allow the JSP access the data objects.

	public void setSelectedCatalogId(int catalogId) {
		this.selectedCatalogId = catalogId;
	}

	public int getSelectedCatalogId() {
		return this.selectedCatalogId;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public List<Plugin> getPlugins() {
		return plugins;
	}
}
