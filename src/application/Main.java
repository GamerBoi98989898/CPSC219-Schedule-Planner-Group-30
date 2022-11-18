package application;
	
import java.io.FileInputStream;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/LoginView.fxml"));
			LoginController controller = (LoginController)loader.getController();
			controller.applicationStage = primaryStage;
			
			// Window Size
			Scene scene = new Scene(root, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Schedule Planner App");
			primaryStage.show();
			
		} 
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

