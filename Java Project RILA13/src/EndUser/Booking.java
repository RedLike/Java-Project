package EndUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import API.ConnectDB;
import Common.FilmShow;
import Interface.ADM;

public class Booking implements ADM{

	private Integer id;
	private Time hour;
	private Date date;
	private FilmShow filmshow;
	
	
	//CONSTRUCTORS
	/**
	 * Empty Booking constructor 
	 */
	private Booking() {
		super();
	}

	/**
	 * Booking constructor with parameters
	 * @param hour
	 * @param date
	 * @param filmshow
	 */
	public Booking(Time hour, Date date, FilmShow filmshow) {
		this();
		this.hour = hour;
		this.date = date;
		this.filmshow = filmshow;
	}

	/**
	 * Booking constructor with parameters and its db id
	 * @param id
	 * @param hour
	 * @param date
	 * @param filmshow
	 */
	public Booking(Integer id, Time hour, Date date, FilmShow filmshow) {
		this(hour, date, filmshow);
		this.id = id;
	}

	//CRUD
	/*
	 * (non-Javadoc)
	 * @see Interface.ADM#create()
	 */
	@Override
	public boolean create() {
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
				String sqlInsert0 = "INSERT INTO Booking(Hour, Date, Id_FilmShow) VALUES('"+this.hour+"', '"+this.date+"', '"+this.filmshow.getId()+"')";
				System.out.println(sqlInsert0);
				db.WriteDB(sqlInsert0);
				
				String sqlRead1 = "SELECT LAST_INSERT_ID()";
				ResultSet result1 = db.ReadDB(sqlRead1);
				
				result1.next();
				setId(result1.getInt(1));
				res = true;				
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
	@Override
	public boolean update() {
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE Booking "
					+ "SET Hour='"+this.hour+"', Date='"+this.date+"'"
					+ ", Id_FilmShow='"+this.filmshow.getId()+"'"
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
	public boolean delete() {
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM Booking WHERE Id='"+this.id+"'";
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
		return id;
	}

	/**
	 * Set Integer value of the id attribute
	 * @param id
	 */
	private void setId(Integer id) {
		this.id = id;
	}

	/**
	 *  Get Time value of the hour attribute
	 * @return hour
	 */
	public Time getHour() {
		return hour;
	}

	/**
	 * Set Time value of the hour attribute
	 * @param hour
	 */
	public void setHour(Time hour) {
		this.hour = hour;
	}

	/**
	 *  Get Date value of the date attribute
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set Date value of the date attribute
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 *  Get FilmShow object of the filmshow attribute
	 * @return filmshow
	 */
	public FilmShow getFilmshow() {
		return filmshow;
	}


	/**
	 * Set FilmShow object of the filmshow attribute
	 * @param filmshow
	 */
	public void setFilmshow(FilmShow filmshow) {
		this.filmshow = filmshow;
	}
}
