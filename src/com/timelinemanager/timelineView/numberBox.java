package com.timelinemanager.timelineView;





import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class numberBox extends BorderPane{

	private int num;
	
	
	public numberBox(int num){
		this.num = num;
		String numString = this.num+"";
		Text numText = new Text(numString);
		Font font = new Font("SansSerif", 20);
		numText.setFont(font);
		numText.setFill(Color.BLACK);
		javafx.scene.paint.Color color = javafx.scene.paint.Color.rgb(250, 124,0, 1.0);
		this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setMinSize(70, 40);
		this.setCenter(numText);
	}
	
	
	

}
