package mokuroku.tabs.interfaceParts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import mokuroku.DBConnection;
import mokuroku.tabs.InventoryTab;

/**
 * @author Rebecca Lee
 *
 */
public class MokuStockItem extends StackPane{
	
	private MokuStockItem self = this;
	
	protected int itemID;
	protected String name;
	protected String description;
	protected double price;
	protected int inStock;
	//protected String imagrUrl;
	
	protected boolean selected = false;
	
	public MokuStockItem(InventoryTab parent, int id, String name, String description, double price, int inStock) {
		this.itemID = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		
		BorderPane itemTile = new BorderPane();
		// set background colour with CSS
		itemTile.getStyleClass().add("itemTile");		
		
		// picture square
		itemTile.setCenter(new Rectangle(200, 200, Color.LIGHTBLUE));
		
		// description panel
		GridPane content = new GridPane();
		content.setHgap(10);
		content.setVgap(10);
		content.setPadding(new Insets(30, 20, 20, 10));
		content.setPrefSize(200, 150);
		
		Label displayName = new Label(name);
		displayName.setFont(Font.font(25));
		content.add(displayName, 0, 0);
		
		Label displayDescr = new Label(description);
		displayDescr.setFont(Font.font(15));
		content.add(displayDescr, 0, 1);
		
		Label displayPrice = new Label(formatPrice(price));
		displayPrice.setFont(Font.font(15));
		content.add(displayPrice, 0, 2);
		
		Label displayStock = new Label(inStock + " in stock");
		displayStock.setFont(Font.font(15));
		content.add(displayStock, 0, 3);
		
		itemTile.setRight(content);
		
		getChildren().add(itemTile);
		
		VBox buttons = new VBox(10);
		buttons.setAlignment(Pos.CENTER);
		Button btnEdit = new Button("Edit Item");
		btnEdit.getStyleClass().add("longButton");
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	// Open an edit item dialog
		        System.out.println("Edit clicked");
		    }
		});
		Button btnDelete = new Button("Delete Item");
		btnDelete.getStyleClass().add("longButton");
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	// delete this item from the database and update the inventory view
		    	parent.delete(self.itemID);
		    }
		});
		buttons.getChildren().addAll(btnEdit, btnDelete);
		
		// http://stackoverflow.com/questions/18597939/javafx-handle-mouse-event-anywhere
		addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	if (!selected) {
		    		selected = true;
		    		
		    		self.getChildren().add(buttons);
		    		// maybe dim background
		    		
		    	} else {
		    		selected = false;
		    		// hide buttons
		    		self.getChildren().remove(buttons);
		    	}
		    }
		});
	}
	
	public void edit(String name, String description, double price, int inStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
	}
	
	public void setItemID(int iid) {
		this.itemID = iid;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getStock() {
		return inStock;
	}
	
	private String formatPrice(double cost) {
		String result = String.format("%.2f", cost);
		return "$" + result;
	}
}
