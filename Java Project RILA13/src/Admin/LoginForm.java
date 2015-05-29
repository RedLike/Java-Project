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

public class LoginForm {

	@FXML // L'annotation @FXML permet de faire le lien avec des éléments IHM du fichier FXML.
	private TextField loginInput;
	@FXML
	private TextField passwordInput;
	@FXML
	private Button connexionButton;
	
	public LoginForm() {
	}
		
	/**
	 * Methode permettant d'afficher la fenêtre d'authentification.
	 * @param stage
	 * @throws IOException
	 */
	public void loadScreen(Stage stage) {
		try {
			stage.setTitle("Authentification");
			Parent root = FXMLLoader.load(Manage.class.getResource("LoginForm.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
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
		if(!loginInput.getText().isEmpty() && !passwordInput.getText().isEmpty()) {
			
			UserAdmin admin = new UserAdmin(loginInput.getText(), passwordInput.getText());
			if (admin.login()) {
				System.out.println("User Connected");
				System.out.println("Name : "+admin.getName());
				System.out.println("Login : "+admin.getLogin());
				
				Button button = (Button) e.getSource();
				Stage stage = (Stage) button.getScene().getWindow();
				stage.close();
				Manage manageForm = new Manage();
				manageForm.loadScreen(stage);
			} else {
				System.out.println("Bad Username/Password");
			}
				
		}
		else {
			System.out.println("empty field");
		}
		
	}

}
