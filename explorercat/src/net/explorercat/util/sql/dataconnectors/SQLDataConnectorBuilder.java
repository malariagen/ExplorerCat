package net.explorercat.util.sql.dataconnectors;

/**
 * An interface to be implemented by concrete builders for SQLDataConnector.
 * There will be a builder per implementation (e.g. MySQL). The builder will
 * decide which connector instance will return based on the application
 * configuration.
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public interface SQLDataConnectorBuilder
{
    /**
     * Builds the concrete data connector.
     */

    public SQLDataConnector buildConnector();

    /**
     * Gets the class name of the data connector that is built by this builder.
     */

    public String getDataConnectorClassName();
}
