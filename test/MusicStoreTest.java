package test;

// MusicStore.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import model.MusicStore;

class MusicStoreTest
{
	// PRIVATE INSTANCE VARIABLE
	private MusicStore ms;
	
	
	// MAIN
	public void main()
	{
		@SuppressWarnings("unused")
		MusicStoreTest tester = new MusicStoreTest();
	}
	
	
	// CONSTRUCTOR
	public MusicStoreTest()
	{
		this.ms = new MusicStore();
	}
	
	
	// TESTS!
	
	// NOTE: We are not testing getAlbums() as that is a helper for
	//       LibraryModel methods and will be covered there.
	
	@Test
	void testSongsByTitle()
	{
		ArrayList<String> songs = ms.songsByTitle("You Ain't Alone");
		
		assertTrue(songs.size() == 1);
		assertTrue(songs.get(0).contentEquals("You Ain't Alone by Alabama Shakes in Boys & Girls"));
	}
	
	
	@Test
	void testSongsByArtist()
	{
		ArrayList<String> songs = ms.songsByArtist("Norah Jones");
		
		assertEquals(7, songs.size());
		
		assertTrue(songs.get(0).contentEquals("My Heart Is Full by Norah Jones in Begin Again"));
		assertTrue(songs.get(1).contentEquals("Begin Again by Norah Jones in Begin Again"));
		assertTrue(songs.get(2).contentEquals("It Was You by Norah Jones in Begin Again"));
		assertTrue(songs.get(3).contentEquals("A Song with No Name by Norah Jones in Begin Again"));
		assertTrue(songs.get(4).contentEquals("Uh Oh by Norah Jones in Begin Again"));
		assertTrue(songs.get(5).contentEquals("Wintertime by Norah Jones in Begin Again"));
		assertTrue(songs.get(6).contentEquals("Just a Little Bit by Norah Jones in Begin Again"));
	}
	
	
	@Test
	void testAlbumByTitle()
	{
		ArrayList<String> albums = ms.albumByTitle("Sigh No More");
		
		assertEquals(14, albums.size());
		
		assertTrue(albums.get(0).contentEquals("Sigh No More by Mumford & Sons, Alternative, 2009"));
		assertTrue(albums.get(1).contentEquals("Songs: "));
		assertTrue(albums.get(2).contentEquals("Sigh No More"));
		assertTrue(albums.get(3).contentEquals("The Cave"));
		assertTrue(albums.get(4).contentEquals("Winter Winds"));
		assertTrue(albums.get(5).contentEquals("Roll Away Your Stone"));
		assertTrue(albums.get(6).contentEquals("White Blank Page"));
		assertTrue(albums.get(7).contentEquals("I Gave You All"));
		assertTrue(albums.get(8).contentEquals("Little Lion Man"));
		assertTrue(albums.get(9).contentEquals("Timshel"));
		assertTrue(albums.get(10).contentEquals("Thistle & Weeds"));
		assertTrue(albums.get(11).contentEquals("Awake My Soul"));
		assertTrue(albums.get(12).contentEquals("Dust Bowl Dance"));
		assertTrue(albums.get(13).contentEquals("After the Storm"));
	}
	
	
	@Test
	void testAlbumByArtist()
	{
		ArrayList<String> albums = ms.albumByArtist("Coldplay");
		
		assertEquals(13, albums.size());
		
		assertTrue(albums.get(0).contentEquals("A Rush of Blood to the Head by Coldplay, Alternative, 2002"));
		assertTrue(albums.get(1).contentEquals("Songs: "));
		assertTrue(albums.get(2).contentEquals("Politik"));
		assertTrue(albums.get(3).contentEquals("In My Place"));
		assertTrue(albums.get(4).contentEquals("God Put a Smile Upon Your Face"));
		assertTrue(albums.get(5).contentEquals("The Scientist"));
		assertTrue(albums.get(6).contentEquals("Clocks"));
		assertTrue(albums.get(7).contentEquals("Daylight"));
		assertTrue(albums.get(8).contentEquals("Green Eyes"));
		assertTrue(albums.get(9).contentEquals("Warning Sign"));
		assertTrue(albums.get(10).contentEquals("A Whisper"));
		assertTrue(albums.get(11).contentEquals("A Rush of Blood to the Head"));
		assertTrue(albums.get(12).contentEquals("Amsterdam"));
	}
}