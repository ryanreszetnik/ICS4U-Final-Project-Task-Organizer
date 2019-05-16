package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.layout.Pane;

public class StoreData {

	public static void readFile() {

		BufferedReader br = null;

		// defines the buffered reader
		String scores = "";
		// string for all of the scores
		// scoresList.clear();

		File f = new File("Tasks");
		try {
			if (f.exists() == false) {// if the file doesn't exist
				PrintWriter writer = new PrintWriter("Tasks");// makes
																// a
																// new
																// file
				writer.close();// closes the file
			}
			br = new BufferedReader(new FileReader("Tasks"));
			// makes a buffered reader for that file
			String CurrentLine;
			// for storing what is on each line
			String name = "";
			String desc = "";
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			String locationOrSubject = "";
			boolean priority = false;
			int counter = 0;
			int discLength = 0;
			int discCounter = 0;

			while ((CurrentLine = br.readLine()) != null) {
				// runs through file and reads each line
				
				if (CurrentLine.equals("event")) {
					counter = 0;
					Task a = new Event(name, desc, locationOrSubject, year, month, day, hour, minute);
					List.addEvent(a);
				} else if (CurrentLine.equals("assignment")) {
					counter = 0;
					Task a = new Assignment(name, desc, locationOrSubject, priority, year, month, day);
					List.addAssignment(a);
					
				}
				else if(!CurrentLine.equals("")){
				
					switch (counter) {
					case 0:
						name = CurrentLine;
						break;
					case 1:
						discLength = Integer.valueOf(CurrentLine);
						discCounter = 0;
						break;
					case 2:
						desc +=CurrentLine + "\n";
						discCounter++;
						if(discCounter < discLength){
							counter--;
						}
						break;
					case 3:
						year = Integer.valueOf(CurrentLine);
						break;
					case 4:
						month = Integer.valueOf(CurrentLine);
						break;
					case 5:
						day = Integer.valueOf(CurrentLine);
						break;
					case 6:
						hour = Integer.valueOf(CurrentLine);
						break;
					case 7:
						minute = Integer.valueOf(CurrentLine);
						break;
					case 8:
						locationOrSubject = CurrentLine;
						break;
					case 9:
						priority = Boolean.valueOf(CurrentLine);
						break;
					}
					counter++;
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeFile() {
		String data = "";
		try {
			for (int i = 0; i < List.list.size(); i++) {
				Task temp = List.list.get(i);
				data += temp.name + "\n";
				String[] lineArray = temp.description.split("\n");
				data += lineArray.length + "\n";
				data += temp.description + "\n";
				data += temp.date.toFile();
				if(temp.isEvent){
					data += ((Event)(temp)).getLocation() +"\n";
					data+= "event\n";
				}else{
					data += ((Assignment)(temp)).getSubject() + "\n";
					data += ((Assignment)(temp)).getPriority() + "\n";
					data+= "assignment\n";
				}
			}
			BufferedWriter out = new BufferedWriter(new FileWriter("Tasks"));
			// makes a buffered writer for the new score list
			out.write(data);// changes the text to the new tasks
			out.close();// closes the file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
