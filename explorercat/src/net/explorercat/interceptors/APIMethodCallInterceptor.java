package net.explorercat.interceptors;

import javax.servlet.http.HttpServletRequest;

import net.explorercat.application.ApplicationController;
import net.explorercat.util.misc.APIRequestTracker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Logs any external API call and checks for signs of abuse. This interceptor
 * will deny access if the request is coming from a blacklisted host address.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 21 Jun 2011
 */

public class APIMethodCallInterceptor implements Interceptor
{
    private static Log log = LogFactory.getLog(APIMethodCallInterceptor.class);

    private static final APIRequestTracker apiRequestTracker;

    static
    {
	apiRequestTracker = ApplicationController.getInstance().getAPIRequestTracker();
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void init()
    {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
	ActionContext context = actionInvocation.getInvocationContext();
	String address = ((HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST)).getRemoteAddr();
	String actionClass = actionInvocation.getAction().getClass().getSimpleName();

	// Bypass the check if we are requesting from the same machine.
	if(address.equals("0:0:0:0:0:0:0:1"))
	    return actionInvocation.invoke();

	if(log.isDebugEnabled())
	    log.debug("External request from host: " + address + " [" + actionClass + "]");

	// Divert the work-flow if the requesting address has been blacklisted.
	if(!apiRequestTracker.isAddressBlacklisted(address))
	    return actionInvocation.invoke();
	else
	    return "requestDenied";
    }
}
