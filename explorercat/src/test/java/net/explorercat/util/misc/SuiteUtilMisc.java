package test.java.net.explorercat.util.misc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * A demonstration of a test-suite.
 * 
 * @version $Id: TestSuiteA.java 552 2010-03-06 11:48:47Z paranoid12 $
 */
@RunWith(value = Suite.class)
@SuiteClasses(value = { TestDateUtils.class, TestHexCodeObfuscator.class, TestFileDeleter.class, TestXMLTagValuesExtractor.class, TestZipCompressor.class })
public class SuiteUtilMisc {}
