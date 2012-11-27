expcat.namespace("expcat.plugins.ResourceDownloader");

/**
 * @ignore
 */

expcat.plugins.ResourceDownloader = (function() {

	/**
	 * Component that generates a list of links pointing to the available
	 * (static) resources for the given catalog.
	 * 
	 * @param utilConnector
	 *            {expcat.cql.UtilConnector} Object that connects to the
	 *            ExplorerCat API layer.
	 * @param tableContainerId
	 *            {String} The id of the table where the links will be appended.
	 * @param contextURL
	 *            {String} Base URL for the web application.
	 * 
	 * @name expcat.plugins.ResourceDownloader
	 * @constructor
	 * @author Jacob Almagro - ExplorerCat project.
	 */

	var ResourceDownloader = function(utilConnector, tableContainerId,
			contextURL) {
		var connector = utilConnector;
		var linkContainerId = tableContainerId;
		var baseURL = contextURL || "/ExplorerCat";
		var resources = [];

		/**
		 * Method that we pass as a callback function to the API call in order
		 * to process the response (containing the resources).
		 * 
		 * @param response
		 *            {Object} JSON object that contains the API response.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.ResourceDownloader#
		 */

		var processResources = function(response) {
			var container = $("#" + linkContainerId + " > tbody:last");
			var i;

			if (response.returnCode === 0)
				resources = response.resources;

			for (i = 0; i < resources.length; ++i) {
				var link = createResourceLink(resources[i]);
				container.append(link);
			}
		};

		/**
		 * Appends the resource links returned from the API call to the
		 * container.
		 * 
		 * @memberOf expcat.plugins.ResourceDownloader#
		 * @public
		 */

		var appendResourceLinksToContainer = function() {
			connector.getStaticResources(processResources);
		};

		/**
		 * Creates and returns a resource link as a tr element.
		 * 
		 * @param resource
		 *            {Object} An object representing a resource (check
		 *            documentation for API methods).
		 * @return {jQuery} A jQuery object containing a tr DOM element
		 *         representing the resource.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.ResourceDownloader#
		 */

		var createResourceLink = function(resource) {
			var link, name, type, description;

			type = $("<td></td>");
			type.attr("class", "resource-type");
			type.html(resource.type);

			description = $("<td></td>");
			description.attr("class", "resource-description");
			description.html(resource.description);

			name = $("<td></td>");
			name.attr("class", "resource-name");
			name.html("<a href='" + baseURL + resource.resourceURI + "'>"
					+ resource.name + "</a>");

			link = $("<tr></tr>");
			link.append(name);
			link.append(type);
			link.append(description);

			return link;
		};

		/**
		 * Gets the resources retrieved from the server.
		 * 
		 * @return {[Object]} An array of objects representing the resources.
		 * 
		 * @memberOf expcat.plugins.ResourceDownloader#
		 * @public
		 */

		var getResources = function() {
			return resources;
		};

		/* Public API returned by the constructor */
		return {
			getResources : getResources,
			appendResourceLinksToContainer : appendResourceLinksToContainer
		};
	};

	/* Prototype */
	expcat.plugins.ResourceDownloader.prototype = {
		constructor : ResourceDownloader
	};

	return ResourceDownloader;

}());

/**
 * Static function that initialises the component, adds the links to the given
 * container and returns the configured component.
 * 
 * @param connections
 *            {Object} Contains the connectors to connect with the EC server.
 * @param contextURL
 *            {String} The web application base URL.
 * @param actionParameterLookup
 *            {Object} Map of parameters passed to the plug-in action.
 * @param universalOptions
 *            {Object} Options to be used by the plug-in for all the catalogs.
 *            Must contain the name of the container where the link will be
 *            appended.
 * @param localOptions
 *            {Object} Options to be used by the plug-in for this specific
 *            catalog.
 * @return A configured instance of the component.
 * 
 * @memberOf expcat.plugins.ResourceDownloader
 * @public
 */

expcat.plugins.ResourceDownloader.init = function(connectors, contextURL,
		actionParameterLookup, universalOptions, localOptions) {

	var containerId = universalOptions.containerId;
	var downloader = new expcat.plugins.ResourceDownloader(
			connectors.utilConnector, containerId, contextURL);

	downloader.appendResourceLinksToContainer();

	return downloader;
};
