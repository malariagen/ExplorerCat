package test.java.net.explorercat.dataaccess.mysql;

import net.explorercat.application.ApplicationController;
import net.explorercat.data.User;
import net.explorercat.dataaccess.UserDAO;
import org.junit.Test;



public class TestUserDAO extends AbstractDbUnitTestCase
{
    private UserDAO userDAO;


    @Test
    // Check that the data has been loaded.
    public void testCheckLoginDataLoaded() throws Exception
    {
	assertNotNull(this.getLoadedDataSet());
	int rowCount = getLoadedDataSet().getTable("Users").getRowCount();
	assertEquals(2, rowCount);
    }

    @Test
    public void testFindUserById() throws Exception
    {
		
	userDAO = ApplicationController.getInstance().getDAOFactory().getUserDAO();
	User user = userDAO.findUser(1);
	assertEquals(user.getName(),"jack");

    }

    @Test
    public void testFindUserByName() throws Exception
    {
	userDAO = ApplicationController.getInstance().getDAOFactory().getUserDAO();
	User user = userDAO.findUser("jack");
	assertEquals(user.getEmail(),"jj10@test.sanger.ac.uk");
    }

}
