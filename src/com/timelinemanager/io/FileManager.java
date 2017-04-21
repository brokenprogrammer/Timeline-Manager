package com.timelinemanager.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileManager class handle saving and loading timeline files.
 * 
 * @author 
 * @version 0.00.00
 * @name FileManager.java
 */
public class FileManager {
	
	private static ArrayList<String> events;
	
	private FileManager(){}
	
	/**
	 * Loads a timeline file from the system returning its
	 * string content.
	 * TODO: change return type to String & implement it.
	 * 
	 * @param pathname - Path to the timeline file to load.
	 */
	public static String load(String pathname){
		try {
			File file = new File(pathname);
			Scanner scan = new Scanner(file);
			String newI;
			while (scan.hasNext()) {
				newI = scan.next();
				events.add(newI);
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new String("");
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
