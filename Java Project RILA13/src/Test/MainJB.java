package Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;
import Common.Cinema;

public class MainJB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConnectDB db = new ConnectDB();
		String sql = "SELECT * FROM Cinema";
		ResultSet resultat = db.ReadDB(sql);
		try {
			while (resultat.next()) {
				System.out.println(resultat.getString("City"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		db.CloseDB();
		
		
		Cinema cinemaTest = new Cinema("Montpelier", "Pathe Maontpelier", "140 avenue montpelier");
		if (cinemaTest.create()) {
//			System.out.println(cinemaTest.getId());
		} else {
//			System.out.println("Cinema ID: "+cinemaTest.getId());
			System.out.println("erreur ajout Cinema, ID: "+cinemaTest.getId());
		}
		
		
//		ConnectDB db0 = new ConnectDB();
//		String sql0 = "INSERT INTO Cinema(City, Name, Address) VALUES('Montpelier', 'Pathe Maontpelier', '140 avenue montpelier')";
//		db0.WriteDB(sql0);
//		
//		db0.CloseDB();
		
		
		
		
	}


}
