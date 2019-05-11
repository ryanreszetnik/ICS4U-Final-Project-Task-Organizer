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
	
	
	public static TextArea description;
	public static DatePicker dateEvent;
	public static DatePicker dateAssignment;
	public static Button doneEvent;
	public static Button doneAssignment;
	public static Button cancelEvent;
	public static Button cancelAssignment;
	private static 	Label descriptionTitle;
	public static TextField eventname;
	public static TextField assignmentname;
	public static TextField subject;
	public static boolean highPriority;
	
	
	public static boolean isEvent;
	
	public static void setup(){
		
		description = new TextArea();
		description.setPromptText("Enter Description");
		description.setTranslateY(75);
		description.setTranslateX(25);
		
		descriptionTitle = new Label("Description");
		descriptionTitle.setTranslateX(265);
		descriptionTitle.setTranslateY(55);
		
	}
	
	public static void displayEvent(){
		dateEvent = new DatePicker();
		dateEvent.setTranslateY(300);
		dateEvent.setTranslateX(25);
		eventname = new TextField(); 
		eventname.setTranslateY(25);
		eventname.setTranslateX(25);
		Rectangle background = new Rectangle(590,350);
		background.setFill(Color.DARKGREY);
		isEvent = true;
		
		TextField location = new TextField();
		location.setPromptText("Enter Location");
		location.setTranslateX(200);
		location.setTranslateY(25);
		
		Label locationLabel = new Label("Location");
		locationLabel.setTranslateX(200);
		locationLabel.setTranslateY(5);
		
		Label nameLabel = new Label("Name");
		nameLabel.setTranslateX(25);
		nameLabel.setTranslateY(5);
				
		doneEvent = new Button("Done");
		doneEvent.setTranslateX(510);
		doneEvent.setTranslateY(300);
		
		cancelEvent = new Button("Cancel");
		cancelEvent.setTranslateX(440);
		cancelEvent.setTranslateY(300);
		
		setup();

		newEvent.getChildren().addAll(background,eventname,description,dateEvent,doneEvent,cancelEvent,descriptionTitle);

		//newEvent.getChildren().addAll(background,name,description,date,doneEvent,cancelEvent,descriptionTitle, location, locationLabel, nameLabel);


	}
	public static void displayAssignment(){
		dateAssignment = new DatePicker();
		dateAssignment.setTranslateY(300);
		dateAssignment.setTranslateX(25);
		assignmentname = new TextField(); 
		assignmentname.setTranslateY(25);
		assignmentname.setTranslateX(25);
		Rectangle background = new Rectangle(590,350);
		background.setFill(Color.DARKGREY);
		isEvent = false;
		setup();
			
		doneAssignment = new Button("Done");
		doneAssignment.setTranslateX(510);
		doneAssignment.setTranslateY(300);
		
		cancelAssignment = new Button("Cancel");
		cancelAssignment.setTranslateX(440);
		cancelAssignment.setTranslateY(300);
		subject = new TextField();
		subject.setPromptText("Enter Subject");
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
		
		high.setOnMouseClicked(event ->{
			highPriority = true;
		});
		regular.setOnMouseClicked(event ->{
			highPriority = false;
		});
		
		
		newAssignment.getChildren().addAll(background,assignmentname,description,dateAssignment,subject,doneAssignment, high, regular,cancelAssignment, nameTitle,subjectTitle,priorityTitle,descriptionTitle,dueDateTitle);
		buttonControls();
	}
	
	public static void buttonControls(){
		
		
	}
	
}
