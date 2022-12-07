package application;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.math.*;

/**
 * This class defines the comparison of 2
 * Users to find common free time
 * @author Connor Ell and Jose Lorenzo Jacobe
 */
public class Comparison {

	private ArrayList<Timeblock> sunfreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> monfreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> tuefreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> wedfreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> thufreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> frifreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> satfreetimelist = new ArrayList<Timeblock>();

	/**
	 * This constructor will simply take 2 users and creates a comparison object
	 * that will hold that data by calling the createcomparison function
	 * @param user1 the first user to compare
	 * @param user2 the second user to compare
	 */
	public Comparison(User user1, User user2) {
		createcomparison(user1, user2);
	}

	/**
	 * takes 2 users and then finds common free times
	 * @param usera the first user to use
	 * @param userb the second user to use
	 */
	private void createcomparison (User usera, User userb) {
		User user1 = new User(usera);
		User user2= new User(userb);
		ArrayList<ArrayList<Timeblock>> user1list = new ArrayList<>();
		ArrayList<ArrayList<Timeblock>> user2list = new ArrayList<>();

		// each index is one day of the week
		user1list.add(user1.getSunfreetime());
		user1list.add(user1.getMonfreetime());
		user1list.add(user1.getTuefreetime());
		user1list.add(user1.getWedfreetime());
		user1list.add(user1.getThufreetime());
		user1list.add(user1.getFrifreetime());
		user1list.add(user1.getSatfreetime());

		user2list.add(user2.getSunfreetime());
		user2list.add(user2.getMonfreetime());
		user2list.add(user2.getTuefreetime());
		user2list.add(user2.getWedfreetime());
		user2list.add(user2.getThufreetime());
		user2list.add(user2.getFrifreetime());
		user2list.add(user2.getSatfreetime());


		for (int i = 0; i < user1list.size(); i +=1) {
			for (Timeblock t1 : user1list.get(i)) { // loop through freetimes in first user
				for (Timeblock t2 : user2list.get(i)) { // loop through freetimes in second user
					if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd()) //checks if start and end times are the same or at least within 5 minutes
							|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) <= 5
							&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) <= 5
					) {
						LocalTime start = t2.getStart(); // then assigns start and end times accordingly
						LocalTime end = t2.getEnd();
						if (t1.getStart().isAfter(t2.getStart())) {
							start = t1.getStart();
						}
						if (t2.getStart().isAfter(t1.getStart())) {
							start = t2.getStart();
						}
						if (t1.getEnd().isAfter(t2.getEnd())) {
							end = t2.getEnd();
						}
						if (t2.getEnd().isAfter(t1.getEnd())) {
							end = t1.getEnd();
						}

						String name = "Free Time with "+user2.getUsername();
						Timeblock block = new Timeblock(start, end, name);

						switch (i) {
							case 0: sunfreetimelist.add(block);
								break;
							case 1: monfreetimelist.add(block);
								break;
							case 2: tuefreetimelist.add(block);
								break;
							case 3: wedfreetimelist.add(block);
								break;
							case 4: thufreetimelist.add(block);
								break;
							case 5: frifreetimelist.add(block);
								break;
							case 6: satfreetimelist.add(block);
								break;
						}
					}
				}
			}
		}
	}

	//getters and setters below
	public ArrayList<Timeblock> getSunfreetimelist() {
		return sunfreetimelist;
	}

	public void setSunfreetimelist(ArrayList<Timeblock> sunfreetimelist) {
		this.sunfreetimelist = sunfreetimelist;
	}

	public ArrayList<Timeblock> getMonfreetimelist() {
		return monfreetimelist;
	}

	public void setMonfreetimelist(ArrayList<Timeblock> monfreetimelist) {
		this.monfreetimelist = monfreetimelist;
	}

	public ArrayList<Timeblock> getTuefreetimelist() {
		return tuefreetimelist;
	}

	public void setTuefreetimelist(ArrayList<Timeblock> tuefreetimelist) {
		this.tuefreetimelist = tuefreetimelist;
	}

	public ArrayList<Timeblock> getWedfreetimelist() {
		return wedfreetimelist;
	}

	public void setWedfreetimelist(ArrayList<Timeblock> wedfreetimelist) {
		this.wedfreetimelist = wedfreetimelist;
	}

	public ArrayList<Timeblock> getThufreetimelist() {
		return thufreetimelist;
	}

	public void setThufreetimelist(ArrayList<Timeblock> thufreetimelist) {
		this.thufreetimelist = thufreetimelist;
	}

	public ArrayList<Timeblock> getFrifreetimelist() {
		return frifreetimelist;
	}

	public void setFrifreetimelist(ArrayList<Timeblock> frifreetimelist) {
		this.frifreetimelist = frifreetimelist;
	}

	public ArrayList<Timeblock> getSatfreetimelist() {
		return satfreetimelist;
	}

	public void setSatfreetimelist(ArrayList<Timeblock> satfreetimelist) {
		this.satfreetimelist = satfreetimelist;
	}


}