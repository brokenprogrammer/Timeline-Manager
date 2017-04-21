package com.timelinemanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.timelinemanager.model.TimelineModel;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
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
public class NavigationController implements ActionListener { 
	
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
	
	private TimelineModel timelineModel;
	
	/*
	 * when a create timeline button is created 
	 * a form should appear and when filled in 
	 * it should create a timeline object
	 * 
	 * write constructor;
	 * inject FXML files in constructor;
	 * call initialize() method at the end;
	 */
	
	public NavigationController(){
		
	}
	
	@FXML
	public void initialize() {

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
		
		
		
		menuItem_exit.setOnAction(actionEvent -> System.exit(0));    
		//TODO: Add potential listeners here
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
        System.exit(0);

	}
}
