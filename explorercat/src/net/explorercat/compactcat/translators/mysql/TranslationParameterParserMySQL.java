package net.explorercat.compactcat.translators.mysql;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.compactcat.translators.TranslationParameterParser;
import net.explorercat.compactcat.translators.mysql.TranslationParameterMySQL.IndexingParameter;
import net.explorercat.compactcat.translators.mysql.TranslationParameterMySQL.NoStatsParameter;
import net.explorercat.compactcat.translators.mysql.TranslationParameterMySQL.PartitioningParameter;
import net.explorercat.compactcat.translators.mysql.TranslationParameterMySQL.MySQLParameterType;
import net.explorercat.util.exceptions.TranslationException;

/**
 * Parser implementation for MySQL parameters.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 5 Apr 2011
 */

public class TranslationParameterParserMySQL implements TranslationParameterParser
{
    @Override
    public TranslationParameter parseParameter(String parameterAsString) throws TranslationException
    {
	try
	{
	    StringTokenizer tokenizer = new StringTokenizer(parameterAsString, ":");
	    String keyword = tokenizer.nextToken();

	    switch(MySQLParameterType.valueOf(keyword))
	    {
		case PARTITIONING:
		    return parsePartitioningParameter(tokenizer);

		case INDEXING:
		    return parseIndexingParameter(tokenizer);

		case NOSTATS:
		    return parseNoStatsParameter(tokenizer);

		default:
		    throw new TranslationException("Error, wrong specification for translation parameter: "
						   + parameterAsString);
	    }
	}
	catch(NoSuchElementException e)
	{
	    throw new TranslationException("Error, wrong specification for translation parameter: " + parameterAsString);
	}
    }

    private PartitioningParameter parsePartitioningParameter(StringTokenizer tokenizer)
    {
	String entity = tokenizer.nextToken();
	String attribute = tokenizer.nextToken();
	int numPartitions = Integer.parseInt(tokenizer.nextToken());
	return new PartitioningParameter(entity, attribute, numPartitions);
    }

    private IndexingParameter parseIndexingParameter(StringTokenizer tokenizer)
    {
	String entity = tokenizer.nextToken();
	String attribute = tokenizer.nextToken();
	return new IndexingParameter(entity, attribute);
    }

    private NoStatsParameter parseNoStatsParameter(StringTokenizer tokenizer)
    {
	String entity = tokenizer.nextToken();
	return new NoStatsParameter(entity);
    }
}
