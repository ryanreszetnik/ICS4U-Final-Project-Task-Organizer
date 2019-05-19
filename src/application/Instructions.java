package application;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Instructions {
	static Button toList = new Button("Switch to List");
	static Rectangle top = new Rectangle(700, 60);
	
	public static void setup(Pane pane) {
		top.setFill(Color.rgb(96, 96, 96));
		pane.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #999999;");
		toList.setPrefHeight(60);
		toList.setTranslateX(285);
		toList.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		pane.getChildren().addAll(top,toList);
	}
	


}
