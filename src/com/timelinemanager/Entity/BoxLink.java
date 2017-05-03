package com.timelinemanager.Entity;

import java.util.ArrayList;
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
		//this.setAlignment(Pos.CENTER);
		//this.setPadding(new Insets(11.5, 12.5, 0, 14.4));
		//this.setHgap(1);
		
		this.setGridLinesVisible(true);
		
		numberBoxGroup = new NumberBox[span];

		arr = new ArrayList<Integer>(bigArr.subList(pos, pos + span  ));
		
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
		
		this.getChildren().clear();

		this.setGridLinesVisible(true);

		this.pos = start;
		numberBoxGroup = new NumberBox[span];

		arr = new ArrayList<Integer>(bigArr.subList(pos, pos + span));

		for (int i = 0; i < numberBoxGroup.length; i++) {
			numberBoxGroup[i] = new NumberBox(arr.get(i));
			this.add(numberBoxGroup[i], i, 0);
		}
	}
}
