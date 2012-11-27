package net.explorercat.cql.types;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;

/**
 * Am enumeration for the CQL type system. In CQL we have integral and marker
 * types. The later are special cases of the former, used for specific purposes
 * (mainly optimisation).
 * 
 * Integral types: BOOLEAN, INTEGER, REAL, DATE, ARRAY, NULL, STRING.
 * 
 * Marker types : TEXT, ARRAY_INTEGER, ARRAY_REAL, ARRAY_BOOLEAN, ARRAY_DATE,
 * ARRAY_STRING, ARRAY_TEXT, ARRAY_NULL.
 * 
 * We prefer abstract methods than static methods using switches (error prone
 * when a adding a new type).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date June 2010
 */

public enum DataType {

	BOOLEAN {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_BOOLEAN;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "BOOLEAN";
		}

		@Override
		public boolean isMarkerType() {
			return false;
		}

		@Override
		public DataType translateToIntegralType() {
			return BOOLEAN;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			return new BooleanValue(Boolean.parseBoolean(valueAsText));
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Boolean";
		}
	},
	INTEGER {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_INTEGER;
		}

		@Override
		public boolean isNumericScalar() {
			return true;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "INT";
		}

		@Override
		public boolean isMarkerType() {
			return false;
		}

		@Override
		public DataType translateToIntegralType() {
			return INTEGER;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			return new IntegerValue(Integer.parseInt(valueAsText));
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Integer";
		}
	},
	REAL {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_REAL;
		}

		@Override
		public boolean isNumericScalar() {
			return true;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "FLOAT";
		}

		@Override
		public boolean isMarkerType() {
			return false;
		}

		@Override
		public DataType translateToIntegralType() {
			return REAL;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			return new RealValue(Float.parseFloat(valueAsText));
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Real";
		}
	},
	DATE {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_DATE;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "DATE";
		}

		@Override
		public boolean isMarkerType() {
			return false;
		}

		@Override
		public DataType translateToIntegralType() {
			return DATE;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			int year = Integer.parseInt(valueAsText.substring(0, 4));
			int month = Integer.parseInt(valueAsText.substring(5, 7));
			int day = Integer.parseInt(valueAsText.substring(8, 10));
			return new DateValue(year, month, day);
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Date";
		}
	},
	ARRAY {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "VARCHAR(127)";
		}

		@Override
		public boolean isMarkerType() {
			return false;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			// Should never happen.
			throw new RuntimeException("Parsing values for generic array type");
		}

		@Override
		public DataType getContainedType() {
			// Should never happen.
			throw new RuntimeException(
					"Checking contained type for generic array type");
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return null;
		}
	},
	NULL {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_NULL;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "NULL";
		}

		@Override
		public boolean isMarkerType() {
			return false;
		}

		@Override
		public DataType translateToIntegralType() {
			return NULL;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			return new NullValue();
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return null;
		}
	},
	STRING {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_STRING;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "VARCHAR(127)";
		}

		@Override
		public boolean isMarkerType() {
			return false;
		}

		@Override
		public DataType translateToIntegralType() {
			return STRING;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			return new StringValue(valueAsText);
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "String";
		}
	},

	// Marker types

	// When specifying catalogs (CompactCat format) Text refers
	// to long texts (no limit) while string to text up to 512 chars.
	// When using the CQL language any kind of text is treated as String
	// (you can NOT use the TEXT type, it only intended to specify the
	// type of attributes in a catalog).

	TEXT {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_TEXT;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return false;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "TEXT";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return STRING;
		}

		@Override
		public DataValue parseValue(String valueAsText) {
			return new StringValue(valueAsText);
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Text";
		}
	},

	// Concrete array types.

	ARRAY_BOOLEAN {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_BOOLEAN;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "BOOLEAN";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataType getContainedType() {
			return BOOLEAN;
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Array<Boolean>";
		}
	},

	ARRAY_INTEGER {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_INTEGER;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return true;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "INT";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataType getContainedType() {
			return INTEGER;
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Array<Integer>";
		}
	},
	ARRAY_REAL {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_REAL;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return true;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "FLOAT";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataType getContainedType() {
			return REAL;
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Array<Real>";
		}
	},
	ARRAY_STRING {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_STRING;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "VARCHAR(127)";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataType getContainedType() {
			return STRING;
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Array<String>";
		}
	},
	ARRAY_TEXT {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_TEXT;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "TEXT";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataType getContainedType() {
			return STRING;
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Array<Text>";
		}
	},
	ARRAY_DATE {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_DATE;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "DATE";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataType getContainedType() {
			return DATE;
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {
			return "Array<Date>";
		}
	},
	ARRAY_NULL {
		@Override
		public DataType getEquivalentArrayType() {
			return ARRAY_NULL;
		}

		@Override
		public boolean isNumericScalar() {
			return false;
		}

		@Override
		public boolean isNumericArray() {
			return false;
		}

		@Override
		public boolean isArray() {
			return true;
		}

		@Override
		public String getEquivalentMySQLType() {
			return "NULL";
		}

		@Override
		public boolean isMarkerType() {
			return true;
		}

		@Override
		public DataType translateToIntegralType() {
			return ARRAY;
		}

		@Override
		public DataType getContainedType() {
			return NULL;
		}

		@Override
		public String getEquivalentDictionaryDefintionType() {

			return null;
		}
	};

	/**
	 * Provides the equivalent array type that would contain this type.
	 */

	public abstract DataType getEquivalentArrayType();

	/**
	 * Checks if the type is a numeric scalar (integer or real).
	 */

	public abstract boolean isNumericScalar();

	/**
	 * Checks if the type is a numeric array (array of integers or reals).
	 */

	public abstract boolean isNumericArray();

	/**
	 * Checks the type is an array.
	 */

	public abstract boolean isArray();

	/**
	 * Translates a type into the equivalent MySQL type. If it's not possible to
	 * translate the type (not supported by MySQL), an exception is raised.
	 */

	public abstract String getEquivalentMySQLType();

	/**
	 * Checks if a type is only a "marker" type and will be treated as a
	 * different one (e.g. the TEXT type will be always treated as STRING).
	 */

	public abstract boolean isMarkerType();

	/**
	 * Translates a type into its equivalent integral type (e.g. TEXT becomes
	 * STRING). Notice that the integral type of any kind of array is ARRAY
	 * (e.g. ARRAY_STRING becomes ARRAY).
	 */

	public abstract DataType translateToIntegralType();

	/**
	 * Gets the contained type. For non-array types the conversion is trivial
	 * (i.e. the same type), arrays will return the data type they are holding
	 * (e.g. ARRAY_STRING returns STRING). As a result this method has to be
	 * override by array types.
	 */

	public DataType getContainedType() {
		return translateToIntegralType();
	}

	/**
	 * Parses a data value from a textual representation. Note that any array
	 * type will delegate the parsing to its contained type. This means that
	 * Array<Integer> won't parse arrays of integers from a string but just
	 * parse from a textual representation individual integers. The consequence
	 * is that no arrays can be directly parsed as a chunk.
	 * 
	 * @param valueAsText
	 *            A textual representation of the value.
	 * @return A data value object (of the appropriate type) containing the
	 *         value parsed from the given parameter.
	 */

	public DataValue parseValue(String valueAsText) {
		// Default implementation for concrete array types.
		return getContainedType().parseValue(valueAsText);
	}

	/**
	 * Gets an array containing containing only the CQL integral types.
	 */

	public static DataType[] getIntegralTypes() {
		DataType[] integralTypes = { BOOLEAN, INTEGER, REAL, DATE, ARRAY, NULL,
				STRING };
		return integralTypes;
	}

	/**
	 * Translates a type into the equivalent Dictionary defintion type..
	 */

	public abstract String getEquivalentDictionaryDefintionType();

	/**
	 * Gets an array containing containing only the CQL integral types.
	 */

	public static DataType[] getDictionaryDefintionTypes() {
		DataType[] integralTypes = { BOOLEAN, INTEGER, REAL, DATE, STRING,
				TEXT, ARRAY_BOOLEAN, ARRAY_INTEGER, ARRAY_REAL, ARRAY_DATE,
				ARRAY_STRING, ARRAY_TEXT };
		return integralTypes;
	}

}
