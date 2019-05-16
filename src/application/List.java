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
	
	public static void updateEvent(Task a) {
		a.isEvent = true;
		if(DisplayList.buttonIndex < list.size()-1) {
			list.remove(DisplayList.buttonIndex);
			list.add(DisplayList.buttonIndex, a);
		}
		
		else {
			list.remove(DisplayList.buttonIndex);
			list.add(a);
		}
	}
	
	public static void updateAssignment(Task a) {
		if(DisplayList.buttonIndex < list.size()-1) {
			a.isEvent = false;
			list.remove(DisplayList.buttonIndex);
			list.add(DisplayList.buttonIndex, a);
		}
		
		else {
			list.remove(DisplayList.buttonIndex);
			list.add(a);
		}
	}
	



}
