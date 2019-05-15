package application; 

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.control.Button;



public class List {
	public static ArrayList<Task> list = new ArrayList<>();

	public static void addEvent(Task a) {
		list.add(a);
		a.isEvent = true;
		DisplayList.addTask(a);
		displayCalendar.addTask(a);
	}
	
	public static void addAssignment(Task a) {
		list.add(a);
		a.isEvent = false;
		DisplayList.addTask(a);
		displayCalendar.addTask(a);
	}
	
	public static void updateTask(Task a) {
		list.remove(DisplayList.buttonIndex);
		list.set(DisplayList.buttonIndex, a);
	}
	



}
