package application;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * 
 * This class defines what a timeblock is for the
 * purposes of our program
 * @author Connor Ell and Jose Lorenzo Jacobe
 *
 */
public class Timeblock {
	
	private LocalTime start;
	private LocalTime end;
	private long duration;
	private String namelabel;

	/**
	 * Default constructor
	 */
	public Timeblock() {
		this.start = LocalTime.MIN;
		this.end = LocalTime.MAX;
		this.duration = start.until(end, ChronoUnit.SECONDS);
	}

	/**
	 * Constructor to create a timeblock with specified start, end, and name
	 * @param start the start of the timeblock
	 * @param end the end of the timeblock
	 * @param namelabel the name of the timeblock
	 */
	public Timeblock(LocalTime start, LocalTime end, String namelabel) {
		if (start.isBefore(end)) {
			this.start = start;
			this.end = end;
			this.namelabel = namelabel;
			this.duration = start.until(end, ChronoUnit.SECONDS);
		}
		else {System.out.println("Error making time block");}
	}

	/**
	 * This function is used to create a list of timeblocks from a list of strings
	 * @param inputList the arraylist of strings to convert
	 * @return Will return the new list as a arraylist of timeblocks
	 */
	public ArrayList<Timeblock> createTimeblocks(ArrayList<String> inputList ) {
		ArrayList<String> list = new ArrayList<String>(inputList);
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

	/**
	 * This is a custom toString method to print timeblocks in a user readable format
	 * @return returns the timeblock as a string
	 */
	public String toString() {
		return "Task: " + namelabel + "\nStart Time: " + this.start.toString() + "\nEnd Time: " + this.end.toString() + "\n";
	}

	/**
	 * Used to covert the timeblock to the proper format for writing to a savefile
	 * @param x the timeblock to get the format of
	 * @return returns the formatted timeblock string
	 */
	public String getSaveFileFormat(Timeblock x) {
		return x.getStart() + "," + x.getNamelabel() + "," + x.getEnd();
	}

	/**
	 * 
	 * Checks if there are overlapping times in taskList to avoid user from entering duplicates or conflicting times
	 * 
	 * @param taskList
	 * @return true if overlap is found
	 */
	public boolean overlappingTime(ArrayList<String> taskList) {
		
		// Set to true if there is a conflict and set to false if there is no conflict
		boolean overlapStatus = false;
		
		// If the compare value is 0, it means that both start and end times are the same
		// and there will be an overlap
		if (start.compareTo(end) == 0) {
			
			overlapStatus = true;
			
			return overlapStatus;

		}
		
		// Compares start time to end time. If the compare value is 1, it means the start time is greater than the end time
		// If the start time is greater than the end time, there will be an overlap
		else if (start.compareTo(end) > 0) {
			
			overlapStatus = true;
			
			return overlapStatus;
			
		}
		
		// Loop through the taskList and find duplicates of times using the String value of start
		for (String tasksCreated : taskList) {
			
			// If start time already exists in the ArrayList
			// The issue with this is that for example the start is 01:00 and end is 02:00
			// The next task cannot be start: 02:00 and end 03:00 as the 02:00 already exists
			// Therefore, the next start time can be a time of 02:05 for it to be added
			 if (tasksCreated.contains(start.toString()) == true || tasksCreated.contains(end.toString()) == true) {
				 
				overlapStatus = true;

			}
	}
		
		return overlapStatus;

	}
	//Getters and setters below
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
}

