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
	private String album;
	
	
	// CONSTRUCTOR
	public Song(String title, String artist, String album)
	{
		this.title = title;
		this.artist = artist;
		this.rating = 0;
		this.album = album;
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
	
	
	public String getAlbum() {
		return album;
	}
	
	
	public int setRating(int r)
	{
		if ((r > 5) || (r < 1))
			return 1;
		else
		{
			this.rating = r;
			return 0;
		}
	}
	
	
	// MISC. METHODS
	public Song songCpy()
	{
		Song song = new Song(title, artist, album);
		song.setRating(getRating());
		return song;
	}
}