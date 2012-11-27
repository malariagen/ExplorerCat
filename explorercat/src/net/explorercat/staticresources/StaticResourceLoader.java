package net.explorercat.staticresources;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.explorercat.application.ApplicationController;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.util.misc.XMLTagValuesExtractor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Loads and registers static resources associated with a catalog. The user
 * specify the resources by means of an XML file.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 27 Sep 2010
 */

public class StaticResourceLoader
{
    // Logging
    private static final Log log = LogFactory.getLog(StaticResourceLoader.class);

    /**
     * Loads static resources into the repository from an XML file.
     * 
     * TODO The code that is in charge of loading precalculated-queries, static
     * resources, etc. can be generalise and refactored.
     * 
     * @param filePath The path to the XML file that contains the description of
     *        the resources.
     */

    public void loadStaticResourcesFromXMLFile(String filePath) throws StaticResourceLoadingException
    {
	try
	{
	    // Get the DOM representation of the XML file.
	    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	    Document manifestDOM = documentBuilder.parse(filePath);
	    Element root = manifestDOM.getDocumentElement();

	    // Extract all the tag values.
	    String[] tags = { "catalogs", "name", "type", "uri", "description" };

	    if(root != null)
	    {
		Map<String, List<String>> tagValues = null;
		tagValues = XMLTagValuesExtractor.getValuesForTags(root, "static-resource", tags);

		// Building process
		int numValues = tagValues.get(tags[0]).size();

		for(int i = 0; i < numValues; ++i)
		{
		    String catalogIds = tagValues.get(tags[0]).get(i);
		    String name = tagValues.get(tags[1]).get(i);
		    String type = tagValues.get(tags[2]).get(i);
		    String uri = tagValues.get(tags[3]).get(i);
		    String description = tagValues.get(tags[4]).get(i);

		    processResource(catalogIds, name, type, uri, description);
		}
	    }
	}
	catch(StaticResourceLoadingException e)
	{
	    throw e;
	}	
	// Catch everything and keep going.
	catch(Exception e)
	{
	    throw new StaticResourceLoadingException(e);
	}
    }

    /**
     * Auxiliary method that processes a static resource, registering it onto
     * the repository.
     */

    private void processResource(String catalogIds, String name, String type, String resourceURI, String description)
	throws StaticResourceLoadingException, DAOException
    {
	// Get all the catalogues.	
	CatalogDAO catalogDAO = ApplicationController.getInstance().getDAOFactory().getCatalogDAO();
	List<Catalog> catalogs = catalogDAO.findAllCatalogs();

	// Get the repository and generate the representation of the resource.
	StaticResourceRepository repository = ApplicationController.getInstance().getStaticResourceRespository();
	StaticResource resource = new StaticResource(name, resourceURI, type, description);	
	
	// Split the catalogue identifiers.
	StringTokenizer tokenizer = new StringTokenizer(catalogIds, ",");
	int numTokens = tokenizer.countTokens();

	while(tokenizer.hasMoreTokens())
	{
	    String catalogId = tokenizer.nextToken();

	    try
	    {
		// Register the resource for all the catalogues.
		if(catalogId.toLowerCase().equals("all"))
		{
		    if(numTokens > 1)
			throw new StaticResourceLoadingException("Invalid catalog specificatin, mixing ALL with ids: "
								 + catalogIds);

		    for(Catalog catalog : catalogs)
			repository.registerResource(catalog.getId(), resource);
		}
		// Register the resource ONLY for a specific catalogue.
		else
		{
		    Catalog specificCatalog = catalogDAO.findCatalog(Integer.valueOf(catalogId));

		    if(specificCatalog == null)
			throw new StaticResourceLoadingException("No catalog is registered for id: " + catalogId);

		    repository.registerResource(specificCatalog.getId(), resource);
		}
	    }
	    catch(NumberFormatException e)
	    {
		throw new StaticResourceLoadingException("Catalog id is not a number: " + catalogId);
	    }
	}
    }
}
