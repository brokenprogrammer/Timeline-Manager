package com.timelinemanager.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <code>Entity</code> class to represent the <code>Timeline</code> object in
 * the application.
 * 
 * @author Mohammed Basel Nasrini
 * @version 0.00.00
 * @name Timeline.java
 */
public class Timeline extends BorderPane {
	private String title;
	private String description;
	private String picture;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;

	// Observable list of events used for windows connected to modifying &
	// removing events.
	private final ObservableList<Event> events = FXCollections.observableArrayList();

	public ArrayList<Event> eventArr = new ArrayList<Event>();
	private int span;
	public ArrayList<Integer> bigArr = new ArrayList<Integer>();
	public ArrayList<Integer> monthBigArr = new ArrayList<Integer>();
	public ArrayList<Integer> yearBigArr = new ArrayList<Integer>();
	public ArrayList<Event> copyBigArr = new ArrayList<Event>();
	public ArrayList<Event> arrWithout = new ArrayList<Event>();
	public ArrayList<Event> shortEvent = new ArrayList<Event>();
	public ArrayList<Event> longEvent = new ArrayList<Event>();


	public BoxLink timelineGrid;
	public Slider timelineSlider = new Slider();
	public EventBoxLink eventGrid;
	private int index = -1;
	private EventEditor editor;

	// Buttons for the modify or remove event window.
	private Button Changebt = new Button("Change");
	private Button cancel = new Button("Cancel");
	private Button delete = new Button("delete");
	private Boolean dayLevel = true;
	private BorderPane timeline = new BorderPane();
	private ScrollPane timeline_container = new ScrollPane();

	/**
	 * Create an empty timeline
	 */
	public Timeline() {
		setId("timelineManagerTimeline");
		timelineSlider.setId("timelineManagerTimelineSlider");
	}

	/**
	 * Create a timeline with picture
	 * 
	 * @param ti
	 *            timeline title
	 * @param des
	 *            timeline description
	 * @param pic
	 *            timeline picture
	 * @param inStartDate
	 *            timeline start date
	 * @param inEndDate
	 *            timeline end date
	 * @param inStartTime
	 *            timeline start time
	 * @param inEndTime
	 *            timeline end time
	 * @param inspan
	 *            the length of timeline to be shown on the screen
	 */
	public Timeline(String ti, String des, String pic, LocalDate inStartDate, LocalDate inEndDate,
			LocalTime inStartTime, LocalTime inEndTime) {
		title = ti;
		description = des;
		startDate = inStartDate;
		endDate = inEndDate;
		startTime = LocalTime.MIDNIGHT;
		endTime = LocalTime.MAX;
		picture = pic;

		setId("timelineManagerTimeline");
		timelineSlider.setId("timelineManagerTimelineSlider");

		setTimelineView();
	}

	/**
	 * Create a timeline without picture
	 * 
	 * @param ti
	 *            timeline title
	 * @param des
	 *            timeline description
	 * @param inStartDate
	 *            timeline start date
	 * @param inEndDate
	 *            timeline end date
	 * @param inStartTime
	 *            timeline start time
	 * @param inEndTime
	 *            timeline end time
	 */

	public Timeline(String ti, String des, LocalDate inStartDate, LocalDate inEndDate, LocalTime inStartTime,
			LocalTime inEndTime) {
		title = ti;
		description = des;
		startDate = inStartDate;
		endDate = inEndDate;
		startTime = LocalTime.MIDNIGHT;
		endTime = LocalTime.MAX;

		setId("timelineManagerTimeline");
		timelineSlider.setId("timelineManagerTimelineSlider");

		setTimelineView();
	}

	/**
	 * Add a title to the timeline
	 * 
	 * @param in
	 *            The timeline title
	 */
	public void setTitle(String in) {
		title = in;
	}

	/**
	 * Add a description to the timeline
	 * 
	 * @param in
	 *            The timeline description
	 */
	public void setDescription(String in) {
		description = in;
	}

	/**
	 * Add a start date to the timeline
	 * 
	 * @param in
	 *            The timeline start date
	 */
	public void setStartDate(LocalDate in) {
		startDate = in;
	}

	/**
	 * Add an end date to the timeline
	 * 
	 * @param in
	 *            The timeline end date
	 */
	public void setEndDate(LocalDate in) {
		endDate = in;
	}

	/**
	 * Add a start time to the timeline
	 * 
	 * @param in
	 *            The timeline start time
	 */
	public void setStartTime(LocalTime in) {
		startTime = in;
	}

	/**
	 * Add an end time to the timeline
	 * 
	 * @param in
	 *            The timeline end time
	 */
	public void setEndTime(LocalTime in) {
		endTime = in;
	}

	/**
	 * Add a picture date to the timeline
	 * 
	 * @param in
	 *            The timeline picture
	 */
	public void setPic(String in) {
		picture = in;
	}

	/**
	 * Get the timeline title
	 * 
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the timeline description
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the timeline start date
	 * 
	 * @return The start date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Get the timeline end date
	 * 
	 * @return The end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Get the timeline start time
	 * 
	 * @return The start time
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * Get the timeline end time
	 * 
	 * @return The end time
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * Get the timeline picture
	 * 
	 * @return The picture
	 */
	public String getPic() {
		return picture;
	}

	/**
	 * Add an event to the timeline
	 * 
	 * @param in
	 *            An event
	 */
	public void addEvent(Event in) {
		eventArr.add(in);

		// Updates the observable list so is the same as the event array.
		events.setAll(eventArr);

		this.setEventGrid();
	}

	/**
	 * Delete an event from the timeline
	 * 
	 * @param in
	 *            An event
	 */
	public void deleteEvent(Event in) {
		eventArr.remove(in);

		// Updates the observable list so is the same as the event array.
		events.setAll(eventArr);

		this.setEventGrid();
	}

	/**
	 * Calculate the the length of the timeline by days
	 * 
	 * @return The number of days
	 */
	public long getDaysLength() {
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	/**
	 * Calculate the the length of the timeline by months
	 * 
	 * @return The number of months
	 */
	public long getMonthsLength() {
		return ChronoUnit.MONTHS.between(startDate, endDate) + 1;
	}

	/**
	 * Calculate the the length of the timeline by years
	 * 
	 * @return The number of years
	 */
	public long getYearsLength() {
		return ChronoUnit.YEARS.between(startDate, endDate) + 1;
	}

	/**
	 * Get the timeline start Date and Time
	 * 
	 * @return The start Date and Time
	 */
	public LocalDateTime getStart() {
		return this.startDate.atTime(this.startTime);
	}

	/**
	 * Get the timeline end Date and Time
	 * 
	 * @return The end Date and Time
	 */
	public LocalDateTime getEnd() {
		return this.endDate.atTime(this.endTime);
	}

	/**
	 * Converts this timeline object into a String.
	 * 
	 * @return String representation of the timeline object.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Timeline]" + "\n");
		sb.append("Title:" + this.getTitle() + "\n");
		sb.append("Description:" + this.getDescription() + "\n");
		sb.append("Start Date:" + this.getStartDate().toString() + "\n");
		sb.append("End Date:" + this.getEndDate().toString() + "\n");
		sb.append("\n");

		// Adds every event within the event array into the String
		// representation.
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
	public void setEventGrid() {
		ArrayList<Event> tem = new ArrayList<Event>();

		if (eventArr.size()>0){
			for(int i=0;i<eventArr.size();i++){
				tem.add(new Event(eventArr.get(i),"initial"));
				}
		}
		eventArr = tem;
		copyBigArr = new ArrayList<Event>();
		arrWithout = new ArrayList<Event>();
		shortEvent = new ArrayList<Event>();
		longEvent = new ArrayList<Event>();
		
		for (int m = 0; m < eventArr.size(); m++) {
			Event tempEve = new Event(eventArr.get(m));	
			
			if (tempEve.getEndDate() != null) {
				copyBigArr.add(tempEve);
			}
			
			if (tempEve.getEndDate() == null){
				arrWithout.add(tempEve);
			}
			else if (tempEve.getMonthsLength() > 1){
				longEvent.add(tempEve);

			}
			else if (tempEve.getMonthsLength() == 1) {
				shortEvent.add(tempEve);
			}
		}
		
		Comparator<Event> comp = (s1, s2) -> (s1.getStart()).compareTo(s2.getStart());
		Collections.sort(copyBigArr, comp);
		Collections.sort(shortEvent, comp);
		Collections.sort(longEvent, comp);
		
		if (dayLevel == true) {
			
			eventGrid.setEventBoxLink(getStart(), span, dayLevel, copyBigArr , arrWithout , shortEvent , longEvent);
			int level1 = eventGrid.getLevel() - 5;
			if (level1 <= 0)
				timeline.setMinHeight(300);
			else
				timeline.setMinHeight(300 + level1 * 35);
			
			LocalDateTime temp = getStart();
			if (this.getDaysLength() <= 14) {
				eventGrid.setEventBoxLink(getStart(), span, dayLevel, copyBigArr , arrWithout , shortEvent , longEvent);
				int leve2 = eventGrid.getLevel() - 5;
				if (leve2 <= 0)
					timeline.setMinHeight(300);
				else
					timeline.setMinHeight(300 + leve2 * 35);
			} else {
				timelineSlider.valueProperty().addListener(property -> {
					int plus = (int) Math.round(timelineSlider.getValue());
					eventGrid.setEventBoxLink(temp.plusDays(plus), span, dayLevel, copyBigArr , arrWithout , shortEvent , longEvent);
					int level3 = eventGrid.getLevel() - 5;
					if (level3 <= 0)
						timeline.setMinHeight(300);
					else
						timeline.setMinHeight(300 + level3 * 35);
				});
			}
		} else if (dayLevel == false) {
			eventGrid.setEventBoxLink(getStart(), span, dayLevel, copyBigArr , arrWithout , shortEvent , longEvent);
			int level1 = eventGrid.getLevel() - 5;
			if (level1 <= 0)
				timeline.setMinHeight(300);
			else
				timeline.setMinHeight(300 + level1 * 35);
			LocalDateTime temp = getStart();
			if (this.getMonthsLength() <= 7) {
				eventGrid.setEventBoxLink(getStart(), span, dayLevel, copyBigArr , arrWithout , shortEvent , longEvent);
				int level2 = eventGrid.getLevel() - 5;
				if (level2 <= 0)
					timeline.setMinHeight(300);
				else
					timeline.setMinHeight(300 + level2 * 35);
			} else {
				timelineSlider.valueProperty().addListener(property -> {
					int plus = (int) Math.round(timelineSlider.getValue());
					eventGrid.setEventBoxLink(temp.plusMonths(plus), span, dayLevel, copyBigArr , arrWithout , shortEvent , longEvent);
					int level3 = eventGrid.getLevel() - 5;
					if (level3 <= 0)
						timeline.setMinHeight(300);
					else
						timeline.setMinHeight(300 + level3 * 35);
				});
			}
		}
		
	}

	/**
	 * Create a timeline view with the scroll controller and timeline chain.
	 */
	public void setTimelineView() {
		this.setMinSize(1020, 300);
		this.setMaxWidth(1020);

		StackPane buttonsPane = new StackPane();
		buttonsPane.setMinSize(30, 300);
		Image upImg = new Image(getClass().getResourceAsStream("/view/img/Up.png"));
		buttonsPane.setMaxSize(20, 300);
		Button upBtn = new Button();
		upBtn.setId("upButton");
		upBtn.setGraphic(new ImageView(upImg));
		upBtn.setOnAction(e -> {
			dayLevel = false;
			timeline = new BorderPane();
			timeline.setMinSize(1000, 300);
			setTimelineView();
			setEventGrid();
		});

		StackPane.setMargin(upBtn, new Insets(200, 0, 150, 50));
		
		Image downImg = new Image(getClass().getResourceAsStream("/view/img/Down.png"));
		Button downBtn = new Button();
		downBtn.setId("downButton");
		downBtn.setGraphic(new ImageView(downImg));

		downBtn.setOnAction(e -> {
			dayLevel = true;
			timeline = new BorderPane();
			timeline.setMinSize(1000, 300);
			setTimelineView();
			setEventGrid();
		});

		StackPane.setMargin(downBtn, new Insets(200, 0, 50, 50));

		buttonsPane.getChildren().addAll(upBtn, downBtn);

		timeline = new BorderPane();
		timeline.setMinSize(1000, 300);

		if (dayLevel == true) {
			this.getChildren().clear();

			if (this.getDaysLength() < 14) {
				span = (int) this.getDaysLength();
			} else {
				span = 14;
			}

			long diffDays = getDaysLength() - 1;
			timelineGrid = new BoxLink(0, span, dayLevel, startDate, endDate);
			eventGrid = new EventBoxLink(getStart(), span, dayLevel);
			timelineGrid.setGridLinesVisible(true);

			timeline.getChildren().addAll(timelineGrid, eventGrid);

			if (diffDays >= 14) {
				timelineSlider.setMin(0);
				timelineSlider.setMax(diffDays - span + 1);
				timelineSlider.setMaxWidth(1000);
				timelineSlider.valueProperty().addListener(property -> {
					timelineGrid.setBoxLink((int) Math.round(timelineSlider.getValue()), span, dayLevel, startDate,
							endDate);
				});
				this.setBottom(timelineSlider);
			}
			timeline_container.setContent(timeline);
			timeline_container.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
			timeline_container.setHbarPolicy(ScrollBarPolicy.NEVER);
			timeline_container.setPrefSize(1000, 310);
			this.setRight(buttonsPane);
			this.setLeft(timeline_container);
		}

		else if (dayLevel == false) {
			this.getChildren().clear();

			if (this.getMonthsLength() < 7) {
				span = (int) this.getMonthsLength();
			} else {
				span = 7;
			}
			long diffMonths = getMonthsLength() - 1;

			timelineGrid = new BoxLink(0, span, dayLevel, startDate, endDate);
			eventGrid = new EventBoxLink(getStart(), span, dayLevel);

			timeline.getChildren().addAll(timelineGrid, eventGrid);

			if (diffMonths >= 7) {
				timelineSlider.setMin(0);
				timelineSlider.setMax(diffMonths - span + 1);
				timelineSlider.setMaxWidth(1000);
				timelineSlider.valueProperty().addListener(property -> {
					timelineGrid.setBoxLink((int) Math.round(timelineSlider.getValue()), span, dayLevel, startDate,
							endDate);
				});
				this.setBottom(timelineSlider);
			}
			timeline_container.setContent(timeline);
			timeline_container.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
			timeline_container.setHbarPolicy(ScrollBarPolicy.NEVER);
			timeline_container.setPrefSize(1000, 310);
			this.setRight(buttonsPane);
			this.setLeft(timeline_container);
		}
	}

	/**
	 * Opens up a new window and lets you search for individual events and
	 * choose to modify or delete them.
	 */
	public void searchEvent() {
		// Container for the entire search event window.
		GridPane pane_container = new GridPane();
		pane_container.getStyleClass().add("editEventPane_Container");
		pane_container.setPadding(new Insets(10, 10, 10, 10));
		pane_container.setHgap(10.0);
		pane_container.setVgap(10.0);

		// Container for the search bar and search button.
		HBox search_event_name = new HBox();
		search_event_name.getStyleClass().add("search_event_name_HBox");
		TextField titleField = new TextField();
		titleField.setPromptText("Event Title");
		search_event_name.getChildren().add(titleField);
		Button event_search = new Button("Search");
		event_search.getStyleClass().add("event_search_Button");
		search_event_name.getChildren().add(event_search);

		// Make sure the observable event list is updated.
		events.setAll(eventArr);

		// List representation of the events in this timeline.
		ListView<Event> eventList = new ListView<Event>();

		// Set display settings for the ListView with events.
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
		// Set the List representation to use the items in the observable list
		// of events.
		eventList.setItems(events);

		// Search button handler.
		event_search.setOnAction(e -> {
			String searchWord = titleField.getText().toUpperCase();

			// If there is a search word then perform the search and populate a
			// new list
			// with all the mathces.
			if (!searchWord.equals("") && searchWord != null) {
				// subentry list which contains all the search results.
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
				// If there was no search word then go back to using the entire
				// list of events.
				eventList.setItems(events);
			}
		});

		// Okay button handler.
		// The ok button is used to initiate the modify event action on target
		// event.
		Button okButton = new Button("Ok");
		okButton.setId("okButtonSearchEvent");
		okButton.setOnAction(e -> {
			if (eventList.getSelectionModel().getSelectedItem() != null) {
				// Get the event that is currently selected in the event list.
				Event selectedEvent = eventList.getSelectionModel().getSelectedItem();
				editEvent(selectedEvent);
			}
		});

		// Cancel button handler, asks if the user really want to stop the
		// selection of an event.
		Button cancelButton = new Button("Cancel");
		cancelButton.setId("cancelButtonSearchEvent");
		cancelButton.setOnAction(e -> {
			Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
					"Are you sure you want to cancel selecting an event?");
			DialogPane dialogPane = closeConfirmation.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
			cancel = (Button) closeConfirmation.getDialogPane().lookupButton(
					ButtonType.OK);
			closeConfirmation.setHeaderText("Confirm Cancel");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Stage stage = (Stage) closeConfirmation.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.png"));
			Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			if (!ButtonType.OK.equals(closeResponse.get())) {
				e.consume();
			} else {
				((Node) (e.getSource())).getScene().getWindow().hide();
			}
		});

		// Container for all the buttons.
		HBox buttons_container = new HBox();
		buttons_container.getStyleClass().add("buttonsContainerSearchEvent");
		buttons_container.setPadding(new Insets(10,10,10,10));
		buttons_container.setSpacing(10);
		buttons_container.getChildren().addAll(okButton, cancelButton);

		StackPane.setAlignment(search_event_name, Pos.TOP_LEFT);
		StackPane.setAlignment(buttons_container, Pos.BOTTOM_RIGHT);

		pane_container.add(search_event_name, 0, 0);
		pane_container.add(eventList, 0, 1);
		pane_container.add(buttons_container, 0, 2);

		// Build the window and display it.
		Scene mainScene = new Scene(pane_container);
		String style= getClass().getResource("/view/style.css").toExternalForm();
		mainScene.getStylesheets().add(style);
		Stage stage = new Stage();
		stage.setHeight(550);
		stage.setWidth(240);
		stage.setScene(mainScene);
		stage.setTitle("Select event");
		stage.setResizable(false);
		stage.getIcons().add(new Image("/view/img/appicon.png"));
		stage.showAndWait();
	}

	/**
	 * set a pane and some action for modifying and deleting events
	 * 
	 * @param e
	 *            - Event to populate the window with.
	 */
	public void editEvent(Event e) {
		// Container for the entire search event window.
		GridPane pane_container = new GridPane();
		pane_container.getStyleClass().add("editEventPane_Container");
		pane_container.setPadding(new Insets(10,10,10,10));
		pane_container.setHgap(10.0);
		pane_container.setVgap(10.0);

		// Set the index to the one of the target Event within our event array.
		index = eventArr.indexOf(e);

		// Container for the editor.
		VBox editor_container = new VBox();
		editor_container.getStyleClass().add("editEventPane_Container");
		editor_container.setPrefSize(600, 300);
		editor = new EventEditor(null, null, null, null, null, null, null);

		editor_container.getChildren().add(editor);
		StackPane.setAlignment(editor_container, Pos.CENTER);

		// Cancel button that asks for confirmation of the cancel of modifying
		// or deleting target event.
		cancel.setOnAction(cancelEvent -> {
			Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
					"Are you sure you want to cancel modifying an event?");
			DialogPane dialogPane = closeConfirmation.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
			cancel = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
			closeConfirmation.setHeaderText("Confirm Exit");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Stage stage = (Stage) closeConfirmation.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.png"));
			Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
			if (!ButtonType.OK.equals(closeResponse.get())) {
				cancelEvent.consume();
			} else {
				((Node) (cancelEvent.getSource())).getScene().getWindow().hide();
			}
		});

		// Remove the editor window and populate it with the specified event
		// data.
		editor_container.getChildren().remove(editor);
		editor = new EventEditor(e.getTitle(), e.getDescription(), e.getPic(), e.getStartDate(), e.getEndDate(),
				e.getStartTime(), e.getStartTime());

		// Change button confirms that the user really want to change this event
		// then modifies it directly in the event array.
		Changebt.setOnAction(changeInformation -> {
			editor.setDescription();
			editor.setTitle();
			editor.setStartDate();
			editor.setStartTime();
			editor.setEndDate();
			editor.setEndTime();
			Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
					"Are you sure you want to modify this event?");
			DialogPane dialogPane = closeConfirmation.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
			closeConfirmation.setHeaderText("Confirm Exit");
			closeConfirmation.initModality(Modality.APPLICATION_MODAL);
			Stage stage = (Stage) closeConfirmation.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.png"));
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
				timelineSlider.setValue(a + 0.1);
				((Node) (changeInformation.getSource())).getScene().getWindow().hide();
			} else {
				changeInformation.consume();

			}
		});


		// Delete button that confirms if the user really want to delete the
		// target event.
		delete.setOnAction(deleteEvent -> {
			Alert Confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this event?");
      			DialogPane dialogPane = Confirmation.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/view/style.css").toString());
			Confirmation.setHeaderText("Confirm Exit");
			Confirmation.initModality(Modality.APPLICATION_MODAL);
			Stage stage = (Stage) Confirmation.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("/view/img/appicon.png"));
			Optional<ButtonType> Response = Confirmation.showAndWait();
			if (ButtonType.OK.equals(Response.get())) {
				deleteEvent(e);
				double a = timelineSlider.getValue();
				timelineSlider.setValue(a + 0.1);
				((Node) (deleteEvent.getSource())).getScene().getWindow().hide();
			} else {
				deleteEvent.consume();
			}
		});

		StackPane.setAlignment(editor, Pos.CENTER);
		editor_container.getChildren().add(editor);

		HBox buttons_container = new HBox();
		buttons_container.getStyleClass().add("buttons_container_HBox");
		buttons_container.setPadding(new Insets(10, 10, 10, 10));
		buttons_container.setSpacing(10);
		buttons_container.getChildren().addAll(Changebt, delete, cancel);

		StackPane.setAlignment(buttons_container, Pos.BOTTOM_RIGHT);

		pane_container.add(editor_container, 0, 0);
		GridPane.setMargin(buttons_container, new Insets(0, 0, 0, 360));
		pane_container.add(buttons_container, 0, 1);

		// Build the window and display it.

		Scene mainScene = new Scene(pane_container);
		Changebt.setId("editorButton");
		cancel.setId("editorButton");
		delete.setId("editorButton");
		String style= getClass().getResource("/view/style.css").toExternalForm();
		mainScene.getStylesheets().add(style);
		Stage stage = new Stage();
		stage.setHeight(500);
		stage.setWidth(620);
		stage.setScene(mainScene);
		stage.setTitle("Event Editor");
		stage.setResizable(false);
		stage.getIcons().add(new Image("/view/img/appicon.png"));
		stage.showAndWait();
		this.setEventGrid();
	}
}
