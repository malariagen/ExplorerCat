package net.explorercat.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import net.explorercat.cql.result.Result;
import net.explorercat.cql.result.ResultHeader;
import net.explorercat.cql.result.Result.ResultRow;
import net.explorercat.cql.types.DataValue;
import net.explorercat.cql.types.DataType;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.cql.types.IntegerValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A processing task that compresses the data points needed to draw a plot.
 * 
 * When a set of data points has to be translated into the system of coordinates
 * of a plot window, many times some of them are mapped to the same pixel
 * location (either for the X coordinate, the Y coordinate or both of them).
 * Assuming the data is a sequence of points along the X axis (one data point
 * per unit), trying to draw a sequence of 1 million data points into a window
 * of 800 pixels width will render 1M/800 = 1250 points per pixel with the same
 * X coordinates. We can optimise the drawing selecting a set of representative
 * points for each "cluster" of 1250 points (e.g. points with minimum and
 * maximum values for the Y coordinate).
 * 
 * Parameters Required:
 * 
 * <code>
 * - columnX: Index of the result column that contains the X coordinates.
 * - columnY: Index of the result column that contains the Y coordinates.
 * - plotWidth: Width of the plot window (in pixels).
 * - plotHeight: Height of the plot window (in pixels).
 * - bottomLeftX: Real X coordinates of the bottom-left corner of the mapping window.
 * - bottomLeftY: Real Y coordinates of the bottom-left corner of the mapping window.
 * - topRightX: Real X coordinates of the top-right corner of the mapping window.
 * - topRightY: Real Y coordinates of the top-right corner of the mapping window.
 * </code>
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 12 Jan 2011
 */

public class PlotDataCompressorTask extends ProcessingTaskBase implements ProcessingTask
{
    // Logging
    private static final Log log = LogFactory.getLog(PlotDataCompressorTask.class);

    // Parameters that HAVE to be passed.
    private static final String[] REQUIRED_PARAMETERS = { "columnX", "columnY", "plotWidth", "plotHeight",
	    "bottomLeftX", "bottomLeftY", "topRightX", "topRightY" };

    // Specifies which columns represents each axis.
    private int columnIndexForX;
    private int columnIndexForY;

    // Dimensions of the plot windows, we'll map the data points to it.
    private int plotWidthInPixels;
    private int plotHeightInPixels;

    // Offset values (bottom-left corner coordinates) to perform the coordinates transformation.
    // These are values are given in real coordinates (i.e. position in the chromosome).
    private float bottomLeftCornerX;
    private float bottomLeftCornerY;

    // Range of the real coordinates window that will be mapped into the plot.
    // Example: Plotting a value across a chromosome.
    // For X it is the length of the genome we are mapping (in number of bases).
    // For Y it is the range (max-min) of the value in that window. 
    private float realCoordinatesRangeX;
    private float realCoordinatesRangeY;

    // Ratios to transform the coordinates (they define the zoom level).
    private float zoomRatioForX;
    private float zoopRatioForY;

    /**
     * Creates a new compressor task.
     * 
     * @param passedParameters Map that contains all the parameters passed to
     *        the API method.
     * @throws ProcessingTaskException
     */

    public PlotDataCompressorTask(Map<String, String[]> passedParameters) throws ProcessingTaskException
    {
	super(passedParameters, REQUIRED_PARAMETERS);

	// Retrieve the user parameters.
	String indexX = this.getParameterValue("columnX")[0];
	String indexY = this.getParameterValue("columnY")[0];
	String plotHeight = this.getParameterValue("plotHeight")[0];
	String plotWidth = this.getParameterValue("plotWidth")[0];
	String bottomLeftX = this.getParameterValue("bottomLeftX")[0];
	String bottomLeftY = this.getParameterValue("bottomLeftY")[0];
	String topRightX = this.getParameterValue("topRightX")[0];
	String topRightY = this.getParameterValue("topRightY")[0];

	this.columnIndexForX = Integer.parseInt(indexX);
	this.columnIndexForY = Integer.parseInt(indexY);
	this.plotHeightInPixels = Integer.parseInt(plotHeight);
	this.plotWidthInPixels = Integer.parseInt(plotWidth);

	this.bottomLeftCornerX = Float.parseFloat(bottomLeftX);
	this.bottomLeftCornerY = Float.parseFloat(bottomLeftY);
	this.realCoordinatesRangeX = Float.parseFloat(topRightX) - bottomLeftCornerX;
	this.realCoordinatesRangeY = Float.parseFloat(topRightY) - bottomLeftCornerY;

	// Ratios that define the zoom level of the mapping window.
	this.zoomRatioForX = plotWidthInPixels / realCoordinatesRangeX;
	this.zoopRatioForY = plotHeightInPixels / realCoordinatesRangeY;
    }

    @Override
    public ResultHeader transformHeaders(ResultHeader header)
    {
	// Notice that the value we are returning is the MAXIMUM number of rows 
	// that will be available after executing the plug-in.
	ResultHeader taskHeader = new ResultHeader(header.getResultName(), header.getNumRows());

	taskHeader.addColumn("x", "X coordinate", "Pixel coordinate for X", DataType.INTEGER);
	taskHeader.addColumn("y", "Y coordinate", "Pixel coordinate for Y", DataType.INTEGER);

	return taskHeader;
    }

    @Override
    public Result processData(Result originalResult) throws ProcessingTaskException
    {
	try
	{
	    List<ResultRow> dataRows = originalResult.getRows();
	    Result processedResult = new Result(originalResult.getLabel());

	    // Note that the index is an output parameter.
	    Integer indexOfTheFirstValidPoint = 0;
	    PointRangeCompressor pointCompressor = initPointRangeCompressor(dataRows, indexOfTheFirstValidPoint);

	    for(int i = indexOfTheFirstValidPoint; i < dataRows.size(); ++i)
	    {
		ResultRow dataRow = dataRows.get(i);
		List<DataValue> rowValues = dataRow.getRawValues();

		// We discard the data point if no valid.
		if(rowValues.get(columnIndexForX) != null && rowValues.get(columnIndexForY) != null)
		{
		    int mappedX = getMappedCoord(rowValues.get(columnIndexForX).getValueAsReal(), bottomLeftCornerX,
						 zoomRatioForX);

		    int mappedY = getMappedCoord(rowValues.get(columnIndexForY).getValueAsReal(), bottomLeftCornerY,
						 zoopRatioForY);

		    pointCompressor.addNextPoint(mappedX, mappedY, dataRow.getId());
		}
	    }

	    if(pointCompressor != null)
	    {
		pointCompressor.finalizeCompression();
		processedResult.addRows(pointCompressor.getPointsAndFinalizeCompression());
	    }

	    return processedResult;
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	    throw new ProcessingTaskException("Error compressing plot data", e);
	}
    }

    /**
     * Auxiliary method that transforms real coordinates into plot coordinates.
     * 
     * @param originalCoordinate Real coordinate to transform.
     * @param offset Offset in real coordinated to be applied (i.e. real
     *        coordinates for the point (0,0) of the plot.
     * @param zoomRatio Ratio that will be used to perform the transformation.
     * @return An integer representing the equivalent plot coordinate (pixel).
     */

    private int getMappedCoord(float originalCoordinate, float offset, float zoomRatio)
    {
	return (int) ((originalCoordinate - bottomLeftCornerX) * zoomRatio);
    }

    /**
     * Finds the first valid data point and configures the point range
     * compressor that will be used to perform the compression.
     * 
     * @param dataRows Data points to be compressed.
     * @param startIndex Input/Output parameter that will contain the index of
     *        the first valid point. The initial value will be used as the
     *        starting position for the searching.
     * @return A configured point range compressor object that can be used to
     *         iterate through the data points.
     */

    private PointRangeCompressor initPointRangeCompressor(List<ResultRow> dataRows, Integer startIndex)
	throws IncompatibleTypeException
    {
	int index = startIndex;
	List<DataValue> row = null;
	boolean invalidCurrentPoint = true;

	while(index < dataRows.size() && invalidCurrentPoint)
	{
	    row = dataRows.get(index).getRawValues();
	    invalidCurrentPoint = row.get(columnIndexForX) == null || row.get(columnIndexForY) == null;
	    ++index;
	}

	startIndex = index; // Update the output parameter.

	if(index < dataRows.size())
	{
	    List<DataValue> rowValues = dataRows.get(index).getRawValues();

	    int mappedX = getMappedCoord(rowValues.get(columnIndexForX).getValueAsReal(), bottomLeftCornerX,
					 zoomRatioForX);
	    int mappedY = getMappedCoord(rowValues.get(columnIndexForY).getValueAsReal(), bottomLeftCornerY,
					 zoopRatioForY);

	    return new PointRangeCompressor(mappedX, mappedY, dataRows.get(index).getId());
	}

	return null;
    }

    /**
     * Auxiliary class that compresses data points with the same X and different
     * Y values. If several points share the same X coordinate, only two of them
     * will be used to represent the whole set. These two points are (x,max(y))
     * and (x,min(y)).
     * 
     * How to use this class: Build the compressor with the initial data point.
     * Iterate through the points adding them to the compressor. Finish the
     * process calling finalizeCompression() after the iteration is done.
     * Retrieving the results using getCompressedPoints().
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 20 Jan 2011
     */

    private static class PointRangeCompressor
    {
	private int currentX;
	private int currentMinY;
	private int currentMaxY;

	private int numPointsInCurrentX;
	private int entityIdForCurrentMinY;
	private int entityIdForCurrentMaxY;

	private List<ResultRow> compressedPoints;
	private boolean hasBeenFinalized;

	/**
	 * Builds a compressor given the coordinates of the first data point.
	 * 
	 * @param x Coordinate in X.
	 * @param y Coordinate in Y.
	 * @param entityId Identifier of the entity that is related to the
	 *        point.
	 */

	public PointRangeCompressor(int x, int y, int entityId)
	{
	    compressedPoints = new ArrayList<ResultRow>();
	    hasBeenFinalized = false;

	    currentX = x;
	    currentMinY = y;
	    entityIdForCurrentMinY = entityId;
	    numPointsInCurrentX = 1;
	}

	/**
	 * Gets the list of compressed points registered so far and finalize the
	 * compression process. DO NOT add more points after callind this
	 * method.
	 * 
	 * @return A list of rows where each row contains a point (x,y) and the
	 *         associated entity id.
	 */

	public List<ResultRow> getPointsAndFinalizeCompression()
	{
	    if(!hasBeenFinalized)
		finalizeCompression();

	    return compressedPoints;
	}

	/**
	 * Adds the next point in the sequence. The compressor will check if it
	 * can be discarded or used as a representative point. Invoking this
	 * method after finalizeCompression will produce an exception.
	 * 
	 * @param x The coordinate in X.
	 * @param y The coordinate in Y.
	 * @param entityId The identifier of the entity associated with the
	 *        point.
	 * @throws OperationNotSupportedException
	 */

	public void addNextPoint(int x, int y, int entityId) throws OperationNotSupportedException
	{
	    // Sanity check we can avoid to boost performance.
	    // Notice this is an inner class only used by the task.
	    // Uncomment if this class becomes public. 
	    /**
	     * if(hasBeenFinalized) throw new OperationNotSupportedException(
	     * "Compression has been finalized, impossible to add a point");
	     **/

	    if(currentX == x)
	    {
		if(y < currentMinY)
		{
		    // With only one point registered the old min Y will be the maximum.
		    if(numPointsInCurrentX == 1)
		    {
			currentMaxY = currentMinY;
			entityIdForCurrentMaxY = entityIdForCurrentMinY;
		    }

		    currentMinY = y;
		    entityIdForCurrentMinY = entityId;
		    ++numPointsInCurrentX;
		}

		// Check we are not getting an identical point.
		else if(y != currentMinY)
		{
		    // We update if we didn't have a max y before or if the new y is greater.
		    if(numPointsInCurrentX == 1 || (numPointsInCurrentX > 1 && y > currentMaxY))
		    {
			currentMaxY = y;
			entityIdForCurrentMaxY = entityId;
		    }

		    ++numPointsInCurrentX;
		}
	    }

	    // A change in X means we have to save the save the current compressed 
	    // point range and start a new one
	    else
	    {
		registerLastCompressedPoint();

		currentX = x;
		currentMinY = y;
		entityIdForCurrentMinY = entityId;
		numPointsInCurrentX = 1;
	    }
	}

	/**
	 * Finishes the compression process. The addNextPoint method MUST NOT be
	 * invoked after calling this method.
	 */

	private void finalizeCompression()
	{
	    registerLastCompressedPoint();
	    numPointsInCurrentX = 0;
	    hasBeenFinalized = true;
	}

	/**
	 * Registers the last compressed point in the processed list. This
	 * method is used when the next point added does not overlap with the
	 * previous one (in X).
	 */

	private void registerLastCompressedPoint()
	{
	    List<DataValue> minValues = new ArrayList<DataValue>(2);

	    // Add the first point of the range.
	    minValues.add(new IntegerValue(currentX));
	    minValues.add(new IntegerValue(currentMinY));
	    compressedPoints.add(new ResultRow(entityIdForCurrentMinY, minValues));

	    // Add the second point of the range if necessary.
	    if(numPointsInCurrentX > 1)
	    {
		List<DataValue> maxValues = new ArrayList<DataValue>(2);

		maxValues.add(new IntegerValue(currentX));
		maxValues.add(new IntegerValue(currentMaxY));
		compressedPoints.add(new ResultRow(entityIdForCurrentMaxY, maxValues));
	    }
	}
    }

}
