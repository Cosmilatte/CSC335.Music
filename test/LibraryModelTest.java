package test;

// LibraryModelTest.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.LibraryModel;
import model.MusicStore;

class LibraryModelTest
{
	// PRIVATE INSTANCE VARIABLE
	private LibraryModel l;
	private MusicStore m;
	
	
	// MAIN
	public void main()
	{
		@SuppressWarnings("unused")
		LibraryModelTest tester = new LibraryModelTest();
	}
	
	
	// CONSTRUCTOR
	public LibraryModelTest()
	{
		this.m = new MusicStore();
		this.l = new LibraryModel(m);
	}
	
	
	// TESTS!
	@Test
	void testGetAlbums()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testGetPlaylists()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testAddSong()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testAddAlbum()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testSongsByTitle()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testSongsByArtist()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testAlbumByTitle()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testAlbumByArtist()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testGetPlaylist()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testGetSongTitles()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testArtists()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testGetFavorite()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testCreatePlaylist()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testAddToPlaylist()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testRemoveFromPlaylist()
	{
		fail("Not yet implemented");
	}
}