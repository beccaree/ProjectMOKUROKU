package mokuroku;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
			
			Label newName = new Label("Name:");
			gridNew.add(newName, 0, 1);

			TextField nameTextField = new TextField();
			gridNew.add(nameTextField, 1, 1);
			
			Button btnCancel = new Button("Cancel");
			Button btnCreate = new Button("Create");
			
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().addAll(btnCancel, btnCreate);
			gridNew.add(hbBtn, 1, 2);
			
			Scene newScene = new Scene(gridNew, 440, 240);
			
			// Button event handlers
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
			        //primaryStage.setScene(newScene);
			    }
			});
			
			btnCancel.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle(ActionEvent e) {
					// back to start scene
					primaryStage.setScene(startScene);
				}
			});
			
			btnCreate.setOnAction(new EventHandler<ActionEvent> () {
				@Override
				public void handle(ActionEvent e) {
					// open main screen
					new MokuMain(0);
					primaryStage.close();					
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// http://www.tutorialspoint.com/sqlite/sqlite_java.htm
		Connection c = null;
		Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:MokuSessions.db");
	    
	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE IF NOT EXISTS Inventory " +
	                   "(id 	INT 	PRIMARY KEY," +
	                   " name 	TEXT 	NOT NULL)";
	    	stmt.executeUpdate(sql);
	    	
	    	sql = "CREATE TABLE IF NOT EXISTS Items" +
	    			"(id	INT," +
	    			" iid	INT		PRIMARY KEY," +
	    			" iname TEXT	NOT NULL," +
	    			" description TEXT," +
	    			" price DECIMAL(3, 2)," +
	    			" stock	INT		NOT NULL," +
	    			" FOREIGN KEY(id) REFERENCES Inventory(id))";
	    	stmt.executeUpdate(sql);
	    	
	    	sql = "CREATE TABLE IF NOT EXISTS Sales" +
	    			"(iid INT," +
	    			" iname TEXT NOT NULL," +
	    			" datetime INT)";
	    	
	    	stmt.close();
	    } catch ( Exception e ) {
		    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
    	System.out.println("Opened database and created tables successfully");
    	
		launch(args);
	}
}
