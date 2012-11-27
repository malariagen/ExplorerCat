package net.explorercat.util.misc;

/**
 * Utility class to check properties regarding the architecture.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 24 Aug 2010
 */

public class ArchitectureModel
{
    // Size of a reference (by default we assume 64 bits architecture).
    private static int REFERENCE_SIZE_IN_BYTES = 8;

    // Static block that tries to figure out the architecture of the VM (32/64 bits).
    static
    {
	try
	{
	    String dataModel = System.getProperty("os.arch.data.model");
	    if(dataModel != null)
		REFERENCE_SIZE_IN_BYTES = Integer.parseInt(dataModel) / 8;
	}
	catch(Exception e)
	{
	    ; // Nothing to do, we use the default assumption.
	}
    }

    /**
     * Static method that returns the size of a reference in the current
     * architecture.
     * 
     * @return The size of bytes of a reference for the current architecture.
     */

    public static int getReferenceSizeInBytes()
    {
	return REFERENCE_SIZE_IN_BYTES;
    }
}
