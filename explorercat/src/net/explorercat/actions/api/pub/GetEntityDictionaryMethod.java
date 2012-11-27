package net.explorercat.actions.api.pub;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.actions.api.JSONBasedAPIMethod;
import net.explorercat.application.ApplicationController;
import net.explorercat.cql.types.IncompatibleTypeException;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDefinition;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.QueryableDataEntityDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API method that gets the property dictionary of an entity type for a given
 * catalog.
 * 
 * - Input : { catalogId, entityType }
 * 
 * - Output : { returnCode,[propertyDescriptors{name, type, description,
 * minimumValue, maximumValue, referencedEntity, referencedProperty,
 * [allowedValues] }] }
 * 
 * - Error output : { returnCode != 0, errorMessage }
 * 
 * If there is an error (returnCode != 0) the JSON object returned provides a
 * returnCode and an errorMessage. Error return codes are:
 * 
 * - 1 There was an error retrieving the entity dictionary.
 * 
 * - 2 Unknown catalog id.
 * 
 * - 3 Unknown entity type.
 * 
 * - 4 Entity type not provided.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date June 2011
 */

public class GetEntityDictionaryMethod extends JSONBasedAPIMethod
{
    private static final Log log = LogFactory.getLog(GetEntityDictionaryMethod.class);

    // Input Parameter: the identifier of the catalog.
    private int catalogId;

    // Input Parameter: the type of entity (i.e. name) whose dictionary we are requesting.
    private String entityType;

    // Output Parameter: Normal JSON response.
    private NormalJSONResponse normalResponse;

    // Input parameters setters.

    public void setCatalogId(int catalogId)
    {
	this.catalogId = catalogId;
    }

    public void setEntityType(String entityType)
    {
	this.entityType = entityType;
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
	    log.debug("Getting entity dictionary info for " + entityType + " (" + catalogId + ")");
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

	    QueryableDataEntityDAO entityDAO = catalog.getEntityDAO(entityType);

	    if(entityDAO == null)
	    {
		if(entityType != null)
		    setErrorResponse(3, "Unknown entity type (" + entityType + ")");
		else
		    setErrorResponse(4, "Entity type not provided");

		return ERROR;
	    }

	    PropertyDictionary dictionary = entityDAO.getPropertyDictionary();
	    List<String> propertyNames = dictionary.getPropertyNames();
	    List<PropertyDefinition> propertyDefinitions = new ArrayList<PropertyDefinition>();

	    for(String currentProperty : propertyNames)
		propertyDefinitions.add(dictionary.getPropertyDefinition(currentProperty));
	    
	    this.normalResponse = new NormalJSONResponse(propertyDefinitions);
	    return SUCCESS;
	}
	catch(Exception e)
	{
	    String errorMessage = "Error trying to retrieve dictionary for " + entityType + ", " + e.getMessage();
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
	// Entity descriptors
	private List<PropertyDescriptor> propertyDescriptors;

	public NormalJSONResponse(List<PropertyDefinition> propertyDefinitions) throws IncompatibleTypeException
	{
	    super(0);	    
	    
	    this.propertyDescriptors = new ArrayList<PropertyDescriptor>();	    
	    for(PropertyDefinition currentDefinition : propertyDefinitions)	    
		this.propertyDescriptors.add(new PropertyDescriptor(currentDefinition));
	}

	/**
	 * Gets the list of property descriptors for the entity type.
	 */

	public List<PropertyDescriptor> getPropertyDescriptors()
	{
	    return this.propertyDescriptors;
	}

	/**
	 * Inner class that describes an entity property.
	 * 
	 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
	 * @date 29 Jun 2011
	 */

	public static class PropertyDescriptor
	{
	    private String name;
	    private String type;
	    private String description;
	    private Float minimumValue;
	    private Float maximumValue;
	    private String referencedEntity;
	    private String referencedProperty;
	    private List<String> allowedValues;

	    public PropertyDescriptor(PropertyDefinition property) throws IncompatibleTypeException
	    {		
		this.name = property.getName();
		this.type = property.getType().toString();
		this.description = property.getDescription();		
		
		// Take extra-care with the nulls.
		this.minimumValue = property.getMinimumValue() == null ? null
			: property.getMinimumValue().getValueAsReal();

		this.maximumValue = property.getMaximumValue() == null ? null
			: property.getMaximumValue().getValueAsReal();				
		
		this.referencedEntity = property.getReferencedEntity();
		this.referencedProperty = property.getReferencedAttribute();
		this.allowedValues = property.getAllowedValues();
	    }

	    public String getName()
	    {
		return name;
	    }

	    public String getType()
	    {
		return type;
	    }

	    public String getDescription()
	    {
		return description;
	    }

	    public Float getMinimumValue()
	    {
		return minimumValue;
	    }

	    public Float getMaximumValue()
	    {
		return maximumValue;
	    }

	    public String getReferencedEntity()
	    {
		return referencedEntity;
	    }

	    public String getReferencedProperty()
	    {
		return referencedProperty;
	    }

	    public List<String> getAllowedValues()
	    {
		return allowedValues;
	    }
	}
    }
}
