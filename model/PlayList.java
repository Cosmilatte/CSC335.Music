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
		name = title;
	}
	
	
	// GETTERS+SETTERS
	String getName()
	{
		return name;
	}

	
	ArrayList<Song> getSongs()
	{
		ArrayList<Song> songs = new ArrayList<>();
		for (Song song : this.songs)
			songs.add(song.songCpy());

		return songs;
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

	@Override
	public String toString()
	{
		String info = "Playlist: " + name + "\nSongs: ";
		for (Song song : songs)
			info += "\n• " + song.toString().split(" from")[0];

		return info;
	}
}