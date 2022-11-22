package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	
	private ArrayList<String> sunTaskList = new ArrayList<String>();
	private ArrayList<String> monTaskList = new ArrayList<String>();
	private ArrayList<String> tueTaskList = new ArrayList<String>();
	private ArrayList<String> wedTaskList = new ArrayList<String>();
	private ArrayList<String> thuTaskList = new ArrayList<String>();
	private ArrayList<String> friTaskList = new ArrayList<String>();
	private ArrayList<String> satTaskList = new ArrayList<String>();
	
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
	TextField usernameTextField;
	@FXML
	PasswordField passwordTextField;
	@FXML
	Label LoginErrorLabel;
	@FXML
	Label RegisterErrorLabel;

	User currentUser = User.getUser();
	
	/**
	 * 
	 * Check if user already exists and if credentials are correct, proceed to show their current schedule
	 * 
	 * @param
	 * @throws
	 */
	
	public void userLogin(ActionEvent Event) {
		User toValidate = new User();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		//System.out.println(username);
		//System.out.println(password);
		try {
			if (toValidate.validateUser(username, password)) {
				System.out.println("VALID");
				currentUser.setUsername(username);
				currentUser.setPassword(password);
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("ScheduleView.fxml"));

					Stage applicationStage = (Stage)loginButton.getScene().getWindow();

					applicationStage.setScene(new Scene(root, 600, 400));
				} catch (IOException ioe) {
					//System.out.print(ioe);
					//ioe.printStackTrace();
				}
			}

		} catch (IOException ioe) {
			username = "";
			password = "";
			LoginErrorLabel.setText("Error could not find user");
			//System.out.print(ioe);
			//ioe.printStackTrace();
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
	
	public void completeRegister(ActionEvent event) {
		String filename = usernameTextField.getText();
		//System.out.println(filename);
		String password = passwordTextField.getText();
		//System.out.println(password);
		File doesExist = new File("src/" +filename+ ".txt");
		if (!doesExist.isFile()) { 
			try {
				
			
				PrintWriter writer= new PrintWriter(new BufferedWriter(new FileWriter("src/" +filename+ ".txt")));
				writer.println(filename);
				writer.println(password);
				writer.close();
				
				Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
				
				Stage applicationStage = (Stage)completeRegisterButton.getScene().getWindow();
				
				applicationStage.setScene(new Scene(root, 600, 400));
			} catch (IOException ioe) {
				System.out.print(ioe);
				ioe.printStackTrace();
			}
		} else {
			RegisterErrorLabel.setText("Error User already exists");
		}
	}
	
	
	/**
	 * 
	 * Brings user to a new scene to allow them to create their schedule
	 * 
	 * @param event
	 * @throws Exception 
	 */
	
	public void createSchedule(ActionEvent event)  {
		
		
		// Changes scene to allow user to create tasks for their schedule
		try {
			Parent root = FXMLLoader.load(getClass().getResource("CreateScheduleView.fxml"));

		
			Stage applicationStage = (Stage)createScheduleButton.getScene().getWindow();
			
			applicationStage.setScene(new Scene(root, 600, 400));
			
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	 

	public void createTask(ActionEvent event) throws Exception  {
		
		String startHour = startHourChoiceBox.getValue();
		String startMin = startMinChoiceBox.getValue();
		
		String startTask = startHour + ":" + startMin;
		
		String endHour = endHourChoiceBox.getValue();
		String endMin = endMinChoiceBox.getValue();
		
		String endTask = endHour + ":" + endMin;
		
		String task = taskName.getText();
		
		System.out.println("Task Created!");;
		
		LocalDate dayOfWeek = LocalDate.parse(dateSelect.getValue().toString());
		
		
		// Check if DatePicker is null, if not, allows user to create tasks to be entered into their day of week schedules
		// using the setters in the User class
		if (dateSelect.getValue() != null) {
			
			if (dayOfWeek.getDayOfWeek().toString() == "SUNDAY") {
				
				sunTaskList.add(startTask + "," + task + "," + endTask);
				currentUser.setSuntimetable(sunTaskList);
				
			}
			
			
			else if (dayOfWeek.getDayOfWeek().toString() == "MONDAY") {
				
				monTaskList.add(startTask + "," + task + "," + endTask);
				currentUser.setMontimetable(monTaskList);
				
			}
			
			
			else if (dayOfWeek.getDayOfWeek().toString() == "TUESDAY") {
				
				tueTaskList.add(startTask + "," + task + "," + endTask);
				currentUser.setTuetimetable(tueTaskList);
				
			}
			
			
			else if (dayOfWeek.getDayOfWeek().toString() == "WEDNESDAY") {
				
				wedTaskList.add(startTask + "," + task + "," + endTask);
				currentUser.setWedtimetable(wedTaskList);
				
			}
			
			
			else if (dayOfWeek.getDayOfWeek().toString() == "THURSDAY") {
				
				thuTaskList.add(startTask + "," + task + "," + endTask);
				currentUser.setThutimetable(thuTaskList);
				
			}
			
			
			else if (dayOfWeek.getDayOfWeek().toString() == "FRIDAY") {
				
				friTaskList.add(startTask + "," + task + "," + endTask);
				currentUser.setFritimetable(friTaskList);
				
			}
			
			
			else if (dayOfWeek.getDayOfWeek().toString() == "SATURDAY") {

				satTaskList.add(startTask + "," + task + "," + endTask);
				currentUser.setSattimetable(satTaskList);
				
			}
			
			else {
				
				System.out.println("Day not found");
				
			}
		}
		
	}
	
	
	
	public void doneScheduleCreate(ActionEvent Event) {
		
		try {
			// Saves the user's schedule into the text file
			currentUser.saveToFile(currentUser.getUsername());
			
			// Switch back to previous schedule view scene
			Parent root = FXMLLoader.load(getClass().getResource("ScheduleView.fxml"));
			
			Stage applicationStage = (Stage)doneButton.getScene().getWindow();
			
			applicationStage.setScene(new Scene(root, 600, 400));
			applicationStage.show();
		
		}
		
		catch(IOException e) {
			e.printStackTrace();
			
			
		}

	
	}
}