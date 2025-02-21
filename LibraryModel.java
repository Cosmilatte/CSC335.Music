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

	public ArrayList<Song> songsByTitle(String title) {
		ArrayList<Song> foundSongs = new ArrayList<>();
		for (Song song : songs) {
			if (song.getTitle().equals(title))
				foundSongs.add(song.songCpy());
		}

		int songIndex = 1;
		System.out.print("Title: " + title);
		for (Song song : foundSongs) {
			System.out.println("\n" + songIndex + ":");
			System.out.println("Artist: " + song.getArtist());
			System.out.println("Album: " + song.getAlbum());
			songIndex++;
		}

		return new ArrayList<>(foundSongs);
	}

	public ArrayList<Song> songsByArtist(String artist) {
		ArrayList<Song> foundSongs = new ArrayList<>();
		for (Song song : songs) {
			if (song.getArtist().equals(artist))
				foundSongs.add(song.songCpy());
		}

		int artistIndex = 1;
		System.out.print("Artist: " + artist);
		for (Song song : foundSongs) {
			System.out.println("\nSong " + artistIndex + ":");
			System.out.println("Title: " + song.getTitle());
			System.out.println("Album: " + song.getAlbum());
			artistIndex++;
		}

		return foundSongs;
	}

	// not sure if we are required to create playlists or add them to the library
	// public void createPlaylist(String name) {
	// 	playlists.add(new PlayList(name));
	// }

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
