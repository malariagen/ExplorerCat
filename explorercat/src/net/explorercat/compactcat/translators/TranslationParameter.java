package net.explorercat.compactcat.translators;

import java.util.List;

/**
 * Interface that represents a parameter that will modify the translation
 * process.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 5 Apr 2011
 */

public interface TranslationParameter
{
    /**
     * Gets the name of the entity referenced by the parameter or null if the
     * parameter does not refer to any entity.
     */

    public String getReferencedEntity();

    /**
     * Gets the name of the attribute referenced by the parameter or null if the
     * parameter does not refer to any attribute.
     */

    public String getReferencedAttribute();

    /**
     * Checks if the parameter references the given entity.
     */

    public boolean referencesEntity(String entity);

    /**
     * Checks if the parameter references the given attribute.
     */

    public boolean referencesAttribute(String attribute);

    /**
     * Gets a list of objects representing the parameter values.
     * 
     * @return A list of objects that contains the parameter values. This list
     *         has to be interpreted by the client code according to the
     *         parameter semantics.
     */

    public abstract List<Object> getParameterValues();

    /**
     * Gets the type of the parameter.
     */

    public abstract TranslationParameterType getType();

    /**
     * Types of parameters supported (initially empty, to be extended by
     * concrete implementations).
     */

    public interface TranslationParameterType
    {
	// Initially empty.
    }
}
