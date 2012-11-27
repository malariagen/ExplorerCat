package net.explorercat.util.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CharStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A dirty modification of the StringStream ANTLR class.
 * 
 * A pretty quick CharStream that pulls all data from an array directly. Every
 * method call counts in the lexer.
 * 
 * To be removed as soon as we update the catalog deployer (we'll replace the
 * ANTLR grammar by a JSON parser)
 */

public class UnbufferedStringStream implements CharStream
{
    private static final Log log = LogFactory.getLog(UnbufferedStringStream.class);

    /** The data being scanned */
    //protected char[] data;    
    private DataStream data;

    /** How many characters are actually in the buffer */
    protected int n;

    /** 0..n-1 index into string of next char */
    protected int p = 0;

    /** line number 1..n within the input */
    protected int line = 1;

    /** The index of the character relative to the beginning of the line 0..n-1 */
    protected int charPositionInLine = 0;

    /** tracks how deep mark() calls are nested */
    protected int markDepth = 0;

    /**
     * A list of CharStreamState objects that tracks the stream state values
     * line, charPositionInLine, and p that can change as you move through the
     * input stream. Indexed from 1..markDepth. A null is kept @ index 0. Create
     * upon first call to mark().
     */
    protected List markers;

    /** Track the last mark() call result value for use in rewind(). */
    protected int lastMarker;

    /** What is name or source of this char stream? */
    public String name;

    /** Copy data in string to a local char array */
    public UnbufferedStringStream(String filename)
    {
	try
	{
	    this.data = new DataStream(20000, 2, filename);
	    this.n = (int) data.length();
	}
	catch(IOException e)
	{
	    throw new RuntimeException("Error accessing the data stream", e);
	}
    }

    /**
     * Reset the stream so that it's in the same state it was when the object
     * was created *except* the data array is not touched.
     */
    public void reset()
    {
	p = 0;
	line = 1;
	charPositionInLine = 0;
	markDepth = 0;
    }

    public void consume()
    {
	//System.out.println("prev p="+p+", c="+(char)data[p]);
	if(p < n)
	{
	    charPositionInLine++;

	    //log.debug("Consume()");

	    if(data.getChar(p) == '\n')
	    {
		/*
		 * System.out.println("newline char found on line: "+line+
		 * "@ pos="+charPositionInLine);
		 */
		line++;
		charPositionInLine = 0;
	    }
	    p++;
	    //System.out.println("p moves to "+p+" (c='"+(char)data[p]+"')");
	}
    }

    public int LA(int i)
    {
	if(i == 0)
	{
	    return 0; // undefined
	}
	if(i < 0)
	{
	    i++; // e.g., translate LA(-1) to use offset i=0; then data[p+0-1]
	    if((p + i - 1) < 0)
	    {
		return CharStream.EOF; // invalid; no char before first char
	    }
	}

	if((p + i - 1) >= n)
	{
	    //System.out.println("char LA("+i+")=EOF; p="+p);
	    return CharStream.EOF;
	}

	//log.debug("LA()");

	//System.out.println("char LA("+i+")="+(char)data[p+i-1]+"; p="+p);
	//System.out.println("LA("+i+"); p="+p+" n="+n+" data.length="+data.length);
	return data.getChar(p + i - 1);
    }

    public int LT(int i)
    {
	return LA(i);
    }

    /**
     * Return the current input symbol index 0..n where n indicates the last
     * symbol has been read. The index is the index of char to be returned from
     * LA(1).
     */
    public int index()
    {
	return p;
    }

    public int size()
    {
	return n;
    }

    public int mark()
    {
	if(markers == null)
	{
	    markers = new ArrayList();
	    markers.add(null); // depth 0 means no backtracking, leave blank
	}
	markDepth++;
	StreamState state = null;
	if(markDepth >= markers.size())
	{
	    state = new StreamState();
	    markers.add(state);
	}
	else
	{
	    state = (StreamState) markers.get(markDepth);
	}
	state.p = p;
	state.line = line;
	state.charPositionInLine = charPositionInLine;
	lastMarker = markDepth;
	return markDepth;
    }

    public void rewind(int m)
    {
	StreamState state = (StreamState) markers.get(m);
	// restore stream state
	seek(state.p);
	line = state.line;
	charPositionInLine = state.charPositionInLine;
	release(m);
    }

    public void rewind()
    {
	rewind(lastMarker);
    }

    public void release(int marker)
    {
	// unwind any other markers made after m and release m
	markDepth = marker;
	// release this marker
	markDepth--;
    }

    /**
     * consume() ahead until p==index; can't just set p=index as we must update
     * line and charPositionInLine.
     */
    public void seek(int index)
    {
	if(index <= p)
	{
	    p = index; // just jump; don't update stream state (line, ...)
	    return;
	}
	// seek forward, consume until p hits index
	while(p < index)
	{
	    consume();
	}
    }

    public String substring(int start, int stop)
    {
	StringBuilder buffer = new StringBuilder();

	if(stop < start)
	    return "";

	//log.debug("Substring()");

	for(int i = start; i <= stop; ++i)
	    buffer.append(data.getChar(i));

	return buffer.toString();
    }

    public int getLine()
    {
	return line;
    }

    public int getCharPositionInLine()
    {
	return charPositionInLine;
    }

    public void setLine(int line)
    {
	this.line = line;
    }

    public void setCharPositionInLine(int pos)
    {
	this.charPositionInLine = pos;
    }

    public String getSourceName()
    {
	return name;
    }

    public String toString()
    {
	throw new UnsupportedOperationException("Unbuffered stream");
    }

    /**
     * Utility class to mark the state of the stream.
     */

    private static class StreamState
    {
	/** Index into the char stream of next lookahead char */
	public int p;

	/** What line number is the scanner at before processing buffer[p]? */
	public int line;

	/**
	 * What char position 0..n-1 in line is scanner before processing
	 * buffer[p]?
	 */
	public int charPositionInLine;
    }

    /**
     * Unbuffered stream that reads data from the input when necessary
     * (transparently). We use a rolling buffer schema with n buffers. When we
     * run out of characters we overwrite the oldest buffer with new data.
     */

    private static class DataStream
    {
	private int bufferSize;
	private int numBuffers;
	private char[][] dataBuffers;

	private InputStreamReader streamReader;
	private long streamLength;

	private int currentPosition;
	private int currentBuffer;
	private int currentColumn;
	private int firstPositionAvailable;
	private int lastPositionAvailable;

	public DataStream(int bufferSize, int numBuffers, String filename) throws IOException
	{
	    this.bufferSize = bufferSize;
	    this.numBuffers = numBuffers;
	    this.dataBuffers = new char[numBuffers][bufferSize];

	    this.streamLength = (new File(filename)).length();
	    if(this.streamLength > Integer.MAX_VALUE)
		throw new RuntimeException("Stream too big for this buffer");

	    this.streamReader = new InputStreamReader(new FileInputStream(filename));

	    this.currentPosition = 0;
	    this.currentBuffer = 0;
	    this.currentColumn = 0;
	    this.firstPositionAvailable = 0;
	    this.lastPositionAvailable = bufferSize * numBuffers - 1;

	    loadBuffers();
	}

	public char getChar(int p)
	{
	    try
	    {
		if(p < firstPositionAvailable)
		    throw new RuntimeException("Buffer to small to access a passed position");

		if(p >= streamLength)
		    return (char) EOF;

		while(p > lastPositionAvailable)
		    rollAndLoadBuffer();

		calculateCursorForPosition(p);

		return dataBuffers[currentBuffer][currentColumn];
	    }
	    catch(IOException e)
	    {
		throw new RuntimeException(e.getMessage());
	    }
	}

	private void calculateCursorForPosition(int p)
	{
	    int offset = p - firstPositionAvailable;
	    currentPosition = p;
	    currentBuffer = offset / bufferSize;
	    currentColumn = offset % bufferSize;
	}

	private void rollAndLoadBuffer() throws IOException
	{
	    char[] bufferToOverwrite = dataBuffers[0];

	    for(int i = 0; i < numBuffers - 1; ++i)
		dataBuffers[i] = dataBuffers[i + 1];

	    dataBuffers[numBuffers - 1] = bufferToOverwrite;
	    streamReader.read(dataBuffers[numBuffers - 1]);

	    firstPositionAvailable += bufferSize;
	    lastPositionAvailable += bufferSize;

	    if(lastPositionAvailable > streamLength)
		lastPositionAvailable = (int) streamLength;
	}

	private void loadBuffers() throws IOException
	{
	    for(int i = 0; i < numBuffers; ++i)
		streamReader.read(dataBuffers[i]);
	}

	public long length()
	{
	    return streamLength;
	}

	public void close() throws IOException
	{
	    streamReader.close();
	}

	public int getCurrentPosition()
	{
	    return currentPosition;
	}
    }
}
