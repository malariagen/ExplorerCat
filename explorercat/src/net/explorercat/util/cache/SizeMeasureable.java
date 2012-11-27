package net.explorercat.util.cache;

/**
 * Interface to be implemented by any class whose instances will be stored in
 * the smart cache.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 24 Aug 2010
 */

public interface SizeMeasureable
{
    /**
     * Gets the size of the object in bytes.
     * 
     * @return A long representing the size of the object in bytes.
     */

    public long getSizeInBytes();
}
