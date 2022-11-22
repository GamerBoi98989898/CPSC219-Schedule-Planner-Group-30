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
	
	@FXML
	TextField usernameTextField;
	
	@FXML
	PasswordField passwordTextField;
	
	@FXML
	Label LoginErrorLabel;
	
	private ArrayList<String> taskList = new ArrayList<String>();
	
	String startHour;
	String startMin;
	
	String startTask = startHour + ":" + startMin;
	
	String endHour;
	String endMin;
	
	String endTask = endHour + ":" + endMin;
	
	String task;
	

	
	/**
	 * 
	 * Check if user already exists and if credentials are correct, proceed to show their current schedule
	 * 
	 * @param
	 * @throws
	 */
	
	public void userLogin(ActionEvent Event) throws Exception {
		User toValidate = new User();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		//System.out.println(username);
		//System.out.println(password);
		try {
			if (toValidate.validateUser(username, password)) {
				System.out.println("VALID");
			}
			try {
				Parent root = FXMLLoader.load(getClass().getResource("ScheduleView.fxml"));
				
				Stage applicationStage = (Stage)loginButton.getScene().getWindow();
			
				applicationStage.setScene(new Scene(root, 600, 400));
				} catch (IOException ioe) {
					//System.out.print(ioe);
					//ioe.printStackTrace();
				}
		} catch (IOException ioe) {
			username = "";
			password = "";
			LoginErrorLabel.setText("Error could not find user");
			//System.out.print(ioe);
			//ioe.printStackTrace();
		}
		
		else {
			System.out.println("Failed to valid");
			LoginErrorLabel.setText("Error User Not Found");
		}


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
		
		String startHour = startHourChoiceBox.getValue();
		String startMin = startMinChoiceBox.getValue();
		
		String startTask = startHour + ":" + startMin;
		
		String endHour = endHourChoiceBox.getValue();
		String endMin = endMinChoiceBox.getValue();
		
		String endTask = endHour + ":" + endMin;
		
		String task = taskName.getText();
		
		System.out.println("Task Created!");
//		System.out.println("Date: " + dateSelect.getValue() + " Task: " + taskName.getText() + " Start Time: " + startHourChoiceBox.getValue() + ":" + startMinChoiceBox.getValue() + " End Time: " + endHourChoiceBox.getValue() + ":" + endMinChoiceBox.getValue());
		
		taskList.add(startTask + "," + task + "," + endTask);
		
		System.out.println(taskList);
		

		
		
		
		
	}
	
	
	
	public void doneScheduleCreate(ActionEvent Event) throws Exception {
		
				
		Timeblock timeBlock = new Timeblock();
					
		timeBlock.createTimeblock(taskList);
		
		Parent root = FXMLLoader.load(getClass().getResource("ScheduleView.fxml"));
		
		Stage applicationStage = (Stage)doneButton.getScene().getWindow();
		
		applicationStage.setScene(new Scene(root, 600, 400));
		applicationStage.show();
	
	
	}
}
