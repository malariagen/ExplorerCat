package net.explorercat.plugins;

import net.explorercat.application.ApplicationPropertyLookup;

/**
 * Dictionary of properties for the plugins package, mainly containing
 * attributes to find and load the plugins. In this package all application
 * properties are accessed using the static methods of this class.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date May 2011
 */

public class PropertyLookup
{
    // Default icon to be used when no icon has been specified for the plug-in.
    private static final String DEFAULT_ICON_PATH;

    // Backup file where we record the configuration hashes marked as permanents.
    private static final String CONFIGURATION_HASH_BACKUP_FILE_PATH;

    // Limit (in MB) for the cache of plugin configuration hashes.
    private static final int CONFIGURATION_HASH_CACHE_MEMORY_LIMIT;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	DEFAULT_ICON_PATH = lookup.getGlobalProperty("config.plugins.defaultIcon");
	CONFIGURATION_HASH_CACHE_MEMORY_LIMIT = Integer.parseInt(lookup.getGlobalProperty("config.plugins.configurationHashCacheMemoryLimit"));
	CONFIGURATION_HASH_BACKUP_FILE_PATH = lookup.getGlobalProperty("config.plugins.configurationHashBackupFilePath");
    }

    public static String getDefaultIconPath()
    {
	return DEFAULT_ICON_PATH;
    }

    public static int getConfigurationHashCacheMemoryLimit()
    {
	return CONFIGURATION_HASH_CACHE_MEMORY_LIMIT;
    }

    public static String getConfigurationHashBackupFilePath()
    {
	return CONFIGURATION_HASH_BACKUP_FILE_PATH;
    }
}
