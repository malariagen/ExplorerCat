// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g 2011-10-20 16:23:31

	package net.explorercat.cql.parser;
	
	import java.util.Stack;	 


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

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
public class CQLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CQL_SCRIPT", "DATA_SOURCE", "SELECTION_DEFINITION", "VARIABLES", "CONDITION", "SELECTION_OPTIONS", "LABEL", "ARGUMENT", "PROPERTY", "MINUS", "ARRAY", "RESULT_DEFINITION", "RESULT_PROPERTIES", "REGULAR_SELECTION_RESULT", "COMBINED_SELECTION_RESULT", "STATS_SELECTION_RESULT", "DISTINCT_SELECTION_RESULT", "SELECT", "FROM", "ID", "VARS", "WHERE", "SORT", "BY", "DESCENDING", "DESC", "LIMIT", "TOP", "BOTTOM", "RANDOM", "INTEGER", "AS", "QUOTED_STRING", "RESULT", "DISTINCT", "AVG", "MEDIAN", "MIN", "MAX", "STDDEV", "VAR", "SUM", "COUNT", "UNION", "INTERSECTION", "DIFFERENCE", "OR", "AND", "EQ", "GE", "LE", "GT", "LT", "NOT", "PLUS", "DIFF", "MULT", "DIV", "POWER", "SQRT", "LOG", "LN", "ABS", "CONTAINS", "MATCHES", "STARTSWITH", "CONCAT", "LENGTH", "RANGE", "REAL", "TRUE", "FALSE", "NULL", "RECOVERY", "S", "E", "L", "C", "T", "V", "A", "R", "W", "H", "F", "O", "M", "N", "I", "G", "D", "X", "U", "B", "Y", "P", "ASSIGN", "Q", "NUMBER", "DATE", "EXPONENT", "LINE_COMMENT", "BLANK", "J", "K", "Z", "';'", "'{'", "'}'", "':='", "'('", "','", "')'", "'['", "']'", "'.'"
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
    public static final int T__119=119;
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


        public CQLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CQLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CQLParser.tokenNames; }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g"; }


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


    public static class cqlScript_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cqlScript"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:164:1: cqlScript : (s+= selectionBlock )* (r+= resultBlock )* -> ^( CQL_SCRIPT ( $s)* ( $r)* ) ;
    public final CQLParser.cqlScript_return cqlScript() throws RecognitionException {
        CQLParser.cqlScript_return retval = new CQLParser.cqlScript_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        List list_s=null;
        List list_r=null;
        RuleReturnScope s = null;
        RuleReturnScope r = null;
        RewriteRuleSubtreeStream stream_selectionBlock=new RewriteRuleSubtreeStream(adaptor,"rule selectionBlock");
        RewriteRuleSubtreeStream stream_resultBlock=new RewriteRuleSubtreeStream(adaptor,"rule resultBlock");

        		// Initialises the stack of error messages.
        		errorMessageStack = new Stack<String>();
        		errorMessages = new ArrayList<String>();	
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:172:2: ( (s+= selectionBlock )* (r+= resultBlock )* -> ^( CQL_SCRIPT ( $s)* ( $r)* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:172:4: (s+= selectionBlock )* (r+= resultBlock )*
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:172:5: (s+= selectionBlock )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==SELECT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:172:5: s+= selectionBlock
            	    {
            	    pushFollow(FOLLOW_selectionBlock_in_cqlScript227);
            	    s=selectionBlock();

            	    state._fsp--;

            	    stream_selectionBlock.add(s.getTree());
            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTree());


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:172:24: (r+= resultBlock )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RESULT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:172:24: r+= resultBlock
            	    {
            	    pushFollow(FOLLOW_resultBlock_in_cqlScript232);
            	    r=resultBlock();

            	    state._fsp--;

            	    stream_resultBlock.add(r.getTree());
            	    if (list_r==null) list_r=new ArrayList();
            	    list_r.add(r.getTree());


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);



            // AST REWRITE
            // elements: s, r
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: s, r
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"token s",list_s);
            RewriteRuleSubtreeStream stream_r=new RewriteRuleSubtreeStream(adaptor,"token r",list_r);
            root_0 = (Object)adaptor.nil();
            // 173:3: -> ^( CQL_SCRIPT ( $s)* ( $r)* )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:173:6: ^( CQL_SCRIPT ( $s)* ( $r)* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CQL_SCRIPT, "CQL_SCRIPT"), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:173:19: ( $s)*
                while ( stream_s.hasNext() ) {
                    adaptor.addChild(root_1, stream_s.nextTree());

                }
                stream_s.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:173:23: ( $r)*
                while ( stream_r.hasNext() ) {
                    adaptor.addChild(root_1, stream_r.nextTree());

                }
                stream_r.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "cqlScript"

    public static class selectionBlock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selectionBlock"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:188:1: selectionBlock : SELECT FROM ID l= labelDefinition sd= selectionBlockDefinition so= selectionBlockOptions ';' -> ^( SELECT ^( DATA_SOURCE ID ) $l $sd $so) ;
    public final CQLParser.selectionBlock_return selectionBlock() throws RecognitionException {
        CQLParser.selectionBlock_return retval = new CQLParser.selectionBlock_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SELECT1=null;
        Token FROM2=null;
        Token ID3=null;
        Token char_literal4=null;
        CQLParser.labelDefinition_return l = null;

        CQLParser.selectionBlockDefinition_return sd = null;

        CQLParser.selectionBlockOptions_return so = null;


        Object SELECT1_tree=null;
        Object FROM2_tree=null;
        Object ID3_tree=null;
        Object char_literal4_tree=null;
        RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
        RewriteRuleTokenStream stream_FROM=new RewriteRuleTokenStream(adaptor,"token FROM");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_SELECT=new RewriteRuleTokenStream(adaptor,"token SELECT");
        RewriteRuleSubtreeStream stream_selectionBlockDefinition=new RewriteRuleSubtreeStream(adaptor,"rule selectionBlockDefinition");
        RewriteRuleSubtreeStream stream_selectionBlockOptions=new RewriteRuleSubtreeStream(adaptor,"rule selectionBlockOptions");
        RewriteRuleSubtreeStream stream_labelDefinition=new RewriteRuleSubtreeStream(adaptor,"rule labelDefinition");
         errorMessageStack.push("SELECT block"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:192:2: ( SELECT FROM ID l= labelDefinition sd= selectionBlockDefinition so= selectionBlockOptions ';' -> ^( SELECT ^( DATA_SOURCE ID ) $l $sd $so) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:192:4: SELECT FROM ID l= labelDefinition sd= selectionBlockDefinition so= selectionBlockOptions ';'
            {
            SELECT1=(Token)match(input,SELECT,FOLLOW_SELECT_in_selectionBlock293);  
            stream_SELECT.add(SELECT1);

            FROM2=(Token)match(input,FROM,FOLLOW_FROM_in_selectionBlock295);  
            stream_FROM.add(FROM2);

            ID3=(Token)match(input,ID,FOLLOW_ID_in_selectionBlock297);  
            stream_ID.add(ID3);

            pushFollow(FOLLOW_labelDefinition_in_selectionBlock301);
            l=labelDefinition();

            state._fsp--;

            stream_labelDefinition.add(l.getTree());
            pushFollow(FOLLOW_selectionBlockDefinition_in_selectionBlock305);
            sd=selectionBlockDefinition();

            state._fsp--;

            stream_selectionBlockDefinition.add(sd.getTree());
            pushFollow(FOLLOW_selectionBlockOptions_in_selectionBlock309);
            so=selectionBlockOptions();

            state._fsp--;

            stream_selectionBlockOptions.add(so.getTree());
            char_literal4=(Token)match(input,110,FOLLOW_110_in_selectionBlock311);  
            stream_110.add(char_literal4);



            // AST REWRITE
            // elements: l, ID, so, sd, SELECT
            // token labels: 
            // rule labels: retval, so, sd, l
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_so=new RewriteRuleSubtreeStream(adaptor,"rule so",so!=null?so.tree:null);
            RewriteRuleSubtreeStream stream_sd=new RewriteRuleSubtreeStream(adaptor,"rule sd",sd!=null?sd.tree:null);
            RewriteRuleSubtreeStream stream_l=new RewriteRuleSubtreeStream(adaptor,"rule l",l!=null?l.tree:null);

            root_0 = (Object)adaptor.nil();
            // 193:3: -> ^( SELECT ^( DATA_SOURCE ID ) $l $sd $so)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:193:6: ^( SELECT ^( DATA_SOURCE ID ) $l $sd $so)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_SELECT.nextNode(), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:193:15: ^( DATA_SOURCE ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(DATA_SOURCE, "DATA_SOURCE"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_l.nextTree());
                adaptor.addChild(root_1, stream_sd.nextTree());
                adaptor.addChild(root_1, stream_so.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "selectionBlock"

    public static class selectionBlockDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selectionBlockDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:196:1: selectionBlockDefinition : ( VARS '{' vd+= variableDefinition (vd+= variableDefinition )* '}' )? WHERE c= condition -> ^( SELECTION_DEFINITION ( ^( VARIABLES ( $vd)+ ) )? ^( CONDITION $c) ) ;
    public final CQLParser.selectionBlockDefinition_return selectionBlockDefinition() throws RecognitionException {
        CQLParser.selectionBlockDefinition_return retval = new CQLParser.selectionBlockDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VARS5=null;
        Token char_literal6=null;
        Token char_literal7=null;
        Token WHERE8=null;
        List list_vd=null;
        CQLParser.condition_return c = null;

        RuleReturnScope vd = null;
        Object VARS5_tree=null;
        Object char_literal6_tree=null;
        Object char_literal7_tree=null;
        Object WHERE8_tree=null;
        RewriteRuleTokenStream stream_WHERE=new RewriteRuleTokenStream(adaptor,"token WHERE");
        RewriteRuleTokenStream stream_112=new RewriteRuleTokenStream(adaptor,"token 112");
        RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
        RewriteRuleTokenStream stream_VARS=new RewriteRuleTokenStream(adaptor,"token VARS");
        RewriteRuleSubtreeStream stream_variableDefinition=new RewriteRuleSubtreeStream(adaptor,"rule variableDefinition");
        RewriteRuleSubtreeStream stream_condition=new RewriteRuleSubtreeStream(adaptor,"rule condition");
         errorMessageStack.push("SELECT block definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:200:2: ( ( VARS '{' vd+= variableDefinition (vd+= variableDefinition )* '}' )? WHERE c= condition -> ^( SELECTION_DEFINITION ( ^( VARIABLES ( $vd)+ ) )? ^( CONDITION $c) ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:200:4: ( VARS '{' vd+= variableDefinition (vd+= variableDefinition )* '}' )? WHERE c= condition
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:200:4: ( VARS '{' vd+= variableDefinition (vd+= variableDefinition )* '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==VARS) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:200:5: VARS '{' vd+= variableDefinition (vd+= variableDefinition )* '}'
                    {
                    VARS5=(Token)match(input,VARS,FOLLOW_VARS_in_selectionBlockDefinition363);  
                    stream_VARS.add(VARS5);

                    char_literal6=(Token)match(input,111,FOLLOW_111_in_selectionBlockDefinition365);  
                    stream_111.add(char_literal6);

                    pushFollow(FOLLOW_variableDefinition_in_selectionBlockDefinition369);
                    vd=variableDefinition();

                    state._fsp--;

                    stream_variableDefinition.add(vd.getTree());
                    if (list_vd==null) list_vd=new ArrayList();
                    list_vd.add(vd.getTree());

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:200:37: (vd+= variableDefinition )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==ID) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:200:39: vd+= variableDefinition
                    	    {
                    	    pushFollow(FOLLOW_variableDefinition_in_selectionBlockDefinition375);
                    	    vd=variableDefinition();

                    	    state._fsp--;

                    	    stream_variableDefinition.add(vd.getTree());
                    	    if (list_vd==null) list_vd=new ArrayList();
                    	    list_vd.add(vd.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    char_literal7=(Token)match(input,112,FOLLOW_112_in_selectionBlockDefinition379);  
                    stream_112.add(char_literal7);


                    }
                    break;

            }

            WHERE8=(Token)match(input,WHERE,FOLLOW_WHERE_in_selectionBlockDefinition383);  
            stream_WHERE.add(WHERE8);

            pushFollow(FOLLOW_condition_in_selectionBlockDefinition387);
            c=condition();

            state._fsp--;

            stream_condition.add(c.getTree());


            // AST REWRITE
            // elements: c, vd
            // token labels: 
            // rule labels: retval, c
            // token list labels: 
            // rule list labels: vd
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_c=new RewriteRuleSubtreeStream(adaptor,"rule c",c!=null?c.tree:null);
            RewriteRuleSubtreeStream stream_vd=new RewriteRuleSubtreeStream(adaptor,"token vd",list_vd);
            root_0 = (Object)adaptor.nil();
            // 201:3: -> ^( SELECTION_DEFINITION ( ^( VARIABLES ( $vd)+ ) )? ^( CONDITION $c) )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:201:6: ^( SELECTION_DEFINITION ( ^( VARIABLES ( $vd)+ ) )? ^( CONDITION $c) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTION_DEFINITION, "SELECTION_DEFINITION"), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:201:29: ( ^( VARIABLES ( $vd)+ ) )?
                if ( stream_vd.hasNext() ) {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:201:29: ^( VARIABLES ( $vd)+ )
                    {
                    Object root_2 = (Object)adaptor.nil();
                    root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(VARIABLES, "VARIABLES"), root_2);

                    if ( !(stream_vd.hasNext()) ) {
                        throw new RewriteEarlyExitException();
                    }
                    while ( stream_vd.hasNext() ) {
                        adaptor.addChild(root_2, stream_vd.nextTree());

                    }
                    stream_vd.reset();

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_vd.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:201:48: ^( CONDITION $c)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONDITION, "CONDITION"), root_2);

                adaptor.addChild(root_2, stream_c.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "selectionBlockDefinition"

    public static class variableDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:204:1: variableDefinition : i= ID ':=' e= arithmeticExpression ';' -> ^( VAR $i $e) ;
    public final CQLParser.variableDefinition_return variableDefinition() throws RecognitionException {
        CQLParser.variableDefinition_return retval = new CQLParser.variableDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token string_literal9=null;
        Token char_literal10=null;
        CQLParser.arithmeticExpression_return e = null;


        Object i_tree=null;
        Object string_literal9_tree=null;
        Object char_literal10_tree=null;
        RewriteRuleTokenStream stream_113=new RewriteRuleTokenStream(adaptor,"token 113");
        RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_arithmeticExpression=new RewriteRuleSubtreeStream(adaptor,"rule arithmeticExpression");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:205:2: (i= ID ':=' e= arithmeticExpression ';' -> ^( VAR $i $e) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:205:4: i= ID ':=' e= arithmeticExpression ';'
            {
            i=(Token)match(input,ID,FOLLOW_ID_in_variableDefinition427);  
            stream_ID.add(i);

            string_literal9=(Token)match(input,113,FOLLOW_113_in_variableDefinition429);  
            stream_113.add(string_literal9);

            pushFollow(FOLLOW_arithmeticExpression_in_variableDefinition433);
            e=arithmeticExpression();

            state._fsp--;

            stream_arithmeticExpression.add(e.getTree());
            char_literal10=(Token)match(input,110,FOLLOW_110_in_variableDefinition435);  
            stream_110.add(char_literal10);



            // AST REWRITE
            // elements: i, e
            // token labels: i
            // rule labels: retval, e
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);

            root_0 = (Object)adaptor.nil();
            // 206:3: -> ^( VAR $i $e)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:206:6: ^( VAR $i $e)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VAR, "VAR"), root_1);

                adaptor.addChild(root_1, stream_i.nextNode());
                adaptor.addChild(root_1, stream_e.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "variableDefinition"

    public static class condition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:209:1: condition : logicalExpression ;
    public final CQLParser.condition_return condition() throws RecognitionException {
        CQLParser.condition_return retval = new CQLParser.condition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.logicalExpression_return logicalExpression11 = null;



         errorMessageStack.push("Condition definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:213:2: ( logicalExpression )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:213:4: logicalExpression
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_logicalExpression_in_condition477);
            logicalExpression11=logicalExpression();

            state._fsp--;

            adaptor.addChild(root_0, logicalExpression11.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "condition"

    public static class selectionBlockOptions_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selectionBlockOptions"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:217:1: selectionBlockOptions : (od+= selectionBlockOptionsDefinition )* -> ^( SELECTION_OPTIONS ( $od)* ) ;
    public final CQLParser.selectionBlockOptions_return selectionBlockOptions() throws RecognitionException {
        CQLParser.selectionBlockOptions_return retval = new CQLParser.selectionBlockOptions_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        List list_od=null;
        RuleReturnScope od = null;
        RewriteRuleSubtreeStream stream_selectionBlockOptionsDefinition=new RewriteRuleSubtreeStream(adaptor,"rule selectionBlockOptionsDefinition");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:218:2: ( (od+= selectionBlockOptionsDefinition )* -> ^( SELECTION_OPTIONS ( $od)* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:218:4: (od+= selectionBlockOptionsDefinition )*
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:218:4: (od+= selectionBlockOptionsDefinition )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==SORT||LA5_0==LIMIT) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:218:5: od+= selectionBlockOptionsDefinition
            	    {
            	    pushFollow(FOLLOW_selectionBlockOptionsDefinition_in_selectionBlockOptions496);
            	    od=selectionBlockOptionsDefinition();

            	    state._fsp--;

            	    stream_selectionBlockOptionsDefinition.add(od.getTree());
            	    if (list_od==null) list_od=new ArrayList();
            	    list_od.add(od.getTree());


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);



            // AST REWRITE
            // elements: od
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: od
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_od=new RewriteRuleSubtreeStream(adaptor,"token od",list_od);
            root_0 = (Object)adaptor.nil();
            // 219:3: -> ^( SELECTION_OPTIONS ( $od)* )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:219:6: ^( SELECTION_OPTIONS ( $od)* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECTION_OPTIONS, "SELECTION_OPTIONS"), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:219:26: ( $od)*
                while ( stream_od.hasNext() ) {
                    adaptor.addChild(root_1, stream_od.nextTree());

                }
                stream_od.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "selectionBlockOptions"

    public static class selectionBlockOptionsDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selectionBlockOptionsDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:222:1: selectionBlockOptionsDefinition : ( sortOptions | limitOptions );
    public final CQLParser.selectionBlockOptionsDefinition_return selectionBlockOptionsDefinition() throws RecognitionException {
        CQLParser.selectionBlockOptionsDefinition_return retval = new CQLParser.selectionBlockOptionsDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.sortOptions_return sortOptions12 = null;

        CQLParser.limitOptions_return limitOptions13 = null;



        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:223:2: ( sortOptions | limitOptions )
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
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:223:4: sortOptions
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_sortOptions_in_selectionBlockOptionsDefinition524);
                    sortOptions12=sortOptions();

                    state._fsp--;

                    adaptor.addChild(root_0, sortOptions12.getTree());

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:224:4: limitOptions
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_limitOptions_in_selectionBlockOptionsDefinition530);
                    limitOptions13=limitOptions();

                    state._fsp--;

                    adaptor.addChild(root_0, limitOptions13.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "selectionBlockOptionsDefinition"

    public static class sortOptions_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sortOptions"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:228:1: sortOptions : ( SORT BY i= ID -> ^( SORT $i) | SORT BY i= ID ( DESCENDING | DESC ) -> ^( SORT $i DESC ) );
    public final CQLParser.sortOptions_return sortOptions() throws RecognitionException {
        CQLParser.sortOptions_return retval = new CQLParser.sortOptions_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token SORT14=null;
        Token BY15=null;
        Token SORT16=null;
        Token BY17=null;
        Token DESCENDING18=null;
        Token DESC19=null;

        Object i_tree=null;
        Object SORT14_tree=null;
        Object BY15_tree=null;
        Object SORT16_tree=null;
        Object BY17_tree=null;
        Object DESCENDING18_tree=null;
        Object DESC19_tree=null;
        RewriteRuleTokenStream stream_SORT=new RewriteRuleTokenStream(adaptor,"token SORT");
        RewriteRuleTokenStream stream_BY=new RewriteRuleTokenStream(adaptor,"token BY");
        RewriteRuleTokenStream stream_DESC=new RewriteRuleTokenStream(adaptor,"token DESC");
        RewriteRuleTokenStream stream_DESCENDING=new RewriteRuleTokenStream(adaptor,"token DESCENDING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

         errorMessageStack.push("SORT statement"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:232:2: ( SORT BY i= ID -> ^( SORT $i) | SORT BY i= ID ( DESCENDING | DESC ) -> ^( SORT $i DESC ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==SORT) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==BY) ) {
                    int LA8_2 = input.LA(3);

                    if ( (LA8_2==ID) ) {
                        int LA8_3 = input.LA(4);

                        if ( (LA8_3==SORT||LA8_3==LIMIT||LA8_3==110) ) {
                            alt8=1;
                        }
                        else if ( ((LA8_3>=DESCENDING && LA8_3<=DESC)) ) {
                            alt8=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:232:4: SORT BY i= ID
                    {
                    SORT14=(Token)match(input,SORT,FOLLOW_SORT_in_sortOptions559);  
                    stream_SORT.add(SORT14);

                    BY15=(Token)match(input,BY,FOLLOW_BY_in_sortOptions561);  
                    stream_BY.add(BY15);

                    i=(Token)match(input,ID,FOLLOW_ID_in_sortOptions565);  
                    stream_ID.add(i);



                    // AST REWRITE
                    // elements: SORT, i
                    // token labels: i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 233:3: -> ^( SORT $i)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:233:6: ^( SORT $i)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_SORT.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_i.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:235:4: SORT BY i= ID ( DESCENDING | DESC )
                    {
                    SORT16=(Token)match(input,SORT,FOLLOW_SORT_in_sortOptions584);  
                    stream_SORT.add(SORT16);

                    BY17=(Token)match(input,BY,FOLLOW_BY_in_sortOptions586);  
                    stream_BY.add(BY17);

                    i=(Token)match(input,ID,FOLLOW_ID_in_sortOptions590);  
                    stream_ID.add(i);

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:235:17: ( DESCENDING | DESC )
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==DESCENDING) ) {
                        alt7=1;
                    }
                    else if ( (LA7_0==DESC) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 0, input);

                        throw nvae;
                    }
                    switch (alt7) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:235:18: DESCENDING
                            {
                            DESCENDING18=(Token)match(input,DESCENDING,FOLLOW_DESCENDING_in_sortOptions593);  
                            stream_DESCENDING.add(DESCENDING18);


                            }
                            break;
                        case 2 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:235:29: DESC
                            {
                            DESC19=(Token)match(input,DESC,FOLLOW_DESC_in_sortOptions595);  
                            stream_DESC.add(DESC19);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: DESC, i, SORT
                    // token labels: i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 236:3: -> ^( SORT $i DESC )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:236:6: ^( SORT $i DESC )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_SORT.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_i.nextNode());
                        adaptor.addChild(root_1, stream_DESC.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "sortOptions"

    public static class limitOptions_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "limitOptions"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:240:1: limitOptions : LIMIT (ld+= limitDefinition )+ -> ^( LIMIT ( $ld)+ ) ;
    public final CQLParser.limitOptions_return limitOptions() throws RecognitionException {
        CQLParser.limitOptions_return retval = new CQLParser.limitOptions_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LIMIT20=null;
        List list_ld=null;
        RuleReturnScope ld = null;
        Object LIMIT20_tree=null;
        RewriteRuleTokenStream stream_LIMIT=new RewriteRuleTokenStream(adaptor,"token LIMIT");
        RewriteRuleSubtreeStream stream_limitDefinition=new RewriteRuleSubtreeStream(adaptor,"rule limitDefinition");
         errorMessageStack.push("LIMIT statement"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:244:2: ( LIMIT (ld+= limitDefinition )+ -> ^( LIMIT ( $ld)+ ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:244:4: LIMIT (ld+= limitDefinition )+
            {
            LIMIT20=(Token)match(input,LIMIT,FOLLOW_LIMIT_in_limitOptions637);  
            stream_LIMIT.add(LIMIT20);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:244:12: (ld+= limitDefinition )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=TOP && LA9_0<=RANDOM)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:244:12: ld+= limitDefinition
            	    {
            	    pushFollow(FOLLOW_limitDefinition_in_limitOptions641);
            	    ld=limitDefinition();

            	    state._fsp--;

            	    stream_limitDefinition.add(ld.getTree());
            	    if (list_ld==null) list_ld=new ArrayList();
            	    list_ld.add(ld.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);



            // AST REWRITE
            // elements: ld, LIMIT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: ld
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_ld=new RewriteRuleSubtreeStream(adaptor,"token ld",list_ld);
            root_0 = (Object)adaptor.nil();
            // 245:3: -> ^( LIMIT ( $ld)+ )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:245:6: ^( LIMIT ( $ld)+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_LIMIT.nextNode(), root_1);

                if ( !(stream_ld.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ld.hasNext() ) {
                    adaptor.addChild(root_1, stream_ld.nextTree());

                }
                stream_ld.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "limitOptions"

    public static class limitDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "limitDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:248:1: limitDefinition : ( TOP lp= limitParameters -> ^( TOP $lp) | BOTTOM lp= limitParameters -> ^( BOTTOM $lp) | RANDOM lp= limitParameters -> ^( RANDOM $lp) );
    public final CQLParser.limitDefinition_return limitDefinition() throws RecognitionException {
        CQLParser.limitDefinition_return retval = new CQLParser.limitDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TOP21=null;
        Token BOTTOM22=null;
        Token RANDOM23=null;
        CQLParser.limitParameters_return lp = null;


        Object TOP21_tree=null;
        Object BOTTOM22_tree=null;
        Object RANDOM23_tree=null;
        RewriteRuleTokenStream stream_BOTTOM=new RewriteRuleTokenStream(adaptor,"token BOTTOM");
        RewriteRuleTokenStream stream_RANDOM=new RewriteRuleTokenStream(adaptor,"token RANDOM");
        RewriteRuleTokenStream stream_TOP=new RewriteRuleTokenStream(adaptor,"token TOP");
        RewriteRuleSubtreeStream stream_limitParameters=new RewriteRuleSubtreeStream(adaptor,"rule limitParameters");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:249:2: ( TOP lp= limitParameters -> ^( TOP $lp) | BOTTOM lp= limitParameters -> ^( BOTTOM $lp) | RANDOM lp= limitParameters -> ^( RANDOM $lp) )
            int alt10=3;
            switch ( input.LA(1) ) {
            case TOP:
                {
                alt10=1;
                }
                break;
            case BOTTOM:
                {
                alt10=2;
                }
                break;
            case RANDOM:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:249:4: TOP lp= limitParameters
                    {
                    TOP21=(Token)match(input,TOP,FOLLOW_TOP_in_limitDefinition666);  
                    stream_TOP.add(TOP21);

                    pushFollow(FOLLOW_limitParameters_in_limitDefinition670);
                    lp=limitParameters();

                    state._fsp--;

                    stream_limitParameters.add(lp.getTree());


                    // AST REWRITE
                    // elements: TOP, lp
                    // token labels: 
                    // rule labels: retval, lp
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_lp=new RewriteRuleSubtreeStream(adaptor,"rule lp",lp!=null?lp.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 249:28: -> ^( TOP $lp)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:249:31: ^( TOP $lp)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_TOP.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_lp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:250:4: BOTTOM lp= limitParameters
                    {
                    BOTTOM22=(Token)match(input,BOTTOM,FOLLOW_BOTTOM_in_limitDefinition686);  
                    stream_BOTTOM.add(BOTTOM22);

                    pushFollow(FOLLOW_limitParameters_in_limitDefinition690);
                    lp=limitParameters();

                    state._fsp--;

                    stream_limitParameters.add(lp.getTree());


                    // AST REWRITE
                    // elements: lp, BOTTOM
                    // token labels: 
                    // rule labels: retval, lp
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_lp=new RewriteRuleSubtreeStream(adaptor,"rule lp",lp!=null?lp.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 250:30: -> ^( BOTTOM $lp)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:250:33: ^( BOTTOM $lp)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_BOTTOM.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_lp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:251:4: RANDOM lp= limitParameters
                    {
                    RANDOM23=(Token)match(input,RANDOM,FOLLOW_RANDOM_in_limitDefinition704);  
                    stream_RANDOM.add(RANDOM23);

                    pushFollow(FOLLOW_limitParameters_in_limitDefinition708);
                    lp=limitParameters();

                    state._fsp--;

                    stream_limitParameters.add(lp.getTree());


                    // AST REWRITE
                    // elements: RANDOM, lp
                    // token labels: 
                    // rule labels: retval, lp
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_lp=new RewriteRuleSubtreeStream(adaptor,"rule lp",lp!=null?lp.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 251:30: -> ^( RANDOM $lp)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:251:33: ^( RANDOM $lp)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_RANDOM.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_lp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "limitDefinition"

    public static class limitParameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "limitParameters"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:254:1: limitParameters : '(' INTEGER ( ',' INTEGER )? ')' ;
    public final CQLParser.limitParameters_return limitParameters() throws RecognitionException {
        CQLParser.limitParameters_return retval = new CQLParser.limitParameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal24=null;
        Token INTEGER25=null;
        Token char_literal26=null;
        Token INTEGER27=null;
        Token char_literal28=null;

        Object char_literal24_tree=null;
        Object INTEGER25_tree=null;
        Object char_literal26_tree=null;
        Object INTEGER27_tree=null;
        Object char_literal28_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:255:2: ( '(' INTEGER ( ',' INTEGER )? ')' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:255:5: '(' INTEGER ( ',' INTEGER )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal24=(Token)match(input,114,FOLLOW_114_in_limitParameters733); 
            INTEGER25=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_limitParameters736); 
            INTEGER25_tree = (Object)adaptor.create(INTEGER25);
            adaptor.addChild(root_0, INTEGER25_tree);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:255:18: ( ',' INTEGER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==115) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:255:19: ',' INTEGER
                    {
                    char_literal26=(Token)match(input,115,FOLLOW_115_in_limitParameters739); 
                    INTEGER27=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_limitParameters742); 
                    INTEGER27_tree = (Object)adaptor.create(INTEGER27);
                    adaptor.addChild(root_0, INTEGER27_tree);


                    }
                    break;

            }

            char_literal28=(Token)match(input,116,FOLLOW_116_in_limitParameters746); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "limitParameters"

    public static class labelDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "labelDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:259:1: labelDefinition : AS labelName ;
    public final CQLParser.labelDefinition_return labelDefinition() throws RecognitionException {
        CQLParser.labelDefinition_return retval = new CQLParser.labelDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AS29=null;
        CQLParser.labelName_return labelName30 = null;


        Object AS29_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:260:2: ( AS labelName )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:260:4: AS labelName
            {
            root_0 = (Object)adaptor.nil();

            AS29=(Token)match(input,AS,FOLLOW_AS_in_labelDefinition761); 
            pushFollow(FOLLOW_labelName_in_labelDefinition764);
            labelName30=labelName();

            state._fsp--;

            adaptor.addChild(root_0, labelName30.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "labelDefinition"

    public static class labelName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "labelName"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:263:1: labelName : (i= ID | qs= QUOTED_STRING ) -> ^( LABEL ( $i)? ( $qs)? ) ;
    public final CQLParser.labelName_return labelName() throws RecognitionException {
        CQLParser.labelName_return retval = new CQLParser.labelName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token qs=null;

        Object i_tree=null;
        Object qs_tree=null;
        RewriteRuleTokenStream stream_QUOTED_STRING=new RewriteRuleTokenStream(adaptor,"token QUOTED_STRING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:264:2: ( (i= ID | qs= QUOTED_STRING ) -> ^( LABEL ( $i)? ( $qs)? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:264:4: (i= ID | qs= QUOTED_STRING )
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:264:4: (i= ID | qs= QUOTED_STRING )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ID) ) {
                alt12=1;
            }
            else if ( (LA12_0==QUOTED_STRING) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:264:5: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_labelName782);  
                    stream_ID.add(i);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:264:10: qs= QUOTED_STRING
                    {
                    qs=(Token)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_labelName786);  
                    stream_QUOTED_STRING.add(qs);


                    }
                    break;

            }



            // AST REWRITE
            // elements: qs, i
            // token labels: qs, i
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_qs=new RewriteRuleTokenStream(adaptor,"token qs",qs);
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 265:3: -> ^( LABEL ( $i)? ( $qs)? )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:265:6: ^( LABEL ( $i)? ( $qs)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LABEL, "LABEL"), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:265:14: ( $i)?
                if ( stream_i.hasNext() ) {
                    adaptor.addChild(root_1, stream_i.nextNode());

                }
                stream_i.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:265:18: ( $qs)?
                if ( stream_qs.hasNext() ) {
                    adaptor.addChild(root_1, stream_qs.nextNode());

                }
                stream_qs.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "labelName"

    public static class resultBlock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "resultBlock"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:276:1: resultBlock : RESULT '{' a= resultDefinition '}' ';' -> ^( RESULT $a) ;
    public final CQLParser.resultBlock_return resultBlock() throws RecognitionException {
        CQLParser.resultBlock_return retval = new CQLParser.resultBlock_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RESULT31=null;
        Token char_literal32=null;
        Token char_literal33=null;
        Token char_literal34=null;
        CQLParser.resultDefinition_return a = null;


        Object RESULT31_tree=null;
        Object char_literal32_tree=null;
        Object char_literal33_tree=null;
        Object char_literal34_tree=null;
        RewriteRuleTokenStream stream_RESULT=new RewriteRuleTokenStream(adaptor,"token RESULT");
        RewriteRuleTokenStream stream_112=new RewriteRuleTokenStream(adaptor,"token 112");
        RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
        RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
        RewriteRuleSubtreeStream stream_resultDefinition=new RewriteRuleSubtreeStream(adaptor,"rule resultDefinition");
         errorMessageStack.push("RESULT block"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:280:2: ( RESULT '{' a= resultDefinition '}' ';' -> ^( RESULT $a) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:280:4: RESULT '{' a= resultDefinition '}' ';'
            {
            RESULT31=(Token)match(input,RESULT,FOLLOW_RESULT_in_resultBlock838);  
            stream_RESULT.add(RESULT31);

            char_literal32=(Token)match(input,111,FOLLOW_111_in_resultBlock840);  
            stream_111.add(char_literal32);

            pushFollow(FOLLOW_resultDefinition_in_resultBlock844);
            a=resultDefinition();

            state._fsp--;

            stream_resultDefinition.add(a.getTree());
            char_literal33=(Token)match(input,112,FOLLOW_112_in_resultBlock846);  
            stream_112.add(char_literal33);

            char_literal34=(Token)match(input,110,FOLLOW_110_in_resultBlock848);  
            stream_110.add(char_literal34);



            // AST REWRITE
            // elements: RESULT, a
            // token labels: 
            // rule labels: retval, a
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

            root_0 = (Object)adaptor.nil();
            // 281:3: -> ^( RESULT $a)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:281:6: ^( RESULT $a)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_RESULT.nextNode(), root_1);

                adaptor.addChild(root_1, stream_a.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "resultBlock"

    public static class resultDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "resultDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:284:1: resultDefinition : e= resultExpression (ps= propertySelection )? (l= labelDefinition )? -> ^( RESULT_DEFINITION $e ( $ps)? ( $l)? ) ;
    public final CQLParser.resultDefinition_return resultDefinition() throws RecognitionException {
        CQLParser.resultDefinition_return retval = new CQLParser.resultDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.resultExpression_return e = null;

        CQLParser.propertySelection_return ps = null;

        CQLParser.labelDefinition_return l = null;


        RewriteRuleSubtreeStream stream_resultExpression=new RewriteRuleSubtreeStream(adaptor,"rule resultExpression");
        RewriteRuleSubtreeStream stream_propertySelection=new RewriteRuleSubtreeStream(adaptor,"rule propertySelection");
        RewriteRuleSubtreeStream stream_labelDefinition=new RewriteRuleSubtreeStream(adaptor,"rule labelDefinition");
         errorMessageStack.push("RESULT definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:288:2: (e= resultExpression (ps= propertySelection )? (l= labelDefinition )? -> ^( RESULT_DEFINITION $e ( $ps)? ( $l)? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:288:4: e= resultExpression (ps= propertySelection )? (l= labelDefinition )?
            {
            pushFollow(FOLLOW_resultExpression_in_resultDefinition891);
            e=resultExpression();

            state._fsp--;

            stream_resultExpression.add(e.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:288:23: (ps= propertySelection )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==117) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:288:24: ps= propertySelection
                    {
                    pushFollow(FOLLOW_propertySelection_in_resultDefinition896);
                    ps=propertySelection();

                    state._fsp--;

                    stream_propertySelection.add(ps.getTree());

                    }
                    break;

            }

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:288:47: (l= labelDefinition )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==AS) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:288:48: l= labelDefinition
                    {
                    pushFollow(FOLLOW_labelDefinition_in_resultDefinition903);
                    l=labelDefinition();

                    state._fsp--;

                    stream_labelDefinition.add(l.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: e, l, ps
            // token labels: 
            // rule labels: retval, e, l, ps
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);
            RewriteRuleSubtreeStream stream_l=new RewriteRuleSubtreeStream(adaptor,"rule l",l!=null?l.tree:null);
            RewriteRuleSubtreeStream stream_ps=new RewriteRuleSubtreeStream(adaptor,"rule ps",ps!=null?ps.tree:null);

            root_0 = (Object)adaptor.nil();
            // 289:3: -> ^( RESULT_DEFINITION $e ( $ps)? ( $l)? )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:289:6: ^( RESULT_DEFINITION $e ( $ps)? ( $l)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RESULT_DEFINITION, "RESULT_DEFINITION"), root_1);

                adaptor.addChild(root_1, stream_e.nextTree());
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:289:29: ( $ps)?
                if ( stream_ps.hasNext() ) {
                    adaptor.addChild(root_1, stream_ps.nextTree());

                }
                stream_ps.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:289:34: ( $l)?
                if ( stream_l.hasNext() ) {
                    adaptor.addChild(root_1, stream_l.nextTree());

                }
                stream_l.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "resultDefinition"

    public static class resultExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "resultExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:292:1: resultExpression : (i= ID -> ^( REGULAR_SELECTION_RESULT $i) | qs= QUOTED_STRING -> ^( REGULAR_SELECTION_RESULT $qs) | sf= setOperation -> ^( COMBINED_SELECTION_RESULT $sf) | rsf= resultStatisticalFunction -> ^( STATS_SELECTION_RESULT $rsf) | DISTINCT i= ID -> ^( DISTINCT_SELECTION_RESULT $i) | DISTINCT qs= QUOTED_STRING -> ^( DISTINCT_SELECTION_RESULT $qs) );
    public final CQLParser.resultExpression_return resultExpression() throws RecognitionException {
        CQLParser.resultExpression_return retval = new CQLParser.resultExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token qs=null;
        Token DISTINCT35=null;
        Token DISTINCT36=null;
        CQLParser.setOperation_return sf = null;

        CQLParser.resultStatisticalFunction_return rsf = null;


        Object i_tree=null;
        Object qs_tree=null;
        Object DISTINCT35_tree=null;
        Object DISTINCT36_tree=null;
        RewriteRuleTokenStream stream_QUOTED_STRING=new RewriteRuleTokenStream(adaptor,"token QUOTED_STRING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DISTINCT=new RewriteRuleTokenStream(adaptor,"token DISTINCT");
        RewriteRuleSubtreeStream stream_resultStatisticalFunction=new RewriteRuleSubtreeStream(adaptor,"rule resultStatisticalFunction");
        RewriteRuleSubtreeStream stream_setOperation=new RewriteRuleSubtreeStream(adaptor,"rule setOperation");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:293:2: (i= ID -> ^( REGULAR_SELECTION_RESULT $i) | qs= QUOTED_STRING -> ^( REGULAR_SELECTION_RESULT $qs) | sf= setOperation -> ^( COMBINED_SELECTION_RESULT $sf) | rsf= resultStatisticalFunction -> ^( STATS_SELECTION_RESULT $rsf) | DISTINCT i= ID -> ^( DISTINCT_SELECTION_RESULT $i) | DISTINCT qs= QUOTED_STRING -> ^( DISTINCT_SELECTION_RESULT $qs) )
            int alt15=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt15=1;
                }
                break;
            case QUOTED_STRING:
                {
                alt15=2;
                }
                break;
            case UNION:
            case INTERSECTION:
            case DIFFERENCE:
                {
                alt15=3;
                }
                break;
            case AVG:
            case MEDIAN:
            case MIN:
            case MAX:
            case STDDEV:
            case VAR:
            case SUM:
            case COUNT:
                {
                alt15=4;
                }
                break;
            case DISTINCT:
                {
                int LA15_5 = input.LA(2);

                if ( (LA15_5==ID) ) {
                    alt15=5;
                }
                else if ( (LA15_5==QUOTED_STRING) ) {
                    alt15=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:293:4: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_resultExpression938);  
                    stream_ID.add(i);



                    // AST REWRITE
                    // elements: i
                    // token labels: i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 293:15: -> ^( REGULAR_SELECTION_RESULT $i)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:293:18: ^( REGULAR_SELECTION_RESULT $i)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(REGULAR_SELECTION_RESULT, "REGULAR_SELECTION_RESULT"), root_1);

                        adaptor.addChild(root_1, stream_i.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:294:4: qs= QUOTED_STRING
                    {
                    qs=(Token)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_resultExpression960);  
                    stream_QUOTED_STRING.add(qs);



                    // AST REWRITE
                    // elements: qs
                    // token labels: qs
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_qs=new RewriteRuleTokenStream(adaptor,"token qs",qs);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 294:24: -> ^( REGULAR_SELECTION_RESULT $qs)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:294:27: ^( REGULAR_SELECTION_RESULT $qs)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(REGULAR_SELECTION_RESULT, "REGULAR_SELECTION_RESULT"), root_1);

                        adaptor.addChild(root_1, stream_qs.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:295:4: sf= setOperation
                    {
                    pushFollow(FOLLOW_setOperation_in_resultExpression979);
                    sf=setOperation();

                    state._fsp--;

                    stream_setOperation.add(sf.getTree());


                    // AST REWRITE
                    // elements: sf
                    // token labels: 
                    // rule labels: retval, sf
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_sf=new RewriteRuleSubtreeStream(adaptor,"rule sf",sf!=null?sf.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 295:24: -> ^( COMBINED_SELECTION_RESULT $sf)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:295:27: ^( COMBINED_SELECTION_RESULT $sf)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COMBINED_SELECTION_RESULT, "COMBINED_SELECTION_RESULT"), root_1);

                        adaptor.addChild(root_1, stream_sf.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:296:4: rsf= resultStatisticalFunction
                    {
                    pushFollow(FOLLOW_resultStatisticalFunction_in_resultExpression999);
                    rsf=resultStatisticalFunction();

                    state._fsp--;

                    stream_resultStatisticalFunction.add(rsf.getTree());


                    // AST REWRITE
                    // elements: rsf
                    // token labels: 
                    // rule labels: retval, rsf
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_rsf=new RewriteRuleSubtreeStream(adaptor,"rule rsf",rsf!=null?rsf.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 296:34: -> ^( STATS_SELECTION_RESULT $rsf)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:296:37: ^( STATS_SELECTION_RESULT $rsf)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATS_SELECTION_RESULT, "STATS_SELECTION_RESULT"), root_1);

                        adaptor.addChild(root_1, stream_rsf.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:297:5: DISTINCT i= ID
                    {
                    DISTINCT35=(Token)match(input,DISTINCT,FOLLOW_DISTINCT_in_resultExpression1015);  
                    stream_DISTINCT.add(DISTINCT35);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultExpression1019);  
                    stream_ID.add(i);



                    // AST REWRITE
                    // elements: i
                    // token labels: i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 297:37: -> ^( DISTINCT_SELECTION_RESULT $i)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:297:40: ^( DISTINCT_SELECTION_RESULT $i)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DISTINCT_SELECTION_RESULT, "DISTINCT_SELECTION_RESULT"), root_1);

                        adaptor.addChild(root_1, stream_i.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:298:5: DISTINCT qs= QUOTED_STRING
                    {
                    DISTINCT36=(Token)match(input,DISTINCT,FOLLOW_DISTINCT_in_resultExpression1052);  
                    stream_DISTINCT.add(DISTINCT36);

                    qs=(Token)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_resultExpression1056);  
                    stream_QUOTED_STRING.add(qs);



                    // AST REWRITE
                    // elements: qs
                    // token labels: qs
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_qs=new RewriteRuleTokenStream(adaptor,"token qs",qs);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 298:32: -> ^( DISTINCT_SELECTION_RESULT $qs)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:298:35: ^( DISTINCT_SELECTION_RESULT $qs)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DISTINCT_SELECTION_RESULT, "DISTINCT_SELECTION_RESULT"), root_1);

                        adaptor.addChild(root_1, stream_qs.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "resultExpression"

    public static class resultStatisticalFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "resultStatisticalFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:301:1: resultStatisticalFunction : ( AVG '(' i= ID ',' p= ID ')' -> ^( AVG ^( ARGUMENT $i) ^( PROPERTY $p) ) | MEDIAN '(' i= ID ',' p= ID ')' -> ^( MEDIAN ^( ARGUMENT $i) ^( PROPERTY $p) ) | MIN '(' i= ID ',' p= ID ')' -> ^( MIN ^( ARGUMENT $i) ^( PROPERTY $p) ) | MAX '(' i= ID ',' p= ID ')' -> ^( MAX ^( ARGUMENT $i) ^( PROPERTY $p) ) | STDDEV '(' i= ID ',' p= ID ')' -> ^( STDDEV ^( ARGUMENT $i) ^( PROPERTY $p) ) | VAR '(' i= ID ',' p= ID ')' -> ^( VAR ^( ARGUMENT $i) ^( PROPERTY $p) ) | SUM '(' i= ID ',' p= ID ')' -> ^( SUM ^( ARGUMENT $i) ^( PROPERTY $p) ) | COUNT '(' i= ID ',' p= ID ')' -> ^( COUNT ^( ARGUMENT $i) ^( PROPERTY $p) ) );
    public final CQLParser.resultStatisticalFunction_return resultStatisticalFunction() throws RecognitionException {
        CQLParser.resultStatisticalFunction_return retval = new CQLParser.resultStatisticalFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token p=null;
        Token AVG37=null;
        Token char_literal38=null;
        Token char_literal39=null;
        Token char_literal40=null;
        Token MEDIAN41=null;
        Token char_literal42=null;
        Token char_literal43=null;
        Token char_literal44=null;
        Token MIN45=null;
        Token char_literal46=null;
        Token char_literal47=null;
        Token char_literal48=null;
        Token MAX49=null;
        Token char_literal50=null;
        Token char_literal51=null;
        Token char_literal52=null;
        Token STDDEV53=null;
        Token char_literal54=null;
        Token char_literal55=null;
        Token char_literal56=null;
        Token VAR57=null;
        Token char_literal58=null;
        Token char_literal59=null;
        Token char_literal60=null;
        Token SUM61=null;
        Token char_literal62=null;
        Token char_literal63=null;
        Token char_literal64=null;
        Token COUNT65=null;
        Token char_literal66=null;
        Token char_literal67=null;
        Token char_literal68=null;

        Object i_tree=null;
        Object p_tree=null;
        Object AVG37_tree=null;
        Object char_literal38_tree=null;
        Object char_literal39_tree=null;
        Object char_literal40_tree=null;
        Object MEDIAN41_tree=null;
        Object char_literal42_tree=null;
        Object char_literal43_tree=null;
        Object char_literal44_tree=null;
        Object MIN45_tree=null;
        Object char_literal46_tree=null;
        Object char_literal47_tree=null;
        Object char_literal48_tree=null;
        Object MAX49_tree=null;
        Object char_literal50_tree=null;
        Object char_literal51_tree=null;
        Object char_literal52_tree=null;
        Object STDDEV53_tree=null;
        Object char_literal54_tree=null;
        Object char_literal55_tree=null;
        Object char_literal56_tree=null;
        Object VAR57_tree=null;
        Object char_literal58_tree=null;
        Object char_literal59_tree=null;
        Object char_literal60_tree=null;
        Object SUM61_tree=null;
        Object char_literal62_tree=null;
        Object char_literal63_tree=null;
        Object char_literal64_tree=null;
        Object COUNT65_tree=null;
        Object char_literal66_tree=null;
        Object char_literal67_tree=null;
        Object char_literal68_tree=null;
        RewriteRuleTokenStream stream_STDDEV=new RewriteRuleTokenStream(adaptor,"token STDDEV");
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_MAX=new RewriteRuleTokenStream(adaptor,"token MAX");
        RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
        RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
        RewriteRuleTokenStream stream_COUNT=new RewriteRuleTokenStream(adaptor,"token COUNT");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_MIN=new RewriteRuleTokenStream(adaptor,"token MIN");
        RewriteRuleTokenStream stream_SUM=new RewriteRuleTokenStream(adaptor,"token SUM");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_MEDIAN=new RewriteRuleTokenStream(adaptor,"token MEDIAN");
        RewriteRuleTokenStream stream_AVG=new RewriteRuleTokenStream(adaptor,"token AVG");

         errorMessageStack.push("RESULT statistical function"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:305:2: ( AVG '(' i= ID ',' p= ID ')' -> ^( AVG ^( ARGUMENT $i) ^( PROPERTY $p) ) | MEDIAN '(' i= ID ',' p= ID ')' -> ^( MEDIAN ^( ARGUMENT $i) ^( PROPERTY $p) ) | MIN '(' i= ID ',' p= ID ')' -> ^( MIN ^( ARGUMENT $i) ^( PROPERTY $p) ) | MAX '(' i= ID ',' p= ID ')' -> ^( MAX ^( ARGUMENT $i) ^( PROPERTY $p) ) | STDDEV '(' i= ID ',' p= ID ')' -> ^( STDDEV ^( ARGUMENT $i) ^( PROPERTY $p) ) | VAR '(' i= ID ',' p= ID ')' -> ^( VAR ^( ARGUMENT $i) ^( PROPERTY $p) ) | SUM '(' i= ID ',' p= ID ')' -> ^( SUM ^( ARGUMENT $i) ^( PROPERTY $p) ) | COUNT '(' i= ID ',' p= ID ')' -> ^( COUNT ^( ARGUMENT $i) ^( PROPERTY $p) ) )
            int alt16=8;
            switch ( input.LA(1) ) {
            case AVG:
                {
                alt16=1;
                }
                break;
            case MEDIAN:
                {
                alt16=2;
                }
                break;
            case MIN:
                {
                alt16=3;
                }
                break;
            case MAX:
                {
                alt16=4;
                }
                break;
            case STDDEV:
                {
                alt16=5;
                }
                break;
            case VAR:
                {
                alt16=6;
                }
                break;
            case SUM:
                {
                alt16=7;
                }
                break;
            case COUNT:
                {
                alt16=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:305:4: AVG '(' i= ID ',' p= ID ')'
                    {
                    AVG37=(Token)match(input,AVG,FOLLOW_AVG_in_resultStatisticalFunction1094);  
                    stream_AVG.add(AVG37);

                    char_literal38=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1097);  
                    stream_114.add(char_literal38);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1101);  
                    stream_ID.add(i);

                    char_literal39=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1103);  
                    stream_115.add(char_literal39);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1107);  
                    stream_ID.add(p);

                    char_literal40=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1109);  
                    stream_116.add(char_literal40);



                    // AST REWRITE
                    // elements: AVG, p, i
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 305:31: -> ^( AVG ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:305:34: ^( AVG ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_AVG.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:305:40: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:305:55: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:306:4: MEDIAN '(' i= ID ',' p= ID ')'
                    {
                    MEDIAN41=(Token)match(input,MEDIAN,FOLLOW_MEDIAN_in_resultStatisticalFunction1135);  
                    stream_MEDIAN.add(MEDIAN41);

                    char_literal42=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1137);  
                    stream_114.add(char_literal42);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1141);  
                    stream_ID.add(i);

                    char_literal43=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1143);  
                    stream_115.add(char_literal43);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1147);  
                    stream_ID.add(p);

                    char_literal44=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1149);  
                    stream_116.add(char_literal44);



                    // AST REWRITE
                    // elements: p, i, MEDIAN
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 306:33: -> ^( MEDIAN ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:306:36: ^( MEDIAN ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MEDIAN.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:306:45: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:306:60: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:307:4: MIN '(' i= ID ',' p= ID ')'
                    {
                    MIN45=(Token)match(input,MIN,FOLLOW_MIN_in_resultStatisticalFunction1175);  
                    stream_MIN.add(MIN45);

                    char_literal46=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1178);  
                    stream_114.add(char_literal46);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1182);  
                    stream_ID.add(i);

                    char_literal47=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1184);  
                    stream_115.add(char_literal47);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1188);  
                    stream_ID.add(p);

                    char_literal48=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1190);  
                    stream_116.add(char_literal48);



                    // AST REWRITE
                    // elements: p, i, MIN
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 307:31: -> ^( MIN ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:307:34: ^( MIN ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MIN.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:307:40: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:307:55: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:308:4: MAX '(' i= ID ',' p= ID ')'
                    {
                    MAX49=(Token)match(input,MAX,FOLLOW_MAX_in_resultStatisticalFunction1216);  
                    stream_MAX.add(MAX49);

                    char_literal50=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1219);  
                    stream_114.add(char_literal50);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1223);  
                    stream_ID.add(i);

                    char_literal51=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1225);  
                    stream_115.add(char_literal51);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1229);  
                    stream_ID.add(p);

                    char_literal52=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1231);  
                    stream_116.add(char_literal52);



                    // AST REWRITE
                    // elements: MAX, i, p
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 308:31: -> ^( MAX ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:308:34: ^( MAX ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_MAX.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:308:40: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:308:55: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:309:4: STDDEV '(' i= ID ',' p= ID ')'
                    {
                    STDDEV53=(Token)match(input,STDDEV,FOLLOW_STDDEV_in_resultStatisticalFunction1257);  
                    stream_STDDEV.add(STDDEV53);

                    char_literal54=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1259);  
                    stream_114.add(char_literal54);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1263);  
                    stream_ID.add(i);

                    char_literal55=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1265);  
                    stream_115.add(char_literal55);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1269);  
                    stream_ID.add(p);

                    char_literal56=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1271);  
                    stream_116.add(char_literal56);



                    // AST REWRITE
                    // elements: i, STDDEV, p
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 309:33: -> ^( STDDEV ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:309:36: ^( STDDEV ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_STDDEV.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:309:45: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:309:60: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:310:4: VAR '(' i= ID ',' p= ID ')'
                    {
                    VAR57=(Token)match(input,VAR,FOLLOW_VAR_in_resultStatisticalFunction1297);  
                    stream_VAR.add(VAR57);

                    char_literal58=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1300);  
                    stream_114.add(char_literal58);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1304);  
                    stream_ID.add(i);

                    char_literal59=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1306);  
                    stream_115.add(char_literal59);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1310);  
                    stream_ID.add(p);

                    char_literal60=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1312);  
                    stream_116.add(char_literal60);



                    // AST REWRITE
                    // elements: i, p, VAR
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 310:31: -> ^( VAR ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:310:34: ^( VAR ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_VAR.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:310:40: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:310:55: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:311:4: SUM '(' i= ID ',' p= ID ')'
                    {
                    SUM61=(Token)match(input,SUM,FOLLOW_SUM_in_resultStatisticalFunction1338);  
                    stream_SUM.add(SUM61);

                    char_literal62=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1341);  
                    stream_114.add(char_literal62);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1345);  
                    stream_ID.add(i);

                    char_literal63=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1347);  
                    stream_115.add(char_literal63);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1351);  
                    stream_ID.add(p);

                    char_literal64=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1353);  
                    stream_116.add(char_literal64);



                    // AST REWRITE
                    // elements: i, p, SUM
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 311:31: -> ^( SUM ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:311:34: ^( SUM ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_SUM.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:311:40: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:311:55: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 8 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:312:4: COUNT '(' i= ID ',' p= ID ')'
                    {
                    COUNT65=(Token)match(input,COUNT,FOLLOW_COUNT_in_resultStatisticalFunction1379);  
                    stream_COUNT.add(COUNT65);

                    char_literal66=(Token)match(input,114,FOLLOW_114_in_resultStatisticalFunction1381);  
                    stream_114.add(char_literal66);

                    i=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1385);  
                    stream_ID.add(i);

                    char_literal67=(Token)match(input,115,FOLLOW_115_in_resultStatisticalFunction1387);  
                    stream_115.add(char_literal67);

                    p=(Token)match(input,ID,FOLLOW_ID_in_resultStatisticalFunction1391);  
                    stream_ID.add(p);

                    char_literal68=(Token)match(input,116,FOLLOW_116_in_resultStatisticalFunction1393);  
                    stream_116.add(char_literal68);



                    // AST REWRITE
                    // elements: p, i, COUNT
                    // token labels: p, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_p=new RewriteRuleTokenStream(adaptor,"token p",p);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 312:32: -> ^( COUNT ^( ARGUMENT $i) ^( PROPERTY $p) )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:312:35: ^( COUNT ^( ARGUMENT $i) ^( PROPERTY $p) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_COUNT.nextNode(), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:312:43: ^( ARGUMENT $i)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_2);

                        adaptor.addChild(root_2, stream_i.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:312:58: ^( PROPERTY $p)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTY, "PROPERTY"), root_2);

                        adaptor.addChild(root_2, stream_p.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "resultStatisticalFunction"

    public static class setOperation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "setOperation"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:316:1: setOperation : ( UNION setOperationParameters | INTERSECTION setOperationParameters | DIFFERENCE setOperationParameters );
    public final CQLParser.setOperation_return setOperation() throws RecognitionException {
        CQLParser.setOperation_return retval = new CQLParser.setOperation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token UNION69=null;
        Token INTERSECTION71=null;
        Token DIFFERENCE73=null;
        CQLParser.setOperationParameters_return setOperationParameters70 = null;

        CQLParser.setOperationParameters_return setOperationParameters72 = null;

        CQLParser.setOperationParameters_return setOperationParameters74 = null;


        Object UNION69_tree=null;
        Object INTERSECTION71_tree=null;
        Object DIFFERENCE73_tree=null;

         errorMessageStack.push("RESULT set operation"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:320:2: ( UNION setOperationParameters | INTERSECTION setOperationParameters | DIFFERENCE setOperationParameters )
            int alt17=3;
            switch ( input.LA(1) ) {
            case UNION:
                {
                alt17=1;
                }
                break;
            case INTERSECTION:
                {
                alt17=2;
                }
                break;
            case DIFFERENCE:
                {
                alt17=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:320:4: UNION setOperationParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    UNION69=(Token)match(input,UNION,FOLLOW_UNION_in_setOperation1442); 
                    UNION69_tree = (Object)adaptor.create(UNION69);
                    root_0 = (Object)adaptor.becomeRoot(UNION69_tree, root_0);

                    pushFollow(FOLLOW_setOperationParameters_in_setOperation1445);
                    setOperationParameters70=setOperationParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, setOperationParameters70.getTree());

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:321:4: INTERSECTION setOperationParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    INTERSECTION71=(Token)match(input,INTERSECTION,FOLLOW_INTERSECTION_in_setOperation1450); 
                    INTERSECTION71_tree = (Object)adaptor.create(INTERSECTION71);
                    root_0 = (Object)adaptor.becomeRoot(INTERSECTION71_tree, root_0);

                    pushFollow(FOLLOW_setOperationParameters_in_setOperation1453);
                    setOperationParameters72=setOperationParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, setOperationParameters72.getTree());

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:322:4: DIFFERENCE setOperationParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    DIFFERENCE73=(Token)match(input,DIFFERENCE,FOLLOW_DIFFERENCE_in_setOperation1458); 
                    DIFFERENCE73_tree = (Object)adaptor.create(DIFFERENCE73);
                    root_0 = (Object)adaptor.becomeRoot(DIFFERENCE73_tree, root_0);

                    pushFollow(FOLLOW_setOperationParameters_in_setOperation1461);
                    setOperationParameters74=setOperationParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, setOperationParameters74.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "setOperation"

    public static class setOperationParameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "setOperationParameters"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:325:1: setOperationParameters : '(' a= setOperationAttribute ',' b= setOperationAttribute ')' -> $a $b;
    public final CQLParser.setOperationParameters_return setOperationParameters() throws RecognitionException {
        CQLParser.setOperationParameters_return retval = new CQLParser.setOperationParameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal75=null;
        Token char_literal76=null;
        Token char_literal77=null;
        CQLParser.setOperationAttribute_return a = null;

        CQLParser.setOperationAttribute_return b = null;


        Object char_literal75_tree=null;
        Object char_literal76_tree=null;
        Object char_literal77_tree=null;
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
        RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
        RewriteRuleSubtreeStream stream_setOperationAttribute=new RewriteRuleSubtreeStream(adaptor,"rule setOperationAttribute");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:326:2: ( '(' a= setOperationAttribute ',' b= setOperationAttribute ')' -> $a $b)
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:326:4: '(' a= setOperationAttribute ',' b= setOperationAttribute ')'
            {
            char_literal75=(Token)match(input,114,FOLLOW_114_in_setOperationParameters1475);  
            stream_114.add(char_literal75);

            pushFollow(FOLLOW_setOperationAttribute_in_setOperationParameters1479);
            a=setOperationAttribute();

            state._fsp--;

            stream_setOperationAttribute.add(a.getTree());
            char_literal76=(Token)match(input,115,FOLLOW_115_in_setOperationParameters1481);  
            stream_115.add(char_literal76);

            pushFollow(FOLLOW_setOperationAttribute_in_setOperationParameters1485);
            b=setOperationAttribute();

            state._fsp--;

            stream_setOperationAttribute.add(b.getTree());
            char_literal77=(Token)match(input,116,FOLLOW_116_in_setOperationParameters1487);  
            stream_116.add(char_literal77);



            // AST REWRITE
            // elements: a, b
            // token labels: 
            // rule labels: retval, b, a
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);
            RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

            root_0 = (Object)adaptor.nil();
            // 327:3: -> $a $b
            {
                adaptor.addChild(root_0, stream_a.nextTree());
                adaptor.addChild(root_0, stream_b.nextTree());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "setOperationParameters"

    public static class setOperationAttribute_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "setOperationAttribute"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:330:1: setOperationAttribute : ( (i= ID | qs= QUOTED_STRING ) -> ^( ARGUMENT ( $i)? ( $qs)? ) | setOperation );
    public final CQLParser.setOperationAttribute_return setOperationAttribute() throws RecognitionException {
        CQLParser.setOperationAttribute_return retval = new CQLParser.setOperationAttribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token qs=null;
        CQLParser.setOperation_return setOperation78 = null;


        Object i_tree=null;
        Object qs_tree=null;
        RewriteRuleTokenStream stream_QUOTED_STRING=new RewriteRuleTokenStream(adaptor,"token QUOTED_STRING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:2: ( (i= ID | qs= QUOTED_STRING ) -> ^( ARGUMENT ( $i)? ( $qs)? ) | setOperation )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==ID||LA19_0==QUOTED_STRING) ) {
                alt19=1;
            }
            else if ( ((LA19_0>=UNION && LA19_0<=DIFFERENCE)) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:4: (i= ID | qs= QUOTED_STRING )
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:4: (i= ID | qs= QUOTED_STRING )
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ID) ) {
                        alt18=1;
                    }
                    else if ( (LA18_0==QUOTED_STRING) ) {
                        alt18=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 0, input);

                        throw nvae;
                    }
                    switch (alt18) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:5: i= ID
                            {
                            i=(Token)match(input,ID,FOLLOW_ID_in_setOperationAttribute1513);  
                            stream_ID.add(i);


                            }
                            break;
                        case 2 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:10: qs= QUOTED_STRING
                            {
                            qs=(Token)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_setOperationAttribute1517);  
                            stream_QUOTED_STRING.add(qs);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: qs, i
                    // token labels: qs, i
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_qs=new RewriteRuleTokenStream(adaptor,"token qs",qs);
                    RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 331:28: -> ^( ARGUMENT ( $i)? ( $qs)? )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:31: ^( ARGUMENT ( $i)? ( $qs)? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_1);

                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:42: ( $i)?
                        if ( stream_i.hasNext() ) {
                            adaptor.addChild(root_1, stream_i.nextNode());

                        }
                        stream_i.reset();
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:331:46: ( $qs)?
                        if ( stream_qs.hasNext() ) {
                            adaptor.addChild(root_1, stream_qs.nextNode());

                        }
                        stream_qs.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:332:4: setOperation
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_setOperation_in_setOperationAttribute1537);
                    setOperation78=setOperation();

                    state._fsp--;

                    adaptor.addChild(root_0, setOperation78.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "setOperationAttribute"

    public static class propertySelection_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "propertySelection"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:336:1: propertySelection : '[' f= propertyDefinition ( ',' rf+= propertyDefinition )* ']' -> ^( RESULT_PROPERTIES $f ( $rf)* ) ;
    public final CQLParser.propertySelection_return propertySelection() throws RecognitionException {
        CQLParser.propertySelection_return retval = new CQLParser.propertySelection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal79=null;
        Token char_literal80=null;
        Token char_literal81=null;
        List list_rf=null;
        CQLParser.propertyDefinition_return f = null;

        RuleReturnScope rf = null;
        Object char_literal79_tree=null;
        Object char_literal80_tree=null;
        Object char_literal81_tree=null;
        RewriteRuleTokenStream stream_117=new RewriteRuleTokenStream(adaptor,"token 117");
        RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
        RewriteRuleTokenStream stream_118=new RewriteRuleTokenStream(adaptor,"token 118");
        RewriteRuleSubtreeStream stream_propertyDefinition=new RewriteRuleSubtreeStream(adaptor,"rule propertyDefinition");
         errorMessageStack.push("RESULT properties definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:340:2: ( '[' f= propertyDefinition ( ',' rf+= propertyDefinition )* ']' -> ^( RESULT_PROPERTIES $f ( $rf)* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:340:4: '[' f= propertyDefinition ( ',' rf+= propertyDefinition )* ']'
            {
            char_literal79=(Token)match(input,117,FOLLOW_117_in_propertySelection1567);  
            stream_117.add(char_literal79);

            pushFollow(FOLLOW_propertyDefinition_in_propertySelection1571);
            f=propertyDefinition();

            state._fsp--;

            stream_propertyDefinition.add(f.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:340:29: ( ',' rf+= propertyDefinition )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==115) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:340:30: ',' rf+= propertyDefinition
            	    {
            	    char_literal80=(Token)match(input,115,FOLLOW_115_in_propertySelection1574);  
            	    stream_115.add(char_literal80);

            	    pushFollow(FOLLOW_propertyDefinition_in_propertySelection1578);
            	    rf=propertyDefinition();

            	    state._fsp--;

            	    stream_propertyDefinition.add(rf.getTree());
            	    if (list_rf==null) list_rf=new ArrayList();
            	    list_rf.add(rf.getTree());


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            char_literal81=(Token)match(input,118,FOLLOW_118_in_propertySelection1582);  
            stream_118.add(char_literal81);



            // AST REWRITE
            // elements: rf, f
            // token labels: 
            // rule labels: f, retval
            // token list labels: 
            // rule list labels: rf
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"rule f",f!=null?f.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_rf=new RewriteRuleSubtreeStream(adaptor,"token rf",list_rf);
            root_0 = (Object)adaptor.nil();
            // 341:3: -> ^( RESULT_PROPERTIES $f ( $rf)* )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:341:6: ^( RESULT_PROPERTIES $f ( $rf)* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RESULT_PROPERTIES, "RESULT_PROPERTIES"), root_1);

                adaptor.addChild(root_1, stream_f.nextTree());
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:341:29: ( $rf)*
                while ( stream_rf.hasNext() ) {
                    adaptor.addChild(root_1, stream_rf.nextTree());

                }
                stream_rf.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "propertySelection"

    public static class propertyDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "propertyDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:344:1: propertyDefinition : i= ID (l= labelDefinition )? -> ^( ARGUMENT $i ( $l)? ) ;
    public final CQLParser.propertyDefinition_return propertyDefinition() throws RecognitionException {
        CQLParser.propertyDefinition_return retval = new CQLParser.propertyDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        CQLParser.labelDefinition_return l = null;


        Object i_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_labelDefinition=new RewriteRuleSubtreeStream(adaptor,"rule labelDefinition");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:345:2: (i= ID (l= labelDefinition )? -> ^( ARGUMENT $i ( $l)? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:345:4: i= ID (l= labelDefinition )?
            {
            i=(Token)match(input,ID,FOLLOW_ID_in_propertyDefinition1612);  
            stream_ID.add(i);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:345:9: (l= labelDefinition )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==AS) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:345:10: l= labelDefinition
                    {
                    pushFollow(FOLLOW_labelDefinition_in_propertyDefinition1617);
                    l=labelDefinition();

                    state._fsp--;

                    stream_labelDefinition.add(l.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: i, l
            // token labels: i
            // rule labels: retval, l
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_l=new RewriteRuleSubtreeStream(adaptor,"rule l",l!=null?l.tree:null);

            root_0 = (Object)adaptor.nil();
            // 346:3: -> ^( ARGUMENT $i ( $l)? )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:346:6: ^( ARGUMENT $i ( $l)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_1);

                adaptor.addChild(root_1, stream_i.nextNode());
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:346:20: ( $l)?
                if ( stream_l.hasNext() ) {
                    adaptor.addChild(root_1, stream_l.nextTree());

                }
                stream_l.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "propertyDefinition"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:353:1: expression : logicalExpression ;
    public final CQLParser.expression_return expression() throws RecognitionException {
        CQLParser.expression_return retval = new CQLParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.logicalExpression_return logicalExpression82 = null;



        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:354:2: ( logicalExpression )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:354:4: logicalExpression
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_logicalExpression_in_expression1651);
            logicalExpression82=logicalExpression();

            state._fsp--;

            adaptor.addChild(root_0, logicalExpression82.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class logicalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "logicalExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:358:1: logicalExpression : orExpression ;
    public final CQLParser.logicalExpression_return logicalExpression() throws RecognitionException {
        CQLParser.logicalExpression_return retval = new CQLParser.logicalExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.orExpression_return orExpression83 = null;



        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:359:2: ( orExpression )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:359:4: orExpression
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_orExpression_in_logicalExpression1664);
            orExpression83=orExpression();

            state._fsp--;

            adaptor.addChild(root_0, orExpression83.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "logicalExpression"

    public static class orExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "orExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:362:1: orExpression : andExpression ( OR andExpression )* ;
    public final CQLParser.orExpression_return orExpression() throws RecognitionException {
        CQLParser.orExpression_return retval = new CQLParser.orExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OR85=null;
        CQLParser.andExpression_return andExpression84 = null;

        CQLParser.andExpression_return andExpression86 = null;


        Object OR85_tree=null;

         errorMessageStack.push("OR expression"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:366:2: ( andExpression ( OR andExpression )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:366:4: andExpression ( OR andExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpression_in_orExpression1693);
            andExpression84=andExpression();

            state._fsp--;

            adaptor.addChild(root_0, andExpression84.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:366:18: ( OR andExpression )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==OR) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:366:19: OR andExpression
            	    {
            	    OR85=(Token)match(input,OR,FOLLOW_OR_in_orExpression1696); 
            	    OR85_tree = (Object)adaptor.create(OR85);
            	    root_0 = (Object)adaptor.becomeRoot(OR85_tree, root_0);

            	    pushFollow(FOLLOW_andExpression_in_orExpression1699);
            	    andExpression86=andExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, andExpression86.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "orExpression"

    public static class andExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:369:1: andExpression : relationalExpression ( AND relationalExpression )* ;
    public final CQLParser.andExpression_return andExpression() throws RecognitionException {
        CQLParser.andExpression_return retval = new CQLParser.andExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AND88=null;
        CQLParser.relationalExpression_return relationalExpression87 = null;

        CQLParser.relationalExpression_return relationalExpression89 = null;


        Object AND88_tree=null;

         errorMessageStack.push("AND expression"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:373:2: ( relationalExpression ( AND relationalExpression )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:373:4: relationalExpression ( AND relationalExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relationalExpression_in_andExpression1729);
            relationalExpression87=relationalExpression();

            state._fsp--;

            adaptor.addChild(root_0, relationalExpression87.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:373:25: ( AND relationalExpression )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==AND) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:373:26: AND relationalExpression
            	    {
            	    AND88=(Token)match(input,AND,FOLLOW_AND_in_andExpression1732); 
            	    AND88_tree = (Object)adaptor.create(AND88);
            	    root_0 = (Object)adaptor.becomeRoot(AND88_tree, root_0);

            	    pushFollow(FOLLOW_relationalExpression_in_andExpression1735);
            	    relationalExpression89=relationalExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, relationalExpression89.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "andExpression"

    public static class relationalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relationalExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:377:1: relationalExpression : notExpression ( ( EQ | GE | LE | GT | LT ) notExpression )? ;
    public final CQLParser.relationalExpression_return relationalExpression() throws RecognitionException {
        CQLParser.relationalExpression_return retval = new CQLParser.relationalExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set91=null;
        CQLParser.notExpression_return notExpression90 = null;

        CQLParser.notExpression_return notExpression92 = null;


        Object set91_tree=null;

         errorMessageStack.push("Relational expression"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:381:2: ( notExpression ( ( EQ | GE | LE | GT | LT ) notExpression )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:381:4: notExpression ( ( EQ | GE | LE | GT | LT ) notExpression )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_notExpression_in_relationalExpression1767);
            notExpression90=notExpression();

            state._fsp--;

            adaptor.addChild(root_0, notExpression90.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:381:18: ( ( EQ | GE | LE | GT | LT ) notExpression )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=EQ && LA24_0<=LT)) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:381:19: ( EQ | GE | LE | GT | LT ) notExpression
                    {
                    set91=(Token)input.LT(1);
                    set91=(Token)input.LT(1);
                    if ( (input.LA(1)>=EQ && input.LA(1)<=LT) ) {
                        input.consume();
                        root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set91), root_0);
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_notExpression_in_relationalExpression1783);
                    notExpression92=notExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, notExpression92.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "relationalExpression"

    public static class notExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "notExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:384:1: notExpression : ( NOT )? arithmeticExpression ;
    public final CQLParser.notExpression_return notExpression() throws RecognitionException {
        CQLParser.notExpression_return retval = new CQLParser.notExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT93=null;
        CQLParser.arithmeticExpression_return arithmeticExpression94 = null;


        Object NOT93_tree=null;

         errorMessageStack.push("NOT expression"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:388:2: ( ( NOT )? arithmeticExpression )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:388:4: ( NOT )? arithmeticExpression
            {
            root_0 = (Object)adaptor.nil();

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:388:4: ( NOT )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NOT) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:388:5: NOT
                    {
                    NOT93=(Token)match(input,NOT,FOLLOW_NOT_in_notExpression1816); 
                    NOT93_tree = (Object)adaptor.create(NOT93);
                    root_0 = (Object)adaptor.becomeRoot(NOT93_tree, root_0);


                    }
                    break;

            }

            pushFollow(FOLLOW_arithmeticExpression_in_notExpression1821);
            arithmeticExpression94=arithmeticExpression();

            state._fsp--;

            adaptor.addChild(root_0, arithmeticExpression94.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "notExpression"

    public static class arithmeticExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arithmeticExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:392:1: arithmeticExpression : additiveExpression ;
    public final CQLParser.arithmeticExpression_return arithmeticExpression() throws RecognitionException {
        CQLParser.arithmeticExpression_return retval = new CQLParser.arithmeticExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.additiveExpression_return additiveExpression95 = null;



         errorMessageStack.push("Arithmetic expression"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:396:2: ( additiveExpression )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:396:4: additiveExpression
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_additiveExpression_in_arithmeticExpression1850);
            additiveExpression95=additiveExpression();

            state._fsp--;

            adaptor.addChild(root_0, additiveExpression95.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "arithmeticExpression"

    public static class additiveExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "additiveExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:399:1: additiveExpression : multiplicativeExpression ( ( PLUS | DIFF ) multiplicativeExpression )* ;
    public final CQLParser.additiveExpression_return additiveExpression() throws RecognitionException {
        CQLParser.additiveExpression_return retval = new CQLParser.additiveExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set97=null;
        CQLParser.multiplicativeExpression_return multiplicativeExpression96 = null;

        CQLParser.multiplicativeExpression_return multiplicativeExpression98 = null;


        Object set97_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:400:2: ( multiplicativeExpression ( ( PLUS | DIFF ) multiplicativeExpression )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:400:4: multiplicativeExpression ( ( PLUS | DIFF ) multiplicativeExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1862);
            multiplicativeExpression96=multiplicativeExpression();

            state._fsp--;

            adaptor.addChild(root_0, multiplicativeExpression96.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:400:29: ( ( PLUS | DIFF ) multiplicativeExpression )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=PLUS && LA26_0<=DIFF)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:400:30: ( PLUS | DIFF ) multiplicativeExpression
            	    {
            	    set97=(Token)input.LT(1);
            	    set97=(Token)input.LT(1);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=DIFF) ) {
            	        input.consume();
            	        root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set97), root_0);
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1872);
            	    multiplicativeExpression98=multiplicativeExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multiplicativeExpression98.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "additiveExpression"

    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multiplicativeExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:403:1: multiplicativeExpression : powerExpression ( ( MULT | DIV ) powerExpression )* ;
    public final CQLParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        CQLParser.multiplicativeExpression_return retval = new CQLParser.multiplicativeExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set100=null;
        CQLParser.powerExpression_return powerExpression99 = null;

        CQLParser.powerExpression_return powerExpression101 = null;


        Object set100_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:404:2: ( powerExpression ( ( MULT | DIV ) powerExpression )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:404:4: powerExpression ( ( MULT | DIV ) powerExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powerExpression_in_multiplicativeExpression1886);
            powerExpression99=powerExpression();

            state._fsp--;

            adaptor.addChild(root_0, powerExpression99.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:404:20: ( ( MULT | DIV ) powerExpression )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=MULT && LA27_0<=DIV)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:404:21: ( MULT | DIV ) powerExpression
            	    {
            	    set100=(Token)input.LT(1);
            	    set100=(Token)input.LT(1);
            	    if ( (input.LA(1)>=MULT && input.LA(1)<=DIV) ) {
            	        input.consume();
            	        root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set100), root_0);
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powerExpression_in_multiplicativeExpression1896);
            	    powerExpression101=powerExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, powerExpression101.getTree());

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "multiplicativeExpression"

    public static class powerExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "powerExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:407:1: powerExpression : minusExpression ( POWER minusExpression )* ;
    public final CQLParser.powerExpression_return powerExpression() throws RecognitionException {
        CQLParser.powerExpression_return retval = new CQLParser.powerExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token POWER103=null;
        CQLParser.minusExpression_return minusExpression102 = null;

        CQLParser.minusExpression_return minusExpression104 = null;


        Object POWER103_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:408:2: ( minusExpression ( POWER minusExpression )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:408:4: minusExpression ( POWER minusExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_minusExpression_in_powerExpression1911);
            minusExpression102=minusExpression();

            state._fsp--;

            adaptor.addChild(root_0, minusExpression102.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:408:20: ( POWER minusExpression )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==POWER) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:408:21: POWER minusExpression
            	    {
            	    POWER103=(Token)match(input,POWER,FOLLOW_POWER_in_powerExpression1914); 
            	    POWER103_tree = (Object)adaptor.create(POWER103);
            	    root_0 = (Object)adaptor.becomeRoot(POWER103_tree, root_0);

            	    pushFollow(FOLLOW_minusExpression_in_powerExpression1917);
            	    minusExpression104=minusExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, minusExpression104.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "powerExpression"

    public static class minusExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "minusExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:411:1: minusExpression : ( DIFF fe= functionExpression -> ^( MINUS $fe) | functionExpression );
    public final CQLParser.minusExpression_return minusExpression() throws RecognitionException {
        CQLParser.minusExpression_return retval = new CQLParser.minusExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DIFF105=null;
        CQLParser.functionExpression_return fe = null;

        CQLParser.functionExpression_return functionExpression106 = null;


        Object DIFF105_tree=null;
        RewriteRuleTokenStream stream_DIFF=new RewriteRuleTokenStream(adaptor,"token DIFF");
        RewriteRuleSubtreeStream stream_functionExpression=new RewriteRuleSubtreeStream(adaptor,"rule functionExpression");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:412:3: ( DIFF fe= functionExpression -> ^( MINUS $fe) | functionExpression )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==DIFF) ) {
                alt29=1;
            }
            else if ( (LA29_0==ID||LA29_0==INTEGER||LA29_0==QUOTED_STRING||(LA29_0>=AVG && LA29_0<=COUNT)||(LA29_0>=SQRT && LA29_0<=ABS)||(LA29_0>=RANGE && LA29_0<=NULL)||LA29_0==111||LA29_0==114||LA29_0==117) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:412:5: DIFF fe= functionExpression
                    {
                    DIFF105=(Token)match(input,DIFF,FOLLOW_DIFF_in_minusExpression1932);  
                    stream_DIFF.add(DIFF105);

                    pushFollow(FOLLOW_functionExpression_in_minusExpression1936);
                    fe=functionExpression();

                    state._fsp--;

                    stream_functionExpression.add(fe.getTree());


                    // AST REWRITE
                    // elements: fe
                    // token labels: 
                    // rule labels: retval, fe
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_fe=new RewriteRuleSubtreeStream(adaptor,"rule fe",fe!=null?fe.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 412:32: -> ^( MINUS $fe)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:412:35: ^( MINUS $fe)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MINUS, "MINUS"), root_1);

                        adaptor.addChild(root_1, stream_fe.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:413:5: functionExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_functionExpression_in_minusExpression1951);
                    functionExpression106=functionExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, functionExpression106.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "minusExpression"

    public static class functionExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionExpression"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:418:1: functionExpression : ( mathematicalFunction | statisticalFunction | rangeFunction | stringFunction | arrayDefinition );
    public final CQLParser.functionExpression_return functionExpression() throws RecognitionException {
        CQLParser.functionExpression_return retval = new CQLParser.functionExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.mathematicalFunction_return mathematicalFunction107 = null;

        CQLParser.statisticalFunction_return statisticalFunction108 = null;

        CQLParser.rangeFunction_return rangeFunction109 = null;

        CQLParser.stringFunction_return stringFunction110 = null;

        CQLParser.arrayDefinition_return arrayDefinition111 = null;



        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:419:2: ( mathematicalFunction | statisticalFunction | rangeFunction | stringFunction | arrayDefinition )
            int alt30=5;
            switch ( input.LA(1) ) {
            case SQRT:
            case LOG:
            case LN:
            case ABS:
                {
                alt30=1;
                }
                break;
            case AVG:
            case MEDIAN:
            case MIN:
            case MAX:
            case STDDEV:
            case VAR:
            case SUM:
            case COUNT:
                {
                alt30=2;
                }
                break;
            case RANGE:
                {
                alt30=3;
                }
                break;
            case ID:
            case INTEGER:
            case QUOTED_STRING:
            case REAL:
            case TRUE:
            case FALSE:
            case NULL:
            case 111:
            case 114:
                {
                alt30=4;
                }
                break;
            case 117:
                {
                alt30=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:419:4: mathematicalFunction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_mathematicalFunction_in_functionExpression1965);
                    mathematicalFunction107=mathematicalFunction();

                    state._fsp--;

                    adaptor.addChild(root_0, mathematicalFunction107.getTree());

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:420:4: statisticalFunction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_statisticalFunction_in_functionExpression1970);
                    statisticalFunction108=statisticalFunction();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunction108.getTree());

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:421:4: rangeFunction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_rangeFunction_in_functionExpression1975);
                    rangeFunction109=rangeFunction();

                    state._fsp--;

                    adaptor.addChild(root_0, rangeFunction109.getTree());

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:422:4: stringFunction
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_stringFunction_in_functionExpression1981);
                    stringFunction110=stringFunction();

                    state._fsp--;

                    adaptor.addChild(root_0, stringFunction110.getTree());

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:423:4: arrayDefinition
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arrayDefinition_in_functionExpression1987);
                    arrayDefinition111=arrayDefinition();

                    state._fsp--;

                    adaptor.addChild(root_0, arrayDefinition111.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "functionExpression"

    public static class mathematicalFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mathematicalFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:426:1: mathematicalFunction : ( SQRT | LOG | LN | ABS ) '(' arithmeticExpression ')' ;
    public final CQLParser.mathematicalFunction_return mathematicalFunction() throws RecognitionException {
        CQLParser.mathematicalFunction_return retval = new CQLParser.mathematicalFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set112=null;
        Token char_literal113=null;
        Token char_literal115=null;
        CQLParser.arithmeticExpression_return arithmeticExpression114 = null;


        Object set112_tree=null;
        Object char_literal113_tree=null;
        Object char_literal115_tree=null;

         errorMessageStack.push("Mathematical function definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:431:2: ( ( SQRT | LOG | LN | ABS ) '(' arithmeticExpression ')' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:431:4: ( SQRT | LOG | LN | ABS ) '(' arithmeticExpression ')'
            {
            root_0 = (Object)adaptor.nil();

            set112=(Token)input.LT(1);
            set112=(Token)input.LT(1);
            if ( (input.LA(1)>=SQRT && input.LA(1)<=ABS) ) {
                input.consume();
                root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set112), root_0);
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            char_literal113=(Token)match(input,114,FOLLOW_114_in_mathematicalFunction2031); 
            pushFollow(FOLLOW_arithmeticExpression_in_mathematicalFunction2034);
            arithmeticExpression114=arithmeticExpression();

            state._fsp--;

            adaptor.addChild(root_0, arithmeticExpression114.getTree());
            char_literal115=(Token)match(input,116,FOLLOW_116_in_mathematicalFunction2036); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "mathematicalFunction"

    public static class statisticalFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statisticalFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:434:1: statisticalFunction : ( AVG statisticalFunctionParameters | MEDIAN statisticalFunctionParameters | MIN statisticalFunctionParameters | MAX statisticalFunctionParameters | STDDEV statisticalFunctionParameters | VAR statisticalFunctionParameters | SUM statisticalFunctionParameters | COUNT statisticalFunctionParameters );
    public final CQLParser.statisticalFunction_return statisticalFunction() throws RecognitionException {
        CQLParser.statisticalFunction_return retval = new CQLParser.statisticalFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AVG116=null;
        Token MEDIAN118=null;
        Token MIN120=null;
        Token MAX122=null;
        Token STDDEV124=null;
        Token VAR126=null;
        Token SUM128=null;
        Token COUNT130=null;
        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters117 = null;

        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters119 = null;

        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters121 = null;

        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters123 = null;

        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters125 = null;

        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters127 = null;

        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters129 = null;

        CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters131 = null;


        Object AVG116_tree=null;
        Object MEDIAN118_tree=null;
        Object MIN120_tree=null;
        Object MAX122_tree=null;
        Object STDDEV124_tree=null;
        Object VAR126_tree=null;
        Object SUM128_tree=null;
        Object COUNT130_tree=null;

         errorMessageStack.push("Statistical function definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:438:2: ( AVG statisticalFunctionParameters | MEDIAN statisticalFunctionParameters | MIN statisticalFunctionParameters | MAX statisticalFunctionParameters | STDDEV statisticalFunctionParameters | VAR statisticalFunctionParameters | SUM statisticalFunctionParameters | COUNT statisticalFunctionParameters )
            int alt31=8;
            switch ( input.LA(1) ) {
            case AVG:
                {
                alt31=1;
                }
                break;
            case MEDIAN:
                {
                alt31=2;
                }
                break;
            case MIN:
                {
                alt31=3;
                }
                break;
            case MAX:
                {
                alt31=4;
                }
                break;
            case STDDEV:
                {
                alt31=5;
                }
                break;
            case VAR:
                {
                alt31=6;
                }
                break;
            case SUM:
                {
                alt31=7;
                }
                break;
            case COUNT:
                {
                alt31=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:438:4: AVG statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    AVG116=(Token)match(input,AVG,FOLLOW_AVG_in_statisticalFunction2065); 
                    AVG116_tree = (Object)adaptor.create(AVG116);
                    root_0 = (Object)adaptor.becomeRoot(AVG116_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2069);
                    statisticalFunctionParameters117=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters117.getTree());

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:439:4: MEDIAN statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    MEDIAN118=(Token)match(input,MEDIAN,FOLLOW_MEDIAN_in_statisticalFunction2074); 
                    MEDIAN118_tree = (Object)adaptor.create(MEDIAN118);
                    root_0 = (Object)adaptor.becomeRoot(MEDIAN118_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2077);
                    statisticalFunctionParameters119=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters119.getTree());

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:440:4: MIN statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    MIN120=(Token)match(input,MIN,FOLLOW_MIN_in_statisticalFunction2082); 
                    MIN120_tree = (Object)adaptor.create(MIN120);
                    root_0 = (Object)adaptor.becomeRoot(MIN120_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2085);
                    statisticalFunctionParameters121=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters121.getTree());

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:441:4: MAX statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    MAX122=(Token)match(input,MAX,FOLLOW_MAX_in_statisticalFunction2090); 
                    MAX122_tree = (Object)adaptor.create(MAX122);
                    root_0 = (Object)adaptor.becomeRoot(MAX122_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2093);
                    statisticalFunctionParameters123=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters123.getTree());

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:442:4: STDDEV statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    STDDEV124=(Token)match(input,STDDEV,FOLLOW_STDDEV_in_statisticalFunction2098); 
                    STDDEV124_tree = (Object)adaptor.create(STDDEV124);
                    root_0 = (Object)adaptor.becomeRoot(STDDEV124_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2101);
                    statisticalFunctionParameters125=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters125.getTree());

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:443:4: VAR statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    VAR126=(Token)match(input,VAR,FOLLOW_VAR_in_statisticalFunction2106); 
                    VAR126_tree = (Object)adaptor.create(VAR126);
                    root_0 = (Object)adaptor.becomeRoot(VAR126_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2109);
                    statisticalFunctionParameters127=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters127.getTree());

                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:444:4: SUM statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    SUM128=(Token)match(input,SUM,FOLLOW_SUM_in_statisticalFunction2114); 
                    SUM128_tree = (Object)adaptor.create(SUM128);
                    root_0 = (Object)adaptor.becomeRoot(SUM128_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2117);
                    statisticalFunctionParameters129=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters129.getTree());

                    }
                    break;
                case 8 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:445:4: COUNT statisticalFunctionParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    COUNT130=(Token)match(input,COUNT,FOLLOW_COUNT_in_statisticalFunction2122); 
                    COUNT130_tree = (Object)adaptor.create(COUNT130);
                    root_0 = (Object)adaptor.becomeRoot(COUNT130_tree, root_0);

                    pushFollow(FOLLOW_statisticalFunctionParameters_in_statisticalFunction2126);
                    statisticalFunctionParameters131=statisticalFunctionParameters();

                    state._fsp--;

                    adaptor.addChild(root_0, statisticalFunctionParameters131.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "statisticalFunction"

    public static class statisticalFunctionParameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statisticalFunctionParameters"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:448:1: statisticalFunctionParameters : '(' i= ID ( ',' ln= labelName )? ')' -> ^( ARGUMENT $i) ( $ln)? ;
    public final CQLParser.statisticalFunctionParameters_return statisticalFunctionParameters() throws RecognitionException {
        CQLParser.statisticalFunctionParameters_return retval = new CQLParser.statisticalFunctionParameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token char_literal132=null;
        Token char_literal133=null;
        Token char_literal134=null;
        CQLParser.labelName_return ln = null;


        Object i_tree=null;
        Object char_literal132_tree=null;
        Object char_literal133_tree=null;
        Object char_literal134_tree=null;
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
        RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_labelName=new RewriteRuleSubtreeStream(adaptor,"rule labelName");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:449:2: ( '(' i= ID ( ',' ln= labelName )? ')' -> ^( ARGUMENT $i) ( $ln)? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:449:4: '(' i= ID ( ',' ln= labelName )? ')'
            {
            char_literal132=(Token)match(input,114,FOLLOW_114_in_statisticalFunctionParameters2138);  
            stream_114.add(char_literal132);

            i=(Token)match(input,ID,FOLLOW_ID_in_statisticalFunctionParameters2142);  
            stream_ID.add(i);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:449:13: ( ',' ln= labelName )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==115) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:449:14: ',' ln= labelName
                    {
                    char_literal133=(Token)match(input,115,FOLLOW_115_in_statisticalFunctionParameters2145);  
                    stream_115.add(char_literal133);

                    pushFollow(FOLLOW_labelName_in_statisticalFunctionParameters2149);
                    ln=labelName();

                    state._fsp--;

                    stream_labelName.add(ln.getTree());

                    }
                    break;

            }

            char_literal134=(Token)match(input,116,FOLLOW_116_in_statisticalFunctionParameters2153);  
            stream_116.add(char_literal134);



            // AST REWRITE
            // elements: ln, i
            // token labels: i
            // rule labels: ln, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_ln=new RewriteRuleSubtreeStream(adaptor,"rule ln",ln!=null?ln.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 450:3: -> ^( ARGUMENT $i) ( $ln)?
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:450:6: ^( ARGUMENT $i)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT, "ARGUMENT"), root_1);

                adaptor.addChild(root_1, stream_i.nextNode());

                adaptor.addChild(root_0, root_1);
                }
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:450:21: ( $ln)?
                if ( stream_ln.hasNext() ) {
                    adaptor.addChild(root_0, stream_ln.nextTree());

                }
                stream_ln.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "statisticalFunctionParameters"

    public static class stringFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stringFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:454:1: stringFunction : containsFunction ;
    public final CQLParser.stringFunction_return stringFunction() throws RecognitionException {
        CQLParser.stringFunction_return retval = new CQLParser.stringFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.containsFunction_return containsFunction135 = null;



         errorMessageStack.push("String function definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:458:2: ( containsFunction )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:458:4: containsFunction
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_containsFunction_in_stringFunction2199);
            containsFunction135=containsFunction();

            state._fsp--;

            adaptor.addChild(root_0, containsFunction135.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "stringFunction"

    public static class containsFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "containsFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:463:1: containsFunction : e1= matchesFunction ( CONTAINS e2= matchesFunction )? ;
    public final CQLParser.containsFunction_return containsFunction() throws RecognitionException {
        CQLParser.containsFunction_return retval = new CQLParser.containsFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CONTAINS136=null;
        CQLParser.matchesFunction_return e1 = null;

        CQLParser.matchesFunction_return e2 = null;


        Object CONTAINS136_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:464:2: (e1= matchesFunction ( CONTAINS e2= matchesFunction )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:464:5: e1= matchesFunction ( CONTAINS e2= matchesFunction )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_matchesFunction_in_containsFunction2221);
            e1=matchesFunction();

            state._fsp--;

            adaptor.addChild(root_0, e1.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:464:24: ( CONTAINS e2= matchesFunction )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==CONTAINS) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:464:25: CONTAINS e2= matchesFunction
                    {
                    CONTAINS136=(Token)match(input,CONTAINS,FOLLOW_CONTAINS_in_containsFunction2224); 
                    CONTAINS136_tree = (Object)adaptor.create(CONTAINS136);
                    root_0 = (Object)adaptor.becomeRoot(CONTAINS136_tree, root_0);

                    pushFollow(FOLLOW_matchesFunction_in_containsFunction2229);
                    e2=matchesFunction();

                    state._fsp--;

                    adaptor.addChild(root_0, e2.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "containsFunction"

    public static class matchesFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "matchesFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:467:1: matchesFunction : e1= startsFunction ( MATCHES e2= startsFunction )? ;
    public final CQLParser.matchesFunction_return matchesFunction() throws RecognitionException {
        CQLParser.matchesFunction_return retval = new CQLParser.matchesFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MATCHES137=null;
        CQLParser.startsFunction_return e1 = null;

        CQLParser.startsFunction_return e2 = null;


        Object MATCHES137_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:468:2: (e1= startsFunction ( MATCHES e2= startsFunction )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:468:4: e1= startsFunction ( MATCHES e2= startsFunction )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_startsFunction_in_matchesFunction2248);
            e1=startsFunction();

            state._fsp--;

            adaptor.addChild(root_0, e1.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:468:22: ( MATCHES e2= startsFunction )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==MATCHES) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:468:23: MATCHES e2= startsFunction
                    {
                    MATCHES137=(Token)match(input,MATCHES,FOLLOW_MATCHES_in_matchesFunction2251); 
                    MATCHES137_tree = (Object)adaptor.create(MATCHES137);
                    root_0 = (Object)adaptor.becomeRoot(MATCHES137_tree, root_0);

                    pushFollow(FOLLOW_startsFunction_in_matchesFunction2256);
                    e2=startsFunction();

                    state._fsp--;

                    adaptor.addChild(root_0, e2.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "matchesFunction"

    public static class startsFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "startsFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:471:1: startsFunction : e1= concatFunction ( STARTSWITH e2= concatFunction )? ;
    public final CQLParser.startsFunction_return startsFunction() throws RecognitionException {
        CQLParser.startsFunction_return retval = new CQLParser.startsFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STARTSWITH138=null;
        CQLParser.concatFunction_return e1 = null;

        CQLParser.concatFunction_return e2 = null;


        Object STARTSWITH138_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:472:2: (e1= concatFunction ( STARTSWITH e2= concatFunction )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:472:4: e1= concatFunction ( STARTSWITH e2= concatFunction )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_concatFunction_in_startsFunction2272);
            e1=concatFunction();

            state._fsp--;

            adaptor.addChild(root_0, e1.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:472:22: ( STARTSWITH e2= concatFunction )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==STARTSWITH) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:472:23: STARTSWITH e2= concatFunction
                    {
                    STARTSWITH138=(Token)match(input,STARTSWITH,FOLLOW_STARTSWITH_in_startsFunction2275); 
                    STARTSWITH138_tree = (Object)adaptor.create(STARTSWITH138);
                    root_0 = (Object)adaptor.becomeRoot(STARTSWITH138_tree, root_0);

                    pushFollow(FOLLOW_concatFunction_in_startsFunction2280);
                    e2=concatFunction();

                    state._fsp--;

                    adaptor.addChild(root_0, e2.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "startsFunction"

    public static class concatFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "concatFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:475:1: concatFunction : e1= arrayOperation ( CONCAT e2= arrayOperation )? ;
    public final CQLParser.concatFunction_return concatFunction() throws RecognitionException {
        CQLParser.concatFunction_return retval = new CQLParser.concatFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CONCAT139=null;
        CQLParser.arrayOperation_return e1 = null;

        CQLParser.arrayOperation_return e2 = null;


        Object CONCAT139_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:476:2: (e1= arrayOperation ( CONCAT e2= arrayOperation )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:476:4: e1= arrayOperation ( CONCAT e2= arrayOperation )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_arrayOperation_in_concatFunction2304);
            e1=arrayOperation();

            state._fsp--;

            adaptor.addChild(root_0, e1.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:476:22: ( CONCAT e2= arrayOperation )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==CONCAT) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:476:23: CONCAT e2= arrayOperation
                    {
                    CONCAT139=(Token)match(input,CONCAT,FOLLOW_CONCAT_in_concatFunction2307); 
                    CONCAT139_tree = (Object)adaptor.create(CONCAT139);
                    root_0 = (Object)adaptor.becomeRoot(CONCAT139_tree, root_0);

                    pushFollow(FOLLOW_arrayOperation_in_concatFunction2312);
                    e2=arrayOperation();

                    state._fsp--;

                    adaptor.addChild(root_0, e2.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "concatFunction"

    public static class arrayDefinition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayDefinition"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:480:1: arrayDefinition : '[' (a= atom )? ( ',' ra+= atom )* ']' -> ^( ARRAY ( $a)? ( $ra)* ) ;
    public final CQLParser.arrayDefinition_return arrayDefinition() throws RecognitionException {
        CQLParser.arrayDefinition_return retval = new CQLParser.arrayDefinition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal140=null;
        Token char_literal141=null;
        Token char_literal142=null;
        List list_ra=null;
        CQLParser.atom_return a = null;

        RuleReturnScope ra = null;
        Object char_literal140_tree=null;
        Object char_literal141_tree=null;
        Object char_literal142_tree=null;
        RewriteRuleTokenStream stream_117=new RewriteRuleTokenStream(adaptor,"token 117");
        RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
        RewriteRuleTokenStream stream_118=new RewriteRuleTokenStream(adaptor,"token 118");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:2: ( '[' (a= atom )? ( ',' ra+= atom )* ']' -> ^( ARRAY ( $a)? ( $ra)* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:4: '[' (a= atom )? ( ',' ra+= atom )* ']'
            {
            char_literal140=(Token)match(input,117,FOLLOW_117_in_arrayDefinition2327);  
            stream_117.add(char_literal140);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:9: (a= atom )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==ID||LA37_0==INTEGER||LA37_0==QUOTED_STRING||(LA37_0>=REAL && LA37_0<=NULL)||LA37_0==111||LA37_0==114) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:9: a= atom
                    {
                    pushFollow(FOLLOW_atom_in_arrayDefinition2331);
                    a=atom();

                    state._fsp--;

                    stream_atom.add(a.getTree());

                    }
                    break;

            }

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:16: ( ',' ra+= atom )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==115) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:17: ',' ra+= atom
            	    {
            	    char_literal141=(Token)match(input,115,FOLLOW_115_in_arrayDefinition2335);  
            	    stream_115.add(char_literal141);

            	    pushFollow(FOLLOW_atom_in_arrayDefinition2339);
            	    ra=atom();

            	    state._fsp--;

            	    stream_atom.add(ra.getTree());
            	    if (list_ra==null) list_ra=new ArrayList();
            	    list_ra.add(ra.getTree());


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            char_literal142=(Token)match(input,118,FOLLOW_118_in_arrayDefinition2343);  
            stream_118.add(char_literal142);



            // AST REWRITE
            // elements: a, ra
            // token labels: 
            // rule labels: retval, a
            // token list labels: 
            // rule list labels: ra
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);
            RewriteRuleSubtreeStream stream_ra=new RewriteRuleSubtreeStream(adaptor,"token ra",list_ra);
            root_0 = (Object)adaptor.nil();
            // 481:36: -> ^( ARRAY ( $a)? ( $ra)* )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:39: ^( ARRAY ( $a)? ( $ra)* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARRAY, "ARRAY"), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:47: ( $a)?
                if ( stream_a.hasNext() ) {
                    adaptor.addChild(root_1, stream_a.nextTree());

                }
                stream_a.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:481:51: ( $ra)*
                while ( stream_ra.hasNext() ) {
                    adaptor.addChild(root_1, stream_ra.nextTree());

                }
                stream_ra.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "arrayDefinition"

    public static class arrayOperation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayOperation"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:484:1: arrayOperation : arrayAccess ;
    public final CQLParser.arrayOperation_return arrayOperation() throws RecognitionException {
        CQLParser.arrayOperation_return retval = new CQLParser.arrayOperation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CQLParser.arrayAccess_return arrayAccess143 = null;



         errorMessageStack.push("Array access"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:488:2: ( arrayAccess )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:488:4: arrayAccess
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_arrayAccess_in_arrayOperation2388);
            arrayAccess143=arrayAccess();

            state._fsp--;

            adaptor.addChild(root_0, arrayAccess143.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop();
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "arrayOperation"

    public static class arrayLength_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayLength"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:491:1: arrayLength : e= arrayAccess ( '.' LENGTH )? ;
    public final CQLParser.arrayLength_return arrayLength() throws RecognitionException {
        CQLParser.arrayLength_return retval = new CQLParser.arrayLength_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal144=null;
        Token LENGTH145=null;
        CQLParser.arrayAccess_return e = null;


        Object char_literal144_tree=null;
        Object LENGTH145_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:492:2: (e= arrayAccess ( '.' LENGTH )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:492:4: e= arrayAccess ( '.' LENGTH )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_arrayAccess_in_arrayLength2403);
            e=arrayAccess();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:492:18: ( '.' LENGTH )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==119) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:492:19: '.' LENGTH
                    {
                    char_literal144=(Token)match(input,119,FOLLOW_119_in_arrayLength2406); 
                    LENGTH145=(Token)match(input,LENGTH,FOLLOW_LENGTH_in_arrayLength2409); 
                    LENGTH145_tree = (Object)adaptor.create(LENGTH145);
                    root_0 = (Object)adaptor.becomeRoot(LENGTH145_tree, root_0);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "arrayLength"

    public static class arrayAccess_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayAccess"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:495:1: arrayAccess : e= atom ( '[' i= INTEGER ']' )? ;
    public final CQLParser.arrayAccess_return arrayAccess() throws RecognitionException {
        CQLParser.arrayAccess_return retval = new CQLParser.arrayAccess_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token char_literal146=null;
        Token char_literal147=null;
        CQLParser.atom_return e = null;


        Object i_tree=null;
        Object char_literal146_tree=null;
        Object char_literal147_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:496:2: (e= atom ( '[' i= INTEGER ']' )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:496:4: e= atom ( '[' i= INTEGER ']' )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_atom_in_arrayAccess2429);
            e=atom();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:496:11: ( '[' i= INTEGER ']' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==117) ) {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==INTEGER) ) {
                    int LA40_3 = input.LA(3);

                    if ( (LA40_3==118) ) {
                        alt40=1;
                    }
                }
            }
            switch (alt40) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:496:12: '[' i= INTEGER ']'
                    {
                    char_literal146=(Token)match(input,117,FOLLOW_117_in_arrayAccess2432); 
                    char_literal146_tree = (Object)adaptor.create(char_literal146);
                    root_0 = (Object)adaptor.becomeRoot(char_literal146_tree, root_0);

                    i=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_arrayAccess2437); 
                    i_tree = (Object)adaptor.create(i);
                    adaptor.addChild(root_0, i_tree);

                    char_literal147=(Token)match(input,118,FOLLOW_118_in_arrayAccess2439); 
                    char_literal147_tree = (Object)adaptor.create(char_literal147);
                    adaptor.addChild(root_0, char_literal147_tree);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "arrayAccess"

    public static class rangeFunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rangeFunction"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:500:1: rangeFunction : RANGE e= expression ( (openType= '[' ) | (openType= '(' ) ) rangeStart= expression ',' rangeEnd= expression (closeType= ')' | closeType= ']' ) -> ^( RANGE $e $openType $closeType $rangeStart $rangeEnd) ;
    public final CQLParser.rangeFunction_return rangeFunction() throws RecognitionException {
        CQLParser.rangeFunction_return retval = new CQLParser.rangeFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token openType=null;
        Token closeType=null;
        Token RANGE148=null;
        Token char_literal149=null;
        CQLParser.expression_return e = null;

        CQLParser.expression_return rangeStart = null;

        CQLParser.expression_return rangeEnd = null;


        Object openType_tree=null;
        Object closeType_tree=null;
        Object RANGE148_tree=null;
        Object char_literal149_tree=null;
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_117=new RewriteRuleTokenStream(adaptor,"token 117");
        RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
        RewriteRuleTokenStream stream_RANGE=new RewriteRuleTokenStream(adaptor,"token RANGE");
        RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
        RewriteRuleTokenStream stream_118=new RewriteRuleTokenStream(adaptor,"token 118");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
         errorMessageStack.push("RANGE definition"); 
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:2: ( RANGE e= expression ( (openType= '[' ) | (openType= '(' ) ) rangeStart= expression ',' rangeEnd= expression (closeType= ')' | closeType= ']' ) -> ^( RANGE $e $openType $closeType $rangeStart $rangeEnd) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:4: RANGE e= expression ( (openType= '[' ) | (openType= '(' ) ) rangeStart= expression ',' rangeEnd= expression (closeType= ')' | closeType= ']' )
            {
            RANGE148=(Token)match(input,RANGE,FOLLOW_RANGE_in_rangeFunction2480);  
            stream_RANGE.add(RANGE148);

            pushFollow(FOLLOW_expression_in_rangeFunction2484);
            e=expression();

            state._fsp--;

            stream_expression.add(e.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:23: ( (openType= '[' ) | (openType= '(' ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==117) ) {
                alt41=1;
            }
            else if ( (LA41_0==114) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:24: (openType= '[' )
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:24: (openType= '[' )
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:25: openType= '['
                    {
                    openType=(Token)match(input,117,FOLLOW_117_in_rangeFunction2490);  
                    stream_117.add(openType);


                    }


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:41: (openType= '(' )
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:41: (openType= '(' )
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:42: openType= '('
                    {
                    openType=(Token)match(input,114,FOLLOW_114_in_rangeFunction2498);  
                    stream_114.add(openType);


                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_expression_in_rangeFunction2504);
            rangeStart=expression();

            state._fsp--;

            stream_expression.add(rangeStart.getTree());
            char_literal149=(Token)match(input,115,FOLLOW_115_in_rangeFunction2506);  
            stream_115.add(char_literal149);

            pushFollow(FOLLOW_expression_in_rangeFunction2510);
            rangeEnd=expression();

            state._fsp--;

            stream_expression.add(rangeEnd.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:103: (closeType= ')' | closeType= ']' )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==116) ) {
                alt42=1;
            }
            else if ( (LA42_0==118) ) {
                alt42=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:104: closeType= ')'
                    {
                    closeType=(Token)match(input,116,FOLLOW_116_in_rangeFunction2515);  
                    stream_116.add(closeType);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:504:119: closeType= ']'
                    {
                    closeType=(Token)match(input,118,FOLLOW_118_in_rangeFunction2520);  
                    stream_118.add(closeType);


                    }
                    break;

            }



            // AST REWRITE
            // elements: openType, e, rangeEnd, rangeStart, closeType, RANGE
            // token labels: closeType, openType
            // rule labels: retval, e, rangeStart, rangeEnd
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_closeType=new RewriteRuleTokenStream(adaptor,"token closeType",closeType);
            RewriteRuleTokenStream stream_openType=new RewriteRuleTokenStream(adaptor,"token openType",openType);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);
            RewriteRuleSubtreeStream stream_rangeStart=new RewriteRuleSubtreeStream(adaptor,"rule rangeStart",rangeStart!=null?rangeStart.tree:null);
            RewriteRuleSubtreeStream stream_rangeEnd=new RewriteRuleSubtreeStream(adaptor,"rule rangeEnd",rangeEnd!=null?rangeEnd.tree:null);

            root_0 = (Object)adaptor.nil();
            // 505:3: -> ^( RANGE $e $openType $closeType $rangeStart $rangeEnd)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:505:6: ^( RANGE $e $openType $closeType $rangeStart $rangeEnd)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_RANGE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_e.nextTree());
                adaptor.addChild(root_1, stream_openType.nextNode());
                adaptor.addChild(root_1, stream_closeType.nextNode());
                adaptor.addChild(root_1, stream_rangeStart.nextTree());
                adaptor.addChild(root_1, stream_rangeEnd.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             errorMessageStack.pop(); 
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "rangeFunction"

    public static class atom_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:509:1: atom : (i= ID | INTEGER | REAL | QUOTED_STRING | TRUE | FALSE | NULL | '{' e= expression '}' RECOVERY '(' er= expression ')' -> ^( RECOVERY $er $e) | '(' e= expression ')' -> $e);
    public final CQLParser.atom_return atom() throws RecognitionException {
        CQLParser.atom_return retval = new CQLParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token INTEGER150=null;
        Token REAL151=null;
        Token QUOTED_STRING152=null;
        Token TRUE153=null;
        Token FALSE154=null;
        Token NULL155=null;
        Token char_literal156=null;
        Token char_literal157=null;
        Token RECOVERY158=null;
        Token char_literal159=null;
        Token char_literal160=null;
        Token char_literal161=null;
        Token char_literal162=null;
        CQLParser.expression_return e = null;

        CQLParser.expression_return er = null;


        Object i_tree=null;
        Object INTEGER150_tree=null;
        Object REAL151_tree=null;
        Object QUOTED_STRING152_tree=null;
        Object TRUE153_tree=null;
        Object FALSE154_tree=null;
        Object NULL155_tree=null;
        Object char_literal156_tree=null;
        Object char_literal157_tree=null;
        Object RECOVERY158_tree=null;
        Object char_literal159_tree=null;
        Object char_literal160_tree=null;
        Object char_literal161_tree=null;
        Object char_literal162_tree=null;
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_RECOVERY=new RewriteRuleTokenStream(adaptor,"token RECOVERY");
        RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
        RewriteRuleTokenStream stream_112=new RewriteRuleTokenStream(adaptor,"token 112");
        RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:510:2: (i= ID | INTEGER | REAL | QUOTED_STRING | TRUE | FALSE | NULL | '{' e= expression '}' RECOVERY '(' er= expression ')' -> ^( RECOVERY $er $e) | '(' e= expression ')' -> $e)
            int alt43=9;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt43=1;
                }
                break;
            case INTEGER:
                {
                alt43=2;
                }
                break;
            case REAL:
                {
                alt43=3;
                }
                break;
            case QUOTED_STRING:
                {
                alt43=4;
                }
                break;
            case TRUE:
                {
                alt43=5;
                }
                break;
            case FALSE:
                {
                alt43=6;
                }
                break;
            case NULL:
                {
                alt43=7;
                }
                break;
            case 111:
                {
                alt43=8;
                }
                break;
            case 114:
                {
                alt43=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:510:5: i= ID
                    {
                    root_0 = (Object)adaptor.nil();

                    i=(Token)match(input,ID,FOLLOW_ID_in_atom2562); 
                    i_tree = (Object)adaptor.create(i);
                    adaptor.addChild(root_0, i_tree);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:511:5: INTEGER
                    {
                    root_0 = (Object)adaptor.nil();

                    INTEGER150=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_atom2568); 
                    INTEGER150_tree = (Object)adaptor.create(INTEGER150);
                    adaptor.addChild(root_0, INTEGER150_tree);


                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:512:5: REAL
                    {
                    root_0 = (Object)adaptor.nil();

                    REAL151=(Token)match(input,REAL,FOLLOW_REAL_in_atom2574); 
                    REAL151_tree = (Object)adaptor.create(REAL151);
                    adaptor.addChild(root_0, REAL151_tree);


                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:513:5: QUOTED_STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    QUOTED_STRING152=(Token)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_atom2580); 
                    QUOTED_STRING152_tree = (Object)adaptor.create(QUOTED_STRING152);
                    adaptor.addChild(root_0, QUOTED_STRING152_tree);


                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:514:5: TRUE
                    {
                    root_0 = (Object)adaptor.nil();

                    TRUE153=(Token)match(input,TRUE,FOLLOW_TRUE_in_atom2586); 
                    TRUE153_tree = (Object)adaptor.create(TRUE153);
                    adaptor.addChild(root_0, TRUE153_tree);


                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:515:5: FALSE
                    {
                    root_0 = (Object)adaptor.nil();

                    FALSE154=(Token)match(input,FALSE,FOLLOW_FALSE_in_atom2592); 
                    FALSE154_tree = (Object)adaptor.create(FALSE154);
                    adaptor.addChild(root_0, FALSE154_tree);


                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:516:4: NULL
                    {
                    root_0 = (Object)adaptor.nil();

                    NULL155=(Token)match(input,NULL,FOLLOW_NULL_in_atom2600); 
                    NULL155_tree = (Object)adaptor.create(NULL155);
                    adaptor.addChild(root_0, NULL155_tree);


                    }
                    break;
                case 8 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:517:4: '{' e= expression '}' RECOVERY '(' er= expression ')'
                    {
                    char_literal156=(Token)match(input,111,FOLLOW_111_in_atom2608);  
                    stream_111.add(char_literal156);

                    pushFollow(FOLLOW_expression_in_atom2612);
                    e=expression();

                    state._fsp--;

                    stream_expression.add(e.getTree());
                    char_literal157=(Token)match(input,112,FOLLOW_112_in_atom2614);  
                    stream_112.add(char_literal157);

                    RECOVERY158=(Token)match(input,RECOVERY,FOLLOW_RECOVERY_in_atom2616);  
                    stream_RECOVERY.add(RECOVERY158);

                    char_literal159=(Token)match(input,114,FOLLOW_114_in_atom2618);  
                    stream_114.add(char_literal159);

                    pushFollow(FOLLOW_expression_in_atom2622);
                    er=expression();

                    state._fsp--;

                    stream_expression.add(er.getTree());
                    char_literal160=(Token)match(input,116,FOLLOW_116_in_atom2624);  
                    stream_116.add(char_literal160);



                    // AST REWRITE
                    // elements: RECOVERY, er, e
                    // token labels: 
                    // rule labels: retval, e, er
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);
                    RewriteRuleSubtreeStream stream_er=new RewriteRuleSubtreeStream(adaptor,"rule er",er!=null?er.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 517:57: -> ^( RECOVERY $er $e)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:517:60: ^( RECOVERY $er $e)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_RECOVERY.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_er.nextTree());
                        adaptor.addChild(root_1, stream_e.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:518:4: '(' e= expression ')'
                    {
                    char_literal161=(Token)match(input,114,FOLLOW_114_in_atom2642);  
                    stream_114.add(char_literal161);

                    pushFollow(FOLLOW_expression_in_atom2646);
                    e=expression();

                    state._fsp--;

                    stream_expression.add(e.getTree());
                    char_literal162=(Token)match(input,116,FOLLOW_116_in_atom2648);  
                    stream_116.add(char_literal162);



                    // AST REWRITE
                    // elements: e
                    // token labels: 
                    // rule labels: retval, e
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 518:25: -> $e
                    {
                        adaptor.addChild(root_0, stream_e.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_selectionBlock_in_cqlScript227 = new BitSet(new long[]{0x0000002000200002L});
    public static final BitSet FOLLOW_resultBlock_in_cqlScript232 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_SELECT_in_selectionBlock293 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_FROM_in_selectionBlock295 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_selectionBlock297 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_labelDefinition_in_selectionBlock301 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_selectionBlockDefinition_in_selectionBlock305 = new BitSet(new long[]{0x0000000044000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_selectionBlockOptions_in_selectionBlock309 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_selectionBlock311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARS_in_selectionBlockDefinition363 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_111_in_selectionBlockDefinition365 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_variableDefinition_in_selectionBlockDefinition369 = new BitSet(new long[]{0x0000000000800000L,0x0001000000000000L});
    public static final BitSet FOLLOW_variableDefinition_in_selectionBlockDefinition375 = new BitSet(new long[]{0x0000000000800000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_selectionBlockDefinition379 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_WHERE_in_selectionBlockDefinition383 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_condition_in_selectionBlockDefinition387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_variableDefinition427 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_113_in_variableDefinition429 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_arithmeticExpression_in_variableDefinition433 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_variableDefinition435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logicalExpression_in_condition477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectionBlockOptionsDefinition_in_selectionBlockOptions496 = new BitSet(new long[]{0x0000000044000002L});
    public static final BitSet FOLLOW_sortOptions_in_selectionBlockOptionsDefinition524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_limitOptions_in_selectionBlockOptionsDefinition530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SORT_in_sortOptions559 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_BY_in_sortOptions561 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_sortOptions565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SORT_in_sortOptions584 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_BY_in_sortOptions586 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_sortOptions590 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_DESCENDING_in_sortOptions593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESC_in_sortOptions595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIMIT_in_limitOptions637 = new BitSet(new long[]{0x0000000380000000L});
    public static final BitSet FOLLOW_limitDefinition_in_limitOptions641 = new BitSet(new long[]{0x0000000380000002L});
    public static final BitSet FOLLOW_TOP_in_limitDefinition666 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_limitParameters_in_limitDefinition670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOTTOM_in_limitDefinition686 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_limitParameters_in_limitDefinition690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANDOM_in_limitDefinition704 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_limitParameters_in_limitDefinition708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_limitParameters733 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_INTEGER_in_limitParameters736 = new BitSet(new long[]{0x0000000000000000L,0x0018000000000000L});
    public static final BitSet FOLLOW_115_in_limitParameters739 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_INTEGER_in_limitParameters742 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_limitParameters746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AS_in_labelDefinition761 = new BitSet(new long[]{0x0000001000800000L});
    public static final BitSet FOLLOW_labelName_in_labelDefinition764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_labelName782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_labelName786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RESULT_in_resultBlock838 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_111_in_resultBlock840 = new BitSet(new long[]{0x0003FFD000800000L});
    public static final BitSet FOLLOW_resultDefinition_in_resultBlock844 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_resultBlock846 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_resultBlock848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resultExpression_in_resultDefinition891 = new BitSet(new long[]{0x0000000800000002L,0x0020000000000000L});
    public static final BitSet FOLLOW_propertySelection_in_resultDefinition896 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_labelDefinition_in_resultDefinition903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_resultExpression938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_resultExpression960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_setOperation_in_resultExpression979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resultStatisticalFunction_in_resultExpression999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DISTINCT_in_resultExpression1015 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultExpression1019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DISTINCT_in_resultExpression1052 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_resultExpression1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVG_in_resultStatisticalFunction1094 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1097 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1101 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1103 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1107 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIAN_in_resultStatisticalFunction1135 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1137 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1141 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1143 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1147 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIN_in_resultStatisticalFunction1175 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1178 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1182 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1184 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1188 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAX_in_resultStatisticalFunction1216 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1219 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1223 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1225 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1229 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STDDEV_in_resultStatisticalFunction1257 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1259 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1263 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1265 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1269 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_resultStatisticalFunction1297 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1300 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1304 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1306 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1310 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUM_in_resultStatisticalFunction1338 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1341 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1345 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1347 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1351 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_resultStatisticalFunction1379 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_resultStatisticalFunction1381 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1385 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_resultStatisticalFunction1387 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_resultStatisticalFunction1391 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_resultStatisticalFunction1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNION_in_setOperation1442 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_setOperationParameters_in_setOperation1445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERSECTION_in_setOperation1450 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_setOperationParameters_in_setOperation1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIFFERENCE_in_setOperation1458 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_setOperationParameters_in_setOperation1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_setOperationParameters1475 = new BitSet(new long[]{0x0003801000800000L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperationParameters1479 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_setOperationParameters1481 = new BitSet(new long[]{0x0003801000800000L});
    public static final BitSet FOLLOW_setOperationAttribute_in_setOperationParameters1485 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_setOperationParameters1487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_setOperationAttribute1513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_setOperationAttribute1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_setOperation_in_setOperationAttribute1537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_propertySelection1567 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_propertyDefinition_in_propertySelection1571 = new BitSet(new long[]{0x0000000000000000L,0x0048000000000000L});
    public static final BitSet FOLLOW_115_in_propertySelection1574 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_propertyDefinition_in_propertySelection1578 = new BitSet(new long[]{0x0000000000000000L,0x0048000000000000L});
    public static final BitSet FOLLOW_118_in_propertySelection1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_propertyDefinition1612 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_labelDefinition_in_propertyDefinition1617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logicalExpression_in_expression1651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_logicalExpression1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpression_in_orExpression1693 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_OR_in_orExpression1696 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_andExpression_in_orExpression1699 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_relationalExpression_in_andExpression1729 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_AND_in_andExpression1732 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_relationalExpression_in_andExpression1735 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_notExpression_in_relationalExpression1767 = new BitSet(new long[]{0x01F0000000000002L});
    public static final BitSet FOLLOW_set_in_relationalExpression1770 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_notExpression_in_relationalExpression1783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_notExpression1816 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_arithmeticExpression_in_notExpression1821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_arithmeticExpression1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1862 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_set_in_additiveExpression1865 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1872 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_powerExpression_in_multiplicativeExpression1886 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression1889 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_powerExpression_in_multiplicativeExpression1896 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_minusExpression_in_powerExpression1911 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_POWER_in_powerExpression1914 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_minusExpression_in_powerExpression1917 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_DIFF_in_minusExpression1932 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_functionExpression_in_minusExpression1936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionExpression_in_minusExpression1951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mathematicalFunction_in_functionExpression1965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statisticalFunction_in_functionExpression1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rangeFunction_in_functionExpression1975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_functionExpression1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayDefinition_in_functionExpression1987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_mathematicalFunction2020 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_mathematicalFunction2031 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_arithmeticExpression_in_mathematicalFunction2034 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_mathematicalFunction2036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVG_in_statisticalFunction2065 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIAN_in_statisticalFunction2074 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIN_in_statisticalFunction2082 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAX_in_statisticalFunction2090 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STDDEV_in_statisticalFunction2098 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_statisticalFunction2106 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUM_in_statisticalFunction2114 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_statisticalFunction2122 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_statisticalFunctionParameters_in_statisticalFunction2126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_statisticalFunctionParameters2138 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_statisticalFunctionParameters2142 = new BitSet(new long[]{0x0000000000000000L,0x0018000000000000L});
    public static final BitSet FOLLOW_115_in_statisticalFunctionParameters2145 = new BitSet(new long[]{0x0000001000800000L});
    public static final BitSet FOLLOW_labelName_in_statisticalFunctionParameters2149 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_statisticalFunctionParameters2153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containsFunction_in_stringFunction2199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_matchesFunction_in_containsFunction2221 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_CONTAINS_in_containsFunction2224 = new BitSet(new long[]{0x0000001400800000L,0x0004800000001E00L});
    public static final BitSet FOLLOW_matchesFunction_in_containsFunction2229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_startsFunction_in_matchesFunction2248 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_MATCHES_in_matchesFunction2251 = new BitSet(new long[]{0x0000001400800000L,0x0004800000001E00L});
    public static final BitSet FOLLOW_startsFunction_in_matchesFunction2256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_concatFunction_in_startsFunction2272 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_STARTSWITH_in_startsFunction2275 = new BitSet(new long[]{0x0000001400800000L,0x0004800000001E00L});
    public static final BitSet FOLLOW_concatFunction_in_startsFunction2280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayOperation_in_concatFunction2304 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_CONCAT_in_concatFunction2307 = new BitSet(new long[]{0x0000001400800000L,0x0004800000001E00L});
    public static final BitSet FOLLOW_arrayOperation_in_concatFunction2312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_arrayDefinition2327 = new BitSet(new long[]{0x0000001400800000L,0x004C800000001E00L});
    public static final BitSet FOLLOW_atom_in_arrayDefinition2331 = new BitSet(new long[]{0x0000000000000000L,0x0048000000000000L});
    public static final BitSet FOLLOW_115_in_arrayDefinition2335 = new BitSet(new long[]{0x0000001400800000L,0x0004800000001E00L});
    public static final BitSet FOLLOW_atom_in_arrayDefinition2339 = new BitSet(new long[]{0x0000000000000000L,0x0048000000000000L});
    public static final BitSet FOLLOW_118_in_arrayDefinition2343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayAccess_in_arrayOperation2388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayAccess_in_arrayLength2403 = new BitSet(new long[]{0x0000000000000002L,0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_arrayLength2406 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_LENGTH_in_arrayLength2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_arrayAccess2429 = new BitSet(new long[]{0x0000000000000002L,0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_arrayAccess2432 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_INTEGER_in_arrayAccess2437 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_118_in_arrayAccess2439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANGE_in_rangeFunction2480 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_expression_in_rangeFunction2484 = new BitSet(new long[]{0x0000000000000000L,0x0024000000000000L});
    public static final BitSet FOLLOW_117_in_rangeFunction2490 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_114_in_rangeFunction2498 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_expression_in_rangeFunction2504 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_115_in_rangeFunction2506 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_expression_in_rangeFunction2510 = new BitSet(new long[]{0x0000000000000000L,0x0050000000000000L});
    public static final BitSet FOLLOW_116_in_rangeFunction2515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_118_in_rangeFunction2520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_atom2568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_atom2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_atom2580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_atom2586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_atom2592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_atom2600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_atom2608 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_expression_in_atom2612 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_atom2614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RECOVERY_in_atom2616 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_atom2618 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_expression_in_atom2622 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_atom2624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_atom2642 = new BitSet(new long[]{0x8A007F9400800000L,0x0024800000001F07L});
    public static final BitSet FOLLOW_expression_in_atom2646 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_atom2648 = new BitSet(new long[]{0x0000000000000002L});

}