package net.explorercat.tasks;

import java.util.Map;

/**
 * Base class that adds support for parameters. Parameters have to be provided
 * at building time and will be interpreted by each particular implementation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 Jan 2011
 */

public abstract class ProcessingTaskBase implements ProcessingTask
{
    private TaskParameters parameters;

    /**
     * Creates a new task that will be configured with the given parameters.
     * 
     * @param passedParameters A map containing the parameters (as strings)
     *        passed to the task.
     * @param requiredParameters List of parameters REQUIRED by the task. If
     *        they are not provided an exception will be raised.
     * @throws ProcessingTaskException
     */

    public ProcessingTaskBase(Map<String, String[]> passedParameters, String[] requiredParameters)
	    throws ProcessingTaskException
    {
	parameters = new TaskParameters(passedParameters, requiredParameters);
    }

    public String[] getParameterValue(String parameterName)
    {
	return parameters.getValueForParameter(parameterName);
    }

    /**
     * Inner class that helps dealing with parameters.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 14 Jan 2011
     */

    private static class TaskParameters
    {
	private Map<String, String[]> parameters;

	/**
	 * Builds a helper object to manage parameters.
	 * 
	 * @param passedParameters Map of parameters passed to the API method.
	 * @param requiredParameters List of parameters required by the task.
	 * @throws ProcessingTaskException Will be raised if any of the required
	 *         parameters is missing.
	 */

	public TaskParameters(Map<String, String[]> passedParameters, String[] requiredParameters)
		throws ProcessingTaskException
	{
	    parameters = passedParameters;

	    for(String parameter : requiredParameters)
		if(parameters.get(parameter) == null)
		    throw new ProcessingTaskException("Error, required parameter not provided: " + parameter);
	}

	/**
	 * Gets the values for the given parameter as a list of string.
	 * 
	 * @param parameterName The name of the parameter we are requesting.
	 * @return A string that represents its value or null if the parameter
	 *         wasn't registered.
	 */

	public String[] getValueForParameter(String parameterName)
	{
	    return parameters.get(parameterName);
	}
    }
}
