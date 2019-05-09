package application;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class DisplayList {
	public ArrayList<Button> list = new ArrayList<>();
	
	public void addButton(String name, int hour, int minute) {
		Button a = new Button(name + "\n" + hour + ":" + minute);
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		list.add(a);
		
	}
	
}
