package test;

// SongTest.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Song;

public class SongTest
{
	// PRIVATE INSTANCE VARIABLE
	private Song s;
	
	
	// MAIN
	public void main()
	{
		@SuppressWarnings("unused")
		SongTest tester = new SongTest();
	}
	
	
	// CONSTRUCTOR
	public SongTest()
	{
		this.s = new Song("Bitter Medicine", "The Crane Wives", "Beyond Beyond Beyond");
	}
	
	
	// TESTS!
	@Test
	void testGetters()
	{
		assertTrue(s.getTitle().compareTo("Bitter Medicine") == 0);
		assertFalse(s.getTitle().compareTo("Scars") == 0);
	}
	
	
	@Test
	void testGetArtist()
	{
		assertTrue(s.getArtist().compareTo("The Crane Wives") == 0);
		assertFalse(s.getArtist().compareTo("The Oh Hellos") == 0);
	}
	
	
	@Test
	void testSetRating()
	{
		assertTrue(s.setRating(4) == 0);
		assertTrue(s.setRating(6) == 1);
	}
	
	
	@Test
	void testGetRating()
	{
		s.setRating(4);
		
		assertTrue(s.getRating() == 4);
		assertFalse(s.getRating() == 3);
	}
	
	
	@Test
	void testgetAlbum()
	{
		assertTrue(s.getAlbum().compareTo("Beyond Beyond Beyond") == 0);
		assertFalse(s.getAlbum().compareTo("Coyote Stories") == 0);
	}
	
	
	@Test
	void testSongCpy()
	{
		assertTrue(s.songCpy().getTitle().compareTo("Bitter Medicine") == 0);
	}
}
