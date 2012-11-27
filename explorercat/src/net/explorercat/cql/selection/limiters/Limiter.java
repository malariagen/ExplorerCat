package net.explorercat.cql.selection.limiters;

import java.util.List;

import net.explorercat.cql.selection.query.QuerySQLTranslationException;
import net.explorercat.data.QueryableDataEntity;

/**
 * Interface that specifies the functionality to be provided by any
 * implementation of the CQL limit clause.
 * 
 * A limiter is in charge of discarding a set of entities from a selection (and
 * therefore retaining the rest). Normally limiting a selection is NOT a
 * reversible operation so take care before doing it.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Jul 2010
 */

public interface Limiter
{
    /**
     * Limits a list of entities discarding the entities that don't meet the
     * limit criteria.
     * 
     * @param entities The list of entities that will be limited.
     * @return A list containing the new selection of entities (the entities
     *         that didn't meet the limit criteria will be filtered out). Note
     *         we are not returning a copy of the entities so client code must
     *         not modify them.
     */

    public List<QueryableDataEntity> limit(List<QueryableDataEntity> entities);

    /**
     * Translate the limiter to SQL. Notice right now we are bound to the MySQL
     * implementation.
     * 
     * @return A string containing SQL clause equivalent to the limiter.
     */

    public String translateToSQL() throws QuerySQLTranslationException;

    /**
     * Gets the type of limiter (BOTTOM, TOP, etc.)
     * 
     * @return The type of limiter the object is implementing.
     */

    public LimiterType getType();

    /**
     * Gets a string that identifies the limiter uniquely.
     * 
     * @return A string that identifies uniquely the limiter.
     */

    public String getStringKey();

    /**
     * Gets the maximum number of entities returned by the limiter when the
     * {@link limit} method is called.
     * 
     * @return Maximum number of entities that will be returned by the
     *         {@link limit} method.
     */

    public int getMaxNumberOfEntities();

    /**
     * Gets the offset that will be used by the limiter.
     * 
     * @return The offset used by the limiter or 0 if there is no offset.
     */

    public int getOffset();

}
