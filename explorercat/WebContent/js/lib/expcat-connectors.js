
/**
 * Base namespace for explorercat libraries.
 *
 * @author Jacob Almagro - ExplorerCat project
 * @namespace
 */

var expcat = {};

/**
 * Adds a new component to the current namespace.
 *
 * @param {String} componentName The name of the component.
 */

expcat.namespace = function(componentName) {
	var container = expcat;
	var components = componentName.split('.');
	var i;

	// Remove the namespace root.
	if(components[0] === "expcat") {
		components = components.slice(1);
	}

	// Create intermediate components if necessary.
	for( i = 0; i < components.length; ++i) {
		if( typeof container[components[i]] === "undefined") {
			container[components[i]] = {};
		}
		container = container[components[i]];
	}

	return container;
};
expcat.namespace("expcat.cql.condition");

/**
 * Namespace that contains all the utilities related to the creation of CQL
 * queries.
 * @namespace
 */

expcat.cql = {}/**
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

/**
 * @namespace Contains the utility connector. Use this object in order to
 *            communicate with the ExplorerCat utility services.
 */

expcat.namespace("expcat.cql.UtilConnector");

/**
 * Connector that simplifies the connection with the utility services. The
 * methods provided by this object allows the user to interact with the
 * utilities API.
 */

expcat.cql.UtilConnector = (function() {

	/**
	 * Creates a connector for accessing the utility methods.
	 * 
	 * @public
	 * @param configuration {Object} Contains the catalog id and API URLs to
	 *            configure the connector.
	 * @param configuration.catalogId {Integer} Identifier of the catalog that
	 *            will be queried
	 * @param configuration.baseURL {String} URL that points to the ExplorerCat
	 *            instance.
	 * @param configuration.getEntityDictionaryPath {String} Path to the method
	 *            that retrieves entity dictionaries.
	 * @param configuration.getPluginConfigurationPath {String} Path to the
	 *            method that retrieves the configuration of a plug-in.
	 * @param configuration.savePluginConfigurationPath {String} Path to to the
	 *            method that saves the configuration of a plug-in.
	 * @param configuration.getStaticResourcesPath {String} Path to the method
	 *            that retrieves the list of static resources available.
	 * @param configuration.getCatalogsPath {String} Path to the method that
	 *            retrieves the list of available catalogs.
	 * @param configuration.getCatalogInforPath {String} Path to the method that
	 *            retrieves info about a given catalog.
	 */
	
	var UtilConnector = function(configuration) {

		/* Identifier of the catalog that will be queried. */
		var catalogId = configuration.catalogId || -1;
		
		/* URL of the ExporerCat instance */
		var baseURL = configuration.baseURL || "http://localhost:8080/ExplorerCat";
		
		/* Path for the method that retrieves plug-in configurations */
		var getPluginConfigurationPath = configuration.getPluginConfigurationPath ||  "/pub/api/getPluginConfiguration";

		/* Path for the method that saves plug-in configurations. */
		var savePluginConfigurationPath = configuration.savePluginConfigurationPath || "/pub/api/registerPluginConfiguration";
		
		/*
		 * Path for the method that retrieves the list of static resources for
		 * this catalog
		 */
		var getStaticResourcesPath = configuration.getStaticResourcesPath || "/pub/api/getStaticResources";
		
		/* Path for the method that retrieves entity dictionaries. */
		var getEntityDictionaryPath = configuration.getEntityDictionaryPath || "/pub/api/getEntityDictionary";
		
		/* Path for the method that retrieves the list of available catalogs */
		var getCatalogsPath = configuration.getCatalogsPath || "/pub/api/getCatalogs";
		
		/* Path for the method that retrieves info about a given catalog */
		var getCatalogInfoPath = configuration.getCatalogInfoPath || "/pub/api/getCatalogInfo";
				
		/**
		 * Saves a JSON object describing the plug-in current status
		 * configuration (this configuration will be used to recreate the
		 * current plug-in status). A call-back function has to be provided in
		 * order to process the returned JSON object (this function does not
		 * return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {returnCode, hash}.
		 * 
		 * <code>
		 * 
		 * returnCode = 0 (no errors).
		 * 
		 * hash is the key that identifies the plug-in configuration.
		 * 
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 * 
		 * 1 There was an error saving the plug-in configuration.
		 * 
		 * </code>
		 * 
		 * @public
		 * @param pluginConfiguration {Object} JSON description of the plug-in
		 *            current configuration.
		 * @param callbackFunction {Function} The function that will be called
		 *            to process the JSON response from the server. Note this
		 *            function must receive a parameter (the JSON response
		 *            object).
		 */

		var savePluginConfiguration = function(configuration, callbackFunction) {
			/* Just in case the developer forgot the catalog id */
			configuration.catalogId = configuration.catalogId || catalogId;

			jQuery.getJSON(baseURL + savePluginConfigurationPath + "?jsoncallback=?", {
			    configuration : configuration
			}, callbackFunction, "json");
		};

		/**
		 * Gets a JSON object describing the configuration of the plug-in that
		 * corresponds to the given hash. This method is used to recreate shared
		 * URLs (unique views). A call-back function has to be provided in order
		 * to process the returned JSON object (this function does not return
		 * anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {returnCode, configuration}.
		 * 
		 * <code>
		 * 
		 * returnCode = 0 (no errors).
		 * 
		 * configuration is an object that describes the plug-in configuration.
		 * 
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 * 
		 * 1 There was an error retrieving the plug-in configuration.
		 * 
		 * </code>
		 * 
		 * @public
		 * @param hash {String} Key that identifies the plug-in configuration.
		 * @param callbackFunction {Function} The function that will be called
		 *            to process the JSON response from the server. Note this
		 *            function must receive a parameter (the JSON response
		 *            object).
		 */

		var getPluginConfiguration = function(hash, callbackFunction) {
			jQuery.getJSON(baseURL + getPluginConfigurationURL + "?jsoncallback=?", {			    
			    hash : hash
			}, callbackFunction, "json");
		};

		/**
		 * Gets the list of static resources related to the current catalog. A
		 * call-back function has to be provided in order to process the
		 * returned JSON object (this function does not return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: {returnCode, [resource]}.
		 * 
		 * <code>
		 * 
		 * returnCode = 0 (no errors).
		 * 
		 * resources is an array of the static resources associated with the catalog.
		 * 
		 * resources[i].name is the name of the resource.
		 * 
		 * resources[i].type is the type of resource.
		 * 
		 * resources[i].resourceURI is the location of the resource.
		 * 
		 * resources[i].description is a brief description of the resource.
		 * 
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 * 
		 * 1 There was an error retrieving the list of static resources.
		 * 
		 * </code>
		 * 
		 * @public
		 * @param callbackFunction {Function} The function that will be called
		 *            to process the JSON response from the server. Note this
		 *            function must receive a parameter (the JSON response
		 *            object).
		 */

		var getStaticResources = function(callbackFunction) {
			jQuery.getJSON(baseURL + getStaticResourcesPath + "?jsoncallback=?", {
			    catalogId : catalogId			
			}, callbackFunction, "json");
		};
		
		/**
		 * Gets the dictionary of the given entity (from the current catalog). A
		 * call-back function has to be provided in order to process the
		 * returned JSON object (this function does not return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: { returnCode,[propertyDescriptors{name,
		 * type, description, minimumValue, maximumValue, referencedEntity,
		 * referencedProperty, [allowedValues] }] }
		 * 
		 * <code>
		 * 
		 * returnCode = 0 (No errors)
		 * 
		 * propertyDescriptors is an array of objects describing each entity property.
		 * 
		 * propertyDescriptors[i].name is the name of the property.
		 * 
		 * propertyDescriptors[i].type is the type of the property (INTEGER, REAL, STRING, DATE, BOOLEAN, etc.).
		 * 
		 * propertyDescriptors[i].description is a brief description of the property.
		 * 
		 * propertyDescriptors[i].minimumValue the minimum value the property can take (null if none).  
		 * 
		 * propertyDescriptors[i].maximumValue the maximum value the property can take (null if none),
		 * 
		 * propertyDescriptors[i].referencedEntity the name of the entity referenced by the property (null if none).
		 * 
		 * propertyDescriptors[i].referencedProperty the name of the property referenced (null if none).
		 * 
		 * propertyDescriptors[i].allowedValues is an array of strings that contains the values allowed by this property as strings.
		 * 
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 * 
		 * 1 There was an error retrieving the entity dictionary.
		 * 
		 * 2 Unknown catalog id.
		 * 
		 * 3 Unknown entity type.
		 * 
		 * 4 Entity type not provided.
		 * 
		 * </code>
		 * 
		 * 
		 * @public
		 * @param entityType {String} Name of the entity whose dictionary we are
		 *            requesting.
		 * @param callbackFunction {Function} The function that will be called
		 *            to process the JSON response from the server. Note this
		 *            function must receive a parameter (the JSON response
		 *            object).
		 */

		var getEntityDictionary = function(entityType, callbackFunction) {
			jQuery.getJSON(baseURL + getEntityDictionaryPath + "?jsoncallback=?", {
			    catalogId : catalogId,
			    entityType : entityType
			}, callbackFunction, "json");
		};				
		
		
		/**
		 * Gets the list of catalogs available. A call-back function has to be
		 * provided in order to process the returned JSON object (this function
		 * does not return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: { returnCode, [catalogDescriptors {id,
		 * name, releaseDate, version, description}] }
		 * 
		 * <code>
		 * 
		 * returnCode = 0 (No errors)
		 * 
		 * catalogDescriptors is an array of objects describing each catalog
		 * 
		 * catalogDescriptors[i].name is the name of the catalog.
		 * 
		 * catalogDescriptors[i].id is the unique identifier of the catalog.
		 * 
		 * catalogDescriptors[i].releaseDate is the date when the catalog was released.
		 * 
		 * catalogDescriptors[i].version is the current version of the catalog.  
		 * 
		 * catalogDescriptors[i].description is a brief description of the catalog.
		 *  
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 * 
		 * 1 There was an error retrieving the catalogs.
		 * 
		 * </code>
		 * 
		 * 
		 * @public
		 * @param callbackFunction {Function} The function that will be called
		 *            to process the JSON response from the server. Note this
		 *            function must receive a parameter (the JSON response
		 *            object).
		 */

		var getCatalogs = function(callbackFunction) {
			jQuery.getJSON(baseURL + getCatalogsPath + "?jsoncallback=?", {
			}, callbackFunction, "json");
		};		
		
		
		/**
		 * Gets info for a given catalog. A call-back function has to be
		 * provided in order to process the returned JSON object (this function
		 * does not return anything).
		 * 
		 * The JSON object received by the call-back function as a normal
		 * response (no error) contains: { returnCode, id, name, releaseDate,
		 * version, description, [entityDescriptors{type, description,
		 * instances}] }
		 * 
		 * <code>
		 * 
		 * id is the unique identifier of the catalog.
		 * 
		 * releaseDate is the date when the catalog was released.
		 * 
		 * version is the current version of the catalog.  
		 * 
		 * description is a brief description of the catalog.
		 * 
		 * entityDescriptors is an array of objects describing the entities that belongs to the catalog.
		 * 
		 * entityDescriptors[i].type is the type (i.e. name) of the entity.
		 * 
		 * entityDescriptors[i].description is a brief description of the entity.
		 * 
		 * entityDescriptors[i].instances is the number of entity instances in the catalog.
		 *  
		 * </code>
		 * 
		 * If there is an error (returnCode != 0) the JSON object returned
		 * provides a returnCode and an errorMessage. Error return codes are:
		 * 
		 * <code>
		 * 
		 * 1 There was an error retrieving catalog info.
		 * 
		 * 2 Unknown catalog id.
		 * 
		 * </code>	
		 * 
		 * 
		 * @public
		 * @param callbackFunction {Function} The function that will be called
		 *            to process the JSON response from the server. Note this
		 *            function must receive a parameter (the JSON response
		 *            object).
		 */

		var getCatalogInfo = function(callbackFunction) {
			jQuery.getJSON(baseURL + getCatalogInfoPath + "?jsoncallback=?", {
				catalogId : catalogId
			}, callbackFunction, "json");
		};		
		
		
		/* Public API returned by the constructor */
		return {
			getStaticResources : getStaticResources,
		    getEntityDictionary : getEntityDictionary,
		    savePluginConfiguration : savePluginConfiguration,
		    getPluginConfiguration : getPluginConfiguration,
		    getCatalogs : getCatalogs,
		    getCatalogInfo : getCatalogInfo
		};
	};

	/* Prototype methods and properties */
	UtilConnector.prototype = {
		constructor : expcat.cql.UtilConnector
	};

	return UtilConnector;

}());
