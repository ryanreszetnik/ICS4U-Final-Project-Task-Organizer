package application;

public abstract class Task {
	public String name;
	public String description;
	public Date date;
	boolean hasTime = false;
	
	public abstract void display();
	public void setTime(int hour, int min) {
		date.setTime(hour, min);
		hasTime = true;

	}
	public String Format(){
		return name + date.getTime();		
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void changeDate(int year, int month, int day){
		date.setDate(year, month, day);
	}
	public String toString(){
		return "Name: "+name +"   Date: "+date.toString();
	}
	
	
	
}
