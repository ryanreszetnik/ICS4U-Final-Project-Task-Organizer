package application;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DisplayTask {
	//declarations for everything
	//does separate ones for events and assignment to avoid getting text issues
	public static Pane newEvent;
	public static Pane newAssignment;
	
	
	public static TextArea eventDescription;
	public static TextArea assignDescription;
	
	public static DatePicker dateEvent;
	public static DatePicker dateAssignment;
	public static Button doneEvent;
	public static Button doneAssignment;
	public static Button cancelEvent;
	public static Button cancelAssignment;
	public static TextField eventname;
	public static TextField assignmentname;
	public static TextField subject;
	public static TextField location;
	public static TextField hour;
	public static TextField minute;
	public static int mornafternoon = 0;
	public static boolean highPriority;
	public static ToggleButton am;
	public static ToggleButton pm;
	public static ToggleButton regular;
	public static ToggleButton high;
	public static boolean newThing;
	public static Button deleteEvent;
	public static Button deleteAssign;
	
	public static boolean isEvent;
	
	
	//all the code for creating the newEvent window
	//does all of the declarations, formatting and translating for everything
	public static void displayEvent(){
		
		
		eventDescription = new TextArea();
		eventDescription.setPromptText("Enter Description");
		eventDescription.setTranslateY(75);
		eventDescription.setTranslateX(25);
		
		Label eventDescriptionTitle = new Label("Description");
		eventDescriptionTitle.setTranslateX(265);
		eventDescriptionTitle.setTranslateY(55);
		
		Label nameTitle = new Label("Event Name");
		nameTitle.setTranslateX(45);
		nameTitle.setTranslateY(5);
		
		dateEvent = new DatePicker();
		dateEvent.setTranslateY(300);
		dateEvent.setTranslateX(25);
		eventname = new TextField(); 
		eventname.setPromptText("Name");
		eventname.setTranslateY(25);
		eventname.setTranslateX(25);
		
		Rectangle background = new Rectangle(590,350);
		background.setFill(Color.DARKGREY);
		isEvent = true;
		
		location = new TextField();
		location.setPromptText("Enter Location");
		location.setTranslateX(200);
		location.setTranslateY(25);
		
		Label locationLabel = new Label("Location");
		locationLabel.setTranslateX(200);
		locationLabel.setTranslateY(5);
		
		Label nameLabel = new Label("Name");
		nameLabel.setTranslateX(25);
		nameLabel.setTranslateY(5);
		
		Label dateLabel = new Label("Date");
		dateLabel.setTranslateX(100);
		dateLabel.setTranslateY(275);
				
		doneEvent = new Button("Done");
		doneEvent.setTranslateX(510);
		doneEvent.setTranslateY(300);
		
		cancelEvent = new Button("Cancel");
		cancelEvent.setTranslateX(440);
		cancelEvent.setTranslateY(300);
		
		deleteEvent = new Button("Delete");
		deleteEvent.setTranslateX(370);
		deleteEvent.setTranslateY(300);
		
		hour = new TextField();
		minute = new TextField();
		hour.setPrefWidth(35);
		minute.setPrefWidth(35);
		hour.setPromptText("00");
		minute.setPromptText("00");
		hour.setTranslateX(400);
		minute.setTranslateX(440);
		hour.setTranslateY(25);
		minute.setTranslateY(25);
		Label time = new Label("Time:");
		Label timecolon = new Label(":");
		time.setTranslateX(400);
		time.setTranslateY(5);
		timecolon.setTranslateX(435);
		timecolon.setTranslateY(27);
		
		//uses toggle group for am/pm buttons so only 1 is selected
		ToggleGroup ampm = new ToggleGroup();
		am = new ToggleButton("am");
		pm = new ToggleButton("pm");
		am.setToggleGroup(ampm);
		pm.setToggleGroup(ampm);
		am.setSelected(true);
		am.setTranslateY(25);
		am.setTranslateX(485);
		pm.setTranslateY(25);
		pm.setTranslateX(520);
		
		//event handlers for am / pm (morningafternoon converts 12hr based time to 24hr based time)
		am.setOnMouseClicked(event ->{
			mornafternoon = 0;
		});
		pm.setOnMouseClicked(event ->{
			mornafternoon = 12;
		});
		
		//code for when you delete an event
		deleteEvent.setOnMouseClicked(event ->{
			DisplayList.pane.getChildren().remove(DisplayList.list.get(DisplayList.buttonIndex));
			DisplayCalendar.root.getChildren().remove(DisplayCalendar.tasks.get(DisplayList.buttonIndex));
			List.list.remove(DisplayList.buttonIndex);
			DisplayList.list.remove(DisplayList.buttonIndex);
			DisplayCalendar.tasks.remove(DisplayList.buttonIndex);
			Main.cancelEvent();
			DisplayList.displayTasks();
			DisplayCalendar.displayTasks();
			StoreData.writeFile();
		});
		//adds everything to the pane
		newEvent.getChildren().addAll( background,nameTitle,dateLabel,eventname,eventDescription,dateEvent,doneEvent,cancelEvent,eventDescriptionTitle, location, locationLabel, time, timecolon, hour, minute,am,pm,deleteEvent);


	}
	//all the code for creating the newAssignment window
	//does all of the declarations, formatting and translating for everything
	public static void displayAssignment(){
		assignDescription = new TextArea();
		assignDescription.setPromptText("Enter Description");
		assignDescription.setTranslateY(75);
		assignDescription.setTranslateX(25);
		
		Label assignDescriptionTitle = new Label("Description");
		assignDescriptionTitle.setTranslateX(265);
		assignDescriptionTitle.setTranslateY(55);
		
		Label dateLabel = new Label("Date");
		dateLabel.setTranslateX(100);
		dateLabel.setTranslateY(275);
		
		dateAssignment = new DatePicker();
		dateAssignment.setTranslateY(300);
		dateAssignment.setTranslateX(25);
		assignmentname = new TextField(); 
		assignmentname.setPromptText("Enter Name");
		assignmentname.setTranslateY(25);
		assignmentname.setTranslateX(25);
		Rectangle background = new Rectangle(590,350);
		background.setFill(Color.DARKGREY);
		isEvent = false;
	
			
		doneAssignment = new Button("Done");
		doneAssignment.setTranslateX(510);
		doneAssignment.setTranslateY(300);
		
		cancelAssignment = new Button("Cancel");
		cancelAssignment.setTranslateX(440);
		cancelAssignment.setTranslateY(300);
		
		deleteAssign = new Button("Delete");
		deleteAssign.setTranslateX(370);
		deleteAssign.setTranslateY(300);
		
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
		//toggle group for priority
		ToggleGroup hl = new ToggleGroup();
		regular = new ToggleButton("Regular");
		high = new ToggleButton("High");
		regular.setToggleGroup(hl);
		high.setToggleGroup(hl);
		regular.setSelected(true);
		highPriority = false;
		regular.setTranslateY(25);
		regular.setTranslateX(440);
		high.setTranslateY(25);
		high.setTranslateX(515);
		high.setTextFill(Color.DARKRED);
		
		//event handlers for changing priority
		high.setOnMouseClicked(event ->{
			
			highPriority = true;
		});
		regular.setOnMouseClicked(event ->{
			
			highPriority = false;
		});
		//event handler for when you delete an assignment
		deleteAssign.setOnMouseClicked(event ->{
			DisplayList.pane.getChildren().remove(DisplayList.list.get(DisplayList.buttonIndex));
			DisplayCalendar.root.getChildren().remove(DisplayCalendar.tasks.get(DisplayList.buttonIndex));
			List.list.remove(DisplayList.buttonIndex);
			DisplayList.list.remove(DisplayList.buttonIndex);
			DisplayCalendar.tasks.remove(DisplayList.buttonIndex);
			DisplayList.displayTasks();
			DisplayCalendar.displayTasks();
			Main.cancelAssignment();
			StoreData.writeFile();
		});
		//adds everything to the pane
		newAssignment.getChildren().addAll(background,dateLabel,assignmentname,assignDescription,dateAssignment,subject,doneAssignment, high, regular,cancelAssignment, nameTitle,subjectTitle,priorityTitle,assignDescriptionTitle,deleteAssign);
		
	}
	
	//checks if the time fields are filled properly with numbers and within the 12 hr clock time range
	public static boolean timeFilled(){
		if(hour.getText().equals("") || minute.getText().equals("")){
			return false;
		}
		if(contains(hour.getText(),"0123456789") && contains(minute.getText(),"0123456789")){
			if(Integer.valueOf(hour.getText())<= 12 && Integer.valueOf(minute.getText()) <=  59){
				return true;
			}
		}
		return false;
	}
	//used to check if the time text field only has numbers (called in timeFilled) 
	public static boolean contains(String input, String good){
		boolean isgood = false;
		for(int i = 0; i < input.length(); i++){
			for(int p = 0; p < good.length(); p++){
				if(input.charAt(i) == good.charAt(p)){
					isgood = true;
				}
			}
			if(!isgood){
				return false;
			}
			isgood = false;
		}
	
		return true;
	}
	
}
