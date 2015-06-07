package Admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import API.ConnectDB;
import API.TheMovieDB;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



/**
 * Controller permettant de faire le lien entre les boutons de la fenetre de management de l'application et les fonctions
 */
public class ManageController {

	private Stage stage;
	private static ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
	private static ArrayList<Cinema> cinemaListFilter = new ArrayList<Cinema>();
	private static ArrayList<Room> roomList = new ArrayList<Room>();
	private static ArrayList<Room> roomListFilter = new ArrayList<Room>();
	private static ArrayList<Format> formatList = new ArrayList<Format>();
	private static ArrayList<Format> formatListFilter = new ArrayList<Format>();
	private static ArrayList<Movie> movieList = new ArrayList<Movie>();
	private static ArrayList<Movie> movieListFilter = new ArrayList<Movie>();
	private static ArrayList<FilmShow> filmShowList = new ArrayList<FilmShow>();	
	private static ArrayList<FilmShow> filmShowListFilter = new ArrayList<FilmShow>();
	private static ArrayList<Booking> bookingList = new ArrayList<Booking>();
	private static ArrayList<Booking> bookingListFilter = new ArrayList<Booking>();
	private static ArrayList<Terminal> terminalList = new ArrayList<Terminal>();
	private static ArrayList<Terminal> terminalListFilter = new ArrayList<Terminal>();
	private static ArrayList<UserAdmin> userList = new ArrayList<UserAdmin>();
	private static ArrayList<UserAdmin> userListFilter = new ArrayList<UserAdmin>();
	
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
	private TextField inCinemaName;
	@FXML
	private TextField inCinemaCity;
	@FXML
	private TextArea inCinemaAddress;
	@FXML
	private TextField inCinemaId;
	@FXML
	private Button btnCinemaAdd;
	@FXML
	private Button btnCinemaModify;
	@FXML
	private Button btnCinemaDelete;
	
	@FXML
	private Tab tabRoom;
	@FXML
	private ComboBox<String> comboRoomCinema;
	@FXML
	private ListView<Integer> listviewRoom;
	@FXML
	private TextField inRoomNumber;
	@FXML
	private TextField inRoomChair;
	@FXML
	private TextField inRoomId;
	@FXML
	private Button btnRoomAdd;
	@FXML
	private Button btnRoomModify;
	@FXML
	private Button btnRoomDelete;
	
	@FXML
	private Tab tabTerminal;
	@FXML
	private ComboBox<String> comboTerminalCinema;
	@FXML
	private ListView<Integer> listviewTerminal;
	@FXML
	private TextField inTerminalNumber;
	@FXML
	private TextField inTerminalId;
	@FXML
	private Button btnTerminalAdd;
	@FXML
	private Button btnTerminalModify;
	@FXML
	private Button btnTerminalDelete;

	
	@FXML
	private Group grpMovies;
	@FXML
	private TabPane tabPaneMovie;
	@FXML
	private Tab tabMovie;
	@FXML
	private ListView<String> listviewMovie;
	@FXML
	private TextField inMovieName;
	@FXML
	private TextField inMovieDuration;
	@FXML
	private TextArea inMovieDescription;
	@FXML
	private TextField inMovieFormat;
	@FXML
	private TextField inMovieGenre;
	@FXML
	private TextField inMovieProducer;
	@FXML
	private TextField inMovieReleasedate;
	@FXML
	private ImageView inMovieImage;
	@FXML
	private Button btnTheMovieDBUpdate;

	
	@FXML
	private Group grpUsers;
	@FXML
	private TabPane tabPaneUsers;
	@FXML
	private Tab tabUsers;
	@FXML
	private ListView<String> listviewUsers;
	@FXML
	private TextField inUsersName;
	@FXML
	private TextField inUsersLogin;
	@FXML
	private TextField inUsersPassword;
	@FXML
	private TextField inUsersId;
	@FXML
	private Button btnUsersAdd;
	@FXML
	private Button btnUsersModify;
	@FXML
	private Button btnUsersDelete;
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	//CONSTRUCTOR
	/**
	 * Constructeur par défaut
	 */
	public ManageController() {
		super();		
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

/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Méthode permettant d'afficher le menu de droite associé à la catégorie Cinema.
	 * @param e
	 */
	@FXML
	private void displayTabCinemaAll(ActionEvent e) {
		grpMovies.setVisible(false);
		tabPaneMovie.setVisible(false);
		grpUsers.setVisible(false);
		tabPaneUsers.setVisible(false);
		
		
		refreshListViewCinema();
		grpCinema.setVisible(true);
		tabPaneCinema.setVisible(true);
		
	}
	
	/**
	 * Méthode permettant de refresh toutes les ListView/combox/etc... de la catégorie Cinema.
	 */

	private void refreshListViewCinema() {
		listviewRoom.getItems().clear();
		comboRoomCinema.getItems().clear();
		listviewTerminal.getItems().clear();
		comboTerminalCinema.getItems().clear();
		listViewCinema.getItems().clear();
				
		for (Cinema cinema : ManageController.cinemaList) {
			listViewCinema.getItems().add(cinema.getName());
			
			comboRoomCinema.getItems().add(cinema.getName());
			comboTerminalCinema.getItems().add(cinema.getName());
		}
		
		
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
		tabPaneUsers.setVisible(false);
		
		refreshListViewMovie();
		grpMovies.setVisible(true);
		tabPaneMovie.setVisible(true);
	}
	
	/**
	 * Méthode permettant de refresh toutes les ListView/combox/etc... de la catégorie Movie.
	 */
	@FXML
	private void refreshListViewMovie() {
		
		listviewMovie.getItems().clear();
	

				
		for (Movie movie : ManageController.movieList) {
			listviewMovie.getItems().add(movie.getName());
					
		}

	}
	
	
	
	/**
	 * Méthode permettant d'afficher le menu de droite associé à la catégorie Users.
	 * @param e
	 */
	@FXML
	private void displayTabUsersAll(ActionEvent e) {
		grpCinema.setVisible(false);
		tabPaneCinema.setVisible(false);
		grpMovies.setVisible(false);
		tabPaneMovie.setVisible(false);
		
		
		refreshListViewUsers();
		
		grpUsers.setVisible(true);
		tabPaneUsers.setVisible(true);
		
	}
	
	
	/**
	 * Méthode permettant de refresh toutes les ListView/combox/etc... de la catégorie Movie.
	 */
	@FXML
	private void refreshListViewUsers() {

		listviewUsers.getItems().clear();

		
		for (UserAdmin user : ManageController.userList) {
			listviewUsers.getItems().add(user.getName());
					
		}

	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	//FUNCTION FOR USERS(Users TAB) PANEL (Display, Update DB)		
	/**
	 * Méthode permettant de remplir champs en fonction des choix de la lisstview dans la partie Users=>Users.
	 */
	@FXML
	private void selectUser() {	
		int itemUser = listviewUsers.getSelectionModel().getSelectedIndex();
		if (itemUser >= 0) {
			UserAdmin user = ManageController.userList.get(itemUser);
			
			inUsersId.setText(user.getId().toString());
			inUsersName.setText(user.getName());
			inUsersLogin.setText(user.getLogin());
			
			btnUsersModify.setDisable(false);
			btnUsersDelete.setDisable(false);
		}
	}
	
	
	/**
	 * Méthode permettant d'ajouter un user.
	 */
	@FXML
	private void addUsers() {
		
//		System.out.println("test");
		String name = inUsersName.getText();
		String login = inUsersLogin.getText();
		String password = inUsersPassword.getText();
		
		UserAdmin user = new UserAdmin(name, login, password);
		user.create();
				
		funcListUsers();
		refreshListViewUsers();
	}
	
	/**
	 * Méthode permettant de modifier un user.
	 */
	@FXML
	private void modifyUsers() {
		
		String name = inUsersName.getText();
		String login = inUsersLogin.getText();
		String password = inUsersPassword.getText();
		String id = inUsersId.getText();
		
		
		for (UserAdmin user : ManageController.userList) {;
			if (id.equals(""+user.getId())) {
				user.setName(name);
				user.setLogin(login);
				user.setPassword(password);
				user.update();
			}
		}
				
		refreshListViewUsers();
	}
	
	/**
	 * Méthode permettant de supprimer un user.
	 */
	@FXML
	private void deleteUsers() {
		String id = inUsersId.getText();
		
		for (UserAdmin user : ManageController.userList) {;
			if (id.equals(""+user.getId())) {
				if (user.delete()) {
					System.out.println("delete OK");
					btnUsersModify.setDisable(true);
					btnUsersDelete.setDisable(true);
				} else {
					System.out.println("delete NOK");
				}
			}
		}			
		
//		funcListCinema();
		
		initApp();
		
		refreshListViewUsers();
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	//FUNCTION FOR MOVIES(Movie TAB) PANEL (Display, Update DB)	
	/**
	 * Méthode permettant de remplir champs en fonction des choix de la lisstview dans la partie Movie=>Movie.
	 */
	@FXML
	private void selectMovie() {	
		int itemMovie = listviewMovie.getSelectionModel().getSelectedIndex();
		if (itemMovie >= 0) {
			Movie movie = ManageController.movieList.get(itemMovie);
			
			inMovieName.setText(movie.getName());
			
			int hours = movie.getDuration() / 60;
			int minutes =movie.getDuration() % 60;
			inMovieDuration.setText(hours+"h"+String.format("%02d",minutes)+"min");
			
			inMovieFormat.setText(movie.getFormat().getLabel());
			inMovieGenre.setText(movie.getGenre());
			inMovieProducer.setText(movie.getProducer());
			inMovieReleasedate.setText(movie.getReleaseDate());
			inMovieDescription.setText(movie.getDescription());
			
			Image imageMovie = new Image(movie.getImage());
			inMovieImage.setImage(imageMovie);
			
			btnCinemaModify.setDisable(false);
			btnCinemaDelete.setDisable(false);
		}
	}
	
	
	/**
	 * Méthode permettant de lancer la fonction permettant de récupéré des film depuis Th Movie DB.
	 */
	@FXML
	private void StartTheMovieDB() {	
		TheMovieDB info;
		List<Movie> movies;
		try {
			info = new TheMovieDB();
			if (!ManageController.formatList.isEmpty()) {
				movies = info.Discover(ManageController.formatList);
				if (!movies.isEmpty()) {
					info.Insert(movies);
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initApp();
		refreshListViewMovie();
		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	// FUNCTION FOR CINEMA(Terminal TAB) PANEL (ADD, MODIFY, DELETE, DISPLAY)
	/**
	 * Méthode permettant de remplir la liste view en fonction des choix de la combobbox dans la partie Cinema=>Terminal.
	 */
	@FXML
	private void selectTerminalCinema() {
		listviewTerminal.getItems().clear();
		
		btnTerminalModify.setDisable(true);
		btnTerminalDelete.setDisable(true);
		inTerminalNumber.setText("");
		inTerminalId.setText("");
		
		int itemCinema = comboTerminalCinema.getSelectionModel().getSelectedIndex();
		
		if (itemCinema >= 0) {
			Cinema cinema = ManageController.cinemaList.get(itemCinema);
			
			ManageController.terminalListFilter.clear();
			
			for (Terminal terminal : terminalList) {
				if (terminal.getCinema().equals(cinema)) {
					ManageController.terminalListFilter.add(terminal);
					listviewTerminal.getItems().add(terminal.getNumber());

				} else {
					
				}
			}
			
		}	
	}
	
	/**
	 * Méthode permettant de remplir champs en fonction des choix de la lisstview dans la partie Cinema=>Terminal.
	 */
	@FXML
	private void selectTerminal() {	
		int itemTerminal = listviewTerminal.getSelectionModel().getSelectedIndex();
		
		if (itemTerminal >= 0) {
			Terminal terminal = ManageController.terminalListFilter.get(itemTerminal);
			
			inTerminalNumber.setText(""+terminal.getNumber());
			inTerminalId.setText(""+terminal.getId());
			
			btnTerminalModify.setDisable(false);
			btnTerminalDelete.setDisable(false);
		}
	}
	
	/**
	 * Méthode permettant de créer une Room à un cinema (Cinema=>Terminal).
	 */
	@FXML
	private void addTerminal() {
		
		int item = comboTerminalCinema.getSelectionModel().getSelectedIndex();
		Cinema cinema = ManageController.cinemaList.get(item);

		
		Integer Number = Integer.parseInt(inTerminalNumber.getText());

		
		Terminal terminal = new Terminal(Number, cinema);
		terminal.create();
				
		funcListTerminal();
		selectTerminalCinema();
				
		
	}
	
	/**
	 * Méthode permettant de modifuer une Room d'un cinema (Cinema=>Terminal).
	 */
	@FXML
	private void modifyTerminal() {
		int item = comboTerminalCinema.getSelectionModel().getSelectedIndex();
		Cinema cinema = ManageController.cinemaList.get(item);

		Integer Number = Integer.parseInt(inTerminalNumber.getText());
		String Id = inTerminalId.getText();
		
		for (Terminal terminal : ManageController.terminalListFilter) {;
			if (Id.equals(""+terminal.getId())) {
				terminal.setNumber(Number);
				terminal.setCinema(cinema);
				terminal.update();
			}
		}
				
		selectTerminalCinema();
	}
	
	/**
	 * Méthode permettant de supprimer un Terminal d'un cinema.
	 */
	@FXML
	private void deleteTerminal() {
		String id = inTerminalId.getText();
		
		for (Terminal terminal : ManageController.terminalList) {;
			if (id.equals(""+terminal.getId())) {
				if (terminal.delete()) {
					System.out.println("delete OK");
					btnTerminalModify.setDisable(true);
					btnTerminalDelete.setDisable(true);
				} else {
					System.out.println("delete NOK");
				}
			}
		}			
		funcListTerminal();
		selectTerminalCinema();
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////

	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	// FUNCTION FOR CINEMA(ROOM TAB) PANEL (ADD, MODIFY, DELETE, DISPLAY)	
	/**
	 * Méthode permettant de remplir la liste view en fonction des choix de la combobbox dans la partie Cinema=>Room.
	 */
	@FXML
	private void selectRoomCinema() {
		listviewRoom.getItems().clear();
		
		btnRoomModify.setDisable(true);
		btnRoomDelete.setDisable(true);
		inRoomNumber.setText("");
		inRoomChair.setText("");
		inRoomId.setText("");
		
		int itemCinema = comboRoomCinema.getSelectionModel().getSelectedIndex();
		
		if (itemCinema >= 0) {
			Cinema cinema = ManageController.cinemaList.get(itemCinema);
			
			ManageController.roomListFilter.clear();
			
			for (Room room : roomList) {
				if (room.getCinema().equals(cinema)) {
					ManageController.roomListFilter.add(room);
					listviewRoom.getItems().add(room.getNumber());

				} else {
					
				}
			}
			
		}
		
		
		
		
	}
	
	/**
	 * Méthode permettant de remplir champs en fonction des choix de la lisstview dans la partie Cinema=>Room.
	 */
	@FXML
	private void selectRoom() {	
		int itemRoom = listviewRoom.getSelectionModel().getSelectedIndex();
		
		if (itemRoom >= 0) {
			Room room = ManageController.roomListFilter.get(itemRoom);
			
			inRoomNumber.setText(""+room.getNumber());
			inRoomChair.setText(""+room.getChair());
			inRoomId.setText(""+room.getId());
			
			btnRoomModify.setDisable(false);
			btnRoomDelete.setDisable(false);
		}

	}
	
	/**
	 * Méthode permettant de créer une Room à un cinema (Cinema=>Room).
	 */
	@FXML
	private void addRoom() {
		
		int item = comboRoomCinema.getSelectionModel().getSelectedIndex();
		Cinema cinema = ManageController.cinemaList.get(item);

		
		Integer Number = Integer.parseInt(inRoomNumber.getText());
		Integer Chair = Integer.parseInt(inRoomChair.getText());

		
		Room room = new Room(Number, Chair, cinema);
		room.create();
				
		funcListRoom();
		selectRoomCinema();
				
		
	}
	
	/**
	 * Méthode permettant de modifuer une Room d'un cinema (Cinema=>Room).
	 */
	@FXML
	private void modifyRoom() {
		int item = comboRoomCinema.getSelectionModel().getSelectedIndex();
		Cinema cinema = ManageController.cinemaList.get(item);

		Integer Number = Integer.parseInt(inRoomNumber.getText());
		Integer Chair = Integer.parseInt(inRoomChair.getText());
		String Id = inRoomId.getText();
		
		for (Room room : ManageController.roomListFilter) {;
			if (Id.equals(""+room.getId())) {
				System.out.println(Id+" =?="+room.getId());
				System.out.println(Chair);
				System.out.println(Number);
				room.setChair(Chair);
				room.setNumber(Number);
				room.setCinema(cinema);
				System.out.println("room chair : "+room.getChair());
				System.out.println("room number : "+room.getNumber());
				room.update();
			}
		}
				
		selectRoomCinema();
	}
	
	/**
	 * Méthode permettant de supprimer un Room d'un cinema.
	 */
	@FXML
	private void deleteRoom() {
		String id = inRoomId.getText();
		
		for (Room room : ManageController.roomList) {;
			if (id.equals(""+room.getId())) {
				if (room.delete()) {
					System.out.println("delete OK");
					btnRoomModify.setDisable(true);
					btnRoomDelete.setDisable(true);
				} else {
					System.out.println("delete NOK");
				}
			}
		}			
		funcListRoom();
		selectRoomCinema();
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	// FUNCTION FOR CINEMA(CINEMA TAB) PANEL (ADD, MODIFY, DELETE, DISPLAY)
	/**
	 * Méthode permettant d'ajouter un cinema.
	 */
	@FXML
	private void addCinema() {
		
//		System.out.println("test");
		String name = inCinemaName.getText();
		String city = inCinemaCity.getText();
		String address = inCinemaAddress.getText();
		
		Cinema cinema = new Cinema(city, name, address);
		cinema.create();
				
		funcListCinema();
		refreshListViewCinema();
	}
	
	/**
	 * Méthode permettant de modifier un cinema.
	 */
	@FXML
	private void modifyCinema() {
		
		String name = inCinemaName.getText();
		String city = inCinemaCity.getText();
		String address = inCinemaAddress.getText();
		String id = inCinemaId.getText();
		
		
		for (Cinema cinema : ManageController.cinemaList) {;
			if (id.equals(""+cinema.getId())) {
				cinema.setName(name);
				cinema.setCity(city);
				cinema.setAddress(address);
				cinema.update();
			}
		}
				
		refreshListViewCinema();
	}
	
	/**
	 * Méthode permettant de supprimer un cinema.
	 */
	@FXML
	private void deleteCinema() {
		String id = inCinemaId.getText();
		
		for (Cinema cinema : ManageController.cinemaList) {;
			if (id.equals(""+cinema.getId())) {
				if (cinema.delete()) {
					System.out.println("delete OK");
					btnCinemaModify.setDisable(true);
					btnCinemaDelete.setDisable(true);
				} else {
					System.out.println("delete NOK");
				}
			}
		}			
		
//		funcListCinema();
		
		initApp();
		
		refreshListViewCinema();
	}
	
	/**
	 * Méthode permettant de remplir les champs de "cinema" pour delete ou update.
	 */
	@FXML
	private void selectCinema() {
		
		int item = listViewCinema.getSelectionModel().getSelectedIndex();
		if (item >= 0) {
			Cinema cinema = ManageController.cinemaList.get(item);
			
			inCinemaName.setText(cinema.getName());
			inCinemaCity.setText(cinema.getCity());
			inCinemaAddress.setText(cinema.getAddress());
			inCinemaId.setText(""+cinema.getId());
			
			btnCinemaModify.setDisable(false);
			btnCinemaDelete.setDisable(false);
		}	
	}
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	// INIT APP + FUNCTION GENERATE ARRAYLIST
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
		funcListUsers();
	}	
	
	/**
	 * Initialize user objects lists needed by the application
	 */
	private void funcListUsers() {
		ConnectDB db = new ConnectDB();
		try {
			ManageController.userList.clear();
			//Cinema
			String sqlRead0 = "SELECT Id, Name, Login, Password FROM UserAdmin;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{		
				UserAdmin user = new UserAdmin(res.getInt("Id"), res.getString("Name"), res.getString("Login"), res.getString("Password"));
				ManageController.userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
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
				ManageController.filmShowList.add(new FilmShow(res.getInt("Id"), res.getTime("Hour"), res.getDate("Date"), res.getBoolean("Visibility"), movie, room));
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
				ManageController.bookingList.add(new Booking(res.getInt("Id"), res.getTime("Hour"), res.getDate("Date"), filmshow));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
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
