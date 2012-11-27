package net.explorercat.cql.result.builders;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.tasks.ProcessingTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Generates results that can be a subset of properties coming from the entities
 * of a selection (or combined selection) or a statistical value.
 * 
 * This class is supported by result builders that know how to build a concrete
 * type of result.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 13 Aug 2010
 */

public class ResultGenerator
{
    // Logging
    private static final Log log = LogFactory.getLog(ResultGenerator.class);

    private ResultBuilder resultBuilder;

    // String representation that will be used to obtain a hash number.
    private StringBuilder stringRepresentation;

    public ResultGenerator()
    {
	resultBuilder = null;
	stringRepresentation = new StringBuilder();
    }

    /**
     * Sets the result builder
     * 
     * @param resultBuilder The builder that will be in charge of building the
     *        result object internally.
     */

    public void setResultBuilder(ResultBuilder resultBuilder)
    {
	this.resultBuilder = resultBuilder;
	stringRepresentation.append(resultBuilder.toString());
    }

    /**
     * Checks if a result builder has been assigned to the generator.
     * 
     * @return True if a result builder has been assigned to the generator,
     *         false otherwise.
     */

    public boolean hasResultBuilder()
    {
	return this.resultBuilder != null;
    }

    /**
     * Registers a preprocessing task that will be used to preprocess the result
     * data.
     * 
     * @param task The task in charge of processing the data of the result.
     */

    public void registerPreprocessingTask(ProcessingTask task)
    {
	// Decorates the builder with the preprocessing task.
	resultBuilder = new PreprocessedResultBuilder(resultBuilder, task);
    }

    /**
     * Resolves the result builder. It means that any unresolved selection
     * referenced by the result will be resolved.
     * 
     * @param resolver The resolver in charge of resolving selections.
     * @throws SelectionResolutionException If there is a problem resolving any
     *         of the selections.
     */

    public void resolveResult(SelectionResolver resolver) throws SelectionResolutionException
    {
	if(resultBuilder == null)
	    throw new SelectionResolutionException("Undefined result builder");

	if(log.isDebugEnabled())
	{
	    String resultLabel = resultBuilder.getResultLabel();
	    log.debug("Resolving Result : " + "[" + (resultLabel != null ? resultLabel : "unlabeled") + "]");
	}

	resultBuilder.resolveResult(resolver);
    }

    /**
     * Generates a new CQL result that ONLY contains the data for the given
     * number of entities and starting at the offset position (being 0 the first
     * position of a selection).
     * 
     * @param numEntities Number of entities to be retrieved from the result.
     *        Passing -1 means we retrieve all the entities from the offset
     *        position.
     * @param offset Starting position for the result selection (row number =
     *        number of entity).
     * @return A CQL result object that contains the data.
     * @throws ResultGenerationException If there is any error building the
     *         result.
     */

    public Result generateResult(int numEntities, int offset) throws ResultGenerationException
    {
	if(resultBuilder == null)
	    throw new ResultGenerationException("Undefined result builder");

	if(numEntities == -1)
	    numEntities = resultBuilder.getNumberOfSelectedEntities();

	return resultBuilder.buildResult(numEntities, offset);
    }

    /**
     * Gets the header of the result registered in the builder.
     * 
     * @return A header containing the column information for the result.
     */

    public ResultHeader getResultHeader() throws ResultGenerationException
    {
	if(resultBuilder == null)
	    throw new ResultGenerationException("Undefined result builder");
	
	return resultBuilder.getHeader();
    }

    /**
     * Calculates the hash code associated with the result builder. This code
     * will be used by API clients to implement pagination. The first time a
     * result is requested a hash code and a ticket number are returned to the
     * client. Future calls can use this pair of number to access the result
     * stored in the pagination cache without re-sending the whole query again.
     * 
     * @return An integer that identifies the result query embedded in the
     *         builder.
     */

    @Override
    public int hashCode()
    {
	return stringRepresentation.toString().hashCode();
    }

    // Use of equals is forbidden!
    @Override
    public boolean equals(Object o)
    {
	throw new UnsupportedOperationException();
    }

    @Override
    public String toString()
    {
	return stringRepresentation.toString();
    }

    /**
     * Sorts the selection of the given result builder id by a property value.
     * 
     * @param sortingPropertyName The name of the property that will be used to
     *        sort the entities.
     * @param sortInDescendantOrder True if the sort must be in descendant
     *        order.
     */

    public void sortResultSelectionByPropertyValue(String sortingPropertyName, boolean sortInDescendantOrder) throws ResultGenerationException
    {
	if(resultBuilder == null)
	    throw new ResultGenerationException("Undefined result builder");
	
	resultBuilder.sortSourceSelectionByPropertyValue(sortingPropertyName, sortInDescendantOrder);
    }
}
