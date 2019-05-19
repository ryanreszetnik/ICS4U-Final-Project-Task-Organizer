package application;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Main extends Application {
	//All initializations for main
	static Pane cal = new Pane();
	static Pane newEvent = new Pane();
	static Pane newAssignment = new Pane();
	static Pane list = new Pane();
	static Pane instructionView = new Pane();
	static StackPane calendarstack = new StackPane();
	static StackPane liststack = new StackPane();
	static Scene calendarview = new Scene(calendarstack, 130 * 7, 100 * 6 + 95);
	static Scene listview = new Scene(liststack, 700, 900);
	static Scene instructionScene = new Scene(instructionView, 900, 900);
	static boolean onListView = true;
	static boolean canCreate = true;
	static Label missing = new Label();
	static int scrollCount = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			//Sets up the panes for calendar, list, and instructions
			calendarstack.getChildren().add(cal);
			liststack.getChildren().add(list);
			calendarview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			listview.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			instructionScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			DisplayTask.newEvent = newEvent;
			DisplayTask.newAssignment = newAssignment;
			DisplayCalendar.root = cal;
			DisplayList.pane = list;

			//Runs the setup/display methods in the DisplayList, DisplayCalendar, DisplayTask, and Instructions classes
			DisplayTask.displayAssignment();
			DisplayTask.displayEvent();
			DisplayList.setup();
			DisplayCalendar.setup();
			Instructions.setup(instructionView);

			//Sets the main stage's scene to the list view and displays the stage
			primaryStage.setScene(listview);
			primaryStage.show();

			//Runs the scroll method in Main
			scroll();

			//The event handler for the buttin that switches from calendar to list
			//It switches the scene to listview and changes the position of the newEvent and new Assignment menus. It also updates some logic variables
			DisplayCalendar.toList.setOnMouseClicked(event -> {
				primaryStage.setScene(listview);
				newEvent.setTranslateX(0);
				newEvent.setTranslateY(0);
				newAssignment.setTranslateX(0);
				newAssignment.setTranslateY(0);
				onListView = true;
				DisplayList.displayTasks();
			});

			//Similar to the eventhandler above, except it switches from list to calendar. Updates done as required
			DisplayList.calendarView.setOnMouseClicked(event -> {
				primaryStage.setScene(calendarview);
				newEvent.setTranslateX(160);
				newEvent.setTranslateY(150);
				newAssignment.setTranslateX(160);
				newAssignment.setTranslateY(150);
				onListView = false;
				DisplayCalendar.displayTasks();
			});
			
			//Switches the scene from view to instructionScene
			DisplayList.toInstructions.setOnMouseClicked(event ->{
				primaryStage.setScene(instructionScene);
			});
			
			//Switches the scene from instructionScene to listview
			Instructions.toList.setOnMouseClicked(event -> {
				primaryStage.setScene(listview);
			});

			//calls all of the event handler methods
			newEventButtons();
			newAssignmentButtons();
			doneEventButton();
			doneAssignmnetButton();
			cancelButtons();

			StoreData.readFile();
			DisplayList.displayTasks();
			DisplayCalendar.displayTasks();



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void newEventButtons() {
		// Creates a new event
		//removes all of the text that is already filled in and  adds the new event pane 
		DisplayList.newEvent.setOnAction(e -> {
			
			if(newEvent.getChildren().contains(DisplayTask.deleteEvent)){
				newEvent.getChildren().remove(DisplayTask.deleteEvent);
			}
			DisplayTask.dateEvent.setValue(null);
			DisplayTask.newThing = true;
			if (!(liststack.getChildren().contains(DisplayTask.newEvent)
					&& calendarstack.getChildren().contains(DisplayTask.newEvent))) {
				liststack.getChildren().add(DisplayTask.newEvent);
			}
			list.getChildren().remove(DisplayList.newAssignment);
			list.getChildren().remove(DisplayList.newEvent);
		});
		// Does the same thing but for the calendar
		DisplayCalendar.newEvent.setOnMouseClicked(event -> {
			if(newEvent.getChildren().contains(DisplayTask.deleteEvent)){
				newEvent.getChildren().remove(DisplayTask.deleteEvent);
			}
			DisplayTask.newThing = true;
			DisplayCalendar.pday = -1;
			if (calendarstack.getChildren().contains(newAssignment) == false
					&& calendarstack.getChildren().contains(newEvent) == false) {
				DisplayTask.dateEvent.setValue(LocalDate.of(DisplayCalendar.selectedDate.year,
						DisplayCalendar.selectedDate.month, DisplayCalendar.selectedDate.day));
				calendarstack.getChildren().add(newEvent);
			}
			if (cal.getChildren().contains(DisplayCalendar.newEvent)) {
				cal.getChildren().removeAll(DisplayCalendar.newEvent, DisplayCalendar.newAssignment);
			}
		});
	}

	public static void newAssignmentButtons() {
		// New Assignment event handlers
		//removes all of the text that is already filled in and  adds the new event pane 
		DisplayCalendar.newAssignment.setOnMouseClicked(event -> {
			if(newAssignment.getChildren().contains(DisplayTask.deleteAssign)){
				newAssignment.getChildren().remove(DisplayTask.deleteAssign);
			}
			
			DisplayCalendar.pday = -1;
			DisplayTask.newThing = true;
			DisplayTask.highPriority = false;
			DisplayTask.regular.setSelected(true);
			if (calendarstack.getChildren().contains(newAssignment) == false
					&& calendarstack.getChildren().contains(newEvent) == false) {
				DisplayTask.dateAssignment.setValue(LocalDate.of(DisplayCalendar.selectedDate.year,
						DisplayCalendar.selectedDate.month, DisplayCalendar.selectedDate.day));
				calendarstack.getChildren().add(newAssignment);
			}

			if (cal.getChildren().contains(DisplayCalendar.newEvent)) {
				cal.getChildren().removeAll(DisplayCalendar.newEvent, DisplayCalendar.newAssignment);
			}
		});
		//same thing but for the calendar
		DisplayList.newAssignment.setOnAction(e -> {
			if(newAssignment.getChildren().contains(DisplayTask.deleteAssign)){
				newAssignment.getChildren().remove(DisplayTask.deleteAssign);
			}
			DisplayTask.newThing = true;
			DisplayTask.highPriority = false;
			DisplayTask.regular.setSelected(true);
			if (!(list.getChildren().contains(DisplayTask.newAssignment)
					&& calendarstack.getChildren().contains(DisplayTask.newAssignment))) {
				liststack.getChildren().add(DisplayTask.newAssignment);
			}
			list.getChildren().remove(DisplayList.newAssignment);
			list.getChildren().remove(DisplayList.newEvent);
		});
	}
	//for when you press the done button
	public static void doneEventButton() {
		// Done Event code
		DisplayTask.doneEvent.setOnMouseClicked(event -> {
			//checks if everything required is filled in and gives the error if not
			if (!DisplayTask.timeFilled() || DisplayTask.dateEvent.getValue() == null
					|| DisplayTask.eventname.getText().equals("")) {
				Label missing = new Label("Fields Required: Name, Date, Time");
				missing.setTextFill(Color.DARKRED);
				missing.setTranslateY(275);
				missing.setTranslateX(370);
				if(!DisplayTask.newEvent.getChildren().contains(missing)){
					DisplayTask.newEvent.getChildren().add(missing);
				}

				//creates the new task and adds it to the array list
			} else {
				if(!newEvent.getChildren().contains(DisplayTask.deleteEvent)){
					newEvent.getChildren().add(DisplayTask.deleteEvent);
				}
				int yr = DisplayTask.dateEvent.getValue().getYear();
				int mo = DisplayTask.dateEvent.getValue().getMonthValue();
				int day = DisplayTask.dateEvent.getValue().getDayOfMonth();
				int hr = Integer.valueOf(DisplayTask.hour.getText()) + DisplayTask.mornafternoon;
				int min = Integer.valueOf(DisplayTask.minute.getText());

				Task a = new Event(DisplayTask.eventname.getText(), DisplayTask.eventDescription.getText(),
						DisplayTask.location.getText(), yr, mo, day, hr, min);

				if (DisplayTask.newThing) {
					List.addTask(a);
				} else {
					List.updateTask(a);

					DisplayList.list.get(DisplayList.buttonIndex)
							.setText(List.list.get(DisplayList.buttonIndex).toString());
					DisplayCalendar.tasks.get(DisplayList.buttonIndex)
							.setText(List.list.get(DisplayList.buttonIndex).Format());
				}
				//updates the screen
				if (onListView) {
					liststack.getChildren().remove(newEvent);
					DisplayList.displayTasks();
				} else {

					calendarstack.getChildren().remove(newEvent);
					DisplayCalendar.displayTasks();

					calendarstack.getChildren().remove(newEvent);
					DisplayCalendar.displayTasks();

				}
				//resets all the fields and writes it to the file
				DisplayTask.eventname.clear();
				DisplayTask.eventDescription.clear();
				DisplayTask.location.clear();
				DisplayTask.dateEvent.setValue(null);
				DisplayTask.hour.clear();
				DisplayTask.minute.clear();
				StoreData.writeFile();
				DisplayTask.newEvent.getChildren().remove(missing);
			
			}

		});
	}

	public static void doneAssignmnetButton() {
		// Done assignment code
		DisplayTask.doneAssignment.setOnMouseClicked(event -> {
			// checks if required fields are filled in and if not it gives that error
			if (DisplayTask.dateAssignment.getValue() == null || DisplayTask.assignmentname.getText().equals("")) {
				missing.setText("Required Fields: Name and Date");
				missing.setTextFill(Color.DARKRED);
				missing.setTranslateY(275);
				missing.setTranslateX(370);
				

				if(!DisplayTask.newAssignment.getChildren().contains(missing)){
					DisplayTask.newAssignment.getChildren().add(missing);
				}

			}
			// creates the assignment
			else {
				if(!newAssignment.getChildren().contains(DisplayTask.deleteAssign)){
					newAssignment.getChildren().add(DisplayTask.deleteAssign);
				}
				int yr = DisplayTask.dateAssignment.getValue().getYear();
				int mo = DisplayTask.dateAssignment.getValue().getMonthValue();
				int day = DisplayTask.dateAssignment.getValue().getDayOfMonth();

				Task a = new Assignment(DisplayTask.assignmentname.getText(), DisplayTask.assignDescription.getText(),
						DisplayTask.subject.getText(), DisplayTask.highPriority, yr, mo, day);

				if (DisplayTask.newThing) {
					List.addTask(a);
				} else {

					List.updateTask(a);
					DisplayList.list.get(DisplayList.buttonIndex)
							.setText(List.list.get(DisplayList.buttonIndex).toString());
					DisplayCalendar.tasks.get(DisplayList.buttonIndex)
							.setText(List.list.get(DisplayList.buttonIndex).Format());
				}
				//updates the scenes
				if (onListView) {
					DisplayList.displayTasks();
					liststack.getChildren().remove(newAssignment);
				} else {
					DisplayCalendar.displayTasks();
					calendarstack.getChildren().remove(newAssignment);
			

				}
				//resets the text fields and writes to file
				DisplayTask.assignmentname.clear();
				DisplayTask.assignDescription.clear();
				DisplayTask.subject.clear();
				DisplayTask.dateAssignment.setValue(null);
				StoreData.writeFile();
				DisplayTask.newAssignment.getChildren().remove(missing);
			}

		});
	}
	//for when you press cancel
	public static void cancelButtons() {
		// cancel assignment
		DisplayTask.cancelAssignment.setOnMouseClicked(event -> {
			cancelAssignment();
		});

		// Cancel event
		DisplayTask.cancelEvent.setOnMouseClicked(event -> {
			cancelEvent();
		});
	}
	//code for cancel (also used when you press delete)
	public static void cancelEvent(){
		DisplayTask.newThing = false;
		//removes the pane
		if (liststack.getChildren().contains(DisplayTask.newEvent)) {
			liststack.getChildren().remove(newEvent);

		} else {

			calendarstack.getChildren().remove(newEvent);

		}
		//clears the textfields
		DisplayTask.eventname.clear();
		DisplayTask.eventDescription.clear();
		DisplayTask.location.clear();
		DisplayTask.dateEvent.setValue(null);
		DisplayTask.hour.clear();
		DisplayTask.minute.clear();
		DisplayTask.newEvent.getChildren().remove(missing);
		
	}
	//code for when you cancel an assignment
	public static void cancelAssignment(){
		DisplayTask.newThing = false;
		//removes the pane
		if (liststack.getChildren().contains(DisplayTask.newAssignment)) {

			liststack.getChildren().remove(newAssignment);

		} else {

			calendarstack.getChildren().remove(newAssignment);
		}
		//clears all of the text fields
		DisplayTask.assignmentname.clear();
		DisplayTask.assignDescription.clear();
		DisplayTask.subject.clear();
		DisplayTask.dateAssignment.setValue(null);
		DisplayTask.newAssignment.getChildren().remove(missing);
		
	}
	// code for scrolling
	public static void scroll() {
		
		listview.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			//if you press up
			case UP:
				//makes sure you don't go off screen then translates all of the tasks
				if (scrollCount > 0) {
					scrollCount--;
					for (int i = 0; i < DisplayList.list.size(); i++) {
						DisplayList.list.get(i).setTranslateY(DisplayList.list.get(i).getTranslateY() + 100);
					}
				}
				break;
				//if you press down
			case DOWN:
				//makes sure you don't go off screen then translates all of the tasks
				if (scrollCount < List.list.size()-7) {
					scrollCount++;
					for (int i = 0; i < DisplayList.list.size(); i++) {
						DisplayList.list.get(i).setTranslateY(DisplayList.list.get(i).getTranslateY() - 100);
					}
				}
				break;

			}
		});
	}
}
