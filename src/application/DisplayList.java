package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DisplayList {
	public static ArrayList<Button> list = new ArrayList<>();
	static Button addTask;
	static Button assignment = new Button("Assignment");
	static Button event = new Button("Event");
	static Button calendarView = new Button("Switch to Calendar");
	static String[] values = {"Add New Task", "Event", "Assignment"};
	static Button newEvent = new Button("New Event");
	static Button newAssignment = new Button("New Assignmentt");
	static boolean isAssignment;
	

	public static void addButton(Button a) {
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		//a.setTranslateY(50 + (list.size() * 100));
		list.add(a);
	}
	public static void addTask(Task t){
		Button newTask = new Button(t.toString());
		addButton(newTask);
	}

	public static void setup(Pane pane) {
		newEvent.setPrefSize(140, 30);
		newEvent.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		newAssignment.setPrefSize(140, 30);
		newAssignment.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		pane.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #999999;");
		addTask = new Button("New Task");
		Font f1 = new Font(20);
		calendarView.setFont(f1);
		calendarView.setPrefHeight(60);
		calendarView.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		calendarView.setTranslateX(510);
		Font fo = new Font(18);
		assignment.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		assignment.setFont(fo);
		event.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		event.setFont(fo);

		
		addTask.setPrefSize(150, 60);
		addTask.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");

		Rectangle top = new Rectangle(700, 60);
		top.setFill(Color.rgb(96, 96, 96));
		pane.getChildren().addAll(top, addTask, calendarView);
	
	}
	
	public static void displayTasks(Pane pane) {
		for (int i = 0; i < list.size(); i++) {
			
			
			list.get(i).setPrefSize(680, 100);
			list.get(i).setTranslateX(10);
			list.get(i).setTranslateY(65 + (i * 100));
			Font fo = new Font(25);
			list.get(i).setFont(fo);
			if(pane.getChildren().contains(list.get(i)) == false){
				pane.getChildren().add(list.get(i));
			}
			list.get(i).setOnAction(e ->{
					System.out.print("ooga");
						Main.liststack.getChildren().add(displayTask.newAssignment);
			});
		}
	}

}
