// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g 2011-10-20 10:15:18

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


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

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
public class CQLTreeWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CQL_SCRIPT", "DATA_SOURCE", "SELECTION_DEFINITION", "VARIABLES", "CONDITION", "SELECTION_OPTIONS", "LABEL", "ARGUMENT", "PROPERTY", "MINUS", "ARRAY", "RESULT_DEFINITION", "RESULT_PROPERTIES", "REGULAR_SELECTION_RESULT", "COMBINED_SELECTION_RESULT", "STATS_SELECTION_RESULT", "DISTINCT_SELECTION_RESULT", "SELECT", "FROM", "ID", "VARS", "WHERE", "SORT", "BY", "DESCENDING", "DESC", "LIMIT", "TOP", "BOTTOM", "RANDOM", "INTEGER", "AS", "QUOTED_STRING", "RESULT", "DISTINCT", "AVG", "MEDIAN", "MIN", "MAX", "STDDEV", "VAR", "SUM", "COUNT", "UNION", "INTERSECTION", "DIFFERENCE", "OR", "AND", "EQ", "GE", "LE", "GT", "LT", "NOT", "PLUS", "DIFF", "MULT", "DIV", "POWER", "SQRT", "LOG", "LN", "ABS", "CONTAINS", "MATCHES", "STARTSWITH", "CONCAT", "LENGTH", "RANGE", "REAL", "TRUE", "FALSE", "NULL", "RECOVERY", "S", "E", "L", "C", "T", "V", "A", "R", "W", "H", "F", "O", "M", "N", "I", "G", "D", "X", "U", "B", "Y", "P", "ASSIGN", "Q", "NUMBER", "DATE", "EXPONENT", "LINE_COMMENT", "BLANK", "J", "K", "Z", "';'", "'{'", "'}'", "'('", "','", "')'", "'['", "']'", "'.'"
    };
    public static final int EOF=-1;
    public static final int T__110=110;
    public static final int T__111=111;
    public static final int T__112=112;
    public static final int T__113=113;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__118=118;
    public static final int CQL_SCRIPT=4;
    public static final int DATA_SOURCE=5;
    public static final int SELECTION_DEFINITION=6;
    public static final int VARIABLES=7;
    public static final int CONDITION=8;
    public static final int SELECTION_OPTIONS=9;
    public static final int LABEL=10;
    public static final int ARGUMENT=11;
    public static final int PROPERTY=12;
    public static final int MINUS=13;
    public static final int ARRAY=14;
    public static final int RESULT_DEFINITION=15;
    public static final int RESULT_PROPERTIES=16;
    public static final int REGULAR_SELECTION_RESULT=17;
    public static final int COMBINED_SELECTION_RESULT=18;
    public static final int STATS_SELECTION_RESULT=19;
    public static final int DISTINCT_SELECTION_RESULT=20;
    public static final int SELECT=21;
    public static final int FROM=22;
    public static final int ID=23;
    public static final int VARS=24;
    public static final int WHERE=25;
    public static final int SORT=26;
    public static final int BY=27;
    public static final int DESCENDING=28;
    public static final int DESC=29;
    public static final int LIMIT=30;
    public static final int TOP=31;
    public static final int BOTTOM=32;
    public static final int RANDOM=33;
    public static final int INTEGER=34;
    public static final int AS=35;
    public static final int QUOTED_STRING=36;
    public static final int RESULT=37;
    public static final int DISTINCT=38;
    public static final int AVG=39;
    public static final int MEDIAN=40;
    public static final int MIN=41;
    public static final int MAX=42;
    public static final int STDDEV=43;
    public static final int VAR=44;
    public static final int SUM=45;
    public static final int COUNT=46;
    public static final int UNION=47;
    public static final int INTERSECTION=48;
    public static final int DIFFERENCE=49;
    public static final int OR=50;
    public static final int AND=51;
    public static final int EQ=52;
    public static final int GE=53;
    public static final int LE=54;
    public static final int GT=55;
    public static final int LT=56;
    public static final int NOT=57;
    public static final int PLUS=58;
    public static final int DIFF=59;
    public static final int MULT=60;
    public static final int DIV=61;
    public static final int POWER=62;
    public static final int SQRT=63;
    public static final int LOG=64;
    public static final int LN=65;
    public static final int ABS=66;
    public static final int CONTAINS=67;
    public static final int MATCHES=68;
    public static final int STARTSWITH=69;
    public static final int CONCAT=70;
    public static final int LENGTH=71;
    public static final int RANGE=72;
    public static final int REAL=73;
    public static final int TRUE=74;
    public static final int FALSE=75;
    public static final int NULL=76;
    public static final int RECOVERY=77;
    public static final int S=78;
    public static final int E=79;
    public static final int L=80;
    public static final int C=81;
    public static final int T=82;
    public static final int V=83;
    public static final int A=84;
    public static final int R=85;
    public static final int W=86;
    public static final int H=87;
    public static final int F=88;
    public static final int O=89;
    public static final int M=90;
    public static final int N=91;
    public static final int I=92;
    public static final int G=93;
    public static final int D=94;
    public static final int X=95;
    public static final int U=96;
    public static final int B=97;
    public static final int Y=98;
    public static final int P=99;
    public static final int ASSIGN=100;
    public static final int Q=101;
    public static final int NUMBER=102;
    public static final int DATE=103;
    public static final int EXPONENT=104;
    public static final int LINE_COMMENT=105;
    public static final int BLANK=106;
    public static final int J=107;
    public static final int K=108;
    public static final int Z=109;

    // delegates
    // delegators


        public CQLTreeWalker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public CQLTreeWalker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CQLTreeWalker.tokenNames; }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g"; }


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


    protected static class cqlScript_scope {
        Map<String, SelectionProxy> selectionMap;
        ResultGenerator resultGenerator;
    }
    protected Stack cqlScript_stack = new Stack();

    public static class cqlScript_return extends TreeRuleReturnScope {
        public Map<String, SelectionProxy> scriptSelectionMap;
        public ResultGenerator scriptResultGenerator;;
    };

    // $ANTLR start "cqlScript"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:156:1: cqlScript returns [Map<String, SelectionProxy> scriptSelectionMap, ResultGenerator scriptResultGenerator;] : ^( CQL_SCRIPT ( selectionBlock )* ( resultBlock )* ) ;
    public final CQLTreeWalker.cqlScript_return cqlScript() throws RecognitionException {
        cqlScript_stack.push(new cqlScript_scope());
        CQLTreeWalker.cqlScript_return retval = new CQLTreeWalker.cqlScript_return();
        retval.start = input.LT(1);

         
        		((cqlScript_scope)cqlScript_stack.peek()).selectionMap = new HashMap<String, SelectionProxy>();	 
        		((cqlScript_scope)cqlScript_stack.peek()).resultGenerator = new ResultGenerator(); 
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:177:2: ( ^( CQL_SCRIPT ( selectionBlock )* ( resultBlock )* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:177:4: ^( CQL_SCRIPT ( selectionBlock )* ( resultBlock )* )
            {
            match(input,CQL_SCRIPT,FOLLOW_CQL_SCRIPT_in_cqlScript98); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:177:17: ( selectionBlock )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==SELECT) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:177:17: selectionBlock
                	    {
                	    pushFollow(FOLLOW_selectionBlock_in_cqlScript100);
                	    selectionBlock();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:177:33: ( resultBlock )*
                loop2:
                do {
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==RESULT) ) {
                        alt2=1;
                    }


                    switch (alt2) {
                	case 1 :
                	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:177:33: resultBlock
                	    {
                	    pushFollow(FOLLOW_resultBlock_in_cqlScript103);
                	    resultBlock();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop2;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
            								
            			// Returning the map of proxy selections.
            			retval.scriptSelectionMap = ((cqlScript_scope)cqlScript_stack.peek()).selectionMap;
            			
            			// Returning the result generate for the script (can be null).
            			retval.scriptResultGenerator = ((cqlScript_scope)cqlScript_stack.peek()).resultGenerator;																	
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            cqlScript_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "cqlScript"

    protected static class selectionBlock_scope {
        QueryBuilder queryBuilder;
        Map<String, Expression> variableMap;
    }
    protected Stack selectionBlock_stack = new Stack();


    // $ANTLR start "selectionBlock"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:193:1: selectionBlock : ^( SELECT ^( DATA_SOURCE i= ID ) ld= labelDefinition sbd= selectionBlockDefinition sbo= selectionBlockOptions ) ;
    public final void selectionBlock() throws RecognitionException {
        selectionBlock_stack.push(new selectionBlock_scope());
        CommonTree i=null;
        String ld = null;


         
        		((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder = new QueryBuilder();  
        		((selectionBlock_scope)selectionBlock_stack.peek()).variableMap = new HashMap<String, Expression>();			
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:205:2: ( ^( SELECT ^( DATA_SOURCE i= ID ) ld= labelDefinition sbd= selectionBlockDefinition sbo= selectionBlockOptions ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:205:4: ^( SELECT ^( DATA_SOURCE i= ID ) ld= labelDefinition sbd= selectionBlockDefinition sbo= selectionBlockOptions )
            {
            match(input,SELECT,FOLLOW_SELECT_in_selectionBlock153); 

            match(input, Token.DOWN, null); 
            match(input,DATA_SOURCE,FOLLOW_DATA_SOURCE_in_selectionBlock156); 

            match(input, Token.DOWN, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_selectionBlock160); 

            match(input, Token.UP, null); 
            pushFollow(FOLLOW_labelDefinition_in_selectionBlock165);
            ld=labelDefinition();

            state._fsp--;


            			// Associate the query builder with the source selection label (FROM)
            			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setSourceSelectionLabel(i.getText());
            			
            			// Associate the query builder with the label.			
            			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setQueryLabel(ld);			
            			
            			// Register the selection proxy in the symbol table.
            			// Check if the proxy is already in the table.
            			SelectionProxy proxySelection = ((cqlScript_scope)cqlScript_stack.peek()).selectionMap.get(ld);
            			
            			if(proxySelection == null) 
            				proxySelection = new SelectionProxy(ld);
            				
            			try 
            			{
            				proxySelection.setSelectionQueryBuilder(((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder);							
            				((cqlScript_scope)cqlScript_stack.peek()).selectionMap.put(ld, proxySelection);
            			}
            			catch(SelectionException e)
            			{
            				// TODO deal with the error.
                    		e.printStackTrace();					
            			}
            		
            pushFollow(FOLLOW_selectionBlockDefinition_in_selectionBlock181);
            selectionBlockDefinition();

            state._fsp--;

            pushFollow(FOLLOW_selectionBlockOptions_in_selectionBlock185);
            selectionBlockOptions();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            selectionBlock_stack.pop();
        }
        return ;
    }
    // $ANTLR end "selectionBlock"


    // $ANTLR start "selectionBlockDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:235:1: selectionBlockDefinition : ^( SELECTION_DEFINITION variablesBlock ^( CONDITION e= expression[DataType.BOOLEAN, null] ) ) ;
    public final void selectionBlockDefinition() throws RecognitionException {
        Expression e = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:237:2: ( ^( SELECTION_DEFINITION variablesBlock ^( CONDITION e= expression[DataType.BOOLEAN, null] ) ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:237:4: ^( SELECTION_DEFINITION variablesBlock ^( CONDITION e= expression[DataType.BOOLEAN, null] ) )
            {
            match(input,SELECTION_DEFINITION,FOLLOW_SELECTION_DEFINITION_in_selectionBlockDefinition203); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_variablesBlock_in_selectionBlockDefinition206);
            variablesBlock();

            state._fsp--;

            match(input,CONDITION,FOLLOW_CONDITION_in_selectionBlockDefinition209); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_selectionBlockDefinition213);
            e=expression(DataType.BOOLEAN, null);

            state._fsp--;


            match(input, Token.UP, null); 

            match(input, Token.UP, null); 

            			// Register the condition on the query builder.
            			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setQueryCondition(new QueryCondition(e)); 
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "selectionBlockDefinition"


    // $ANTLR start "variablesBlock"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:244:1: variablesBlock : ( ^( VARIABLES ( variableDefinition )+ ) | );
    public final void variablesBlock() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:245:2: ( ^( VARIABLES ( variableDefinition )+ ) | )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==VARIABLES) ) {
                alt4=1;
            }
            else if ( (LA4_0==CONDITION) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:245:4: ^( VARIABLES ( variableDefinition )+ )
                    {
                    match(input,VARIABLES,FOLLOW_VARIABLES_in_variablesBlock236); 

                    match(input, Token.DOWN, null); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:245:16: ( variableDefinition )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==VAR) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:245:16: variableDefinition
                    	    {
                    	    pushFollow(FOLLOW_variableDefinition_in_variablesBlock238);
                    	    variableDefinition();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:247:2: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "variablesBlock"


    // $ANTLR start "variableDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:249:1: variableDefinition : ^( VAR i= ID e= expression[DataType.STRING, null] ) ;
    public final void variableDefinition() throws RecognitionException {
        CommonTree i=null;
        Expression e = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:251:2: ( ^( VAR i= ID e= expression[DataType.STRING, null] ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:251:4: ^( VAR i= ID e= expression[DataType.STRING, null] )
            {
            match(input,VAR,FOLLOW_VAR_in_variableDefinition260); 

            match(input, Token.DOWN, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_variableDefinition264); 
            pushFollow(FOLLOW_expression_in_variableDefinition268);
            e=expression(DataType.STRING, null);

            state._fsp--;


            match(input, Token.UP, null); 
              						
            			// Register the variable in the query builder.
            			Expression variable = new VariableExpression(i.getText(), e);				
            			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.registerVariable(i.getText(), variable);
            			 
            			// Register the variable on the map.
            			((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.put(i.getText(), variable);		
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "variableDefinition"


    // $ANTLR start "selectionBlockOptions"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:265:1: selectionBlockOptions : ^( SELECTION_OPTIONS ( selectionBlockOptionsDefinition )* ) ;
    public final void selectionBlockOptions() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:266:2: ( ^( SELECTION_OPTIONS ( selectionBlockOptionsDefinition )* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:266:4: ^( SELECTION_OPTIONS ( selectionBlockOptionsDefinition )* )
            {
            match(input,SELECTION_OPTIONS,FOLLOW_SELECTION_OPTIONS_in_selectionBlockOptions293); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:266:24: ( selectionBlockOptionsDefinition )*
                loop5:
                do {
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==SORT||LA5_0==LIMIT) ) {
                        alt5=1;
                    }


                    switch (alt5) {
                	case 1 :
                	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:266:24: selectionBlockOptionsDefinition
                	    {
                	    pushFollow(FOLLOW_selectionBlockOptionsDefinition_in_selectionBlockOptions295);
                	    selectionBlockOptionsDefinition();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop5;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "selectionBlockOptions"


    // $ANTLR start "selectionBlockOptionsDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:269:1: selectionBlockOptionsDefinition : ( sortOptions | limitOptions );
    public final void selectionBlockOptionsDefinition() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:270:2: ( sortOptions | limitOptions )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SORT) ) {
                alt6=1;
            }
            else if ( (LA6_0==LIMIT) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:270:4: sortOptions
                    {
                    pushFollow(FOLLOW_sortOptions_in_selectionBlockOptionsDefinition310);
                    sortOptions();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:271:4: limitOptions
                    {
                    pushFollow(FOLLOW_limitOptions_in_selectionBlockOptionsDefinition316);
                    limitOptions();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "selectionBlockOptionsDefinition"


    // $ANTLR start "sortOptions"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:274:1: sortOptions : ( ^( SORT i= ID d= DESC ) | ^( SORT i= ID ) );
    public final void sortOptions() throws RecognitionException {
        CommonTree i=null;
        CommonTree d=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:275:2: ( ^( SORT i= ID d= DESC ) | ^( SORT i= ID ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==SORT) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==DOWN) ) {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==ID) ) {
                        int LA7_3 = input.LA(4);

                        if ( (LA7_3==DESC) ) {
                            alt7=1;
                        }
                        else if ( (LA7_3==UP) ) {
                            alt7=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:275:5: ^( SORT i= ID d= DESC )
                    {
                    match(input,SORT,FOLLOW_SORT_in_sortOptions332); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_sortOptions336); 
                    d=(CommonTree)match(input,DESC,FOLLOW_DESC_in_sortOptions340); 

                    match(input, Token.UP, null); 
                     
                    			// Creates a new descent sorter that will use a property or a variable (if no variable was registered in the map
                    			// for the given name we assume it was referencing a property, we'll pass null to the constructor). 
                    			EntitySorter sorter = new SinglePropertyEntitySorter(i.getText(), ((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),true);
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setSelectionSorter(sorter); 
                    		

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:283:5: ^( SORT i= ID )
                    {
                    match(input,SORT,FOLLOW_SORT_in_sortOptions354); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_sortOptions358); 

                    match(input, Token.UP, null); 
                     
                    			// Creates a new ascendent sorter that will use a property or a variable (if no variable was registered in the map
                    			// for the given name we assume it was referencing a property, we'll pass null to the constructor). 
                    			EntitySorter sorter = new SinglePropertyEntitySorter(i.getText(), ((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),false);
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setSelectionSorter(sorter); 
                    		

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "sortOptions"


    // $ANTLR start "limitOptions"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:292:1: limitOptions : ^( LIMIT ( limitDefinition )+ ) ;
    public final void limitOptions() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:293:2: ( ^( LIMIT ( limitDefinition )+ ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:293:4: ^( LIMIT ( limitDefinition )+ )
            {
            match(input,LIMIT,FOLLOW_LIMIT_in_limitOptions377); 

            match(input, Token.DOWN, null); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:293:12: ( limitDefinition )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=TOP && LA8_0<=RANDOM)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:293:12: limitDefinition
            	    {
            	    pushFollow(FOLLOW_limitDefinition_in_limitOptions379);
            	    limitDefinition();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "limitOptions"


    // $ANTLR start "limitDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:298:1: limitDefinition : ( ^( TOP size= INTEGER (offset= INTEGER )? ) | ^( BOTTOM size= INTEGER (offset= INTEGER )? ) | ^( RANDOM size= INTEGER seed= INTEGER ) | ^( RANDOM size= INTEGER ) );
    public final void limitDefinition() throws RecognitionException {
        CommonTree size=null;
        CommonTree offset=null;
        CommonTree seed=null;

         int limitOffset = 0; 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:301:2: ( ^( TOP size= INTEGER (offset= INTEGER )? ) | ^( BOTTOM size= INTEGER (offset= INTEGER )? ) | ^( RANDOM size= INTEGER seed= INTEGER ) | ^( RANDOM size= INTEGER ) )
            int alt11=4;
            switch ( input.LA(1) ) {
            case TOP:
                {
                alt11=1;
                }
                break;
            case BOTTOM:
                {
                alt11=2;
                }
                break;
            case RANDOM:
                {
                int LA11_3 = input.LA(2);

                if ( (LA11_3==DOWN) ) {
                    int LA11_4 = input.LA(3);

                    if ( (LA11_4==INTEGER) ) {
                        int LA11_5 = input.LA(4);

                        if ( (LA11_5==INTEGER) ) {
                            alt11=3;
                        }
                        else if ( (LA11_5==UP) ) {
                            alt11=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 11, 5, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:301:4: ^( TOP size= INTEGER (offset= INTEGER )? )
                    {
                    match(input,TOP,FOLLOW_TOP_in_limitDefinition404); 

                    match(input, Token.DOWN, null); 
                    size=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limitDefinition408); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:301:23: (offset= INTEGER )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==INTEGER) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:301:24: offset= INTEGER
                            {
                            offset=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limitDefinition413); 
                             limitOffset = Integer.parseInt(offset.getText()); 

                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                     ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setSelectionLimiter(new TopLimiter(Integer.parseInt(size.getText()), limitOffset)); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:304:4: ^( BOTTOM size= INTEGER (offset= INTEGER )? )
                    {
                    match(input,BOTTOM,FOLLOW_BOTTOM_in_limitDefinition430); 

                    match(input, Token.DOWN, null); 
                    size=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limitDefinition434); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:304:26: (offset= INTEGER )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==INTEGER) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:304:27: offset= INTEGER
                            {
                            offset=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limitDefinition439); 
                             limitOffset = Integer.parseInt(offset.getText()); 

                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                     ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setSelectionLimiter(new BottomLimiter(Integer.parseInt(size.getText()), limitOffset)); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:307:4: ^( RANDOM size= INTEGER seed= INTEGER )
                    {
                    match(input,RANDOM,FOLLOW_RANDOM_in_limitDefinition456); 

                    match(input, Token.DOWN, null); 
                    size=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limitDefinition460); 
                    seed=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limitDefinition464); 

                    match(input, Token.UP, null); 
                     ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setSelectionLimiter(new RandomLimiter(Integer.parseInt(size.getText()), Integer.parseInt(seed.getText()))); 

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:310:4: ^( RANDOM size= INTEGER )
                    {
                    match(input,RANDOM,FOLLOW_RANDOM_in_limitDefinition480); 

                    match(input, Token.DOWN, null); 
                    size=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_limitDefinition484); 

                    match(input, Token.UP, null); 
                     ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.setSelectionLimiter(new RandomLimiter(Integer.parseInt(size.getText()))); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "limitDefinition"


    // $ANTLR start "labelDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:319:1: labelDefinition returns [String labelString] : ln= labelName ;
    public final String labelDefinition() throws RecognitionException {
        String labelString = null;

        String ln = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:320:2: (ln= labelName )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:320:4: ln= labelName
            {
            pushFollow(FOLLOW_labelName_in_labelDefinition514);
            ln=labelName();

            state._fsp--;

             labelString = ln; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return labelString;
    }
    // $ANTLR end "labelDefinition"


    // $ANTLR start "labelName"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:324:1: labelName returns [String labelString] : ( ^( LABEL i= ID ) | ^( LABEL qs= QUOTED_STRING ) );
    public final String labelName() throws RecognitionException {
        String labelString = null;

        CommonTree i=null;
        CommonTree qs=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:326:2: ( ^( LABEL i= ID ) | ^( LABEL qs= QUOTED_STRING ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==LABEL) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==DOWN) ) {
                    int LA12_2 = input.LA(3);

                    if ( (LA12_2==ID) ) {
                        alt12=1;
                    }
                    else if ( (LA12_2==QUOTED_STRING) ) {
                        alt12=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:326:4: ^( LABEL i= ID )
                    {
                    match(input,LABEL,FOLLOW_LABEL_in_labelName541); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_labelName545); 

                    match(input, Token.UP, null); 
                     labelString = i.getText(); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:329:4: ^( LABEL qs= QUOTED_STRING )
                    {
                    match(input,LABEL,FOLLOW_LABEL_in_labelName560); 

                    match(input, Token.DOWN, null); 
                    qs=(CommonTree)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_labelName564); 

                    match(input, Token.UP, null); 
                     labelString = qs.getText().substring(1,qs.getText().length()-1);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return labelString;
    }
    // $ANTLR end "labelName"


    // $ANTLR start "optionalLabelName"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:335:1: optionalLabelName returns [String labelString] : (ln= labelName | );
    public final String optionalLabelName() throws RecognitionException {
        String labelString = null;

        String ln = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:336:2: (ln= labelName | )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LABEL) ) {
                alt13=1;
            }
            else if ( (LA13_0==UP) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:336:4: ln= labelName
                    {
                    pushFollow(FOLLOW_labelName_in_optionalLabelName587);
                    ln=labelName();

                    state._fsp--;

                     labelString = ln; 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:337:17: 
                    {
                     labelString = null; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return labelString;
    }
    // $ANTLR end "optionalLabelName"

    protected static class resultBlock_scope {
        ResultBuilderBase resultBuilder;
    }
    protected Stack resultBlock_stack = new Stack();


    // $ANTLR start "resultBlock"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:345:1: resultBlock : ^( RESULT resultDefinition ) ;
    public final void resultBlock() throws RecognitionException {
        resultBlock_stack.push(new resultBlock_scope());

        		((resultBlock_scope)resultBlock_stack.peek()).resultBuilder = null;
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:356:2: ( ^( RESULT resultDefinition ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:356:4: ^( RESULT resultDefinition )
            {
            match(input,RESULT,FOLLOW_RESULT_in_resultBlock630); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_resultDefinition_in_resultBlock632);
            resultDefinition();

            state._fsp--;


            match(input, Token.UP, null); 
            					 		
            			if(((cqlScript_scope)cqlScript_stack.peek()).resultGenerator.hasResultBuilder())
            				throw new CQLSemanticException("CQL error: more than one result block has been defined");
            		
            			// Sets the result generator at script level.
            			((cqlScript_scope)cqlScript_stack.peek()).resultGenerator.setResultBuilder(((resultBlock_scope)resultBlock_stack.peek()).resultBuilder);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            resultBlock_stack.pop();
        }
        return ;
    }
    // $ANTLR end "resultBlock"


    // $ANTLR start "resultDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:366:1: resultDefinition : ^( RESULT_DEFINITION resultExpression ( propertySelection )? (ld= labelDefinition )? ) ;
    public final void resultDefinition() throws RecognitionException {
        String ld = null;


         						
        		// Will store the optional label for the result
        		String label = null;
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:373:2: ( ^( RESULT_DEFINITION resultExpression ( propertySelection )? (ld= labelDefinition )? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:373:4: ^( RESULT_DEFINITION resultExpression ( propertySelection )? (ld= labelDefinition )? )
            {
            match(input,RESULT_DEFINITION,FOLLOW_RESULT_DEFINITION_in_resultDefinition669); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_resultExpression_in_resultDefinition671);
            resultExpression();

            state._fsp--;

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:373:41: ( propertySelection )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RESULT_PROPERTIES) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:373:41: propertySelection
                    {
                    pushFollow(FOLLOW_propertySelection_in_resultDefinition673);
                    propertySelection();

                    state._fsp--;


                    }
                    break;

            }

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:373:60: (ld= labelDefinition )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==LABEL) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:373:61: ld= labelDefinition
                    {
                    pushFollow(FOLLOW_labelDefinition_in_resultDefinition679);
                    ld=labelDefinition();

                    state._fsp--;

                     label = ld; 

                    }
                    break;

            }


            match(input, Token.UP, null); 

            			// Set the field label only if it was specified.
            			if(label != null)
            				((resultBlock_scope)resultBlock_stack.peek()).resultBuilder.setResultLabel(label); 
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "resultDefinition"


    // $ANTLR start "resultExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:387:1: resultExpression : ( ^( REGULAR_SELECTION_RESULT i= ID ) | ^( REGULAR_SELECTION_RESULT qs= QUOTED_STRING ) | ^( DISTINCT_SELECTION_RESULT i= ID ) | ^( DISTINCT_SELECTION_RESULT qs= QUOTED_STRING ) | ^( COMBINED_SELECTION_RESULT sf= setOperation ) | ^( STATS_SELECTION_RESULT resultStatisticalFunction ) );
    public final void resultExpression() throws RecognitionException {
        CommonTree i=null;
        CommonTree qs=null;
        SelectionSetCombination sf = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:389:2: ( ^( REGULAR_SELECTION_RESULT i= ID ) | ^( REGULAR_SELECTION_RESULT qs= QUOTED_STRING ) | ^( DISTINCT_SELECTION_RESULT i= ID ) | ^( DISTINCT_SELECTION_RESULT qs= QUOTED_STRING ) | ^( COMBINED_SELECTION_RESULT sf= setOperation ) | ^( STATS_SELECTION_RESULT resultStatisticalFunction ) )
            int alt16=6;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:389:4: ^( REGULAR_SELECTION_RESULT i= ID )
                    {
                    match(input,REGULAR_SELECTION_RESULT,FOLLOW_REGULAR_SELECTION_RESULT_in_resultExpression710); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultExpression714); 

                    match(input, Token.UP, null); 
                     
                    			((resultBlock_scope)resultBlock_stack.peek()).resultBuilder = new RegularResultBuilder(i.getText()); 
                    		

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:394:4: ^( REGULAR_SELECTION_RESULT qs= QUOTED_STRING )
                    {
                    match(input,REGULAR_SELECTION_RESULT,FOLLOW_REGULAR_SELECTION_RESULT_in_resultExpression728); 

                    match(input, Token.DOWN, null); 
                    qs=(CommonTree)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_resultExpression732); 

                    match(input, Token.UP, null); 
                     
                    			((resultBlock_scope)resultBlock_stack.peek()).resultBuilder = new RegularResultBuilder(qs.getText().substring(1,qs.getText().length()-1)); 
                    		

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:399:7: ^( DISTINCT_SELECTION_RESULT i= ID )
                    {
                    match(input,DISTINCT_SELECTION_RESULT,FOLLOW_DISTINCT_SELECTION_RESULT_in_resultExpression749); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultExpression753); 

                    match(input, Token.UP, null); 
                     
                          		((resultBlock_scope)resultBlock_stack.peek()).resultBuilder = new DistinctResultBuilder(i.getText()); 
                        	

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:404:6: ^( DISTINCT_SELECTION_RESULT qs= QUOTED_STRING )
                    {
                    match(input,DISTINCT_SELECTION_RESULT,FOLLOW_DISTINCT_SELECTION_RESULT_in_resultExpression774); 

                    match(input, Token.DOWN, null); 
                    qs=(CommonTree)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_resultExpression778); 

                    match(input, Token.UP, null); 
                     
                          		((resultBlock_scope)resultBlock_stack.peek()).resultBuilder = new DistinctResultBuilder(qs.getText().substring(1,qs.getText().length()-1)); 
                        	

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:409:4: ^( COMBINED_SELECTION_RESULT sf= setOperation )
                    {
                    match(input,COMBINED_SELECTION_RESULT,FOLLOW_COMBINED_SELECTION_RESULT_in_resultExpression794); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_setOperation_in_resultExpression798);
                    sf=setOperation();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			CombinedResultBuilder builder = new CombinedResultBuilder(); 
                    			builder.setSelectionCombination(sf);
                    			((resultBlock_scope)resultBlock_stack.peek()).resultBuilder = builder;
                    		

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:416:4: ^( STATS_SELECTION_RESULT resultStatisticalFunction )
                    {
                    match(input,STATS_SELECTION_RESULT,FOLLOW_STATS_SELECTION_RESULT_in_resultExpression812); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_resultStatisticalFunction_in_resultExpression814);
                    resultStatisticalFunction();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "resultExpression"


    // $ANTLR start "resultStatisticalFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:426:1: resultStatisticalFunction : ( ^( AVG ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( MEDIAN ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( MIN ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( MAX ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( STDDEV ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( VAR ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( SUM ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( COUNT ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) );
    public final void resultStatisticalFunction() throws RecognitionException {
        CommonTree i=null;
        CommonTree p=null;

        		
        		StatsResultBuilder statsResultBuilder =  new StatsResultBuilder();
        		((resultBlock_scope)resultBlock_stack.peek()).resultBuilder = statsResultBuilder; 
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:433:2: ( ^( AVG ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( MEDIAN ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( MIN ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( MAX ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( STDDEV ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( VAR ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( SUM ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) | ^( COUNT ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) ) )
            int alt17=8;
            switch ( input.LA(1) ) {
            case AVG:
                {
                alt17=1;
                }
                break;
            case MEDIAN:
                {
                alt17=2;
                }
                break;
            case MIN:
                {
                alt17=3;
                }
                break;
            case MAX:
                {
                alt17=4;
                }
                break;
            case STDDEV:
                {
                alt17=5;
                }
                break;
            case VAR:
                {
                alt17=6;
                }
                break;
            case SUM:
                {
                alt17=7;
                }
                break;
            case COUNT:
                {
                alt17=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:433:4: ^( AVG ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,AVG,FOLLOW_AVG_in_resultStatisticalFunction851); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction854); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction858); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction862); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction866); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 			
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.AVG);
                    			statsResultBuilder.addProperty(p.getText(), null); 
                    		

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:440:4: ^( MEDIAN ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,MEDIAN,FOLLOW_MEDIAN_in_resultStatisticalFunction881); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction884); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction888); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction892); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction896); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.MEDIAN);
                    			statsResultBuilder.addProperty(p.getText(), null);
                    		

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:447:4: ^( MIN ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,MIN,FOLLOW_MIN_in_resultStatisticalFunction911); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction914); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction918); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction922); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction926); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.MIN);
                    			statsResultBuilder.addProperty(p.getText(), null);
                    		

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:454:4: ^( MAX ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,MAX,FOLLOW_MAX_in_resultStatisticalFunction941); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction944); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction948); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction952); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction956); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.MAX);
                    			statsResultBuilder.addProperty(p.getText(), null);
                    		

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:461:4: ^( STDDEV ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,STDDEV,FOLLOW_STDDEV_in_resultStatisticalFunction972); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction975); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction979); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction983); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction987); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.STDDEV);
                    			statsResultBuilder.addProperty(p.getText(), null);
                    		

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:468:4: ^( VAR ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,VAR,FOLLOW_VAR_in_resultStatisticalFunction1002); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction1005); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1009); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction1013); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1017); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.AVG);
                    			statsResultBuilder.addProperty(p.getText(), null);
                    		

                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:475:4: ^( SUM ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,SUM,FOLLOW_SUM_in_resultStatisticalFunction1033); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction1036); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1040); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction1044); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1048); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.SUM);
                    			statsResultBuilder.addProperty(p.getText(), null);
                    		

                    }
                    break;
                case 8 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:482:4: ^( COUNT ^( ARGUMENT i= ID ) ^( PROPERTY p= ID ) )
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_resultStatisticalFunction1063); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_resultStatisticalFunction1066); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1070); 

                    match(input, Token.UP, null); 
                    match(input,PROPERTY,FOLLOW_PROPERTY_in_resultStatisticalFunction1074); 

                    match(input, Token.DOWN, null); 
                    p=(CommonTree)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1078); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                    			 
                    			statsResultBuilder.setSelectionName(i.getText());
                    			statsResultBuilder.setStatsType(StatsType.COUNT);
                    			statsResultBuilder.addProperty(p.getText(), null);
                    		

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "resultStatisticalFunction"


    // $ANTLR start "setOperation"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:493:1: setOperation returns [SelectionSetCombination combination] : ( ^( UNION attr1= setOperationAttribute attr2= setOperationAttribute ) | ^( INTERSECTION attr1= setOperationAttribute attr2= setOperationAttribute ) | ^( DIFFERENCE attr1= setOperationAttribute attr2= setOperationAttribute ) );
    public final SelectionSetCombination setOperation() throws RecognitionException {
        SelectionSetCombination combination = null;

        CQLTreeWalker.setOperationAttribute_return attr1 = null;

        CQLTreeWalker.setOperationAttribute_return attr2 = null;



        		combination = new SelectionSetCombination();
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:499:2: ( ^( UNION attr1= setOperationAttribute attr2= setOperationAttribute ) | ^( INTERSECTION attr1= setOperationAttribute attr2= setOperationAttribute ) | ^( DIFFERENCE attr1= setOperationAttribute attr2= setOperationAttribute ) )
            int alt18=3;
            switch ( input.LA(1) ) {
            case UNION:
                {
                alt18=1;
                }
                break;
            case INTERSECTION:
                {
                alt18=2;
                }
                break;
            case DIFFERENCE:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:499:4: ^( UNION attr1= setOperationAttribute attr2= setOperationAttribute )
                    {
                    match(input,UNION,FOLLOW_UNION_in_setOperation1112); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_setOperationAttribute_in_setOperation1116);
                    attr1=setOperationAttribute();

                    state._fsp--;

                    pushFollow(FOLLOW_setOperationAttribute_in_setOperation1120);
                    attr2=setOperationAttribute();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			combination.setOperationType(SetOperationType.UNION);		
                    			configureSelectionSetCombination(combination, (attr1!=null?attr1.selectionId:null), (attr1!=null?attr1.combination:null),
                    											 (attr2!=null?attr2.selectionId:null), (attr2!=null?attr2.combination:null)); 									
                    		

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:506:4: ^( INTERSECTION attr1= setOperationAttribute attr2= setOperationAttribute )
                    {
                    match(input,INTERSECTION,FOLLOW_INTERSECTION_in_setOperation1134); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_setOperationAttribute_in_setOperation1138);
                    attr1=setOperationAttribute();

                    state._fsp--;

                    pushFollow(FOLLOW_setOperationAttribute_in_setOperation1142);
                    attr2=setOperationAttribute();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			combination.setOperationType(SetOperationType.INTERSECTION);	
                    			configureSelectionSetCombination(combination, (attr1!=null?attr1.selectionId:null), (attr1!=null?attr1.combination:null),
                    											 (attr2!=null?attr2.selectionId:null), (attr2!=null?attr2.combination:null));		
                    		

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:513:4: ^( DIFFERENCE attr1= setOperationAttribute attr2= setOperationAttribute )
                    {
                    match(input,DIFFERENCE,FOLLOW_DIFFERENCE_in_setOperation1155); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_setOperationAttribute_in_setOperation1159);
                    attr1=setOperationAttribute();

                    state._fsp--;

                    pushFollow(FOLLOW_setOperationAttribute_in_setOperation1163);
                    attr2=setOperationAttribute();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			combination.setOperationType(SetOperationType.DIFFERENCE);
                    			configureSelectionSetCombination(combination, (attr1!=null?attr1.selectionId:null), (attr1!=null?attr1.combination:null),
                    											 (attr2!=null?attr2.selectionId:null), (attr2!=null?attr2.combination:null));
                    		

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return combination;
    }
    // $ANTLR end "setOperation"

    public static class setOperationAttribute_return extends TreeRuleReturnScope {
        public String selectionId;
        public SelectionSetCombination combination;
    };

    // $ANTLR start "setOperationAttribute"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:524:1: setOperationAttribute returns [String selectionId, SelectionSetCombination combination] : ( ^( ARGUMENT i= ID ) | ^( ARGUMENT i= QUOTED_STRING ) | setOperation );
    public final CQLTreeWalker.setOperationAttribute_return setOperationAttribute() throws RecognitionException {
        CQLTreeWalker.setOperationAttribute_return retval = new CQLTreeWalker.setOperationAttribute_return();
        retval.start = input.LT(1);

        CommonTree i=null;
        SelectionSetCombination setOperation1 = null;



        		retval.selectionId = null;
        		retval.combination = null;
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:531:2: ( ^( ARGUMENT i= ID ) | ^( ARGUMENT i= QUOTED_STRING ) | setOperation )
            int alt19=3;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==ARGUMENT) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==DOWN) ) {
                    int LA19_3 = input.LA(3);

                    if ( (LA19_3==ID) ) {
                        alt19=1;
                    }
                    else if ( (LA19_3==QUOTED_STRING) ) {
                        alt19=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA19_0>=UNION && LA19_0<=DIFFERENCE)) ) {
                alt19=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:531:4: ^( ARGUMENT i= ID )
                    {
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_setOperationAttribute1200); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_setOperationAttribute1204); 

                    match(input, Token.UP, null); 
                     retval.selectionId = i.getText(); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:534:4: ^( ARGUMENT i= QUOTED_STRING )
                    {
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_setOperationAttribute1217); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_setOperationAttribute1221); 

                    match(input, Token.UP, null); 
                     retval.selectionId = i.getText().substring(1,i.getText().length()-1); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:538:4: setOperation
                    {
                    pushFollow(FOLLOW_setOperation_in_setOperationAttribute1236);
                    setOperation1=setOperation();

                    state._fsp--;

                     retval.combination = setOperation1; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "setOperationAttribute"


    // $ANTLR start "propertySelection"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:546:1: propertySelection : ^( RESULT_PROPERTIES pd= propertyDefinition (rpd= propertyDefinition )* ) ;
    public final void propertySelection() throws RecognitionException {
        CQLTreeWalker.propertyDefinition_return pd = null;

        CQLTreeWalker.propertyDefinition_return rpd = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:547:2: ( ^( RESULT_PROPERTIES pd= propertyDefinition (rpd= propertyDefinition )* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:547:4: ^( RESULT_PROPERTIES pd= propertyDefinition (rpd= propertyDefinition )* )
            {
            match(input,RESULT_PROPERTIES,FOLLOW_RESULT_PROPERTIES_in_propertySelection1258); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_propertyDefinition_in_propertySelection1262);
            pd=propertyDefinition();

            state._fsp--;

             ((resultBlock_scope)resultBlock_stack.peek()).resultBuilder.addProperty((pd!=null?pd.propertyName:null), (pd!=null?pd.propertyLabel:null)); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:548:4: (rpd= propertyDefinition )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ARGUMENT) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:548:5: rpd= propertyDefinition
            	    {
            	    pushFollow(FOLLOW_propertyDefinition_in_propertySelection1275);
            	    rpd=propertyDefinition();

            	    state._fsp--;

            	     ((resultBlock_scope)resultBlock_stack.peek()).resultBuilder.addProperty((rpd!=null?rpd.propertyName:null), (rpd!=null?rpd.propertyLabel:null));

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "propertySelection"

    public static class propertyDefinition_return extends TreeRuleReturnScope {
        public String propertyName;
        public String propertyLabel;
    };

    // $ANTLR start "propertyDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:554:1: propertyDefinition returns [String propertyName, String propertyLabel] : ^( ARGUMENT i= ID (ld= labelDefinition )? ) ;
    public final CQLTreeWalker.propertyDefinition_return propertyDefinition() throws RecognitionException {
        CQLTreeWalker.propertyDefinition_return retval = new CQLTreeWalker.propertyDefinition_return();
        retval.start = input.LT(1);

        CommonTree i=null;
        String ld = null;



        		// The label is optional.  
        		retval.propertyLabel = null; 
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:561:2: ( ^( ARGUMENT i= ID (ld= labelDefinition )? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:561:4: ^( ARGUMENT i= ID (ld= labelDefinition )? )
            {
            match(input,ARGUMENT,FOLLOW_ARGUMENT_in_propertyDefinition1312); 

            match(input, Token.DOWN, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_propertyDefinition1316); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:561:21: (ld= labelDefinition )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==LABEL) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:561:22: ld= labelDefinition
                    {
                    pushFollow(FOLLOW_labelDefinition_in_propertyDefinition1322);
                    ld=labelDefinition();

                    state._fsp--;

                     retval.propertyLabel = ld; 

                    }
                    break;

            }


            match(input, Token.UP, null); 
             
            			retval.propertyName = i.getText();
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "propertyDefinition"


    // $ANTLR start "expression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:575:1: expression[DataType inferredType, Expression recoveryExpression] returns [Expression expResult] : ( ^( OR e1= expression[DataType.BOOLEAN, null] e2= expression[DataType.BOOLEAN, null] ) | ^( AND e1= expression[DataType.BOOLEAN, null] e2= expression[DataType.BOOLEAN, null] ) | ^( NOT e1= expression[DataType.BOOLEAN, null] ) | ^( EQ e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( GE e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( LE e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( GT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( LT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( PLUS e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( DIFF e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( MULT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( DIV e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( MINUS e1= expression[DataType.REAL, null] ) | ^( POWER e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( SQRT e1= expression[DataType.REAL, null] ) | ^( LOG e1= expression[DataType.REAL, null] ) | ^( LN e1= expression[DataType.REAL, null] ) | ^( ABS e1= expression[DataType.REAL, null] ) | ^( AVG ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( MEDIAN ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( MIN ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( MAX ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( STDDEV ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( VAR ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( SUM ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( COUNT ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( CONTAINS e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( MATCHES e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( CONCAT e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( STARTSWITH e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( '[' e= expression[DataType.ARRAY, null] i= INTEGER ) | ^( LENGTH e= expression[DataType.ARRAY, null] ) | ^( RANGE e= expression[DataType.REAL, null] ( ( '[' ) | ( '(' ) ) ( ( ']' ) | ( ')' ) ) startExpression= expression[DataType.REAL, null] endExpression= expression[DataType.REAL, null] ) | ^( ARRAY (e= expression[DataType.STRING, null] )* ) | id= ID | d= DATE | i= INTEGER | r= REAL | qs= QUOTED_STRING | t= TRUE | f= FALSE | n= NULL | ^( RECOVERY er= expression[DataType.STRING, null] e= expression[DataType.BOOLEAN, $er.expResult] ) );
    public final Expression expression(DataType inferredType, Expression recoveryExpression) throws RecognitionException {
        Expression expResult = null;

        CommonTree i=null;
        CommonTree id=null;
        CommonTree d=null;
        CommonTree r=null;
        CommonTree qs=null;
        CommonTree t=null;
        CommonTree f=null;
        CommonTree n=null;
        Expression e1 = null;

        Expression e2 = null;

        String ol = null;

        Expression e = null;

        Expression startExpression = null;

        Expression endExpression = null;

        Expression er = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:579:2: ( ^( OR e1= expression[DataType.BOOLEAN, null] e2= expression[DataType.BOOLEAN, null] ) | ^( AND e1= expression[DataType.BOOLEAN, null] e2= expression[DataType.BOOLEAN, null] ) | ^( NOT e1= expression[DataType.BOOLEAN, null] ) | ^( EQ e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( GE e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( LE e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( GT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( LT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( PLUS e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( DIFF e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( MULT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( DIV e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( MINUS e1= expression[DataType.REAL, null] ) | ^( POWER e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] ) | ^( SQRT e1= expression[DataType.REAL, null] ) | ^( LOG e1= expression[DataType.REAL, null] ) | ^( LN e1= expression[DataType.REAL, null] ) | ^( ABS e1= expression[DataType.REAL, null] ) | ^( AVG ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( MEDIAN ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( MIN ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( MAX ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( STDDEV ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( VAR ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( SUM ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( COUNT ^( ARGUMENT i= ID ) ol= optionalLabelName ) | ^( CONTAINS e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( MATCHES e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( CONCAT e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( STARTSWITH e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] ) | ^( '[' e= expression[DataType.ARRAY, null] i= INTEGER ) | ^( LENGTH e= expression[DataType.ARRAY, null] ) | ^( RANGE e= expression[DataType.REAL, null] ( ( '[' ) | ( '(' ) ) ( ( ']' ) | ( ')' ) ) startExpression= expression[DataType.REAL, null] endExpression= expression[DataType.REAL, null] ) | ^( ARRAY (e= expression[DataType.STRING, null] )* ) | id= ID | d= DATE | i= INTEGER | r= REAL | qs= QUOTED_STRING | t= TRUE | f= FALSE | n= NULL | ^( RECOVERY er= expression[DataType.STRING, null] e= expression[DataType.BOOLEAN, $er.expResult] ) )
            int alt25=43;
            switch ( input.LA(1) ) {
            case OR:
                {
                alt25=1;
                }
                break;
            case AND:
                {
                alt25=2;
                }
                break;
            case NOT:
                {
                alt25=3;
                }
                break;
            case EQ:
                {
                alt25=4;
                }
                break;
            case GE:
                {
                alt25=5;
                }
                break;
            case LE:
                {
                alt25=6;
                }
                break;
            case GT:
                {
                alt25=7;
                }
                break;
            case LT:
                {
                alt25=8;
                }
                break;
            case PLUS:
                {
                alt25=9;
                }
                break;
            case DIFF:
                {
                alt25=10;
                }
                break;
            case MULT:
                {
                alt25=11;
                }
                break;
            case DIV:
                {
                alt25=12;
                }
                break;
            case MINUS:
                {
                alt25=13;
                }
                break;
            case POWER:
                {
                alt25=14;
                }
                break;
            case SQRT:
                {
                alt25=15;
                }
                break;
            case LOG:
                {
                alt25=16;
                }
                break;
            case LN:
                {
                alt25=17;
                }
                break;
            case ABS:
                {
                alt25=18;
                }
                break;
            case AVG:
                {
                alt25=19;
                }
                break;
            case MEDIAN:
                {
                alt25=20;
                }
                break;
            case MIN:
                {
                alt25=21;
                }
                break;
            case MAX:
                {
                alt25=22;
                }
                break;
            case STDDEV:
                {
                alt25=23;
                }
                break;
            case VAR:
                {
                alt25=24;
                }
                break;
            case SUM:
                {
                alt25=25;
                }
                break;
            case COUNT:
                {
                alt25=26;
                }
                break;
            case CONTAINS:
                {
                alt25=27;
                }
                break;
            case MATCHES:
                {
                alt25=28;
                }
                break;
            case CONCAT:
                {
                alt25=29;
                }
                break;
            case STARTSWITH:
                {
                alt25=30;
                }
                break;
            case 116:
                {
                alt25=31;
                }
                break;
            case LENGTH:
                {
                alt25=32;
                }
                break;
            case RANGE:
                {
                alt25=33;
                }
                break;
            case ARRAY:
                {
                alt25=34;
                }
                break;
            case ID:
                {
                alt25=35;
                }
                break;
            case DATE:
                {
                alt25=36;
                }
                break;
            case INTEGER:
                {
                alt25=37;
                }
                break;
            case REAL:
                {
                alt25=38;
                }
                break;
            case QUOTED_STRING:
                {
                alt25=39;
                }
                break;
            case TRUE:
                {
                alt25=40;
                }
                break;
            case FALSE:
                {
                alt25=41;
                }
                break;
            case NULL:
                {
                alt25=42;
                }
                break;
            case RECOVERY:
                {
                alt25=43;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:579:4: ^( OR e1= expression[DataType.BOOLEAN, null] e2= expression[DataType.BOOLEAN, null] )
                    {
                    match(input,OR,FOLLOW_OR_in_expression1369); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1373);
                    e1=expression(DataType.BOOLEAN, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1378);
                    e2=expression(DataType.BOOLEAN, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new OrExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:582:4: ^( AND e1= expression[DataType.BOOLEAN, null] e2= expression[DataType.BOOLEAN, null] )
                    {
                    match(input,AND,FOLLOW_AND_in_expression1394); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1398);
                    e1=expression(DataType.BOOLEAN, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1403);
                    e2=expression(DataType.BOOLEAN, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new AndExpression(e1, e2, recoveryExpression);

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:585:4: ^( NOT e1= expression[DataType.BOOLEAN, null] )
                    {
                    match(input,NOT,FOLLOW_NOT_in_expression1420); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1424);
                    e1=expression(DataType.BOOLEAN, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new NotExpression(e1, recoveryExpression); 

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:590:4: ^( EQ e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] )
                    {
                    match(input,EQ,FOLLOW_EQ_in_expression1446); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1450);
                    e1=expression(DataType.STRING, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1455);
                    e2=expression(DataType.STRING, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new EqualThanExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:593:5: ^( GE e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,GE,FOLLOW_GE_in_expression1471); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1475);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1480);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new GreaterOrEqualThanExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:596:5: ^( LE e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,LE,FOLLOW_LE_in_expression1496); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1500);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1505);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new LessOrEqualThanExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:599:5: ^( GT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1521); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1525);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1530);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new GreaterThanExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 8 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:602:5: ^( LT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1546); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1550);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1555);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new LessThanExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 9 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:607:4: ^( PLUS e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expression1576); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1580);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1585);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new AdditionExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 10 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:610:4: ^( DIFF e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,DIFF,FOLLOW_DIFF_in_expression1600); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1604);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1609);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new DifferenceExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 11 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:613:4: ^( MULT e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,MULT,FOLLOW_MULT_in_expression1624); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1628);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1633);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new MultiplicationExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 12 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:616:5: ^( DIV e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,DIV,FOLLOW_DIV_in_expression1649); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1653);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1658);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new DivisionExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 13 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:619:5: ^( MINUS e1= expression[DataType.REAL, null] )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expression1674); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1678);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new MinusExpression(e1, recoveryExpression); 

                    }
                    break;
                case 14 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:624:4: ^( POWER e1= expression[DataType.REAL, null] e2= expression[DataType.REAL, null] )
                    {
                    match(input,POWER,FOLLOW_POWER_in_expression1704); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1708);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1713);
                    e2=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new PowerExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 15 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:627:4: ^( SQRT e1= expression[DataType.REAL, null] )
                    {
                    match(input,SQRT,FOLLOW_SQRT_in_expression1728); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1732);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new SquareRootExpression(e1, recoveryExpression); 

                    }
                    break;
                case 16 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:630:4: ^( LOG e1= expression[DataType.REAL, null] )
                    {
                    match(input,LOG,FOLLOW_LOG_in_expression1747); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1751);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new LogarithmExpression(e1, recoveryExpression); 

                    }
                    break;
                case 17 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:633:4: ^( LN e1= expression[DataType.REAL, null] )
                    {
                    match(input,LN,FOLLOW_LN_in_expression1767); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1771);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new NeperianLogarithmExpression(e1, recoveryExpression); 

                    }
                    break;
                case 18 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:636:4: ^( ABS e1= expression[DataType.REAL, null] )
                    {
                    match(input,ABS,FOLLOW_ABS_in_expression1786); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1790);
                    e1=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new AbsoluteValueExpression(e1, recoveryExpression); 

                    }
                    break;
                case 19 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:645:4: ^( AVG ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,AVG,FOLLOW_AVG_in_expression1822); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression1825); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1829); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression1834);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(AVG, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression);
                    		

                    }
                    break;
                case 20 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:653:4: ^( MEDIAN ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,MEDIAN,FOLLOW_MEDIAN_in_expression1848); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression1851); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1855); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression1860);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(MEDIAN, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression);
                    		

                    }
                    break;
                case 21 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:661:4: ^( MIN ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,MIN,FOLLOW_MIN_in_expression1875); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression1878); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1882); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression1887);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(MIN, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression);		 
                    		

                    }
                    break;
                case 22 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:669:4: ^( MAX ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,MAX,FOLLOW_MAX_in_expression1902); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression1905); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1909); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression1914);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(MAX, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression);
                    		

                    }
                    break;
                case 23 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:677:4: ^( STDDEV ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,STDDEV,FOLLOW_STDDEV_in_expression1930); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression1933); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1937); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression1942);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(STDDEV, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression);		 
                    		

                    }
                    break;
                case 24 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:685:4: ^( VAR ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,VAR,FOLLOW_VAR_in_expression1957); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression1960); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1964); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression1969);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(VAR, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression); 
                    		

                    }
                    break;
                case 25 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:693:4: ^( SUM ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,SUM,FOLLOW_SUM_in_expression1985); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression1988); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1992); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression1997);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(SUM, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression);	 
                    		

                    }
                    break;
                case 26 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:701:4: ^( COUNT ^( ARGUMENT i= ID ) ol= optionalLabelName )
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_expression2012); 

                    match(input, Token.DOWN, null); 
                    match(input,ARGUMENT,FOLLOW_ARGUMENT_in_expression2015); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2019); 

                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_optionalLabelName_in_expression2024);
                    ol=optionalLabelName();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     
                    			String referencedSelection =  (ol != null ? ol : ((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.getSourceSelectionLabel());
                    			((selectionBlock_scope)selectionBlock_stack.peek()).queryBuilder.addLabelOfSelectionReferencedByStats(referencedSelection);
                    			expResult = buildStatisticalFunctionExpression(COUNT, i.getText(), referencedSelection, ((cqlScript_scope)cqlScript_stack.peek()).selectionMap, 
                    															((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(i.getText()),recoveryExpression);	 
                    		

                    }
                    break;
                case 27 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:711:4: ^( CONTAINS e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] )
                    {
                    match(input,CONTAINS,FOLLOW_CONTAINS_in_expression2045); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2049);
                    e1=expression(DataType.STRING, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2054);
                    e2=expression(DataType.STRING, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new ContainsExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 28 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:714:4: ^( MATCHES e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] )
                    {
                    match(input,MATCHES,FOLLOW_MATCHES_in_expression2070); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2074);
                    e1=expression(DataType.STRING, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2079);
                    e2=expression(DataType.STRING, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new MatchesStringExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 29 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:717:4: ^( CONCAT e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] )
                    {
                    match(input,CONCAT,FOLLOW_CONCAT_in_expression2095); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2099);
                    e1=expression(DataType.STRING, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2104);
                    e2=expression(DataType.STRING, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new ConcatenationExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 30 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:720:4: ^( STARTSWITH e1= expression[DataType.STRING, null] e2= expression[DataType.STRING, null] )
                    {
                    match(input,STARTSWITH,FOLLOW_STARTSWITH_in_expression2121); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2125);
                    e1=expression(DataType.STRING, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2130);
                    e2=expression(DataType.STRING, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new StartsWithExpression(e1, e2, recoveryExpression); 

                    }
                    break;
                case 31 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:724:4: ^( '[' e= expression[DataType.ARRAY, null] i= INTEGER )
                    {
                    match(input,116,FOLLOW_116_in_expression2148); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2152);
                    e=expression(DataType.ARRAY, null);

                    state._fsp--;

                    i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression2157); 

                    match(input, Token.UP, null); 
                     expResult = new ArrayAccessExpression(e, Integer.parseInt(i.getText()), recoveryExpression); 

                    }
                    break;
                case 32 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:728:4: ^( LENGTH e= expression[DataType.ARRAY, null] )
                    {
                    match(input,LENGTH,FOLLOW_LENGTH_in_expression2183); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2187);
                    e=expression(DataType.ARRAY, null);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = new ArrayLengthExpression(e, recoveryExpression); 

                    }
                    break;
                case 33 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:733:4: ^( RANGE e= expression[DataType.REAL, null] ( ( '[' ) | ( '(' ) ) ( ( ']' ) | ( ')' ) ) startExpression= expression[DataType.REAL, null] endExpression= expression[DataType.REAL, null] )
                    {
                    match(input,RANGE,FOLLOW_RANGE_in_expression2213); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2217);
                    e=expression(DataType.REAL, null);

                    state._fsp--;

                     boolean closedStart = true; boolean closedEnd = true; 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:735:4: ( ( '[' ) | ( '(' ) )
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==116) ) {
                        alt22=1;
                    }
                    else if ( (LA22_0==113) ) {
                        alt22=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 0, input);

                        throw nvae;
                    }
                    switch (alt22) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:735:5: ( '[' )
                            {
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:735:5: ( '[' )
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:735:6: '['
                            {
                            match(input,116,FOLLOW_116_in_expression2232); 

                            }


                            }
                            break;
                        case 2 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:735:13: ( '(' )
                            {
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:735:13: ( '(' )
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:735:14: '('
                            {
                            match(input,113,FOLLOW_113_in_expression2238); 
                             closedStart = false; 

                            }


                            }
                            break;

                    }

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:736:4: ( ( ']' ) | ( ')' ) )
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==117) ) {
                        alt23=1;
                    }
                    else if ( (LA23_0==115) ) {
                        alt23=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 0, input);

                        throw nvae;
                    }
                    switch (alt23) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:736:5: ( ']' )
                            {
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:736:5: ( ']' )
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:736:6: ']'
                            {
                            match(input,117,FOLLOW_117_in_expression2251); 

                            }


                            }
                            break;
                        case 2 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:736:13: ( ')' )
                            {
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:736:13: ( ')' )
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:736:14: ')'
                            {
                            match(input,115,FOLLOW_115_in_expression2257); 
                             closedEnd = false; 

                            }


                            }
                            break;

                    }

                    pushFollow(FOLLOW_expression_in_expression2269);
                    startExpression=expression(DataType.REAL, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2274);
                    endExpression=expression(DataType.REAL, null);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			expResult = new RangeExpression(e, startExpression, endExpression, 
                    											 closedStart, closedEnd, recoveryExpression);	  	 		 	
                    		

                    }
                    break;
                case 34 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:745:4: ^( ARRAY (e= expression[DataType.STRING, null] )* )
                    {
                    match(input,ARRAY,FOLLOW_ARRAY_in_expression2307); 

                     List<Expression> arrayExpressions = new ArrayList<Expression>(); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:747:4: (e= expression[DataType.STRING, null] )*
                        loop24:
                        do {
                            int alt24=2;
                            int LA24_0 = input.LA(1);

                            if ( ((LA24_0>=MINUS && LA24_0<=ARRAY)||LA24_0==ID||LA24_0==INTEGER||LA24_0==QUOTED_STRING||(LA24_0>=AVG && LA24_0<=COUNT)||(LA24_0>=OR && LA24_0<=RECOVERY)||LA24_0==DATE||LA24_0==116) ) {
                                alt24=1;
                            }


                            switch (alt24) {
                        	case 1 :
                        	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:747:5: e= expression[DataType.STRING, null]
                        	    {
                        	    pushFollow(FOLLOW_expression_in_expression2322);
                        	    e=expression(DataType.STRING, null);

                        	    state._fsp--;

                        	     arrayExpressions.add(e); 

                        	    }
                        	    break;

                        	default :
                        	    break loop24;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                     
                    			try
                    			{
                    				List<DataValue> arrayValues = new ArrayList<DataValue>();
                    				for(Expression currentExpression : arrayExpressions)
                    					arrayValues.add(currentExpression.calculateExpressionValue(null));
                    					
                    				expResult = new LiteralExpression(new ArrayValue(arrayValues),null);
                    			}
                    			catch(ExpressionEvaluationException evalException)
                    			{
                    				// TODO Deal with the error.
                    				evalException.printStackTrace();  
                    			}						
                    		

                    }
                    break;
                case 35 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:766:4: id= ID
                    {
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2368); 
                    	
                    			DataType inferredDataType = inferredType; 
                    			Expression variableExpression = ((selectionBlock_scope)selectionBlock_stack.peek()).variableMap.get(id.getText());			
                    			
                    			// Check if we are dealing with a local variable or with an attribute reference.
                    			if(variableExpression != null)
                    			{
                    				expResult = variableExpression;
                    			}
                    			else
                    				expResult = new ReferenceExpression(id.getText(), inferredDataType,  recoveryExpression); 
                    		

                    }
                    break;
                case 36 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:780:4: d= DATE
                    {
                    d=(CommonTree)match(input,DATE,FOLLOW_DATE_in_expression2382); 
                     
                    			String stringDate = d.getText();
                    			int year = Integer.parseInt(stringDate.substring(1,5));
                    			int month = Integer.parseInt(stringDate.substring(6,8));
                    			int day = Integer.parseInt(stringDate.substring(9,11));
                    			expResult = new LiteralExpression(new DateValue(year,month,day),null); 
                    		

                    }
                    break;
                case 37 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:789:4: i= INTEGER
                    {
                    i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression2396); 
                     expResult = new LiteralExpression(new IntegerValue(Integer.parseInt(i.getText())),null); 

                    }
                    break;
                case 38 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:792:5: r= REAL
                    {
                    r=(CommonTree)match(input,REAL,FOLLOW_REAL_in_expression2410); 
                     expResult = new LiteralExpression(new RealValue(Float.parseFloat(r.getText())),null); 

                    }
                    break;
                case 39 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:795:5: qs= QUOTED_STRING
                    {
                    qs=(CommonTree)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_expression2426); 
                     expResult = new LiteralExpression(new StringValue(qs.getText().substring(1,qs.getText().length()-1)),null); 

                    }
                    break;
                case 40 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:798:4: t= TRUE
                    {
                    t=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_expression2442); 
                     expResult = new LiteralExpression(new BooleanValue(true),null);

                    }
                    break;
                case 41 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:801:4: f= FALSE
                    {
                    f=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_expression2456); 
                     expResult = new LiteralExpression(new BooleanValue(false),null);

                    }
                    break;
                case 42 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:804:4: n= NULL
                    {
                    n=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2470); 
                     expResult = new LiteralExpression(new NullValue(),null); 

                    }
                    break;
                case 43 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQLTreeWalker.g:807:4: ^( RECOVERY er= expression[DataType.STRING, null] e= expression[DataType.BOOLEAN, $er.expResult] )
                    {
                    match(input,RECOVERY,FOLLOW_RECOVERY_in_expression2483); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2487);
                    er=expression(DataType.STRING, null);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2492);
                    e=expression(DataType.BOOLEAN, er);

                    state._fsp--;


                    match(input, Token.UP, null); 
                     expResult = e; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return expResult;
    }
    // $ANTLR end "expression"

    // Delegated rules


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\13\uffff";
    static final String DFA16_eofS =
        "\13\uffff";
    static final String DFA16_minS =
        "\1\21\2\2\2\uffff\2\27\4\uffff";
    static final String DFA16_maxS =
        "\1\24\2\2\2\uffff\2\44\4\uffff";
    static final String DFA16_acceptS =
        "\3\uffff\1\5\1\6\2\uffff\1\1\1\2\1\3\1\4";
    static final String DFA16_specialS =
        "\13\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\1\1\3\1\4\1\2",
            "\1\5",
            "\1\6",
            "",
            "",
            "\1\7\14\uffff\1\10",
            "\1\11\14\uffff\1\12",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "387:1: resultExpression : ( ^( REGULAR_SELECTION_RESULT i= ID ) | ^( REGULAR_SELECTION_RESULT qs= QUOTED_STRING ) | ^( DISTINCT_SELECTION_RESULT i= ID ) | ^( DISTINCT_SELECTION_RESULT qs= QUOTED_STRING ) | ^( COMBINED_SELECTION_RESULT sf= setOperation ) | ^( STATS_SELECTION_RESULT resultStatisticalFunction ) );";
        }
    }
 

    public static final BitSet FOLLOW_CQL_SCRIPT_in_cqlScript98 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selectionBlock_in_cqlScript100 = new BitSet(new long[]{0x0000002000200008L});
    public static final BitSet FOLLOW_resultBlock_in_cqlScript103 = new BitSet(new long[]{0x0000002000000008L});
    public static final BitSet FOLLOW_SELECT_in_selectionBlock153 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DATA_SOURCE_in_selectionBlock156 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_selectionBlock160 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_labelDefinition_in_selectionBlock165 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_selectionBlockDefinition_in_selectionBlock181 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_selectionBlockOptions_in_selectionBlock185 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SELECTION_DEFINITION_in_selectionBlockDefinition203 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_variablesBlock_in_selectionBlockDefinition206 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_CONDITION_in_selectionBlockDefinition209 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_selectionBlockDefinition213 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VARIABLES_in_variablesBlock236 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_variableDefinition_in_variablesBlock238 = new BitSet(new long[]{0x0000100000000008L});
    public static final BitSet FOLLOW_VAR_in_variableDefinition260 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_variableDefinition264 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_variableDefinition268 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SELECTION_OPTIONS_in_selectionBlockOptions293 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selectionBlockOptionsDefinition_in_selectionBlockOptions295 = new BitSet(new long[]{0x0000000044000008L});
    public static final BitSet FOLLOW_sortOptions_in_selectionBlockOptionsDefinition310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_limitOptions_in_selectionBlockOptionsDefinition316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SORT_in_sortOptions332 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_sortOptions336 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_DESC_in_sortOptions340 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SORT_in_sortOptions354 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_sortOptions358 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LIMIT_in_limitOptions377 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_limitDefinition_in_limitOptions379 = new BitSet(new long[]{0x0000000380000008L});
    public static final BitSet FOLLOW_TOP_in_limitDefinition404 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_limitDefinition408 = new BitSet(new long[]{0x0000000400000008L});
    public static final BitSet FOLLOW_INTEGER_in_limitDefinition413 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOTTOM_in_limitDefinition430 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_limitDefinition434 = new BitSet(new long[]{0x0000000400000008L});
    public static final BitSet FOLLOW_INTEGER_in_limitDefinition439 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RANDOM_in_limitDefinition456 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_limitDefinition460 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_INTEGER_in_limitDefinition464 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RANDOM_in_limitDefinition480 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_limitDefinition484 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_labelName_in_labelDefinition514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LABEL_in_labelName541 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_labelName545 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LABEL_in_labelName560 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_labelName564 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_labelName_in_optionalLabelName587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RESULT_in_resultBlock630 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_resultDefinition_in_resultBlock632 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RESULT_DEFINITION_in_resultDefinition669 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_resultExpression_in_resultDefinition671 = new BitSet(new long[]{0x0000000000010408L});
    public static final BitSet FOLLOW_propertySelection_in_resultDefinition673 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_labelDefinition_in_resultDefinition679 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REGULAR_SELECTION_RESULT_in_resultExpression710 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultExpression714 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REGULAR_SELECTION_RESULT_in_resultExpression728 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_resultExpression732 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISTINCT_SELECTION_RESULT_in_resultExpression749 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultExpression753 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISTINCT_SELECTION_RESULT_in_resultExpression774 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_resultExpression778 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_COMBINED_SELECTION_RESULT_in_resultExpression794 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_setOperation_in_resultExpression798 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STATS_SELECTION_RESULT_in_resultExpression812 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_resultStatisticalFunction_in_resultExpression814 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AVG_in_resultStatisticalFunction851 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction854 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction858 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction862 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction866 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEDIAN_in_resultStatisticalFunction881 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction884 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction888 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction892 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction896 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MIN_in_resultStatisticalFunction911 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction914 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction918 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction922 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction926 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAX_in_resultStatisticalFunction941 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction944 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction948 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction952 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction956 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STDDEV_in_resultStatisticalFunction972 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction975 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction979 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction983 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction987 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VAR_in_resultStatisticalFunction1002 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction1005 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1009 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction1013 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1017 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUM_in_resultStatisticalFunction1033 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction1036 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1040 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction1044 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1048 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_COUNT_in_resultStatisticalFunction1063 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_resultStatisticalFunction1066 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1070 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_in_resultStatisticalFunction1074 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1078 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNION_in_setOperation1112 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperation1116 = new BitSet(new long[]{0x0003800000000800L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperation1120 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INTERSECTION_in_setOperation1134 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperation1138 = new BitSet(new long[]{0x0003800000000800L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperation1142 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIFFERENCE_in_setOperation1155 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperation1159 = new BitSet(new long[]{0x0003800000000800L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperation1163 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGUMENT_in_setOperationAttribute1200 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_setOperationAttribute1204 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGUMENT_in_setOperationAttribute1217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_setOperationAttribute1221 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_setOperation_in_setOperationAttribute1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RESULT_PROPERTIES_in_propertySelection1258 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_propertyDefinition_in_propertySelection1262 = new BitSet(new long[]{0x0000000000000808L});
    public static final BitSet FOLLOW_propertyDefinition_in_propertySelection1275 = new BitSet(new long[]{0x0000000000000808L});
    public static final BitSet FOLLOW_ARGUMENT_in_propertyDefinition1312 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_propertyDefinition1316 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_labelDefinition_in_propertyDefinition1322 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_expression1369 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1373 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1378 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_expression1394 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1398 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1403 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_expression1420 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1424 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_expression1446 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1450 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1455 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GE_in_expression1471 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1475 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1480 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LE_in_expression1496 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1500 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1505 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1521 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1525 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1530 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1546 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1550 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1555 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expression1576 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1580 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1585 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIFF_in_expression1600 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1604 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1609 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MULT_in_expression1624 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1628 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1633 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIV_in_expression1649 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1653 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1658 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expression1674 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1678 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_POWER_in_expression1704 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1708 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression1713 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SQRT_in_expression1728 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1732 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOG_in_expression1747 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1751 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LN_in_expression1767 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1771 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ABS_in_expression1786 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1790 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AVG_in_expression1822 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression1825 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1829 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression1834 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEDIAN_in_expression1848 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression1851 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1855 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression1860 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MIN_in_expression1875 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression1878 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1882 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression1887 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAX_in_expression1902 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression1905 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1909 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression1914 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STDDEV_in_expression1930 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression1933 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1937 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression1942 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VAR_in_expression1957 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression1960 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1964 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression1969 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUM_in_expression1985 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression1988 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1992 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression1997 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_COUNT_in_expression2012 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARGUMENT_in_expression2015 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression2019 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_optionalLabelName_in_expression2024 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONTAINS_in_expression2045 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2049 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression2054 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MATCHES_in_expression2070 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2074 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression2079 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONCAT_in_expression2095 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2099 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression2104 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STARTSWITH_in_expression2121 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2125 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression2130 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_116_in_expression2148 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2152 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_INTEGER_in_expression2157 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LENGTH_in_expression2183 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2187 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RANGE_in_expression2213 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2217 = new BitSet(new long[]{0x0000000000000000L,0x0012000000000000L});
    public static final BitSet FOLLOW_116_in_expression2232 = new BitSet(new long[]{0x0000000000000000L,0x0028000000000000L});
    public static final BitSet FOLLOW_113_in_expression2238 = new BitSet(new long[]{0x0000000000000000L,0x0028000000000000L});
    public static final BitSet FOLLOW_117_in_expression2251 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_115_in_expression2257 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression2269 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression2274 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARRAY_in_expression2307 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2322 = new BitSet(new long[]{0xFFFC7F9400806008L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_ID_in_expression2368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATE_in_expression2382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_expression2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_expression2410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_expression2426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_expression2442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_expression2456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_expression2470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECOVERY_in_expression2483 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2487 = new BitSet(new long[]{0xFFFC7F9400806000L,0x0010008000003FFFL});
    public static final BitSet FOLLOW_expression_in_expression2492 = new BitSet(new long[]{0x0000000000000008L});

}