package net.explorercat.util.sql.dataconnectors.mysql;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.util.sql.dataconnectors.SQLDataConnector;
import net.explorercat.util.sql.dataconnectors.SQLDataConnectorBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A builder in charge of creating a data connector of the proper type for the
 * MySQL implementation.
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public class MySQLDataConnectorBuilder implements SQLDataConnectorBuilder
{
    // Logging
    private static Log log = LogFactory.getLog(ApplicationPropertyLookup.class);

    private static final String IMPLEMENTATION_CLASS;

    static
    {
	ApplicationPropertyLookup lookup = ApplicationPropertyLookup.getInstance();
	IMPLEMENTATION_CLASS = lookup.getGlobalProperty("config.sqldataconnector.class");
    }

    // Builder that will serve the data connector.
    private SQLDataConnectorBuilder dataConnectorBuilder;

    /**
     * Creates the builder choosing the proper implementation for the
     * config.sql.data.connector.implementation.class property.
     */

    public MySQLDataConnectorBuilder() throws ClassNotFoundException
    {
	// Extract the real class name (no packages)
	int lastDotIndex = IMPLEMENTATION_CLASS.lastIndexOf(".");
	String unqualifiedClassName = IMPLEMENTATION_CLASS.substring(lastDotIndex == -1 ? 0 : lastDotIndex + 1);

	if(log.isDebugEnabled())
	    log.debug("Creating MySQLDataProviderBuilder for " + unqualifiedClassName);

	// Creates the instance of the proper builder.
	if(ProductionMySQLDataConnectorBuilder.class.getName().contains(unqualifiedClassName))
	    this.dataConnectorBuilder = new ProductionMySQLDataConnectorBuilder();

	else if(DebugMySQLDataConnectorBuilder.class.getName().contains(unqualifiedClassName))
	    this.dataConnectorBuilder = new DebugMySQLDataConnectorBuilder();

	else
	{
	    if(log.isErrorEnabled())
		log.error("Builder implementation for " + IMPLEMENTATION_CLASS + " not found.");

	    throw new ClassNotFoundException("MySQLDataProviderBuilder: " + IMPLEMENTATION_CLASS + " not found.");
	}
    }

    @Override
    public SQLDataConnector buildConnector()
    {
	return dataConnectorBuilder.buildConnector();
    }

    @Override
    public String getDataConnectorClassName()
    {
	return dataConnectorBuilder.getDataConnectorClassName();
    }

    // Concrete builders for the MySQL implementation.
    // We have one builder per implementation because we are not creating 
    // the instances of the connector dynamically (i.e. using instanceOf).
    // That is a good approach when we create a low number of objects but 
    // for SQLDataConnectors we have to create one new object per request and
    // doing it dynamically will undermine the performance. 

    // Anyhow, the complexity is hidden in this class. The client only knows
    // about the interfaces SQLDataConnector and SQLDataConnectorBuilder.
    // We encapsulate the builders of a given implementation in the same class
    // to avoid an explosion in the number of classes.

    /**
     * A builder for a MySQL data connector that traces the execution of the
     * queries
     * 
     * @author Jacob Almagro Garcia - April 2010
     */

    private static class DebugMySQLDataConnectorBuilder implements SQLDataConnectorBuilder
    {
	@Override
	public SQLDataConnector buildConnector()
	{
	    return new DebugMySQLDataConnector();
	}

	@Override
	public String getDataConnectorClassName()
	{
	    return DebugMySQLDataConnector.class.getName();
	}
    }

    /**
     * A builder for a MySQL data connector that has minimum overhead and is up
     * to be used in production systems.
     * 
     * @author Jacob Almagro Garcia - April 2010
     */

    private static class ProductionMySQLDataConnectorBuilder implements SQLDataConnectorBuilder
    {
	@Override
	public SQLDataConnector buildConnector()
	{
	    return new ProductionMySQLDataConnector();
	}

	@Override
	public String getDataConnectorClassName()
	{
	    return ProductionMySQLDataConnector.class.getName();
	}
    }
}
