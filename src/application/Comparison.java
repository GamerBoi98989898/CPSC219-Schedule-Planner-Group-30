package application;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.math.*;

public class Comparison {

	private ArrayList<Timeblock> sunfreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> monfreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> tuefreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> wedfreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> thufreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> frifreetimelist = new ArrayList<Timeblock>();
	private ArrayList<Timeblock> satfreetimelist = new ArrayList<Timeblock>();

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


		for (Timeblock t1 : user1list.get(0)) {
			for (Timeblock t2 : user2list.get(0)) {
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())
						|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) <= 5
						&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) <= 5
				) {
					LocalTime start = t2.getStart();
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
                    sunfreetimelist.add(block);
				}
			}
		}
		for (Timeblock t1 : user1list.get(1)) {
			for (Timeblock t2 : user2list.get(1)) {
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())
						|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) < 5
						&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) < 5
				 ) {
					LocalTime start = t2.getStart();
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
					monfreetimelist.add(block);
				}
			}
		}
		for (Timeblock t1 : user1list.get(2)) {
			for (Timeblock t2 : user2list.get(2)) {
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())
						|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) < 5
						&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) < 5
				) {
					LocalTime start = t2.getStart();
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
					tuefreetimelist.add(block);
				}
			}
		}
		for (Timeblock t1 : user1list.get(3)) {
			for (Timeblock t2 : user2list.get(3)) {
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())
						|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) < 5
						&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) < 5
				) {
					LocalTime start = t2.getStart();
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
					wedfreetimelist.add(block);
				}
			}
		}
		for (Timeblock t1 : user1list.get(4)) {
			for (Timeblock t2 : user2list.get(4)) {
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())
						|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) < 5
						&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) < 5
				) {
					LocalTime start = t2.getStart();
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
					thufreetimelist.add(block);
				}
			}
		}
		for (Timeblock t1 : user1list.get(5)) {
			for (Timeblock t2 : user2list.get(5)) {
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())
						|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) < 5
						&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) < 5
				) {
					LocalTime start = t2.getStart();
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
					frifreetimelist.add(block);
				}
			}
		}
		for (Timeblock t1 : user1list.get(6)) {
			for (Timeblock t2 : user2list.get(6)) {
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())
						|| Math.abs(t1.getStart().getLong(ChronoField.MINUTE_OF_DAY) - t2.getStart().getLong(ChronoField.MINUTE_OF_DAY)) < 5
						&& Math.abs(t1.getEnd().getLong(ChronoField.MINUTE_OF_DAY) - t2.getEnd().getLong(ChronoField.MINUTE_OF_DAY)) < 5
				) {
					LocalTime start = t2.getStart();
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
					satfreetimelist.add(block);
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