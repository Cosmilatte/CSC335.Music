package model;

import java.util.ArrayList;

// Album.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: Album is a Class that represents, using an ArrayList
//   and other instance variables, a collection of Songs.

class Album
{
	// INSTANCE VARIABLES
	private ArrayList<Song> songs;
	private String title;
	private String artist;
	private String genre;
	private int year;
	
	
	// CONSTRUCTOR
	Album(String title, String artist, String genre, int year)
	{
		this.songs = new ArrayList<Song>();
		
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
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
	
	
	String getGenre()
	{
		return genre;
	}
	
	
	int getYear()
	{
		return year;
	}

	
	ArrayList<Song> getSongs()
	{
		// Clones the array and its songs to prevent escaping reference
		ArrayList<Song> songs = new ArrayList<Song>();
		
		for (Song song : this.songs)
			songs.add(song.songCpy());

		return songs;
	}
	
	
	// MISC. METHODS
	void addSong(Song s)
	{
		if ((s.getArtist()).compareTo(artist) == 0)
			songs.add(s);
	}
}
