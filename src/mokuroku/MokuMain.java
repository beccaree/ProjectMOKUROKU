package mokuroku;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mokuroku.menus.FileMenu;
import mokuroku.tabs.MokuTabs;

public class MokuMain extends Stage {
	
	public MokuMain(int inventoryID) {
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
		
		setTitle("Mokuroku");
		setScene(scene);
		setMaximized(true);
		show();
	}
	
}
