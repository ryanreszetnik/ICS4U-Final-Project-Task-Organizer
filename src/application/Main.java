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
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane cal = new Pane();
			Pane newTask = new Pane();
			Pane list = new Pane();
			StackPane calendarstack = new StackPane();
			StackPane liststack = new StackPane();
			calendarstack.getChildren().add(newTask);
			liststack.getChildren().add(list);
			
			Scene calendarview = new Scene(calendarstack,130*7,100*6+95);
			Scene listview = new Scene(liststack,700,900);
			calendarview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			listview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			displayCalendar.setup(cal);

			displayTask.newTask = newTask;
			displayTask.displayEvent();
			primaryStage.setScene(calendarview);

			DisplayList.setup(list);
			
			//primaryStage.setScene(listview);

			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
