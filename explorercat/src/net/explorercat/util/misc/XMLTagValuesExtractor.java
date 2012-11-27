package net.explorercat.util.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Utility class to extract values from XML tags. To be removed as soon as we
 * update the catalog deployer (we'll replace the ANTLR grammar by a JSON
 * parser).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 1 Dec 2010
 */

public class XMLTagValuesExtractor
{

    /**
     * Extracts the values of all the contained tags passed as a parameter. The
     * values are extracted for ALL the container tags that are descendants of
     * the root element. They are returned in a map that has an entry per tag to
     * be extracted, having each entry a value per container tag found. We
     * assume that no tags are repeated within the container tag.
     * 
     * Example:
     * 
     * <code>
     * 
     * <root>
     * 	<container> <tag1> valueA </tag> <tag2> valueB </tag2></container>
     * 	<container> <tag1> valueC </tag> <tag2> valueD </tag2></container>
     * </root>
     * 
     * </code>
     * 
     * Calling the method with parameters (root, container, [tag1, tag2]) will
     * return a map with the following structure:
     * 
     * <code>
     * 
     * 	[tag1][valueA, valueC]
     * 	[tag2][valueB, valueD]
     * 
     * </code>
     * 
     * @param root The root element from which we look for the tags.
     * @param containerTag Name of the tag that contains all the tags we are
     *        interested in extracting.
     * @param tagsToExtract List of tags whouse value will be extracted as
     *        normalized strings.
     */

    public static Map<String, List<String>> getValuesForTags(Element root, String containerTag, String[] tagsToExtract)
    {
	Map<String, List<String>> tagValuesMap = new HashMap<String, List<String>>();

	for(String tag : tagsToExtract)
	    tagValuesMap.put(tag, new ArrayList<String>());

	NodeList nodes = root.getElementsByTagName(containerTag);

	if(nodes != null)
	{
	    for(int i = 0; i < nodes.getLength(); ++i)
	    {
		Element containerTagElement = (Element) nodes.item(i);

		for(String tag : tagsToExtract)
		{
		    List<String> values = getTagValuesAsNormalizedStrings(containerTagElement, tag);
		    tagValuesMap.get(tag).add(values.get(0));
		}
	    }
	}

	return tagValuesMap;
    }

    /**
     * Gets the values (as strings) for the tags of the given name that are
     * descendants of the given root element. Note that the strings are
     * normalised: trimmed and any intermediate concatenation of blank spaces is
     * reduced to one.
     * 
     * @return The list of values (as Strings) for the given tag name.
     */

    public static List<String> getTagValuesAsNormalizedStrings(Element root, String tagName)
    {
	List<String> values = new ArrayList<String>();
	NodeList nodes = root.getElementsByTagName(tagName);

	if(nodes != null)
	{
	    for(int i = 0; i < nodes.getLength(); ++i)
	    {
		Element element = (Element) nodes.item(i);
		values.add(element.getFirstChild().getNodeValue().replaceAll("\\s+|\t", " ").trim());
	    }
	}

	return values;
    }
}
