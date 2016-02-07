package mokuroku.tabs.interfaceParts;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Rebecca Lee
 *
 */
public class ItemTile extends BorderPane {
	
	public ItemTile() {
		this.setCenter(new Rectangle(200, 200, Color.LIGHTBLUE));
		this.setRight(new Rectangle(150, 200, Color.LIGHTGREY));
	}
	
}
