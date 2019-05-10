package application;

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
			displayTask.displayAssignment();
			displayTask.displayEvent();
			DisplayList.setup(list);
			displayCalendar.setup(cal);

			primaryStage.setScene(listview);
			primaryStage.show();
			// Add task List
			DisplayList.addTask.setOnAction((e) -> {

				int a = DisplayList.addTask.getSelectionModel().getSelectedIndex();

				DisplayList.addTask.setValue("Add New Task");

				if (a == 0) {
					if (liststack.getChildren().contains(newEvent) == false
							&& liststack.getChildren().contains(newAssignment) == false) {
						liststack.getChildren().add(newEvent);
					}

				} else {
					if (liststack.getChildren().contains(newEvent) == false
							&& liststack.getChildren().contains(newAssignment) == false) {
						liststack.getChildren().add(newAssignment);
					}

				}

			});
			// Add task Calendar
			displayCalendar.addTask.setOnAction((e) -> {

				int a = displayCalendar.addTask.getSelectionModel().getSelectedIndex();

				displayCalendar.addTask.setValue("Add New Task");

				if (a == 0) {

					if (calendarstack.getChildren().contains(newAssignment) == false
							&& calendarstack.getChildren().contains(newEvent) == false) {
						calendarstack.getChildren().add(newEvent);
					}

				} else {

					if (calendarstack.getChildren().contains(newAssignment) == false
							&& calendarstack.getChildren().contains(newEvent) == false) {
						calendarstack.getChildren().add(newAssignment);
					}

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
			});
			// calendar--> list
			displayCalendar.toList.setOnMouseClicked(event -> {
				primaryStage.setScene(listview);
				newEvent.setTranslateX(0);
				newEvent.setTranslateY(0);
				newAssignment.setTranslateX(0);
				newAssignment.setTranslateY(0);
				onListView = true;
			});
			// cancel assignment
			displayTask.cancelAssignment.setOnMouseClicked(event -> {

				DisplayList.addTask.getSelectionModel().clearSelection();
				displayCalendar.addTask.getSelectionModel().clearSelection();
				if (onListView) {

					liststack.getChildren().remove(newAssignment);

				} else {

					calendarstack.getChildren().remove(newAssignment);
				}

			});
			// cancel event
			displayTask.cancelEvent.setOnMouseClicked(event -> {

				DisplayList.addTask.getSelectionModel().clearSelection();
				displayCalendar.addTask.getSelectionModel().clearSelection();

				if (onListView) {
					liststack.getChildren().remove(newEvent);

				} else {

					calendarstack.getChildren().remove(newEvent);

				}
			});
			
			//Done assignment
			displayTask.doneAssignment.setOnMouseClicked(event ->{
				System.out.println(displayTask.name.getText());
				Task a = new Assignment(displayTask.name.getText(), displayTask.description.getText(), "Functions", true);
				List.addAssignment(a);
				DisplayList.displayTasks(cal);
				DisplayList.addTask.getSelectionModel().clearSelection();
				displayCalendar.addTask.getSelectionModel().clearSelection();
				if (onListView) {
					liststack.getChildren().remove(newEvent);

				} else {

					calendarstack.getChildren().remove(newEvent);

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
