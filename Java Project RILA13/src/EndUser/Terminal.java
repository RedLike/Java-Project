package EndUser;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;
import Common.Cinema;
import Interface.ADM;

public class Terminal implements ADM {

	private Integer id;
	private Integer number;
	private Cinema cinema;
	
	
	//CONSTRUCTORS
	private Terminal() {
		super();
	}

	public Terminal(Integer number, Cinema cinema) {
		this();
		this.number = number;
		this.cinema = cinema;
	}

	public Terminal(Integer id, Integer number,  Cinema cinema) {
		this(number, cinema);
		this.id = id;
	}

	
	
	//GETTERS & SETTERS
	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	
	
	//CRUD
	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Number, Id_Cinema FROM Terminal "
							+ "WHERE Number='"+this.number+"' AND Id_Cinema='"+this.cinema.getId()+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO Terminal(Number, Id_Cinema) VALUES('"+this.number+"', '"+this.cinema.getId()+"')";
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
				System.out.println("Terminal already exist");
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
			String sqlUpdate0 = "UPDATE Terminal "
					+ "SET Number='"+this.number+"', Id_Cinema='"+this.cinema.getId()+"'"
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
			String sqlDelete0 = "DELETE FROM UserAdmin WHERE Id='"+this.id+"'";
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
