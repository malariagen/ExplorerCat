package net.explorercat.actions.api.pub.cql;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.cql.interpreters.CQLInterpreter;
import net.explorercat.cql.interpreters.CQLScriptContext;
import net.explorercat.cql.interpreters.CQLScriptInterpreterException;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.ResultIdentifier;
import net.explorercat.cql.result.ResultPaginationCache;
import net.explorercat.cql.result.builders.ResultGenerator;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.scopes.UnresolvedSelectionScope;
import net.explorercat.tasks.ProcessingTask;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;

/**
 * API entry point for executing CQL queries. This method will execute the query
 * and establish a connection that can be used to retrieve the result data. This
 * API has been designed to be used with AJAX and will provide JSON answers.
 * 
 * - Input : { query, catalogId }
 * 
 * - Output : { ticketNumber, hashCode, returnCode, header{resultLabel, numRows,
 * columns[{name, alias, type, desc}]} }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * The user provides the CQL query to be executed (a result block is mandatory)
 * and the catalog to be used.
 * 
 * The JSON returned as a normal response (no error) has the following
 * properties:
 * 
 * - returnCode = 0 (no errors).
 * 
 * - ticketNumber is a unique integer that identifies the request.
 * 
 * - hashCode is a number that identifies the query sent to the server.
 * 
 * - header is an object describing the result and its columns
 * 
 * - resultLabel is the label given by the user to the result.
 * 
 * - numRows is the total number of rows for the result (count of all the rows
 * selected).
 * 
 * - columns is an array of objects {name, alias, desc, type}, one per column in
 * the result.
 * 
 * - name is the original name of the column (entity property or variable).
 * 
 * - alias is the name given to the column by the user (in the results block).
 * 
 * - desc is a description of the column.
 * 
 * - type is the value data type for the column.
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error parsing/interpreting the query
 * 
 * - 2 There was an error accessing the results.
 * 
 * - 3 There was an error accessing a processing task (on the server side).
 * 
 * - 4 No CQL result block has been specified.
 * 
 * JSONP: There is an optional input parameter (jsoncallback) that specifies the
 * callback function to process the JSON response. This allows scripts from
 * other domains to access this API.
 * 
 * preTask: With this optional parameter the user can specify a task on the
 * server side that will be used to process the data of the result. All the
 * required parameters for the specified task have to be provided or an error
 * will occur.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 23 Sep 2010
 */

public class SetupQuerySessionMethod extends JSONBasedAPIMethod implements
		ParameterAware {
	// Logging
	private static final Log log = LogFactory
			.getLog(SetupQuerySessionMethod.class);

	// Input Parameter: Id of the catalog to be queried.
	private int catalogId = -1;

	// Input Parameter: CQL query to be executed.
	private String query = null;

	// Input Parameters: parameters that will be used by processing tasks
	// (server side).
	private Map<String, String[]> taskParameters;

	// Output Parameter: Normal JSON response.
	private NormalJSONResponse normalResponse;

	// Input parameters setters.

	public void setQuery(String query) {
		this.query = unquoteString(query);
	}

	private String unquoteString(String stringToUnquote) {
		if (stringToUnquote.charAt(0) == '"'
				|| stringToUnquote.charAt(0) == '\'')
			return stringToUnquote.substring(1, stringToUnquote.length() - 1);
		else
			return stringToUnquote;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.taskParameters = parameters;
	}

	// Output parameters getters.

	public NormalJSONResponse getNormalResponse() {
		return this.normalResponse;
	}

	/**
	 * Parses the CQL query and execute it.
	 */

	@Override
	public String execute() {
		if (log.isDebugEnabled())
			log.debug("Executing SQL query session setup for catalog "
					+ catalogId + ", query: " + query);

		try {
			CQLScriptContext scriptContext = interpretQuery(query);

			// Create the result generator and register it in the pagination
			// cache.
			ResultGenerator resultGenerator = scriptContext
					.getResultGenerator();
			ResultPaginationCache paginationCache = ApplicationController
					.getInstance().getPaginationCache();
			ResultIdentifier resultId = paginationCache
					.registerResultGenerator(resultGenerator);

			// Check a result block has been specified.
			if (!resultGenerator.hasResultBuilder()) {
				setErrorResponse(4,
						"No RESULT block has been specified, check CQL reference.");
				log.error(getErrorResponse().getErrorMessage());
				return ERROR;
			}

			resolveResultFields(resultId, scriptContext, resultGenerator);
			registerProcessingTasks(resultGenerator);

			// Return the normal response to the user (no errors).
			this.normalResponse = new NormalJSONResponse(
					resultId.getTicketNumber(),
					resultId.getResultGeneratorHashCode(),
					resultGenerator.getResultHeader());
			return SUCCESS;
		} catch (CQLScriptInterpreterException e) {
			setErrorResponse(1, e.getMessage() + " "
					+ getParsingErrorMessages(e));
			log.error(getErrorResponse().getErrorMessage());
			return ERROR;
		} catch (ExplorerCatCheckedException e) {
			setErrorResponse(2,
					"Error accessing the results: " + e.getMessage());
			log.error(getErrorResponse().getErrorMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			setErrorResponse(3, "Error loading task: " + e.getMessage());
			log.error(getErrorResponse().getErrorMessage());
			return ERROR;
		}
	}

	/**
	 * Gets the parsing error messages from an interpreter exceptions.
	 */

	private String getParsingErrorMessages(CQLScriptInterpreterException e) {
		StringBuilder errorMessages = new StringBuilder();
		List<String> parsingErrors = e.getParsingErrorMessages();

		for (String error : parsingErrors)
			errorMessages.append("[").append(error).append("] ");

		return errorMessages.toString();
	}

	/**
	 * Interprets the CQL script returning the script context.
	 * 
	 * @return The context of the interpreted script (provides access to the
	 *         objects derived from the script interpretation).
	 */

	private CQLScriptContext interpretQuery(String query)
			throws CQLScriptInterpreterException {
		CQLInterpreter interpreter = new CQLInterpreter();
		return interpreter.interpretCQLQuery(query);
	}

	/**
	 * Resolves all the fields for the result generator after executing the CQL
	 * query.
	 */

	private void resolveResultFields(ResultIdentifier resultId,
			CQLScriptContext context, ResultGenerator generator)
			throws SelectionException, SelectionResolutionException {
		String scopeName = "CQLQueryAPI" + resultId;
		UnresolvedSelectionScope scriptScope = new UnresolvedSelectionScope(
				scopeName, catalogId, context);
		SelectionResolver resolver = new SelectionResolver(catalogId,
				scriptScope);

		generator.resolveResult(resolver);
	}

	/**
	 * Registers all the processing tasks to be executed before sending the
	 * query results back to the user.
	 * 
	 * @param resultGenerator
	 * @throws Exception
	 *             If there is an error loading a processing task.
	 */

	private void registerProcessingTasks(ResultGenerator resultGenerator)
			throws Exception {
		// Register all the processing tasks specified.
		String[] tasks = taskParameters.get("task");

		// This implies a bit of effort loading dynamically the classes.
		// Notice that a task has the role of a server-side plug-in that
		// pre-processes
		// the data before sending it back to the user.
		if (tasks != null) {
			if (log.isDebugEnabled())
				log.debug("Registering processing tasks");

			for (String task : tasks) {
				if (log.isDebugEnabled())
					log.debug("Task: " + task);

				// We assume each task only provides a constructor that receives
				// a map
				// with the parameters passed to the API method.
				Class<?> taskClass = Class
						.forName("net.explorercat.preprocessing.tasks." + task);
				Constructor<?> taskConstructor = taskClass.getConstructors()[0];
				ProcessingTask taskInstance = (ProcessingTask) (taskConstructor
						.newInstance(taskParameters));
				resultGenerator.registerPreprocessingTask(taskInstance);
			}
		}
	}

	/**
	 * Inner class that encapsulates the normal JSON response that is sent to
	 * the user.
	 * 
	 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
	 * @date 11 Oct 2010
	 */

	public static class NormalJSONResponse extends JSONResponse {
		private long ticketNumber;
		private int hashCode;
		private ResultHeader resultHeader;

		public NormalJSONResponse(long ticketNumer, int hashCode,
				ResultHeader header) {
			super(0);
			this.ticketNumber = ticketNumer;
			this.hashCode = hashCode;
			this.resultHeader = header;
		}

		/**
		 * Gets the ticket number generated for the current session.
		 */

		public long getTicketNumber() {
			return ticketNumber;
		}

		/**
		 * Gets the hash key generated to identify the current session.
		 */

		public int getHashCode() {
			return hashCode;
		}

		/**
		 * Gets the header that specifies the format of the results obtained
		 * after executing the query.
		 */

		public ResultHeader getHeader() {
			return resultHeader;
		}
	}
}
