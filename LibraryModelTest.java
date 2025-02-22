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
	void test()
	{
		fail("Not yet implemented");
	}
}