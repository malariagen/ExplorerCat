package test.java.net.explorercat.dataaccess.mysql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
//We extend this class but we can't user @afterclass of @after directly. 
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.AbstractDatabaseConnection;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;

import test.java.net.explorercat.application.junit.JunitInitializer;



public abstract class AbstractDbUnitTestCase extends DatabaseTestCase
{

    private FlatXmlDataSet loadedDataSet;
    private IDatabaseConnection iDataConnection;
    private Connection jdbcConnection;
    
 
    // Provide a connection to the database
    protected IDatabaseConnection getConnection() throws Exception
    {
	new JunitInitializer();
	
	Class.forName("com.mysql.jdbc.Driver");
	jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/explorercat-junit", "root", null);	
	iDataConnection = new DatabaseConnection(jdbcConnection);
	DatabaseConfig config = iDataConnection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        
        return iDataConnection;
    }

    // Load the data which will be inserted for the test
    protected IDataSet getDataSet() throws Exception
    {
	FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
	builder.setColumnSensing(true);
	loadedDataSet = builder.build(new File("src/test/resources/full.xml"));
	
	return loadedDataSet;
    }
 
    
    public FlatXmlDataSet getLoadedDataSet()
    {
       return loadedDataSet;
    }

}
                                     