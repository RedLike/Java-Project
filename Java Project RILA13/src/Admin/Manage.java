package Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;




import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;


public class Manage {
	
	@FXML 
	private TilePane titlePane;
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
	
	private List<ToggleButton> buttonsCategories;
	
	
	public Manage() {
	}
		

	public void loadScreen(Stage stage) {
		try {
			stage.setTitle("Manage");
			Parent root = FXMLLoader.load(Manage.class.getResource("Manage.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
			
			
			initCategories();
//			loadTitlePane();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadTitlePane() {
		titlePane.setId("test");

	}
	
	private void initCategories() {
		buttonsCategories = new ArrayList<>();
//				
		for(Category categorie : Category.values()) {
			ToggleButton button = createCategorieToggleButton(categorie);
			buttonsCategories.add(button); 
			System.out.println(categorie);
		}
		
		// on ajoute tous les boutons des catégories au flowCategories.
		
		System.out.println(buttonsCategories.size());
		
		flowCategories.getChildren().addAll(buttonsCategories);
//		flowCategories.getChildren().addAll(buttonsCategories);
//		
//		// activation du bouton de la catégorie TOUS par défaut : 
//		buttonsCategories.get(0).getStyleClass().add("active");
	}
	
	private ToggleButton createCategorieToggleButton(Category categorie) {
		ToggleButton button = new ToggleButton(categorie.getLabel());
		button.setPrefSize(100, 40);
		button.getStyleClass().add("bevel-grey");
		// exemple d'implémentation avec Java8
		button.setOnAction((ActionEvent event) -> {
				resetActiveCategorie();
				ToggleButton buttonClicked = (ToggleButton) event.getSource();
				buttonClicked.getStyleClass().add("active");
				String labelCategorie = buttonClicked.getText();
				Category selectedCategorie = Category.getCategorie(labelCategorie);
				filterListView(selectedCategorie);
		});
		// équivalent de : 
//		button.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				resetActiveCategorie();
//				ToggleButton buttonClicked = (ToggleButton) event.getSource();
//				buttonClicked.getStyleClass().add("active");
//				String labelCategorie = buttonClicked.getText();
//				Categorie selectedCategorie = Categorie.getCategorie(labelCategorie);
//				filterListView(selectedCategorie);
//			}
//		});
		return button;
	}
	
	
	private void resetActiveCategorie() {
		// suppression de la classe CSS 'active'
		for(ToggleButton toggleButton : buttonsCategories) {
			toggleButton.getStyleClass().remove("active");
		}
	}
	
	private void filterListView(Category selectedCategorie) {
		// nettoyage des éléments dans la liste :
		listObject.getItems().clear();
		
	}
	
	
	
	
	
	
	
}
