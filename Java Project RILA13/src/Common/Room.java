package Common;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;
import Interface.ADM;

public class Room implements ADM{
	
	private int id;
	private int number;
	private int chair;
	private Cinema cinema;

	/**
	 * Empty Room constructor 
	 */
	public Room() {
		super();
	}
	
	/**
	 * Room constructor with parameters
	 * @param number
	 * @param chair
	 * @param cinema
	 */
	public Room(int number, int chair, Cinema cinema) {
		this();
		this.number = number;
		this.chair = chair;
		this.cinema = cinema;
	}
	
	/**
	 * Room constructor with parameters and its db id
	 * @param id
	 * @param number
	 * @param chair
	 * @param cinema
	 */
	public Room(int id, int number, int chair, Cinema cinema) {
		this(number, chair, cinema);
		this.id = id;
	}

	
	//CRUD
	/*
	 * (non-Javadoc)
	 * @see Interface.ADM#create()
	 */
	public boolean create() {
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Number, Chair, Id_Cinema FROM Room  "
							+ "WHERE Id_Cinema='"+this.cinema.getId()+"' AND Number='"+this.number+"' AND Chair='"+this.chair+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO Room(Id_Cinema, Number, Chair) VALUES('"+this.cinema.getId()+"','"+this.number+"', '"+this.chair+"')";
				System.out.println(sqlInsert0);
				db.WriteDB(sqlInsert0);
				
				String sqlRead1 = "SELECT LAST_INSERT_ID()";
				ResultSet result1 = db.ReadDB(sqlRead1);
				
				result1.next();
				setId(result1.getInt(1));
				res = true;
				
			} else {
				setId(result0.getInt("Id"));
				System.out.println("Room already exist");
				res = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
		
		return res;
	}
	
	/**
	 *  Return a boolean that is false if the Room don't exist in the database or if the request doesn't succeed
	 * @return res
	 */
	public boolean read(){
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Id_Cinema, Number, Chair FROM Room  "
							+ "WHERE Id_Cinema='"+this.cinema.getId()+"' AND Number='"+this.number+"' AND Chair='"+this.chair+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				System.out.println("Room already exist");
			}
			else
			{
				System.out.println(result0.getString(0));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
		
		return res;
	}
	
	/*
	 * (non-Javadoc)
	 * @see Interface.ADM#update()
	 */
	public boolean update(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE Room "
					+ "SET Number='"+this.number+"', Chair='"+this.chair+"', Id_Cinema='"+this.cinema.getId()+"'"
					+ "WHERE Id='"+this.id+"'";
			System.out.println(sqlUpdate0);
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
	public boolean delete(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM Room WHERE Id='"+this.id+"'";
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
	 * Get Integer value of the id attribute
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set Integer value of the id attribute
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get Integer value of the number attribute
	 * @return number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Set Integer value of the number attribute
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Get Integer value of the chair attribute
	 * @return chair
	 */
	public int getChair() {
		return this.chair;
	}

	/**
	 * Set Integer value of the chair attribute
	 * @param chair
	 */
	public void setChair(int chair) {
		this.chair = chair;
	}
	
	/**
	 * Get Cinema object of the cinema attribute
	 * @return cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * Set Cinema object of the cinema attribute
	 * @param cinema
	 */
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
}
