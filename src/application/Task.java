package application;

public abstract class Task {
	public String name;
	public String description;
	public Date date;
	boolean hasTime = false;
	
	//Mutator method for the time 
	public void setTime(int hour, int min) {
		date.setTime(hour, min);
		hasTime = true;
	}
	
	//formats the name and date into a string
	public String Format(){
		return name + date.getTime();		
	}
	//abstract method for if it is an event or assignment
	public abstract boolean isAssignment();
	
	//changes the description
	public void setDescription(String description){
		this.description = description;
	}
	
	//sets the name
	public void setName(String name) {
		this.name = name;
	}
	//changes the date
	public void changeDate(int year, int month, int day){
		date.setDate(year, month, day);
	}
	//for formatting the text on the calendar
	public abstract String toString();
	
	
	
}
