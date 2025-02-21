// package model;

import java.util.ArrayList;

// LibraryModel.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class LibraryModel
{
	// INSTANCE VARIABLES
	private MusicStore store;
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
	private ArrayList<PlayList> playlists;
	
	// CONSTRUCTOR
	public LibraryModel(MusicStore ms) {
		store = ms;
		songs = new ArrayList<>();
		albums = new ArrayList<>();
		playlists = new ArrayList<>();

	}
	
	// GETTERS+SETTERS
	
	// MISC. METHODS
	// public void addSong(Song song) {
	// 	if (isInLibrary(song)) 
	// 		songs.add(song.songCpy());
	// }

	private boolean isInLibrary(Song song) {
		for (Song s : songs) {
			if (song.getTitle().equals(s.getTitle()) && song.getTitle().equals(s.getTitle()))
				return false;
		}

		return true;
	}
}
