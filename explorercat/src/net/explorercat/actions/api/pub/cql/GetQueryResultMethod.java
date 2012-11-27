package net.explorercat.actions.api.pub.cql;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultIdentifier;
import net.explorercat.cql.result.ResultPaginationCache;
import net.explorercat.cql.result.builders.ResultGenerationException;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API entry point for data retrieval of CQL queries. This API has been designed
 * to be used with AJAX and will provide JSON answers.
 * 
 * In this step the user ask for a set of rows related to a result:
 * 
 * - Input : { ticketNumber, hashCode, numRows, offset }
 * 
 * - Output : { returnCode = 0, resultData{resultLabel, rows[{id, values[]}]} }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * The user identifies the result using the ticket number and hash code returned
 * by the setup step.The number of rows and the offset are also specified by the
 * user. The server will reply with a JSON object that contains a matrix with
 * the result rows.
 * 
 * The JSON returned as a normal response (no error) has the following
 * properties:
 * 
 * - returnCode = 0 (no errors).
 * 
 * - resultData { resultLabel, rows[ {id, values[]} ] }
 * 
 * - resultLabel is the label of the result we have requested (could be empty).
 * 
 * - rows is an array of objects that have two properties {id, values[]}
 * 
 * - id is the numeric identifier of the entity.
 * 
 * - values is an array that contains the row values for all the columns.
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error building the results.
 * 
 * - 2 The requested result was not found in the pagination cache.
 * 
 * - 3 Bad parameters (any of the required parameters was not provided).
 * 
 * JSONP: There is an optional input parameter (jsoncallback) that specifies the
 * callback function to process the JSON response. This allows scripts from
 * other domains to access this API.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 23 Sep 2010
 */

public class GetQueryResultMethod extends JSONBasedAPIMethod {
	// Logging
	private static final Log log = LogFactory
			.getLog(GetQueryResultMethod.class);

	// Input Parameter: ticket number assigned to the user.
	private int ticketNumber = -1;

	// Input Parameter: hash code assigned to the result.
	private int hashCode = -1;

	// Input Parameter: number of entities/rows to select.
	private int numberOfRows = 10;

	// Input Parameter: offset in the selection.
	private int offset = -1;

	// Output Parameter: Normal JSON response
	private NormalJSONResponse normalResponse;

	// Input parameters setters.

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setNumRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	// Output parameters getters.

	public NormalJSONResponse getNormalResponse() {
		return this.normalResponse;
	}

	/**
	 * Executes the action, returning the set of rows specified by the user.
	 */

	@Override
	public String execute() {
		if (hashCode == -1 || ticketNumber == -1 || offset == -1) {
			setErrorResponse(3,
					"Bad Parameters, check you are providing hashCode, ticketNumber and offset");
			log.error(getErrorResponse().getErrorMessage());
			return ERROR;
		}

		if (log.isDebugEnabled())
			log.debug("Getting " + numberOfRows + " data rows for query: "
					+ hashCode);

		try {
			Result result = getResultFromPaginationCache();

			if (result == null) {
				setErrorResponse(2,
						"Result not found in the pagination cache for "
								+ hashCode);
				log.error(getErrorResponse().getErrorMessage());
				return ERROR;
			}

			normalResponse = new NormalJSONResponse(result);
			return SUCCESS;
		} catch (ExplorerCatCheckedException e) {
			setErrorResponse(1, "Error accessing the result: " + e);
			log.error(getErrorResponse().getErrorMessage());
			return ERROR;
		}
	}

	/**
	 * Gets the query result from the pagination cache.
	 */

	private Result getResultFromPaginationCache()
			throws ResultGenerationException {
		ResultPaginationCache paginationCache = ApplicationController
				.getInstance().getPaginationCache();
		ResultIdentifier resultIdentifier = new ResultIdentifier(hashCode,
				ticketNumber);
		return paginationCache.findResult(resultIdentifier, numberOfRows,
				offset);
	}

	/**
	 * Inner class that encapsulates the JSON response that is sent to the user.
	 * 
	 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
	 * @date 11 Oct 2010
	 */

	public static class NormalJSONResponse extends JSONResponse {
		private Result resultData;

		public NormalJSONResponse(Result resultData) {
			super(0);
			this.resultData = resultData;
		}

		public Result getResultData() {
			return resultData;
		}
	}
}
