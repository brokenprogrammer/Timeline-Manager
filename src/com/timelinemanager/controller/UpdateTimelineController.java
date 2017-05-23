package com.timelinemanager.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.timelinemanager.Entity.Event;
import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.model.TimelineModel;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * 
 * @author
 * @version 0.00.00
 * @name UpdateTimelineController.java
 */
public class UpdateTimelineController {

	/*
	 * Elements and user input variables from the "Update timeline" window
	 */
	@FXML
	private AnchorPane updateNewTimeline_anchorPane;
	@FXML
	private DatePicker updateDatePicker_startDate;
	@FXML
	private DatePicker updateDatePicker_endDate;
	@FXML
	private Button updateEditTimelineButton;
	@FXML
	private Button updateCancelEditTimeline;
	@FXML
	private TextArea updateTimelineDescription;
	@FXML
	private TextField updateTimelineTitle;

	private Timeline updatedTimeline;
	private TimelineModel timelineModel;

	@FXML
	public void initialize() {

		// Action event for the Update Timeline button.
		// Opens a new window with the data from the current timeline which can
		// be modified and saved.
		// Alert dialogs for error inputs.
		
		updateEditTimelineButton.setOnAction(ev -> {
			LocalDate start = updateDatePicker_startDate.getValue();
			LocalDate end = updateDatePicker_endDate.getValue();

			if (updateTimelineTitle.getText().length() == 0 && start == null && end == null) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Input Error");
				alert.setContentText("The required fields are empty, please fill them to create a Timeline!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
				
			} else if (updateTimelineTitle.getText().length() == 0) {
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
				
			} else if (updateTimelineTitle.getText().length() > 50) {
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Input Error");
				alert.setContentText("Max 50 characters only!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
				
			} else if (updateTimelineDescription.getText().length() > 500) {
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
				boolean eventsChecked = true;
				Timeline t = new Timeline(updateTimelineTitle.getText(), updateTimelineDescription.getText(),
						start, end, LocalTime.now(), LocalTime.now().plusHours(1));
				
				// Check so that all events are within the timespan of the new timeline.
				for (Event e : this.timelineModel.getTimeline().getValue().eventArr) {
					if (e.getStartDate().isBefore(t.getStartDate()) || e.getStartDate().isAfter(t.getEndDate())) {
						eventsChecked = false;
						
						Alert alert = new Alert(AlertType.ERROR);
						DialogPane dialogPane = alert.getDialogPane();
						dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Date Error");
						alert.setContentText("All the events start dates are not within the timelines timespan!");
						Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
						stage.getIcons().add(new Image("/view/img/appicon.PNG"));
						alert.showAndWait();
					} else if (e.getEndDate().isBefore(t.getStartDate()) || e.getEndDate().isAfter(t.getEndDate())) {
						eventsChecked = false;
						
						Alert alert = new Alert(AlertType.ERROR);
						DialogPane dialogPane = alert.getDialogPane();
						dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Date Error");
						alert.setContentText("All the events end dates are not within the timelines timespan!");
						Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
						stage.getIcons().add(new Image("/view/img/appicon.PNG"));
						alert.showAndWait();
					}
				}
				
				
				if (eventsChecked) {
					// Add all the events to the newly constructed timeline.
					for (Event e : this.timelineModel.getTimeline().getValue().eventArr) {
						t.addEvent(e);
					}
					
					// Update the model with the new timeline and update the list with loaded timelines as well.
					this.timelineModel.setTimeline(t);
					int indx = this.timelineModel.getLoadedTimelines().indexOf(updatedTimeline);
					this.timelineModel.getLoadedTimelines().set(indx, t);
					((Node) (ev.getSource())).getScene().getWindow().hide();
				}
			}
		});

		// ActionEvent for the cancel button inside the Update current timeline
		// window
		// Opens a pop-up window asking for exit confirmation
		updateCancelEditTimeline.setOnAction(cancelEvent -> {

			Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
					"Are you sure you want to cancel editing the timeline?");
			DialogPane dialogPane = closeConfirmation.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
			updateCancelEditTimeline = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
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
			
		 if  (this.timelineModel != null) {
			throw new IllegalStateException("Model can only be initiated once");
		}
		 
		this.timelineModel = timelineModel;
		this.updatedTimeline = timelineModel.getTimeline().getValue();
		updateTimelineTitle.setText(updatedTimeline.getTitle());
		updateTimelineDescription.setText(updatedTimeline.getDescription());
		updateDatePicker_startDate.setValue(updatedTimeline.getStartDate());
		updateDatePicker_endDate.setValue(updatedTimeline.getEndDate());

		// Code for when timeline was changed.
		timelineModel.getTimeline().addListener((ChangeListener<Timeline>) (observable, oldValue, newValue) -> {

		});
	}
}
