package mokuroku;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mokuroku.menus.FileMenu;
import mokuroku.tabs.InventoryTab;
import mokuroku.tabs.MainTab;
import mokuroku.tabs.SalesTab;

public class MokuMain extends Stage {
	
	public MokuMain(DBConnection c, int inventoryID, String inventoryName) {
		if (inventoryID == 0) {
			inventoryID = c.getNewID("Inventory");
			//System.out.println("newid = " + inventoryID);
			c.addNewInventory(inventoryID, inventoryName);
		}
		
		BorderPane pane = new BorderPane();
		
		// Initialize the tabs for the main application
		TabPane allTabs = new TabPane();
		allTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		MainTab mainTab = new MainTab(c, inventoryID);
		InventoryTab inventoryTab = new InventoryTab(c, inventoryID, this, mainTab);
		SalesTab salesTab = new SalesTab();
		allTabs.getTabs().addAll(mainTab, inventoryTab, salesTab);
		pane.setCenter(allTabs);

		// Initialize the menu bar
		MenuBar menu = new MenuBar();
		Menu fileMenu = new FileMenu();
		menu.getMenus().addAll(fileMenu);
		pane.setTop(menu);
		
		Scene scene = new Scene(pane, 640, 480);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		setTitle("Mokuroku - " + inventoryName);
		setScene(scene);
		setMaximized(true);
		show();
	}
	
}
