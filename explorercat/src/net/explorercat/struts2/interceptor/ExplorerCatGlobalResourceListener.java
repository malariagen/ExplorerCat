package net.explorercat.struts2.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * This class allows to load different environments for explorarcat-global resource.
 * E.g. test, production, staging, development.
 * In this moment we manage only two environment. Test and production.
 * No string = production 
 * If you need to define another environment you have to set "explorercat.environment"
 * Remember to create explorercat-globalYOURENV resource
 * 
 * @author Cinzia Malangone - cm10@sanger.ac.uk
 * @date 11 Aug 2011
 */

public class ExplorerCatGlobalResourceListener implements ServletContextListener
{
    private static Log log = LogFactory.getLog(ExplorerCatGlobalResourceListener.class);
    private static String DEFAULT_EXPLORERCAT_GLOBAL_RESOURCE;

    public ExplorerCatGlobalResourceListener()
    {
	DEFAULT_EXPLORERCAT_GLOBAL_RESOURCE = "explorercat-global" + System.getProperty("explorercat.environment", "");
    }

    /**
     * Uses the LocalizedTextUtil to load messages from the global message
     * bundle.
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.Servle
     *      tContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)
    {
	if(log.isDebugEnabled())
	    log.debug("Loading explorercat-global resource from " + DEFAULT_EXPLORERCAT_GLOBAL_RESOURCE);
	
	LocalizedTextUtil.addDefaultResourceBundle(DEFAULT_EXPLORERCAT_GLOBAL_RESOURCE);
    }

    /**
     * Return the value of the explorercat resource.
     */
    public static String get_DEFAULT_EXPLORERCAT_GLOBAL_RESOURCE()
    {
	return DEFAULT_EXPLORERCAT_GLOBAL_RESOURCE;
    }

    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)
    {
	// do nothing
    }
}
