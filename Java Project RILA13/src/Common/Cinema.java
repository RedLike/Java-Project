package Common;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;
import Interface.ADM;

public class Cinema implements ADM {

	private Integer id;
	private String city;
	private String name;
	private String address;
	
	
	//CONSTRUCTOR
	public Cinema() {
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


	
	//GETTERS & SETTERS
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public int getId() {
		return id;
	}
	
	private void setId(Integer Id) {
		this.id = Id;
	}

	
	//CRUD
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
				setId(result1.getInt("Id"));
				res = true;
				
			} else {
//				result0.next();
				setId(result0.getInt("Id"));
				System.out.println("Cinéma already exist");
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
	public boolean update() {
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
//			db.WriteDB(sqlDelete0);
			if (db.WriteDB(sqlDelete0) != null) {
				res = true;
			} else {
				res = false;
			}
			
		} finally {
			db.CloseDB();
		}
		
		return res;
	}

	
		
	
	
}
