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
	
	//CRUD
	@Override
	public boolean create() {
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
				setId(result0.getInt("Id"));
				System.out.println("Terminal already exist");
				res = false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}		
		return res;
	}


	@Override
	public boolean update() {
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
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM UserAdmin WHERE Id='"+this.id+"'";
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
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set Integer value of the id attribute
	 * @param id
	 */
	private void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get Integer value of the number attribute
	 * @return number
	 */
	public Integer getNumber() {
		return this.number;
	}

	/**
	 * Set Integer value of the number attribute
	 * @param number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Get Cinema object of the cinema attribute
	 * @return cinema
	 */
	public Cinema getCinema() {
		return this.cinema;
	}

	/**
	 * Set Cinema object of the cinema attribute
	 * @param cinema
	 */
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
}
