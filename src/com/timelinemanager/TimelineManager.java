package com.timelinemanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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
		//Boiler-plate code for Java-FX application.
		primaryStage.setTitle("Hello World!");
		
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 300, 200));
		primaryStage.show();
	}

}
