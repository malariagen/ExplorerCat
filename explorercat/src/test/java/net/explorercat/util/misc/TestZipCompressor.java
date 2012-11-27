package test.java.net.explorercat.util.misc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import net.explorercat.util.misc.ZipCompressor;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.io.File;


import org.junit.rules.TemporaryFolder;

public class TestZipCompressor
{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    private File filetocompress;
    
    @Before
    public void createFileToBeCompressed() throws IOException {
	filetocompress = folder.newFile("filetocompress.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(filetocompress));
        out.write("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\n");
        out.write("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\n");
        out.close();
    }
    
    @Test
    public void testCompressAsZipFile()
    {

	String namefile = filetocompress.getAbsolutePath();
	String possibleZipFile = filetocompress.getParent()+"/filetocompress.txt.zip";
	assertThat(filetocompress.exists(), is(true));
	assertThat(new File(possibleZipFile).exists(), is(false));
	ZipCompressor.compressAsZipFile(namefile, namefile);
	assertThat(new File(possibleZipFile).exists(), is(true));
    }
}
