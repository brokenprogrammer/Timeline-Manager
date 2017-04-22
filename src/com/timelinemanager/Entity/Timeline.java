package com.timelinemanager.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * <code>Entity</code> class to represent the <code>Timeline</code> object in the
 * application.
 * 
 * @author Mohammed Basel Nasrini
 * @version 0.00.00
 * @name Timeline.java
 */
public class Timeline {
	
	private String title;
	private String description;
	private String picture ;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	
	private ArrayList<Event> arr = new ArrayList<Event>();
	
	/**
	 * Create an empty timeline
	 */
	public Timeline () {
		
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
	 */
	public Timeline (String ti , String des , String pic, LocalDate inStartDate , LocalDate inEndDate, LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
		picture = pic ;
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
		arr.add(in);
	}
	
	/**
	 * Delete an event from the timeline
	 * @param in An event
	 */
	public void deleteEvent (Event in){
		arr.remove(in);
	}
	
	/**
	 * Calculate the the length of the timeline by days
	 * @return The number of days
	 */
	public long getDaysLength(){
		return ChronoUnit.DAYS.between(startDate, endDate) ;
	}
	
	/**
	 * Calculate the the length of the timeline by months
	 * @return The number of months
	 */
	public long getMonthsLength(){
		return ChronoUnit.MONTHS.between(startDate, endDate) ;
	}
	
	/**
	 * Calculate the the length of the timeline by years
	 * @return The number of years
	 */
	public long getYearsLength(){
		return ChronoUnit.YEARS.between(startDate, endDate) ;
	}
	
	@Override
	public String toString(){
		return title ;
	}
}
