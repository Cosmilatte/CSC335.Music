package model;

import java.util.ArrayList;


// PlayList.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

class PlayList
{
	// INSTANCE VARIABLES
	private String name;
	private ArrayList<Song> songs;
	
	
	// CONSTRUCTOR
	PlayList(String title)
	{
		this.name = title;
		this.songs = new ArrayList<Song>();
	}
	
	
	// GETTERS+SETTERS
	String getName()
	{
		return name;
	}

	
	ArrayList<Song> getSongs()
	{
		ArrayList<Song> songsArr = new ArrayList<>();
		
		for (Song song : this.songs)
			songsArr.add(song.songCpy());

		return songsArr;
	}
	
	
	// MISC. METHODS
	void addSong(Song song)
	{
		songs.add(song);
	}

	
	void removeSong(Song song)
	{
		songs.remove(song);
	}

	
	PlayList playlistCpy()
	{
		PlayList playlist = new PlayList(name);
		playlist.songs = getSongs();
		return playlist;
	}
}