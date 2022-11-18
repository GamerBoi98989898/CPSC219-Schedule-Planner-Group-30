package application;
import java.io.*;
import java.util.ArrayList;

public class User {
	private String fileName; //May be removed
	private String username;
	private String password;
	private ArrayList<String> timetable;

	public User() {
	}
	
	//
	public User(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			this.username = reader.readLine();
			this.password = reader.readLine();
			System.out.println(username);
			System.out.println(password);
			reader.close();
		} catch (FileNotFoundException fnf) {
			//
			fnf.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void saveToFile(String filename) {
		try {
			PrintWriter writer= new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			writer.println(username);
			writer.println(password);
			writer.close();
		} catch (IOException ioe) {
			System.out.print(ioe);
			ioe.printStackTrace();
		}
	}

	
	
	
	// Getters and setters below
	String getFileName() {
		return fileName;
	}

	void setFileName(String fileName) {
		this.fileName = fileName;
	}

	String getUsername() {
		return username;
	}

	void setUsername(String username) {
		this.username = username;
	}

	String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	ArrayList<String> getTimetable() {
		return timetable;
	}

	void setTimetable(ArrayList<String> timetable) {
		this.timetable = timetable;
	}
	

}
