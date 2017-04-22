package com.timelinemanager.Entity;

import java.time.*;

/**
 * <code>Entity</code> class to represent the <code>Event</code> object in the
 * application.
 * 
 * @author Mohammed Basel Nasrini
 * @version 1.00.00
 * @name Event.java
 */
public class Event {
	
	private String title;
	private String description;
	private String picture ;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	
	/**
	 *  Create an event with duration and without picture
	 *   
	 * @param ti Title
	 * @param des Description
	 * @param inStartDate Start date
	 * @param inEndDate	End date
	 * @param inStartTime Start time
	 * @param inEndTime End time
	 */
	public Event (String ti , String des , LocalDate inStartDate , LocalDate inEndDate , LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
	}
	
	/**
	 * Create an event with duration and picture
	 * 
	 * @param ti Title
	 * @param des Description
	 * @param pic Picture
	 * @param inStartDate Start date
	 * @param inEndDate	End date
	 * @param inStartTime Start time
	 * @param inEndTime End time
	 */
	public Event (String ti , String des , String pic , LocalDate inStartDate , LocalDate inEndDate , LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
		picture = pic ;
	}
	
	/**
	 * Create an event without duration and picture
	 * 
	 * @param ti Title
	 * @param des Description
	 * @param inStartDate The date
	 * @param inStartTime The time
	 */
	public Event (String ti , String des , LocalDate inDate , LocalTime inTime){
		title = ti;
		description = des ;
		startDate = inDate ;
		startTime = inTime ;
	}

	/**
	 * Create an event without duration and with picture
	 * 
	 * @param ti Title
	 * @param des Description
	 * @param pic Picture
	 * @param inStartDate The date
	 * @param inStartTime The time
	 */
	public Event (String ti , String des , String pic , LocalDate inDate , LocalTime inTime){
		title = ti;
		description = des ;
		picture = pic ;
		startDate = inDate ;
		startTime = inTime ;
	}
	
	/**
	 * Add a title to the event
	 * @param in The event title
	 */
	public void setTitle(String in){
		title = in ;
	}
	
	/**
	 * Add a description to the event
	 * @param in The event description
	 */
	public void setDescription(String in){
		description = in ;
	}
	
	/**
	 * Add a start date to the event
	 * @param in The event start date
	 */
	public void setStartDate(LocalDate in){
		startDate = in ;
	}
	
	/**
	 * Add an end date to the event
	 * @param in The event end date
	 */
	public void setEndDate(LocalDate in){
		endDate = in ;
	}
	
	/**
	 * Add a start time to the event
	 * @param in The event start time
	 */
	public void setStartTime(LocalTime in){
		startTime = in ;
	}

	/**
	 * Add a end time to the event
	 * @param in The event end time
	 */
	public void setEndTime(LocalTime in){
		endTime = in ;
	}
	
	/**
	 * Add a picture date to the event
	 * @param in The event picture
	 */
	public void setPic (String in){
		picture = in ;
	}
	
	/**
	 *  Get the event title
	 * @return The title
	 */
	public String getTitle(){
		return title ;
	}
	
	/**
	 *  Get the event description
	 * @return The description
	 */
	public String getDescription(){
		return description ;
	}
	
	/**
	 *  Get the event start date
	 * @return The start date
	 */
	public LocalDate getStartDate(){
		return startDate ;
	}
	
	/**
	 *  Get the event end date
	 * @return The end date
	 */
	public LocalDate getEndDate(){
		return endDate ;
	}
	
	/**
	 *  Get the event start time
	 * @return The start date
	 */
	public LocalTime getStartTime(){
		return startTime ;
	}
	
	/**
	 *  Get the event end time
	 * @return The end time
	 */
	public LocalTime getEndTime(){
		return endTime ;
	}
	
	/**
	 *  Get the event picture
	 * @return The picture
	 */
	public String getPic (){
		return picture ;
	}
	
	@Override
	public String toString(){
		return title ;
	}
}
