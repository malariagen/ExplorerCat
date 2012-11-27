// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g 2011-10-20 14:12:43
 
	package net.explorercat.compactcat.parser.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CompactCatDictionaryLexer extends Lexer {
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

    public CompactCatDictionaryLexer() {;} 
    public CompactCatDictionaryLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CompactCatDictionaryLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g"; }

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:11:7: ( '{' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:11:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:12:7: ( ',' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:12:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:13:7: ( '}' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:13:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:14:7: ( ';' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:14:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:15:7: ( ':' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:15:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:16:7: ( '[' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:16:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:17:7: ( ']' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:17:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:18:7: ( '<' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:18:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:19:7: ( '>' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:19:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:20:7: ( '(' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:20:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:21:7: ( ')' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:21:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "CATALOG"
    public final void mCATALOG() throws RecognitionException {
        try {
            int _type = CATALOG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:216:11: ( C A T A L O G )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:216:13: C A T A L O G
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

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:217:8: ( N A M E )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:217:10: N A M E
            {
            mN(); 
            mA(); 
            mM(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "DICTIONARY"
    public final void mDICTIONARY() throws RecognitionException {
        try {
            int _type = DICTIONARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:218:13: ( D I C T I O N A R Y )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:218:15: D I C T I O N A R Y
            {
            mD(); 
            mI(); 
            mC(); 
            mT(); 
            mI(); 
            mO(); 
            mN(); 
            mA(); 
            mR(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DICTIONARY"

    // $ANTLR start "ATTRIBUTES"
    public final void mATTRIBUTES() throws RecognitionException {
        try {
            int _type = ATTRIBUTES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:219:13: ( A T T R I B U T E S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:219:15: A T T R I B U T E S
            {
            mA(); 
            mT(); 
            mT(); 
            mR(); 
            mI(); 
            mB(); 
            mU(); 
            mT(); 
            mE(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ATTRIBUTES"

    // $ANTLR start "VALUES"
    public final void mVALUES() throws RecognitionException {
        try {
            int _type = VALUES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:220:10: ( V A L U E S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:220:12: V A L U E S
            {
            mV(); 
            mA(); 
            mL(); 
            mU(); 
            mE(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUES"

    // $ANTLR start "REFERENCES"
    public final void mREFERENCES() throws RecognitionException {
        try {
            int _type = REFERENCES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:221:13: ( R E F E R E N C E S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:221:15: R E F E R E N C E S
            {
            mR(); 
            mE(); 
            mF(); 
            mE(); 
            mR(); 
            mE(); 
            mN(); 
            mC(); 
            mE(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REFERENCES"

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:222:8: ( T Y P E )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:222:10: T Y P E
            {
            mT(); 
            mY(); 
            mP(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TYPE"

    // $ANTLR start "MINIMUM"
    public final void mMINIMUM() throws RecognitionException {
        try {
            int _type = MINIMUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:223:11: ( M I N I M U M | M I N )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='M'||LA1_0=='m') ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1=='I'||LA1_1=='i') ) {
                    int LA1_2 = input.LA(3);

                    if ( (LA1_2=='N'||LA1_2=='n') ) {
                        int LA1_3 = input.LA(4);

                        if ( (LA1_3=='I'||LA1_3=='i') ) {
                            alt1=1;
                        }
                        else {
                            alt1=2;}
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:223:13: M I N I M U M
                    {
                    mM(); 
                    mI(); 
                    mN(); 
                    mI(); 
                    mM(); 
                    mU(); 
                    mM(); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:223:29: M I N
                    {
                    mM(); 
                    mI(); 
                    mN(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINIMUM"

    // $ANTLR start "MAXIMUM"
    public final void mMAXIMUM() throws RecognitionException {
        try {
            int _type = MAXIMUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:224:11: ( M A X I M U M | M A X )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='M'||LA2_0=='m') ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='A'||LA2_1=='a') ) {
                    int LA2_2 = input.LA(3);

                    if ( (LA2_2=='X'||LA2_2=='x') ) {
                        int LA2_3 = input.LA(4);

                        if ( (LA2_3=='I'||LA2_3=='i') ) {
                            alt2=1;
                        }
                        else {
                            alt2=2;}
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:224:13: M A X I M U M
                    {
                    mM(); 
                    mA(); 
                    mX(); 
                    mI(); 
                    mM(); 
                    mU(); 
                    mM(); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:224:29: M A X
                    {
                    mM(); 
                    mA(); 
                    mX(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MAXIMUM"

    // $ANTLR start "VERSION"
    public final void mVERSION() throws RecognitionException {
        try {
            int _type = VERSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:225:11: ( V E R S I O N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:225:13: V E R S I O N
            {
            mV(); 
            mE(); 
            mR(); 
            mS(); 
            mI(); 
            mO(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VERSION"

    // $ANTLR start "DESCRIPTION"
    public final void mDESCRIPTION() throws RecognitionException {
        try {
            int _type = DESCRIPTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:226:14: ( D E S C R I P T I O N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:226:16: D E S C R I P T I O N
            {
            mD(); 
            mE(); 
            mS(); 
            mC(); 
            mR(); 
            mI(); 
            mP(); 
            mT(); 
            mI(); 
            mO(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DESCRIPTION"

    // $ANTLR start "INTEGER_TYPE"
    public final void mINTEGER_TYPE() throws RecognitionException {
        try {
            int _type = INTEGER_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:230:14: ( I N T E G E R )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:230:16: I N T E G E R
            {
            mI(); 
            mN(); 
            mT(); 
            mE(); 
            mG(); 
            mE(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER_TYPE"

    // $ANTLR start "REAL_TYPE"
    public final void mREAL_TYPE() throws RecognitionException {
        try {
            int _type = REAL_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:231:12: ( R E A L )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:231:14: R E A L
            {
            mR(); 
            mE(); 
            mA(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REAL_TYPE"

    // $ANTLR start "BOOLEAN_TYPE"
    public final void mBOOLEAN_TYPE() throws RecognitionException {
        try {
            int _type = BOOLEAN_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:232:14: ( B O O L E A N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:232:16: B O O L E A N
            {
            mB(); 
            mO(); 
            mO(); 
            mL(); 
            mE(); 
            mA(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN_TYPE"

    // $ANTLR start "STRING_TYPE"
    public final void mSTRING_TYPE() throws RecognitionException {
        try {
            int _type = STRING_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:233:14: ( S T R I N G )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:233:16: S T R I N G
            {
            mS(); 
            mT(); 
            mR(); 
            mI(); 
            mN(); 
            mG(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_TYPE"

    // $ANTLR start "TEXT_TYPE"
    public final void mTEXT_TYPE() throws RecognitionException {
        try {
            int _type = TEXT_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:234:12: ( T E X T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:234:14: T E X T
            {
            mT(); 
            mE(); 
            mX(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TEXT_TYPE"

    // $ANTLR start "DATE_TYPE"
    public final void mDATE_TYPE() throws RecognitionException {
        try {
            int _type = DATE_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:235:12: ( D A T E )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:235:14: D A T E
            {
            mD(); 
            mA(); 
            mT(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DATE_TYPE"

    // $ANTLR start "ARRAY_TYPE"
    public final void mARRAY_TYPE() throws RecognitionException {
        try {
            int _type = ARRAY_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:236:13: ( A R R A Y )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:236:15: A R R A Y
            {
            mA(); 
            mR(); 
            mR(); 
            mA(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARRAY_TYPE"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:240:2: ( T R U E | F A L S E )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='T'||LA3_0=='t') ) {
                alt3=1;
            }
            else if ( (LA3_0=='F'||LA3_0=='f') ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:240:4: T R U E
                    {
                    mT(); 
                    mR(); 
                    mU(); 
                    mE(); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:241:4: F A L S E
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:245:2: ( N U L L )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:245:4: N U L L
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:250:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:250:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:250:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='+'||LA4_0=='-'||(LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:
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
            	    break loop4;
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:255:2: ( '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"' | '\\'' (~ ( '\\'' | '\\n' | '\\r' ) )* '\\'' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else if ( (LA7_0=='\'') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:255:4: '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"'
                    {
                    match('\"'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:255:8: (~ ( '\"' | '\\n' | '\\r' ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='\u0000' && LA5_0<='\t')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='\uFFFF')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:255:9: ~ ( '\"' | '\\n' | '\\r' )
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
                    	    break loop5;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:256:4: '\\'' (~ ( '\\'' | '\\n' | '\\r' ) )* '\\''
                    {
                    match('\''); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:256:9: (~ ( '\\'' | '\\n' | '\\r' ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='\u0000' && LA6_0<='\t')||(LA6_0>='\u000B' && LA6_0<='\f')||(LA6_0>='\u000E' && LA6_0<='&')||(LA6_0>='(' && LA6_0<='\uFFFF')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:256:10: ~ ( '\\'' | '\\n' | '\\r' )
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
                    	    break loop6;
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

    // $ANTLR start "DATE_YEAR_MONTH_DAY"
    public final void mDATE_YEAR_MONTH_DAY() throws RecognitionException {
        try {
            int _type = DATE_YEAR_MONTH_DAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:260:2: ( NUMBER NUMBER NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:260:4: NUMBER NUMBER NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER ( '/' | '-' ) NUMBER NUMBER
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

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:265:2: ( ( NUMBER )+ )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:265:4: ( NUMBER )+
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:265:4: ( NUMBER )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:265:4: NUMBER
            	    {
            	    mNUMBER(); 

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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:2: ( ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )? | '.' ( NUMBER )+ ( EXPONENT )? | ( NUMBER )+ EXPONENT )
            int alt15=3;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:6: ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )?
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:6: ( NUMBER )+
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
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:6: NUMBER
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
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:18: ( NUMBER )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:18: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:26: ( EXPONENT )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='E'||LA11_0=='e') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:269:26: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:270:9: '.' ( NUMBER )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:270:13: ( NUMBER )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:270:13: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:270:21: ( EXPONENT )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='E'||LA13_0=='e') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:270:21: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:271:9: ( NUMBER )+ EXPONENT
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:271:9: ( NUMBER )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:271:9: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:275:5: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:275:7: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:275:21: ( '+' | '-' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='+'||LA16_0=='-') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:
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

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:275:36: ( '0' .. '9' )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:275:38: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:279:2: ( ( '0' .. '9' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:279:4: ( '0' .. '9' )
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:279:4: ( '0' .. '9' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:279:5: '0' .. '9'
            {
            matchRange('0','9'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )?
            {
            match("//"); 

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:14: (~ ( '\\n' | '\\r' ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>='\u0000' && LA18_0<='\t')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\uFFFF')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:14: ~ ( '\\n' | '\\r' )
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
            	    break loop18;
                }
            } while (true);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:29: ( '\\r\\n' | '\\r' | '\\n' )?
            int alt19=4;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='\r') ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1=='\n') ) {
                    alt19=1;
                }
            }
            else if ( (LA19_0=='\n') ) {
                alt19=3;
            }
            switch (alt19) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:30: '\\r\\n'
                    {
                    match("\r\n"); 


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:39: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:284:46: '\\n'
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:289:2: ( ( '\\t' | ' ' | '\\n' | '\\r' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:289:4: ( '\\t' | ' ' | '\\n' | '\\r' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:293:11: ( ( 'a' | 'A' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:293:12: ( 'a' | 'A' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:294:11: ( ( 'b' | 'B' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:294:12: ( 'b' | 'B' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:295:11: ( ( 'c' | 'C' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:295:12: ( 'c' | 'C' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:296:11: ( ( 'd' | 'D' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:296:12: ( 'd' | 'D' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:297:11: ( ( 'e' | 'E' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:297:12: ( 'e' | 'E' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:298:11: ( ( 'f' | 'F' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:298:12: ( 'f' | 'F' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:299:11: ( ( 'g' | 'G' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:299:12: ( 'g' | 'G' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:300:11: ( ( 'h' | 'H' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:300:12: ( 'h' | 'H' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:301:11: ( ( 'i' | 'I' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:301:12: ( 'i' | 'I' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:302:11: ( ( 'j' | 'J' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:302:12: ( 'j' | 'J' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:303:11: ( ( 'k' | 'K' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:303:12: ( 'k' | 'K' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:304:11: ( ( 'l' | 'L' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:304:12: ( 'l' | 'L' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:305:11: ( ( 'm' | 'M' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:305:12: ( 'm' | 'M' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:306:11: ( ( 'n' | 'N' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:306:12: ( 'n' | 'N' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:307:11: ( ( 'o' | 'O' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:307:12: ( 'o' | 'O' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:308:11: ( ( 'p' | 'P' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:308:12: ( 'p' | 'P' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:309:11: ( ( 'q' | 'Q' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:309:12: ( 'q' | 'Q' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:310:11: ( ( 'r' | 'R' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:310:12: ( 'r' | 'R' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:311:11: ( ( 's' | 'S' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:311:12: ( 's' | 'S' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:312:11: ( ( 't' | 'T' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:312:12: ( 't' | 'T' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:313:11: ( ( 'u' | 'U' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:313:12: ( 'u' | 'U' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:314:11: ( ( 'v' | 'V' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:314:12: ( 'v' | 'V' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:315:11: ( ( 'w' | 'W' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:315:12: ( 'w' | 'W' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:316:11: ( ( 'x' | 'X' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:316:12: ( 'x' | 'X' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:317:11: ( ( 'y' | 'Y' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:317:12: ( 'y' | 'Y' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:318:11: ( ( 'z' | 'Z' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:318:12: ( 'z' | 'Z' )
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
        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:8: ( T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION | INTEGER_TYPE | REAL_TYPE | BOOLEAN_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | ARRAY_TYPE | BOOLEAN | NULL | ID | STRING | DATE_YEAR_MONTH_DAY | INTEGER | REAL | LINE_COMMENT | BLANK )
        int alt20=38;
        alt20 = dfa20.predict(input);
        switch (alt20) {
            case 1 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:10: T__70
                {
                mT__70(); 

                }
                break;
            case 2 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:16: T__71
                {
                mT__71(); 

                }
                break;
            case 3 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:22: T__72
                {
                mT__72(); 

                }
                break;
            case 4 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:28: T__73
                {
                mT__73(); 

                }
                break;
            case 5 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:34: T__74
                {
                mT__74(); 

                }
                break;
            case 6 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:40: T__75
                {
                mT__75(); 

                }
                break;
            case 7 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:46: T__76
                {
                mT__76(); 

                }
                break;
            case 8 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:52: T__77
                {
                mT__77(); 

                }
                break;
            case 9 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:58: T__78
                {
                mT__78(); 

                }
                break;
            case 10 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:64: T__79
                {
                mT__79(); 

                }
                break;
            case 11 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:70: T__80
                {
                mT__80(); 

                }
                break;
            case 12 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:76: CATALOG
                {
                mCATALOG(); 

                }
                break;
            case 13 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:84: NAME
                {
                mNAME(); 

                }
                break;
            case 14 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:89: DICTIONARY
                {
                mDICTIONARY(); 

                }
                break;
            case 15 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:100: ATTRIBUTES
                {
                mATTRIBUTES(); 

                }
                break;
            case 16 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:111: VALUES
                {
                mVALUES(); 

                }
                break;
            case 17 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:118: REFERENCES
                {
                mREFERENCES(); 

                }
                break;
            case 18 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:129: TYPE
                {
                mTYPE(); 

                }
                break;
            case 19 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:134: MINIMUM
                {
                mMINIMUM(); 

                }
                break;
            case 20 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:142: MAXIMUM
                {
                mMAXIMUM(); 

                }
                break;
            case 21 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:150: VERSION
                {
                mVERSION(); 

                }
                break;
            case 22 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:158: DESCRIPTION
                {
                mDESCRIPTION(); 

                }
                break;
            case 23 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:170: INTEGER_TYPE
                {
                mINTEGER_TYPE(); 

                }
                break;
            case 24 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:183: REAL_TYPE
                {
                mREAL_TYPE(); 

                }
                break;
            case 25 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:193: BOOLEAN_TYPE
                {
                mBOOLEAN_TYPE(); 

                }
                break;
            case 26 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:206: STRING_TYPE
                {
                mSTRING_TYPE(); 

                }
                break;
            case 27 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:218: TEXT_TYPE
                {
                mTEXT_TYPE(); 

                }
                break;
            case 28 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:228: DATE_TYPE
                {
                mDATE_TYPE(); 

                }
                break;
            case 29 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:238: ARRAY_TYPE
                {
                mARRAY_TYPE(); 

                }
                break;
            case 30 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:249: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 31 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:257: NULL
                {
                mNULL(); 

                }
                break;
            case 32 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:262: ID
                {
                mID(); 

                }
                break;
            case 33 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:265: STRING
                {
                mSTRING(); 

                }
                break;
            case 34 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:272: DATE_YEAR_MONTH_DAY
                {
                mDATE_YEAR_MONTH_DAY(); 

                }
                break;
            case 35 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:292: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 36 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:300: REAL
                {
                mREAL(); 

                }
                break;
            case 37 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:305: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 38 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/compactcat/parser/CompactCatDictionary.g:1:318: BLANK
                {
                mBLANK(); 

                }
                break;

        }

    }


    protected DFA15 dfa15 = new DFA15(this);
    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA15_eotS =
        "\5\uffff";
    static final String DFA15_eofS =
        "\5\uffff";
    static final String DFA15_minS =
        "\2\56\3\uffff";
    static final String DFA15_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA15_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA15_specialS =
        "\5\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "268:1: REAL : ( ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )? | '.' ( NUMBER )+ ( EXPONENT )? | ( NUMBER )+ EXPONENT );";
        }
    }
    static final String DFA20_eotS =
        "\14\uffff\14\30\2\uffff\1\62\3\uffff\24\30\1\uffff\1\62\17\30\1"+
        "\131\1\133\4\30\1\62\1\30\1\143\1\144\2\30\1\147\5\30\1\155\1\156"+
        "\1\157\1\160\1\uffff\1\30\1\uffff\5\30\1\62\1\30\2\uffff\2\30\1"+
        "\uffff\1\30\1\175\3\30\4\uffff\5\30\1\160\1\uffff\1\62\4\30\1\uffff"+
        "\1\u008a\6\30\1\u0091\1\u0092\3\30\1\uffff\1\u0096\1\30\1\131\1"+
        "\133\1\u0098\1\u0099\2\uffff\3\30\1\uffff\1\30\2\uffff\4\30\1\u00a2"+
        "\1\30\1\u00a4\1\u00a5\1\uffff\1\u00a6\3\uffff";
    static final String DFA20_eofS =
        "\u00a7\uffff";
    static final String DFA20_minS =
        "\1\11\13\uffff\3\101\1\122\1\101\2\105\1\101\1\116\1\117\1\124\1"+
        "\101\2\uffff\1\56\3\uffff\1\124\1\115\1\114\1\103\1\123\2\124\1"+
        "\122\1\114\1\122\1\101\1\120\1\130\1\125\1\116\1\130\1\124\1\117"+
        "\1\122\1\114\1\uffff\1\56\1\101\1\105\1\114\1\124\1\103\1\105\1"+
        "\122\1\101\1\125\1\123\1\105\1\114\1\105\1\124\1\105\2\53\1\105"+
        "\1\114\1\111\1\123\1\56\1\114\2\53\1\111\1\122\1\53\1\111\1\131"+
        "\1\105\1\111\1\122\4\53\1\uffff\1\115\1\uffff\1\115\1\107\1\105"+
        "\1\116\1\105\1\55\1\117\2\uffff\1\117\1\111\1\uffff\1\102\1\53\1"+
        "\123\1\117\1\105\4\uffff\2\125\1\105\1\101\1\107\1\53\1\uffff\1"+
        "\56\1\107\1\116\1\120\1\125\1\uffff\1\53\2\116\2\115\1\122\1\116"+
        "\2\53\1\101\2\124\1\uffff\1\53\1\103\4\53\2\uffff\1\122\1\111\1"+
        "\105\1\uffff\1\105\2\uffff\1\131\1\117\2\123\1\53\1\116\2\53\1\uffff"+
        "\1\53\3\uffff";
    static final String DFA20_maxS =
        "\1\175\13\uffff\1\141\1\165\1\151\1\164\2\145\1\171\1\151\1\156"+
        "\1\157\1\164\1\141\2\uffff\1\145\3\uffff\1\164\1\155\1\154\1\143"+
        "\1\163\2\164\1\162\1\154\1\162\1\146\1\160\1\170\1\165\1\156\1\170"+
        "\1\164\1\157\1\162\1\154\1\uffff\1\145\1\141\1\145\1\154\1\164\1"+
        "\143\1\145\1\162\1\141\1\165\1\163\1\145\1\154\1\145\1\164\1\145"+
        "\2\172\1\145\1\154\1\151\1\163\1\145\1\154\2\172\1\151\1\162\1\172"+
        "\1\151\1\171\1\145\1\151\1\162\4\172\1\uffff\1\155\1\uffff\1\155"+
        "\1\147\1\145\1\156\2\145\1\157\2\uffff\1\157\1\151\1\uffff\1\142"+
        "\1\172\1\163\1\157\1\145\4\uffff\2\165\1\145\1\141\1\147\1\172\1"+
        "\uffff\1\145\1\147\1\156\1\160\1\165\1\uffff\1\172\2\156\2\155\1"+
        "\162\1\156\2\172\1\141\2\164\1\uffff\1\172\1\143\4\172\2\uffff\1"+
        "\162\1\151\1\145\1\uffff\1\145\2\uffff\1\171\1\157\2\163\1\172\1"+
        "\156\2\172\1\uffff\1\172\3\uffff";
    static final String DFA20_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\14\uffff"+
        "\1\40\1\41\1\uffff\1\44\1\45\1\46\24\uffff\1\43\46\uffff\1\23\1"+
        "\uffff\1\24\7\uffff\1\15\1\37\2\uffff\1\34\5\uffff\1\30\1\22\1\33"+
        "\1\36\6\uffff\1\42\5\uffff\1\35\14\uffff\1\20\6\uffff\1\32\1\14"+
        "\3\uffff\1\25\1\uffff\1\27\1\31\10\uffff\1\16\1\uffff\1\17\1\21"+
        "\1\26";
    static final String DFA20_specialS =
        "\u00a7\uffff}>";
    static final String[] DFA20_transitionS = {
            "\2\35\2\uffff\1\35\22\uffff\1\35\1\uffff\1\31\4\uffff\1\31\1"+
            "\12\1\13\2\uffff\1\2\1\uffff\1\33\1\34\12\32\1\5\1\4\1\10\1"+
            "\uffff\1\11\2\uffff\1\17\1\25\1\14\1\16\1\30\1\27\2\30\1\24"+
            "\3\30\1\23\1\15\3\30\1\21\1\26\1\22\1\30\1\20\4\30\1\6\1\uffff"+
            "\1\7\1\uffff\1\30\1\uffff\1\17\1\25\1\14\1\16\1\30\1\27\2\30"+
            "\1\24\3\30\1\23\1\15\3\30\1\21\1\26\1\22\1\30\1\20\4\30\1\1"+
            "\1\uffff\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\36\37\uffff\1\36",
            "\1\37\23\uffff\1\40\13\uffff\1\37\23\uffff\1\40",
            "\1\43\3\uffff\1\42\3\uffff\1\41\27\uffff\1\43\3\uffff\1\42"+
            "\3\uffff\1\41",
            "\1\45\1\uffff\1\44\35\uffff\1\45\1\uffff\1\44",
            "\1\46\3\uffff\1\47\33\uffff\1\46\3\uffff\1\47",
            "\1\50\37\uffff\1\50",
            "\1\52\14\uffff\1\53\6\uffff\1\51\13\uffff\1\52\14\uffff\1\53"+
            "\6\uffff\1\51",
            "\1\55\7\uffff\1\54\27\uffff\1\55\7\uffff\1\54",
            "\1\56\37\uffff\1\56",
            "\1\57\37\uffff\1\57",
            "\1\60\37\uffff\1\60",
            "\1\61\37\uffff\1\61",
            "",
            "",
            "\1\33\1\uffff\12\63\13\uffff\1\33\37\uffff\1\33",
            "",
            "",
            "",
            "\1\64\37\uffff\1\64",
            "\1\65\37\uffff\1\65",
            "\1\66\37\uffff\1\66",
            "\1\67\37\uffff\1\67",
            "\1\70\37\uffff\1\70",
            "\1\71\37\uffff\1\71",
            "\1\72\37\uffff\1\72",
            "\1\73\37\uffff\1\73",
            "\1\74\37\uffff\1\74",
            "\1\75\37\uffff\1\75",
            "\1\77\4\uffff\1\76\32\uffff\1\77\4\uffff\1\76",
            "\1\100\37\uffff\1\100",
            "\1\101\37\uffff\1\101",
            "\1\102\37\uffff\1\102",
            "\1\103\37\uffff\1\103",
            "\1\104\37\uffff\1\104",
            "\1\105\37\uffff\1\105",
            "\1\106\37\uffff\1\106",
            "\1\107\37\uffff\1\107",
            "\1\110\37\uffff\1\110",
            "",
            "\1\33\1\uffff\12\111\13\uffff\1\33\37\uffff\1\33",
            "\1\112\37\uffff\1\112",
            "\1\113\37\uffff\1\113",
            "\1\114\37\uffff\1\114",
            "\1\115\37\uffff\1\115",
            "\1\116\37\uffff\1\116",
            "\1\117\37\uffff\1\117",
            "\1\120\37\uffff\1\120",
            "\1\121\37\uffff\1\121",
            "\1\122\37\uffff\1\122",
            "\1\123\37\uffff\1\123",
            "\1\124\37\uffff\1\124",
            "\1\125\37\uffff\1\125",
            "\1\126\37\uffff\1\126",
            "\1\127\37\uffff\1\127",
            "\1\130\37\uffff\1\130",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\10\30\1\132\21\30"+
            "\4\uffff\1\30\1\uffff\10\30\1\132\21\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\10\30\1\134\21\30"+
            "\4\uffff\1\30\1\uffff\10\30\1\134\21\30",
            "\1\135\37\uffff\1\135",
            "\1\136\37\uffff\1\136",
            "\1\137\37\uffff\1\137",
            "\1\140\37\uffff\1\140",
            "\1\33\1\uffff\12\141\13\uffff\1\33\37\uffff\1\33",
            "\1\142\37\uffff\1\142",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\145\37\uffff\1\145",
            "\1\146\37\uffff\1\146",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\150\37\uffff\1\150",
            "\1\151\37\uffff\1\151",
            "\1\152\37\uffff\1\152",
            "\1\153\37\uffff\1\153",
            "\1\154\37\uffff\1\154",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "",
            "\1\161\37\uffff\1\161",
            "",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\164\37\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\1\33\1\167\12\170\13\uffff\1\33\37\uffff\1\33",
            "\1\171\37\uffff\1\171",
            "",
            "",
            "\1\172\37\uffff\1\172",
            "\1\173\37\uffff\1\173",
            "",
            "\1\174\37\uffff\1\174",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\176\37\uffff\1\176",
            "\1\177\37\uffff\1\177",
            "\1\u0080\37\uffff\1\u0080",
            "",
            "",
            "",
            "",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\u0084\37\uffff\1\u0084",
            "\1\u0085\37\uffff\1\u0085",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "",
            "\1\33\1\uffff\12\170\13\uffff\1\33\37\uffff\1\33",
            "\1\u0086\37\uffff\1\u0086",
            "\1\u0087\37\uffff\1\u0087",
            "\1\u0088\37\uffff\1\u0088",
            "\1\u0089\37\uffff\1\u0089",
            "",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\u008b\37\uffff\1\u008b",
            "\1\u008c\37\uffff\1\u008c",
            "\1\u008d\37\uffff\1\u008d",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0090\37\uffff\1\u0090",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\u0097\37\uffff\1\u0097",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "",
            "",
            "\1\u009a\37\uffff\1\u009a",
            "\1\u009b\37\uffff\1\u009b",
            "\1\u009c\37\uffff\1\u009c",
            "",
            "\1\u009d\37\uffff\1\u009d",
            "",
            "",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "",
            "\1\30\1\uffff\1\30\2\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "",
            "",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | CATALOG | NAME | DICTIONARY | ATTRIBUTES | VALUES | REFERENCES | TYPE | MINIMUM | MAXIMUM | VERSION | DESCRIPTION | INTEGER_TYPE | REAL_TYPE | BOOLEAN_TYPE | STRING_TYPE | TEXT_TYPE | DATE_TYPE | ARRAY_TYPE | BOOLEAN | NULL | ID | STRING | DATE_YEAR_MONTH_DAY | INTEGER | REAL | LINE_COMMENT | BLANK );";
        }
    }
 

}