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
	private static MusicStore store;
	private static LibraryModel library;
	
	// Main
	public static void main(String[] args)
	{
		Access runProgram = new Access();
	}
	
	
	// Constructor
	public Access()
	{
		this.store = new MusicStore();
		this.library = new LibraryModel(store);
		
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
					for (String song : library.songsByTitle(keywords[1]))
						System.out.println(song);
				}

				else if (keywords[0].equals("song_A")) {
					for (String song : library.songsByArtist(keywords[1]))
						System.out.println(song);
				}

				else if (keywords[0].equals("album_T")) {
					System.out.println(library.albumByTitle(response).get(0));
				}

				else if (keywords[0].equals("album_A")) {
					for (String album : library.albumByArtist(keywords[1]))
						System.out.println(album);
				}

				else if (keywords[0].equals("playlist"))
				{
					for (String playlist : library.playlistByTitle(keywords[1]))
						System.out.println(playlist);
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
		System.out.println("• To add a SONG to the library");
		System.out.println("    add_S: <song title>");
		System.out.println("    Ex: add_S: My Same\n");

		System.out.println("• To add an ALBUM to the library");
		System.out.println("    add_A: <album title>");
		System.out.println("    Ex: add_A: 19\n");

		System.out.println("• To search for SONGS by TITLE from the STORE");
		System.out.println("    song_TS: <song title>");
		System.out.println("    Ex: song_TS: My Same\n");

		System.out.println("• To search for SONGS by TITLE from the LIBRARY");
		System.out.println("    song_TL: <song title>");
		System.out.println("    Ex: song_TL: My Same\n");

		System.out.println("• To search for SONGS by ARTIST from the STORE");
		System.out.println("    song_AS: <artist name>");
		System.out.println("    Ex: song_AS: Adele\n");

		System.out.println("• To search for SONGS by ARTIST from the LIBRARY");
		System.out.println("    song_AL: <artist name>");
		System.out.println("    Ex: song_AL: Adele\n");
		
		System.out.println("• To search for ALBUM by TITLE");
		System.out.println("    album_T: <album title>");
		System.out.println("    Ex: album_T: Coat of Many Colors\n");

		System.out.println("• To search for ALBUMS by ARTIST");
		System.out.println("    album_A: <artist name>");
		System.out.println("    Ex: album_A: Adele\n");

		System.out.println("• To search for a PLAYLIST");
		System.out.println("    playlist: <playlist name>");
		System.out.println("    Ex: playlist: recent");
		System.out.println();

		System.out.println("|--======================================--|");
	}
}