
/**
 * Tree walker in charge of translating a CQL AST into a tree of Java objects 
 * that will interpret the script.
 *
 * During the walking we translate each selection block into a query builder. 
 * This object will be encapsulated into a proxy selection that will be resolved 
 * during the next step (using the builder to build the query).  
 * 
 * For each result we configure a result builder step by step, result objects
 * will be created on the fly during the interpretation phase.. 
 *
 * Depends on ANTLR 3.2
 *
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Summer 2010
 */

tree grammar CQLTreeWalker; 

// Importing vocabulary and specifying the type of AST.
options 
{
  language = Java;
  tokenVocab = CQL;
  ASTLabelType = CommonTree;
}

// Package definition and imports. 
@header 
{
	package net.explorercat.cql.parser;  
	
	import java.util.HashMap;	
	import java.util.Map; 
	import java.util.NoSuchElementException;
		
	import net.explorercat.cql.expressions.*;
	import net.explorercat.cql.expressions.stats.*;
	import net.explorercat.cql.selection.stats.StatsType;
	import net.explorercat.cql.expressions.special.*;
	import net.explorercat.cql.expressions.unary.*;
	import net.explorercat.cql.expressions.binary.*;
	import net.explorercat.cql.expressions.values.*;
	import net.explorercat.cql.types.*;	

	import net.explorercat.cql.selection.query.*;
	import net.explorercat.cql.selection.limiters.*; 
	import net.explorercat.cql.selection.sorters.*;
	import net.explorercat.cql.selection.*;
	import net.explorercat.cql.result.builders.*;		
	import net.explorercat.cql.result.builders.CombinedResultBuilder.SelectionSetCombination;
	import net.explorercat.cql.result.builders.SelectionCombiner.SetOperationType;
}

// Definition of auxiliary method to be used while walking the AST. 
@members
{
	/**
	 * Auxiliary method that builds the proper statistical function expression based on the given 
	 * parameters.
	 * @param token Integer representing the stats type.
	 * @param referencedSelectionName Name of the selection that will be used to calculate the stats.
	 * @param scriptSelectionMap Dictionary that contains all the selections of the script processed so far.
	 * @param variableExpression Variable definition to be used instead of an entity property (null otherwise).
	 * @param errorRecoveryExpression Expression to be used in case of error.
	 * @return The configured stats function.
	 */
	 
	private Expression buildStatisticalFunctionExpression(int token, 
														  String propertyName, 
														  String referencedSelectionName, 
														  Map<String, SelectionProxy> scriptSelectionMap,
														  Expression variableExpression,
														  Expression errorRecoveryExpression)
	{    
		// Get the referenced selection to be used with the stats.		
		SelectionProxy sourceSelection = scriptSelectionMap.get(referencedSelectionName);
			
		// If the selection is not registered in the map we have to add it as a proxy selection.
		// (this is a forward reference).
		if(sourceSelection == null)
		{
			sourceSelection = new SelectionProxy(referencedSelectionName);
			scriptSelectionMap.put(referencedSelectionName, sourceSelection);
		}		
	
		switch(token)
		{
			case AVG:
				return new AverageExpression(propertyName, variableExpression, sourceSelection, errorRecoveryExpression);			
		
			case MEDIAN:
				return new MedianExpression(propertyName, variableExpression, sourceSelection, errorRecoveryExpression);				
		
			case MAX:
				return new MaximumExpression(propertyName, variableExpression, sourceSelection, errorRecoveryExpression);
		
			case MIN:
				return new MinimumExpression(propertyName, variableExpression, sourceSelection, errorRecoveryExpression);
		
			case STDDEV:
				return new StandardDeviationExpression(propertyName, variableExpression, sourceSelection, errorRecoveryExpression);
		
			case VAR:
				return new VarianceExpression(propertyName, variableExpression, sourceSelection, errorRecoveryExpression);
		
			case SUM:
				return new SumExpression(propertyName, variableExpression, sourceSelection, errorRecoveryExpression);
			
			// Something went wrong.
			default:
				throw new NoSuchElementException("There is not a statistical function definition for token value: " + token);					
		}	
	}
		
	/**
	* Auxiliary method that configures a CombinedFieldBuilder with the given parameters.
	* @param combination The combination (set operation over selections) to be configured.
	* @param firstOperandAsSelection A string with the selection name if we are referencing a selection 
	*		 as first operand, null otherwise.
	* @param firstOperandAsNestedCombination A combination if we are nesting another selection combination 
	*		 as first operand, null otherwise.
	* @param secondOperandAsSelection A string with the selection name if we are referencing a selection 
	*		 as second operand, null otherwise.
	* @param secondOperandAsNestedCombination A combination if we are nesting another selection combination 
	*		 as second operand, null otherwise.
	*/
	
	private void configureSelectionSetCombination(SelectionSetCombination combination, String firstOperandAsSelection,
											   	  SelectionSetCombination firstOperandAsNestedCombination,
											   	  String secondOperandAsSelection, 
											   	  SelectionSetCombination secondOperandAsNestedCombination)
	{
		// Configure the first operand (selection name or nested combination).
		if(firstOperandAsSelection != null)
			combination.setFirstOperand(firstOperandAsSelection);
		else
			combination.setFirstOperand(firstOperandAsNestedCombination);
			 
		// Configure the second operand (selection name or nested combination).
		if(secondOperandAsSelection != null)
			combination.setSecondOperand(secondOperandAsSelection);
		else
			combination.setSecondOperand(secondOperandAsNestedCombination);					
	}											   											   
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Tree Parser: We build and return a map that contains all the selections (partially resolved) in the script. We also
// return a list with all the result builders required by the script.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
 
cqlScript returns[Map<String, SelectionProxy> scriptSelectionMap, ResultGenerator scriptResultGenerator;]
	scope 
	{ 
		// Symbol table for selections. 
		// We delegete resolving selections to the next step.
		// During the walk of the tree we'll build and configure proxies (i.e. selections)
		// that will be resolved during the script interpretation. A query builder assigned 
		// to the proxy will be in charge of that.	
		
		Map<String, SelectionProxy> selectionMap;	
		 
		// Generator in charge of build the result.
		
		ResultGenerator resultGenerator;			
	}
	@init  
	{ 
		$cqlScript::selectionMap = new HashMap<String, SelectionProxy>();	 
		$cqlScript::resultGenerator = new ResultGenerator(); 
	}		
	
	:	^(CQL_SCRIPT selectionBlock* resultBlock*) 
		
		{								
			// Returning the map of proxy selections.
			$scriptSelectionMap = $cqlScript::selectionMap;
			
			// Returning the result generate for the script (can be null).
			$scriptResultGenerator = $cqlScript::resultGenerator;																	
		}
			
	;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
// Selection block: We build the query object step by step while exploring the tree.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

selectionBlock
	scope 
	{ 
		QueryBuilder queryBuilder; 				// Builder that will create the query object for the block.
		Map<String, Expression> variableMap;	// Symbol table for local variables. 
	}
	@init 
	{ 
		$selectionBlock::queryBuilder = new QueryBuilder();  
		$selectionBlock::variableMap = new HashMap<String, Expression>();			
	}			
	
	: ^(SELECT ^(DATA_SOURCE i=ID) ld=labelDefinition 
		{
			// Associate the query builder with the source selection label (FROM)
			$selectionBlock::queryBuilder.setSourceSelectionLabel($i.getText());
			
			// Associate the query builder with the label.			
			$selectionBlock::queryBuilder.setQueryLabel($ld.labelString);			
			
			// Register the selection proxy in the symbol table.
			// Check if the proxy is already in the table.
			SelectionProxy proxySelection = $cqlScript::selectionMap.get($ld.labelString);
			
			if(proxySelection == null) 
				proxySelection = new SelectionProxy($ld.labelString);
				
			try 
			{
				proxySelection.setSelectionQueryBuilder($selectionBlock::queryBuilder);							
				$cqlScript::selectionMap.put($ld.labelString, proxySelection);
			}
			catch(SelectionException e)
			{
				// TODO deal with the error.
        		e.printStackTrace();					
			}
		}		
		
		sbd=selectionBlockDefinition sbo=selectionBlockOptions)		
	;

selectionBlockDefinition
		// Note that we assume inferred type for the condition will be booelan.
	:	^(SELECTION_DEFINITION  variablesBlock ^(CONDITION e=expression[DataType.BOOLEAN, null])) 
		{
			// Register the condition on the query builder.
			$selectionBlock::queryBuilder.setQueryCondition(new QueryCondition($e.expResult)); 
		}
	;
			
variablesBlock
	:	^(VARIABLES variableDefinition+)
	|	// No var definition
	;
	
variableDefinition
		// Assume initial inferred type is String (it will be automatically coerced if necessary)
	:	^(VAR i=ID e=expression[DataType.STRING, null])	
		{  						
			// Register the variable in the query builder.
			Expression variable = new VariableExpression($i.getText(), $e.expResult);				
			$selectionBlock::queryBuilder.registerVariable($i.getText(), variable);
			 
			// Register the variable on the map.
			$selectionBlock::variableMap.put($i.getText(), variable);		
		}
	;
		 
// Block options: sorting and limits. 
// They can be placed in any order and repeated several times.

selectionBlockOptions
	:	^(SELECTION_OPTIONS selectionBlockOptionsDefinition*)
	;
		
selectionBlockOptionsDefinition
	:	sortOptions	
	|	limitOptions	
	;
	
sortOptions	
	: 	^(SORT i=ID d=DESC)
		{ 
			// Creates a new descent sorter that will use a property or a variable (if no variable was registered in the map
			// for the given name we assume it was referencing a property, we'll pass null to the constructor). 
			EntitySorter sorter = new SinglePropertyEntitySorter($i.getText(), $selectionBlock::variableMap.get($i.getText()),true);
			$selectionBlock::queryBuilder.setSelectionSorter(sorter); 
		}
	
	| 	^(SORT i=ID)		
		{ 
			// Creates a new ascendent sorter that will use a property or a variable (if no variable was registered in the map
			// for the given name we assume it was referencing a property, we'll pass null to the constructor). 
			EntitySorter sorter = new SinglePropertyEntitySorter($i.getText(), $selectionBlock::variableMap.get($i.getText()),false);
			$selectionBlock::queryBuilder.setSelectionSorter(sorter); 
		}
	;

limitOptions
	:	^(LIMIT limitDefinition+)
	;

// Limiters: TOP, BOTTOM and RANDOM (with an optional seed).		

limitDefinition
	@init { int limitOffset = 0; }
	 
	:	^(TOP size=INTEGER (offset=INTEGER { limitOffset = Integer.parseInt($offset.getText()); })?)
		{ $selectionBlock::queryBuilder.setSelectionLimiter(new TopLimiter(Integer.parseInt($size.getText()), limitOffset)); }
	
	|	^(BOTTOM size=INTEGER (offset=INTEGER { limitOffset = Integer.parseInt($offset.getText()); })?)
		{ $selectionBlock::queryBuilder.setSelectionLimiter(new BottomLimiter(Integer.parseInt($size.getText()), limitOffset)); }
	
	|	^(RANDOM size=INTEGER seed=INTEGER)  
		{ $selectionBlock::queryBuilder.setSelectionLimiter(new RandomLimiter(Integer.parseInt($size.getText()), Integer.parseInt($seed.getText()))); }
		
	|	^(RANDOM size=INTEGER)  
		{ $selectionBlock::queryBuilder.setSelectionLimiter(new RandomLimiter(Integer.parseInt($size.getText()))); }	
	; 

// Label definition, used for several kind of elements (selections, properties, fields, etc.)
// We support quoted strings as well, needed if the label includes blank spaces.

// labelDefinition has been reduced, this should be re-factorized

labelDefinition returns[String labelString]
	:	ln=labelName
		{ $labelDefinition.labelString = $ln.labelString; }		 
	;	
	
labelName returns[String labelString] 
	
	:	^(LABEL i=ID) 
		{ $labelName.labelString = $i.getText(); }
	 
	|	^(LABEL qs=QUOTED_STRING)
		{ $labelName.labelString = $qs.getText().substring(1,$qs.getText().length()-1);}
	;

// Branch for elements where the labelling is optional.

optionalLabelName returns[String labelString]
	:	ln=labelName 	{ $optionalLabelName.labelString = $ln.labelString; }
	|	/* Empty */		{ $optionalLabelName.labelString = null; }
	;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
// Result block: It is translated into a result generator that will create results by demand, 
// internally using a result builder.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

resultBlock
	scope
	{
		// The builder for this result.
		ResultBuilderBase resultBuilder;		
	}
	@init
	{
		$resultBlock::resultBuilder = null;
	}
	
	:	^(RESULT resultDefinition)			
		{					 		
			if($cqlScript::resultGenerator.hasResultBuilder())
				throw new CQLSemanticException("CQL error: more than one result block has been defined");
		
			// Sets the result generator at script level.
			$cqlScript::resultGenerator.setResultBuilder($resultBlock::resultBuilder);
		}			
	;
	
resultDefinition	
	@init 
	{ 						
		// Will store the optional label for the result
		String label = null;
	}	
		
	:	^(RESULT_DEFINITION resultExpression propertySelection? (ld=labelDefinition { label = $ld.labelString; })?)
		{
			// Set the field label only if it was specified.
			if(label != null)
				$resultBlock::resultBuilder.setResultLabel(label); 
		} 
	;

// 	The result expression specifies the type of result:
// - Regular : Using a single selection.
// - Combined : Using a combined selection (set operator applied over selections).
// - Stats : A statistical value from a selection.
// - Distinct : Only select unique values.
	
resultExpression
	
	:	^(REGULAR_SELECTION_RESULT i=ID)	
		{ 
			$resultBlock::resultBuilder = new RegularResultBuilder($i.getText()); 
		}
	
	|	^(REGULAR_SELECTION_RESULT qs=QUOTED_STRING)
		{ 
			$resultBlock::resultBuilder = new RegularResultBuilder($qs.getText().substring(1,$qs.getText().length()-1)); 
		}
		
  	| 	^(DISTINCT_SELECTION_RESULT i=ID)
    	{ 
      		$resultBlock::resultBuilder = new DistinctResultBuilder($i.getText()); 
    	}
    
 	| 	^(DISTINCT_SELECTION_RESULT qs=QUOTED_STRING)
    	{ 
      		$resultBlock::resultBuilder = new DistinctResultBuilder($qs.getText().substring(1,$qs.getText().length()-1)); 
    	}
	
	|	^(COMBINED_SELECTION_RESULT sf=setOperation) 
		{ 
			CombinedResultBuilder builder = new CombinedResultBuilder(); 
			builder.setSelectionCombination($sf.combination);
			$resultBlock::resultBuilder = builder;
		}
	
	|	^(STATS_SELECTION_RESULT resultStatisticalFunction) 
		// Field builder created in resultStatisticalFunction
	;			

// When using a statistical function in a result we have to specify
// the selection label (different from the definition on selection blocks).
// We cast the field builder interface to the concrete class for this case (stats).
// For any stats function we register the selection, the type of stats and the property 
// that will be used to calculate it.

resultStatisticalFunction
	@init 	
	{		
		StatsResultBuilder statsResultBuilder =  new StatsResultBuilder();
		$resultBlock::resultBuilder = statsResultBuilder; 
	}
	
	:	^(AVG ^(ARGUMENT i=ID) ^(PROPERTY p=ID)) 
		{			 			
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.AVG);
			statsResultBuilder.addProperty($p.getText(), null); 
		}
	
	|	^(MEDIAN ^(ARGUMENT i=ID) ^(PROPERTY p=ID))
		{			 
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.MEDIAN);
			statsResultBuilder.addProperty($p.getText(), null);
		}
		
	|	^(MIN ^(ARGUMENT i=ID) ^(PROPERTY p=ID))
		{			 
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.MIN);
			statsResultBuilder.addProperty($p.getText(), null);
		}
		
	|	^(MAX ^(ARGUMENT i=ID) ^(PROPERTY p=ID))
		{			 
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.MAX);
			statsResultBuilder.addProperty($p.getText(), null);
		}
			
	|	^(STDDEV	^(ARGUMENT i=ID) ^(PROPERTY p=ID))
		{			 
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.STDDEV);
			statsResultBuilder.addProperty($p.getText(), null);
		}
		
	|	^(VAR ^(ARGUMENT i=ID) ^(PROPERTY p=ID))
		{			 
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.AVG);
			statsResultBuilder.addProperty($p.getText(), null);
		}
			
	|	^(SUM ^(ARGUMENT i=ID) ^(PROPERTY p=ID))
		{			 
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.SUM);
			statsResultBuilder.addProperty($p.getText(), null);
		}
		
	|	^(COUNT ^(ARGUMENT i=ID) ^(PROPERTY p=ID))
		{			 
			statsResultBuilder.setSelectionName($i.getText());
			statsResultBuilder.setStatsType(StatsType.COUNT);
			statsResultBuilder.addProperty($p.getText(), null);
		}
	;
 
// Functions that transform selections using set operations.
// A combination is just a tree of set operations (can be nested) that transforms selections.

setOperation returns[SelectionSetCombination combination]
	@init
	{
		$setOperation.combination = new SelectionSetCombination();
	}
	
	:	^(UNION attr1=setOperationAttribute attr2=setOperationAttribute) 
		{
			$setOperation.combination.setOperationType(SetOperationType.UNION);		
			configureSelectionSetCombination($setOperation.combination, $attr1.selectionId, $attr1.combination,
											 $attr2.selectionId, $attr2.combination); 									
		}
	
	|	^(INTERSECTION attr1=setOperationAttribute attr2=setOperationAttribute)
		{
			$setOperation.combination.setOperationType(SetOperationType.INTERSECTION);	
			configureSelectionSetCombination($setOperation.combination, $attr1.selectionId, $attr1.combination,
											 $attr2.selectionId, $attr2.combination);		
		}
	
	|	^(DIFFERENCE attr1=setOperationAttribute attr2=setOperationAttribute)
		{
			$setOperation.combination.setOperationType(SetOperationType.DIFFERENCE);
			configureSelectionSetCombination($setOperation.combination, $attr1.selectionId, $attr1.combination,
											 $attr2.selectionId, $attr2.combination);
		} 		
	;
		
// We return a string (representing a selection name) or a combination object
// (representing a nested set operation) depending on the alternative matched.

setOperationAttribute returns[String selectionId, SelectionSetCombination combination]
	@init
	{
		$setOperationAttribute.selectionId = null;
		$setOperationAttribute.combination= null;
	}
	
	:	^(ARGUMENT i=ID)
		{ $setOperationAttribute.selectionId = $i.getText(); }
	
	|	^(ARGUMENT i=QUOTED_STRING)
		{ $setOperationAttribute.selectionId = $i.getText().substring(1,$i.getText().length()-1); }
	
		// Nested set operation.
	|	setOperation
		{ $setOperationAttribute.combination = $setOperation.combination; }
	
	;	

// Selection of properties for the result.
// Registers each property in the result builder.

propertySelection
	:	^(RESULT_PROPERTIES pd=propertyDefinition { $resultBlock::resultBuilder.addProperty($pd.propertyName, $pd.propertyLabel); } 
		 (rpd = propertyDefinition { $resultBlock::resultBuilder.addProperty($rpd.propertyName, $rpd.propertyLabel);})*)
	;		 

// Definition of a property that will be selected in the result.
// Returns the individual property as a pair (name, label).

propertyDefinition returns[String propertyName, String propertyLabel]
	@init 
	{
		// The label is optional.  
		$propertyDefinition.propertyLabel = null; 
	} 
	
	:	^(ARGUMENT i=ID  (ld=labelDefinition { $propertyDefinition.propertyLabel = $ld.labelString; })?)
		{ 
			$propertyDefinition.propertyName = $i.getText();
		} 
	;		
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
// Hierarchy of expressions (by precedence, closer to the bottom means higher precedence).
// We build a tree of expressions with java objects that will be simplied later on and optimized when executed as 
// a query if possible. Notice we have to infer the type of each expression and that error recovery expressions
// travel down the subtree of the expression for which have been defined.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
// Logical expressions (OR, AND, NOT is a special case with top precedence)		
expression[DataType inferredType, Expression recoveryExpression] returns[Expression expResult]
	
		// Logical expressions.
		 
	:	^(OR e1=expression[DataType.BOOLEAN, null] e2=expression[DataType.BOOLEAN, null])	
		{ $expResult = new OrExpression($e1.expResult, $e2.expResult, $recoveryExpression); } 
	
	|	^(AND e1=expression[DataType.BOOLEAN, null] e2=expression[DataType.BOOLEAN, null]) 
		{ $expResult = new AndExpression($e1.expResult, $e2.expResult, $recoveryExpression);}	
		
	|	^(NOT e1=expression[DataType.BOOLEAN, null])
		{ $expResult = new NotExpression($e1.expResult, $recoveryExpression); }
				
		// Relational expressions.
	
	|	^(EQ e1=expression[DataType.STRING, null] e2=expression[DataType.STRING, null] )
		{ $expResult = new EqualThanExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	| 	^(GE e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new GreaterOrEqualThanExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	| 	^(LE e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new LessOrEqualThanExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	| 	^(GT e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new GreaterThanExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	| 	^(LT e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new LessThanExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
		
		// Arithmetic expressions.
	
	|	^(PLUS e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new AdditionExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	|	^(DIFF e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new DifferenceExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	|	^(MULT e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new MultiplicationExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	| 	^(DIV e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new DivisionExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	| 	^(MINUS e1=expression[DataType.REAL, null] )
    	{ $expResult = new MinusExpression($e1.expResult, $recoveryExpression); }
    
		// Mathematical functions.
	
	|	^(POWER e1=expression[DataType.REAL, null] e2=expression[DataType.REAL, null] )
		{ $expResult = new PowerExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
	|	^(SQRT e1=expression[DataType.REAL, null] )
		{ $expResult = new SquareRootExpression($e1.expResult, $recoveryExpression); }
	
	|	^(LOG e1=expression[DataType.REAL, null] )
		{ $expResult = new LogarithmExpression($e1.expResult, $recoveryExpression); }
	 
	|	^(LN e1=expression[DataType.REAL, null] )
		{ $expResult = new NeperianLogarithmExpression($e1.expResult, $recoveryExpression); }
	
	|	^(ABS e1=expression[DataType.REAL, null] )
		{ $expResult = new AbsoluteValueExpression($e1.expResult, $recoveryExpression); }
	
		// Statistical functions.
		// They can have an optional label reference (to a selection block).
		// The source selection for stats function will be a proxy that will be resolved to a 
		// selection during the next phase. The label of any referenced selection is registered
		// in the selection query builder.
	
	|	^(AVG ^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(AVG, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression);
		}
	
	|	^(MEDIAN ^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(MEDIAN, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression);
		}
		
	|	^(MIN ^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(MIN, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression);		 
		}
		
	|	^(MAX ^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(MAX, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression);
		}
			
	|	^(STDDEV	^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(STDDEV, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression);		 
		}
		
	|	^(VAR ^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(VAR, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression); 
		}
			
	|	^(SUM ^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(SUM, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression);	 
		}
		
	|	^(COUNT ^(ARGUMENT i=ID) ol=optionalLabelName )
		{ 
			String referencedSelection =  ($ol.labelString != null ? $ol.labelString : $selectionBlock::queryBuilder.getSourceSelectionLabel());
			$selectionBlock::queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
			$expResult = buildStatisticalFunctionExpression(COUNT, $i.getText(), referencedSelection, $cqlScript::selectionMap, 
															$selectionBlock::variableMap.get($i.getText()),$recoveryExpression);	 
		}
			
		// String functions (altough contais is also an array function).
	
	|	^(CONTAINS e1=expression[DataType.STRING, null] e2=expression[DataType.STRING, null] )
		{ $expResult = new ContainsExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
		
	|	^(MATCHES e1=expression[DataType.STRING, null] e2=expression[DataType.STRING, null] )
		{ $expResult = new MatchesStringExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
		
	|	^(CONCAT e1=expression[DataType.STRING, null] e2=expression[DataType.STRING, null] ) 
		{ $expResult = new ConcatenationExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
		
	|	^(STARTSWITH e1=expression[DataType.STRING, null] e2=expression[DataType.STRING, null] )
		{ $expResult = new StartsWithExpression($e1.expResult, $e2.expResult, $recoveryExpression); }
	
		// Array access.	
	|	^('[' e=expression[DataType.ARRAY, null] i=INTEGER )
		{ $expResult = new ArrayAccessExpression($e.expResult, Integer.parseInt($i.getText()), $recoveryExpression); } 						
	 
	 	// Array length
	|	^(LENGTH e=expression[DataType.ARRAY, null] )
		{ $expResult = new ArrayLengthExpression($e.expResult, $recoveryExpression); }		
		
		// Range function	  
				
	|	^(RANGE e=expression[DataType.REAL, null] 
			{ boolean closedStart = true; boolean closedEnd = true; } 
			(('[') | ('(' { closedStart = false; }))  
			((']') | (')' { closedEnd = false; })) 
			startExpression=expression[DataType.REAL, null] endExpression=expression[DataType.REAL, null] )
		{
			$expResult = new RangeExpression($e.expResult, $startExpression.expResult, $endExpression.expResult, 
											 closedStart, closedEnd, $recoveryExpression);	  	 		 	
		} 
			 		
		// Arrays (translates all the expressions into data values and create the array).
					 		
	|	^(ARRAY 
			{ List<Expression> arrayExpressions = new ArrayList<Expression>(); } 
			(e=expression[DataType.STRING, null] { arrayExpressions.add($e.expResult); })*) 
		{ 
			try
			{
				List<DataValue> arrayValues = new ArrayList<DataValue>();
				for(Expression currentExpression : arrayExpressions)
					arrayValues.add(currentExpression.calculateExpressionValue(null));
					
				$expResult = new LiteralExpression(new ArrayValue(arrayValues),null);
			}
			catch(ExpressionEvaluationException evalException)
			{
				// TODO Deal with the error.
				evalException.printStackTrace();  
			}						
		}			 		
			 			 		 
		// Atoms.					 
					 
	|	id=ID 
		{	
			DataType inferredDataType = $inferredType; 
			Expression variableExpression = $selectionBlock::variableMap.get($id.getText());			
			
			// Check if we are dealing with a local variable or with an attribute reference.
			if(variableExpression != null)
			{
				$expResult = variableExpression;
			}
			else
				$expResult = new ReferenceExpression($id.getText(), inferredDataType,  $recoveryExpression); 
		}
	
	|	d=DATE
		{ 
			String stringDate = $d.getText();
			int year = Integer.parseInt(stringDate.substring(1,5));
			int month = Integer.parseInt(stringDate.substring(6,8));
			int day = Integer.parseInt(stringDate.substring(9,11));
			$expResult = new LiteralExpression(new DateValue(year,month,day),null); 
		}	
	
	|	i=INTEGER
		{ $expResult = new LiteralExpression(new IntegerValue(Integer.parseInt($i.getText())),null); }
	
	| 	r=REAL  
		{ $expResult = new LiteralExpression(new RealValue(Float.parseFloat($r.getText())),null); }
	
	| 	qs=QUOTED_STRING
		{ $expResult = new LiteralExpression(new StringValue($qs.getText().substring(1,$qs.getText().length()-1)),null); }		
		
	|	t=TRUE
		{ $expResult = new LiteralExpression(new BooleanValue(true),null);}
		
	|	f=FALSE
		{ $expResult = new LiteralExpression(new BooleanValue(false),null);}
		
	|	n=NULL
		{ $expResult = new LiteralExpression(new NullValue(),null); }
		
	|	^(RECOVERY er=expression[DataType.STRING, null] e=expression[DataType.BOOLEAN, $er.expResult])
		{ $expResult = $e.expResult; }				
	
	;						
