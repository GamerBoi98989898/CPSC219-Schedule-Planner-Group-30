package application;
import java.io.*;
import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private ArrayList<String> suntimetable = new ArrayList<String>();
	private ArrayList<String> montimetable = new ArrayList<String>();
	private ArrayList<String> tuetimetable = new ArrayList<String>();
	private ArrayList<String> wedtimetable = new ArrayList<String>();
	private ArrayList<String> thutimetable = new ArrayList<String>();
	private ArrayList<String> fritimetable = new ArrayList<String>();
	private ArrayList<String> sattimetable = new ArrayList<String>();
	
	private ArrayList<Timeblock> blocktest = new ArrayList<Timeblock>();

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
			
			String line = reader.readLine();
			while (line!=null) {
				if (line.contains(":")) {
					line = "time";
				}
				switch (line) {
				case "sun":
					System.out.println("sun was found");
					line = reader.readLine();
					while (line.contains(":")) {
						System.out.println("writing times");
						suntimetable.add(line);
						line = reader.readLine();
					}
					continue;
				case "mon":
					System.out.println("mon was found");
					line = reader.readLine();
					while (line.contains(":")) {
						System.out.println("writing times");
						montimetable.add(line);
						line = reader.readLine();
					}
					continue;
				case "tue":
					System.out.println("tue was found");
					line = reader.readLine();
					while (line.contains(":")) {
						System.out.println("writing times");
						tuetimetable.add(line);
						line = reader.readLine();
					}
					continue;
				case "wed":
					System.out.println("wed was found");
					line = reader.readLine();
					while (line.contains(":")) {
						System.out.println("writing times");
						wedtimetable.add(line);
						line = reader.readLine();
					}
					continue;
				case "thu":
					System.out.println("thu was found");
					line = reader.readLine();
					while (line.contains(":")) {
						System.out.println("writing times");
						thutimetable.add(line);
						line = reader.readLine();
					}
					continue;
				case "fri":
					System.out.println("fri was found");
					line = reader.readLine();
					while (line.contains(":")) {
						System.out.println("writing times");
						fritimetable.add(line);
						line = reader.readLine();
					}
					continue;
				case "sat":
					System.out.println("sat was found");
					line = reader.readLine();
					while (line.contains(":")) {
						System.out.println("writing times");
						sattimetable.add(line);
						line = reader.readLine();
					}
					continue;
				case "time":
					System.out.println("time was found");
					continue;
				case "end":
					System.out.println("eof was found");
					break;
				}
				
				
				line = reader.readLine();
			}
			
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

	public ArrayList<String> getSuntimetable() {
		return suntimetable;
	}

	public void setSuntimetable(ArrayList<String> suntimetable) {
		this.suntimetable = suntimetable;
	}

	public ArrayList<String> getMontimetable() {
		return montimetable;
	}

	public void setMontimetable(ArrayList<String> montimetable) {
		this.montimetable = montimetable;
	}

	public ArrayList<String> getTuetimetable() {
		return tuetimetable;
	}

	public void setTuetimetable(ArrayList<String> tuetimetable) {
		this.tuetimetable = tuetimetable;
	}

	public ArrayList<String> getWedtimetable() {
		return wedtimetable;
	}

	public void setWedtimetable(ArrayList<String> wedtimetable) {
		this.wedtimetable = wedtimetable;
	}

	public ArrayList<String> getThutimetable() {
		return thutimetable;
	}

	public void setThutimetable(ArrayList<String> thutimetable) {
		this.thutimetable = thutimetable;
	}

	public ArrayList<String> getFritimetable() {
		return fritimetable;
	}

	public void setFritimetable(ArrayList<String> fritimetable) {
		this.fritimetable = fritimetable;
	}

	public ArrayList<String> getSattimetable() {
		return sattimetable;
	}

	public void setSattimetable(ArrayList<String> sattimetable) {
		this.sattimetable = sattimetable;
	}

}
