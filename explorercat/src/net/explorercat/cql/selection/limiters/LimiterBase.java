package net.explorercat.cql.selection.limiters;

/**
 * Base class for limiters.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 4 Mar 2011
 */

public abstract class LimiterBase implements Limiter
{
    protected int maxNumberOfEntities;
    protected int offset;

    /**
     * Creates a base limiter that will select n elements with a given offset.
     * 
     * @param maxNumberOfEntities The number of elements to be selected.
     * @param offset The offset for the selection.
     */

    public LimiterBase(int maxNumberOfEntities, int offset)
    {
	this.maxNumberOfEntities = maxNumberOfEntities;
	this.offset = offset;
    }

    @Override
    public int getMaxNumberOfEntities()
    {
	return this.maxNumberOfEntities;
    }

    @Override
    public int getOffset()
    {
	return this.offset;
    }
}
