package net.explorercat.application;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import net.explorercat.struts2.interceptor.ExplorerCatGlobalResourceListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A class to centralise the access to the application properties. This includes
 * any global property, skins properties and the DB configuration (default and
 * secondary data sources URI).
 * 
 * @author Jacob Almagro Garcia - April 2010
 */

public class ApplicationPropertyLookup
{
    // Logging
    private static Log log = LogFactory.getLog(ApplicationPropertyLookup.class);

    // Global properties bundle name.
    private static final String GLOBAL_PROPERTIES_BUNDLE_NAME = ExplorerCatGlobalResourceListener.get_DEFAULT_EXPLORERCAT_GLOBAL_RESOURCE();

    // Skins properties (CSS layout).
    private static final String SKIN_PROPERTIES_BUNDLE_NAME = "explorercat-skins";

    // Default URI property.
    private static final String DEFAULT_URI = "config.db.default.uri";

    // The global bundle that contains all the configuration parameters
    private ResourceBundle globalBundle;

    // Bundle that contains the allowed skin configurations.
    private ResourceBundle skinsBundle;

    // DB source.
    private String dataSourceURI;

    // Singleton
    private static ApplicationPropertyLookup instance = new ApplicationPropertyLookup();

    /**
     * Gets the instance that provides access to the properties.
     */

    static public ApplicationPropertyLookup getInstance()
    {
	return instance;
    }

    /**
     * Gets the value for a property of the global bundle.
     * 
     * @param propertyName The name of the property.
     * @return The value of the property or propertyName if the property has not
     *         been defined.
     */

    public String getGlobalProperty(String propertyName)
    {
	try
	{
	    return this.globalBundle.getString(propertyName).trim();
	}
	catch(MissingResourceException e)
	{
	    if(log.isErrorEnabled())
		log.error("Error accessing " + propertyName + ". Default value " + propertyName + " will be used.");
	    return propertyName;
	}
    }

    /**
     * Gets the CSS prefix for the given skin layout. The CSS files must be
     * located in WebContent/css/prefix/prefix*.css The pictures must be located
     * in WebContent/css/prefix/img/
     * 
     * @param skinName The name of the skin in the form: skin.skinName
     * @return The prefix for that skin that locates the proper CSS files.
     */

    public String getSkinLayoutPrefix(String skinName)
    {
	try
	{
	    return this.skinsBundle.getString(skinName);
	}
	catch(MissingResourceException e)
	{
	    if(log.isErrorEnabled())
		log.error("Error accessing " + skinName + ". Default value: " + skinName + ", will be used.");
	    return skinName;
	}
    }

    /**
     * Gets the default skin layout configured for the application.
     * 
     * @return The prefix for the default skin that locates the proper CSS
     *         files.
     */

    public String getDefaultSkinLayoutPrefix()
    {
	return getSkinLayoutPrefix("skin");
    }

    /**
     * Gets the default data source URI for the application.
     */

    public String getDataSourceURI()
    {
	return dataSourceURI;
    }

    /**
     * Creates the instance of the Lookup, loading the bundle of global
     * properties.
     * 
     * @throws MissingResourceException Fatal error if we can't load the
     *         resource bundle.
     */

    private ApplicationPropertyLookup() throws MissingResourceException
    {
	try
	{
	    // Get the bundle with the global properties for the application.
	    globalBundle = PropertyResourceBundle.getBundle(GLOBAL_PROPERTIES_BUNDLE_NAME);

	    // Allowed skins.
	    skinsBundle = PropertyResourceBundle.getBundle(SKIN_PROPERTIES_BUNDLE_NAME);

	    // Configure the default database URI.
	    dataSourceURI = globalBundle.getString(DEFAULT_URI).trim();

	    if(log.isDebugEnabled())
		log.debug("dataSourceURI: " + dataSourceURI);
	}
	catch(MissingResourceException e)
	{
	    throw e; // Fatal error, required properties not defined. We have to abort.
	}
    }
}
