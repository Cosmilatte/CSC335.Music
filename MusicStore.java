package model;

import java.util.ArrayList;

// MusicStore.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class MusicStore
{
	// INSTANCE VARIABLES
	private ArrayList<Artist> artists;
	
	
	// CONSTRUCTOR
	public MusicStore()
	{
		// When we read in the files, Artists will contain Albums will contain Songs?
		this.artists = new ArrayList<Artist>();
		
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
}
