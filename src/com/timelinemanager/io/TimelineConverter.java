package com.timelinemanager.io;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

import com.timelinemanager.Entity.Event;
import com.timelinemanager.Entity.Timeline;

/**
 * TimelineConverter class to handle converting a timeline object together with
 * its content to text format so it later can be saved to a text file.
 * 
 * @author
 * @version 0.00.00
 * @name TimelineConverter.java
 */
public class TimelineConverter {

	private static final String[] TIMELINE_FIELDS = { "[Timeline]", "Title:", "Description:", "Start Date:",
			"End Date:" };
	private static final String[] EVENT_FIELDS = { "[Event]", "Title:", "Description:", "Image:", "Start Date:",
			"End Date:", "Start Time:", "End Time:" };

	/**
	 * Converts a timeline to string format suitable for saving to a text file.
	 * 
	 * @param t
	 *            - Timeline object to be converted to a string.
	 * @return A string representing a timeline object.
	 */
	public static String convert(Timeline t) {
		return t.toString();
	}

	/**
	 * Converts a string into a timeline object.
	 * 
	 * @param s
	 *            - String to be converted.
	 * @return A timeline populated with data from the string.
	 */
	public static Timeline convert(String s) {
		Timeline t = null;
		String[] content = s.split("\n");

		// In case the text files got auto formatted.
		for (int i = 0; i < content.length; i++) {
			content[i] = content[i].trim();
		}
		
		String[] timelineData;
		for (int x = 0; x < content.length; x++) {
			if (content[x].contains("[Timeline]")) {
				timelineData = readTimeline(content, x + 1);
				t = new Timeline(timelineData[0], timelineData[1], LocalDate.parse(timelineData[2]),
						LocalDate.parse(timelineData[3]), LocalTime.now(), LocalTime.now().plusHours(1));
			} else if (content[x].equals("[Event]")) {
				t.addEvent(readEvent(content, x + 1));
			}
		}
		
		return t;
	}

	/**
	 * Checks if the given file is a valid timeline file by checking if all the
	 * required fields for a timeline is stored within the file.
	 * 
	 * @param f
	 *            - File to validate.
	 * @return True of the file is a valid timeline file, false otherwise.
	 */
	public static boolean isTimeline(File f) {
		String s = FileManager.load(f.getPath());
		boolean[] exists = new boolean[TIMELINE_FIELDS.length];
		String[] content = s.split("\n");

		for (int i = 0; i < content.length; i++) {
			for (int x = 0; x < TIMELINE_FIELDS.length; x++) {
				if (content[i].contains(TIMELINE_FIELDS[x])) {
					exists[x] = true;
				}
			}
		}

		boolean allExists = true;
		for (int i = 0; i < exists.length; i++) {
			if (exists[i] != true) {
				allExists = false;
			}
		}

		return allExists;
	}

	/**
	 * Reads the timeline from a String array. A timeline has required fields
	 * and all of them have to be added to the timeline so this method throws an
	 * error if there is a missing field.
	 * 
	 * @param content
	 *            - Array with data to populate the Timeline data.
	 * @param pos
	 *            - Position within the content array to start from.
	 * @throws IllegalArgumentException
	 *             - When a field is missing.
	 * @return String array with data content to use when making a timeline
	 *         object.
	 */
	private static String[] readTimeline(String[] content, int pos) {
		// Data content to be extracted for a timeline, -1 because [Timeline]
		// does not count.
		String[] s = new String[TIMELINE_FIELDS.length - 1];
		int counter = pos;
		int i = 0;

		while (counter < content.length && counter < TIMELINE_FIELDS.length) {
			String tmp = content[counter];

			if (!tmp.contains(TIMELINE_FIELDS[counter])) {
				throw new IllegalArgumentException("Error in file format, missing field: " + TIMELINE_FIELDS[counter]);
			}

			s[i] = tmp.substring(TIMELINE_FIELDS[counter].length());
			counter++;
			i++;
		}

		return s;
	}

	/**
	 * Reads an event from a String array. This method popualtes and returns an
	 * event object using the data read from the content array.
	 * 
	 * @param content
	 *            - Array with data to populate the Event data.
	 * @param pos
	 *            - Position within the content array to start from.
	 * @return An Event object populated with data read from the content array.
	 */
	private static Event readEvent(String[] content, int pos) {
		// Data content to be extracted for a event, -1 because [Event] does not
		// count.
		String[] s = new String[EVENT_FIELDS.length - 1];
		int counter = pos;
		int i = 0;
		
		while (counter < content.length && i + 1 < EVENT_FIELDS.length) {
			String tmp = content[counter];
			
			if (i == 1) {
				s[i] = "";
				while (!tmp.contains(EVENT_FIELDS[i+2])) {
					s[i] += tmp;
					counter++;
					tmp = content[counter];
				}
				i++;
			} else {
				s[i] = tmp.substring(EVENT_FIELDS[i + 1].length());
	
				counter++;
				i++;
			}
		}

		// Populate event object with data
		Event newEvent = new Event();
		newEvent.setTitle(s[0]);
		newEvent.setDescription(s[1].substring(EVENT_FIELDS[2].length()));
		newEvent.setStartDate(LocalDate.parse(s[3]));
		
		// Checks for the temprary fields, adding them if they are present.
		if (!s[2].equals("") && s[2] != null) {
			newEvent.setPic(s[2]);
		}
		if (!s[4].equals("") && s[4] != null) {
			newEvent.setEndDate(LocalDate.parse(s[4]));
		}
		if (!s[5].equals("") && s[5] != null) {
			newEvent.setStartTime(LocalTime.parse(s[5]));
		}
		if (!s[6].equals("") && s[6] != null) {
			newEvent.setEndTime(LocalTime.parse(s[6]));		
		}
		
		return newEvent;
	}
}
