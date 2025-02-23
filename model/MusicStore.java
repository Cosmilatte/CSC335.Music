package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// MusicStore.java
// Created 2 - 15 - 2025
// Authors: Lilian and Lucian
// Purpose: 

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
	
	
	// GETTERS+SETTERS
	public ArrayList<Album> getAlbums()
	{
		ArrayList<Album> albumsCopy = new ArrayList<Album>();
		for (Album album : albums)
		{
			Album a2 = new Album(album.getTitle(), album.getArtist(), album.getGenre(), album.getYear());
			for (Song s : album.getSongs())
				a2.addSong(s);
			
			albumsCopy.add(a2);
		}
		
		return albumsCopy;
	}
	
	
	// MISC. METHODS
	public ArrayList<String> songsByTitle(String title)
	{
		ArrayList<String> songs = new ArrayList<String>();
		for (Album album : albums)
		{
			for (Song song : album.getSongs())
			{
				if (song.getTitle().equals(title))
					songs.add(song.getTitle() + " by " + song.getArtist() + " in " +
							song.getAlbum());
			}
		}

		return songs;
	}

	
	public ArrayList<String> songsByArtist(String artist)
	{
		ArrayList<String> songs = new ArrayList<String>();
		
		for (Album album : albums)
		{
			if (album.getArtist().equals(artist))
			{
				for (Song song : album.getSongs())
				{
					songs.add(song.getTitle() + " by " + song.getArtist() + " in " +
						song.getAlbum());
				}
			}
		}

		return songs;
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

		return albumsArr;
	}

	
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