package application;

public abstract class Task {
	public static String name;
	public static String description;
	public Date date;
	
	public abstract void display();
	
	public void setDescription(String desc){
		description = desc;
	}
	public void changeDate(int year, int month, int day){
		date.setDate(year, month, day);
	}
	
}
