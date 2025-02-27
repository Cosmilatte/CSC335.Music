package model;

import java.util.ArrayList;

// PlayList.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: PlayList is a Class that represents, using an ArrayList
//   and name, a PlayList created by the user to store Songs of
//   their choosing.

class PlayList
{
	// INSTANCE VARIABLES
	private String name;
	private ArrayList<Song> songs;
	
	
	// CONSTRUCTOR
	/** @pre Input != null */
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
		// Clones the array and its songs to prevent escaping reference
		ArrayList<Song> songsArr = new ArrayList<>();
		
		for (Song song : this.songs)
			songsArr.add(song.songCpy());

		return songsArr;
	}
	
	
	// MISC. METHODS
	/** @pre Input != null */
	void addSong(Song song)
	{
		songs.add(song);
	}

	
	/** @pre Inputs != null */
	void removeSong(Song song)
	{
		songs.remove(song);
	}
}