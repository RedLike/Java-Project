package EndUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import API.ConnectDB;
import Common.FilmShow;
import Interface.ADM;

public class Booking implements ADM{

	private Integer id;
	private Date hour;
	private Date date;
	private FilmShow filmshow;
	
	
	//CONSTRUCTORS
	private Booking() {
		super();
	}


	public Booking(Date hour, Date date, FilmShow filmshow) {
		this();
		this.hour = hour;
		this.date = date;
		this.filmshow = filmshow;
	}


	public Booking(Integer id, Date hour, Date date, FilmShow filmshow) {
		this(hour, date, filmshow);
		this.id = id;
	}


	//GETTERS & SETTERS
	public Integer getId() {
		return id;
	}


	private void setId(Integer id) {
		this.id = id;
	}


	public Date getHour() {
		return hour;
	}


	public void setHour(Date hour) {
		this.hour = hour;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public FilmShow getFilmshow() {
		return filmshow;
	}


	public void setFilmshow(FilmShow filmshow) {
		this.filmshow = filmshow;
	}

	//CRUD
		@Override
		public boolean create() {
			// TODO Auto-generated method stub
			
			boolean res = false;
			ConnectDB db = new ConnectDB();
			
			try {
				String sqlRead0 = "SELECT Id, Hour, Date, Id_FilmShow, Id_Terminal FROM Booking "
								+ "WHERE Hour='"+this.hour+"' AND Date='"+this.date+"'"
								+ "AND Id_FilmShow='"+this.filmshow.getId()+"'";
				ResultSet result0 = db.ReadDB(sqlRead0);
				
				if (!result0.next()) {
					String sqlInsert0 = "INSERT INTO Booking(Hour, Date, Id_FilmShow) "
							+ "VALUES('"+this.hour+"', '"+this.date+"', '"+this.filmshow.getId()+"')";
					System.out.println(sqlInsert0);
					db.WriteDB(sqlInsert0);
					
					String sqlRead1 = "SELECT LAST_INSERT_ID()";
					ResultSet result1 = db.ReadDB(sqlRead1);
					
					result1.next();
					setId(result1.getInt(1));
					res = true;
					
				} else {
//					result0.next();
					setId(result0.getInt(1));
					System.out.println("Booking already exist");
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
		

		@Override
		public boolean delete() {
			// TODO Auto-generated method stub
			boolean res = false;
			ConnectDB db = new ConnectDB();
			
			try {
				String sqlDelete0 = "DELETE FROM Booking WHERE Id='"+this.id+"'";
//				db.WriteDB(sqlDelete0);
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
