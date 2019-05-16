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
	public static boolean onListView = true;
	public static boolean canCreate = true;

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
			
			DisplayList.newAssignment.setOnAction(e ->{
				displayTask.newThing = true;
				if(!(list.getChildren().contains(displayTask.newAssignment) && calendarstack.getChildren().contains(displayTask.newAssignment))) {
					liststack.getChildren().add(displayTask.newAssignment);
				}
				list.getChildren().remove(DisplayList.newAssignment);
				list.getChildren().remove(DisplayList.newEvent);
			});
			
			DisplayList.newEvent.setOnAction(e ->{
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


			});
			// cancel event
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

				int yr = displayTask.dateAssignment.getValue().getYear();
				int mo = displayTask.dateAssignment.getValue().getMonthValue();
				int day = displayTask.dateAssignment.getValue().getDayOfMonth();
				System.out.println(displayTask.assignDescription.getText());
				Task a = new Assignment(displayTask.assignmentname.getText(), displayTask.assignDescription.getText(),
						displayTask.subject.getText(), displayTask.highPriority, yr, mo, day);

				List.addAssignment(a);

				if (onListView) {
					liststack.getChildren().remove(newAssignment);
					DisplayList.displayTasks(list);
				} else {
					displayCalendar.displayTasks();
					calendarstack.getChildren().remove(newAssignment);

				}
				
				displayTask.assignmentname.clear();
				displayTask.assignDescription.clear();
				displayTask.subject.clear();
				displayTask.hour.clear();
				displayTask.minute.clear();
				displayTask.dateAssignment.setValue(null);
				StoreData.writeFile();
			});
			
//			Done Event
			displayTask.doneEvent.setOnMouseClicked(event -> {
				//System.out.println(displayTask.eventDescription.getText());
				int yr = displayTask.dateEvent.getValue().getYear();
				int mo = displayTask.dateEvent.getValue().getMonthValue();
				int day = displayTask.dateEvent.getValue().getDayOfMonth();
				int hr = Integer.valueOf(displayTask.hour.getText())+displayTask.mornafternoon;
				int min = Integer.valueOf(displayTask.minute.getText());
			//	System.out.println(hr+" " + min);

				Task a = new Event(displayTask.eventname.getText(), displayTask.eventDescription.getText(),

						displayTask.subject.getText(), yr, mo, day,hr,min);
				if(displayTask.newThing) {
					List.addEvent(a);
				}
				else{ 
					System.out.println(displayTask.newThing);
					List.updateTask(a);
				}
					

			//	List.addEvent(a);
				//DisplayList.addTask.getSelectionModel().clearSelection();

			
				if (onListView) {
					liststack.getChildren().remove(newEvent);
					DisplayList.displayTasks(list);
				} else {
					displayCalendar.displayTasks();
					calendarstack.getChildren().remove(newEvent);

				}
				displayTask.eventname.clear();
				displayTask.eventDescription.clear();
				displayTask.location.clear();
				displayTask.dateEvent.setValue(null);
				StoreData.writeFile();
			});
			
			
			StoreData.readFile();
			DisplayList.displayTasks(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
