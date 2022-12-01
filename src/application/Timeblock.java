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
	
	
	/**
	 * 
	 * Checks if there are overlapping times in taskList to avoid user from entering duplicates or conflicting times
	 * 
	 * @param taskList
	 * @return
	 */

	public boolean overlappingTime(ArrayList<String> taskList) {
		
		
		// Returns true if there is a conflict and returns false if there is no conflict
		boolean overlapStatus = false;
		
		
		// If start time and end times are the same, return true
		if (start == end) {
			
			System.out.println("Same end and start!");
			
			overlapStatus = true;
			
			return overlapStatus;
			
		}
		
		// Loop through the taskList and find duplicates of times using the String value of start
		for (String tasksCreated : taskList) {
			
			// If start time already exists in the ArrayList
			// The issue with this is that for example the start is 01:00 and end is 02:00
			// The next task cannot be start: 02:00 and end 03:00 as the 02:00 already exists
			// Therefore, the next start time can be a time of 02:05 for it to be added
			 if (tasksCreated.contains(start.toString()) == true) {
				 
				overlapStatus = true;

			}
	}
		
		return overlapStatus;

	}
}