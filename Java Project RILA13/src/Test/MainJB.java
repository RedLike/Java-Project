package Test;

import API.ConnectDB;

public class MainJB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConnectDB db = new ConnectDB();
		
		String sql = "SELECT * FROM JMJB_Cinema";
		
		db.ReadDB(sql);
		
		db.CloseDB();
	}


}
