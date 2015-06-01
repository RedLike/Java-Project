package Admin;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Controller permettant de faire le lien entre les boutons de la fenetre d'autentification et les fonctions
 */

public class LoginController {

	
	private Stage stage;
	
	// L'annotation @FXML permet de faire le lien avec des éléments IHM du fichier FXML.
	@FXML
	private TextField loginInput;
	@FXML
	private TextField passwordInput;
	@FXML
	private Button connexionButton;
	
	
	
	//CONSTRUCTOR
	/**
	 * Constructeur par défaut
	 */
	public LoginController() {
		super();
	}
	
	/**
	 * Constructeur par defaut, permet d'afficher la fenêtre d'authentification.
	 * @param stage
	 */
	public LoginController(Stage stage) {
		this();
		try {
			this.stage = stage;
			this.stage.setTitle("Authentification");
			Parent root = FXMLLoader.load(LoginController.class.getResource("LoginForm.fxml"));
			this.stage.setScene(new Scene(root));
			this.stage.show();		
						
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Méthode appelée sur le click du bouton "Connexion" du formulaire d'authentification.
	 * @param e
	 */
	@FXML
	private void connexion(ActionEvent e) {
		
//		Button button = (Button) e.getSource();
//		Stage stage = (Stage) button.getScene().getWindow();
//		stage.close();
		
//		@SuppressWarnings("unused")
//		ManageController manageForm = new ManageController(stage);
		
		if(!loginInput.getText().isEmpty() && !passwordInput.getText().isEmpty()) {
			
			UserAdmin admin = new UserAdmin(loginInput.getText(), passwordInput.getText());
			if (admin.login()) {
				System.out.println("User Connected");
				System.out.println("Name : "+admin.getName());
				System.out.println("Login : "+admin.getLogin());
				
				Button button = (Button) e.getSource();
				Stage stage = (Stage) button.getScene().getWindow();
				stage.close();
				
				@SuppressWarnings("unused")
				ManageController manageForm = new ManageController(stage);
				
				
			} else {
				System.out.println("Bad Username/Password");
			}
				
		}
		else {
			System.out.println("empty field");
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
