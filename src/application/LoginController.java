package application;

import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {
	
	Stage applicationStage;
	
	
	
	/**
	 * 
	 * Check if user already exists and if credentials are correct, proceed to show their current schedule
	 * 
	 * @param event
	 */
	public void userLogin(ActionEvent event) {
		

		
		Scene testScene = new Scene(new Label("Next Scene Label"), 600, 400);
		applicationStage.setScene(testScene);
		

		

		
		
		
	}
	
	
	/**
	 * 
	 * Register new user and prompt their username and password upon button press
	 * 
	 * @param event
	 */
	public void registerUser(ActionEvent event) {
		
		
		
		try {
			
			FXMLLoader registerPage = new FXMLLoader();
			VBox root = registerPage.load(new FileInputStream("src/application/RegisterView.fxml"));
			
			Scene testScene = new Scene(root, 600, 400);
			applicationStage.setScene(testScene);
			
			
			
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
			
		}
		


		

		
		
		
	}
	
	

}
