package com.timelinemanager.io;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for FileManager.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name FileManagerTest.java
 */
public class FileManagerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.timelinemanager.io.FileManager#load(java.lang.String)}.
	 */
	@Test
	public final void testLoad() {
		String s = "Dummy String adsad \nTesting    ";
		FileManager.save(s, "testingString.txt");
		
		//Test so the read content form the saved file is what we saved.
		assertEquals("The saved file content matches with the read.", s, FileManager.load("testingString.txt"));
		
		//Delete the created text file again.
		try {
			File f = new File("testingString.txt");
			
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.timelinemanager.io.FileManager#save(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testSave() {
		String s = "Dummy String adsad \nTesting    ";
		FileManager.save(s, "myDummyString.txt");
		
		BufferedReader in;
		String read = "";
		
		try {
			in = new BufferedReader(new FileReader("myDummyString.txt"));
			int c = 0;
			while ((c = in.read()) != -1) {
				read += (char) c;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Test so the read content form the saved file is what we saved.
		assertEquals("The saved file content matches with the read.", s, read);
		
		//Delete the created text file again.
		try {
			File f = new File("myDummyString.txt");
			
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
