package application;

public class Assignment extends Task{
	private boolean priority;
	private String subject;

	
	
	public Assignment() {
		
	}
	
	public Assignment(String name, String description, String subject, boolean priority) {
		this.name = name;
		this.description = description;
		this.subject = subject;
		this.priority = priority;
	}
	
	public void setSubject(String Subject) {
		this.subject = subject;
	}
	
	public void setPriority(boolean priority){
		this.priority = priority;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
}
