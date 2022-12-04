package application;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
		LoginErrorLabel.setText("");
		if (usernameTextField.getText() != null && passwordTextField.getText() != null) {
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
		} else {LoginErrorLabel.setText("Please enter a username and password");}
	}


	public void TestButton(ActionEvent Event) {
		Stage stage = (Stage)TestButton.getScene().getWindow();
		User x = (User) stage.getUserData();
		User y = new User(x);
		System.out.println(y.getUsername());
		TestArea.setText(y.getUsername());

		User test = new User("guy");
		test.createFreeTimeArrays();
		User comp1 = new User("comp1");
		User comp2 = new User("comp2");
		comp1.createFreeTimeArrays();
		comp2.createFreeTimeArrays();
		//Comparison comp = new Comparison(comp1.getSunfreetime(), comp2.getSunfreetime());
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

				if (dayOfWeek.getDayOfWeek().toString() == "SUNDAY") {

					sunTaskList = currentUser.getSuntimetable();
					sunTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setSuntimetable(sunTaskList);

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(sunTaskList);

					currentUser.setSuntimeblocks(sunTimeblock.createTimeblocks(sunTaskList));
					currentUser = new User(currentUser);
					for (Timeblock x : currentUser.getSuntimeblocks()) {
						text = text.concat(x.toString()+ "\n");
						sunTextArea.setText(text);
					}
				}

				else if (dayOfWeek.getDayOfWeek().toString() == "MONDAY") {

					monTaskList = currentUser.getMontimetable();
					monTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setMontimetable(monTaskList);

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(monTaskList);

					currentUser.setMontimeblocks(monTimeblock.createTimeblocks(monTaskList));
					currentUser = new User(currentUser);
					for (Timeblock x : currentUser.getMontimeblocks()) {
						monTextArea.setText(x.toString());
					}
				}

				else if (dayOfWeek.getDayOfWeek().toString() == "TUESDAY") {

					tueTaskList = currentUser.getTuetimetable();
					tueTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setTuetimetable(tueTaskList);

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(tueTaskList);

					currentUser.setTuetimeblocks(tueTimeblock.createTimeblocks(tueTaskList));
					currentUser = new User(currentUser);
					for (Timeblock x : currentUser.getTuetimeblocks()) {
						tueTextArea.setText(x.toString());
					}
					//tueTextArea.setText(timeblockToString(currentUser.getTuetimeblocks()));

				}

				else if (dayOfWeek.getDayOfWeek().toString() == "WEDNESDAY") {

					wedTaskList = currentUser.getWedtimetable();
					wedTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setWedtimetable(wedTaskList);

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(wedTaskList);

					currentUser.setWedtimeblocks(wedTimeblock.createTimeblocks(wedTaskList));
					currentUser = new User(currentUser);
					//currentUser.convertToTimeblock();
					for (Timeblock x : currentUser.getWedtimeblocks()) {
						wedTextArea.setText(x.toString());
					}
					//wedTextArea.setText(timeblockToString(currentUser.getWedtimeblocks()));

				}


				else if (dayOfWeek.getDayOfWeek().toString() == "THURSDAY") {

					thuTaskList = currentUser.getThutimetable();
					thuTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setThutimetable(thuTaskList);

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(thuTaskList);

					currentUser.setThutimeblocks(thuTimeblock.createTimeblocks(thuTaskList));
					currentUser = new User(currentUser);
					//currentUser.convertToTimeblock();
					for (Timeblock x : currentUser.getThutimeblocks()) {
						thuTextArea.setText(x.toString());
					}
					//thuTextArea.setText(timeblockToString(currentUser.getThutimeblocks()));

				}


				else if (dayOfWeek.getDayOfWeek().toString() == "FRIDAY") {

					friTaskList = currentUser.getFritimetable();
					friTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setFritimetable(friTaskList);

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(friTaskList);

					currentUser.setFritimeblocks(friTimeblock.createTimeblocks(friTaskList));
					currentUser = new User(currentUser);
					//currentUser.convertToTimeblock();
					for (Timeblock x : currentUser.getFritimeblocks()) {
						friTextArea.setText(x.toString());
					}
					//friTextArea.setText(timeblockToString(currentUser.getFritimeblocks()));

				}

				else if (dayOfWeek.getDayOfWeek().toString() == "SATURDAY") {

					satTaskList = currentUser.getSattimetable();
					satTaskList.add(startTask + "," + task + "," + endTask);
					currentUser.setSattimetable(satTaskList);

					// sorts the list of tasks in the Text Area and into the saved text file
					Collections.sort(satTaskList);

					currentUser.setSattimeblocks(satTimeblock.createTimeblocks(satTaskList));
					currentUser = new User(currentUser);
					//currentUser.convertToTimeblock();
					for (Timeblock x : currentUser.getSattimeblocks()) {
						satTextArea.setText(x.toString());
					}
					//satTextArea.setText(timeblockToString(currentUser.getSattimeblocks()));

				}
			}

			else {

				System.out.println("Day not found");

				}

		}

		catch(DateTimeParseException dtpe) {

			dtpe.printStackTrace();

		}

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
		//Stage stage = (Stage)applicationStage.getScene().getWindow();
		//User u = (User) stage.getUserData();
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