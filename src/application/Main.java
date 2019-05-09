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
	static Pane cal = new Pane();
	static Pane newTask = new Pane();
	static Pane list = new Pane();
	static StackPane calendarstack = new StackPane();
	static StackPane liststack = new StackPane();
	static Scene calendarview = new Scene(calendarstack,130*7,100*6+95);
	static Scene listview = new Scene(liststack,700,900);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			calendarstack.getChildren().add(newTask);
			liststack.getChildren().add(list);
			calendarview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			listview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			DisplayList.setup(list);
			displayCalendar.setup(cal);
			primaryStage.setScene(listview);
			primaryStage.show();
			
			
			DisplayList.addTask.setOnAction((e) ->{
				DisplayList.addTask.getSelectionModel().selectFirst();	
				System.out.print("functioning");
			});
			DisplayList.assignment.setOnMouseClicked(event -> {
				System.out.print("assignment");
			});
			DisplayList.event.setOnMouseClicked(event -> {
				System.out.print("event");
			});
			DisplayList.calendarView.setOnMouseClicked(event -> {
				primaryStage.setScene(calendarview);
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	


	
	public static void main(String[] args) {
		launch(args);
	}
}
