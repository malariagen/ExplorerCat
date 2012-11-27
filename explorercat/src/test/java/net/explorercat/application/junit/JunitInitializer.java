package test.java.net.explorercat.application.junit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.explorercat.application.ApplicationController;
import net.explorercat.application.ApplicationInitializer;
import net.explorercat.struts2.interceptor.ExplorerCatGlobalResourceListener;
import net.explorercat.util.misc.ExplorerCatEnvironment;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JunitInitializer 
{
    // Logging
    private static Log log = LogFactory.getLog(JunitInitializer.class);

    public JunitInitializer() throws Exception
    {
	// Create initial context
	System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
	InitialContext context = new InitialContext();

	try
	{
	    context.lookup("java:comp/env/jdbc/explorercat-test-junit");
	}
	catch(NamingException namingException)
	{
	    context.createSubcontext("java:");
	    context.createSubcontext("java:comp");
	    context.createSubcontext("java:comp/env");
	    context.createSubcontext("java:comp/env/jdbc");

	    MysqlConnectionPoolDataSource datasource = new MysqlConnectionPoolDataSource();
	    datasource.setURL("jdbc:mysql://localhost:3306/explorercat-junit");
	    datasource.setUser("root");
	    //datasource.setPassword("MY_USER_PASSWORD");

	    context.bind("java:comp/env/jdbc/explorercat-test-junit", datasource);

	    new ExplorerCatEnvironment("test");
	    ExplorerCatGlobalResourceListener explorerCatGlobalResource = new ExplorerCatGlobalResourceListener();
	    explorerCatGlobalResource.contextInitialized(null);
	    ApplicationInitializer applicationInitializer = new ApplicationInitializer();
	    applicationInitializer.contextInitialized(null);
	}
    }

    public static boolean isValidContext(String name) throws NamingException
    {
	InitialContext context = new InitialContext();

	try
	{
	    context.lookup(name);
	    return true;
	}
	catch(NamingException namingException)
	{
	    return false;
	}
    }
    
    public static void printEnvironment() throws NamingException
    {
	InitialContext context = null;
	try
	{
	    context = new InitialContext();
	}
	catch(NamingException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	if(log.isDebugEnabled())
	    log.debug("InitialContext.env: "+ context.getEnvironment());
	
	System.out.println("InitialContext.env: "+ context.getEnvironment());
    }
}
