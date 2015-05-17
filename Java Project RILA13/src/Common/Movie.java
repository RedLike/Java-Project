package Common;

import java.sql.ResultSet;
import java.sql.SQLException;

import API.ConnectDB;

public class Movie {
	
	private int id;
	private String name;
	private String duration;
	private String description;
	private String genre;
	private String author;
	private String producer;
	private String releaseDate;
	private Format format;
	
	public Movie(String name, String duration, String description, String genre, String author, String producer, String releaseDate, Format format) {
		super();
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.genre = genre;
		this.author = author;
		this.producer = producer;
		this.releaseDate = releaseDate;
		this.format = format;
	}

	//CRUD
	public boolean create(String name, String duration, String description, String genre, String author, String producer, String releaseDate, Format format) {
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlRead0 = "SELECT Id, Name, Duration, Description, Genre, Author, Producer, ReleaseDate, Id_Format FROM Format "
							+ "WHERE Name='"+name+"' AND Duration='"+duration+"' AND Description='"+description+"' AND Genre='"+genre+"' AND Author='"+author+"' AND Producer='"+producer+"' AND ReleaseDate='"+releaseDate+"' AND Id_Format='"+format.getId()+"'";
			ResultSet result0 = db.ReadDB(sqlRead0);
			
			if (!result0.next()) {
				String sqlInsert0 = "INSERT INTO Movie(Name, Duration, Description, Genre, Author, Producer, ReleaseDate, Id_Format) VALUES('"+name+"', '"+duration+"', '"+description+"', '"+genre+"', '"+author+"', '"+producer+"', '"+releaseDate+"', '"+format.getId()+"')";
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
				System.out.println("Format already exist");
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
			String sqlRead0 = "SELECT Id, Name, Duration, Description, Genre, Author, Producer, ReleaseDate, Id_Format FROM Format "
							+ "WHERE Name='"+name+"' AND Duration='"+duration+"' AND Description='"+description+"' AND Genre='"+genre+"' AND Author='"+author+"' AND Producer='"+producer+"' AND ReleaseDate='"+releaseDate+"' AND Id_Format='"+format.getId()+"'";
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
	
	public boolean update(String name, String duration, String description, String genre, String author, String producer, String releaseDate, Format format){
		
		boolean res = false;
		ConnectDB db = new ConnectDB();
		
		try {
			String sqlUpdate0 = "UPDATE Format "
					+ "SET Name='"+name+"' AND Duration='"+duration+"' AND Description='"+description+"' AND Genre='"+genre+"' AND Author='"+author+"' AND Producer='"+producer+"' AND ReleaseDate='"+releaseDate+"' AND Id_Format='"+format.getId()+"'"
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
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
}
