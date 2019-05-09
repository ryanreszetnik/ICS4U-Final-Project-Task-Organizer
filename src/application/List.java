package application; 
import java.util.ArrayList;



public class List {
	public static ArrayList<Task> list = new ArrayList<>();

	public static void addEvent(Task a) {
		list.add(a);
	}
	
	public static void addAssignment(Task a) {
		list.add(a);
	}


}
