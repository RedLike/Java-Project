package application;
	
import API.ConnectDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import Common.Cinema;
import Common.FilmShow;
import Common.Format;
import Common.Movie;
import Common.Room;
import EndUser.Booking;
import EndUser.MovieList;

public class Main extends Application  {
	
	private static ArrayList<Cinema> cinemaList = new ArrayList<>();
	private static ArrayList<Room> roomList = new ArrayList<>();
	private static ArrayList<Format> formatList = new ArrayList<>();
	private static ArrayList<Movie> movieList = new ArrayList<>();
	private static ArrayList<FilmShow> filmShowList = new ArrayList<>();	
	private static ArrayList<Booking> bookingList = new ArrayList<>();

	public static void main(String[] args) {	
		initApp();
		launch(args);	
	}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		MovieList movieListScreen = new MovieList(movieList, filmShowList);
		movieListScreen.loadScreen(primaryStage);
	}

	/**
	 * Initialize all objects lists needed by the application
	 */
	public static void initApp()
	{		
		ConnectDB db = new ConnectDB();
		
		try {
			
			//Cinema
			String sqlRead0 = "SELECT Id, City, Name, Address FROM Cinema;";
			ResultSet res = db.ReadDB(sqlRead0);
			if(res.next())
			{
				do{
					cinemaList.add(new Cinema(res.getInt("Id"), res.getString("City"), res.getString("Name"), res.getString("Address")));
				}while(res.next());
				
			}		
			//Room			
			sqlRead0 = "SELECT  Id, Id_Cinema, Number, Chair FROM room;";
			res = db.ReadDB(sqlRead0);
			if(res.next())
			{
				do{
					Cinema cinema = new Cinema();
					for (Cinema cinemaElement : cinemaList) {
						if(res.getInt("Id_Cinema")==cinemaElement.getId())
						{
							cinema = cinemaElement;
						}
					}
					roomList.add(new Room(res.getInt("Id"), res.getInt("Number"), res.getInt("Chair"), cinema));
				}while(res.next());				
			}
			
			//Format
			sqlRead0 = "SELECT Id, Label, Language, Description FROM Format;";
			res = db.ReadDB(sqlRead0);
			if(res.next())
			{
				do{
					formatList.add(new Format(res.getInt("Id"), res.getString("Label"), res.getString("Language"), res.getString("Description")));
				}while(res.next());
				
			}
			
			//Movie
			sqlRead0 = "SELECT Id, Name, idMovieDB, Image, Duration, Description, Genre, Producer, ReleaseDate, Id_Format FROM Movie;";
			res = db.ReadDB(sqlRead0);
			if(res.next())
			{
				do{
					Format format = new Format();
					for (Format formatElement : formatList)
					{
						if(res.getInt("Id_Format")==formatElement.getId())
						{
							format=formatElement;
						}
					}
					movieList.add(new Movie(res.getInt("Id"),res.getString("Name"), res.getInt("idMovieDB"), res.getString("Image"), res.getString("Description"), res.getString("ReleaseDate"), res.getString("Producer"), res.getString("Genre"), res.getInt("Duration"), format));
				}while(res.next());				
			}
			
			//FilmShow
			sqlRead0 = "SELECT Id, Hour, Date, Visibility, Id_Movie, Id_Room FROM filmshow;";
			res = db.ReadDB(sqlRead0);	
			if(res.next())
			{
				do{
					Room room = new Room();
					for (Room roomElement : roomList) 
					{
						if(res.getInt("Id_Room")==roomElement.getId())
						{
							room = roomElement;
						}
					}
					Movie movie  = new Movie();
					for (Movie movieElement : movieList)
					{
						if(res.getInt("Id_Movie")==movieElement.getId())
						{
							movie = movieElement;
						}				
					}
					filmShowList.add(new FilmShow(res.getInt("Id"), res.getTime("Hour"), res.getDate("Date"), res.getBoolean("Visibility"), movie, room));
				}while(res.next());				
			}
			
			//Booking
			sqlRead0 = "SELECT Id, Hour, Date, Id_FilmShow FROM booking;";
			res = db.ReadDB(sqlRead0);
			if(res.next())
			{
				do{
					FilmShow filmshow = new FilmShow();
					for(FilmShow filmShowElement : filmShowList)
					{
						if(res.getInt("Id")==filmShowElement.getId())
						{
							filmshow=filmShowElement;
						}
					}
					bookingList.add(new Booking(res.getInt("Id"), res.getTime("Hour"), res.getDate("Date"), filmshow));
				}while(res.next());				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Lists initialized.");
			db.CloseDB();
		}		
	}	
}
