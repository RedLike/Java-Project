package Admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import API.ConnectDB;
import Common.Cinema;
import Common.FilmShow;
import Common.Format;
import Common.Movie;
import Common.Room;
import EndUser.Booking;
import EndUser.Terminal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * Controller permettant de faire le lien entre les boutons de la fenetre de management de l'application et les fonctions
 */
public class ManageController {

	private Stage stage;
	private static ArrayList<Cinema> cinemaList = new ArrayList();
	private static ArrayList<Room> roomList = new ArrayList();
	private static ArrayList<Format> formatList = new ArrayList();
	private static ArrayList<Movie> movieList = new ArrayList();
	private static ArrayList<FilmShow> filmShowList = new ArrayList();	
	private static ArrayList<Booking> bookingList = new ArrayList();
	private static ArrayList<Terminal> terminalList = new ArrayList();
	
	@FXML
	private Button btnCinema;
	@FXML
	private Button btnMovies;
	@FXML
	private Button btnUsers;
	
	@FXML
	private Group grpCinema;
	@FXML
	private TabPane tabPaneCinema;
	@FXML
	private Tab tabCinema;
	@FXML
	private ListView<String> listViewCinema;
	@FXML
	private TextField inCinemaId;
	@FXML
	private TextField inCinemaName;
	@FXML
	private TextField inCinemaCity;
	@FXML
	private TextArea inCinemaAddress;
	@FXML
	private Button btnCinemaAdd;
	@FXML
	private Button btnCinemaModify;
	@FXML
	private Button btnCinemaDelete;
	
	@FXML
	private Group grpMovies;
	
	@FXML
	private Group grpUsers;
	
	
	
	
	
	//CONSTRUCTOR
	/**
	 * Constructeur par défaut
	 */
	public ManageController() {
		super();
	
//		this.cinemaList = new ArrayList();
//		this.roomList = new ArrayList();
//		this.formatList = new ArrayList();
//		this.movieList = new ArrayList();
//		this.filmShowList = new ArrayList();	
//		this.bookingList = new ArrayList();
//		this.terminalList = new ArrayList();
		
	}
	
	/**
	 * Constructeur par defaut, permet d'afficher la fenêtre de management de l'application.
	 * @param stage
	 */
	public ManageController(Stage stage) {
		this();
		this.stage = stage;
		try {
			this.stage.setTitle("Manage");
			Parent root = FXMLLoader.load(ManageController.class.getResource("ManageForm.fxml"));
			this.stage.setScene(new Scene(root));
			this.stage.show();
			initApp();
			
					
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Méthode permettant d'afficher le menu de droite associé à la catégorie Cinema.
	 * @param e
	 */
	@FXML
	private void displayTabCinemaAll(ActionEvent e) {
		grpMovies.setVisible(false);
		
		grpUsers.setVisible(false);
		
		displayListViewCinema();
		grpCinema.setVisible(true);
		tabPaneCinema.setVisible(true);
		
	}
	
	/**
	 * Méthode permettant d'afficher le menu de droite associé à la catégorie Movies.
	 * @param e
	 */
	@FXML
	private void displayTabMoviesAll(ActionEvent e) {
		grpCinema.setVisible(false);
		tabPaneCinema.setVisible(false);
		
		grpUsers.setVisible(false);
		
		
		grpMovies.setVisible(true);
	}
	

	
	
	/**
	 * Méthode permettant de remplir la ListView de Cinema dans l'onglet "Cinema".
	 */
	@FXML
	private void displayListViewCinema() {
		listViewCinema.getItems().clear();
				
		for (Cinema cinema : ManageController.cinemaList) {
			listViewCinema.getItems().add(cinema.getName());
		}
	}
	
	
	/**
	 * Méthode permettant de remplir la ListView de Cinema dans l'onglet "Cinema".
	 * @param e
	 */
	@FXML
	private void addCinema() {
		
		System.out.println("test");
		String name = inCinemaName.getText();
		String city = inCinemaCity.getText();
		String address = inCinemaAddress.getText();
		
		Cinema cinema = new Cinema(city, name, address);
		cinema.create();
				
		
		displayListViewCinema();
	}
	
	/**
	 * Méthode permettant de remplir les champs de "cinema" pour delete ou update.
	 */
	@FXML
	private void selectCinema() {
		
		int item = listViewCinema.getSelectionModel().getSelectedIndex();
		Cinema cinema = ManageController.cinemaList.get(item);
		
		inCinemaName.setText(cinema.getName());
		inCinemaCity.setText(cinema.getCity());
		inCinemaId.setText(""+cinema.getId());
		inCinemaAddress.setText(cinema.getAddress());
		
		btnCinemaModify.setDisable(false);
		btnCinemaDelete.setDisable(false);
		
		
	}
	
	
	
	/**
	 * Initialize all objects lists needed by the application
	 */
	public void initApp()
	{		
		funcListCinema();
		funcListRoom();
		funcListTerminal();
		funcListFormat();
		funcListMovie();
		funcListFilmshow();
		funcListBooking();
	}	
	
	/**
	 * Initialize cinema objects lists needed by the application
	 */
	private void funcListCinema() {
		ConnectDB db = new ConnectDB();
		try {
			ManageController.cinemaList.clear();
			//Cinema
			String sqlRead0 = "SELECT Id, City, Name, Address FROM Cinema;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{		
				Cinema cinema = new Cinema(res.getInt("Id"), res.getString("City"), res.getString("Name"), res.getString("Address"));
				ManageController.cinemaList.add(cinema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	
	/**
	 * Initialize room objects lists needed by the application
	 */
	private void funcListRoom() {
		ConnectDB db = new ConnectDB();
		try {
			ManageController.roomList.clear();
			//Room			
			String sqlRead0 = "SELECT  Id, Id_Cinema, Number, Chair FROM room;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{		
				Cinema cinema = new Cinema();
				for (Cinema cinemaElement : ManageController.cinemaList) {
					if(res.getInt("Id_Cinema")==cinemaElement.getId())
					{
						cinema = cinemaElement;
					}
				}
				
				ManageController.roomList.add(new Room(res.getInt("Id"), res.getInt("Number"), res.getInt("Chair"), cinema));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	
	/**
	 * Initialize terminal objects lists needed by the application
	 */
	private void funcListTerminal() {
		ConnectDB db = new ConnectDB();
		try {
			ManageController.terminalList.clear();
			//Terminal			
			String sqlRead0 = "SELECT  Id, Number, Id_Cinema FROM terminal;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{		
				Cinema cinema = new Cinema();
				for (Cinema cinemaElement : ManageController.cinemaList) {
					if(res.getInt("Id_Cinema")==cinemaElement.getId())
					{
						cinema = cinemaElement;
					}
				}
				
				ManageController.terminalList.add(new Terminal(res.getInt("Id"), res.getInt("Number"), cinema));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	
	/**
	 * Initialize format objects lists needed by the application
	 */
	private void funcListFormat() {
		ConnectDB db = new ConnectDB();
		try {
			if (ManageController.formatList != null) {
				ManageController.formatList.clear();
			}
			//Format
			String sqlRead0 = "SELECT Id, Label, Language, Description FROM Format;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{				
				ManageController.formatList.add(new Format(res.getInt("Id"), res.getString("Label"), res.getString("Language"), res.getString("Description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	
	/**
	 * Initialize movie objects lists needed by the application
	 */
	private void funcListMovie() {
		ConnectDB db = new ConnectDB();
		try {
			ManageController.movieList.clear();
			//Movie
			String sqlRead0 = "SELECT Id, Name, idMovieDB, Image, Duration, Description, Genre, Producer, ReleaseDate, Id_Format FROM Movie;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{	
				Format format = new Format();
				for (Format formatElement : ManageController.formatList)
				{
					if(res.getInt("Id_Format")==formatElement.getId())
					{
						format=formatElement;
					}
				}
				ManageController.movieList.add(new Movie(res.getInt("Id"),res.getString("Name"), res.getInt("idMovieDB"), res.getString("Image"), res.getString("Description"), res.getString("ReleaseDate"), res.getString("Producer"), res.getString("Genre"), res.getInt("Duration"), format));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	
	
	/**
	 * Initialize filmshow objects lists needed by the application
	 */
	private void funcListFilmshow() {
		ConnectDB db = new ConnectDB();
		try {
			ManageController.filmShowList.clear();
			//FilmShow
			String sqlRead0 = "SELECT Id, Hour, Date, Visibility, Id_Movie, Id_Room FROM filmshow;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{
				Room room = new Room();
				for (Room roomElement : ManageController.roomList) 
				{
					if(res.getInt("Id_Room")==roomElement.getId())
					{
						room = roomElement;
					}
				}
				Movie movie  = new Movie();
				for (Movie movieElement : ManageController.movieList)
				{
					if(res.getInt("Id_Movie")==movieElement.getId())
					{
						movie = movieElement;
					}				
				}
				ManageController.filmShowList.add(new FilmShow(res.getInt("Id"), res.getDate("Hour"), res.getDate("Date"), res.getBoolean("Visibility"), movie, room));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	
	
	/**
	 * Initialize booking objects lists needed by the application
	 */
	private void funcListBooking() {
		ConnectDB db = new ConnectDB();
		try {
			ManageController.bookingList.clear();
			//Booking
			String sqlRead0 = "SELECT Id, Hour, Date, Id_FilmShow FROM booking;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{
				FilmShow filmshow = new FilmShow();
				for(FilmShow filmShowElement : ManageController.filmShowList)
				{
					if(res.getInt("Id")==filmShowElement.getId())
					{
						filmshow=filmShowElement;
					}
				}
				ManageController.bookingList.add(new Booking(res.getInt("Id"), res.getDate("Hour"), res.getDate("Date"), filmshow));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	

	
	
	
	
	//GETTERS & SETTERS
	@SuppressWarnings("unused")
	private Stage getStage() {
		return stage;
	}

	@SuppressWarnings("unused")
	private void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
