package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
  
public class DisplayList {
	public static ArrayList<Button> list = new ArrayList<>();
	public static Pane pane;
	static Button addTask;
	static Button assignment = new Button("Assignment");
	static Button event = new Button("Event");
	static Button calendarView = new Button("Switch to Calendar");
	static String[] values = {"Add New Task", "Event", "Assignment"};
	static Button newEvent = new Button("New Event");
	static Button newAssignment = new Button("New Assignment");
	static boolean isAssignment;
	static boolean exists;
	static int buttonIndex;
	static Rectangle top;
	
	

	public static void addButton(Button a) {
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		list.add(a);
	}
	
	public static void addTask(Task t){
		Button newTask = new Button(t.toString());
		addButton(newTask);
	}
	
	

	public static void setup() {
		newEvent.setPrefSize(140, 30);
		newEvent.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		newAssignment.setPrefSize(140, 30);
		newAssignment.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		newAssignment.setTranslateX(3);
		newAssignment.setTranslateY(65);
		
		newEvent.setTranslateX(3);
		newEvent.setTranslateY(100);
		
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
		addTask.setOnMouseClicked((e) -> {

			if(!(pane.getChildren().contains(newAssignment) && pane.getChildren().contains(newEvent))) {
				pane.getChildren().addAll(newEvent, newAssignment);
			}
			else {
				pane.getChildren().removeAll(newEvent, newAssignment);
			}

		});
	
	}
	
	public static void displayTasks() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPrefSize(680, 100);
			list.get(i).setTranslateX(10);
			list.get(i).setTranslateY(65 + (i * 100));
			if( List.list.get(i).isAssignment() && ((Assignment)List.list.get(i)).getPriority()){				
				list.get(i).setStyle("-fx-border-color: #990000; -fx-border-width: 5px; -fx-background-color: #5e5e5e;");
			}
			else {
			list.get(i).setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
			}
			
			Font fo = new Font(25);
			list.get(i).setFont(fo);
			if(pane.getChildren().contains(list.get(i)) == false){
				pane.getChildren().add(list.get(i));
			}
			

			pane.getChildren().removeAll(top, addTask, calendarView);
			pane.getChildren().addAll(top, addTask, calendarView);

			if(List.list.get(i).isAssignment()) {
			int count = i;
			String name = List.list.get(i).name;
			String description = List.list.get(i).description;
			String subject = ((Assignment)(List.list.get(i))).getSubject();
			boolean highPri = ((Assignment)(List.list.get(i))).getPriority();
			Date date = List.list.get(i).date;
			int year = date.year;
			int month = date.month;
			int day = date.day; 
			list.get(i).setOnAction(e ->{
				DisplayTask.newThing = false;
					buttonIndex = count;
					Main.liststack.getChildren().add(DisplayTask.newAssignment);
					DisplayTask.assignmentname.setText(name);
					DisplayTask.assignDescription.setText(description);
					DisplayTask.subject.setText(subject);
					DisplayTask.high.setSelected(highPri);
					DisplayTask.regular.setSelected(!highPri);
					DisplayTask.highPriority = highPri;
					DisplayTask.dateAssignment.setValue(LocalDate.of(year,month,day)); 
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
				DisplayTask.dateEvent.setValue(LocalDate.of(year, month, day));
				int hour = date.getHour();
				int min = date.minute;
				boolean morn = date.isMorining();
				
				list.get(i).setOnAction(e ->{
					DisplayTask.newThing = false;
					buttonIndex = count;
					DisplayTask.newThing = false;
					Main.liststack.getChildren().add(DisplayTask.newEvent);
					DisplayTask.eventname.setText(name);
					DisplayTask.eventDescription.setText(description);
					DisplayTask.location.setText(location);
					DisplayTask.dateEvent.setValue(LocalDate.of(year, month, day));
			
					DisplayTask.minute.setText(min+"");
					if(morn){
						DisplayTask.am.setSelected(true);
						DisplayTask.pm.setSelected(false);
						DisplayTask.hour.setText(hour+"");
					}
					else{
						DisplayTask.am.setSelected(false);
						DisplayTask.pm.setSelected(true);
						DisplayTask.hour.setText(hour+"");
					}

				});
				
			}
		
		}
	}

}
