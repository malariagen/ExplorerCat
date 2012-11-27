package net.explorercat.dataaccess.cached;

import java.util.Iterator;
import java.util.List;

import net.explorercat.data.QueryableDataEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the iterator interface for data entities that are backed by
 * a memory cache.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public class QueryableDataEntityIteratorCached implements Iterator<QueryableDataEntity>
{
    // Logging
    private static Log log = LogFactory.getLog(QueryableDataEntityIteratorCached.class);

    private List<QueryableDataEntity> entities;
    private int iteratorIndex;

    /**
     * Builds an iterator that will allow to iterate over the collection of
     * cached entities.
     * 
     * @param entities A list containing all the cached data entities.
     */

    public QueryableDataEntityIteratorCached(List<QueryableDataEntity> entities)
    {
	if(log.isDebugEnabled())
	    log.debug("Creating entity iterator base on cache");

	this.entities = entities;
	this.iteratorIndex = 0;
    }

    @Override
    public boolean hasNext()
    {
	return iteratorIndex < entities.size();
    }

    @Override
    public QueryableDataEntity next()
    {
	return entities.get(iteratorIndex++);
    }

    @Override
    public void remove()
    {
	throw new UnsupportedOperationException("Remove operation not supported");
    }
}
