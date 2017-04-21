package com.timelinemanager.io;

import java.util.ArrayList;

import com.timelinemanager.Entity.Timeline;

/**
 * TimelineConverter class to handle converting a timeline object
 * together with its content to text format so it later can be saved
 * to a text file.
 * 
 * @author 
 * @version 0.00.00
 * @name TimelineConverter.java
 */
public class TimelineConverter {
	
	private ArrayList<String> events;
	
	/**
	 * Converts a timeline to string format suitable for saving 
	 * to a text file.
	 * 
	 * @param t - Timeline object to be converted to a string.
	 * @return A string representing a timeline object.
	 */
	public static String convert(Timeline t) {
		return t.toString();
	}
	
	/**
	 * Converts a string into a timeline object.
	 * 
	 * @param s - String to be converted.
	 * @return A timeline populated with data from the string.
	 */
	public static Timeline convert(String s) {
		Timeline t = new Timeline();
		//TODO: Populate timeline depending on content of String s.
		return t;
	}

	public TimelineConverter(Timeline t) {
		events.add(t.getTitle());
		events.add("\n");
		//events.addAll(t.outputdata());
	}
	
	public void addInformation(Timeline t){
		events.add(t.getTitle());
		events.add("\n");
		//events.addAll(t.outputdata());
	}
}
