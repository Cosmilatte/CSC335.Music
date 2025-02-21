// package model;

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

	public ArrayList<Song> getSongs() {
		ArrayList<Song> returnSongs = new ArrayList<>();
		for (Song song : this.songs)
			returnSongs.add(song.songCpy());

		return returnSongs;
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

	public Song songByTitle(String title) {
		for (Song song : this.songs) {
			if (song.getTitle().equals(title))
				return song.songCpy();
		}

		return null;
	}

	public Album albumCpy() {
		Album returningAlbum = new Album(this.title, this.artist, this.genre, this.year);
		for (Song song : this.songs)
			returningAlbum.addSong(song.songCpy());

		return returningAlbum;
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
