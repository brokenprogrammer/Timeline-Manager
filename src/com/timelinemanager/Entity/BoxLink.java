package com.timelinemanager.Entity;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * BoxLink class to represent timeline chain of the whole timeline view.
 * 
 * @author Shizhen Zhao
 * @version 0.00.00
 * @name BoxLink.java
 */

public class BoxLink extends GridPane {

	private int pos;
	private int span;
	private ArrayList<Integer> bigArr;
	private NumberBox[] numberBoxGroup;
	private static ArrayList<Integer> arr = new ArrayList<Integer>();

	/**
	 * Create a box chain .
	 * 
	 * @param pos the position of start of the chain
	 * @param span the length of box chain which is shown on screen
	 * @param bigArr the numbers which are represented by the boxes
	 */
	public BoxLink(int pos, int span, ArrayList<Integer> bigArr) {
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.4));
		this.setHgap(5.5);
		this.setVgap(5.5);

		numberBoxGroup = new NumberBox[span];

		arr = new ArrayList<Integer>(bigArr.subList(pos, pos + span));

		for (int i = 0; i < numberBoxGroup.length; i++) {
			numberBoxGroup[i] = new NumberBox(arr.get(i));
			this.add(numberBoxGroup[i], i, 0);
		}
	}

	/**
	 * reset the box chain with new parameter.
	 * 
	 * @param pos the position of start of the chain
	 * @param span length of the chain
	 * @param bigArr the numbers which are represented by the boxes
	 */
	public void setBoxLink(int start, int span, ArrayList<Integer> bigArr) {
		this.pos = start;
		this.span = span;
		this.bigArr = bigArr;
		numberBoxGroup = new NumberBox[span];

		arr = new ArrayList<Integer>(bigArr.subList(pos, pos + span));

		for (int i = 0; i < numberBoxGroup.length; i++) {
			numberBoxGroup[i] = new NumberBox(arr.get(i));
			this.add(numberBoxGroup[i], i, 0);
		}
	}
}
