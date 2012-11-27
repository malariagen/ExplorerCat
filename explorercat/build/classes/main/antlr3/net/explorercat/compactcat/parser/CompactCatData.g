
/**
 * A grammar for the CompactCat DAT file format. Notice the goal of this grammar
 * is to parse the data and translate it into the targe representation (normally a DB). 
 * The trasnlation is done on the fly, without building an AST (it would be too heavey 
 * for large catalogs).
 *
 * TODO : Use UnbufferedTokenStream
 * 
 * (Note this file defines the parser and the lexer).
 *  
 * Developed as a part of the ExplorerCat system.
 * Depends on ANTLR 3.2
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date January 2011
 */

grammar CompactCatData;

options 
{
  language = Java;
}

// Package and imports for the parser.
@header  
{
	package net.explorercat.compactcat.parser;	
	
	import net.explorercat.compactcat.definitions.*;
	import net.explorercat.compactcat.translators.*;	
	import net.explorercat.cql.types.*;
	import net.explorercat.cql.types.DataValue;
	import net.explorercat.util.exceptions.TranslationException;
	
	import org.apache.commons.logging.Log;
	import org.apache.commons.logging.LogFactory;
}

// Definition of the catalog dictionary that will be used to parse the data.
@parser::members
{	
  	private static Log log = LogFactory.getLog(CompactCatDataParser.class);
  
	private CatalogDefinition catalogDefinition;
	private CatalogTranslator catalogTranslator;
	private TranslationParameterParser parameterParser;
	
	// Number of rows that will be consecutively read from the data file.	
	// A row is equivalent to an entity instance.
	private int consecutiveRowsToRead = 10;
	
	// Parameters to guide the translation.
	private List<String> translationParametersAsStrings;
	private List<TranslationParameter> translationParameters;
					
		
	public void setCatalogDefinition(CatalogDefinition definition)
	{
		this.catalogDefinition = definition;	
	}
	
	public void setCatalogTranslator(CatalogTranslator translator)
	{
		this.catalogTranslator = translator;
	}
		
	public void setConsecutiveRowsToRead(int numberOfRows)
	{
		this.consecutiveRowsToRead = numberOfRows;
	}	
	
	public void setTranslationParametersAsStrings(List<String> parameters)
	{
		this.translationParametersAsStrings = parameters;
	}
	
	public void setTranslationParameterParser(TranslationParameterParser parser)
	{
		this.parameterParser = parser;
	}
	
	private void parseTranslationParameters() throws TranslationException
	{
		this.translationParameters = new ArrayList<TranslationParameter>();
		
		if(this.parameterParser != null && this.translationParametersAsStrings != null)
 		{
 			for(String currentParameter : this.translationParametersAsStrings)
 				this.translationParameters.add(this.parameterParser.parseParameter(currentParameter));
 		}
	}
}
 
// Package and imports for the lexer.  
@lexer::header  
{ 
	package net.explorercat.compactcat.parser;
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

// Create the translator using the definitions.
// Use the translator to create the DB.
catalog		 	
 	: 	CATALOG '{'  	
 		
 		{ 
 			this.parseTranslationParameters();
 			this.catalogTranslator.translateCatalog(this.catalogDefinition, this.translationParameters);
 		}
 	
 		entityData (',' entityData)* '}' ';'? 	 
	;	

// Use the translator to create the entity table.
// TODO Add parameters to the translate method (entity).
entityData 
	scope 
	{ 
		EntityDefinition entityDefinition; 
		EntityTranslator entityTranslator;
		List<List<DataValue>> dataRows;
	}	
		
	: 	i=ID ':' 
		{
			$entityData::entityDefinition = this.catalogDefinition.getEntityDefinition($i.getText());
			$entityData::dataRows = new ArrayList<List<DataValue>>(this.consecutiveRowsToRead);
			$entityData::entityTranslator = this.catalogTranslator.getEntityTranslator();	
			$entityData::entityTranslator.translateEntity($entityData::entityDefinition, this.translationParameters);
		}
		
		'[' rv=rowValues 
			{
				$entityData::dataRows.add($rv.rowDataValues);
				
				if(log.isDebugEnabled())
	    			log.debug("Inserting data instances for " + $entityData::entityDefinition.getName());					
			} 
		
		(',' rrv=rowValues 		
			{
				$entityData::dataRows.add($rrv.rowDataValues);
				
				if($entityData::dataRows.size() >= this.consecutiveRowsToRead)
				{
					$entityData::entityTranslator.addDataRowsToEntityTranslation($entityData::dataRows, $entityData::entityDefinition);
					$entityData::dataRows.clear();				
				}			
			}
		)* ']'		
		
		{
			$entityData::entityTranslator.addDataRowsToEntityTranslation($entityData::dataRows, $entityData::entityDefinition);	
			$entityData::entityTranslator.finishTranslation($entityData::entityDefinition, this.translationParameters);			
		}						
	;	
	
	catch[TranslationException e] { e.printStackTrace(); }

// Use the translator to add value by value.
rowValues returns [List<DataValue> rowDataValues]
	scope
	{
		List<DataValue> dataValues;
	}
	@init
	{
		$rowValues::dataValues = new ArrayList<DataValue>();
	}	
	:	'[' av=attributeValue { $rowValues::dataValues.add($av.value); }							
		 (',' rav=attributeValue { $rowValues::dataValues.add($rav.value);} )* ']'		
		
		{
			$rowDataValues = $rowValues::dataValues;
		}
	;	
	
attributeValue returns [DataValue value]
	
	:	bv=basicValue { $value = $bv.dataValue; }
	
	|	'[' { List<DataValue> arrayValues = new ArrayList<DataValue>(); }	
		
		bv=basicValue? { arrayValues.add($bv.dataValue); }	 
		
		(',' rbv=basicValue { arrayValues.add($rbv.dataValue); } )* ']'
		
		{
			$value = new ArrayValue(arrayValues);		
		}	
	;
	
basicValue returns [DataValue dataValue]
 
	:	i=INTEGER 	{ $dataValue = new IntegerValue(Integer.parseInt($i.getText())); } 
	
	|	s=STRING	{ $dataValue = new StringValue($s.getText().substring(1,$s.getText().length()-1)); }
	
	|	d=date 		{ $dataValue = $d.dateValue; }
	
	| 	b=BOOLEAN 	{ $dataValue = new BooleanValue(Boolean.parseBoolean($b.getText())); }
	
	| 	r=REAL 		{ $dataValue = new RealValue(Float.parseFloat($r.getText())); }
	
	| 	NULL		{ $dataValue = new NullValue(); }
	
	;
	
date returns [DateValue dateValue] 

	:	d=DATE_YEAR_MONTH_DAY 
		
		{
			int year = Integer.parseInt($d.getText().substring(0,4));
			int month = Integer.parseInt($d.getText().substring(5,7));
			int day = Integer.parseInt($d.getText().substring(8,10));
			
			$dateValue = new DateValue(year, month, day);
		}		
	;
				
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Lexer	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
// Reserved words
CATALOG	: C A T A L O G;

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
	: ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'-'|'_'|'+')*
	; 

// Strings (TODO Manage escaped sequences). 
STRING 
	:	'"' (~('"'|'\n'|'\r'))* '"' 
	|	'\'' (~('\''|'\n'|'\r'))* '\''  
	;
	
// Numbers (integer and real).
INTEGER		
	: ('-')? NUMBER+
	;
 
REAL
	:   ('-')? NUMBER+ '.' NUMBER* EXPONENT?  
    |   ('-')? '.' NUMBER+ EXPONENT?  
    |   ('-')? NUMBER+ EXPONENT    
    ;        
        
fragment EXPONENT    
    :	( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ 
    ;

fragment NUMBER
	:	('0' .. '9')
	;
		
DATE_YEAR_MONTH_DAY
	:	NUMBER NUMBER NUMBER NUMBER ('/' | '-') NUMBER NUMBER ('/' | '-') NUMBER NUMBER
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

