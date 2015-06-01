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
	
	//CONSTRUCTORS
	/**
	 * Empty Cinema constructor 
	 */
	public Cinema() {
		super();
	}
	
	/**
	 * Cinema constructor with parameters
	 * @param city
	 * @param name
	 * @param address
	 */
	public Cinema(String city, String name, String address) {
		this();
		this.id = null;
		this.city = city;
		this.name = name;
		this.address = address;
	}

	/**
	 * Cinema constructor with parameters and its db id
	 * @param id
	 * @param city
	 * @param name
	 * @param address
	 */
	public Cinema(int id, String city, String name, String address) {
		this(city, name, address);
		this.id = id;
	}
	
	
	
	
	
	
	//CRUD
	/* (non-Javadoc)
	 * @see Interface.ADM#create()
	 */
	@Override
	public boolean create() 
	{
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
				setId(result0.getInt("Id"));
				System.out.println("Cinéma already exist");
				res = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}		
		return res;
	}

	/* (non-Javadoc)
	 * @see Interface.ADM#update()
	 */
	@Override
	public boolean update() 
	{
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
	
	/*
	 * (non-Javadoc)
	 * @see Interface.ADM#delete()
	 */
	@Override
	public boolean delete() 
	{
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM Cinema WHERE Id='"+this.id+"'";
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

	//GETTERS & SETTERS
	
	/** 
	 * Get integer value of the id attribute
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	
	/** 
	 * Set id attribute value
	 * @param Id
	 */
	private void setId(Integer Id) {
		this.id = Id;
	}	
	
	/** 
	 * Get String value of the city attribute
	 * @return city
	 */
	public String getCity() {
		return this.city;
	}
	
	/** 
	 * Set city attribute value
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 
	 *  Get String value of the name attribute
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/** 
	 * Set name attribute value
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * Get String value of the address attribute
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/** 
	 * Set address attribute value
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
