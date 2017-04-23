package com.timelinemanager.timelineView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class TimelineView extends StackPane{
	
	private int startYear;
	private int startMonth;
	private int startDay;
	private int endYear;
	private int endMonth;
	private int endDay;
	public ArrayList<Integer> bigArr = new ArrayList<Integer> ();
	public static boxLink timelineGrid;
	public Slider timelineSlider;
	
	public TimelineView(int startYear,int startMonth,int startDay,int endYear,int endMonth,int endDay,int span){
		
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.startDay = startDay;
		this.endYear = endYear;
		this.endMonth = endMonth;
		this.endDay = endDay;
		
		this.setMinSize(300, 300);
		
		
		
		LocalDate start = LocalDate.of(this.startYear,this.startMonth,this.startDay) ;
	    LocalDate end = LocalDate.of(this.endYear,this.endMonth,this.endDay);
	    
	    long diffDays = ChronoUnit.DAYS.between(start, end);
	    
		while (start.compareTo(end) < 1) {
			bigArr.add(start.getDayOfMonth());
			start = start.plusDays(1);
		}
		
		
	    
		
		
		timelineSlider = new Slider();
		timelineSlider.setMin(0);
		timelineSlider.setMax(diffDays-span+1);
		timelineSlider.setMaxWidth(1000);
        
        
		
		timelineGrid = new boxLink(0,span,bigArr);
		StackPane.setAlignment(timelineGrid, Pos.CENTER);
		
		timelineSlider.valueProperty().addListener(
				property -> timelineGrid.setBoxLink((int) Math.round(timelineSlider.getValue()), span, bigArr)
				
				
				);
				
		this.getChildren().add(timelineGrid);
		
		
		
		StackPane.setAlignment(timelineSlider, Pos.BOTTOM_CENTER);
		
		
		this.getChildren().add(timelineSlider);
		
	}

	
	
	public static void setBoxLink(int start,int span,ArrayList<Integer> bigArr){
		timelineGrid = new boxLink(start, span, bigArr);
	}
}
