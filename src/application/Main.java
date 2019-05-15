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
				
				displayCalendar.newAssignment.setTranslateX(10);
				displayCalendar.newAssignment.setTranslateY(65);
				
				displayCalendar.newEvent.setTranslateX(10);
				displayCalendar.newEvent.setTranslateY(100);
				
				
				if(!(list.getChildren().contains(displayCalendar.newAssignment) && list.getChildren().contains(displayCalendar.newEvent))) {
					list.getChildren().addAll(displayCalendar.newEvent, displayCalendar.newAssignment);
				}
				else {
					list.getChildren().removeAll(displayCalendar.newEvent, displayCalendar.newAssignment);


				}

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
				
				if (onListView) {

					liststack.getChildren().remove(newAssignment);

				} else {

					calendarstack.getChildren().remove(newAssignment);
				}

			});
			// cancel event
			displayTask.cancelEvent.setOnMouseClicked(event -> {
				

				if (onListView) {
					liststack.getChildren().remove(newEvent);

				} else {

					calendarstack.getChildren().remove(newEvent);

				}
			});

			displayCalendar.newEvent.setOnMouseClicked(event -> {
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
			displayCalendar.newAssignment.setOnMouseClicked(event -> {
				displayCalendar.pday = -1;
				if (calendarstack.getChildren().contains(newAssignment) == false
						&& calendarstack.getChildren().contains(newEvent) == false) {
					displayTask.dateAssignment.setValue(LocalDate.of(displayCalendar.selectedDate.year,
							displayCalendar.selectedDate.month, displayCalendar.selectedDate.day));
					calendarstack.getChildren().add(newAssignment);
				}

				// LocalDate temp = new LocalDate();
				if (cal.getChildren().contains(displayCalendar.newEvent)) {
					cal.getChildren().removeAll(displayCalendar.newEvent, displayCalendar.newAssignment);
				}
			});

			// Done assignment

			displayTask.doneAssignment.setOnMouseClicked(event -> {

				int yr = displayTask.dateAssignment.getValue().getYear();
				int mo = displayTask.dateAssignment.getValue().getMonthValue();
				int day = displayTask.dateAssignment.getValue().getDayOfMonth();

				Task a = new Assignment(displayTask.assignmentname.getText(), displayTask.description.getText(),
						displayTask.subject.getText(), displayTask.highPriority, yr, mo, day);

				List.addAssignment(a);

				if (onListView) {
					liststack.getChildren().remove(newAssignment);
					DisplayList.displayTasks(list);
				} else {
					displayCalendar.displayTasks();
					calendarstack.getChildren().remove(newAssignment);

				}
				StoreData.writeFile();
			});
			
			displayTask.doneEvent.setOnMouseClicked(event -> {

				int yr = displayTask.dateEvent.getValue().getYear();
				int mo = displayTask.dateEvent.getValue().getMonthValue();
				int day = displayTask.dateEvent.getValue().getDayOfMonth();
				int hr = Integer.valueOf(displayTask.hour.getText())+displayTask.mornafternoon;
				int min = Integer.valueOf(displayTask.minute.getText());
				System.out.println(hr+" " + min);

				Task a = new Event(displayTask.eventname.getText(), displayTask.description.getText(),
						displayTask.subject.getText(), yr, mo, day,hr,min);

				List.addAssignment(a);
				//DisplayList.addTask.getSelectionModel().clearSelection();
			
				if (onListView) {
					liststack.getChildren().remove(newEvent);
					DisplayList.displayTasks(list);
				} else {
					displayCalendar.displayTasks();
					calendarstack.getChildren().remove(newEvent);

				}
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
