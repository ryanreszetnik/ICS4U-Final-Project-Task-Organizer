package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DisplayList {
	public static ArrayList<Button> list = new ArrayList<>();
	static ComboBox<String> addTask;
	static Button assignment = new Button("Assignment");
	static Button event = new Button("Event");
	static Button calendarView = new Button("Switch to Calendar");
	

	public static void addButton() {
		Button a = new Button();
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		list.add(a);

		a.setTranslateY(50 + (list.size() * 100));
	}

	public static void setup(Pane pane) {
		pane.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #999999;");
		addTask = new ComboBox<String>();
		Font f1 = new Font(20);
		calendarView.setFont(f1);
		calendarView.setPrefHeight(60);
		calendarView.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		calendarView.setTranslateX(510);
		Font fo = new Font(18);
		assignment.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		assignment.setFont(fo);
		event.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		event.setFont(fo);
		addTask.getItems().add("Event");
		addTask.setValue("Add New Task");
		addTask.getItems().add("Assignment");
		addTask.setPrefSize(150, 60);
		addTask.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");

		Rectangle top = new Rectangle(700, 60);
		top.setFill(Color.rgb(96, 96, 96));
		pane.getChildren().addAll(top, addTask, calendarView);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFont(fo);
			list.get(i).setPrefSize(650, 100);
			list.get(i).setTranslateY(60 + (100 * i));
			list.get(i).setText(List.list.get(list.size()-1).name + "\n" + (List.list.get(list.size()-1).date.hour + ":" + (List.list.get(list.size()-1).date.minute)));
			pane.getChildren().add(list.get(i));
		}
	
	}

}
