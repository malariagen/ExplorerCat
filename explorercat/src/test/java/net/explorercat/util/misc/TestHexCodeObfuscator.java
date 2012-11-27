package test.java.net.explorercat.util.misc;

import net.explorercat.util.misc.HexCodeObfuscator; 
import static org.junit.Assert.*;
import org.junit.Test;

public class TestHexCodeObfuscator
{

    @Test
    public void testObfuscateKey() {
	String hash = "thiscouldbeapassword";
	String obfuscateString = HexCodeObfuscator.obfuscateKey(hash);
	String deobfuscateString = HexCodeObfuscator.deobfuscateKey(obfuscateString);
	assertEquals(hash, deobfuscateString);
    }
    
    @Test
    public void testDeobfuscateKey() {
	String hash = "t2h2i2s2cbo1u3l2deb7e5aepfa7sasBwBo5r6d5";
	String result = HexCodeObfuscator.deobfuscateKey(hash);
	assertEquals(result,"thiscouldbeapassword");
	assertTrue(result.equals("thiscouldbeapassword"));
    }
}

