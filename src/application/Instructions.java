package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Instructions {
	//Initializations
	static Button toList = new Button("Switch to List");
	static Rectangle top = new Rectangle(900, 60);
	static Label instructionsText = new Label();
	
	//This setup method takes a pane as a parameter and adds the nodes to it. 
	public static void setup(Pane pane) {
		//Creates the top rectangle and sets the position and style of the pane and toList button
		top.setFill(Color.rgb(96, 96, 96));
		pane.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #999999;");
		toList.setPrefHeight(60);
		toList.setTranslateX(400);
		toList.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		
		//Sets the label with the instructions text
		instructionsText.setText("Creating Tasks"+"\n"+" - Enter the task name, description, date for the tasks" + "\n"+
				" - For an event, you can add location and time and for assignment, you can add priority" + "\n"+
				" - Enter in the time in 12-hour format and use the am/pm buttons" + "\n"+
				" - The required fields are name, date and if it is an event then time as well" + "\n"+
				" - Make sure everything is in the correct format or else it won't allow you to create it" + "\n"+
				" - Click on the button beside the textbox for date to open a small calendar to choose the date"+ "\n" + 
				" -Once you press done, the task is added to both the list and the calendar"+ "\n" + 
				" -Should you press cancel, the fields you entered will be discarded"+ "\n" + 
				" -You can also remove a task using the delete button found in the same menu"
				+ "\n(only shows up if you are editing not creating a task)" + "\n\n" + 
				"Calendar"  + "\n" + 
				" - Click on any day in the month showing to show a dropdown where you can add either an event or \n   assignment"  + "\n"+
				" - Click on a task to bring up more details or to edit/delete it"  + "\n"+
				" - Use the arrow buttons to go forward/back months"  + "\n"+
				" - Use the To List button to return to the List View"  + "\n\n"+

				"List" + "\n" +
				" -Click on New Task to bring down a dropdown where you can create events or assignments\n"+
				" -Click on a task to bring up more details or to edit/delete it\n"+
				" -If you have more tasks that can fit on the screen, use the up and down arrow keys to \n   scroll through them\n"+
				" -Click on instructions button to get here and 'To List' to get back to the list");
		
		//Sets the position and font size of the instructions text
		instructionsText.setTranslateY(65);
		Font fo = new Font(18);
		instructionsText.setFont(fo);
		
		//Adds all the nodes
		pane.getChildren().addAll(top,toList,instructionsText);
	}
	


}
