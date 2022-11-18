package application;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*User test = new User();
		test.setFileName("usertest");
		test.setUsername("testname");
		test.setPassword("testpass");
		test.saveToFile("src/test");
		User.writeFile("src/testfile2.txt");
		User.readFile("src/testfile.txt");*/
		User test = new User("src/test");
		test.setUsername("da man");
		System.out.println(test.getUsername());
		test.setPassword("da boi");
		System.out.println(test.getPassword());
		test.saveToFile("src/ThatFile");
		
	    long duration = 0;
	    LocalTime start = LocalTime.MIN;
	    LocalTime end = LocalTime.MAX;
	    duration = start.until(end, ChronoUnit.HOURS);
	    System.out.println(duration);
	}

}
