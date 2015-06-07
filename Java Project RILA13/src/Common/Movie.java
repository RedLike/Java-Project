package Common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import API.ConnectDB;
import Interface.ADM;

public class Movie implements ADM{
	
	private int id;
	private int idMovieDB;
	private String name;
	private int duration;
	private String description;
	private String genre;
	private String producer;
	private String releaseDate;
	private Format format;
	private String image;
	
	/**
	 * Empty Movie constructor 
	 */
	public Movie() {
		super();
	}
	
	/**
	 * Movie constructor with minimun parameters parameters
	 * @param name
	 * @param idMovieDB
	 * @param image
	 * @param description
	 * @param releaseDate
	 */
	public Movie(String name, int idMovieDB, String image, String description, String releaseDate) {
		this();
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.image = image;
		this.idMovieDB = idMovieDB;
	}
	
	/**
	 * Movie constructor with all parameters
	 * @param name
	 * @param idMovieDB
	 * @param image
	 * @param description
	 * @param releaseDate
	 * @param producer
	 * @param genre
	 * @param duration
	 * @param format
	 */
	public Movie(String name, int idMovieDB, String image, String description, String releaseDate, String producer, String genre, int duration, Format format) {
		this(name, idMovieDB, image, description, releaseDate);
		this.duration = duration;
		this.genre = genre;
		this.format = format;
		this.producer = producer;
	}
	
	/**
	 * Movie constructor with all parameters and its db id
	 * @param id
	 * @param name
	 * @param idMovieDB
	 * @param image
	 * @param description
	 * @param releaseDate
	 * @param producer
	 * @param genre
	 * @param duration
	 * @param format
	 */
	public Movie(int id, String name, int idMovieDB, String image, String description, String releaseDate, String producer, String genre, int duration, Format format) {
		this (name, idMovieDB, image, description, releaseDate, producer, genre, duration, format);
		this.id = id;
	}
	
	// FUNCTION
	/**
	 * Return an ArrayList of all the FilmShow objects associate to the active object Movie
	 * @return listFilmShowToMovie
	 */
	public ArrayList<FilmShow> listFilmShow(ArrayList<FilmShow> filmShowList)
	{
		ArrayList<FilmShow> listFilmShowToMovie = new ArrayList<>();
		  
		for (FilmShow filmShow : filmShowList){
			if (filmShow.getMovie().getId() == this.getId()) {
				listFilmShowToMovie.add(filmShow);
			}					  
		}		  
		return listFilmShowToMovie;
	}
 
	/**
	 * Delete in the database all the FilmShow objects associate to the active object Movie
	 * @param filmShowList
	 */
	public void deleteFilmShow(ArrayList<FilmShow> filmShowList)
	{
		for (FilmShow filmShow : filmShowList) {
			if(filmShow.getMovie().getId()==this.getId())
			{
				filmShow.delete();
			}
		}
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
			String sqlRead0 = "SELECT Id, idMovieDB, Name, Duration, Genre, Producer, ReleaseDate, Id_Format FROM Movie "
							+ "WHERE Name='"+this.name+"' AND idMovieDB='"+this.idMovieDB+"' AND Duration='"+this.duration+"' AND Genre='"+this.genre+"' AND Producer='"+this.producer+"' AND ReleaseDate='"+this.releaseDate+"' AND Id_Format='"+this.format.getId()+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO Movie(Name, idMovieDB, Image, Duration, Description, Genre, Producer, ReleaseDate, Id_Format) VALUES('"+this.name+"', '"+this.idMovieDB+"', '"+this.image+"', '"+this.duration+"', '"+this.description+"', '"+this.genre+"', '"+this.producer+"', '"+this.releaseDate+"', '"+this.format.getId()+"')";
				System.out.println(sqlInsert0);
				db.WriteDB(sqlInsert0);
				
				String sqlRead1 = "SELECT LAST_INSERT_ID()";
				ResultSet result1 = db.ReadDB(sqlRead1);
				
				result1.next();
				setId(result1.getInt(1));
				res = true;				
			} else {
				setId(result0.getInt("Id"));
				System.out.println("Movie already exist");
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
	 *  Return a boolean that is false if the Movie don't exist in the database or if the request doesn't succeed
	 * @return res
	 */
	public boolean read(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Name, Duration, Description, Genre, Producer, ReleaseDate, Id_Format FROM Movie "
							+ "WHERE Name='"+name+"' AND Duration='"+duration+"' AND Description='"+description+"' AND Genre='"+genre+"' AND Producer='"+producer+"' AND ReleaseDate='"+releaseDate+"' AND Id_Format='"+format.getId()+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				System.out.println("Movie already exist");
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
			String sqlUpdate0 = "UPDATE Movie "
					+ "SET Name='"+this.name+"', Image='"+this.image+"', Duration='"+this.duration+"', Description='"+this.description+"', Genre='"+this.genre+"', Producer='"+this.producer+"', ReleaseDate='"+this.releaseDate+"', Id_Format='"+this.format.getId()+"'"
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
	public boolean delete(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlDelete0 = "DELETE FROM Movie WHERE Id='"+this.id+"'";
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
	 * Get String value of the name attribute
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set String value of the name attribute
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get Integer value of the idMovieDB attribute
	 * @return idMovieDB
	 */
	public int getIdMovieDB() {
		return this.idMovieDB;
	}

	/**
	 * Set Integer value of the idMovieDB attribute
	 * @param idMovieDB
	 */
	public void setIdMovieDB(int idMovieDB) {
		this.idMovieDB = idMovieDB;
	}
	
	/**
	 * Get String value of the image attribute
	 * @return image
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * Set String value of the image attribute
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}	
	
	/**
	 * Get Integer value of the duration attribute
	 * @return duration
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * Set Integer value of the duration attribute
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Get String value of the description attribute
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set String value of the description attribute
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get String value of the genre attribute
	 * @return genre
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * Set String value of the genre attribute
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}


	/**
	 * Get String value of the producer attribute
	 * @return producer
	 */
	public String getProducer() {
		return this.producer;
	}

	/**
	 * Set String value of the producer attribute
	 * @param producer
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/**
	 * Get String value of the releaseDate attribute
	 * @return releaseDate
	 */
	public String getReleaseDate() {
		return this.releaseDate;
	}

	/**
	 * Set String value of the releaseDate attribute
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Get Format object of the format attribute
	 * @return format
	 */
	public Format getFormat() {
		return this.format;
	}

	/**
	 * Set Format object of the format attribute
	 * @param format
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
}
