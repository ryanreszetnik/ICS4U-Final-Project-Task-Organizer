package application;

public class Assignment extends Task{
	private boolean priority;
	private String subject;
	
	public Assignment(String name, String description, String subject, boolean priority, int year, int month, int day) {
		this.name = name;
		this.description = description;
		this.subject = subject;
		this.priority = priority;
		this.date = new Date(year, month, day);
	}
	
	public void setSubject(String subject) {// sets the subject
		this.subject = subject;
	}
	public String getSubject() {// gets the subject
		return subject;
	}
	
	public void setPriority(boolean priority){// sets the priority
		this.priority = priority;
	}
	public boolean getPriority() {// gets the priority
		return priority;
	}
	

	@Override
	public boolean isAssignment() {// always returns true since it is an assignmnet
		return true;
	}
	public String toString(){
		return "Name: "+name +"   Date: "+date.getDate();
	}
}
