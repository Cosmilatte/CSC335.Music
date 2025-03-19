package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// MusicStore.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: MusicStore is a Class that represents, using an ArrayList
//   to store Albums, a virtual store that Users can take Songs and
//   Albums from to put into their own personal library.

public class MusicStore
{
	// INSTANCE VARIABLES
	private ArrayList<Album> albums;
	
	
	// CONSTRUCTOR
	public MusicStore()
	{
		albums = new ArrayList<>();
		try
		{
			readAlbums();
		}
		
		catch (IOException e)
		{
			System.out.println("Error: This is not a valid input file");
			System.exit(1);
		}
	}
	
	
	// GETTER
	public ArrayList<Album> getAlbums()
	{
		// Clones the item to prevent escaping reference
		ArrayList<Album> albumsCopy = new ArrayList<Album>();
		
		for (Album album : albums)
		{
			Album a2 = new Album(album.getTitle(), album.getArtist(), album.getGenre(), 
					album.getYear());
			
			for (Song song : album.getSongs())
				a2.addSong(song.songCpy());
			
			albumsCopy.add(a2);
		}
		
		return albumsCopy;
	}
	
	
	// SEARCHERS
	/** @pre Input != null */
	public ArrayList<String> songsByTitle(String title)
	{
		// Make a String ArrayList of the requested info for printing
		ArrayList<String> songsArr = new ArrayList<String>();
		
		for (Album album : albums)
		{
			for (Song song : album.getSongs())
			{
				if (song.getTitle().equals(title))
					// Format: "Title by Artist in Album"
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
	public ArrayList<String> songsByArtist(String artist)
	{
		// Make a String ArrayList of the requested info for printing
		ArrayList<String> songsArr = new ArrayList<String>();
		
		for (Album album : albums)
		{
			if (album.getArtist().equals(artist))
			{
				for (Song song : album.getSongs())
				{
					// Format: "Title by Artist in Album"
					songsArr.add(song.getTitle() + " by " + song.getArtist() + " in " +
						song.getAlbum());
				}
			}
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
		// Make a String ArrayList of the requested info for printing
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
		// Make a String ArrayList of the requested info for printing
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

	
	// THE READ-IN
	private void readAlbums() throws IOException
	{
		try {
			String absolutePath = "C:\\Users\\akjon\\300sWorkspace\\CSC335la1\\src\\albums\\";
			BufferedReader titlesReader = new BufferedReader(new FileReader(absolutePath + "albums.txt"));
			String title_artist = titlesReader.readLine();
			
			while (title_artist != null)
			{
				try
				{
					BufferedReader albumReader = new BufferedReader(new FileReader(absolutePath + 
							title_artist.split(",")[0] + "_" + title_artist.split(",")[1] +
							".txt"));
					String[] information = albumReader.readLine().split(",");
					Album curAlbum = new Album(information[0], information[1], information[2], 
							Integer.parseInt(information[3].trim()));
					String songTitle = albumReader.readLine();
					while (songTitle != null)
					{
						curAlbum.addSong(new Song(songTitle, curAlbum.getArtist(), curAlbum.getTitle()));
						songTitle = albumReader.readLine();
						
					}
		
					albumReader.close();
					albums.add(curAlbum);
					title_artist = titlesReader.readLine();
				}

				catch (IOException e)
				{
					System.out.println("Error: This is not a valid input file");
					System.exit(1);
				}
				
			}
	
			titlesReader.close();
		}

		catch (IOException e)
		{
			System.out.println("Error: This is not a valid input file");
			System.exit(1);
		}	
	}
}