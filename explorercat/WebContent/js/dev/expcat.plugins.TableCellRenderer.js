expcat.namespace("expcat.plugins.TableCellRenderer");

/**
 * @ignore
 */

expcat.plugins.TableCellRenderer = (function() {

	/**
	 * Component responsible for providing different TableCellRenderer methods
	 * to transform ExtJS4 table's data. For example applying different colors,
	 * etc. See also ExtJS4 related components:
	 * http://docs.sencha.com/ext-js/4-0/#!/api/Ext.grid.column.Column-cfg-TableCellRenderer
	 * 
	 * @name expcat.plugins.TableCellRenderer
	 * @constructor
	 * @public
	 * @author Dushyanth Jyothi - ExplorerCat Project.
	 */

	var TableCellRenderer = function() {

		// Aliases
		var RGB = expcat.plugins.RGB;
		var Gradient = expcat.plugins.RGB.Gradient;

		/**
		 * 
		 * Method that will add appropriate HTML code to show string colors
		 * 
		 * @memberOf expcat.plugins.TableCellRenderer#
		 * @public
		 */

		var applyStringColorer = function(rendererItem, value) {
			if (rendererItem.colors[(value + "")]) {
				return rendererItem.colors[(value + "")];
			}
		};

		/**
		 * Method to replace string tokens
		 * 
		 * @memberOf expcat.plugins.TableCellRenderer#
		 * @public
		 */

		var applyStringReplacer = function(rendererItem, value) {
			for (j = 0; j < rendererItem.stringsToReplace.length; ++j) {
				if (rendererItem.stringsToReplace[j] === (value + "")) {
					value = rendererItem.replacementStrings[j];
				}
			}
			return value;
		};

		/**
		 * Method to strip array to string
		 * 
		 * @memberOf expcat.plugins.TableCellRenderer#
		 * @public
		 */

		var applyArrayStripper = function(value) {
			return ((value + "").replace("[", "").replace("]", "").replace(
					/\"/g, " ").replace(/,/g, " , "));
		};

		/**
		 * Method that will add appropriate HTML code to show column colors
		 * 
		 * @memberOf expcat.plugins.TableCellRenderer#
		 * @public
		 */

		var applyBackgroundColor = function(value, startLimit, endLimit, startColor,
				endColor) {
			if ((startLimit != undefined) && (endLimit != undefined)
					&& (startColor != undefined) && (endColor != undefined)) {
				var gradient = new Gradient(new RGB(startColor[0],
						startColor[1], startColor[2]), new RGB(endColor[0],
						endColor[1], endColor[2]), startLimit, endLimit);
				return gradient.getRGB(value).getCssRGB();
			}
		};

		/**
		 * Method that will add appropriate HTML code to show tooltip
		 * 
		 * @memberOf expcat.plugins.TableCellRenderer#
		 * @public
		 */

		var applyTooltip = function(value) {
			return "data-qtip='" + value + "'";
		};

		/**
		 * Method that will add Links to PlasmoDB, GeneDB
		 * 
		 * @memberOf expcat.plugins.TableCellRenderer#
		 * @public
		 */

		var addLinks = function(value) {
			var plasmoDBLink = '<a href="http://v8-2.plasmodb.org/plasmo/showRecord.do?name=GeneRecordClasses.GeneRecordClass&project_id=&primary_key='
					+ value + '" target="_blank"> PlasmoDB </a>';
			var geneDBLink = '<a href="http://www.genedb.org/Query/quickSearch?pseudogenes=true&product=true&allNames=true&x=46&y=20&taxons=Pfalciparum&searchText='
					+ value + '" target="_blank"> GeneDB </a>';
			value = plasmoDBLink + " &nbsp; | &nbsp; " + geneDBLink;
			return value;
		};

		/* Public API returned by the constructor */
		return {
			applyTooltip : applyTooltip,
			applyBackgroundColor : applyBackgroundColor,
			applyArrayStripper : applyArrayStripper,
			applyStringReplacer : applyStringReplacer,
			applyStringColorer : applyStringColorer,
			addLinks : addLinks
		};
	};

	/* Prototype methods and properties */
	TableCellRenderer.prototype = {
		constructor : TableCellRenderer
	};
	return TableCellRenderer;
}());