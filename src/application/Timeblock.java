package application;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Timeblock {
	
	private LocalTime start;
	private LocalTime end;
	private long duration;
	private String namelabel;
	// possibly add a color

	public Timeblock() {
		this.start = LocalTime.MIN;
		this.end = LocalTime.MAX;
		this.duration = start.until(end, ChronoUnit.SECONDS);
	}
	
	public Timeblock(LocalTime start, LocalTime end, String namelabel) {
		this.start = start;
		this.end = end;
		this.namelabel = namelabel;
		if (start.isBefore(end)) {this.duration = start.until(end, ChronoUnit.SECONDS);}
	}
	
	// function to convert our timeblocks in the .txt to a usable format
	ArrayList<Timeblock> createTimeblocks(ArrayList<String> list ) {
		ArrayList<Timeblock> newList = new ArrayList<Timeblock>();
		
		for (String i : list) {
			Timeblock toAdd = new Timeblock();
			String[] readlist = i.split(",");
			String starttime = readlist[0];
			toAdd.setStart(LocalTime.parse(starttime));
			String name = readlist[1];
			toAdd.setNamelabel(name);
			String endtime = readlist[2];
			toAdd.setEnd(LocalTime.parse(endtime));
			newList.add(toAdd);

		}
		return newList;
	}
	
	public String toString() {
		
		return "Task: " + namelabel + "\nStart Time: " + this.start.toString() + "\nEnd Time: " + this.end.toString() + "\n";
	}
	public String getSaveFileFormat(Timeblock x) {
		String string = x.getStart() + "," + x.getNamelabel() + "," + x.getEnd();
		return string;
	}

	LocalTime getStart() {
		return start;
	}

	void setStart(LocalTime start) {
		this.start = start;
	}

	LocalTime getEnd() {
		return end;
	}

	void setEnd(LocalTime end) {
		this.end = end;
	}

	long getDuration() {
		return duration;
	}

	void setDuration(long duration) {
		this.duration = duration;
	}

	String getNamelabel() {
		return namelabel;
	}

	void setNamelabel(String namelabel) {
		this.namelabel = namelabel;
	}
	
	
	
	// WIP
	public boolean overlappingTime(Timeblock otherTimeblock) {
		
		
		if (otherTimeblock.start == end) {
			
			System.out.println(otherTimeblock.start + " Overlaps with " + end);
			return true;
			
		}
		
		else {
		
			return false;
			
		}
		
		
	}

}