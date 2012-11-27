package net.explorercat.cql.selection.scopes;

import net.explorercat.cql.selection.SelectionException;
import net.explorercat.dataaccess.CatalogDAO;

/**
 * Factory in charge of providing selection scopes. These factory give access to
 * the scope for precalculated selections and to the default scope (implemented
 * by a catalog DAO).
 * 
 * TODO Clarify the use and roles of scopes. This class must be a singleton!!
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Aug 2010
 */

public class SelectionScopeFactory
{
    // Scope for pre-calculated selections.
    private PrecalculatedSelectionScope precalculatedSelectionsScope;

    // Scope for DAOs selections (default).
    private SelectionScope DAOselectionsScope;

    /**
     * Creates a selection scoped based on the given catalog DAO.
     * 
     * @param catalogDAO The DAO that will act as default scope for DAOs,
     *        meaning it will resolve all the selections that couldn't be
     *        resolved using the previous scopes.
     */

    public SelectionScopeFactory(CatalogDAO catalogDAO) throws SelectionException
    {
	// Get the global scope for pre-calculated selections.
	precalculatedSelectionsScope = new PrecalculatedSelectionScope();

	// Get the global scope for DAOs.	   
	DAOselectionsScope = catalogDAO;
    }

    /**
     * Gets the global scope for pre-calculated selections.
     * 
     * @return An instance of the scope for pre-calculated selections.
     */

    public PrecalculatedSelectionScope getPrecalculatedSelectionsScope()
    {
	return precalculatedSelectionsScope;
    }

    /**
     * Gets the default selection scope (based on a DAO).
     * 
     * @return An instance of the default scope for selections.
     */

    public SelectionScope getDAOselectionsScope()
    {
	return DAOselectionsScope;
    }
}
