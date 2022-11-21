package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppController  {
	
	Stage applicationStage;
	
	Scene mainScene;
	
	@FXML
	Button loginButton;
	
	@FXML
	Button registerButton;
	
	@FXML
	Button completeRegisterButton;
	
	@FXML
	Button createScheduleButton;
	
	@FXML
	Button createTaskButton;
	
	@FXML
	DatePicker dateSelect;
	
	
	
	@FXML
	ChoiceBox<String> startHourChoiceBox;
	@FXML
	ChoiceBox<String> startMinChoiceBox;
	@FXML
	ChoiceBox<String> endHourChoiceBox;
	@FXML
	ChoiceBox<String> endMinChoiceBox;
	@FXML
	TextField taskName;
	
	@FXML
	Button doneButton;
	
	@FXML
	TextField createUsernameTextField;
	@FXML
	PasswordField createPasswordField;

	
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
		try {
		String filename = createUsernameTextField.getText();
		String password = createPasswordField.getText();
		System.out.println(filename);
		System.out.println(password);
		
		
		PrintWriter writer= new PrintWriter(new BufferedWriter(new FileWriter("src/saved" +filename+ ".txt")));
		writer.println(filename);
		writer.println(password);
		writer.close();
		} catch (IOException ioe) {
			System.out.print(ioe);
			ioe.printStackTrace();
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
		
		Stage applicationStage = (Stage)completeRegisterButton.getScene().getWindow();
		
		applicationStage.setScene(new Scene(root, 600, 400));
		
		
	}
	
	
	/**
	 * 
	 * Brings user to a new scene to allow them to create their schedule
	 * 
	 * @param event
	 * @throws Exception 
	 */
	
	public void createSchedule(ActionEvent event) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("CreateScheduleView.fxml"));
		
		Stage applicationStage = (Stage)createScheduleButton.getScene().getWindow();
		
		applicationStage.setScene(new Scene(root, 600, 400));

	}
	
	 

	public void createTask(ActionEvent event) throws Exception  {
		
		System.out.println("Task Created!");
		System.out.println("Date: " + dateSelect.getValue() + " Task: " + taskName.getText() + " Start Time: " + startHourChoiceBox.getValue() + ":" + startMinChoiceBox.getValue() + " End Time: " + endHourChoiceBox.getValue() + ":" + endMinChoiceBox.getValue());
		
		Timeblock timeBlock = new Timeblock(LocalTime.parse(startHourChoiceBox.getValue() + ":" + startMinChoiceBox.getValue()), LocalTime.parse(endHourChoiceBox.getValue() + ":" + endMinChoiceBox.getValue()), taskName.getText());
		
		System.out.println(timeBlock.toString());
		
		
	}
	
	
	
	public void doneScheduleCreate(ActionEvent Event) throws Exception {
		
		
			Parent root = FXMLLoader.load(getClass().getResource("ScheduleView.fxml"));
			
			Stage applicationStage = (Stage)doneButton.getScene().getWindow();
			
			applicationStage.setScene(new Scene(root, 600, 400));
			applicationStage.show();
	
	
	}
}
