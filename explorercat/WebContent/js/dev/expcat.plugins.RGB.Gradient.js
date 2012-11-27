expcat.namespace("expcat.plugins.RGB.Gradient");

/**
 * @ignore
 */

expcat.plugins.RGB.Gradient = (function() {

	// Aliases
	var RGB = expcat.plugins.RGB;

	/**
	 * An object responsible for generating gradient RGB colour using a start
	 * colour, Integer start limit and end colour, Integer end limit values.
	 * 
	 * @param startRGB
	 *            {RGB} RGB object to be used as start colour value.
	 * @param endRGB
	 *            {RGB} RGB object to be used as end colour value.
	 * @param startLimit
	 *            {Integer} Integer value to be used as start limit.
	 * @param endLimit
	 *            {Integer} Integer value to be used as end limit..
	 * @name expcat.plugins.RGB.Gradient
	 * @constructor
	 * @public
	 * @author Dushyanth Jyothi - ExplorerCat Project.
	 */
	var Gradient = function(startRGB, endRGB, startLimit, endLimit) {
		var startRGB = startRGB;
		var endRGB = endRGB;
		var startLimit = startLimit;
		var endLimit = endLimit;

		/**
		 * Private method to get normalised colour value.
		 * 
		 * @memberOf expcat.plugins.RGB.Gradient#
		 * @private
		 * @ignore
		 */
		var getNormalisedColor = function(color) {
			return color / 255.0;
		};

		/**
		 * Gets the new gradient RGB colour for the given value.
		 * 
		 * @return {RGB} new gradient RGB colour.
		 * @memberOf expcat.plugins.RGB.Gradient#
		 * @public
		 */
		var getRGB = function(value) {
			value = parseFloat(value, 10);
			var range = Math.abs(endLimit - startLimit);
			var absStartLimit = Math.abs(startLimit);
			var gradientRGB = null;

			if (value >= endLimit) {
				gradientRGB = endRGB;
			}
			if (value <= startLimit) {
				gradientRGB = startRGB;
			} else {
				var t = (value ? (value + absStartLimit) / range : 0);

				var red = (getNormalisedColor(endRGB.getRed()) - getNormalisedColor(startRGB
						.getRed()))
						* t + getNormalisedColor(startRGB.getRed());
				var green = (getNormalisedColor(endRGB.getGreen()) - getNormalisedColor(startRGB
						.getGreen()))
						* t + getNormalisedColor(startRGB.getGreen());
				var blue = (getNormalisedColor(endRGB.getBlue()) - getNormalisedColor(startRGB
						.getBlue()))
						* t + getNormalisedColor(startRGB.getBlue());
				gradientRGB = new RGB(Math.round(red * 255), Math
						.round(green * 255), Math.round(blue * 255));
			}
			return gradientRGB;
		};

		/* Public API returned by the constructor */
		return {
			getRGB : getRGB
		};
	};

	Gradient.prototype = {
		constructor : Gradient
	};
	return Gradient;
}());
