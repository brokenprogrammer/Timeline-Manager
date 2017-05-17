package com.timelinemanager.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 * <code>EventBoxLink</code> class is an array that hold the event elements
 * 
 * @author Mohammed Basel Nasrini
 * @version 0.00.00
 * @name EventBoxLink.java
 *
 */
public class EventBoxLink extends GridPane {

	public ArrayList<Event> arr = new ArrayList<Event>();
	public boolean monthLevel = false;
	private int level = 0;

	/**
	 * Create a chain to hold the event elements.
	 * 
	 * @param pos
	 *            the start date and time of the timeline
	 * @param span
	 *            the length of the timeline
	 */
	public EventBoxLink(LocalDateTime start, int span, boolean dayLevel) {

		if (dayLevel == true) {
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
		} else if (dayLevel == false) {
			for (int colIndex = 0; colIndex < span; colIndex++) {
				ColumnConstraints cc = new ColumnConstraints(145);
				this.getColumnConstraints().add(cc);
			}
			for (int rowIndex = 0; rowIndex < 2; rowIndex++) {
				RowConstraints rc = new RowConstraints(5);
				rc.setVgrow(Priority.ALWAYS);
				rc.setFillHeight(true);
				this.getRowConstraints().add(rc);
			}
		}

	}

	/**
	 * Add the event elements to the chain.
	 * 
	 * @param pos
	 *            the position of start of the chain
	 * @param span
	 *            the start date and time of the timeline
	 * @param inbigArr
	 *            Array that holds the events
	 */
	public void setEventBoxLink(LocalDateTime start, int span, boolean dayLevel, ArrayList<Event> inbigArr) {
		ArrayList<Event> tem = new ArrayList<Event>();
		if (inbigArr.size()>0){
			for(int i=0;i<inbigArr.size();i++){
				tem.add(new Event(inbigArr.get(i),"initial"));
				}
		}
		inbigArr = tem;
		
		if (dayLevel == true) {
			this.setMouseTransparent(false);
			this.getChildren().clear();
			arr.clear();

			LocalDateTime temp = start;
			LocalDateTime end = temp.plusDays(span);
			end = end.minusHours(1);

			ArrayList<Event> copyBigArr = new ArrayList<Event>();
			ArrayList<Event> arrWithout = new ArrayList<Event>();

			for (int m = 0; m < inbigArr.size(); m++) {
				Event tempEve = new Event(inbigArr.get(m));
				
				if (tempEve.getEndDate() == null)
					arrWithout.add(tempEve);
				else {
					copyBigArr.add(tempEve);
				}
			}
			LocalDateTime startEvent;
			LocalDateTime endEvent;

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
					Event tempEve = copyBigArr.get(m);
					arr.add(tempEve);
				} else if (a && f) {
					Event tempEve = copyBigArr.get(m);
					tempEve.setStartTime(start.toLocalTime());
					tempEve.setStartDate(start.toLocalDate());
					tempEve.setEndTime(end.toLocalTime());
					tempEve.setEndDate(end.toLocalDate());
					arr.add(tempEve);
//					tempEve = null;
				} else if (a && f || e || d) {
					Event tempEve = copyBigArr.get(m);
					tempEve.setStartTime(start.toLocalTime());
					tempEve.setStartDate(start.toLocalDate());
					arr.add(tempEve);
//					tempEve = null;
				} else if (f && a || b) {
					Event tempEve = copyBigArr.get(m);
					tempEve.setEndTime(end.toLocalTime());
					tempEve.setEndDate(end.toLocalDate());
					arr.add(tempEve);
//					tempEve = null;
				} else if (f && c || e) {
					Event tempEve = copyBigArr.get(m);
					tempEve.setEndTime(end.toLocalTime());
					tempEve.setEndDate(end.toLocalDate());
					arr.add(tempEve);
//					tempEve = null;
				}
			}

			int withoutLevel = 0;

			for (int m = 0; m < arrWithout.size(); m++) {
				LocalDateTime temp1 = start;
				Event a = new Event(arrWithout.get(m));
				temp1 = start;
				if (a.getStart().isAfter(start) && a.getStart().isBefore(end)) {
					int wPos = 0;
					while (temp1.isBefore(a.getStart())) {
						temp1 = temp1.plusHours(1);
						wPos += 1;
					}
					this.add(new Event(a, ++withoutLevel), wPos, 0);
				}
			}
			level = withoutLevel;
			for (int i = 0; i < arr.size(); i++) {
				LocalDateTime temp1 = start;
				Event a = arr.get(i);

				int pos = 0;

				while (temp1.isBefore(a.getStart())) {
					temp1 = temp1.plusHours(1);
					pos += 1;
				}

				if (i == 0) {
					Event b = new Event(a);
					this.add(b, pos, 0);
				}
				if (i > 0) {
					if (a.getStart().isAfter(arr.get(i - 1).getStart())
							&& a.getStart().isBefore(arr.get(i - 1).getEnd())) {
						Event b = new Event(a, ++level);
						b.inf_end = a.inf_end;
						this.add(b, pos, 0);
						continue;
					}
					if (a.getEnd().isAfter(arr.get(i - 1).getStart())
							&& a.getStart().isBefore(arr.get(i - 1).getEnd())) {
						Event b = new Event(a, ++level);
						b.inf_end = a.inf_end;
						this.add(b, pos, 0);
						continue;
					} else {
						level = withoutLevel;
						for (int m = i; m > 0; m--) {

							if (a.getStart().isAfter(arr.get(i - 1).getStart())
									&& a.getStart().isBefore(arr.get(i - 1).getEnd())) {
								Event b = new Event(a, ++level);
								b.inf_end = a.inf_end;
								this.add(b, pos, 0);
								continue;
							} else if (a.getEnd().isAfter(arr.get(m).getStart())
									&& a.getStart().isBefore(arr.get(m).getEnd())) {
								Event b = new Event(a, ++level);
								b.inf_end = a.inf_end;
								this.add(b, pos, 0);
							}
						}
					}
				}
			}
		}

		else if (dayLevel == false) {
			this.setMouseTransparent(false);
			this.getChildren().clear();
			arr.clear();

			LocalDateTime temp = start;
			LocalDateTime end = temp.plusMonths(span);
			ArrayList<Event> shortEvent = new ArrayList<Event>();
			ArrayList<Event> longEvent = new ArrayList<Event>();
			ArrayList<Event> withoutDura = new ArrayList<Event>();

			for (int m = 0; m < inbigArr.size(); m++) {
				Event tempEve = new Event(inbigArr.get(m));
				if (tempEve.getEndDate() == null) {
					withoutDura.add(tempEve);
				} else if (tempEve.getMonthsLength() > 1) {
					longEvent.add(tempEve);
				} else if (tempEve.getMonthsLength() == 1) {
					shortEvent.add(tempEve);
				}

			}

			temp = start;
			Pane pane = new Pane();
			Tooltip tool = new Tooltip();
			Comparator<Event> comp = (s1, s2) -> (s1.getStart()).compareTo(s2.getStart());
			Collections.sort(shortEvent, comp);
			Collections.sort(longEvent, comp);
			Collections.sort(withoutDura, comp);

			for (int m = 0; m < span; m++) {

				pane = new Pane();
				pane.setMinSize(145, 130);
				pane.setMaxSize(145, 130);

				StringBuilder sb = new StringBuilder();
				tool = new Tooltip();

				for (int i = 0; i < withoutDura.size(); i++) {
					if (withoutDura.get(i).getStartDate().getMonth().getValue() == temp.getMonth().getValue()) {
						sb.append(withoutDura.get(i).getTitle() + ".\n");
					}
				}
				temp = temp.plusMonths(1);

				if (sb.length() == 0) {
					tool.setText("No events without duration during this month");
				}
				if (sb.length() > 0) {
					String msg = "Events without duration during this month:\n" + sb.toString();
					tool.setText(msg);
				}

				Tooltip.install(pane, tool);
				this.add(pane, m, 0);
			}

			int withoutLevel = 0;

			for (int m = 0; m < shortEvent.size(); m++) {
				LocalDateTime temp1 = start.withDayOfMonth(1);
				Event a = new Event(shortEvent.get(m), dayLevel, dayLevel);

				if (a.getStart().isAfter(start) && a.getStart().isBefore(end)) {
					double wPos = 0;
					while (temp1.isBefore(a.getStart())) {
						double monthLength = temp1.getMonth().length(temp1.toLocalDate().isLeapYear());
						double plusPos = 145 / monthLength;
						wPos += plusPos;
						temp1 = temp1.plusDays(1);
					}
					Event b = new Event(a, ++withoutLevel, dayLevel, dayLevel);
					GridPane.setMargin(b, new Insets(0, 0, 0, wPos));
					this.add(b, 0, 0);
				}
			}
			level = withoutLevel;

			LocalDateTime startEvent;
			LocalDateTime endEvent;
			for (int m = 0; m < longEvent.size(); m++) {

				startEvent = longEvent.get(m).getStart();
				endEvent = longEvent.get(m).getEnd();

				boolean a = startEvent.isBefore(start);
				boolean b = startEvent.isEqual(start);
				boolean c = startEvent.isAfter(start) && startEvent.isBefore(end);
				boolean d = endEvent.isAfter(start) && endEvent.isBefore(end);
				boolean e = endEvent.isEqual(end);
				boolean f = endEvent.isAfter(end);

				if (b || c && d || e) {
					Event tempEve = longEvent.get(m);
					arr.add(tempEve);
				} else if (a && f) {
					Event tempEve = longEvent.get(m);
					tempEve.setStartTime(start.toLocalTime());
					tempEve.setStartDate(start.toLocalDate());
					tempEve.setEndTime(end.toLocalTime());
					tempEve.setEndDate(end.toLocalDate());
					arr.add(tempEve);
					tempEve = null;
				} else if (a && f || e || d) {
					Event tempEve = longEvent.get(m);
					tempEve.setStartTime(start.toLocalTime());
					tempEve.setStartDate(start.toLocalDate());
					arr.add(tempEve);
					tempEve = null;
				} else if (f && a || b) {
					Event tempEve = longEvent.get(m);
					tempEve.setEndTime(end.toLocalTime());
					tempEve.setEndDate(end.toLocalDate());
					arr.add(tempEve);
					tempEve = null;
				} else if (f && c || e) {
					Event tempEve = longEvent.get(m);
					tempEve.setEndTime(end.toLocalTime());
					tempEve.setEndDate(end.toLocalDate());
					arr.add(tempEve);
					tempEve = null;
				}
			}

			for (int i = 0; i < arr.size(); i++) {
				LocalDateTime temp1 = start.withDayOfMonth(1);
				Event a = new Event(arr.get(i), dayLevel);

				double pos = 0;

				while (temp1.isBefore(a.getStart())) {
					double monthLength = temp1.getMonth().length(temp1.toLocalDate().isLeapYear());
					double plusPos = 145 / monthLength;
					pos += plusPos;
					temp1 = temp1.plusDays(1);
				}

				if (i == 0) {
					Event b = new Event(a, dayLevel);
					GridPane.setMargin(b, new Insets(0, 0, 0, pos));
					this.add(b, 0, 0);
				}
				if (i > 0) {
					if (a.getStart().isAfter(arr.get(i - 1).getStart())
							&& a.getStart().isBefore(arr.get(i - 1).getEnd())) {
						Event b = new Event(a, ++level, dayLevel);
						b.inf_end = a.inf_end;
						GridPane.setMargin(b, new Insets(0, 0, 0, pos));
						this.add(b, 0, 0);
						continue;
					}
					if (a.getEnd().isAfter(arr.get(i - 1).getStart())
							&& a.getStart().isBefore(arr.get(i - 1).getEnd())) {
						Event b = new Event(a, ++level, dayLevel);
						b.inf_end = a.inf_end;
						GridPane.setMargin(b, new Insets(0, 0, 0, pos));
						this.add(b, 0, 0);
						continue;
					} else {
						level = 0;
						for (int m = i; m > 0; m--) {
							if (a.getStart().isAfter(arr.get(i - 1).getStart())
									&& a.getStart().isBefore(arr.get(i - 1).getEnd())) {
								Event b = new Event(a, ++level, dayLevel);
								b.inf_end = a.inf_end;
								GridPane.setMargin(b, new Insets(0, 0, 0, pos));
								this.add(b, 0, 0);
								continue;
							} else if (a.getEnd().isAfter(arr.get(m).getStart())
									&& a.getStart().isBefore(arr.get(m).getEnd())) {
								Event b = new Event(a, ++level, dayLevel);
								b.inf_end = a.inf_end;
								GridPane.setMargin(b, new Insets(0, 0, 0, pos));
								this.add(b, 0, 0);
							}
						}
					}
				}
			}
		}
	}
	public int getLevel(){
		return level;
	}
}
