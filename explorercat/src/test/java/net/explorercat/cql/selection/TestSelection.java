package test.java.net.explorercat.cql.selection;

import static org.junit.Assert.*;


import java.util.Collection;
import java.util.List;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import test.java.net.explorercat.application.junit.JunitInitializer;
import test.java.net.explorercat.dataaccess.mysql.AbstractDbUnitTestCase;

import java.util.Arrays;
import net.explorercat.application.ApplicationController;
import net.explorercat.cql.selection.*;
import net.explorercat.data.Catalog;
import net.explorercat.data.PropertyDictionary;
import net.explorercat.data.QueryableDataEntity;
import net.explorercat.dataaccess.*;
import net.explorercat.dataaccess.mysql.QueryableDataEntityDAOMySQL;

@RunWith(value = Parameterized.class)
public class TestSelection extends AbstractDbUnitTestCase
{
    private Selection selection;
    private static QueryableDataEntityDAOMySQL entityDAO;
    private static String selectionLabel;
    private static Catalog catalog;
    private static String entityType;
    private static List<QueryableDataEntity> entities;
    private static PropertyDictionary augmentedDictionary;

    public static void init() throws DAOException, SelectionException
    {
	selectionLabel = "sys_snp_selection";
	entityType = "snp";
	CatalogDAO catalogDAO = ApplicationController.getInstance().getDAOFactory().getCatalogDAO();
	catalog = catalogDAO.findCatalog(1);
	entityDAO = (QueryableDataEntityDAOMySQL) catalog.getEntityDAO(entityType);
	augmentedDictionary = entityDAO.getPropertyDictionary();
	entities = entityDAO.getEntities(100, 0);
    }

    public TestSelection(Selection selection)
    {
	this.selection = selection;
    }

    @Test
    public void testGetSelectionLabel()
    {
	assertEquals(selection.getSelectionLabel(), selectionLabel);
    }

    @Test
    public void testGetEntityType()
    {
	assertEquals(selection.getEntityType(), entityType);
    }

    @Test
    public void testGetEntityPropertyNames()
    {
	assertEquals(selection.getEntityPropertyNames(), augmentedDictionary.getPropertyNames());
    }

    @Test
    public void testGetEntityCatalog()
    {
	assertEquals(selection.getEntityCatalog(), catalog);
    }

    @Test
    public void testGetEntityPropertyTypes()
    {
	assertEquals(selection.getEntityPropertyTypes(), augmentedDictionary.getPropertyTypes());
    }

    @Test
    public void testGetSelectionCopyWithSharedEntities()
    {
	Selection copyResultSelection = selection.getSelectionCopyWithSharedEntities("copysnp");

	assertEquals(selection.getEntityCatalog(), copyResultSelection.getEntityCatalog());
	assertEquals(selection.getEntityType(), copyResultSelection.getEntityType());
	assertEquals(selection.getSelectionLabel(), selectionLabel);
	assertEquals(copyResultSelection.getSelectionLabel(), "copysnp");
	assertFalse(selection.getSelectionLabel().equals(copyResultSelection.getSelectionLabel()));
    }

    @Test
    public void testGetEntityPropertyDescriptions()
    {
	assertEquals(selection.getEntityPropertyDescriptions(), augmentedDictionary.getPropertyDescriptions());
    }

    @Test
    public void testHasEntityProperty()
    {
	assertTrue(selection.hasEntityProperty(augmentedDictionary.getPropertyNames().get(0)));
    }

    @Test
    public void testgetEntityPropertyType()
    {
	assertEquals(selection.getEntityPropertyType(augmentedDictionary.getPropertyNames().get(0)),augmentedDictionary.getPropertyType(augmentedDictionary.getPropertyNames().get(0)));
    }
    
    @Parameters
    public static Collection<Object[]> instanceToTest() throws Exception
    {
	new JunitInitializer();
	init();

	return Arrays.asList(new Object[][] { { new SelectionResult(selectionLabel, entityType, catalog, entities,
								    augmentedDictionary, null, false) } });
    }

}
