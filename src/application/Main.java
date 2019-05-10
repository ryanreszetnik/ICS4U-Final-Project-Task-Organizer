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

	@Override
	public void start(Stage primaryStage) {
		try {

			// calendarstack.getChildren().addAll(cal,newTask);

			calendarstack.getChildren().addAll(cal);// take out newTask if you
													// want

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

			DisplayList.addTask.setOnAction((e) -> {
				int a = DisplayList.addTask.getSelectionModel().getSelectedIndex();
				
				DisplayList.addTask.setValue("Add New Task");
				liststack.getChildren().remove(newAssignment);
				liststack.getChildren().remove(newEvent);
				if (a == 0) {

					liststack.getChildren().add(newEvent);

					// displayTask.displayEvent();
				} else {

					liststack.getChildren().add(newAssignment);

					// PUT DISPLAYASSIGNMENT CODE IN HERE
				}
			});
			displayCalendar.addTask.setOnAction((e) -> {
				int a = displayCalendar.addTask.getSelectionModel().getSelectedIndex();

				
				displayCalendar.addTask.setValue("Add New Task");
				calendarstack.getChildren().remove(newAssignment);
				calendarstack.getChildren().remove(newEvent);
				if (a == 0) {

					calendarstack.getChildren().add(newEvent);

					// displayTask.displayEvent();
				} else {

					calendarstack.getChildren().add(newAssignment);

					// PUT DISPLAYASSIGNMENT CODE IN HERE
				}
			});

			DisplayList.calendarView.setOnMouseClicked(event -> {
				primaryStage.setScene(calendarview);
				newEvent.setTranslateX(160);
				newEvent.setTranslateY(150);
				newAssignment.setTranslateX(160);
				newAssignment.setTranslateY(150);
				onListView = false;
			});
			displayCalendar.toList.setOnMouseClicked(event -> {
				primaryStage.setScene(listview);
				newEvent.setTranslateX(0);
				newEvent.setTranslateY(0);
				newAssignment.setTranslateX(0);
				newAssignment.setTranslateY(0);
				onListView = true;
			});
			displayTask.cancelAssignment.setOnMouseClicked(event -> {
				if (onListView) {
					DisplayList.addTask.getSelectionModel().clearSelection();
					liststack.getChildren().remove(newAssignment);
					liststack.getChildren().remove(newEvent);

				} else {

					displayCalendar.addTask.getSelectionModel().clearSelection();
					calendarstack.getChildren().remove(newEvent);
					calendarstack.getChildren().remove(newAssignment);
				}

			});
			displayTask.cancelEvent.setOnMouseClicked(event -> {
				if (onListView) {
					DisplayList.addTask.getSelectionModel().clearSelection();
					liststack.getChildren().remove(newEvent);
					liststack.getChildren().remove(newAssignment);

				} else {

					displayCalendar.addTask.getSelectionModel().clearSelection();
					calendarstack.getChildren().remove(newEvent);
					calendarstack.getChildren().remove(newAssignment);

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
