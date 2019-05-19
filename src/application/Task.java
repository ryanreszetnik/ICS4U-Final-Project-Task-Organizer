package application;

public abstract class Task {
	public String name;
	public String description;
	public Date date;
	boolean hasTime = false;
	
	public void setTime(int hour, int min) {
		date.setTime(hour, min);
		hasTime = true;
	}
	public String Format(){
		return name + date.getTime();		
	}
	
	public abstract boolean isAssignment();
	
	public void setDescription(String description){
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void changeDate(int year, int month, int day){
		date.setDate(year, month, day);
	}
	
	public abstract String toString();
	
	
	
}
