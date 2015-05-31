package Admin;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Classe permettant l'affichage de la fenetre d'authentification
 */
public class LoginForm {

	
	private Stage stage;
	
	
	//CONSTRUCTOR
	/**
	 * Constructeur par défaut
	 * "private" car non instanciable sous cette forme
	 */
	private LoginForm() {
		super();
	}
	
	
	/**
	 * Constructeur par defaut, permet d'afficher la fenêtre d'authentification.
	 * @param stage
	 */
	public LoginForm(Stage stage) {
		this();
		try {
			this.stage = stage;
			this.stage.setTitle("Authentification");
			Parent root = FXMLLoader.load(ManageForm.class.getResource("LoginForm.fxml"));
			this.stage.setScene(new Scene(root));
			this.stage.show();
						
			
		} catch (IOException e) {
			e.printStackTrace();
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
