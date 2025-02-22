package test;

// PlayListTest.java
// Created 2 - 22 - 2025
// Authors: Lilian and Lucian
// Purpose: 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.PlayList;

class PlayListTest
{
	// PRIVATE INSTANCE VARIABLE
	private PlayList p;
	
	
	// MAIN
	public void main()
	{
		@SuppressWarnings("unused")
		PlayListTest tester = new PlayListTest();
	}
	
	
	// CONSTRUCTOR
	public PlayListTest()
	{
		this.p = new PlayList("SEVERE WEATHER ADVISORY");
	}
	
	
	// TESTS!
	@Test
	void test()
	{
		fail("Not yet implemented");
	}
}
