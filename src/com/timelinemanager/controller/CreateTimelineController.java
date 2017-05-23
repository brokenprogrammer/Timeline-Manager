package com.timelinemanager.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.model.TimelineModel;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
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
	private TextField timelineTitle;
	@FXML
	private TextArea timelineDescription;
	@FXML
	private DatePicker datePicker_startDate;
	@FXML
	private DatePicker datePicker_endDate;
	@FXML
	private Button createTimelineButton;
	@FXML
	private Button cancelCreateTimeline;

	private Timeline newTimeline;
	private TimelineModel timelineModel;

	@FXML
	public void initialize() {

		// ActionEvent for the create button inside the "Create new timeline"
		// window
		// Alert dialogs for error inputs
		createTimelineButton.setOnMouseClicked(createTimeline -> {

			LocalDate start = datePicker_startDate.getValue();
			LocalDate end = datePicker_endDate.getValue();

			if (timelineTitle.getText().length() == 0 && start == null && end == null) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Input Error");
				alert.setContentText("The required fields are empty, please fill them all to create a Timeline!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();

			} else if (timelineTitle.getText().length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Input Error");
				alert.setContentText("Title is missing, please enter a title to create a Timeline!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
				
			} else if (start == null || end == null) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Date Error");
				alert.setContentText("Date is missing, please enter a start and end date to create a Timeline!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
				
			} else if (timelineTitle.getText().length() > 50) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Input Error");
				alert.setContentText("Max 50 characters only!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
				
			} else if (timelineDescription.getText().length() > 500) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Input Error");
				alert.setContentText("Max 500 characters only!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
				
			} else if (start.isAfter(end)) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Date Error");
				alert.setContentText("Please type date in the correct date format!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
			} else {
			// Populate timeline object with data
				newTimeline = new Timeline(timelineTitle.getText(), timelineDescription.getText(),
						datePicker_startDate.getValue(), datePicker_endDate.getValue(), LocalTime.now(),
						LocalTime.now().plusHours(1));

				this.timelineModel.setTimeline(newTimeline);
				this.timelineModel.getLoadedTimelines().add(newTimeline);
				((Node) (createTimeline.getSource())).getScene().getWindow().hide();
			}
		});

		// ActionEvent for the cancel button inside the "Create new timeline"
		// window
		// Opens a pop-up window asking for exit confirmation
		cancelCreateTimeline.setOnAction(cancelEvent -> {

			Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
					"Are you sure you want to cancel creating a timeline?");
			DialogPane dialogPane = closeConfirmation.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
			cancelCreateTimeline = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
			closeConfirmation.setHeaderText("Confirm Exit");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Stage stage = (Stage) closeConfirmation.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.PNG"));

			Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			if (!ButtonType.OK.equals(closeResponse.get())) {
				cancelEvent.consume();
			} else
				((Node) (cancelEvent.getSource())).getScene().getWindow().hide();
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
