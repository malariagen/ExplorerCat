package test.java.net.explorercat.util.misc;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.CoreMatchers.*;


import net.explorercat.util.misc.FileDeleter;


public class TestFileDeleter
{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private File properties;
    private File otherFile;
    
    @Before
    public void createTestDataForDeleteSingleFile() throws IOException {
        properties = folder.newFile("file.test");
        BufferedWriter out = new BufferedWriter(new FileWriter(properties));
        out.write("Arthur Dent\n");
        out.close();
    }
    
    @Before
    public void createTestDataForDeleteAllFile() throws IOException {
        otherFile = folder.newFile("fileN2.test");
        BufferedWriter out = new BufferedWriter(new FileWriter(otherFile));
        out.write("Zaphod Beeblebrox\n");
        out.close();
    }
    
    @Test
    public void testFileDeleterNotValid() {
	boolean isDeleted = FileDeleter.deleteFile("local.notexist");
	assertFalse(isDeleted);
    }

    @Test (expected=IllegalArgumentException.class) 
    public void testFileDeleterException() {
	assertThat(properties.exists(), is(true));
	String directoryname = properties.getParent();
	FileDeleter.deleteFile(directoryname);        
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void deletefileRaiseExceptionWithMessage() throws IllegalArgumentException {
	String directoryname = properties.getParent();
	thrown.expect(IllegalArgumentException.class);
    	thrown.expectMessage("Error, " + directoryname + " is a directory");
	assertThat(properties.exists(), is(true));	
	FileDeleter.deleteFile(directoryname);  
    }

    @Test
    public void testFileDeleterValid() {
	assertThat(properties.exists(), is(true));
	String namefile = properties.getAbsolutePath();
	boolean isDeleted = FileDeleter.deleteFile(namefile);
	assertTrue(isDeleted);
	
    }
    
    @Test
    public void testDeleteAllFilesContainedInFolder() {
	String directoryname = properties.getParent();
	System.out.println(folder.getRoot());
	boolean isDeleted = FileDeleter.deleteAllFilesContainedInFolder(directoryname);
	assertTrue(isDeleted);
    }
    
    @Test
    public void deleteAllFileRaiseExceptionWithMessage() throws IllegalArgumentException {
	String folderName = "/directorynotexist/tester";
	thrown.expect(IllegalArgumentException.class);
    	thrown.expectMessage("The provided folder path does is unreachable or unreadable:" + folderName);
	FileDeleter.deleteAllFilesContainedInFolder(folderName);  
    }

    
}

