package Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;


/**
 * Controller permettant de faire le lien entre les boutons de la fenetre de management de l'application et les fonctions
 */
public class ManageController {

	@FXML 
	private TitledPane titlePane;
	@FXML 
	private TitledPane detailManage;
	@FXML 
	private FlowPane flowCategories;
	@FXML
	private ListView<String> listObject;
	@FXML
	private Button btnNew;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private MenuItem menuClose;
	@FXML
	private MenuItem menuChangeCinema;
	
	
	
	//CONSTRUCTOR
	/**
	 * Constructeur par défaut
	 */
	public ManageController() {
		super();
	}

	
	
	
	
	
}
