package Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import API.ConnectDB;
import Common.Cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



/**
 * Controller permettant de faire le lien entre les boutons de la fenetre de management de l'application et les fonctions
 */
public class ManageController {

	
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
	 * Méthode permettant de lister tous les cinema.
	 */
	@FXML
	private ArrayList<Cinema> listCinema() {
		
		ArrayList<Cinema> listCinema = new ArrayList<Cinema>();
		
		ConnectDB db = new ConnectDB();
		try {
			//Cinema
			String sqlRead0 = "SELECT Id, City, Name, Address FROM Cinema;";
			ResultSet res = db.ReadDB(sqlRead0);
			while(res.next())
			{				
				Cinema cinema = new Cinema(res.getInt("Id"), res.getString("City"), res.getString("Name"), res.getString("Address"));
				listCinema.add(cinema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.CloseDB();
		}	
		return listCinema;
	}
	
	
	/**
	 * Méthode permettant de remplir la ListView de Cinema dans l'onglet "Cinema".
	 */
	@FXML
	private void displayListViewCinema() {
		listViewCinema.getItems().clear();
		
		ArrayList<Cinema> listCinema = listCinema();

		for (Cinema cinema : listCinema) {
			listViewCinema.getItems().add(cinema.toString());
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
	
}
