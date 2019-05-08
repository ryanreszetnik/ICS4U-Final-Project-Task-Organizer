package application;

import java.util.ArrayList;

public class Calendar {

	public ArrayList<Task> tasks = new ArrayList<>();
	
	public static int weekDay(int day, int month, int year){
		int century = year/100;
		int yr = year%100;
		if(month < 3){//january and feburary treated as previous year for formula
			month += 12;//january would be month 11 and feb month 12
			yr--;//makes it previous year
		}
		month-=2;//every month shifted back 2 months so march is considered month 1
		int wd = (int) ((day+Math.floor(2.6*month-0.2)-2*century+yr+Math.floor(yr/4)+Math.floor(century/4))%7);
		if(wd < 0){
			wd+=7;
		}
		return wd;//returns 0-6 for Sunday - Saturday
	}
	public static String dayName(int day, int month, int year){
		int wd = weekDay(day, month, year);
		return dayName(wd);
	}
	public static String dayName(int weekDay){
		String name = "";
		switch(weekDay){
		case 0:
			name = "Sunday";
			break;
		case 1:
			name = "Monday";
			break;
		case 2:
			name = "Tuesday";
			break;
		case 3:
			name = "Wednesday";
			break;
		case 4:
			name = "Thursday";
			break;
		case 5:
			name = "Friday";
			break;
		case 6:
			name = "Saturday";
			break;
		}
		return name;
	}
	public static int monthSize(int month, int year){
		int size = 0;
		if(month > 7){
			month--;
		}
		if(month%2==0){
			size = 30;
		}else{
			size = 31;
		}
		
		if(month == 2){//feburary
			size = 28;
			if(year%4==0 && year%100 != 0 || year% 400 ==0){// if it is a leap year
				size = 29;
			}
		}
		return size;
	}
	public static String monthName(int month){
		String name = "";
		switch(month){
		case 1:
			name = "January";
			break;
		case 2:
			name = "Feburary";
			break;
		case 3:
			name = "March";
			break;
		case 4:
			name = "April";
			break;
		case 5:
			name = "May";
			break;
		case 6:
			name = "June";
			break;
		case 7:
			name = "July";
			break;
		case 8:
			name = "August";
			break;
		case 9:
			name = "September";
			break;
		case 10:
			name = "October";
			break;
		case 11:
			name = "November";
			break;
		case 12:
			name = "December";
			break;
		}
		return name;
	}
	
}
