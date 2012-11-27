package net.explorercat.compactcat.translators.mysql;

import java.util.List;

import net.explorercat.compactcat.definitions.AttributeDefinition;
import net.explorercat.compactcat.definitions.EntityDefinition;
import net.explorercat.compactcat.translators.TranslationParameter;
import net.explorercat.compactcat.translators.mysql.MySQLCodeFragmentPool.CodeFragmentType;
import net.explorercat.cql.types.DataType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper class for translating entities and its attributes into a MySQL
 * representation.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 14 Feb 2011
 */

public class CodeTranslatorMySQL
{
    private static Log log = LogFactory.getLog(CodeTranslatorMySQL.class);

    /**
     * Translates an entity into a MySQL table.
     * 
     * @param tableName Name that will be used for the table.
     * @param entity Definition of the entity being translated.
     * @param parameters MySQL translation parameters that will guide the
     *        translation.
     * @return The MySQL code that will create the MySQL table representing the
     *         entity.
     */

    public String translateEntityIntoMySQLTable(String tableName, EntityDefinition entity,
						List<TranslationParameter> parameters)
    {
	MySQLCodeFragmentPool codeFragments = new MySQLCodeFragmentPool();

	translateEntityIntoMySQLCode(codeFragments, entity);
	addDefaultPrimaryKeyColumns(codeFragments, entity);
	addDefaultTableOptions(codeFragments, entity);
	addParametersCode(parameters, entity, null, codeFragments);

	codeFragments.deleteTrailingCommas();
	return assemblySQLQuery(tableName, codeFragments, false);
    }

    /**
     * Translates an entity attribute (must be an array) into a MySQL table.
     * 
     * @param tableName Name that will be used for the table.
     * @param entity Definition of the entity being translated.
     * @param attribute Definition of the attribute to be translated (must be an
     *        array).
     * @param parameters MySQL translation parameters that will guide the
     *        translation.
     * @return The MySQL code that will create the table representing the array
     *         attribute.
     */

    public String translateArrayAttributeIntoMySQLTable(String tableName, EntityDefinition entity,
							AttributeDefinition attribute,
							List<TranslationParameter> parameters)
    {
	MySQLCodeFragmentPool codeFragments = new MySQLCodeFragmentPool();

	translateArrayAttributeIntoMySQLCode(codeFragments, attribute);
	addDefaultPrimaryKeyColumns(codeFragments, entity);
	addDefaultTableOptions(codeFragments, entity);
	addParametersCode(parameters, entity, attribute, codeFragments);

	codeFragments.deleteTrailingCommas();
	return assemblySQLQuery(tableName, codeFragments, true);
    }

    /**
     * Assemblies the MySQL code based on the value of the given code fragments.
     */

    private String assemblySQLQuery(String tableName, MySQLCodeFragmentPool codeFragments, boolean isArrayAttribute)
    {
	// Assemble the query.
	StringBuilder sql = new StringBuilder();

	sql.append("CREATE TABLE ").append(tableName).append("(");
	sql.append(codeFragments.getCodeFragment(CodeFragmentType.COLUMNS)).append(",");

	if(!isArrayAttribute)
	{
	    sql.append(" CONSTRAINT PRIMARY KEY (");
	    sql.append(codeFragments.getCodeFragment(CodeFragmentType.PRIMARY_KEY_COLUMNS));
	    sql.append("),");
	}

	sql.append(codeFragments.getCodeFragment(CodeFragmentType.INDICES));

	if(sql.charAt(sql.length() - 1) == ',')
	    sql.deleteCharAt(sql.length() - 1);

	sql.append(")");
	sql.append(codeFragments.getCodeFragment(CodeFragmentType.TABLE_OPTIONS)).append(" ");
	sql.append(codeFragments.getCodeFragment(CodeFragmentType.PARTITION_OPTIONS));

	return sql.toString();
    }

    /**
     * Translates an array attribute into MySQL columns (for its array table
     * (id,value)).
     */

    private void translateArrayAttributeIntoMySQLCode(MySQLCodeFragmentPool codeFragments, AttributeDefinition attribute)
    {
	codeFragments.appendCodeToFragment(PropertyLookup.getUniqueIdAttribute() + " int(10) unsigned NOT NULL,",
					   CodeFragmentType.COLUMNS);

	String sqlType = attribute.getType().getEquivalentMySQLType();
	codeFragments.appendCodeToFragment(attribute.getName() + " " + sqlType, CodeFragmentType.COLUMNS);
    }

    /**
     * Translates the attributes of the entity into MySQL columns.
     */

    private void translateEntityIntoMySQLCode(MySQLCodeFragmentPool sqlFragments, EntityDefinition entityDefinition)
    {
	// Get the SQL code for each attribute (skipping arrays).
	List<AttributeDefinition> attributes = entityDefinition.getAttributes();
	sqlFragments.appendCodeToFragment(PropertyLookup.getUniqueIdAttribute()
					  + " int(10) unsigned NOT NULL AUTO_INCREMENT,", CodeFragmentType.COLUMNS);

	for(AttributeDefinition attribute : attributes)
	{
	    DataType attributeType = attribute.getType();

	    if(!attributeType.isArray() && attributeType != DataType.NULL)
	    {
		String name = attribute.getName();
		String sqlType = attribute.getType().getEquivalentMySQLType();
		sqlFragments.appendCodeToFragment(name + " " + sqlType + ",", CodeFragmentType.COLUMNS);
	    }
	}
    }

    /**
     * Adds the default primary key columns.
     */

    private void addDefaultPrimaryKeyColumns(MySQLCodeFragmentPool codeFragments, EntityDefinition entityDefinition)
    {
	codeFragments.appendCodeToFragment(PropertyLookup.getUniqueIdAttribute(), CodeFragmentType.PRIMARY_KEY_COLUMNS);
    }

    /**
     * Adds the default options for creating a table.
     */

    private void addDefaultTableOptions(MySQLCodeFragmentPool codeFragments, EntityDefinition entityDefinition)
    {
	codeFragments.appendCodeToFragment(" ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 "
					   + "COMMENT='Catalogs of entities.'", CodeFragmentType.TABLE_OPTIONS);
    }

    /**
     * Adds the MySQL code translated from the parameters to the given code
     * pool.
     * 
     * @param parameters List of translation parameters that could be
     *        translated.
     * @param entity Definition of the entity being translated.
     * @param attribute Definition of the attribute being translated (for array
     *        attributes, null otherwise).
     * @param codeFragments MySQL code fragments where the parameters' code will
     *        be placed.
     */

    private void addParametersCode(List<TranslationParameter> parameters, EntityDefinition entity,
				   AttributeDefinition attribute, MySQLCodeFragmentPool codeFragments)
    {
	for(TranslationParameter parameter : parameters)
	{
	    // Ugly but reduces complexity.
	    if(parameter instanceof TranslationParameterMySQL)
	    {
		TranslationParameterMySQL mysqlParameter = (TranslationParameterMySQL) (parameter);

		if(mysqlParameter.isApplicableToArrayAttribute(entity, attribute))
		    mysqlParameter.translateIntoMySQLCode(codeFragments);

		else if(mysqlParameter.isApplicableToEntity(entity))
		    mysqlParameter.translateIntoMySQLCode(codeFragments);
	    }
	}
    }
}
