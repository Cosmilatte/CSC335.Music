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
	public void addSong(Song song) {
		if (isInLibrary(song) && isInStore(song)) 
			songs.add(song.songCpy());
	}

	public void addAlbum(Album album) {
		if (isInStore(album)) {
			albums.add(album.albumCpy());
			for (Song song : album.getSongs())
				addSong(song);
		}
	}

	public void createPlaylist(String name) {
		playlists.add(new PlayList(name));
	}

	private boolean isInLibrary(Song song) {
		for (Song s : songs) {
			if (song.getTitle().equals(s.getTitle()) && song.getArtist().equals(s.getArtist()))
				return false;
		}

		return true;
	}

	private boolean isInStore(Song song) {
		for (Album album : store.getAlbums()) {
			for (Song s : album.getSongs()) {
				if (song.getTitle().equals(s.getTitle()) && song.getArtist().equals(s.getArtist()))
					return true;
			}
		}

		return false;
	}

	private boolean isInStore(Album album) {
		for (Album a : store.getAlbums()) {
			if (album.getTitle().equals(a.getTitle()) && album.getArtist().equals(a.getArtist()))
				return true;
		}

		return false;
	}
}
