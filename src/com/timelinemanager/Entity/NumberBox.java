package com.timelinemanager.Entity;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDate;

import javafx.geometry.Insets;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * NumberBox class to represent the single box of the Timeline chain.
 * 
 * @author Shizhen Zhao
 * @version 0.00.00
 * @name NumberBox.java
 */

public class NumberBox extends BorderPane{

	private int num;
	private LocalDate temp ;
	/**
	 * Create a single box with a number.
	 * 
	 * @param num number in the box.
	 */
	public NumberBox(int num , boolean dayLevel){
		if (dayLevel == true){
		this.num = num;
		setId("timelineManagerNumberBox");
		
		String numString = this.num+"";
		Text numText = new Text(numString);
		Font font = new Font("SansSerif", 20);
		numText.setFont(font);
		numText.setFill(Color.BLACK);
		javafx.scene.paint.Color color = javafx.scene.paint.Color.rgb(250, 124,0, 1.0);
		this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setMinSize(72, 40);
		this.setCenter(numText);
		}
		else{
			temp = LocalDate.of(2014, 01, 01);
			Text numText = new Text(temp.withMonth(num).getMonth().toString());
//			int length = temp.withMonth(num).getMonth().length(false);
			Font font = new Font("SansSerif", 20);
			numText.setFont(font);
			numText.setFill(Color.BLACK);
			javafx.scene.paint.Color color = javafx.scene.paint.Color.rgb(250, 124,0, 1.0);
			this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

			this.setMinSize(142, 40);
			
			
				
			this.setCenter(numText);
		}
			
	}
}
