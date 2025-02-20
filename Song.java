// package model;

// Song.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class Song
{
	// INSTANCE VARIABLES
	private String title;
	private String artist;
	private int rating;
	
	
	// CONSTRUCTOR
	public Song(String title, String artist)
	{
		this.title = title;
		this.artist = artist;
		
		this.rating = 0;
	}
	
	
	// GETTERS+SETTERS
	public String getTitle()
	{
		return title;
	}
	
	
	public String getArtist()
	{
		return artist;
	}
	
	
	public int getRating()
	{
		return rating;
	}
	
	
	public void setRating(int r)
	{
		if ((r > 5) || (r < 1))
		{
			System.err.println("ERROR: Rating number must be betweem 1 - 5, was " + r);
		}
		else
		{
			this.rating = r;
		}
	}
	
	
	// MISC. METHODS
}
