package view;

// Access.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import model.LibraryModel;
import model.MusicStore;

public class Access 
{
	// PRIVATE INSTANCE VARIABLES
	private MusicStore ms;
	private LibraryModel lm;
	
	
	public void main()
	{
		this.ms = new MusicStore();
		this.lm = new LibraryModel(ms);
	}
}
