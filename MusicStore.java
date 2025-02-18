package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// MusicStore.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class MusicStore
{
	// INSTANCE VARIABLES
	// private ArrayList<Artist> artists;
	private ArrayList<Album> albums;
	
	// CONSTRUCTOR
	public MusicStore()
	{
		// When we read in the files, Artists will contain Albums will contain Songs?
		this.artists = new ArrayList<Artist>();
		this.albums = new ArrayList<>();
		
		setStore();
	}
	
	
	// GETTERS+SETTERS
	public void setStore()
	{
		// THIS IS THE CONSTRUCTOR. 
		// PLEASE READ IN THE FILES HERE.
		
		// Albums file format: Album Title,Artist
		// This is the file you get all the other file names from so it must be read in first
		// Perhaps construct Artists here, but don't touch the Albums just yet
		
		// Album file format: Album Title,Artist,Genre,Year \n song \n song etc.
		// This is where you make Albums I think
		// Find the Artist by scrolling the this.artists array you added to already
		// Then add Albums you construct from the following information
	}
	
	
	// MISC. METHODS
	private void readAlbums() throws IOException{
		BufferedReader titlesReader = new BufferedReader(new FileReader("albums.txt"));
		// String title_artist = titlesReader.readLine();
		// while (title_artist != null) {
		// 	BufferedReader albumReader = new BufferedReader(new FileReader(title_artist.split(",")[0] + "_" + title_artist.split(",")[1] + ".txt"));
		// 	String[] information = albumReader.readLine().split(",");
		// 	Album curAlbum = new Album(information[0], information[1], information[2], Integer.parseInt(information[3].trim()));
		// 	String songTitle = albumReader.readLine();
		// 	while (songTitle != null) {
		// 		curAlbum.addSong(songTitle, curAlbum.getArtist());
		// 		songTitle = albumReader.readLine();

		// 	}

		// 	albums.add(curAlbum);
		// 	title_artist = titlesReader.readLine();
		// }
	}
}
