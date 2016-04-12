package mokuroku;
	
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Rebecca Lee
 *
 */
public class MokuInit extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Starting scene
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
			
			// New scene
			GridPane gridNew = new GridPane();
			gridNew.setAlignment(Pos.CENTER);
			gridNew.setHgap(10);
			gridNew.setVgap(20);
			
			Text titleNew = new Text("Create New Inventory");
			titleNew.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			gridNew.add(titleNew, 0, 0, 2, 1);
			
			Scene newScene = new Scene(gridNew, 440, 240);
			
			btnNewInventory.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	// Goes to new inventory scene, prompts user to enter a name for the new inventory
			        primaryStage.setScene(newScene);
			    }
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// http://www.tutorialspoint.com/sqlite/sqlite_java.htm
//		Connection c = null;
//	    try {
//	      Class.forName("org.sqlite.JDBC");
//	      c = DriverManager.getConnection("jdbc:sqlite:MokuSessions.db");
//	    } catch ( Exception e ) {
//	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	      System.exit(0);
//	    }
//	    System.out.println("Opened database successfully");
		launch(args);
	}
}
