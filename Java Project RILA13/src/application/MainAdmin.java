package application;

import Admin.LoginForm;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainAdmin extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		launch(args);

	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// juste montrer l'encha�nement des fen�tre, j'ai rajout� une fen�tre de login
		LoginForm loginForm = new LoginForm();
		loginForm.loadScreen(primaryStage);
	}

}
