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
	
	
	// CONSTRUCTOR
	public LibraryModel(MusicStore ms)
	{
		store = ms;
		songs = new ArrayList<>();
		albums = new ArrayList<>();
		playlists = new ArrayList<>();
	}
	
	
	// ADDERS
	public void addSong(String title, String artist)
	{
		if ( (!isInLibrarySong(title, artist)) && isInStoreSong(title, artist) ) 
		{
			for (Album album : store.getAlbums())
			{
				for (Song song : album.getSongs())
				{
					if (title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
					{	
						songs.add(song.songCpy());
						return;
					}
				}
			}
		}
	}

	
	public void addAlbum(String title, String artist)
	{
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

	
	// SEARCHERS
	public ArrayList<String> songsByTitle(String title)
	{
		ArrayList<String> songsArr = new ArrayList<String>();
		
		for (Song song : this.songs)
		{
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

	
	public ArrayList<String> songsByArtist(String artist)
	{
		ArrayList<String> songsArr = new ArrayList<String>();
		
		for (Song song : this.songs)
		{
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

	
	public ArrayList<String> albumByTitle(String title)
	{
		ArrayList<String> albumsArr = new ArrayList<String>();
		
		for (Album album : albums)
		{
			if (album.getTitle().equals(title))
			{
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

	
	public ArrayList<String> albumByArtist(String artist)
	{
		ArrayList<String> albumsArr = new ArrayList<String>();
		
		for (Album album : albums)
		{
			if (album.getArtist().equals(artist))
			{
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

	
	public ArrayList<String> playlistByTitle(String title)
	{
		ArrayList<String> playlistArr = new ArrayList<String>();
		
		for (PlayList playlist : playlists)
		{
			if (playlist.getName().equals(title))
			{
				playlistArr.add("Playlist: " + title);
				for (Song song : playlist.getSongs())
					playlistArr.add(song.getTitle() + " by " + song.getArtist());

				return playlistArr;
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
		Set<String> titlesArr = new HashSet<>();
		for (Song song : songs)
			titlesArr.add(song.getTitle());
		String arr[] = new String[titlesArr.size()];

		return titlesArr.toArray(arr);
	}

	
	public String[] getArtists()
	{
		Set<String> artistsArr = new HashSet<>();
		for (Song song : songs)
			artistsArr.add(song.getArtist());
		String arr[] = new String[artistsArr.size()];

		return artistsArr.toArray(arr);
	}
	
	
	public String[] getAlbums()
	{
		Set<String> albumsArr = new HashSet<>();
		for (Album album : albums)
			albumsArr.add(album.getTitle());
		String arr[] = new String[albumsArr.size()];

		return albumsArr.toArray(arr);
	}
	
	
	public String[] getPlaylistNames()
	{
		Set<String> playlistsArr = new HashSet<>();
		
		for (PlayList playlist : playlists)
			playlistsArr.add(playlist.getName());
		String arr[] = new String[playlistsArr.size()];

		return playlistsArr.toArray(arr);
	}

	
	public String[] getFavorites()
	{
		Set<String> favoritesArr = new HashSet<>();
		for (Song song : songs)
		{
			if (song.getRating() == 5)
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

	
	public void createPlaylist(String name)
	{
		playlists.add(new PlayList(name));
	}

	
	public void addToPlaylist(String pName, String title, String artist)
	{
		if (isInLibrarySong(title, artist))
		{
			if (!isInLibraryPlaylist(pName))
				createPlaylist(pName);
			for (Song s : songs)
			{
				if (title.equals(s.getTitle()) && artist.equals(s.getArtist()))
					getPlaylist(pName).addSong(s);
			}
		}
	}

	
	public void removeFromPlaylist(String name, Song song)
	{
		if (isInLibrarySong(song.getTitle(), song.getArtist()) && isInLibraryPlaylist(name))
			getPlaylist(name).removeSong(song);
	}


	// HELPERS
	private boolean isInLibrarySong(String title, String artist)
	{
		for (Song s : songs)
		{
			if (title.equals(s.getTitle()) && artist.equals(s.getArtist()))
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
			for (Song s : album.getSongs())
			{
				if (title.equals(s.getTitle()) && artist.equals(s.getArtist()))
					return true;
			}
		}

		return false;
	}

	
	private boolean isInStoreAlbum(String title, String artist)
	{
		for (Album a : store.getAlbums())
		{
			if (title.equals(a.getTitle()) && artist.equals(a.getArtist()))
				return true;
		}

		return false;
	}
}