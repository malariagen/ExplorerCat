package test.java.net.explorercat.util.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.*;
import org.w3c.dom.*;


import net.explorercat.util.misc.XMLTagValuesExtractor;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class TestXMLTagValuesExtractor
{
    private Document document;
    private Map<String, List<String>> correctTagValueMap;
    
    
    @Before
    public void createTagValueMap() {
	correctTagValueMap = new HashMap<String, List<String>>();
	correctTagValueMap.put("tag1", new ArrayList<String>());
	correctTagValueMap.get("tag1").add("value1");
	correctTagValueMap.get("tag1").add("value2");
	correctTagValueMap.get("tag1").add("value3");
	correctTagValueMap.put("tag2", new ArrayList<String>());
	correctTagValueMap.get("tag2").add("value11");
	correctTagValueMap.get("tag2").add("value12");
	correctTagValueMap.get("tag2").add("value13");
    }
    
    @Before
    public void createXMLDocument() throws Exception
    {

	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	this.document = documentBuilder.newDocument();
	Element rootElement = document.createElement("root");
	document.appendChild(rootElement);
	for(int i = 1; i <= 3; i++)
	{
	    Element em = document.createElement("container");
	    rootElement.appendChild(em);
	    Element tag = document.createElement("tag1");
	    tag.appendChild(document.createTextNode("value"+Integer.toString(i)));
	    em.appendChild(tag);
	    Element tag2 = document.createElement("tag2");
	    tag2.appendChild(document.createTextNode("value"+ Integer.toString(i+10)));
	    em.appendChild(tag2);
	}	
    }
  
    @Test
    public void testGetValuesForTags()
    {
	Element root = document.getDocumentElement();
	String conteiner = "container";
	String[] tagsToExtract  = {"tag1","tag2"};
	
	Map<String, List<String>> tagValuesMap = XMLTagValuesExtractor.getValuesForTags(root,conteiner, tagsToExtract);
	assertEquals(tagValuesMap,this.correctTagValueMap);

    }
   
    @Test
    public void testGetTagValuesAsNormalizedStrings()
    {
	List<String> correctValueExtracted = new ArrayList<String>();
	correctValueExtracted.add("value1");
	NodeList nodes = document.getElementsByTagName("container");
	Element containerTagElement = (Element) nodes.item(0);
	List<String> valueExtracted =XMLTagValuesExtractor.getTagValuesAsNormalizedStrings(containerTagElement,"tag1");
	assertEquals(valueExtracted,correctValueExtracted);
	
    }
    
}
