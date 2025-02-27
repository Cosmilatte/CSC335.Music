package model;

// Song.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

class Song
{
	// INSTANCE VARIABLES
	private String title;
	private String artist;
	private int rating;
	private String album;
	
	
	// CONSTRUCTOR
	Song(String title, String artist, String album)
	{
		this.title = title;
		this.artist = artist;
		this.rating = 0;
		this.album = album;
	}
	
	
	// GETTERS+SETTERS
	String getTitle()
	{
		return title;
	}
	
	
	String getArtist()
	{
		return artist;
	}
	
	
	int getRating()
	{
		return rating;
	}
	
	
	String getAlbum() {
		return album;
	}
	
	
	int setRating(int r)
	{
		// NOTE: This only works if the song has not been rated before
		// This helps with making this Class almost immutable
		
		if ((r > 5) || (r < 1))
			return 1;
		else if (r == 0)
			this.rating = r;
		return 0;
	}
	
	
	// MISC. METHODS
	Song songCpy()
	{
		Song song = new Song(title, artist, album);
		song.setRating(getRating());
		return song;
	}

	@Override
	public String toString()
	{
		return title + " by " + artist + " from " + album;
	}
}