package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DisplayCalendar {
	//all of the declarations
	public static Button days[];
	public static Label MonthTitle;
	public static Label YearTitle;
	public static int day1;
	public static int monthsize;
	public static Button next;
	public static Button prev;

	public static Button toList;

	public static ArrayList<Button> list = new ArrayList<>();
	
	public static Pane root;
	public static Button newEvent;
	public static Button newAssignment;
	public static final double buttonsizex = 130;
	public static final double buttonsizey = 100;
	public static int pday = -1;
	public static ArrayList<Button> tasks = new ArrayList<>();
	public static ArrayList<Label> moreTasks = new ArrayList<>();
	static int[][] hasTask = new int[7][6];

	public static Date currDate = new Date();
	public static Date selectedDate = new Date();


	// what runs at the start of the program
	public static void setup() {
		//sets background color
		root.setBackground(new Background(new BackgroundFill(Color.web("#777777"), CornerRadii.EMPTY, Insets.EMPTY)));

		days = new Button[42];
		//loops through all of the buttons and formats them
		
		for (int i = 0; i < 42; i++) {

			days[i] = new Button();
			days[i].setPrefSize(buttonsizex, buttonsizey);
			days[i].setTranslateX(i % 7 * buttonsizex);
			days[i].setTranslateY(i / 7 * buttonsizey + 95);
			days[i].setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
			root.getChildren().add(days[i]);
		}
		//formats all of the buttons (translates, sizes, text, color etc)
		newEvent = new Button("New Event");
		newAssignment = new Button("New Assignment");

		newEvent.setPrefSize(buttonsizex, 25);
		newAssignment.setPrefSize(buttonsizex, 25);
		newEvent.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color:#D2D2D2  ;");
		newAssignment.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color:#D2D2D2  ;");

		next = new Button();
		prev = new Button();
		next.setText(">");
		next.setPrefWidth(26);
		prev.setText("<");
		prev.setPrefWidth(26);
		next.setTranslateX(buttonsizex * 7 * 2 / 3 - 13);
		next.setTranslateY(20);
		prev.setTranslateX(buttonsizex * 7 / 3 - 13);
		prev.setTranslateY(20);
		next.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		prev.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");

		toList = new Button("To List");
		Font listFont = new Font(20);
		toList.setFont(listFont);
		toList.setTranslateX(800);
		toList.setTranslateY(10);
		toList.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		// adds in the titles Monday, Tuesday etc above the calendar
		Label[] dayTitles = new Label[7];
		for (int i = 0; i < 7; i++) {
			dayTitles[i] = new Label();
			dayTitles[i].setPrefWidth(buttonsizex);
			dayTitles[i].setTranslateX(buttonsizex * i);
			dayTitles[i].setTranslateY(70);
			dayTitles[i].setText(Calendar.dayName(i));
			dayTitles[i].setAlignment(Pos.CENTER);
			Font fo = new Font(Math.min(20, buttonsizex / 7));
			dayTitles[i].setFont(fo);
			root.getChildren().add(dayTitles[i]);
		}
		// month and year label formatting
		MonthTitle = new Label();
		MonthTitle.setText(Calendar.monthName(currDate.month));
		Font fon = new Font(Math.min(50, buttonsizex / 2.6));
		MonthTitle.setFont(fon);
		MonthTitle.setPrefWidth(buttonsizex * 7);
		MonthTitle.setAlignment(Pos.CENTER);

		YearTitle = new Label();
		YearTitle.setFont(fon);
		YearTitle.setText("" + currDate.year);
		//adds the buttons to the pane
		root.getChildren().addAll(MonthTitle, YearTitle);
		root.getChildren().addAll(next, prev, toList);
		// sets the calendar to the current month
		setupDays(currDate.month, currDate.year);
		//where the event handlers are 
		buttonControl();
	}
// sets calendar to the current month code
	public static void setupDays(int month, int year) {
		int count = 1;
		// sets every button to the style and text of the days not in the month
		resetButtons();
		//gets the first day of the month, and size and updates the titles' texts
		day1 = Calendar.weekDay(1, month, year);
		monthsize = Calendar.monthSize(month, year);
		MonthTitle.setText(Calendar.monthName(month));
		YearTitle.setText("" + currDate.year);
		// loops though all of the days within the month, sets the day# and the style
		for (int i = day1; count <= monthsize; i++) {
			days[i].setText(count + "");
			days[i].setAlignment(Pos.TOP_LEFT);
			if (!today(count, month, year)) {
				days[i].setStyle(
						"-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;-fx-text-fill: #ffffff;");
			// if the day is the current day on your computer, it makes it a different color
			} else {
				days[i].setStyle(
						"-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #a0a0a0;-fx-text-fill: #ffffff;");
			}
			count++;
		}
	}

	
	public static int dayPos(String day) {
		for (int i = 0; i < 42; i++) {
			if (days[i].getText().equals(day)) {
				return i;
			}
		}
		return -1;
	}
	
	// sets every button to the style and text of the days not in the month
	public static void resetButtons() {
		for (int i = 0; i < 42; i++) {
			days[i].setText("");
			days[i].setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #424242;");
		}
	}
	
	//checks if a day is the current day
	public static boolean today(int day, int month, int year) {
		return currDate.isToday(day, month, year);
	}

	//for the button event handlers
	public static void buttonControl() {

		//for changing the month to the previous month
		prev.setOnMouseClicked(event -> {
			currDate.prevMonth();
			//removes the drop down
			if (root.getChildren().contains(newEvent)) {
				root.getChildren().removeAll(newEvent, newAssignment);
			}
			pday = -1;
			//re-makes the calendar
			setupDays(currDate.month, currDate.year);
			displayTasks();
		});
		//for changing the month to the next month
		next.setOnMouseClicked(event -> {
			currDate.nextMonth();
			//removes the drop down
			if (root.getChildren().contains(newEvent)) {
				root.getChildren().removeAll(newEvent, newAssignment);
			}
			pday = -1;
			//re-makes the calendar
			setupDays(currDate.month, currDate.year);
			displayTasks();

		});
		// event handlers for when you press on the calendar
		for (int i = 1; i < 42; i++) {
			int count = i;

			days[i].setOnMouseClicked(event -> {
				//only for the days of the current month
				if (count >= day1 && count < day1 + monthsize) {
					selectedDate.day = count - day1 + 1;
					selectedDate.month = currDate.month;
					selectedDate.year = currDate.year;
					// Adds the dropdown
					newEvent.setTranslateX(days[count].getTranslateX());
					newAssignment.setTranslateX(days[count].getTranslateX());
					newEvent.setTranslateY(days[count].getTranslateY() + buttonsizey);
					newAssignment.setTranslateY(days[count].getTranslateY() + buttonsizey + 25);
					if (root.getChildren().contains(newEvent) == false) {
						root.getChildren().addAll(newEvent, newAssignment);
					}
					// if you click on the same day twice, it removes the dropdown
					if (pday == count) {
						root.getChildren().removeAll(newEvent, newAssignment);
						pday = -1;
					} else {
						pday = count;
					}

				}
			});

		}

	}

	// all the code for the task buttons
	public static void displayTasks() {
		//resets the value of hasTask counter array
		for (int i = 0; i < 7; i++) {
			for (int p = 0; p < 6; p++) {
				hasTask[i][p] = 0;
			}
		}
		//loops though all of the tasks
		for (int i = 0; i < tasks.size(); i++) {
			//removes all of them from the screen
			if (root.getChildren().contains(tasks.get(i))) {
				root.getChildren().remove(tasks.get(i));
			}
			//for the priority border being red or not
			if( List.list.get(i).isAssignment() && ((Assignment)List.list.get(i)).getPriority()){				
				tasks.get(i).setStyle("-fx-border-color: #990000; -fx-border-width: 5px; -fx-background-color: #5e5e5e;-fx-text-fill: #ffffff;");
			}
			else {
				tasks.get(i).setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;-fx-text-fill: #ffffff;");
			}

			//if it should be on screen it adds it and moves it and formats it 
			if (List.list.get(i).date.month == currDate.month && List.list.get(i).date.year == currDate.year) {
				String day = "" + List.list.get(i).date.day;
			
				tasks.get(i).setPrefSize(buttonsizex, buttonsizey / 5);
			
				tasks.get(i).setTranslateX(dayPos(day) % 7 * buttonsizex);
				tasks.get(i).setTranslateY(
						dayPos(day) / 7 * buttonsizey + 115 + hasTask[dayPos(day) % 7][dayPos(day) / 7] * 25);
				hasTask[dayPos(day) % 7][dayPos(day) / 7]++;
				if(hasTask[dayPos(day) % 7][dayPos(day) / 7] <= 2){
					root.getChildren().add(tasks.get(i));
				}
			}

		}
		//resets the moreTasks array list for the eg. +2 tasks that shows up
		for(int i= 0; i < moreTasks.size(); i++){
			root.getChildren().remove(moreTasks.get(i));
		}
		moreTasks.clear();
		//loops through and if there is more than 2 on a day, adds a label to that day with the amount
		for(int i = 0; i < 7; i++){
			for(int p = 0; p< 6; p++){
				if(hasTask[i][p] > 2){
					Label l = new Label("+" + (hasTask[i][p]-2) + " more");
					l.setTranslateX(i*buttonsizex+40);
					l.setTranslateY(p*buttonsizey+170);
					moreTasks.add(l);
					root.getChildren().add(l);
				}
			}
		}
		//for the event handlers for all of the tasks
		for (int i = 0; i < tasks.size(); i++) {
			//sets all of the required information for assignments
			if (List.list.get(i).isAssignment()) {
				
				String name = List.list.get(i).name;
				String description = List.list.get(i).description;
				String subject = ((Assignment) (List.list.get(i))).getSubject();
				Date date = List.list.get(i).date;
				int year = date.year;
				int month = date.month;
				int day = date.day;
				boolean highPri = ((Assignment) List.list.get(i)).getPriority();
				int count = i;
				//when you press the button, it updates it all
				tasks.get(i).setOnAction(e -> {
					if(!DisplayTask.newAssignment.getChildren().contains(DisplayTask.deleteAssign)){
						DisplayTask.newAssignment.getChildren().add(DisplayTask.deleteAssign);
					}
					DisplayList.buttonIndex = count;
					DisplayTask.high.setSelected(highPri);
					DisplayTask.regular.setSelected(!highPri);
					
					DisplayTask.newThing = false;
					Main.calendarstack.getChildren().add(DisplayTask.newAssignment);
					DisplayTask.assignmentname.setText(name);
					DisplayTask.assignDescription.setText(description);
					DisplayTask.subject.setText(subject);
					DisplayTask.dateAssignment.setValue(LocalDate.of(year, month, day));
				});
			}
			//same for event where it sets the variables
			else {
				int count = i;
				String name = List.list.get(i).name;
				String description = List.list.get(i).description;
				String location = ((Event) (List.list.get(i))).getLocation();
				Date date = List.list.get(i).date;
				int year = date.year;
				int month = date.month;
				int day = date.day;
			
				int hour = date.getHour();
				int min = date.minute;
				boolean morn = date.isMorining();
				//event handler for when you press it to update everything
				tasks.get(i).setOnAction(e -> {
					if(!DisplayTask.newEvent.getChildren().contains(DisplayTask.deleteEvent)){
						DisplayTask.newEvent.getChildren().add(DisplayTask.deleteEvent);
					}
					DisplayTask.newThing = false;
					DisplayList.buttonIndex = count;
					Main.calendarstack.getChildren().add(DisplayTask.newEvent);
					DisplayTask.eventname.setText(name);
					DisplayTask.eventDescription.setText(description);
					DisplayTask.location.setText(location);
					DisplayTask.dateEvent.setValue(LocalDate.of(year, month, day));

					DisplayTask.minute.setText(min + "");
					if (morn) {
						DisplayTask.am.setSelected(true);
						DisplayTask.pm.setSelected(false);
						DisplayTask.hour.setText(hour + "");
					} else {
						DisplayTask.am.setSelected(false);
						DisplayTask.pm.setSelected(true);
						DisplayTask.hour.setText(hour + "");

					}
				});

			}
		}
		
	}
	// adds a button to the array list
	public static void addButton(Button a) {
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		tasks.add(a);
	}
	// adds a task to the array list and then calls to make a button from it
	public static void addTask(Task t) {
		Button newTask1 = new Button(t.Format());
		addButton(newTask1);
	}
	
}
