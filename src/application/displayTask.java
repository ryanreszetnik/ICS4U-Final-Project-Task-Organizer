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
	public static Pane newTask;
	public static TextField name;
	public static TextArea description;
	public static DatePicker date;
	public static Button done;
	public static Button cancel;
	
	public static boolean isEvent;
	
	
	public static void displayEvent(){
		isEvent = true;
		name = new TextField(); 
		TextField location = new TextField();
		name.setPromptText("Name");
		location.setPromptText("Location");
		location.setTranslateX(200);
		
		description = new TextArea();
		description.setPromptText("Description");
		description.setTranslateY(30);
		date = new DatePicker();
		date.setTranslateY(220);
		

		System.out.println("event");
		
		done = new Button("Done");

		done.setTranslateX(300);
		done.setTranslateY(400);
		newTask.getChildren().addAll(done,name,description,date,location);
		buttonControls();

		newTask.getChildren().addAll(name,description,date,location);

	}
	public static void displayAssignment(){
		Rectangle background = new Rectangle(590,325);
		background.setFill(Color.DARKGREY);
		isEvent = false;
		name = new TextField(); 
		TextField subject = new TextField();
		name.setPromptText("Assignment Name");
		name.setTranslateY(25);
		name.setTranslateX(25);
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
		
		Label descriptionTitle = new Label("Description");
		descriptionTitle.setTranslateX(265);
		descriptionTitle.setTranslateY(55);
		
		Label dueDateTitle = new Label("Due Date");
		dueDateTitle.setTranslateX(80);
		dueDateTitle.setTranslateY(260);
		
		description = new TextArea();
		description.setPromptText("Description");
		description.setTranslateY(75);
		description.setTranslateX(25);
		
		
		date = new DatePicker();
		date.setTranslateY(275);
		date.setTranslateX(25);
		
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
		done = new Button("Done");

		done.setTranslateX(510);
		done.setTranslateY(275);
		cancel = new Button("Cancel");

		cancel.setTranslateX(440);
		cancel.setTranslateY(275);
		newTask.getChildren().addAll(background,name,description,date,subject,done, high, regular,cancel, nameTitle,subjectTitle,priorityTitle,descriptionTitle,dueDateTitle);
		buttonControls();
	}
	public static void buttonControls(){
		done.setOnMouseClicked(event -> {
			
		});
		cancel.setOnMouseClicked(event -> {
			
		});
	}
	
}
