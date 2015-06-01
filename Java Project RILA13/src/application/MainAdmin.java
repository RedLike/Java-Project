package application;



import Admin.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainAdmin extends Application {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		launch(args);

	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		@SuppressWarnings("unused")
		LoginController loginForm = new LoginController(primaryStage);
		
	}
	
	

}
