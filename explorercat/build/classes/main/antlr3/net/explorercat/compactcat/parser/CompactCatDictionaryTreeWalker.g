
/**
 * Tree walker in charge of creating the definition objects that will be used
 * by the translators to guide the translation process.
 *
 * Depends on ANTLR 3.2
 *
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Summer 2010
 */

tree grammar CompactCatDictionaryTreeWalker;

// Importing vocabulary and specifying the type of AST.
options 
{
	language = Java;
  	tokenVocab = CompactCatDictionary;
  	ASTLabelType = CommonTree;
}

// Package definition and imports. 
@header 
{
	package net.explorercat.compactcat.parser;		
	import net.explorercat.compactcat.definitions.*;
	import net.explorercat.cql.types.*;
	import net.explorercat.util.exceptions.ExplorerCatCheckedException; 
}

@members
{
	private String getUnquotedText(CommonTree tokenNode)
	{
		String text = tokenNode.getText();
		return text.substring(1,text.length()-1);	
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Tree Parser: We build and return a CatalogDefinition object that encapsualates the definition of all the elements
// in the catalog.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
 
catalog returns[CatalogDefinition completeCatalogDefinition]
	scope 
	{ 
		CatalogDefinition catalogDefinition
	}
	@init  
	{ 
		$catalog::catalogDefinition = new CatalogDefinition();
	}		
	
	:	^(CATALOG ^(PROPERTIES catalogProperty+)) 
		
		{								
			// Returning the catalog definition.
			$completeCatalogDefinition = $catalog::catalogDefinition;																	
		}			
	;
	
catalogProperty

	:	^(NAME i=ID)
		{ $catalog::catalogDefinition.setName($i.getText()); }
	
	|	^(NAME rn=(CATALOG|NAME|DICTIONARY|ATTRIBUTES|VALUES|REFERENCES|TYPE|MINIMUM|MAXIMUM|VERSION|DESCRIPTION))
		{ $catalog::catalogDefinition.setName($rn.getText()); }
	
	|	^(NAME s=STRING)
		{ $catalog::catalogDefinition.setName(getUnquotedText($s)); }
	
	|	^(VERSION s=STRING)
		{ $catalog::catalogDefinition.setVersion(getUnquotedText($s)); }
	
	|	^(RELEASE_DATE ^(DATE  d=DATE_YEAR_MONTH_DAY))		
		{ 
			int year = Integer.parseInt($d.getText().substring(0,4));
			int month = Integer.parseInt($d.getText().substring(5,7));
			int day = Integer.parseInt($d.getText().substring(8,10));
										
			$catalog::catalogDefinition.setReleaseDate(new DateValue(year, month, day)); 
		} 
	
	|	^(DESCRIPTION s=STRING)
		{ $catalog::catalogDefinition.setDescription(getUnquotedText($s)); } 
	
	| 	dictionary
	
	;	
	
dictionary
	:	^(DICTIONARY entity+)
	;
	
	
entity
	scope 
	{ 
		EntityDefinition entityDefinition;
	}
	@init  
	{ 
		$entity::entityDefinition = new EntityDefinition();
	}	
	
	:	^(ENTITY ^(NAME i=ID) ^(PROPERTIES entityProperty+) ^(ATTRIBUTES entityAttribute+))
		{ 
			try
			{
				$entity::entityDefinition.setName($i.getText());
				$catalog::catalogDefinition.addEntityDefinition($entity::entityDefinition);
			}
			catch(Exception e)
			{
				// TODO Deal with errors!
			}
		}
	;
	
entityProperty

	:	^(DESCRIPTION s=STRING)
		{ $entity::entityDefinition.setDescription(getUnquotedText($s)); }
	;
	
entityAttribute
	scope 
	{ 
		RegularAttributeDefinition attributeDefinition;
	}
	@init  
	{ 
		$entityAttribute::attributeDefinition = new RegularAttributeDefinition();
	}	
	
	:	^(ATTRIBUTE av=attributeValue+)
		{
			try
			{ 
				$entity::entityDefinition.addAttributeDefintion($entityAttribute::attributeDefinition);
			}
			catch(ExplorerCatCheckedException e)
			{
				// TODO Deal with errors!
				System.err.println("Error, duplicated attribute: " + $entityAttribute::attributeDefinition.getName());
			} 
		}	
	;
	
attributeValue
	
	:	^(NAME i=ID)
		{ $entityAttribute::attributeDefinition.setName($i.getText()); }
		
	|	^(NAME s=STRING)
		{ $entityAttribute::attributeDefinition.setName(getUnquotedText($s)); }
	
	|	^(DESCRIPTION s=STRING)
		{ $entityAttribute::attributeDefinition.setDescription(getUnquotedText($s)); }
	
	|	^(VALUES valueList)
	
	|	^(MINIMUM i=INTEGER)
		{ $entityAttribute::attributeDefinition.setMinimumValue(new IntegerValue(Integer.parseInt($i.getText()))); }
	
	|	^(MINIMUM r=REAL)
		{ $entityAttribute::attributeDefinition.setMinimumValue(new RealValue(Float.parseFloat($r.getText()))); }
	
	|	^(MAXIMUM i=INTEGER)
		{ $entityAttribute::attributeDefinition.setMaximumValue(new IntegerValue(Integer.parseInt($i.getText()))); }
	
	|	^(MAXIMUM r=REAL)
		{ $entityAttribute::attributeDefinition.setMaximumValue(new RealValue(Float.parseFloat($r.getText()))); }
	
	|	^(TYPE t=type)
		{ $entityAttribute::attributeDefinition.setType($t.dataType); }
		
	|	^(REFERENCES ^(ENTITY ei=ID) ^(ATTRIBUTE ai=ID))
		{ $entityAttribute::attributeDefinition.setReference($ei.getText(), $ai.getText()); }
		
	|	^(REFERENCES ^(ENTITY ei=ID) ^(ATTRIBUTE rn=(CATALOG|NAME|DICTIONARY|ATTRIBUTES|VALUES|REFERENCES|TYPE|MINIMUM|MAXIMUM|VERSION|DESCRIPTION)))
		{ $entityAttribute::attributeDefinition.setReference($ei.getText(), $rn.getText()); }
		
	|	^(REFERENCES ^(ENTITY ei=ID) ^(ATTRIBUTE as=STRING))
		{ $entityAttribute::attributeDefinition.setReference($ei.getText(), getUnquotedText($as)); }
					
	;	
	
valueList
	
	:	^(SINGLE_VALUE v=value)	
		{ $entityAttribute::attributeDefinition.registerAllowedValue($v.dataValue); }
	
	|	^(ARRAY_VALUES (v=value {$entityAttribute::attributeDefinition.registerAllowedValue($v.dataValue);})+ )			
	
	;
	
value returns[DataValue dataValue]
	
	:	i=INTEGER 	{ $dataValue = new IntegerValue(Integer.parseInt($i.getText())); }
	
	| 	s=STRING 	{ $dataValue = new StringValue(getUnquotedText($s)); }		
		
	| 	b=BOOLEAN 	{ $dataValue = new BooleanValue(Boolean.parseBoolean($b.getText())); }
	
	| 	r=REAL 		{ $dataValue = new RealValue(Float.parseFloat($r.getText())); }
	
	| 	NULL		{ $dataValue = null; }
	
	| 	^(DATE d=DATE_YEAR_MONTH_DAY)		
		{ 
			int year = Integer.parseInt($d.getText().substring(0,4));
			int month = Integer.parseInt($d.getText().substring(5,7));
			int day = Integer.parseInt($d.getText().substring(8,10));
							
			$dataValue = new DateValue(year, month, day);
		}	
	;
	
	
type returns[DataType dataType]
	
	:	^(BASIC_TYPE bt=basicType)
		{ $dataType = $bt.dataType; }
	
	|	^(ARRAY_TYPE bt=basicType)
		{ $dataType = $bt.dataType.getEquivalentArrayType(); }			
	;
	
basicType returns[DataType dataType]
	
	:	INTEGER_TYPE 	{ $dataType = DataType.INTEGER; } 
	
	| 	STRING_TYPE		{ $dataType = DataType.STRING; }
	
	|	TEXT_TYPE		{ $dataType = DataType.TEXT; }
	
	| 	DATE_TYPE 		{ $dataType = DataType.DATE; }
	
	| 	BOOLEAN_TYPE 	{ $dataType = DataType.BOOLEAN; }
	
	| 	REAL_TYPE		{ $dataType = DataType.REAL; }
	
	;	
	