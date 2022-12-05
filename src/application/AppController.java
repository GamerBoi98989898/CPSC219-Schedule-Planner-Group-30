package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
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
 * @author Jose Lorenzo Jacobe and Connor Ell
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
	TextField usernameTextField;

	@FXML
	PasswordField passwordTextField;

	@FXML
	Label LoginErrorLabel;
	@FXML
	Label RegisterErrorLabel;

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
	Button showcomp = new Button();
	@FXML
	Button compareButton = new Button();
	@FXML
	TextField compareNameTField = new TextField();
	@FXML
	Button finishViewingFreetime = new Button();
	@FXML
	Label FreetimeErrorLabel = new Label();
	@FXML
	Label createErrorLabel = new Label();

	/**
	 *
	 * Check if user already exists and if credentials are correct, proceed to show their current schedule
	 *
	 * @param
	 * @throws
	 */

	public void userLogin(ActionEvent Event) {
		LoginErrorLabel.setText("");
		if (usernameTextField.getText() != null && passwordTextField.getText() != null) {
			User toValidate = new User();
			String username = usernameTextField.getText();
			String password = passwordTextField.getText();
			try {
				if (toValidate.validateUser(username, password)) {
					User currentUser = new User(username);
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleView.fxml"));
						Stage applicationStage = (Stage)loginButton.getScene().getWindow();
						Parent root = loader.load();
						applicationStage.setUserData(currentUser);
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


					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			} catch (IOException ioe) {
				username = "";
				password = "";
				ioe.printStackTrace();
			} LoginErrorLabel.setText("Error could not find user. Check Username and Password");
		} else {LoginErrorLabel.setText("Please enter a username and password");}
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
		// If it does not exist, the registration will be completed and the user will be brought back to the
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

				applicationStage.setScene(new Scene(root, 800, 600));
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
	 * Going into this scene will clear the user's entire existing schedule
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
			User currentUser = (User) applicationStage.getUserData();

			// When the user creates a new schedule, it clears all the existing schedule prior to entering the create schedule scene
			currentUser.getSuntimetable().clear();
			currentUser.getMontimetable().clear();
			currentUser.getTuetimetable().clear();
			currentUser.getWedtimetable().clear();
			currentUser.getThutimetable().clear();
			currentUser.getFritimetable().clear();
			currentUser.getSattimetable().clear();

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

		// Combines the hour and minutes with a colon to use when converting it to time using LocalTime
		String startTask = startHour + ":" + startMin;

		String endHour = endHourChoiceBox.getValue();
		String endMin = endMinChoiceBox.getValue();

		String endTask = endHour + ":" + endMin;

		// Gets the text store in the task name TextField
		String task = taskName.getText();

		createErrorLabel.setText("");

		// Get current user's data for adding schedule
		Stage stage = (Stage)createTaskButton.getScene().getWindow();
		User currentUser = (User) stage.getUserData();


		try {

			// Checks if ChoiceBox is null and change the label to let user know to enter a day
			if (dayChoiceBox.getValue() != null) {

				// Checks if the TextField is empty or null and change the label to let user know to enter a task name
				if (taskName.getText() == null || taskName.getText() == "") {

					createErrorLabel.setText("Please enter a task name");

				}

				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Sunday") == true) {

					// Get the User's timetable and assign to the day's task list
					sunTaskList = currentUser.getSuntimetable();

					// Sets the time selected in the in the ChoiceBoxes using Java LocalTime
					sunTimeblock.setStart(LocalTime.parse(startTask));
					sunTimeblock.setEnd(LocalTime.parse(endTask));

					// Validate overlapping times to avoid user entering the same task multiple times
					if (sunTimeblock.overlappingTime(sunTaskList) == false) {

						// If there are no overlaps, allow task to be added to the task list of the day
						sunTaskList.add(startTask + "," + task + "," + endTask);

						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(sunTaskList);

						// Uses the day's task list to set the user's schedule in the Timeblock class
						currentUser.setSuntimeblocks(sunTimeblock.createTimeblocks(sunTaskList));
						currentUser = new User(currentUser);

						sunTimeblock.setNamelabel(task);

						sunTimeblock.createTimeblocks(sunTaskList);


						// Displays the contents of the Timeblocks into the TextArea for user to see
						sunTextArea.setText(timeblockToDisplay(currentUser.getSuntimeblocks()));

					}

					else {
						// If an overlap is detected, set the error message
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");

					}

				}
				
				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Monday") == true) {

					// Get the User's timetable and assign to the day's task list
					monTaskList = currentUser.getMontimetable();

					// Sets the time selected in the in the ChoiceBoxes using Java LocalTime
					monTimeblock.setStart(LocalTime.parse(startTask));
					monTimeblock.setEnd(LocalTime.parse(endTask));

					// Validate overlapping times to avoid user entering the same task multiple times
					if (monTimeblock.overlappingTime(monTaskList) == false) {

						// If there are no overlaps, allow task to be added to the task list of the day
						monTaskList.add(startTask + "," + task + "," + endTask);

						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(monTaskList);

						// Uses the day's task list to set the user's schedule in the Timeblock class
						currentUser.setMontimeblocks(monTimeblock.createTimeblocks(monTaskList));
						currentUser = new User(currentUser);

						monTimeblock.setNamelabel(task);

						monTimeblock.createTimeblocks(monTaskList);

						// Displays the contents of the Timeblocks into the TextArea for user to see
						monTextArea.setText(timeblockToDisplay(currentUser.getMontimeblocks()));

				}

					else {
						
						// If an overlap is detected, set the error message
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");

					}
				}

				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Tuesday") == true) {

					// Get the User's timetable and assign to the day's task list
					tueTaskList = currentUser.getTuetimetable();

					// Sets the time selected in the in the ChoiceBoxes using Java LocalTime
					tueTimeblock.setStart(LocalTime.parse(startTask));
					tueTimeblock.setEnd(LocalTime.parse(endTask));

					// Validate overlapping times to avoid user entering the same task multiple times
					if (tueTimeblock.overlappingTime(tueTaskList) == false) {

						// If there are no overlaps, allow task to be added to the task list of the day
						tueTaskList.add(startTask + "," + task + "," + endTask);

						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(tueTaskList);

						// Uses the day's task list to set the user's schedule in the Timeblock class
						currentUser.setTuetimeblocks(tueTimeblock.createTimeblocks(tueTaskList));
						currentUser = new User(currentUser);

						tueTimeblock.setNamelabel(task);

						tueTimeblock.createTimeblocks(tueTaskList);

						// Displays the contents of the Timeblocks into the TextArea for user to see
						tueTextArea.setText(timeblockToDisplay(currentUser.getTuetimeblocks()));

				}

					else {

						// If an overlap is detected, set the error message
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");

						}
				}


				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Wednesday") == true) {

					// Get the User's timetable and assign to the day's task list
					wedTaskList = currentUser.getWedtimetable();

					// Sets the time selected in the in the ChoiceBoxes using Java LocalTime
					wedTimeblock.setStart(LocalTime.parse(startTask));
					wedTimeblock.setEnd(LocalTime.parse(endTask));

					// Validate overlapping times to avoid user entering the same task multiple times
					if (wedTimeblock.overlappingTime(wedTaskList) == false) {

						// If there are no overlaps, allow task to be added to the task list of the day
						wedTaskList.add(startTask + "," + task + "," + endTask);

						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(wedTaskList);

						// Uses the day's task list to set the user's schedule in the Timeblock class
						currentUser.setWedtimeblocks(wedTimeblock.createTimeblocks(wedTaskList));
						currentUser = new User(currentUser);

						wedTimeblock.setNamelabel(task);

						wedTimeblock.createTimeblocks(wedTaskList);

						// Displays the contents of the Timeblocks into the TextArea for user to see
						wedTextArea.setText(timeblockToDisplay(currentUser.getWedtimeblocks()));

				}

					else {
						
						// If an overlap is detected, set the error message
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");

						}
				}

				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Thursday") == true) {

					// Get the User's timetable and assign to the day's task list
					thuTaskList = currentUser.getThutimetable();

					// Sets the time selected in the in the ChoiceBoxes using Java LocalTime
					thuTimeblock.setStart(LocalTime.parse(startTask));
					thuTimeblock.setEnd(LocalTime.parse(endTask));

					// Validate overlapping times to avoid user entering the same task multiple times
					if (thuTimeblock.overlappingTime(thuTaskList) == false) {

						// If there are no overlaps, allow task to be added to the task list of the day
						thuTaskList.add(startTask + "," + task + "," + endTask);

						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(thuTaskList);

						// Uses the day's task list to set the user's schedule in the Timeblock class
						currentUser.setThutimeblocks(thuTimeblock.createTimeblocks(thuTaskList));
						currentUser = new User(currentUser);

						thuTimeblock.setNamelabel(task);

						thuTimeblock.createTimeblocks(thuTaskList);

						// Displays the contents of the Timeblocks into the TextArea for user to see
						thuTextArea.setText(timeblockToDisplay(currentUser.getThutimeblocks()));

				}

					else {

						// If an overlap is detected, set the error message
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");

						}
				}

				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Friday") == true) {

					// Get the User's timetable and assign to the day's task list
					friTaskList = currentUser.getFritimetable();

					// Sets the time selected in the in the ChoiceBoxes using Java LocalTime
					friTimeblock.setStart(LocalTime.parse(startTask));
					friTimeblock.setEnd(LocalTime.parse(endTask));

					// Validate overlapping times to avoid user entering the same task multiple times
					if (friTimeblock.overlappingTime(friTaskList) == false) {

						// If there are no overlaps, allow task to be added to the task list of the day
						friTaskList.add(startTask + "," + task + "," + endTask);


						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(friTaskList);

						// Uses the day's task list to set the user's schedule in the Timeblock class
						currentUser.setFritimeblocks(friTimeblock.createTimeblocks(friTaskList));
						currentUser = new User(currentUser);

						friTimeblock.setNamelabel(task);

						friTimeblock.createTimeblocks(friTaskList);

						// Displays the contents of the Timeblocks into the TextArea for user to see
						friTextArea.setText(timeblockToDisplay(currentUser.getFritimeblocks()));

				}

					else {

						// If an overlap is detected, set the error message
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");

						}
				}

				// Checks which day the user has selected and add tasks to the ArrayList and Timeblock for the day
				else if (dayChoiceBox.getValue().equals("Saturday") == true) {

					// Get the User's timetable and assign to the day's task list
					satTaskList = currentUser.getSattimetable();

					// Sets the time selected in the in the ChoiceBoxes using Java LocalTime
					satTimeblock.setStart(LocalTime.parse(startTask));
					satTimeblock.setEnd(LocalTime.parse(endTask));

					// Validate overlapping times to avoid user entering the same task multiple times
					if (satTimeblock.overlappingTime(satTaskList) == false) {

						// If there are no overlaps, allow task to be added to the task list of the day
						satTaskList.add(startTask + "," + task + "," + endTask);

						// sorts the list of tasks in the Text Area and into the saved text file
						Collections.sort(satTaskList);

						// Uses the day's task list to set the user's schedule in the Timeblock class
						currentUser.setSattimeblocks(satTimeblock.createTimeblocks(satTaskList));
						currentUser = new User(currentUser);

						satTimeblock.setNamelabel(task);

						satTimeblock.createTimeblocks(satTaskList);

						// Displays the contents of the Timeblocks into the TextArea for user to see
						satTextArea.setText(timeblockToDisplay(currentUser.getSattimeblocks()));

				}

					else {

						// If an overlap is detected, set the error message
						createErrorLabel.setText("There is a conflict with \nthe Start and End times");

						}
				}
			}

			else {
				
				// If a day is not selected in the ChoiceBox, set message to let user know to select a day
				createErrorLabel.setText("Please select a day");

				}
		} // end of try block

		catch(Exception e) {

			e.printStackTrace();

		}

	}

	
	/**
	 * 
	 * Converts the values in the Timeblock objects into string and displays them in the create schedule scene to create a stacking effect
	 * on the text
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

			// Get current user's data for saving into the text file
			Stage stage = (Stage)doneButton.getScene().getWindow();
			User currentUser = (User) stage.getUserData();

			// Saves the user's schedule into the text file
			currentUser.saveToFile(currentUser.getUsername());

			// Switch back to previous schedule view scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleView.fxml"));
			Stage applicationStage = (Stage)doneButton.getScene().getWindow();
			Parent root = loader.load();
			AppController controller = loader.getController();

			// Set the TextArea to the user's schedule for each day
			controller.ScheduleViewSun.setText(timeblockToDisplay(currentUser.getSuntimeblocks()));
			controller.ScheduleViewMon.setText(timeblockToDisplay(currentUser.getMontimeblocks()));
			controller.ScheduleViewTue.setText(timeblockToDisplay(currentUser.getTuetimeblocks()));
			controller.ScheduleViewWed.setText(timeblockToDisplay(currentUser.getWedtimeblocks()));
			controller.ScheduleViewThu.setText(timeblockToDisplay(currentUser.getThutimeblocks()));
			controller.ScheduleViewFri.setText(timeblockToDisplay(currentUser.getFritimeblocks()));
			controller.ScheduleViewSat.setText(timeblockToDisplay(currentUser.getSattimeblocks()));
			applicationStage.setScene(new Scene(root, 800, 600));
			applicationStage.show();

		}

		catch(IOException e) {
			e.printStackTrace();


		}


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
			ioe.printStackTrace();
		}
	}
	public void compareName(ActionEvent Event) {
		if (compareNameTField != null) {
			try {
				User.validateUser(compareNameTField.getText());
				String tocomparestr = compareNameTField.getText();
				User tocompareusr = new User(tocomparestr);
				Stage applicationStage = (Stage) compareButton.getScene().getWindow();
				User currentUser = (User) applicationStage.getUserData();
				Comparison comp = new Comparison(currentUser, tocompareusr);
				ArrayList<Timeblock> list = comp.getSunfreetimelist();
				FreeViewSun.setText(timeblockToDisplay(list));
				list = comp.getMonfreetimelist();
				FreeViewMon.setText(timeblockToDisplay(list));
				list = comp.getTuefreetimelist();
				FreeViewTue.setText(timeblockToDisplay(list));
				list = comp.getWedfreetimelist();
				FreeViewWed.setText(timeblockToDisplay(list));
				list = comp.getThufreetimelist();
				FreeViewThu.setText(timeblockToDisplay(list));
				list = comp.getFrifreetimelist();
				FreeViewFri.setText(timeblockToDisplay(list));
				list = comp.getSatfreetimelist();
				FreeViewSat.setText(timeblockToDisplay(list));
				FreetimeErrorLabel.setText("");
			} catch (IOException e) {
				FreetimeErrorLabel.setText("Error could not find User");

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

			User currentUser = (User) applicationStage.getUserData();
			controller.ScheduleViewSun.setText(timeblockToDisplay(currentUser.getSuntimeblocks()));
			controller.ScheduleViewMon.setText(timeblockToDisplay(currentUser.getMontimeblocks()));
			controller.ScheduleViewTue.setText(timeblockToDisplay(currentUser.getTuetimeblocks()));
			controller.ScheduleViewWed.setText(timeblockToDisplay(currentUser.getWedtimeblocks()));
			controller.ScheduleViewThu.setText(timeblockToDisplay(currentUser.getThutimeblocks()));
			controller.ScheduleViewFri.setText(timeblockToDisplay(currentUser.getFritimeblocks()));
			controller.ScheduleViewSat.setText(timeblockToDisplay(currentUser.getSattimeblocks()));


		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public String displayTable(int i) {
		Stage stage = (Stage)applicationStage.getScene().getWindow();
		User currentUser = (User) stage.getUserData();
		String text = "";

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
}