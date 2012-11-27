package net.explorercat.cql.selection.limiters;

/**
 * Types of limiters supported by the system.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date Aug 2010
 */

public enum LimiterType
{
    TOP_LIMITER,    // n first entities (with optional offset).
    RANDOM_LIMITER, // n random entities (with optional seed).
    BOTTOM_LIMITER; // n last entities (with optional offset).
}
