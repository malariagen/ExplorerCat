expcat.namespace("expcat.plugins.DataTable");

/**
 * @ignore
 */

expcat.plugins.DataTable = (function() {

	/**
	 * Component responsible for creating ExtJS4 Table. See also ExtJS4 related
	 * components: http://docs.sencha.com/ext-js/4-0/#!/api/Ext.grid.Panel
	 * 
	 * @param cqlConnector
	 *            {CQLConnector} The configured CQL connector that will be used
	 *            to connect with the server.
	 * @param cqlQuery
	 *            {String} cqlQuery to be executed.
	 * @param tableConfiguration
	 *            {{Object}} JSON object providing configurable table options.
	 * 
	 * @name expcat.plugins.DataTable
	 * @constructor
	 * @public
	 * @author Dushyanth Jyothi - ExplorerCat Project.
	 */

	var Table = function(connector, query, tableConfig, finalCallBack) {

		var cqlConnector = connector;
		var cqlQuery = query;
		var tableConfiguration = tableConfig;

		var width = tableConfiguration.width || 600;
		var height = tableConfiguration.height || 600;
		var renderTo = tableConfiguration.renderTo;
		var columnOptions = tableConfiguration.columnOptions || [];
		var groupOptions = tableConfiguration.groupOptions || [];

		var queryConfiguration = {};
		var tableColumns = [];
		var tableData = [];
		var tableObj = null;

		var sEcho = 0;
		var iSortingCols = [];
		var iSortCol_0 = [];
		var sSortDir_0 = false;
		var iDisplayStart = 0;
		var iDisplayLength = 0;
		var fnServerDataCallback;

		// Constants

		var ARRAY_STRIPPER_RENDERER = 'arrayStripper';
		var STRING_REPLACER_RENDERER = 'stringReplacer';
		var STRING_COLORER_RENDERER = 'stringColorer';
		var COLOR_RENDERER = 'colorRenderer';
		var ADD_LINKS = 'addLinks';

		var columnRenderHandler = function(oObj, sVal) {
			var columnConfiguration = getColumnConfigurationByAlias(oObj.oSettings.aoColumns[oObj.iDataColumn].sTitle);
			var renderers = columnConfiguration.renderers;
			var value = sVal;
			var tableCellRendererObj = new expcat.plugins.TableCellRenderer();
			for ( var i = 0; i < renderers.length; i++) {

				if (renderers[i].name === STRING_REPLACER_RENDERER) {
					value = tableCellRendererObj.applyStringReplacer(
							renderers[i], sVal);
				}
				if (renderers[i].name === ARRAY_STRIPPER_RENDERER) {
					value = tableCellRendererObj.applyArrayStripper(sVal);
				}

				if (renderers[i].name === ADD_LINKS) {
					value = tableCellRendererObj.addLinks(value);
				}
			}
			return value;
		};

		var fnCreatedCellHandler = function(nTd, sData, oData, iRow, iCol) {
			var oTable = $('#' + tableConfiguration.tableDomId).dataTable();
			var oSettings = oTable.fnSettings();
			var columnConfiguration = getColumnConfigurationByAlias(oSettings.aoColumns[iCol].sTitle);
			var renderers = columnConfiguration.renderers;
			var tableCellRendererObj = new expcat.plugins.TableCellRenderer();
			for ( var i = 0; i < renderers.length; i++) {
				if (renderers[i].name === COLOR_RENDERER) {
					bgcolor = tableCellRendererObj.applyBackgroundColor(sData,
							renderers[i].startLimit, renderers[i].endLimit,
							renderers[i].startColor, renderers[i].endColor);
					$(nTd).css('background-color', bgcolor);
				}

				if (renderers[i].name === STRING_COLORER_RENDERER) {
					var stringColor = tableCellRendererObj.applyStringColorer(
							renderers[i], sData);
					$(nTd).css('color', stringColor);
					$(nTd).css('font-weight', 'bold');
				}
			}
		};

		var fnRenderHandler = function(oObj, sVal) {
			var columnConfiguration = getColumnConfigurationByAlias(oObj.oSettings.aoColumns[oObj.iDataColumn].sTitle);
			var renderers = columnConfiguration.renderers;
			var value = sVal;
			var tableCellRendererObj = new expcat.plugins.TableCellRenderer();
			for ( var i = 0; i < renderers.length; i++) {
				if (renderers[i].name === STRING_REPLACER_RENDERER) {
					value = tableCellRendererObj.applyStringReplacer(
							renderers[i], sVal);
				}
				if (renderers[i].name === ARRAY_STRIPPER_RENDERER) {
					value = tableCellRendererObj.applyArrayStripper(sVal);
				}
				if (renderers[i].name === ADD_LINKS) {
					value = tableCellRendererObj.addLinks(value);
				}
			}
			return value;
		};

		var getColumnConfigurationByAlias = function(columnAlias) {
			for ( var i = 0; i < columnOptions.length; i++) {
				if (columnOptions[i].alias === columnAlias) {
					return columnOptions[i];
				}
			}
		};

		var createTableColumns = function() {
			var tableColumns = [];
			var columns = queryConfiguration.header.columns;
			for ( var i = 0; i < columns.length; i++) {
				var columnConfiguration = getColumnConfigurationByAlias(columns[i].alias);
				var renderers = columnConfiguration.renderers;

				var column = {
					sName : columnConfiguration.name,
					sTitle : columnConfiguration.alias,
					bVisible : (columnConfiguration.hide ? false : true),
					bSortable : (columnConfiguration.sort ? true : false),
					sWidth : columnConfiguration.width + 'px',
					fnRender : fnRenderHandler,
					fnCreatedCell : fnCreatedCellHandler
				};
				tableColumns.push(column);
			}
			return tableColumns;
		};

		var constructTableStructure = function() {
			var table = $('<table cellpadding="0" cellspacing="0" border="0" class="display" id="'
					+ tableConfiguration.tableDomId + '" >');
			var thead = $("<thead></thead>");
			var tbody = $("<tbody></tbody>");
			var tfoot = $("<tfoot></tfoot>");
			var headGroupTr = $("<tr></tr>");
			var headColumnTr = $("<tr></tr>");
			var footGroupTr = $("<tr></tr>");
			var footColumnTr = $("<tr></tr>");

			for ( var i = 0; i < groupOptions.length; i++) {
				var groupColumns = groupOptions[i].columns;

				var groupTh = '<th colspan="' + groupColumns.length
						+ '" class="ui-state-default">' + groupOptions[i].name
						+ '</th>';

				headGroupTr.append(groupTh);
				footGroupTr.append(groupTh);

				for ( var j = 0; j < groupColumns.length; j++) {
					headColumnTr.append('<th>' + groupColumns[j] + '</th>');
					footColumnTr.append('<th>' + groupColumns[j] + '</th>');
				}
			}
			thead.append(headGroupTr);
			thead.append(headColumnTr);
			tfoot.append(footColumnTr);
			// tfoot.append(footGroupTr);

			table.append(thead);
			table.append(tbody);
			table.append(tfoot);
			$('#' + tableConfiguration.tableContainer).append(table);
		};

		var getAoDataValue = function(aoData, key) {
			for ( var j = 0; j < aoData.length; j++) {
				if (aoData[j].name === key) {
					return aoData[j].value;
				}
			}
		};

		var getResultDataCallback = function(response) {
			if (response.returnCode === 0) {
				tableData = [];
				iTotalRecords = queryConfiguration.header.numRows;
				iTotalDisplayRecords = queryConfiguration.header.numRows;

				for ( var i = 0; i < response.resultData.rows.length; i++) {
					tableData.push(response.resultData.rows[i].values);
				}
				var dtReadyData = {
					"sEcho" : sEcho,
					"iTotalRecords" : iTotalRecords,
					"iTotalDisplayRecords" : iTotalRecords,
					"aaData" : tableData
				};
				fnServerDataCallback(dtReadyData);
			} else {
				if (typeof finalCallBack === 'function') {
					finalCallBack(response.errorMessage);
				}
				throw new Error(response.errorMessage);
			}
		};

		var getResultData = function(ticketNumber, hashCode, numberOfRows,
				offset) {
			cqlConnector.getResultData(ticketNumber, hashCode, numberOfRows,
					offset, getResultDataCallback);
		};

		var sortResults = function(ticketNumber, hashCode, sortingPropertyName,
				sortInDescendantOrder) {
			var sortResultsCallback = function(response) {
				if (response.returnCode === 0) {
					getResultData(queryConfiguration.ticketNumber,
							queryConfiguration.hashCode, iDisplayLength,
							iDisplayStart);
				} else {
					if (typeof finalCallBack === 'function') {
						finalCallBack('<p id="error">' + response.errorMessage
								+ '</p>');
					}
					throw new Error(response.errorMessage);
				}
			};
			/**
			 * Hard Coded to achive Chromosome numerical sorting, should be
			 * removed*
			 */
			if (sortingPropertyName === 'Chromosome') {
				sortingPropertyName = 'Chr';
			}
			cqlConnector.sortResultData(ticketNumber, hashCode,
					sortingPropertyName, sortInDescendantOrder,
					sortResultsCallback);
		};

		var fnServerDataHandler = function(sSource, aoData, fnCallback) {
			sEcho = getAoDataValue(aoData, 'sEcho');
			iSortingCols = getAoDataValue(aoData, 'iSortingCols');
			iSortCol_0 = getAoDataValue(aoData, 'iSortCol_0');
			sSortDir_0 = getAoDataValue(aoData, 'sSortDir_0');
			iDisplayStart = getAoDataValue(aoData, 'iDisplayStart');
			iDisplayLength = getAoDataValue(aoData, 'iDisplayLength');
			fnServerDataCallback = fnCallback;
			if (iSortingCols > 0 && sEcho > 1) {
				sortResults(queryConfiguration.ticketNumber,
						queryConfiguration.hashCode,
						queryConfiguration.header.columns[iSortCol_0].name,
						sSortDir_0 != 'asc');
			} else {
				getResultData(queryConfiguration.ticketNumber,
						queryConfiguration.hashCode, iDisplayLength,
						iDisplayStart);
			}
		};

		var fnInitCompleteHandler = function() {
			if (typeof finalCallBack === 'function') {
				finalCallBack();
			}
		};

		var createDataTable = function() {
			tableColumns = createTableColumns();
			constructTableStructure();
			tableObj = $('#' + tableConfiguration.tableDomId).dataTable({
				"aoColumns" : tableColumns,
				"bProcessing" : true,
				"bServerSide" : true,
				"fnServerData" : fnServerDataHandler,
				"sScrollX" : "100%",
				"bAutoWidth" : true,
				"bJQueryUI" : true,
				"bFilter" : false,
				"bPaginate" : true,
				"sPaginationType" : "full_numbers",
				"fnInitComplete" : fnInitCompleteHandler
			});
		};

		var setupQuery = function() {
			var setupQueryCallback = function(response) {
				if (response.returnCode === 0) {
					queryConfiguration = response;
					if (response.header.numRows && response.header.numRows > 0) {
						createDataTable();
					} else {
						finalCallBack('<h2>No results found</h2>');
					}
				} else {
					if (typeof finalCallBack === 'function') {
						finalCallBack('<p id="error">' + response.errorMessage
								+ '</p>');

					}
					throw new Error(response.errorMessage);
				}
			};

			var parameters = {};
			cqlConnector.setupQuery(cqlQuery, setupQueryCallback, parameters);
		};

		/**
		 * Destroys Table, Help window and clip window objects
		 * 
		 * @memberOf expcat.plugins.DataTable#
		 * @private
		 * @ignore
		 */

		var destroy = function() {
			if ((typeof tableObj !== "undefined") && (tableObj !== null)) {
				tableObj.fnDestroy();
				$('#' + tableConfiguration.tableContainer).empty();
				// Still doesnt completely work, fix it by set to null
				tableObj = null;
			}
		};

		var getTable = function() {
			return tableObj;
		};

		/**
		 * Gets the ticket and hash code for the query expected by the provider.
		 * 
		 * @memberOf expcat.plugins.DataTable#
		 * @public
		 */

		var getCurrentQueryTicketAndHashCode = function() {
			if (queryConfiguration) {
				return {
					"ticket" : queryConfiguration.ticketNumber,
					"hashCode" : queryConfiguration.hashCode
				};
			} else {
				return null;
			}
		};

		/**
		 * Private method to initialise the object.
		 * 
		 * @memberOf expcat.plugins.DataTable#
		 * @private
		 * @ignore
		 */

		var init = function() {
			destroy();
			setupQuery();
		};

		init();
		/* Public API returned by the constructor */
		return {
			destroy : destroy,
			getTable : getTable,
			getCurrentQueryTicketAndHashCode : getCurrentQueryTicketAndHashCode
		};
	};

	/* Prototype methods and properties */
	Table.prototype = {
		constructor : Table
	};
	return Table;
}());
