package Common;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;




import API.ConnectDB;
import EndUser.Booking;
import Interface.ADM;


public class FilmShow implements ADM {

	private Integer id;
	private Date hour;
	private Date date;
	private boolean visibility;
	private Movie movie;
	private Room room;
	
	
	//CONSTRUCTORS
	private FilmShow() {
		super();
	}


	public FilmShow(Date hour, Date date, boolean visibility, Movie movie,
			Room room) {
		this();
		this.hour = hour;
		this.date = date;
		this.visibility = visibility;
		this.movie = movie;
		this.room = room;
	}


	public FilmShow(Integer id, Date hour, Date date, boolean visibility,
			Movie movie, Room room) {
		this(hour, date, visibility, movie, room);
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


	public boolean isVisibility() {
		return visibility;
	}


	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}



	
	
	//CRUD
	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Hour, Date, Visibility, Id_Movie, Id_Room FROM FilmShow "
							+ "WHERE Hour='"+this.hour+"' AND Date='"+this.date+"' AND Visibility='"+this.visibility+"'"
							+ " AND Id_Movie='"+this.movie.getId()+"' AND Id_Room='"+this.room.getId()+"' ";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO FilmShow(Hour, Date, Visibility, Id_Movie, Id_Room) "
						+ "VALUES('"+this.hour+"', '"+this.date+"', '"+this.visibility+"', '"+this.movie.getId()+"'"
						+ ", '"+this.room.getId()+"')";
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
				System.out.println("FilmShow already exist");
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
			String sqlUpdate0 = "UPDATE FilmShow "
					+ "SET Hour='"+this.hour+"', Date='"+this.date+"', Visibility='"+this.visibility+"'"
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
	

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM FilmShow WHERE Id='"+this.id+"'";
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
	
	
	// FUNCTION
	
	public ArrayList<Booking> listBooking()
	 {
	  
	  ArrayList<Booking> alListBooking = new ArrayList<>();
	  
	  ConnectDB dblistbooking = new ConnectDB();
	   
	  try {
	   String sqlRead0 = "SELECT * FROM booking WHERE Id_FilmShow="+this.getId()+";";
	   ResultSet res = dblistbooking.ReadDB(sqlRead0);
	   
	   while(res.next())
	   {    
	    alListBooking.add(new Booking(res.getInt(1), res.getDate(2), res.getDate(3), this));
	   }
	   
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } finally {
	   dblistbooking.CloseDB();
	  }
	  
	  return alListBooking;
	  
	 }
	 
	 public void deleteBooking(ArrayList<Booking> bookingList)
	 {
	  for (Booking booking : bookingList) {
	   if(booking.getFilmshow().getId()==this.getId())
	   {
	    booking.delete();
	   }
	  }
	 }
	 
	 public int placeLeft()
	 {
	  return this.getRoom().getChair()-this.listBooking().size();
	 }
	
	
}
