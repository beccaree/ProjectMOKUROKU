package mokuroku.tabs;

import java.util.List;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mokuroku.tabs.interfaceParts.mokuObjects.MokuStockItem;

/**
 * @author Rebecca Lee
 *
 */
public class MokuTabs extends TabPane {
	
	public static List<MokuStockItem> stockList;

	public MokuTabs() {
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab mainTab = new MainTab();
		Tab inventoryTab = new InventoryTab();
		Tab salesTab = new SalesTab();
		getTabs().addAll(mainTab, inventoryTab, salesTab);
	}
	
}
