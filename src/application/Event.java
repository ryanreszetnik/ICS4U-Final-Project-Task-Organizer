package application;

public class Event extends Task{
	private String location;
	
	//Default constructor that instantiates the date
	public Event() {
		date = new Date();
		}

	//Non-default constructor to initialize instance variables as well as the date
	public Event(String name, String desc, String loc, int year, int month, int day, int hour, int minute){
		this.name = name;
		description=desc;
		location = loc;
		date = new Date(year, month, day, hour, minute);
	}

	//Mutator method for the location
	public void setLocation(String location) {
		this.location = location;
	}
	
	//Accessor method for the location
	public String getLocation(){
		return location;
	}
	
	//Used to determine whether the task is an assignment or an event
	public boolean isAssignment() {
		return false;
	}
	
	////The formatting for quick view used when displaying the tasks in the list
	public String toString(){
		return "Name: "+name +"   Date: "+date.getDate()+ " @ " + date.getTime();
	}
}
