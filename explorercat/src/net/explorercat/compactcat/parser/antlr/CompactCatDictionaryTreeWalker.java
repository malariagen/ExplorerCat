// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g 2011-10-20 10:15:22

	package net.explorercat.compactcat.parser.antlr;		
	import net.explorercat.compactcat.definitions.*;
	import net.explorercat.cql.types.*;
	import net.explorercat.util.exceptions.ExplorerCatCheckedException; 


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * Tree walker in charge of creating the definition objects that will be used
 * by the translators to guide the translation process.
 *
 * Depends on ANTLR 3.2
 *
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Summer 2010
 */
public class CompactCatDictionaryTreeWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PROPERTIES", "RELEASE_DATE", "NAME", "ENTITY", "ATTRIBUTE", "SINGLE_VALUE", "DATE", "YEAR", "MONTH", "DAY", "ARRAY_VALUES", "BASIC_TYPE", "ARRAY_TYPE", "CATALOG", "VERSION", "STRING", "DATE_TYPE", "ID", "DESCRIPTION", "DICTIONARY", "ATTRIBUTES", "VALUES", "INTEGER", "BOOLEAN", "REAL", "NULL", "MINIMUM", "MAXIMUM", "TYPE", "INTEGER_TYPE", "STRING_TYPE", "TEXT_TYPE", "BOOLEAN_TYPE", "REAL_TYPE", "DATE_YEAR_MONTH_DAY", "REFERENCES", "C", "A", "T", "L", "O", "G", "N", "M", "E", "D", "I", "R", "Y", "B", "U", "S", "V", "F", "P", "X", "NUMBER", "EXPONENT", "LINE_COMMENT", "BLANK", "H", "J", "K", "Q", "W", "Z", "'{'", "','", "'}'", "';'", "':'", "'['", "']'", "'<'", "'>'", "'('", "')'"
    };
    public static final int EOF=-1;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int PROPERTIES=4;
    public static final int RELEASE_DATE=5;
    public static final int NAME=6;
    public static final int ENTITY=7;
    public static final int ATTRIBUTE=8;
    public static final int SINGLE_VALUE=9;
    public static final int DATE=10;
    public static final int YEAR=11;
    public static final int MONTH=12;
    public static final int DAY=13;
    public static final int ARRAY_VALUES=14;
    public static final int BASIC_TYPE=15;
    public static final int ARRAY_TYPE=16;
    public static final int CATALOG=17;
    public static final int VERSION=18;
    public static final int STRING=19;
    public static final int DATE_TYPE=20;
    public static final int ID=21;
    public static final int DESCRIPTION=22;
    public static final int DICTIONARY=23;
    public static final int ATTRIBUTES=24;
    public static final int VALUES=25;
    public static final int INTEGER=26;
    public static final int BOOLEAN=27;
    public static final int REAL=28;
    public static final int NULL=29;
    public static final int MINIMUM=30;
    public static final int MAXIMUM=31;
    public static final int TYPE=32;
    public static final int INTEGER_TYPE=33;
    public static final int STRING_TYPE=34;
    public static final int TEXT_TYPE=35;
    public static final int BOOLEAN_TYPE=36;
    public static final int REAL_TYPE=37;
    public static final int DATE_YEAR_MONTH_DAY=38;
    public static final int REFERENCES=39;
    public static final int C=40;
    public static final int A=41;
    public static final int T=42;
    public static final int L=43;
    public static final int O=44;
    public static final int G=45;
    public static final int N=46;
    public static final int M=47;
    public static final int E=48;
    public static final int D=49;
    public static final int I=50;
    public static final int R=51;
    public static final int Y=52;
    public static final int B=53;
    public static final int U=54;
    public static final int S=55;
    public static final int V=56;
    public static final int F=57;
    public static final int P=58;
    public static final int X=59;
    public static final int NUMBER=60;
    public static final int EXPONENT=61;
    public static final int LINE_COMMENT=62;
    public static final int BLANK=63;
    public static final int H=64;
    public static final int J=65;
    public static final int K=66;
    public static final int Q=67;
    public static final int W=68;
    public static final int Z=69;

    // delegates
    // delegators


        public CompactCatDictionaryTreeWalker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public CompactCatDictionaryTreeWalker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CompactCatDictionaryTreeWalker.tokenNames; }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g"; }


    	private String getUnquotedText(CommonTree tokenNode)
    	{
    		String text = tokenNode.getText();
    		return text.substring(1,text.length()-1);	
    	}


    protected static class catalog_scope {
        CatalogDefinition catalogDefinition;
    }
    protected Stack catalog_stack = new Stack();


    // $ANTLR start "catalog"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:47:1: catalog returns [CatalogDefinition completeCatalogDefinition] : ^( CATALOG ^( PROPERTIES ( catalogProperty )+ ) ) ;
    public final CatalogDefinition catalog() throws RecognitionException {
        catalog_stack.push(new catalog_scope());
        CatalogDefinition completeCatalogDefinition = null;

         
        		((catalog_scope)catalog_stack.peek()).catalogDefinition = new CatalogDefinition();
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:57:2: ( ^( CATALOG ^( PROPERTIES ( catalogProperty )+ ) ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:57:4: ^( CATALOG ^( PROPERTIES ( catalogProperty )+ ) )
            {
            match(input,CATALOG,FOLLOW_CATALOG_in_catalog97); 

            match(input, Token.DOWN, null); 
            match(input,PROPERTIES,FOLLOW_PROPERTIES_in_catalog100); 

            match(input, Token.DOWN, null); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:57:27: ( catalogProperty )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RELEASE_DATE && LA1_0<=NAME)||LA1_0==VERSION||(LA1_0>=DESCRIPTION && LA1_0<=DICTIONARY)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:57:27: catalogProperty
            	    {
            	    pushFollow(FOLLOW_catalogProperty_in_catalog102);
            	    catalogProperty();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            match(input, Token.UP, null); 

            match(input, Token.UP, null); 
            								
            			// Returning the catalog definition.
            			completeCatalogDefinition = ((catalog_scope)catalog_stack.peek()).catalogDefinition;																	
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            catalog_stack.pop();
        }
        return completeCatalogDefinition;
    }
    // $ANTLR end "catalog"


    // $ANTLR start "catalogProperty"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:65:1: catalogProperty : ( ^( NAME i= ID ) | ^( NAME rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ) | ^( NAME s= STRING ) | ^( VERSION s= STRING ) | ^( RELEASE_DATE ^( DATE d= DATE_YEAR_MONTH_DAY ) ) | ^( DESCRIPTION s= STRING ) | dictionary );
    public final void catalogProperty() throws RecognitionException {
        CommonTree i=null;
        CommonTree rn=null;
        CommonTree s=null;
        CommonTree d=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:67:2: ( ^( NAME i= ID ) | ^( NAME rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ) | ^( NAME s= STRING ) | ^( VERSION s= STRING ) | ^( RELEASE_DATE ^( DATE d= DATE_YEAR_MONTH_DAY ) ) | ^( DESCRIPTION s= STRING ) | dictionary )
            int alt2=7;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:67:4: ^( NAME i= ID )
                    {
                    match(input,NAME,FOLLOW_NAME_in_catalogProperty130); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_catalogProperty134); 

                    match(input, Token.UP, null); 
                     ((catalog_scope)catalog_stack.peek()).catalogDefinition.setName(i.getText()); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:70:4: ^( NAME rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) )
                    {
                    match(input,NAME,FOLLOW_NAME_in_catalogProperty147); 

                    match(input, Token.DOWN, null); 
                    rn=(CommonTree)input.LT(1);
                    if ( input.LA(1)==NAME||(input.LA(1)>=CATALOG && input.LA(1)<=VERSION)||(input.LA(1)>=DESCRIPTION && input.LA(1)<=VALUES)||(input.LA(1)>=MINIMUM && input.LA(1)<=TYPE)||input.LA(1)==REFERENCES ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.UP, null); 
                     ((catalog_scope)catalog_stack.peek()).catalogDefinition.setName(rn.getText()); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:73:4: ^( NAME s= STRING )
                    {
                    match(input,NAME,FOLLOW_NAME_in_catalogProperty186); 

                    match(input, Token.DOWN, null); 
                    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_catalogProperty190); 

                    match(input, Token.UP, null); 
                     ((catalog_scope)catalog_stack.peek()).catalogDefinition.setName(getUnquotedText(s)); 

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:76:4: ^( VERSION s= STRING )
                    {
                    match(input,VERSION,FOLLOW_VERSION_in_catalogProperty203); 

                    match(input, Token.DOWN, null); 
                    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_catalogProperty207); 

                    match(input, Token.UP, null); 
                     ((catalog_scope)catalog_stack.peek()).catalogDefinition.setVersion(getUnquotedText(s)); 

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:79:4: ^( RELEASE_DATE ^( DATE d= DATE_YEAR_MONTH_DAY ) )
                    {
                    match(input,RELEASE_DATE,FOLLOW_RELEASE_DATE_in_catalogProperty220); 

                    match(input, Token.DOWN, null); 
                    match(input,DATE,FOLLOW_DATE_in_catalogProperty223); 

                    match(input, Token.DOWN, null); 
                    d=(CommonTree)match(input,DATE_YEAR_MONTH_DAY,FOLLOW_DATE_YEAR_MONTH_DAY_in_catalogProperty228); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                     
                    			int year = Integer.parseInt(d.getText().substring(0,4));
                    			int month = Integer.parseInt(d.getText().substring(5,7));
                    			int day = Integer.parseInt(d.getText().substring(8,10));
                    										
                    			((catalog_scope)catalog_stack.peek()).catalogDefinition.setReleaseDate(new DateValue(year, month, day)); 
                    		

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:88:4: ^( DESCRIPTION s= STRING )
                    {
                    match(input,DESCRIPTION,FOLLOW_DESCRIPTION_in_catalogProperty245); 

                    match(input, Token.DOWN, null); 
                    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_catalogProperty249); 

                    match(input, Token.UP, null); 
                     ((catalog_scope)catalog_stack.peek()).catalogDefinition.setDescription(getUnquotedText(s)); 

                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:91:5: dictionary
                    {
                    pushFollow(FOLLOW_dictionary_in_catalogProperty263);
                    dictionary();

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
    // $ANTLR end "catalogProperty"


    // $ANTLR start "dictionary"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:95:1: dictionary : ^( DICTIONARY ( entity )+ ) ;
    public final void dictionary() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:96:2: ( ^( DICTIONARY ( entity )+ ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:96:4: ^( DICTIONARY ( entity )+ )
            {
            match(input,DICTIONARY,FOLLOW_DICTIONARY_in_dictionary279); 

            match(input, Token.DOWN, null); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:96:17: ( entity )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ENTITY) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:96:17: entity
            	    {
            	    pushFollow(FOLLOW_entity_in_dictionary281);
            	    entity();

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "dictionary"

    protected static class entity_scope {
        EntityDefinition entityDefinition;
    }
    protected Stack entity_stack = new Stack();


    // $ANTLR start "entity"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:100:1: entity : ^( ENTITY ^( NAME i= ID ) ^( PROPERTIES ( entityProperty )+ ) ^( ATTRIBUTES ( entityAttribute )+ ) ) ;
    public final void entity() throws RecognitionException {
        entity_stack.push(new entity_scope());
        CommonTree i=null;

         
        		((entity_scope)entity_stack.peek()).entityDefinition = new EntityDefinition();
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:110:2: ( ^( ENTITY ^( NAME i= ID ) ^( PROPERTIES ( entityProperty )+ ) ^( ATTRIBUTES ( entityAttribute )+ ) ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:110:4: ^( ENTITY ^( NAME i= ID ) ^( PROPERTIES ( entityProperty )+ ) ^( ATTRIBUTES ( entityAttribute )+ ) )
            {
            match(input,ENTITY,FOLLOW_ENTITY_in_entity317); 

            match(input, Token.DOWN, null); 
            match(input,NAME,FOLLOW_NAME_in_entity320); 

            match(input, Token.DOWN, null); 
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_entity324); 

            match(input, Token.UP, null); 
            match(input,PROPERTIES,FOLLOW_PROPERTIES_in_entity328); 

            match(input, Token.DOWN, null); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:110:39: ( entityProperty )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==DESCRIPTION) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:110:39: entityProperty
            	    {
            	    pushFollow(FOLLOW_entityProperty_in_entity330);
            	    entityProperty();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            match(input, Token.UP, null); 
            match(input,ATTRIBUTES,FOLLOW_ATTRIBUTES_in_entity335); 

            match(input, Token.DOWN, null); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:110:69: ( entityAttribute )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==ATTRIBUTE) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:110:69: entityAttribute
            	    {
            	    pushFollow(FOLLOW_entityAttribute_in_entity337);
            	    entityAttribute();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            match(input, Token.UP, null); 

            match(input, Token.UP, null); 
             
            			try
            			{
            				((entity_scope)entity_stack.peek()).entityDefinition.setName(i.getText());
            				((catalog_scope)catalog_stack.peek()).catalogDefinition.addEntityDefinition(((entity_scope)entity_stack.peek()).entityDefinition);
            			}
            			catch(Exception e)
            			{
            				// TODO Deal with errors!
            			}
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            entity_stack.pop();
        }
        return ;
    }
    // $ANTLR end "entity"


    // $ANTLR start "entityProperty"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:124:1: entityProperty : ^( DESCRIPTION s= STRING ) ;
    public final void entityProperty() throws RecognitionException {
        CommonTree s=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:126:2: ( ^( DESCRIPTION s= STRING ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:126:4: ^( DESCRIPTION s= STRING )
            {
            match(input,DESCRIPTION,FOLLOW_DESCRIPTION_in_entityProperty358); 

            match(input, Token.DOWN, null); 
            s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_entityProperty362); 

            match(input, Token.UP, null); 
             ((entity_scope)entity_stack.peek()).entityDefinition.setDescription(getUnquotedText(s)); 

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
    // $ANTLR end "entityProperty"

    protected static class entityAttribute_scope {
        RegularAttributeDefinition attributeDefinition;
    }
    protected Stack entityAttribute_stack = new Stack();


    // $ANTLR start "entityAttribute"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:130:1: entityAttribute : ^( ATTRIBUTE (av= attributeValue )+ ) ;
    public final void entityAttribute() throws RecognitionException {
        entityAttribute_stack.push(new entityAttribute_scope());
         
        		((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition = new RegularAttributeDefinition();
        	
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:140:2: ( ^( ATTRIBUTE (av= attributeValue )+ ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:140:4: ^( ATTRIBUTE (av= attributeValue )+ )
            {
            match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_entityAttribute399); 

            match(input, Token.DOWN, null); 
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:140:18: (av= attributeValue )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==NAME||LA6_0==DESCRIPTION||LA6_0==VALUES||(LA6_0>=MINIMUM && LA6_0<=TYPE)||LA6_0==REFERENCES) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:140:18: av= attributeValue
            	    {
            	    pushFollow(FOLLOW_attributeValue_in_entityAttribute403);
            	    attributeValue();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            match(input, Token.UP, null); 

            			try
            			{ 
            				((entity_scope)entity_stack.peek()).entityDefinition.addAttributeDefintion(((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition);
            			}
            			catch(ExplorerCatCheckedException e)
            			{
            				// TODO Deal with errors!
            				System.err.println("Error, duplicated attribute: " + ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.getName());
            			} 
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            entityAttribute_stack.pop();
        }
        return ;
    }
    // $ANTLR end "entityAttribute"


    // $ANTLR start "attributeValue"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:154:1: attributeValue : ( ^( NAME i= ID ) | ^( NAME s= STRING ) | ^( DESCRIPTION s= STRING ) | ^( VALUES valueList ) | ^( MINIMUM i= INTEGER ) | ^( MINIMUM r= REAL ) | ^( MAXIMUM i= INTEGER ) | ^( MAXIMUM r= REAL ) | ^( TYPE t= type ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE ai= ID ) ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ) ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE as= STRING ) ) );
    public final void attributeValue() throws RecognitionException {
        CommonTree i=null;
        CommonTree s=null;
        CommonTree r=null;
        CommonTree ei=null;
        CommonTree ai=null;
        CommonTree rn=null;
        CommonTree as=null;
        DataType t = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:156:2: ( ^( NAME i= ID ) | ^( NAME s= STRING ) | ^( DESCRIPTION s= STRING ) | ^( VALUES valueList ) | ^( MINIMUM i= INTEGER ) | ^( MINIMUM r= REAL ) | ^( MAXIMUM i= INTEGER ) | ^( MAXIMUM r= REAL ) | ^( TYPE t= type ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE ai= ID ) ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ) ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE as= STRING ) ) )
            int alt7=12;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:156:4: ^( NAME i= ID )
                    {
                    match(input,NAME,FOLLOW_NAME_in_attributeValue425); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,ID,FOLLOW_ID_in_attributeValue429); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setName(i.getText()); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:159:4: ^( NAME s= STRING )
                    {
                    match(input,NAME,FOLLOW_NAME_in_attributeValue443); 

                    match(input, Token.DOWN, null); 
                    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_attributeValue447); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setName(getUnquotedText(s)); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:162:4: ^( DESCRIPTION s= STRING )
                    {
                    match(input,DESCRIPTION,FOLLOW_DESCRIPTION_in_attributeValue460); 

                    match(input, Token.DOWN, null); 
                    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_attributeValue464); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setDescription(getUnquotedText(s)); 

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:165:4: ^( VALUES valueList )
                    {
                    match(input,VALUES,FOLLOW_VALUES_in_attributeValue477); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_valueList_in_attributeValue479);
                    valueList();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:167:4: ^( MINIMUM i= INTEGER )
                    {
                    match(input,MINIMUM,FOLLOW_MINIMUM_in_attributeValue488); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_attributeValue492); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setMinimumValue(new IntegerValue(Integer.parseInt(i.getText()))); 

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:170:4: ^( MINIMUM r= REAL )
                    {
                    match(input,MINIMUM,FOLLOW_MINIMUM_in_attributeValue505); 

                    match(input, Token.DOWN, null); 
                    r=(CommonTree)match(input,REAL,FOLLOW_REAL_in_attributeValue509); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setMinimumValue(new RealValue(Float.parseFloat(r.getText()))); 

                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:173:4: ^( MAXIMUM i= INTEGER )
                    {
                    match(input,MAXIMUM,FOLLOW_MAXIMUM_in_attributeValue522); 

                    match(input, Token.DOWN, null); 
                    i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_attributeValue526); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setMaximumValue(new IntegerValue(Integer.parseInt(i.getText()))); 

                    }
                    break;
                case 8 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:176:4: ^( MAXIMUM r= REAL )
                    {
                    match(input,MAXIMUM,FOLLOW_MAXIMUM_in_attributeValue539); 

                    match(input, Token.DOWN, null); 
                    r=(CommonTree)match(input,REAL,FOLLOW_REAL_in_attributeValue543); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setMaximumValue(new RealValue(Float.parseFloat(r.getText()))); 

                    }
                    break;
                case 9 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:179:4: ^( TYPE t= type )
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_attributeValue556); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_attributeValue560);
                    t=type();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setType(t); 

                    }
                    break;
                case 10 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:182:4: ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE ai= ID ) )
                    {
                    match(input,REFERENCES,FOLLOW_REFERENCES_in_attributeValue574); 

                    match(input, Token.DOWN, null); 
                    match(input,ENTITY,FOLLOW_ENTITY_in_attributeValue577); 

                    match(input, Token.DOWN, null); 
                    ei=(CommonTree)match(input,ID,FOLLOW_ID_in_attributeValue581); 

                    match(input, Token.UP, null); 
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attributeValue585); 

                    match(input, Token.DOWN, null); 
                    ai=(CommonTree)match(input,ID,FOLLOW_ID_in_attributeValue589); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setReference(ei.getText(), ai.getText()); 

                    }
                    break;
                case 11 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:185:4: ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ) )
                    {
                    match(input,REFERENCES,FOLLOW_REFERENCES_in_attributeValue604); 

                    match(input, Token.DOWN, null); 
                    match(input,ENTITY,FOLLOW_ENTITY_in_attributeValue607); 

                    match(input, Token.DOWN, null); 
                    ei=(CommonTree)match(input,ID,FOLLOW_ID_in_attributeValue611); 

                    match(input, Token.UP, null); 
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attributeValue615); 

                    match(input, Token.DOWN, null); 
                    rn=(CommonTree)input.LT(1);
                    if ( input.LA(1)==NAME||(input.LA(1)>=CATALOG && input.LA(1)<=VERSION)||(input.LA(1)>=DESCRIPTION && input.LA(1)<=VALUES)||(input.LA(1)>=MINIMUM && input.LA(1)<=TYPE)||input.LA(1)==REFERENCES ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setReference(ei.getText(), rn.getText()); 

                    }
                    break;
                case 12 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:188:4: ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE as= STRING ) )
                    {
                    match(input,REFERENCES,FOLLOW_REFERENCES_in_attributeValue656); 

                    match(input, Token.DOWN, null); 
                    match(input,ENTITY,FOLLOW_ENTITY_in_attributeValue659); 

                    match(input, Token.DOWN, null); 
                    ei=(CommonTree)match(input,ID,FOLLOW_ID_in_attributeValue663); 

                    match(input, Token.UP, null); 
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attributeValue667); 

                    match(input, Token.DOWN, null); 
                    as=(CommonTree)match(input,STRING,FOLLOW_STRING_in_attributeValue671); 

                    match(input, Token.UP, null); 

                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.setReference(ei.getText(), getUnquotedText(as)); 

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
    // $ANTLR end "attributeValue"


    // $ANTLR start "valueList"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:193:1: valueList : ( ^( SINGLE_VALUE v= value ) | ^( ARRAY_VALUES (v= value )+ ) );
    public final void valueList() throws RecognitionException {
        DataValue v = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:195:2: ( ^( SINGLE_VALUE v= value ) | ^( ARRAY_VALUES (v= value )+ ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==SINGLE_VALUE) ) {
                alt9=1;
            }
            else if ( (LA9_0==ARRAY_VALUES) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:195:4: ^( SINGLE_VALUE v= value )
                    {
                    match(input,SINGLE_VALUE,FOLLOW_SINGLE_VALUE_in_valueList699); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_value_in_valueList703);
                    v=value();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.registerAllowedValue(v); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:198:4: ^( ARRAY_VALUES (v= value )+ )
                    {
                    match(input,ARRAY_VALUES,FOLLOW_ARRAY_VALUES_in_valueList717); 

                    match(input, Token.DOWN, null); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:198:19: (v= value )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==DATE||LA8_0==STRING||(LA8_0>=INTEGER && LA8_0<=NULL)) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:198:20: v= value
                    	    {
                    	    pushFollow(FOLLOW_value_in_valueList722);
                    	    v=value();

                    	    state._fsp--;

                    	    ((entityAttribute_scope)entityAttribute_stack.peek()).attributeDefinition.registerAllowedValue(v);

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
    // $ANTLR end "valueList"


    // $ANTLR start "value"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:202:1: value returns [DataValue dataValue] : (i= INTEGER | s= STRING | b= BOOLEAN | r= REAL | NULL | ^( DATE d= DATE_YEAR_MONTH_DAY ) );
    public final DataValue value() throws RecognitionException {
        DataValue dataValue = null;

        CommonTree i=null;
        CommonTree s=null;
        CommonTree b=null;
        CommonTree r=null;
        CommonTree d=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:204:2: (i= INTEGER | s= STRING | b= BOOLEAN | r= REAL | NULL | ^( DATE d= DATE_YEAR_MONTH_DAY ) )
            int alt10=6;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt10=1;
                }
                break;
            case STRING:
                {
                alt10=2;
                }
                break;
            case BOOLEAN:
                {
                alt10=3;
                }
                break;
            case REAL:
                {
                alt10=4;
                }
                break;
            case NULL:
                {
                alt10=5;
                }
                break;
            case DATE:
                {
                alt10=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:204:4: i= INTEGER
                    {
                    i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_value752); 
                     dataValue = new IntegerValue(Integer.parseInt(i.getText())); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:206:5: s= STRING
                    {
                    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_value765); 
                     dataValue = new StringValue(getUnquotedText(s)); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:208:5: b= BOOLEAN
                    {
                    b=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_value781); 
                     dataValue = new BooleanValue(Boolean.parseBoolean(b.getText())); 

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:210:5: r= REAL
                    {
                    r=(CommonTree)match(input,REAL,FOLLOW_REAL_in_value794); 
                     dataValue = new RealValue(Float.parseFloat(r.getText())); 

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:212:5: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_value806); 
                     dataValue = null; 

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:214:5: ^( DATE d= DATE_YEAR_MONTH_DAY )
                    {
                    match(input,DATE,FOLLOW_DATE_in_value818); 

                    match(input, Token.DOWN, null); 
                    d=(CommonTree)match(input,DATE_YEAR_MONTH_DAY,FOLLOW_DATE_YEAR_MONTH_DAY_in_value822); 

                    match(input, Token.UP, null); 
                     
                    			int year = Integer.parseInt(d.getText().substring(0,4));
                    			int month = Integer.parseInt(d.getText().substring(5,7));
                    			int day = Integer.parseInt(d.getText().substring(8,10));
                    							
                    			dataValue = new DateValue(year, month, day);
                    		

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
        return dataValue;
    }
    // $ANTLR end "value"


    // $ANTLR start "type"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:225:1: type returns [DataType dataType] : ( ^( BASIC_TYPE bt= basicType ) | ^( ARRAY_TYPE bt= basicType ) );
    public final DataType type() throws RecognitionException {
        DataType dataType = null;

        DataType bt = null;


        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:227:2: ( ^( BASIC_TYPE bt= basicType ) | ^( ARRAY_TYPE bt= basicType ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==BASIC_TYPE) ) {
                alt11=1;
            }
            else if ( (LA11_0==ARRAY_TYPE) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:227:4: ^( BASIC_TYPE bt= basicType )
                    {
                    match(input,BASIC_TYPE,FOLLOW_BASIC_TYPE_in_type850); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_basicType_in_type854);
                    bt=basicType();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     dataType = bt; 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:230:4: ^( ARRAY_TYPE bt= basicType )
                    {
                    match(input,ARRAY_TYPE,FOLLOW_ARRAY_TYPE_in_type867); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_basicType_in_type871);
                    bt=basicType();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     dataType = bt.getEquivalentArrayType(); 

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
        return dataType;
    }
    // $ANTLR end "type"


    // $ANTLR start "basicType"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:234:1: basicType returns [DataType dataType] : ( INTEGER_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | BOOLEAN_TYPE | REAL_TYPE );
    public final DataType basicType() throws RecognitionException {
        DataType dataType = null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:236:2: ( INTEGER_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | BOOLEAN_TYPE | REAL_TYPE )
            int alt12=6;
            switch ( input.LA(1) ) {
            case INTEGER_TYPE:
                {
                alt12=1;
                }
                break;
            case STRING_TYPE:
                {
                alt12=2;
                }
                break;
            case TEXT_TYPE:
                {
                alt12=3;
                }
                break;
            case DATE_TYPE:
                {
                alt12=4;
                }
                break;
            case BOOLEAN_TYPE:
                {
                alt12=5;
                }
                break;
            case REAL_TYPE:
                {
                alt12=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:236:4: INTEGER_TYPE
                    {
                    match(input,INTEGER_TYPE,FOLLOW_INTEGER_TYPE_in_basicType896); 
                     dataType = DataType.INTEGER; 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:238:5: STRING_TYPE
                    {
                    match(input,STRING_TYPE,FOLLOW_STRING_TYPE_in_basicType908); 
                     dataType = DataType.STRING; 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:240:4: TEXT_TYPE
                    {
                    match(input,TEXT_TYPE,FOLLOW_TEXT_TYPE_in_basicType918); 
                     dataType = DataType.TEXT; 

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:242:5: DATE_TYPE
                    {
                    match(input,DATE_TYPE,FOLLOW_DATE_TYPE_in_basicType929); 
                     dataType = DataType.DATE; 

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:244:5: BOOLEAN_TYPE
                    {
                    match(input,BOOLEAN_TYPE,FOLLOW_BOOLEAN_TYPE_in_basicType941); 
                     dataType = DataType.BOOLEAN; 

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionaryTreeWalker.g:246:5: REAL_TYPE
                    {
                    match(input,REAL_TYPE,FOLLOW_REAL_TYPE_in_basicType952); 
                     dataType = DataType.REAL; 

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
        return dataType;
    }
    // $ANTLR end "basicType"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA2_eotS =
        "\12\uffff";
    static final String DFA2_eofS =
        "\12\uffff";
    static final String DFA2_minS =
        "\1\5\1\2\4\uffff\1\6\3\uffff";
    static final String DFA2_maxS =
        "\1\27\1\2\4\uffff\1\47\3\uffff";
    static final String DFA2_acceptS =
        "\2\uffff\1\4\1\5\1\6\1\7\1\uffff\1\1\1\2\1\3";
    static final String DFA2_specialS =
        "\12\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\3\1\1\13\uffff\1\2\3\uffff\1\4\1\5",
            "\1\6",
            "",
            "",
            "",
            "",
            "\1\10\12\uffff\2\10\1\11\1\uffff\1\7\4\10\4\uffff\3\10\6\uffff"+
            "\1\10",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "65:1: catalogProperty : ( ^( NAME i= ID ) | ^( NAME rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ) | ^( NAME s= STRING ) | ^( VERSION s= STRING ) | ^( RELEASE_DATE ^( DATE d= DATE_YEAR_MONTH_DAY ) ) | ^( DESCRIPTION s= STRING ) | dictionary );";
        }
    }
    static final String DFA7_eotS =
        "\33\uffff";
    static final String DFA7_eofS =
        "\33\uffff";
    static final String DFA7_minS =
        "\1\6\1\2\2\uffff\2\2\1\uffff\1\2\1\23\2\32\1\7\6\uffff\1\2\1\25"+
        "\1\3\1\10\1\2\1\6\3\uffff";
    static final String DFA7_maxS =
        "\1\47\1\2\2\uffff\2\2\1\uffff\1\2\1\25\2\34\1\7\6\uffff\1\2\1\25"+
        "\1\3\1\10\1\2\1\47\3\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\3\1\4\2\uffff\1\11\5\uffff\1\1\1\2\1\5\1\6\1\7\1\10\6"+
        "\uffff\1\12\1\13\1\14";
    static final String DFA7_specialS =
        "\33\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\17\uffff\1\2\2\uffff\1\3\4\uffff\1\4\1\5\1\6\6\uffff\1"+
            "\7",
            "\1\10",
            "",
            "",
            "\1\11",
            "\1\12",
            "",
            "\1\13",
            "\1\15\1\uffff\1\14",
            "\1\16\1\uffff\1\17",
            "\1\20\1\uffff\1\21",
            "\1\22",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\31\12\uffff\2\31\1\32\1\uffff\1\30\4\31\4\uffff\3\31\6\uffff"+
            "\1\31",
            "",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "154:1: attributeValue : ( ^( NAME i= ID ) | ^( NAME s= STRING ) | ^( DESCRIPTION s= STRING ) | ^( VALUES valueList ) | ^( MINIMUM i= INTEGER ) | ^( MINIMUM r= REAL ) | ^( MAXIMUM i= INTEGER ) | ^( MAXIMUM r= REAL ) | ^( TYPE t= type ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE ai= ID ) ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE rn= ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ) ) | ^( REFERENCES ^( ENTITY ei= ID ) ^( ATTRIBUTE as= STRING ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_CATALOG_in_catalog97 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PROPERTIES_in_catalog100 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_catalogProperty_in_catalog102 = new BitSet(new long[]{0x0000000000C40068L});
    public static final BitSet FOLLOW_NAME_in_catalogProperty130 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_catalogProperty134 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_catalogProperty147 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_set_in_catalogProperty151 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_catalogProperty186 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_catalogProperty190 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VERSION_in_catalogProperty203 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_catalogProperty207 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RELEASE_DATE_in_catalogProperty220 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DATE_in_catalogProperty223 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DATE_YEAR_MONTH_DAY_in_catalogProperty228 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DESCRIPTION_in_catalogProperty245 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_catalogProperty249 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_dictionary_in_catalogProperty263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DICTIONARY_in_dictionary279 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_entity_in_dictionary281 = new BitSet(new long[]{0x0000000000000088L});
    public static final BitSet FOLLOW_ENTITY_in_entity317 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NAME_in_entity320 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_entity324 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTIES_in_entity328 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_entityProperty_in_entity330 = new BitSet(new long[]{0x0000000000400008L});
    public static final BitSet FOLLOW_ATTRIBUTES_in_entity335 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_entityAttribute_in_entity337 = new BitSet(new long[]{0x0000000000000108L});
    public static final BitSet FOLLOW_DESCRIPTION_in_entityProperty358 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_entityProperty362 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_entityAttribute399 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attributeValue_in_entityAttribute403 = new BitSet(new long[]{0x00000081C2400048L});
    public static final BitSet FOLLOW_NAME_in_attributeValue425 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_attributeValue429 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_attributeValue443 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_attributeValue447 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DESCRIPTION_in_attributeValue460 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_attributeValue464 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VALUES_in_attributeValue477 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_valueList_in_attributeValue479 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINIMUM_in_attributeValue488 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_attributeValue492 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINIMUM_in_attributeValue505 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_REAL_in_attributeValue509 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAXIMUM_in_attributeValue522 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_attributeValue526 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAXIMUM_in_attributeValue539 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_REAL_in_attributeValue543 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPE_in_attributeValue556 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_attributeValue560 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REFERENCES_in_attributeValue574 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ENTITY_in_attributeValue577 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_attributeValue581 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attributeValue585 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_attributeValue589 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REFERENCES_in_attributeValue604 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ENTITY_in_attributeValue607 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_attributeValue611 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attributeValue615 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_set_in_attributeValue619 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REFERENCES_in_attributeValue656 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ENTITY_in_attributeValue659 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_attributeValue663 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attributeValue667 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_attributeValue671 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SINGLE_VALUE_in_valueList699 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_value_in_valueList703 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARRAY_VALUES_in_valueList717 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_value_in_valueList722 = new BitSet(new long[]{0x000000003C080408L});
    public static final BitSet FOLLOW_INTEGER_in_value752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_value765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_value781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_value794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_value806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATE_in_value818 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DATE_YEAR_MONTH_DAY_in_value822 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BASIC_TYPE_in_type850 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_basicType_in_type854 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARRAY_TYPE_in_type867 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_basicType_in_type871 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INTEGER_TYPE_in_basicType896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_TYPE_in_basicType908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_TYPE_in_basicType918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATE_TYPE_in_basicType929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_TYPE_in_basicType941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_TYPE_in_basicType952 = new BitSet(new long[]{0x0000000000000002L});

}