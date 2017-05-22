package com.timelinemanager.controller;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

import com.timelinemanager.io.TimelineConverter;
import com.timelinemanager.model.TimelineModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;

/**
 * Controls the actions for the UI events related to the navigation area of the
 * application.
 * 
 * @author
 * @version 0.00.00
 * @name NavigationController.java
 */
public class NavigationController {

	@FXML
	private MenuBar navigationMenu;

	// Buttons within the File menu inside the MenuBar
	@FXML
	private Menu menuItem_new;
	@FXML
	private MenuItem menuItem_open;
	@FXML
	private MenuItem menuItem_save;
	@FXML
	private MenuItem menuItem_saveAs;
	@FXML
	private MenuItem menuItem_close;
	@FXML
	private MenuItem menuItem_exit;

	// New submenu options: new Timeline and new Event
	@FXML
	private MenuItem menuItem_newTimeline;
	@FXML
	private MenuItem menuItem_newEvent;

	// Buttons within the Edit menu inside the MenuBar
	@FXML
	private Menu menuItem_edit;
	@FXML
	private Menu menuItem_timeline;
	@FXML
	private Menu menuItem_event;

	// Timeline submenu options: update & remove
	@FXML
	private MenuItem menuItem_updateTimeline;
	@FXML
	private MenuItem menuItem_removeTimeline;

	// Event submenu options: update & remove
	@FXML
	private MenuItem menuItem_updateEvent;
	@FXML
	private MenuItem menuItem_deleteEvent;

	// Buttons within the Help menu inside the MenuBar
	@FXML
	private MenuItem menuItem_about;
	@FXML
	private MenuItem menuItem_manual;

	/*
	 * Elements and user input variables from the "Create new timeline" window
	 */
	@FXML
	private AnchorPane anchorPane;
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

	/*
	 * Elements and user input variables from the "Create new event" window
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
	// @FXML private Label addImageLabel;

	/*
	 * Time measuring elements for the event.
	 */
	private static LocalTimePicker eventStartTime = new LocalTimePicker();
	private static LocalTimePicker eventEndTime = new LocalTimePicker();
	private TimelineModel timelineModel;

	@FXML
	public void initialize() {

		eventStartTime.setId("timelineManagerTimePickerStart");
		eventEndTime.setId("timelineManagerTimePickerEnd");

		// ActionEvent for the open button. //UPDATE!!
		menuItem_open.setOnAction((openFileEvent -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");

			// Creating a new file object to retrieve the path of the current
			// directory
			// and open the FileChooser there.
			File openingDir = new File(".");
			openingDir = new File(openingDir.getPath());
			fileChooser.setInitialDirectory(openingDir);

			File f = fileChooser.showOpenDialog(null);

			if ((f != null) && f.isFile()) {
				if (TimelineConverter.isTimeline(f)) {
					timelineModel.loadTimeline(f.getPath());
				} else {
					Alert closeConfirmation = new Alert(Alert.AlertType.WARNING,
							"The specified file is not a valid Timeline file. \n" + "Please select a valid file.");
					closeConfirmation.setHeaderText("Invalid Timeline file.");
					closeConfirmation.initModality(Modality.APPLICATION_MODAL);
					Stage stage = (Stage) closeConfirmation.getDialogPane().getScene().getWindow();
					stage.getIcons().add(new Image("/view/img/appicon.PNG"));
					Optional<ButtonType> result = closeConfirmation.showAndWait();
					if (result.get() == ButtonType.OK) {
					}
				}
			}
		}));

		// ActionEvent for new timeline button.
		// Opens new window which allows the user to create a new timeline.
		menuItem_newTimeline.setOnAction(newTimelineWindow -> {

			try {
				FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/createNewTimeline.fxml"));
				Parent root = (Parent) fxmlLoader.load();

				// Initialize the same timeline model into every controller.
				if (fxmlLoader.getController() != null) {
					if (fxmlLoader.getController().getClass() == CreateTimelineController.class) {
						CreateTimelineController n = (CreateTimelineController) fxmlLoader.getController();
						n.initTimelineModel(this.timelineModel);
					}
				}

				Scene mainScene = new Scene(root);
				Stage stage = new Stage();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				stage.setHeight(500);
				stage.setWidth(620);
				stage.setScene(mainScene);
				stage.setTitle("Create a new timeline");
				stage.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		// ActionEvent for new event button.
		// Opens new window which allows the user to create a new event.
		menuItem_newEvent.setOnAction(newEventWindow -> {

			try {
				FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/createNewEvent.fxml"));
				Parent root = (Parent) fxmlLoader.load();

				// Initialize the same timeline model into every controller.
				if (fxmlLoader.getController() != null) {
					if (fxmlLoader.getController().getClass() == CreateEventController.class) {
						CreateEventController n = (CreateEventController) fxmlLoader.getController();
						n.initTimelineModel(this.timelineModel);
						n.initTimePickers(eventStartTime, eventEndTime);
					}
				}

				Scene mainScene = new Scene(root);

				((AnchorPane) root).getChildren().add(eventStartTime);
				eventStartTime.setLayoutX(90);
				eventStartTime.setLayoutY(240);

				((AnchorPane) root).getChildren().add(eventEndTime);
				eventEndTime.setLayoutX(355);
				eventEndTime.setLayoutY(240);

				Stage stage = new Stage();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				stage.setHeight(500);
				stage.setWidth(620);
				stage.setScene(mainScene);
				stage.setTitle("Create a new event");
				stage.showAndWait();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		// ActionEvent for save button.
		// Saves the currently active timeline to the file system.
		menuItem_save.setOnAction(saveTimeline -> {
			if (timelineModel.getTimeline().getValue() == null) {
				Alert noTimeline = new Alert(Alert.AlertType.WARNING,
						"There is no timeline to save, please open or create a timeline before saving.");
				noTimeline.setHeaderText("No Timeline to save.");
				noTimeline.initModality(Modality.APPLICATION_MODAL);
				Stage stage = (Stage) noTimeline.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				noTimeline.showAndWait();
			} else {
				this.timelineModel.saveTimeline();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Your timeline is saved now");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
			}
		});

		menuItem_saveAs.setOnAction(saveAsTimeline -> {
			if (timelineModel.getTimeline().getValue() == null) {
				Alert noTimeline1 = new Alert(Alert.AlertType.WARNING,
						"There is no timeline to save, please open or create a timeline before saving.");
				noTimeline1.setHeaderText("No Timeline to save.");
				noTimeline1.initModality(Modality.APPLICATION_MODAL);
				Stage stage1 = (Stage) noTimeline1.getDialogPane().getScene().getWindow();
				stage1.getIcons().add(new Image("/view/img/appicon.PNG"));
				noTimeline1.showAndWait();
			} else {

				this.timelineModel.saveTimeline();
			}
		});
		// Action Event to close the active timeline
		menuItem_close.setOnAction(closeTimeline -> {
			if (timelineModel.getTimeline().getValue() == null) {
				Alert noTimeline = new Alert(Alert.AlertType.WARNING, "There is no active timeline to close.");
				noTimeline.setHeaderText("No Timeline to close.");
				noTimeline.initModality(Modality.APPLICATION_MODAL);
				Stage stage1 = (Stage) noTimeline.getDialogPane().getScene().getWindow();
				stage1.getIcons().add(new Image("/view/img/appicon.PNG"));
				noTimeline.showAndWait();
			} else {
				timelineModel.setTimeline(null);

			}
		});

		// ActionEvent for update timeline button.
		// Opens new window which allows the user to edit the currently active
		// timeline.
		menuItem_updateTimeline.setOnAction(updateTimeline -> {
			if (timelineModel.getTimeline().getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Timeline Error");
				alert.setContentText(
						"There is no currently active timeline. Please open or create a timeline before updating!");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				alert.showAndWait();
			} else {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/updateTimeline.fxml"));
					Parent root = (Parent) fxmlLoader.load();

					// Initialize the same timeline model into every controller.
					if (fxmlLoader.getController() != null) {
						if (fxmlLoader.getController().getClass() == UpdateTimelineController.class) {
							UpdateTimelineController n = (UpdateTimelineController) fxmlLoader.getController();
							n.initTimelineModel(this.timelineModel);
						}
					}

					Scene mainScene = new Scene(root);
					Stage stage = new Stage();
					stage.getIcons().add(new Image("/view/img/appicon.PNG"));
					stage.setHeight(500);
					stage.setWidth(620);
					stage.setScene(mainScene);
					stage.setTitle("Edit the currently active timeline");
					stage.showAndWait();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// ActionEvent for the close button.
		// Closes the currently active timeline but keeps it in the timeline
		// listings.
		menuItem_close.setOnAction(e -> {
			if (timelineModel.getTimeline().getValue() == null) {
				Alert noTimeline = new Alert(Alert.AlertType.WARNING,
						"There is no Timeline to close, please open a Timeline.");
				noTimeline.setHeaderText("No Timeline to close.");
				noTimeline.initModality(Modality.APPLICATION_MODAL);
				Stage stage = (Stage) noTimeline.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				noTimeline.showAndWait();
			} else {
				timelineModel.setTimeline(null);
			}
		});
		// ActionEvent for remove timeline button.
		// Opens an alert dialogue which allows the user to remove the currently
		// active timeline.
		menuItem_removeTimeline.setOnAction(removeTimeline -> {
			if (timelineModel.getTimeline().getValue() != null) {
				Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
						"Are you sure you want to remove the currently active timeline?");
				closeConfirmation.setHeaderText("Confirm removal");
				closeConfirmation.initModality(Modality.APPLICATION_MODAL);
				Stage stage = (Stage) closeConfirmation.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				Optional<ButtonType> result = closeConfirmation.showAndWait();
				if (result.get() == ButtonType.OK) {
					timelineModel.getLoadedTimelines().remove(timelineModel.getTimeline().getValue());
					timelineModel.setTimeline(null);
				}
			} else {
				Alert noTimeline = new Alert(Alert.AlertType.WARNING, "There is no Timeline to remove.");
				noTimeline.setHeaderText("No Timeline to remove.");
				noTimeline.initModality(Modality.APPLICATION_MODAL);
				Stage stage = (Stage) noTimeline.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				noTimeline.showAndWait();
			}
		});

		menuItem_updateEvent.setOnAction(updateEvent -> {
			if (this.timelineModel.getTimeline().getValue() != null) {
				this.timelineModel.getTimeline().getValue().searchEvent();
			} else {
				Alert noTimeline = new Alert(Alert.AlertType.WARNING,
						"There is no Timeline to update events in, please create a Timeline.");
				noTimeline.setHeaderText("No Timeline.");
				noTimeline.initModality(Modality.APPLICATION_MODAL);
				Stage stage = (Stage) noTimeline.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				noTimeline.showAndWait();
			}
		});

		menuItem_deleteEvent.setOnAction(updateEvent -> {
			if (this.timelineModel.getTimeline().getValue() != null) {
				this.timelineModel.getTimeline().getValue().searchEvent();
			} else {
				Alert noTimeline = new Alert(Alert.AlertType.WARNING,
						"There is no Timeline to remove events in, please create a Timeline.");
				noTimeline.setHeaderText("No Timeline.");
				noTimeline.initModality(Modality.APPLICATION_MODAL);
				Stage stage = (Stage) noTimeline.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("/view/img/appicon.PNG"));
				noTimeline.showAndWait();
			}
		});

		// ActionEvent for about menu button
		// Opens information dialog with info about the creators
		menuItem_about.setOnAction(openAbout -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About TimelineManager");
			alert.setHeaderText(null);
			alert.setContentText("This is a timeline managing application created by Group8." + "\nWe are.. "
					+ "\n\n\t\tMendel Oskar" + "\n\t\tNasrini Mohammed Basel"
					+ "\n\t\tAlhrazy Waeel" + "\n\t\tZhao Shizhen" + "\n\t\tModic Milan"
					+ "\n\t\tMironov Georgiana" + "\n\t\tArgyriou Dimitrios"
					+ "\n\nAll resources used in the making of this application are used under a Creative Commons license. Futhermore, the application itself is under the MIT License. ");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.PNG"));
			alert.showAndWait();
		});

		// ActionEvent for manual menu button
		// Opens a confirmation dialog where the user can choose to open
		// an external file containing the manual for the application
		menuItem_manual.setOnAction(openManual -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Application Manual");
			alert.setHeaderText("Need some help?");
			alert.setContentText("Unsure about how our application works? Open the manual.");

			ButtonType manual = new ButtonType("Open");
			ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.PNG"));
			alert.getButtonTypes().setAll(manual, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == manual) {
				if(Desktop.isDesktopSupported())
		        	{
		            		try {
		                		Desktop.getDesktop().browse(new URI("https://github.com/brokenprogrammer/Timeline-Manager/blob/master/documentation/Manual%20v0.1.pdf"));
		            		} catch (Exception e1) {
		                		e1.printStackTrace();
		            		}
		        	}
			} 
		});

		// ActionEvent for the exit button.
		menuItem_exit.setOnAction(exitAppEvent -> {
			Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
			closeConfirmation.setHeaderText("Confirm Exit");
			closeConfirmation.setContentText("Don't forget to save!");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Stage stage = (Stage) closeConfirmation.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.PNG"));
			
			Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			if (ButtonType.CANCEL.equals(closeResponse.get())) {
				exitAppEvent.consume();
			} else {
				if (this.timelineModel.getTimeline().getValue() != null) {
					Alert saveConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
							"Do you want to save your work before you exit");
					saveConfirmation.setHeaderText("Save Timeline");
					saveConfirmation.initModality(Modality.APPLICATION_MODAL);
					Stage stage2 = (Stage) saveConfirmation.getDialogPane().getScene().getWindow();
					stage2.getIcons().add(new Image("/view/img/appicon.PNG"));
					
					Optional<ButtonType> saveResponse = saveConfirmation.showAndWait();
					if (ButtonType.CANCEL.equals(saveResponse.get())) {
						exitAppEvent.consume();
					} else {
						this.timelineModel.saveTimeline();
						System.exit(0);
					}
				} else {
					System.exit(0);
				}
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

		// TODO: Add potential listeners here

	}
}
