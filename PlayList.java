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
	public PlayList(String title) {
		this.name = title;
	}
	
	// GETTERS+SETTERS
	
	// MISC. METHODS
	public void addSong(Song song) {
		this.songs.add(song);
	}

	public void removeSong(Song song) {
		this.songs.remove(song);
	}
}
