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

	private Room() {
		super();
	}
	
	public Room(int number, int chair, Cinema cinema) {
		this();
		this.number = number;
		this.chair = chair;
		this.cinema = cinema;
	}

	
	//CRUD
	public boolean create() {
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Number, Chair FROM Room  "
							+ "WHERE Id_Cinema='"+this.cinema.getId()+"' AND Number='"+this.number+"' AND Chair='"+this.chair+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO Room(Id_Cinema, Number, Chair) VALUES('"+this.cinema.getId()+"','"+this.number+"', '"+this.chair+"')";
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
	
	public boolean update(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE Room "
					+ "SET Number='"+this.number+"' AND Chair='"+this.chair+"' AND Id_Cinema='"+this.cinema.getId()+"'"
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
	
	public boolean delete(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM Room WHERE Id='"+this.id+"'";
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

	
	//GETTERS & SETTERS
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getChair() {
		return this.chair;
	}

	public void setChair(int chair) {
		this.chair = chair;
	}
	
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
}
