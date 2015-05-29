package Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;
import API.ConnectDB;
import Admin.LoginForm;
import Common.Cinema;

public class MainJB extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Date date = new Date();
//		SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
//		System.out.println(ft.format(date));
		

//		ConnectDB db = new ConnectDB();
//		String sql = "SELECT * FROM Cinema";
//		ResultSet resultat = db.ReadDB(sql);
//		try {
//			while (resultat.next()) {
//				System.out.println(resultat.getString("City"));
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		db.CloseDB();
		
		
		launch(args);
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// juste montrer l'enchaînement des fenêtre, j'ai rajouté une fenêtre de login
		LoginForm loginForm = new LoginForm();
		loginForm.loadScreen(primaryStage);
	}


}
