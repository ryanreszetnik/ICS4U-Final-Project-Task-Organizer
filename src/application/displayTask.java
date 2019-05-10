package application;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class displayTask {
	public static Pane newEvent;
	public static Pane newAssignment;
	
	public static TextField name;
	public static TextArea description;
	public static DatePicker date;
	public static Button doneEvent;
	public static Button doneAssignment;
	public static Button cancelEvent;
	public static Button cancelAssignment;
	private static 	Label descriptionTitle;
	
	
	public static boolean isEvent;
	
	public static void setup(){
		
		
		date = new DatePicker();
		date.setTranslateY(300);
		date.setTranslateX(25);
		
		description = new TextArea();
		description.setPromptText("Description");
		description.setTranslateY(75);
		description.setTranslateX(25);
		
		
		
		name = new TextField(); 
		name.setTranslateY(25);
		name.setTranslateX(25);
		
		descriptionTitle = new Label("Description");
		descriptionTitle.setTranslateX(265);
		descriptionTitle.setTranslateY(55);
		
	}
	
	public static void displayEvent(){
		Rectangle background = new Rectangle(590,350);
		background.setFill(Color.DARKGREY);
		isEvent = true;
		
		name.setPromptText("Event Name");
		
		TextField location = new TextField();
		location.setPromptText("Location");
		location.setTranslateX(200);
		
		doneEvent = new Button("Done");
		doneEvent.setTranslateX(510);
		doneEvent.setTranslateY(300);
		
		cancelEvent = new Button("Cancel");
		cancelEvent.setTranslateX(440);
		cancelEvent.setTranslateY(300);
		
		setup();
		newEvent.getChildren().addAll(background,name,description,date,doneEvent,cancelEvent,descriptionTitle);

	}
	public static void displayAssignment(){
		Rectangle background = new Rectangle(590,350);
		background.setFill(Color.DARKGREY);
		isEvent = false;
		setup();
		
		name.setPromptText("Assignment Name");
		
		doneAssignment = new Button("Done");
		doneAssignment.setTranslateX(510);
		doneAssignment.setTranslateY(300);
		
		cancelAssignment = new Button("Cancel");
		cancelAssignment.setTranslateX(440);
		cancelAssignment.setTranslateY(300);
		TextField subject = new TextField();
		subject.setPromptText("Subject");
		subject.setTranslateX(225);
		subject.setTranslateY(25);
		
		Label nameTitle = new Label("Assignment Name");
		nameTitle.setTranslateX(45);
		nameTitle.setTranslateY(5);
		
		Label subjectTitle = new Label("Subject");
		subjectTitle.setTranslateX(275);
		subjectTitle.setTranslateY(5);
		
		Label priorityTitle = new Label("Priority");
		priorityTitle.setTranslateX(485);
		priorityTitle.setTranslateY(5);
		
		Label dueDateTitle = new Label("Due Date");
		dueDateTitle.setTranslateX(80);
		dueDateTitle.setTranslateY(280);
		
		ToggleGroup hl = new ToggleGroup();
		ToggleButton regular = new ToggleButton("Regular");
		ToggleButton high = new ToggleButton("High");
		regular.setToggleGroup(hl);
		high.setToggleGroup(hl);
		regular.setSelected(true);
		regular.setTranslateY(25);
		regular.setTranslateX(440);
		high.setTranslateY(25);
		high.setTranslateX(515);
		
		newAssignment.getChildren().addAll(background,name,description,date,subject,doneAssignment, high, regular,cancelAssignment, nameTitle,subjectTitle,priorityTitle,descriptionTitle,dueDateTitle);
		buttonControls();
	}
	public static void buttonControls(){
		
		
	}
	
}
