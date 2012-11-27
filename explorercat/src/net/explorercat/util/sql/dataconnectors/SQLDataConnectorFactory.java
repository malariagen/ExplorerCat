package net.explorercat.util.sql.dataconnectors;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.dataaccess.DAOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A factory that serves data connectors. The object served is always a new
 * instance of the connector (it has never established a connection with the
 * database).
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public class SQLDataConnectorFactory
{
    // Logging
    private static Log log = LogFactory.getLog(SQLDataConnectorFactory.class);

    // Builder that will serve the data connector.
    private SQLDataConnectorBuilder dataConnectorBuilder;

    // Builder implementation class.
    private static final String BUILDER_CLASS;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	BUILDER_CLASS = lookup.getGlobalProperty("config.sqldataconnector.class.builder");
    }
    
    // Singleton
    private static SQLDataConnectorFactory instance = new SQLDataConnectorFactory();

    /**
     * Gets the singleton instance for the factory.
     * 
     * @throws DAOException
     */

    public static SQLDataConnectorFactory getInstance()
    {
	return instance;
    }

    /**
     * Builds the concrete DAO implementations using the builder provided by the
     * DAOBuilderFactory.
     */

    protected SQLDataConnectorFactory()
    {
	// We are not creating connectors dynamically because we need to create a new connector 
	// for each request. Doing that with newInstance will degrade the performance of the 
	// system. Instead we identify the type of connector configured and create a private 
	// builder for that implementation that satisfies the SQLDataConnectorBuilder interface.    

	if(log.isDebugEnabled())
	    log.debug("Creating instance of SQLDataProviderFactory");

	// If we can not instantiate the builder we are facing a fatal error
	// (we can't go on with the execution) so we throw a runtime error.

	try
	{
	    log.debug("BUILDER CLASS:" + BUILDER_CLASS);
	    Class<?> builderClass = Class.forName(BUILDER_CLASS);
	    dataConnectorBuilder = (SQLDataConnectorBuilder) builderClass.newInstance();
	}
	catch(IllegalAccessException e)
	{
	    throw new RuntimeException("SQLDataProviderFactory: Error, no enough privileges to instantiate: "
				       + BUILDER_CLASS, e);
	}
	catch(ClassNotFoundException e)
	{
	    throw new RuntimeException("SQLDataProviderFactory: Error, class not found: " + BUILDER_CLASS, e);
	}
	catch(InstantiationException e)
	{
	    throw new RuntimeException("SQLDataProviderFactory: Error,not possible to instantiate :" + BUILDER_CLASS, e);
	}
    }

    /**
     * Gets the SQL data connector in charge of executing the SQL queries and
     * updates.
     * 
     * @return A connector that will execute the SQL requests of the application
     */

    public SQLDataConnector getDataConnector()
    {
	return dataConnectorBuilder.buildConnector();
    }
}
