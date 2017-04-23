package com.timelinemanager.timelineView;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class boxLink extends GridPane{
	
	private int pos;
	private int span;
	private ArrayList<Integer> bigArr;
	private numberBox[] numberBoxGroup;
	
	
	private static ArrayList<Integer> arr = new ArrayList<Integer> ();
	
	
	public boxLink(int pos,int span,ArrayList<Integer> bigArr){
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5,12.5,13.5,14.4));
		this.setHgap(5.5);
		this.setVgap(5.5);
		
		
	    
	    numberBoxGroup = new numberBox[span];
	    
	    arr = new ArrayList<Integer>(bigArr.subList(pos, pos+span));
		
		for (int i = 0; i < numberBoxGroup.length; i++) {
			numberBoxGroup[i] = new numberBox(arr.get(i));
			this.add(numberBoxGroup[i], i, 0);
		}
		
		
		
		
	}
	
	
	
	public void setBoxLink(int start,int span,ArrayList<Integer> bigArr){
		this.pos = start;
		this.span = span;
		this.bigArr = bigArr;
		numberBoxGroup = new numberBox[span];

		arr = new ArrayList<Integer>(bigArr.subList(pos, pos + span));

		for (int i = 0; i < numberBoxGroup.length; i++) {
			numberBoxGroup[i] = new numberBox(arr.get(i));
			this.add(numberBoxGroup[i], i, 0);
		}
	}

}
