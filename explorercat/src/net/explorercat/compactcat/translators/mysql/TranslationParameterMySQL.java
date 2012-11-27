package net.explorercat.compactcat.translators.mysql;

import java.util.ArrayList;
import java.util.List;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.compactcat.translators.mysql.MySQLCodeFragmentPool.CodeFragmentType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Translation parameters that are specific to MySQL.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 6 Apr 2011
 */

public abstract class TranslationParameterMySQL implements TranslationParameter
{
    static protected Log log = LogFactory.getLog(TranslationParameterMySQL.class);

    protected String referencedEntity;
    protected String referencedAttribute;

    /**
     * Creates a new translation parameter to guide the translation into MySQL.
     * 
     * @param referencedEntityName Name of the entity referenced by the
     *        parameter or null.
     * @param referencedAttributeName Name of the attribute referenced by the
     *        parameter or null.
     */

    public TranslationParameterMySQL(String referencedEntityName, String referencedAttributeName)
    {
	this.referencedEntity = referencedEntityName;
	this.referencedAttribute = referencedAttributeName;
    }

    @Override
    public String getReferencedAttribute()
    {
	return this.referencedAttribute;
    }

    @Override
    public String getReferencedEntity()
    {
	return this.referencedEntity;
    }

    @Override
    public boolean referencesEntity(String entity)
    {
	if(this.referencedEntity != null)
	    return this.referencedEntity.equals(entity);

	return false;
    }

    @Override
    public boolean referencesAttribute(String attribute)
    {
	if(this.referencedAttribute != null)
	    return this.referencedAttribute.equals(attribute);

	return false;
    }

    @Override
    public abstract List<Object> getParameterValues();

    @Override
    public abstract TranslationParameterType getType();

    /**
     * Translates the parameter into MySQL code. This method is only available
     * in MySQL parameters. Out of the {@link TranslationParameter} interface.
     * 
     * @param codeFragments The code buffer where the code for the translator
     *        parameter will be placed.
     */

    public abstract void translateIntoMySQLCode(MySQLCodeFragmentPool codeFragments);

    /**
     * Checks if the MySQL parameter is applicable to the given entity. Out of
     * the {@link TranslationParameter} interface. This method is tied to the
     * {@link CodeTranslatorMySQL} class.
     */

    public abstract boolean isApplicableToEntity(EntityDefinition entity);

    /**
     * Checks if the MySQL parameter is applicable to an array attribute
     * (specified by the container entity and the attribute definition). Out of
     * the {@link TranslationParameter} interface. This method is tied to the
     * {@link CodeTranslatorMySQL} class.
     */

    public abstract boolean isApplicableToArrayAttribute(EntityDefinition entity, AttributeDefinition attribute);

    /**
     * Parameter types supported by the MySQL translators.
     * 
     * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
     * @date 5 Apr 2011
     */

    public static enum MySQLParameterType implements TranslationParameterType
    {
	PARTITIONING,
	INDEXING,
	NOSTATS;
    }

    /**
     * Specifies a partitioning strategy for an entity or array attribute table.
     * It will be based on a hash over the given attribute. To be used during
     * table creation.
     */

    public static class PartitioningParameter extends TranslationParameterMySQL
    {
	private int numberOfPartitions;

	public PartitioningParameter(String entity, String attribute, int numPartitions)
	{
	    super(entity, attribute);
	    this.numberOfPartitions = numPartitions;
	}

	@Override
	public TranslationParameterType getType()
	{
	    return MySQLParameterType.PARTITIONING;
	}

	/**
	 * Gets the values of the parameter.
	 * 
	 * @return A list with an integer object representing the number of
	 *         partitions.
	 */

	@Override
	public List<Object> getParameterValues()
	{
	    List<Object> values = new ArrayList<Object>();
	    values.add(numberOfPartitions);
	    return values;
	}

	@Override
	public void translateIntoMySQLCode(MySQLCodeFragmentPool codeFragments)
	{
	    // Add the partitioning code.
	    String code = "PARTITION by KEY(" + this.referencedAttribute + ") PARTITIONS " + this.numberOfPartitions;
	    codeFragments.appendCodeToFragment(code, CodeFragmentType.PARTITION_OPTIONS);

	    // Add the partitioning attribute as primary key.
	    codeFragments.appendCodeToFragment("," + this.referencedAttribute, CodeFragmentType.PRIMARY_KEY_COLUMNS);
	}

	@Override
	public boolean isApplicableToEntity(EntityDefinition entity)
	{
	    // Adding partitions to an entity table if the parameter references the entity, 
	    // the entity contains the referenced attribute and it is NOT an array.
	    if(entity == null)
		return false;

	    AttributeDefinition attribute = entity.getAttributeDefinition(referencedAttribute);
	    return referencesEntity(entity.getName()) && attribute != null && !attribute.isArray();
	}

	@Override
	public boolean isApplicableToArrayAttribute(EntityDefinition entity, AttributeDefinition attribute)
	{
	    // Adding partitions to an array attribute table if the parameter references the entity
	    // and the attribute and the attribute is an array.
	    return entity != null && attribute != null && referencesEntity(entity.getName())
		   && referencesAttribute(attribute.getName()) && attribute.isArray();
	}
    }

    /**
     * Specifies an index for an attribute. To be used after having inserted all
     * the rows in the table.
     */

    public static class IndexingParameter extends TranslationParameterMySQL
    {
	public IndexingParameter(String entity, String attribute)
	{
	    super(entity, attribute);
	}

	@Override
	public TranslationParameterType getType()
	{
	    return MySQLParameterType.INDEXING;
	}

	@Override
	public List<Object> getParameterValues()
	{
	    return new ArrayList<Object>();
	}

	@Override
	public void translateIntoMySQLCode(MySQLCodeFragmentPool codeFragments)
	{
	    String indexName = "IDX_" + this.referencedEntity + "_" + this.referencedAttribute;
	    String indexCode = "INDEX " + indexName + "(" + this.referencedAttribute + "),";

	    codeFragments.appendCodeToFragment(indexCode, CodeFragmentType.INDICES);
	}

	@Override
	public boolean isApplicableToEntity(EntityDefinition entity)
	{
	    // Index for an attribute of an entity table: Entity is referenced 
	    // by the parameter and the referenced attribute is not an array. 
	    if(entity == null)
		return false;

	    AttributeDefinition attribute = entity.getAttributeDefinition(referencedAttribute);
	    return referencesEntity(entity.getName()) && !attribute.isArray();
	}

	@Override
	public boolean isApplicableToArrayAttribute(EntityDefinition entity, AttributeDefinition attribute)
	{
	    // Index for an array attribute: Entity is referenced and attribute is 
	    // an array and is referenced by the parameter.	    
	    return entity != null && attribute != null && referencesEntity(entity.getName())
		   && referencesAttribute(attribute.getName()) && attribute.isArray();
	}
    }

    /**
     * Specifies that no statistics will be generated for the given entity.
     */

    public static class NoStatsParameter extends TranslationParameterMySQL
    {
	public NoStatsParameter(String entity)
	{
	    super(entity, null);
	}

	@Override
	public TranslationParameterType getType()
	{
	    return MySQLParameterType.NOSTATS;
	}

	@Override
	public List<Object> getParameterValues()
	{
	    return new ArrayList<Object>();
	}

	@Override
	public void translateIntoMySQLCode(MySQLCodeFragmentPool codeFragments)
	{
	    // Nothing to do.
	}

	@Override
	public boolean isApplicableToEntity(EntityDefinition entity)
	{
	    // Only applicable to complete entities.
	    return entity != null && referencesEntity(entity.getName());
	}

	@Override
	public boolean isApplicableToArrayAttribute(EntityDefinition entity, AttributeDefinition attribute)
	{
	    return false;
	}
    }
}
