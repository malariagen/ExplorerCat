package net.explorercat.actions.api.pub;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.CatalogDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that gets the list of all the available catalogs.
 * 
 * - Input : {}
 * 
 * - Output : { returnCode, [catalogDescriptors{id, name, releaseDate, version,
 * description}] }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error retrieving the catalogs.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date June 2011
 */

public class GetCatalogsMethod extends JSONBasedAPIMethod
{
    private static final Log log = LogFactory.getLog(GetCatalogsMethod.class);

    // Output Parameter: Normal JSON response.
    private NormalJSONResponse normalResponse;

    // Output parameters

    public NormalJSONResponse getNormalResponse()
    {
	return this.normalResponse;
    }

    /**
     * Executes the action, returning the location of the resource (the user can
     * use that path to retrieve it).
     */

    @Override
    public String execute()
    {
	if(log.isDebugEnabled())
	    log.debug("Getting list of available catalogs");
	try
	{
	    CatalogDAO catalogDAO = ApplicationController.getInstance().getDAOFactory().getCatalogDAO();
	    List<Catalog> catalogs = catalogDAO.findAllCatalogs();
	    this.normalResponse = new NormalJSONResponse(catalogs);
	    return SUCCESS;
	}
	catch(Exception e)
	{
	    String errorMessage = "Error trying to retrieve the catalogs list, " + e.getMessage();
	    log.error(errorMessage);
	    setErrorResponse(1, errorMessage);
	    return ERROR;
	}
    }

    /**
     * Inner class that encapsulates the JSON response that is sent to the user.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date Jun 2011
     */

    public static class NormalJSONResponse extends JSONResponse
    {
	private List<CatalogDescriptor> catalogDescriptors;

	public NormalJSONResponse(List<Catalog> catalogs)
	{
	    super(0);
	    this.catalogDescriptors = new ArrayList<CatalogDescriptor>();

	    for(Catalog currentCatalog : catalogs)
		this.catalogDescriptors.add(new CatalogDescriptor(currentCatalog));
	}

	public List<CatalogDescriptor> getCatalogDescriptors()
	{
	    return catalogDescriptors;
	}

	/**
	 * Inner class that describes a catalog
	 * 
	 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
	 * @date 29 Jun 2011
	 */

	public static class CatalogDescriptor
	{
	    private int id;
	    private String name;
	    private String releaseDate;
	    private String version;
	    private String description;

	    public CatalogDescriptor(Catalog catalog)
	    {
		this.id = catalog.getId();
		this.name = catalog.getName();
		this.version = catalog.getVersion();
		this.description = catalog.getDescription();

		GregorianCalendar date = catalog.getReleaseDate();
		this.releaseDate = date.get(Calendar.DAY_OF_MONTH) + "-" + date.get(Calendar.MONTH) + "-"
				   + date.get(Calendar.YEAR);
	    }

	    public int getId()
	    {
		return id;
	    }

	    public String getName()
	    {
		return name;
	    }

	    public String getReleaseDate()
	    {
		return releaseDate;
	    }

	    public String getVersion()
	    {
		return version;
	    }

	    public String getDescription()
	    {
		return description;
	    }
	}
    }
}
