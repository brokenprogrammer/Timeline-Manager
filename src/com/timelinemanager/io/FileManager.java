package com.timelinemanager.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * FileManager class handle saving and loading timeline files.
 * 
 * @author 
 * @version 0.00.00
 * @name FileManager.java
 */
public class FileManager {
	
	private FileManager(){}
	
	/**
	 * Loads a timeline file from the system returning its
	 * string content.
	 * 
	 * @param pathname - Path to the timeline file to load.
	 */
	public static String load(String pathname){
		BufferedReader in;
		String read = "";
		
		try {
			in = new BufferedReader(new FileReader(pathname));
			int c = 0;
			while ((c = in.read()) != -1) {
				read += (char) c;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return read;
	}
	
	/**
	 * Saves the target file to the fiesystem.
	 * 
	 * @param content - Content to save into the file.
	 * @param pathname - Path and name of the file to save.
	 */
	public static void save(String content, String pathname){
		PrintWriter out;
		try {
			out = new PrintWriter(pathname);
			out.write(content);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
