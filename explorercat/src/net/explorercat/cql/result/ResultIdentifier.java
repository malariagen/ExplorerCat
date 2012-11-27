package net.explorercat.cql.result;

/**
 * An class that is used to uniquely identify a result generator. The identifier
 * is composed by a hash code coming from the result generator and a unique
 * ticket number that is generated when the result is registered in the
 * pagination cache.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 19 Aug 2010
 */

public class ResultIdentifier
{
    private int resultGeneratorHashCode;
    private int ticketNumber;

    /**
     * Creates a new entry identifier.
     * 
     * @param hashCode Hash code representing the result generator.
     * @param ticketNumber Unique number assigned to the generator.
     */

    public ResultIdentifier(int hashCode, int ticketNumber)
    {
	this.resultGeneratorHashCode = hashCode;
	this.ticketNumber = ticketNumber;
    }

    /**
     * Gets the ticket number assigned to the result..
     * 
     * @return Unique number assigned to the result.
     */

    public int getTicketNumber()
    {
	return ticketNumber;
    }

    // Necessary to get the maps working properly.

    @Override
    public int hashCode()
    {
	return (ticketNumber + "_" + resultGeneratorHashCode).hashCode();
    }

    /**
     * Gets the hash code of the associated result generator.
     * 
     * @return Hash code representing the associated result generator.
     */

    public int getResultGeneratorHashCode()
    {
	return resultGeneratorHashCode;
    }

    @Override
    public boolean equals(Object other)
    {
	if(!(other instanceof ResultIdentifier))
	    return false;

	ResultIdentifier otherEntry = (ResultIdentifier) other;

	return this.ticketNumber == otherEntry.ticketNumber
	       && this.resultGeneratorHashCode == otherEntry.resultGeneratorHashCode;
    }

    @Override
    public String toString()
    {
	return "<Ticket: " + ticketNumber + ", Hash: " + resultGeneratorHashCode + ">";
    }
}
