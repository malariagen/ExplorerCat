// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g 2011-10-20 14:12:42

	package net.explorercat.compactcat.parser.antlr;	


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

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
public class CompactCatDictionaryParser extends Parser {
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


        public CompactCatDictionaryParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CompactCatDictionaryParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CompactCatDictionaryParser.tokenNames; }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g"; }


    public static class catalog_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "catalog"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:61:1: catalog : CATALOG '{' cp= catalogProperty ( ',' rcp+= catalogProperty )* '}' ( ';' )? -> ^( CATALOG ^( PROPERTIES $cp ( $rcp)* ) ) ;
    public final CompactCatDictionaryParser.catalog_return catalog() throws RecognitionException {
        CompactCatDictionaryParser.catalog_return retval = new CompactCatDictionaryParser.catalog_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CATALOG1=null;
        Token char_literal2=null;
        Token char_literal3=null;
        Token char_literal4=null;
        Token char_literal5=null;
        List list_rcp=null;
        CompactCatDictionaryParser.catalogProperty_return cp = null;

        RuleReturnScope rcp = null;
        Object CATALOG1_tree=null;
        Object char_literal2_tree=null;
        Object char_literal3_tree=null;
        Object char_literal4_tree=null;
        Object char_literal5_tree=null;
        RewriteRuleTokenStream stream_CATALOG=new RewriteRuleTokenStream(adaptor,"token CATALOG");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
        RewriteRuleSubtreeStream stream_catalogProperty=new RewriteRuleSubtreeStream(adaptor,"rule catalogProperty");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:62:3: ( CATALOG '{' cp= catalogProperty ( ',' rcp+= catalogProperty )* '}' ( ';' )? -> ^( CATALOG ^( PROPERTIES $cp ( $rcp)* ) ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:62:6: CATALOG '{' cp= catalogProperty ( ',' rcp+= catalogProperty )* '}' ( ';' )?
            {
            CATALOG1=(Token)match(input,CATALOG,FOLLOW_CATALOG_in_catalog131);  
            stream_CATALOG.add(CATALOG1);

            char_literal2=(Token)match(input,70,FOLLOW_70_in_catalog133);  
            stream_70.add(char_literal2);

            pushFollow(FOLLOW_catalogProperty_in_catalog137);
            cp=catalogProperty();

            state._fsp--;

            stream_catalogProperty.add(cp.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:62:37: ( ',' rcp+= catalogProperty )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==71) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:62:38: ',' rcp+= catalogProperty
            	    {
            	    char_literal3=(Token)match(input,71,FOLLOW_71_in_catalog140);  
            	    stream_71.add(char_literal3);

            	    pushFollow(FOLLOW_catalogProperty_in_catalog144);
            	    rcp=catalogProperty();

            	    state._fsp--;

            	    stream_catalogProperty.add(rcp.getTree());
            	    if (list_rcp==null) list_rcp=new ArrayList();
            	    list_rcp.add(rcp.getTree());


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            char_literal4=(Token)match(input,72,FOLLOW_72_in_catalog148);  
            stream_72.add(char_literal4);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:62:69: ( ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==73) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:62:69: ';'
                    {
                    char_literal5=(Token)match(input,73,FOLLOW_73_in_catalog150);  
                    stream_73.add(char_literal5);


                    }
                    break;

            }



            // AST REWRITE
            // elements: rcp, CATALOG, cp
            // token labels: 
            // rule labels: retval, cp
            // token list labels: 
            // rule list labels: rcp
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_cp=new RewriteRuleSubtreeStream(adaptor,"rule cp",cp!=null?cp.tree:null);
            RewriteRuleSubtreeStream stream_rcp=new RewriteRuleSubtreeStream(adaptor,"token rcp",list_rcp);
            root_0 = (Object)adaptor.nil();
            // 63:4: -> ^( CATALOG ^( PROPERTIES $cp ( $rcp)* ) )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:63:7: ^( CATALOG ^( PROPERTIES $cp ( $rcp)* ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_CATALOG.nextNode(), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:63:17: ^( PROPERTIES $cp ( $rcp)* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTIES, "PROPERTIES"), root_2);

                adaptor.addChild(root_2, stream_cp.nextTree());
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:63:34: ( $rcp)*
                while ( stream_rcp.hasNext() ) {
                    adaptor.addChild(root_2, stream_rcp.nextTree());

                }
                stream_rcp.reset();

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "catalog"

    public static class catalogProperty_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "catalogProperty"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:66:1: catalogProperty : ( name | version | releaseDate | description | dictionary );
    public final CompactCatDictionaryParser.catalogProperty_return catalogProperty() throws RecognitionException {
        CompactCatDictionaryParser.catalogProperty_return retval = new CompactCatDictionaryParser.catalogProperty_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CompactCatDictionaryParser.name_return name6 = null;

        CompactCatDictionaryParser.version_return version7 = null;

        CompactCatDictionaryParser.releaseDate_return releaseDate8 = null;

        CompactCatDictionaryParser.description_return description9 = null;

        CompactCatDictionaryParser.dictionary_return dictionary10 = null;



        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:67:2: ( name | version | releaseDate | description | dictionary )
            int alt3=5;
            switch ( input.LA(1) ) {
            case NAME:
                {
                alt3=1;
                }
                break;
            case VERSION:
                {
                alt3=2;
                }
                break;
            case DATE_TYPE:
                {
                alt3=3;
                }
                break;
            case DESCRIPTION:
                {
                alt3=4;
                }
                break;
            case DICTIONARY:
                {
                alt3=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:67:4: name
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_name_in_catalogProperty184);
                    name6=name();

                    state._fsp--;

                    adaptor.addChild(root_0, name6.getTree());

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:68:4: version
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_version_in_catalogProperty189);
                    version7=version();

                    state._fsp--;

                    adaptor.addChild(root_0, version7.getTree());

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:69:4: releaseDate
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_releaseDate_in_catalogProperty194);
                    releaseDate8=releaseDate();

                    state._fsp--;

                    adaptor.addChild(root_0, releaseDate8.getTree());

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:70:4: description
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_description_in_catalogProperty199);
                    description9=description();

                    state._fsp--;

                    adaptor.addChild(root_0, description9.getTree());

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:71:4: dictionary
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_dictionary_in_catalogProperty204);
                    dictionary10=dictionary();

                    state._fsp--;

                    adaptor.addChild(root_0, dictionary10.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "catalogProperty"

    public static class version_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "version"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:76:1: version : VERSION ':' s= STRING -> ^( VERSION $s) ;
    public final CompactCatDictionaryParser.version_return version() throws RecognitionException {
        CompactCatDictionaryParser.version_return retval = new CompactCatDictionaryParser.version_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token s=null;
        Token VERSION11=null;
        Token char_literal12=null;

        Object s_tree=null;
        Object VERSION11_tree=null;
        Object char_literal12_tree=null;
        RewriteRuleTokenStream stream_VERSION=new RewriteRuleTokenStream(adaptor,"token VERSION");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:77:2: ( VERSION ':' s= STRING -> ^( VERSION $s) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:77:4: VERSION ':' s= STRING
            {
            VERSION11=(Token)match(input,VERSION,FOLLOW_VERSION_in_version225);  
            stream_VERSION.add(VERSION11);

            char_literal12=(Token)match(input,74,FOLLOW_74_in_version227);  
            stream_74.add(char_literal12);

            s=(Token)match(input,STRING,FOLLOW_STRING_in_version231);  
            stream_STRING.add(s);



            // AST REWRITE
            // elements: s, VERSION
            // token labels: s
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_s=new RewriteRuleTokenStream(adaptor,"token s",s);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 78:3: -> ^( VERSION $s)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:78:6: ^( VERSION $s)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_VERSION.nextNode(), root_1);

                adaptor.addChild(root_1, stream_s.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "version"

    public static class releaseDate_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "releaseDate"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:81:1: releaseDate : DATE_TYPE ':' d= date -> ^( RELEASE_DATE $d) ;
    public final CompactCatDictionaryParser.releaseDate_return releaseDate() throws RecognitionException {
        CompactCatDictionaryParser.releaseDate_return retval = new CompactCatDictionaryParser.releaseDate_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DATE_TYPE13=null;
        Token char_literal14=null;
        CompactCatDictionaryParser.date_return d = null;


        Object DATE_TYPE13_tree=null;
        Object char_literal14_tree=null;
        RewriteRuleTokenStream stream_DATE_TYPE=new RewriteRuleTokenStream(adaptor,"token DATE_TYPE");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_date=new RewriteRuleSubtreeStream(adaptor,"rule date");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:82:2: ( DATE_TYPE ':' d= date -> ^( RELEASE_DATE $d) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:82:4: DATE_TYPE ':' d= date
            {
            DATE_TYPE13=(Token)match(input,DATE_TYPE,FOLLOW_DATE_TYPE_in_releaseDate254);  
            stream_DATE_TYPE.add(DATE_TYPE13);

            char_literal14=(Token)match(input,74,FOLLOW_74_in_releaseDate256);  
            stream_74.add(char_literal14);

            pushFollow(FOLLOW_date_in_releaseDate260);
            d=date();

            state._fsp--;

            stream_date.add(d.getTree());


            // AST REWRITE
            // elements: d
            // token labels: 
            // rule labels: retval, d
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);

            root_0 = (Object)adaptor.nil();
            // 83:3: -> ^( RELEASE_DATE $d)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:83:6: ^( RELEASE_DATE $d)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(RELEASE_DATE, "RELEASE_DATE"), root_1);

                adaptor.addChild(root_1, stream_d.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "releaseDate"

    public static class name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "name"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:86:1: name : NAME ':' (i= ID | rn= reservedName | s= STRING ) -> ^( NAME ( $i)? ( $rn)? ( $s)? ) ;
    public final CompactCatDictionaryParser.name_return name() throws RecognitionException {
        CompactCatDictionaryParser.name_return retval = new CompactCatDictionaryParser.name_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token s=null;
        Token NAME15=null;
        Token char_literal16=null;
        CompactCatDictionaryParser.reservedName_return rn = null;


        Object i_tree=null;
        Object s_tree=null;
        Object NAME15_tree=null;
        Object char_literal16_tree=null;
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleSubtreeStream stream_reservedName=new RewriteRuleSubtreeStream(adaptor,"rule reservedName");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:87:2: ( NAME ':' (i= ID | rn= reservedName | s= STRING ) -> ^( NAME ( $i)? ( $rn)? ( $s)? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:87:5: NAME ':' (i= ID | rn= reservedName | s= STRING )
            {
            NAME15=(Token)match(input,NAME,FOLLOW_NAME_in_name285);  
            stream_NAME.add(NAME15);

            char_literal16=(Token)match(input,74,FOLLOW_74_in_name287);  
            stream_74.add(char_literal16);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:87:14: (i= ID | rn= reservedName | s= STRING )
            int alt4=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt4=1;
                }
                break;
            case NAME:
            case CATALOG:
            case VERSION:
            case DESCRIPTION:
            case DICTIONARY:
            case ATTRIBUTES:
            case VALUES:
            case MINIMUM:
            case MAXIMUM:
            case TYPE:
            case REFERENCES:
                {
                alt4=2;
                }
                break;
            case STRING:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:87:15: i= ID
                    {
                    i=(Token)match(input,ID,FOLLOW_ID_in_name292);  
                    stream_ID.add(i);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:87:22: rn= reservedName
                    {
                    pushFollow(FOLLOW_reservedName_in_name298);
                    rn=reservedName();

                    state._fsp--;

                    stream_reservedName.add(rn.getTree());

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:87:40: s= STRING
                    {
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_name304);  
                    stream_STRING.add(s);


                    }
                    break;

            }



            // AST REWRITE
            // elements: s, rn, i, NAME
            // token labels: s, i
            // rule labels: retval, rn
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_s=new RewriteRuleTokenStream(adaptor,"token s",s);
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_rn=new RewriteRuleSubtreeStream(adaptor,"rule rn",rn!=null?rn.tree:null);

            root_0 = (Object)adaptor.nil();
            // 88:3: -> ^( NAME ( $i)? ( $rn)? ( $s)? )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:88:6: ^( NAME ( $i)? ( $rn)? ( $s)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_NAME.nextNode(), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:88:13: ( $i)?
                if ( stream_i.hasNext() ) {
                    adaptor.addChild(root_1, stream_i.nextNode());

                }
                stream_i.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:88:17: ( $rn)?
                if ( stream_rn.hasNext() ) {
                    adaptor.addChild(root_1, stream_rn.nextTree());

                }
                stream_rn.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:88:22: ( $s)?
                if ( stream_s.hasNext() ) {
                    adaptor.addChild(root_1, stream_s.nextNode());

                }
                stream_s.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "name"

    public static class description_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "description"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:91:1: description : DESCRIPTION ':' s= STRING -> ^( DESCRIPTION $s) ;
    public final CompactCatDictionaryParser.description_return description() throws RecognitionException {
        CompactCatDictionaryParser.description_return retval = new CompactCatDictionaryParser.description_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token s=null;
        Token DESCRIPTION17=null;
        Token char_literal18=null;

        Object s_tree=null;
        Object DESCRIPTION17_tree=null;
        Object char_literal18_tree=null;
        RewriteRuleTokenStream stream_DESCRIPTION=new RewriteRuleTokenStream(adaptor,"token DESCRIPTION");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:92:2: ( DESCRIPTION ':' s= STRING -> ^( DESCRIPTION $s) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:92:4: DESCRIPTION ':' s= STRING
            {
            DESCRIPTION17=(Token)match(input,DESCRIPTION,FOLLOW_DESCRIPTION_in_description338);  
            stream_DESCRIPTION.add(DESCRIPTION17);

            char_literal18=(Token)match(input,74,FOLLOW_74_in_description340);  
            stream_74.add(char_literal18);

            s=(Token)match(input,STRING,FOLLOW_STRING_in_description344);  
            stream_STRING.add(s);



            // AST REWRITE
            // elements: DESCRIPTION, s
            // token labels: s
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_s=new RewriteRuleTokenStream(adaptor,"token s",s);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 93:3: -> ^( DESCRIPTION $s)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:93:6: ^( DESCRIPTION $s)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_DESCRIPTION.nextNode(), root_1);

                adaptor.addChild(root_1, stream_s.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "description"

    public static class dictionary_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dictionary"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:102:1: dictionary : DICTIONARY ':' '{' ed= entityDictionary ( ',' red+= entityDictionary )* '}' -> ^( DICTIONARY $ed ( $red)* ) ;
    public final CompactCatDictionaryParser.dictionary_return dictionary() throws RecognitionException {
        CompactCatDictionaryParser.dictionary_return retval = new CompactCatDictionaryParser.dictionary_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DICTIONARY19=null;
        Token char_literal20=null;
        Token char_literal21=null;
        Token char_literal22=null;
        Token char_literal23=null;
        List list_red=null;
        CompactCatDictionaryParser.entityDictionary_return ed = null;

        RuleReturnScope red = null;
        Object DICTIONARY19_tree=null;
        Object char_literal20_tree=null;
        Object char_literal21_tree=null;
        Object char_literal22_tree=null;
        Object char_literal23_tree=null;
        RewriteRuleTokenStream stream_DICTIONARY=new RewriteRuleTokenStream(adaptor,"token DICTIONARY");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_entityDictionary=new RewriteRuleSubtreeStream(adaptor,"rule entityDictionary");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:103:2: ( DICTIONARY ':' '{' ed= entityDictionary ( ',' red+= entityDictionary )* '}' -> ^( DICTIONARY $ed ( $red)* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:103:4: DICTIONARY ':' '{' ed= entityDictionary ( ',' red+= entityDictionary )* '}'
            {
            DICTIONARY19=(Token)match(input,DICTIONARY,FOLLOW_DICTIONARY_in_dictionary374);  
            stream_DICTIONARY.add(DICTIONARY19);

            char_literal20=(Token)match(input,74,FOLLOW_74_in_dictionary376);  
            stream_74.add(char_literal20);

            char_literal21=(Token)match(input,70,FOLLOW_70_in_dictionary378);  
            stream_70.add(char_literal21);

            pushFollow(FOLLOW_entityDictionary_in_dictionary382);
            ed=entityDictionary();

            state._fsp--;

            stream_entityDictionary.add(ed.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:103:43: ( ',' red+= entityDictionary )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==71) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:103:44: ',' red+= entityDictionary
            	    {
            	    char_literal22=(Token)match(input,71,FOLLOW_71_in_dictionary385);  
            	    stream_71.add(char_literal22);

            	    pushFollow(FOLLOW_entityDictionary_in_dictionary389);
            	    red=entityDictionary();

            	    state._fsp--;

            	    stream_entityDictionary.add(red.getTree());
            	    if (list_red==null) list_red=new ArrayList();
            	    list_red.add(red.getTree());


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            char_literal23=(Token)match(input,72,FOLLOW_72_in_dictionary393);  
            stream_72.add(char_literal23);



            // AST REWRITE
            // elements: DICTIONARY, ed, red
            // token labels: 
            // rule labels: retval, ed
            // token list labels: 
            // rule list labels: red
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_ed=new RewriteRuleSubtreeStream(adaptor,"rule ed",ed!=null?ed.tree:null);
            RewriteRuleSubtreeStream stream_red=new RewriteRuleSubtreeStream(adaptor,"token red",list_red);
            root_0 = (Object)adaptor.nil();
            // 104:3: -> ^( DICTIONARY $ed ( $red)* )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:104:6: ^( DICTIONARY $ed ( $red)* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_DICTIONARY.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ed.nextTree());
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:104:23: ( $red)*
                while ( stream_red.hasNext() ) {
                    adaptor.addChild(root_1, stream_red.nextTree());

                }
                stream_red.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dictionary"

    public static class entityDictionary_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityDictionary"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:107:1: entityDictionary : i= ID ':' '{' ( (ep= entityProperties ',' ea= entityAttributes ) | (ea= entityAttributes ',' ep= entityProperties ) ) '}' -> ^( ENTITY ^( NAME $i) ^( PROPERTIES $ep) ^( ATTRIBUTES $ea) ) ;
    public final CompactCatDictionaryParser.entityDictionary_return entityDictionary() throws RecognitionException {
        CompactCatDictionaryParser.entityDictionary_return retval = new CompactCatDictionaryParser.entityDictionary_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token char_literal24=null;
        Token char_literal25=null;
        Token char_literal26=null;
        Token char_literal27=null;
        Token char_literal28=null;
        CompactCatDictionaryParser.entityProperties_return ep = null;

        CompactCatDictionaryParser.entityAttributes_return ea = null;


        Object i_tree=null;
        Object char_literal24_tree=null;
        Object char_literal25_tree=null;
        Object char_literal26_tree=null;
        Object char_literal27_tree=null;
        Object char_literal28_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_entityProperties=new RewriteRuleSubtreeStream(adaptor,"rule entityProperties");
        RewriteRuleSubtreeStream stream_entityAttributes=new RewriteRuleSubtreeStream(adaptor,"rule entityAttributes");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:2: (i= ID ':' '{' ( (ep= entityProperties ',' ea= entityAttributes ) | (ea= entityAttributes ',' ep= entityProperties ) ) '}' -> ^( ENTITY ^( NAME $i) ^( PROPERTIES $ep) ^( ATTRIBUTES $ea) ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:4: i= ID ':' '{' ( (ep= entityProperties ',' ea= entityAttributes ) | (ea= entityAttributes ',' ep= entityProperties ) ) '}'
            {
            i=(Token)match(input,ID,FOLLOW_ID_in_entityDictionary426);  
            stream_ID.add(i);

            char_literal24=(Token)match(input,74,FOLLOW_74_in_entityDictionary428);  
            stream_74.add(char_literal24);

            char_literal25=(Token)match(input,70,FOLLOW_70_in_entityDictionary430);  
            stream_70.add(char_literal25);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:17: ( (ep= entityProperties ',' ea= entityAttributes ) | (ea= entityAttributes ',' ep= entityProperties ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==DESCRIPTION) ) {
                alt6=1;
            }
            else if ( (LA6_0==ATTRIBUTES) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:18: (ep= entityProperties ',' ea= entityAttributes )
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:18: (ep= entityProperties ',' ea= entityAttributes )
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:19: ep= entityProperties ',' ea= entityAttributes
                    {
                    pushFollow(FOLLOW_entityProperties_in_entityDictionary436);
                    ep=entityProperties();

                    state._fsp--;

                    stream_entityProperties.add(ep.getTree());
                    char_literal26=(Token)match(input,71,FOLLOW_71_in_entityDictionary438);  
                    stream_71.add(char_literal26);

                    pushFollow(FOLLOW_entityAttributes_in_entityDictionary442);
                    ea=entityAttributes();

                    state._fsp--;

                    stream_entityAttributes.add(ea.getTree());

                    }


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:66: (ea= entityAttributes ',' ep= entityProperties )
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:66: (ea= entityAttributes ',' ep= entityProperties )
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:108:67: ea= entityAttributes ',' ep= entityProperties
                    {
                    pushFollow(FOLLOW_entityAttributes_in_entityDictionary450);
                    ea=entityAttributes();

                    state._fsp--;

                    stream_entityAttributes.add(ea.getTree());
                    char_literal27=(Token)match(input,71,FOLLOW_71_in_entityDictionary452);  
                    stream_71.add(char_literal27);

                    pushFollow(FOLLOW_entityProperties_in_entityDictionary456);
                    ep=entityProperties();

                    state._fsp--;

                    stream_entityProperties.add(ep.getTree());

                    }


                    }
                    break;

            }

            char_literal28=(Token)match(input,72,FOLLOW_72_in_entityDictionary460);  
            stream_72.add(char_literal28);



            // AST REWRITE
            // elements: ep, i, ea
            // token labels: i
            // rule labels: retval, ep, ea
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_ep=new RewriteRuleSubtreeStream(adaptor,"rule ep",ep!=null?ep.tree:null);
            RewriteRuleSubtreeStream stream_ea=new RewriteRuleSubtreeStream(adaptor,"rule ea",ea!=null?ea.tree:null);

            root_0 = (Object)adaptor.nil();
            // 109:3: -> ^( ENTITY ^( NAME $i) ^( PROPERTIES $ep) ^( ATTRIBUTES $ea) )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:109:6: ^( ENTITY ^( NAME $i) ^( PROPERTIES $ep) ^( ATTRIBUTES $ea) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ENTITY, "ENTITY"), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:109:15: ^( NAME $i)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(NAME, "NAME"), root_2);

                adaptor.addChild(root_2, stream_i.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:109:26: ^( PROPERTIES $ep)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROPERTIES, "PROPERTIES"), root_2);

                adaptor.addChild(root_2, stream_ep.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:109:44: ^( ATTRIBUTES $ea)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ATTRIBUTES, "ATTRIBUTES"), root_2);

                adaptor.addChild(root_2, stream_ea.nextTree());

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entityDictionary"

    public static class entityProperties_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityProperties"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:112:1: entityProperties : entityProperty ( ',' entityProperty )* ;
    public final CompactCatDictionaryParser.entityProperties_return entityProperties() throws RecognitionException {
        CompactCatDictionaryParser.entityProperties_return retval = new CompactCatDictionaryParser.entityProperties_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal30=null;
        CompactCatDictionaryParser.entityProperty_return entityProperty29 = null;

        CompactCatDictionaryParser.entityProperty_return entityProperty31 = null;


        Object char_literal30_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:113:2: ( entityProperty ( ',' entityProperty )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:113:4: entityProperty ( ',' entityProperty )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_entityProperty_in_entityProperties500);
            entityProperty29=entityProperty();

            state._fsp--;

            adaptor.addChild(root_0, entityProperty29.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:113:19: ( ',' entityProperty )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==71) ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1==DESCRIPTION) ) {
                        alt7=1;
                    }


                }


                switch (alt7) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:113:20: ',' entityProperty
            	    {
            	    char_literal30=(Token)match(input,71,FOLLOW_71_in_entityProperties503); 
            	    char_literal30_tree = (Object)adaptor.create(char_literal30);
            	    adaptor.addChild(root_0, char_literal30_tree);

            	    pushFollow(FOLLOW_entityProperty_in_entityProperties505);
            	    entityProperty31=entityProperty();

            	    state._fsp--;

            	    adaptor.addChild(root_0, entityProperty31.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entityProperties"

    public static class entityProperty_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityProperty"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:117:1: entityProperty : description ;
    public final CompactCatDictionaryParser.entityProperty_return entityProperty() throws RecognitionException {
        CompactCatDictionaryParser.entityProperty_return retval = new CompactCatDictionaryParser.entityProperty_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CompactCatDictionaryParser.description_return description32 = null;



        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:118:2: ( description )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:118:4: description
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_description_in_entityProperty520);
            description32=description();

            state._fsp--;

            adaptor.addChild(root_0, description32.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entityProperty"

    public static class entityAttributes_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityAttributes"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:121:1: entityAttributes : ATTRIBUTES ':' '[' ea= entityAttribute ( ',' rea+= entityAttribute )* ']' -> $ea ( $rea)* ;
    public final CompactCatDictionaryParser.entityAttributes_return entityAttributes() throws RecognitionException {
        CompactCatDictionaryParser.entityAttributes_return retval = new CompactCatDictionaryParser.entityAttributes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ATTRIBUTES33=null;
        Token char_literal34=null;
        Token char_literal35=null;
        Token char_literal36=null;
        Token char_literal37=null;
        List list_rea=null;
        CompactCatDictionaryParser.entityAttribute_return ea = null;

        RuleReturnScope rea = null;
        Object ATTRIBUTES33_tree=null;
        Object char_literal34_tree=null;
        Object char_literal35_tree=null;
        Object char_literal36_tree=null;
        Object char_literal37_tree=null;
        RewriteRuleTokenStream stream_ATTRIBUTES=new RewriteRuleTokenStream(adaptor,"token ATTRIBUTES");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_entityAttribute=new RewriteRuleSubtreeStream(adaptor,"rule entityAttribute");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:122:2: ( ATTRIBUTES ':' '[' ea= entityAttribute ( ',' rea+= entityAttribute )* ']' -> $ea ( $rea)* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:122:5: ATTRIBUTES ':' '[' ea= entityAttribute ( ',' rea+= entityAttribute )* ']'
            {
            ATTRIBUTES33=(Token)match(input,ATTRIBUTES,FOLLOW_ATTRIBUTES_in_entityAttributes539);  
            stream_ATTRIBUTES.add(ATTRIBUTES33);

            char_literal34=(Token)match(input,74,FOLLOW_74_in_entityAttributes541);  
            stream_74.add(char_literal34);

            char_literal35=(Token)match(input,75,FOLLOW_75_in_entityAttributes543);  
            stream_75.add(char_literal35);

            pushFollow(FOLLOW_entityAttribute_in_entityAttributes547);
            ea=entityAttribute();

            state._fsp--;

            stream_entityAttribute.add(ea.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:122:43: ( ',' rea+= entityAttribute )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==71) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:122:44: ',' rea+= entityAttribute
            	    {
            	    char_literal36=(Token)match(input,71,FOLLOW_71_in_entityAttributes550);  
            	    stream_71.add(char_literal36);

            	    pushFollow(FOLLOW_entityAttribute_in_entityAttributes554);
            	    rea=entityAttribute();

            	    state._fsp--;

            	    stream_entityAttribute.add(rea.getTree());
            	    if (list_rea==null) list_rea=new ArrayList();
            	    list_rea.add(rea.getTree());


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            char_literal37=(Token)match(input,76,FOLLOW_76_in_entityAttributes558);  
            stream_76.add(char_literal37);



            // AST REWRITE
            // elements: ea, rea
            // token labels: 
            // rule labels: retval, ea
            // token list labels: 
            // rule list labels: rea
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_ea=new RewriteRuleSubtreeStream(adaptor,"rule ea",ea!=null?ea.tree:null);
            RewriteRuleSubtreeStream stream_rea=new RewriteRuleSubtreeStream(adaptor,"token rea",list_rea);
            root_0 = (Object)adaptor.nil();
            // 123:3: -> $ea ( $rea)*
            {
                adaptor.addChild(root_0, stream_ea.nextTree());
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:123:10: ( $rea)*
                while ( stream_rea.hasNext() ) {
                    adaptor.addChild(root_0, stream_rea.nextTree());

                }
                stream_rea.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entityAttributes"

    public static class entityAttribute_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityAttribute"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:128:1: entityAttribute : '{' av= attributeValue ( ',' rav+= attributeValue )* '}' -> ^( ATTRIBUTE $av ( $rav)* ) ;
    public final CompactCatDictionaryParser.entityAttribute_return entityAttribute() throws RecognitionException {
        CompactCatDictionaryParser.entityAttribute_return retval = new CompactCatDictionaryParser.entityAttribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal38=null;
        Token char_literal39=null;
        Token char_literal40=null;
        List list_rav=null;
        CompactCatDictionaryParser.attributeValue_return av = null;

        RuleReturnScope rav = null;
        Object char_literal38_tree=null;
        Object char_literal39_tree=null;
        Object char_literal40_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleSubtreeStream stream_attributeValue=new RewriteRuleSubtreeStream(adaptor,"rule attributeValue");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:129:2: ( '{' av= attributeValue ( ',' rav+= attributeValue )* '}' -> ^( ATTRIBUTE $av ( $rav)* ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:129:4: '{' av= attributeValue ( ',' rav+= attributeValue )* '}'
            {
            char_literal38=(Token)match(input,70,FOLLOW_70_in_entityAttribute583);  
            stream_70.add(char_literal38);

            pushFollow(FOLLOW_attributeValue_in_entityAttribute587);
            av=attributeValue();

            state._fsp--;

            stream_attributeValue.add(av.getTree());
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:129:26: ( ',' rav+= attributeValue )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==71) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:129:27: ',' rav+= attributeValue
            	    {
            	    char_literal39=(Token)match(input,71,FOLLOW_71_in_entityAttribute590);  
            	    stream_71.add(char_literal39);

            	    pushFollow(FOLLOW_attributeValue_in_entityAttribute594);
            	    rav=attributeValue();

            	    state._fsp--;

            	    stream_attributeValue.add(rav.getTree());
            	    if (list_rav==null) list_rav=new ArrayList();
            	    list_rav.add(rav.getTree());


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            char_literal40=(Token)match(input,72,FOLLOW_72_in_entityAttribute598);  
            stream_72.add(char_literal40);



            // AST REWRITE
            // elements: rav, av
            // token labels: 
            // rule labels: retval, av
            // token list labels: 
            // rule list labels: rav
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_av=new RewriteRuleSubtreeStream(adaptor,"rule av",av!=null?av.tree:null);
            RewriteRuleSubtreeStream stream_rav=new RewriteRuleSubtreeStream(adaptor,"token rav",list_rav);
            root_0 = (Object)adaptor.nil();
            // 130:3: -> ^( ATTRIBUTE $av ( $rav)* )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:130:6: ^( ATTRIBUTE $av ( $rav)* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ATTRIBUTE, "ATTRIBUTE"), root_1);

                adaptor.addChild(root_1, stream_av.nextTree());
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:130:22: ( $rav)*
                while ( stream_rav.hasNext() ) {
                    adaptor.addChild(root_1, stream_rav.nextTree());

                }
                stream_rav.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entityAttribute"

    public static class attributeValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attributeValue"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:133:1: attributeValue : ( name | description | allowedValues | minimumValue | maximumValue | attributeType | attributeReference );
    public final CompactCatDictionaryParser.attributeValue_return attributeValue() throws RecognitionException {
        CompactCatDictionaryParser.attributeValue_return retval = new CompactCatDictionaryParser.attributeValue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CompactCatDictionaryParser.name_return name41 = null;

        CompactCatDictionaryParser.description_return description42 = null;

        CompactCatDictionaryParser.allowedValues_return allowedValues43 = null;

        CompactCatDictionaryParser.minimumValue_return minimumValue44 = null;

        CompactCatDictionaryParser.maximumValue_return maximumValue45 = null;

        CompactCatDictionaryParser.attributeType_return attributeType46 = null;

        CompactCatDictionaryParser.attributeReference_return attributeReference47 = null;



        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:134:2: ( name | description | allowedValues | minimumValue | maximumValue | attributeType | attributeReference )
            int alt10=7;
            switch ( input.LA(1) ) {
            case NAME:
                {
                alt10=1;
                }
                break;
            case DESCRIPTION:
                {
                alt10=2;
                }
                break;
            case VALUES:
                {
                alt10=3;
                }
                break;
            case MINIMUM:
                {
                alt10=4;
                }
                break;
            case MAXIMUM:
                {
                alt10=5;
                }
                break;
            case TYPE:
                {
                alt10=6;
                }
                break;
            case REFERENCES:
                {
                alt10=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:134:5: name
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_name_in_attributeValue626);
                    name41=name();

                    state._fsp--;

                    adaptor.addChild(root_0, name41.getTree());

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:135:4: description
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_description_in_attributeValue631);
                    description42=description();

                    state._fsp--;

                    adaptor.addChild(root_0, description42.getTree());

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:136:4: allowedValues
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_allowedValues_in_attributeValue636);
                    allowedValues43=allowedValues();

                    state._fsp--;

                    adaptor.addChild(root_0, allowedValues43.getTree());

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:137:4: minimumValue
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_minimumValue_in_attributeValue641);
                    minimumValue44=minimumValue();

                    state._fsp--;

                    adaptor.addChild(root_0, minimumValue44.getTree());

                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:138:4: maximumValue
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_maximumValue_in_attributeValue646);
                    maximumValue45=maximumValue();

                    state._fsp--;

                    adaptor.addChild(root_0, maximumValue45.getTree());

                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:139:4: attributeType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_attributeType_in_attributeValue651);
                    attributeType46=attributeType();

                    state._fsp--;

                    adaptor.addChild(root_0, attributeType46.getTree());

                    }
                    break;
                case 7 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:140:4: attributeReference
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_attributeReference_in_attributeValue656);
                    attributeReference47=attributeReference();

                    state._fsp--;

                    adaptor.addChild(root_0, attributeReference47.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attributeValue"

    public static class allowedValues_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "allowedValues"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:143:1: allowedValues : VALUES ':' vl= valuesList -> ^( VALUES $vl) ;
    public final CompactCatDictionaryParser.allowedValues_return allowedValues() throws RecognitionException {
        CompactCatDictionaryParser.allowedValues_return retval = new CompactCatDictionaryParser.allowedValues_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VALUES48=null;
        Token char_literal49=null;
        CompactCatDictionaryParser.valuesList_return vl = null;


        Object VALUES48_tree=null;
        Object char_literal49_tree=null;
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_VALUES=new RewriteRuleTokenStream(adaptor,"token VALUES");
        RewriteRuleSubtreeStream stream_valuesList=new RewriteRuleSubtreeStream(adaptor,"rule valuesList");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:144:2: ( VALUES ':' vl= valuesList -> ^( VALUES $vl) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:144:4: VALUES ':' vl= valuesList
            {
            VALUES48=(Token)match(input,VALUES,FOLLOW_VALUES_in_allowedValues669);  
            stream_VALUES.add(VALUES48);

            char_literal49=(Token)match(input,74,FOLLOW_74_in_allowedValues671);  
            stream_74.add(char_literal49);

            pushFollow(FOLLOW_valuesList_in_allowedValues675);
            vl=valuesList();

            state._fsp--;

            stream_valuesList.add(vl.getTree());


            // AST REWRITE
            // elements: vl, VALUES
            // token labels: 
            // rule labels: vl, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_vl=new RewriteRuleSubtreeStream(adaptor,"rule vl",vl!=null?vl.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 145:3: -> ^( VALUES $vl)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:145:6: ^( VALUES $vl)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_VALUES.nextNode(), root_1);

                adaptor.addChild(root_1, stream_vl.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "allowedValues"

    public static class valuesList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "valuesList"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:148:1: valuesList : (bv= basicValue -> ^( SINGLE_VALUE $bv) | '[' bv= basicValue ( ',' rbv+= basicValue )* ']' -> ^( ARRAY_VALUES $bv ( $rbv)* ) );
    public final CompactCatDictionaryParser.valuesList_return valuesList() throws RecognitionException {
        CompactCatDictionaryParser.valuesList_return retval = new CompactCatDictionaryParser.valuesList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal50=null;
        Token char_literal51=null;
        Token char_literal52=null;
        List list_rbv=null;
        CompactCatDictionaryParser.basicValue_return bv = null;

        RuleReturnScope rbv = null;
        Object char_literal50_tree=null;
        Object char_literal51_tree=null;
        Object char_literal52_tree=null;
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_basicValue=new RewriteRuleSubtreeStream(adaptor,"rule basicValue");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:149:2: (bv= basicValue -> ^( SINGLE_VALUE $bv) | '[' bv= basicValue ( ',' rbv+= basicValue )* ']' -> ^( ARRAY_VALUES $bv ( $rbv)* ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==STRING||(LA12_0>=INTEGER && LA12_0<=NULL)||LA12_0==DATE_YEAR_MONTH_DAY) ) {
                alt12=1;
            }
            else if ( (LA12_0==75) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:149:4: bv= basicValue
                    {
                    pushFollow(FOLLOW_basicValue_in_valuesList701);
                    bv=basicValue();

                    state._fsp--;

                    stream_basicValue.add(bv.getTree());


                    // AST REWRITE
                    // elements: bv
                    // token labels: 
                    // rule labels: retval, bv
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_bv=new RewriteRuleSubtreeStream(adaptor,"rule bv",bv!=null?bv.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 150:3: -> ^( SINGLE_VALUE $bv)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:150:6: ^( SINGLE_VALUE $bv)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SINGLE_VALUE, "SINGLE_VALUE"), root_1);

                        adaptor.addChild(root_1, stream_bv.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:152:4: '[' bv= basicValue ( ',' rbv+= basicValue )* ']'
                    {
                    char_literal50=(Token)match(input,75,FOLLOW_75_in_valuesList720);  
                    stream_75.add(char_literal50);

                    pushFollow(FOLLOW_basicValue_in_valuesList724);
                    bv=basicValue();

                    state._fsp--;

                    stream_basicValue.add(bv.getTree());
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:152:22: ( ',' rbv+= basicValue )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==71) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:152:23: ',' rbv+= basicValue
                    	    {
                    	    char_literal51=(Token)match(input,71,FOLLOW_71_in_valuesList727);  
                    	    stream_71.add(char_literal51);

                    	    pushFollow(FOLLOW_basicValue_in_valuesList731);
                    	    rbv=basicValue();

                    	    state._fsp--;

                    	    stream_basicValue.add(rbv.getTree());
                    	    if (list_rbv==null) list_rbv=new ArrayList();
                    	    list_rbv.add(rbv.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    char_literal52=(Token)match(input,76,FOLLOW_76_in_valuesList735);  
                    stream_76.add(char_literal52);



                    // AST REWRITE
                    // elements: rbv, bv
                    // token labels: 
                    // rule labels: retval, bv
                    // token list labels: 
                    // rule list labels: rbv
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_bv=new RewriteRuleSubtreeStream(adaptor,"rule bv",bv!=null?bv.tree:null);
                    RewriteRuleSubtreeStream stream_rbv=new RewriteRuleSubtreeStream(adaptor,"token rbv",list_rbv);
                    root_0 = (Object)adaptor.nil();
                    // 153:3: -> ^( ARRAY_VALUES $bv ( $rbv)* )
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:153:6: ^( ARRAY_VALUES $bv ( $rbv)* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARRAY_VALUES, "ARRAY_VALUES"), root_1);

                        adaptor.addChild(root_1, stream_bv.nextTree());
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:153:25: ( $rbv)*
                        while ( stream_rbv.hasNext() ) {
                            adaptor.addChild(root_1, stream_rbv.nextTree());

                        }
                        stream_rbv.reset();

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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "valuesList"

    public static class basicValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "basicValue"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:156:1: basicValue : ( INTEGER | STRING | date | BOOLEAN | REAL | NULL ) ;
    public final CompactCatDictionaryParser.basicValue_return basicValue() throws RecognitionException {
        CompactCatDictionaryParser.basicValue_return retval = new CompactCatDictionaryParser.basicValue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INTEGER53=null;
        Token STRING54=null;
        Token BOOLEAN56=null;
        Token REAL57=null;
        Token NULL58=null;
        CompactCatDictionaryParser.date_return date55 = null;


        Object INTEGER53_tree=null;
        Object STRING54_tree=null;
        Object BOOLEAN56_tree=null;
        Object REAL57_tree=null;
        Object NULL58_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:2: ( ( INTEGER | STRING | date | BOOLEAN | REAL | NULL ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:4: ( INTEGER | STRING | date | BOOLEAN | REAL | NULL )
            {
            root_0 = (Object)adaptor.nil();

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:4: ( INTEGER | STRING | date | BOOLEAN | REAL | NULL )
            int alt13=6;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt13=1;
                }
                break;
            case STRING:
                {
                alt13=2;
                }
                break;
            case DATE_YEAR_MONTH_DAY:
                {
                alt13=3;
                }
                break;
            case BOOLEAN:
                {
                alt13=4;
                }
                break;
            case REAL:
                {
                alt13=5;
                }
                break;
            case NULL:
                {
                alt13=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:5: INTEGER
                    {
                    INTEGER53=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_basicValue764); 
                    INTEGER53_tree = (Object)adaptor.create(INTEGER53);
                    adaptor.addChild(root_0, INTEGER53_tree);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:15: STRING
                    {
                    STRING54=(Token)match(input,STRING,FOLLOW_STRING_in_basicValue768); 
                    STRING54_tree = (Object)adaptor.create(STRING54);
                    adaptor.addChild(root_0, STRING54_tree);


                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:24: date
                    {
                    pushFollow(FOLLOW_date_in_basicValue772);
                    date55=date();

                    state._fsp--;

                    adaptor.addChild(root_0, date55.getTree());

                    }
                    break;
                case 4 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:31: BOOLEAN
                    {
                    BOOLEAN56=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_basicValue776); 
                    BOOLEAN56_tree = (Object)adaptor.create(BOOLEAN56);
                    adaptor.addChild(root_0, BOOLEAN56_tree);


                    }
                    break;
                case 5 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:41: REAL
                    {
                    REAL57=(Token)match(input,REAL,FOLLOW_REAL_in_basicValue780); 
                    REAL57_tree = (Object)adaptor.create(REAL57);
                    adaptor.addChild(root_0, REAL57_tree);


                    }
                    break;
                case 6 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:157:48: NULL
                    {
                    NULL58=(Token)match(input,NULL,FOLLOW_NULL_in_basicValue784); 
                    NULL58_tree = (Object)adaptor.create(NULL58);
                    adaptor.addChild(root_0, NULL58_tree);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "basicValue"

    public static class minimumValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "minimumValue"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:161:1: minimumValue : MINIMUM ':' (i= INTEGER | r= REAL ) -> ^( MINIMUM ( $i)? ( $r)? ) ;
    public final CompactCatDictionaryParser.minimumValue_return minimumValue() throws RecognitionException {
        CompactCatDictionaryParser.minimumValue_return retval = new CompactCatDictionaryParser.minimumValue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token r=null;
        Token MINIMUM59=null;
        Token char_literal60=null;

        Object i_tree=null;
        Object r_tree=null;
        Object MINIMUM59_tree=null;
        Object char_literal60_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");
        RewriteRuleTokenStream stream_REAL=new RewriteRuleTokenStream(adaptor,"token REAL");
        RewriteRuleTokenStream stream_MINIMUM=new RewriteRuleTokenStream(adaptor,"token MINIMUM");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:162:2: ( MINIMUM ':' (i= INTEGER | r= REAL ) -> ^( MINIMUM ( $i)? ( $r)? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:162:4: MINIMUM ':' (i= INTEGER | r= REAL )
            {
            MINIMUM59=(Token)match(input,MINIMUM,FOLLOW_MINIMUM_in_minimumValue801);  
            stream_MINIMUM.add(MINIMUM59);

            char_literal60=(Token)match(input,74,FOLLOW_74_in_minimumValue803);  
            stream_74.add(char_literal60);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:162:16: (i= INTEGER | r= REAL )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==INTEGER) ) {
                alt14=1;
            }
            else if ( (LA14_0==REAL) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:162:17: i= INTEGER
                    {
                    i=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_minimumValue808);  
                    stream_INTEGER.add(i);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:162:29: r= REAL
                    {
                    r=(Token)match(input,REAL,FOLLOW_REAL_in_minimumValue814);  
                    stream_REAL.add(r);


                    }
                    break;

            }



            // AST REWRITE
            // elements: i, MINIMUM, r
            // token labels: r, i
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_r=new RewriteRuleTokenStream(adaptor,"token r",r);
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 163:3: -> ^( MINIMUM ( $i)? ( $r)? )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:163:6: ^( MINIMUM ( $i)? ( $r)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_MINIMUM.nextNode(), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:163:16: ( $i)?
                if ( stream_i.hasNext() ) {
                    adaptor.addChild(root_1, stream_i.nextNode());

                }
                stream_i.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:163:20: ( $r)?
                if ( stream_r.hasNext() ) {
                    adaptor.addChild(root_1, stream_r.nextNode());

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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "minimumValue"

    public static class maximumValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "maximumValue"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:166:1: maximumValue : MAXIMUM ':' (i= INTEGER | r= REAL ) -> ^( MAXIMUM ( $i)? ( $r)? ) ;
    public final CompactCatDictionaryParser.maximumValue_return maximumValue() throws RecognitionException {
        CompactCatDictionaryParser.maximumValue_return retval = new CompactCatDictionaryParser.maximumValue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        Token r=null;
        Token MAXIMUM61=null;
        Token char_literal62=null;

        Object i_tree=null;
        Object r_tree=null;
        Object MAXIMUM61_tree=null;
        Object char_literal62_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");
        RewriteRuleTokenStream stream_REAL=new RewriteRuleTokenStream(adaptor,"token REAL");
        RewriteRuleTokenStream stream_MAXIMUM=new RewriteRuleTokenStream(adaptor,"token MAXIMUM");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:167:2: ( MAXIMUM ':' (i= INTEGER | r= REAL ) -> ^( MAXIMUM ( $i)? ( $r)? ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:167:4: MAXIMUM ':' (i= INTEGER | r= REAL )
            {
            MAXIMUM61=(Token)match(input,MAXIMUM,FOLLOW_MAXIMUM_in_maximumValue843);  
            stream_MAXIMUM.add(MAXIMUM61);

            char_literal62=(Token)match(input,74,FOLLOW_74_in_maximumValue845);  
            stream_74.add(char_literal62);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:167:16: (i= INTEGER | r= REAL )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==INTEGER) ) {
                alt15=1;
            }
            else if ( (LA15_0==REAL) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:167:17: i= INTEGER
                    {
                    i=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_maximumValue850);  
                    stream_INTEGER.add(i);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:167:29: r= REAL
                    {
                    r=(Token)match(input,REAL,FOLLOW_REAL_in_maximumValue856);  
                    stream_REAL.add(r);


                    }
                    break;

            }



            // AST REWRITE
            // elements: MAXIMUM, i, r
            // token labels: r, i
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_r=new RewriteRuleTokenStream(adaptor,"token r",r);
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 168:3: -> ^( MAXIMUM ( $i)? ( $r)? )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:168:6: ^( MAXIMUM ( $i)? ( $r)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_MAXIMUM.nextNode(), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:168:16: ( $i)?
                if ( stream_i.hasNext() ) {
                    adaptor.addChild(root_1, stream_i.nextNode());

                }
                stream_i.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:168:20: ( $r)?
                if ( stream_r.hasNext() ) {
                    adaptor.addChild(root_1, stream_r.nextNode());

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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "maximumValue"

    public static class attributeType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attributeType"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:171:1: attributeType : TYPE ':' t= type -> ^( TYPE $t) ;
    public final CompactCatDictionaryParser.attributeType_return attributeType() throws RecognitionException {
        CompactCatDictionaryParser.attributeType_return retval = new CompactCatDictionaryParser.attributeType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TYPE63=null;
        Token char_literal64=null;
        CompactCatDictionaryParser.type_return t = null;


        Object TYPE63_tree=null;
        Object char_literal64_tree=null;
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_TYPE=new RewriteRuleTokenStream(adaptor,"token TYPE");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:172:2: ( TYPE ':' t= type -> ^( TYPE $t) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:172:4: TYPE ':' t= type
            {
            TYPE63=(Token)match(input,TYPE,FOLLOW_TYPE_in_attributeType889);  
            stream_TYPE.add(TYPE63);

            char_literal64=(Token)match(input,74,FOLLOW_74_in_attributeType891);  
            stream_74.add(char_literal64);

            pushFollow(FOLLOW_type_in_attributeType895);
            t=type();

            state._fsp--;

            stream_type.add(t.getTree());


            // AST REWRITE
            // elements: TYPE, t
            // token labels: 
            // rule labels: retval, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 173:3: -> ^( TYPE $t)
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:173:6: ^( TYPE $t)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_TYPE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_t.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attributeType"

    public static class type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:176:1: type : (bt= basicType -> ^( BASIC_TYPE $bt) | at= arrayType -> ^( ARRAY_TYPE $at) );
    public final CompactCatDictionaryParser.type_return type() throws RecognitionException {
        CompactCatDictionaryParser.type_return retval = new CompactCatDictionaryParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CompactCatDictionaryParser.basicType_return bt = null;

        CompactCatDictionaryParser.arrayType_return at = null;


        RewriteRuleSubtreeStream stream_arrayType=new RewriteRuleSubtreeStream(adaptor,"rule arrayType");
        RewriteRuleSubtreeStream stream_basicType=new RewriteRuleSubtreeStream(adaptor,"rule basicType");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:177:2: (bt= basicType -> ^( BASIC_TYPE $bt) | at= arrayType -> ^( ARRAY_TYPE $at) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==DATE_TYPE||(LA16_0>=INTEGER_TYPE && LA16_0<=REAL_TYPE)) ) {
                alt16=1;
            }
            else if ( (LA16_0==ARRAY_TYPE) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:177:4: bt= basicType
                    {
                    pushFollow(FOLLOW_basicType_in_type921);
                    bt=basicType();

                    state._fsp--;

                    stream_basicType.add(bt.getTree());


                    // AST REWRITE
                    // elements: bt
                    // token labels: 
                    // rule labels: retval, bt
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_bt=new RewriteRuleSubtreeStream(adaptor,"rule bt",bt!=null?bt.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 178:3: -> ^( BASIC_TYPE $bt)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:178:6: ^( BASIC_TYPE $bt)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BASIC_TYPE, "BASIC_TYPE"), root_1);

                        adaptor.addChild(root_1, stream_bt.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:180:5: at= arrayType
                    {
                    pushFollow(FOLLOW_arrayType_in_type943);
                    at=arrayType();

                    state._fsp--;

                    stream_arrayType.add(at.getTree());


                    // AST REWRITE
                    // elements: at
                    // token labels: 
                    // rule labels: retval, at
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_at=new RewriteRuleSubtreeStream(adaptor,"rule at",at!=null?at.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 181:3: -> ^( ARRAY_TYPE $at)
                    {
                        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:181:6: ^( ARRAY_TYPE $at)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARRAY_TYPE, "ARRAY_TYPE"), root_1);

                        adaptor.addChild(root_1, stream_at.nextTree());

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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type"

    public static class basicType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "basicType"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:184:1: basicType : ( INTEGER_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | BOOLEAN_TYPE | REAL_TYPE ) ;
    public final CompactCatDictionaryParser.basicType_return basicType() throws RecognitionException {
        CompactCatDictionaryParser.basicType_return retval = new CompactCatDictionaryParser.basicType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set65=null;

        Object set65_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:185:2: ( ( INTEGER_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | BOOLEAN_TYPE | REAL_TYPE ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:185:5: ( INTEGER_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | BOOLEAN_TYPE | REAL_TYPE )
            {
            root_0 = (Object)adaptor.nil();

            set65=(Token)input.LT(1);
            if ( input.LA(1)==DATE_TYPE||(input.LA(1)>=INTEGER_TYPE && input.LA(1)<=REAL_TYPE) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set65));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "basicType"

    public static class arrayType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayType"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:188:1: arrayType : ARRAY_TYPE '<' bt= basicType '>' -> $bt;
    public final CompactCatDictionaryParser.arrayType_return arrayType() throws RecognitionException {
        CompactCatDictionaryParser.arrayType_return retval = new CompactCatDictionaryParser.arrayType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ARRAY_TYPE66=null;
        Token char_literal67=null;
        Token char_literal68=null;
        CompactCatDictionaryParser.basicType_return bt = null;


        Object ARRAY_TYPE66_tree=null;
        Object char_literal67_tree=null;
        Object char_literal68_tree=null;
        RewriteRuleTokenStream stream_ARRAY_TYPE=new RewriteRuleTokenStream(adaptor,"token ARRAY_TYPE");
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleSubtreeStream stream_basicType=new RewriteRuleSubtreeStream(adaptor,"rule basicType");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:189:2: ( ARRAY_TYPE '<' bt= basicType '>' -> $bt)
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:189:4: ARRAY_TYPE '<' bt= basicType '>'
            {
            ARRAY_TYPE66=(Token)match(input,ARRAY_TYPE,FOLLOW_ARRAY_TYPE_in_arrayType1002);  
            stream_ARRAY_TYPE.add(ARRAY_TYPE66);

            char_literal67=(Token)match(input,77,FOLLOW_77_in_arrayType1004);  
            stream_77.add(char_literal67);

            pushFollow(FOLLOW_basicType_in_arrayType1008);
            bt=basicType();

            state._fsp--;

            stream_basicType.add(bt.getTree());
            char_literal68=(Token)match(input,78,FOLLOW_78_in_arrayType1010);  
            stream_78.add(char_literal68);



            // AST REWRITE
            // elements: bt
            // token labels: 
            // rule labels: retval, bt
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_bt=new RewriteRuleSubtreeStream(adaptor,"rule bt",bt!=null?bt.tree:null);

            root_0 = (Object)adaptor.nil();
            // 190:3: -> $bt
            {
                adaptor.addChild(root_0, stream_bt.nextTree());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arrayType"

    public static class date_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "date"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:193:1: date : DATE_YEAR_MONTH_DAY -> ^( DATE DATE_YEAR_MONTH_DAY ) ;
    public final CompactCatDictionaryParser.date_return date() throws RecognitionException {
        CompactCatDictionaryParser.date_return retval = new CompactCatDictionaryParser.date_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DATE_YEAR_MONTH_DAY69=null;

        Object DATE_YEAR_MONTH_DAY69_tree=null;
        RewriteRuleTokenStream stream_DATE_YEAR_MONTH_DAY=new RewriteRuleTokenStream(adaptor,"token DATE_YEAR_MONTH_DAY");

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:194:2: ( DATE_YEAR_MONTH_DAY -> ^( DATE DATE_YEAR_MONTH_DAY ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:194:5: DATE_YEAR_MONTH_DAY
            {
            DATE_YEAR_MONTH_DAY69=(Token)match(input,DATE_YEAR_MONTH_DAY,FOLLOW_DATE_YEAR_MONTH_DAY_in_date1035);  
            stream_DATE_YEAR_MONTH_DAY.add(DATE_YEAR_MONTH_DAY69);



            // AST REWRITE
            // elements: DATE_YEAR_MONTH_DAY
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 195:3: -> ^( DATE DATE_YEAR_MONTH_DAY )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:195:6: ^( DATE DATE_YEAR_MONTH_DAY )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DATE, "DATE"), root_1);

                adaptor.addChild(root_1, stream_DATE_YEAR_MONTH_DAY.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "date"

    public static class attributeReference_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attributeReference"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:198:1: attributeReference : REFERENCES ':' entity= ID '(' (attId= ID | attName= reservedName | attString= STRING ) ')' -> ^( REFERENCES ^( ENTITY $entity) ^( ATTRIBUTE ( $attId)? ( $attName)? ( $attString)? ) ) ;
    public final CompactCatDictionaryParser.attributeReference_return attributeReference() throws RecognitionException {
        CompactCatDictionaryParser.attributeReference_return retval = new CompactCatDictionaryParser.attributeReference_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token entity=null;
        Token attId=null;
        Token attString=null;
        Token REFERENCES70=null;
        Token char_literal71=null;
        Token char_literal72=null;
        Token char_literal73=null;
        CompactCatDictionaryParser.reservedName_return attName = null;


        Object entity_tree=null;
        Object attId_tree=null;
        Object attString_tree=null;
        Object REFERENCES70_tree=null;
        Object char_literal71_tree=null;
        Object char_literal72_tree=null;
        Object char_literal73_tree=null;
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_REFERENCES=new RewriteRuleTokenStream(adaptor,"token REFERENCES");
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleSubtreeStream stream_reservedName=new RewriteRuleSubtreeStream(adaptor,"rule reservedName");
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:199:2: ( REFERENCES ':' entity= ID '(' (attId= ID | attName= reservedName | attString= STRING ) ')' -> ^( REFERENCES ^( ENTITY $entity) ^( ATTRIBUTE ( $attId)? ( $attName)? ( $attString)? ) ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:199:4: REFERENCES ':' entity= ID '(' (attId= ID | attName= reservedName | attString= STRING ) ')'
            {
            REFERENCES70=(Token)match(input,REFERENCES,FOLLOW_REFERENCES_in_attributeReference1059);  
            stream_REFERENCES.add(REFERENCES70);

            char_literal71=(Token)match(input,74,FOLLOW_74_in_attributeReference1061);  
            stream_74.add(char_literal71);

            entity=(Token)match(input,ID,FOLLOW_ID_in_attributeReference1065);  
            stream_ID.add(entity);

            char_literal72=(Token)match(input,79,FOLLOW_79_in_attributeReference1067);  
            stream_79.add(char_literal72);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:199:33: (attId= ID | attName= reservedName | attString= STRING )
            int alt17=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt17=1;
                }
                break;
            case NAME:
            case CATALOG:
            case VERSION:
            case DESCRIPTION:
            case DICTIONARY:
            case ATTRIBUTES:
            case VALUES:
            case MINIMUM:
            case MAXIMUM:
            case TYPE:
            case REFERENCES:
                {
                alt17=2;
                }
                break;
            case STRING:
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
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:199:34: attId= ID
                    {
                    attId=(Token)match(input,ID,FOLLOW_ID_in_attributeReference1072);  
                    stream_ID.add(attId);


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:199:45: attName= reservedName
                    {
                    pushFollow(FOLLOW_reservedName_in_attributeReference1078);
                    attName=reservedName();

                    state._fsp--;

                    stream_reservedName.add(attName.getTree());

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:199:68: attString= STRING
                    {
                    attString=(Token)match(input,STRING,FOLLOW_STRING_in_attributeReference1084);  
                    stream_STRING.add(attString);


                    }
                    break;

            }

            char_literal73=(Token)match(input,80,FOLLOW_80_in_attributeReference1087);  
            stream_80.add(char_literal73);



            // AST REWRITE
            // elements: attString, entity, REFERENCES, attId, attName
            // token labels: attString, entity, attId
            // rule labels: retval, attName
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_attString=new RewriteRuleTokenStream(adaptor,"token attString",attString);
            RewriteRuleTokenStream stream_entity=new RewriteRuleTokenStream(adaptor,"token entity",entity);
            RewriteRuleTokenStream stream_attId=new RewriteRuleTokenStream(adaptor,"token attId",attId);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_attName=new RewriteRuleSubtreeStream(adaptor,"rule attName",attName!=null?attName.tree:null);

            root_0 = (Object)adaptor.nil();
            // 200:3: -> ^( REFERENCES ^( ENTITY $entity) ^( ATTRIBUTE ( $attId)? ( $attName)? ( $attString)? ) )
            {
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:200:6: ^( REFERENCES ^( ENTITY $entity) ^( ATTRIBUTE ( $attId)? ( $attName)? ( $attString)? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_REFERENCES.nextNode(), root_1);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:200:19: ^( ENTITY $entity)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ENTITY, "ENTITY"), root_2);

                adaptor.addChild(root_2, stream_entity.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:200:37: ^( ATTRIBUTE ( $attId)? ( $attName)? ( $attString)? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ATTRIBUTE, "ATTRIBUTE"), root_2);

                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:200:49: ( $attId)?
                if ( stream_attId.hasNext() ) {
                    adaptor.addChild(root_2, stream_attId.nextNode());

                }
                stream_attId.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:200:57: ( $attName)?
                if ( stream_attName.hasNext() ) {
                    adaptor.addChild(root_2, stream_attName.nextTree());

                }
                stream_attName.reset();
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:200:67: ( $attString)?
                if ( stream_attString.hasNext() ) {
                    adaptor.addChild(root_2, stream_attString.nextNode());

                }
                stream_attString.reset();

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attributeReference"

    public static class reservedName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "reservedName"
    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:204:1: reservedName : ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) ;
    public final CompactCatDictionaryParser.reservedName_return reservedName() throws RecognitionException {
        CompactCatDictionaryParser.reservedName_return retval = new CompactCatDictionaryParser.reservedName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set74=null;

        Object set74_tree=null;

        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:205:2: ( ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:205:4: ( CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION )
            {
            root_0 = (Object)adaptor.nil();

            set74=(Token)input.LT(1);
            if ( input.LA(1)==NAME||(input.LA(1)>=CATALOG && input.LA(1)<=VERSION)||(input.LA(1)>=DESCRIPTION && input.LA(1)<=VALUES)||(input.LA(1)>=MINIMUM && input.LA(1)<=TYPE)||input.LA(1)==REFERENCES ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set74));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reservedName"

    // Delegated rules


 

    public static final BitSet FOLLOW_CATALOG_in_catalog131 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_catalog133 = new BitSet(new long[]{0x0000000000D40040L});
    public static final BitSet FOLLOW_catalogProperty_in_catalog137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_71_in_catalog140 = new BitSet(new long[]{0x0000000000D40040L});
    public static final BitSet FOLLOW_catalogProperty_in_catalog144 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_72_in_catalog148 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_catalog150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_catalogProperty184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_version_in_catalogProperty189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_releaseDate_in_catalogProperty194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_description_in_catalogProperty199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dictionary_in_catalogProperty204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERSION_in_version225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_version227 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_STRING_in_version231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATE_TYPE_in_releaseDate254 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_releaseDate256 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_date_in_releaseDate260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_name285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_name287 = new BitSet(new long[]{0x00000081C3EE0040L});
    public static final BitSet FOLLOW_ID_in_name292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reservedName_in_name298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_name304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCRIPTION_in_description338 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_description340 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_STRING_in_description344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DICTIONARY_in_dictionary374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_dictionary376 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_dictionary378 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_entityDictionary_in_dictionary382 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_71_in_dictionary385 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_entityDictionary_in_dictionary389 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_72_in_dictionary393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_entityDictionary426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_entityDictionary428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_entityDictionary430 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_entityProperties_in_entityDictionary436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_entityDictionary438 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_entityAttributes_in_entityDictionary442 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_entityAttributes_in_entityDictionary450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_entityDictionary452 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_entityProperties_in_entityDictionary456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_entityDictionary460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entityProperty_in_entityProperties500 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_entityProperties503 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_entityProperty_in_entityProperties505 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_description_in_entityProperty520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTES_in_entityAttributes539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_entityAttributes541 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_entityAttributes543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_entityAttribute_in_entityAttributes547 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001080L});
    public static final BitSet FOLLOW_71_in_entityAttributes550 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_entityAttribute_in_entityAttributes554 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001080L});
    public static final BitSet FOLLOW_76_in_entityAttributes558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_entityAttribute583 = new BitSet(new long[]{0x00000081C2400040L});
    public static final BitSet FOLLOW_attributeValue_in_entityAttribute587 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_71_in_entityAttribute590 = new BitSet(new long[]{0x00000081C2400040L});
    public static final BitSet FOLLOW_attributeValue_in_entityAttribute594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_72_in_entityAttribute598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_attributeValue626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_description_in_attributeValue631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_allowedValues_in_attributeValue636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_minimumValue_in_attributeValue641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_maximumValue_in_attributeValue646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attributeType_in_attributeValue651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attributeReference_in_attributeValue656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUES_in_allowedValues669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_allowedValues671 = new BitSet(new long[]{0x000000403C080000L,0x0000000000000800L});
    public static final BitSet FOLLOW_valuesList_in_allowedValues675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basicValue_in_valuesList701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_valuesList720 = new BitSet(new long[]{0x000000403C080000L});
    public static final BitSet FOLLOW_basicValue_in_valuesList724 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001080L});
    public static final BitSet FOLLOW_71_in_valuesList727 = new BitSet(new long[]{0x000000403C080000L});
    public static final BitSet FOLLOW_basicValue_in_valuesList731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001080L});
    public static final BitSet FOLLOW_76_in_valuesList735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_basicValue764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_basicValue768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_in_basicValue772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_basicValue776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_basicValue780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_basicValue784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINIMUM_in_minimumValue801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_minimumValue803 = new BitSet(new long[]{0x0000000014000000L});
    public static final BitSet FOLLOW_INTEGER_in_minimumValue808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_minimumValue814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAXIMUM_in_maximumValue843 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_maximumValue845 = new BitSet(new long[]{0x0000000014000000L});
    public static final BitSet FOLLOW_INTEGER_in_maximumValue850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_maximumValue856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_attributeType889 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_attributeType891 = new BitSet(new long[]{0x0000003E00110000L});
    public static final BitSet FOLLOW_type_in_attributeType895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_basicType_in_type921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayType_in_type943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_basicType968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARRAY_TYPE_in_arrayType1002 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_arrayType1004 = new BitSet(new long[]{0x0000003E00100000L});
    public static final BitSet FOLLOW_basicType_in_arrayType1008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_arrayType1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DATE_YEAR_MONTH_DAY_in_date1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REFERENCES_in_attributeReference1059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_attributeReference1061 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ID_in_attributeReference1065 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_attributeReference1067 = new BitSet(new long[]{0x00000081C3EE0040L});
    public static final BitSet FOLLOW_ID_in_attributeReference1072 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_reservedName_in_attributeReference1078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_STRING_in_attributeReference1084 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_attributeReference1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_reservedName1140 = new BitSet(new long[]{0x0000000000000002L});

}