package net.explorercat.util.misc;

import java.util.Random;

/**
 * A helper class to obfuscate/deobfuscate hexadecimal hashes. This will avoid
 * the users to easily guess hashes keys (although all the information is
 * public).
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 30 Jun 2011
 */

public class HexCodeObfuscator
{
    private static final String CODING_CHARACTERS = "0123456789abcdef";

    /**
     * Obfuscates a heaxadecimal key adding some noise that the
     * {@link deobfuscateKey} method knows how to remove.
     * 
     * @param key The hash key to be obfuscated.
     * @return An obfuscated version of the key that can be deobfuscated with
     *         the {@link deobfuscateKey} method.
     */

    public static String obfuscateKey(String key)
    {
	StringBuffer obfuscatedKey = new StringBuffer();
	Random randomGenerator = new Random();
	int n = CODING_CHARACTERS.length();

	// Add noise characters at odd positions.
	for(int i = 0; i < key.length(); ++i)
	{
	    obfuscatedKey.append(key.charAt(i));

	    char noiseCharacter = CODING_CHARACTERS.charAt(randomGenerator.nextInt((n)));
	    if(randomGenerator.nextBoolean())
		obfuscatedKey.append(Character.toUpperCase(noiseCharacter));
	    else
		obfuscatedKey.append(noiseCharacter);
	}

	return obfuscatedKey.toString();
    }

    /**
     * Deobfuscates a heaxadecimal key removing the noise added by the
     * {@link ObfuscateKey} method.
     * 
     * @param key The hash key to be deobfuscated.
     * @return The original key that was obfuscated.
     */

    public static String deobfuscateKey(String key)
    {
	StringBuffer originalKey = new StringBuffer();

	for(int i = 0; i < key.length(); ++i)
	    if(i % 2 == 0)
		originalKey.append(key.charAt(i));

	return originalKey.toString();
    }
}
