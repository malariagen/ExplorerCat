package net.explorercat.cql.expressions;

/**
 * Supported expression types by the CQL language.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jun 2010
 */

public enum ExpressionType
{
    ADDITION
    {
	@Override
	public String getShortName()
	{
	    return "+";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },

    ABS
    {
	@Override
	public String getShortName()
	{
	    return "ABS";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    AND
    {
	@Override
	public String getShortName()
	{
	    return "&";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    AVG
    {
	@Override
	public String getShortName()
	{
	    return "AVG";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    LITERAL
    {
	@Override
	public String getShortName()
	{
	    return "LIT";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    CONCATENATION
    {
	@Override
	public String getShortName()
	{
	    return "CONC";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    CONTAINS
    {
	@Override
	public String getShortName()
	{
	    return "CONT";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    MATCHES_STRING
    {
	@Override
	public String getShortName()
	{
	    return "MATCH";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    COUNT
    {
	@Override
	public String getShortName()
	{
	    return "#";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    DIFFERENCE
    {
	@Override
	public String getShortName()
	{
	    return "-";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    DIVISION
    {
	@Override
	public String getShortName()
	{
	    return "/";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    EQUAL
    {
	@Override
	public String getShortName()
	{
	    return "=";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    GREATER_OR_EQUAL
    {
	@Override
	public String getShortName()
	{
	    return ">=";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    GREATER
    {
	@Override
	public String getShortName()
	{
	    return ">";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    LESS
    {
	@Override
	public String getShortName()
	{
	    return "<";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    LESS_OR_EQUAL
    {
	@Override
	public String getShortName()
	{
	    return "<=";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    LOG
    {
	@Override
	public String getShortName()
	{
	    return "LOG10";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    LN
    {
	@Override
	public String getShortName()
	{
	    return "LN";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    MAX
    {
	@Override
	public String getShortName()
	{
	    return "MAX";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    MIN
    {
	@Override
	public String getShortName()
	{
	    return "MIN";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    MINUS
    {
	@Override
	public String getShortName()
	{
	    return "-";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    MEDIAN
    {
	@Override
	public String getShortName()
	{
	    return "MED";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    MULTIPLICATION
    {
	@Override
	public String getShortName()
	{
	    return "*";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    NOT
    {
	@Override
	public String getShortName()
	{
	    return "!";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    OR
    {
	@Override
	public String getShortName()
	{
	    return "|";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    POWER
    {
	@Override
	public String getShortName()
	{
	    return "^";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    RANGE
    {
	@Override
	public String getShortName()
	{
	    return "RG";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    REFERENCE
    {
	@Override
	public String getShortName()
	{
	    return "REF";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    SQRT
    {
	@Override
	public String getShortName()
	{
	    return "SQRT";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    STDEV
    {
	@Override
	public String getShortName()
	{
	    return "STDEV";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    SUM
    {
	@Override
	public String getShortName()
	{
	    return "SUM";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    VARIABLE
    {
	@Override
	public String getShortName()
	{
	    return "VAR";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    VARIANCE
    {
	@Override
	public String getShortName()
	{
	    return "VARNC";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    STARTS_WITH
    {
	@Override
	public String getShortName()
	{
	    return "STARTS";
	}

	@Override
	public boolean isLogical()
	{
	    return true;
	}
    },
    ARRAY_ACCESS
    {
	@Override
	public String getShortName()
	{
	    return "[i]";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    },
    ARRAY_LENGTH
    {
	@Override
	public String getShortName()
	{
	    return "LENGTH";
	}

	@Override
	public boolean isLogical()
	{
	    return false;
	}
    };

    /**
     * Checks if the expression is a logical expression (will check a condition
     * producing true or false).
     * 
     * @return True if the expression is a logical expression, false otherwise.
     */

    public abstract boolean isLogical();

    /**
     * Gets a shorter string representation of the type.
     * 
     * @return A short string representation of the expression type.
     */

    public abstract String getShortName();
}
