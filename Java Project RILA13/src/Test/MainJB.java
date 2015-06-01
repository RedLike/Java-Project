package Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import API.ConnectDB;
import API.TheMovieDB;
import Admin.LoginController;
import Common.Cinema;
import Common.Format;
import Common.Movie;

public class MainJB extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		ArrayList<Format> listFormat = new ArrayList();
//		
//		String readFormat = "SELECT id, Label, Language, Description FROM format";
//		ConnectDB db = new ConnectDB();
//		ResultSet resultat = db.ReadDB(readFormat);
//		try {
//			while (resultat.next()) {
//				Format format = new Format(resultat.getString("Label"),resultat.getString("Language"),resultat.getString("Description"));
//				format.create();
//				listFormat.add(format);
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		db.CloseDB();
//		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Movie test = new Movie("Mad Max: Fury Road", 76341, "/uuvSvLb3ntGA9B0wx2JskVDSuWi.jpg", "Mad Max","2015-05-15");
//		
//		TheMovieDB info;
//		try {
//			info = new TheMovieDB();
//			
//			System.out.println(test.getName());
//			System.out.println(test.getIdMovieDB());
//			
//			List<Movie> movies = info.Discover(listFormat);
////				info.getInfos(test, listFormat);
//			info.Insert(movies);
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		launch(args);
		
//		Date date = new Date();
//		SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
//		System.out.println(ft.format(date));
		
//		ConnectDB db = new ConnectDB();
//		String sql = "SELECT * FROM Cinema";
//		ResultSet resultat = db.ReadDB(sql);
//		ArrayList<Cinema> cinemaList = new ArrayList();
//		try {
//			if (resultat.next()) {
//				loginForm.Hide();
//				do {
//					int id = resultat.getInt("Id");
//					String city = resultat.getString("City");
//					String name = resultat.getString("name");
//					String address = resultat.getString("address");
//					Cinema cinema = new Cinema(id, city, name, address);
//					cinemaList.add(cinema);
//					
//					System.out.println(resultat.getString("City"));
//				} while(resultat.next());
//			} else {
//				loginForm.Hide();
//			}
//			
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		db.CloseDB();

		
	}
	
	
	
	
	@Override
	public void start(Stage primaryStage){
		// juste montrer l'enchaînement des fenêtre, j'ai rajouté une fenêtre de login
		LoginController loginForm = new LoginController(primaryStage);
		
//		loginForm.Hide();
		
		
	}


}
