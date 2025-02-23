package view;

// Access.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import model.LibraryModel;
import model.MusicStore;
import java.util.Scanner;

public class Access 
{
	// PRIVATE INSTANCE VARIABLES
	private MusicStore ms;
	private LibraryModel lm;
	
	
	// Main
	public static void main(String[] args)
	{
		//Access runProgram = new Access();
		run();
	}
	
	// Constructor
	public Access()
	{
		this.ms = new MusicStore();
		this.lm = new LibraryModel(ms);
		
		run();
	}
	
	
	// Method
	private static void run()
	{
		System.out.println("|--======================================--|");
		System.out.println("\n");
		System.out.println(" Hello User! Welcome to Lilian and Lucian's ");
		System.out.println("            MUSIC LIBRARY MODEL!            ");
		System.out.println("\n");
		System.out.println("|--======================================--|");
		System.out.println("\n");
		System.out.println("Please type ''help'' to begin, or whenever you need help! ");
		
		Scanner scanner = new Scanner(System.in);
		String response = scanner.nextLine();
		if (response.contentEquals("help"))
			help();
		
		while (true)
		{
			System.out.println(">: ");
			response = scanner.nextLine();
			if (response.contentEquals("help"))
				help();
			else
			{
				if (response.contentEquals("quit"))
					return;
			}
		}
	}
	
	
	private static void help()
	{
		System.out.println("|--======================================--|");
		System.out.println("\n");
		System.out.println("Type 'help' at any time to return to this page.");
		System.out.println("Type 'quit' at any time to exit the program.");
		System.out.println("\n");
		System.out.println("USABLE COMMANDS:");
		
		
		
		System.out.println("\n");
		System.out.println("|--======================================--|");
	}
}
