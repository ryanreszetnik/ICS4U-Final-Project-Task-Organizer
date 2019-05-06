package application;

import java.time.LocalDate;

public class Date {
	public static int year;
	public static int month;
	public static int day;
	public static int hour;
	public static int minute;

	public Date() {
		LocalDate localDate = LocalDate.now();
		year = localDate.getYear();
		month = localDate.getMonthValue();
		day = localDate.getDayOfMonth();
	}
	public Date(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public Date(int year, int month, int day, int hour, int minute){
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		
	}

	public static void nextMonth() {
		month++;
		if(month > 12){
			month-=12;
			year++;
		}
		
	}

	public static void prevMonth() {
		month--;
		if(month <= 0){
			month+=12;
			year--;
		}
	
	}
	public static boolean isToday(int day, int month, int year){
		LocalDate localDate = LocalDate.now();
		if(year == localDate.getYear()&& month == localDate.getMonthValue() && day == localDate.getDayOfMonth()){
			return true;
		}
		return false;
		
		
	}
}
