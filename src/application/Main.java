package application;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User test = new User();
		test.setFileName("usertest");
		test.setUsername("testname");
		test.setPassword("testpass");
		test.saveToFile("src/test");
		User.writeFile("src/testfile2.txt");
		User.readFile("src/testfile.txt");
	}

}
