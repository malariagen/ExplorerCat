
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
