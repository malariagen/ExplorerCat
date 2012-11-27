
/**
 * A grammar for the CompactCat DIC file format. Notice the goal of this grammar
 * is to parse and build an AST for the dictionary definition of the catalog.
 * The actual data will be retreived by the parser implemented by the CompactCatData
 * grammar.
 *
 * (Note this file defines the parser and the lexer).
 *  
 * Developed as a part of the ExplorerCat system.
 * Depends on ANTLR 3.2
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date January 2011
 */
 
grammar CompactCatDictionary;

options 
{
  language = Java;
  output = AST;
}

// Tokens that will be used in the AST but are not present in the lexer.
tokens
{
	PROPERTIES;
	RELEASE_DATE;
	NAME;
	ENTITY;	
	ATTRIBUTE;
	SINGLE_VALUE;
	DATE;
	YEAR;
	MONTH;
	DAY;
	ARRAY_VALUES;
	BASIC_TYPE;
	ARRAY_TYPE;
}

// Package and imports for the parser.
@header  
{
	package net.explorercat.compactcat.parser;	
}
 
// Package and imports for the lexer. 
@lexer::header  
{ 
	package net.explorercat.compactcat.parser;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Catalog: Contains global metadata and the definition of each entity.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

catalog			
 	: 	CATALOG '{' cp=catalogProperty (',' rcp+=catalogProperty)* '}' ';'?
 		-> ^(CATALOG ^(PROPERTIES $cp $rcp*)) 
	;
	
catalogProperty
	:	name
	|	version
	|	releaseDate
	|	description
	|	dictionary
	;						

// Note that these properties will be reused by other rules.

version		
	:	VERSION ':' s=STRING
		-> ^(VERSION $s)
	;

releaseDate	
	:	DATE_TYPE ':' d=date 
		-> ^(RELEASE_DATE $d)
	;
	
name
	: 	NAME ':' (i=ID | rn=reservedName | s=STRING)
		-> ^(NAME $i? $rn? $s?)
	;
	
description	
	:	DESCRIPTION ':' s=STRING
		-> ^(DESCRIPTION $s)
	;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Dictionary (metadata describing the entities and their attributes).
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

dictionary		
	:	DICTIONARY ':' '{' ed=entityDictionary (',' red+=entityDictionary)* '}'
		-> ^(DICTIONARY $ed $red*)
	;
			
entityDictionary 	
	:	i=ID ':' '{' ((ep=entityProperties ',' ea=entityAttributes) | (ea=entityAttributes ',' ep=entityProperties)) '}'
		-> ^(ENTITY ^(NAME $i) ^(PROPERTIES $ep) ^(ATTRIBUTES $ea))
	;

entityProperties
	:	entityProperty (',' entityProperty)*	
	;

// Mandatory properties for the entities.
entityProperty
	:	description
	;
						
entityAttributes	
	: 	ATTRIBUTES ':' '[' ea=entityAttribute (',' rea+=entityAttribute)* ']'
		-> $ea $rea*
	;

// We allow any combination of attribute values.
// We'll check later they are sematically valid.
entityAttribute	
	:	'{' av=attributeValue (',' rav+=attributeValue)* '}'
		-> ^(ATTRIBUTE $av $rav*)
	;

attributeValue	
	: 	name
	|	description
	|	allowedValues
	|	minimumValue
	|	maximumValue
	|	attributeType
	|	attributeReference
	;

allowedValues		
	:	VALUES ':' vl=valuesList
		-> ^(VALUES $vl)
	;

valuesList		
	:	bv=basicValue
		-> ^(SINGLE_VALUE $bv)
		
	|	'[' bv=basicValue (',' rbv+=basicValue)* ']'
		-> ^(ARRAY_VALUES $bv $rbv*)
	;
	
basicValue	
	:	(INTEGER | STRING | date | BOOLEAN | REAL | NULL)
	;
		
	
minimumValue	
	:	MINIMUM ':' (i=INTEGER | r=REAL)
		-> ^(MINIMUM $i? $r?)
	;

maximumValue	
	:	MAXIMUM ':' (i=INTEGER | r=REAL) 
		-> ^(MAXIMUM $i? $r?)
	;
			
attributeType	
	:	TYPE ':' t=type	
		-> ^(TYPE $t)	
	;

type
	:	bt=basicType
		-> ^(BASIC_TYPE $bt)
	 
	| 	at=arrayType
		-> ^(ARRAY_TYPE $at)
	;

basicType		
	: 	(INTEGER_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | BOOLEAN_TYPE | REAL_TYPE)
	;

arrayType	
	:	ARRAY_TYPE '<' bt=basicType '>'
		-> $bt
	;				
		
date
	: 	DATE_YEAR_MONTH_DAY
		-> ^(DATE DATE_YEAR_MONTH_DAY)		
	;

attributeReference	
	:	REFERENCES ':' entity=ID '(' (attId=ID | attName=reservedName | attString=STRING) ')'
		-> ^(REFERENCES ^(ENTITY $entity) ^(ATTRIBUTE $attId? $attName? $attString?))
	;
									
// Reserved words that we allow to be used as property names.
reservedName 
	:	(CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION)
	;
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Lexer	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
// Reserved words
CATALOG			: C A T A L O G;
NAME			: N A M E;
DICTIONARY		: D I C T I O N A R Y;
ATTRIBUTES		: A T T R I B U T E S;
VALUES			: V A L U E S;
REFERENCES		: R E F E R E N C E S;
TYPE			: T Y P E;
MINIMUM			: M I N I M U M | M I N;	
MAXIMUM			: M A X I M U M | M A X;
VERSION			: V E R S I O N;
DESCRIPTION		: D E S C R I P T I O N;


// Data types
INTEGER_TYPE	: I N T E G E R;
REAL_TYPE		: R E A L;
BOOLEAN_TYPE	: B O O L E A N;
STRING_TYPE		: S T R I N G;
TEXT_TYPE		: T E X T;
DATE_TYPE		: D A T E;
ARRAY_TYPE		: A R R A Y;

// Literals
BOOLEAN	
	:	T R U E  
	|	F A L S E
	;
	
NULL
	: N U L L 
	;

// Identifiers.
ID		
	: 	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'-'|'_'|'+')*
	; 

// Strings (TODO Manage escaped sequences). 
STRING 
	:	'"' (~('"'|'\n'|'\r'))* '"' 
	|	'\'' (~('\''|'\n'|'\r'))* '\''  
	;
	
DATE_YEAR_MONTH_DAY
	:	NUMBER NUMBER NUMBER NUMBER ('/' | '-') NUMBER NUMBER ('/' | '-') NUMBER NUMBER
	;		
	
// Numbers (integer and real).
INTEGER		
	: NUMBER+
	;
 
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
			