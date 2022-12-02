package application;

import java.util.ArrayList;

public class Comparison {


	private ArrayList<Timeblock> freetimelist = new ArrayList<Timeblock>();

	public Comparison(ArrayList<Timeblock> free1, ArrayList<Timeblock> free2) {
		for (Timeblock t1 : free1) {
			for (Timeblock t2 : free2) {
				//System.out.println(t1 + "" + t2);
				if (t1.getStart().equals(t2.getStart()) && t1.getEnd().equals(t2.getEnd())) {
					System.out.println("YEET");
					Timeblock block = new Timeblock(t1.getStart(), t1.getEnd(), "YEET");
					freetimelist.add(block);
				}
			}
		}

	}
	public ArrayList<Timeblock> getFreetimelist() {
		return freetimelist;
	}

	public void setFreetimelist(ArrayList<Timeblock> freetimelist) {
		this.freetimelist = freetimelist;
	}
}