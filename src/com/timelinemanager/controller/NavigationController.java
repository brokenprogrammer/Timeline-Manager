package com.timelinemanager.controller;

import com.timelinemanager.model.TimelineModel;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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
	@FXML private MenuItem menuItem_new;
	@FXML private MenuItem menuItem_open;
	@FXML private MenuItem menuItem_save;
	@FXML private MenuItem menuItem_saveAs;
	@FXML private MenuItem menuItem_close;
	@FXML private MenuItem menuItem_exit;
	
	//Buttons within the Edit menu inside the MenuBar
	@FXML private MenuItem menuItem_timeline;
	@FXML private MenuItem menuItem_event;
	
	//Buttons within the Help menu inside the MenuBar
	@FXML private MenuItem menuItem_about;
	
	private TimelineModel timelineModel;
	
	@FXML
	public void initialize() {

	}
	
	/**
	 * /**
	 * Initializes the TimelineModel which this class will get data from when a
	 * new timeline is created or updated.
	 * 
	 * @param timelineModel - timelineModel object to send and recieve data from.
	 */
	public void initTimelineModel(TimelineModel timelineModel) {
		if (this.timelineModel != null) {
			throw new IllegalStateException("Model can only be initiated once");
		}
		
		this.timelineModel = timelineModel;
		
		//TODO: Add potential listeners here
		
	}
}
