package com.timelinemanager.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TimeLine {
	
	private String title;
	private String description;
	private String picture ;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	
	private ArrayList<Event> arr = new ArrayList<Event>();
	
	public TimeLine (String ti , String des , String pic, LocalDate inStartDate , LocalDate inEndDate , LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
		picture = pic ;
	}
	
	public TimeLine (String ti , String des , LocalDate inStartDate , LocalDate inEndDate , LocalTime inStartTime , LocalTime inEndTime){
		title = ti;
		description = des ;
		startDate = inStartDate ;
		endDate = inEndDate ;
		startTime = inStartTime ;
		endTime = inEndTime ;
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
	
	public void addEvent (Event in){
		arr.add(in);
	}
	
	public void deleteEvent (Event in){
		arr.remove(in);
	}
	
	public String toString(){
		return title ;
	}
	
}
