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
		name.setPromptText("Enter Event Name");
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
		
		newTask.getChildren().addAll(name,description,date,regular, high);
	}
	public static void displayAssignment(Task assgn){
		
	}
	
}
