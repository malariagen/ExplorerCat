expcat.namespace("expcat.plugins.TextFileExporter");
/**
 * @ignore
 */

expcat.plugins.TextFileExporter = (function() {

	/**
	 * Component in charge of managing the generation of text files for query
	 * results.
	 * 
	 * @name expcat.plugins.TextFileExporter
	 * @constructor
	 * @public
	 * @author Jacob Almagro - ExplorerCat Project
	 */

	var TextFileExporter = function(containerId, fileExportingFormat,
			connector, ticketAndHashProvider, webContext) {

		var divContainer = $("#" + containerId);
		var exportFile = fileExportingFormat;
		var cqlConnector = connector;
		var isGeneratingFile = false;
		var isFileGenerated = false;
		var hasErrorOccurred = false;
		var fileURL = null;
		var prefixURL = webContext || "localhost:8080/ExplorerCat";
		var queryTicketAndHashProvider = ticketAndHashProvider

		if (prefixURL.charAt(prefixURL.length - 1) === "/")
			prefixURL = prefixURL.substring(0, prefixURL.length - 1);

		/**
		 * Initialises the object, updating the button text and binding the
		 * handlers.
		 * 
		 * @memberOf expcat.plugins.TextFileExporter#
		 * @private
		 * @ignore
		 */

		var init = function() {

			divContainer.html("Click here to export results as "
					+ exportFile.description + " file");
			divContainer.bind("click", function() {

				var ticketAndHash = queryTicketAndHashProvider
						.getCurrentQueryTicketAndHashCode();

				if (hasErrorOccurred) {
					isGeneratingFile = false;
					isFileGenerated = false;
				}
				if (ticketAndHash && ticketAndHash.ticket !== null
						&& ticketAndHash.hashCode !== null && !isGeneratingFile
						&& !isFileGenerated) {
					generateFile(ticketAndHash);
				}
			});
		};

		/**
		 * Uses the connector to request the generation of the text file.
		 * 
		 * @memberOf expcat.plugins.TextFileExporter#
		 * @private
		 * @ignore
		 */

		var generateFile = function(ticketAndHash) {
			cqlConnector.generateTextFile(ticketAndHash.ticket,
					ticketAndHash.hashCode, exportFile.format,
					processFileResponse);
			divContainer.html("Generating " + exportFile.description
					+ " file... please wait");
		};

		/**
		 * Callback fucntion that processes the server response.
		 * 
		 * @memberOf expcat.plugins.TextFileExporter#
		 * @private
		 * @ignore
		 */

		var processFileResponse = function(response) {

			var ticketAndHash = queryTicketAndHashProvider
					.getCurrentQueryTicketAndHashCode();

			if (response.returnCode === 0) {
				fileURL = response.retrievalURL;
				isFileGenerated = true;
				isGeneratingFile = false;
				divContainer.html("<a href='" + prefixURL + fileURL
						+ "&ticketNumber=" + ticketAndHash.ticket
						+ "&hashCode=" + ticketAndHash.hashCode
						+ "'> Download results as a " + exportFile.description
						+ " file</a>");
				triggerAutomaticFileDownloadingDialogue(prefixURL + fileURL
						+ "&ticketNumber=" + ticketAndHash.ticket
						+ "&hashCode=" + ticketAndHash.hashCode);
			} else if (response.returnCode === 2) {
				hasErrorOccurred = true;
				divContainer
						.html("File would be too big, please reduce your selection and try again clicking here");
			} else if (response.returnCode === 3) {
				hasErrorOccurred = true;
				divContainer
						.html("Ooops, system too busy. Please try again later");
			}
		};

		/**
		 * Function that automatically triggers file download
		 * 
		 * @memberOf expcat.plugins.TextFileExporter#
		 * @private
		 * @ignore
		 */
		var triggerAutomaticFileDownloadingDialogue = function downloadURL(url) {
			var iframe = document
					.getElementById("HiddenIframeForAutomaticallyDownloadingFile");
			if (iframe === null) {
				iframe = document.createElement('iframe');
				iframe.id = "HiddenIframeForAutomaticallyDownloadingFile";
				iframe.style.display = 'none';
				document.body.appendChild(iframe);
			}
			iframe.src = url;
		};

		/**
		 * Destroys the component.
		 * 
		 * @memberOf expcat.plugins.TextFileExporter#
		 * @public
		 */

		var destroy = function() {
			divContainer.unbind();
		};

		// Initialises the component.
		init();

		/* Public API returned by the constructor */
		return {
			destroy : destroy
		};
	};

	/* Prototype methods and properties */
	TextFileExporter.prototype = {
		constructor : TextFileExporter
	};

	return TextFileExporter;
}());
