package application;

import java.util.ArrayList;

public class List {
	// The arraylist to store all of the tasks
	public static ArrayList<Task> list = new ArrayList<>();

	/*
	  Adds an event to the arraylist based on the parameter that was entered. This
	  also calls methods that add buttons to the arraylists in DisplayList and DisplayCalendar
	 */
	public static void addTask(Task a) {
		list.add(a);
		DisplayList.addTask(a);
		DisplayCalendar.addTask(a);
	}

	//Removes an existing event and adds a new one at the same index with the updated information
	//The if statement checks whether or not it at the last index, because the logic would slightly different
	public static void updateTask(Task a) {
		if (DisplayList.buttonIndex < list.size() - 1) {
			list.remove(DisplayList.buttonIndex);
			list.add(DisplayList.buttonIndex, a);
		}

		else {
			list.remove(DisplayList.buttonIndex);
			list.add(a);
		}
	}

}
