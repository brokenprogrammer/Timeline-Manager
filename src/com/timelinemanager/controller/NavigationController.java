package com.timelinemanager.controller;

import java.awt.TextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private TimelineModel timelineModel;
	
	@FXML
	public void initialize() {
		/*
		 * Set actions to each UI element.
		 */
		
		//Opens new window which allows the user to create a new timeline
		menuItem_newTimeline.setOnAction(actionEvent ->  {   
			
			try {
				
			    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/createNewTimeline.fxml"));
			    Parent root = (Parent)fxmlLoader.load();
			  
			    Scene mainScene = new Scene(root);
			    LocalTimePicker startTime = new LocalTimePicker();
			    ((AnchorPane) root).getChildren().add(startTime);
			    startTime.setLayoutX(90);
			    startTime.setLayoutY(240);
			    
			    LocalTimePicker endTime = new LocalTimePicker();
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
		
		menuItem_exit.setOnAction(actionEvent -> System.exit(0)); 
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
