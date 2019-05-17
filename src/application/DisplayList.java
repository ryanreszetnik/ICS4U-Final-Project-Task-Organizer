package application;

import java.time.LocalDate;
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
	static boolean exists;
	static int buttonIndex;
	static Rectangle top;
	

	public static void addButton(Button a) {
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		list.add(a);
	}
	public static void updateButton(Button a) {
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		list.remove(buttonIndex);
		list.set(buttonIndex, a);
	}
	public static void addTask(Task t){
		Button newTask = new Button(t.toString());
		addButton(newTask);
	}
	public static void updateTask(Task t) {
		Button newTask = new Button(t.toString());
		updateButton(newTask);
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

		top = new Rectangle(700, 60);
		top.setFill(Color.rgb(96, 96, 96));
		pane.getChildren().addAll(top, addTask, calendarView);
	
	}
	
	public static void displayTasks(Pane pane) {
		System.out.println(List.list.size() +"====" + list.size());
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPrefSize(680, 100);
			list.get(i).setTranslateX(10);
			list.get(i).setTranslateY(65 + (i * 100));
			Font fo = new Font(25);
			list.get(i).setFont(fo);
			if(pane.getChildren().contains(list.get(i)) == false){
				pane.getChildren().add(list.get(i));
			}
			pane.getChildren().removeAll(top, addTask, calendarView);
			pane.getChildren().addAll(top, addTask, calendarView);
			if(!List.list.get(i).isEvent) {
			buttonIndex = i;
			String name = List.list.get(i).name;
			String description = List.list.get(i).description;
			String subject = ((Assignment)(List.list.get(i))).getSubject();
			Date date = List.list.get(i).date;
			int year = date.year;
			int month = date.month;
			int day = date.day;
			Boolean priority = ((Assignment)List.list.get(i)).getPriority();
			int count = i; 
			
			list.get(i).setOnAction(e ->{
					buttonIndex = count;
					displayTask.newThing = false;
					Main.liststack.getChildren().add(displayTask.newAssignment);
					displayTask.assignmentname.setText(name);
					displayTask.assignDescription.setText(description);
					displayTask.subject.setText(subject);
					displayTask.dateAssignment.setValue(LocalDate.of(year,month,day));
			});
			}
			
			else {
				int count = i;
				String name = List.list.get(i).name;
				String description = List.list.get(i).description;
				String location = ((Event)(List.list.get(i))).getLocation();
				Date date = List.list.get(i).date;
				int year = date.year;
				int month = date.month;
				int day = date.day;
				System.out.println("hour:" +date.hour);
				int hour = date.getHour();
				int min = date.minute;
				boolean morn = date.isMorining();
				
				list.get(i).setOnAction(e ->{
					
					displayTask.newThing = false;
					buttonIndex = count;
					Main.liststack.getChildren().add(displayTask.newEvent);
					displayTask.eventname.setText(name);
					displayTask.eventDescription.setText(description);
					displayTask.location.setText(location);
					displayTask.dateEvent.setValue(LocalDate.of(year, month, day));
					
					displayTask.minute.setText(min+"");
					if(morn){
						displayTask.am.setSelected(true);
						displayTask.pm.setSelected(false);
						displayTask.hour.setText(hour+"");
					}else{
						displayTask.am.setSelected(false);
						displayTask.pm.setSelected(true);
						displayTask.hour.setText(hour+"");
						
						
					}
				});
				
			}
		}
	}

}
