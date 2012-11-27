expcat.namespace("expcat.plugins.TableExplorer");

/**
 * @ignore
 */

expcat.plugins.TableExplorer = (function() {
	
	/**
	 * Independent component responsible for all actions related to
	 * TableExplorer plugin, display cql query results in a table using Ext JS4
	 * table and CQL query.
	 * 
	 * @param tableConfiguration
	 *            {{Object}} JSON object providing configurable table options.
	 * @param cqlConnector
	 *            {CQLConnector} The configured CQL connector that will be used
	 *            to connect with the server.
	 * @param cqlQuery
	 *            {String} cqlQuery to be executed.
	 * @name expcat.plugins.TableExplorer
	 * @constructor
	 * @public
	 * @author Jacob Almagro - ExplorerCat Project.
	 */

	var TableExplorer = function(cqlConnector, cqlQuery, tableConfiguration) {
		
		// Alias
		var DataProvider = expcat.plugins.TableExplorer.DataProvider;
		
		var connector = cqlConnector;
		var query = cqlQuery;

		var width = tableConfiguration.width || 600;
		var height = tableConfiguration.height || 600;
		var renderTo = tableConfiguration.renderTo;
		var columnOptions = tableConfiguration.columnOptions || {};
		var pageSize = tableConfiguration.pageSize || 25;

		var provider = null;
		var table = null;

		/**
		 * Housekeeping method.
		 * 
		 * @memberOf expcat.plugins.TableExplorer#
		 * @public
		 */
		var destroy = function() {
			if (provider !== null) {
				provider.destroy();
			}
			if (table !== null) {
				table.destroy();
			}
		};

		/**
		 * Creates a new grid panel.
		 * 
		 * @memberOf expcat.plugins.TableExplorer#
		 * @private
		 * @ignore
		 */
		var createGridPanel = function() {
			// Enable tooltips
			Ext.QuickTips.init();

			
			// setup the state provider saved to a cookie
			Ext.state.Manager.setProvider(Ext
					.create('Ext.state.CookieProvider'));

			table = Ext.create('Ext.grid.Panel', {
				renderTo : renderTo || Ext.getBody(),
				store : provider.getDataStore(),
				width : width,
				height : height,
				columnLines : true,
				title : provider.getResultName() || 'Query Results',
				columns : provider.getTableheaders(),
				stateful : true,
				stateId : 'catalogExplorerTableGrid',
				autoDestroy : true,
				dockedItems : [ {
					xtype : 'pagingtoolbar',
					store : provider.getDataStore(),
					dock : 'bottom',
					displayInfo : true,
					inputItemWidth : 100,
					listeners : {
						change : function(ptb, pageData, eOpts) {
							if (pageData.total == 0) {
								ptb.setDisabled(true);
							}
						}
					}
				} ],
				viewConfig : {
					splitHandleWidth : 100,
					forceFit : true
				},
				listeners : { // For header Tooltip
					afterrender : function(thisTable, eOpts) {
						var headerCt = thisTable.headerCt;						
						thisTable.tip = Ext.create('Ext.tip.ToolTip', {
							target : headerCt.el,
							delegate : ".x-column-header",
							trackMouse : true,
							renderTo : renderTo || Ext.getBody(),
							listeners : {
								beforeshow : function(tip) {
									var c = headerCt.down('gridcolumn[id='
											+ tip.triggerElement.id + ']');
									if (c && c.desc) {
										tip.update(c.desc);
									} else {
										return false;
									}

								}
							}
						});
					}
				}

			});
			return table;
		};


		/**
		 * Gets the GridPanel object that represents the table.
		 * 
		 * @memberOf expcat.plugins.TableExplorer#
		 * @public
		 */
		var getTable = function() {
			return table;
		};

		/**
		 * Private method to initialise the object.
		 * 
		 * @memberOf expcat.plugins.TableExplorer#
		 * @private
		 * @ignore
		 */
		var init = function() {
			destroy();
			provider = new expcat.plugins.TableExplorer.DataProvider(connector, pageSize, columnOptions);
			provider.executeQuery(query, createGridPanel);
		};

		
		/**
		 * Gets the ticket and hash code for the query executed by the table.
		 * 
		 * @memberOf expcat.plugins.TableExplorer#
		 * @public
		 */
		
		var getCurrentQueryTicketAndHashCode = function () {
			if(provider)
				return provider.getCurrentQueryTicketAndHashCode();
			else
				return null;
		};
		
		init();

		/* Public API returned by the constructor */
		return {
			getTable : getTable,
			destroy : destroy,
			getCurrentQueryTicketAndHashCode : getCurrentQueryTicketAndHashCode
		};

	};
	/* Prototype methods and properties */
	TableExplorer.prototype = {
		constructor : TableExplorer		
	};

	return TableExplorer;

}());
