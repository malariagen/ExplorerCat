package net.explorercat.dataaccess.mysql.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.explorercat.cql.selection.stats.StatsType;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.PropertyStatsLookup;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.dataaccess.PropertyLookup;
import net.explorercat.dataaccess.loaders.PropertyStatsLoader;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class in charge of getting all the stats related to entity properties from
 * the DB (MySQL implementation).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class PropertyStatsLoaderMySQL implements PropertyStatsLoader
{
    // Logging
    private static Log log = LogFactory.getLog(PropertyStatsLoaderMySQL.class);

    private String entityType;
    private Catalog catalog;
    private String entityTableName;
    private SQLDataConnector sqlProvider;

    /**
     * Creates a property statistics dictionary loader for all the entities of
     * the given type contained in the given catalog.
     * 
     * @param entityType The type of the entity.
     * @param catalog The catalog that contains the entities.
     */

    public PropertyStatsLoaderMySQL(String entityType, Catalog catalog) throws DAOException
    {
	this.entityType = entityType;
	this.catalog = catalog;

	this.entityTableName = PropertyLookup.getTableNamePrefix() + PropertyLookup.getTableNameSeparator()
			       + catalog.getId() + PropertyLookup.getTableNameSeparator() + entityType;

	this.sqlProvider = SQLDataConnectorFactory.getInstance().getDataConnector();
    }

    @Override
    public PropertyStatsLookup loadPropertyStats(PropertyDictionary propertyDictionary) throws DAOException
    {
	if(log.isErrorEnabled())
	    log.debug("Loading property stats for " + entityType + " in catalog: " + catalog.getId());

	PropertyStatsLookup propertyStats = new PropertyStatsLookup();

	try
	{
	    String tableName = entityTableName + PropertyLookup.getTableNameSeparator()
			       + PropertyLookup.getStatsTableNameSuffix();

	    sqlProvider.createPreparedStatement("SELECT * FROM " + tableName);

	    ResultSet resultSet = sqlProvider.executePreparedStatementAsQuery(null);
	    List<String> propertyNames = propertyDictionary.getPropertyNames();

	    for(String property : propertyNames)
		if(resultSet.next())
		{
		    // Sanity check (order).
		    String propertyName = resultSet.getString("attribute_name");
		    if(propertyName.equals(property))
		    {
			for(StatsType currentStats : StatsType.values())
			{
			    float statsValue = resultSet.getFloat(currentStats.toString());
			    propertyStats.addPropertyStats(property, currentStats, statsValue);
			}
		    }
		    else
			log.error("Skipping stats for property: " + property + " in " + tableName
				  + ", stats founded for " + propertyName);
		}

	    sqlProvider.closeConnection();
	    return propertyStats;
	}
	catch(SQLException e)
	{
	    log.error("Error querying the property stats for " + entityType);
	    throw new DAOException("Error loading the stats", e);
	}
	finally
	{
	    sqlProvider.closeConnection();
	}
    }
}
