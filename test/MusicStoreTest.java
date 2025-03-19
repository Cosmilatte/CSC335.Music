package test;

// MusicStore.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: To test all the accessible methods of the Store

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
	@Test
	void testGetAlbums()
	{
		assertTrue(ms.getAlbums().size() == 15);
	}
	
	
	@Test
	void testSongsByTitle()
	{
		ArrayList<String> songsArr = ms.songsByTitle("You Ain't Alone");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("You Ain't Alone by Alabama Shakes in Boys & Girls"));
	}
	
	
	@Test
	void testSongsByTitleEMPTY()
	{
		ArrayList<String> songsArr = ms.songsByTitle("Eaten Alive By Wolves");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testSongsByArtist()
	{
		ArrayList<String> songsArr = ms.songsByArtist("Norah Jones");
		
		assertEquals(7, songsArr.size());
		
		assertTrue(songsArr.get(0).contentEquals("My Heart Is Full by Norah Jones in Begin Again"));
		assertTrue(songsArr.get(1).contentEquals("Begin Again by Norah Jones in Begin Again"));
		assertTrue(songsArr.get(2).contentEquals("It Was You by Norah Jones in Begin Again"));
		assertTrue(songsArr.get(3).contentEquals("A Song with No Name by Norah Jones in Begin Again"));
		assertTrue(songsArr.get(4).contentEquals("Uh Oh by Norah Jones in Begin Again"));
		assertTrue(songsArr.get(5).contentEquals("Wintertime by Norah Jones in Begin Again"));
		assertTrue(songsArr.get(6).contentEquals("Just a Little Bit by Norah Jones in Begin Again"));
	}
	
	
	@Test
	void testSongsByArtistEMPTY()
	{
		ArrayList<String> songsArr = ms.songsByArtist("Family Crest");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testAlbumByTitle()
	{
		ArrayList<String> albumsArr = ms.albumByTitle("Sigh No More");
		
		assertEquals(14, albumsArr.size());
		
		assertTrue(albumsArr.get(0).contentEquals("Sigh No More by Mumford & Sons, Alternative, 2009"));
		assertTrue(albumsArr.get(1).contentEquals("Songs: "));
		assertTrue(albumsArr.get(2).contentEquals("Sigh No More"));
		assertTrue(albumsArr.get(3).contentEquals("The Cave"));
		assertTrue(albumsArr.get(4).contentEquals("Winter Winds"));
		assertTrue(albumsArr.get(5).contentEquals("Roll Away Your Stone"));
		assertTrue(albumsArr.get(6).contentEquals("White Blank Page"));
		assertTrue(albumsArr.get(7).contentEquals("I Gave You All"));
		assertTrue(albumsArr.get(8).contentEquals("Little Lion Man"));
		assertTrue(albumsArr.get(9).contentEquals("Timshel"));
		assertTrue(albumsArr.get(10).contentEquals("Thistle & Weeds"));
		assertTrue(albumsArr.get(11).contentEquals("Awake My Soul"));
		assertTrue(albumsArr.get(12).contentEquals("Dust Bowl Dance"));
		assertTrue(albumsArr.get(13).contentEquals("After the Storm"));
	}
	
	
	@Test
	void testAlbumByTitleEMPTY()
	{
		ArrayList<String> albumsArr = ms.albumByTitle("Approaching Normal");
		
		assertTrue(albumsArr.size() == 1);
		assertTrue(albumsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testAlbumByArtist()
	{
		ArrayList<String> albumsArr = ms.albumByArtist("Coldplay");
		
		assertEquals(13, albumsArr.size());
		
		assertTrue(albumsArr.get(0).contentEquals("A Rush of Blood to the Head by Coldplay, Alternative, 2002"));
		assertTrue(albumsArr.get(1).contentEquals("Songs: "));
		assertTrue(albumsArr.get(2).contentEquals("Politik"));
		assertTrue(albumsArr.get(3).contentEquals("In My Place"));
		assertTrue(albumsArr.get(4).contentEquals("God Put a Smile Upon Your Face"));
		assertTrue(albumsArr.get(5).contentEquals("The Scientist"));
		assertTrue(albumsArr.get(6).contentEquals("Clocks"));
		assertTrue(albumsArr.get(7).contentEquals("Daylight"));
		assertTrue(albumsArr.get(8).contentEquals("Green Eyes"));
		assertTrue(albumsArr.get(9).contentEquals("Warning Sign"));
		assertTrue(albumsArr.get(10).contentEquals("A Whisper"));
		assertTrue(albumsArr.get(11).contentEquals("A Rush of Blood to the Head"));
		assertTrue(albumsArr.get(12).contentEquals("Amsterdam"));
	}
	
	
	@Test
	void testAlbumByArtistEMPTY()
	{
		ArrayList<String> albumsArr = ms.albumByArtist("U2");
		
		assertTrue(albumsArr.size() == 1);
		assertTrue(albumsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
}