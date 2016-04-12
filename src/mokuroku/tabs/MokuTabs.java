package mokuroku.tabs;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Rebecca Lee
 *
 */
public class MokuTabs extends TabPane {

	public MokuTabs() {
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab mainTab = new MainTab();
		Tab inventoryTab = new InventoryTab();
		Tab salesTab = new SalesTab();
		getTabs().addAll(mainTab, inventoryTab, salesTab);
	}
	
}
