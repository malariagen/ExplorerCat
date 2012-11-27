// $ANTLR 3.3 Nov 30, 2010 12:50:56 /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g 2011-10-20 16:23:32
 
	package net.explorercat.cql.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CQLLexer extends Lexer {
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

    public CQLLexer() {;} 
    public CQLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CQLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g"; }

    // $ANTLR start "T__110"
    public final void mT__110() throws RecognitionException {
        try {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:11:8: ( ';' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:11:10: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__111"
    public final void mT__111() throws RecognitionException {
        try {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:12:8: ( '{' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:12:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__112"
    public final void mT__112() throws RecognitionException {
        try {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:13:8: ( '}' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:13:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__113"
    public final void mT__113() throws RecognitionException {
        try {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:14:8: ( ':=' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:14:10: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:15:8: ( '(' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:15:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__115"
    public final void mT__115() throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:16:8: ( ',' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:16:10: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__116"
    public final void mT__116() throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:17:8: ( ')' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:17:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__116"

    // $ANTLR start "T__117"
    public final void mT__117() throws RecognitionException {
        try {
            int _type = T__117;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:18:8: ( '[' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:18:10: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__117"

    // $ANTLR start "T__118"
    public final void mT__118() throws RecognitionException {
        try {
            int _type = T__118;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:19:8: ( ']' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:19:10: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__118"

    // $ANTLR start "T__119"
    public final void mT__119() throws RecognitionException {
        try {
            int _type = T__119;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:20:8: ( '.' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:20:10: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__119"

    // $ANTLR start "SELECT"
    public final void mSELECT() throws RecognitionException {
        try {
            int _type = SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:538:9: ( S E L E C T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:538:11: S E L E C T
            {
            mS(); 
            mE(); 
            mL(); 
            mE(); 
            mC(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SELECT"

    // $ANTLR start "VARS"
    public final void mVARS() throws RecognitionException {
        try {
            int _type = VARS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:541:7: ( V A R S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:541:9: V A R S
            {
            mV(); 
            mA(); 
            mR(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARS"

    // $ANTLR start "WHERE"
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:542:8: ( W H E R E )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:542:10: W H E R E
            {
            mW(); 
            mH(); 
            mE(); 
            mR(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHERE"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:543:7: ( F R O M )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:543:9: F R O M
            {
            mF(); 
            mR(); 
            mO(); 
            mM(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FROM"

    // $ANTLR start "MATCHES"
    public final void mMATCHES() throws RecognitionException {
        try {
            int _type = MATCHES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:544:10: ( M A T C H E S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:544:12: M A T C H E S
            {
            mM(); 
            mA(); 
            mT(); 
            mC(); 
            mH(); 
            mE(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MATCHES"

    // $ANTLR start "CONTAINS"
    public final void mCONTAINS() throws RecognitionException {
        try {
            int _type = CONTAINS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:545:10: ( C O N T A I N S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:545:12: C O N T A I N S
            {
            mC(); 
            mO(); 
            mN(); 
            mT(); 
            mA(); 
            mI(); 
            mN(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONTAINS"

    // $ANTLR start "STARTSWITH"
    public final void mSTARTSWITH() throws RecognitionException {
        try {
            int _type = STARTSWITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:546:12: ( S T A R T S W I T H )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:546:14: S T A R T S W I T H
            {
            mS(); 
            mT(); 
            mA(); 
            mR(); 
            mT(); 
            mS(); 
            mW(); 
            mI(); 
            mT(); 
            mH(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STARTSWITH"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:547:8: ( R A N G E F O R )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:547:10: R A N G E F O R
            {
            mR(); 
            mA(); 
            mN(); 
            mG(); 
            mE(); 
            mF(); 
            mO(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RANGE"

    // $ANTLR start "CONCAT"
    public final void mCONCAT() throws RecognitionException {
        try {
            int _type = CONCAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:548:9: ( C O N C A T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:548:11: C O N C A T
            {
            mC(); 
            mO(); 
            mN(); 
            mC(); 
            mA(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONCAT"

    // $ANTLR start "AVG"
    public final void mAVG() throws RecognitionException {
        try {
            int _type = AVG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:549:7: ( A V G )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:549:9: A V G
            {
            mA(); 
            mV(); 
            mG(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AVG"

    // $ANTLR start "MEDIAN"
    public final void mMEDIAN() throws RecognitionException {
        try {
            int _type = MEDIAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:550:9: ( M E D I A N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:550:11: M E D I A N
            {
            mM(); 
            mE(); 
            mD(); 
            mI(); 
            mA(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MEDIAN"

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:551:7: ( V A R )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:551:9: V A R
            {
            mV(); 
            mA(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "MIN"
    public final void mMIN() throws RecognitionException {
        try {
            int _type = MIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:552:7: ( M I N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:552:9: M I N
            {
            mM(); 
            mI(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MIN"

    // $ANTLR start "MAX"
    public final void mMAX() throws RecognitionException {
        try {
            int _type = MAX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:553:7: ( M A X )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:553:9: M A X
            {
            mM(); 
            mA(); 
            mX(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MAX"

    // $ANTLR start "STDDEV"
    public final void mSTDDEV() throws RecognitionException {
        try {
            int _type = STDDEV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:554:9: ( S T D D E V )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:554:11: S T D D E V
            {
            mS(); 
            mT(); 
            mD(); 
            mD(); 
            mE(); 
            mV(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STDDEV"

    // $ANTLR start "SUM"
    public final void mSUM() throws RecognitionException {
        try {
            int _type = SUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:555:7: ( S U M )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:555:9: S U M
            {
            mS(); 
            mU(); 
            mM(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUM"

    // $ANTLR start "COUNT"
    public final void mCOUNT() throws RecognitionException {
        try {
            int _type = COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:556:8: ( C O U N T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:556:10: C O U N T
            {
            mC(); 
            mO(); 
            mU(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COUNT"

    // $ANTLR start "SORT"
    public final void mSORT() throws RecognitionException {
        try {
            int _type = SORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:559:7: ( S O R T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:559:9: S O R T
            {
            mS(); 
            mO(); 
            mR(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SORT"

    // $ANTLR start "BY"
    public final void mBY() throws RecognitionException {
        try {
            int _type = BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:560:6: ( B Y )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:560:8: B Y
            {
            mB(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BY"

    // $ANTLR start "DESCENDING"
    public final void mDESCENDING() throws RecognitionException {
        try {
            int _type = DESCENDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:561:12: ( D E S C E N D I N G )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:561:14: D E S C E N D I N G
            {
            mD(); 
            mE(); 
            mS(); 
            mC(); 
            mE(); 
            mN(); 
            mD(); 
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
    // $ANTLR end "DESCENDING"

    // $ANTLR start "DESC"
    public final void mDESC() throws RecognitionException {
        try {
            int _type = DESC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:562:7: ( D E S C )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:562:9: D E S C
            {
            mD(); 
            mE(); 
            mS(); 
            mC(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DESC"

    // $ANTLR start "LIMIT"
    public final void mLIMIT() throws RecognitionException {
        try {
            int _type = LIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:563:8: ( L I M I T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:563:10: L I M I T
            {
            mL(); 
            mI(); 
            mM(); 
            mI(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LIMIT"

    // $ANTLR start "TOP"
    public final void mTOP() throws RecognitionException {
        try {
            int _type = TOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:564:7: ( T O P )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:564:9: T O P
            {
            mT(); 
            mO(); 
            mP(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TOP"

    // $ANTLR start "BOTTOM"
    public final void mBOTTOM() throws RecognitionException {
        try {
            int _type = BOTTOM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:565:9: ( B O T T O M )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:565:11: B O T T O M
            {
            mB(); 
            mO(); 
            mT(); 
            mT(); 
            mO(); 
            mM(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOTTOM"

    // $ANTLR start "RANDOM"
    public final void mRANDOM() throws RecognitionException {
        try {
            int _type = RANDOM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:566:9: ( R A N D O M )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:566:11: R A N D O M
            {
            mR(); 
            mA(); 
            mN(); 
            mD(); 
            mO(); 
            mM(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RANDOM"

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:567:6: ( A S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:567:8: A S
            {
            mA(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AS"

    // $ANTLR start "RESULT"
    public final void mRESULT() throws RecognitionException {
        try {
            int _type = RESULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:570:9: ( R E S U L T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:570:11: R E S U L T
            {
            mR(); 
            mE(); 
            mS(); 
            mU(); 
            mL(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RESULT"

    // $ANTLR start "DISTINCT"
    public final void mDISTINCT() throws RecognitionException {
        try {
            int _type = DISTINCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:571:12: ( D I S T I N C T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:571:14: D I S T I N C T
            {
            mD(); 
            mI(); 
            mS(); 
            mT(); 
            mI(); 
            mN(); 
            mC(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DISTINCT"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:574:9: ( ':' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:574:11: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "UNION"
    public final void mUNION() throws RecognitionException {
        try {
            int _type = UNION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:577:9: ( U N I O N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:577:11: U N I O N
            {
            mU(); 
            mN(); 
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
    // $ANTLR end "UNION"

    // $ANTLR start "INTERSECTION"
    public final void mINTERSECTION() throws RecognitionException {
        try {
            int _type = INTERSECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:578:14: ( I N T E R S E C T I O N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:578:16: I N T E R S E C T I O N
            {
            mI(); 
            mN(); 
            mT(); 
            mE(); 
            mR(); 
            mS(); 
            mE(); 
            mC(); 
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
    // $ANTLR end "INTERSECTION"

    // $ANTLR start "DIFFERENCE"
    public final void mDIFFERENCE() throws RecognitionException {
        try {
            int _type = DIFFERENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:579:13: ( D I F F E R E N C E )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:579:15: D I F F E R E N C E
            {
            mD(); 
            mI(); 
            mF(); 
            mF(); 
            mE(); 
            mR(); 
            mE(); 
            mN(); 
            mC(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIFFERENCE"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:582:7: ( A N D )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:582:9: A N D
            {
            mA(); 
            mN(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:583:6: ( O R )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:583:8: O R
            {
            mO(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:584:7: ( N O T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:584:9: N O T
            {
            mN(); 
            mO(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:587:6: ( '=' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:587:8: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:588:6: ( '>=' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:588:8: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:589:6: ( '<=' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:589:8: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:590:6: ( '>' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:590:8: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:591:6: ( '<' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:591:8: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:594:7: ( '+' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:594:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:595:7: ( '/' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:595:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "MULT"
    public final void mMULT() throws RecognitionException {
        try {
            int _type = MULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:596:7: ( '*' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:596:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULT"

    // $ANTLR start "DIFF"
    public final void mDIFF() throws RecognitionException {
        try {
            int _type = DIFF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:597:7: ( '-' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:597:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIFF"

    // $ANTLR start "POWER"
    public final void mPOWER() throws RecognitionException {
        try {
            int _type = POWER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:598:8: ( '^' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:598:10: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "POWER"

    // $ANTLR start "SQRT"
    public final void mSQRT() throws RecognitionException {
        try {
            int _type = SQRT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:599:7: ( S Q R T )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:599:9: S Q R T
            {
            mS(); 
            mQ(); 
            mR(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SQRT"

    // $ANTLR start "LOG"
    public final void mLOG() throws RecognitionException {
        try {
            int _type = LOG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:600:7: ( L O G )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:600:9: L O G
            {
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
    // $ANTLR end "LOG"

    // $ANTLR start "LN"
    public final void mLN() throws RecognitionException {
        try {
            int _type = LN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:601:6: ( L N )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:601:8: L N
            {
            mL(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LN"

    // $ANTLR start "ABS"
    public final void mABS() throws RecognitionException {
        try {
            int _type = ABS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:602:7: ( A B S )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:602:9: A B S
            {
            mA(); 
            mB(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ABS"

    // $ANTLR start "LENGTH"
    public final void mLENGTH() throws RecognitionException {
        try {
            int _type = LENGTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:605:9: ( L E N G T H )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:605:11: L E N G T H
            {
            mL(); 
            mE(); 
            mN(); 
            mG(); 
            mT(); 
            mH(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LENGTH"

    // $ANTLR start "RECOVERY"
    public final void mRECOVERY() throws RecognitionException {
        try {
            int _type = RECOVERY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:608:10: ( R E C O V E R Y )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:608:12: R E C O V E R Y
            {
            mR(); 
            mE(); 
            mC(); 
            mO(); 
            mV(); 
            mE(); 
            mR(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RECOVERY"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:611:6: ( T R U E )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:611:8: T R U E
            {
            mT(); 
            mR(); 
            mU(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:612:7: ( F A L S E )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:612:9: F A L S E
            {
            mF(); 
            mA(); 
            mL(); 
            mS(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:615:6: ( N U L L )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:615:8: N U L L
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

    // $ANTLR start "DATE"
    public final void mDATE() throws RecognitionException {
        try {
            int _type = DATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:618:6: ( '{' NUMBER NUMBER NUMBER NUMBER '-' NUMBER NUMBER '-' NUMBER NUMBER '}' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:618:8: '{' NUMBER NUMBER NUMBER NUMBER '-' NUMBER NUMBER '-' NUMBER NUMBER '}'
            {
            match('{'); 
            mNUMBER(); 
            mNUMBER(); 
            mNUMBER(); 
            mNUMBER(); 
            match('-'); 
            mNUMBER(); 
            mNUMBER(); 
            match('-'); 
            mNUMBER(); 
            mNUMBER(); 
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DATE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:621:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )* )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:621:8: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:621:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '+' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='+'||LA1_0=='-'||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:
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
            	    break loop1;
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

    // $ANTLR start "QUOTED_STRING"
    public final void mQUOTED_STRING() throws RecognitionException {
        try {
            int _type = QUOTED_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:625:2: ( '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"' | '\\'' (~ ( '\\'' | '\\n' | '\\r' ) )* '\\'' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\"') ) {
                alt4=1;
            }
            else if ( (LA4_0=='\'') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:625:4: '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"'
                    {
                    match('\"'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:625:8: (~ ( '\"' | '\\n' | '\\r' ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='!')||(LA2_0>='#' && LA2_0<='\uFFFF')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:625:9: ~ ( '\"' | '\\n' | '\\r' )
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
                    	    break loop2;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:626:4: '\\'' (~ ( '\\'' | '\\n' | '\\r' ) )* '\\''
                    {
                    match('\''); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:626:9: (~ ( '\\'' | '\\n' | '\\r' ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='\u0000' && LA3_0<='\t')||(LA3_0>='\u000B' && LA3_0<='\f')||(LA3_0>='\u000E' && LA3_0<='&')||(LA3_0>='(' && LA3_0<='\uFFFF')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:626:10: ~ ( '\\'' | '\\n' | '\\r' )
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
                    	    break loop3;
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
    // $ANTLR end "QUOTED_STRING"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:630:10: ( ( NUMBER )+ )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:630:12: ( NUMBER )+
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:630:12: ( NUMBER )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:630:12: NUMBER
            	    {
            	    mNUMBER(); 

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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:2: ( ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )? | '.' ( NUMBER )+ ( EXPONENT )? | ( NUMBER )+ EXPONENT )
            int alt12=3;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:6: ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )?
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:6: ( NUMBER )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:6: NUMBER
                    	    {
                    	    mNUMBER(); 

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

                    match('.'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:18: ( NUMBER )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:18: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:26: ( EXPONENT )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='E'||LA8_0=='e') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:633:26: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:634:9: '.' ( NUMBER )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:634:13: ( NUMBER )+
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
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:634:13: NUMBER
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

                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:634:21: ( EXPONENT )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='E'||LA10_0=='e') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:634:21: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:635:9: ( NUMBER )+ EXPONENT
                    {
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:635:9: ( NUMBER )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:635:9: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:639:5: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:639:7: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:639:21: ( '+' | '-' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='+'||LA13_0=='-') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:
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

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:639:36: ( '0' .. '9' )+
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
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:639:38: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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


            }

        }
        finally {
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:643:2: ( ( '0' .. '9' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:643:4: ( '0' .. '9' )
            {
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:643:4: ( '0' .. '9' )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:643:5: '0' .. '9'
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )? )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )?
            {
            match("//"); 

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:14: (~ ( '\\n' | '\\r' ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:14: ~ ( '\\n' | '\\r' )
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
            	    break loop15;
                }
            } while (true);

            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:29: ( '\\r\\n' | '\\r' | '\\n' )?
            int alt16=4;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='\r') ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1=='\n') ) {
                    alt16=1;
                }
            }
            else if ( (LA16_0=='\n') ) {
                alt16=3;
            }
            switch (alt16) {
                case 1 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:30: '\\r\\n'
                    {
                    match("\r\n"); 


                    }
                    break;
                case 2 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:39: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:648:46: '\\n'
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:653:2: ( ( '\\t' | ' ' | '\\n' | '\\r' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:653:4: ( '\\t' | ' ' | '\\n' | '\\r' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:657:11: ( ( 'a' | 'A' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:657:12: ( 'a' | 'A' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:658:11: ( ( 'b' | 'B' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:658:12: ( 'b' | 'B' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:659:11: ( ( 'c' | 'C' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:659:12: ( 'c' | 'C' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:660:11: ( ( 'd' | 'D' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:660:12: ( 'd' | 'D' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:661:11: ( ( 'e' | 'E' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:661:12: ( 'e' | 'E' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:662:11: ( ( 'f' | 'F' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:662:12: ( 'f' | 'F' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:663:11: ( ( 'g' | 'G' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:663:12: ( 'g' | 'G' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:664:11: ( ( 'h' | 'H' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:664:12: ( 'h' | 'H' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:665:11: ( ( 'i' | 'I' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:665:12: ( 'i' | 'I' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:666:11: ( ( 'j' | 'J' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:666:12: ( 'j' | 'J' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:667:11: ( ( 'k' | 'K' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:667:12: ( 'k' | 'K' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:668:11: ( ( 'l' | 'L' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:668:12: ( 'l' | 'L' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:669:11: ( ( 'm' | 'M' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:669:12: ( 'm' | 'M' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:670:11: ( ( 'n' | 'N' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:670:12: ( 'n' | 'N' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:671:11: ( ( 'o' | 'O' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:671:12: ( 'o' | 'O' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:672:11: ( ( 'p' | 'P' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:672:12: ( 'p' | 'P' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:673:11: ( ( 'q' | 'Q' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:673:12: ( 'q' | 'Q' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:674:11: ( ( 'r' | 'R' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:674:12: ( 'r' | 'R' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:675:11: ( ( 's' | 'S' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:675:12: ( 's' | 'S' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:676:11: ( ( 't' | 'T' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:676:12: ( 't' | 'T' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:677:11: ( ( 'u' | 'U' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:677:12: ( 'u' | 'U' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:678:11: ( ( 'v' | 'V' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:678:12: ( 'v' | 'V' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:679:11: ( ( 'w' | 'W' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:679:12: ( 'w' | 'W' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:680:11: ( ( 'x' | 'X' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:680:12: ( 'x' | 'X' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:681:11: ( ( 'y' | 'Y' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:681:12: ( 'y' | 'Y' )
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
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:682:11: ( ( 'z' | 'Z' ) )
            // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:682:12: ( 'z' | 'Z' )
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
        // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:8: ( T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | SELECT | VARS | WHERE | FROM | MATCHES | CONTAINS | STARTSWITH | RANGE | CONCAT | AVG | MEDIAN | VAR | MIN | MAX | STDDEV | SUM | COUNT | SORT | BY | DESCENDING | DESC | LIMIT | TOP | BOTTOM | RANDOM | AS | RESULT | DISTINCT | ASSIGN | UNION | INTERSECTION | DIFFERENCE | AND | OR | NOT | EQ | GE | LE | GT | LT | PLUS | DIV | MULT | DIFF | POWER | SQRT | LOG | LN | ABS | LENGTH | RECOVERY | TRUE | FALSE | NULL | DATE | ID | QUOTED_STRING | INTEGER | REAL | LINE_COMMENT | BLANK )
        int alt17=71;
        alt17 = dfa17.predict(input);
        switch (alt17) {
            case 1 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:10: T__110
                {
                mT__110(); 

                }
                break;
            case 2 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:17: T__111
                {
                mT__111(); 

                }
                break;
            case 3 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:24: T__112
                {
                mT__112(); 

                }
                break;
            case 4 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:31: T__113
                {
                mT__113(); 

                }
                break;
            case 5 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:38: T__114
                {
                mT__114(); 

                }
                break;
            case 6 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:45: T__115
                {
                mT__115(); 

                }
                break;
            case 7 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:52: T__116
                {
                mT__116(); 

                }
                break;
            case 8 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:59: T__117
                {
                mT__117(); 

                }
                break;
            case 9 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:66: T__118
                {
                mT__118(); 

                }
                break;
            case 10 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:73: T__119
                {
                mT__119(); 

                }
                break;
            case 11 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:80: SELECT
                {
                mSELECT(); 

                }
                break;
            case 12 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:87: VARS
                {
                mVARS(); 

                }
                break;
            case 13 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:92: WHERE
                {
                mWHERE(); 

                }
                break;
            case 14 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:98: FROM
                {
                mFROM(); 

                }
                break;
            case 15 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:103: MATCHES
                {
                mMATCHES(); 

                }
                break;
            case 16 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:111: CONTAINS
                {
                mCONTAINS(); 

                }
                break;
            case 17 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:120: STARTSWITH
                {
                mSTARTSWITH(); 

                }
                break;
            case 18 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:131: RANGE
                {
                mRANGE(); 

                }
                break;
            case 19 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:137: CONCAT
                {
                mCONCAT(); 

                }
                break;
            case 20 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:144: AVG
                {
                mAVG(); 

                }
                break;
            case 21 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:148: MEDIAN
                {
                mMEDIAN(); 

                }
                break;
            case 22 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:155: VAR
                {
                mVAR(); 

                }
                break;
            case 23 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:159: MIN
                {
                mMIN(); 

                }
                break;
            case 24 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:163: MAX
                {
                mMAX(); 

                }
                break;
            case 25 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:167: STDDEV
                {
                mSTDDEV(); 

                }
                break;
            case 26 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:174: SUM
                {
                mSUM(); 

                }
                break;
            case 27 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:178: COUNT
                {
                mCOUNT(); 

                }
                break;
            case 28 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:184: SORT
                {
                mSORT(); 

                }
                break;
            case 29 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:189: BY
                {
                mBY(); 

                }
                break;
            case 30 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:192: DESCENDING
                {
                mDESCENDING(); 

                }
                break;
            case 31 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:203: DESC
                {
                mDESC(); 

                }
                break;
            case 32 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:208: LIMIT
                {
                mLIMIT(); 

                }
                break;
            case 33 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:214: TOP
                {
                mTOP(); 

                }
                break;
            case 34 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:218: BOTTOM
                {
                mBOTTOM(); 

                }
                break;
            case 35 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:225: RANDOM
                {
                mRANDOM(); 

                }
                break;
            case 36 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:232: AS
                {
                mAS(); 

                }
                break;
            case 37 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:235: RESULT
                {
                mRESULT(); 

                }
                break;
            case 38 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:242: DISTINCT
                {
                mDISTINCT(); 

                }
                break;
            case 39 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:251: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 40 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:258: UNION
                {
                mUNION(); 

                }
                break;
            case 41 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:264: INTERSECTION
                {
                mINTERSECTION(); 

                }
                break;
            case 42 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:277: DIFFERENCE
                {
                mDIFFERENCE(); 

                }
                break;
            case 43 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:288: AND
                {
                mAND(); 

                }
                break;
            case 44 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:292: OR
                {
                mOR(); 

                }
                break;
            case 45 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:295: NOT
                {
                mNOT(); 

                }
                break;
            case 46 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:299: EQ
                {
                mEQ(); 

                }
                break;
            case 47 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:302: GE
                {
                mGE(); 

                }
                break;
            case 48 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:305: LE
                {
                mLE(); 

                }
                break;
            case 49 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:308: GT
                {
                mGT(); 

                }
                break;
            case 50 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:311: LT
                {
                mLT(); 

                }
                break;
            case 51 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:314: PLUS
                {
                mPLUS(); 

                }
                break;
            case 52 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:319: DIV
                {
                mDIV(); 

                }
                break;
            case 53 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:323: MULT
                {
                mMULT(); 

                }
                break;
            case 54 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:328: DIFF
                {
                mDIFF(); 

                }
                break;
            case 55 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:333: POWER
                {
                mPOWER(); 

                }
                break;
            case 56 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:339: SQRT
                {
                mSQRT(); 

                }
                break;
            case 57 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:344: LOG
                {
                mLOG(); 

                }
                break;
            case 58 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:348: LN
                {
                mLN(); 

                }
                break;
            case 59 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:351: ABS
                {
                mABS(); 

                }
                break;
            case 60 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:355: LENGTH
                {
                mLENGTH(); 

                }
                break;
            case 61 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:362: RECOVERY
                {
                mRECOVERY(); 

                }
                break;
            case 62 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:371: TRUE
                {
                mTRUE(); 

                }
                break;
            case 63 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:376: FALSE
                {
                mFALSE(); 

                }
                break;
            case 64 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:382: NULL
                {
                mNULL(); 

                }
                break;
            case 65 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:387: DATE
                {
                mDATE(); 

                }
                break;
            case 66 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:392: ID
                {
                mID(); 

                }
                break;
            case 67 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:395: QUOTED_STRING
                {
                mQUOTED_STRING(); 

                }
                break;
            case 68 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:409: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 69 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:417: REAL
                {
                mREAL(); 

                }
                break;
            case 70 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:422: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 71 :
                // /home/chinaski/programming/agile_workspace/ExplorerCat/src/main/antlr3/net/explorercat/cql/parser/CQL.g:1:435: BLANK
                {
                mBLANK(); 

                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA12_eotS =
        "\5\uffff";
    static final String DFA12_eofS =
        "\5\uffff";
    static final String DFA12_minS =
        "\2\56\3\uffff";
    static final String DFA12_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA12_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA12_specialS =
        "\5\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "632:1: REAL : ( ( NUMBER )+ '.' ( NUMBER )* ( EXPONENT )? | '.' ( NUMBER )+ ( EXPONENT )? | ( NUMBER )+ EXPONENT );";
        }
    }
    static final String DFA17_eotS =
        "\2\uffff\1\47\1\uffff\1\52\5\uffff\1\53\20\43\1\uffff\1\120\1\122"+
        "\1\uffff\1\124\5\uffff\1\125\7\uffff\20\43\1\152\2\43\1\155\5\43"+
        "\1\164\5\43\1\172\2\43\7\uffff\3\43\1\u0080\2\43\1\u0083\4\43\1"+
        "\u0089\1\43\1\u008b\5\43\1\u0093\1\uffff\1\u0094\1\u0095\1\uffff"+
        "\5\43\1\u009b\1\uffff\1\43\1\u009d\3\43\1\uffff\1\u00a1\4\43\1\uffff"+
        "\1\u00a6\1\u00a7\1\uffff\1\u00a8\1\43\1\u00aa\2\43\1\uffff\1\43"+
        "\1\uffff\7\43\3\uffff\1\43\1\u00b6\3\43\1\uffff\1\43\1\uffff\1\u00bc"+
        "\2\43\1\uffff\1\u00bf\3\43\3\uffff\1\u00c3\1\uffff\1\u00c4\4\43"+
        "\1\u00c9\5\43\1\uffff\3\43\1\u00d2\1\43\1\uffff\1\u00d4\1\43\1\uffff"+
        "\1\u00d6\1\43\1\u00d8\2\uffff\1\43\1\u00da\1\43\1\u00dc\1\uffff"+
        "\1\43\1\u00de\1\u00df\1\43\1\u00e1\3\43\1\uffff\1\u00e5\1\uffff"+
        "\1\43\1\uffff\1\43\1\uffff\1\u00e8\1\uffff\1\43\1\uffff\1\43\2\uffff"+
        "\1\43\1\uffff\3\43\1\uffff\2\43\1\uffff\1\u00f1\1\u00f2\1\u00f3"+
        "\1\43\1\u00f5\3\43\3\uffff\1\43\1\uffff\2\43\1\u00fc\1\u00fd\1\u00fe"+
        "\1\43\3\uffff\1\43\1\u0101\1\uffff";
    static final String DFA17_eofS =
        "\u0102\uffff";
    static final String DFA17_minS =
        "\1\11\1\uffff\1\60\1\uffff\1\75\5\uffff\1\60\1\105\1\101\1\110\2"+
        "\101\1\117\1\101\1\102\1\117\2\105\1\117\2\116\1\122\1\117\1\uffff"+
        "\2\75\1\uffff\1\57\5\uffff\1\56\7\uffff\1\114\1\101\1\115\3\122"+
        "\1\105\1\117\1\114\1\124\1\104\3\116\1\103\1\107\1\53\1\104\1\123"+
        "\1\53\1\124\1\123\1\106\1\115\1\107\1\53\1\116\1\120\1\125\1\111"+
        "\1\124\1\53\1\124\1\114\7\uffff\1\105\1\122\1\104\1\53\2\124\1\53"+
        "\1\122\1\115\1\123\1\103\1\53\1\111\1\53\1\103\1\116\1\104\1\125"+
        "\1\117\1\53\1\uffff\2\53\1\uffff\1\124\1\103\1\124\1\106\1\111\1"+
        "\53\1\uffff\1\107\1\53\1\105\1\117\1\105\1\uffff\1\53\1\114\1\103"+
        "\1\124\1\105\1\uffff\2\53\1\uffff\1\53\1\105\1\53\1\105\1\110\1"+
        "\uffff\1\101\1\uffff\2\101\1\124\1\105\1\117\1\114\1\126\3\uffff"+
        "\1\117\1\53\1\111\1\105\1\124\1\uffff\1\124\1\uffff\1\53\1\116\1"+
        "\122\1\uffff\1\53\1\124\1\123\1\126\3\uffff\1\53\1\uffff\1\53\1"+
        "\105\1\116\1\111\1\124\1\53\1\106\1\115\1\124\1\105\1\115\1\uffff"+
        "\2\116\1\122\1\53\1\110\1\uffff\1\53\1\123\1\uffff\1\53\1\127\1"+
        "\53\2\uffff\1\123\1\53\1\116\1\53\1\uffff\1\117\2\53\1\122\1\53"+
        "\1\104\1\103\1\105\1\uffff\1\53\1\uffff\1\105\1\uffff\1\111\1\uffff"+
        "\1\53\1\uffff\1\123\1\uffff\1\122\2\uffff\1\131\1\uffff\1\111\1"+
        "\124\1\116\1\uffff\1\103\1\124\1\uffff\3\53\1\116\1\53\1\103\1\124"+
        "\1\110\3\uffff\1\107\1\uffff\1\105\1\111\3\53\1\117\3\uffff\1\116"+
        "\1\53\1\uffff";
    static final String DFA17_maxS =
        "\1\175\1\uffff\1\71\1\uffff\1\75\5\uffff\1\71\1\165\1\141\1\150"+
        "\1\162\1\151\1\157\1\145\1\166\1\171\1\151\1\157\1\162\2\156\1\162"+
        "\1\165\1\uffff\2\75\1\uffff\1\57\5\uffff\1\145\7\uffff\1\154\1\144"+
        "\1\155\3\162\1\145\1\157\1\154\1\170\1\144\1\156\1\165\1\156\1\163"+
        "\1\147\1\172\1\144\1\163\1\172\1\164\2\163\1\155\1\147\1\172\1\156"+
        "\1\160\1\165\1\151\1\164\1\172\1\164\1\154\7\uffff\1\145\1\162\1"+
        "\144\1\172\2\164\1\172\1\162\1\155\1\163\1\143\1\172\1\151\1\172"+
        "\1\164\1\156\1\147\1\165\1\157\1\172\1\uffff\2\172\1\uffff\1\164"+
        "\1\143\1\164\1\146\1\151\1\172\1\uffff\1\147\1\172\1\145\1\157\1"+
        "\145\1\uffff\1\172\1\154\1\143\1\164\1\145\1\uffff\2\172\1\uffff"+
        "\1\172\1\145\1\172\1\145\1\150\1\uffff\1\141\1\uffff\2\141\1\164"+
        "\1\145\1\157\1\154\1\166\3\uffff\1\157\1\172\1\151\1\145\1\164\1"+
        "\uffff\1\164\1\uffff\1\172\1\156\1\162\1\uffff\1\172\1\164\1\163"+
        "\1\166\3\uffff\1\172\1\uffff\1\172\1\145\1\156\1\151\1\164\1\172"+
        "\1\146\1\155\1\164\1\145\1\155\1\uffff\2\156\1\162\1\172\1\150\1"+
        "\uffff\1\172\1\163\1\uffff\1\172\1\167\1\172\2\uffff\1\163\1\172"+
        "\1\156\1\172\1\uffff\1\157\2\172\1\162\1\172\1\144\1\143\1\145\1"+
        "\uffff\1\172\1\uffff\1\145\1\uffff\1\151\1\uffff\1\172\1\uffff\1"+
        "\163\1\uffff\1\162\2\uffff\1\171\1\uffff\1\151\1\164\1\156\1\uffff"+
        "\1\143\1\164\1\uffff\3\172\1\156\1\172\1\143\1\164\1\150\3\uffff"+
        "\1\147\1\uffff\1\145\1\151\3\172\1\157\3\uffff\1\156\1\172\1\uffff";
    static final String DFA17_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\uffff\1\5\1\6\1\7\1\10\1\11\21\uffff"+
        "\1\56\2\uffff\1\63\1\uffff\1\65\1\66\1\67\1\102\1\103\1\uffff\1"+
        "\107\1\2\1\101\1\4\1\47\1\12\1\105\42\uffff\1\57\1\61\1\60\1\62"+
        "\1\106\1\64\1\104\24\uffff\1\44\2\uffff\1\35\6\uffff\1\72\5\uffff"+
        "\1\54\5\uffff\1\32\2\uffff\1\26\5\uffff\1\30\1\uffff\1\27\7\uffff"+
        "\1\24\1\53\1\73\5\uffff\1\71\1\uffff\1\41\3\uffff\1\55\4\uffff\1"+
        "\34\1\70\1\14\1\uffff\1\16\13\uffff\1\37\5\uffff\1\76\2\uffff\1"+
        "\100\3\uffff\1\15\1\77\4\uffff\1\33\10\uffff\1\40\1\uffff\1\50\1"+
        "\uffff\1\13\1\uffff\1\31\1\uffff\1\25\1\uffff\1\23\1\uffff\1\43"+
        "\1\45\1\uffff\1\42\3\uffff\1\74\2\uffff\1\17\10\uffff\1\20\1\22"+
        "\1\75\1\uffff\1\46\6\uffff\1\21\1\36\1\52\2\uffff\1\51";
    static final String DFA17_specialS =
        "\u0102\uffff}>";
    static final String[] DFA17_transitionS = {
            "\2\46\2\uffff\1\46\22\uffff\1\46\1\uffff\1\44\4\uffff\1\44\1"+
            "\5\1\7\1\40\1\36\1\6\1\41\1\12\1\37\12\45\1\4\1\1\1\35\1\33"+
            "\1\34\2\uffff\1\22\1\23\1\20\1\24\1\43\1\16\2\43\1\30\2\43\1"+
            "\25\1\17\1\32\1\31\2\43\1\21\1\13\1\26\1\27\1\14\1\15\3\43\1"+
            "\10\1\uffff\1\11\1\42\1\43\1\uffff\1\22\1\23\1\20\1\24\1\43"+
            "\1\16\2\43\1\30\2\43\1\25\1\17\1\32\1\31\2\43\1\21\1\13\1\26"+
            "\1\27\1\14\1\15\3\43\1\2\1\uffff\1\3",
            "",
            "\12\50",
            "",
            "\1\51",
            "",
            "",
            "",
            "",
            "",
            "\12\54",
            "\1\55\11\uffff\1\60\1\uffff\1\61\2\uffff\1\56\1\57\17\uffff"+
            "\1\55\11\uffff\1\60\1\uffff\1\61\2\uffff\1\56\1\57",
            "\1\62\37\uffff\1\62",
            "\1\63\37\uffff\1\63",
            "\1\65\20\uffff\1\64\16\uffff\1\65\20\uffff\1\64",
            "\1\66\3\uffff\1\67\3\uffff\1\70\27\uffff\1\66\3\uffff\1\67"+
            "\3\uffff\1\70",
            "\1\71\37\uffff\1\71",
            "\1\72\3\uffff\1\73\33\uffff\1\72\3\uffff\1\73",
            "\1\77\13\uffff\1\76\4\uffff\1\75\2\uffff\1\74\13\uffff\1\77"+
            "\13\uffff\1\76\4\uffff\1\75\2\uffff\1\74",
            "\1\101\11\uffff\1\100\25\uffff\1\101\11\uffff\1\100",
            "\1\102\3\uffff\1\103\33\uffff\1\102\3\uffff\1\103",
            "\1\107\3\uffff\1\104\4\uffff\1\106\1\105\25\uffff\1\107\3\uffff"+
            "\1\104\4\uffff\1\106\1\105",
            "\1\110\2\uffff\1\111\34\uffff\1\110\2\uffff\1\111",
            "\1\112\37\uffff\1\112",
            "\1\113\37\uffff\1\113",
            "\1\114\37\uffff\1\114",
            "\1\115\5\uffff\1\116\31\uffff\1\115\5\uffff\1\116",
            "",
            "\1\117",
            "\1\121",
            "",
            "\1\123",
            "",
            "",
            "",
            "",
            "",
            "\1\54\1\uffff\12\45\13\uffff\1\54\37\uffff\1\54",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\126\37\uffff\1\126",
            "\1\127\2\uffff\1\130\34\uffff\1\127\2\uffff\1\130",
            "\1\131\37\uffff\1\131",
            "\1\132\37\uffff\1\132",
            "\1\133\37\uffff\1\133",
            "\1\134\37\uffff\1\134",
            "\1\135\37\uffff\1\135",
            "\1\136\37\uffff\1\136",
            "\1\137\37\uffff\1\137",
            "\1\140\3\uffff\1\141\33\uffff\1\140\3\uffff\1\141",
            "\1\142\37\uffff\1\142",
            "\1\143\37\uffff\1\143",
            "\1\144\6\uffff\1\145\30\uffff\1\144\6\uffff\1\145",
            "\1\146\37\uffff\1\146",
            "\1\150\17\uffff\1\147\17\uffff\1\150\17\uffff\1\147",
            "\1\151\37\uffff\1\151",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\153\37\uffff\1\153",
            "\1\154\37\uffff\1\154",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\156\37\uffff\1\156",
            "\1\157\37\uffff\1\157",
            "\1\161\14\uffff\1\160\22\uffff\1\161\14\uffff\1\160",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\173\37\uffff\1\173",
            "\1\174\37\uffff\1\174",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\175\37\uffff\1\175",
            "\1\176\37\uffff\1\176",
            "\1\177\37\uffff\1\177",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\22\43\1\u0084\7\43"+
            "\4\uffff\1\43\1\uffff\22\43\1\u0084\7\43",
            "\1\u0085\37\uffff\1\u0085",
            "\1\u0086\37\uffff\1\u0086",
            "\1\u0087\37\uffff\1\u0087",
            "\1\u0088\37\uffff\1\u0088",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u008a\37\uffff\1\u008a",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u008d\20\uffff\1\u008c\16\uffff\1\u008d\20\uffff\1\u008c",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u0090\2\uffff\1\u008f\34\uffff\1\u0090\2\uffff\1\u008f",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\u009c\37\uffff\1\u009c",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00ab\37\uffff\1\u00ab",
            "\1\u00ac\37\uffff\1\u00ac",
            "",
            "\1\u00ad\37\uffff\1\u00ad",
            "",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\u00b1\37\uffff\1\u00b1",
            "\1\u00b2\37\uffff\1\u00b2",
            "\1\u00b3\37\uffff\1\u00b3",
            "\1\u00b4\37\uffff\1\u00b4",
            "",
            "",
            "",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\4\43\1\u00b7\25\43"+
            "\4\uffff\1\43\1\uffff\4\43\1\u00b7\25\43",
            "\1\u00b8\37\uffff\1\u00b8",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "",
            "\1\u00bb\37\uffff\1\u00bb",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "",
            "",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00c5\37\uffff\1\u00c5",
            "\1\u00c6\37\uffff\1\u00c6",
            "\1\u00c7\37\uffff\1\u00c7",
            "\1\u00c8\37\uffff\1\u00c8",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cc\37\uffff\1\u00cc",
            "\1\u00cd\37\uffff\1\u00cd",
            "\1\u00ce\37\uffff\1\u00ce",
            "",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00d3\37\uffff\1\u00d3",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00d5\37\uffff\1\u00d5",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00db\37\uffff\1\u00db",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00e0\37\uffff\1\u00e0",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\u00e3\37\uffff\1\u00e3",
            "\1\u00e4\37\uffff\1\u00e4",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\u00e6\37\uffff\1\u00e6",
            "",
            "\1\u00e7\37\uffff\1\u00e7",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "",
            "\1\u00e9\37\uffff\1\u00e9",
            "",
            "\1\u00ea\37\uffff\1\u00ea",
            "",
            "",
            "\1\u00eb\37\uffff\1\u00eb",
            "",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\u00ed\37\uffff\1\u00ed",
            "\1\u00ee\37\uffff\1\u00ee",
            "",
            "\1\u00ef\37\uffff\1\u00ef",
            "\1\u00f0\37\uffff\1\u00f0",
            "",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00f4\37\uffff\1\u00f4",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "",
            "",
            "",
            "\1\u00f9\37\uffff\1\u00f9",
            "",
            "\1\u00fa\37\uffff\1\u00fa",
            "\1\u00fb\37\uffff\1\u00fb",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
            "\1\u00ff\37\uffff\1\u00ff",
            "",
            "",
            "",
            "\1\u0100\37\uffff\1\u0100",
            "\1\43\1\uffff\1\43\2\uffff\12\43\7\uffff\32\43\4\uffff\1\43"+
            "\1\uffff\32\43",
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
            return "1:1: Tokens : ( T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | SELECT | VARS | WHERE | FROM | MATCHES | CONTAINS | STARTSWITH | RANGE | CONCAT | AVG | MEDIAN | VAR | MIN | MAX | STDDEV | SUM | COUNT | SORT | BY | DESCENDING | DESC | LIMIT | TOP | BOTTOM | RANDOM | AS | RESULT | DISTINCT | ASSIGN | UNION | INTERSECTION | DIFFERENCE | AND | OR | NOT | EQ | GE | LE | GT | LT | PLUS | DIV | MULT | DIFF | POWER | SQRT | LOG | LN | ABS | LENGTH | RECOVERY | TRUE | FALSE | NULL | DATE | ID | QUOTED_STRING | INTEGER | REAL | LINE_COMMENT | BLANK );";
        }
    }
 

}