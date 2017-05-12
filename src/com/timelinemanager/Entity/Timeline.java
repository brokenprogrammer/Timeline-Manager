package com.timelinemanager.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <code>Entity</code> class to represent the <code>Timeline</code> object in the
 * application.
 * 
 * @author Mohammed Basel Nasrini
 * @version 0.00.00
 * @name Timeline.java
 */
public class Timeline extends StackPane{
	
	private String title;
	private String description;
	private String picture ;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	private final ObservableList<Event> events = FXCollections.observableArrayList();
	public ArrayList<Event> eventArr = new ArrayList<Event>();	
	private int span ;	
	public ArrayList<Integer> bigArr = new ArrayList<Integer> ();

	public BoxLink timelineGrid;
	public Slider timelineSlider = new Slider();
	public EventBoxLink eventGrid ;
	private int index = -1;
	private EventEditor editor;
	private Button Changebt = new Button("Change");
	private Button cancel = new Button("Cancel");
	private Button delete = new Button("delete");
	
	/**
	 * Create an empty timeline
	 */
	public Timeline(){		
	}
	
	/**
	 * Create a timeline with picture
	 * 
	 * @param ti timeline title
	 * @param des timeline description
	 * @param pic timeline picture
	 * @param inStartDate timeline start date
	 * @param inEndDate timeline end date
	 * @param inStartTime timeline start time
	 * @param inEndTime timeline end time
	 * @param inspan the length of timeline to be shown on the screen
	 */
	public Timeline (String ti , String des , String pic, LocalDate inStartDate , LocalDate inEndDate, LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
		picture = pic ;
		setTimelineView();
	}	
	
	/**
	 * Create a timeline without picture
	 * 
	 * @param ti timeline title
	 * @param des timeline description
	 * @param inStartDate timeline start date
	 * @param inEndDate timeline end date
	 * @param inStartTime timeline start time
	 * @param inEndTime timeline end time
	 */
	
	public Timeline (String ti , String des , LocalDate inStartDate , LocalDate inEndDate , LocalTime inStartTime , LocalTime inEndTime ){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime;
		endTime = inEndTime;
		setTimelineView();
	}
	
	/**
	 * Add a title to the timeline
	 * @param in The timeline title
	 */
	public void setTitle(String in){
		title = in ;
	}
	
	/**
	 * Add a description to the timeline
	 * @param in The timeline description
	 */
	public void setDescription(String in){
		description = in ;
	}
	
	/**
	 * Add a start date to the timeline
	 * @param in The timeline start date
	 */
	public void setStartDate(LocalDate in){
		startDate = in ;
	}
	
	/**
	 * Add an end date to the timeline
	 * @param in The timeline end date
	 */
	public void setEndDate(LocalDate in){
		endDate = in ;
	}
	
	/**
	 * Add a start time to the timeline
	 * @param in The timeline start time
	 */
	public void setStartTime(LocalTime in){
		startTime = in ;
	}
	
	/**
	 * Add an end time to the timeline
	 * @param in The timeline end time
	 */
	public void setEndTime(LocalTime in){
		endTime = in ;
	}
	
	/**
	 * Add a picture date to the timeline
	 * @param in The timeline picture
	 */
	public void setPic (String in){
		picture = in ;
	}
	
	/**
	 *  Get the timeline title
	 * @return The title
	 */
	public String getTitle(){
		return title ;
	}
	
	/**
	 *  Get the timeline description
	 * @return The description
	 */
	public String getDescription(){
		return description ;
	}
	
	/**
	 *  Get the timeline start date
	 * @return The start date
	 */
	public LocalDate getStartDate(){
		return startDate ;
	}
	
	/**
	 *  Get the timeline end date
	 * @return The end date
	 */
	public LocalDate getEndDate(){
		return endDate ;
	}
	
	/**
	 *  Get the timeline start time
	 * @return The start time
	 */
	public LocalTime getStartTime(){
		return startTime ;
	}
	
	/**
	 *  Get the timeline end time
	 * @return The end time
	 */
	public LocalTime getEndTime(){
		return endTime ;
	}
	
	/**
	 *  Get the timeline picture
	 * @return The picture
	 */
	public String getPic (){
		return picture ;
	}
	
	/**
	 * Add an event to the timeline
	 * @param in An event
	 */
	public void addEvent (Event in){
		eventArr.add(in);
		events.setAll(eventArr);
		this.setEventGrid();
	}
	
	/**
	 * Delete an event from the timeline
	 * @param in An event
	 */
	public void deleteEvent (Event in){
		eventArr.remove(in);
		events.setAll(eventArr);
		this.setEventGrid();
	}
	
	/**
	 * Calculate the the length of the timeline by days
	 * @return The number of days
	 */
	public long getDaysLength(){
		return ChronoUnit.DAYS.between(startDate, endDate) + 1 ; 
	}
	
	/**
	 * Calculate the the length of the timeline by months
	 * @return The number of months
	 */
	public long getMonthsLength(){
		return ChronoUnit.MONTHS.between(startDate, endDate) +1 ;
	}
	
	/**
	 * Calculate the the length of the timeline by years
	 * @return The number of years
	 */
	public long getYearsLength(){
		return ChronoUnit.YEARS.between(startDate, endDate) +1 ;
	}
	
	/**
	 * Get the timeline start Date and Time 
	 * @return The start Date and Time 
	 */
	public LocalDateTime getStart(){
		return this.startDate.atTime(this.startTime);
	}
	
	/**
	 * Get the timeline end Date and Time 
	 * @return The end Date and Time 
	 */
	public LocalDateTime getEnd(){
		return this.endDate.atTime(this.endTime);
	}	
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[Timeline]" + "\n");
		sb.append("Title:" + this.getTitle() + "\n");
		sb.append("Description:" + this.getDescription() + "\n");
		sb.append("Start Date:" + this.getStartDate().toString() + "\n");
		sb.append("End Date:" + this.getEndDate().toString() + "\n");
		sb.append("\n");
		
		for (Event e : this.eventArr) {
			sb.append("[Event]" + "\n");
			sb.append(e.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}	
	
	/**
	 * Add event's elements to the timeline.
	 */
	public void setEventGrid(){
		eventGrid.setEventBoxLink(getStart() , span , eventArr );
		
		LocalDateTime temp = getStart();
		if (this.getDaysLength() <= 14)
			eventGrid.setEventBoxLink(getStart() , span , eventArr );
		else{
		timelineSlider.valueProperty().addListener(
				property ->{
					eventGrid.getChildren().clear();
				int plus =  (int) Math.round(timelineSlider.getValue());
				eventGrid.setEventBoxLink( temp.plusDays(plus) , span, eventArr );});
		}			
	}	
	
	/**
	 * Create a timeline view with the scroll controller and timeline chain.
	 */
	public void setTimelineView() { 
		if (this.getDaysLength() < 14){
			span = (int) this.getDaysLength()   ;
		}		
		else{
			span = 14 ;
		}		
		
		
		eventGrid = new EventBoxLink (getStart() , span );
		StackPane.setAlignment(eventGrid, Pos.CENTER);
			
		this.setMinSize(300, 300);

		long diffDays = getDaysLength() -1 ;
		LocalDate temp = startDate ;
		while (temp.compareTo(endDate) < 1) {
			bigArr.add(temp.getDayOfMonth());
			temp = temp.plusDays(1);
		}
		
		timelineGrid = new BoxLink(0, span, bigArr);
		StackPane.setAlignment(timelineGrid, Pos.CENTER);
		this.getChildren().add(timelineGrid);
		this.getChildren().add(eventGrid);	
		if (diffDays >= 14){
		timelineSlider.setMin(0);
		timelineSlider.setMax(diffDays - span + 1);
		timelineSlider.setMaxWidth(1000);
		
		timelineSlider.valueProperty().addListener(
				property -> timelineGrid.setBoxLink((int) Math.round(timelineSlider.getValue()), span, bigArr));
		this.getChildren().add(timelineSlider);
		StackPane.setAlignment(timelineSlider, Pos.BOTTOM_CENTER);		
		}
	}
	
	/**
	 * Rebuilds the timeline view.
	 */
	public void reDraw() {
		this.getChildren().remove(eventGrid);
		this.getChildren().add(eventGrid);
		this.getChildren().remove(timelineGrid);
		this.getChildren().add(timelineGrid);
		this.getChildren().remove(timelineSlider);
		this.getChildren().add(timelineSlider);
		timelineGrid.setBoxLink((int) Math.round(timelineSlider.getValue()), span, bigArr);
	}
	
	/**
	 * Opens up a new window and lets you search for individual events
	 * and choose to modify or delete them.
	 */
	public void searchEvent() {
		GridPane pane_container = new GridPane();
		pane_container.setPadding(new Insets(10,10,10,10));
		pane_container.setHgap(10.0);
		pane_container.setVgap(10.0);
		
		HBox search_event_name = new HBox();
		TextField titleField = new TextField();
		titleField.setPromptText("Event Title");
		search_event_name.getChildren().add(titleField);
		Button event_search = new Button("Search");
		search_event_name.getChildren().add(event_search);
		
		//Make sure the observable event list is updated.
		events.setAll(eventArr);
		ListView<Event> eventList = new ListView<Event>();
		
		//Set display settings for the ListView with events.
		eventList.setCellFactory(lv -> new ListCell<Event>() {
			@Override
			public void updateItem(Event result, boolean empty) {
				super.updateItem(result, empty);
				if (empty) {
					setText(null);
				} else {
					setText(result.getTitle());
				}
			}
		});
		eventList.setItems(events);
		
		event_search.setOnAction(e -> {
			String searchWord = titleField.getText().toUpperCase();
			
			//If there is a search word then perform the search and populate a new list
			//with all the mathces.
			if (!searchWord.equals("") && searchWord != null) {
			    ObservableList<Event> subentries = FXCollections.observableArrayList();
			    for (Event event : events) {
			    	boolean match = true;
			    	String eventTitle = event.getTitle().toUpperCase();
			    	
			    	if (!eventTitle.contains(searchWord)) {
			    		match = false;
			    	}
			    	
			    	if (match) {
			    		subentries.add(event);
			    	}
			    }
			    eventList.setItems(subentries);
			} else {
				eventList.setItems(events);
			}
		});
		
		Button okButton = new Button("Ok");
		okButton.setOnAction(e -> {
			Event selectedEvent = eventList.getSelectionModel().getSelectedItem();
			editEvent(selectedEvent);
		});
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> {
			Alert closeConfirmation = new Alert(
					Alert.AlertType.CONFIRMATION,
					"Are you sure you want to cancel selecting an event?");
			cancel = (Button) closeConfirmation.getDialogPane().lookupButton(
					ButtonType.OK);
			closeConfirmation.setHeaderText("Confirm Cancel");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			if (!ButtonType.OK.equals(closeResponse.get())) {
				e.consume();
			}
			else
			{	
				((Node)(e.getSource())).getScene().getWindow().hide();
			}
		});
		
		HBox buttons_container = new HBox();
		buttons_container.setPadding(new Insets(10,10,10,10));
		buttons_container.setSpacing(10);
		buttons_container.getChildren().addAll(okButton,cancelButton);
		
		StackPane.setAlignment(search_event_name, Pos.TOP_LEFT);
		StackPane.setAlignment(buttons_container, Pos.BOTTOM_RIGHT);
		
		pane_container.add(search_event_name, 0, 0);
		pane_container.add(eventList, 0, 1);
		pane_container.add(buttons_container, 0, 2);
		
		Scene mainScene = new Scene(pane_container);
		Stage stage = new Stage();
		stage.setHeight(550);
		stage.setWidth(240);
		stage.setScene(mainScene);
		stage.setTitle("Select event");
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	/**
	 * set a pane and some action for modifying and deleting events
	 */
	public void editEvent(Event e){
		GridPane pane_container = new GridPane();
		pane_container.setPadding(new Insets(10,10,10,10));
		pane_container.setHgap(10.0);
		pane_container.setVgap(10.0);
		
		index = eventArr.indexOf(e);
		
		VBox editor_container = new VBox();
		editor_container.setPrefSize(600, 300);
		editor = new EventEditor(null,null,null,null,null,null,null);
		editor_container.getChildren().add(editor);
		StackPane.setAlignment(editor_container, Pos.CENTER);
		cancel.setOnAction(cancelEvent -> {
			Alert closeConfirmation = new Alert(
					Alert.AlertType.CONFIRMATION,
					"Are you sure you want to cancel modifying an event?");
			cancel = (Button) closeConfirmation.getDialogPane().lookupButton(
					ButtonType.OK);
			closeConfirmation.setHeaderText("Confirm Exit");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			if (!ButtonType.OK.equals(closeResponse.get())) {
				cancelEvent.consume();
			}
			else
			{	
				((Node)(cancelEvent.getSource())).getScene().getWindow().hide();
			}
		});
		
		
		editor_container.getChildren().remove(editor);
		editor = new EventEditor(e.getTitle(),e.getDescription(), e.getPic(), e.getStartDate(), e.getEndDate(), e.getStartTime(), e.getStartTime());
		
		
		Changebt.setOnAction(changeInformation->{
			editor.setDescription();
			editor.setTitle();
			editor.setStartDate();
			editor.setStartTime();
			editor.setEndDate();
			editor.setEndTime();
			Alert closeConfirmation = new Alert(
					Alert.AlertType.CONFIRMATION,
					"Are you sure you want to modify this event?");
			closeConfirmation.setHeaderText("Confirm Exit");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			if (ButtonType.OK.equals(closeResponse.get())) {
				eventArr.get(index).setTitle(editor.getTitle()); 
				eventArr.get(index).setDescription(editor.getDescription());
				eventArr.get(index).setPic(editor.getPicture());
				eventArr.get(index).setStartDate(editor.getStartDate());
				eventArr.get(index).setStartTime(editor.getStartTime());
				eventArr.get(index).setEndDate(editor.getEndDate());
				eventArr.get(index).setEndTime(editor.getEndTime());
				events.setAll(eventArr);
				double a = timelineSlider.getValue();
				timelineSlider.setValue(a+0.1);
				((Node) (changeInformation.getSource())).getScene().getWindow().hide();
			}
			else
			{	
				changeInformation.consume();
				
			}
		});
		
		delete.setOnAction(deleteEvent->{
			Alert Confirmation = new Alert(
					Alert.AlertType.CONFIRMATION,
					"Are you sure you want to delete this event?");
			Confirmation.setHeaderText("Confirm Exit");
			Confirmation.initModality(Modality.APPLICATION_MODAL);
			Optional<ButtonType> Response = Confirmation.showAndWait();
			if (ButtonType.OK.equals(Response.get())) {
				deleteEvent(e);
				double a = timelineSlider.getValue();
				timelineSlider.setValue(a+0.1);
				((Node) (deleteEvent.getSource())).getScene().getWindow().hide();
			}
			else
			{	
				deleteEvent.consume();
			}
		});

		StackPane.setAlignment(editor, Pos.CENTER);
		editor_container.getChildren().add(editor);
		
		HBox buttons_container = new HBox();
		buttons_container.setPadding(new Insets(10,10,10,10));
		buttons_container.setSpacing(10);
		buttons_container.getChildren().addAll(Changebt,delete,cancel);
		
		StackPane.setAlignment(buttons_container, Pos.BOTTOM_RIGHT);
		
		pane_container.add(editor_container, 0, 0);
		GridPane.setMargin(buttons_container, new Insets(0, 0, 0, 360));
		pane_container.add(buttons_container, 0, 1);
		
		Scene mainScene = new Scene(pane_container);
		Stage stage = new Stage();
		stage.setHeight(500);
		stage.setWidth(620);
		stage.setScene(mainScene);
		stage.setTitle("Event Editor");
		stage.setResizable(false);
		stage.showAndWait();
	}
}
