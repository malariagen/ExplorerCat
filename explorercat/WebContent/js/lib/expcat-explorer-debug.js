
expcat.namespace("expcat.cql.Type");

/**
 * Collection of integral types supported in CQL. Notice we refer only to
 * integral types, an array of integers will be considered of type INTEGER.
 *
 * @author Jacob Almagro - ExplorerCat project
 * @namespace
 */

expcat.cql.Type = {

	/**
	 * Equivalent to a Java integer.
	 * @namespace
	 */

	INTEGER : {

		getName : function() {
			return "INTEGER";
		},

		/**
		 * Parses the given value as an integer.
		 */

		parse : function(value) {
			return parseInt(value, 10);
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return 0;
		}

	},

	/**
	 * Equivalent to a Java Float.
	 * @namespace
	 */

	REAL : {

		getName : function() {
			return "REAL";
		},

		/**
		 * Parses the given value as a float.
		 */

		parse : function(value) {
			return parseFloat(value, 10);
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return 0.0;
		}

	},

	/**
	 * Equivalent to a Java String.
	 * @namespace
	 */

	STRING : {

		getName : function() {
			return "STRING";
		},

		/**
		 * Parses the given value as a string.
		 */

		parse : function(value) {
			return String(value);
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return "";
		}

	},

	/**
	 * Equivalent to a Java String.
	 * @namespace
	 */

	TEXT : {

		getName : function() {
			return "TEXT";
		},

		/**
		 * Parses the given value as a string.
		 */

		parse : function(value) {
			return String(value);
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return "";
		}

	},
	
	/**
	 * Equivalent to a Java boolean.
	 * @namespace
	 */

	BOOLEAN : {

		getName : function() {
			return "BOOLEAN";
		},

		/**
		 * Parses the given value as a boolean.
		 */

		parse : function(value) {
			if(value)
				return true;
			return false;
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return true;
		}

	},

	/**
	 * Represents a date as dd/mm/yyyy or yyyy/mm/dd where the separators can be
	 * slash (/) or dash (-).
	 * @namespace
	 */

	DATE : {

		getName : function() {
			return "DATE";
		},

		/**
		 * Parses the given value as a a date. Supported formats are dd/mm/yyyy and
		 * yyyy/mm/dd (being the separator a slash(/) or a dash(-)).
		 *
		 * @example "2010/08/25", "25-08-2010" are valid dates whereas "2010/8/25" is
		 * not.
		 */

		parse : function(value) {
			var stringValue = value.toString();
			var daySeparator = stringValue.charAt(2);
			var yearSeparator = stringValue.charAt(4);
			var day, month, year;

			if((daySeparator === '-' || daySeparator === '/') && stringValue.length === 10) {
				day = parseInt(stringValue.substr(0, 2), 10);
				month = parseInt(stringValue.substr(3, 2), 10);
				year = parseInt(stringValue.substr(6, 4), 10);
			}
			else if((yearSeparator === '-' || yearSeparator === '/') && stringValue.length === 10) {
				day = parseInt(stringValue.substr(8, 2), 10);
				month = parseInt(stringValue.substr(5, 2), 10);
				year = parseInt(stringValue.substr(0, 4), 10);
			}
			else {
				throw new Error("Wrong date format, expecting yyyy/mm/dd or dd/mm/yyyy");
			}

			// Weak validation.
			if(day > 31 || day < 1 || month > 12 || month < 1 || year < 0) {
				throw new Error("Wrong date");
			}

			return new Date(year, month, day);
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return parse("2000-01-01");
		}

	},

	/**
	 * Equivalent to an array of Strings.
	 * @namespace
	 */

	ARRAY_STRING : {

		getName : function() {
			return "ARRAY_STRING";
		},

		/**
		 * Parses the given value as an array of strings.
		 */

		parse : function(value) {
			return String(value).split(",");
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return [];
		}
	},
	
	
	/**
	 * Equivalent to an array of Text strings.
	 * @namespace
	 */

	ARRAY_TEXT : {

		getName : function() {
			return "ARRAY_TEXT";
		},

		/**
		 * Parses the given value as an array of strings.
		 */

		parse : function(value) {
			return String(value).split(",");
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return [];
		}
	},
	
	/**
	 * Equivalent to an array of integers.
	 * @namespace
	 */

	ARRAY_INTEGER : {

		getName : function() {
			return "ARRAY_INTEGER";
		},

		/**
		 * Parses the given value as an array of integers.
		 */

		parse : function(value) {
			var valueArray = String(value).split(",");
			var i;
			for(i = 0; i < valueArray.length; ++i)
				valueArray[i] =  expcat.cql.Type.INTEGER.parse(valueArray[i]);			
			return valueArray;
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return [];
		}
	},
	
	/**
	 * Equivalent to an array of reals.
	 * @namespace
	 */

	ARRAY_REAL: {

		getName : function() {
			return "ARRAY_REAL";
		},

		/**
		 * Parses the given value as an array of reals.
		 */

		parse : function(value) {
			var valueArray = String(value).split(",");
			var i;
			for(i = 0; i < valueArray.length; ++i)
				valueArray[i] =  expcat.cql.Type.REAL.parse(valueArray[i]);			
			return valueArray;
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return [];
		}
	},
			
	/**
	 * Equivalent to an array of booleans.
	 * @namespace
	 */

	ARRAY_BOOLEAN: {

		getName : function() {
			return "ARRAY_BOOLEAN";
		},

		/**
		 * Parses the given value as an array of booleans.
		 */

		parse : function(value) {
			var valueArray = String(value).split(",");
			var i;
			for(i = 0; i < valueArray.length; ++i)
				valueArray[i] = (valueArray[i].toLowerCase() === "true");			
			return valueArray;
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return [];
		}
	},
	
	/**
	 * Equivalent to an array of dates.
	 * @namespace
	 */

	ARRAY_DATE: {

		getName : function() {
			return "ARRAY_DATE";
		},

		/**
		 * Parses the given value as an array of dates.
		 */

		parse : function(value) {
			var valueArray = String(value).split(",");
			var i;
			for(i = 0; i < valueArray.length; ++i)
				valueArray[i] = expcat.cql.Type.DATE.parse(valueArray[i]);			
			return valueArray;
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return [];
		}
	},
	
	/**
	 * Artificial type used to identify clauses
	 * @see expcat.cql.Clause
	 * @namespace
	 */

	CLAUSE : {

		getName : function() {
			return "CLAUSE";
		},

		/** @ignore */
		parse : function(value) {
			throw new Error("Invalid operation for type CLAUSE");
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return null;
		}

	},

	/**
	 * Artificial type used to identify property references.
	 * @see expcat.cql.Property
	 * @namespace
	 */

	PROPERTY : {

		getName : function() {
			return "PROPERTY";
		},

		parse : function(value) {
			return value;
		},

		/**
		 * Generates a default value for the type.
		 */

		defaultValue : function() {
			return null;
		}

	}
};
expcat.namespace("expcat.cql.Operator");

/**
 * Defines the operators that can be used to create CQL queries (currently a
 * subset of the CQL operators). Some operators are limited to a set of value
 * types, using them with the wrong type will raise an exception (this also
 * applies to operators arity).
 * 
 * @author Jacob Almagro - ExplorerCat Project
 * @namespace
 */

expcat.cql.Operator = (function() {

	// Aliases.
	var TYPES = expcat.cql.Type;

	/**
	 * Checks the arity of an operator is right for the given number of
	 * operands.
	 * 
	 * @private
	 * @ignore
	 */

	var checkArity = function(operator, arity, operands) {
		if (arity !== operands.length) {
			throw new Error(operator + " was expecting " + arity + " operands");
		}
	};

	/**
	 * Checks if the given type is numeric.
	 * 
	 * @private
	 * @ignore
	 */

	var checkTypeIsNumeric = function(type) {
		return (type === TYPES.INTEGER || type === TYPES.REAL);
	};

	/**
	 * Checks if the given type is numeric.
	 * 
	 * @private
	 * @ignore
	 */

	var checkTypeIsClause = function(type) {
		return (type === TYPES.CLAUSE);
	};

	/**
	 * Cheks if it is safe to use the operator with the given operands.
	 * 
	 * @private
	 * @ignore
	 */

	var checkTypeSafety = function(operands, op) {
		var i = 0;
		for (; i < operands.length; ++i) {
			if (!op.supportsType(operands[i].getType())) {
				throw new Error("Type " + operands[i].getType().getName()
						+ " not supported by " + op.getName());
			}
		}
	};

	return {

		/**
		 * Equality operator.
		 * 
		 * @example a = b
		 * @namespace
		 */

		EQUAL : {

			getSymbol : function() {
				return "=";
			},

			getName : function() {
				return "EQUAL";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("EQUAL", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " = "
						+ operands[1].translate();
			},

			supportsType : function(type) {
				return true;
			},

			getArity : function() {
				return 2;
			}

		},

		/**
		 * Greater than operator.
		 * 
		 * @example a > b
		 * @namespace
		 */

		GREATER : {

			getSymbol : function() {
				return ">";
			},

			getName : function() {
				return "GREATER";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("GREATER", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " > "
						+ operands[1].translate();
			},

			/** @function */
			supportsType : checkTypeIsNumeric,

			getArity : function() {
				return 2;
			}

		},

		/**
		 * Greater or equal than operator.
		 * 
		 * @example a >= b
		 * @namespace
		 */

		GREATER_EQUAL : {

			getSymbol : function() {
				return ">=";
			},

			getName : function() {
				return "GREATER_EQUAL";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("GREATER_EQUAL", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " >= "
						+ operands[1].translate();
			},

			/** @function */
			supportsType : checkTypeIsNumeric,

			getArity : function() {
				return 2;
			}

		},

		/**
		 * Greater or equal than operator.
		 * 
		 * @example a > b
		 * @namespace
		 */

		LESS_EQUAL : {

			getSymbol : function() {
				return "<=";
			},

			getName : function() {
				return "LESS_EQUAL";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("LESS_EQUAL", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " <= "
						+ operands[1].translate();
			},

			/** @function */
			supportsType : checkTypeIsNumeric,

			getArity : function() {
				return 2;
			}

		},

		/**
		 * Less than operator.
		 * 
		 * @example a < b
		 * @namespace
		 */

		LESS : {

			getSymbol : function() {
				return "<";
			},

			getName : function() {
				return "LESS";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("LESS", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " < "
						+ operands[1].translate();
			},

			/** @function */
			supportsType : checkTypeIsNumeric,

			getArity : function() {
				return 2;
			}

		},

		/**
		 * Contains operator. Checks if a string contains another string. Can be
		 * used also to check if an array contains an element.
		 * 
		 * @example a CONTAINS b
		 * @namespace
		 */

		CONTAINS : {

			getSymbol : function() {
				return "contains";
			},

			getName : function() {
				return "CONTAINS";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("CONTAINS", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " contains "
						+ operands[1].translate();
			},

			supportsType : function(type) {
				return !checkTypeIsNumeric(type);
			},

			getArity : function() {
				return 2;
			}

		},

		/**
		 * Matches operator. Checks if a string matches a regular expression.
		 * 
		 * @example a MATCHES b
		 * @namespace
		 */

		MATCHES : {

			getSymbol : function() {
				return "matches";
			},

			getName : function() {
				return "MATCHES";
			},

			/**
			 * Translates into CQL code. Notice the inversion of operands.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("MATCHES", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[1].translate() + " matches "
						+ operands[0].translate();
			},

			supportsType : function(type) {
				return type === TYPES.STRING || type === TYPES.ARRAY_STRING
						|| type === TYPES.TEXT || type === TYPES.ARRAY_TEXT;
			},

			getArity : function() {
				return 2;
			}

		},

		/**
		 * StartsWith operator. Checks if a string starts with another string.
		 * 
		 * @example a STARTSWITH b
		 * @namespace
		 */

		STARTS_WITH : {

			getSymbol : function() {
				return "startsWith";
			},

			getName : function() {
				return "STARTS_WITH";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("STARTS_WITH", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " startsWith "
						+ operands[1].translate();
			},

			supportsType : function(type) {
				return type === TYPES.STRING || type === TYPES.ARRAY_STRING
						|| type === TYPES.TEXT || type === TYPES.ARRAY_TEXT;

			},

			getArity : function() {
				return 2;
			}

		},

		/**
		 * RangeFor operator. Checks if a value is contained in a range. Notice
		 * that the range is always inclusive.
		 * 
		 * @example RANGEFOR a [low, high]
		 * @namespace
		 */

		RANGE_FOR : {

			getSymbol : function() {
				return "range";
			},

			getName : function() {
				return "RANGE_FOR";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("RANGEFOR", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return "rangeFor " + operands[0].translate() + " ["
						+ operands[1].translate() + ","
						+ operands[2].translate() + "]";
			},

			/** @function */
			supportsType : checkTypeIsNumeric,

			getArity : function() {
				return 3;
			}

		},

		/**
		 * Not operator. Negates the logical value of an expression.
		 * 
		 * @example NOT a
		 * @namespace
		 */

		NOT : {

			getSymbol : function() {
				return "NOT";
			},

			getName : function() {
				return "NOT";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("NOT", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return "NOT " + operands[0].translate();
			},

			/** @function */
			supportsType : checkTypeIsClause,

			getArity : function() {
				return 1;
			}

		},

		/**
		 * AND operator. Logical conjunction.
		 * 
		 * @example a AND b
		 * @namespace
		 */

		AND : {

			getSymbol : function() {
				return "AND";
			},

			getName : function() {
				return "AND";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("AND", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " AND "
						+ operands[1].translate();
			},

			/** @function */
			supportsType : checkTypeIsClause,

			getArity : function() {
				return 2;
			}

		},

		/**
		 * OR operator. Logical disjunction.
		 * 
		 * @example a OR b
		 * @namespace
		 */

		OR : {

			getSymbol : function() {
				return "OR";
			},

			getName : function() {
				return "OR";
			},

			/**
			 * Translates into CQL code.
			 * 
			 * @param operands
			 *            {Array} The array of operands to be used with the
			 *            operator.
			 * @return {String} The CQL code that applies the operator to the
			 *         operands.
			 */

			translate : function(operands) {
				checkArity("OR", this.getArity(), operands);
				checkTypeSafety(operands, this);
				return operands[0].translate() + " OR "
						+ operands[1].translate();
			},

			/** @function */
			supportsType : checkTypeIsClause,

			getArity : function() {
				return 2;
			}

		},

		/**
		 * Static method that returns a map that relates operator symbols with
		 * operator objects.
		 * 
		 * @public
		 */

		getSymbolMap : function() {
			var map = {};
			var op;
			var operators = expcat.cql.Operator

			for (op in this) {
				if (typeof this[op] !== 'function') {
					map[this[op].getSymbol()] = this[op];
				}
			}

			return map;
		}

	};
}());
expcat.namespace("expcat.cql.Value");

/**
 * @ignore
 */

expcat.cql.Value = ( function() {

	// Aliases.
	var TYPES = expcat.cql.Type;

	/**
	 * Represents a CQL data value with an associated CQL type that can be translated
	 * into CQL. The constructor builds a data value with the given type.
	 * @see expcat.cql.Type
	 * @param type {String} A string with the name of the value type.
	 * @param value A representation of the value (will be cast to the given type).
	 *
	 * @constructor
	 * @name expcat.cql.Value
	 * @public
	 * @author Jacob Almagro - ExplorerCat project
	 */

	var Value = function(type, value) {
		if( typeof TYPES[type] === "undefined") {
			throw new Error("Unknown CQL type : " + type);
		}
		type = TYPES[type];
		value = type.parse(value);

		/**
		 * Gets the stored value.
		 * @return The contained value as a primitive (cast to the assigned type).
		 *
		 * @memberOf expcat.cql.Value#
		 * @public
		 */

		var getValue = function() {
			return value;
		};
		/**
		 * Gets the type of the value.
		 * @return {expcat.cql.Type} The object type for this value.
		 *
		 * @memberOf expcat.cql.Value#
		 * @public
		 */

		var getType = function() {
			return type;
		};
		/**
		 * Translates the value into CQL code.
		 * @return {String} The equivalent CQL code for the value.
		 *
		 * @memberOf expcat.cql.Value#
		 * @public
		 */

		var translate = function() {
			// Date is a special case.
			if(type === TYPES.DATE) {
				var year = value.getFullYear();
				var month = value.getMonth();
				var day = value.getDate();

				return year + "/" + (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day);
			}
			// In case of strings, texts we add the quotes.
			if(type === TYPES.STRING || type === TYPES.ARRAY_STRING || type === TYPES.TEXT || type === TYPES.ARRAY_TEXT)
				return "'" + value + "'";

			return value;
		};
		/* Public API returned by the constructor. */
		return {
			getValue : getValue,
			getType : getType,
			translate : translate
		};
	};
	/* Prototype methods. */
	Value.prototype = {
		constructor : Value
	};

	return Value;
}());
expcat.namespace("expcat.cql.Property");

/**
 * @ignore
 */
expcat.cql.Property = (function(){

    // Aliases.
    var TYPES = expcat.cql.Type;
    var Value = expcat.cql.Value;
    
    /**
     * Represents the definition of an entity property. They are basically named
     * values that can have some additional attributes like maximum, minimum or a
     * list of allowed values.
     *
     * The constructor builds a property from a property definition (object).
     *
     * @param definition.name {String} The name of the property.
     * @param definition.alias {String} The alias of the property.
     * @param definition.type {String} CQL type for the property value.
     * @param definition.description {String} Brief description of the property.
     * @param definition.maximum {Number} Maximum value it can take (undefined if
     * there is no maximum).
     * @param definition.minimum {Number} Minimum value it can take (undefined if
     * there is no minimum).
     * @param definition.allowedValues {Array} Array of permitted values for the
     * property (undefined if there are no restrictions).
     *
     * @name expcat.cql.Property
     * @constructor
     * @author Jacob Almagro - ExplorerCat Project.
     */
    var Property = function(definition){
    
        var type;
        var name = definition.name || "null";
        var alias = definition.alias || name;
        var description = definition.description || null;
        var maximum = (typeof definition.maximum === "undefined" || definition.maximum === null) ? null : definition.maximum;
        var minimum = (typeof definition.minimum === "undefined" || definition.minimum === null) ? null : definition.minimum;
        var allowedValues = definition.allowedValues || null;
        var i;
        
        if (typeof TYPES[definition.type] === "undefined") {
            throw new Error("Unknown CQL type : " + definition.type);
        }
        
        type = TYPES[definition.type];
        
        if (allowedValues !== null) {
            if (type === TYPES.INTEGER || type === TYPES.REAL) {
                for (i = 0; i < allowedValues.length; ++i) {
                    allowedValues[i] = type.parse(allowedValues[i]);
                }
				allowedValues.sort(function numSort(a, b){return (a-b)}); 
            }
			else
            	allowedValues.sort();
        }        
        
        /**
         * Gets the name of the property.
         * @return {String} The name of the property.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getName = function(){
            return name;
        }
        
        /**
         * Gets the alias of the property.
         * @return {String} The alias of the property or the name of the property
         * 		if no alias was provided.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getAlias = function(){
            return alias;
        }
        
        /**
         * Gets the type of the property.
         * @return {expcat.cql.Type} The object that represents the property type.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getType = function(){
            return type;
        }
        
        /**
         * Gets the value of the property. Since this is a property reference it will
         * return the name of the propery.
         * @return {String} The name of the property.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getValue = function(){
            return getName();
        };
        
        /**
         * Translates the property into CQL code (this is equivalent to the name of the
         * property).
         * @return {String} The property translated into CQL code.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var translate = function(){
            return getName();
        };
        
        /**
         * Gets the description of the property.
         * @return {String} The property description or null.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getDescription = function(){
            return description;
        };
        
        /**
         * Gets the maximum value for the property.
         * @retun {Number} The maximum value this property can store or null if not
         * specified.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getMaximum = function(){
            return maximum;
        };
        
        /**
         * Gets the minimum value for the property.
         * @retun {Number} The minimum value this property can store or null if not
         * specified.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getMinimum = function(){
            return minimum;
        };
        
        /**
         * Gets an array containing the values allowed for the property.
         * @return {Array} An array of strings containing the values allowed for the
         * property or null if not specified.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var getAllowedValues = function(){
            return allowedValues;
        };
        
        /**
         * Checks if the given value is valid for the property (types have to match).
         * @param value {expcat.cql.Value} A data value object that contains the
         * value to be checked.
         * @return {Boolean} True if the value is compatible with the property, false
         * otherwise.
         *
         * @memberOf expcat.cql.Property#
         * @public
         */
        var isValueValid = function(value){
        
            if (!value instanceof Value) 
                throw new Error("Expecting a Value object but you provided a primitive type " + typeof value);
            
            // Notice that we don't allow type conversion for numeric values in this method.
            if (!(value.getType() === type)) 
                return false;
            
            if ((value.getType() === TYPES.INTEGER || value.getType() === TYPES.REAL) && isNaN(value.getValue())) 
                return false;
            
            if (maximum && value.getValue() > maximum) {
                return false;
            }
            if (minimum && value.getValue() < minimum) {
                return false;
            }
            // Linear search (small arrays anyway).
            if (allowedValues && $.inArray(value.getValue(), allowedValues) === -1) {
                return false;
            }
            
            return true;
        };
        
        /* Public API returned by the constructor */
        return {
            getName: getName,
            getAlias: getAlias,
            getValue: getValue,
            getType: getType,
            translate: translate,
            getDescription: getDescription,
            getMinimum: getMinimum,
            getMaximum: getMaximum,
            getAllowedValues: getAllowedValues,
            isValueValid: isValueValid
        };
    };
    
    /* Prototype methods and properties */
    Property.prototype = {
        constructor: Property
    };
    
    return Property;
    
}());
expcat.namespace("expcat.cql.Clause");

/**
 * @ignore
 */

expcat.cql.Clause = ( function() {

	// Aliases.
	var TYPES = expcat.cql.Type;
	var OPERATORS = expcat.cql.Operator;

	/**
	 * Gets the type of a clause object. Allows a clause to be used as a regular
	 * operand (duck typing). This method is static and goes bound to the prototype.
	 * @return The object type for a clause.
	 *
	 * @see expcat.cql.Type.CLAUSE
	 * @memberOf expcat.cql.Clause
	 * @public
	 */

	var getType = function() {
		return TYPES.CLAUSE;
	};

	/**
	 * Represents a CQL expression (operator and operands) that can be evaluated as
	 * true/false. As you would expect clauses can be used to build other clauses.
	 *
	 * The constructor creates an empty clause that has to be configured using the
	 * setter methods.
	 *
	 * @name expcat.cql.Clause
	 * @constructor
	 * @author Jacob Almagro - ExplorerCat project.
	 */

	var Clause = function() {
		var operator;
		var operands = [];
		var enclosedInBrackets = false;

		/**
		 * Sets the operator of the clause.
		 * @param op {String} The name of the operator to be used with the clause.
		 *
		 * @memberOf expcat.cql.Clause#
		 * @public
		 */

		var setOperator = function(op) {
			var i;
			var tempOperator = OPERATORS[op];

			if( typeof tempOperator === "undefined") {
				throw new Error("Unknown operator " + op);
			}

			for( i = 0; i < operands.length; ++i) {
				if(!tempOperator.supportsType(operands[i].getType())) {
					throw new Error("Operand not supported by operator: " + tempOperator.getName() + " and " + operands[i].getType());
				}
			}
			operator = tempOperator;
		};

		/**
		 * Gets the operator used in this clause.
		 * @return {expcat.cql.operator} The object that represents the operator
		 * or null if no operator has been set.
		 * @memberOf expcat.cql.Clause#
		 * @public
		 */

		var getOperator = function() {
			return operator;
		};

		/**
		 * Sets the operands of the clause
		 * @param ops {Array} An array of objects that contains the operands to be used
		 * in the clause. Notice the operands have to be supported by the operator, they
		 * can be values, properties or other clauses.
		 *
		 * @see expcat.cql.Value
		 * @see expcat.cql.Property
		 * @memberOf expcat.cql.Clause#
		 * @public
		 */

		var setOperands = function(ops) {
			var i;
			clearOperands();
			for( i = 0; i < ops.length; ++i) {
				addOperand(ops[i]);
			}
		}

		/**
		 * Private method that adds an operand to the clause checking the operator
		 * supports it.
		 *
		 * @memberOf expcat.cql.Clause#
		 * @private
		 * @ignore
		 */

		var addOperand = function(operand) {
			if(operator && !operator.supportsType(operand.getType())) {
				throw new Error("Operand not supported by operator: " + operator.getName() + " and " + operand.getType().getName());
			}
			operands.push(operand);
		};

		/**
		 * Gets an array with all the operands registered in the clause.
		 * @return {Array} An array of objects that represents the clause operands. They
		 * can be values, properties or other clauses.
		 *
		 * @memberOf expcat.cql.Clause#
		 * @public
		 */

		var getOperands = function() {
			return operands;
		};

		/**
		 * Clears the operands of the clause (removes them).
		 *
		 * @memberOf expcat.cql.Clause#
		 * @public
		 */

		var clearOperands = function() {
			operands = [];
		};

		/**
		 * Sets if the clause will be enclosed in curly brackets (grouped).
		 * @param enclose True to put the clause in curly brackets when translated, false
		 * otherwise.
		 *
		 * @memberOf expcat.cql.Clause#
		 * @public
		 *
		 */

		var encloseInBrackets = function(enclose) {
			enclosedInBrackets = enclose;
		}

		/**
		 * Translates the clause into CQL code.
		 * @return {String} An string with the equivalent CQL code.
		 *
		 * @memberOf expcat.cql.Clause#
		 * @public
		 */

		var translate = function() {
			if( typeof operator === "undefined") {
				throw new Error("Operator not defined for translation");
			}
			if(enclosedInBrackets)
				return "(" + operator.translate(operands) + ")";
			else
				return operator.translate(operands);
		};

		/* Public API returned by the constructor */
		return {
			setOperator : setOperator,
			getOperator : getOperator,
			setOperands : setOperands,
			getOperands : getOperands,
			clearOperands : clearOperands,
			encloseInBrackets : encloseInBrackets,
			translate : translate,
			getType : getType
		};
	};

	/* Prototype */
	Clause.prototype = {
		constructor : Clause,
		getType : getType
	};

	return Clause;

}());
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
expcat.namespace("expcat.plugins.OperatorBar");

/**
 * TODO The components of expcat.plugins.* are tightly coupled, some
 * refactoring will do good.
 */

/**
 * @ignore
 */

expcat.plugins.OperatorBar = ( function() {

	/**
	 * An operator bar represents operator and operator that can be configured by the
	 * user via UI. The operator represented here joins two clauses or group of
	 * clauses (or conditions).
	 *
	 * @param id {Integer} An integer that identifies uniquely the operator bar.
	 * @param operatorSymbolMap {Object} A hash that associates operator symbols to
	 * operators.
	 * @param updateFunction {Function} The callback that will be executed when the
	 * operator changes.
	 *
	 * @name expcat.plugins.OperatorBar
	 * @constructor
	 * @public
	 * @author Jacob Almagro - ExplorerCat Project
	 */

	var OperatorBar = function(id, operatorSymbolMap, updateFunction) {

		var operatorId = id;
		var operatorsBySymbol = operatorSymbolMap;
		var updateCallback = updateFunction;

		// Operator select box.
		var selectOperator;

		/**
		 * Initializes the object.
		 * @memberOf expcat.plugins.OperatorBar#
		 * @private
		 * @ignore
		 */

		var init = function() {
			selectOperator = createSelectBox("selectOperator" + id, getMapKeys(operatorsBySymbol));
		};

		/**
		 * Creates a new select box with the given name and values.
		 * @param name {String} The name of the select box.
		 * @param values {Array} Array of text values for the select box.
		 * @return {jQuery} The configured select box.
		 *
		 * @memberOf expcat.plugins.OperatorBar#
		 * @private
		 * @ignore
		 */

		var createSelectBox = function(name, values) {
			var selectBox = $("<select></select>");
			var i;

			selectBox.attr("name", name);
			selectBox.attr("id", name);

			if(values && values.length > 0) {
				setSelectOptions(selectBox, values);
			}

			// Operator box handlers.
			selectBox.bind("change", function() {
				updateCallback();
			});

			return selectBox;
		}

		/**
		 * Sets the options of the given selection box using the values parameter.
		 * @param selectBox {jQuery} The select object whose options will be set.
		 * @param valeus {Array} An array of text values that will be used to create the
		 * options.
		 *
		 * @memberOf expcat.plugins.OperatorBar#
		 * @private
		 * @ignore
		 */

		var setSelectOptions = function(selectBox, values) {
			var optionsHTML = [];
			var currentOption;

			// Clear the options.
			selectBox.html("");

			for( i = 0; i < values.length; ++i) {
				currentOption = "<option value=\"" + i + "\">" + values[i] + "</option>";
				optionsHTML.push(currentOption);
			}

			// Add the options
			selectBox.html(optionsHTML.join(" "));
			selectBox.val(0);
		}

		/**
		 * Gets the operator selected in the condition.
		 * @return {expcat.cql.Operator} The selected operator.
		 *
		 * @memberOf expcat.plugins.OperatorBar#
		 * @public
		 */

		var getSelectedOperator = function() {
			var selectDOM = selectOperator.get(0);
			var op = selectDOM.options[selectDOM.selectedIndex].text;
			return operatorsBySymbol[op];
		}

		/**
		 * Gets the select box for the operator.
		 * @return {jQuery} The select box for the condition operator.
		 *
		 * @memberOf expcat.plugins.OperatorBar#
		 * @public
		 */

		var getOperatorSelect = function() {
			return selectOperator;
		}

		/**
		 * Gets the id of the condition bar.
		 * @return {Integer} The id that identifiers uniquely the condition in the query.
		 *
		 * @memberOf expcat.plugins.OperatorBar#
		 * @public
		 */

		var getId = function() {
			return operatorId;
		}

		/**
		 * Auxiliary method that returns the keys of the given hash.
		 * @memberOf expcat.plugins.OperatorBar#
		 * @private
		 * @ignore
		 */

		var getMapKeys = function(map) {
			var k;
			var keys = [];
			for(k in map) {
				keys.push(k);
			}
			return keys;
		};

		/**
		 * Translates the operator bar into CQL.
		 * @return {String} The equivalent CQL code for the operator.
		 *
		 * @memberOf expcat.plugins.OperatorBar#
		 * @public
		 */

		var translateToCQL = function() {
			return getSelectedOperator().getSymbol();
		}

		// Initializes the object.
		init();

		/* Public API returned by the constructor */
		return {
			getSelectedOperator : getSelectedOperator,
			getOperatorSelect : getOperatorSelect,
			getId : getId,
			translateToCQL : translateToCQL
		};
	};

	/* Prototype methods and properties */
	OperatorBar.prototype = {
		constructor : OperatorBar
	};

	return OperatorBar;

}());
expcat.namespace("expcat.plugins.QueryComposerUI");

/**
 * TODO The components of expcat.plugins.* are tightly coupled, some
 * refactoring will do good.
 */

/**
 * @ignore
 */

expcat.plugins.QueryComposerUI = ( function() {

	/**
	 * Builds the user interface DOM for composing CQL queries. This implementation
	 * allows only one level of nesting. The composer provides a method to build a
	 * DOM tree that can be attached to the document.
	 *
	 * The constructor creates a new composer.
	 *
	 * @param nestingDivs {[jQuery]} An array containing the nesting divs (sorted
	 * from outer to inner) that will be cloned when building the DOM tree for the
	 * user interface.
	 *
	 * @name expcat.plugins.QueryComposerUI
	 * @constructor
	 * @author Jacob Almagro - ExplorerCat Project.
	 */

	var QueryComposerUI = function(nestingDivs) {

		// The DOM elements that will used as nested containers.
		nestingDivs = nestingDivs || [$("<div></div>")];

		// Contains all the components of the UI (operator selects, conditions,etc.).
		var uiComponents = [];

		// Indicates the status of each component (true = nested).
		var isComponentNested = [];

		/**
		 * Adds a new UI component to the composer.
		 * @param component {jQuery} The UI component that will be
		 * added to the composer.
		 * @param isNested {Boolean} True if the component is nested (false by default).
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var addUIComponent = function(component, isNested) {
			// Notice we convert the element into a jQuery element.
			uiComponents.push(component);
			isComponentNested.push(isNested || false);
		};

		/**
		 * Gets the UI component for the given id.
		 * @param id {String} The identifier of the component.
		 * @return {jQuery} The associated component or null if not found.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var getUIComponent = function(id) {
			var index = getIndexForComponentId(id);
			if(index === -1)
				return null;
			else
				return uiComponents[index];
		}

		/**
		 * Removes a set of consecutive components from the composer.
		 * @param baseComponentId {String} The identifier of the first component to be
		 * removed.
		 * @param removingWindowOffset {Integer} The offset of the removing window. This
		 * A value of 0 will only remove the base component, -1 will also remove the
		 * previous one and so on.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var removeUIComponents = function(baseComponentId, removingWindowOffset) {
			var index = getIndexForComponentId(baseComponentId);
			removingWindowOffset = removingWindowOffset || 0;
			var numElementsToRemove = Math.abs(removingWindowOffset) + 1;

			// If the id doesn't exist we leave.
			if(index === -1)
				return;

			if(removingWindowOffset < 0) {
				index = index + removingWindowOffset;
				if(index < 0)
					index = 0;
			}

			uiComponents.splice(index, numElementsToRemove);
			isComponentNested.splice(index, numElementsToRemove);
		};

		/**
		 * Gets the number of components registered in the composer.
		 * @return {Integer} Number of registered components.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var getNumUIComponents = function() {
			return uiComponents.length;
		}

		/**
		 * Private method that translates a component id into an array index.
		 * @return {Integer} The index for the given id or -1 if not found.
		 *
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUI#
		 */

		var getIndexForComponentId = function(id) {
			var i;
			for( i = 0; i < uiComponents.length; ++i) {
				if(uiComponents[i].attr("id") === id)
					return i;
			}
			return -1;
		};

		/**
		 * Sets the nesting level (true/false) of the given component.
		 * @param componentId {String} The identifier of the component.
		 * @param isNested {Boolean} True if the component is nested, false otherwise.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var setComponentNesting = function(componentId, isNested) {
			var index = getIndexForComponentId(componentId);
			isComponentNested[index] = isNested;
		};

		/**
		 * Sets the nesting level (true/false) of the given component and all the
		 * component within the given radius around it.
		 * @param componentId {String} The identifier of the component.
		 * @param radius {Integer} Number of components above/below that will be
		 * affected.
		 * @param areNested {Boolean} True if the components are nested, false otherwise.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var setComponentNestingWithinRadius = function(componentId, radius, areNested) {
			var index = getIndexForComponentId(componentId);
			var i;

			isComponentNested[index] = areNested;
			i = index - radius;
			if(i >= 0) {
				while(i < index) {
					isComponentNested[i] = areNested;
					i++;
				}
			}
			i = index + radius;
			if(i < isComponentNested.length) {
				while(i > index) {
					isComponentNested[i] = areNested;
					i--;
				}
			}
		};

		/**
		 * Applies the given function to the array of nesting flags.
		 * The function is in charge of correcting the values of this array.
		 * @param correctionFunction {Function} Function that will correct any incoherent
		 * nesting.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var applyNestingCorrectorFunction = function(correctorFunction) {
			correctorFunction(isComponentNested);
		}

		/**
		 * Checks if the given component is nested.
		 * @param id {String} Identifier of the component.
		 * @return True if the component is nested.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var checkComponentIsNested = function(id) {
			var index = getIndexForComponentId(id);
			if(index === -1) {
				return false;
			}
			else {
				return isComponentNested[i];
			}
		}

		/**
		 * Checks if the component that precedes the given one is nested.
		 * @param id {String} Identifier of the component.
		 * @return {Boolean} True if the preceding component is nested.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var checkPreviousComponentIsNested = function(id) {
			var index = getIndexForComponentId(id);
			if(index === -1 || index === 0) {
				return false;
			}
			else {
				return isComponentNested[index - 1];
			}
		}

		/**
		 * Checks if the component that follows the given one is nested.
		 * @param id {String} Identifier of the component.
		 * @return {Boolean} True if the following component is nested.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var checkNextComponentIsNested = function(id) {
			var index = getIndexForComponentId(id);
			if(index === -1 || index === isComponentNested.length) {
				return false;
			}
			else {
				return isComponentNested[index + 1];
			}
		}

		/**
		 * Gets the number of consecutive nested components, starting to count from the
		 * given one.
		 * @param id {String} Identifier of the component.
		 * @return {Integer} The number of consecutive nested components that follow the
		 * given one.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var getNumConsecutiveNestedComponents = function(id) {
			var index = getIndexForComponentId(id);
			var i;

			if(index === -1) {
				return 0;
			}

			for( i = index; i < isComponentNested.length; ++i) {
				if(!isComponentNested[i])
					return i - index;
			}

			return i - index - 1;
		}

		/**
		 * Builds a DOM tree, attaching all the components registered in the composer
		 * (with the proper configuration of nested containers) to the given root.
		 * @param root {jQuery} The element that will be used as the root of the tree.
		 * @return {jQuery} The root element of the DOM tree, ready to be
		 * attached to the document.
		 *
		 * @memberOf expcat.plugins.QueryComposerUI#
		 * @public
		 */

		var buildDOMTreeForComponents = function(root) {
			root = root || "<div></div>";
			var wasPreviousNested = false;
			var currentContainer = root;
			root = $(root);
			var i;

			for( i = 0; i < uiComponents.length; ++i) {
				if(!wasPreviousNested && isComponentNested[i]) {
					currentContainer = createNestingContainer();
					root.append(currentContainer);
					currentContainer = getInnerChild(currentContainer);
					currentContainer.append(uiComponents[i]);
				}
				else if(wasPreviousNested && !isComponentNested[i]) {
					currentContainer = root;
					currentContainer.append(uiComponents[i]);
				}
				else {
					currentContainer.append(uiComponents[i]);
				}
				wasPreviousNested = isComponentNested[i];
			}

			return root;
		};

		/**
		 * Private method that creates the nesting container where nested components will
		 * be appended.
		 * @return {jQuery} The root of the DOM subtree that represent the
		 * containers.
		 *
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUI#
		 */

		var createNestingContainer = function() {
			var i;
			var childContainer = null;
			var root = $(nestingDivs[0]).clone();
			var nestingContainer = root;

			for( i = 1; i < nestingDivs.length; ++i) {
				childContainer = $(nestingDivs[i]).clone();
				nestingContainer.append(childContainer);
				nestingContainer = childContainer;
			}

			return root;
		};

		/**
		 * Gets an array of boolean flags that indicates if a component is nested (true)
		 * or not (false).
		 * @return {[Boolean]} An array of booleans that specifies which components are
		 * nested (true)
		 */

		var getNestingFlags = function() {
			return isComponentNested;
		}

		/**
		 * Gets the deepest child of the given root. In case of multiple children, only
		 * the first one is considered.
		 * @return {jQuery} The deepest child of the given element.
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUI#
		 */

		var getInnerChild = function(root) {
			var children = root.children();
			while(children.length !== 0) {
				root = $(children[0]);
				children = root.children();
			}

			return root;
		};
						
		/* Public API returned by the constructor */
		return {
			addUIComponent : addUIComponent,
			removeUIComponents : removeUIComponents,
			getUIComponent : getUIComponent,
			getNumUIComponents : getNumUIComponents,
			setComponentNesting : setComponentNesting,
			setComponentNestingWithinRadius : setComponentNestingWithinRadius,
			applyNestingCorrectorFunction : applyNestingCorrectorFunction,
			checkComponentIsNested : checkComponentIsNested,
			checkPreviousComponentIsNested : checkPreviousComponentIsNested,
			checkNextComponentIsNested : checkNextComponentIsNested,
			getNumConsecutiveNestedComponents : getNumConsecutiveNestedComponents,
			getNestingFlags : getNestingFlags,
			buildDOMTreeForComponents : buildDOMTreeForComponents
		};
	};

	/* Prototype methods and properties */
	QueryComposerUI.prototype = {
		constructor : QueryComposerUI
	};

	return QueryComposerUI;

}());
expcat.namespace("expcat.plugins.QueryComposerUIManager");

/**
 * TODO The components of expcat.plugins.* are tightly coupled, some refactoring
 * will do good.
 */

/**
 * @ignore
 */

expcat.plugins.QueryComposerUIManager = (function() {

	// Aliases
	var OPERATORS = expcat.cql.Operator;
	var Property = expcat.cql.Property;
	var ConditionBar = expcat.plugins.ConditionBar;
	var OperatorBar = expcat.plugins.OperatorBar;
	var QueryBuilder = expcat.cql.QueryBuilder;

	/**
	 * Manages the UI for the query composer component. This object is in charge
	 * of directing the UI creation and sync.
	 * 
	 * The constructor creates a new manager.
	 * 
	 * @name expcat.plugins.QueryComposerUIManager
	 * @constructor
	 * @author Jacob Almagro - ExplorerCat Project.
	 */

	var QueryComposerUIManager = function(ops, properties, aliases, nestedDivs,
			containerId) {

		var operatorMap = ops;
		var propertyMap = properties;
		var propertyAliases = aliases;
		var currentId = 0;
		var uiContainerId = containerId;
		var queryElements = [];
		var ui = new expcat.plugins.QueryComposerUI(nestedDivs);

		var logicalOperatorMap = {
			"AND" : OPERATORS.AND,
			"OR" : OPERATORS.OR
		};

		/**
		 * Callback function in charge of fixing any incoherent nesting for
		 * operators (it is passed to the QueryComposerUI). Notice that this
		 * method modifies the array given as a parameter.
		 * 
		 * @param nestingFlags
		 *            {[Boolean]} The array of flags that defines which
		 *            components are nested (true).
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var brokenOpCorrectorFunction = function(nestingFlags) {
			var i;
			for (i = 1; i < nestingFlags.length; i += 2) {
				if (!nestingFlags[i - 1] && nestingFlags[i]
						|| !nestingFlags[i + 1] && nestingFlags[i]) {
					nestingFlags[i] = false;
				}
			}
		};

		/**
		 * Callback function that is executed when a condition is removed by the
		 * user.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var removeConditionHandler = function() {
			var conditionPanelId = "cnd" + $(this).attr("id").substring(3);
			var elementIndex = getIndexForQueryElement(parseInt($(this).attr(
					"id").substring(3)));

			if (!ui.checkPreviousComponentIsNested(conditionPanelId)
					&& ui.getNumConsecutiveNestedComponents(conditionPanelId) > 1) {
				ui.removeUIComponents(conditionPanelId, 1);
				queryElements.splice(elementIndex,
						elementIndex < queryElements.length ? 2 : 1);
			} else {
				ui.removeUIComponents(conditionPanelId, -1);
				if (elementIndex > 0)
					queryElements.splice(elementIndex - 1, 2);
				else
					queryElements.splice(elementIndex, 2);
			}

			ui.applyNestingCorrectorFunction(brokenOpCorrectorFunction);
			updateUI();
		};

		/**
		 * Callback function that is executed when the user clicks on the nest
		 * button.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var nestingHandler = function() {
			var buttonId = $(this).attr("id").substring(4);
			ui.setComponentNestingWithinRadius("op" + buttonId, 1, true);
			// $(this).css("display","none");
			// $("#ubtn" + buttonId).css("display","inline");
			updateUI();
		};

		/**
		 * Callback function that is executed when the user clicks on the unnest
		 * button.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var unnestingHandler = function() {
			var buttonId = $(this).attr("id").substring(4);
			ui.setComponentNestingWithinRadius("op"
					+ $(this).attr("id").substring(4), 1, false);
			ui.applyNestingCorrectorFunction(brokenOpCorrectorFunction);
			// $(this).css("display","none");
			// $("#nbtn" + buttonId).css("display","inline");
			updateUI();
		};

		/**
		 * Gets the index fo the given query element.
		 * 
		 * @param elementId
		 *            The identifier of the element we are looking for.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var getIndexForQueryElement = function(elementId) {
			var i;
			for (i = 0; i < queryElements.length; ++i) {
				if (queryElements[i].getId() === elementId)
					return i;
			}
			return -1;
		}

		/**
		 * Creates an operator panel that represents an OperatorBar instance.
		 * 
		 * @return {jQuery} The DOM element that represents the panel.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var createOperatorPanel = function() {

			var operatorBar = new OperatorBar(currentId, logicalOperatorMap,
					updateCQLCode);
			var operatorSelect = operatorBar.getOperatorSelect();
			var id = operatorBar.getId();
			var operatorPanel, operatorLine, operator, nestButton, unnestButton;

			queryElements.push(operatorBar);
			operator = createDIV("operator", "operator" + id);
			operatorLine = createDIV("operatorLine", "opLine" + id);
			operatorPanel = createDIV("operatorPanel", "op" + id);
			nestButton = createDIV("nestButton", "nbtn" + id);
			unnestButton = createDIV("unnestButton", "ubtn" + id);

			nestButton.bind("click", nestingHandler);
			unnestButton.bind("click", unnestingHandler);

			operatorPanel.append(operatorLine);
			operator.append(operatorSelect);
			operatorPanel.append(operator);
			operatorPanel.append(unnestButton);
			operatorPanel.append(nestButton);
			currentId++;

			return operatorPanel;
		};

		/**
		 * Creates an condition panel that represents a ConditionBar instance.
		 * 
		 * @return {jQuery} The DOM element that represents the panel.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var createConditionPanel = function() {

			var conditionBar = new ConditionBar(currentId, propertyMap,
					propertyAliases, operatorMap, updateConditionValues,
					updateCQLCode);
			conditionBar.refresh();

			var propertySelect = conditionBar.getPropertySelect();
			var operatorSelect = conditionBar.getOperatorSelect();
			var inputValues = conditionBar.getInputValues();
			var id = conditionBar.getId();
			var conditionPanel, negationDiv, propertyDiv, operatorDiv, valuesDiv, optionsDiv;
			var negationButton, helpButton, removeButton;
			var i;

			queryElements.push(conditionBar);
			conditionPanel = createDIV("conditionPanel", "cnd" + id);
			negationDiv = createDIV("negation", "negationDiv" + id);
			propertyDiv = createDIV("property", "propertyDiv" + id);
			helpButton = createDIV("helpButton", "helpProperty" + id);
			helpTooltip = createDIV("helpTooltip", "helpTooltip" + id);
			operatorDiv = createDIV("operator", "operatorDiv" + id);
			valuesDiv = createDIV("value", "valueDiv" + id);
			optionsDiv = createDIV("grouping", "optionsDiv" + id);
			negateButton = createDIV("negationButton", "neg" + id);
			removeButton = createDIV("removeButton", "rem" + id);

			for (i = 0; i < inputValues.length; ++i) {
				valuesDiv.append(inputValues[i]);
			}

			negateButton.bind("click", function() {
				if ($(this).attr("class").indexOf("Down") !== -1) {
					$(this).attr("class", "negationButton");
				} else {
					$(this).attr("class", "negationButtonDown");
				}

				conditionBar.negateCondition();
				updateCQLCode();
			});

			helpButton.tooltip({
				position : "top right",
				relative : true,
				opacity : 0.9
			});

			helpTooltip.html(conditionBar.getPropertyDescription());
			removeButton.bind("click", removeConditionHandler);

			negationDiv.append(negateButton);
			conditionPanel.append(negationDiv);
			propertyDiv.append(propertySelect);
			propertyDiv.append(helpButton);
			propertyDiv.append(helpTooltip);
			conditionPanel.append(propertyDiv);
			operatorDiv.append(operatorSelect);
			conditionPanel.append(operatorDiv);
			conditionPanel.append(valuesDiv);
			optionsDiv.append(removeButton);
			conditionPanel.append(optionsDiv);
			currentId++;

			return conditionPanel;
		};

		/**
		 * Creates a new DOM element for a div container.
		 * 
		 * @return {jQuery} The DOM element that represents the div.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var createDIV = function(divClass, divId) {
			return $("<div></div>", {
				"class" : divClass,
				"id" : divId
			});
		}

		/**
		 * Updates the values of the condition with the given array.
		 * 
		 * @param conditionId
		 *            The id of the condition to update.
		 * @param values
		 *            The array of values to be set.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var updateConditionValues = function(conditionId, values) {
			var conditionPanel = ui.getUIComponent("cnd" + conditionId);
			var i;

			// TODO We don't use the empty method because it
			// removes the handlers, we need to check this is not
			// causing a memory leak.
			$(".value > input", conditionPanel).detach();
			$(".value > select", conditionPanel).detach();

			for (i = 0; i < values.length; ++i) {
				$(".value", conditionPanel).append(values[i]);
			}

		}

		/**
		 * Adds a new condition (configured with default values) to the UI.
		 * 
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 * @public
		 */

		var addCondition = function() {
			if ($("#" + uiContainerId).children().length > 0)
				ui.addUIComponent(createOperatorPanel());
			ui.addUIComponent(createConditionPanel());
			updateUI();
		};

		/**
		 * Updates and sync the UI.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var updateUI = function() {
			var root = $("#" + uiContainerId);

			// Notice the detach to avoid the problem of losing event handlers.
			root.detach();
			root.children().detach();

			$("#query-container").append(ui.buildDOMTreeForComponents(root));
			updateCQLCode();
		};

		/**
		 * Updates the CQL code shown in the UI.
		 * 
		 * @public
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 */

		var updateCQLCode = function() {
			var cqlCode = generateCQLCode();
			if ($.trim(cqlCode) === "")
				$("#cqlCode").html("Select everything...");
			else
				$("#cqlCode").html(cqlCode);

		}

		/**
		 * Generates the CQL code for all the conditions in the UI.
		 * 
		 * @return {String} The CQL code that represents all the conditions of
		 *         the UI.
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 * @public
		 */

		var generateCQLCode = function() {
			var currentElementCode;
			var wasPreviousNested = false;
			var nestingFlags = ui.getNestingFlags();
			var queryArray = [];
			var i;

			for (i = 0; i < queryElements.length; ++i) {
				currentElementCode = queryElements[i].translateToCQL();

				if (!wasPreviousNested && nestingFlags[i]) {
					queryArray.push("(");
				}

				if (wasPreviousNested && !nestingFlags[i]) {
					queryArray.push(")");
				}

				queryArray.push(currentElementCode);
				wasPreviousNested = nestingFlags[i];
			}

			if (nestingFlags[i - 1])
				queryArray.push(")");

			return queryArray.join(" ");
		};

		/**
		 * Destroys the component removing any element attached to the UI.
		 * 
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 * @public
		 */

		var destroy = function() {
			$("#" + uiContainerId).empty();
		};

		/**
		 * Method to return current query elements. This method is added to
		 * support setupUIifParametersProvided of
		 * expcat.plugins.CatalogExplorer, which enables dynamic url binding
		 * from genotype tool temporarly
		 * 
		 * @memberOf expcat.plugins.QueryComposerUIManager#
		 * @public
		 */

		var getQueryElements = function() {
			return queryElements;
		};

		/* Public API returned by the constructor */
		return {
			addCondition : addCondition,
			generateCQLCode : generateCQLCode,
			getQueryElements : getQueryElements,
			updateUI : updateUI,
			destroy : destroy
		};
	};

	/* Prototype methods and properties */
	QueryComposerUIManager.prototype = {
		constructor : QueryComposerUIManager
	};

	return QueryComposerUIManager;
}());
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
}());expcat.namespace("expcat.plugins.DataTable");

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
