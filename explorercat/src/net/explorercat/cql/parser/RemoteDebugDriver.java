package net.explorercat.cql.parser;

import net.explorercat.cql.parser.CQLParser.cqlScript_return;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

/**
 * Class to setup an interactive debugging session using ANTLR Works to connect
 * remotely.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 11 Jul 2011
 */

public class RemoteDebugDriver
{

    public static void main(String[] args) throws Exception
    {
	CommonTokenStream tokens = new CommonTokenStream();

	ANTLRStringStream input = new ANTLRStringStream("SELECT FROM snp AS sys_snp WHERE {Coordinates AND Coordinates} RECOVERY ({FALSE} RECOVERY(TRUE)); RESULT{sys_snp};");
	CQLLexer lexer = new CQLLexer(input);
	tokens.setTokenSource(lexer);

	CommonTreeNodeStream nodes;
	CQLParser parser = new CQLParser(tokens);
	cqlScript_return parsing = parser.cqlScript();

	CommonTree tree = (CommonTree) parsing.getTree();
	nodes = new CommonTreeNodeStream(tree);

	CQLTreeWalker walker = new CQLTreeWalker(nodes);
	net.explorercat.cql.parser.CQLTreeWalker.cqlScript_return walking = walker.cqlScript();

	System.out.println(walking.toString());
	System.exit(0);
    }
}
