// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g 2011-10-20 14:12:44

	package net.explorercat.compactcat.parser.antlr;	
	
	import net.explorercat.compactcat.definitions.*;
	import net.explorercat.compactcat.translators.*;	
	import net.explorercat.cql.types.*;
	import net.explorercat.cql.types.DataValue;
	import net.explorercat.util.exceptions.TranslationException;
	
	import org.apache.commons.logging.Log;
	import org.apache.commons.logging.LogFactory;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

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
public class CompactCatDataParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CATALOG", "ID", "INTEGER", "STRING", "BOOLEAN", "REAL", "NULL", "DATE_YEAR_MONTH_DAY", "C", "A", "T", "L", "O", "G", "R", "U", "E", "F", "S", "N", "NUMBER", "EXPONENT", "LINE_COMMENT", "BLANK", "B", "D", "H", "I", "J", "K", "M", "P", "Q", "V", "W", "X", "Y", "Z", "'{'", "','", "'}'", "';'", "':'", "'['", "']'"
    };
    public static final int EOF=-1;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int CATALOG=4;
    public static final int ID=5;
    public static final int INTEGER=6;
    public static final int STRING=7;
    public static final int BOOLEAN=8;
    public static final int REAL=9;
    public static final int NULL=10;
    public static final int DATE_YEAR_MONTH_DAY=11;
    public static final int C=12;
    public static final int A=13;
    public static final int T=14;
    public static final int L=15;
    public static final int O=16;
    public static final int G=17;
    public static final int R=18;
    public static final int U=19;
    public static final int E=20;
    public static final int F=21;
    public static final int S=22;
    public static final int N=23;
    public static final int NUMBER=24;
    public static final int EXPONENT=25;
    public static final int LINE_COMMENT=26;
    public static final int BLANK=27;
    public static final int B=28;
    public static final int D=29;
    public static final int H=30;
    public static final int I=31;
    public static final int J=32;
    public static final int K=33;
    public static final int M=34;
    public static final int P=35;
    public static final int Q=36;
    public static final int V=37;
    public static final int W=38;
    public static final int X=39;
    public static final int Y=40;
    public static final int Z=41;

    // delegates
    // delegators


        public CompactCatDataParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CompactCatDataParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CompactCatDataParser.tokenNames; }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g"; }

    	
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



    // $ANTLR start "catalog"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:114:1: catalog : CATALOG '{' entityData ( ',' entityData )* '}' ( ';' )? ;
    public final void catalog() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:115:3: ( CATALOG '{' entityData ( ',' entityData )* '}' ( ';' )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:115:6: CATALOG '{' entityData ( ',' entityData )* '}' ( ';' )?
            {
            match(input,CATALOG,FOLLOW_CATALOG_in_catalog78); 
            match(input,42,FOLLOW_42_in_catalog80); 
             
             			this.parseTranslationParameters();
             			this.catalogTranslator.translateCatalog(this.catalogDefinition, this.translationParameters);
             		
            pushFollow(FOLLOW_entityData_in_catalog100);
            entityData();

            state._fsp--;

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:122:15: ( ',' entityData )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==43) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:122:16: ',' entityData
            	    {
            	    match(input,43,FOLLOW_43_in_catalog103); 
            	    pushFollow(FOLLOW_entityData_in_catalog105);
            	    entityData();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match(input,44,FOLLOW_44_in_catalog109); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:122:37: ( ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==45) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:122:37: ';'
                    {
                    match(input,45,FOLLOW_45_in_catalog111); 

                    }
                    break;

            }


            }

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return ;
    }
    // $ANTLR end "catalog"

    protected static class entityData_scope {
        EntityDefinition entityDefinition;
        EntityTranslator entityTranslator;
        List<List<DataValue>> dataRows;
    }
    protected Stack entityData_stack = new Stack();


    // $ANTLR start "entityData"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:127:1: entityData : i= ID ':' '[' rv= rowValues ( ',' rrv= rowValues )* ']' ;
    public final void entityData() throws RecognitionException {
        entityData_stack.push(new entityData_scope());
        Token i=null;
        List<DataValue> rv = null;

        List<DataValue> rrv = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:135:2: (i= ID ':' '[' rv= rowValues ( ',' rrv= rowValues )* ']' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:135:5: i= ID ':' '[' rv= rowValues ( ',' rrv= rowValues )* ']'
            {
            i=(Token)match(input,ID,FOLLOW_ID_in_entityData144); 
            match(input,46,FOLLOW_46_in_entityData146); 

            			((entityData_scope)entityData_stack.peek()).entityDefinition = this.catalogDefinition.getEntityDefinition(i.getText());
            			((entityData_scope)entityData_stack.peek()).dataRows = new ArrayList<List<DataValue>>(this.consecutiveRowsToRead);
            			((entityData_scope)entityData_stack.peek()).entityTranslator = this.catalogTranslator.getEntityTranslator();	
            			((entityData_scope)entityData_stack.peek()).entityTranslator.translateEntity(((entityData_scope)entityData_stack.peek()).entityDefinition, this.translationParameters);
            		
            match(input,47,FOLLOW_47_in_entityData158); 
            pushFollow(FOLLOW_rowValues_in_entityData162);
            rv=rowValues();

            state._fsp--;


            				((entityData_scope)entityData_stack.peek()).dataRows.add(rv);
            				
            				if(log.isDebugEnabled())
            	    			log.debug("Inserting data instances for " + ((entityData_scope)entityData_stack.peek()).entityDefinition.getName());					
            			
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:151:3: ( ',' rrv= rowValues )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==43) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:151:4: ',' rrv= rowValues
            	    {
            	    match(input,43,FOLLOW_43_in_entityData177); 
            	    pushFollow(FOLLOW_rowValues_in_entityData181);
            	    rrv=rowValues();

            	    state._fsp--;


            	    				((entityData_scope)entityData_stack.peek()).dataRows.add(rrv);
            	    				
            	    				if(((entityData_scope)entityData_stack.peek()).dataRows.size() >= this.consecutiveRowsToRead)
            	    				{
            	    					((entityData_scope)entityData_stack.peek()).entityTranslator.addDataRowsToEntityTranslation(((entityData_scope)entityData_stack.peek()).dataRows, ((entityData_scope)entityData_stack.peek()).entityDefinition);
            	    					((entityData_scope)entityData_stack.peek()).dataRows.clear();				
            	    				}			
            	    			

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_entityData196); 

            			((entityData_scope)entityData_stack.peek()).entityTranslator.addDataRowsToEntityTranslation(((entityData_scope)entityData_stack.peek()).dataRows, ((entityData_scope)entityData_stack.peek()).entityDefinition);	
            			((entityData_scope)entityData_stack.peek()).entityTranslator.finishTranslation(((entityData_scope)entityData_stack.peek()).entityDefinition, this.translationParameters);			
            		

            }

        }
        catch (TranslationException e) {
             e.printStackTrace(); 
        }
        finally {
            entityData_stack.pop();
        }
        return ;
    }
    // $ANTLR end "entityData"

    protected static class rowValues_scope {
        List<DataValue> dataValues;
    }
    protected Stack rowValues_stack = new Stack();


    // $ANTLR start "rowValues"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:172:1: rowValues returns [List<DataValue> rowDataValues] : '[' av= attributeValue ( ',' rav= attributeValue )* ']' ;
    public final List<DataValue> rowValues() throws RecognitionException {
        rowValues_stack.push(new rowValues_scope());
        List<DataValue> rowDataValues = null;

        DataValue av = null;

        DataValue rav = null;



        		((rowValues_scope)rowValues_stack.peek()).dataValues = new ArrayList<DataValue>();
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:181:2: ( '[' av= attributeValue ( ',' rav= attributeValue )* ']' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:181:4: '[' av= attributeValue ( ',' rav= attributeValue )* ']'
            {
            match(input,47,FOLLOW_47_in_rowValues250); 
            pushFollow(FOLLOW_attributeValue_in_rowValues254);
            av=attributeValue();

            state._fsp--;

             ((rowValues_scope)rowValues_stack.peek()).dataValues.add(av); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:182:4: ( ',' rav= attributeValue )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==43) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:182:5: ',' rav= attributeValue
            	    {
            	    match(input,43,FOLLOW_43_in_rowValues269); 
            	    pushFollow(FOLLOW_attributeValue_in_rowValues273);
            	    rav=attributeValue();

            	    state._fsp--;

            	     ((rowValues_scope)rowValues_stack.peek()).dataValues.add(rav);

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_rowValues280); 

            			rowDataValues = ((rowValues_scope)rowValues_stack.peek()).dataValues;
            		

            }

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
            rowValues_stack.pop();
        }
        return rowDataValues;
    }
    // $ANTLR end "rowValues"


    // $ANTLR start "attributeValue"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:189:1: attributeValue returns [DataValue value] : (bv= basicValue | '[' (bv= basicValue )? ( ',' rbv= basicValue )* ']' );
    public final DataValue attributeValue() throws RecognitionException {
        DataValue value = null;

        DataValue bv = null;

        DataValue rbv = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:191:2: (bv= basicValue | '[' (bv= basicValue )? ( ',' rbv= basicValue )* ']' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=INTEGER && LA7_0<=DATE_YEAR_MONTH_DAY)) ) {
                alt7=1;
            }
            else if ( (LA7_0==47) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:191:4: bv= basicValue
                    {
                    pushFollow(FOLLOW_basicValue_in_attributeValue310);
                    bv=basicValue();

                    state._fsp--;

                     value = bv; 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:193:4: '[' (bv= basicValue )? ( ',' rbv= basicValue )* ']'
                    {
                    match(input,47,FOLLOW_47_in_attributeValue319); 
                     List<DataValue> arrayValues = new ArrayList<DataValue>(); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:195:5: (bv= basicValue )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( ((LA5_0>=INTEGER && LA5_0<=DATE_YEAR_MONTH_DAY)) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:195:5: bv= basicValue
                            {
                            pushFollow(FOLLOW_basicValue_in_attributeValue331);
                            bv=basicValue();

                            state._fsp--;


                            }
                            break;

                    }

                     arrayValues.add(bv); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:197:3: ( ',' rbv= basicValue )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==43) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:197:4: ',' rbv= basicValue
                    	    {
                    	    match(input,43,FOLLOW_43_in_attributeValue344); 
                    	    pushFollow(FOLLOW_basicValue_in_attributeValue348);
                    	    rbv=basicValue();

                    	    state._fsp--;

                    	     arrayValues.add(rbv); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match(input,48,FOLLOW_48_in_attributeValue355); 

                    			value = new ArrayValue(arrayValues);		
                    		

                    }
                    break;

            }
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return value;
    }
    // $ANTLR end "attributeValue"


    // $ANTLR start "basicValue"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:204:1: basicValue returns [DataValue dataValue] : (i= INTEGER | s= STRING | d= date | b= BOOLEAN | r= REAL | NULL );
    public final DataValue basicValue() throws RecognitionException {
        DataValue dataValue = null;

        Token i=null;
        Token s=null;
        Token b=null;
        Token r=null;
        DateValue d = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:206:2: (i= INTEGER | s= STRING | d= date | b= BOOLEAN | r= REAL | NULL )
            int alt8=6;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt8=1;
                }
                break;
            case STRING:
                {
                alt8=2;
                }
                break;
            case DATE_YEAR_MONTH_DAY:
                {
                alt8=3;
                }
                break;
            case BOOLEAN:
                {
                alt8=4;
                }
                break;
            case REAL:
                {
                alt8=5;
                }
                break;
            case NULL:
                {
                alt8=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:206:4: i= INTEGER
                    {
                    i=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_basicValue383); 
                     dataValue = new IntegerValue(Integer.parseInt(i.getText())); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:208:4: s= STRING
                    {
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_basicValue396); 
                     dataValue = new StringValue(s.getText().substring(1,s.getText().length()-1)); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:210:4: d= date
                    {
                    pushFollow(FOLLOW_date_in_basicValue407);
                    d=date();

                    state._fsp--;

                     dataValue = d; 

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:212:5: b= BOOLEAN
                    {
                    b=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_basicValue421); 
                     dataValue = new BooleanValue(Boolean.parseBoolean(b.getText())); 

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:214:5: r= REAL
                    {
                    r=(Token)match(input,REAL,FOLLOW_REAL_in_basicValue434); 
                     dataValue = new RealValue(Float.parseFloat(r.getText())); 

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:216:5: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_basicValue446); 
                     dataValue = new NullValue(); 

                    }
                    break;

            }
        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return dataValue;
    }
    // $ANTLR end "basicValue"


    // $ANTLR start "date"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:220:1: date returns [DateValue dateValue] : d= DATE_YEAR_MONTH_DAY ;
    public final DateValue date() throws RecognitionException {
        DateValue dateValue = null;

        Token d=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:222:2: (d= DATE_YEAR_MONTH_DAY )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:222:4: d= DATE_YEAR_MONTH_DAY
            {
            d=(Token)match(input,DATE_YEAR_MONTH_DAY,FOLLOW_DATE_YEAR_MONTH_DAY_in_date471); 

            			int year = Integer.parseInt(d.getText().substring(0,4));
            			int month = Integer.parseInt(d.getText().substring(5,7));
            			int day = Integer.parseInt(d.getText().substring(8,10));
            			
            			dateValue = new DateValue(year, month, day);
            		

            }

        }
        	
        	catch(RecognitionException re)
        	{	
        		reportError(re);
        		throw re;
        	}
        finally {
        }
        return dateValue;
    }
    // $ANTLR end "date"

    // Delegated rules


 

    public static final BitSet FOLLOW_CATALOG_in_catalog78 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_catalog80 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_entityData_in_catalog100 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_43_in_catalog103 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_entityData_in_catalog105 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_44_in_catalog109 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_catalog111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_entityData144 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_entityData146 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_entityData158 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_rowValues_in_entityData162 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_43_in_entityData177 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_rowValues_in_entityData181 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_48_in_entityData196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_rowValues250 = new BitSet(new long[]{0x0000800000000FC0L});
    public static final BitSet FOLLOW_attributeValue_in_rowValues254 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_43_in_rowValues269 = new BitSet(new long[]{0x0000800000000FC0L});
    public static final BitSet FOLLOW_attributeValue_in_rowValues273 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_48_in_rowValues280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basicValue_in_attributeValue310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_attributeValue319 = new BitSet(new long[]{0x0001080000000FC0L});
    public static final BitSet FOLLOW_basicValue_in_attributeValue331 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_43_in_attributeValue344 = new BitSet(new long[]{0x0000000000000FC0L});
    public static final BitSet FOLLOW_basicValue_in_attributeValue348 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_48_in_attributeValue355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_basicValue383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_basicValue396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_in_basicValue407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_basicValue421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_basicValue434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_basicValue446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATE_YEAR_MONTH_DAY_in_date471 = new BitSet(new long[]{0x0000000000000002L});

}