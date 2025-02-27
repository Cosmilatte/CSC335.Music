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
	private static LibraryModel library;
	
	
	// Main
	public static void main(String[] args)
	{
		Access runProgram = new Access();
	}
	
	
	// Constructor
	public Access()
	{
		this.library = new LibraryModel(new MusicStore());
		
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

			else if (keywords.length != 2)
				System.out.println("Invalid Command: Please type again");

			else
			{
				if (keywords[0].equals("song_T")) {
					for (String song : library.songsByTitle(keywords[1])) {
						System.out.println(song);
					}
				}

				else if (keywords[0].equals("song_A")) {
					for (String song : library.songsByArtist(keywords[1])) {
						System.out.println(song);
					}
				}

				else if (keywords[0].equals("album_T")) {
					System.out.println(library.albumByTitle(response).get(0));
				}

				else if (keywords[0].equals("album_A")) {
					for (String album : library.albumByArtist(keywords[1])) {
						System.out.println(album);
					}
				}

				else if (keywords[0].equals("playlist"))
				{
					for (String playlist : library.playlistByTitle(keywords[1])) {
						System.out.println(playlist);
					}
				}

				else
				{
					System.out.println("Invalid Command: Please type again");
				}
			}

			response = scanner.nextLine();
		}

		scanner.close();
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
		System.out.println("    Ex: album_A: Adele\n");

		System.out.println("• To search a PLAYLIST");
		System.out.println("    playlist: <playlist name>");
		System.out.println("    Ex: playlist: recent");
		System.out.println();

		System.out.println("|--======================================--|");
	}
}