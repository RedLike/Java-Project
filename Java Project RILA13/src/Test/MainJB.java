package Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import API.ConnectDB;
import Common.Cinema;

public class MainJB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
		System.out.println(ft.format(date));
		
		
		

		
		
		
//		Cinema cinemaTest = new Cinema("Montpelier", "Pathe Maontpelier", "140 avenue montpelier");
//		if (cinemaTest.create()) {
//			System.out.println(cinemaTest.getId());
//		} else {
//			System.out.println("Cinema ID: "+cinemaTest.getId());
//			System.out.println("erreur ajout Cinema, ID: "+cinemaTest.getId());
//		}
		
//		cinemaTest.setCity("test1");
//		cinemaTest.setAddress("test2");
//		cinemaTest.setName("test3");
//		if (cinemaTest.update()) {
//			System.out.println("update Ok");
//		} else {
//			System.out.println("update KO");
//		}
		
//		if (cinemaTest.delete()) {
//			System.out.println("Delete Ok");
//		} else {
//			System.out.println("Delete KO");
//		}

		
		
		
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
		
		
	}


}
