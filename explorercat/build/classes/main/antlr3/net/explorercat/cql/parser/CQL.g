
/**
 * A grammar for the CQL language (Catalog Query Language). This language will
 * be used by any client of the system to retrieve data by means of an API call.
 * It is similar to SQL but with some add-ons that make life easier to the
 * front-end developer.
 * 
 * This parser will create a tree representation of the CQL script and, after
 * that, a tree walker will translate it into a set of internal classes in
 * charge of resolving the selections, simplifying the tree of expressions,
 * optimizing the queries, etc.
 * 
 * (Note this file defines the parser and the lexer).
 * 
 * Please check the developer documentation and the language reference for
 * details.
 * 
 * Developed as a part of the ExplorerCat system.
 * Depends on ANTLR 3.2
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Summer 2010
 */

grammar CQL; 

// We build an AST (Abstract Syntax Tree)  
// to represent the content of the script.
options	 
{
	language = Java;	
	output=AST;			
}

// Tokens that will be used in the AST but are
// not present in the lexer.
tokens
{
	// The root of the AST.
	CQL_SCRIPT;
	
	// Data source for the selection.
	DATA_SOURCE;
	
	// Selection definition (variables and where condition).
	SELECTION_DEFINITION;
	VARIABLES;
	CONDITION;
	
	// Selection block options.
	SELECTION_OPTIONS;
		
	// Alias definition for a selection, property, result, etc.		
	LABEL; 
	
	// To mark the arguments of any function.
	ARGUMENT;
	
	// Entity property.
	PROPERTY;
	
	// To distinguish the difference operator from the unary minus.
	MINUS;
	
	// Array type and access.
	ARRAY;	
	
	// Result definition and properties.
	RESULT_DEFINITION;
	RESULT_PROPERTIES;
	
	// Type of results.
	REGULAR_SELECTION_RESULT;
	COMBINED_SELECTION_RESULT;
	STATS_SELECTION_RESULT;	
	DISTINCT_SELECTION_RESULT;
}
 
// Package and imports for the parser.
@header  
{
	package net.explorercat.cql.parser;
	
	import java.util.Stack;	 
}
 
// Package and imports for the lexer. 
@lexer::header  
{ 
	package net.explorercat.cql.parser;
}

@members
{
	// Stack that will contain an appropiate error message for the current 
	// rule/context of the parsing.
	private Stack<String> errorMessageStack;
	private String currentErrorMessage;
	
	// We register all the error messages in this list.
    private List<String> errorMessages;   
    
    public boolean hasErrors()
    {
    	return errorMessages.size() == 0;
    }
    
    public String getCurrentErrorMessage()
	{
		return currentErrorMessage;
	}	
            
    public List<String> getErrors() 
    {
        return errorMessages;
    }
    
    // Override the error message generation.
    @Override 
   	public void displayRecognitionError(String[] tokenNames, RecognitionException e) 
    { 
        String header = getErrorHeader(e);
        String message = getErrorMessage(e, tokenNames);
        errorMessages.add(header + " " + message);
        System.out.println("ERROR DETECTED : " + header + " " + message);
    }
			
	@Override
	public String getErrorMessage(RecognitionException e, String[] tokenNames)
	{
		// Default error message.
		String errorMessage = super.getErrorMessage(e, tokenNames);
		
		if(errorMessageStack.size() > 0)
			errorMessage += " in " + errorMessageStack.peek();
		
		this.currentErrorMessage = errorMessage;
		return errorMessage;
	} 					
}

// Alter code generation so catch clause gets replaced with this code.
@rulecatch 
{	
	catch(RecognitionException re)
	{	
		reportError(re);
		throw re;
	}
} 

  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CQL Parser
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// A CQL script can have different forms:
// - A precalculated script will have a list of selections to be precalculated.
// - A script that accesses precalculated selections will have only a result block. 
// - A regular script will have a list of selections and a result block.

cqlScript
	@init 
	{
		// Initialises the stack of error messages.
		errorMessageStack = new Stack<String>();
		errorMessages = new ArrayList<String>();	
	}
	 
	:	s+=selectionBlock* r+=resultBlock*
		-> ^(CQL_SCRIPT $s* $r*)   
	; 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
// Selection block: contains the definition of a selection. It includes variable definition, limits, sorting and error
// recovery expressions but not the list of properties to be selected.
//
// A selection block can refer to: 
//	- An entity (meaning we'll be querying the data for that entity).
//	- A precalculated selection (specifying the selection name).
//	- Another selection present in the same script.
// 
// A selection can only refer to one catalog. The id of the catalog is specified by an API parameter.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

selectionBlock 
	@init 	{ errorMessageStack.push("SELECT block"); }
	@after  { errorMessageStack.pop(); }
	
	:	SELECT FROM ID l=labelDefinition sd=selectionBlockDefinition so=selectionBlockOptions ';'
		-> ^(SELECT ^(DATA_SOURCE ID) $l $sd $so)
	;
 
selectionBlockDefinition
	@init 	{ errorMessageStack.push("SELECT block definition"); }
	@after  { errorMessageStack.pop(); }
	
	:	(VARS '{' vd+=variableDefinition ( vd+=variableDefinition)* '}')? WHERE c=condition 
		-> ^(SELECTION_DEFINITION ^(VARIABLES $vd+)? ^(CONDITION $c)) 
	;
	
variableDefinition
	:	i=ID ':=' e=arithmeticExpression ';'
		-> ^(VAR $i $e)	
	;

condition
	@init 	{ errorMessageStack.push("Condition definition"); }
	@after  { errorMessageStack.pop(); }
	
	:	logicalExpression
	; 
		 
// Block options.
selectionBlockOptions
	:	(od+=selectionBlockOptionsDefinition)* 
		-> ^(SELECTION_OPTIONS $od*)
	;
		
selectionBlockOptionsDefinition
	:	sortOptions	
	|	limitOptions	
	;

// Sorting	
sortOptions
	@init 	{ errorMessageStack.push("SORT statement"); }
	@after  { errorMessageStack.pop(); }
	
	:	SORT BY i=ID
		-> ^(SORT $i)
		
	|	SORT BY i=ID (DESCENDING|DESC)
		-> ^(SORT $i DESC)
	;

// Limiters	
limitOptions
	@init 	{ errorMessageStack.push("LIMIT statement"); }
	@after  { errorMessageStack.pop(); }
	
	:	LIMIT ld+=limitDefinition+
		-> ^(LIMIT $ld+)
	;
	
limitDefinition
	:	TOP lp=limitParameters		-> ^(TOP $lp)	
	|	BOTTOM lp=limitParameters	-> ^(BOTTOM $lp)
	|	RANDOM lp=limitParameters	-> ^(RANDOM $lp)  
	; 
	
limitParameters
	: 	'('! INTEGER (','! INTEGER)? ')'!
	;

// Labels	  	  
labelDefinition	 
	:	AS! labelName		
	;	
	
labelName
	:	(i=ID|qs=QUOTED_STRING)
		-> ^(LABEL $i? $qs?)
	;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
// Result block: Defines a result (set of selected properties and variables) that can be labeled with an alias. 
// It could contain data coming from different selections.
// It can use a single selection, combine several selections by means of set operations or present a statistical value
// calculated for a property. In addition, the list of properties to be included (from the entities contained in the 
// selections) can be decorated with aliases.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

resultBlock
	@init 	{ errorMessageStack.push("RESULT block"); }
	@after  { errorMessageStack.pop(); }
	
	:	RESULT '{' a=resultDefinition '}' ';'
		-> ^(RESULT $a)	
	;
	
resultDefinition	
	@init 	{ errorMessageStack.push("RESULT definition"); }
	@after  { errorMessageStack.pop(); }
	
	:	e=resultExpression (ps=propertySelection)? (l=labelDefinition)?
		-> ^(RESULT_DEFINITION $e $ps? $l?)
	;
	
resultExpression
	:	i=ID							-> ^(REGULAR_SELECTION_RESULT $i)
	|	qs=QUOTED_STRING				-> ^(REGULAR_SELECTION_RESULT $qs)
	|	sf=setOperation					-> ^(COMBINED_SELECTION_RESULT $sf)
	|	rsf=resultStatisticalFunction	-> ^(STATS_SELECTION_RESULT $rsf)	
	| 	DISTINCT i=ID                   -> ^(DISTINCT_SELECTION_RESULT $i)
	| 	DISTINCT qs=QUOTED_STRING		-> ^(DISTINCT_SELECTION_RESULT $qs)
	;	

resultStatisticalFunction	
	@init 	{ errorMessageStack.push("RESULT statistical function"); }
	@after  { errorMessageStack.pop(); }

	:	AVG 	'(' i=ID ',' p=ID ')' -> ^(AVG ^(ARGUMENT $i) ^(PROPERTY $p)) 
	|	MEDIAN	'(' i=ID ',' p=ID ')' -> ^(MEDIAN ^(ARGUMENT $i) ^(PROPERTY $p)) 
	|	MIN		'(' i=ID ',' p=ID ')' -> ^(MIN ^(ARGUMENT $i) ^(PROPERTY $p)) 
	|	MAX		'(' i=ID ',' p=ID ')' -> ^(MAX ^(ARGUMENT $i) ^(PROPERTY $p)) 
	|	STDDEV	'(' i=ID ',' p=ID ')' -> ^(STDDEV ^(ARGUMENT $i) ^(PROPERTY $p)) 
	|	VAR		'(' i=ID ',' p=ID ')' -> ^(VAR ^(ARGUMENT $i) ^(PROPERTY $p)) 
	|	SUM		'(' i=ID ',' p=ID ')' -> ^(SUM ^(ARGUMENT $i) ^(PROPERTY $p)) 
	|	COUNT	'(' i=ID ',' p=ID ')' -> ^(COUNT ^(ARGUMENT $i) ^(PROPERTY $p))
	;
 
// Set operations over selections.
setOperation
	@init 	{ errorMessageStack.push("RESULT set operation"); }
	@after  { errorMessageStack.pop(); }
	
	:	UNION^ setOperationParameters
	|	INTERSECTION^ setOperationParameters
	|	DIFFERENCE^ setOperationParameters 	
	;
	
setOperationParameters
	:	'(' a=setOperationAttribute ',' b=setOperationAttribute ')'
		-> $a $b
	;
		
setOperationAttribute
	:	(i=ID|qs=QUOTED_STRING)	-> ^(ARGUMENT $i? $qs?)
	|	setOperation
	;	
	
// Rroperty selection for results.
propertySelection
	@init 	{ errorMessageStack.push("RESULT properties definition"); }
	@after  { errorMessageStack.pop(); }
	
	:	'[' f=propertyDefinition (',' rf+=propertyDefinition)* ']'
		-> ^(RESULT_PROPERTIES $f $rf*)
	;		

propertyDefinition
	:	i=ID (l=labelDefinition)?
		-> ^(ARGUMENT $i $l?)
	;	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
// Hierarchy of expressions (by precedence, closer to the bottom means higher precedence).
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

expression
	:	logicalExpression
	;
	
// Logical expressions (OR, AND, NOT is a special case with top precedence)		
logicalExpression
	:	orExpression
	;
		
orExpression
	@init 	{ errorMessageStack.push("OR expression"); }
	@after  { errorMessageStack.pop();}
	
	:	andExpression (OR^ andExpression)*
	;
	
andExpression
	@init 	{ errorMessageStack.push("AND expression"); }
	@after  { errorMessageStack.pop();}
	
	:	relationalExpression (AND^ relationalExpression)* 
	;
	
// Relational expressions (=, >=, <=, >, <)
relationalExpression
	@init 	{ errorMessageStack.push("Relational expression"); }
	@after  { errorMessageStack.pop();}
	
	:	notExpression ((EQ|GE|LE|GT|LT)^ notExpression)?			
	;

notExpression
	@init 	{ errorMessageStack.push("NOT expression"); }
	@after  { errorMessageStack.pop();}
	
	:	(NOT^)? arithmeticExpression
	;
	
// Aritmetic expressions (+, -, *, /, ^, log, ln, sqrt)
arithmeticExpression
	@init 	{ errorMessageStack.push("Arithmetic expression"); }
	@after  { errorMessageStack.pop();}
	
	:	additiveExpression
	;
	
additiveExpression
	:	multiplicativeExpression ((PLUS|DIFF)^ multiplicativeExpression)*
	;
	
multiplicativeExpression
	:	powerExpression ((MULT|DIV)^ powerExpression)*
	;		

powerExpression
	:	minusExpression (POWER^ minusExpression)*
	;
	
minusExpression
  : DIFF fe=functionExpression -> ^(MINUS $fe)
  | functionExpression
  ;


// Functions (mathematical, statistical, strings, range, atom and arrays) 
functionExpression
	:	mathematicalFunction
	|	statisticalFunction
	|	rangeFunction	
	|	stringFunction	
	|	arrayDefinition		// Not defined in atom to avoid recursive arrays.
	;
		
mathematicalFunction
	@init 	{ errorMessageStack.push("Mathematical function definition"); }
	@after  { errorMessageStack.pop();}
	
	
	:	(SQRT|LOG|LN|ABS)^ '('! arithmeticExpression ')'!
	;
	
statisticalFunction
	@init 	{ errorMessageStack.push("Statistical function definition"); }
	@after  { errorMessageStack.pop();}
	
	:	AVG^ 	statisticalFunctionParameters
	|	MEDIAN^	statisticalFunctionParameters
	|	MIN^	statisticalFunctionParameters
	|	MAX^	statisticalFunctionParameters
	|	STDDEV^	statisticalFunctionParameters
	|	VAR^	statisticalFunctionParameters
	|	SUM^	statisticalFunctionParameters
	|	COUNT^ 	statisticalFunctionParameters
	;
	
statisticalFunctionParameters
	:	'(' i=ID (',' ln=labelName)? ')'
		-> ^(ARGUMENT $i) $ln?
	;
	
// Functions that use strings.
stringFunction
	@init 	{ errorMessageStack.push("String function definition"); }
	@after  { errorMessageStack.pop();}
			
	:	containsFunction
	;
						
// If e1 is a string it behaves like matches.
// If e1 is an array it will check if it contains the elements of e2.		
containsFunction
	: 	e1=matchesFunction (CONTAINS^ e2=matchesFunction)?  
	;	
	
matchesFunction
	:	e1=startsFunction (MATCHES^ e2=startsFunction)?
	;
	
startsFunction
	:	e1=concatFunction (STARTSWITH^ e2=concatFunction)?								
	;
	
concatFunction
	:	e1=arrayOperation (CONCAT^ e2=arrayOperation)?
	;	

// Arrays
arrayDefinition
	: '[' a=atom? (',' ra+=atom)* ']' -> ^(ARRAY $a? $ra*)
	;
				
arrayOperation
	@init 	{ errorMessageStack.push("Array access"); }
	@after  { errorMessageStack.pop();}
	
	:	arrayAccess	
	;
	
arrayLength
	:	e=arrayAccess ('.'! LENGTH^ )? 
	;
		
arrayAccess
	:	e=atom ('['^ i=INTEGER ']')?  	// We mark the array access with the '[' token.
	;				 			

// This expression has a very important role since it will be used to optimize queries.	
rangeFunction
	@init 	{ errorMessageStack.push("RANGE definition"); }
	@after  { errorMessageStack.pop(); }
	
	:	RANGE e=expression ((openType='[') | (openType='(')) rangeStart=expression ',' rangeEnd=expression (closeType=')'| closeType=']')
		-> ^(RANGE $e $openType $closeType $rangeStart $rangeEnd) 
	;	
	
// Leaves of the parsing tree. 
atom
	: 	i=ID
	| 	INTEGER
	| 	REAL
	| 	QUOTED_STRING
	| 	TRUE
	| 	FALSE  	
	|	NULL	  
	|	'{' e=expression '}' RECOVERY '(' er=expression ')'  -> ^(RECOVERY $er $e)
	|	'(' e=expression ')' -> $e
	;

/**
// Expression that will be used in case of error when evaluating the expression on its left.
errorRecovery 
	@after  { errorMessageStack.pop();}
	@init 	{ errorMessageStack.push("RECOVERY expression definition"); }
	
	:	-> ^(RECOVERY $e) 
	;
*/

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Lexer	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
// Selection block header. 
SELECT		: S E L E C T;

// Selection block definition.
VARS		: V A R S;  			// Local variables.
WHERE		: W H E R E;
FROM		: F R O M;
MATCHES		: M A T C H E S;
CONTAINS	: C O N T A I N S;
STARTSWITH	: S T A R T S W I T H;
RANGE		: R A N G E F O R;
CONCAT		: C O N C A T;
AVG			: M E A N;
MEDIAN		: M E D I A N;
VAR			: V A R;
MIN			: M I N;
MAX			: M A X;
STDDEV		: S T D D E V;
SUM			: S U M; 
COUNT		: C O U N T;
 
// Selection block options.
SORT		: S O R T;
BY			: B Y;
DESCENDING	: D E S C E N D I N G;
DESC		: D E S C;
LIMIT		: L I M I T;		
TOP			: T O P;
BOTTOM		: B O T T O M;
RANDOM		: R A N D O M;
AS			: A S;

// Result block.
RESULT		: R E S U L T;
DISTINCT  	: D I S T I N C T;

// Assignment
ASSIGN		: ':';

// Set operators
UNION			: U N I O N;
INTERSECTION	: I N T E R S E C T I O N;
DIFFERENCE		: D I F F E R E N C E;	

// Logical operators. 
AND			: A N D;
OR			: O R;
NOT			: N O T;

// Relational operators.
EQ			: '=';
GE			: '>=';
LE			: '<=';
GT			: '>';
LT			: '<';

// Numerical operators.
PLUS		: '+';			// Also string concatenation.
DIV			: '/';
MULT		: '*';
DIFF		: '-';
POWER		: '^';
SQRT		: S Q R T;
LOG			: L O G;		// Base 10.
LN			: L N;			// Neperian
ABS			: A B S;

// Array length
LENGTH		: L E N G T H;

// Error recovery
RECOVERY	: R E C O V E R Y;

// Boolean literals
TRUE	: T R U E;
FALSE	: F A L S E;

// Null literal
NULL	: N U L L;

// Date Literals [year-month-day]
DATE	: '{' NUMBER NUMBER NUMBER NUMBER '-' NUMBER NUMBER '-' NUMBER NUMBER '}';

// Identifiers.
ID			: ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'-'|'_'|'+')*; 

// Strings (TODO Manage escaped sequences). 
QUOTED_STRING 
	:	'"' (~('"'|'\n'|'\r'))* '"' 
	|	'\'' (~('\''|'\n'|'\r'))* '\''  
	;
	
// Numbers (integer and real).
INTEGER		: NUMBER+;
 
REAL
	:   NUMBER+ '.' NUMBER* EXPONENT?  
    |   '.' NUMBER+ EXPONENT?  
    |   NUMBER+ EXPONENT    
    ;        
        
fragment EXPONENT    
    :	( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ 
    ;

fragment NUMBER
	:	('0' .. '9')
	;

// Removing comments (only line comments).	  
LINE_COMMENT
    :   '//' ~('\n'|'\r')*  ('\r\n' | '\r' | '\n')?  { $channel=HIDDEN; }
    ;
    
// Removing blank spaces
BLANK
	:	('\t'|' '|'\n'|'\r') {$channel=HIDDEN;}
	;	   	
    
// Keywords are not case sensitive.
fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');
	