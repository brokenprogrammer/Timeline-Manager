package com.timelinemanager.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * BoxLink class to represent timeline chain of the whole timeline view.
 * 
 * @author Shizhen Zhao
 * @version 0.00.00
 * @name BoxLink.java
 */

public class BoxLink extends GridPane {

	private int pos;
	private NumberBox[] numberBoxGroup;
	private ArrayList<Integer> arr = new ArrayList<Integer>();
	private ArrayList<Integer> dayarr = new ArrayList<Integer>();
	private ArrayList<Integer> montharr = new ArrayList<Integer>();
	private ArrayList<Integer> yeararr = new ArrayList<Integer>();

	/**
	 * Create a box chain .
	 * 
	 * @param pos
	 *            the position of start of the chain
	 * @param span
	 *            the length of box chain which is shown on screen
	 * @param bigArr
	 *            the numbers which are represented by the boxes
	 */
	public BoxLink(int pos, int span, boolean dayLevel, LocalDate startDate, LocalDate endDate) {
		this.setGridLinesVisible(true);

		if (dayLevel == true) {

			LocalDate temp = startDate;

			while (temp.compareTo(endDate) < 1) {
				dayarr.add(temp.getDayOfMonth());
				temp = temp.plusDays(1);
			}

			numberBoxGroup = new NumberBox[span];
			arr = new ArrayList<Integer>(dayarr.subList(pos, pos + span));

			for (int i = 0; i < numberBoxGroup.length; i++) {
				numberBoxGroup[i] = new NumberBox(arr.get(i), dayLevel);
				this.add(numberBoxGroup[i], i, 1);
			}
		}

		else {
			LocalDate temp = startDate;

			while (temp.compareTo(endDate) < 1) {
				montharr.add(temp.getMonthValue());
				temp = temp.plusMonths(1);
			}
			numberBoxGroup = new NumberBox[span];
			arr = new ArrayList<Integer>(montharr.subList(pos, pos + span));

			for (int i = 0; i < span; i++) {
				numberBoxGroup[i] = new NumberBox(arr.get(i), dayLevel);
				this.add(numberBoxGroup[i], i, 1);
			}

			temp = startDate;
			for (int i = 0; i < span; i++) {
				String year = temp.plusMonths(i).getYear() + "";
				Text yearText = new Text(year);
				Font font = new Font("SansSerif", 20);
				yearText.setFont(font);
				yearText.setFill(Color.BLACK);
				this.add(yearText, i, 0);
			}
		}
	}

	/**
	 * reset the box chain with new parameter.
	 * 
	 * @param pos
	 *            the position of start of the chain
	 * @param span
	 *            length of the chain
	 * @param bigArr
	 *            the numbers which are represented by the boxes
	 */
	public void setBoxLink(int start, int span, boolean dayLevel, LocalDate startDate, LocalDate endDate) {
		this.getChildren().clear();
		this.pos = start;
		this.setGridLinesVisible(true);

		if (dayLevel == true) {
			LocalDate temp = startDate;
			while (temp.compareTo(endDate) < 1) {
				dayarr.add(temp.getDayOfMonth());
				temp = temp.plusDays(1);
			}

			numberBoxGroup = new NumberBox[span];
			arr = new ArrayList<Integer>(dayarr.subList(pos, pos + span));

			for (int i = 0; i < numberBoxGroup.length; i++) {
				numberBoxGroup[i] = new NumberBox(arr.get(i), dayLevel);
				this.add(numberBoxGroup[i], i, 1);
			}
		}
		else {

			LocalDate temp = startDate;
			yeararr.clear();
			while (temp.compareTo(endDate) < 1) {
				montharr.add(temp.getMonthValue());
				yeararr.add(temp.getYear());
				temp = temp.plusMonths(1);
			}

			numberBoxGroup = new NumberBox[span];
			arr = new ArrayList<Integer>(montharr.subList(pos, pos + span));
			for (int i = 0; i < span; i++) {
				numberBoxGroup[i] = new NumberBox(arr.get(i), dayLevel);
				this.add(numberBoxGroup[i], i, 1);
			}

			arr = new ArrayList<Integer>(yeararr.subList(pos, pos + span));
			for (int i = 0; i < span; i++) {

				String year = arr.get(i).toString();
				Font font = new Font("SansSerif", 18);
				Text text = new Text(year);
				text.setFont(font);
				this.add(text, i, 0);
			}
		}
	}
}
