package com.timelinemanager;

import java.io.IOException;

import com.timelinemanager.controller.NavigationController;
import com.timelinemanager.controller.TimelineController;
import com.timelinemanager.controller.TimelineListingController;
import com.timelinemanager.model.TimelineModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
	
	private TimelineModel timelineModel;
	private Stage mainStage;
	
	public void showTimelineManager(Stage primaryStage) throws IOException {
		mainStage = primaryStage;
		mainStage.setTitle("TimelineManager");
		
		timelineModel = new TimelineModel();
		
		VBox navigationLayout = (VBox) loadLayout(NAVIGATION_LAYOUT);
		AnchorPane timelinesListingLayout = (AnchorPane) loadLayout(TIMELINES_LISTING_LAYOUT);
		AnchorPane timelineLayout = (AnchorPane) loadLayout(TIMELINE_LAYOUT); 
		BorderPane rootLayout = (BorderPane) loadLayout(ROOT_LAYOUT);
		
		rootLayout.setTop(navigationLayout);
		rootLayout.setLeft(timelinesListingLayout);
		rootLayout.setCenter(timelineLayout);
		
		Scene mainScene = new Scene(rootLayout);
		mainStage.getIcons().add(new Image("/view/img/appicon.PNG"));
		mainScene.getStylesheets().add(getClass().getResource("/view/style.css").toString());
		mainStage.setOnCloseRequest(onExitCloseEverything -> System.exit(0));
		mainStage.setScene(mainScene);
		mainStage.setMinWidth(800);
		mainStage.setMinHeight(600);
    mainStage.show();
	}
	
	/**
	 * Loads a FXML layout and returns its parent node.
	 * 
	 * @param layout The *.fxml file to be loaded.
	 * @return The {@link Parent} object that is in the layout.
	 * @throws IOException thrown if the *.fxml file was not found.
	 */
	private Parent loadLayout(String layout) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(getClass().getResource(layout));
		Parent nodeLayout = loader.load();
		//Store controller of target layout
		//controllers.put(layout, loader.getController());
		
		//Initialize the same timeline model into every controller.
		if(loader.getController() != null) {
			if(loader.getController().getClass() == NavigationController.class) {
				NavigationController n = (NavigationController) loader.getController();
				n.initTimelineModel(this.timelineModel);
			} else if (loader.getController().getClass() == TimelineController.class) {
				TimelineController n = (TimelineController) loader.getController();
				n.initTimelineModel(this.timelineModel);
			} else if (loader.getController().getClass() == TimelineListingController.class) {
				TimelineListingController n = (TimelineListingController) loader.getController();
				n.initTimelineModel(this.timelineModel);
			}
		}
		
		return nodeLayout;
	}
}
