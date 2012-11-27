package net.explorercat.util.sql;

import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A class that looks for the data source used by the application.
 * 
 * @author Olivo Miotto (from the MapSeq codebase).
 */

public class DataSourceLookup
{
    // Logging
    private static Log log = LogFactory.getLog(DataSourceLookup.class);

    // Source Map
    private static HashMap<String, DataSource> dataSources = new HashMap<String, DataSource>();

    /**
     * Gets the associated data source for the giving URI. The data sources can
     * be configured in the context.xml file.
     */

    public static DataSource getDataSource(String dataSourceUri) throws SQLException
    {
	DataSource dataSource = dataSources.get(dataSourceUri);

	if(dataSource == null)
	{
	    try
	    {
		Context ctx = new InitialContext();
		dataSource = (DataSource) ctx.lookup(dataSourceUri);
		dataSources.put(dataSourceUri, dataSource);
	    }
	    catch(NamingException e)
	    {
		throw new SQLException("Error getting the data source \"" + dataSourceUri + "\"", e);
	    }
	}

	return dataSource;
    }
}
