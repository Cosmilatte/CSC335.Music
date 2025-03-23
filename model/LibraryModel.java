package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// LibraryModel.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: LibraryModel is a Class that represents, using ArrayLists,
//   a user's editable song library. This interacts with the Store by
//   pulling information from it to store in this Library.

public class LibraryModel
{
	// INSTANCE VARIABLES
	private MusicStore store;
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
	private ArrayList<PlayList> playlists;
	private ArrayList<String> frequentsCount;
	
	
	// CONSTRUCTOR
	/** @pre Input != null */
	public LibraryModel(MusicStore ms)
	{
		store = ms;
		songs = new ArrayList<>();
		albums = new ArrayList<>();
		playlists = new ArrayList<>();
		frequentsCount = new ArrayList<>();
		
		createPlaylist("favorites");
		createPlaylist("recents");
		createPlaylist("frequents");
		createPlaylist("top_rated");
	}
	
	
	// MISCELLANEOUS
	/** @pre Inputs != null, int >= 1, int <= 5 */
	public void rateSong(String title, String artist, int r)
	{
		PlayList favorites = getPlaylist("favorites");
		PlayList topRated = getPlaylist("top_rated");
		// If the Song is in the Library and store, find and rate it
		if ( isInLibrarySong(title, artist) && isInStoreSong(title, artist) ) 
		{
			for (Song song : songs)
			{
				if (title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
				{
					song.setRating(r);
					if (r == 5)
					{
						favorites.addSong(song);
						topRated.addSong(song);
					}
					else if (r == 4)
						topRated.addSong(song);
					
				}
			}
		}
	}
	
	
	/** @pre Inputs != null */
	public String playSong(String title, String artist)
	{
		PlayList recentlyPlayed = getPlaylist("recents");
		boolean found = false;
		int dashIndex = 0;
		int currCount = 0;
		int freqIndex = 0;
		
		// If the Song is in the Library and store, find and play it
		if ( isInLibrarySong(title, artist) && isInStoreSong(title, artist) ) 
		{
			for (Song song : songs)
			{
				if (title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
				{
					song.addPlay();
					
					// Frequents
					for (String string : frequentsCount)
					{
						// FORMAT: ### - title by artist
						if (string.contains(title + " by " + artist))
						{
							dashIndex = string.indexOf(" - ");
							currCount = Integer.valueOf(string.substring(0, dashIndex))+1;
							frequentsCount.set(freqIndex, String.valueOf(currCount) + " - " 
									+ title + " by " + artist);
							found = true;
						}
						freqIndex++;
					}
					if (found == false)
						frequentsCount.add("1" + " - " + title + " by " + artist);
					
					// Recents
					if (recentlyPlayed.getSongs().size() < 10)
						recentlyPlayed.addSong(song);
					else
					{
						recentlyPlayed.removeEnd();
						recentlyPlayed.addSong(song);
					}
					return "Now playing: " + title + " by " + artist;
				}
			}
		}
		return "Song not found";
	}
	
	
	/** @pre Inputs != null */
	public void shuffleSongs(String str)
	{
		Collections.shuffle(songs);
	}
	
	
	/** @pre Input != null */
	public void shufflePlaylist(String name)
	{
		for (PlayList playlist : playlists)
		{
			if (name.contentEquals(playlist.getName()))
				Collections.shuffle(playlist.getSongsNONCOPY());
		}
	}
	
	
	// ADDERS + REMOVERS
	/** @pre Inputs != null */
	public void addSong(String title, String artist)
	{
		String genre = "GENRE ";
		
		// If the Song is not in the Library and is in store, find and add it
		if ( (!isInLibrarySong(title, artist)) && isInStoreSong(title, artist) ) 
		{
			for (Album album : store.getAlbums())
			{
				for (Song song : album.getSongs())
				{
					// If the album doesn't yet exist, make the album
					if ((title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
							&& !(isInLibraryAlbum(album.getTitle(), album.getArtist())))
					{
						Album albumCpy = new Album(album.getTitle(), album.getArtist(), album.getGenre(), 
								album.getYear());
						albumCpy.addSong(song.songCpy());
						albums.add(albumCpy);
						songs.add(song.songCpy());
						
						// And add to the Genre playlist!
						genre += album.getGenre();
						addToPlaylist(genre, title, artist);
						genre = "GENRE ";
					}
					// If the album DOES exist, make sure to add to that album
					else if ((title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
							&& (isInLibraryAlbum(album.getTitle(), album.getArtist())))
					{
						Song songCpy = song.songCpy();
						songs.add(songCpy);
						for (Album storeAlbum : albums)
						{
							if (song.getAlbum().contentEquals(storeAlbum.getTitle()) && 
									song.getArtist().contentEquals(storeAlbum.getArtist()))
							{
								storeAlbum.addSong(songCpy);
								
								// And add to the Genre playlist!
								genre += album.getGenre();
								addToPlaylist(genre, title, artist);
								genre = "GENRE ";
							}
						}
					}
				}
			}
		}
	}
	
	
	/** @pre Inputs != null */
	public void removeSong(String title, String artist)
	{
		// If the Song is in the Library and store, find and remove it
		int save = 0;
		Song curr = null;
		if ( (isInLibrarySong(title, artist)) && isInStoreSong(title, artist) ) 
		{
			// Iterate over until you find the index of the Song you wish to remove
			while (save != songs.size())
			{
				curr = songs.get(save);
				if (title.contentEquals(curr.getTitle()) && artist.contentEquals(curr.getArtist()))
					break;
				save++;
			}
			songs.remove(save);
		}
	}

	
	/** @pre Inputs != null */
	public void addAlbum(String title, String artist)
	{
		String genre = "GENRE ";
		
		// If the Album is not in the Library and is in store, find and add it
		if (!isInLibraryAlbum(title, artist) && isInStoreAlbum(title, artist)) 
		{
			for (Album album : store.getAlbums())
			{
				if (title.contentEquals(album.getTitle()) && artist.contentEquals(album.getArtist()))
				{
					albums.add(album);
					for (Song song : album.getSongs())
					{
						if (!songs.contains(song))
						{
							songs.add(song);
							
							// And add to the Genre playlist!
							genre += album.getGenre();
							addToPlaylist(genre, song.getTitle(), song.getArtist());
							genre = "GENRE ";
						}
					}
				}
			}
		}
		
		// If the Album is in the Library and is in store, find and check if it needs filling
		else if (isInLibraryAlbum(title, artist) && isInStoreAlbum(title, artist)) 
		{
			Album storeAlbum = null;
			for (Album album : store.getAlbums())
			{
				if (title.contentEquals(album.getTitle()) && artist.contentEquals(album.getArtist()))
					storeAlbum = album;
			}
			
			for (Album album : albums)
			{
				if (title.contentEquals(album.getTitle()) && artist.contentEquals(album.getArtist()))
				{
					for (Song song : storeAlbum.getSongs())
					{
						if (!isInLibrarySong(song.getTitle(), song.getArtist()))
						{
							songs.add(song);
							album.addSong(song);
							
							// And add to the Genre playlist!
							genre += album.getGenre();
							addToPlaylist(genre, song.getTitle(), song.getArtist());
							genre = "GENRE ";
						}
					}
				}
			}
		}
	}
	
	
	/** @pre Inputs != null */
	public void removeAlbum(String title, String artist)
	{
		// If the Album is in the Library and store, find and remove it
		int save = 0;
		Album curr = null;
		
		if ( (isInLibraryAlbum(title, artist)) && isInStoreAlbum(title, artist) ) 
		{
			// Iterate until you find the index of the Album you want to remove
			while (save != albums.size())
			{
				curr = albums.get(save);
				if (title.contentEquals(curr.getTitle()) && artist.contentEquals(curr.getArtist()))
					break;
				save++;
			}

			// Then cut out all the songs 
			for (Song song : albums.get(save).getSongs())
				removeSong(song.getTitle(), song.getArtist());
			albums.remove(save);
		}
	}
	
	
	/** @pre Inputs != null */
	public void addFavorite(String title, String artist)
	{
		// If the Song is in the Library and store, find and rate it
		if ( isInLibrarySong(title, artist) && isInStoreSong(title, artist) ) 
		{
			for (Song song : songs)
			{
				if (title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
					getPlaylist("favorites").addSong(song);
			}
		}
	}

	
	// SEARCHERS
	/** @pre Input != null */
	public ArrayList<String> songsByTitle(String title)
	{
		// Find the item(s), store into a String array to be printed
		ArrayList<String> songsArr = new ArrayList<String>();
		
		for (Song song : this.songs)
		{
			// Format: "Title by Artist in Album"
			if (song.getTitle().equals(title))
				songsArr.add(song.getTitle() + " by " + song.getArtist() + " in " +
						song.getAlbum());
		}

		if (songsArr.size() == 0)
		{
			songsArr.add("ITEM NOT FOUND.");
		}
		
		return songsArr;
	}

	
	/** @pre Input != null */
	public ArrayList<String> songsByArtist(String artist)
	{
		// Find the item(s), store into a String array to be printed
		ArrayList<String> songsArr = new ArrayList<String>();
		
		for (Song song : this.songs)
		{
			// Format: "Title by Artist in Album"
			if (song.getArtist().equals(artist))
				songsArr.add(song.getTitle() + " by " + song.getArtist() + " in " +
						song.getAlbum());
		}
		
		if (songsArr.size() == 0)
		{
			songsArr.add("ITEM NOT FOUND.");
		}
		
		return songsArr;
	}
	
	
	/** @pre Input != null */
	public ArrayList<String> songsByGenre(String genre)
	{
		// Find the item(s), store into a String array to be printed
		ArrayList<String> songsArr = new ArrayList<String>();
		
		for (Album album : this.albums)
		{
			// Format: "Title by Artist in Album"
			
			if (album.getGenre().equals(genre))
			{
				for (Song song : album.getSongs())
					songsArr.add(song.getTitle() + " by " + song.getArtist() + " in " +
							song.getAlbum());
			}
		}
		
		if (songsArr.size() == 0)
		{
			songsArr.add("ITEM NOT FOUND.");
		}
		
		return songsArr;
	}

	
	/** @pre Input != null */
	public ArrayList<String> albumBySong(String str, boolean isTitle)
	{
		// Find the item(s), store into a String array to be printed
		ArrayList<String> albumsArr = new ArrayList<String>();
		ArrayList<String> curr = new ArrayList<String>();
		
		// If the string is the title of the Song
		if (isTitle == true)
		{
			for (Song song : this.songs)
			{
				if (song.getTitle().equals(str))
				{
					// Add the album info
					curr = store.albumByTitle(song.getAlbum());
					albumsArr.addAll(curr);
					
					// Then, pull the album's title and artist from that Array
					String[] arr = curr.get(0).split(" by ");
					int stop = arr[1].indexOf(",");
					// Is it in the Library? Check the lengths
					if (curr.size()-2 != getSize(arr[0], arr[1].substring(0, stop)))
						albumsArr.add("ALBUM CONTAINED IN LIBRARY: NO");
					else
						albumsArr.add("ALBUM CONTAINED IN LIBRARY: YES");
				}
			}
		}
		// Else if the string is the artist of the Song
		else if (isTitle == false)
		{
			// Add the album info
			curr = store.albumByArtist(str);
			albumsArr.addAll(curr);
			
			// Then, pull the album's title and artist from that Array
			String[] arr = curr.get(0).split(" by ");
			// Is it in the Library? Check the lengths
			if (curr.size()-2 != getSize(arr[0], str))
				albumsArr.add("ALBUM(S) CONTAINED IN LIBRARY: NO");
			else
				albumsArr.add("ALBUM(S) CONTAINED IN LIBRARY: YES");
		}
		
		if (albumsArr.size() == 0)
		{
			albumsArr.add("ITEM NOT FOUND.");
		}
		
		return albumsArr;
	}
	
	
	/** @pre Input != null */
	public ArrayList<String> albumByTitle(String title)
	{
		// Find the item(s), store into a String array to be printed
		ArrayList<String> albumsArr = new ArrayList<String>();
		
		for (Album album : albums)
		{
			if (album.getTitle().equals(title))
			{
				// Format: "Album by Artist, Genre, Year"
				albumsArr.add(title + " by " + album.getArtist() + ", " +
						album.getGenre() + ", " + album.getYear());
				albumsArr.add("Songs: ");
				
				for (Song song : album.getSongs())
				{
					albumsArr.add(song.getTitle());
				}
			}
		}

		if (albumsArr.size() == 0)
		{
			albumsArr.add("ITEM NOT FOUND.");
		}
		
		return albumsArr;
	}

	
	/** @pre Input != null */
	public ArrayList<String> albumByArtist(String artist)
	{
		// Find the item(s), store into a String array to be printed
		ArrayList<String> albumsArr = new ArrayList<String>();
		
		for (Album album : albums)
		{
			if (album.getArtist().equals(artist))
			{
				// Format: "Album by Artist, Genre, Year"
				albumsArr.add(album.getTitle() + " by " + artist + ", " +
						album.getGenre() + ", " + album.getYear());
				albumsArr.add("Songs: ");
				
				for (Song song : album.getSongs())
					albumsArr.add(song.getTitle());
			}
		}

		if (albumsArr.size() == 0)
		{
			albumsArr.add("ITEM NOT FOUND.");
		}
		
		return albumsArr;
	}

	
	/** @pre Input != null */
	public ArrayList<String> playlistByTitle(String title)
	{
		// Find the item(s), store into a String array to be printed
		ArrayList<String> playlistArr = new ArrayList<String>();
		
		for (PlayList playlist : playlists)
		{
			if (playlist.getName().equals(title))
			{
				playlistArr.add("Playlist: " + title);
				
				// Format: "Title by Artist in Album"
				for (Song song : playlist.getSongs())
					playlistArr.add(song.getTitle() + " by " + song.getArtist());
			}
		}

		if (playlistArr.size() == 0)
		{
			playlistArr.add("ITEM NOT FOUND.");
		}
		
		return playlistArr;
	}

	
	// SEARCHERS (LIST EDITION)
	public String[] getSongTitles()
	{
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> titlesArr = new HashSet<>();
		
		for (Song song : songs)
			titlesArr.add(song.getTitle());
		String arr[] = new String[titlesArr.size()];

		return titlesArr.toArray(arr);
	}

	
	public String[] getArtists()
	{
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> artistsArr = new HashSet<>();
		
		for (Song song : songs)
			artistsArr.add(song.getArtist());
		String arr[] = new String[artistsArr.size()];

		return artistsArr.toArray(arr);
	}
	
	
	public String[] getAlbums()
	{
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> albumsArr = new HashSet<>();
		
		for (Album album : albums)
			albumsArr.add(album.getTitle());
		String arr[] = new String[albumsArr.size()];

		return albumsArr.toArray(arr);
	}
	
	
	public String[] getPlaylistNames()
	{
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> playlistsArr = new HashSet<>();
		
		for (PlayList playlist : playlists)
		{
			
			if (playlist.getName().contains("GENRE "))
			{
				if (playlist.getSongs().size() >= 10)
					playlistsArr.add(playlist.getName());
			}
			else
			{
				playlistsArr.add(playlist.getName());
			}
		}
		String arr[] = new String[playlistsArr.size()];

		return playlistsArr.toArray(arr);
	}

	
	public String[] getFavorites()
	{
		ArrayList<Song> favorites = getPlaylist("favorites").getSongs();
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> favoritesArr = new HashSet<>();
		
		for (Song song : favorites)
		{
				favoritesArr.add(song.getTitle());
		}
		String arr[] = new String[favoritesArr.size()];

		return favoritesArr.toArray(arr);
	}
	
	
	public String[] getRecents()
	{
		ArrayList<Song> recents = getPlaylist("recents").getSongs();
		// Find the item(s), store into a no-duplicates Hash to be printed
		String[] recentsArr = new String[10];
		int i = 0;
		
		// Now store those first 10 into an array for printing
		for (Song song : recents)
		{
			recentsArr[i] = song.getTitle();
			i++;
		}

		return recentsArr;
	}
	
	
	private void updateFrequents()
	{
		PlayList frequent = getPlaylist("frequents");
		String curr = "";
		int dashIndex = 0;
		int byIndex = 0;
		int i = 0;
		
		// First, clear all songs
		frequent.clearSongs();
		
		// Now sort and add the first 10
		Collections.sort(frequentsCount);
		while ((i < 10) && (i < frequentsCount.size()))
		{
			curr = frequentsCount.get(i);
			dashIndex = curr.indexOf(" - ");
			byIndex = curr.indexOf(" by ");
			addToPlaylist("frequents", curr.substring(dashIndex+3,byIndex),
					curr.substring(byIndex+4));
			i++;
		}
	}
	
	
	public String[] getFrequents()
	{
		// First, update our frequents based on the play count
		updateFrequents();
		
		ArrayList<Song> frequent = getPlaylist("frequents").getSongs();
		String[] frequentsArr = new String[10];
		int i = 0;
		
		// Now store those first 10 into an array for printing
		for (Song song : frequent)
		{
			frequentsArr[i] = song.getTitle();
			i++;
		}

		return frequentsArr;
	}
	
	
	public String[] getTopRated()
	{
		ArrayList<Song> top = getPlaylist("top_rated").getSongs();
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> topsArr = new HashSet<>();
		
		for (Song song : top)
		{
			topsArr.add(song.getTitle());
		}
		String arr[] = new String[topsArr.size()];

		return topsArr.toArray(arr);
	}
	
	
	// PLAYLIST METHODS
	private PlayList getPlaylist(String name)
	{
		for (PlayList playlist : playlists)
		{
			if (playlist.getName().equals(name))
				return playlist;
		}

		return null;
	}

	
	/** @pre Input != null */
	public void createPlaylist(String name)
	{
		playlists.add(new PlayList(name));
	}

	
	/** @pre Inputs != null */
	public void addToPlaylist(String pName, String title, String artist)
	{
		// If the Song exists, find and add it to the playlist
		if (isInLibrarySong(title, artist))
		{
			// If the playlist doesn't exist, create that first
			if (!isInLibraryPlaylist(pName))
				createPlaylist(pName);
			
			for (Song song : songs)
			{
				if (title.equals(song.getTitle()) && artist.equals(song.getArtist()))
				{
					if (!(getPlaylist(pName).getSongsNONCOPY().contains(song)))
						getPlaylist(pName).addSong(song);
				}
			}
		}
	}

	
	/** @pre Inputs != null */
	public void removeFromPlaylist(String pName, String title, String artist)
	{
		// If the Song exists, find and remove from the playlist
		if (isInLibrarySong(title, artist))
		{
			// If the playlist doesn't exist, exist
			if (!isInLibraryPlaylist(pName))
				return;
			
			for (Song s : songs)
			{
				if (title.equals(s.getTitle()) && artist.equals(s.getArtist()))
					getPlaylist(pName).removeSong(s);
			}
		}
	}


	// HELPERS
	private boolean isInLibrarySong(String title, String artist)
	{
		for (Song song : songs)
		{
			if (title.equals(song.getTitle()) && artist.equals(song.getArtist()))
				return true;
		}

		return false;
	}
	
	
	private boolean isInLibraryAlbum(String title, String artist)
	{
		for (Album album : albums)
		{
			if (title.equals(album.getTitle()) && artist.equals(album.getArtist()))
				return true;
		}

		return false;
	}

	
	// only for PlayList name
	private boolean isInLibraryPlaylist(String name)
	{
		for (PlayList playlist : playlists)
		{
			if (playlist.getName().equals(name))
				return true;
		}

		return false;
	}

	
	private boolean isInStoreSong(String title, String artist)
	{
		for (Album album : store.getAlbums())
		{
			for (Song song : album.getSongs())
			{
				if (title.equals(song.getTitle()) && artist.equals(song.getArtist()))
					return true;
			}
		}

		return false;
	}

	
	private boolean isInStoreAlbum(String title, String artist)
	{
		for (Album album : store.getAlbums())
		{
			if (title.equals(album.getTitle()) && artist.equals(album.getArtist()))
				return true;
		}

		return false;
	}
	
	
	private int getSize(String title, String artist)
	{
		for (Album album : albums)
		{
			if (title.equals(album.getTitle()) && artist.equals(album.getArtist()))
				return album.getSongs().size();
		}
		return 0;
	}
}