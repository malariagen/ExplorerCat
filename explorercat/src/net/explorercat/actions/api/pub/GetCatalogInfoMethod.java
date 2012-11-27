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
 * API method that gets info about the specified catalog.
 * 
 * - Input : { catalogId }
 * 
 * - Output : { returnCode, id, name, releaseDate, version, description,
 * [entityDescriptors{type, description, instances}] }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error retrieving catalog info.
 * 
 * - 2 Unknown catalog id.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date June 2011
 */

public class GetCatalogInfoMethod extends JSONBasedAPIMethod
{
    private static final Log log = LogFactory.getLog(GetCatalogInfoMethod.class);

    // Input Parameter: the identifier of the catalog.
    private int catalogId;

    // Output Parameter: Normal JSON response.
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public void setCatalogId(int catalogId)
    {
	this.catalogId = catalogId;
    }

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
	    log.debug("Getting catalog info for catalog id: " + catalogId);
	try
	{
	    CatalogDAO catalogDAO = ApplicationController.getInstance().getDAOFactory().getCatalogDAO();
	    Catalog catalog = catalogDAO.findCatalog(catalogId);

	    if(catalog == null)
	    {
		String errorMessage = "Unknown catalog id (" + catalogId + ")";
		setErrorResponse(2, errorMessage);
		return ERROR;
	    }

	    this.normalResponse = new NormalJSONResponse(catalog);
	    return SUCCESS;
	}
	catch(Exception e)
	{
	    String errorMessage = "Error trying to retrieve info for catalog (" + catalogId + "), " + e.getMessage();
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
	// Catalog Info
	private int id;
	private String name;
	private String releaseDate;
	private String version;
	private String description;

	// Entity descriptors
	List<EntityDescriptor> entityDescriptors;

	public NormalJSONResponse(Catalog catalog)
	{
	    super(0);
	    this.id = catalog.getId();
	    this.name = catalog.getName();
	    this.version = catalog.getVersion();
	    this.description = catalog.getDescription();

	    GregorianCalendar date = catalog.getReleaseDate();
	    this.releaseDate = date.get(Calendar.DAY_OF_MONTH) + "-" + date.get(Calendar.MONTH) + "-"
			       + date.get(Calendar.YEAR);

	    this.entityDescriptors = new ArrayList<EntityDescriptor>();

	    List<String> entityTypes = catalog.getEntityTypes();
	    List<String> entityDescriptions = catalog.getEntityDescriptions();

	    for(int i = 0; i < entityTypes.size(); ++i)
	    {
		String currentType = entityTypes.get(i);
		String currentDescription = entityDescriptions.get(i);
		int numberOfInstances = catalog.getEntityDAO(currentType).getSelectionSize();

		this.entityDescriptors.add(new EntityDescriptor(currentType, currentDescription, numberOfInstances));
	    }
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

	public List<EntityDescriptor> getEntityDescriptors()
	{
	    return entityDescriptors;
	}

	/**
	 * Inner class that describes a catalog.
	 * 
	 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
	 * @date 29 Jun 2011
	 */

	public static class EntityDescriptor
	{
	    private String type;
	    private String description;
	    private int instances;

	    public EntityDescriptor(String type, String description, int numInstances)
	    {
		this.type = type;
		this.description = description;
		this.instances = numInstances;
	    }

	    public String getType()
	    {
		return type;
	    }

	    public String getDescription()
	    {
		return description;
	    }

	    public int getInstances()
	    {
		return instances;
	    }
	}
    }
}
