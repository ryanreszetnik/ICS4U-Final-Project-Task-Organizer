package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
  
public class DisplayList {
	//All the necessary initializations
	public static ArrayList<Button> list = new ArrayList<>();
	public static Pane pane;
	static Button addTask;
	static Button assignment = new Button("Assignment");
	static Button event = new Button("Event");
	static Button calendarView = new Button("Switch to Calendar");
	static String[] values = {"Add New Task", "Event", "Assignment"};
	static Button newEvent = new Button("New Event");
	static Button newAssignment = new Button("New Assignment");
	static Button toInstructions = new Button("Switch to Instructions");
	static boolean isAssignment;
	static boolean exists;
	static int buttonIndex;
	static Rectangle top;
	
	//Adds a new button to the arraylist and sets the style 
	public static void addButton(Button a) {
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		list.add(a);
	}
	
	//Creates a button to add to the arraylist with a label based on the task parameter
	public static void addTask(Task t){
		Button newTask = new Button(t.toString());
		addButton(newTask);
	}
	
	
	public static void setup() {
		//Sets up the two buttons used to create new events and assignments
		newEvent.setPrefSize(140, 30);
		newEvent.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		newEvent.setTranslateX(3);
		newEvent.setTranslateY(100);
		newAssignment.setPrefSize(140, 30);
		newAssignment.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		newAssignment.setTranslateX(3);
		newAssignment.setTranslateY(65);
		
		//Sets up the button that switches the view from list to instructions
		toInstructions.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		toInstructions.setPrefHeight(60);
		toInstructions.setTranslateX(250);
		
		//Sets up the pane colour as well as the button used to switch from list to calendar
		pane.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #999999;");
		Font f1 = new Font(20);
		calendarView.setFont(f1);
		calendarView.setPrefHeight(60);
		calendarView.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		calendarView.setTranslateX(510);
		
		//The two buttons that show up when you want to create a new task. They each bring up different menus
		Font fo = new Font(18);
		assignment.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		assignment.setFont(fo);
		event.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		event.setFont(fo);

		//Sets up the button used to let you choose whether you want to create a new assignment or event 
		addTask = new Button("New Task");
		addTask.setPrefSize(150, 60);
		addTask.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");

		//The top border of the pane
		top = new Rectangle(700, 60);
		top.setFill(Color.rgb(96, 96, 96));
		
		//Adds all the nodes that were set up to the pane
		pane.getChildren().addAll(top, addTask, calendarView, toInstructions);
		
		
		/*The event handler that determines whether to bring up the "newEvent" and "newAssignment" buttons, 
		the condition being whether or not they already exist*/
		addTask.setOnMouseClicked((e) -> {

			if(!(pane.getChildren().contains(newAssignment) && pane.getChildren().contains(newEvent))) {
				pane.getChildren().addAll(newEvent, newAssignment);
			}
			else {
				pane.getChildren().removeAll(newEvent, newAssignment);
			}

		});
	
	}
	
	//The method that displays the arraylist of buttons on the list view. 
	public static void displayTasks() {
		
		//Loops through every button in the arraylist
		for (int i = 0; i < list.size(); i++) {
			//Sets the size and position of each button. The vertical position is based on how many buttons there are before it
			list.get(i).setPrefSize(680, 100);
			list.get(i).setTranslateX(10);
			list.get(i).setTranslateY(65 + (i * 100));
			
			//Determines whether to change the border of the button to red, depending on the priority of the assignment it represents
			if( List.list.get(i).isAssignment() && ((Assignment)List.list.get(i)).getPriority()){				
				list.get(i).setStyle("-fx-border-color: #990000; -fx-border-width: 5px; -fx-background-color: #5e5e5e;");
			}
			else {
			list.get(i).setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
			}
			
			//Sets the font size of the button
			Font fo = new Font(25);
			list.get(i).setFont(fo);
			
			//If the button does not already exist, add it
			if(pane.getChildren().contains(list.get(i)) == false){
				pane.getChildren().add(list.get(i));
			}
			
			//This is to ensure that the top rectangle and all of the buttons on it remain at the top when scrolling down on the list
			pane.getChildren().removeAll(top, addTask, calendarView, toInstructions);
			pane.getChildren().addAll(top, addTask, calendarView, toInstructions);

			//The next two blocks of code are used to bring up the correct menu depending on whether an assignment or an event was pressed
			
			//If the button represents an assignment, this code runs. 
			if(List.list.get(i).isAssignment()) {
				/*Initializes the name, subject, description, date, and priority of the assignment to be used in the eventhandler,
				where they will be pre-displayed in the menu that pops up*/
				int count = i;
				String name = List.list.get(i).name;
				String description = List.list.get(i).description;
				String subject = ((Assignment)(List.list.get(i))).getSubject();
				boolean highPri = ((Assignment)(List.list.get(i))).getPriority();
				Date date = List.list.get(i).date;
				int year = date.year;
				int month = date.month;
				int day = date.day; 
				
				//The actual eventhandler for when the button is pressed
				list.get(i).setOnAction(e ->{
					//The code in here fills out the DisplayTask "form" depending on the variables initialized above
					DisplayTask.newThing = false;
					buttonIndex = count;
					Main.liststack.getChildren().add(DisplayTask.newAssignment);
					DisplayTask.assignmentname.setText(name);
					DisplayTask.assignDescription.setText(description);
					DisplayTask.subject.setText(subject);
					DisplayTask.high.setSelected(highPri);
					DisplayTask.regular.setSelected(!highPri);
					DisplayTask.highPriority = highPri;
					DisplayTask.dateAssignment.setValue(LocalDate.of(year,month,day)); 
				});
			}
			
			//This else statement is very similar to the if before it, but with a few changes to support event data
			else {
				//All the necessarily variables are created here. There is location instead of subject in an event 
				int count = i;
				String name = List.list.get(i).name;
				String description = List.list.get(i).description;
				String location = ((Event)(List.list.get(i))).getLocation();
				Date date = List.list.get(i).date;
				int year = date.year;
				int month = date.month;
				int day = date.day;
				DisplayTask.dateEvent.setValue(LocalDate.of(year, month, day));
				int hour = date.getHour();
				int min = date.minute;
				boolean morn = date.isMorining();
				
				list.get(i).setOnAction(e ->{
					DisplayTask.newThing = false;
					buttonIndex = count;
					DisplayTask.newThing = false;
					Main.liststack.getChildren().add(DisplayTask.newEvent);
					DisplayTask.eventname.setText(name);
					DisplayTask.eventDescription.setText(description);
					DisplayTask.location.setText(location);
					DisplayTask.dateEvent.setValue(LocalDate.of(year, month, day));
			
					DisplayTask.minute.setText(min+"");
					if(morn){
						DisplayTask.am.setSelected(true);
						DisplayTask.pm.setSelected(false);
						DisplayTask.hour.setText(hour+"");
					}
					else{
						DisplayTask.am.setSelected(false);
						DisplayTask.pm.setSelected(true);
						DisplayTask.hour.setText(hour+"");
					}

				});
				
			}
		
		}
	}

}
