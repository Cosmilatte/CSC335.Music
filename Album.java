package model;

import java.util.ArrayList;

// Album.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class Album
{
	// INSTANCE VARIABLES
	ArrayList<Song> songs;
	String title;
	String artist;
	String genre;
	int year;
	
	
	// CONSTRUCTOR
	public Album(String title, String artist, String genre, int year)
	{
		this.songs = new ArrayList<Song>();
		
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
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
	
	
	public String getGenre()
	{
		return genre;
	}
	
	
	public int getYear()
	{
		return year;
	}
	
	
	public int getLength()
	{
		return songs.size();
	}
	
	
	// MISC. METHODS
	public void addSong(Song s)
	{
		if ((s.getArtist()).compareTo(artist) != 0)
		{
			System.err.println("ERROR: Song's artist does not align with Album");
			System.err.println("Song: " + s.getArtist() + " VS Album: " + artist);
		}
		else
		{
			songs.add(s);
		}
	}
	
	
	@Override
	public String toString()
	{
		String result = "";
		
		for (Song s : songs)
		{
			result += s.getTitle() + ", ";
		}
		result = result.substring(0, result.length()-2);
		
		return result;
	}
}
