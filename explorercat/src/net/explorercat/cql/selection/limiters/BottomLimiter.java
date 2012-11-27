package net.explorercat.cql.selection.limiters;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.cql.selection.query.QuerySQLTranslationException;
import net.explorercat.data.QueryableDataEntity;

/**
 * Implementation of the bottom limit clause that will select the n last
 * entities from the given list with an optional offset.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public class BottomLimiter extends LimiterBase implements Limiter
{
    /**
     * Creates a bottom limiter that will select the last n with a given offset.
     * 
     * @param maxNumberOfEntities The maximum number of elements to be selected.
     * @param offset The offset (beginning from the last element).
     */

    public BottomLimiter(int maxNumberOfEntities, int offset)
    {
	super(maxNumberOfEntities, offset);
    }

    @Override
    public List<QueryableDataEntity> limit(List<QueryableDataEntity> baseSelection)
    {
	ArrayList<QueryableDataEntity> limitedSelection = new ArrayList<QueryableDataEntity>(maxNumberOfEntities);

	for(int i = baseSelection.size() - offset - 1; i > 0 && limitedSelection.size() < maxNumberOfEntities; --i)
	    limitedSelection.add(baseSelection.get(i));

	return limitedSelection;
    }

    @Override
    public String toString()
    {
	return "BOTTOM(" + maxNumberOfEntities + "," + offset + ")";
    }

    @Override
    public String translateToSQL() throws QuerySQLTranslationException
    {
	throw new QuerySQLTranslationException("It is not possible to translate a bottom selection limiter to SQL");
    }

    @Override
    public LimiterType getType()
    {
	return LimiterType.BOTTOM_LIMITER;
    }

    @Override
    public String getStringKey()
    {
	return toString();
    }
}
