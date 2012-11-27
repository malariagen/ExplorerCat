package net.explorercat.data;

/**
 * Stores an API key associated with a host domain/IP and the email address that
 * requested it.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Jun 2011
 */

public class PublicAPIKey
{
    private String key;
    private String email;
    private String host;
    private boolean isEnabled;

    /**
     * Builds a new API key instance.
     * 
     * @param key The hash that identifies the key (it is unique).
     * @param host The domain/IP associated with the key (requests must come
     *        from this host).
     * @param email The mail of the person that requested the key.
     * @param isEnabled True if the key is enabled or false if it has been
     *        revoked.
     */

    public PublicAPIKey(String key, String host, String email, boolean isEnabled)
    {
	this.key = key;
	this.email = email;
	this.host = host;
	this.isEnabled = isEnabled;
    }

    public String getKey()
    {
	return key;
    }

    public String getEmail()
    {
	return email;
    }

    public String getHost()
    {
	return host;
    }

    public boolean isEnabled()
    {
	return isEnabled;
    }

    public void disable()
    {
	isEnabled = false;
    }

    @Override
    public String toString()
    {
	return "[" + host + ", " + email + ", " + key + ", " + isEnabled + "]";
    }
}
