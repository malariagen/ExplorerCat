/**
 * @namespace Contains the CQL connector API. Use this object in order to
 *            communicate with the ExplorerCat server using the CQL language.
 */

expcat.namespace("expcat.cql.CQLConnector");

/**
 * CQL connector that simplifies the connection to the ExplorerCat server. The
 * methods provided by this object allows the user to interact with ExplorerCat
 * by means of CQL queries.
 */

expcat.cql.CQLConnector = (function() {

	/**
	 * Creates a connector for CQL queries.
	 * 
	 * @public
	 * @param configuration
	 *            {Object} Contains the catalog id and the API URLs to configure
	 *            the connector.
	 * @param configuration.catalogId
	 *            {Integer} Identifier of the catalog that will be queried.
	 * @param configuration.baseURL
	 *            {String} URL that points to the ExplorerCat instance.
	 * @param configuration.setupQueryPath
	 *            {String} overwrites the path to the method that setups the
	 *            query/session,
	 * @param configuration.getResultPath
	 *            {String} overwrites the path to the method that gets data
	 *            query result,
	 * @param configuration.sortResultPath
	 *            {String} overwrites the path to the method that sorts the
	 *            query result on the server side,
	 * @param configuration.releaseQueryPath
	 *            {String} overwrites the path to the method that releases the
	 *            current query session,
	 * @param configuration.generateTextFilePath
	 *            {String} overwrites the path to the method that generates a
	 *            text file with the query result.
	 */

	var CqlConnector = function(configuration) {

		configuration = configuration || {};

		/* Identifier of the catalog that will be queried. */
		var catalogId = configuration.catalogId || -1;

		/* URL of the ExporerCat instance */
		var baseURL = configuration.baseURL
				|| "http://localhost:8080/ExplorerCat";

		/* Path for setup step (init). */
		var setupQueryPath = configuration.setupQueryPath
				|| "/pub/api/cql/setupQuerySession";

		/* Path for the data retrieval. */
		var getResultPath = configuration.getResultPath
				|| "/pub/api/cql/getQueryResult";

		/* Path for sorting requests. */
		var sortResultPath = configuration.sortResultPath
				|| "/pub/api/cql/sortQueryResult";

		/* Path for releasing the query. */
		var releaseQueryPath = configuration.releaseQueryPath
				|| "/pub/api/cql/releaseQuerySession";

		/* Path for generating a CSV file for the query result */
		var generateTextFilePath = configuration.generateTextFilePath
				|| "/pub/api/cql/generateTextFileFromResult";

		// Removes the last slash from the base URL (if present).
		if (baseURL.charAt(baseURL.length - 1) === "/")
			baseURL = baseURL.substring(0, baseURL.length - 1);

		/**
		 * Initialises the CQL query request, executing the given CQL query on
		 * the server. A call-back function has to be provided in order to
		 * process the returned JSON object (this function does not return
		 * anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {ticketNumber, hashCode, returnCode,
		 * header{resultLabel, numRows, columns[{name, alias, type, desc}]}
		 * 
		 * <code>
		 *
		 * returnCode = 0 (no errors).

		 * ticketNumber is a unique integer that identifies the request.
		 *
		 * hashCode is a number that identifies the query sent to the server.
		 *
		 * header is an object describing the result and its columns.
		 *
		 * resultLabel is the label given by the user to the result.
		 *
		 * numRows is the total number of rows for the result (count of all the rows
		 * selected).
		 *
		 * columns is an array of objects {name, alias, desc, type}, one per column in
		 * the result.
		 *
		 * columns[i].name is the original name of the column (entity property or
		 * variable).
		 *
		 * columns[i].alias is the name given to the column by the user (in the results
		 * block).
		 *
		 * columns[i].desc is a description of the column.
		 *
		 * columns[i].type is the value data type for the column.
		 *
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 *
		 * 1 There was an error parsing/interpreting the query
		 *
		 * 2 There was an error accessing the results.
		 *
		 * 3 There was an error accessing a processing task (on the server side).
		 *
		 * 4 No RESULT block has been specified.
		 *
		 * </code>
		 * 
		 * This API method must be called before any other method is executed.
		 * Once this method has been called (and returned without error) the
		 * query session is considered open. It will remain open for 30 minutes
		 * since the last time it is accessed. To close the session the
		 * {@link releaseQuery} method must be called.
		 * 
		 * @param query
		 *            {String} CQL query/script to be executed on the server.
		 * 
		 * @param parameters
		 *            {Object} Parameters to be passed to the API method. They
		 *            will refer to the plug-in parameters and the server tasks.
		 * 
		 * @param callbackFunction
		 *            {Function} The function that will be called to process the
		 *            JSON response from the server. Note this function must
		 *            receive a parameter (the JSON response object).
		 */

		var setupQuery = function(query, callbackFunction, parameters) {
			parameters = parameters || {};

			/* Add the protected parameters */
			parameters.catalogId = catalogId;
			parameters.query = query;

			jQuery.getJSON(baseURL + setupQueryPath + "?jsoncallback=?",
					parameters, callbackFunction, "json");
		};
		/**
		 * Gets data from the selection produced by the CQL query (executed by
		 * the {@link setupQuery} call). A call-back function has to be provided
		 * in order to process the returned JSON object (this function does not
		 * return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {returnCode = 0,
		 * resultData{resultLabel, rows[{id, values[]}]}}
		 * 
		 * <code>
		 *
		 * returnCode = 0 (no errors).
		 *
		 * resultData { resultLabel, rows[ {id, values[]} ] }
		 *
		 * resultData.resultLabel is the label of the result we have requested (could be
		 * empty).
		 *
		 * resultData.rows is an array of objects that have two properties {id,
		 * values[]}
		 *
		 * resultData.rows[i].id is the numeric identifier of the entity.
		 *
		 * resultData.rows[i].values is an array that contains the row values for all the
		 * columns.
		 *
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 *
		 *  1 There was an error building the results.
		 *
		 *  2 The requested result was not found in the pagination cache.
		 *
		 *  3 Bad parameters (any of the required parameters was not provided).
		 *
		 * </code>
		 * 
		 * @public
		 * @param ticketNumber
		 *            {Integer} Ticket number returned by the {@link setupQuery}
		 *            API call.
		 * @param hashCode
		 *            {String} Hash returned by the {@link setupQuery} API call.
		 * @param numberOfRows
		 *            The number of data rows to be retrieved from the server
		 *            (each entity instance is considered a row).
		 * @param offset
		 *            The starting position to retrieve the data rows (first
		 *            position is 0).
		 * @param callbackFunction
		 *            {Function} The function that will be called to process the
		 *            JSON response from the server. Note this function must
		 *            receive a parameter (the JSON response object).
		 */

		var getResultData = function(ticketNumber, hashCode, numberOfRows,
				offset, callbackFunction) {
			var callTemp = baseURL + getResultPath
					+ '?jsoncallback=?{ticketNumber:' + ticketNumber
					+ ',hashCode:' + hashCode + ',offset:' + offset
					+ ',numRows:' + numberOfRows + '},callbackFunction,"json"';
			jQuery.getJSON(baseURL + getResultPath + "?jsoncallback=?", {
				ticketNumber : ticketNumber,
				hashCode : hashCode,
				offset : offset,
				numRows : numberOfRows
			}, callbackFunction, "json");
		};
		/**
		 * Sorts the data of the selection generated by the CQL query (executed
		 * by the {@link setupQuery} call). Notice that the sorting is performed
		 * on the server side. A call-back function has to be provided in order
		 * to process the returned JSON object (this function does not return
		 * anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {returnCode = 0}
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 *
		 * 1 There was an error sorting the results.
		 *
		 * 2 The requested result was not found in the pagination cache.
		 *
		 * </code>
		 * 
		 * @public
		 * @param ticketNumber
		 *            {Integer} Ticket number returned by the {@link setupQuery}
		 *            API call.
		 * @param hashCode
		 *            {String} Hash returned by the {@link setupQuery} API call.
		 * @param sortingPropertyName
		 *            Name of the property that will be used to sort the data.
		 * @param sortInDescendantOrder
		 *            True if the data will be sorted in descendant order, false
		 *            otherwise.
		 * @param callbackFunction
		 *            {Function} The function that will be called to process the
		 *            JSON response from the server. Note this function must
		 *            receive a parameter (the JSON response object).
		 */

		var sortResultData = function(ticketNumber, hashCode,
				sortingPropertyName, sortInDescendantOrder, callbackFunction) {
			var callTemp = baseURL + sortResultPath
					+ '?jsoncallback=?{ticketNumber:' + ticketNumber
					+ ',hashCode:' + hashCode + ',sortingPropertyName:'
					+ sortingPropertyName + ',sortInDescendantOrder:'
					+ sortInDescendantOrder + '},callbackFunction,"json"';
			jQuery.getJSON(baseURL + sortResultPath + "?jsoncallback=?", {
				ticketNumber : ticketNumber,
				hashCode : hashCode,
				sortingPropertyName : sortingPropertyName,
				sortInDescendantOrder : sortInDescendantOrder
			}, callbackFunction, "json");
		};
		/**
		 * Closes the session with the server. A call-back function has to be
		 * provided in order to process the returned JSON object (this function
		 * does not return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {returnCode = 0}
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 *
		 * 2 There was an error removing the results from the pagination cache.
		 *
		 * </code>
		 * 
		 * @public
		 * @param ticketNumber
		 *            {Integer} Ticket number returned by the {@link setupQuery}
		 *            API call.
		 * @param hashCode
		 *            {String} Hash returned by the {@link setupQuery} API call.
		 * @param callbackFunction
		 *            {Function} The function that will be called to process the
		 *            JSON response from the server. Note this function must
		 *            receive a parameter (the JSON response object).
		 */

		var releaseQuery = function(ticketNumber, hashCode, callbackFunction) {
			jQuery.getJSON(baseURL + releaseQueryPath + "?jsoncallback=?", {
				ticketNumber : ticketNumber,
				hashCode : hashCode
			}, callbackFunction, "json");
		};
		/**
		 * Generates a text file that contains the CQL query resulting selection
		 * (executed by the {@link setupQuery} call). A call-back function has
		 * to be provided in order to process the returned JSON object (this
		 * function does not return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {returnCode, resourceId, retrievalURL}
		 * 
		 * <code>
		 *
		 * returnCode = 0 (no errors).
		 *
		 * resourceId is the unique identifier of the resource.
		 *
		 * retrievalURL is the URL that will give access to the resource (the text file).
		 *
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 *
		 * 1 There was an error generating the file.
		 *
		 * 2 The resource is too big (result too big to be written as a text file).
		 *
		 * 3 The system is too busy to write the text file now.
		 *
		 * 4 Duplicated request, the resource is being generated.
		 *
		 * </code>
		 * 
		 * @public
		 * @param ticketNumber
		 *            {Integer} Ticket number returned by the {@link setupQuery}
		 *            API call.
		 * @param hashCode
		 *            {String} Hash returned by the {@link setupQuery} API call.
		 * 
		 * @param fileFormat
		 *            {String} The format of the file to generate (TAB, CSV,
		 *            etc.).
		 * @param callbackFunction
		 *            {Function} The function that will be called to process the
		 *            JSON response from the server. Note this function must
		 *            receive a parameter (the JSON response object).
		 */

		var generateTextFile = function(ticketNumber, hashCode, fileFormat,
				callbackFunction) {
			jQuery.getJSON(baseURL + generateTextFilePath + "?jsoncallback=?",
					{
						ticketNumber : ticketNumber,
						hashCode : hashCode,
						fileFormat : fileFormat
					}, callbackFunction, "json");
		};
		/* Public API returned by the constructor */
		return {
			setupQuery : setupQuery,
			releaseQuery : releaseQuery,
			getResultData : getResultData,
			sortResultData : sortResultData,
			generateTextFile : generateTextFile
		};
	};
	/* Prototype methods and properties */
	CqlConnector.prototype = {
		constructor : expcat.cql.CQLConnector
	};

	return CqlConnector;

}());
