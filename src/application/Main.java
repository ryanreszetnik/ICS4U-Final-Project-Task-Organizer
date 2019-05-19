package application;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
   
	static Pane cal = new Pane();
	static Pane newEvent = new Pane();
	static Pane newAssignment = new Pane();
	static Pane list = new Pane();
	static StackPane calendarstack = new StackPane();
	static StackPane liststack = new StackPane();
	static Scene calendarview = new Scene(calendarstack, 130 * 7, 100 * 6 + 95);
	static Scene listview = new Scene(liststack, 700, 900);
	static boolean onListView = true;
	static boolean canCreate = true;
	static boolean requiredFields = false;
	static Label missing = new Label();


	@Override
	public void start(Stage primaryStage) {
		try {
			
			calendarstack.getChildren().add(cal);
			liststack.getChildren().add(list);
			calendarview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			listview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			displayTask.newEvent = newEvent;
			displayTask.newAssignment = newAssignment;
			displayCalendar.root = cal;
			displayTask.displayAssignment();
			displayTask.displayEvent();
			DisplayList.setup(list);
			displayCalendar.setup();

			primaryStage.setScene(listview);
			primaryStage.show();

			// Add task List
			//scrolling
			listview.setOnKeyPressed(e -> {
				switch (e.getCode()) {
				case UP:
					for(int i = 0; i < DisplayList.list.size();i++){
						DisplayList.list.get(i).setTranslateY(DisplayList.list.get(i).getTranslateY()+100);
					}
					break;
				case DOWN:
					for(int i = 0; i < DisplayList.list.size();i++){
						DisplayList.list.get(i).setTranslateY(DisplayList.list.get(i).getTranslateY()-100);
					}
					break;

				}
			});
			
			DisplayList.addTask.setOnMouseClicked((e) -> {
				
				DisplayList.newAssignment.setTranslateX(3);
				DisplayList.newAssignment.setTranslateY(65);
				
				DisplayList.newEvent.setTranslateX(3);
				DisplayList.newEvent.setTranslateY(100);
				
				
				if(!(list.getChildren().contains(DisplayList.newAssignment) && list.getChildren().contains(DisplayList.newEvent))) {
					list.getChildren().addAll(DisplayList.newEvent, DisplayList.newAssignment);
				}
				else {
					list.getChildren().removeAll(DisplayList.newEvent, DisplayList.newAssignment);
				}

			});
			
			
			//Creates a new assignment 
			DisplayList.newAssignment.setOnAction(e ->{
				displayTask.newThing = true;
				if(!(list.getChildren().contains(displayTask.newAssignment) && calendarstack.getChildren().contains(displayTask.newAssignment))) {
					liststack.getChildren().add(displayTask.newAssignment);
				}
				list.getChildren().remove(DisplayList.newAssignment);
				list.getChildren().remove(DisplayList.newEvent);
			});

			//Creates a new event 
			DisplayList.newEvent.setOnAction(e ->{
				displayTask.dateEvent.setValue(null);
				displayTask.newThing = true;
				if(!(liststack.getChildren().contains(displayTask.newEvent) && calendarstack.getChildren().contains(displayTask.newEvent))) {
					liststack.getChildren().add(displayTask.newEvent);
				}
				list.getChildren().remove(DisplayList.newAssignment);
				list.getChildren().remove(DisplayList.newEvent);
			});
			

			// list--> calendar
			DisplayList.calendarView.setOnMouseClicked(event -> {
				primaryStage.setScene(calendarview);
				newEvent.setTranslateX(160);
				newEvent.setTranslateY(150);
				newAssignment.setTranslateX(160);
				newAssignment.setTranslateY(150);
				onListView = false;
				displayCalendar.displayTasks();
			});
			// calendar--> list
			displayCalendar.toList.setOnMouseClicked(event -> {
				primaryStage.setScene(listview);
				newEvent.setTranslateX(0);
				newEvent.setTranslateY(0);
				newAssignment.setTranslateX(0);
				newAssignment.setTranslateY(0);
				onListView = true;
				DisplayList.displayTasks(list);
			});
			
			// cancel assignment
			displayTask.cancelAssignment.setOnMouseClicked(event -> {
				displayTask.newThing = false;
				if (liststack.getChildren().contains(displayTask.newAssignment)) {

					liststack.getChildren().remove(newAssignment);

				} else {

					calendarstack.getChildren().remove(newAssignment);
				}
				displayTask.assignmentname.clear();
				displayTask.assignDescription.clear();
				displayTask.subject.clear();
				displayTask.dateAssignment.setValue(null);
				displayTask.newAssignment.getChildren().remove(missing);
				requiredFields = false;
			});
		
			// Cancel event
			displayTask.cancelEvent.setOnMouseClicked(event -> {
				displayTask.newThing = false;
				if (liststack.getChildren().contains(displayTask.newEvent)) {
					liststack.getChildren().remove(newEvent);

				} else {

					calendarstack.getChildren().remove(newEvent);

				}
				displayTask.eventname.clear();
				displayTask.eventDescription.clear();
				displayTask.location.clear();
				displayTask.dateEvent.setValue(null);
				displayTask.hour.clear();
				displayTask.minute.clear();
				displayTask.newEvent.getChildren().remove(missing);
				requiredFields = false;
			});
			
//			New Event
			displayCalendar.newEvent.setOnMouseClicked(event -> {
				displayTask.newThing = true;
				displayCalendar.pday = -1;
				if (calendarstack.getChildren().contains(newAssignment) == false
						&& calendarstack.getChildren().contains(newEvent) == false) {
					displayTask.dateEvent.setValue(LocalDate.of(displayCalendar.selectedDate.year,
							displayCalendar.selectedDate.month, displayCalendar.selectedDate.day));
					calendarstack.getChildren().add(newEvent);
				}
				if (cal.getChildren().contains(displayCalendar.newEvent)) {
					cal.getChildren().removeAll(displayCalendar.newEvent, displayCalendar.newAssignment);
				}
			});
			
			//New Assignment
			displayCalendar.newAssignment.setOnMouseClicked(event -> {
				displayCalendar.pday = -1;
				displayTask.newThing = true;
				if (calendarstack.getChildren().contains(newAssignment) == false
						&& calendarstack.getChildren().contains(newEvent) == false) {
					displayTask.dateAssignment.setValue(LocalDate.of(displayCalendar.selectedDate.year,
							displayCalendar.selectedDate.month, displayCalendar.selectedDate.day));
					calendarstack.getChildren().add(newAssignment);
				}

				if (cal.getChildren().contains(displayCalendar.newEvent)) {
					cal.getChildren().removeAll(displayCalendar.newEvent, displayCalendar.newAssignment);
				}
			});

			// Done assignment
			displayTask.doneAssignment.setOnMouseClicked(event -> {
				
				if(displayTask.dateAssignment.getValue() == null || displayTask.assignmentname.getText().equals("")) {
					missing.setText("Required Fields: Name and Date");
					missing.setTextFill(Color.DARKRED);
					missing.setTranslateY(275);
					missing.setTranslateX(40);
					
					if(!requiredFields) {
						displayTask.newAssignment.getChildren().add(missing);
						requiredFields = true;
					}
					
				}

				else {
				int yr = displayTask.dateAssignment.getValue().getYear();
				int mo = displayTask.dateAssignment.getValue().getMonthValue();
				int day = displayTask.dateAssignment.getValue().getDayOfMonth();
				Task a = new Assignment(displayTask.assignmentname.getText(), displayTask.assignDescription.getText(),
						displayTask.subject.getText(), displayTask.highPriority, yr, mo, day);

				if(displayTask.newThing) {
					List.addAssignment(a);
				}
				else {
					List.updateAssignment(a);
					DisplayList.list.get(DisplayList.buttonIndex).setText(List.list.get(DisplayList.buttonIndex).toString());
					displayCalendar.tasks.get(DisplayList.buttonIndex).setText(List.list.get(DisplayList.buttonIndex).Format());
				}

				if (onListView) {
					liststack.getChildren().remove(newAssignment);
					DisplayList.displayTasks(list);
				} else {
					displayCalendar.displayTasks();
					calendarstack.getChildren().remove(newAssignment);
					requiredFields = false;

				}
				
				displayTask.assignmentname.clear();
				displayTask.assignDescription.clear();
				displayTask.subject.clear();
				displayTask.dateAssignment.setValue(null);
				StoreData.writeFile();
				displayTask.newAssignment.getChildren().remove(missing);
				}

			});
			
//			Done Event
			displayTask.doneEvent.setOnMouseClicked(event -> {
				if (!displayTask.timeFilled() || 
						displayTask.dateEvent.getValue() == null || displayTask.eventname.getText().equals("")) {
					Label missing = new Label("Fields Required: Name, Date, Time");
					missing.setTextFill(Color.DARKRED);
					missing.setTranslateY(275);
					missing.setTranslateX(40);
					
					if(!requiredFields) {
						displayTask.newEvent.getChildren().add(missing);
						requiredFields = true;
					}
				}
				else {
				int yr = displayTask.dateEvent.getValue().getYear();
				int mo = displayTask.dateEvent.getValue().getMonthValue();
				int day = displayTask.dateEvent.getValue().getDayOfMonth();
				int hr = Integer.valueOf(displayTask.hour.getText())+ displayTask.mornafternoon;
				int min = Integer.valueOf(displayTask.minute.getText());

				Task a = new Event(displayTask.eventname.getText(), displayTask.eventDescription.getText(), 
						displayTask.location.getText(), yr, mo, day,hr,min);
				
				if(displayTask.newThing) {
					List.addEvent(a);
				}
				
				else{ 
					List.updateEvent(a);
					DisplayList.list.get(DisplayList.buttonIndex).setText(List.list.get(DisplayList.buttonIndex).toString());
				}
				if (onListView) {
					liststack.getChildren().remove(newEvent);
					DisplayList.displayTasks(list);
				} else {
					calendarstack.getChildren().remove(newEvent);
					displayCalendar.displayTasks();
				}
				
				displayTask.eventname.clear();
				displayTask.eventDescription.clear();
				displayTask.location.clear();
				displayTask.dateEvent.setValue(null);
				displayTask.hour.clear();
				displayTask.minute.clear();
				StoreData.writeFile();
				displayTask.newEvent.getChildren().remove(missing);
				requiredFields = false;
				}
			
			});
			
			
			StoreData.readFile();
			DisplayList.displayTasks(list);
			displayCalendar.displayTasks();


			displayCalendar.editTasks();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
