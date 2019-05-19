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
		
		File f = new File("Tasks");
		try {
			//checks if the file already exists
			if (f.exists() == false) {
				//creates the file
				PrintWriter writer = new PrintWriter("Tasks");
				//close file
				writer.close();
			}
			// reads the file
			br = new BufferedReader(new FileReader("Tasks"));
			// for what is on each line
			String CurrentLine;
			// variables that will be used to create the objects
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
			
			// runs through file and reads each line
			while ((CurrentLine = br.readLine()) != null) {
				
				//if it needs to create an event
				if (CurrentLine.equals("event")&& counter > 8) {
					counter = 0;
					Task a = new Event(name, desc, locationOrSubject, year, month, day, hour, minute);
					List.addTask(a);
				//if it needs to create an assignment
				} else if (CurrentLine.equals("assignment")&& counter > 8) {
					counter = 0;
					Task a = new Assignment(name, desc, locationOrSubject, priority, year, month, day);
					List.addTask(a);
					
				}
				// if it is reading lines with info about the task
				else{
					// depending on which line it is reading, it sets the variables above to that line
					switch (counter) {
					case 0:
						
						name = CurrentLine;
						break;
					case 1:
						desc = "";
						discLength = Integer.valueOf(CurrentLine);
						discCounter = 0;
						break;
					case 2:
						desc +=CurrentLine;
						discCounter++;
						if(discCounter < discLength){
							desc+="\n";
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
// when it needs to write to the file
	public static void writeFile() {
		//the string everything is put into
		String data = "";
		try {
			//loops though all of the tasks and adds every necessary variable to the string
			for (int i = 0; i < List.list.size(); i++) {
				Task temp = List.list.get(i);
				data += temp.name + "\n";
				String[] lineArray = temp.description.split("\n");
				System.out.print("desc start|" + temp.description +"|end");
				data += lineArray.length + "\n";
				data += temp.description + "\n";
				data += temp.date.toFile();
				if(!temp.isAssignment()){
					data += ((Event)(temp)).getLocation() +"\n";
					data+= "event\n";
				}else{
					data += ((Assignment)(temp)).getSubject() + "\n";
					data += ((Assignment)(temp)).getPriority() + "\n";
					data+= "assignment\n";
				}
			}
			//edits the file named "Tasks"
			BufferedWriter out = new BufferedWriter(new FileWriter("Tasks"));
			//changes the text of the file to the string
			out.write(data);
			//closes the file
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
