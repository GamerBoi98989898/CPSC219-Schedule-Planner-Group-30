 package application;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private ChoiceBox<Integer> tasksPlannedChoiceBox;
	
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
			
			Scene registerScene = new Scene(root, 600, 400);
			applicationStage.setScene(registerScene);
			
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 
	 * Create new user's schedule after they have created their username and password
	 * 
	 * @param event
	 */
	public void registerSchedule(ActionEvent event) {
		
		
		
		try {
			
			FXMLLoader registerSchedulePage = new FXMLLoader();
			VBox root = registerSchedulePage.load(new FileInputStream("src/application/CreateScheduleView.fxml"));
			
			Scene registerScheduleScene = new Scene(root, 600, 400);
			
			applicationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			applicationStage.setScene(registerScheduleScene);
			applicationStage.show();
			
			
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
			
		}
		

	}
	public void createSchedule(ActionEvent event) {
		int rowsOfPlannedTasks = tasksPlannedChoiceBox.getValue();
		int tasksRows = 0;
		
		VBox scheduledTasksContainer = new VBox();
		
		ArrayList<TextField> taskPlannedTextFields = new ArrayList<TextField>();
    	while(tasksRows<rowsOfPlannedTasks){
    	
    	HBox tasksContainer = new HBox();
    	Label taskName = new Label("Task Planned");
    	TextField plansTextField = new TextField();
    	taskPlannedTextFields.add(plansTextField);
    	tasksContainer.getChildren().add(taskName);
    	tasksContainer.getChildren().addAll(taskPlannedTextFields);
    	
    	tasksRows++;
    	
    	scheduledTasksContainer.getChildren().add(tasksContainer);
    	}
    	Scene tasksPlanned = new Scene(scheduledTasksContainer);
    	applicationStage.setScene(tasksPlanned);
    	
		
	}
	
	

	}

