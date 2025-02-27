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
	private String album;
	
	
	// CONSTRUCTOR
	/** @pre Inputs != null */
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
	
	
	/** @pre Input >= 1, input <= 5 */
	void setRating(int r)
	{
		if (rating == 0)
			this.rating = r;
	}
	
	
	// MISC. METHODS
	Song songCpy()
	{
		// Clones the item to prevent escaping reference
		Song song = new Song(title, artist, album);
		song.setRating(getRating());
		return song;
	}
}