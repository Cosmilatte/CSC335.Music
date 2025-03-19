package model;

// Song.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: Song is a Class that represents, in String and int
//    form, the Title, Artist, Rating, and Album that a song
//    belongs to.

class Song
{
	// INSTANCE VARIABLES
	private String title;
	private String artist;
	private int rating;
	private int plays;
	private String album;
	
	
	// CONSTRUCTOR
	/** @pre Inputs != null */
	Song(String title, String artist, String album)
	{
		this.title = title;
		this.artist = artist;
		this.rating = 0;
		this.plays = 0;
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
	
	
	int getPlays()
	{
		return plays;
	}
	
	
	String getAlbum() {
		return album;
	}
	
	
	/** @pre Input >= 1, input <= 5 */
	void setRating(int r)
	{
		if (rating == 0)
			this.rating = r;
	}
	
	
	// Exclusively for use in songCpy
	private void setPlays(int r)
	{
		this.plays = r;
	}
	
	
	// MISC. METHODS
	Song songCpy()
	{
		// Clones the item to prevent escaping reference
		Song song = new Song(title, artist, album);
		song.setRating(getRating());
		song.setPlays(getPlays());
		return song;
	}
	
	
	void addPlay()
	{
		this.plays = plays++;
	}
}