package application;
import java.io.*;
import java.util.ArrayList;

public class User {
	private String fileName;
	private String username;
	private String password;
	private ArrayList<String> timetable;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public static void readFile(String x) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(x));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			
		} catch (FileNotFoundException fnf){
			System.out.print(fnf);
			fnf.printStackTrace();
		} catch (IOException ioe) {
			System.out.print(ioe);
			ioe.printStackTrace();
		}
		
	}
	public static void writeFile(String x) {
		try {
			PrintWriter writer= new PrintWriter(new BufferedWriter(new FileWriter(x)));
			writer.print("b");
			writer.close();
		} catch (IOException e) {
			System.out.print(e);
			e.printStackTrace();
		}
	}
	public void saveToFile(String x) {
		try {
			PrintWriter writer= new PrintWriter(new BufferedWriter(new FileWriter(x)));
			writer.println(fileName);
			writer.println(username);
			writer.println(password);
			writer.close();
		} catch (IOException e) {
			System.out.print(e);
			e.printStackTrace();
		}
	}
	
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
