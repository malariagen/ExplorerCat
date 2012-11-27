package net.explorercat.data;

import java.util.GregorianCalendar;

/**
 * Simple class that represents a piece of new including the content,
 * author(user), URL link and date.
 * 
 * @author Jacob Almagro Garcia - jg10@sanger.ac.uk
 * @date 20 Sep 2010
 */

public class PieceOfNews
{
    private int id;
    private String content;
    private User author;

    // Date
    private int day;
    private int month;
    private int year;

    // Number of characters that will be extracted from the piece of news to act
    // as a brief line.
    private static int NUM_BRIEF_CHARACTERS = 256;

    /**
     * Constructor.
     */

    public PieceOfNews(int id, String content, int day, int month, int year, User author)
    {
	this.id = id;
	this.content = content;
	this.day = day;
	this.month = month;
	this.year = year;
	this.author = author;
    }

    public int getId()
    {
	return id;
    }

    public String getContent()
    {
	return content;
    }

    public User getAuthorUser()
    {
	return author;
    }

    public int getDay()
    {
	return day;
    }

    public int getMonth()
    {
	return month;
    }

    /**
     * Gets the month of the piece of news as a short string.
     * 
     * @return A short string representing the month.
     */

    public String getMonthAsString()
    {
	switch(month)
	{
	    case 1:
		return "Jan.";
	    case 2:
		return "Feb.";
	    case 3:
		return "Mar.";
	    case 4:
		return "Apr";
	    case 5:
		return "May";
	    case 6:
		return "Jun.";
	    case 7:
		return "Jul.";
	    case 8:
		return "Aug.";
	    case 9:
		return "Sep.";
	    case 10:
		return "Oct.";
	    case 11:
		return "Nov.";
	    case 12:
		return "Dec.";
	    default:
		return "Unknown";
	}
    }

    public int getYear()
    {
	return year;
    }

    public GregorianCalendar getDate()
    {
	return new GregorianCalendar(year, month, day);
    }

    /**
     * Gets a brief line from the content of the piece of news.
     * 
     * @return A line that contains the first NUM_BRIEF_CHARACTERS characters of
     *         the piece of news.
     */

    public String getBriefLine()
    {
	if(NUM_BRIEF_CHARACTERS < content.length())
	    return content.substring(0, NUM_BRIEF_CHARACTERS) + "...";
	else
	    return content;
    }
}
