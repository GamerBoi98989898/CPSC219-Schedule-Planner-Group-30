package application;
import java.io.*;
import java.time.LocalTime;
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
	
	private ArrayList<Timeblock> suntimeblocks = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> montimeblocks = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> tuetimeblocks = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> wedtimeblocks = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> thutimeblocks = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> fritimeblocks = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> sattimeblocks = new ArrayList<Timeblock>();

	private ArrayList<Timeblock> sunfreetime = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> monfreetime = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> tuefreetime = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> wedfreetime = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> thufreetime = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> frifreetime = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> satfreetime = new ArrayList<Timeblock>();



	private static User currentUser = new User();

	public User() {
	}

	public User(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.suntimetable = user.getSuntimetable();
		this.montimetable = user.getMontimetable();
		this.tuetimetable = user.getTuetimetable();
		this.wedtimetable = user.getWedtimetable();
		this.thutimetable = user.getThutimetable();
		this.fritimetable = user.getFritimetable();
		this.sattimetable = user.getSattimetable();
		this.suntimeblocks = user.getSuntimeblocks();
		this.montimeblocks = user.getMontimeblocks();
		this.tuetimeblocks = user.getTuetimeblocks();
		this.wedtimeblocks = user.getWedtimeblocks();
		this.thutimeblocks = user.getThutimeblocks();
		this.fritimeblocks = user.getFritimeblocks();
		this.sattimeblocks = user.getSattimeblocks();
		this.sunfreetime = user.getSunfreetime();
		this.monfreetime = user.getMonfreetime();
		this.tuefreetime = user.getTuefreetime();
		this.wedfreetime = user.getWedfreetime();
		this.thufreetime = user.getThufreetime();
		this.frifreetime = user.getFrifreetime();
		this.satfreetime = user.getSatfreetime();
	}


	/**
	 * Constructor will take a filename and then read that file using the readFromFile method. See readFromFile for more info
	 * @param filename name of the file to read
	 */
	public User(String filename) {
		readFromFile(filename);
		convertToTimeblock();
		createFreeTimeArrays();
	}

	/**
	 * method will try to find and read a file. Throws IOException
	 * @param filename name of file to read
	 */
	private void readFromFile(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/"+filename+ ".txt"));
			this.username = reader.readLine();
			this.password = reader.readLine();
			//	System.out.println(username);
			//	System.out.println(password);

			String line = reader.readLine();
			while (line!=null) {
				if (line.contains(":")) {
					line = "time";
				}
				switch (line) {
					case "sun":
						//System.out.println("sun was found");
						line = reader.readLine();
						while (line.contains(":")) {
							//System.out.println("writing times");
							suntimetable.add(line);
							line = reader.readLine();
						}
						continue;
					case "mon":
						//System.out.println("mon was found");
						line = reader.readLine();
						while (line.contains(":")) {
							//System.out.println("writing times");
							montimetable.add(line);
							line = reader.readLine();
						}
						continue;
					case "tue":
						//System.out.println("tue was found");
						line = reader.readLine();
						while (line.contains(":")) {
							//System.out.println("writing times");
							tuetimetable.add(line);
							line = reader.readLine();
						}
						continue;
					case "wed":
						//System.out.println("wed was found");
						line = reader.readLine();
						while (line.contains(":")) {
							//System.out.println("writing times");
							wedtimetable.add(line);
							line = reader.readLine();
						}
						continue;
					case "thu":
						//System.out.println("thu was found");
						line = reader.readLine();
						while (line.contains(":")) {
							//System.out.println("writing times");
							thutimetable.add(line);
							line = reader.readLine();
						}
						continue;
					case "fri":
						//System.out.println("fri was found");
						line = reader.readLine();
						while (line.contains(":")) {
							//System.out.println("writing times");
							fritimetable.add(line);
							line = reader.readLine();
						}
						continue;
					case "sat":
						//System.out.println("sat was found");
						line = reader.readLine();
						while (line.contains(":")) {
							//System.out.println("writing times");
							sattimetable.add(line);
							line = reader.readLine();
						}
						continue;
					case "time":
						//System.out.println("time was found");
						continue;
					case "end":
						//System.out.println("eof was found");
						break;
				}


				line = reader.readLine();
			}

			reader.close();
			this.convertToTimeblock();
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Will save the current user timetable to a .txt file using the appropriate format. Creates a new file if it doesn't
	 * exist already
	 * @param filename name of the file to save to
	 */
	public void saveToFile(String filename) {
		try {
			PrintWriter writer= new PrintWriter(new BufferedWriter(new FileWriter("src/"+filename+ ".txt")));
			writer.println(username);
			writer.println(password);
			writer.println("sun");

			for (Timeblock x : getSuntimeblocks()) {
				writer.println(x.getSaveFileFormat(x));

			}

			writer.println("mon");

			for (String i : getMontimetable()) {
				writer.println(i);

			}

			writer.println("tue");

			for (String i : getTuetimetable()) {
				writer.println(i);

			}

			writer.println("wed");

			for (String i : getWedtimetable()) {
				writer.println(i);

			}

			writer.println("thu");

			for (String i : getThutimetable()) {
				writer.println(i);

			}

			writer.println("fri");

			for (String i : getFritimetable()) {
				writer.println(i);

			}

			writer.println("sat");

			for (String i : getSattimetable()) {
				writer.println(i);

			}

			writer.println("end");


			writer.close();
		} catch (IOException ioe) {
			System.out.print(ioe);
			ioe.printStackTrace();
		}
	}

	/**
	 * will convert the timetables of current object from lists of strings to lists of timeblocks
	 */
	private void convertToTimeblock() {
		Timeblock convert = new Timeblock();
		suntimeblocks = convert.createTimeblocks(suntimetable);
		montimeblocks = convert.createTimeblocks(montimetable);
		tuetimeblocks = convert.createTimeblocks(tuetimetable);
		wedtimeblocks = convert.createTimeblocks(wedtimetable);
		thutimeblocks = convert.createTimeblocks(thutimetable);
		fritimeblocks = convert.createTimeblocks(fritimetable);
		sattimeblocks = convert.createTimeblocks(sattimetable);

	}

	/**
	 * checks to see if a file with the correct username or password exists
	 * @param filename name of user/file to check for
	 * @param password password to check with
	 * @return
	 * @throws IOException if the user is not valid
	 */
	public boolean validateUser(String filename, String password) throws IOException{
			int x = 0;
			BufferedReader reader = new BufferedReader(new FileReader("src/" +filename+ ".txt"));
			String Thisfilename = reader.readLine();
			String Thispassword = reader.readLine();
			//System.out.println(Thisfilename);
			//System.out.println(Thispassword);
			//System.out.println(filename);
			//System.out.println(password);
			if (filename.equals(Thisfilename)) {
				x+=1;}
			if (password.equals(Thispassword)) {
				x+=1;}
		    //System.out.println(x);
			reader.close();
			if (x == 2) {return true;}
			return false;
	}
	public static boolean validateUser(String filename) throws IOException{
		int x = 0;
		BufferedReader reader = new BufferedReader(new FileReader("src/" +filename+ ".txt"));
		String Thisfilename = reader.readLine();
		if (filename.equals(Thisfilename)) {return true;}
		//System.out.println(x);
		reader.close();
		return false;
	}


	public void createFreeTimeArrays() {
		int i = 0;
		if (suntimeblocks.size() == 1) {
			LocalTime start1 = LocalTime.MIN;
			LocalTime end1 = suntimeblocks.get(0).getStart();
			LocalTime start2 = suntimeblocks.get(0).getEnd();
			LocalTime end2 = LocalTime.MAX;
			sunfreetime.add(new Timeblock(start1,end1,"free"));
			sunfreetime.add(new Timeblock(start2,end2,"free"));
		}
		while (i < suntimeblocks.size()-1) {
			LocalTime start = suntimeblocks.get(i).getEnd();
			LocalTime end = suntimeblocks.get(i+1).getStart();
			String name = "free";
			//System.out.println("start"+start+" "+"end"+end);
			sunfreetime.add(new Timeblock(start,end,name));
			//System.out.println(sunfreetime.get(i));
			i++;
		}
		i = 0;
		if (montimeblocks.size() == 1) {
			LocalTime start1 = LocalTime.MIN;
			LocalTime end1 = montimeblocks.get(0).getStart();
			LocalTime start2 = montimeblocks.get(0).getEnd();
			LocalTime end2 = LocalTime.MAX;
			monfreetime.add(new Timeblock(start1,end1,"free"));
			monfreetime.add(new Timeblock(start2,end2,"free"));
		}
		while (i < montimeblocks.size()-1) {
			LocalTime start = montimeblocks.get(i).getEnd();
			LocalTime end = montimeblocks.get(i+1).getStart();
			String name = "free";
			//System.out.println("start"+start+" "+"end"+end);
			monfreetime.add(new Timeblock(start,end,name));
			//System.out.println(monfreetime.get(i));
			i++;
		}
		i = 0;
		if (tuetimeblocks.size() == 1) {
			LocalTime start1 = LocalTime.MIN;
			LocalTime end1 = tuetimeblocks.get(0).getStart();
			LocalTime start2 = tuetimeblocks.get(0).getEnd();
			LocalTime end2 = LocalTime.MAX;
			tuefreetime.add(new Timeblock(start1,end1,"free"));
			tuefreetime.add(new Timeblock(start2,end2,"free"));
		}
		while (i < tuetimeblocks.size()-1) {
			LocalTime start = tuetimeblocks.get(i).getEnd();
			LocalTime end = tuetimeblocks.get(i+1).getStart();
			String name = "free";
			//System.out.println("start"+start+" "+"end"+end);
			tuefreetime.add(new Timeblock(start,end,name));
			//System.out.println(tuefreetime.get(i));
			i++;
		}
		i = 0;
		if (wedtimeblocks.size() == 1) {
			LocalTime start1 = LocalTime.MIN;
			LocalTime end1 = wedtimeblocks.get(0).getStart();
			LocalTime start2 = wedtimeblocks.get(0).getEnd();
			LocalTime end2 = LocalTime.MAX;
			wedfreetime.add(new Timeblock(start1,end1,"free"));
			wedfreetime.add(new Timeblock(start2,end2,"free"));
		}
		while (i < wedtimeblocks.size()-1) {
			LocalTime start = wedtimeblocks.get(i).getEnd();
			LocalTime end = wedtimeblocks.get(i+1).getStart();
			String name = "free";
			//System.out.println("start"+start+" "+"end"+end);
			wedfreetime.add(new Timeblock(start,end,name));
			//System.out.println(wedfreetime.get(i));
			i++;
		}
		i = 0;
		if (thutimeblocks.size() == 1) {
			LocalTime start1 = LocalTime.MIN;
			LocalTime end1 = thutimeblocks.get(0).getStart();
			LocalTime start2 = thutimeblocks.get(0).getEnd();
			LocalTime end2 = LocalTime.MAX;
			thufreetime.add(new Timeblock(start1,end1,"free"));
			thufreetime.add(new Timeblock(start2,end2,"free"));
		}
		while (i < thutimeblocks.size()-1) {
			LocalTime start = thutimeblocks.get(i).getEnd();
			LocalTime end = thutimeblocks.get(i+1).getStart();
			String name = "free";
			//System.out.println("start"+start+" "+"end"+end);
			thufreetime.add(new Timeblock(start,end,name));
			//System.out.println(thufreetime.get(i));
			i++;
		}
		i = 0;
		if (fritimeblocks.size() == 1) {
			LocalTime start1 = LocalTime.MIN;
			LocalTime end1 = fritimeblocks.get(0).getStart();
			LocalTime start2 = fritimeblocks.get(0).getEnd();
			LocalTime end2 = LocalTime.MAX;
			frifreetime.add(new Timeblock(start1,end1,"free"));
			frifreetime.add(new Timeblock(start2,end2,"free"));
		}
		while (i < fritimeblocks.size()-1) {
			LocalTime start = fritimeblocks.get(i).getEnd();
			LocalTime end = fritimeblocks.get(i+1).getStart();
			String name = "free";
			//System.out.println("start"+start+" "+"end"+end);
			frifreetime.add(new Timeblock(start,end,name));
			//System.out.println(frifreetime.get(i));
			i++;
		}
		i = 0;
		if (sattimeblocks.size() == 1) {
			LocalTime start1 = LocalTime.MIN;
			LocalTime end1 = sattimeblocks.get(0).getStart();
			LocalTime start2 = sattimeblocks.get(0).getEnd();
			LocalTime end2 = LocalTime.MAX;
			satfreetime.add(new Timeblock(start1,end1,"free"));
			satfreetime.add(new Timeblock(start2,end2,"free"));
		}
		while (i < sattimeblocks.size()-1) {
			LocalTime start = sattimeblocks.get(i).getEnd();
			LocalTime end = sattimeblocks.get(i+1).getStart();
			String name = "free";
			//System.out.println("start"+start+" "+"end"+end);
			satfreetime.add(new Timeblock(start,end,name));
			//System.out.println(satfreetime.get(i));
			i++;
		}

	}

	// Getters and setters below
	String getUsername() {return username;}

	void setUsername(String username) {this.username = username;}

	String getPassword() {return password;}

	void setPassword(String password) {this.password = password;}

	public ArrayList<String> getSuntimetable() {return suntimetable;}

	public void setSuntimetable(ArrayList<String> suntimetable) {this.suntimetable = suntimetable;}

	public ArrayList<String> getMontimetable() {return montimetable;}

	public void setMontimetable(ArrayList<String> montimetable) {this.montimetable = montimetable;}

	public ArrayList<String> getTuetimetable() {return tuetimetable;}

	public void setTuetimetable(ArrayList<String> tuetimetable) {this.tuetimetable = tuetimetable;}

	public ArrayList<String> getWedtimetable() {return wedtimetable;}

	public void setWedtimetable(ArrayList<String> wedtimetable) {this.wedtimetable = wedtimetable;}

	public ArrayList<String> getThutimetable() {return thutimetable;}

	public void setThutimetable(ArrayList<String> thutimetable) {this.thutimetable = thutimetable;}

	public ArrayList<String> getFritimetable() {return fritimetable;}

	public void setFritimetable(ArrayList<String> fritimetable) {this.fritimetable = fritimetable;}

	public ArrayList<String> getSattimetable() {return sattimetable;}

	public void setSattimetable(ArrayList<String> sattimetable) {this.sattimetable = sattimetable;}

	public ArrayList<Timeblock> getSuntimeblocks() {return suntimeblocks;}

	public void setSuntimeblocks(ArrayList<Timeblock> suntimeblocks) {this.suntimeblocks = suntimeblocks;}

	public ArrayList<Timeblock> getMontimeblocks() {return montimeblocks;}

	public void setMontimeblocks(ArrayList<Timeblock> montimeblocks) {this.montimeblocks = montimeblocks;}

	public ArrayList<Timeblock> getTuetimeblocks() {return tuetimeblocks;}

	public void setTuetimeblocks(ArrayList<Timeblock> tuetimeblocks) {this.tuetimeblocks = tuetimeblocks;}

	public ArrayList<Timeblock> getWedtimeblocks() {return wedtimeblocks;}

	public void setWedtimeblocks(ArrayList<Timeblock> wedtimeblocks) {this.wedtimeblocks = wedtimeblocks;}

	public ArrayList<Timeblock> getThutimeblocks() {return thutimeblocks;}

	public void setThutimeblocks(ArrayList<Timeblock> thutimeblocks) {this.thutimeblocks = thutimeblocks;}

	public ArrayList<Timeblock> getFritimeblocks() {return fritimeblocks;}

	public void setFritimeblocks(ArrayList<Timeblock> fritimeblocks) {this.fritimeblocks = fritimeblocks;}

	public ArrayList<Timeblock> getSattimeblocks() {return sattimeblocks;}

	public void setSattimeblocks(ArrayList<Timeblock> sattimeblocks) {this.sattimeblocks = sattimeblocks;}

	public ArrayList<Timeblock> getSunfreetime() {
		return sunfreetime;
	}

	public void setSunfreetime(ArrayList<Timeblock> sunfreetime) {
		this.sunfreetime = sunfreetime;
	}

	public ArrayList<Timeblock> getMonfreetime() {
		return monfreetime;
	}

	public void setMonfreetime(ArrayList<Timeblock> monfreetime) {
		this.monfreetime = monfreetime;
	}

	public ArrayList<Timeblock> getTuefreetime() {
		return tuefreetime;
	}

	public void setTuefreetime(ArrayList<Timeblock> tuefreetime) {
		this.tuefreetime = tuefreetime;
	}

	public ArrayList<Timeblock> getWedfreetime() {
		return wedfreetime;
	}

	public void setWedfreetime(ArrayList<Timeblock> wedfreetime) {
		this.wedfreetime = wedfreetime;
	}

	public ArrayList<Timeblock> getThufreetime() {
		return thufreetime;
	}

	public void setThufreetime(ArrayList<Timeblock> thufreetime) {
		this.thufreetime = thufreetime;
	}

	public ArrayList<Timeblock> getFrifreetime() {
		return frifreetime;
	}

	public void setFrifreetime(ArrayList<Timeblock> frifreetime) {
		this.frifreetime = frifreetime;
	}

	public ArrayList<Timeblock> getSatfreetime() {
		return satfreetime;
	}

	public void setSatfreetime(ArrayList<Timeblock> satfreetime) {
		this.satfreetime = satfreetime;
	}
}
