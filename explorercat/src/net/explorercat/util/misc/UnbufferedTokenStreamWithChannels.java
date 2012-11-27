package net.explorercat.util.misc;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.UnbufferedTokenStream;

/**
 * An extension of the unbuffered token stream to support off-channel tokens.
 * Notice this implementation is very naive, it gets a token from the token
 * source on demand, discarding it if it is not coming from the default channel.
 * 
 * As with the base class, you can only look backwards 1 token: LT(-1). Notice
 * that this class depends on ANTLR 3.3 runtime libraries.
 * 
 * To be removed as soon as we update the catalog deployer (we'll replace the
 * ANTLR grammar by a JSON parser)
 * 
 * @author Jacob Almagro-Garcia
 * @date May 2011
 */

public class UnbufferedTokenStreamWithChannels extends UnbufferedTokenStream implements TokenStream
{
    public UnbufferedTokenStreamWithChannels(TokenSource tokenSource)
    {
	super(tokenSource);
    }

    @Override
    public Token nextElement()
    {
	Token token = null;

	do
	{
	    token = tokenSource.nextToken();
	    token.setTokenIndex(tokenIndex++);
	}
	while(token.getChannel() != channel && !isEOF(token));

	return token;
    }
}
