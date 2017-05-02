package com.timelinemanager.controller;

import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.model.TimelineModel;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Controls the actions for the UI events related to the timeline area of the
 * application.
 * 
 * @author
 * @version 0.00.00
 * @name TimelineController.java
 */
public class TimelineController {
	// Add timeline image button.
	@FXML
	private Button addImage_button;

	// Search related UI controls/
	@FXML
	private TextField SearchContainer;
	@FXML
	private Button SearchButton;

	// Timeline related UI controls.
	@FXML
	private AnchorPane timeline_anchorpane_container;
	@FXML
	private Button add_event_button;

	private TimelineModel timelineModel;

	@FXML
	public void initialize() {
		// timeline_anchorpane_container.getChildren().add(myTimeline);
	}

	/**
	 * Initializes the TimelineModel which this class will get data from when a
	 * new timeline is created or updated.
	 * 
	 * @param timelineModel
	 *            - timelineModel object to send and receive data from.
	 */
	public void initTimelineModel(TimelineModel timelineModel) {
		if (this.timelineModel != null) {
			throw new IllegalStateException("Model can only be initiated once");
		}

		this.timelineModel = timelineModel;

		// Code for when timeline was changed.
		timelineModel.getTimeline().addListener((ChangeListener<Timeline>) (observable, oldValue, newValue) -> {
			timeline_anchorpane_container.getChildren().add(timelineModel.getTimeline().getValue());
		});
	}
}
