package com.timelinemanager.controller;

import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.model.TimelineModel;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * 
 * @author 
 * @version 0.00.00
 * @name TimelineController.java
 */
public class CreateTimelineController {
	
	/*
	 * Elements from the "Create new timeline" window
	 */
	@FXML private AnchorPane newTimeline_anchorPane;
	@FXML private DatePicker datePicker_startDate;
	@FXML private DatePicker datePicker_endDate;
	@FXML private Button createTimelineButton;
	@FXML private Button cancelCreateTimeline;
	@FXML private TextArea timelineDescription;
	@FXML private TextField timelineTitle;
	
	private static Timeline newTimeline = new Timeline();
	private TimelineModel timelineModel;
	
	@FXML
	public void initialize() {
		
		//ActionEvent for the create button inside the "Create new timeline" window
		createTimelineButton.setOnMouseClicked(cancelTmEvent -> {
			
			//Populate timeline object with data
			newTimeline.setTitle(timelineTitle.getText());
		  newTimeline.setDescription(timelineDescription.getText());
		  newTimeline.setStartTime(NavigationController.getEndTime());
		  newTimeline.setEndTime(NavigationController.getEndTime());
		  newTimeline.setStartDate(datePicker_startDate.getValue());
		  newTimeline.setEndDate(datePicker_endDate.getValue());	     
		    
	    });
		
		//ActionEvent for the cancel button inside the "Create new timeline" window		
		cancelCreateTimeline.setOnAction(cancelEvent -> {
			((Node)(cancelEvent.getSource())).getScene().getWindow().hide();
		});
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
		
		//Code for when timeline was changed.
		timelineModel.getTimeline().addListener((ChangeListener<Timeline>) (observable, oldValue, newValue) -> {
			
		});
	}
}
