package com.timelinemanager.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 *  <code>EventBoxLink</code> class is an array that hold the event elements
 *  
 * @author Mohammed Basel Nasrini
 * @version 0.00.00
 * @name EventBoxLink.java
 *
 */
public class EventBoxLink extends GridPane {

	public static ArrayList<Event> arr = new ArrayList<Event>();

	/**
	 * Create a chain to hold the event elements.
	 * 
	 * @param pos the start date and time of the timeline
	 * @param span the length of the timeline
	 */
	public EventBoxLink(LocalDateTime start, int span ) {
		setId("timelineManagerEventBoxLink");
		
		for (int colIndex = 0; colIndex < 336; colIndex++) {
			ColumnConstraints cc = new ColumnConstraints(3);
			cc.setHgrow(Priority.ALWAYS);
			cc.setFillWidth(true);
			this.getColumnConstraints().add(cc);
		}
		for (int rowIndex = 0; rowIndex < 1; rowIndex++) {
			RowConstraints rc = new RowConstraints(1);
			rc.setVgrow(Priority.ALWAYS);
			rc.setFillHeight(true);
			this.getRowConstraints().add(rc);
		}
		this.setGridLinesVisible(true);
	}

	/**
	 * Add the event elements to the chain.
	 * 
	 * @param pos the position of start of the chain
	 * @param span the start date and time of the timeline
	 * @param inbigArr Array that holds the events
	 */
	public void setEventBoxLink(LocalDateTime start, int span, ArrayList<Event> inbigArr) {
		this.getChildren().clear();
		arr.clear();

		LocalDateTime temp = start;
		LocalDateTime end = temp.plusDays(span);
		
		ArrayList<Event> copyBigArr = new ArrayList<Event>();
		ArrayList<Event> arrWithout = new ArrayList<Event>();
		for (int m = 0; m < inbigArr.size(); m++) {
			Event tempEve = new Event(inbigArr.get(m));
			if (tempEve.getEndDate() == null)
				arrWithout.add(tempEve);
			else{
				copyBigArr.add(tempEve);}
		}

		LocalDateTime startEvent;
		LocalDateTime endEvent ;
		
		Comparator<Event> comp = (s1, s2) -> (s1.getStart()).compareTo(s2.getStart()); 
		Collections.sort(copyBigArr, comp);
		
		for (int m = 0; m < copyBigArr.size(); m++) {
			
			
			startEvent = copyBigArr.get(m).getStart();
			endEvent = copyBigArr.get(m).getEnd();
			
			boolean a = startEvent.isBefore(start);
			boolean b = startEvent.isEqual(start);
			boolean c = startEvent.isAfter(start) && startEvent.isBefore(end);
			boolean d = endEvent.isAfter(start) && endEvent.isBefore(end);
			boolean e = endEvent.isEqual(end);
			boolean f = endEvent.isAfter(end);

			if (b || c && d || e) {
				Event tempEve = new Event(copyBigArr.get(m));
				arr.add(tempEve);
			} else if (a && f) {
				Event tempEve = new Event(copyBigArr.get(m));
				tempEve.setStartTime(start.toLocalTime());
				tempEve.setStartDate(start.toLocalDate());
				tempEve.setEndTime(end.toLocalTime());
				tempEve.setEndDate(end.toLocalDate());
				arr.add(tempEve);
				tempEve = null;
			} else if (a && f || e || d) {
				Event tempEve = new Event(copyBigArr.get(m));
				tempEve.setStartTime(start.toLocalTime());
				tempEve.setStartDate(start.toLocalDate());
				arr.add(tempEve);
				tempEve = null;
			} else if (f && a || b) {
				Event tempEve = new Event(copyBigArr.get(m));
				tempEve.setEndTime(end.toLocalTime());
				tempEve.setEndDate(end.toLocalDate());
				arr.add(tempEve);
				tempEve = null;
			} else if (f && c || e) {
				Event tempEve = new Event(copyBigArr.get(m));
				tempEve.setEndTime(end.toLocalTime());
				tempEve.setEndDate(end.toLocalDate());
				arr.add(tempEve);
				tempEve = null;
			}
		}

		int withoutLevel = 0 ;

		for (int m = 0 ; m<arrWithout.size() ; m++){
				LocalDateTime temp1 = start;
				
				Event a = new Event(arrWithout.get(m));
				temp1 = start ;
				if (a.getStart().isAfter(start) && a.getStart().isBefore(end)){
					
					int wPos = 0;
					while (temp1.isBefore(a.getStart())) {
						temp1 = temp1.plusHours(1);
						wPos += 1;
					}
				
					this.add(new Event(a, ++ withoutLevel ), wPos, 0);
				}
			}		
		int level = withoutLevel ;
		for (int i = 0; i < arr.size(); i++) {
			LocalDateTime temp1 = start;
			Event a = new Event(arr.get(i));		
			int pos = 0;
			
			while (temp1.isBefore(a.getStart())) {
				temp1 = temp1.plusHours(1);
				pos += 1;
			}
		
			if (i == 0) {
				this.add(new Event(a), pos, 0);
			}
			if (i > 0) {
				if (a.getStart().isAfter(arr.get(i - 1).getStart()) && a.getStart().isBefore(arr.get(i - 1).getEnd())) {
					this.add(new Event(a, ++level), pos, 0);
					continue;
				} 
				if (a.getEnd().isAfter(arr.get(i - 1).getStart()) && a.getStart().isBefore(arr.get(i - 1).getEnd())) {
					this.add(new Event(a, ++level), pos, 0);
					continue;
				} else {
					level = withoutLevel;
					for (int m = i; m > 0; m--) {

						if (a.getStart().isAfter(arr.get(i - 1).getStart()) && a.getStart().isBefore(arr.get(i - 1).getEnd())) {
							this.add(new Event(a, ++level), pos, 0);
							continue;
						}
						else if (a.getEnd().isAfter(arr.get(m).getStart()) && a.getStart().isBefore(arr.get(m).getEnd())) {
							this.add(new Event(a, ++level), pos, 0);
						}
					}
				}		
			}	
		}		
	}	
}
