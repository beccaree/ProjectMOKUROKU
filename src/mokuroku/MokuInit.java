package mokuroku;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mokuroku.tabs.MokuTabs;
import mokuroku.menus.FileMenu;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 * @author Rebecca Lee
 *
 */
public class MokuInit extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane pane = new BorderPane();
			
			// Initialize the tabs for the main application
			TabPane allTabs = new MokuTabs();
			pane.setCenter(allTabs);

			// Initialize the menu bar
			MenuBar menu = new MenuBar();
			Menu fileMenu = new FileMenu();
			menu.getMenus().addAll(fileMenu);
			pane.setTop(menu);
			
			Scene scene = new Scene(pane, 640, 480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Mokuroku");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent event) {
			        // Prompt user to save application if not saved already
			    }
			});
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
