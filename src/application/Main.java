package application;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(new FileInputStream("src/application/LoginView.fxml"));
		AppController controller = (AppController)loader.getController();
		controller.applicationStage = primaryStage;
		primaryStage.setTitle("Schedule Planner App");
		primaryStage.setScene(new Scene(root, 800, 600));
		
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
