package mokuroku;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Rebecca Lee
 * Every scene squeezed on this page to reduce spaghetti code
 *
 */
public class MokuInit extends Application {
	
	static DBConnection c;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Starting scene ------------------------------------------------------------------>
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(20);
			
			Text scenetitle = new Text("Welcome to Mokuroku");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);

			Button btnNewInventory = new Button("New Inventory");
			grid.add(btnNewInventory, 0, 2);

			Button btnOpenExisting = new Button("Open Existing");
			grid.add(btnOpenExisting, 1, 2);
			
			Scene startScene = new Scene(grid, 440, 240);
			startScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Choose your Inventory");
			primaryStage.setScene(startScene);
			primaryStage.show();
			
			// New scene ----------------------------------------------------------------------->
			GridPane gridNew = new GridPane();
			gridNew.setAlignment(Pos.CENTER);
			gridNew.setHgap(10);
			gridNew.setVgap(20);
			
			Text titleNew = new Text("Create New Inventory");
			titleNew.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			gridNew.add(titleNew, 0, 0, 2, 1);
			
			Label newName = new Label("Name:");
			gridNew.add(newName, 0, 1);

			TextField nameTextField = new TextField();
			gridNew.add(nameTextField, 1, 1);
			
			Button btnCancel = new Button("Cancel");
			Button btnCreate = new Button("Create");
			btnCreate.setDisable(true);
			nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				// disable create button if name text field is empty
				if (nameTextField.getText().isEmpty()) {
					btnCreate.setDisable(true);
				} else {
					btnCreate.setDisable(false);
				}
			});
			
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().addAll(btnCancel, btnCreate);
			gridNew.add(hbBtn, 1, 2);
			
			Scene newScene = new Scene(gridNew, 440, 240);
			
			// Open Existing scene -------------------------------------------------------------->
			GridPane gridOpen = new GridPane();
			gridOpen.setAlignment(Pos.CENTER);
			gridOpen.setHgap(10);
			gridOpen.setVgap(20);
			
			Text titleOpen = new Text("Open Existing Inventory");
			titleOpen.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			gridOpen.add(titleOpen, 0, 0, 2, 1);
			
			Label lblSelect = new Label("Select an inventory:");
			gridOpen.add(lblSelect, 0, 1);
			
			ListView<String> list = new ListView<String>();
			ObservableList<String> items = FXCollections.observableArrayList(c.getInventories());
			list.setPrefHeight(80);
			list.setItems(items);
			gridOpen.add(list, 0, 2);
			
			Button btnOpen = new Button("Open");
			Button btnCancel2 = new Button("Cancel");
			
			HBox hbBtn2 = new HBox(10);
			hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn2.getChildren().addAll(btnCancel2, btnOpen);
			gridOpen.add(hbBtn2, 0, 3);
			
			Scene openScene = new Scene(gridOpen, 440, 240);
			
			// Button event handlers ------------------------------------------------------------>
			btnNewInventory.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	// Goes to new inventory scene, prompts user to enter a name for the new inventory
			        primaryStage.setScene(newScene);
			    }
			});
			
			btnOpenExisting.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	// Goes to open existing scene
			        primaryStage.setScene(openScene);
			    }
			});
			
			EventHandler<ActionEvent> actionCancel = new EventHandler<ActionEvent> () {
				@Override
				public void handle(ActionEvent e) {
					// back to start scene
					primaryStage.setScene(startScene);
				}
			};
			btnCancel.setOnAction(actionCancel);
			btnCancel2.setOnAction(actionCancel);
			
			btnCreate.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle(ActionEvent e) {
					// open main screen
					new MokuMain(c, 0, nameTextField.getText());
					primaryStage.close();					
				}
			});
			
			btnOpen.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle(ActionEvent e) {
					int id = 0;
					// open main screen with initialized values
					String selected = list.getSelectionModel().getSelectedItem();
					Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(selected);
				    while(m.find()) {
				    	id = Integer.parseInt(m.group(1));    
				    }
				    String name = selected.substring(selected.indexOf(" ") + 1, selected.length());
					new MokuMain(c, id, name);
					primaryStage.close();					
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// initialize database connection
		c = new DBConnection();
		c.initDB();
    	
		launch(args);
	}
}
