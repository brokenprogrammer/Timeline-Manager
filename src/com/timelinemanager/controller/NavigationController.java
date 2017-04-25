package com.timelinemanager.controller;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.time.LocalTime;
import javafx.stage.FileChooser;

import com.timelinemanager.model.TimelineModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;

/**
 * Controls the actions for the UI events related to the
 * navigation area of the application.
 * 
 * @author 
 * @version 0.00.00
 * @name NavigationController.java
 */
public class NavigationController {
	
	@FXML private MenuBar navigationMenu;
	
	//Buttons within the File menu inside the MenuBar
	@FXML private Menu menuItem_new;
	@FXML private MenuItem menuItem_open;
	@FXML private MenuItem menuItem_save;
	@FXML private MenuItem menuItem_saveAs;
	@FXML private MenuItem menuItem_close;
	@FXML private MenuItem menuItem_exit;
	
	//New submenu options: new Timeline and new Event
	@FXML private MenuItem menuItem_newTimeline;
	@FXML private MenuItem menuItem_newEvent;
	
	//Buttons within the Edit menu inside the MenuBar
	@FXML private Menu menuItem_edit;
	@FXML private Menu menuItem_timeline;
	@FXML private Menu menuItem_event;
	
	//Timeline submenu options: update & remove
	@FXML private MenuItem menuItem_updateTimeline;
	@FXML private MenuItem menuItem_removeTimeline;
	
	//Event submenu options: update & remove
	@FXML private MenuItem menuItem_updateEvent;
	@FXML private MenuItem menuItem_deleteEvent;
	
	//Buttons within the Help menu inside the MenuBar
	@FXML private MenuItem menuItem_about;
	
	/*
	 * Elements and user input variables from the
	 * "Create new timeline" window
	 */
	@FXML private AnchorPane anchorPane;
	@FXML private DatePicker datePicker_startDate;
	@FXML private DatePicker datePicker_endDate;
	@FXML private Button createTimelineButton;
	@FXML private Button cancelCreateTimeline;
	@FXML private TextArea timelineDescription;
	@FXML private TextField timelineTitle;
	
	/*
	 * Elements and user input variables from the
	 * "Create new event" window
	 */
	@FXML private AnchorPane newEvent_anchorPane;
	@FXML private DatePicker datePicker_eventStartDate;
	@FXML private DatePicker datePicker_eventEndDate;
	@FXML private Button createEventButton;
	@FXML private Button cancelCreateEvent;
	@FXML private TextArea eventDescription;
	@FXML private TextField eventTitle;
	@FXML private ImageView eventImage;
	@FXML private Label addImageLabel;
	
	/*
	 * Time measuring elements for the timeline.
	 */
	private static LocalTimePicker startTime = new LocalTimePicker();
	private static LocalTimePicker endTime = new LocalTimePicker();
	
	/*
	 * Time measuring elements for the event.
	 */
	private static LocalTimePicker eventStartTime = new LocalTimePicker();
	private static LocalTimePicker eventEndTime = new LocalTimePicker();
	private TimelineModel timelineModel;
	
	@FXML
	public void initialize() {
		
		//ActionEvent for the open button. //UPDATE!!
		menuItem_open.setOnAction((openFileEvent -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.showOpenDialog(null);
		}));
		
		//ActionEvent for new timeline button.
		//Opens new window which allows the user to create a new timeline.
		menuItem_newTimeline.setOnAction(newTimelineWindow ->  {   
			
			try {
			    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/createNewTimeline.fxml"));
			    Parent root = (Parent)fxmlLoader.load();
			  
			    Scene mainScene = new Scene(root);

			    ((AnchorPane) root).getChildren().add(startTime);
			    startTime.setLayoutX(90);
			    startTime.setLayoutY(240);
			    
			    ((AnchorPane) root).getChildren().add(endTime);
			    endTime.setLayoutX(355);
			    endTime.setLayoutY(240);
			    
			    Stage stage = new Stage();  
			    stage.setHeight(500);
			    stage.setWidth(620);
			    stage.setScene(mainScene);
			    stage.setTitle("Create a new timeline");        
			    stage.showAndWait(); 	
		       
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		});
		
		//ActionEvent for new event button.
		//Opens new window which allows the user to create a new event.
		menuItem_newEvent.setOnAction(newEventWindow -> { 
			try {
			    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/createNewEvent.fxml"));
			    Parent root = (Parent)fxmlLoader.load();
			  
			    Scene mainScene = new Scene(root);
			    
			    ((AnchorPane) root).getChildren().add(eventStartTime);
			    eventStartTime.setLayoutX(90);
			    eventStartTime.setLayoutY(240);
			    
			    ((AnchorPane) root).getChildren().add(eventEndTime);
			    eventEndTime.setLayoutX(355);
			    eventEndTime.setLayoutY(240);
			    
			    Stage stage = new Stage();  
			    stage.setHeight(500);
			    stage.setWidth(620);
			    stage.setScene(mainScene);
			    stage.setTitle("Create a new event");   
			    stage.showAndWait(); 	
			    
		    } catch (IOException e1) 
			{
		        e1.printStackTrace();
		    }
            
        	});
		
		//ActionEvent for the exit button.
		menuItem_exit.setOnAction(exitAppEvent -> System.exit(0)); 
	}
	
	/**
	 * Get the local start time for the timeline.
	 * @return local start time for timeline
	 */
	public static LocalTime getStartTime(){
		return startTime.getLocalTime();
	}
	
	/**
	 * Get the local end time for the timeline.
	 * @return local end time for timeline
	 */
	public static LocalTime getEndTime(){
		return endTime.getLocalTime();
	}
	
	/**
	 * Get the local start time for the event.
	 * @return local start time for event
	 */
	public static LocalTime getEventStartTime(){
		return eventStartTime.getLocalTime();
	}
	
	/**
	 * Get the local end time for the event.
	 * @return local end time for event
	 */
	public static LocalTime getEventEndTime(){
		return eventEndTime.getLocalTime();
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
