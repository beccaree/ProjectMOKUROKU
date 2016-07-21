package mokuroku.tabs.interfaceParts;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileItem extends StackPane {
	
	protected int itemID;
	protected String name;
	protected String description;
	protected double price;
	protected int inStock;
	protected String imgUrl;
	
	public TileItem(int itemID, String name, String description, double price, int stock, String image) {
		this.itemID = itemID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = stock;
		this.imgUrl = image;
		
		// picture square
		Rectangle img = new Rectangle(200, 200, Color.LIGHTBLUE);
		if (imgUrl.length() > 0) {
			File file = new File(imgUrl);
			Image picture = new Image(file.toURI().toString());
		    ImagePattern imagePattern = new ImagePattern(picture);
		    img.setFill(imagePattern);
		}
		
		// opaque name label at the bottom
		Rectangle labelBG = new Rectangle(200, 40, new Color(1, 1, 1, 0.8));
		Label lblName = new Label(name);
		lblName.setFont(Font.font(15));
		lblName.setTranslateY(-15);
		Label lblDescr = new Label(description);
		
		StackPane.setAlignment(labelBG, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(lblName, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(lblDescr, Pos.BOTTOM_CENTER);
		getChildren().addAll(img, labelBG, lblName, lblDescr);
		
	}

}
