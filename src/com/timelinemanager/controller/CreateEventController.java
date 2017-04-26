package com.timelinemanager.controller;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

/**
 * 
 * 
 * @author
 * @version 0.00.00
 * @name TimelineController.java
 */
public class CreateEventController {

	/*
	 * Elements from the "Create new event" window
	 */
	@FXML
	private AnchorPane newEvent_anchorPane;
	@FXML
	private DatePicker datePicker_eventStartDate;
	@FXML
	private DatePicker datePicker_eventEndDate;
	@FXML
	private Button createEventButton;
	@FXML
	private Button cancelCreateEvent;
	@FXML
	private TextArea eventDescription;
	@FXML
	private TextField eventTitle;
	@FXML
	private ImageView eventImage;
	@FXML
	private Label addImageLabel;

	//private static Event newEvent = new Event();
	private TimelineModel timelineModel;

	@FXML
	public void initialize() throws IOException {

		// ActionEvent for the create button inside the "Create new event"
		// window
		createEventButton.setOnMouseClicked(createEvent -> {

			// Populate event object with data
//			newEvent.setTitle(eventTitle.getText());
//			newEvent.setDescription(eventDescription.getText());
//			newEvent.setStartTime(NavigationController.getEventStartTime());
//			newEvent.setEndTime(NavigationController.getEventEndTime());
//			newEvent.setStartDate(datePicker_eventStartDate.getValue());
//			newEvent.setEndDate(datePicker_eventEndDate.getValue());

		});

		// ActionEvent for the add image label inside the "Create new event"
		// window
		addImageLabel.setOnMouseClicked(addImage -> {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Image File");

			// Set extension filter
			FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
			FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
			fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

			File file = fileChooser.showOpenDialog(null);

			Image image1 = new Image(file.toURI().toString());
			eventImage.setImage(image1);
			eventImage.setPreserveRatio(true);
			eventImage.setFitWidth(189.0);
			eventImage.setFitHeight(128.0);
			eventImage.setSmooth(true);
			eventImage.setCache(true);
			addImageLabel.setVisible(false);

		});

		// ActionEvent for the cancel button inside the "Create new event"
		// window
		//Opens a pop-up window asking for exit confirmation
		cancelCreateEvent.setOnAction(cancelEvent -> {
			
			Alert closeConfirmation = new Alert(
	                Alert.AlertType.CONFIRMATION,
	                "Are you sure you want to cancel creating an event?"
	        );
			cancelCreateEvent = (Button) closeConfirmation.getDialogPane().lookupButton(
	                ButtonType.OK
	        );
	        closeConfirmation.setHeaderText("Confirm Exit");
	        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
	        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
	        if (!ButtonType.OK.equals(closeResponse.get())) {
	        	cancelEvent.consume();
	        }
	        else
	        {
	        	((Node)(cancelEvent.getSource())).getScene().getWindow().hide();
	        }
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
