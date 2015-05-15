package Common;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;
import Interface.ADM;

public class Cinema implements ADM {

	public Integer id;
	public String city;
	public String name;
	public String address;
	
	
	private Cinema() {
		super();
	}
	
	public Cinema(String city, String name, String address) {
		this();
		this.id = null;
		this.city = city;
		this.name = name;
		this.address = address;
	}
	
	public Cinema(int id, String city, String name, String address) {
		this(city, name, address);
		this.id = id;
	}


	public String getCity() {
		return city;
	}
	
	public boolean setCity(String city) {
		this.city = city;
		return modify();
	}


	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return modify();
	}


	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		this.address = address;
		return modify();
	}


	public int getId() {
		return id;
	}
	
	private void setId(Integer Id) {
		this.id = Id;
	}

	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, City, Name, Address FROM Cinema "
							+ "WHERE City='"+this.city+"' AND Name='"+this.name+"' AND Address='"+this.address+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO Cinema(City, Name, Address) VALUES('"+this.city+"', '"+this.name+"', '"+this.address+"')";
				System.out.println(sqlInsert0);
				db.WriteDB(sqlInsert0);
				
				String sqlRead1 = "SELECT LAST_INSERT_ID()";
				ResultSet result1 = db.ReadDB(sqlRead1);
				
				result1.next();
				setId(result1.getInt(1));
				res = true;
				
			} else {
//				result0.next();
				setId(result0.getInt(1));
				System.out.println("Cinéma déjà existant");
				res = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
		
		return res;
	}


	@Override
	public boolean modify() {
		// TODO Auto-generated method stub
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE Cinema "
					+ "SET City='"+this.city+"', Name='"+this.name+"', Address='"+this.address+"'"
					+ "WHERE Id='"+this.id+"'";
			if (db.WriteDB(sqlUpdate0) != null) {
				res = true;
			} else {
				res = false;
			}
			
			
		} finally {
			db.CloseDB();
		}
		
		return res;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM Cinema WHERE Id='"+this.id+"'";
			db.WriteDB(sqlDelete0);
			
			res = true;
		} finally {
			db.CloseDB();
		}
		
		return res;
	}

	
		
	
	
}
