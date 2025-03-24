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
	
	
	// CONSTRUCTOR
	/** @pre Input != null */
	public LibraryModel(MusicStore ms)
	{
		store = ms;
		songs = new ArrayList<>();
		albums = new ArrayList<>();
		playlists = new ArrayList<>();
		
		createPlaylist("favorites");
		createPlaylist("Recently Played");
		createPlaylist("Frequently Played");
		createPlaylist("Top Rated");
	}
	
	
	// MISCELLANEOUS
	/** @pre Inputs != null, int >= 1, int <= 5 */
	public void rateSong(String title, String artist, int r)
	{
		PlayList favorites = getPlaylist("favorites");
		PlayList topRated = getPlaylist("Top Rated");
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
		PlayList recentlyPlayed = getPlaylist("Recently Played");
		// If the Song is in the Library and store, find and play it
		if ( isInLibrarySong(title, artist) && isInStoreSong(title, artist) ) 
		{
			for (Song song : songs)
			{
				if (title.contentEquals(song.getTitle()) && artist.contentEquals(song.getArtist()))
				{
					song.addPlay();
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
	public void shuffleSongs()
	{
		Collections.shuffle(songs);
	}
	
	
	/** @pre Inputs != null */
	public void shufflePlaylist(String title, String artist)
	{
		for (PlayList playlist : playlists)
		{
			if (title.contentEquals(playlist.getName()))
				Collections.shuffle(playlist.getSongsNONCOPY());
		}
	}
	
	
	// ADDERS + REMOVERS
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
							songs.add(song);
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
		int saveS = 0;
		Album curr = null;
		Song first = null;
		
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

			// Now establish the first song of that Album - find where it is in Songs
			first = albums.get(save).getSongs().get(0);
			for (Song song : songs)
			{
				if (first.getTitle().contentEquals(song.getTitle()) && first.getArtist().contentEquals(song.getArtist()))
					break;
				saveS++;
			}
			// Then cut out all the songs from (first song) to (length of album's songs)
			for (int i = saveS; i < albums.get(save).getSongs().size(); i++)
				songs.remove(saveS);
			albums.remove(save);
		}
	}
	
	
	/** @pre Inputs != null */
	public void addFavorite(String title, String artist)
	{
		ArrayList<Song> favorites = getPlaylist("favorites").getSongs();
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
	
	
	public String[] getRecentlyPlayed()
	{
		// TODO: NOT DONE
		ArrayList<Song> recent = getPlaylist("Recently Played").getSongs();
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> recentsArr = new HashSet<>();
		
		for (Song song : recent)
		{
			recentsArr.add(song.getTitle());
		}
		String arr[] = new String[recentsArr.size()];

		return recentsArr.toArray(arr);
	}
	
	
	public String[] getFrequentlyPlayed()
	{
		// TODO: NOT DONE
		ArrayList<Song> frequent = getPlaylist("Frequently Played").getSongs();
		// Find the item(s), store into a no-duplicates Hash to be printed
		Set<String> frequentsArr = new HashSet<>();
		
		for (Song song : frequent)
		{
			frequentsArr.add(song.getTitle());
		}
		String arr[] = new String[frequentsArr.size()];

		return frequentsArr.toArray(arr);
	}
	
	
	public String[] getTopRated()
	{
		ArrayList<Song> top = getPlaylist("Top Rated").getSongs();
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

	/** @pre Inputs != null */
	public ArrayList<String> sortByTitle()
	{
		ArrayList<Song> songList = new ArrayList<>();
		ArrayList<String> songTitles = new ArrayList<>();
		
		// No song added
		if (songs.size() == 0)
			return songTitles;

		songList.add(songs.get(0));
		if (songs.size() > 1)
		{
			if (songs.get(1).getTitle().compareTo(songs.get(0).getTitle()) > 0)
				songList.add(songs.get(1));

			else
				songList.add(0, songs.get(1));
		}

		for (int i = 2; i < songs.size(); i++)
		{
			int j = 0;
			while (songs.get(i).getTitle().compareTo(songList.get(j).getTitle()) > 0 && j < i)
			{
				j++;

				// Prevent out of range
				if (j == songList.size())
					break;
			}

			songList.add(j, songs.get(i));
		}

		for (Song song : songList)
			songTitles.add(song.getTitle() + " / " + song.getArtist() + " / " + song.getRating());

		return songTitles;
	}

	/** @pre Inputs != null */
	public ArrayList<String> sortByArtist()
	{
		ArrayList<Song> songList = new ArrayList<>();
		ArrayList<String> songTitles = new ArrayList<>();
		if (songs.size() == 0)
			return songTitles;

		songList.add(songs.get(0));
		if (songs.size() > 1)
		{
			if (songs.get(1).getArtist().compareTo(songs.get(0).getArtist()) > 0)
				songList.add(songs.get(1));

			else
				songList.add(0, songs.get(1));
		}

		for (int i = 2; i < songs.size(); i++)
		{
			int j = 0;
			while (songs.get(i).getArtist().compareTo(songList.get(j).getArtist()) > 0 && j < i)
			{
				j++;
				if (j == songList.size())
					break;
			}

			songList.add(j, songs.get(i));
		}

		for (Song song : songList)
			songTitles.add(song.getTitle() + " / " + song.getArtist() + " / " + song.getRating());

		return songTitles;
	}

	/** @pre Inputs != null */
	public ArrayList<String> sortByRating()
	{
		ArrayList<Song> songList = new ArrayList<>();
		ArrayList<String> songTitles = new ArrayList<>();
		if (songs.size() == 0)
			return songTitles;

		songList.add(songs.get(0));
		if (songs.size() > 1)
		{
			if (songs.get(1).getRating()> songs.get(0).getRating())
				songList.add(songs.get(1));

			else
				songList.add(0, songs.get(1));
		}

		for (int i = 2; i < songs.size(); i++)
		{
			int j = 0;
			while (songs.get(i).getRating() > songList.get(j).getRating() && j < i)
			{
				j++;
				if (j == songList.size())
					break;
			}

			songList.add(j, songs.get(i));
		}

		for (Song song : songList)
			songTitles.add(song.getTitle() + " / " + song.getArtist() + " / " + song.getRating());

		return songTitles;
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