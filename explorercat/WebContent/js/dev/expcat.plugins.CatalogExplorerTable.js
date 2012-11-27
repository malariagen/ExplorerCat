expcat.namespace("expcat.plugins.CatalogExplorerTable");

/**
 * @ignore
 */

expcat.plugins.CatalogExplorerTable = (function() {

	/**
	 * Component responsible for creating Catalog Explorer Table to display cql
	 * query results.
	 * 
	 * @constructor
	 * @name expcat.plugins.CatalogExplorerTable
	 * @public
	 * @author Dushyanth Jyothi - ExplorerCat project
	 */

	var CatalogExplorerTable = function(cqlConnector, cqlQuery,
			tableConfiguration) {

		var cqlConnector = cqlConnector;
		var cqlQuery = cqlQuery;
		var tableConfiguration = tableConfiguration;

		// Alias to expcat.plugins.TableExplorer
		var TableExplorer = expcat.plugins.TableExplorer;

		// CatalogExplorer specific table to display cql query results, using
		// instance of expcat.plugins.TableExplorer
		var table = null;

		/**
		 * Housekeeping method.
		 * 
		 * @memberOf expcat.plugins.CatalogExplorerTable#
		 * @public
		 */
		var destroy = function() {
			if ((typeof table !== "undefined") && (table !== null)) {
				table.destroy();				
			}
			table = null;
		};

		/**
		 * Responsible for Initialises the object, and constructing query result
		 * table using TableExplorer plugin #expcat.plugins.TableExplorer.
		 * 
		 * @memberOf expcat.plugins.CatalogExplorerTable#
		 * @private
		 * @ignore
		 */

		var init = function() {
			destroy();
			table = new expcat.plugins.TableExplorer(cqlConnector, cqlQuery,
					tableConfiguration);
		};

		/**
		 * Gets the TableExplorer object that represents the table.
		 * 
		 * @memberOf expcat.plugins.CatalogExplorerTable#
		 * @public
		 */
		var getCatalogExplorerTable = function() {
			return table;
		};

		/**
		 * Private method that initialises the object.
		 * 
		 * @memberOf expcat.plugins.CatalogExplorerTable#
		 * @private
		 * @ignore
		 */
		init();

		/* Public API returned by the constructor */
		return {
			getCatalogExplorerTable : getCatalogExplorerTable,
			destroy : destroy
		};
	};

	CatalogExplorerTable.prototype = {
		constructor : CatalogExplorerTable
	};
	return CatalogExplorerTable;
}());
