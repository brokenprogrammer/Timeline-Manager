package com.timelinemanager;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point of this application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name TimelineManager.java
 */
public class TimelineManager extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageManager sm = new StageManager();
		sm.showTimelineManager(primaryStage);
	}

}
