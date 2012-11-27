package net.explorercat.plugins;

import java.io.File;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Loads all the plug-ins contained in the plug-ins folder using their manifest
 * XML files. We assume that each plug-in is contained in a folder named after
 * the plug-in name and that the XML manifest lives at the root of this folder.
 * After the plug-in is loaded any pre-calculated query is computed. Note that
 * this class can be used to load individual plug-ins when the exact location of
 * the manifest plug-in is provided.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 27 Oct 2010
 */

public class PluginLoader {
	private static final Log log = LogFactory.getLog(PluginLoader.class);

	// Name of the file that describes a plug-in.
	private String manifestFilename;

	private PluginBuilder pluginBuilder;
	private PluginRegistry registry;

	/**
	 * Creates a plug-in loader based on manifest files.
	 * 
	 * @param manifestFilename
	 *            The name of the file that will describe a plug-in.
	 */

	public PluginLoader(String manifestFilename) {
		this.manifestFilename = manifestFilename;

		ApplicationController globalController = ApplicationController
				.getInstance();
		registry = globalController.getPluginRegistry();

		pluginBuilder = new PluginBuilder();
	}

	/**
	 * Loads all the plug-ins contained in the given folder. We assume that each
	 * plug-in is contained in a folder named after the plug-in name and that
	 * the XML manifest lives at the root of this folder.
	 * 
	 * @param pluginsFolderPath
	 *            The absolute path to the folder that contains all the
	 *            plug-ins.
	 */

	public void loadPluginsIntoRegistry(String pluginsFolderPath)
			throws PluginLoadingException {
		if (log.isDebugEnabled())
			log.debug("Loading plugins found in folder: " + pluginsFolderPath);

		File pluginsFolder = new File(pluginsFolderPath);

		if (!pluginsFolder.exists() || !pluginsFolder.isDirectory())
			throw new PluginLoadingException(
					"The folder doesn't exist or is not a directory: "
							+ pluginsFolderPath, "all", "Bat PATH");
		try {
			String[] containedPluginsFolders = pluginsFolder.list();

			for (int i = 0; i < containedPluginsFolders.length; ++i) {
				File currentFolder = new File(pluginsFolderPath
						+ File.separatorChar + containedPluginsFolders[i]);

				// Note we avoid hidden folders (like .svn)
				if (!containedPluginsFolders[i].startsWith(".")
						&& currentFolder.isDirectory()) {
					if (log.isDebugEnabled())
						log.debug("Loading plug-in: "
								+ containedPluginsFolders[i]);

					// TODO Remove this HORRIBLE trick!
					// Gets the plug-in path relative to the WebContent folder.
					String relativePluginPath = currentFolder.getPath()
							.replaceAll(".*plugins", "plugins").substring(0);

					Plugin plugin = loadIndividualPlugin(
							currentFolder.getPath() + File.separator
									+ manifestFilename, relativePluginPath);

					if (registry.isPluginRegistered(plugin.getName())) {
						plugin.loadPrecalculatedQueries();
						registry.registerPlugin(plugin);
					}
				}
			}
		} catch (PluginLoadingException e) {
			throw e;
		} catch (ExplorerCatCheckedException e) {
			throw new PluginLoadingException("unknown", "Bad LOAD", e);
		}
	}

	/**
	 * Loads a plug-in into the registry given the path to its XML manifest
	 * file.
	 * 
	 * @param pluginManifestFileAbsolutePath
	 *            The absolute path to the XML manifest file that describes the
	 *            plug-in.
	 * 
	 * @param pluginFolderWebContentRelativePath
	 *            The path to the plug-in folder (relative to the WebContent
	 *            folder).
	 * 
	 * @return The plugin object that representes the plugin that has been
	 *         loaded.
	 */

	public Plugin loadIndividualPlugin(String pluginManifestFileAbsolutePath,
			String pluginFolderWebContentRelativePath)
			throws PluginLoadingException, SelectionException,
			SelectionResolutionException, DAOException {
		Plugin plugin = pluginBuilder.buildPlugin(
				pluginManifestFileAbsolutePath,
				pluginFolderWebContentRelativePath);
		return plugin;
	}

}
