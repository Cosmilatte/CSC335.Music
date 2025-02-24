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
	private LibraryModel lm;
	private MusicStore ms;
	
	
	// MAIN
	public void main()
	{
		@SuppressWarnings("unused")
		LibraryModelTest tester = new LibraryModelTest();
	}
	
	
	// CONSTRUCTOR
	public LibraryModelTest()
	{
		this.ms = new MusicStore();
		this.lm = new LibraryModel(ms);
	}
	
	
	// TESTS!
	
	// NOTE: We do not have methods for testing the Adders as these
	//       will be proven functional when we use the search methods.
	
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
	void testPlaylistByTitle()
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
	void testAlbums()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	void testGetFavorite()
	{
		fail("Not yet implemented");
	}
}