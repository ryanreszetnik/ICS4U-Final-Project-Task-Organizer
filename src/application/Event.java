package application;

public class Event extends Task{
	private String location;

	public Event(String name, String desc, String loc, int year, int month, int day, int hour, int minute){
		this.name = name;
		description=desc;
		location = loc;
		date = new Date(year, month, day, hour, minute);
	}
	public void display() {
			
	}
	
}
