package net.explorercat.actions;

import net.explorercat.application.ApplicationPropertyLookup;

/**
 * Dictionary of properties for the actions package, mainly containing
 * attributes for local paths to be used by the actions. In this package all
 * application properties are accessed using the static methods of this class.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date May 2011
 */

public class PropertyLookup
{
    // Name of the file that describes a plugin.
    private static final String PLUGIN_MANIFEST_FILENAME;

    // Path to the folder that contains all the plug-ins.
    private static final String PLUGINS_FOLDER_PATH;

    // Location of the folder where the catalogs are copied duing deployment.
    private static final String CATALOGS_FOLDER;

    // Names for the data and dictionary files.
    private static final String DICTIONARY_FILENAME;
    private static final String DATA_FILENAME;

    // Number of recent news to show.
    private static final int NUM_LATEST_NEWS;

    // Where to generate the resources for download.
    private static final String DOWNLOAD_FOLDER_PATH;

    // Maximum number of concurrent resources (as a threshold)
    // to measure if the system is busy.
    private static final int MAX_NUM_CONCURRENT_RESOURCES;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();

	PLUGIN_MANIFEST_FILENAME = lookup.getGlobalProperty("config.plugins.manifestFilename");
	PLUGINS_FOLDER_PATH = lookup.getGlobalProperty("config.plugins.folderPath");
	CATALOGS_FOLDER = lookup.getGlobalProperty("config.catalogs.folder");
	DICTIONARY_FILENAME = lookup.getGlobalProperty("config.catalogs.dicFilename");
	DATA_FILENAME = lookup.getGlobalProperty("config.catalogs.datFilename");
	NUM_LATEST_NEWS = Integer.parseInt(lookup.getGlobalProperty("config.frontpage.news"));
	DOWNLOAD_FOLDER_PATH = lookup.getGlobalProperty("config.download.folder");
	MAX_NUM_CONCURRENT_RESOURCES = Integer.parseInt(lookup.getGlobalProperty("config.download.maxNumConcurrentResources"));
    }

    public static String getCatalogsFolder()
    {
	return CATALOGS_FOLDER;
    }

    public static String getDictionaryFilename()
    {
	return DICTIONARY_FILENAME;
    }

    public static String getDataFilename()
    {
	return DATA_FILENAME;
    }

    public static int getNumLatestNews()
    {
	return NUM_LATEST_NEWS;
    }

    public static int getMaxNumberOfConcurrentResources()
    {
	return MAX_NUM_CONCURRENT_RESOURCES;
    }

    public static String getDownloadFolderPath()
    {
	return DOWNLOAD_FOLDER_PATH;
    }

    public static String getPluginManifestFilename()
    {
	return PLUGIN_MANIFEST_FILENAME;
    }

    public static String getPluginsFolderPath()
    {
	return PLUGINS_FOLDER_PATH;
    }
}
