package com.timelinemanager.model;

import java.util.List;

import com.timelinemanager.Entity.Event;
import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.io.FileManager;
import com.timelinemanager.io.TimelineConverter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Timeline model that allows the application to share timeline data across the
 * application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name TimelineModel.java
 */
public class TimelineModel {
	private ObjectProperty<Timeline> activeTimeline = new SimpleObjectProperty<>();
	private final ObservableList<Event> activeTimelineEvents = FXCollections.observableArrayList();

	private final String savePath = "";

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
	 * Gets the observable list of events within the currently active
	 * Timeline. 
	 * 
	 * @return The observable list of events.
	 */
	public ObservableList<Event> getEvents() {
		return this.activeTimelineEvents;
	}
	
	/**
	 * Sets the events of the active Timeline.
	 * 
	 * @param events - List of Events to set.
	 */
	public void setEvents(List<Event> events) {
		this.activeTimelineEvents.clear();
		
		this.activeTimelineEvents.addAll(events);
	}

	/**
	 * Loads target timeline located at the specified path.
	 * 
	 * @param path - Path to the timeline file to load.
	 */
	public void loadTimeline(String path) {
		this.activeTimeline.set(TimelineConverter.convert(FileManager.load(path)));
	}

	/**
	 * Saves the currently active timeline to the filesystem.
	 */
	public void saveTimeline() {
		FileManager.save(TimelineConverter.convert(this.activeTimeline.getValue()),
				savePath + this.activeTimeline.getValue().getTitle() + ".txt");
	}
}
