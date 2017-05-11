package com.timelinemanager.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * <code>Entity</code> class to represent the <code>Event</code> object in the
 * application.
 * 
 * @author Mohammed Basel Nasrini
 * @version 0.00.00
 * @name Event.java
 */
public class Event extends StackPane {
	
	private String title;
	private String description;
	private String picture ;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	
	/**
	 * Create an empty event
	 */
	public Event () {
		
	}
	
	/**
	 *  Create an event with duration and without picture  
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
		setEventElement() ;
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
		
		if (inEndDate != null)
			setEventElement() ;
		else
			setEventWithoutDuraElement();

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
		setEventWithoutDuraElement() ;
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
		setEventWithoutDuraElement() ;

	}
	/**
	 * Create an event as a copy of the given event
	 * @param in An event
	 */
	public Event (Event in){
		this.title = in.title ;
		this.description = in.description ;
		this.startDate = in.startDate ;
		this.startTime = in.startTime ;	
		this.picture = in.picture ;
		this.endDate = in.endDate ;
		this.endTime = in.endTime ;
		
		if (in.getEndDate() != null)
			setEventElement() ;
		else
			setEventWithoutDuraElement();

	}
	
	/**
	 * Create an event as a copy of the given event has a different level in the timeline view
	 * @param in An event
	 * @param l The level
	 */
	public Event (Event in , int l){
		this.title = in.title ;
		this.description = in.description ;
		this.startDate = in.startDate ;
		this.startTime = in.startTime ;	
		if (in.picture != null)
			this.picture = in.picture ;
		
		if ( in.endDate != null ){
			this.endDate = in.endDate ;
			this.endTime = in.endTime ;
			setElementWithLevel(l) ;
		}else {
			setElementWithoutDuraWithLevel(l);

		}

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
	 * Calculate the the length of the Event by days
	 * @return The number of months
	 */
	public long getDaysLength(){
		return ChronoUnit.DAYS.between(startDate, endDate) + 1 ; 
	}
	
	/**
	 * Calculate the the length of the Event by months
	 * @return The number of months
	 */
	public long getMonthsLength(){
		return ChronoUnit.MONTHS.between(startDate, endDate) +1 ;
	}
	
	/**
	 * Calculate the the length of the event by years
	 * @return The number of years
	 */
	public long getYearsLength(){
		return ChronoUnit.YEARS.between(startDate, endDate) +1 ;
	} 
	
	/**
	 * Get the start date and time of the event
	 * @return The start date and time
	 */
	public LocalDateTime getStart(){
		return this.startDate.atTime(this.startTime);
	}
	
	/**
	 * Get the end date and time of the event
	 * @return The end date and time
	 */
	public LocalDateTime getEnd(){
		if (this.endDate!= null)
			return this.endDate.atTime(this.endTime);
		else
			return null ;
	}

	/**
	 * Checks if this event starts after the other event
	 * @param in the other event to compare to
	 * @return true if this event starts after the other event
	 */
	public boolean isAfter(Event in){
		LocalDateTime LDT = this.startDate.atTime(this.startTime);
		LocalDateTime inLDT = in.startDate.atTime(in.startTime);
		
		return LDT.isAfter(inLDT);
	}
	
	/**
	 * Checks if this event starts before the other event
	 * @param in the other event to compare to
	 * @return true if this event starts before the other event
	 */
	public boolean isBefor(Event in){
		LocalDateTime LDT = this.startDate.atTime(this.startTime);
		LocalDateTime inLDT = in.startDate.atTime(in.startTime);
		
		return LDT.isBefore(inLDT);
	}
	
	/**
	 * Checks if this event starts at the same time as the other event
	 * @param in the other event to compare to
	 * @return true if this event starts at the same time as the other event
	 */
	public boolean isEqual(Event in){
		LocalDateTime LDT = this.startDate.atTime(this.startTime);
		LocalDateTime inLDT = in.startDate.atTime(in.startTime);
		
		return LDT.isEqual(inLDT);
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
		StringBuilder sb = new StringBuilder();
		sb.append("Title:" + this.getTitle() + "\n");
		sb.append("Description:" + this.getDescription() + "\n");
		sb.append("Start Date:" + this.getStartDate().toString() + "\n");
		
		if (this.endDate != null) {
			sb.append("End Date:" + this.getEndDate().toString() + "\n");
		} else {
			sb.append("End Date:\n");
		}
		if (this.startTime != null) {
			sb.append("Start Time:" + this.getStartTime().toString() + "\n");
		} else {
			sb.append("Start Time:\n");
		}
		
		if (this.endTime != null) {
			sb.append("End Time:" + this.getEndTime().toString() + "\n");
		} else {
			sb.append("End Time:\n");
		}
		
		sb.append("\n");
		
		return sb.toString();
	}
	
	
	 final int level = 200;
	 
	 /**
	  * Create an event with duration UI element
	 */
	public void setEventElement(){
		int width = 0 ;
		
		LocalDateTime temp = this.getStart();
		while (temp.isBefore(this.getEnd())){
			temp = temp.plusHours(1);
			width += 3 ;
		}
		
				
		if (width > 80){			
			this.setMinSize(width,level);
			
			Rectangle rectangle = new Rectangle(width,20,Color.DARKCYAN);			
			StackPane.setAlignment(rectangle, Pos.BOTTOM_LEFT);
			
			Text text = new Text(this.getTitle());
			Font font = new Font("SansSerif", 20);
			text.setFont(font);
			text.setFill(Color.BLACK);
			StackPane.setAlignment(text, Pos.BOTTOM_CENTER);
			
			Line lineleft = new Line(0, 0,   0,   level-5);
			lineleft.setStrokeWidth(2);
			lineleft.getStrokeDashArray().addAll(6d, 15d);
			lineleft.setStrokeLineCap(StrokeLineCap.ROUND);
			StackPane.setAlignment(lineleft, Pos.TOP_LEFT);
			Line lineright = new Line(width-10, level -5 ,   width-10,   0);
			lineright.setStrokeWidth(2);
			lineright.getStrokeDashArray().addAll(6d, 15d);
			lineright.setStrokeLineCap(StrokeLineCap.ROUND);
			StackPane.setAlignment(lineright, Pos.TOP_RIGHT);		
			this.getChildren().addAll(rectangle, text ,lineleft, lineright);
		}
		else{
			this.setMinSize(width,level);
			
			Rectangle rectangle = new Rectangle(80,20,Color.DARKCYAN);			
			StackPane.setAlignment(rectangle, Pos.BOTTOM_LEFT);		
			
			Text text = new Text(this.getTitle());
			Font font = new Font("SansSerif", 20);
			text.setFont(font);
			text.setFill(Color.BLACK);
			StackPane.setAlignment(text, Pos.BOTTOM_LEFT);
			
			Line  lineleft = new Line(0, 0,   0,   level-5);
			lineleft.setStrokeWidth(2);
			lineleft.getStrokeDashArray().addAll(6d, 15d);
			lineleft.setStrokeLineCap(StrokeLineCap.ROUND);
			StackPane.setAlignment(lineleft, Pos.TOP_LEFT);
			Line lineright = new Line(width-10, level-5,   width-10,   0);
			lineright.setStrokeWidth(2);
			lineright.getStrokeDashArray().addAll(6d, 15d);
			lineright.setStrokeLineCap(StrokeLineCap.ROUND);
			StackPane.setAlignment(lineright, Pos.TOP_RIGHT);	
			
			this.getChildren().addAll(rectangle, text ,lineleft, lineright);
		}
		
	}
	
	/**
	  * Create an event with duration UI element if it places at another level in the screen
	 * @param a The level
	 */
	public void setElementWithLevel(int a){
		int newlevel = level ;
		newlevel = 70 * a + newlevel ;
		int width = 0 ;
		
			LocalDateTime temp = this.getStart();
			while (temp.isBefore(this.getEnd())){
				temp = temp.plusHours(1);
				width += 3 ;
			}
			
					
			if (width > 80){				
				this.setMinSize(width,newlevel);
				Rectangle rectangle = new Rectangle(width,20,Color.DARKCYAN);				
				StackPane.setAlignment(rectangle, Pos.BOTTOM_LEFT);
				
				Text text = new Text(this.getTitle());
				Font font = new Font("SansSerif", 20);
				text.setFont(font);
				text.setFill(Color.BLACK);
				StackPane.setAlignment(text, Pos.BOTTOM_CENTER);
				
				Line lineleft = new Line(0, 0,   0,   newlevel-5);
				lineleft.setStrokeWidth(2);
				lineleft.getStrokeDashArray().addAll(6d, 15d);
				lineleft.setStrokeLineCap(StrokeLineCap.ROUND);				
				StackPane.setAlignment(lineleft, Pos.TOP_LEFT);
				Line lineright = new Line(width-10, newlevel -5 ,   width-10,   0);
				lineright.setStrokeWidth(2);
				lineright.getStrokeDashArray().addAll(6d, 15d);
				lineright.setStrokeLineCap(StrokeLineCap.ROUND);

				StackPane.setAlignment(lineright, Pos.TOP_RIGHT);				
				this.getChildren().addAll(rectangle, text ,lineleft, lineright);
			}
			else{
				this.setMinSize(width,newlevel);				
				Rectangle rectangle = new Rectangle(80,20,Color.DARKCYAN);				
				StackPane.setAlignment(rectangle, Pos.BOTTOM_LEFT);				
				
				Text text = new Text(this.getTitle());
				Font font = new Font("SansSerif", 20);
				text.setFont(font);
				text.setFill(Color.BLACK);
				StackPane.setAlignment(text, Pos.BOTTOM_LEFT);
				
				Line lineleft = new Line(0, 0,   0,   newlevel-5);
				lineleft.setStrokeWidth(2);
				lineleft.getStrokeDashArray().addAll(6d, 15d);
				lineleft.setStrokeLineCap(StrokeLineCap.ROUND);
				StackPane.setAlignment(lineleft, Pos.TOP_LEFT);

				Line lineright = new Line(width-10, newlevel-5,   width-10,   0);
				lineright.setStrokeWidth(2);
				lineright.getStrokeDashArray().addAll(6d, 15d);
				lineright.setStrokeLineCap(StrokeLineCap.ROUND);
				StackPane.setAlignment(lineright, Pos.TOP_RIGHT);
				
				this.getChildren().addAll(rectangle, text ,lineleft, lineright);
			}
		}
	
	/**
	  * Create an event without duration UI element
	 */
	public void setEventWithoutDuraElement(){			
			this.setMinSize(100,level);
			
			Rectangle rectangle = new Rectangle(130,20,Color.DARKCYAN);	
			StackPane.setAlignment(rectangle, Pos.BOTTOM_LEFT);
			
			Text text = new Text(this.getTitle());
			Font font = new Font("SansSerif", 20);
			text.setFont(font);
			text.setFill(Color.BLACK);
			StackPane.setAlignment(text, Pos.BOTTOM_CENTER);
			
			Line lineleft = new Line(0, 0,   0,   level-5);
			lineleft.setStrokeWidth(4);
			StackPane.setAlignment(lineleft, Pos.TOP_LEFT);			
			this.getChildren().addAll(rectangle, text ,lineleft);
		}
	
	/**
	  * Create an event without duration UI element if it places at another level in the screen
	 * @param a The level
	 */
	public void setElementWithoutDuraWithLevel(int a){
				int newlevel = level ;
				newlevel = 70 * a + newlevel ;
				this.setMinSize(100,newlevel);
				
				Rectangle rectangle = new Rectangle(130,20,Color.DARKCYAN);		
				StackPane.setAlignment(rectangle, Pos.BOTTOM_LEFT);
				
				Text text = new Text(this.getTitle());
				Font font = new Font("SansSerif", 20);
				text.setFont(font);
				text.setFill(Color.BLACK);
				StackPane.setAlignment(text, Pos.BOTTOM_CENTER);
				
				Line lineleft = new Line(0, 0,   0,   newlevel-5);
				lineleft.setStrokeWidth(4);				
				StackPane.setAlignment(lineleft, Pos.TOP_LEFT);				
				this.getChildren().addAll(rectangle, text ,lineleft);
			}
}