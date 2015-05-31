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
	private String author;
	private String producer;
	private String releaseDate;
	private Format format;
	private String image;
	
	public Movie() {
		super();
	}
	
	public Movie(String name, int idMovieDB, String image, String description, String releaseDate) {
		this();
		this.name = name;
		this.description = description;
		this.releaseDate = releaseDate;
		this.image = image;
		this.idMovieDB = idMovieDB;
	}
	
	public Movie(String name, int idMovieDB, String image, String description, String releaseDate, String producer, String genre, int duration, Format format) {
		this(name, idMovieDB, image, description, releaseDate);
		this.duration = duration;
		this.genre = genre;
		this.format = format;
		this.producer = producer;
	}
	public Movie(int id, String name, int idMovieDB, String image, String description, String releaseDate, String producer, String genre, int duration, Format format) {
		this (name, idMovieDB, image, description, releaseDate, producer, genre, duration, format);
		this.id = id;
	}

	//CRUD
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
//				result0.next();
				setId(result0.getInt(1));
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
	
	public boolean update(){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE Movie "
					+ "SET Name='"+this.name+"' AND Image='"+this.image+"' AND Duration='"+this.duration+"' AND Description='"+this.description+"' AND Genre='"+this.genre+"' AND Producer='"+this.producer+"' AND ReleaseDate='"+this.releaseDate+"' AND Id_Format='"+this.format.getId()+"'"
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
			String sqlDelete0 = "DELETE FROM Movie WHERE Id='"+this.id+"'";
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
	 
	
	 public void deleteFilmShow(ArrayList<FilmShow> filmShowList)
	 {
	  for (FilmShow filmShow : filmShowList) {
	   if(filmShow.getMovie().getId()==this.getId())
	   {
	    filmShow.delete();
	   }
	  }
	 }
	
	
	
	//GETTERS & SETTERS
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public int getIdMovieDB() {
		return idMovieDB;
	}

	public void setIdMovieDB(int idMovieDB) {
		this.idMovieDB = idMovieDB;
	}
}
