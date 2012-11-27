package net.explorercat.cql.selection.limiters;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.data.QueryableDataEntity;

/**
 * Implementation of the top limiter that will select the n first entities from
 * the given list with an optional offset.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class TopLimiter extends LimiterBase implements Limiter
{
    /**
     * Creates a top limiter that will select the first n elements with a given
     * offset.
     * 
     * @param maxNumberOfEntities The number of elements to be selected.
     * @param offset The offset from the first element.
     */

    public TopLimiter(int maxNumberOfEntities, int offset)
    {
	super(maxNumberOfEntities, offset);
    }

    @Override
    public List<QueryableDataEntity> limit(List<QueryableDataEntity> baseSelection)
    {
	ArrayList<QueryableDataEntity> limitedSelection = new ArrayList<QueryableDataEntity>(maxNumberOfEntities);

	for(int i = offset; i < baseSelection.size() && limitedSelection.size() < maxNumberOfEntities; ++i)
	    limitedSelection.add(baseSelection.get(i));

	return limitedSelection;
    }

    @Override
    public String toString()
    {
	return "TOP(" + maxNumberOfEntities + "," + offset + ")";
    }

    @Override
    public String getStringKey()
    {
	return toString();
    }

    @Override
    public String translateToSQL()
    {
	return "LIMIT " + maxNumberOfEntities + " OFFSET " + offset;
    }

    @Override
    public LimiterType getType()
    {
	return LimiterType.TOP_LIMITER;
    }

}
