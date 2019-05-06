package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class displayCalendar {
	public static Button days[];
	public static Label MonthTitle;
	public static Label YearTitle;
	public static int day1;
	public static int monthsize;
	public static Button next;
	public static Button prev;


	public static Date currDate = new Date();

	public static void setup(Pane root) {
		
		root.setBackground(new Background(new BackgroundFill(Color.web("#777777"), CornerRadii.EMPTY, Insets.EMPTY)));
		days = new Button[42];
		double buttonsizex = 130;
		double buttonsizey = 100;
		for (int i = 0; i < 42; i++) {
			days[i] = new Button();
			days[i].setPrefSize(buttonsizex, buttonsizey);
			days[i].setTranslateX(i % 7 * buttonsizex);
			days[i].setTranslateY(i / 7 * buttonsizey + 95);
			days[i].setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
			root.getChildren().add(days[i]);
		}
		
		next = new Button();
		prev = new Button();
		next.setText(">");
		next.setPrefWidth(26);
		prev.setText("<");
		prev.setPrefWidth(26);
		next.setTranslateX(buttonsizex*7*2/3-13);
		next.setTranslateY(20);
		prev.setTranslateX(buttonsizex*7/3-13);
		prev.setTranslateY(20);
		next.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		prev.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		
		
		
		

		Label[] dayTitles = new Label[7];
		for (int i = 0; i < 7; i++) {
			dayTitles[i] = new Label();
			dayTitles[i].setPrefWidth(buttonsizex);
			dayTitles[i].setTranslateX(buttonsizex * i);
			dayTitles[i].setTranslateY(70);
			dayTitles[i].setText(Calendar.dayName(i));
			dayTitles[i].setAlignment(Pos.CENTER);
			Font fo = new Font(Math.min(20, buttonsizex/7));
			dayTitles[i].setFont(fo);
			root.getChildren().add(dayTitles[i]);
		}

		MonthTitle = new Label();
		MonthTitle.setText(Calendar.monthName(currDate.month));
		Font fon = new Font(Math.min(50, buttonsizex/2.6));
		MonthTitle.setFont(fon);
		MonthTitle.setPrefWidth(buttonsizex * 7);
		MonthTitle.setAlignment(Pos.CENTER);
		
		YearTitle = new Label();
		YearTitle.setFont(fon);
		YearTitle.setText(""+currDate.year);
		
		
		
		
		root.getChildren().addAll(MonthTitle, YearTitle);
		root.getChildren().addAll(next,prev);
		
		setupDays(currDate.month, currDate.year);
		buttonControl();
	}

	public static void setupDays(int month, int year) {
		int count = 1;
		resetButtons();
		day1 = Calendar.weekDay(1, month, year);
		monthsize = Calendar.monthSize(month, year);
		MonthTitle.setText(Calendar.monthName(month));
		YearTitle.setText(""+currDate.year);
		for (int i = day1; count <= monthsize; i++) {
			days[i].setText(count+"");
			days[i].setAlignment(Pos.TOP_LEFT);
			if(!today(count,month,year)){
			days[i].setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;-fx-text-fill: #ffffff;");
			}else{
				days[i].setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #a0a0a0;-fx-text-fill: #ffffff;");
			}
			count++;
		}
	}

	public static void resetButtons() {
		for (int i = 0; i < 42; i++) {
			days[i].setText("");
			days[i].setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #424242;");
		}
	}
	public static boolean today(int day, int month, int year){
		return currDate.isToday(day, month, year);
	}
	public static void buttonControl(){
		
		prev.setOnMouseClicked(event -> {
			currDate.prevMonth();
			setupDays(currDate.month, currDate.year);
		});
		next.setOnMouseClicked(event -> {
			currDate.nextMonth();
			setupDays(currDate.month, currDate.year);
			
		});
		for (int i = 1; i < 42; i++) {
			int count = i;
			//System.out.println(count);
			days[i].setOnMouseClicked(event -> {
				if(count >= day1 && count < day1+monthsize){
					System.out.println(Calendar.monthName(currDate.month)+" "+(count-day1+1)+" "+currDate.year);
				
				}
			});
		}
	}
}
