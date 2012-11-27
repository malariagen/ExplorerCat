package net.explorercat.dataaccess;

import net.explorercat.application.ApplicationPropertyLookup;

/**
 * Dictionary of properties for the data access package, mainly containing
 * attributes to form the DB table names and class names to be instantiated. In
 * this package all application properties are accessed using the static methods
 * of this class.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date May 2011
 */

public class PropertyLookup
{
    // Prefix to be used when accessing implementation properties for DAOs.
    // Use prefix + name of the DAO (e.g. config.dao.implementation.class.catalog)
    private static final String DAO_CLASS_PROPERTY_PREFIX = "config.dao.class";

    // DAO implementation classes
    private static final String CATALOG_DAO_CLASS;
    private static final String USER_DAO_CLASS;
    private static final String NEWS_DAO_CLASS;
    private static final String ENTITY_DAO_CLASS;
    private static final String PLUGIN_HASH_DAO_CLASS;

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

	CATALOG_DAO_CLASS = lookup.getGlobalProperty(DAO_CLASS_PROPERTY_PREFIX + ".catalog");
	USER_DAO_CLASS = lookup.getGlobalProperty(DAO_CLASS_PROPERTY_PREFIX + ".user");
	NEWS_DAO_CLASS = lookup.getGlobalProperty(DAO_CLASS_PROPERTY_PREFIX + ".news");
	ENTITY_DAO_CLASS = lookup.getGlobalProperty(DAO_CLASS_PROPERTY_PREFIX + ".entity");
	PLUGIN_HASH_DAO_CLASS = lookup.getGlobalProperty(DAO_CLASS_PROPERTY_PREFIX + ".pluginHash");

	CATALOGS_TABLE_NAME = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.catalogs");
	TABLE_NAME_PREFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.prefix");
	TABLE_NAME_SEPARATOR = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.separator");
	ENTITIES_TABLE_SUFFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.entities.suffix");
	DICTIONARY_TABLE_SUFFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.dictionary.suffix");
	STATS_TABLE_SUFFIX = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.stats.suffix");
	UNIQUE_ID_ATTRIBUTE = lookup.getGlobalProperty("config.catalogDeployer.mysql.tables.uniqueId");
    }

    public static String getCatalogDAOClassName()
    {
	return CATALOG_DAO_CLASS;
    }

    public static String getUserDAOClassName()
    {
	return USER_DAO_CLASS;
    }

    public static String getNewsDAOClassName()
    {
	return NEWS_DAO_CLASS;
    }

    public static String getEntityDAOClassName()
    {
	return ENTITY_DAO_CLASS;
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

    public static String getPluginHashDAOClassName()
    {
	return PLUGIN_HASH_DAO_CLASS;
    }
}
