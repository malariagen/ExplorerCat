package net.explorercat.util.misc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.ByteArrayInputStream;

/**
 * Class that creates the system environment variable explorercat.environment
 * @author Cinzia Malangone - cm10@sanger.ac.uk
 * @date 11 Aug 2011
 */

public class ExplorerCatEnvironment
{
    private Properties properties = new Properties(System.getProperties());

    /**
     * Set up new property for explorercat.environment
     */
    public ExplorerCatEnvironment(String newEnvironment)
    {
	InputStream newProperty = new ByteArrayInputStream(("explorercat.environment : " + newEnvironment).getBytes());
	try
	{
	    properties.load(newProperty);
	}
	catch(IOException error)
	{
	    System.out.println("Error to config enviroment variable");
	    error.printStackTrace();
	}
	// set the system properties
	System.setProperties(properties);
    }

    /**
     * Return the lists of the System Properties.
     */
    public static void printProperties()
    {
	System.getProperties().list(System.out);
    }
}
