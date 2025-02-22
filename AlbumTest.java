package test;

// AlbumTest.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Album;

class AlbumTest
{
	// PRIVATE INSTANCE VARIABLE
	private Album a;
	
	
	// MAIN
	public void main()
	{
		@SuppressWarnings("unused")
		AlbumTest tester = new AlbumTest();
	}
	
	
	// CONSTRUCTOR
	public AlbumTest()
	{
		this.a = new Album("Beyond Beyond Beyond", "The Crane Wives", "Folk", 2024);
	}
	
	
	// TESTS!
	@Test
	void test()
	{
		fail("Not yet implemented");
	}
}
