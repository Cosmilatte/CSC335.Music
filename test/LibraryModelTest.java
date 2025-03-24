package test;

// LibraryModelTest.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: To test all the accessible methods of the Library

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import model.LibraryModel;
import model.MusicStore;

class LibraryModelTest
{
	// PRIVATE INSTANCE VARIABLE
	private LibraryModel lm;
	private MusicStore ms;
	
	
	// CONSTRUCTOR
	public LibraryModelTest()
	{
		// The Store and Library
		this.ms = new MusicStore();
		this.lm = new LibraryModel(ms);
		
		// Adders
		lm.addAlbum("19", "Adele");
		lm.addAlbum("21", "Adele");
		lm.addAlbum("Fight for Your Mind", "Ben Harper");
		lm.addSong("Rise to the Sun", "Alabama Shakes");
		lm.addSong("Hang Loose", "Alabama Shakes");
		
		// Playlist
		lm.createPlaylist("Some songs I know");
		lm.addToPlaylist("Some songs I know", "Rise to the Sun", "Alabama Shakes");
		lm.addToPlaylist("Some songs I know", "Chasing Pavements", "Adele");
		lm.addToPlaylist("Some songs I know", "Set Fire to the Rain", "Adele");
		
		lm.addToPlaylist("Some songs I know", "Hang Loose", "Alabama Shakes");
		lm.removeFromPlaylist("Some songs I know", "Hang Loose", "Alabama Shakes");
		
		// Rating + favorite
		lm.rateSong("Rise to the Sun", "Alabama Shakes", 5);
		lm.rateSong("Cold Shoulder", "Adele", 5);
		lm.rateSong("Rumour Has It", "Adele", 4);
		lm.rateSong("Give a Man a Home", "Ben Harper", 5);
		lm.addFavorite("Burn One Down", "Ben Harper");
		
		// Plays
		lm.playSong("Don't You Remember", "Adele");
		lm.playSong("Don't You Remember", "Adele");
		lm.playSong("Don't You Remember", "Adele");
		lm.playSong("One and Only", "Adele");
		lm.playSong("Rolling in the Deep", "Adele");
		lm.playSong("Rolling in the Deep", "Adele");
		lm.playSong("Rolling in the Deep", "Adele");
		lm.playSong("Rolling in the Deep", "Adele");
		lm.playSong("Rolling in the Deep", "Adele");
	}
	
	
	// TESTS!
	// We do not have methods for testing the Adders as these
	// will be proven functional when we use the search methods.
	@Test
	void testSongsByTitle()
	{
		ArrayList<String> songsArr = lm.songsByTitle("Make You Feel My Love");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("Make You Feel My Love by Adele in 19"));
	}
	
	
	@Test
	void testSongsByTitleEMPTY()
	{
		ArrayList<String> songsArr = lm.songsByTitle("Reciever of Wreck");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testSongsByTitleRM()
	{
		lm.addSong("I Found You", "Alabama Shakes");
		lm.removeSong("I Found You", "Alabama Shakes");
		ArrayList<String> songsArr = lm.songsByTitle("I Found You");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testSongsByArtist()
	{
		ArrayList<String> songsArr = lm.songsByArtist("Adele");
		String alOne = " by Adele in 19";
		String alTwo = " by Adele in 21";
		
		assertTrue(songsArr.size() == 24);
		assertTrue(songsArr.get(0).contentEquals("Daydreamer" + alOne));
		assertTrue(songsArr.get(1).contentEquals("Best for Last" + alOne));
		assertTrue(songsArr.get(2).contentEquals("Chasing Pavements" + alOne));
		assertTrue(songsArr.get(3).contentEquals("Cold Shoulder" + alOne));
		assertTrue(songsArr.get(4).contentEquals("Crazy for You" + alOne));
		assertTrue(songsArr.get(5).contentEquals("Melt My Heart to Stone" + alOne));
		assertTrue(songsArr.get(6).contentEquals("First Love" + alOne));
		assertTrue(songsArr.get(7).contentEquals("Right as Rain" + alOne));
		assertTrue(songsArr.get(8).contentEquals("Make You Feel My Love" + alOne));
		assertTrue(songsArr.get(9).contentEquals("My Same" + alOne));
		assertTrue(songsArr.get(10).contentEquals("Tired" + alOne));
		assertTrue(songsArr.get(11).contentEquals("Hometown Glory" + alOne));
		
		assertTrue(songsArr.get(12).contentEquals("Rolling in the Deep" + alTwo));
		assertTrue(songsArr.get(13).contentEquals("Rumour Has It" + alTwo));
		assertTrue(songsArr.get(14).contentEquals("Turning Tables" + alTwo));
		assertTrue(songsArr.get(15).contentEquals("Don't You Remember" + alTwo));
		assertTrue(songsArr.get(16).contentEquals("Set Fire to the Rain" + alTwo));
		assertTrue(songsArr.get(17).contentEquals("He Won't Go" + alTwo));
		assertTrue(songsArr.get(18).contentEquals("Take It All" + alTwo));
		assertTrue(songsArr.get(19).contentEquals("I'll Be Waiting" + alTwo));
		assertTrue(songsArr.get(20).contentEquals("One and Only" + alTwo));
		assertTrue(songsArr.get(21).contentEquals("Lovesong" + alTwo));
		assertTrue(songsArr.get(22).contentEquals("Someone Like You" + alTwo));
		assertTrue(songsArr.get(23).contentEquals("I Found a Boy" + alTwo));
	}
	
	
	@Test
	void testSongsByArtistEMPTY()
	{
		ArrayList<String> songsArr = lm.songsByArtist("Pigpen Theater Co.");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testSongsByGenre()
	{
		lm.addSong("City Of Angels", "Ozomatli");
		lm.addSong("Magnolia Soul", "Ozomatli");
		lm.addSong("La Temperatura", "Ozomatli");
		ArrayList<String> songsArr = lm.songsByGenre("Rock");
		
		assertEquals(3, songsArr.size());
		
		assertTrue(songsArr.get(0).contentEquals("City Of Angels by Ozomatli in Don't Mess With the Dragon"));
		assertTrue(songsArr.get(1).contentEquals("Magnolia Soul by Ozomatli in Don't Mess With the Dragon"));
		assertTrue(songsArr.get(2).contentEquals("La Temperatura by Ozomatli in Don't Mess With the Dragon"));
	}
	
	
	@Test
	void testAlbumByTitle()
	{
		ArrayList<String> albumsArr = lm.albumByTitle("Fight for Your Mind");
		
		assertTrue(albumsArr.size() == 15);
		assertTrue(albumsArr.get(0).contentEquals("Fight for Your Mind by Ben Harper, Alternative, 1995"));
		assertTrue(albumsArr.get(1).contentEquals("Songs: "));
		assertTrue(albumsArr.get(2).contentEquals("Oppression"));
		assertTrue(albumsArr.get(3).contentEquals("Ground on Down"));
		assertTrue(albumsArr.get(4).contentEquals("Another Lonely Day"));
		assertTrue(albumsArr.get(5).contentEquals("Gold to Me"));
		assertTrue(albumsArr.get(6).contentEquals("Burn One Down"));
		assertTrue(albumsArr.get(7).contentEquals("Excuse Me Mr."));
		assertTrue(albumsArr.get(8).contentEquals("People Lead"));
		assertTrue(albumsArr.get(9).contentEquals("Fight for Your Mind"));
		assertTrue(albumsArr.get(10).contentEquals("Give a Man a Home"));
		assertTrue(albumsArr.get(11).contentEquals("By My Side"));
		assertTrue(albumsArr.get(12).contentEquals("Power of the Gospel"));
		assertTrue(albumsArr.get(13).contentEquals("God Fearing Man"));
		assertTrue(albumsArr.get(14).contentEquals("One Road to Freedom"));
	}
	
	
	@Test
	void testAlbumByTitleEMPTY()
	{
		ArrayList<String> albumsArr = lm.albumByTitle("The Raven Locks");
		
		assertTrue(albumsArr.size() == 1);
		assertTrue(albumsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testAlbumByTitlePARTIAL()
	{
		// This is to test adding a song + album not causing duplicates
		lm.addSong("I Gave You All", "Mumford & Sons");
		lm.addAlbum("Sigh No More", "Mumford & Sons");
		ArrayList<String> albumsArr = lm.albumByTitle("Sigh No More");
		
		// 14 = Album info + "Songs: " + song list (12 songs)
		assertEquals(14, albumsArr.size());
	}
	
	
	@Test
	void testAlbumByTitleRM()
	{
		lm.addAlbum("Sigh No More", "Mumford & Sons");
		lm.removeAlbum("Sigh No More", "Mumford & Sons");
		ArrayList<String> albumsArr = lm.albumByTitle("Sigh No More");
		
		assertTrue(albumsArr.size() == 1);
		assertTrue(albumsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testAlbumByArtist()
	{
		ArrayList<String> songsArr = lm.albumByArtist("Adele");
		
		assertTrue(songsArr.size() == 28);
		assertTrue(songsArr.get(0).contentEquals("19 by Adele, Pop, 2008"));
		assertTrue(songsArr.get(1).contentEquals("Songs: "));
		assertTrue(songsArr.get(2).contentEquals("Daydreamer"));
		assertTrue(songsArr.get(3).contentEquals("Best for Last"));
		assertTrue(songsArr.get(4).contentEquals("Chasing Pavements"));
		assertTrue(songsArr.get(5).contentEquals("Cold Shoulder"));
		assertTrue(songsArr.get(6).contentEquals("Crazy for You"));
		assertTrue(songsArr.get(7).contentEquals("Melt My Heart to Stone"));
		assertTrue(songsArr.get(8).contentEquals("First Love"));
		assertTrue(songsArr.get(9).contentEquals("Right as Rain"));
		assertTrue(songsArr.get(10).contentEquals("Make You Feel My Love"));
		assertTrue(songsArr.get(11).contentEquals("My Same"));
		assertTrue(songsArr.get(12).contentEquals("Tired"));
		assertTrue(songsArr.get(13).contentEquals("Hometown Glory"));
		
		assertTrue(songsArr.get(14).contentEquals("21 by Adele, Pop, 2011"));
		assertTrue(songsArr.get(15).contentEquals("Songs: "));
		assertTrue(songsArr.get(16).contentEquals("Rolling in the Deep"));
		assertTrue(songsArr.get(17).contentEquals("Rumour Has It"));
		assertTrue(songsArr.get(18).contentEquals("Turning Tables"));
		assertTrue(songsArr.get(19).contentEquals("Don't You Remember"));
		assertTrue(songsArr.get(20).contentEquals("Set Fire to the Rain"));
		assertTrue(songsArr.get(21).contentEquals("He Won't Go"));
		assertTrue(songsArr.get(22).contentEquals("Take It All"));
		assertTrue(songsArr.get(23).contentEquals("I'll Be Waiting"));
		assertTrue(songsArr.get(24).contentEquals("One and Only"));
		assertTrue(songsArr.get(25).contentEquals("Lovesong"));
		assertTrue(songsArr.get(26).contentEquals("Someone Like You"));
		assertTrue(songsArr.get(27).contentEquals("I Found a Boy"));
	}
	
	
	@Test
	void testAlbumByArtistEMPTY()
	{
		ArrayList<String> songsArr = lm.albumByArtist("Louis Armstrong");
		
		assertTrue(songsArr.size() == 1);
		assertTrue(songsArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testAlbumBySongTITLE()
	{
		ArrayList<String> songsArr = lm.albumBySong("Set Fire to the Rain", true);
		
		assertTrue(songsArr.size() == 15);
	}
	

	@Test
	void testAlbumBySongARTIST()
	{
		ArrayList<String> songsArr = lm.albumBySong("Alabama Shakes", false);
		
		assertEquals(15, songsArr.size());
	}
	
	
	@Test
	void testAlbumBySongEMPTY()
	{
		ArrayList<String> songsArr = lm.albumBySong("Louis Armstrong", false);
		
		assertEquals(2, songsArr.size());
		assertTrue(songsArr.get(0).contentEquals("ITEM NOT FOUND."));
		assertTrue(songsArr.get(1).contentEquals("ALBUM(S) CONTAINED IN LIBRARY: NO"));
	}
	
	
	@Test
	void testPlaylistByTitle()
	{
		ArrayList<String> playlistArr = lm.playlistByTitle("Some songs I know");
		assertEquals(4, playlistArr.size());
		
		assertTrue(playlistArr.get(0).contentEquals("Playlist: Some songs I know"));
		assertTrue(playlistArr.get(3).contentEquals("Rise to the Sun by Alabama Shakes"));
		assertTrue(playlistArr.get(2).contentEquals("Chasing Pavements by Adele"));
		assertTrue(playlistArr.get(1).contentEquals("Set Fire to the Rain by Adele"));
	}
	
	
	@Test
	void testPlaylistByTitleEMPTY()
	{
		ArrayList<String> playlistArr = lm.playlistByTitle("SEVERE WEATHER ADVISORY");
		assertEquals(1, playlistArr.size());
		
		assertTrue(playlistArr.get(0).contentEquals("ITEM NOT FOUND."));
	}
	
	
	@Test
	void testPlaylistByTitleSHUFFLE()
	{
		lm.shufflePlaylist("Some songs I know");
		ArrayList<String> playlistArr = lm.playlistByTitle("Some songs I know");
		assertEquals(4, playlistArr.size());
		
		boolean isSame = true;
		
		assertTrue(playlistArr.get(0).contentEquals("Playlist: Some songs I know"));
		
		// Due to the randomness of shuffling;
		// Check if every index is as it was in testPlaylistByTitle()
		// We know the shuffling works if any one of them isn't where they're supposed to be
		if (!playlistArr.get(3).contentEquals("Rise to the Sun by Alabama Shakes"))
			isSame = false;
		if (!playlistArr.get(2).contentEquals("Chasing Pavements by Adele"))
			isSame = false;
		if (!playlistArr.get(1).contentEquals("Set Fire to the Rain by Adele"))
			isSame = false;
		
		assertFalse(isSame);
	}
	
	
	@Test
	void testGetSongTitles()
	{
		String[] songsArr = lm.getSongTitles();
		assertEquals(39, songsArr.length);
	}
	
	
	@Test
	void testGetArtists()
	{
		String[] artistsArr = lm.getArtists();
		assertEquals(3, artistsArr.length);
		
		//for (String s : artistsArr)
			//System.out.println(s);
	}
	
	
	@Test
	void testGetAlbums()
	{
		String[] albumsArr = lm.getAlbums();
		assertEquals(4, albumsArr.length);
	}
	
	
	@Test
	void testGetPlaylistNames()
	{
		String[] playlistsArr = lm.getPlaylistNames();
		
		// Will be 7 due to: favorites, top_rated, frequents, recents, 2 GENREs, made playlist
		assertEquals(7, playlistsArr.length);
	}
	
	
	@Test
	void testGetFavorite()
	{
		String[] favoritesArr = lm.getFavorites();
		assertEquals(4, favoritesArr.length);
	}
	
	
	@Test
	void testGetRecents()
	{
		int count = 0;
		String[] recentsArr = lm.getRecents();
		
		for (String song : recentsArr)
		{
			if (song != null)
				count++;
		}
		
		assertEquals(3, count);
		assertTrue(recentsArr[0].contentEquals("Rolling in the Deep"));
		assertTrue(recentsArr[1].contentEquals("One and Only"));
		assertTrue(recentsArr[2].contentEquals("Don't You Remember"));
	}
	
	
	@Test
	void testGetFrequents()
	{
		int count = 0;
		String[] frequentsArr = lm.getFrequents();
		
		for (String song : frequentsArr)
		{
			if (song != null)
				count++;
		}
		
		assertEquals(3, count);
		assertTrue(frequentsArr[0].contentEquals("Rolling in the Deep"));
		assertTrue(frequentsArr[1].contentEquals("Don't You Remember"));
		assertTrue(frequentsArr[2].contentEquals("One and Only"));
	}
	
	
	@Test
	void testGetTopRated()
	{
		String[] topratedarr = lm.getTopRated();
		assertEquals(4, topratedarr.length);
	}
}