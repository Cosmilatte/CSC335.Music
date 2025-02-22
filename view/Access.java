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
	
	
	// Main
	public static void main(String[] args)
	{
		Access runProgram = new Access();
	}
	
	// Constructor
	public Access()
	{
		this.ms = new MusicStore();
		this.lm = new LibraryModel(ms);
		
		run();
	}
	
	
	// Method
	private void run()
	{
		System.out.println("|--======================================--|");
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("Hello User! Welcome to Lilian and Lucian's");
		System.out.println("           MUSIC LIBRARY MODEL!           ");
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("|--======================================--|");
	}
}
