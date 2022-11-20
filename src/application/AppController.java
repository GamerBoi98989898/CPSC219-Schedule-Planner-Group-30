package application;


import java.io.IOException;


import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AppController extends Timeblock {
	
	Stage applicationStage;
	
	@FXML
	Button loginButton;
	
	@FXML
	Button registerButton;
	
	@FXML
	Button completeRegisterButton;
	
	/**
	 * 
	 * Check if user already exists and if credentials are correct, proceed to show their current schedule
	 * 
	 * @param event
	 * @throws IOException 
	 */
	public void userLogin(ActionEvent Event) throws Exception {
		
		
			Parent root = FXMLLoader.load(getClass().getResource("ScheduleView.fxml"));
			
			Stage applicationStage = (Stage)loginButton.getScene().getWindow();
			
			applicationStage.setScene(new Scene(root, 600, 400));

	}
	
	
	/**
	 * 
	 * Register new user and prompt their username and password upon button press
	 * 
	 * @param event
	 * @throws Exception 
	 */
	public void registerUser(ActionEvent event) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("RegisterView.fxml"));
		
		Stage applicationStage = (Stage)registerButton.getScene().getWindow();
		
		applicationStage.setScene(new Scene(root, 600, 400));
		
	}
		
	
	
	/**
	 * 
	 * Once user has completed their registration, it will return to the main login screen for them to login
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void completeRegister(ActionEvent event) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
		
		Stage applicationStage = (Stage)completeRegisterButton.getScene().getWindow();
		
		applicationStage.setScene(new Scene(root, 600, 400));
		
		
	}
	
}
