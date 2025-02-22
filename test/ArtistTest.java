package test;

// ArtistTest.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Artist;

class ArtistTest
{
	// PRIVATE INSTANCE VARIABLE
	private Artist ar;
	
	
	// MAIN
	public void main()
	{
		@SuppressWarnings("unused")
		ArtistTest tester = new ArtistTest();
	}
	
	
	// CONSTRUCTOR
	public ArtistTest()
	{
		this.ar = new Artist("The Crane Wives");
	}
	
	
	// TESTS!
	@Test
	void test()
	{
		fail("Not yet implemented");
	}
}
