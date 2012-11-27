package test.java.net.explorercat;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.java.net.explorercat.cql.selection.TestSelection;
import test.java.net.explorercat.dataaccess.mysql.TestUserDAO;
import test.java.net.explorercat.util.misc.TestDateUtils;
import test.java.net.explorercat.util.misc.TestFileDeleter;
import test.java.net.explorercat.util.misc.TestHexCodeObfuscator;
import test.java.net.explorercat.util.misc.TestXMLTagValuesExtractor;
import test.java.net.explorercat.util.misc.TestZipCompressor;

/**
 * A demonstration of a test-suite.
 * 
 * @version $Id: TestSuiteA.java 552 2010-03-06 11:48:47Z paranoid12 $
 */
@RunWith(value = Suite.class)
@SuiteClasses(value = { TestSelection.class, TestUserDAO.class, TestDateUtils.class, TestHexCodeObfuscator.class,
	TestFileDeleter.class, TestXMLTagValuesExtractor.class, TestZipCompressor.class })
public class AllTests
{
}
