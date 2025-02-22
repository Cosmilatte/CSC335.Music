// package model;

import java.util.ArrayList;

// Artist.java
// Created 2 - 17 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class Artist
{
	// INSTANCE VARIABLES
	ArrayList<Album> albums;
	String name;
	
	
	// CONSTRUCTOR
	public Artist(String name)
	{
		this.albums = new ArrayList<Album>();
		this.name = name;
	}
	
	
	// GETTERS+SETTERS
	
	
	// MISC. METHODS
	public void addAlbum(Album a)
	{
		if ((a.getArtist()).compareTo(name) != 0)
		{
			System.err.println("ERROR: Album's artist does not align with artist");
			System.err.println("Album: " + a.getArtist() + " VS: " + name);
		}
		else
			albums.add(a);
	}
}
