package com.timelinemanager.controller;

import java.time.LocalTime;
import java.util.Optional;

import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.model.TimelineModel;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 * 
 * 
 * @author
 * @version 0.00.00
 * @name CreateTimelineController.java
 */
public class CreateTimelineController {

	/*
	 * Elements from the "Create new timeline" window
	 */
	@FXML
	private AnchorPane newTimeline_anchorPane;
	@FXML
	private DatePicker datePicker_startDate;
	@FXML
	private DatePicker datePicker_endDate;
	@FXML
	private Button createTimelineButton;
	@FXML
	private Button cancelCreateTimeline;
	@FXML
	private TextArea timelineDescription;
	@FXML
	private TextField timelineTitle;

	private Timeline newTimeline;
	private TimelineModel timelineModel;

	@FXML
	public void initialize() {

		// ActionEvent for the create button inside the "Create new timeline" window
		createTimelineButton.setOnMouseClicked(createTimelineEvent -> {

			// Populate timeline object with data
			newTimeline = new Timeline(timelineTitle.getText(), 
					timelineDescription.getText(), datePicker_startDate.getValue(), 
					datePicker_endDate.getValue(), LocalTime.now(), LocalTime.now().plusHours(1));
			
			this.timelineModel.setTimeline(newTimeline);
			((Node) (createTimelineEvent.getSource())).getScene().getWindow().hide();
		});

		// ActionEvent for the cancel button inside the "Create new timeline" window
		//Opens a pop-up window asking for exit confirmation
		cancelCreateTimeline.setOnAction(cancelEvent -> {
			
			Alert closeConfirmation = new Alert(
	                			Alert.AlertType.CONFIRMATION,
	                			"Are you sure you want to cancel creating a timeline?");
	        	cancelCreateTimeline = (Button) closeConfirmation.getDialogPane().lookupButton(
	                			ButtonType.OK);
	        	closeConfirmation.setHeaderText("Confirm Exit");
	        	closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			
	        	Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
	        	if (!ButtonType.OK.equals(closeResponse.get())) {
	        		cancelEvent.consume();
	        	}
	        	else
	        		((Node)(cancelEvent.getSource())).getScene().getWindow().hide();
		});
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

		});
	}
}
