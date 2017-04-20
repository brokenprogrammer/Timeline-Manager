package com.timelinemanager.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
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
	private static PrintStream p;
	
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
	 * TODO: Change parameters & implement the method.
	 * 
	 * @param pathname
	 * 
	 */
	public static void save(String pathname){
		FileOutputStream out;
		try {
			out = new FileOutputStream(pathname);
			p = new PrintStream(out);
			for (String s : events) {
				p.append(s);
				p.append("\n");
			}
		} catch (Exception e) {
			System.err.println("Error writing to file");
		}
	}
}
