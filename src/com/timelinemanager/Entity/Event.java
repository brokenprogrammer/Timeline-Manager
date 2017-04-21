package com.timelinemanager.Entity;

import java.time.*;

/**
 * Entity class to represent the Event object in the
 * application.
 * 
 * @author 
 * @version 0.00.00
 * @name Event.java
 */
public class Event {
	
	private String title;
	private String description;
	private String picture ;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	
	public Event (String ti , String des , LocalDate inStartDate , LocalDate inEndDate , LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
	}
	
	public Event (String ti , String des , String pic , LocalDate inStartDate , LocalDate inEndDate , LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
		picture = pic ;
	}

	public Event (String ti , String des , String pic){
		title = ti;
		description = des ;
		picture = pic ;
	}
	
	public Event (String ti , String des ){
		title = ti;
		description = des ;
	}
	
	
	public void setTitle(String in){
		title = in ;
	}
	
	public void setDescription(String in){
		description = in ;
	}
	
	public void setStartDate(LocalDate in){
		startDate = in ;
	}
	
	public void setEndDAte(LocalDate in){
		endDate = in ;
	}
	
	public void setStartTime(LocalTime in){
		startTime = in ;
	}

	public void setEndDate(LocalTime in){
		endTime = in ;
	}
	
	public void setPic (String in){
		picture = in ;
	}
	
	public String getTitle(){
		return title ;
	}
	
	public String getDescription(){
		return description ;
	}
	
	public LocalDate getStartDate(){
		return startDate ;
	}
	
	public LocalDate getEndDate(){
		return endDate ;
	}
	
	public LocalTime getStartTime(){
		return startTime ;
	}
	
	public LocalTime getEndTime(){
		return endTime ;
	}
	
	
	public String getPic (){
		return picture ;
	}
	
	@Override
	public String toString(){
		return title ;
	}
}
