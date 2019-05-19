package application;

public class Assignment extends Task{
	private boolean priority;
	private String subject;
	
	//Non-default constructor
	public Assignment(String name, String description, String subject, boolean priority, int year, int month, int day) {
		this.name = name;
		this.description = description;
		this.subject = subject;
		this.priority = priority;
		this.date = new Date(year, month, day);
	}
	
	//Mutator method for the subject
	public void setSubject(String subject) {
		this.subject = subject;
	}
	//Accessor method for the subject
	public String getSubject() {
		return subject;
	}
	
	//Mutator method for the priority
	public void setPriority(boolean priority){
		this.priority = priority;
	}
	
	//Accessor method for the priority
	public boolean getPriority() {
		return priority;
	}
	
	//Used to determine whether the object is of type Assignment or Event
	public boolean isAssignment() {
		return true;
	}
	
	//The formatting for quick view used when displaying the tasks in the list
	public String toString(){
		return "Name: "+name +"   Date: "+date.getDate();
	}
}
