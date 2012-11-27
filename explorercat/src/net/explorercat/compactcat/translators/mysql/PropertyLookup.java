package net.explorercat.compactcat.translators.mysql;

import net.explorercat.application.ApplicationPropertyLookup;

/**
 * Dictionary of properties for the MySQL translators package, mainly containing
 * attributes to form the DB table names. In this package all application
 * properties are accessed using the static methods of this class.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 21 Apr 2011
 */

public class PropertyLookup
{
    // Properties used to form the DB tables names.
    private static final String CATALOGS_TABLE_NAME;
    private static final String TABLE_NAME_PREFIX;
    private static final String TABLE_NAME_SEPARATOR;
    private static final String ENTITIES_TABLE_SUFFIX;
    private static final String DICTIONARY_TABLE_SUFFIX;
    private static final String STATS_TABLE_SUFFIX;
    private static final String UNIQUE_ID_ATTRIBUTE;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();

	CATALOGS_TABLE_NAME = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.catalogs");
	TABLE_NAME_PREFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.prefix");
	TABLE_NAME_SEPARATOR = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.separator");
	ENTITIES_TABLE_SUFFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.entities.suffix");
	DICTIONARY_TABLE_SUFFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.dictionary.suffix");
	STATS_TABLE_SUFFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.stats.suffix");
	UNIQUE_ID_ATTRIBUTE = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.uniqueId");
    }

    public static String getCatalogsTableName()
    {
	return CATALOGS_TABLE_NAME;
    }

    public static String getTableNamePrefix()
    {
	return TABLE_NAME_PREFIX;
    }

    public static String getTableNameSeparator()
    {
	return TABLE_NAME_SEPARATOR;
    }

    public static String getEntitiesTableNameSuffix()
    {
	return ENTITIES_TABLE_SUFFIX;
    }

    public static String getDictionaryTableNameSuffix()
    {
	return DICTIONARY_TABLE_SUFFIX;
    }

    public static String getStatsTableNameSuffix()
    {
	return STATS_TABLE_SUFFIX;
    }

    public static String getUniqueIdAttribute()
    {
	return UNIQUE_ID_ATTRIBUTE;
    }
}
