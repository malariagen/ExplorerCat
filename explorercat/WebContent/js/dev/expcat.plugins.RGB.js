expcat.namespace("expcat.plugins.RGB");

/**
 * @ignore
 */

expcat.plugins.RGB = (function() {
	/**
	 * An object representing RGB colour.
	 * 
	 * @param red
	 *            {Integer} Integer value between 0-255 representing RGB's red
	 *            colour.
	 * @param green
	 *            {Integer} Integer value between 0-255 representing RGB's green
	 *            colour.
	 * @param blue
	 *            {Integer} Integer value between 0-255 representing RGB's blue
	 *            colour.
	 * @name expcat.plugins.RGB
	 * @constructor
	 * @public
	 * @author Dushyanth Jyothi - ExplorerCat Project.
	 */
	var RGB = function(r, g, b) {
		var red = r;
		var green = g;
		var blue = b;

		/**
		 * Private method to set colours between 0 and 255
		 * 
		 * @memberOf expcat.plugins.RGB#
		 * @private
		 * @ignore
		 */
		var setColor = function(color) {
			color = Math.round(color);
			if (!isInteger(color)) {
				throw new Error("RGB Color is not Integer: " + color);
			}

			if (color < 0)
				color = 0;
			else if (color > 255)
				color = 255;
			return color;
		};

		/**
		 * Private method to check if given value is an integer.
		 * 
		 * @memberOf expcat.plugins.RGB#
		 * @private
		 * @ignore
		 */
		var isInteger = function(value) {
			if ((parseFloat(value) == parseInt(value)) && !isNaN(value)) {
				return true;
			} else {
				return false;
			}

		};

		/**
		 * Private method to initialise the object
		 * 
		 * @memberOf expcat.plugins.RGB#
		 * @private
		 * @ignore
		 */
		var init = function() {
			red = setColor(red);
			green = setColor(green);
			blue = setColor(blue);
		};

		/**
		 * Gets red colour of RGB.
		 * 
		 * @return {Integer} Integer representing RGB red colour.
		 * @memberOf expcat.plugins.RGB#
		 * @public
		 */
		var getRed = function() {
			return red;
		};

		/**
		 * Gets green colour of RGB.
		 * 
		 * @return {Integer} Integer representing RGB green colour.
		 * @memberOf expcat.plugins.RGB#
		 * @public
		 */
		var getGreen = function() {
			return green;
		};

		/**
		 * Gets blue colour of RGB.
		 * 
		 * @return {Integer} Integer representing RGB blue colour.
		 * @memberOf expcat.plugins.RGB#
		 * @public
		 */
		var getBlue = function() {
			return blue;
		};

		/**
		 * Gets the CSS friendly RGB colour to be used with HTML style.
		 * 
		 * @return {String} CSS ready RGB colour.
		 * @memberOf expcat.plugins.RGB#
		 * @public
		 */
		var getCssRGB = function() {
			return "rgb(" + red + "," + green + "," + blue + ")";
		};

		init();

		/* Public API returned by the constructor */
		return {
			getCssRGB : getCssRGB,
			getRed : getRed,
			getGreen : getGreen,
			getBlue : getBlue
		};
	};

	RGB.prototype = {
		constructor : RGB
	};
	return RGB;
}());
