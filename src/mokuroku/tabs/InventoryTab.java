package mokuroku.tabs;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import mokuroku.DBConnection;
import mokuroku.tabs.interfaceParts.MokuStockItem;
import mokuroku.tabs.interfaceParts.NewItemDialog;

/**
 * @author Rebecca Lee
 *
 */
public class InventoryTab extends Tab {
	
	InventoryTab self = this;
	Stage window;
	
	DBConnection c;
	int iID;
	private TilePane tilePane = new TilePane();

	public InventoryTab(DBConnection c, int iID, Stage parent) {
		this.window = parent;
		this.c = c;
		this.iID = iID;
		setText("Inventory");
		BorderPane container = new BorderPane();
		
		// Top row with New button
		Button btnNewItem = new Button("+New Item");
		BorderPane topBar = new BorderPane();
		topBar.setRight(btnNewItem);
		BorderPane.setMargin(btnNewItem, new Insets(10, 10, 10, 10));
		container.setTop(topBar);
		
		// Bottom half with all the item tiles
		ScrollPane scrollBar = new ScrollPane();
		tilePane.setPadding(new Insets(30, 30, 30, 30));
		tilePane.setVgap(30);
		tilePane.setHgap(50);
		tilePane.setPrefColumns(3);
		
		tilePane.getChildren().addAll(c.getInventoryItems(iID, self));
		
		// Action Listener for btnNewItem
		btnNewItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	
            	Dialog<MokuStockItem> dialog = new NewItemDialog(self);
            	Optional<MokuStockItem> newItem = dialog.showAndWait();
            	if (newItem.isPresent()) {
            		MokuStockItem item = newItem.get();
            		int id = c.addItem(iID, item);
            		item.setItemID(id); // set item id that has been retrieved from database
            		tilePane.getChildren().add(item);
            	}
            	
            }
        }); 
		
		scrollBar.setContent(tilePane);
		container.setCenter(scrollBar);
		setContent(container);
	}
	
	public void update() {
		tilePane.getChildren().clear();
		tilePane.getChildren().addAll(c.getInventoryItems(iID, self));
	}
	
	public void updateItem(int itemID, MokuStockItem item) {
		c.update(itemID, item);
		update();
	}

	public void deleteItem(int itemID) {
		c.delete(itemID);
		update();
	}
	
	public Stage getWindow() {
		return window;
	}
}
