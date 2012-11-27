package net.explorercat.plugins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Registry of plugins, stores all the plugins registered in the system. This
 * class is a singleton.
 * 
 * Check the documentation of the Plugin class for more information.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 24 Sep 2010
 */

public class PluginRegistry {
	private static final Log log = LogFactory.getLog(PluginRegistry.class);

	// Maps plugins by name.
	private Map<String, Plugin> plugins;

	public PluginRegistry() {
		plugins = new HashMap<String, Plugin>();
	}

	/**
	 * Gets the list of registered plug-ins available for the given catalog id
	 * sorted by name (notice this won't include abstract plugins).
	 * 
	 * TODO It is still not possible specify when a plugin mustn't be visible
	 * for a given catalog (however, pre-calculated queries can be catalog
	 * specific). Right now we are returning all the plugins that are not
	 * abstract. UPDATE: The TODO functionality is added, testing.
	 * 
	 * TODO: Need to check/ammend if plugin is abstract
	 * 
	 * @param catalogId
	 *            The identifier of the catalog for which we are retrieving the
	 *            plug-ins.
	 * @return The list of plug-ins enabled for the given catalog sorted by name
	 *         (not including abstract plugins).
	 */

	public List<Plugin> getPlugins(int catalogId) {
		Iterator<Plugin> pluginInterator = plugins.values().iterator();
		List<Plugin> catalogPlugins = new ArrayList<Plugin>();

		while (pluginInterator.hasNext()) {
			Plugin currentPlugin = pluginInterator.next();
			if (!currentPlugin.isAbstract()) {
				if (currentPlugin.isAssociatedToCatalog(catalogId)) {
					catalogPlugins.add(currentPlugin);
				}
			}
		}
		Collections.sort(catalogPlugins);
		return catalogPlugins;
	}

	/**
	 * Gets the plug-in associated with the given name.
	 * 
	 * @param pluginName
	 *            The name of the plug-in.
	 * @return The plug-in registered with the given name or null if no present.
	 */

	public Plugin getPlugin(String pluginName) {
		return plugins.get(pluginName);
	}

	/**
	 * Registers a plug-in in the register.
	 * 
	 * @param plugin
	 *            The plug-in to be registered.
	 */

	public void registerPlugin(Plugin plugin) {
		if (log.isDebugEnabled())
			log.debug("Registering plugin: " + plugin);

		plugins.put(plugin.getName(), plugin);
	}

	/**
	 * Checks if a plugin with the given name has been registered.
	 */

	public boolean isPluginRegistered(String pluginName) {
		return plugins.get(pluginName) == null;
	}
}
