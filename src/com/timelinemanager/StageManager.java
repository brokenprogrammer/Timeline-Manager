package com.timelinemanager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Manages the loading and changes of the stage.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Stagemanager.java
 */
public class StageManager {
	
	private final String ROOT_LAYOUT = "/view/RootLayout.fxml";
	private final String NAVIGATION_LAYOUT = "/view/NavigationLayout.fxml";
	private final String TIMELINE_LAYOUT = "/view/TimelineLayout.fxml";
	private final String TIMELINES_LISTING_LAYOUT = "/view/TimelinesListingLayout.fxml";
	
	
	private Stage mainStage;
	
	public void showTimelineManager(Stage primaryStage) throws IOException {
		mainStage = primaryStage;
		mainStage.setTitle("TimelineManager");
		
		//TODO: Inside fxml files add fx:controller back
		//TODO: Fix size on elements in fxml
		VBox navigationLayout = (VBox) loadLayout(NAVIGATION_LAYOUT);
		VBox timelinesListingLayout = (VBox) loadLayout(TIMELINES_LISTING_LAYOUT);
		VBox timelineLayout = (VBox) loadLayout(TIMELINE_LAYOUT);
		BorderPane rootLayout = (BorderPane) loadLayout(ROOT_LAYOUT);
		
		rootLayout.setTop(navigationLayout);
		rootLayout.setLeft(timelinesListingLayout);
		rootLayout.setCenter(timelineLayout);
		
		Scene mainScene = new Scene(rootLayout);
		
		mainStage.setScene(mainScene);
		mainStage.setMinWidth(1080);
		mainStage.setMinHeight(900);
        mainStage.show();
	}
	
	/**
	 * 
	 * @param layout The *.fxml file to be loaded.
	 * 
	 * @return The {@link Parent} object that is in the layout.
	 * 
	 * @throws IOException thrown if the *.fxxml file was not found.
	 */
	private Parent loadLayout(String layout) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(getClass().getResource(layout));
		Parent nodeLayout = loader.load();
		
		//Store controller of target layout
		//controllers.put(layout, loader.getController());
		
		return nodeLayout;
	}
}
