// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g 2011-10-20 14:12:44
 
	package net.explorercat.compactcat.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CompactCatDataLexer extends Lexer {
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

    public CompactCatDataLexer() {;} 
    public CompactCatDataLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CompactCatDataLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g"; }

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:11:7: ( '{' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:11:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:12:7: ( ',' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:12:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:13:7: ( '}' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:13:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:14:7: ( ';' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:14:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:15:7: ( ':' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:15:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:16:7: ( '[' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:16:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:17:7: ( ']' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:17:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "CATALOG"
    public final void mCATALOG() throws RecognitionException {
        try {
            int _type = CATALOG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:240:9: ( C A T A L O G )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:240:11: C A T A L O G
            {
            mC(); 
            mA(); 
            mT(); 
            mA(); 
            mL(); 
            mO(); 
            mG(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CATALOG"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:244:2: ( T R U E | F A L S E )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='T'||LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='F'||LA1_0=='f') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:244:4: T R U E
                    {
                    mT(); 
                    mR(); 
                    mU(); 
                    mE(); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:245:4: F A L S E
                    {
                    mF(); 
                    mA(); 
                    mL(); 
                    mS(); 
                    mE(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:249:2: ( N U L L )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:249:4: N U L L
            {
            mN(); 
            mU(); 
            mL(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:254:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:254:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:254:27: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='+'||LA2_0=='-'||(LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:
            	    {
            	    if ( input.LA(1)=='+'||input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:259:2: ( '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"' | '\\'' (~ ( '\\'' | '\\n' | '\\r' ) )* '\\'' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\"') ) {
                alt5=1;
            }
            else if ( (LA5_0=='\'') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:259:4: '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"'
                    {
                    match('\"'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:259:8: (~ ( '\"' | '\\n' | '\\r' ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='\u0000' && LA3_0<='\t')||(LA3_0>='\u000B' && LA3_0<='\f')||(LA3_0>='\u000E' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='\uFFFF')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:259:9: ~ ( '\"' | '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:260:4: '\\'' (~ ( '\\'' | '\\n' | '\\r' ) )* '\\''
                    {
                    match('\''); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:260:9: (~ ( '\\'' | '\\n' | '\\r' ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='\u0000' && LA4_0<='\t')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='&')||(LA4_0>='(' && LA4_0<='\uFFFF')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:260:10: ~ ( '\\'' | '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:265:2: ( ( '-' )? ( NUMBER )+ )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:265:4: ( '-' )? ( NUMBER )+
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:265:4: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:265:5: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:265:11: ( NUMBER )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:265:11: NUMBER
            	    {
            	    mNUMBER(); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
        try {
            int _type = REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:2: ( ( '-' )? ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )? | ( '-' )? '.' ( NUMBER )+ ( EXPONENT )? | ( '-' )? ( NUMBER )+ EXPONENT )
            int alt17=3;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:6: ( '-' )? ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )?
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:6: ( '-' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='-') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:7: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:13: ( NUMBER )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:13: NUMBER
                    	    {
                    	    mNUMBER(); 

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

                    match('.'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:25: ( NUMBER )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:25: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:33: ( EXPONENT )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='E'||LA11_0=='e') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:269:33: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:270:9: ( '-' )? '.' ( NUMBER )+ ( EXPONENT )?
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:270:9: ( '-' )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='-') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:270:10: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    match('.'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:270:20: ( NUMBER )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:270:20: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:270:28: ( EXPONENT )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='E'||LA14_0=='e') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:270:28: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:271:9: ( '-' )? ( NUMBER )+ EXPONENT
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:271:9: ( '-' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='-') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:271:10: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:271:16: ( NUMBER )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:271:16: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt16 >= 1 ) break loop16;
                                EarlyExitException eee =
                                    new EarlyExitException(16, input);
                                throw eee;
                        }
                        cnt16++;
                    } while (true);

                    mEXPONENT(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REAL"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:275:5: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:275:7: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:275:21: ( '+' | '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='+'||LA18_0=='-') ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:275:36: ( '0' .. '9' )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:275:38: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:279:2: ( ( '0' .. '9' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:279:4: ( '0' .. '9' )
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:279:4: ( '0' .. '9' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:279:5: '0' .. '9'
            {
            matchRange('0','9'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "DATE_YEAR_MONTH_DAY"
    public final void mDATE_YEAR_MONTH_DAY() throws RecognitionException {
        try {
            int _type = DATE_YEAR_MONTH_DAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:283:2: ( NUMBER NUMBER NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:283:4: NUMBER NUMBER NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER
            {
            mNUMBER(); 
            mNUMBER(); 
            mNUMBER(); 
            mNUMBER(); 
            if ( input.LA(1)=='-'||input.LA(1)=='/' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            mNUMBER(); 
            mNUMBER(); 
            if ( input.LA(1)=='-'||input.LA(1)=='/' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            mNUMBER(); 
            mNUMBER(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DATE_YEAR_MONTH_DAY"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )?
            {
            match("//"); 

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:14: (~ ( '\\n' | '\\r' ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>='\u0000' && LA20_0<='\t')||(LA20_0>='\u000B' && LA20_0<='\f')||(LA20_0>='\u000E' && LA20_0<='\uFFFF')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:14: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:29: ( '\\r\\n' | '\\r' | '\\n' )?
            int alt21=4;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\r') ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1=='\n') ) {
                    alt21=1;
                }
            }
            else if ( (LA21_0=='\n') ) {
                alt21=3;
            }
            switch (alt21) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:30: '\\r\\n'
                    {
                    match("\r\n"); 


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:39: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:288:46: '\\n'
                    {
                    match('\n'); 

                    }
                    break;

            }

             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "BLANK"
    public final void mBLANK() throws RecognitionException {
        try {
            int _type = BLANK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:293:2: ( ( '\\t' | ' ' | '\\n' | '\\r' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:293:4: ( '\\t' | ' ' | '\\n' | '\\r' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLANK"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:297:11: ( ( 'a' | 'A' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:297:12: ( 'a' | 'A' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:298:11: ( ( 'b' | 'B' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:298:12: ( 'b' | 'B' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:299:11: ( ( 'c' | 'C' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:299:12: ( 'c' | 'C' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:300:11: ( ( 'd' | 'D' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:300:12: ( 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:301:11: ( ( 'e' | 'E' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:301:12: ( 'e' | 'E' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:302:11: ( ( 'f' | 'F' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:302:12: ( 'f' | 'F' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:303:11: ( ( 'g' | 'G' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:303:12: ( 'g' | 'G' )
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:304:11: ( ( 'h' | 'H' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:304:12: ( 'h' | 'H' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:305:11: ( ( 'i' | 'I' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:305:12: ( 'i' | 'I' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:306:11: ( ( 'j' | 'J' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:306:12: ( 'j' | 'J' )
            {
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:307:11: ( ( 'k' | 'K' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:307:12: ( 'k' | 'K' )
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:308:11: ( ( 'l' | 'L' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:308:12: ( 'l' | 'L' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:309:11: ( ( 'm' | 'M' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:309:12: ( 'm' | 'M' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:310:11: ( ( 'n' | 'N' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:310:12: ( 'n' | 'N' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:311:11: ( ( 'o' | 'O' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:311:12: ( 'o' | 'O' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:312:11: ( ( 'p' | 'P' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:312:12: ( 'p' | 'P' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:313:11: ( ( 'q' | 'Q' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:313:12: ( 'q' | 'Q' )
            {
            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:314:11: ( ( 'r' | 'R' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:314:12: ( 'r' | 'R' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:315:11: ( ( 's' | 'S' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:315:12: ( 's' | 'S' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:316:11: ( ( 't' | 'T' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:316:12: ( 't' | 'T' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:317:11: ( ( 'u' | 'U' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:317:12: ( 'u' | 'U' )
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:318:11: ( ( 'v' | 'V' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:318:12: ( 'v' | 'V' )
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:319:11: ( ( 'w' | 'W' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:319:12: ( 'w' | 'W' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:320:11: ( ( 'x' | 'X' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:320:12: ( 'x' | 'X' )
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:321:11: ( ( 'y' | 'Y' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:321:12: ( 'y' | 'Y' )
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:322:11: ( ( 'z' | 'Z' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:322:12: ( 'z' | 'Z' )
            {
            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Z"

    public void mTokens() throws RecognitionException {
        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:8: ( T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | CATALOG | BOOLEAN | NULL | ID | STRING | INTEGER | REAL | DATE_YEAR_MONTH_DAY | LINE_COMMENT | BLANK )
        int alt22=17;
        alt22 = dfa22.predict(input);
        switch (alt22) {
            case 1 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:10: T__42
                {
                mT__42(); 

                }
                break;
            case 2 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:16: T__43
                {
                mT__43(); 

                }
                break;
            case 3 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:22: T__44
                {
                mT__44(); 

                }
                break;
            case 4 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:28: T__45
                {
                mT__45(); 

                }
                break;
            case 5 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:34: T__46
                {
                mT__46(); 

                }
                break;
            case 6 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:40: T__47
                {
                mT__47(); 

                }
                break;
            case 7 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:46: T__48
                {
                mT__48(); 

                }
                break;
            case 8 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:52: CATALOG
                {
                mCATALOG(); 

                }
                break;
            case 9 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:60: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 10 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:68: NULL
                {
                mNULL(); 

                }
                break;
            case 11 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:73: ID
                {
                mID(); 

                }
                break;
            case 12 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:76: STRING
                {
                mSTRING(); 

                }
                break;
            case 13 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:83: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 14 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:91: REAL
                {
                mREAL(); 

                }
                break;
            case 15 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:96: DATE_YEAR_MONTH_DAY
                {
                mDATE_YEAR_MONTH_DAY(); 

                }
                break;
            case 16 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:116: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 17 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatData.g:1:129: BLANK
                {
                mBLANK(); 

                }
                break;

        }

    }


    protected DFA17 dfa17 = new DFA17(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA17_eotS =
        "\6\uffff";
    static final String DFA17_eofS =
        "\6\uffff";
    static final String DFA17_minS =
        "\1\55\2\56\3\uffff";
    static final String DFA17_maxS =
        "\2\71\1\145\3\uffff";
    static final String DFA17_acceptS =
        "\3\uffff\1\2\1\1\1\3";
    static final String DFA17_specialS =
        "\6\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\1\1\3\1\uffff\12\2",
            "\1\3\1\uffff\12\2",
            "\1\4\1\uffff\12\2\13\uffff\1\5\37\uffff\1\5",
            "",
            "",
            ""
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "268:1: REAL : ( ( '-' )? ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )? | ( '-' )? '.' ( NUMBER )+ ( EXPONENT )? | ( '-' )? ( NUMBER )+ EXPONENT );";
        }
    }
    static final String DFA22_eotS =
        "\10\uffff\4\14\3\uffff\1\30\3\uffff\4\14\1\30\1\uffff\1\30\4\14"+
        "\1\30\1\14\1\45\1\14\1\47\1\30\1\14\1\uffff\1\45\2\uffff\1\14\1"+
        "\53\1\uffff";
    static final String DFA22_eofS =
        "\54\uffff";
    static final String DFA22_minS =
        "\1\11\7\uffff\1\101\1\122\1\101\1\125\2\uffff\2\56\3\uffff\1\124"+
        "\1\125\2\114\1\56\1\uffff\1\56\1\101\1\105\1\123\1\114\1\56\1\114"+
        "\1\53\1\105\1\53\1\55\1\117\1\uffff\1\53\2\uffff\1\107\1\53\1\uffff";
    static final String DFA22_maxS =
        "\1\175\7\uffff\1\141\1\162\1\141\1\165\2\uffff\1\71\1\145\3\uffff"+
        "\1\164\1\165\2\154\1\145\1\uffff\1\145\1\141\1\145\1\163\1\154\1"+
        "\145\1\154\1\172\1\145\1\172\1\145\1\157\1\uffff\1\172\2\uffff\1"+
        "\147\1\172\1\uffff";
    static final String DFA22_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\4\uffff\1\13\1\14\2\uffff\1"+
        "\16\1\20\1\21\5\uffff\1\15\14\uffff\1\11\1\uffff\1\12\1\17\2\uffff"+
        "\1\10";
    static final String DFA22_specialS =
        "\54\uffff}>";
    static final String[] DFA22_transitionS = {
            "\2\22\2\uffff\1\22\22\uffff\1\22\1\uffff\1\15\4\uffff\1\15\4"+
            "\uffff\1\2\1\16\1\20\1\21\12\17\1\5\1\4\5\uffff\2\14\1\10\2"+
            "\14\1\12\7\14\1\13\5\14\1\11\6\14\1\6\1\uffff\1\7\1\uffff\1"+
            "\14\1\uffff\2\14\1\10\2\14\1\12\7\14\1\13\5\14\1\11\6\14\1\1"+
            "\1\uffff\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23\37\uffff\1\23",
            "\1\24\37\uffff\1\24",
            "\1\25\37\uffff\1\25",
            "\1\26\37\uffff\1\26",
            "",
            "",
            "\1\20\1\uffff\12\27",
            "\1\20\1\uffff\12\31\13\uffff\1\20\37\uffff\1\20",
            "",
            "",
            "",
            "\1\32\37\uffff\1\32",
            "\1\33\37\uffff\1\33",
            "\1\34\37\uffff\1\34",
            "\1\35\37\uffff\1\35",
            "\1\20\1\uffff\12\27\13\uffff\1\20\37\uffff\1\20",
            "",
            "\1\20\1\uffff\12\36\13\uffff\1\20\37\uffff\1\20",
            "\1\37\37\uffff\1\37",
            "\1\40\37\uffff\1\40",
            "\1\41\37\uffff\1\41",
            "\1\42\37\uffff\1\42",
            "\1\20\1\uffff\12\43\13\uffff\1\20\37\uffff\1\20",
            "\1\44\37\uffff\1\44",
            "\1\14\1\uffff\1\14\2\uffff\12\14\7\uffff\32\14\4\uffff\1\14"+
            "\1\uffff\32\14",
            "\1\46\37\uffff\1\46",
            "\1\14\1\uffff\1\14\2\uffff\12\14\7\uffff\32\14\4\uffff\1\14"+
            "\1\uffff\32\14",
            "\1\50\1\20\1\50\12\27\13\uffff\1\20\37\uffff\1\20",
            "\1\51\37\uffff\1\51",
            "",
            "\1\14\1\uffff\1\14\2\uffff\12\14\7\uffff\32\14\4\uffff\1\14"+
            "\1\uffff\32\14",
            "",
            "",
            "\1\52\37\uffff\1\52",
            "\1\14\1\uffff\1\14\2\uffff\12\14\7\uffff\32\14\4\uffff\1\14"+
            "\1\uffff\32\14",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | CATALOG | BOOLEAN | NULL | ID | STRING | INTEGER | REAL | DATE_YEAR_MONTH_DAY | LINE_COMMENT | BLANK );";
        }
    }
 

}