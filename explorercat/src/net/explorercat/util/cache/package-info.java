/**
 * Contains the basic cache implementation for EC. Caches are mainly used to
 * store frequently accessed data selections (query results). We use a compact
 * representation of the query as a key for the cache. As usual, there is a
 * trade-off between computing time and memory.
 * 
 * @author Jacob Almagro-Garcia
 */

package net.explorercat.util.cache;