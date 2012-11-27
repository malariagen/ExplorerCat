package net.explorercat.actions.pub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import net.explorercat.actions.PropertyLookup;
import net.explorercat.application.ApplicationController;
import net.explorercat.data.Catalog;
import net.explorercat.data.PieceOfNews;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.DAOFactory;
import net.explorercat.plugins.Plugin;
import net.explorercat.plugins.PluginRegistry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.util.ServletContextAware;

import sun.awt.GlobalCursorManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Generic action that configures and renders plug-ins. It will receive the
 * plug-in name as a parameter, retrieve the plug-in from the registry and
 * provide all the required data to the plug-in view as bean properties.
 * 
 * The view of this action (genericplugin.jsp) will configure all the JavaScript
 * libraries to be used by the plug-in, importing all the configuration files.
 * All the parameters received by this action will be rendered as a JSON object.
 * The JSP file provided by the plug-in will be embedded (included) into the
 * generic view.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 24 Sep 2010
 */

public class GenericPluginAction extends ActionSupport implements Preparable,
		ParameterAware, ServletContextAware {
	private static final Log log = LogFactory.getLog(GenericPluginAction.class);

	// Plug-in registry instance (all the plug-ins are registered there)
	private PluginRegistry pluginRegistry;

	// Input Parameter: Name of the plug-in that is calling the action.
	private String pluginName;

	// Instance of the plug-in
	private Plugin plugin;

	// Parameters passed to the plug-in.
	private Map<String, String[]> passedParameters;

	// Output parameter: Identifier of the selected catalog.
	private int selectedCatalogId;

	private String selectedCatalogName;

	private String selectedCatalogVersion;

	// Context path to be used when accessing resources.
	private String contextPath;

	// Provides associated catalogs to this plugin
	private List<Catalog> catalogs;

	@Override
	public void prepare() throws Exception {
		ApplicationController globalController = ApplicationController
				.getInstance();
		pluginRegistry = globalController.getPluginRegistry();
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.passedParameters = parameters;
	}

	/**
	 * Executes the action, extracting all the required info from the plug-in
	 * registry.
	 */

	@Override
	public String execute() {
		if (pluginName != null) {
			plugin = pluginRegistry.getPlugin(pluginName);
			if (plugin == null) {
				if (log.isDebugEnabled())
					log.error("Error loading plug-in, " + pluginName
							+ " not found");

				return ERROR;
			}
			// Adds only catalogs for which this plugin is available to
			catalogs = new ArrayList<Catalog>();
			try {
				DAOFactory factory = ApplicationController.getInstance()
						.getDAOFactory();
				CatalogDAO catalogDAO = factory.getCatalogDAO();
				Iterator<Catalog> catalogIterator = catalogDAO
						.getCatalogIterator();
				while (catalogIterator.hasNext()) {
					Catalog currentCatalog = catalogIterator.next();
					if (plugin.isAssociatedToCatalog(currentCatalog.getId())) {
						catalogs.add(currentCatalog);
					}
				}
			} catch (DAOException e) {
				e.printStackTrace();
			}
			// Set catalog name
			setSelectedCatalogName();
			setSelectedCatalogVersion();

			return SUCCESS;
		}

		// Plug-in not found.
		return ERROR;
	}

	/**
	 * Gets a textual JSON representation of the map of passed parameters.
	 * 
	 * @return A string that codes a JSON object that contains all the
	 *         parameters passed to the plug-in.
	 */

	public String getPassedParametersAsJSONObject() {
		StringBuilder json = new StringBuilder(512);
		json.append("{");

		Set<String> keys = passedParameters.keySet();
		for (String parameter : keys) {
			json.append("'").append(parameter).append("':");

			String[] values = passedParameters.get(parameter);

			if (values.length == 0)
				json.append("null");

			else if (values.length == 1)
				json.append("'").append(values[0]).append("'");

			else {
				json.append("[");

				for (int i = 0; i < values.length; ++i)
					json.append("'").append(values[i]).append("'").append(",");

				// Removes the last comma.
				json.deleteCharAt(json.length() - 1);
				json.append("]");
			}

			json.append(",");
		}

		// Removes the last comma.
		json.deleteCharAt(json.length() - 1);
		json.append("}");

		return json.toString();
	}

	// Getters/Setters for the properties to be accessed by the view.

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getPluginDescription() {
		return this.plugin.getDescription();
	}

	public String getPluginInitFunction() {
		return plugin.getInitFunctionName();
	}

	public boolean isPluginInitFunctionDefined() {
		return plugin.getInitFunctionName() != null;
	}

	public void setSelectedCatalogId(int selectedCatalogId) {
		this.selectedCatalogId = selectedCatalogId;
	}

	public int getSelectedCatalogId() {
		return selectedCatalogId;
	}

	public String getPluginJSONUniversalOptions() {
		return plugin.getGlobalConfigurationObject();
	}

	public String getPluginJSONCatalogSpecificOptions() {
		try {
			ApplicationController globalController = ApplicationController
					.getInstance();
			String catalogName = globalController.getDAOFactory()
					.getCatalogDAO().findCatalog(selectedCatalogId).getName();
			return plugin.getConfigurationObjectForCatalog(catalogName);
		} catch (DAOException e) {
			log.error(e.getMessage());
			return "unkwnown";
		}
	}

	public List<String> getPluginCSSFilesToBeImported() {
		return plugin.getCSSFilesToBeImported(contextPath);
	}

	public List<String> getPluginJSFilesToBeImported() {
		return plugin.getJSFilesToBeImported(contextPath);
	}

	/**
	 * Gets the path of the plug-in view (HTML or JSP file that renders the
	 * plug-in).
	 * 
	 * @return The path to the plug-in view file.
	 */

	public String getPluginViewPath() {
		return plugin.getJSPPath();
	}

	/**
	 * Gets the path of the plug-in icon file (a small picture that identifies
	 * the plug-in).
	 * 
	 * @return The path to the plug-in icon picture.
	 */

	public String getPluginIconPath() {
		return contextPath + plugin.getIconPath();
	}

	/**
	 * Gets the path of the given resource.
	 * 
	 * @param resourceName
	 *            The name of the resource for which we are requesting the path.
	 * @return The path for the given resource.
	 */

	public String getResourcePath(String resourceName) {
		return contextPath + plugin.getResourcePath(resourceName);
	}

	@Override
	public void setServletContext(ServletContext context) {
		contextPath = context.getContextPath() + "/";
	}

	/**
	 * Gets only associated catalogs.
	 * 
	 * @return List<Catalog> only associated catalogs.
	 */

	public List<Catalog> getCatalogs() {
		return catalogs;
	}

	public String getPluginTitle() {
		return this.plugin.getTitle();
	}

	private void setSelectedCatalogName() {
		try {
			ApplicationController globalController = ApplicationController
					.getInstance();
			this.selectedCatalogName = globalController.getDAOFactory()
					.getCatalogDAO().findCatalog(selectedCatalogId).getName();
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}

	private void setSelectedCatalogVersion() {
		try {
			ApplicationController globalController = ApplicationController
					.getInstance();
			this.selectedCatalogVersion = globalController.getDAOFactory()
					.getCatalogDAO().findCatalog(selectedCatalogId)
					.getVersion();
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}

	public String getSelectedCatalogName() {
		return this.selectedCatalogName;
	}

	public String getSelectedCatalogVersion() {
		return this.selectedCatalogVersion;
	}

}
