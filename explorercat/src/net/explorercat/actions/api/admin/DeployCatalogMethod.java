package net.explorercat.actions.api.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.explorercat.actions.PropertyLookup;
import net.explorercat.actions.api.admin.UpdatePluginsMethod.NormalJSONResponse;
import net.explorercat.compactcat.deployer.CompactCatDeployer;
import net.explorercat.util.exceptions.TranslationException;
import net.explorercat.util.misc.FileDeleter;
import net.explorercat.util.sql.dataconnectors.mysql.ProductionMySQLDataConnector;

import org.antlr.runtime.RecognitionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that deploys a catalog (CompactCat format) on the system.
 * Communication with the client is done via JSON objects. Notice that a return
 * code value different from zero means there was an error performing the
 * deployment and an error message can be queried from the response.
 * 
 * TODO Write the API documentation with proper JavaDoc.
 * 
 * - deployCatalog: Input{user, password, catalogPath, (translationParameters),
 * (readFrameSize)}, Output{returnCode}. Where content is the text of the piece
 * of news (can be HTML).[returnCode = 2] means there was an error accessing the
 * news DAO. Deploys a catalog (in CompactCat format) on the system.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date April 2011
 */

public class DeployCatalogMethod extends AdminAPIMethod
{
    private static final Log log = LogFactory.getLog(DeployCatalogMethod.class);

    // Input Parameter: Path to the CompactCat catalog to deploy.
    private String catalogPath;

    // Input Parameter: Parameters to guide the translation 
    private String translationParameters;

    // Input Parameter: Specifies the number of consecutive rows 
    // to read from the data file (default value used if not provided).
    private int readFrameSize = 5000;

    // Output Parameter: Normal JSON response (success).
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public void setCatalogPath(String catalogPath)
    {
	this.catalogPath = catalogPath;
    }

    public void setTranslationParameters(String parameters)
    {
	this.translationParameters = parameters;
    }

    public void setReadFrameSize(int numRows)
    {
	this.readFrameSize = numRows;
    }

    // Output parameters getters

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    /**
     * Deploys the CompactCat catalog. We copy the catalog file, extract the
     * catalog components (dictionary and data), deploy the catalog parsing
     * these files (using the translation parameters if any) and finally we
     * delete the temporary files extracted.
     */

    @Override
    public String execute()
    {
	try
	{
	    if(hasUserAdminRights())
	    {
		long startTime = System.currentTimeMillis();
		File catalogFile = copyCatalogFileIntoCatalogsFolder();

		if(catalogFile == null)
		    return ERROR;

		List<File> extractedFiles = extractCatalogFileContents(catalogFile);

		if(extractedFiles == null)
		    return ERROR;

		File dictionary = extractedFiles.get(0);
		File data = extractedFiles.get(1);

		CompactCatDeployer deployer = new CompactCatDeployer(dictionary, data, getListOfTranslationParameters());
		deployer.setConsecutiveRowsToRead(readFrameSize);

		// Turn off the strict mode just for the deployment of the catalog.
		ProductionMySQLDataConnector.setStrictMode(false);
		deployer.deployCatalog();
		ProductionMySQLDataConnector.setStrictMode(true);

		deleteTemporalFiles(extractedFiles);

		long endTime = System.currentTimeMillis();

		this.normalResponse = new NormalJSONResponse((int) ((endTime - startTime) / 1000));
		return SUCCESS;
	    }
	    else
	    {
		setErrorResponse(4, "User has not privileges to perform this operation");
		return ERROR;
	    }
	}
	catch(RecognitionException e)
	{
	    String errorMessage = "Error parsing the catalog, " + e.getMessage();
	    log.error(errorMessage);
	    setErrorResponse(5, errorMessage);
	    return ERROR;
	}
	catch(TranslationException e)
	{
	    String errorMessage = "Error translating the catalog, " + e.getMessage();
	    log.error(errorMessage);
	    setErrorResponse(6, errorMessage);
	    return ERROR;
	}
	catch(IOException e)
	{
	    String errorMessage = "Error accessing the catalog files, " + e.getMessage();
	    log.error(errorMessage);
	    setErrorResponse(7, errorMessage);
	    return ERROR;
	}
	catch(Throwable e)
	{
	    String errorMessage = "Unknown error deploying catalog, " + e.getMessage();
	    e.printStackTrace();
	    log.error(errorMessage);
	    setErrorResponse(8, errorMessage);
	    return ERROR;
	}
    }

    /**
     * Parses the parameters provided to guide the translation process.
     * 
     * @return A list containing all the parameters provided as strings.
     */

    private List<String> getListOfTranslationParameters()
    {
	List<String> splitParameters = new ArrayList<String>();

	if(this.translationParameters != null)
	{
	    StringTokenizer splitter = new StringTokenizer(this.translationParameters, ",");

	    while(splitter.hasMoreTokens())
		splitParameters.add(splitter.nextToken());
	}

	return splitParameters;
    }

    /**
     * Copy the catalog file into the catalogs folder if it is not already
     * there.
     * 
     * @return The file represented the copied catalog or null if there was an
     *         error copying the file.
     */

    private File copyCatalogFileIntoCatalogsFolder()
    {
	File catalogFile = null;
	String catalogsFolder = PropertyLookup.getCatalogsFolder();

	if(log.isDebugEnabled())
	    log.debug("Copying catalog file " + this.catalogPath + " into " + catalogsFolder);

	try
	{
	    if(catalogPath != null)
	    {
		catalogFile = new File(this.catalogPath);

		if(catalogFile.exists() && catalogFile.canRead() && catalogFile.isFile())
		{
		    if(!catalogFile.getAbsolutePath().startsWith(catalogsFolder))
		    {
			Process cp = Runtime.getRuntime().exec("cp " + catalogFile + " " + catalogsFolder);
			cp.waitFor();
		    }

		    String missingSlash = catalogsFolder.charAt(catalogsFolder.length() - 1) == '/' ? "" : "/";
		    return new File(catalogsFolder + missingSlash + catalogFile.getName());
		}
		else
		{
		    String errorMessage = "CompactCat file does not exist or unreadable (" + this.catalogPath + ")";
		    log.error(errorMessage);
		    setErrorResponse(1, errorMessage);
		    return null;
		}
	    }
	    else
	    {
		String errorMessage = "CompactCat file not specified.";
		log.error(errorMessage);
		setErrorResponse(2, errorMessage);
		return null;
	    }
	}
	catch(Exception e)
	{
	    String errorMessage = "Error copying catalog file, " + e.getMessage();
	    log.error(errorMessage);
	    setErrorResponse(3, errorMessage);
	    return null;
	}
    }

    /**
     * Extracts the files compressed within the CompactCat catalog file.
     * 
     * @return A list containing two files representing the dictionary (at 0)
     *         and the data (at 1). It will return null if there is an error
     *         extracting the files.
     */

    private List<File> extractCatalogFileContents(File catalogFile)
    {
	try
	{
	    if(log.isDebugEnabled())
		log.debug("Extracting components for catalog: " + catalogFile.getAbsolutePath());

	    String catalogsFolder = PropertyLookup.getCatalogsFolder();
	    String unzipCommand = "unzip -o " + catalogFile.getAbsolutePath() + " -d " + catalogsFolder;

	    Process unzip = Runtime.getRuntime().exec(unzipCommand);
	    unzip.waitFor();

	    String missingSlash = catalogsFolder.charAt(catalogsFolder.length() - 1) == '/' ? "" : "/";
	    File dictionaryFile = new File(catalogsFolder + missingSlash + PropertyLookup.getDictionaryFilename());
	    File dataFile = new File(catalogsFolder + missingSlash + PropertyLookup.getDataFilename());

	    List<File> extractedFiles = new ArrayList<File>(2);
	    extractedFiles.add(dictionaryFile);
	    extractedFiles.add(dataFile);

	    return extractedFiles;
	}
	catch(Exception e)
	{
	    String errorMessage = "Error extracting the catalog files for " + catalogFile.getAbsolutePath();
	    log.error(errorMessage);
	    setErrorResponse(5, errorMessage);
	    return null;
	}
    }

    /**
     * Deletes a given list of temporal files.
     */

    private void deleteTemporalFiles(List<File> files)
    {
	for(File currentFile : files)
	    FileDeleter.deleteFile(currentFile.getAbsolutePath());
    }

    /**
     * Encapsulates the JSON response that is sent back to the user.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date May 2011
     */

    public static class NormalJSONResponse extends JSONResponse
    {
	int deploymentTimeInSeconds;

	public NormalJSONResponse(int deploymentTimeInSeconds)
	{
	    super(0);
	    this.deploymentTimeInSeconds = deploymentTimeInSeconds;
	}

	/**
	 * Gets the time (in seconds) that took to deploy the catalog.
	 */

	public int getDeploymentTime()
	{
	    return this.deploymentTimeInSeconds;
	}
    }
}
