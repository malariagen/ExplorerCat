package net.explorercat.util.misc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utility class that compress files into a ZIP.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Oct 2010
 */

public class ZipCompressor
{
    private static final int COMPRESSION_LEVEL = 4;

    /**
     * Compress a file as a ZIP.
     * 
     * @param filename The path to the file that will be compressed.
     * @param zipEntryName The name of the file inside the ZIP. It will become
     *        the name of the file after the unzip operation.
     */

    public static void compressAsZipFile(String filename, String zipEntryName)
    {
	byte[] inputBuffer = new byte[1024 * 1024 * 20];

	try
	{
	    // Open the file to be compressed.
	    BufferedInputStream fileToCompress = new BufferedInputStream(new FileInputStream(new File(filename)));

	    // Create the ZIP file.	    
	    String zipFilename = filename + ".zip";
	    ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(zipFilename));

	    //Compression level.
	    zipFile.setLevel(COMPRESSION_LEVEL);

	    // Add the ZIP entry for the file. 	    
	    zipFile.putNextEntry(new ZipEntry(zipEntryName));

	    // Put everything into the ZIP file.
	    int bytesRead;
	    while((bytesRead = fileToCompress.read(inputBuffer)) > 0)
		zipFile.write(inputBuffer, 0, bytesRead);

	    zipFile.closeEntry();
	    zipFile.close();
	    fileToCompress.close();

	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
    }
}
