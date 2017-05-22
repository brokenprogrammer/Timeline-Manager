package com.timelinemanager.Entity;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import jfxtras.scene.control.LocalTimePicker;

/**
 * EventEditor class to represent the pane for modify events in the
 * application.
 * 
 * @author Mohammed Basel Nasrini
 * @version 0.00.00
 * @name EventEditor.java
 */

public class EventEditor extends GridPane{
	
	private String title;
	private String description;
	private String picture ;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	
	private final TextField titleField = new TextField();
	private final TextField descriptionField = new TextField();
	private final LocalTimePicker eventStartTime = new LocalTimePicker();
	private final LocalTimePicker eventEndTime = new LocalTimePicker();
	private final DatePicker eventStartDate = new DatePicker();
	private final DatePicker eventEndDate = new DatePicker();
	Button cancel ;
	
	public EventEditor(String title,String description,String picture,LocalDate startDate,LocalDate endDate,LocalTime startTime,LocalTime endTime){
		
		eventStartDate.setId("datepickerStart");
		eventEndDate.setId("datepickerEnd");
		titleField.setId("eventEditorTitleField");
		descriptionField.setId("eventEditorDescriptionField");
		titleField.setText(title);
		this.add(titleField, 0, 0);
		titleField.setMinWidth(450);
		GridPane.setMargin(titleField, new Insets(50, 0, 0, 80));

		descriptionField.setText(description);
		descriptionField.setMinHeight(130);
		descriptionField.setMinWidth(280);
		descriptionField.setMaxHeight(130);
		descriptionField.setMaxWidth(280);
		GridPane.setMargin(descriptionField, new Insets(15, 0, 0, 80));

		this.add(descriptionField, 0, 1);
		
		
		if (picture == null){
		Label clickToAdd = new Label ("Click to add picture");
		this.add(clickToAdd, 0, 1);
		GridPane.setMargin(clickToAdd, new Insets(40, 0, 30, 360));

		clickToAdd.setOnMouseClicked(addImage -> {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Image File");

			// Set extension filter
			FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
			FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
			fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

			File file = fileChooser.showOpenDialog(null);
			if(file != null){
				Image image = new Image(file.toURI().toString());
			
				ImageView imageView = new ImageView ();
				imageView.setImage(image);
				imageView.setPreserveRatio(true);
				imageView.setFitWidth(189.0);
				imageView.setFitHeight(128.0);
				imageView.setSmooth(true);
				imageView.setCache(true);
				clickToAdd.setVisible(false);
				this.picture = file.toURI().toString();
			
				System.out.println(this.picture);
				GridPane.setMargin(imageView, new Insets(15, 0, 0, 360));
				this.add(imageView, 0, 1);
			}
		});
		
		}
		else if (picture != null){
			Image image = new Image(picture);
			ImageView imageView = new ImageView ();
			imageView.setImage(image);
			imageView.setPreserveRatio(true);
			imageView.setFitWidth(189.0);
			imageView.setFitHeight(128.0);
			imageView.setSmooth(true);
			imageView.setCache(true);
			this.picture = picture ;
			GridPane.setMargin(imageView, new Insets(15, 0, 0, 360));
			this.add(imageView, 0, 1);
		}
		eventStartTime.setLocalTime(startTime);
		this.add(eventStartTime, 0, 2);
		eventStartTime.setMinWidth(140);
		eventStartTime.setMaxWidth(140);
		
		GridPane.setMargin(eventStartTime, new Insets(15, 0, 0, 90));

		eventEndTime.setLocalTime(endTime);
		this.add(eventEndTime, 0, 2);
		eventEndTime.setMinWidth(140);
		eventEndTime.setMaxWidth(140);

		GridPane.setMargin(eventEndTime, new Insets(15, 0, 0, 360));

		eventStartDate.setValue(startDate);
		this.add(eventStartDate, 0, 3);
		GridPane.setMargin(eventStartDate, new Insets(15, 0, 0, 90));

		eventEndDate.setValue(endDate);
		this.add(eventEndDate, 0, 3);
		GridPane.setMargin(eventEndDate, new Insets(15, 0, 0, 360));

		this.setStyle("-fx-background-color: #B0C4DE;");
		this.setMinSize(600	, 300);
	}
	
	/**
	 * return the title 
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * return the path of the picture
	 */
	public String getPicture(){
		return this.picture;
	}
	
	/**
	 * return the description
	 */
	public String getDescription(){
		return this.description;
		
	}
	/**
	 * return the startDate of the event
	 */
	public LocalDate getStartDate(){
		return this.startDate;
		
	}
	
	/**
	 * return the endDate of the event
	 */
	public LocalDate getEndDate(){
		return this.endDate;
	}
	
	/**
	 * return the startTime of the event
	 */
	public LocalTime getStartTime(){
		return this.startTime;
		
	}
	

	/**
	 * return the endTime of the event
	 */
	public LocalTime getEndTime(){
		return this.endTime;
	}
	
	/**
	 * set the title 
	 */
	public void setTitle(){
		this.title = titleField.getText();
	}
	
	/**
	 * set the path of the picture
	 */
	public void setPicture(String picture){
		this.picture = picture;
	}
	
	/**
	 * set the description
	 */
	public void setDescription(){
		this.description = descriptionField.getText();
		
	}
	
	/**
	 * set the startDate of the event
	 */
	public void setStartDate(){
		this.startDate = eventStartDate.getValue();
		
	}
	
	/**
	 * set the endDate of the event
	 */
	public void setEndDate(){
		this.endDate = eventEndDate.getValue();
	}
	
	/**
	 * set the startTime of the event
	 */
	public void setStartTime(){
		this.startTime = eventStartTime.getLocalTime();
		
	}
	
	/**
	 * set the endTime of the event
	 */
	public void setEndTime(){
		this.endTime = eventEndTime.getLocalTime();
	}

}
