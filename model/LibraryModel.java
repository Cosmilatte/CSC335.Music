package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// LibraryModel.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

public class LibraryModel
{
	// INSTANCE VARIABLES
	private MusicStore store;
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
	private ArrayList<PlayList> playlists;
	private ArrayList<Song> favorites;
	
	
	// CONSTRUCTOR
	/** @pre Input != null */
	public LibraryModel(MusicStore ms)
	{
		store = ms;
		songs = new ArrayList<>();
		albums = new ArrayList<>();
		playlists = new ArrayList<>();
		favorites = new ArrayList<>();
	}
	
	
	// MISCELLANEOUS (RATING)
	/** @pre Inputs != null, int >= 1, int <= 5 */
	public void rateSong(String title, String artist, int r)
	{
		// If the Song is in the Library and store, find and rate it
		if ( isInLibrarySong(title, artist) && isInStoreSong(title, artist) ) 
		{
			for (Song song : songs)
			{
				if (title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
				{
					song.setRating(r);
					if (r == 5)
						favorites.add(song);
				}
			}
		}
	}
	
	
	// ADDERS
	/** @pre Inputs != null */
	public void addSong(String title, String artist)
	{
		// If the Song is not in the Library and is in store, find and add it
		if ( (!isInLibrarySong(title, artist)) && isInStoreSong(title, artist) ) 
		{
			for (Album album : store.getAlbums())
			{
				for (Song song : album.getSongs())
				{
					if (title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
						songs.add(song.songCpy());
				}
			}
		}
	}

	
	/** @pre Inputs != null */
	public void addAlbum(String title, String artist)
	{
		// If the Song is not in the Library and is in store, find and add it
		if (!isInLibraryAlbum(title, artist) && isInStoreAlbum(title, artist)) 
		{
			for (Album album : store.getAlbums())
			{
				if (title.contentEquals(album.getTitle()) && artist.contentEquals(album.getArtist()))
				{
					albums.add(album);
					for (Song song : album.getSongs())
						songs.add(song.songCpy());
				}
			}
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
					favorites.add(song);
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
			playlistsArr.add(playlist.getName());
		String arr[] = new String[playlistsArr.size()];

		return playlistsArr.toArray(arr);
	}

	
	public String[] getFavorites()
	{
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> favoritesArr = new HashSet<>();
		
		for (Song song : favorites)
		{
				favoritesArr.add(song.getTitle());
		}
		String arr[] = new String[favoritesArr.size()];

		return favoritesArr.toArray(arr);
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
			
			for (Song s : songs)
			{
				if (title.equals(s.getTitle()) && artist.equals(s.getArtist()))
					getPlaylist(pName).addSong(s);
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
}