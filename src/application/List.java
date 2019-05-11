package application; 

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.control.Button;



public class List {
	public static ArrayList<Task> list = new ArrayList<>();

	public static void addEvent(Task a) {
		list.add(a);
	}
	
	public static void addAssignment(Task a) {
		list.add(a);
		DisplayList.addTask(a);
		displayCalendar.addTask(a);
	}


}
