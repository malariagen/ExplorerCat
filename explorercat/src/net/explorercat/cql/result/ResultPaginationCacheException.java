package net.explorercat.cql.result;

import net.explorercat.util.exceptions.ExplorerCatCheckedException;

/**
 * Exception when accession the cache for paginated results.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 26 Oct 2010
 */

public class ResultPaginationCacheException extends ExplorerCatCheckedException
{
    public ResultPaginationCacheException(String msg)
    {
	super(msg);
    }

    public ResultPaginationCacheException(String msg, Exception e)
    {
	super(msg + "\n\t" + e.getMessage(), e);
    }

    public ResultPaginationCacheException(Exception e)
    {
	super(e);
    }
}
