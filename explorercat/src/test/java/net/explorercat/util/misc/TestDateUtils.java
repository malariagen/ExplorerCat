package test.java.net.explorercat.util.misc;

import net.explorercat.util.misc.DateUtils;
import net.explorercat.cql.types.DateValue;
import net.explorercat.cql.types.IncompatibleTypeException;


import java.util.Collection;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
  

@RunWith(value=Parameterized.class)
public class TestDateUtils
{
    private String dateToConvert;
    private String dateExpected;

    @Parameters 
    public static Collection<String[]> getTestParameters() {
	return Arrays.asList(new String[][] { { "2004/13/24", "1900-1-1" }, { "2004/11/23", "2004-11-23" }, { "2011/05/05", "2011-5-5" }, { "2004/03/32", "1900-1-1" } });
    }
  
    public TestDateUtils(String dateToConvert, String dateExpected) {
       this.dateToConvert = dateToConvert;
       this.dateExpected = dateExpected;
    }
  
    
  @Test
  public void testDate() throws IncompatibleTypeException {
     DateValue dateGregorian = DateUtils.parseStringDate(this.dateToConvert);
     String dateGregorianString = dateGregorian.getValueAsString();
     assertTrue(dateGregorianString.equals(this.dateExpected));
     
  }
}
