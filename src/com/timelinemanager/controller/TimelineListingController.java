package com.timelinemanager.controller;

import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.model.TimelineModel;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controls the actions for the UI events related to the timeline listings area
 * of the application.
 * 
 * @author
 * @version 0.00.00
 * @name TimelineListingController.java
 */
public class TimelineListingController {

	// UI controls for the timeline listings area.
	@FXML
	private AnchorPane timelineListing_anchorpane;
	@FXML
	private Label Timelines_Headline_text;
	@FXML
	private AnchorPane t_listings_container_anchorpane;
	
	private ListView<Timeline> list = new ListView<Timeline>();
	private TimelineModel timelineModel;

	@FXML
	public void initialize() {
		timelineListing_anchorpane.setPrefSize(160, 478);
		timelineListing_anchorpane.setMinSize(160, 478);
		timelineListing_anchorpane.getChildren().add(list);
		list.setId("timelineManagerTimelineListingList");
		list.setPrefSize(160, 478);
		list.setMinSize(160, 478);
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
		
		//Set display settings for the ListView with timelines.
		list.setCellFactory(lv -> {
            		ListCell<Timeline> cell = new ListCell<Timeline>() {
            			@Override
    				public void updateItem(Timeline result, boolean empty) {
    					super.updateItem(result, empty);

    					if (empty) {
    						setText(null);					
    					} else {
    					
    					setText(result.getTitle());
    					}
    				}
            		};
            
            		cell.setOnMouseEntered(showTimelineDescription -> {
   			 	if (cell.getItem() != null) {
   					Tooltip timelineDescription = new Tooltip(cell.getItem().getDescription());
   					timelineDescription.setMaxHeight(500);
   					timelineDescription.setMaxWidth(500);
   					timelineDescription.setWrapText(true);
   					cell.setTooltip(timelineDescription);
   				}
   			});
            		cell.setOnMouseExited(end -> {
            			end.consume();
            		});

            		return cell ;
        	});
		
		//Changes active timeline on click.
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if (event.getClickCount() == 2) {
	            	Timeline currentItemSelected = list.getSelectionModel().getSelectedItem();
	            	
	            	if(currentItemSelected != null) {
			            timelineModel.setTimeline(currentItemSelected);
	            	}
	            }
	        }
	    });
		
		timelineModel.getLoadedTimelines().addListener(new ListChangeListener<Timeline>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Timeline> c) {
				list.setItems(timelineModel.getLoadedTimelines());
			}
		});
	}
}
