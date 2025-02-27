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
		while (!response.equals("quit"))
		{
			System.out.print(">: ");
			String[] keywords = response.split(": ");
			if (response.contentEquals("help"))
				help();

			// else
			// {
			// 	if (keywords[0].equals("song_T")) {
			// 		System.out.println(lm.songsByTitle)
			// 	}
			// }

			response = scanner.nextLine();
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
		System.out.println("• To search SONGS by TITLE");
		System.out.println("    song_T: <song title>");
		System.out.println("    Ex: song_T: Ground on Down\n");

		System.out.println("• To search SONGS by ARTIST");
		System.out.println("    song_A: <artist name>");
		System.out.println("    Ex: song_A: Adele\n");
		
		System.out.println("• To search ALBUM by TITLE");
		System.out.println("    album_T: <album title>");
		System.out.println("    Ex: album_T: Coat of Many Colors\n");

		System.out.println("• To search ALBUMS by ARTIST");
		System.out.println("    album_A: <artist name>");
		System.out.println("    Ex: slbum_A: Adele\n");

		System.out.println("• To search a PLAYLIST");
		System.out.println("    playlist: <playlist name>");
		System.out.println("    Ex: playlist: recent");
		System.out.println();

		System.out.println("|--======================================--|");
	}
}
