package Admin;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


/**
 * Classe permettant l'affichage de la fenetre de management de l'application
 */
public class ManageForm {
	
	
	private Stage stage;
	
	@SuppressWarnings("unused")
	private Stage getStage() {
		return stage;
	}

	@SuppressWarnings("unused")
	private void setStage(Stage stage) {
		this.stage = stage;
	}

	//CONSTRUCTOR
	/**
	 * Constructeur par défaut
	 * "private" car non instanciable sous cette forme
	 */
	private ManageForm() {
		super();
	}
		
	/**
	 * Constructeur par defaut, permet d'afficher la fenêtre de management de l'application.
	 * @param stage
	 */
	public ManageForm(Stage stage) {
		this();
		this.stage = stage;
		try {
			this.stage.setTitle("Manage");
			Parent root = FXMLLoader.load(ManageForm.class.getResource("ManageForm.fxml"));
			this.stage.setScene(new Scene(root));
			this.stage.show();
			
					
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

		
	
	
	
}
