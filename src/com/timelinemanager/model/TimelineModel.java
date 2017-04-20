package com.timelinemanager.model;

import com.timelinemanager.Entity.Timeline;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Timeline model that allows the application to share
 * timeline data across the application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name TimelineModel.java
 */
public class TimelineModel {
	//TODO: Connect with IO classes.
	private ObjectProperty<Timeline> activeTimeline = new SimpleObjectProperty<>();
	private final ObservableList<Timeline> loadedTimelineList = FXCollections.observableArrayList();
	
	/**
	 * Gets the ObjectProperty of the currently active timeline.
	 * 
	 * @return ObjectProperty for the currently active timeline.
	 */
	public ObjectProperty<Timeline> getTimeline() {
		return activeTimeline;
	}
	
	/**
	 * Sets the currently active timeline.
	 * 
	 * @param t - Timeline object to set to active.
	 */
	public void setTimeline(Timeline t) {
		this.activeTimeline.set(t);
	}
	
	/**
	 * Loads target timeline located at the specified path.
	 * 
	 * @param path - Path to the timeline file to load.
	 */
	public void loadTimeline(String path) {
		
	}
	
	/**
	 * Saves the currently active timeline to the filesystem.
	 */
	public void saveTimeline() {
		//Saves the active timeline
	}
}
