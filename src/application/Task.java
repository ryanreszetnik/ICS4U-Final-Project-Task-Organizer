package application;

public abstract class Task {
	public String name;
	public String description;
	public Date date;
	
	public abstract void display();
	
	public void setDescription(String desc){
		description = desc;
	}
	public void changeDate(int year, int month, int day){
		date.setDate(year, month, day);
	}
	
}
