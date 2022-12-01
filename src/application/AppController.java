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
	TextArea TestArea = new TextArea();
	@FXML
	Button showSched = new Button();
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
		User toValidate = new User();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		//System.out.println(username);
		//System.out.println(password);
		try {
			if (toValidate.validateUser(username, password)) {
				//System.out.println("VALID");
				currentUser = new User(username);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleView.fxml"));
					Stage applicationStage = (Stage)loginButton.getScene().getWindow();
					Parent root = loader.load();
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

	public void showSched(ActionEvent Event) {
		System.out.println(currentUser.getUsername());
		TestArea.setText(currentUser.getUsername());
		
		// Show schedule upon button press after create schedule scene
		ScheduleViewSun.setText(displayTable(0));
		ScheduleViewMon.setText(displayTable(1));
		ScheduleViewTue.setText(displayTable(2));
		ScheduleViewWed.setText(displayTable(3));
		ScheduleViewThu.setText(displayTable(4));
		ScheduleViewFri.setText(displayTable(5));
		ScheduleViewSat.setText(displayTable(6));
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateScheduleView.fxml"));
			Stage applicationStage = (Stage)createScheduleButton.getScene().getWindow();
			Parent root = loader.load();
			AppController controller = loader.getController();
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

		String startHour = startHourChoiceBox.getValue();
		String startMin = startMinChoiceBox.getValue();

		String startTask = startHour + ":" + startMin;

		String endHour = endHourChoiceBox.getValue();
		String endMin = endMinChoiceBox.getValue();

		String endTask = endHour + ":" + endMin;

		String task = taskName.getText();
		// Check if DatePicker is null, if not, allows user to create tasks to be entered into their day of week schedules
		// using the setters in the User class
		try {

			if (dateSelect.getValue() != null) {

				LocalDate dayOfWeek = LocalDate.parse(dateSelect.getValue().toString());
				String text = "";
				
				if (taskName.getText() == null || taskName.getText() == "") {
					
					createErrorLabel.setText("Please enter a task name");
					
				}
				

				else if (dayOfWeek.getDayOfWeek().toString() == "SUNDAY") {

					sunTaskList = currentUser.getSuntimetable();
					sunTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setSuntimetable(sunTaskList);

					//sunTimeblock.setNamelabel(task);
					//sunTimeblock.setStart(LocalTime.parse(startTask));
					//sunTimeblock.setEnd(LocalTime.parse(endTask));

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(sunTaskList);

					//sunTimeblock.createTimeblocks(sunTaskList);
					currentUser.convertToTimeblock();
					//currentUser.setSuntimetable(sunTaskList);
					for (Timeblock x : currentUser.getSuntimeblocks()) {
						text = text.concat(x.toString()+ "\n");
						sunTextArea.setText(text);
					}
					//sunTextArea.setText(timeblockToString(currentUser.getSuntimeblocks()));
				}

				else if (dayOfWeek.getDayOfWeek().toString() == "MONDAY") {

					monTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setMontimetable(monTaskList);

					monTimeblock.setNamelabel(task);
					monTimeblock.setStart(LocalTime.parse(startTask));
					monTimeblock.setEnd(LocalTime.parse(endTask));

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(monTaskList);

					monTimeblock.createTimeblocks(monTaskList);
					currentUser.convertToTimeblock();

					monTextArea.setText(timeblockToDisplay(currentUser.getMontimeblocks()));

				}

				else if (dayOfWeek.getDayOfWeek().toString() == "TUESDAY") {

					tueTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setTuetimetable(tueTaskList);

					tueTimeblock.setNamelabel(task);
					tueTimeblock.setStart(LocalTime.parse(startTask));
					tueTimeblock.setEnd(LocalTime.parse(endTask));

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(monTaskList);

					tueTimeblock.createTimeblocks(tueTaskList);
					currentUser.convertToTimeblock();

					tueTextArea.setText(timeblockToDisplay(currentUser.getTuetimeblocks()));

				}

				else if (dayOfWeek.getDayOfWeek().toString() == "WEDNESDAY") {

					wedTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setWedtimetable(wedTaskList);

					wedTimeblock.setNamelabel(task);
					wedTimeblock.setStart(LocalTime.parse(startTask));
					wedTimeblock.setEnd(LocalTime.parse(endTask));

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(monTaskList);

					wedTimeblock.createTimeblocks(wedTaskList);
					currentUser.convertToTimeblock();

					wedTextArea.setText(timeblockToDisplay(currentUser.getWedtimeblocks()));

				}


				else if (dayOfWeek.getDayOfWeek().toString() == "THURSDAY") {

					thuTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setThutimetable(thuTaskList);

					thuTimeblock.setNamelabel(task);
					thuTimeblock.setStart(LocalTime.parse(startTask));
					thuTimeblock.setEnd(LocalTime.parse(endTask));

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(monTaskList);

					thuTimeblock.createTimeblocks(thuTaskList);
					currentUser.convertToTimeblock();

					thuTextArea.setText(timeblockToDisplay(currentUser.getThutimeblocks()));

				}


				else if (dayOfWeek.getDayOfWeek().toString() == "FRIDAY") {

					friTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setFritimetable(friTaskList);

					friTimeblock.setNamelabel(task);
					friTimeblock.setStart(LocalTime.parse(startTask));
					friTimeblock.setEnd(LocalTime.parse(endTask));

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(monTaskList);

					friTimeblock.createTimeblocks(friTaskList);
					currentUser.convertToTimeblock();
		
					friTextArea.setText(timeblockToDisplay(currentUser.getFritimeblocks()));

				}

				else if (dayOfWeek.getDayOfWeek().toString() == "SATURDAY") {

					satTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setSattimetable(satTaskList);

					satTimeblock.setNamelabel(task);
					satTimeblock.setStart(LocalTime.parse(startTask));
					satTimeblock.setEnd(LocalTime.parse(endTask));

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(monTaskList);

					satTimeblock.createTimeblocks(satTaskList);
					currentUser.convertToTimeblock();
	
					satTextArea.setText(timeblockToDisplay(currentUser.getSattimeblocks()));

				}
				
				
				else {
					
					createErrorLabel.setText("Please Enter a Task Name");
					
				}
			}

			else {

				createErrorLabel.setText("Day not found");

				}
		}

		catch(DateTimeParseException dtpe) {

			dtpe.printStackTrace();

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
}