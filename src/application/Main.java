package application;


import java.io.FileInputStream;
import java.time.LocalTime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/LoginView.fxml"));
			LoginController controller = (LoginController)loader.getController();
			controller.applicationStage = primaryStage;
			
			// Window Size
			Scene scene = new Scene(root, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Schedule Planner App");
			primaryStage.show();
			
		} 
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}*/
	
public class Main {

	public static void main(String[] args) {
		/*User test = new User();
		test.setFileName("usertest");
		test.setUsername("testname");
		test.setPassword("testpass");
		test.saveToFile("src/test");
		User.writeFile("src/testfile2.txt");
		User.readFile("src/testfile.txt");
		User test = new User("src/test");
		test.setUsername("da man");
		System.out.println(test.getUsername());
		test.setPassword("da boi");
		System.out.println(test.getPassword());
		test.saveToFile("src/daFile");*/
		User thing = new User("src/yeet.txt");
		thing.getUsername();
		thing.getPassword();
		Timeblock tester = new Timeblock();
		tester.createTimeblock(thing.getSuntimetable());
	}
}