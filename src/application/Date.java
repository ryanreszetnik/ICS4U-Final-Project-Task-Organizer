package application;

import java.time.LocalDate;

public class Date {
	//variables for what is stored
	public int year;
	public int month;
	public int day;
	public int hour = -1;
	public int minute = -1;
	//constructor that defaults to the current time/date
	public Date() {
		LocalDate localDate = LocalDate.now();
		year = localDate.getYear();
		month = localDate.getMonthValue();
		day = localDate.getDayOfMonth();
	}
	//constructor that just sets the date
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	//constructor that sets date and time
	public Date(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;

	}
	//format all of the variables for storing to the text file
	public String toFile(){
		String output = "";
		output+=year + "\n";
		output+=month + "\n";
		output+=day + "\n";
		output+=hour + "\n";
		output+=minute + "\n";
		
		return output;
	}
	
	//updates the date
	public void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	//updates the time
	public void setTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	//updates teh date and time together
	public void setDT(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}
	
	//changes the month to the next one
	public void nextMonth() {
		month++;
		if (month > 12) {
			month -= 12;
			year++;
		}

	}
	//changes the month to the previous one
	public void prevMonth() {
		month--;
		if (month <= 0) {
			month += 12;
			year--;
		}

	}

	//checks if a day is the current day on your computer
	public static boolean isToday(int day, int month, int year) {
		LocalDate localDate = LocalDate.now();
		if (year == localDate.getYear() && month == localDate.getMonthValue() && day == localDate.getDayOfMonth()) {
			return true;
		}
		return false;

	}
	//formats time into a string
	public String getTime() {
		String time = "";
		String morning = " am";
		String min = ""+minute;
		int hr = hour;
		if (hr >= 0 && minute >= 0) {
			if (hr >= 12) {
				morning = " pm";
				if (hr > 12) {
					hr -= 12;
				}
			}
			if (hr == 0) {
				hr = 12;
			}
			if(minute == 0){
				min+= "0";
			}
			time = " " +hr + ":" + min + morning;
		}
		return time;
	}
	//returns the hour in 12 hr time
	public int getHour(){
		if(hour > 12){
			return hour-12;
		}
		return hour;
		
	}
	//checks if it is the morning or afternoon
	public boolean isMorining(){
		if(hour >=12){
			return false;
		}
		return true;
	}
	

	public String getDate() {
		return day + "/" + month + "/" + year;
	}

	
}
