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
				String command = keywords[0];
				String option = keywords[1];
				if (command.equals("add_S"))
					library.addSong(option.split("/")[0], option.split("/")[1]);

				else if (command.equals("add_A"))
					library.addAlbum(option.split("/")[0], option.split("/")[1]);

				else if (command.equals("add_P"))
				{
					String[] newSong = option.split("/");
					library.addToPlaylist(newSong[0], newSong[1], newSong[2]);
				}

				else if (command.equals("rm"))
				{
					String[] newSong = option.split("/");
					library.removeFromPlaylist(newSong[0], newSong[1], newSong[2]);
				}

				else if (command.equals("song_TS")) {
					for (String song : store.songsByTitle(option))
						System.out.println(song);
				}

				else if (command.equals("song_TL")) {
					for (String song : library.songsByTitle(option))
						System.out.println(song);
				}

				else if (command.equals("song_AS")) {
					for (String song : store.songsByArtist(option))
						System.out.println(song);
				}
				
				else if (command.equals("song_AL")) {
					for (String song : library.songsByArtist(option))
						System.out.println(song);
				}

				else if (command.equals("album_TS")) {
					System.out.println(store.albumByTitle(option).get(0));
				}

				else if (command.equals("album_TL")) {
					System.out.println(library.albumByTitle(option).get(0));
				}

				else if (command.equals("album_AS")) {
					for (String album : store.albumByArtist(option))
						System.out.println(album);
				}

				else if (command.equals("album_AL")) {
					for (String album : library.albumByArtist(option))
						System.out.println(album);
				}

				else if (command.equals("playlist"))
				{
					for (String playlist : library.playlistByTitle(option))
						System.out.println(playlist);
				}

				else if (command.equals("get"))
				{
					if (option.equals("songs"))
					{
						for (String song : library.getSongTitles())
							System.out.println(song);
					}

					else if (option.equals("artists"))
					{
						for (String artist : library.getArtists())
							System.out.println(artist);
					}

					else if (option.equals("albums"))
					{
						for (String album : library.getAlbums())
							System.out.println(album);
					}

					else if (option.equals("albums"))
					{
						for (String album : library.getAlbums())
							System.out.println(album);
					}

					else if (option.equals("playlists"))
					{
						for (String playlist : library.getPlaylistNames())
							System.out.println(playlist);
					}

					else if (option.equals("favorites"))
					{
						for (String goodSong : library.getFavorites())
							System.out.println(goodSong);
					}
					
					else
					{
						System.out.println("Invalid Command: Please type again");
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

		/*
		 * Command Rule:
		 * The 'command id' is isolated by ": ",
		 * The input(arguments) of the command can only be separated by /
		 */
		
		System.out.println("• Add a SONG to the library");
		System.out.println("    add_S: <song title>/<artist name>");
		System.out.println("    Ex: add_S: My Same/Adele\n");

		System.out.println("• Add an ALBUM to the library");
		System.out.println("    add_A: <album title>/<artist name>");
		System.out.println("    Ex: add_A: 1/Adele9\n");

		System.out.println("• Add a SONG to the PLAYLIST");
		System.out.println("    add_P: <playlist name/<song title>/<artist name>");
		System.out.println("    Ex: add_P: Driving/My Same/Adele\n");

		System.out.println("• Remove a SONG from the PLAYLIST");
		System.out.println("    rm: <playlist name/<song title>/<artist name>");
		System.out.println("    Ex: rm: Driving/My Same/Adele\n");

		System.out.println("• Create a PLAYLIST");
		System.out.println("    new: <playlist name>");
		System.out.println("    Ex: new: driving\n");

		System.out.println("• Search for SONGS by TITLE from the STORE");
		System.out.println("    song_TS: <song title>");
		System.out.println("    Ex: song_TS: My Same\n");

		System.out.println("• Search for SONGS by TITLE from the LIBRARY");
		System.out.println("    song_TL: <song title>");
		System.out.println("    Ex: song_TL: My Same\n");

		System.out.println("• Search for SONGS by ARTIST from the STORE");
		System.out.println("    song_AS: <artist name>");
		System.out.println("    Ex: song_AS: Adele\n");

		System.out.println("• Search for SONGS by ARTIST from the LIBRARY");
		System.out.println("    song_AL: <artist name>");
		System.out.println("    Ex: song_AL: Adele\n");
		
		System.out.println("• Search for ALBUM by TITLE from the STORE");
		System.out.println("    album_T: <album title>");
		System.out.println("    Ex: album_T: Coat of Many Colors\n");

		System.out.println("• Search for ALBUM by TITLE from the LIBRARY");
		System.out.println("    album_T: <album title>");
		System.out.println("    Ex: album_T: Coat of Many Colors\n");

		System.out.println("• Search for ALBUMS by ARTIST from the STORE");
		System.out.println("    album_A: <artist name>");
		System.out.println("    Ex: album_A: Adele\n");

		System.out.println("• Search for ALBUMS by ARTIST from the LIBRARY");
		System.out.println("    album_A: <artist name>");
		System.out.println("    Ex: album_A: Adele\n");

		System.out.println("• Search for a PLAYLIST");
		System.out.println("    playlist: <playlist name>");
		System.out.println("    Ex: playlist: recent");

		System.out.println("• Get a list of song titles");
		System.out.println("    get: songs");

		System.out.println("• Get a list of artists");
		System.out.println("    get: artists");

		System.out.println("• Get a list of albums");
		System.out.println("    get: albums");

		System.out.println("• Get a list of playlists");
		System.out.println("    get: playlists");

		System.out.println("• Get a list of favorite songs");
		System.out.println("    get: favorites");

		System.out.println();



		System.out.println("|--======================================--|");
	}
}