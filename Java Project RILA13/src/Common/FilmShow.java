package Common;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;

import API.ConnectDB;
import EndUser.Booking;
import Interface.ADM;


public class FilmShow implements ADM {

	private Integer id;
	private Time hour;
	private Date date;
	private boolean visibility;
	private Movie movie;
	private Room room;	
	
	//CONSTRUCTORS
	
	/**
	 * Empty FilmShow constructor 
	 */
	public FilmShow() {
		super();
	}

	/**
	 * FilmShow constructor with parameters
	 * @param hour
	 * @param date
	 * @param visibility
	 * @param movie
	 * @param room
	 */
	public FilmShow(Time hour, Date date, boolean visibility, Movie movie,
			Room room) {
		this();
		this.hour = hour;
		this.date = date;
		this.visibility = visibility;
		this.movie = movie;
		this.room = room;
	}

	/**
	 * FilmShow constructor with parameters and its db id
	 * @param id
	 * @param hour
	 * @param date
	 * @param visibility
	 * @param movie
	 * @param room
	 */
	public FilmShow(Integer id, Time hour, Date date, boolean visibility,
			Movie movie, Room room) {
		this(hour, date, visibility, movie, room);
		this.id = id;
	}	
	
	// FUNCTIONS
	/**
	 * Return an ArrayList of all the Booking objects associate to the active object FilmShow
	 * @return alListBooking
	 */
	public ArrayList<Booking> listBooking()
	 {
	  
	  ArrayList<Booking> alListBooking = new ArrayList<>();
	  
	  ConnectDB dblistbooking = new ConnectDB();
	   
	  try {
	   String sqlRead0 = "SELECT * FROM booking WHERE Id_FilmShow="+this.getId()+";";
	   ResultSet res = dblistbooking.ReadDB(sqlRead0);
	   
	   while(res.next())
	   {    
	    alListBooking.add(new Booking(res.getInt(1), res.getTime(2), res.getDate(3), this));
	   }
	   
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } finally {
	   dblistbooking.CloseDB();
	  }
	  
	  return alListBooking;
	  
	 }
	
	/**
	 * Delete in the database all the Booking objects associate to the active object FilmShow
	 * @param bookingList
	 */
	 public void deleteBooking(ArrayList<Booking> bookingList)
	 {
	  for (Booking booking : bookingList) {
	   if(booking.getFilmshow().getId()==this.getId())
	   {
	    booking.delete();
	   }
	  }
	 }
	 
	 /**
	  * Return the number of places remaining in a room for the active object FilmShow
	  * @return placesLeft
	  */
	 public int placesLeft()
	 {
		 int placesLeft=this.getRoom().getChair()-this.listBooking().size();
		 return placesLeft;
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
			int visible = 0;
			if(this.visibility)
			{
				visible = 1;
			}
			
			String sqlRead0 = "SELECT Id, Hour, Date, Visibility, Id_Movie, Id_Room FROM FilmShow "
							+ "WHERE Hour='"+this.hour+"' AND Date='"+this.date+"' AND Visibility='"+visible+"'"
							+ " AND Id_Movie='"+this.movie.getId()+"' AND Id_Room='"+this.room.getId()+"' ";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO FilmShow(Hour, Date, Visibility, Id_Movie, Id_Room) "
						+ "VALUES('"+this.hour+"', '"+this.date+"', '"+visible+"', '"+this.movie.getId()+"'"
						+ ", '"+this.room.getId()+"')";
				System.out.println(sqlInsert0);
				db.WriteDB(sqlInsert0);
				
				String sqlRead1 = "SELECT LAST_INSERT_ID()";
				ResultSet result1 = db.ReadDB(sqlRead1);
				
				result1.next();
				setId(result1.getInt(1));
				res = true;
				
			} else {
				setId(result0.getInt("Id"));
				System.out.println("FilmShow already exist");
				res = false;
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
	@Override
	public boolean update() {
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			int visible = 0;
			if(this.visibility)
			{
				visible = 1;
			}
			
			String sqlUpdate0 = "UPDATE FilmShow "
					+ "SET Hour='"+this.hour+"', Date='"+this.date+"', Visibility='"+visible+"'"
					+ ", Id_Movie='"+this.movie.getId()+"', Id_Room='"+this.room.getId()+"' "
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
			String sqlDelete0 = "DELETE FROM FilmShow WHERE Id='"+this.id+"'";
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
	 *  Get Integer value of the id attribute
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
	 *  Get Time value of the hour attribute
	 * @return hour
	 */
	public Time getHour() {
		return this.hour;
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
		return this.date;
	}

	/**
	 * Set Date value of the date attribute
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 *  Get Boolean value of the visibility attribute
	 * @return visibility
	 */
	public boolean getVisibility() {
		return this.visibility;
	}

	/**
	 * Set Boolean value of the visibility attribute
	 * @param visibility
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 *  Get Movie object of the movie attribute
	 * @return movie
	 */
	public Movie getMovie() {
		return this.movie;
	}

	/**
	 * Set Movie object of the movie attribute
	 * @param movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 *  Get Room object of the room attribute
	 * @return room
	 */
	public Room getRoom() {
		return this.room;
	}

	/**
	 * Set Room object of the room attribute
	 * @param room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}		
}
