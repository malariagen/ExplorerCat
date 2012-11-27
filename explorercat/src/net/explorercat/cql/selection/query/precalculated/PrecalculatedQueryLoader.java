package net.explorercat.cql.selection.query.precalculated;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.explorercat.application.ApplicationController;
import net.explorercat.cql.interpreters.CQLInterpreter;
import net.explorercat.cql.interpreters.CQLScriptContext;
import net.explorercat.cql.interpreters.CQLScriptInterpreterException;
import net.explorercat.cql.selection.Selection;
import net.explorercat.cql.selection.SelectionException;
import net.explorercat.cql.selection.SelectionProxy;
import net.explorercat.cql.selection.resolvers.SelectionResolver;
import net.explorercat.cql.selection.resolvers.SelectionResolutionException;
import net.explorercat.cql.selection.scopes.PrecalculatedSelectionScope;
import net.explorercat.cql.selection.scopes.SelectionScopeFactory;
import net.explorercat.cql.selection.scopes.UnresolvedSelectionScope;
import net.explorercat.data.Catalog;
import net.explorercat.dataaccess.CatalogDAO;
import net.explorercat.dataaccess.DAOException;
import net.explorercat.util.exceptions.ExplorerCatCheckedException;
import net.explorercat.util.misc.XMLTagValuesExtractor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Loads and computes precalculated queries, storing the resulting selections
 * into memory. The user specify the queries by means of an XML file. In this
 * file universal queries (computed for all the catalogs) and catalog specific
 * queries (computed for a limited set of catalogs) are described.
 * 
 * Queries associated with plug-ins can be loaded as well using the methods of
 * this class.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 27 Sep 2010
 */

public class PrecalculatedQueryLoader
{
    // Logging
    private static final Log log = LogFactory.getLog(PrecalculatedQueryLoader.class);

    /**
     * Loads and resolves CQL queries from an XML file. This file specifies
     * universal queries (for all the catalogs) and also queries that are
     * specific to a given catalog.
     * 
     * @param filePath The path to the XML file that contains the queries to
     *        load.
     */

    public void loadPrecalculatedQueriesFromXMLFile(String filePath) throws PrecalculatedQueryLoadingException
    {
	try
	{
	    // Get the DOM representation of the XML file.
	    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	    Document manifestDOM = documentBuilder.parse(filePath);
	    Element root = manifestDOM.getDocumentElement();

	    // Extract all the tag values.
	    String[] tags = { "catalogs", "code" };

	    if(root != null)
	    {
		Map<String, List<String>> tagValues = null;
		tagValues = XMLTagValuesExtractor.getValuesForTags(root, "cql-precalculated-query", tags);

		// Building process
		int numValues = tagValues.get(tags[0]).size();

		for(int i = 0; i < numValues; ++i)
		{
		    String catalogIds = tagValues.get(tags[0]).get(i);
		    String code = tagValues.get(tags[1]).get(i);

		    if(log.isDebugEnabled())
			log.debug("Processing: " + code);

		    processQuery(code, catalogIds);
		}
	    }
	}
	// Catch everything and keep going.
	catch(Exception e)
	{
	    e.printStackTrace();
	    throw new PrecalculatedQueryLoadingException("Error loading pre-calculated queries from XML file", e);
	}
    }

    /**
     * Auxiliary method that processes a query, resolving it for the specified
     * list of catalogs.
     */

    private void processQuery(String cqlCode, String catalogIds) throws PrecalculatedQueryLoadingException,
	DAOException
    {
	// Get all the catalogs.	
	CatalogDAO catalogDAO = ApplicationController.getInstance().getDAOFactory().getCatalogDAO();
	List<Catalog> catalogs = catalogDAO.findAllCatalogs();

	// Split the catalog identifiers.
	StringTokenizer tokenizer = new StringTokenizer(catalogIds, ",");
	int numTokens = tokenizer.countTokens();

	while(tokenizer.hasMoreTokens())
	{
	    String catalogId = tokenizer.nextToken();

	    try
	    {
		// Compute the query for all the catalogs.
		if(catalogId.toLowerCase().equals("all"))
		{
		    if(numTokens > 1)
			throw new PrecalculatedQueryLoadingException("Mixing ALL with ids: " + catalogIds);

		    for(Catalog catalog : catalogs)
			loadPrecalculatedQueriesForCatalog(cqlCode, catalog);
		}
		// Compute the query for an specific catalog.
		else
		{
		    Catalog specificCatalog = catalogDAO.findCatalog(Integer.valueOf(catalogId));
		    loadPrecalculatedQueriesForCatalog(cqlCode, specificCatalog);
		}
	    }
	    catch(NumberFormatException e)
	    {
		throw new PrecalculatedQueryLoadingException("Catalog id is not a number: " + catalogId);
	    }
	    catch(PrecalculatedQueryLoadingException e)
	    {
		throw e;
	    }
	    catch(ExplorerCatCheckedException e)
	    {
		throw new PrecalculatedQueryLoadingException("Problem resolving the selections", e);
	    }
	}
    }

    /**
     * Loads and resolves the queries passed as a parameter for the given
     * catalog.
     * 
     * @param cqlQueries A string that contains a set of CQL queries that will
     *        be loaded as precalculated queries.
     * 
     * @param catalog The catalog to be used when resolving the queries.
     * @throws PrecalculatedQueryLoadingException
     */

    public void loadPrecalculatedQueriesForCatalog(String cqlQueries, Catalog catalog) throws SelectionException,
	SelectionResolutionException, PrecalculatedQueryLoadingException
    {
	try
	{
	    if(log.isDebugEnabled())
		log.debug("Loading precalculated queries: " + cqlQueries);

	    // Interpret the file and get all the unresolved selection queries.
	    CQLInterpreter interpreter = new CQLInterpreter();
	    CQLScriptContext interpretedScript = interpreter.interpretCQLQuery(cqlQueries);

	    // Create a scope of unresolved selections that represents the parsed CQL script.
	    UnresolvedSelectionScope scriptScope = new UnresolvedSelectionScope("PrecalculatedQueries["
										+ catalog.getId() + "]",
										catalog.getId(), interpretedScript);

	    // Create a new selection resolver for the CQL script scope. The resolver will check 
	    // a chain of scopes to resolve the selection, the scope on the top will be the 
	    // provided one. This chain includes caches and DAOs.
	    SelectionResolver resolver = new SelectionResolver(catalog.getId(), scriptScope);

	    // Resolve all the selections and register in the scope the ones that 
	    // have been defined in the script.
	    List<SelectionProxy> definedSelections = interpretedScript.getScriptDefinedSelections();	    
	    
	    ApplicationController globalController = ApplicationController.getInstance();
	    SelectionScopeFactory factory = globalController.getSelectionScopeFactory();
	    PrecalculatedSelectionScope precalculatedScope = factory.getPrecalculatedSelectionsScope();

	    for(SelectionProxy selectionToResolve : definedSelections)
	    {
		Selection resolvedSelection = resolver.resolveSelection(selectionToResolve.getSelectionLabel());
		precalculatedScope.registerSelection(resolvedSelection);
	    }
	}
	catch(CQLScriptInterpreterException e)
	{
	    log.error("Precalculated queries couldn't be loaded for catalog: " + catalog.getId() + ", "
		      + e.getMessage());
	    throw new PrecalculatedQueryLoadingException("Error interpreting precalcualted query", e);
	}
    }
}
