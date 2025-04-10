package view;

// Access.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: This program initializes a MusicStore and LibraryModel
//   from which to simulate an online music store and library for
//   a user to interact with. This is the View Class.

import model.LibraryModel;
import model.MusicStore;

import java.util.ArrayList;
import java.util.Scanner;

public class Access 
{
	// PRIVATE INSTANCE VARIABLES
	private MusicStore store;
	private LibraryModel library;
	public final static Scanner SCANNER = new Scanner(System.in);
	
	
	// Constructor
	public Access()
	{
		this.store = new MusicStore();
		this.library = new LibraryModel(store);
	}
	
	
	// Method
	public void run()
	{
		System.out.println("|--==============================================--|");
		System.out.println("");
		System.out.println("     Hello User! Welcome to Lilian and Lucian's     ");
		System.out.println("                MUSIC LIBRARY MODEL!                ");
		System.out.println("");
		System.out.println("|--==============================================--|");
		System.out.println("");
		System.out.println(" 'help' gives you a list of commands, 'exit' exits! ");
		
		System.out.print(">: ");
		String response = SCANNER.nextLine();		
		while (!response.equals("exit"))
		{
			String[] keywords = response.split(": ");
			
			if (response.contentEquals("help"))
				help();

			else if (keywords.length > 3)
				System.out.println("Invalid Command: Please type again");

			else
			{
				String command = keywords[0];
				String option = keywords[1];
				if (command.equals("add_S"))
					library.addSong(option.split("/")[0], option.split("/")[1]);

				else if (command.equals("rm_S"))
					library.removeSong(option.split("/")[0], option.split("/")[1]);
				
				else if (command.equals("add_A"))
					library.addAlbum(option.split("/")[0], option.split("/")[1]);

				else if (command.equals("rm_A"))
					library.removeAlbum(option.split("/")[0], option.split("/")[1]);
				
				else if (command.equals("add_F"))
					library.addFavorite(option.split("/")[0], option.split("/")[1]);
				
				else if (command.equals("rate_S"))
					library.rateSong(option.split("/")[0], option.split("/")[1], Integer.valueOf(option.split("/")[2]));
				
				else if (command.equals("play_S"))
				{
					System.out.println(library.playSong(option.split("/")[0], option.split("/")[1]));
					System.out.println("♫♩ ♪♩ ♫");
				}
				
				else if (command.equals("shuffle_S"))
					library.shuffleSongs(option);
				
				else if (command.equals("new_P"))
					library.createPlaylist(option);
				
				else if (command.equals("shuffle_P"))
					library.shufflePlaylist(option);
				
				else if (command.equals("add_P"))
				{
					String[] newSong = option.split("/");
					library.addToPlaylist(newSong[0], newSong[1], newSong[2]);
				}

				else if (command.equals("rm_P"))
				{
					String[] newSong = option.split("/");
					library.removeFromPlaylist(newSong[0], newSong[1], newSong[2]);
				}

				else if (command.equals("song_TS"))
				{
					for (String song : store.songsByTitle(option))
						System.out.println(song);
				}

				else if (command.equals("song_TL"))
				{
					for (String song : library.songsByTitle(option))
						System.out.println(song);
					
					System.out.println("Do you want the Album info? Type 'Y' or 'N': ");
					System.out.print(">: ");
					response = SCANNER.nextLine();
					if (response.contentEquals("Y"))
					{
						for (String song : library.albumBySong(option, true))
							System.out.println(song);
					}
				}

				else if (command.equals("song_AS"))
				{
					for (String song : store.songsByArtist(option))
						System.out.println(song);
				}
				
				else if (command.equals("song_AL"))
				{
					for (String song : library.songsByArtist(option))
						System.out.println(song);
					
					System.out.println("Do you want the Album info? Type 'Y' or 'N': ");
					System.out.print(">: ");
					response = SCANNER.nextLine();
					if (response.contentEquals("Y"))
					{
						for (String song : library.albumBySong(option, false))
							System.out.println(song);
					}
				}
				
				else if (command.equals("song_GL"))
				{
					for (String song : library.songsByGenre(option))
						System.out.println(song);
				}
				
				
				else if (command.equals("song_OL"))
				{
					for (String song : library.songsByOption(option))
						System.out.println(song);
				}

				else if (command.equals("album_TS"))
				{
					for (String str : store.albumByTitle(option))
						System.out.println(str);
				}

				else if (command.equals("album_TL"))
				{
					for (String str : library.albumByTitle(option))
						System.out.println(str);
				}

				else if (command.equals("album_AS"))
				{
					for (String str : store.albumByArtist(option))
						System.out.println(str);
				}

				else if (command.equals("album_AL"))
				{
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
					
					else if (option.equals("recents"))
					{
						for (String goodSong : library.getRecents())
							System.out.println(goodSong);
					}
					
					else if (option.equals("frequents"))
					{
						for (String goodSong : library.getFrequents())
						{
							if (goodSong != null)
								System.out.println(goodSong);
						}
					}
					
					else if (option.equals("top_rated"))
					{
						for (String goodSong : library.getTopRated())
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

			System.out.print(">: ");
			response = SCANNER.nextLine();
		}

		System.out.println("|--==============================================--|");
		System.out.println("");
		System.out.println("          Exit recieved; closing program!           ");
		System.out.println("                  Have a nice day!                  ");
		System.out.println("");
		System.out.println("|--==============================================--|");
	}
	
	
	private static void help()
	{
		System.out.println("|--==============================================--|");
		System.out.println("");
		System.out.println("Type 'help' at any time to return to this page.");
		System.out.println("Type 'quit' at any time to exit the program.");
		System.out.println("");
		System.out.println("USABLE COMMANDS:");

		/*
		 * Command Rule:
		 * The 'command id' is isolated by ": ",
		 * The input(arguments) of the command can only be separated by /
		 */
		
		System.out.println("• Add a SONG to the library");
		System.out.println("    add_S: <song title>/<artist name>");
		System.out.println("    Ex: add_S: My Same/Adele\n");
		
		System.out.println("• Remove a SONG from the library");
		System.out.println("    rm_S: <song title>/<artist name>");
		System.out.println("    Ex: rm_S: My Same/Adele\n");

		System.out.println("• Add an ALBUM to the library");
		System.out.println("    add_A: <album title>/<artist name>");
		System.out.println("    Ex: add_A: 19/Adele\n");
		
		System.out.println("• Remove an ALBUM from the library");
		System.out.println("    rm_A: <album title>/<artist name>");
		System.out.println("    Ex: rm_A: 19/Adele\n");
		
		System.out.println("• Add a SONG to favorites");
		System.out.println("    add_F: <song title>/<artist name>");
		System.out.println("    Ex: add_F: Awake My Soul/Mumford & Sons\n");
		
		System.out.println("• Rate a SONG in the LIBRARY");
		System.out.println("    rate_S: <song title>/<artist name>/int");
		System.out.println("    Ex: rate_S: Little Lion Man/Mumford & Sons/5\n");
		
		System.out.println("• Play a SONG in the LIBRARY");
		System.out.println("    play_S: <song title>/<artist name>/int");
		System.out.println("    Ex: play_S: Little Lion Man/Mumford & Sons/5\n");
		
		System.out.println("• Shuffle SONGs in the LIBRARY");
		System.out.println("    shuffle_S: N/A");
		System.out.println("    Ex: shuffle_S: N/A\n");

		System.out.println("• Create a PLAYLIST");
		System.out.println("    new_P: <playlist name>");
		System.out.println("    Ex: new_P: driving\n");
		
		System.out.println("• Shuffle a PLAYLIST");
		System.out.println("    shuffle_P: <playlist name>");
		System.out.println("    Ex: shuffle_P: driving\n");
		
		System.out.println("• Add a SONG to the PLAYLIST");
		System.out.println("    add_P: <playlist name>/<song title>/<artist name>");
		System.out.println("    Ex: add_P: Driving/My Same/Adele\n");

		System.out.println("• Remove a SONG from the PLAYLIST");
		System.out.println("    rm_P: <playlist name>/<song title>/<artist name>");
		System.out.println("    Ex: rm_P: Driving/My Same/Adele\n");

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
		
		System.out.println("• Search for SONGS by GENRE from the LIBRARY");
		System.out.println("    song_GL: <artist name>");
		System.out.println("    Ex: song_GL: Adele\n");
		
		System.out.println("• Search for SONGS by OPTION from the LIBRARY");
		System.out.println("    song_OL: <title OR artist OR rating>");
		System.out.println("    Ex: song_OL: title\n");
		
		System.out.println("• Search for ALBUM by TITLE from the STORE");
		System.out.println("    album_TS: <album title>");
		System.out.println("    Ex: album_TS: Coat of Many Colors\n");

		System.out.println("• Search for ALBUM by TITLE from the LIBRARY");
		System.out.println("    album_TL: <album title>");
		System.out.println("    Ex: album_TL: Coat of Many Colors\n");

		System.out.println("• Search for ALBUMS by ARTIST from the STORE");
		System.out.println("    album_AS: <artist name>");
		System.out.println("    Ex: album_AS: Adele\n");

		System.out.println("• Search for ALBUMS by ARTIST from the LIBRARY");
		System.out.println("    album_AL: <artist name>");
		System.out.println("    Ex: album_AL: Adele\n");

		System.out.println("• Search for a PLAYLIST");
		System.out.println("    playlist: <playlist name>");
		System.out.println("    Ex: playlist: recent\n");

		System.out.println("• Get a list of song titles");
		System.out.println("    get: songs\n");

		System.out.println("• Get a list of artists");
		System.out.println("    get: artists\n");

		System.out.println("• Get a list of albums");
		System.out.println("    get: albums\n");

		System.out.println("• Get a list of playlists");
		System.out.println("    get: playlists\n");

		System.out.println("• Get a list of favorite songs");
		System.out.println("    get: favorites\n");
		
		System.out.println("• Get a list of recently played songs");
		System.out.println("    get: recents\n");
		
		System.out.println("• Get a list of frequently played songs");
		System.out.println("    get: frequents\n");
		
		System.out.println("• Get a list of top rated songs");
		System.out.println("    get: top_rated\n");

		System.out.println("");

		System.out.println("|--==============================================--|");
	}
}