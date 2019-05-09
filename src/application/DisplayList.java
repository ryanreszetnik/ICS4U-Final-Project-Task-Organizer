package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DisplayList {
	public static ArrayList<Button> list = new ArrayList<>();
	static Button addTask;

	public static void addButton() {
		Button a = new Button();
		a.setStyle("-fx-border-color: #aaaaaa; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");
		list.add(a);

		a.setTranslateY(50 + (list.size() * 100));
	}

	public static void setup(Pane pane) {
		addTask = new Button();
		Font fo = new Font(25);
		addTask.setText("+");
		addTask.setFont(fo);
		addTask.setPrefSize(60, 60);
		addTask.setStyle("-fx-border-color: #303030; -fx-border-width: 1px; -fx-background-color: #5e5e5e;");

		Rectangle top = new Rectangle(700, 60);
		top.setFill(Color.rgb(96, 96, 96));
		pane.getChildren().addAll(top, addTask);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFont(fo);
			list.get(i).setPrefSize(650, 100);
			list.get(i).setTranslateY(60 + (100 * i));
			pane.getChildren().add(list.get(i));
		}

	}

	public static void buttonControl() {

		addTask.setOnMouseClicked(event -> {
			addButton();
		});

	}

}
