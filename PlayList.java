// package model;

import java.util.ArrayList;


// PlayList.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class PlayList
{
	// INSTANCE VARIABLES
	private String name;
	private ArrayList<Song> songs;
	
	
	// CONSTRUCTOR
	public PlayList(String title)
	{
		name = title;
	}
	
	
	// GETTERS+SETTERS
	public String getName()
	{
		return name;
	}

	
	public ArrayList<Song> getSongs()
	{
		ArrayList<Song> songs = new ArrayList<>();
		for (Song song : this.songs)
			songs.add(song.songCpy());

		return songs;
	}
	
	
	// MISC. METHODS
	public void addSong(Song song)
	{
		songs.add(song);
	}

	
	public void removeSong(Song song)
	{
		songs.remove(song);
	}

	
	public PlayList playlistCpy()
	{
		PlayList playlist = new PlayList(name);
		playlist.songs = getSongs();
		return playlist;
	}
}