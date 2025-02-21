// package model;

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
	// private ArrayList<Artist> artists;
	private ArrayList<Album> albums;
	
	// CONSTRUCTOR
	public MusicStore()
	{
		// When we read in the files, Artists will contain Albums will contain Songs?
		// this.artists = new ArrayList<Artist>();
		albums = new ArrayList<>();
		try {
			readAlbums();
		}
		
		catch (IOException e) {
			System.out.println("Error: This is not a valid input file");
			System.exit(1);
		}

		setStore();
	}
	
	
	// GETTERS+SETTERS
	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void setStore()
	{
		// THIS IS THE CONSTRUCTOR. 
		// PLEASE READ IN THE FILES HERE.
		
		// Albums file format: Album Title,Artist
		// This is the file you get all the other file names from so it must be read in first
		// Perhaps construct Artists here, but don't touch the Albums just yet
		
		// Album file format: Album Title,Artist,Genre,Year \n song \n song etc.
		// This is where you make Albums I think
		// Find the Artist by scrolling the this.artists array you added to already
		// Then add Albums you construct from the following information
	}
	
	
	// MISC. METHODS
	public ArrayList<Song> songsByTitle(String title) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Album album : albums) {
			for (Song song : album.getSongs()) {
				if (song.getTitle().equals(title))
				songs.add(song.songCpy());
			}
		}

		int songIndex = 1;
		System.out.print("Title: " + title);
		for (Song song : songs) {
			System.out.println("\n" + songIndex + ":");
			System.out.println("Artist: " + song.getArtist());
			System.out.println("Album: " + song.getAlbum().getTitle());
			songIndex++;
		}

		return songs;
	}

	public ArrayList<Song> songsByArtist(String artist) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Album album : albums) {
			if (album.getArtist().equals(artist))
				songs.addAll(album.getSongs());
		}

		int artistIndex = 1;
		System.out.print("Artist: " + artist);
		for (Song song : songs) {
			System.out.println("\nSong " + artistIndex + ":");
			System.out.println("Title: " + song.getTitle());
			System.out.println("Album: " + song.getAlbum().getTitle());
			artistIndex++;
		}

		return songs;
	}

	public Album albumByTitle(String title) {
		for (Album album : albums) {
			if (album.getTitle().equals(title)) {
				System.out.println("Album: " + title);
				System.out.println("Artist: " + album.getArtist());
				System.out.println("Genre: " + album.getGenre());
				System.out.println("Year: " + album.getYear());
				System.out.println("Songs: ");
				for (Song song : album.getSongs())
					System.out.println("\t•" + song.getTitle());

				return album.albumCpy();
			}
		}

		return null;
	}

	public ArrayList<Album> albumByArtist(String artist) {
		ArrayList<Album> albums = new ArrayList<>();
		int albumIndex = 1;
		System.out.println("Artist: " + artist);
		for (Album album : this.albums) {
			if (album.getArtist().equals(artist)) {
				albums.add(album.albumCpy());
				System.out.println("Album " + albumIndex + ": " + album.getTitle());
				System.out.println("Genre: " + album.getGenre());
				System.out.println("Year: " + album.getYear());
				System.out.println("Songs: ");
				for (Song song : album.getSongs())
					System.out.println("\t•" + song.getTitle());

				albumIndex++;
			}
		}

		return albums;
	}

	private void readAlbums() throws IOException{
		try {
			BufferedReader titlesReader = new BufferedReader(new FileReader("albums/albums.txt"));
			String title_artist = titlesReader.readLine();
			while (title_artist != null) {
				try {
					BufferedReader albumReader = new BufferedReader(new FileReader("albums/" + title_artist.split(",")[0] + "_" + title_artist.split(",")[1] + ".txt"));
					String[] information = albumReader.readLine().split(",");
					Album curAlbum = new Album(information[0], information[1], information[2], Integer.parseInt(information[3].trim()));
					String songTitle = albumReader.readLine();
					while (songTitle != null) {
						curAlbum.addSong(new Song(songTitle, curAlbum.getArtist(), curAlbum));
						songTitle = albumReader.readLine();
						
					}
		
					albumReader.close();
					albums.add(curAlbum);
					title_artist = titlesReader.readLine();
				}

				catch (IOException e) {
					System.out.println("Error: This is not a valid input file");
					System.exit(1);
				}
				
			}
	
			titlesReader.close();
		}

		catch (IOException e) {
			System.out.println("Error: This is not a valid input file");
			System.exit(1);
		}	
	}
}
