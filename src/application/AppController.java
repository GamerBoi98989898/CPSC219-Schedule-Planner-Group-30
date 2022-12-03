package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * 
 * This class is the controller for the entire application which contains the action for all FXML components
 * and displaying the user's schedule
 * 
 * @author Jose Lorenzo Jacobe, Connor Ell, Raisa Fairuz
 *
 */
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
	ChoiceBox<String> dayChoiceBox;

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
	TextField createUsernameTextField;

	@FXML
	PasswordField createPasswordField;

	@FXML
	TextField usernameTextField;

	@FXML
	PasswordField passwordTextField;

	@FXML
	Label LoginErrorLabel;
	@FXML
	Label RegisterErrorLabel;

	static User currentUser;


	@FXML
	TextArea sunTextArea = new TextArea();

	@FXML
	TextArea monTextArea = new TextArea();

	@FXML
	TextArea tueTextArea = new TextArea();

	@FXML
	TextArea wedTextArea = new TextArea();

	@FXML
	TextArea thuTextArea = new TextArea();

	@FXML
	TextArea friTextArea = new TextArea();

	@FXML
	TextArea satTextArea = new TextArea();

	Timeblock sunTimeblock = new Timeblock();
	Timeblock monTimeblock = new Timeblock();
	Timeblock tueTimeblock = new Timeblock();
	Timeblock wedTimeblock = new Timeblock();
	Timeblock thuTimeblock = new Timeblock();
	Timeblock friTimeblock = new Timeblock();
	Timeblock satTimeblock = new Timeblock();

	@FXML
	TextArea ScheduleViewSun = new TextArea();
	@FXML
	TextArea ScheduleViewMon = new TextArea();
	@FXML
	TextArea ScheduleViewTue = new TextArea();
	@FXML
	TextArea ScheduleViewWed = new TextArea();
	@FXML
	TextArea ScheduleViewThu = new TextArea();
	@FXML
	TextArea ScheduleViewFri = new TextArea();
	@FXML
	TextArea ScheduleViewSat = new TextArea();

	@FXML
	Button showSched = new Button();
	@FXML
	Label createErrorLabel = new Label();

	
	@FXML
	TextArea FreeViewSun = new TextArea();
	@FXML
	TextArea FreeViewMon = new TextArea();
	@FXML
	TextArea FreeViewTue = new TextArea();
	@FXML
	TextArea FreeViewWed = new TextArea();
	@FXML
	TextArea FreeViewThu = new TextArea();
	@FXML
	TextArea FreeViewFri = new TextArea();
	@FXML
	TextArea FreeViewSat = new TextArea();
	@FXML
	TextArea TestArea = new TextArea();
	@FXML
	Button TestButton = new Button();
	@FXML
	Label TestLabel = new Label();
	@FXML
	Button showcomp = new Button();
	@FXML
	Button compareButton = new Button();
	@FXML
	TextField compareNameTField = new TextField();
	@FXML
	TextArea compareFreeTimeTArea = new TextArea();
	@FXML
	Button finishViewingFreetime = new Button();
	@FXML
	Label FreetimeErrorLabel = new Label();
	
	

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
				//System.out.println("VALID");
				currentUser = new User(username);
				User tester = new User(username);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleView.fxml"));
					Stage applicationStage = (Stage)loginButton.getScene().getWindow();
					Parent root = loader.load();
					applicationStage.setUserData(tester);
					AppController controller = loader.getController();
					controller.ScheduleViewSun.setText(displayTable(0));
					controller.ScheduleViewMon.setText(displayTable(1));
					controller.ScheduleViewTue.setText(displayTable(2));
					controller.ScheduleViewWed.setText(displayTable(3));
					controller.ScheduleViewThu.setText(displayTable(4));
					controller.ScheduleViewFri.setText(displayTable(5));
					controller.ScheduleViewSat.setText(displayTable(6));
					applicationStage.setScene(new Scene(root, 800, 600));
					applicationStage.show();
					controller.TestArea.setText("AAAAAg");


				} catch (IOException ioe) {
					//System.out.print(ioe);
					//ioe.printStackTrace();
				}
			}
		} catch (IOException ioe) {
			username = "";
			password = "";
			//System.out.print(ioe);
			//ioe.printStackTrace();
		} LoginErrorLabel.setText("Error could not find user. Check Username and Password");
	}

	public void TestButton(ActionEvent Event) {
		Stage stage = (Stage)TestButton.getScene().getWindow();
		User x = (User) stage.getUserData();
		System.out.println(x.getSuntimetable().get(0));
		TestArea.setText(x.getSuntimetable().get(0));

		User test = new User("guy");
		test.createFreeTimeArrays();
		User comp1 = new User("comp1");
		User comp2 = new User("comp2");
		comp1.createFreeTimeArrays();
		comp2.createFreeTimeArrays();
		//Comparison comp = new Comparison(comp1.getSunfreetime(), comp2.getSunfreetime());
	}

	public void showSched(ActionEvent Event) {
	System.out.println(currentUser.getUsername());
	TestArea.setText(currentUser.getUsername());
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
		
		// File name would be the username
		String filename = usernameTextField.getText();
		//System.out.println(filename);
		String password = passwordTextField.getText();
		//System.out.println(password);
		
		// Find the file that matches the username and if it already exists, set label text to let user know 
		// If it does not exist, the registration will be completed and the user will be brough back to the 
		// main login scene
		File doesExist = new File("src/" +filename+ ".txt");
		if (!doesExist.isFile()) {
			try {

				// Creates a new file for the new user and stores the username and password into the text file
				PrintWriter writer= new PrintWriter(new BufferedWriter(new FileWriter("src/" +filename+ ".txt")));
				writer.println(filename);
				writer.println(password);
				writer.close();

				// Load the main login scene when registration is complete
				Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));

				Stage applicationStage = (Stage)completeRegisterButton.getScene().getWindow();

				applicationStage.setScene(new Scene(root, 600, 400));
			} catch (IOException ioe) {
				System.out.print(ioe);
				ioe.printStackTrace();
			}
		} else {
			// Set text of the label to let user know that a file for the username exists
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateScheduleView.fxml"));
			Stage applicationStage = (Stage)createScheduleButton.getScene().getWindow();
			Parent root = loader.load();
			AppController controller = loader.getController();
			
			// Displays the current schedule of the user for each day of the week
			controller.sunTextArea.setText(displayTable(0));
			controller.monTextArea.setText(displayTable(1));
			controller.tueTextArea.setText(displayTable(2));
			controller.wedTextArea.setText(displayTable(3));
			controller.thuTextArea.setText(displayTable(4));
			controller.friTextArea.setText(displayTable(5));
			controller.satTextArea.setText(displayTable(6));
			applicationStage.setScene(new Scene(root, 800, 600));
			applicationStage.show();

		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 *
	 * Button action for creating tasks in the create schedule scene
	 * Displays a summary of the tasks created using a TextArea
	 * Assigns value to name of task, start time, and end time of the Timeblock objects
	 *
	 * @param event
	 * @throws Exception
	 */
	public void createTask(ActionEvent event) {

		// The hour and minutes are in a choice box
		String startHour = startHourChoiceBox.getValue();
		String startMin = startMinChoiceBox.getValue();

		// Combines the hour and minutes with a semicolon to use when converting it to time using LocalTime
		String startTask = startHour + ":" + startMin;

		String endHour = endHourChoiceBox.getValue();
		String endMin = endMinChoiceBox.getValue();
		
		String endTask = endHour + ":" + endMin;

		// Gets the text store in the task name TextField
		String task = taskName.getText();
		
		createErrorLabel.setText("");
		
		
		try {

			// Checks if ChoiceBox is null and change the label to let user know to enter a day
			if (dayChoiceBox.getValue() != null) {

				
				String text = "";
				
				// Checks if the TextField is empty or null and change the label to let user know to enter a task name
				if (taskName.getText() == null || taskName.getText() == "") {
					
					createErrorLabel.setText("Please enter a task name");
					
				}
				
				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Sunday") == true) {
					
					sunTimeblock.setStart(LocalTime.parse(startTask));
					sunTimeblock.setEnd(LocalTime.parse(endTask));
					
					
					
					
					// Validate overlapping times to avoid user entering the same task multiple times
					if (sunTimeblock.overlappingTime(sunTaskList) == false) {
						sunTaskList.add(startTask + "," + task + "," + endTask);
						currentUser.setSuntimetable(sunTaskList);

	
						sunTimeblock.setNamelabel(task);
						

						
	
						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(sunTaskList);
						
						// Creates an object of timeblocks containing the tasks created by the user stored as String in the task list
						sunTimeblock.createTimeblocks(sunTaskList);
						currentUser.convertToTimeblock();
										
						// Displays the contents of the Timeblocks into the TextArea for user to see
						sunTextArea.setText(timeblockToDisplay(currentUser.getSuntimeblocks()));
						
					}
					
					else {
						
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");
						
					}
					
				}
					

				

				else if (dayChoiceBox.getValue().equals("Monday") == true) {
					
					monTimeblock.setStart(LocalTime.parse(startTask));
					monTimeblock.setEnd(LocalTime.parse(endTask));
					
					if (monTimeblock.overlappingTime(monTaskList) == false) {
					
						monTaskList.add(startTask + "," + task + "," + endTask);
						currentUser.setMontimetable(monTaskList);
	
						monTimeblock.setNamelabel(task);
	
	
						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(monTaskList);
	
						monTimeblock.createTimeblocks(monTaskList);
						currentUser.convertToTimeblock();
	
						monTextArea.setText(timeblockToDisplay(currentUser.getMontimeblocks()));

				}
					
					else {
						
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");
						
					}
				}

				else if (dayChoiceBox.getValue().equals("Tuesday") == true) {
					
					tueTimeblock.setStart(LocalTime.parse(startTask));
					tueTimeblock.setEnd(LocalTime.parse(endTask));
					
					if (tueTimeblock.overlappingTime(tueTaskList) == false) {

						tueTaskList.add(startTask + "," + task + "," + endTask);
						currentUser.setTuetimetable(tueTaskList);
	
						tueTimeblock.setNamelabel(task);
	
						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(monTaskList);
	
						tueTimeblock.createTimeblocks(tueTaskList);
						currentUser.convertToTimeblock();
	
						tueTextArea.setText(timeblockToDisplay(currentUser.getTuetimeblocks()));

				}
					
					else {
						
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");
						
						}
				}
			

				else if (dayChoiceBox.getValue().equals("Wednesday") == true) {
					
					wedTimeblock.setStart(LocalTime.parse(startTask));
					wedTimeblock.setEnd(LocalTime.parse(endTask));
					
					if (wedTimeblock.overlappingTime(wedTaskList) == false) {
					
						wedTaskList.add(startTask + "," + task + "," + endTask);
						currentUser.setWedtimetable(wedTaskList);
	
						wedTimeblock.setNamelabel(task);
	
	
						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(monTaskList);
	
						wedTimeblock.createTimeblocks(wedTaskList);
						currentUser.convertToTimeblock();
	
						wedTextArea.setText(timeblockToDisplay(currentUser.getWedtimeblocks()));

				}
					
					else {
						
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");
						
						}
				}


				else if (dayChoiceBox.getValue().equals("Thursday") == true) {
					
					thuTimeblock.setStart(LocalTime.parse(startTask));
					thuTimeblock.setEnd(LocalTime.parse(endTask));

					if (thuTimeblock.overlappingTime(thuTaskList) == false) {

						thuTaskList.add(startTask + "," + task + "," + endTask);
						currentUser.setThutimetable(thuTaskList);
	
						thuTimeblock.setNamelabel(task);
						
						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(monTaskList);
	
						thuTimeblock.createTimeblocks(thuTaskList);
						currentUser.convertToTimeblock();
	
						thuTextArea.setText(timeblockToDisplay(currentUser.getThutimeblocks()));

				}
					
					else {
						
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");
						
						}
				}


				else if (dayChoiceBox.getValue().equals("Friday") == true) {
					
					friTimeblock.setStart(LocalTime.parse(startTask));
					friTimeblock.setEnd(LocalTime.parse(endTask));
					
					if (friTimeblock.overlappingTime(friTaskList) == false) {
					
						friTaskList.add(startTask + "," + task + "," + endTask);
						currentUser.setFritimetable(friTaskList);
	
						friTimeblock.setNamelabel(task);
	
	
						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(monTaskList);
	
						friTimeblock.createTimeblocks(friTaskList);
						currentUser.convertToTimeblock();
			
						friTextArea.setText(timeblockToDisplay(currentUser.getFritimeblocks()));

				}
					
					else {
						
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");
						
						}
				}

				else if (dayChoiceBox.getValue().equals("Saturday") == true) {
					
					satTimeblock.setStart(LocalTime.parse(startTask));
					satTimeblock.setEnd(LocalTime.parse(endTask));

					if (satTimeblock.overlappingTime(satTaskList) == false) {
						
						satTaskList.add(startTask + "," + task + "," + endTask);
						currentUser.setSattimetable(satTaskList);
	
						satTimeblock.setNamelabel(task);
						
	
						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(monTaskList);
	
						satTimeblock.createTimeblocks(satTaskList);
						currentUser.convertToTimeblock();
		
						satTextArea.setText(timeblockToDisplay(currentUser.getSattimeblocks()));

				}
					
					else {
						
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");
						
						}
				}
			
			
			else {

				createErrorLabel.setText("Please select a day");

				}
			}
		} // end of try block

		catch(Exception e) {

			e.printStackTrace();

		}

	}
	
	
	/**
	 * 
	 * Converts the values in the Timeblock objects into string and displays them in the create schedule scene 
	 * 
	 * @param timeblockList
	 * @return
	 */
	public String timeblockToDisplay(ArrayList<Timeblock> timeblockList) {
		
		String result = "";

		for (Timeblock i : timeblockList) {
			
			result += i.toString() + "\n";
			
		}
		
		return result;
	
	}


	/**
	 * Button action for the done button in create schedule scene
	 * Saves schedule into the user's text file
	 *
	 * @param Event
	 */
	public void doneScheduleCreate(ActionEvent Event) {

		try {
			// Saves the user's schedule into the text file
			currentUser.saveToFile(currentUser.getUsername());

			// Switch back to previous schedule view scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleView.fxml"));
			Stage applicationStage = (Stage)doneButton.getScene().getWindow();
			Parent root = loader.load();
			AppController controller = loader.getController();
			
			// Set the TextArea to the user's schedule for each day
			controller.ScheduleViewSun.setText(displayTable(0));
			controller.ScheduleViewMon.setText(displayTable(1));
			controller.ScheduleViewTue.setText(displayTable(2));
			controller.ScheduleViewWed.setText(displayTable(3));
			controller.ScheduleViewThu.setText(displayTable(4));
			controller.ScheduleViewFri.setText(displayTable(5));
			controller.ScheduleViewSat.setText(displayTable(6));
			applicationStage.setScene(new Scene(root, 800, 600));
			applicationStage.show();


		}

		catch(IOException e) {
			e.printStackTrace();


		}


	}

	// Method to display the schedule of the user for each day and return the String text for the TextArea to display
	public String displayTable(int i) {
		String text = "";
		//The stupid switch staement didn't work so im doing it this way
		if (i == 0) {
			for (Timeblock x : currentUser.getSuntimeblocks()) {
				text = text.concat(x.toString() + "\n");

			}return text;
		}
		if (i == 1) {
			for (Timeblock x : currentUser.getMontimeblocks()) {
				text = text.concat(x.toString() + "\n");

			}return text;
		}
		if (i == 2) {
			for (Timeblock x : currentUser.getTuetimeblocks()) {
				text = text.concat(x.toString() + "\n");

			}return text;
		}
		if (i == 3) {
			for (Timeblock x : currentUser.getWedtimeblocks()) {
				text = text.concat(x.toString() + "\n");

			}return text;
		}
		if (i == 4) {
			for (Timeblock x : currentUser.getThutimeblocks()) {
				text = text.concat(x.toString() + "\n");

			}return text;
		}
		if (i == 5) {
			for (Timeblock x : currentUser.getFritimeblocks()) {
				text = text.concat(x.toString() + "\n");

			}return text;
		}
		if (i == 6) {
			for (Timeblock x : currentUser.getSattimeblocks()) {
				text = text.concat(x.toString() + "\n");

			}return text;
		}
		return text;
	}

	public void showcomp(ActionEvent Event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowFreeTimeView.fxml"));
			Stage applicationStage = (Stage)showcomp.getScene().getWindow();
			Parent root = loader.load();
			AppController controller = loader.getController();
			applicationStage.setScene(new Scene(root, 800, 600));
			applicationStage.show();

			User x = (User) applicationStage.getUserData();
			System.out.println(x.getUsername());


		} catch (IOException ioe) {
			//System.out.print(ioe);
			//ioe.printStackTrace();
		}
	}
	public void compareName(ActionEvent Event) {
		if(compareNameTField != null ){
			try {
				User.validateUser(compareNameTField.getText());
				String tocomparestr = compareNameTField.getText();
				User tocompareusr = new User(tocomparestr);
				Stage applicationStage = (Stage)compareButton.getScene().getWindow();
				User x = (User) applicationStage.getUserData();
				//System.out.println(x.getUsername());
				x.createFreeTimeArrays();
				tocompareusr.createFreeTimeArrays();
				currentUser.createFreeTimeArrays();
				Comparison comp = new Comparison(currentUser, tocompareusr);
				FreeViewSun.setText(comp.getSunfreetimelist().toString());
				FreeViewMon.setText(comp.getMonfreetimelist().toString());
				FreeViewTue.setText(comp.getTuefreetimelist().toString());
				FreeViewWed.setText(comp.getWedfreetimelist().toString());
				FreeViewThu.setText(comp.getThufreetimelist().toString());
				FreeViewFri.setText(comp.getFrifreetimelist().toString());
				FreeViewSat.setText(comp.getSatfreetimelist().toString());
				FreetimeErrorLabel.setText("");
			} catch (IOException e) {
				FreetimeErrorLabel.setText("Error could not find User");
				//System.out.println("User not found");
			}

		}

	}


	public void finishViewingFreetime(ActionEvent Event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleView.fxml"));
			Stage applicationStage = (Stage)finishViewingFreetime.getScene().getWindow();
			Parent root = loader.load();
			AppController controller = loader.getController();
			applicationStage.setScene(new Scene(root, 800, 600));
			applicationStage.show();
			controller.ScheduleViewSun.setText(displayTable(0));
			controller.ScheduleViewMon.setText(displayTable(1));
			controller.ScheduleViewTue.setText(displayTable(2));
			controller.ScheduleViewWed.setText(displayTable(3));
			controller.ScheduleViewThu.setText(displayTable(4));
			controller.ScheduleViewFri.setText(displayTable(5));
			controller.ScheduleViewSat.setText(displayTable(6));

			User x = (User) applicationStage.getUserData();
			//System.out.println(x.getUsername());


		} catch (IOException ioe) {
			//System.out.print(ioe);
			//ioe.printStackTrace();
		}
	}
}