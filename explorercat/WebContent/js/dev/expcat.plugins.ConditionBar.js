expcat.namespace("expcat.plugins.ConditionBar");

/**
 * TODO The components of expcat.plugins.* are tightly coupled, some refactoring
 * will do good.
 */

/**
 * @ignore
 */

expcat.plugins.ConditionBar = (function() {

	var TYPES = expcat.cql.Type;
	var Clause = expcat.cql.Clause;
	var Value = expcat.cql.Value;

	/**
	 * A condition bar represents a condition that can be configured by the user
	 * via UI. Components (select and input boxes) can be retrived by the
	 * interface and events are automatically managed by the callback functions
	 * provided.
	 * 
	 * @param id
	 *            {Integer} An integer that identifies uniquely the condition
	 *            bar.
	 * @param propertyMap
	 *            {Object} A hash that associates property names to properties.
	 * @param aliases
	 *            {[Object]} An array of objects that specify the name/alias for
	 *            each property to be used (order in the array defines the order
	 *            in the select).
	 * @param operatorSymbolMap
	 *            {Object} A hash that associates operator symbols to operators.
	 * @param valuesUpdateFunction
	 *            {Function} The function that will be called when the input
	 *            values are updated.
	 * @param cqlUpdateFunction
	 *            {Function} The function in charge of updating the generated
	 *            cql code when this condition changes.
	 * @param tooltipDivName
	 *            {String} The base name of the container to be updated with the
	 *            description of the selected property.
	 * 
	 * @name expcat.plugins.ConditionBar
	 * @constructor
	 * @public
	 * @author Jacob Almagro - ExplorerCat Project
	 */

	var ConditionBar = function(id, propertyMap, aliases, operatorSymbolMap,
			valuesUpdateFunction, cqlUpdateFunction, tooltipDivName) {

		var conditionId = id;
		var propertyDictionary = propertyMap;
		var propertyAliases = aliases;
		var aliasesAsArray = [];
		var propertyAliasesLookup = null;
		var operatorsBySymbol = operatorSymbolMap;
		var valuesUpdateCallback = valuesUpdateFunction;
		var cqlUpdateCallback = cqlUpdateFunction;
		var isNegated = false;
		var tooltipBaseName = tooltipDivName || "helpTooltip";

		// Select boxes and inputs
		var selectProperty;
		var selectOperator;
		var inputValues = [];

		/**
		 * Initialises the component.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var init = function() {
			createAliasesLookup();
			extractAliasesToArray();
			selectProperty = createSelectBox("selectProperty" + id,
					aliasesAsArray);
			selectOperator = createSelectBox("selectOperator" + id,
					getMapKeys(operatorsBySymbol));
			recreateValues();

			// Handlers for the property box.
			selectProperty.bind("change", function() {
				updateOperators();
				recreateValues();
				valuesUpdateCallback(conditionId, inputValues);
				resetInvalidValues();
				updateTooltipText();
				cqlUpdateCallback();
			});
			// Handlers for the operator box.
			selectOperator.bind("change", function() {
				updateValues();
				valuesUpdateCallback(conditionId, inputValues);
				resetInvalidValues();
				cqlUpdateCallback();
			});
		};

		/**
		 * Creates the lookup that maps aliases to property names.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var createAliasesLookup = function() {
			var i;
			var currentAlias;
			propertyAliasesLookup = {};

			for (i = 0; i < propertyAliases.length; ++i) {
				currentAlias = propertyAliases[i];
				propertyAliasesLookup[currentAlias.alias || currentAlias.name] = propertyDictionary[currentAlias.name];
			}
		}

		/**
		 * Creates an array that contains all the property aliases (in order).
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var extractAliasesToArray = function() {
			var i;
			aliasesAsArray = [];
			for (i = 0; i < propertyAliases.length; ++i)
				aliasesAsArray.push(propertyAliases[i].alias
						|| propertyAliases[i].name)
		}

		/**
		 * Creates a new select box with the given name and values (options).
		 * 
		 * @param name
		 *            {String} The name of the select box.
		 * @param values
		 *            {Array} Array of text values for the select box.
		 * @return {jQuery} The configured select box.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var createSelectBox = function(name, values) {
			var selectBox = $("<select></select>");
			var i;

			selectBox.attr("name", name);
			selectBox.attr("id", name);

			if (values && values.length > 0) {
				setSelectOptions(selectBox, values);
			}

			return selectBox;
		}
		/**
		 * Sets the options of the given selection box using the values
		 * parameter.
		 * 
		 * @param selectBox
		 *            {jQuery} The select object whose options will be set.
		 * @param valeus
		 *            {Array} An array of text values that will be used to
		 *            create the options.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var setSelectOptions = function(selectBox, values) {
			var optionsHTML = [];
			var currentOption;

			// Clear the options.
			selectBox.html("");

			for (i = 0; i < values.length; ++i) {
				currentOption = "<option value=\"" + i + "\">" + values[i]
						+ "</option>";
				optionsHTML.push(currentOption);
			}

			// Add the options
			selectBox.html(optionsHTML.join(" "));
			selectBox.val(0);
		}
		/**
		 * Updates the operators that are shown based on the selected property.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var updateOperators = function() {
			var supportedOperators = getOperatorsSupportedBySelectedProperty();
			var operatorSymbols = [];
			var i;

			for (i = 0; i < supportedOperators.length; ++i) {
				operatorSymbols.push(supportedOperators[i].getSymbol());
			}

			setSelectOptions(selectOperator, operatorSymbols);
		}
		/**
		 * Gets the operators that are supported by the selected property.
		 * 
		 * @return {Array} An array containing the supported operators.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var getOperatorsSupportedBySelectedProperty = function() {
			var selectedProperty = getSelectedProperty();
			var supportedOperators = [];
			var op;

			for (op in operatorsBySymbol) {
				if (operatorsBySymbol[op].supportsType(selectedProperty
						.getType()))
					supportedOperators.push(operatorsBySymbol[op]);
			}

			return supportedOperators;
		}
		/**
		 * Updates the input values according to the selected operator.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var updateValues = function() {
			var operator = getSelectedOperator();
			if (inputValues.length !== operator.getArity() - 1) {
				inputValues = createInputValues(operator.getArity() - 1);
			}
		}
		/**
		 * Recreates the input values according to the selected propety and
		 * operator.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var recreateValues = function() {
			var operator = getSelectedOperator();
			inputValues = createInputValues(operator.getArity() - 1);
		};
		/**
		 * Resets any invalid input value to a default.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var resetInvalidValues = function() {
			var i;
			for (i = 0; i < inputValues.length; ++i) {
				resetInvalidValue(inputValues[i]);
			}
		}
		/**
		 * Resets the given input to a default if its value is invalid.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var resetInvalidValue = function(inputValue) {
			if (!isValueValid(inputValue)) {
				setDefaultValue(inputValue);
			}
		}
		/**
		 * Sets the given input with the default value of the property currently
		 * selected.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var setDefaultValue = function(input) {
			var property = getSelectedProperty();
			var minimum = property.getMinimum();
			var maximum = property.getMaximum();
			var allowedValues = property.getAllowedValues();
			var type = property.getType();

			if (allowedValues !== null) {
				input.val(0);
			} else if (minimum !== null) {
				input.val(minimum);
			} else if (maximum !== null) {
				input.val(maximum);
			} else {
				input.val(type.defaultValue());
			}
		}
		/**
		 * Creates an array of input boxes (or select boxes) to store the
		 * condition values.
		 * 
		 * @param numValues
		 *            The number of text inputs to be created.
		 * @return {Array} An array of text input boxes (jQuery objects0) for
		 *         the values.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var createInputValues = function(numValues) {
			var inputBoxes = [];
			var currentInput;
			var inputId;
			var width = 80 / numValues;
			var allowedValues = getSelectedProperty().getAllowedValues();
			var i;

			if (allowedValues == null) {

				for (i = 0; i < numValues; ++i) {
					currentInput = $("<input type='text'></input>");
					inputId = "inputValue_" + i + "_" + conditionId
					currentInput.attr("name", inputId);
					currentInput.attr("id", inputId);
					currentInput.css("width", width + "%");

					inputBoxes.push(currentInput);

					// Hanlders for the input.
					currentInput.bind("change", function() {
						resetInvalidValue($(this));
						cqlUpdateCallback();
					});
				}
			} else {
				for (i = 0; i < numValues; ++i) {
					inputId = "inputValue_" + i + "_" + conditionId;
					currentInput = createSelectBox(inputId, allowedValues);
					currentInput.css("width", width + "%");

					inputBoxes.push(currentInput);

					// Hanlders for the input.
					currentInput.bind("change", function() {
						cqlUpdateCallback();
					});
				}
			}

			return inputBoxes;
		}
		/**
		 * Gets the property selected in the condition.
		 * 
		 * @return {expcat.cql.Property} The selected property.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var getSelectedProperty = function() {
			var selectDOM = selectProperty.get(0);
			var aliasName = selectDOM.options[selectDOM.selectedIndex].text;
			return propertyAliasesLookup[aliasName];
		}
		/**
		 * Gets the operator selected in the condition.
		 * 
		 * @return {expcat.cql.Operator} The selected operator.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var getSelectedOperator = function() {
			var selectDOM = selectOperator.get(0);
			var op = selectDOM.options[selectDOM.selectedIndex].text;
			return operatorsBySymbol[op];
		}
		/**
		 * Checks the value provided are coherent with the property and operator
		 * selected. In case the value is valid it will be reparsed to trim any
		 * added rubbish. References to other properties (as strings) are
		 * considered valid without type checking.
		 * 
		 * @return {Boolean} True if the value is valid (coherent with the
		 *         selected property).
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var isValueValid = function(inputValue) {
			var property = getSelectedProperty();
			var value = inputValue.val();
			var parsedValue;

			if (property.getAllowedValues() != null)
				value = property.getAllowedValues()[parseInt(value)];

			var isValid = property.isValueValid(new Value(property.getType()
					.getName(), value));
			var i;

			/** Check if we are referencing another property */
			if (!isValid && propertyDictionary[inputValue.val()]) {
				return true;
			}

			/** Re-parse the input value to remove any rubbish * */
			if (isValid) {
				parsedValue = property.getType().parse(inputValue.val());

				/** Just in case we missed a numeric value * */
				if ((property.getType() === TYPES.INTEGER || property.getType() === TYPES.REAL)
						&& isNaN(parsedValue))
					return false;
				else
					inputValue.val(parsedValue);
			}

			return isValid;
		}
		/**
		 * Gets the select box for the condition property.
		 * 
		 * @return {jQuery} The select box for the property referenced in the
		 *         condition.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var getPropertySelect = function() {
			return selectProperty;
		}
		/**
		 * Gets the select box for the condition operator.
		 * 
		 * @return {jQuery} The select box for the condition operator.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var getOperatorSelect = function() {
			return selectOperator;
		}
		/**
		 * Gets the input values for the values.
		 * 
		 * @return {Array} An array of Inputs (jQuery) that contains the
		 *         condition values.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var getInputValues = function() {
			return inputValues;
		}
		/**
		 * Negates the condition contained in the bar. Notice that negating the
		 * condition an even number of times cancels out the negation.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var negateCondition = function() {
			isNegated = !isNegated;
		}
		/**
		 * Checks if the condition is negated.
		 * 
		 * @return {Boolean} True if the condition is negated, false otherwise.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var isConditionNegated = function() {
			return isNegated;
		}
		/**
		 * Gets the id of the condition bar.
		 * 
		 * @return {Integer} The id that identifiers uniquely the condition in
		 *         the query.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var getId = function() {
			return conditionId;
		}
		/**
		 * Updates and sync all the components of the condition.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var refresh = function() {
			updateOperators();
			updateValues();
			resetInvalidValues();
		}
		/**
		 * Builds a clause for the condition.
		 * 
		 * @retun A clause object that encapsulates the condition and can be
		 *        directly translated into CQL.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var buildClause = function() {
			var clause = new Clause();
			var property = getSelectedProperty();
			var clauseValues = [ property ];
			var currentValue;
			var i;

			clause.setOperator(getSelectedOperator().getName());

			for (i = 0; i < inputValues.length; ++i) {
				currentValue = inputValues[i].val();

				// Check if we are accessing a select box of allowed values.
				if (property.getAllowedValues() !== null)
					currentValue = property.getAllowedValues()[parseInt(currentValue)];

				// Check if the value represents another entity property.
				if (propertyDictionary[currentValue])
					clauseValues.push(new Value(TYPES.PROPERTY.getName(),
							currentValue));
				else
					clauseValues.push(new Value(property.getType().getName(),
							currentValue));
			}

			clause.setOperands(clauseValues);

			if (isConditionNegated()) {
				var negatedClause = new Clause();
				negatedClause.setOperator("NOT");
				negatedClause.setOperands([ clause ]);
				return negatedClause;
			}

			return clause;
		}
		/**
		 * Updates the text in the tooltip that shows the property description.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var updateTooltipText = function() {
			$("#" + tooltipBaseName + conditionId).html(
					getPropertyDescription());
		};
		/**
		 * Utility function that retrieves the description of the selected
		 * property.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var getPropertyDescription = function() {
			var property = getSelectedProperty();
			var description = property.getDescription();

			if (property.getDescription() === null)
				return "No description";
			else
				return description;
		}
		/**
		 * Auxiliary method that returns the keys of the given hash.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @private
		 * @ignore
		 */

		var getMapKeys = function(map) {
			var k;
			var keys = [];
			for (k in map) {
				keys.push(k);
			}
			return keys;
		};
		/**
		 * Translates the condition bar into CQL.
		 * 
		 * @return {String} The equivalent CQL code for the condition.
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var translateToCQL = function() {
			return buildClause().translate();
		}

		/**
		 * Method to call when a property has changed explictly by code, than
		 * user interface. This method is added to support
		 * setupUIifParametersProvided of expcat.plugins.CatalogExplorer, which
		 * enables dynamic url binding from genotype tool temporarly
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var firePropertyChange = function() {
			updateOperators();
			recreateValues();
			valuesUpdateCallback(conditionId, inputValues);
			resetInvalidValues();
			updateTooltipText();
			cqlUpdateCallback();
		}

		/**
		 * Method to call when a operator has changed explictly by code, than
		 * user interface. This method is added to support
		 * setupUIifParametersProvided of expcat.plugins.CatalogExplorer, which
		 * enables dynamic url binding from genotype tool temporarly
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */

		var fireOperatorChange = function() {
			updateValues();
			valuesUpdateCallback(conditionId, inputValues);
			resetInvalidValues();
			cqlUpdateCallback();
		}

		/**
		 * Method to call when a value of a condition has changed explictly by
		 * code, than user interface. This method is added to support
		 * setupUIifParametersProvided of expcat.plugins.CatalogExplorer, which
		 * enables dynamic url binding from genotype tool temporarly
		 * 
		 * @memberOf expcat.plugins.ConditionBar#
		 * @public
		 */
	
		var fireValueChange = function() {
			cqlUpdateCallback();
		}

		// Initializes the object.
		init();

		/* Public API returned by the constructor */
		return {
			getSelectedProperty : getSelectedProperty,
			getSelectedOperator : getSelectedOperator,
			getPropertySelect : getPropertySelect,
			getPropertyDescription : getPropertyDescription,
			getOperatorSelect : getOperatorSelect,
			getInputValues : getInputValues,
			negateCondition : negateCondition,
			isConditionNegated : isConditionNegated,
			getId : getId,
			buildClause : buildClause,
			refresh : refresh,
			translateToCQL : translateToCQL,
			firePropertyChange : firePropertyChange,
			fireOperatorChange : fireOperatorChange,
			fireValueChange : fireValueChange
		};
	};
	/* Prototype methods and properties */
	ConditionBar.prototype = {
		constructor : expcat.plugins.ConditionBar
	};

	return ConditionBar;

}());
