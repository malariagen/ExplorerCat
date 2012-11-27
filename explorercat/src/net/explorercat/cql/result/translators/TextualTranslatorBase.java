package net.explorercat.cql.result.translators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.Result.ResultRow;
import net.explorercat.cql.result.ResultHeader.ResultColumn;
import net.explorercat.cql.types.IncompatibleTypeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for textual translators. (refactored from previous code).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 22 Sep 2011
 */

public abstract class TextualTranslatorBase implements ResultTextualTranslator
{
    private static final Log log = LogFactory.getLog(TextualTranslatorBase.class);
    private static final int BUFFER_SIZE = 1024 * 1024;

    private StringBuilder writingBuffer;
    private int numWrittenBytes;
    private String fieldSeparator;
    private String rowSeparator;
    private String fileFormat;

    /**
     * Creates a textual translator for text files where fields are separated by
     * the given string.
     * 
     * @param fieldSeparator Text separator for fields.
     * @param rowSeparator Text separator for rows.
     * @parma format Name of the text file format.
     */

    public TextualTranslatorBase(String fieldSeparator, String rowSeparator, String format)
    {
	this.writingBuffer = new StringBuilder(BUFFER_SIZE);
	this.numWrittenBytes = 0;
	this.fieldSeparator = fieldSeparator;
	this.rowSeparator = rowSeparator;
	this.fileFormat = format;
    }

    @Override
    public void translateResultIntoFile(ResultHeader fieldHeaders, List<ResultRow> dataRows, String filename,
					int maxFileSizeInMB) throws ResultTranslationException
    {
	if(log.isDebugEnabled())
	    log.debug("Translating result into " + fileFormat + "(" + filename + ") [" + dataRows.size() + " rows]");

	File file = new File(filename);
	Writer fileWriter = null;

	try
	{
	    int maxFileSizeInBytes = maxFileSizeInMB * 1024 * 1024;
	    fileWriter = new BufferedWriter(new FileWriter(file));

	    writeHeaders(fileWriter, fieldHeaders);

	    for(ResultRow row : dataRows)
	    {
		writeDataRow(fileWriter, row);

		if(numWrittenBytes > maxFileSizeInBytes)
		{
		    fileWriter.close();
		    file.delete();
		    throw new ResultTooBigException("The result was too big to be written as a file. " + "Bigger than "
						    + maxFileSizeInMB + " MBs");
		}
	    }

	    fileWriter.close();
	}
	catch(IOException e)
	{
	    file.delete();
	    throw new ResultTranslationException("Error translating into CSV, " + e);
	}
	catch(IncompatibleTypeException e)
	{
	    file.delete();
	    throw new ResultTranslationException("Error translating into CSV, " + e);
	}
    }

    /**
     * Auxiliary method that writes the headers into the file.
     * 
     * @param fileWriter The writer in charge of writing into the file.
     * @param fieldHeaders Ehaders that will be translated.
     */

    private void writeHeaders(Writer fileWriter, ResultHeader fieldHeaders) throws IOException
    {
	List<ResultColumn> columns = fieldHeaders.getColumns();

	for(int i = 0; i < columns.size() - 1; ++i)
	{
	    writeFieldHeader(fileWriter, columns.get(i));
	    fileWriter.write(fieldSeparator);
	    ++numWrittenBytes;
	}

	// Write the last header (no comma).
	if(columns.size() > 0)
	    writeFieldHeader(fileWriter, columns.get(columns.size() - 1));

	fileWriter.write(rowSeparator);
	++numWrittenBytes;
    }

    /**
     * Auxiliary method that writes the column name.
     */

    private void writeFieldHeader(Writer fileWriter, ResultColumn column) throws IOException
    {
	String originalName = column.getName();
	String alias = column.getAlias();

	String text = alias.isEmpty() ? originalName : alias;
	fileWriter.write(text);
	numWrittenBytes += text.length() + 2;
    }

    /**
     * Auxiliary method that writes a data row into the file.
     */

    private void writeDataRow(Writer fileWriter, ResultRow row) throws IOException, IncompatibleTypeException
    {
	List<String> values = row.getValues();
	writingBuffer.setLength(0);

	for(int i = 0; i < values.size() - 1; ++i)
	    writingBuffer.append(values.get(i)).append(fieldSeparator);

	// Write the last value (no separator).
	if(values.size() > 0)
	    writingBuffer.append(values.get(values.size() - 1));

	writingBuffer.append(rowSeparator);
	numWrittenBytes += writingBuffer.length();

	// Write the line into the file.
	fileWriter.write(writingBuffer.toString());
    }
}
