package net.explorercat.actions.api;

import com.opensymphony.xwork2.ActionSupport;

/**
 * A base class for actions that implement API methods that are based on JSON
 * objects to communicate with the user. Notice that we are only providing an
 * implementation for the error response, any concrete class must provide a
 * normal JSON response.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 May 2011
 */

public abstract class JSONBasedAPIMethod extends ActionSupport
{
    // Input parameter: Function name to wrap the JSON response (JSONP).
    private String callbackFunctionForJSON;

    // Output parameter: Error as JSON response.
    private ErrorJSONResponse errorResponse;

    /**
     * Sets the name of the function to wrap the JSON response.
     * 
     * @param jsoncallback Name of the function that will be used to wrap the
     *        JSON response (JSONP)
     */

    public void setCallback(String jsoncallback)
    {
	this.callbackFunctionForJSON = jsoncallback;
    }

    /**
     * Gets the name of the function that will be sued to wrap the JSON
     * response.
     * 
     * @return The function that will wrap the JSON response or null if no
     *         callback function was specified.
     */

    public String getCallback()
    {
	return this.callbackFunctionForJSON;
    }

    /**
     * Gets the error response of the method as a JSON object.
     * 
     * @return The JSON object that encapsulates the response (error) that is
     *         sent to the user.
     */

    public ErrorJSONResponse getErrorResponse()
    {
	return this.errorResponse;
    }

    /**
     * Sets the error response that will be sent to the user as a JSON object.
     * 
     * @param returnCode An error code (different from 0)
     * @param errorMessage A brief explanation of the error.
     */

    public void setErrorResponse(int returnCode, String errorMessage)
    {
	this.errorResponse = new ErrorJSONResponse(returnCode, errorMessage);
    }

    /**
     * Inner base class for JSON responses.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date May 2011
     */

    public abstract static class JSONResponse
    {
	protected int returnCode;

	public JSONResponse(int returnCode)
	{
	    this.returnCode = returnCode;
	}

	/**
	 * Gets the return code returned by the API method. We assume any value
	 * different from 0 means there was an error during the execution of the
	 * method.
	 * 
	 * @return The return code of the executed API method (integer).
	 */

	public int getReturnCode()
	{
	    return returnCode;
	}
    }

    /**
     * Encapsulates the error JSON response that is sent to the user.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 11 Oct 2010
     */

    public static class ErrorJSONResponse extends JSONResponse
    {
	private String errorMessage;

	public ErrorJSONResponse(int returnCode, String erroMessage)
	{
	    super(returnCode);
	    this.errorMessage = erroMessage;
	}

	/**
	 * Gets an message with a brief description of the error occurred.
	 * 
	 * @return A string describing the error.
	 */

	public String getErrorMessage()
	{
	    return errorMessage;
	}
    }
}
