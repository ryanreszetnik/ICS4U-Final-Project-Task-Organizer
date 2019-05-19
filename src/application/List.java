package application; 

import java.util.ArrayList;



public class List {
	public static ArrayList<Task> list = new ArrayList<>();

	public static void addTask(Task a) {
		list.add(a);
		DisplayList.addTask(a);
		DisplayCalendar.addTask(a);
	}
	
	
	public static void updateTask(Task a) {
		if(DisplayList.buttonIndex < list.size()-1) {
			list.remove(DisplayList.buttonIndex);
			list.add(DisplayList.buttonIndex, a);
		}
		
		else {
			list.remove(DisplayList.buttonIndex);
			list.add(a);
		}
	}

}
