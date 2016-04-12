package mokuroku.tabs.interfaceParts;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author Rebecca Lee
 *
 */
public class MokuStockItem extends BorderPane{
	
	protected String name;
	protected String description;
	protected double price;
	protected int inStock;
	//protected String imagrUrl;
	
	public MokuStockItem(String name, String description, double price, int inStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		
		// set background colour with CSS
		getStyleClass().add("itemTile");		
		
		// picture square
		setCenter(new Rectangle(200, 200, Color.LIGHTBLUE));
		
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
		
		setRight(content);
	}
	
	public void edit(String name, String description, double price, int inStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
	}
	
	public String getName() {
		return name;
	}
	
	private String formatPrice(double cost) {
		String result = String.format("%.2f", cost);
		return "$" + result;
	}
}
