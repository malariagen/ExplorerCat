package net.explorercat.cql.result.builders;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.tasks.ProcessingTask;
import net.explorercat.tasks.ProcessingTaskException;

/**
 * Builder in charge of building a result whose data is processed by a
 * preprocessing tasks. It acts as a decorator, delegating the data retrieval to
 * the decorated result builder.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 Jan 2011
 */

public class PreprocessedResultBuilder implements ResultBuilder
{
    private ResultBuilder resultBuilder;
    private ProcessingTask task;

    /**
     * Creates a new result builder that will delegate the data operations to the
     * given builder and will use the given task to preprocess the data.
     * 
     * @param baseResultBuilder Base result builder that will be decorated with
     *        the processing task.
     * @param task Task that will process the data before building the result.
     */

    public PreprocessedResultBuilder(ResultBuilder baseResultBuilder, ProcessingTask task)
    {
	this.resultBuilder = baseResultBuilder;
	this.task = task;
    }

    @Override
    public Result buildResult(int numEntities, int offset) throws ResultGenerationException
    {
	try
	{	    
	    Result result = resultBuilder.buildResult(numEntities, offset);	    	 	    
	    return task.processData(result);
	}
	catch(ProcessingTaskException e)
	{
	    throw new ResultGenerationException("Error preprocessing the data", e);
	}
    }

    @Override
    public ResultHeader getHeader() throws ResultGenerationException
    {
	ResultHeader header = resultBuilder.getHeader();

	// Update the header using the task.		
	header = task.transformHeaders(header);

	return header;
    }

    @Override
    public String getResultLabel()
    {
	return resultBuilder.getResultLabel();
    }

    @Override
    public int getNumberOfSelectedEntities() throws ResultGenerationException
    {
	return resultBuilder.getNumberOfSelectedEntities();
    }

    @Override
    public void resolveResult(SelectionResolver resolver) throws SelectionResolutionException
    {
	resultBuilder.resolveResult(resolver);
    }

    @Override
    public void sortSourceSelectionByPropertyValue(String sortingPropertyName, boolean sortInDescendantOrder)
    {
	resultBuilder.sortSourceSelectionByPropertyValue(sortingPropertyName, sortInDescendantOrder);
    }
}
