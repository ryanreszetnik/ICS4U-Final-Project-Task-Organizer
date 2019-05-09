package application;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class displayTask {
	public static Pane newTask;
	
	
	public static void displayEvent(){
		TextField name = new TextField(); 
		TextField location = new TextField();
		name.setPromptText("Enter Event Name");
		location.setPromptText("Enter Location");
		location.setTranslateX(200);
		TextArea description = new TextArea();
		description.setPromptText("Description");
		description.setTranslateY(30);
		DatePicker date = new DatePicker();
		date.setTranslateY(220);
		
		newTask.getChildren().addAll(name,description,date,location);
	}
	public static void displayAssignment(){
		TextField name = new TextField(); 
		TextField subject = new TextField();
		name.setPromptText("Enter Assignment Name");
		subject.setPromptText("Enter Subject");
		subject.setTranslateX(200);
		TextArea description = new TextArea();
		description.setPromptText("Description");
		description.setTranslateY(30);
		DatePicker date = new DatePicker();
		date.setTranslateY(220);
		ToggleGroup hl = new ToggleGroup();
		ToggleButton regular = new ToggleButton("Regular");
		ToggleButton high = new ToggleButton("High");
		regular.setToggleGroup(hl);
		high.setToggleGroup(hl);
		regular.setSelected(true);
		regular.setTranslateY(300);
		high.setTranslateY(300);
		high.setTranslateX(70);
		newTask.getChildren().addAll(name,description,date,subject);

	}
	
}
