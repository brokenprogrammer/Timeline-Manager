package com.timelinemanager.controller;

import com.timelinemanager.model.TimelineModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * Controls the actions for the UI events related to the
 * timeline listings area of the application.
 * 
 * @author 
 * @version 0.00.00
 * @name TimelineListingController.java
 */
public class TimelineListingController {
	
	//UI controls for the timeline listings area.
	@FXML private ScrollPane timelineHolder;
	@FXML private AnchorPane timelineListing_anchorpane;
	@FXML private Label Timelines_Headline_text;
	
	private TimelineModel timelineModel;
	
	@FXML
	public void initialize() {

	}
	
	/**
	 * Initializes the TimelineModel which this class will get data from when a
	 * new timeline is created or updated.
	 * 
	 * @param timelineModel - timelineModel object to send and receive data from.
	 */
	public void initTimelineModel(TimelineModel timelineModel) {
		if (this.timelineModel != null) {
			throw new IllegalStateException("Model can only be initiated once");
		}
		
		this.timelineModel = timelineModel;
		
		//TODO: Add potential listeners here
		
	}
}