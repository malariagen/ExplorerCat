package net.explorercat.actions.api.pub.cql;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.actions.api.JSONBasedAPIMethod.JSONResponse;
import net.explorercat.actions.api.pub.cql.GetQueryResultMethod.NormalJSONResponse;
import net.explorercat.application.ApplicationController;
import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultIdentifier;
import net.explorercat.cql.result.ResultPaginationCache;
import net.explorercat.cql.result.builders.ResultGenerationException;
import net.explorercat.cql.result.builders.ResultGenerator;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * API entry point for sorting CQL query results. The user ask to sort a result
 * by the value of a given property. This API has been designed to be used with
 * AJAX and will provide JSON answers.
 * 
 * - Input : { ticketNumber, hashCode, sortingPropertyName,
 * sortInDescendantOrder }
 * 
 * - Output : { returnCode = 0 }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * The user identifies the result using the ticket number and hash code returned
 * by the setup step. The property that will be used for the sorting is
 * specified by propertyName and the type of sorting by the
 * sortInDescendantOrder flag. The server will reply with a JSON object that
 * contains only a returnCode property (0 if everything went fine).
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error sorting the results.
 * 
 * - 2 The requested result was not found in the pagination cache.
 * 
 * JSONP: There is an optional input parameter (jsoncallback) that specifies the
 * callback function to process the JSON response. This allows scripts from
 * other domains to access this API.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 23 Sep 2010
 */

public class SortQueryResultMethod extends JSONBasedAPIMethod {
	// Logging
	private static final Log log = LogFactory
			.getLog(SortQueryResultMethod.class);

	// Input Parameter: ticket number assigned to the user.
	private int ticketNumber;

	// Input Parameter: hash code assigned to the result.
	private int hashCode;

	// Input Parameter: name of the property to be used in the sorting.
	private String sortingPropertyName;

	// Input Parameter: flag that is true if the sorting must be in descendant
	// order.
	private boolean sortInDescendantOrder;

	// Output Parameter: Normal JSON response.
	private NormalJSONResponse normalResponse;

	// Input parameters setters.

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public void setSortInDescendantOrder(boolean sortInDescendantOrder) {
		this.sortInDescendantOrder = sortInDescendantOrder;
	}

	public void setSortingPropertyName(String sortingPropertyName) {
		this.sortingPropertyName = sortingPropertyName;
	}

	// Output parameters getters.

	public NormalJSONResponse getNormalResponse() {
		if (log.isDebugEnabled())
			log.debug("--> getNormalResponse AT getNormalResponse=====>");
		if (log.isDebugEnabled())
			log.debug(this.normalResponse);
		return this.normalResponse;
	}

	/**
	 * Sorts the result using the specified property.
	 */

	@Override
	public String execute() {
		if (log.isDebugEnabled())
			log.debug("Sorting result for query: " + hashCode);

		try {
			ResultGenerator resultGenerator = getResultGeneratorForQuery();
			resultGenerator.sortResultSelectionByPropertyValue(
					sortingPropertyName, sortInDescendantOrder);

			normalResponse = new NormalJSONResponse();
			if (log.isDebugEnabled())
				log.debug("--> normalResponse AT execute=====>");
			if (log.isDebugEnabled())
				log.debug(normalResponse);
			if (log.isDebugEnabled())
				log.debug("--> resultGenerator.getResultHeader().getColumns().size():  "
						+ resultGenerator.getResultHeader().getColumns().size());
			if (log.isDebugEnabled())
				log.debug("--> "
						+ resultGenerator.getResultHeader().getResultName()
						+ " -- "
						+ resultGenerator.getResultHeader().getNumRows());

			return SUCCESS;
		} catch (ExplorerCatCheckedException e) {
			setErrorResponse(1, "Error sorting the result: " + e.getMessage());
			log.error(getErrorResponse().getErrorMessage());
			return ERROR;
		}

	}

	/**
	 * Gets the result generator for the query specified by the user.
	 * 
	 * @return The object in charge of generating the query results.
	 */

	private ResultGenerator getResultGeneratorForQuery()
			throws ResultGenerationException {
		ResultPaginationCache paginationCache = ApplicationController
				.getInstance().getPaginationCache();
		ResultIdentifier resultIdentifier = new ResultIdentifier(hashCode,
				ticketNumber);
		return paginationCache.findResultGenerator(resultIdentifier);
	}

	/**
	 * Inner class that encapsulates the JSON response that is sent to the user.
	 * 
	 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
	 * @date 11 Oct 2010
	 */

	public static class NormalJSONResponse extends JSONResponse {
		public NormalJSONResponse() {
			super(0);
		}
	}
}
