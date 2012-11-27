expcat.namespace("expcat.plugins.TableExplorer.DataProvider");

/**
 * @ignore
 */

expcat.plugins.TableExplorer.DataProvider = (function() {

	// Aliases
	var RGB = expcat.plugins.RGB;
	var Gradient = expcat.plugins.RGB.Gradient;
	var ToolTipRenderer = 'tooltip';
	var ColorRenderer = 'colorRenderer';
	var StringReplacer = 'stringReplacer';
	var StringColorer = 'stringColorer';
	var ArrayStripper = 'arrayStripper';

	/**
	 * Data provider for elements of the ExtJS framework. This object will
	 * manage the connection with the server providing a configured store to be
	 * used by these elements.
	 * 
	 * @param cqlConnector
	 *            {CQLConnector} The configured CQL connector that will be used
	 *            to connect with the server.
	 * @param pageSize
	 *            {Integer} Number of rows to be fetched from server for each
	 *            request.
	 * @param columnOptions
	 *            {{Object}} JSON object providing configurable table column
	 *            options.
	 * @name expcat.plugins.TableExplorer.DataProvider
	 * @constructor
	 * @public
	 * @author Jacob Almagro - ExplorerCat Project.
	 */

	var DataProvider = function(cqlConnector, pageSize, columnOptions) {

		var connector = cqlConnector;
		var pageSize = pageSize;
		var columnOptions = columnOptions;

		var currentCQLQuery = null;
		var currentQueryConfiguration = null;
		var lastRetrievedData = null;

		// Ext-js4 components.
		var proxy;
		var store;

		/**
		 * Executes the given query. After this method has been called the data
		 * store can be safely retrieved to configure Ext-js components.
		 * 
		 * @param cqlQuery
		 *            {String} The CQL query to be executed.
		 * @param setupCallback
		 *            {Function} Callback that will be executed after the
		 *            session has been set up.
		 * @param parameters
		 *            {Object} Configuration object that conatins plug-in/task
		 *            parameters.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @public
		 */
		var executeQuery = function(cqlQuery, setupCallback, parameters) {

			// We need to wrap the processing function in order to add
			// the call to the external callback.
			var wrappedCallback = function(response) {
				processSetupResponse(response);
				if (typeof setupCallback === "function") {
					setupCallback();
				}
			};
			currentCQLQuery = cqlQuery;
			connector.setupQuery(cqlQuery, wrappedCallback, parameters);
		};

		/**
		 * Gets an array of object with the definitions of the data columns for
		 * the current query.
		 * 
		 * @return {[Object]} An array of object with the definition {name,
		 *         alias, desc, type} for each column.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @public
		 */
		var getColumnDefinitions = function() {
			return currentQueryConfiguration.header.columns;
		};

		/**
		 * Gets the store that can be used by Ext-js components to retrieve
		 * data. Notice this method has to be used after the executeQuery method
		 * has been called.
		 * 
		 * @return {Ext.data.Store} The data store for the result data.
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @public
		 */
		var getDataStore = function() {
			return store;
		};

		/**
		 * Gets the name of the result, to be used as table title.
		 * 
		 * @return {String} resultName.
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @public
		 */
		var getResultName = function() {
			return currentQueryConfiguration.header.resultName;
		};

		/**
		 * Destroys the component releasing the connection.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @public
		 */
		var destroy = function() {
			connector.releaseQuery(currentQueryConfiguration.ticketNumber,
					currentQueryConfiguration.hashCode);
		};

		/**
		 * Converts Column types to ExtJS types
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @public
		 */
		var getExtType = function(columnType) {
			var types = Ext.data.Types;
			if (columnType == 'INTEGER')
				columnType = types.INT.type;
			else if (columnType == 'REAL')
				columnType = types.FLOAT.type;
			else if (columnType == 'STRING')
				columnType = types.STRING.type;
			else
				columnType = types.AUTO.type;

			return columnType;
		};

		/**
		 * Gets the ticket and hash code for the query exected by the provider.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @public
		 */

		var getCurrentQueryTicketAndHashCode = function() {
			if (currentQueryConfiguration)
				return {
					"ticket" : currentQueryConfiguration.ticketNumber,
					"hashCode" : currentQueryConfiguration.hashCode
				};
			else
				return null;
		};

		/**
		 * Gets the fields returned by the current query.
		 * 
		 * @return {[Object]} An array of objects representing the field {name,
		 *         type}
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var getFieldsFromCurrentQuery = function() {
			var fields = [];
			var columns = getColumnDefinitions();
			var i;
			for (i = 0; i < columns.length; ++i) {
				fields.push({
					name : columns[i].alias,
					type : getExtType(columns[i].type)
				});
			}

			return fields;
		};

		/**
		 * Gets the render options for a column from columnOptions.
		 * 
		 * @return {[Object]} An array of objects representing the render
		 *         options
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var getColumnRendererOptions = function(columnName) {
			for ( var i = 0; i < columnOptions.length; i++) {
				if (columnOptions[i].name === columnName) {
					return columnOptions[i].renderers;
				}
			}
		};

		/**
		 * Responsible for providing tooltip and color options for each column,
		 * this method is bound with ExtJS grid panel column 'renderer' option.
		 * see getTableheaders method
		 * 
		 * @return {Object} Grid panel column value with color/tootilp metada
		 *         associated
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var columnRenderer = function(value, metaData, record, rowIndex,
				colIndex, store, view) {
			var i, j;
			var columnName = view.getHeaderCt().getHeaderAtIndex(colIndex).dataIndex;
			var renderers = getColumnRendererOptions(columnName);
			metaData.tdAttr = metaData.tdAttr || " ";

			if (typeof renderers !== "undefined") {
				for (i = 0; i < renderers.length; i++) {

					if (renderers[i].name === ToolTipRenderer) {
						metaData.tdAttr = "data-qtip='" + value + "'";
					}

					else if (renderers[i].name === ColorRenderer) {
						if ((renderers[i].startLimit != undefined)
								&& (renderers[i].endLimit != undefined)
								&& (renderers[i].startColor != undefined)
								&& (renderers[i].endColor != undefined)) {
							var gradient = new Gradient(new RGB(
									renderers[i].startColor[0],
									renderers[i].startColor[1],
									renderers[i].startColor[2]), new RGB(
									renderers[i].endColor[0],
									renderers[i].endColor[1],
									renderers[i].endColor[2]),
									renderers[i].startLimit,
									renderers[i].endLimit);
							metaData.tdAttr += ' style="background-color:'
									+ gradient.getRGB(value).getCssRGB() + ';"';
						}
					}

					else if (renderers[i].name === ArrayStripper) {
						value = ((value + "").replace("[", "").replace("]", "")
								.replace(/\"/g, " ").replace(/,/g, " , "));
					}

					else if (renderers[i].name === StringReplacer) {
						for (j = 0; j < renderers[i].stringsToReplace.length; ++j) {
							if (renderers[i].stringsToReplace[j] === (value + "")) {
								value = renderers[i].replacementStrings[j];
							}
						}
					}

					else if (renderers[i].name === StringColorer) {
						if (renderers[i].colors[(value + "")]) {
							metaData.tdAttr += ' style="color:'
									+ renderers[i].colors[(value + "")]
									+ '; font-weight: bold;"';
						}
					}
				}
			}
			return value;
		};

		/**
		 * Check column options to configure sorting.
		 * 
		 * @return {String} Column name
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var isColumnSortable = function(columnName) {
			for ( var i = 0; i < columnOptions.length; i++) {
				if (columnOptions[i].name === columnName) {
					return columnOptions[i].sort;
				}
			}
		};

		/**
		 * Check column options to configure width.
		 * 
		 * @return {Integer} Column width
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var getColumnWidth = function(columnName) {
			for ( var i = 0; i < columnOptions.length; i++) {
				if (columnOptions[i].name === columnName) {
					return columnOptions[i].width;
				}
			}
		};

		/**
		 * Check column options to get Column group.
		 * 
		 * @return {String} Column group
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var getColumnGroup = function(columnName) {
			for ( var i = 0; i < columnOptions.length; i++) {
				if (columnOptions[i].name === columnName) {
					return columnOptions[i].group;
				}
			}
		};

		/**
		 * Gets the ExtJS grid panel columns associated with renderer's.
		 * 
		 * @return {[Object]} An array of objects representing the columns
		 *         {header, width,dataIndex,sortable,renderer}
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var getTableheaders = function() {
			var tableHeaders = [];
			var columns = getColumnDefinitions();
			var i;

			var getGroupId = function(th, gtext) {
				for (j = 0; j < th.length; j++) {
					if (th[j].text === gtext) {
						return j;
					}
				}
			};

			for (i = 0; i < columns.length; ++i) {
				var options = {};
				options.text = columns[i].alias;
				options.dataIndex = columns[i].name;
				options.width = getColumnWidth(columns[i].name) || 100;
				options.sortable = isColumnSortable(columns[i].name);
				options.renderer = columnRenderer;
				options.desc = columns[i].desc;

				var group = getColumnGroup(columns[i].name);
				if ((typeof group !== "undefined") && (group !== null)) {
					var groupId = getGroupId(tableHeaders, group);
					if (typeof groupId !== "undefined") {
						tableHeaders[groupId].columns.push(options);
					} else {
						var groupOptions = {};
						groupOptions.text = group;
						groupOptions.columns = [];
						groupOptions.columns.push(options);
						tableHeaders.push(groupOptions);
					}
				} else {
					tableHeaders.push(options);
				}
			}
			// Invisible column is added for resizing purposes
			tableHeaders.push({
				text : 'DummyColumn',
				hideable : false,
				hidden : true,
				flex : 1
			});
			return tableHeaders;
		};

		/**
		 * Callback that processes the response of the setupQuery method
		 * (connector).
		 * 
		 * @param response
		 *            {Object} An object containing the response of the
		 *            setupQuery method.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var processSetupResponse = function(response) {
			if (response.returnCode === 0) {
				currentQueryConfiguration = response;
				defineDataModel();
				buildDataStore();
			} else
				throw new Error(response.errorMessage);
		};

		/**
		 * Callback that processes the response of the getResultData method
		 * (connector).
		 * 
		 * @param response
		 *            {Object} An object containing the response of the
		 *            getResultData method.
		 * @param completionCallback
		 *            {Function} The function in charge of notifying the Ext-js
		 *            components that the data has been updated.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var processGetResultDataResponse = function(response,
				completionCallback) {
			var dataRows;
			var rowValues;
			var i;

			if (response.returnCode === 0) {
				lastRetrievedData = [];
				dataRows = response.resultData.rows;
				for (i = 0; i < dataRows.length; ++i) {
					lastRetrievedData
							.push(buildModelInstance(dataRows[i].values));
				}
				if (typeof completionCallback === "function")
					completionCallback();
			} else
				throw new Error(response.errorMessage);
		};

		/**
		 * Builds an instance of ResultModel with the given values.
		 * 
		 * @return {ResultModel} An instance of ResultModel that contains the
		 *         given values.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var buildModelInstance = function(values) {
			var data = {};
			var columns = getColumnDefinitions();
			var i;
			for (i = 0; i < values.length; ++i) {
				data[columns[i].name] = values[i];
			}
			return new ResultModel(data);
		};

		/**
		 * Defines the data model to be used with the current query.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var defineDataModel = function() {
			var resultFields = getFieldsFromCurrentQuery();
			var currentModel;

			if (Ext.ModelManager.isRegistered('ResultModel')) {
				currentModel = Ext.ModelManager.getModel('ResultModel');
				currentModel.fields = resultFields;
				currentModel.proxy = buildCQLProxyForCurrentQuery();
			} else {
				Ext.define('ResultModel', {
					extend : 'Ext.data.Model',
					fields : resultFields,
					proxy : buildCQLProxyForCurrentQuery()
				});
			}
		};

		/**
		 * Builds a data store for the current query.
		 * 
		 * @return {Ext.data.Store} The store to be used with the current query.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var buildDataStore = function(rowsPerPage) {
			store = new Ext.data.Store({
				model : 'ResultModel',
				autoLoad : true,
				remoteSort : true,
				pageSize : pageSize
			});
			return store;
		};

		/**
		 * Builds a proxy instance for the current query.
		 * 
		 * @return {CQLProxy} A proxy instance that retrieves data from the EC
		 *         server.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var buildCQLProxyForCurrentQuery = function() {
			var proxyClass;

			if (Ext.ClassManager.get('CQLProxy')) {
				proxyClass = Ext.ClassManager.get('CQLProxy');
				proxyClass.read = readProxyHandler;
			} else {
				Ext.define('CQLProxy', {
					extend : 'Ext.data.proxy.Ajax',
					create : createProxyHandler,
					update : updateProxyHandler,
					destroy : destroyProxyHandler,
					read : readProxyHandler
				});
			}

			// Updates the handler to avoid variables linked to the old closure.
			proxy = new CQLProxy();
			proxy.read = readProxyHandler;
			proxy.create = createProxyHandler;
			proxy.update = updateProxyHandler;
			proxy.destroy = destroyProxyHandler;

			return proxy;
		};

		/**
		 * Handler for the create operation of the CQLProxy. Check documentation
		 * at http://docs.sencha.com/ext-js/4-0/#!/api/Ext.data.proxy.Proxy
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var createProxyHandler = function(operation, callback, scope) {
			// Nothing to do.
		};

		/**
		 * Handler for the update operation of the CQLProxy. Check documentation
		 * at http://docs.sencha.com/ext-js/4-0/#!/api/Ext.data.proxy.Proxy
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var updateProxyHandler = function(operation, callback, scope) {
			// Nothing to do.
		};

		/**
		 * Handler for the destroy operation of the CQLProxy. Check
		 * documentation at
		 * http://docs.sencha.com/ext-js/4-0/#!/api/Ext.data.proxy.Proxy
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var destroyProxyHandler = function(operation, callback, scope) {
			// Nothing to do.
		};

		/**
		 * Handler for the read operation of the CQLProxy. Check documentation
		 * at http://docs.sencha.com/ext-js/4-0/#!/api/Ext.data.proxy.Proxy
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var readProxyHandler = function(operation, callback, scope) {
			var thisProxy = this;
			var dataRequestCompletionCallback = function() {
				proxyOperationAsyncNotifier(operation, callback, thisProxy,
						scope);
			};

			var sortRequestCompletionCallback = function() {
				retrieveData(operation.start, scope.pageSize,
						dataRequestCompletionCallback);
			};

			if (operation.sorters.length > 0) {
				retrieveSortRequest(operation.start, scope.pageSize,
						sortRequestCompletionCallback, operation.sorters);
			} else {
				retrieveData(operation.start, scope.pageSize,
						dataRequestCompletionCallback);
			}

		};

		/**
		 * Function that will passed as an async callback and whose mission is
		 * to notify the Ext-js components when the data loading/processing is
		 * completed (via the Operation object).
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var proxyOperationAsyncNotifier = function(operation, callback, proxy,
				scope) {
			operation.resultSet = new Ext.data.ResultSet({
				records : lastRetrievedData,
				total : currentQueryConfiguration.header.numRows,
				loaded : true
			});

			operation.setSuccessful();
			operation.setCompleted();

			if (typeof callback == "function") {
				callback.call(scope || proxy, operation);
			}
		};

		/**
		 * Gets data from the server, updating the set of retrieved rows.
		 * 
		 * @param startingRow
		 *            {Integer} The first row to be retrieved.
		 * @param numRows
		 *            {Integer} The number of rows to be retrieved.
		 * @param callback
		 *            {Object} The method to callback.
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var retrieveData = function(startingRow, numRows, completionCallback) {
			var ticket = currentQueryConfiguration.ticketNumber;
			var hash = currentQueryConfiguration.hashCode;
			var offset = startingRow;

			var wrappendHandler = function(response) {
				processGetResultDataResponse(response, completionCallback);
			};
			connector.getResultData(ticket, hash, numRows, offset,
					wrappendHandler);
		};

		/**
		 * Gets data from the server, updating the set of retrieved rows.
		 * 
		 * @param startingRow
		 *            {Integer} The first row to be retrieved.
		 * @param numRows
		 *            {Integer} The number of rows to be retrieved.
		 * @param callback
		 *            {Object} The method to callback.
		 * @param sorters
		 *            {Object} Array providing sorters.
		 * 
		 * @memberOf expcat.plugins.TableExplorer.DataProvider#
		 * @private
		 * @ignore
		 */
		var retrieveSortRequest = function(startingRow, numRows,
				completionCallback, sorters) {
			var ticket = currentQueryConfiguration.ticketNumber;
			var hash = currentQueryConfiguration.hashCode;
			var offset = startingRow;

			var wrappendHandler = function(response) {
				if (response.returnCode === 0) {
					completionCallback();
				} else
					throw new Error(response.errorMessage);
			};
			connector.sortResultData(ticket, hash, sorters[0].property,
					sorters[0].direction != 'ASC', wrappendHandler);
		};

		/* Public API returned by the constructor */
		return {
			executeQuery : executeQuery,
			getColumnDefinitions : getColumnDefinitions,
			getDataStore : getDataStore,
			destroy : destroy,
			getResultName : getResultName,
			getTableheaders : getTableheaders,
			getCurrentQueryTicketAndHashCode : getCurrentQueryTicketAndHashCode
		};
	};

	/* Prototype methods and properties */
	DataProvider.prototype = {
		constructor : DataProvider
	};

	return DataProvider;

}());
