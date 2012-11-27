package net.explorercat.util.misc;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class that deletes files from disk.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 18 Oct 2010
 */

public class FileDeleter
{
    private static final Log log = LogFactory.getLog(FileDeleter.class);

    /**
     * Deletes the given file from the disk
     * 
     * @param filename The path to the file that will be deleted.
     * @return True if the file was deleted, false otherwise.
     */

    public static boolean deleteFile(String filename)
    {
	File fileToBeDeleted = new File(filename);

	if(!fileToBeDeleted.exists())
	    return false;

	if(!fileToBeDeleted.canWrite())
	    throw new IllegalArgumentException("Error, impossible to delete " + filename + ", write protected.");

	if(fileToBeDeleted.isDirectory())
	    throw new IllegalArgumentException("Error, " + filename + " is a directory");

	return fileToBeDeleted.delete();
    }

    /**
     * Deletes all the files within the given folder.
     * 
     * @param folderPath The path to the containing folder.
     * @return True if all the files were deleted false otherwise.
     */

    public static boolean deleteAllFilesContainedInFolder(String folderPath)
    {
	if(log.isDebugEnabled())
	    log.debug("Deleting all the files contained in " + folderPath);

	File folder = new File(folderPath);
	File[] filesToDelete = folder.listFiles();

	if(filesToDelete == null)
	    throw new IllegalArgumentException("The provided folder path does is unreachable or unreadable:" + folder);

	boolean allTheFilesDeleted = true;

	for(File currentFile : filesToDelete)
	    allTheFilesDeleted = currentFile.delete() && allTheFilesDeleted;

	if(allTheFilesDeleted)
	{
	    if(log.isDebugEnabled())
		log.debug("All the files were removed.");
	}
	else
	    log.error("Some files have not been removed");

	return allTheFilesDeleted;
    }
}
