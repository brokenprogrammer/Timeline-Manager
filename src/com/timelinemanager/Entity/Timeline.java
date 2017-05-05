package com.timelinemanager.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

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
	public ArrayList<Event> eventArr = new ArrayList<Event>();	
	private int span ;	
	public ArrayList<Integer> bigArr = new ArrayList<Integer> ();
	public BoxLink timelineGrid;
	public Slider timelineSlider;
	public EventBoxLink eventGrid ;
	
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
		this.setEventGrid();
	}
	
	/**
	 * Delete an event from the timeline
	 * @param in An event
	 */
	public void deleteEvent (Event in){
		eventArr.remove(in);
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
		return title ;
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
		this.getChildren().add(eventGrid);		
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
		
		if (diffDays >= 14){
		timelineSlider = new Slider();
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
}
